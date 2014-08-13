FrontAPI
========

RtStats - FrontAPI: provide APIs to collect counter data.

INSTALLATION
------------
See [INSTALL.md](INSTALL.md)

APIs
----
Provided via REST-like form. Data transferred in JSON format.

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


2014-08-10: v1.0.0
------------------
First release: 4 APIs `add`, `set`, `tag`, `untag`.
