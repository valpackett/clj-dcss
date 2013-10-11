(ns clj-dcss.version-test
  (:use midje.sweet
        clj-dcss.version))

(fact
  (full->major "0.12") => "0.12"
  (full->major "0.13-a0") => "0.13")

(facts
  (trunk? "0.14") => true
  (trunk? "0.14-asrt2nt;4y3jld2ylf") => true
  (trunk? "0.13.14.15-arrrrr") => false
  (trunk? "0.12") => false
  (trunk? "0.12.13") => false
  (trunk? "0.12-cq3uilapdhvu") => false)

(facts
  (normalize "0.12.1-wtf") => "0.12"
  (normalize "0.12") => "0.12"
  (normalize "0.13") => "0.13"
  (normalize "0.14-a123") => "trunk"
  (normalize "0.14") => "trunk")
