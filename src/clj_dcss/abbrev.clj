(ns clj-dcss.abbrev
  (:use clj-dcss.util)
  (:require [clojure.set :as set]
            [clojure.string :as str]))

(def species-map
  {"Centaur"        "Ce"
   "Deep Dwarf"     "DD"
   "Deep Elf"       "DE"
   "Demigod"        "Dg"
   "Demonspawn"     "Ds"
   "Djinni"         "Dj"
   "Draconian"      "Dr"
   "Elf"            "El" ; Removed in 0.3
   "Felid"          "Fe"
   "Gargoyle"       "Gr"
   "Ghoul"          "Gh"
   "Gnome"          "Gn" ; Removed in 0.6
   "Grey Elf"       "GE" ; Removed in 0.5
   "Grotesk"        "Gr"
   "Halfling"       "Ha"
   "High Elf"       "HE"
   "Hill Dwarf"     "HD" ; Removed in 0.3
   "Hill Orc"       "HO"
   "Human"          "Hu"
   "Kenku"          "Ke" ; Renamed to Tengu in 0.10
   "Kobold"         "Ko"
   "Lava Orc"       "LO"
   "Merfolk"        "Mf"
   "Minotaur"       "Mi"
   "Mountain Dwarf" "MD" ; Removed in 0.10
   "Mummy"          "Mu"
   "Naga"           "Na"
   "Octopode"       "Op"
   "Ogre"           "Og"
   "Ogre-mage"      "OM" ; Removed in 0.5
   "Sludge Elf"     "SE" ; Removed in 0.13
   "Spriggan"       "Sp"
   "Tengu"          "Te"
   "Troll"          "Tr"
   "Vampire"        "Vp"})
(def species-demap (set/map-invert species-map))
(def species (set (keys species-map)))
(def species-abbr (set (vals species-map)))
(defn species? [x] (contains? species x))

(def backgrounds-map
  {"Abyssal Knight"     "AK"
   "Air Elementalist"   "AE"
   "Artificer"          "Ar"
   "Assassin"           "As"
   "Arcane Marksman"    "AM"
   "Berserker"          "Be"
   "Chaos Knight"       "CK"
   "Conjurer"           "Cj"
   "Crusader"           "Cr" ; Renamed to Skald in 0.9
   "Death Knight"       "DK"
   "Earth Elementalist" "EE"
   "Enchanter"          "En"
   "Fighter"            "Fi"
   "Fire Elementalist"  "FE"
   "Gladiator"          "Gl"
   "Healer"             "He"
   "Hunter"             "Hu"
   "Ice Elementalist"   "IE"
   "Jester"             "Jr" ; April Fool's background.
   "Monk"               "Mo"
   "Necromancer"        "Ne"
   "Paladin"            "Pa" ; Removed in 0.8
   "Priest"             "Pr"
   "Reaver"             "Re" ; Removed in 0.8
   "Skald"              "Sk"
   "Stalker"            "St" ; Removed in 0.12
   "Summoner"           "Su"
   "Transmuter"         "Tm"
   "Thief"              "Th" ; Removed in 0.7
   "Venom Mage"         "VM"
   "Wanderer"           "Wn"
   "Warper"             "Wa"
   "Wizard"             "Wz"})
(def backgrounds-demap (set/map-invert backgrounds-map))
(def backgrounds (set (keys backgrounds-map)))
(def backgrounds-abbr (set (vals backgrounds-map)))
(defn background? [x] (contains? backgrounds x))

(def colors #{"Red" "White" "Green" "Yellow" "Grey" "Black" "Purple" "Mottled" "Pale"})
(defn color? [x] (contains? colors x))

(defn normalize-char
  "Normalizes any character string or abbreviation ('DEFE', 'Octopode Wizard', even 'Green DrEE')
  to a Clojure map with keys :color, :species and :background."
  [x] ;-(
  (let [words (apply list (str/split x #"\s")) ; XXX: pop works differently on vectors and lists
        r {}
        fword (first words)
        r (if (color? fword) (assoc r :color fword) r)
        words (if (color? fword) (pop words) words)
        fword (first words)
        r (->> [fword
                (str fword " " (second words))
                (get species-demap (first-two fword))]
               (filter species?) first
               (assoc r :species))
        lword (last words)
        r (->> [lword
                (str (penultimate words) " " lword)
                (get backgrounds-demap (last-two lword))]
               (filter background?) first
               (assoc r :background))]
    r))

(defn abbrev-char
  "Converts a full character string to an abbreviation (eg. 'Deep Elf Wizard' to 'DEWz')."
  [x]
  (let [n (normalize-char x)]
    (str (get species-map (:species n)) (get backgrounds-map (:background n)))))

(defn deabbrev-char
  "Converts an abbreviation to a full character string (eg. 'NaAE' to 'Naga Air Elementalist')."
  [x]
  (let [n (normalize-char x)]
    (str (when-let [c (:color n)] (str c " ")) (:species n) " " (:background n))))
