(ns clj-dcss.char-test
  (:use midje.sweet
        clj-dcss.char))

(fact
  (parse-char "Dungeon Crawl Stone Soup version 0.13-a0-1363-g4786dc5 (webtiles) character file.

1463507 floatboth the Anemomancer (level 27, 268/292 HPs)
             Began as a Naga Air Elementalist on May 29, 2013.
             Was the Champion of Cheibriados.
             Escaped with the Orb
             ... and 3 runes on June 2, 2013!
             
             The game lasted 14:59:05 (121525 turns).

floatboth the Anemomancer (Naga Air Elementalist) Turns: 121525, Time: 14:59:06

HP 268/292 (293) AC 39     Str 24      XL: 27
MP  37/52        EV 24     Int 49      God: Cheibriados [******]
Gold 6260        SH 32     Dex 33      Spells: 14 memorised,  4 levels left")
  => {:meta {:version "0.13-a0-1363-g4786dc5", :interface "webtiles"}
      :name "floatboth", :title "Anemomancer", :character "Naga Air Elementalist", :godtitle "Champion"
      :score 1463507, :time "14:59:06", :turns 121525, :startdate "May 29, 2013", :enddate "June 2, 2013"
      :stats {:ac 39, :ev 24, :sh 32, :str 24, :int 49, :dex 33
              :hp 268, :mhp 292, :mmhp 293, :mp 37, :mmp 52, :gold 6260
              :god "Cheibriados", :piety-stars 6, :xl 27
              :spells 14, :spell-levels-left 4}})
