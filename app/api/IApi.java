package api;

public interface IApi {
    /**
     * A no-op operator.
     */
    public void ping();

    /**
     * Gets API version string.
     * 
     * @return
     */
    public String version();

    /**
     * Adds value to a counter.
     * 
     * @param counterName
     * @param value
     * @param timestampMillisec
     * @return
     */
    public boolean add(String counterName, long value, long timestampMillisec);

    /**
     * Sets value to a counter.
     * 
     * @param counterName
     * @param value
     * @param timestampMillisec
     * @return
     */
    public boolean set(String counterName, long value, long timestampMillisec);

    /**
     * Tags a counter.
     * 
     * @param counterName
     * @param tags
     * @return
     */
    public boolean tag(String counterName, String[] tags);

    /**
     * Untags a counter.
     * 
     * @param counterName
     * @param tags
     * @return
     */
    public boolean untag(String counterName, String[] tags);
}
