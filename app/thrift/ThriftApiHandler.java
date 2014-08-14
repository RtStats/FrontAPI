package thrift;

import global.Registry;

import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.thrift.TException;

import com.github.rtstats.thrift.TFrontApi;
import com.github.rtstats.thrift.TParamAddSet;

public class ThriftApiHandler implements TFrontApi.Iface {

    /**
     * {@inheritDoc}
     */
    @Override
    public void tping() throws TException {
        // EMPTY
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String tversion() throws TException {
        return Registry.api.version();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean tadd(TParamAddSet params) throws TException {
        long timestamp = params.timestampMillisec > 0 ? params.timestampMillisec : System
                .currentTimeMillis();
        return Registry.api.add(params.counterName, params.value, timestamp);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onewayAdd(TParamAddSet params) throws TException {
        tadd(params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean tset(TParamAddSet params) throws TException {
        long timestamp = params.timestampMillisec > 0 ? params.timestampMillisec : System
                .currentTimeMillis();
        return Registry.api.set(params.counterName, params.value, timestamp);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onewaySet(TParamAddSet params) throws TException {
        tset(params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean ttag(String counterName, Set<String> tags) throws TException {
        return Registry.api.tag(counterName,
                tags != null ? tags.toArray(ArrayUtils.EMPTY_STRING_ARRAY)
                        : ArrayUtils.EMPTY_STRING_ARRAY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onewayTag(String counterName, Set<String> tags) throws TException {
        ttag(counterName, tags);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean tuntag(String counterName, Set<String> tags) throws TException {
        return Registry.api.untag(counterName,
                tags != null ? tags.toArray(ArrayUtils.EMPTY_STRING_ARRAY)
                        : ArrayUtils.EMPTY_STRING_ARRAY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onewayUntag(String counterName, Set<String> tags) throws TException {
        tuntag(counterName, tags);
    }
}
