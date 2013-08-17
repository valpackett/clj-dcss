(ns clj-dcss.abbrev-test
  (:use midje.sweet
        clj-dcss.abbrev))

(facts
  (normalize-char "Green Draconian Monk")
  => {:color "Green", :species "Draconian", :background "Monk"}
  (normalize-char "Green DrMo")
  => {:color "Green", :species "Draconian", :background "Monk"}
  (normalize-char "DEFE")
  => {:species "Deep Elf", :background "Fire Elementalist"}
  (normalize-char "Green Deep Elf Fire Elementalist") ;-)
  => {:color "Green", :species "Deep Elf", :background "Fire Elementalist"}
  (normalize-char "Minotaur Chaos Knight")
  => {:species "Minotaur", :background "Chaos Knight"}
  (normalize-char "Deep Elf Berserker")
  => {:species "Deep Elf", :background "Berserker"}
  )

(facts
  (abbrev-char {:species "Octopode", :background "Wizard"}) => "OpWz"
  (abbrev-char "Octopode Wizard") => "OpWz"
  (abbrev-char "Green Draconian Fire Elementalist") => "DrFE"
  (abbrev-char "DEIE") => "DEIE")

(facts
  (deabbrev-char {:species "Octopode", :background "Wizard"}) => "Octopode Wizard"
  (deabbrev-char "OpWz") => "Octopode Wizard"
  (deabbrev-char "DrAE") => "Draconian Air Elementalist"
  (deabbrev-char "Green DrSk") => "Green Draconian Skald"
  (deabbrev-char "Deep Elf Wizard") => "Deep Elf Wizard")
