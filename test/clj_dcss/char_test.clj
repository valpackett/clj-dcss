(ns clj-dcss.char-test
  (:use midje.sweet
        clj-dcss.char))

(facts
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

  => {:meta {:version "0.13-a0-1363-g4786dc5", :interface "webtiles"}
      :name "floatboth", :title "Anemomancer", :character "Naga Air Elementalist", :godtitle "Champion"
      :score 1463507, :time "14:59:06", :turns 121525, :startdate "May 29, 2013", :enddate "June 2, 2013"
      :stats {:ac 39, :ev 24, :sh 32, :str 24, :int 49, :dex 33
              :hp 268, :mhp 292, :mmhp 293, :mp 37, :mmp 52, :gold 6260
              :god "Cheibriados", :piety "******", :xl 27
              :spells 14, :spell-levels-left 4}
      :resistances {:rF "+..", :sInv "+", :rC "+..", :Ward ".", :rN "...", :Cons "."
                    :rPois "+", :rCorr ".", :rElec ".", :Clar ".", :SustAb "..", :spirit "."
                    :rMut "+", :stasis ".", :rRot ".", :Fly ".", :Sap "..."}}

  ; https://crawl.develz.org/tavern/viewtopic.php?f=12&t=8773
  (parse-char "Dungeon Crawl Stone Soup version 0.12.1 (tiles) character file.

513027 Shawyer the Invulnerable (level 25, 239/239 (240) HPs)
             Began as a Minotaur Fighter on July 19, 2013.
             Was the Champion of Okawaru.
             Forgot to breathe
             ... caused by wearing the plate armour of Anaesthesia
             ... while paralysed
             ... on Level 3 of the Lair of Beasts on July 24, 2013.
             The game lasted 07:55:31 (83609 turns).

Shawyer the Invulnerable (Minotaur Fighter)        Turns: 83609, Time: 07:55:31

HP 239/239 (240) AC 25     Str 37      XL: 25   Next: 32%
MP  26/26        EV  2     Int  0      God: Okawaru [******]
Gold 3386        SH  0     Dex 21      Spells:  0 memorised, 24 levels left

Res.Fire  : . . .   See Invis. : .   p - +8 demon whip (venom)
Res.Cold  : + + .   Warding    : .   Y - -4 plate armour of Anaesthesia  Str+4 Int-
Life Prot.: . . .   Conserve   : +   w - +0 dwarf large shield
Res.Poison: .       Res.Corr.  : +   c - +0 wizard hat {MR}
Res.Elec. : +       Clarity    : .   t - +0 cloak {rCorr, Cons}
Sust.Abil.: . .     Spirit.Shd : .   N - +0 pair of gauntlets {Str+3}
Res.Mut.  : +       Stasis     : .   g - +1 pair of elf boots {run}
Res.Rott. : .       Ctrl.Telep.: +   i - amulet of resist mutation
Saprovore : . . .   Flight     : .   x - ring of Music {rC+ Dex+5 Dam+5}
                                     D - ring \"Fraxec\" {cTele rElec rC+ Dex+3 Stlth

@: paralysed, lost intelligence, quick, very resistant to hostile enchantments,
extremely unstealthy
A: retaliatory headbutt, horns 2, Int -1
a: Heroism, Finesse, Renounce Religion
}: 3/15 runes: slimy, silver, gossamer")
  => {:character "Minotaur Fighter", :enddate "July 24, 2013", :godtitle "Champion"
      :meta {:version "0.12.1", :interface "tiles"}, :name "Shawyer"
      :resistances {:Clar ".", :Cons "+", :Fly ".", :Sap "...", :SustAb ".."
                    :Ward ".", :rC "++.", :rCorr "+", :rElec "+", :rF "...", :rMut "+"
                    :rN "...", :rPois ".", :rRot ".", :sInv ".", :spirit ".", :stasis "."}
      :score 513027, :startdate "July 19, 2013"
      :stats {:ac 25, :dex 21, :ev 2, :god "Okawaru", :gold 3386, :hp 239, :int 0
              :mhp 239, :mmhp 240, :mmp 26, :mp 26, :percent-of-next-xl 32, :piety "******"
              :sh 0, :spell-levels-left 24, :spells 0, :str 37, :xl 25}
      :time "07:55:31", :title "Invulnerable", :turns 83609})

(fact
  (process-char
    {:stats {:piety "****.."
             :godtitle "Idiot"}})
  => {:stats {:piety 4}
      :godtitle "Idiot"})
