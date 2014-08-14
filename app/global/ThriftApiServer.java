package global;

import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.server.TServer;

import play.Logger;
import thrift.ThriftApiHandler;
import thrift.server.IServerFactory;
import thrift.server.ThreadedSelectorServerFactory;
import thrift.server.ThreadedServerFactory;

import com.github.rtstats.thrift.TFrontApi;

public class ThriftApiServer {

    // public final static int DEFAULT_THRIFT_SERVER_PORT = 9090;
    public final static int DEFAULT_THRIFT_MAX_FRAME_SIZE = 1048576;
    public final static long DEFAULT_THRIFT_MAX_READ_BUFFER_SIZE = 16777216;
    public final static int DEFAULT_THRIFT_CLIENT_TIMEOUT = 5000;

    private int port = 0;
    private boolean nonBlockingServer = true;
    private int clientTimeoutMillisecs = DEFAULT_THRIFT_CLIENT_TIMEOUT;
    private int maxFrameSize = DEFAULT_THRIFT_MAX_FRAME_SIZE;
    private long maxReadBufferSize = DEFAULT_THRIFT_MAX_READ_BUFFER_SIZE;
    private TServer server;

    public ThriftApiServer() {
    }

    // public int getPort() {
    // return port;
    // }
    //
    // public ThriftApiServer setPort(int port) {
    // this.port = port;
    // return this;
    // }

    public int getMaxFrameSize() {
        return maxFrameSize;
    }

    public ThriftApiServer setMaxFrameSize(int maxFrameSize) {
        this.maxFrameSize = maxFrameSize;
        return this;
    }

    public long getMaxReadBufferSize() {
        return maxReadBufferSize;
    }

    public ThriftApiServer setMaxReadBufferSize(long maxReadBufferSize) {
        this.maxReadBufferSize = maxReadBufferSize;
        return this;
    }

    public int getClientTimeoutMillisecs() {
        return clientTimeoutMillisecs;
    }

    public ThriftApiServer setClientTimeoutMillisecs(int clientTimeoutMillisecs) {
        this.clientTimeoutMillisecs = clientTimeoutMillisecs;
        return this;
    }

    public boolean isNonBlockingServer() {
        return nonBlockingServer;
    }

    public ThriftApiServer setNonBlockingServer(boolean nonBlockingServer) {
        this.nonBlockingServer = nonBlockingServer;
        return this;
    }

    public void start() {
        /* Thrift's port is defined via command line: -Dthrift.port=<value> */
        String portStr = System.getProperty("thrift.port");
        try {
            port = Integer.parseInt(portStr);
        } catch (Exception e) {
            port = 0;
        }
        if (port > 0) {
            // only start Thrift interface if thrift.port is defined.
            TProcessor processor = new TFrontApi.Processor<TFrontApi.Iface>(new ThriftApiHandler());
            IServerFactory serverFactory = nonBlockingServer ? new ThreadedSelectorServerFactory(
                    port, processor, clientTimeoutMillisecs, maxFrameSize, maxReadBufferSize)
                    : new ThreadedServerFactory(port, processor, clientTimeoutMillisecs,
                            maxFrameSize);
            Logger.info("Starting Thrift API server on port " + port);
            _start(serverFactory);
        }
    }

    private int _counter = 0;

    private void _start(final IServerFactory serverFactory) {
        try {
            server = serverFactory.createServer();
        } catch (TException e) {
            throw new RuntimeException(e);
        }
        _counter++;
        Thread serverThread = new Thread("FrontApi Thrift Server #" + _counter) {
            public void run() {
                try {
                    server.serve();
                } catch (Throwable e) {
                    String msg = "Thrift server crashed: " + e.getMessage();
                    Logger.error(msg, e);
                    server.stop();
                    _start(serverFactory);
                }
            }
        };
        serverThread.setDaemon(true);
        serverThread.start();
    }

    public void destroy() {
        if (server != null) {
            server.stop();
            server = null;
        }
    }
}
