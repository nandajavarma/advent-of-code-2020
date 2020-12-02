(ns advent-of-code-202.core)
(require '[clojure.java [io :as io]])

(defn get_file_content
  "Reads the input file into a set"
  [filename]
    (map #(Integer/parseInt %) (line-seq (io/reader filename))))

(defn day-1-1
  [input]
  (set (for [val input
        other-val input
        :when (= 2020 (+ val other-val))]
        (* val other-val))))

(defn day-1-2
  [input]
  (set (for [val input
             other-val input
             third-val input
             :when (= 2020 (+ val other-val third-val))]
         (* val other-val third-val))))

(defn solve-day-1-1
  [input-file]
  (day-1-1 (get_file_content input-file)))


(defn solve-day-1-2
  [input-file]
  (day-1-2 (get_file_content input-file)))

(defn -main [arg]
  (println (solve-day-1-1 arg))
  (println (solve-day-1-2 arg))
  )
