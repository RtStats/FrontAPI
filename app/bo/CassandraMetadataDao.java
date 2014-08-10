package bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.ArrayUtils;

import play.Logger;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.github.ddth.tsc.cassandra.internal.CassandraUtils;

public class CassandraMetadataDao implements IMetadataDao {

    private List<String> hosts = new ArrayList<String>();
    private String keyspace;
    private int port = 9042;
    private Cluster cluster;
    private Session session;

    private PreparedStatement pTagCounter, pUntagCounter, pAddToList, pRemoveFromList;

    public CassandraMetadataDao addHost(String host) {
        hosts.add(host);
        return this;
    }

    public String getHost() {
        return hosts.size() > 0 ? hosts.get(0) : null;
    }

    public CassandraMetadataDao setHost(String host) {
        hosts.clear();
        hosts.add(host);
        return this;
    }

    public Collection<String> getHosts() {
        return this.hosts;
    }

    public CassandraMetadataDao setHosts(Collection<String> hosts) {
        this.hosts.clear();
        if (hosts != null) {
            this.hosts.addAll(hosts);
        }
        return this;
    }

    public CassandraMetadataDao setHosts(String[] hosts) {
        this.hosts.clear();
        if (hosts != null) {
            for (String host : hosts) {
                this.hosts.add(host);
            }
        }
        return this;
    }

    public int getPort() {
        return port;
    }

    public CassandraMetadataDao setPort(int port) {
        this.port = port;
        return this;
    }

    public String getKeyspace() {
        return keyspace;
    }

    public CassandraMetadataDao setKeyspace(String keyspace) {
        this.keyspace = keyspace;
        return this;
    }

    public CassandraMetadataDao init() {
        if (cluster == null) {
            cluster = Cluster.builder()
                    .addContactPoints(hosts.toArray(ArrayUtils.EMPTY_STRING_ARRAY)).withPort(port)
                    .build();
        }

        // init connection
        session = cluster.connect(keyspace);

        // init statements
        pTagCounter = session.prepare("INSERT INTO tsc_tag_counter (t, c, v) VALUES (?, ?, ?)");
        pUntagCounter = session.prepare("DELETE FROM tsc_tag_counter WHERE t=? AND c=?");
        pAddToList = session.prepare("INSERT INTO tsc_all_list (n, k, v) VALUES (?, ?, ?)");
        pRemoveFromList = session.prepare("DELETE FROM tsc_all_list WHERE n=? AND k=?");

        return this;
    }

    public void destroy() {
        if (session != null) {
            try {
                session.close();
            } catch (Exception e) {
                Logger.warn(e.getMessage(), e);
            } finally {
                session = null;
            }
        }

        if (cluster != null) {
            try {
                cluster.close();
            } catch (Exception e) {
                Logger.warn(e.getMessage(), e);
            } finally {
                cluster = null;
            }
        }
    }

    /*--------------------------------------------------------------------------------*/
    private final static String LIST_TAGS = "all_tags";
    private final static String LIST_COUNTERS = "all_counters";
    private final static ConcurrentHashMap<String, Boolean> allCounters = new ConcurrentHashMap<String, Boolean>();
    private final static ConcurrentHashMap<String, Boolean> allTags = new ConcurrentHashMap<String, Boolean>();

    private void addTag(String tag) {
        if (allTags.putIfAbsent(tag, Boolean.TRUE) == null) {
            final int v = (int) (System.currentTimeMillis() / 1000);
            CassandraUtils.executeNonSelect(session, pAddToList, LIST_TAGS, tag, v);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCounter(String counter) {
        if (allCounters.putIfAbsent(counter, Boolean.TRUE) == null) {
            final int v = (int) (System.currentTimeMillis() / 1000);
            CassandraUtils.executeNonSelect(session, pAddToList, LIST_COUNTERS, counter, v);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tagCounter(String counterName, String[] tags) {
        addCounter(counterName);
        final int v = (int) (System.currentTimeMillis() / 1000);
        for (String tag : tags) {
            CassandraUtils.executeNonSelect(session, pTagCounter, tag, counterName, v);
            addTag(tag);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void untagCounter(String counterName, String[] tags) {
        addCounter(counterName);
        for (String tag : tags) {
            CassandraUtils.executeNonSelect(session, pUntagCounter, tag, counterName);
            addTag(tag);
        }
    }
}
