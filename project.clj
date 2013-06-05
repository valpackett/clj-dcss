(defproject clj-dcss "0.1.0-SNAPSHOT"
  :description "A Clojure library for parsing and processing Dungeon Crawl Stone Soup data."
  :url "https://github.com/myfreeweb/clj-dcss"
  :license {:name "WTFPL"
            :url "http://www.wtfpl.net/about/"}
  :dependencies [[org.clojure/clojure "1.5.0"]
                 [instaparse "1.1.0"]]
  :profiles {:dev {:dependencies [[midje "1.5.0"]
                                  [lein-release "1.0.0"]]}}
  :plugins [[lein-midje "3.0.0"]
            [lein-release "1.0.0"]]
  :bootclasspath true
  :lein-release {:deploy-via :lein-deploy}
  :repositories [["snapshots" {:url "https://clojars.org/repo" :creds :gpg}]
                 ["releases"  {:url "https://clojars.org/repo" :creds :gpg}]])
