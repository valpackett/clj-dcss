(ns clj-dcss.abbrev
  (:use clj-dcss.util)
  (:require [clojure.set :as set]
            [clojure.string :as str]
            frak))

(def species
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
(def species-reverse (set/map-invert species))
(def species-long (set (keys species)))
(def species-short (set (vals species)))
(def species-pattern
  (frak/pattern (concat species-long species-short)))
(defn normalize-species [x]
  (if (contains? species-short x)
    (get species-reverse x)
    x))

(def backgrounds
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
(def backgrounds-reverse (set/map-invert backgrounds))
(def backgrounds-long (set (keys backgrounds)))
(def backgrounds-short (set (vals backgrounds)))
(def background-pattern
  (frak/pattern (concat backgrounds-long backgrounds-short)))
(defn normalize-background [x]
  (if (contains? backgrounds-short x)
    (get backgrounds-reverse x)
    x))

(def color-pattern (frak/pattern ["Red" "White" "Green" "Yellow" "Grey" "Black" "Purple" "Mottled" "Pale"]))

(defn normalize-char
  "Normalizes any character string or abbreviation ('DEFE', 'Octopode Wizard', even 'Green DrEE')
  to a Clojure map with keys :color, :species and :background."
  [x]
  (let [clr (re-first color-pattern x)
        x   (if clr (str/replace-first x clr "") x) ; Avoid collisions
        spc (re-first species-pattern x)
        x   (str/replace-first x spc "")
        bkg (re-first background-pattern x)
        ret {:species (normalize-species spc), :background (normalize-background bkg)}]
    (if clr
      (assoc ret :color clr)
      ret)))

(defn abbrev-char
  "Converts a full character string to an abbreviation (eg. 'Deep Elf Wizard' to 'DEWz')."
  [x]
  (let [n (normalize-char x)]
    (str (get species (:species n)) (get backgrounds (:background n)))))

(defn deabbrev-char
  "Converts an abbreviation to a full character string (eg. 'NaAE' to 'Naga Air Elementalist')."
  [x]
  (let [n (normalize-char x)]
    (str (when-let [c (:color n)] (str c " ")) (:species n) " " (:background n))))
