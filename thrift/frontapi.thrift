namespace java com.github.rtstats.thrift

/* Parameters for add/set methods */
struct TParamAddSet {
    1: required string counterName,
    2: optional i64 value = 1,
    3: optional i64 timestampMillisec = 0,
}

service TFrontApi {
    /* No-op */
    void tping(),
    
    /* Returns API server's version information */
    string tversion(),
    
    /* add value to a counter, {@code false} is returned if the counter does not exist or not configured */
    bool tadd(1:TParamAddSet params),
    
    /* "oneway" version of method {@code add} */
    oneway void onewayAdd(1:TParamAddSet params),
    
    /* set value to a counter, {@code false} is returned if the counter does not exist or not configured */
    bool tset(1:TParamAddSet params),
    
    /* "oneway" version of method {@code set} */
    oneway void onewaySet(1:TParamAddSet params),
    
    /* tag a counter */
    bool ttag(1:string counterName, 2:set<string> tags),
    
    /* "oneway" version of method {@code tag} */
    oneway void onewayTag(1:string counterName, 2:set<string> tags),
    
    /* untag a counter */
    bool tuntag(1:string counterName, 2:set<string> tags),
    
    /* "oneway" version of method {@code untag} */
    oneway void onewayUntag(1:string counterName, 2:set<string> tags),
}
