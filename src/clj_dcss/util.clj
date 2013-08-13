(ns clj-dcss.util)

(defn penultimate [l] (second (reverse l)))

(defn first-two [x] (apply str (take 2 x)))

(defn last-two [x] (apply str (take-last 2 x)))

(defn re-first [pattern x]
  (let [y (re-find pattern x)]
    (if (seq? y)
      (first y)
      y)))
