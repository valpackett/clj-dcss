(ns clj-dcss.log
  (:require [instaparse.core :as insta])
  (:use clojure.walk))

(def ^:private log->tree
  (insta/parser
    "entry = pair {<':'> pair}
     pair = key <'='> value
     key = text
     value = text | number
     <letter> = #'[^=:]'
     text = {letter | '::'}
     number = #'[0-9-\\+]+'"))

(defn- tree->map [x]
  (insta/transform
    {:text str
     :number #(Integer/parseInt %)
     :pair (fn [& args] (mapv last args))
     :entry (fn [& args] (into {} args))}
    x))

(defn parse-log
  "Parses a DCSS log entry (eg. 'v=0.13-a0:lv=0.1:name=user:race=Naga:cls=Warper:char=NaWr:xl=2...')
   into a Clojure map."
  [x] (-> x log->tree tree->map keywordize-keys))
