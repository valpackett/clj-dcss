(ns clj-dcss.version)

(defn full->major
  "Extracts the major version (like 0.12) from a full DCSS version (like 0.12.1-omgwtf.)"
  [x] (first (re-find #"(\d+[.]\d+)" x)))

(defn trunk?
  "Checks if given (full or major) DCSS version is trunk.
  To update trunk version without updating code, set the CRAWL_TRUNK environment variable."
  [x] (= (or (System/getenv "CRAWL_TRUNK") "0.14") (full->major x)))

(defn normalize
  "Extracts the major version using full->major, but returns 'trunk' if it's trunk."
  [x] (if (trunk? x) "trunk" (full->major x)))
