(ns advent-of-code-202.core)
(require '[clojure.java [io :as io]])

(defn get-file-content
  "Reads the input file into a set"
  [filename]
    (line-seq (io/reader filename)))

(defn to-integer
  ;; converts an element to integer
  [elem]
  (Integer/parseInt elem)
)

(defn to-integers
  ;; converts each element in a list to integer
  [alist]
  (map #(to-integer %) alist))

(defn get-occurance-count
  ;; Counts the frequency of a character in a string
  [string char]
  (get (frequencies string) (first char) 0))

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
  (day-1-1 (to-integers (get-file-content input-file))))

(defn solve-day-1-2
  [input-file]
  (day-1-2 (to-integers (get-file-content input-file))))

; --------------- Day 2 ------------------
(defn extract-policy-params
  [val]
  (zipmap [:min :max :char :string]
          (rest (re-find #"(\d+)-(\d+) (\w+): (\w+)$" val))))

(defn is-password-valid-policy-1?
  [val]
  (let [extract (extract-policy-params val)
        min (to-integer (:min extract))
        max (to-integer (:max extract))
        count (get-occurance-count (:string extract) (:char extract))]
    (<= min count max)))

(defn is-password-valid-policy-2?
  [val]
  (let [extract (extract-policy-params val)
        min (to-integer (:min extract))
        max (to-integer (:max extract))
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
  (day-2-1 (get-file-content input-file)))

(defn solve-day-2-2
  [input-file]
  (day-2-2 (get-file-content input-file)))

; --------------- Day 3 ------------------
(defn count-trees
  [res]
  (count (filter #{(first "#")} res)))

(defn get-nth-in-line
  [input n right down]
  (if (<= (* n down) (count input))
    (let [line (nth input (* n down))]
      (get line (rem (* n right) (count line))))))

(defn get-total-trees-in-path
  [input right down]
  (loop [n 1 res []]
    (if (>= n (count input))
      (count-trees res)
      (recur (inc n) (conj res (get-nth-in-line input n right down))))))

(defn day-3-1
  [input]
  (get-total-trees-in-path input 3 1))

(defn day-3-2
  [input]
  (* (get-total-trees-in-path input 1 1)
     (get-total-trees-in-path input 3 1)
     (get-total-trees-in-path input 5 1)
     (get-total-trees-in-path input 7 1)
     (get-total-trees-in-path input 1 2)))

(defn solve-day-3-1
  [input-file]
  (day-3-1 (apply list (get-file-content input-file))))

(defn solve-day-3-2
  [input-file]
  (day-3-2  (apply list (get-file-content input-file))))

; --------------- Entrypoint ------------------

(defn -main [arg]
  ;; (println (solve-day-1-1 arg))
  ;; (println (solve-day-1-2 arg))
  ;; (println (solve-day-2-1 arg))
  ;; (println (solve-day-2-2 arg))
  ;; (println (solve-day-3-1 arg))
  (println (solve-day-3-2 arg)))
