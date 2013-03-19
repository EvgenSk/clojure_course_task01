(ns task01.core
  (:require [pl.danieljanus.tagsoup :refer :all])
  (:gen-class))

(defn get-links 
  ([] (let [data (parse "clojure_google.html")]
        (get-links data)))

  ([container] (cond (and (vector? container) (= (first container) :h3))
                     (get-in container [2 1 :href])
                     (vector? container)
                     (->> (map get-links container)
                          (remove empty?)
                          (flatten))
                     :else nil)))

(defn -main []
  (println (str "Found " (count (get-links)) " links!")))
