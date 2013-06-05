(ns clj-dcss.char
  (:use [instaparse.core :as insta]))

(def ^:private char->tree
  (insta/parser
    "char = meta {<whitespace>} [scoreline beginning godsummary <text> ending <text>] summary stats

     <whitespace> = #'\\s*'
     <letter> = #'[0-9a-zA-Z\\.,-:!\\(\\)]'
     text = {letter | whitespace}
     number = #'[0-9-\\+]+'

     meta = <'Dungeon Crawl Stone Soup version '> version <' ('> interface <') character file.'>
     version = text
     interface = text

     <scoreline> = score <text '(level' text ''>
     score = number

     <beginning> = {<whitespace>} <'Began' text ' on '> startdate <'.\n'>
     startdate = text

     <godsummary> = {<whitespace>} <'Was ' ('a' | 'an' | 'the') ' '> godtitle <' of ' text '.'>
     godtitle = text

     <ending> = {<whitespace>} <text ' on '> enddate <('.' | '!') '\n'>
     enddate = text

     <summary> = <'.\n\n'> name <' the '> title <' ('> character <')' {whitespace} 'Turns: '> turns <', Time: '> time
     name = text
     title = text
     character = text
     turns = number
     time = text

     stats = stats1 stats2 stats3

     <stats1> = {<whitespace>} <'HP'> {<whitespace>} hp <'/'> mhp [<whitespace '('> mmhp <')'>] {<whitespace>} <'AC'> {<whitespace>} ac {<whitespace>} <'Str'> {<whitespace>} str {<whitespace>} <'XL:'> {<whitespace>} xl
     hp = number
     mhp = number
     mmhp = number
     ac = number
     str = number
     xl = number

     <stats2> = {<whitespace>} <'MP'> {<whitespace>} mp <'/'> mmp [<whitespace '('> mmmp <')'>] {<whitespace>} <'EV'> {<whitespace>} ev {<whitespace>} <'Int'> {<whitespace>} int {<whitespace>} <'God: '> {<whitespace>} [god [<' ['> piety <']'>]]
     mp = number
     mmp = number
     mmmp = number
     ev = number
     int = number
     god = text
     piety = #'[\\.\\*]+'

     <stats3> = {<whitespace>} <'Gold'> {<whitespace>} gold {<whitespace>} <'SH'> {<whitespace>} sh {<whitespace>} <'Dex'> {<whitespace>} dex {<whitespace>} <'Spells:'> {<whitespace>} spells <' memorised,'> {<whitespace>} spell-levels-left <' levels left'>
     gold = number
     sh = number
     dex = number
     spells = number
     spell-levels-left = number
     "))

(defn- tree->map [x]
  (insta/transform
    {:text str
     :number #(Integer/parseInt %)
     :meta  (fn [& args] [:meta (into {} args)])
     :stats (fn [& args] [:stats (into {} args)])
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

(defn process-char
  "Process a DCSS character map, making it more useful and normalized."
  [x] (-> x count-piety))

(defn parse-and-process-char
  "Parse and process a DCSS character file (char dump, morgue file)."
  [x] (-> x parse-char process-char))
