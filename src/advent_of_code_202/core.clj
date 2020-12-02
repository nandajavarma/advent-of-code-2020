(ns advent-of-code-202.core)
(require '[clojure.java [io :as io]])

(defn get_file_content
  "Reads the input file into a set"
  [filename]
    (line-seq (io/reader filename)))

; --------------- Day 1 ------------------
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
  (day-1-1 (map #(Integer/parseInt %) (get_file_content input-file))))


(defn solve-day-1-2
  [input-file]
  (day-1-2 (map #(Integer/parseInt %) (get_file_content input-file))))

; --------------- Day 2 ------------------
(defn extract-policy-params
  [val]
  (zipmap [:min :max :char :string]
          (rest (re-find #"(\d+)-(\d+) (\w+): (\w+)$" val))))

(defn get-occurance-count
  [string char]
  (get (frequencies string) (first char) 0)
)

(defn is-password-valid-policy-1?
  [val]
  (let [extract (extract-policy-params val)
        min (Integer/parseInt (:min extract))
        max (Integer/parseInt (:max extract))
        count (get-occurance-count (:string extract) (:char extract))]
    (<= min count max)))

(defn is-password-valid-policy-2?
  [val]
  (let [extract (extract-policy-params val)
        min (Integer/parseInt (:min extract))
        max (Integer/parseInt (:max extract))
        string (:string extract)
        char (:char extract)]
    (= (= (get string (dec min)) (first char))
         (= (get string (dec max)) (first char)))))

(defn day-2-1
  [input]
  (count (remove false? (map #(is-password-valid-policy-1? %) input))))

(defn day-2-2
  [input]
  (count (remove true? (map #(is-password-valid-policy-2? %) input))))

(defn solve-day-2-1
  [input-file]
  (day-2-1 (get_file_content input-file)))

(defn solve-day-2-2
  [input-file]
  (day-2-2 (get_file_content input-file)))

; --------------- Entrypoint ------------------

(defn -main [arg]
  ;; (println (solve-day-1-1 arg))
  ;; (println (solve-day-1-2 arg))
  ;; (println (solve-day-2-1 arg))
  (println (solve-day-2-2 arg))
  )
