(ns task01.core
  (:require [pl.danieljanus.tagsoup :refer :all])
  (:gen-class))

(defn get-links-1 [container]
  (cond (and (vector? container) (= (first container) :h3))
        (get (get (get container 2) 1) :href)
        (vector? container)
        (flatten (filter #(not (or (nil? %) (empty? %))) (map #(get-links-1 %) container)))
        :else nil))

(defn get-links []
  (let [data (parse "clojure_google.html")]
    (get-links-1 data)))

(defn -main []
  (println (str "Found " (count (get-links)) " links!")))
