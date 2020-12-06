(ns advent-of-code-202.core)
(require '[clojure.java [io :as io]]
         '[clojure.string :as str]
         '[clojure.set])

(defn get-file-content-line-by-line
  "Reads the input file line by line into a set"
  [filename]
    (line-seq (io/reader filename)))

(defn get-file-content
  [filename]
  (slurp filename))

(defn to-integer
  ;; converts an element to integer
  [elem]
  (Long/parseLong elem)
)

(defn to-integers
  ;; converts each element in a list to integer
  [alist]
  (map #(to-integer %) alist))

(defn get-occurance-count
  ;; Counts the frequency of a character in a string
  [string char]
  (get (frequencies string) (first char) 0))

(defn filter-newlines
 [string]
  (filter #(not (= "\n" %))  string))

(defn split-string
  [string]
  (clojure.string/split string  #""))

(defn split-string-by-new-line
  [string]
  (clojure.string/split string  #"\n"))

(defn lists-to-sets
  [alist]
  (map #(into #{} %1) alist))

(defn count-common-chars
  [words]
  (let [strings (split-string-by-new-line  words )
        char-lists (map  split-string strings)]
   (count (reduce clojure.set/intersection  (lists-to-sets char-lists)))))

(defn count-unique-chars
  [word]
  (count (distinct (filter-newlines (split-string word)))))

(defn split-string-by-blank-line
  [string]
  (str/split string #"\r?\n\r?\n"))

(defn split-string-by-space
  [string]
  (str/split string #"(\S+)/\n|\s|\z"))

(defn split-string-by-colon
  [string]
  (str/split string #":"))

(defn split-string-by-colon-and-return-first
  [string]
  (first (split-string-by-colon string)))

(defn split-chars-and-nums
  [string]
  (str/split string #"(\d*)(\D*)"))

(defn is-n-digit
  [string n]
  (== (count string) n))

(defn is-in-the-range
  [num min max]
  (<= min num max))

(defn is-alphanumeric-6
  [string]
  (re-matches #"^[a-z0-9]{6}$" string))

;; (defn call [this & that]
;;   (apply (resolve (symbol this)) that))
;; (defn call [^String nm & args]
;;   (when-let [fun (ns-resolve *ns* (symbol nm))]
;;     (apply fun args)))
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
  (day-1-1 (to-integers (get-file-content-line-by-line input-file))))

(defn solve-day-1-2
  [input-file]
  (day-1-2 (to-integers (get-file-content-line-by-line input-file))))

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
  (day-2-1 (get-file-content-line-by-line input-file)))

(defn solve-day-2-2
  [input-file]
  (day-2-2 (get-file-content-line-by-line input-file)))

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
  (day-3-1 (apply list (get-file-content-line-by-line input-file))))

(defn solve-day-3-2
  [input-file]
  (day-3-2  (apply list (get-file-content-line-by-line input-file))))

; --------------- Day 4 ------------------

(defn validate-byr
  [val]
  (and (is-n-digit val 4) (is-in-the-range (to-integer val) 1920 2002)))

(defn validate-iyr
  [val]
  (and (is-n-digit val 4) (is-in-the-range (to-integer val) 2010 2020)))

(defn validate-eyr
  [val]
  (and (is-n-digit val 4) (is-in-the-range (to-integer val) 2020 2030)))

(defn validate-hgt
  [val]
  (let [contents (split-chars-and-nums val)
        unit (last contents)
        height (first contents)]
    (if (= unit "cm")
      (is-in-the-range height 150 193)
      (do
        (if (= unit "in")
          (is-in-the-range height 59 76)
          false)))))

(defn validate-hcl
  [val]
  (and (= "#" (first val)) (is-alphanumeric-6 (rest val ) )))

(def valid-eyes
  ["amb" "blu" "brn" "gry" "grn" "hzl" "oth"])

(defn validate-ecl
  [val]
  (contains? valid-eyes val))

(defn validate-pid
  [val]
  (and (is-n-digit val 9)  (= \0 (first val))))

(defn validate
  [kv]
  (case (first kv)
    "byr" (validate-byr (last kv))
    "iyr" (validate-iyr (last kv))
    "eyr" (validate-eyr (last kv))
    "hgt" (validate-hgt (last kv))
    "hcl" (validate-hcl (last kv))
    "ecl" (validate-ecl (last kv))
    "pid" (validate-pid (last kv))
    "cid" true))

(def reqd-fields
  ["byr" "iyr" "eyr" "hgt" "hcl" "ecl" "pid"])

(defn get-all-keys
  [passport]
  (let [fields (split-string-by-space passport)]
        (map split-string-by-colon-and-return-first  fields)))

(defn get-all-key-value-pairs
  [passport]
  (let [fields (split-string-by-space passport)]
    (filter #(not (empty? %) )   (map split-string-by-colon  fields))))

(defn all-fields-present?
  [passport]
  (every? (set (get-all-keys  passport)) reqd-fields))

(defn get-valid-passports-1
  [passports]
  (if (empty? passports)
      0
      (do
        (if (all-fields-present? (first passports))
          (+ 1 (get-valid-passports-1 (rest passports)))
          (get-valid-passports-1 (rest passports))))))

(defn call [this & that]
  (cond
    (string? this) (apply (resolve (symbol this)) that)
    (fn? this)     (apply this that)
    :else          (conj that this)))

(defn check-all-valid
  [fields]
  (if (empty? fields)
    true
    (do
      (let [kv (first fields)]
        (println (validate kv))
        (println kv)
        (and (validate kv) (check-all-valid (rest fields)))))))

(defn get-valid-passports-2
  [passports]
  (if (empty? passports)
      0
      (do (let [key-value-pairs (get-all-key-value-pairs (first passports))]
            (if (and (check-all-valid key-value-pairs) (all-fields-present? (first passports)))
                (+ 1 (get-valid-passports-2 (rest passports)))
                (get-valid-passports-2 (rest passports)))))))

(defn day-4-1
  [input]
  (get-valid-passports-1 (split-string-by-blank-line input)))


(defn day-4-2
  [input]
  (get-valid-passports-2 (split-string-by-blank-line input) ))

(defn solve-day-4-1
  [input-file]
  (day-4-1 (get-file-content input-file)))

(defn solve-day-4-2
  [input-file]
  (day-4-2 (get-file-content input-file)))
; --------------- Day 6 ------------------
(defn merge-maps
  [map1 map2]
  (merge-with + map1 map2))

(defn kv [bag [k v]]
  (assoc bag k (+ (bag k 0) v)))

(defn merge-matches
  [property-map-list]
  (reduce #(reduce kv %1 %2) {} property-map-list))

(defn fine-yes-answers
  [answers]
  (if (empty? answers)
    0
  (+ (count-unique-chars (first answers)) (fine-yes-answers (rest answers)))))

(defn fine-common-yes-answers
  [answers]
  (if (empty? answers)
    0
    (+ (count-common-chars (first answers)) (fine-common-yes-answers (rest answers)))))

(defn day-6-1
  [answers]
  (fine-yes-answers (filter #(not (empty? %)) answers)))

(defn day-6-2
  [answers]
  (fine-common-yes-answers (filter #(not (empty? %)) answers)))

(defn solve-day-6-1
  [input-file]
  (day-6-1 (apply list (split-string-by-blank-line (get-file-content input-file)))))

(defn solve-day-6-2
  [input-file]
  (day-6-2 (apply list (split-string-by-blank-line (get-file-content input-file)))))
; --------------- Entrypoint ------------------


(defn -main [arg]
  ;; (println (solve-day-1-1 arg))
  ;; (println (solve-day-1-2 arg))
  ;; (println (solve-day-2-1 arg))
  ;; (println (solve-day-2-2 arg))
  ;; (println (solve-day-3-1 arg))
  ;; (println (solve-day-3-2 arg))
  ;; (println (solve-day-4-1 arg)))
  ;; (println (solve-day-4-2 arg)))
  ;; (println (solve-day-6-1 arg)))
  (println (solve-day-6-2 arg)))
