package bo;

public interface IMetadataDao {

    /**
     * Adds a counter to
     * 
     * @param counterName
     */
    public void addCounter(String counterName);

    /**
     * Tags a counter.
     * 
     * @param counterName
     * @param tags
     */
    public void tagCounter(String counterName, String[] tags);

    /**
     * Untags a counter.
     * 
     * @param counterName
     * @param tags
     */
    public void untagCounter(String counterName, String[] tags);
}
