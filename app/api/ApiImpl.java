package api;

import org.apache.commons.lang3.StringUtils;

import bo.IMetadataDao;

import com.github.ddth.plommon.utils.PlayAppUtils;
import com.github.ddth.tsc.ICounter;
import com.github.ddth.tsc.ICounterFactory;

public class ApiImpl implements IApi {

    private ICounterFactory counterFactory;
    private IMetadataDao metadataDao;

    public ApiImpl setCounterFactory(ICounterFactory counterFactory) {
        this.counterFactory = counterFactory;
        return this;
    }

    protected ICounterFactory getCounterFactory() {
        return counterFactory;
    }

    public ApiImpl setMetadataDao(IMetadataDao metadataDao) {
        this.metadataDao = metadataDao;
        return this;
    }

    protected IMetadataDao getMetadataDao() {
        return metadataDao;
    }

    public void init() {

    }

    public void destroy() {

    }

    /*----------------------------------------------------------------------*/

    /**
     * {@inheritDoc}
     */
    @Override
    public void ping() {
        // EMPTY
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String version() {
        return PlayAppUtils.appConfigString("app.version");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(String counterName, long value, long timestampMillisec) {
        ICounter counter = !StringUtils.isBlank(counterName) ? counterFactory
                .getCounter(counterName) : null;
        if (counter != null) {
            counter.add(timestampMillisec, value);
            metadataDao.addCounter(counterName);
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean set(String counterName, long value, long timestampMillisec) {
        ICounter counter = !StringUtils.isBlank(counterName) ? counterFactory
                .getCounter(counterName) : null;
        if (counter != null) {
            counter.set(timestampMillisec, value);
            metadataDao.addCounter(counterName);
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean tag(String counterName, String[] tags) {
        if (tags != null) {
            ICounter counter = !StringUtils.isBlank(counterName) ? counterFactory
                    .getCounter(counterName) : null;
            if (counter != null) {
                metadataDao.tagCounter(counterName, tags);
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean untag(String counterName, String[] tags) {
        if (tags != null) {
            ICounter counter = !StringUtils.isBlank(counterName) ? counterFactory
                    .getCounter(counterName) : null;
            if (counter != null) {
                metadataDao.untagCounter(counterName, tags);
                return true;
            }
        }
        return false;
    }
}
