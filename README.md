FrontAPI
========

RtStats - FrontAPI: provide APIs to collect counter data.

INSTALLATION
------------
See [INSTALL.md](INSTALL.md)

APIs
----
Provided via REST-like form. Data transferred in JSON format.

> All APIs, except for `ping` and `version`, are accessible only via POST method!

** API: /api/ping **

- No-op, just to test if server is available.


** API: /api/version **

- Return API server's version information.
- Parameters: none


** API: /api/add **

- Add value to a counter.
- Parameters:
  - `authkey`: [String] authentication key for server to authenticate the client (not used as of v1.0.0).
  - `c`: [String] counter name.
  - `v`: (optional)[Long] value to add, default value is `1`.
  - `t`: (optional)[Long] UNIX-timestamp (in millisec) to add, default value is current timestamp at server.


** API: /api/set **

- Set value to a counter.
- Parameters:
  - `authkey`: [String] authentication key for server to authenticate the client (not used as of v1.0.0).
  - `c`: [String] counter name.
  - `v`: [Long] value to add.
  - `t`: (optional)[Long] UNIX-timestamp (in millisec) to add, default value is current timestamp at server.


** API: /api/tag **

- Tag a counter.
- Parameters:
  - `authkey`: [String] authentication key for server to authenticate the client (not used as of v1.0.0).
  - `c`: [String] counter name.
  - `tags`: [Array of String] list of tag names.


** API: /api/untag **

- Untag a counter.
- Parameters:
  - `authkey`: [String] authentication key for server to authenticate the client (not used as of v1.0.0).
  - `c`: [String] counter name.
  - `tags`: [Array of String] list of tag names.


2014-08-15: v1.0.1
------------------
- 2 more APIs: `ping` and `version`.
- Added Thrift interface.


2014-08-10: v1.0.0
------------------
First release: 4 APIs `add`, `set`, `tag`, `untag`.
