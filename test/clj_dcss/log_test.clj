(ns clj-dcss.log-test
  (:use midje.sweet
        clj-dcss.log))

(fact
  (parse-log "v=0.13-a0:lv=0.1:name=user:race=Naga:cls=Warper:char=NaWr:xl=2:sk=Translocations:sklev=3:title=Grasshopper:place=D::2:br=D:lvl=2:absdepth=2:hp=-2:mhp=25:mmhp=25:str=13:int=13:dex=10:ac=3:ev=9:sh=0:start=20130504140219S:dur=102:turn=808:aut=11015:kills=12:gold=129:goldfound=79:goldspent=0:sc=143:ktyp=mon:killer=Terence:dam=6:sdam=6:tdam=6:kaux=a +0,+2 flail:end=20130504140401S:killermap=uniq_terence:tmsg=slain by Terence:vmsg=slain by Terence (a +0,+2 flail)")
  => {:v "0.13-a0", :lv "0.1", :name "user", :race "Naga", :cls "Warper", :char "NaWr",
      :xl 2, :sk "Translocations", :sklev 3, :title "Grasshopper", :place "D::2", :br "D"
      :lvl 2, :absdepth 2, :hp -2, :mhp 25, :mmhp 25, :str 13, :int 13, :dex 10, :ac 3, :ev 9, :sh 0
      :start "20130504140219S", :dur 102, :turn 808, :aut 11015, :kills 12, :gold 129, :goldfound 79
      :goldspent 0, :sc 143, :ktyp "mon", :killer "Terence", :dam 6, :sdam 6, :tdam 6, :kaux "a +0,+2 flail"
      :end "20130504140401S", :killermap "uniq_terence", :tmsg "slain by Terence", :vmsg "slain by Terence (a +0,+2 flail)"})

(fact
  (log->url (fn [{:keys [v]}] (str "crawltp://" v "/games/"))
            {:v "0.13-a0", :name "user", :end "20130504140401S"})
  => "crawltp://0.13-a0/games/user/morgue-user-20130504-140401.txt") ; lol

(fact
  (log->cao-url {:v "0.13-a0", :name "user", :end "20130504140401S"})
  => "http://crawl.akrasiac.org/rawdata/user/morgue-user-20130504-140401.txt")

(facts
  (log->cdo-url {:v "0.12.1", :name "user", :end "20130504140401S"})
  => "http://crawl.develz.org/morgues/0.12/user/morgue-user-20130504-140401.txt"
  (log->cdo-url {:v "0.13-a0", :name "user", :end "20130504140401S"})
  => "http://crawl.develz.org/morgues/trunk/user/morgue-user-20130504-140401.txt")

(fact
  (log->cszo-url {:v "0.13-a0", :name "user", :end "20130504140401S"})
  => "http://dobrazupa.org/morgue/user/morgue-user-20130504-140401.txt")

(fact
  (log->clan-url {:v "0.13-a0", :name "user", :end "20130504140401S"})
  => "http://crawl.lantea.net/crawl/morgue/user/morgue-user-20130504-140401.txt")

(fact
  (log->rhf-url {:v "0.13-a0", :name "user", :end "20130504140401S"})
  => "http://rl.heh.fi/morgue/user/morgue-user-20130604-140401.txt")
