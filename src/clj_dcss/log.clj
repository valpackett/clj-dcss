(ns clj-dcss.log
  (:require [instaparse.core :as insta]
            [clj-dcss.version :as v])
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

(defn- fix-timestamp [x]
  (format "%s-%s" (.substring x 0 8) (.substring x 8 14)))

(defn log->url
  "Turns a parsed DCSS log entry into a morgue URL, given
  a function that returns a host URL based on the entry."
  [f x]
  (format "%s%s/morgue-%s-%s.txt" (f x) (:name x) (:name x) (fix-timestamp (:end x))))

(defn log->cao-url
  "Calls log->url for crawl.akrasiac.org"
  [x] (log->url (constantly "http://crawl.akrasiac.org/rawdata/") x))

(defn log->cdo-url
  "Calls log->url for crawl.develz.org"
  [x] (log->url (fn [l] (format "http://crawl.develz.org/morgues/%s/" (v/normalize (:v l)))) x))

(defn log->cszo-url
  "Calls log->url for crawl.s-z.org"
  [x] (log->url (constantly "http://dobrazupa.org/morgue/") x))

(defn log->clan-url
  "Calls log->url for crawl.lantea.net"
  [x] (log->url (constantly "http://crawl.lantea.net/crawl/morgue/") x))

(defn log->rhf-url
  "Calls log->url for rl.heh.fi"
  [x] (log->url (constantly "http://rl.heh.fi/morgue/") x))
