(ns clj-dcss.char
  (:require [instaparse.core :as insta]
            [clojure.java.io :as io]
            [clojure.string  :as string]))

(def ^:private char->tree
  (-> "char.ebnf" io/resource slurp insta/parser))

(defn- tree->map [x]
  (insta/transform
    {:text str
     :number #(Integer/parseInt %)
     :resist-val (fn [arg] (string/replace arg " " ""))
     :meta  (fn [& args] [:meta (into {} args)])
     :stats (fn [& args] [:stats (into {} args)])
     :resistances (fn [& args] [:resistances (into {} args)])
     :char  (fn [& args] (into {} args))}
    x))

(defn parse-char
  "Parse a DCSS character file (char dump, morgue file) into a Clojure map."
  [x] (-> x char->tree tree->map))

(defn- count-piety-stars [points]
  (->> points
       (map str)
       (filter (partial = "*"))
       count))

(defn count-piety [x]
  (update-in x [:stats :piety] count-piety-stars))

(defn move-godtitle [x]
  (-> x
      (assoc :godtitle (get-in x [:stats :godtitle]))
      (update-in [:stats] #(dissoc % :godtitle))))

(defn process-char
  "Process a DCSS character map, making it more useful and normalized."
  [x] (-> x count-piety move-godtitle))

(defn parse-and-process-char
  "Parse and process a DCSS character file (char dump, morgue file)."
  [x] (-> x parse-char process-char))
