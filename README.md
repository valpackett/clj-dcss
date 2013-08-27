Current [semantic](http://semver.org/) version:

```clojure
[clj-dcss "0.1.0-SNAPSHOT"]
```

# clj-dcss

A Clojure library for parsing and processing [Dungeon Crawl Stone Soup](https://crawl.develz.org/wordpress/) data.

## Usage

### Crawl server logs

```clojure
(ns app
  (:require [clj-dcss.log :as clog]))

(clog/parse-log "v=0.13-a0:lv=0.1:name=user:race=Naga:cls=Warper:char=NaWr:xl=2:sk=Translocations:sklev=3:title=Grasshopper:place=D::2:br=D:lvl=2:absdepth=2:hp=-2:mhp=25:mmhp=25")
; =>  {:v "0.13-a0", :lv "0.1", :name "user", :race "Naga", :cls "Warper", :char "NaWr", :xl 2, :sk "Translocations", :sklev 3, :title "Grasshopper", :place "D::2", :br "D", :lvl 2, :absdepth 2, :hp -2, :mhp 25, :mmhp 25}

; Much faster:
(clog/quick-parse-log-for-url "v=0.13-a0:lv=0.1:name=user:race=Naga:cls=Warper:char=NaWr:xl=2:sk=Translocations:sklev=3:title=Grasshopper:place=D::2:br=D:lvl=2:absdepth=2:hp=-2:mhp=25:mmhp=25:end=20130504140401S")
; =>  {:v "0.13-a0", :name "user", :end "20130504140401S"}

(clog/log->cao-url {:v "0.13-a0", :name "user", :end "20130504140401S"})
; => "http://crawl.akrasiac.org/rawdata/user/morgue-user-20130504-140401.txt"

; See test/clj_dcss/log_test.clj
```

Use [rxjava-http-tail](https://github.com/myfreeweb/rxjava-http-tail) to follow the logs.

### Character abbreviations

```clojure
(ns app
  (:require [clj-dcss.abbrev :as cabbrev]))

(cabbrev/normalize-char "DEFE")
; => {:species "Deep Elf", :background "Fire Elementalist"}

(cabbrev/normalize-char "Green Draconian Monk")
; => {:color "Green", :species "Draconian", :background "Monk"}

(cabbrev/normalize-char "Yellow DrEn")
; => {:color "Yellow", :species "Draconian", :background "Enchanter"}

(cabbrev/abbrev-char "Green Draconian Fire Elementalist")
; => "DrFE"

(cabbrev/deabbrev-char "OpWz")
; => "Octopode Wizard"

; See test/clj_dcss/abbrev_test.clj
```

### Character dumps / morgue files

```clojure
(ns app
  (:require [clj-dcss.char :as cchar]))

(cchar/parse-and-process-char "Dungeon Crawl Stone Soup version 0.13-a0-1363-g4786dc5 (webtiles) character file.

1463507 floatboth the Anemomancer (level 27, 268/292 HPs)
             Began as a Naga Air Elementalist on May 29, 2013.
             Was the Champion of Cheibriados.
             Escaped with the Orb
             ... and 3 runes on June 2, 2013!
             
             The game lasted 14:59:05 (121525 turns).

floatboth the Anemomancer (Naga Air Elementalist) Turns: 121525, Time: 14:59:06

HP 268/292 (293) AC 39     Str 24      XL: 27
MP  37/52        EV 24     Int 49      God: Cheibriados [******]
Gold 6260        SH 32     Dex 33      Spells: 14 memorised,  4 levels left

Res.Fire  : + . .   See Invis. : +   f - +8,+9 demon whip (drain)
Res.Cold  : + . .   Warding    : .   S - +8 steam dragon armour of Hairiness {Str-5
Life Prot.: . . .   Conserve   : .   s - +2 shield {reflect}
Res.Poison: +       Res.Corr.  : .   l - +3 hat of Pondering {ponderous, MR+ MP+10 
Res.Elec. : .       Clarity    : .   T - +3 cloak of Flash {+Fly EV+4}
Sust.Abil.: . .     Spirit.Shd : .   P - +2 pair of gauntlets {Dex+3}
Res.Mut.  : +       Stasis     : .   (no barding)
Res.Rott. : .       Flight     : .   K - amulet of resist mutation
Saprovore : . . .                    p - +6 ring of protection
                                     X - ring \"Puiqi\" {Wiz rF+ rC+}")

; => {:meta {:version "0.13-a0-1363-g4786dc5", :interface "webtiles"}
;      :name "floatboth", :title "Anemomancer", :character {:species "Naga", :background "Air Elementalist"}
;      :score 1463507, :time "14:59:06", :turns 121525, :startdate "May 29, 2013", :enddate "June 2, 2013"
;      :stats {:ac 39, :ev 24, :sh 32, :str 24, :int 49, :dex 33
;              :hp 268, :mhp 292, :mmhp 293, :mp 37, :mmp 52, :gold 6260
;              :god "Cheibriados", :godtitle "Champion", :piety 6, :xl 27
;              :spells 14, :spell-levels-left 4}
;      :resistances {:rF "+..", :sInv "+", :rC "+..", :Ward ".", :rN "...", :Cons "."
;                    :rPois "+", :rCorr ".", :rElec ".", :Clar ".", :SustAb "..", :spirit "."
;                    :rMut "+", :stasis ".", :rRot ".", :Fly ".", :Sap "..."}}
```

Supports both victory/death and in-progress char dumps.

## License

Copyright © 2013 Greg V <floatboth@me.com>  
This work is free. You can redistribute it and/or modify it under the  
terms of the Do What The Fuck You Want To Public License, Version 2,  
as published by Sam Hocevar. See the COPYING file for more details.
