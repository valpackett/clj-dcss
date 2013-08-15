(ns clj-dcss.char-test
  (:use midje.sweet
        clj-dcss.char))

(facts
  ; Victory
  (parse-char " Dungeon Crawl Stone Soup version 0.13-a0-1363-g4786dc5 (webtiles) character file.

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

  ; YASD
  ; https://crawl.develz.org/tavern/viewtopic.php?f=12&t=8773
  (parse-char " Dungeon Crawl Stone Soup version 0.12.1 (tiles) character file.

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
      :time "07:55:31", :title "Invulnerable", :turns 83609}

  ; In progress dump
  ; https://crawl.develz.org/tavern/viewtopic.php?f=12&t=8767
  (parse-char " Dungeon Crawl Stone Soup version 0.13-a0-2496-g11650dd (console) character file.

rebthor the Severer (Lava Orc Ice Elementalist)    Turns: 41918, Time: 06:33:50

HP 103/103       AC 17     Str 13      XL: 14   Next: 80%
MP  25/25        EV 12     Int 19      God: Ashenzari [*****.]
Gold 2442        SH  0     Dex 11      Spells:  7 memorised,  4 levels left

Res.Fire  : + + .   See Invis. : +   L - +3,+6 battleaxe (curse)
Res.Cold  : + . .   Warding    : +   Y - +0 orc leather armour {rC+} (curse)
Life Prot.: + . .   Conserve   : .   (no shield)
Res.Poison: .       Res.Corr.  : .   e - +0 crested helmet (curse)
Res.Elec. : .       Clarity    : +   a - +0 elf cloak (curse)
Sust.Abil.: . .     Spirit.Shd : .   r - +0 pair of gloves
Res.Mut.  : .       Stasis     : .   z - +2 pair of boots (curse)
Res.Rott. : .       Flight     : .   d - cursed amulet of warding
Saprovore : + . .                    F - faintly glowing jade ring {tried, Str+1}
                                     K - cursed ring of protection from fire

@: slow, somewhat resistant to hostile enchantments, stealthy
A: conserve scrolls, saprovore 1
a: Scrying, Transfer Knowledge, Renounce Religion


You are on level 15 of the Dungeon.
You worship Ashenzari.
Ashenzari is exalted by your worship.
You are not hungry.")
  => {:character "Lava Orc Ice Elementalist"
      :meta {:interface "console", :version "0.13-a0-2496-g11650dd"}
      :name "rebthor", :time "06:33:50", :title "Severer", :turns 41918
      :resistances {:Clar "+", :Cons ".", :Fly ".", :Sap "+..", :SustAb "..", :Ward "+"
                    :rC "+..", :rCorr ".", :rElec ".", :rF "++.", :rMut ".", :rN "+.."
                    :rPois ".", :rRot ".", :sInv "+", :spirit ".", :stasis "."}
      :stats {:ac 17, :dex 11, :ev 12, :god "Ashenzari", :gold 2442, :hp 103, :int 19
              :mhp 103, :mmp 25, :mp 25, :percent-of-next-xl 80, :piety "*****.", :sh 0
              :spell-levels-left 4, :spells 7, :str 13, :xl 14}}

  (parse-char " Dungeon Crawl Stone Soup version 0.12.1 (tiles) character file.

6527055 Octomurder the Middleweight Champion (level 27, 300/300 HPs)
             Began as an Octopode Earth Elementalist on June 26, 2013.
             Was the Champion of Makhleb.
             Escaped with the Orb
             ... and 15 runes on July 21, 2013!
             
             The game lasted 20:17:57 (322969 turns).

Octomurder the Grand Master (OpEE)                Turns: 322969, Time: 20:17:58

HP 300/300       AC 55     Str 31      XL: 27
MP  15/49        EV 42     Int 22      God: Makhleb [******]
Gold 8937        SH 64     Dex 21      Spells: 17 memorised,  0 levels left

Res.Fire  : + + +   See Invis. : +     - Unarmed
Res.Cold  : + + +   Warding    : .   q - +6 large shield of Psuqyv {Str+3 Int+4}
Life Prot.: + + +   Conserve   : +   i - +2 wizard hat \"Vamav\" {rElec rF++ MR}
Res.Poison: +       Res.Corr.  : .   z - amulet \"Vymifeoh\" {Cons rF+ Str+3}
Res.Elec. : +       Clarity    : .   l - ring \"Laorph\" {Str+5 Dex+2 Acc+7 Dam+3}
Sust.Abil.: . .     Spirit.Shd : .   m - ring \"Gymos\" {+Inv rC+ rN+}
Res.Mut.  : .       Rnd.Telep. : +   U - ring of Robustness {AC+8}
Res.Rott. : +       Ctrl.Telep.: x   I - ring \"Thiblirr\" {rC+ SInv}
Saprovore : . . .   Flight     : .   T - ring of Shaolin {EV+8}
                                     P - ring \"Sekyulor\" {Hunger- rElec rC+ rN+}
                                     S - ring of Vitality {Regen HP+15}
                                     V - ring \"Klunk\" {rF+ Str+4 Dex+3}

@: statue-form, stone skin, disjoining, phasing, very slightly contaminated,
hasted, darkness, quick, extremely resistant to hostile enchantments, stealthy
A: almost no armour, amphibious, 8 rings, constrict 8, camouflage 1,
deterioration 1, slow healing 1, teleportitis 1
a: End Transformation, Minor Destruction, Lesser Servant of Makhleb, Major
Destruction, Greater Servant of Makhleb, Renounce Religion, Evoke Invisibility
 : 15/15 runes: serpentine, barnacled, slimy, silver, golden, iron, obsidian,
icy, bone, abyssal, demonic, glowing, magical, fiery, dark")
  => {:character "OpEE", :enddate "July 21, 2013", :godtitle "Champion"
      :meta {:interface "tiles", :version "0.12.1"}, :name "Octomurder"
      :resistances {:Clar ".", :Cons "+", :Fly ".", :Sap "...", :SustAb "..", :Ward "."
                    :rC "+++", :rCorr ".", :rElec "+", :rF "+++", :rMut ".", :rN "+++"
                    :rPois "+", :rRot "+", :sInv "+", :spirit "."}
      :stats {:ac 55, :dex 21, :ev 42, :god "Makhleb", :gold 8937, :hp 300, :int 22, :mhp 300, :mmp 49
              :mp 15, :piety "******", :sh 64, :spell-levels-left 0, :spells 17, :str 31, :xl 27}
      :score 6527055, :startdate "June 26, 2013", :time "20:17:58", :title "Grand Master", :turns 322969 }

  (parse-char " Dungeon Crawl Stone Soup version 0.13-a0-2484-gc765b08 (webtiles) character file.

25 Spam the Ducker (level 1, 0/10 HPs)
             Began as a Felid Berserker on July 20, 2013.
             Was a Follower of Trog.
             Slain by a hobgoblin (5 damage)
             ... on Level 1 of the Dungeon.
             The game lasted 00:00:35 (253 turns).

Spam the Ducker (Felid Berserker)                    Turns: 253, Time: 00:00:35

HP   0/10        AC  1     Str 13      XL: 1   Next: 66%
MP   1/1         EV 19     Int  8      God: Trog [*.....]
Gold 20          SH  0     Dex 15      Spells:  0 memorised,  0 levels left
                                       Lives: 0, deaths: 0

Res.Fire  : . . .   See Invis. : +     - Unarmed
Res.Cold  : . . .   Warding    : .   (armour unavailable)
Life Prot.: . . .   Conserve   : .   (shield unavailable)
Res.Poison: .       Res.Corr.  : .   (helmet unavailable)
Res.Elec. : .       Clarity    : .   (cloak unavailable)
Sust.Abil.: . .     Spirit.Shd : .   (gloves unavailable)
Res.Mut.  : .       Stasis     : .   (boots unavailable)
Res.Rott. : .       Flight     : .   (no amulet)
Saprovore : . . .                    (no ring)
                                     (no ring)

@: exhausted, slowed, slow, not resistant to hostile enchantments, fairly
stealthy
A: paw claws, no armour, no advanced items, fangs 3, see invisible, carnivore 3,
speed 1, slow metabolism 1, AC +1
a: Burn Spellbooks, Berserk, Renounce Religion


You were on level 1 of the Dungeon.
You worshipped Trog.
Trog was pleased with you.
You were not hungry.

You visited 1 branch of the dungeon, and saw 1 of its levels.

Inventory:

Comestibles
 c - a meat ration


   Skills:
 - Level 3.0 Fighting
 + Level 4.2 Dodging
 + Level 3.2 Unarmed Combat


You couldn't memorise any spells.
You didn't know any spells.

Dungeon Overview and Level Annotations

Branches:
Dungeon (1/27)            

Altars:


Innate Abilities, Weirdness & Mutations

You cannot wear armour.
You are incapable of any advanced item manipulation.
Your paws have sharp claws.
You have razor-sharp teeth.
You have supernaturally acute eyesight.
You are carnivorous and can eat meat at any time.
You cover ground quickly.
You are covered in fur (AC +1).
You have a slow metabolism.


Message History

You see here a jackal corpse.
You start butchering the jackal corpse with your claws.
You continue butchering the corpse.
Something unseen opens the door. You stop butchering the corpse.
You start resting.
You feel a strong urge to attack something.
A hobgoblin comes into view.
You are no longer berserk.
You are exhausted.
You feel yourself slow down.
The hobgoblin shouts!
You scratch the hobgoblin.
The hobgoblin begins to bleed from its wounds!
The hobgoblin is heavily wounded.
The hobgoblin barely misses you.
You scratch the hobgoblin.
The hobgoblin is severely wounded.
The hobgoblin hits you.
Ouch! That really hurt!
You die...

       ###
      ...#+##
    ###.....#
    ........#
     #......#
     #......#
     #......#
     #.....)########
     #.........g@..#
######......######.#    ...
............#    #.#    #.#
.#####......#    #.#    #.#
............#    #.#    #.#
#############    #.#    #.#
                 #.#    #.#
                 #.#    #.#
                 #.#    #.#


You could see a hobgoblin (bleeding).

Vanquished Creatures
  A hobgoblin (D:1)
  A goblin (D:1)
  3 jackals (D:1)
5 creatures vanquished.

Notes
Turn   | Place    | Note
--------------------------------------------------------------
     0 | D:1      | Spam, the Felid Berserker, began the quest for the Orb.
     0 | D:1      | Reached XP level 1. HP: 10/10 MP: 1/1
   253 | D:1      | Slain by a hobgoblin


Action                   || total
-------------------------++-------
Melee: Unarmed           ||    12
Invok: Berserk           ||     1")
  => {:character "Felid Berserker", :godtitle "Follower", :title "Ducker"
      :meta {:interface "webtiles", :version "0.13-a0-2484-gc765b08"}
      :name "Spam", :score 25, :startdate "July 20, 2013", :time "00:00:35", :turns 253
      :resistances {:Clar ".", :Cons ".", :Fly ".", :Sap "...", :SustAb "..", :Ward ".", :rC "..."
                    :rCorr ".", :rElec ".", :rF "...", :rMut ".", :rN "...", :rPois ".", :rRot "."
                    :sInv "+", :spirit ".", :stasis "."}
      :stats {:ac 1, :deaths 0, :dex 15, :ev 19, :god "Trog", :gold 20, :hp 0, :int 8, :lives 0
              :mhp 10, :mmp 1, :mp 1, :percent-of-next-xl 66, :piety "*.....", :sh 0
              :spell-levels-left 0, :spells 0, :str 13, :xl 1}})

(fact
  (count-piety {:stats {:piety "****.."}}) => {:stats {:piety 4}}
  (count-piety {:stats {:piety ""}}) => {:stats {:piety 0}}
  (count-piety {:stats {}}) => {:stats {}})

(fact
  (move-godtitle {:stats {:godtitle "Idiot"}}) => {:godtitle "Idiot", :stats {}}
  (move-godtitle {:stats {}}) => {:stats {}})

(fact
  (parse-character-str {:character "OpEE"})
  => {:character {:species "Octopode", :background "Earth Elementalist"}}
  (parse-character-str {:character "Green Draconian Air Elementalist"})
  => {:character {:color "Green" :species "Draconian", :background "Air Elementalist"}}
  (parse-character-str {})
  => {})
