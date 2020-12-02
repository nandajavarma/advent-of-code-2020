(ns advent-of-code-202.core-test
  (:require [clojure.test :refer :all]
            [advent-of-code-202.core :refer :all]))

(def test-input-day-1
  [1721
   979
   366
   299
   675
   1456]
)


(deftest test-day-1-1
  (testing "Day 1 1"
    (is (= #{514579} (day-1-1 test-input-day-1)))))

(deftest test-day-1-2
  (testing "Day 1 2"
    (is (= #{241861950} (day-1-2 test-input-day-1)))))
