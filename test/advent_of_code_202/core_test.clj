(ns advent-of-code-202.core-test
  (:require [clojure.test :refer :all]
            [advent-of-code-202.core :refer :all]))

(def test-input-day-1
  [1721
   979
   366
   299
   675
   1456])

(def test-input-day-2
  [
   "1-3 a: abcde"
   "1-3 b: cdefg"
   "2-9 c: ccccccccc"])

(def test-input-day-3
  ["..##.........##.........##.........##.........##.........##......."
   "#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..#...#...#.."
   ".#....#..#..#....#..#..#....#..#..#....#..#..#....#..#..#....#..#."
   "..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#"
   ".#...##..#..#...##..#..#...##..#..#...##..#..#...##..#..#...##..#."
   "..#.##.......#.##.......#.##.......#.##.......#.##.......#.##....."
   ".#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#"
   ".#........#.#........#.#........#.#........#.#........#.#........#"
   "#.##...#...#.##...#...#.##...#...#.##...#...#.##...#...#.##...#..."
   "#...##....##...##....##...##....##...##....##...##....##...##....#"
   ".#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#"])

(def test-input-day-4
  "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
   byr:1937 iyr:2017 cid:147 hgt:183cm

   iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
   hcl:#cfa07d byr:1929

   hcl:#ae17e1 iyr:2013
   eyr:2024
   ecl:brn pid:760753108 byr:1931
   hgt:179cm

   hcl:#cfa07d eyr:2025 pid:166559648
   iyr:2011 ecl:brn hgt:59in")

(def test-input-day-6
  ["abc

   a
   b
   c

   ab
   ac

   a
   a
   a
   a

   b"])

(deftest test-day-1-1
  (testing "Day 1 1"
    (is (= #{514579} (day-1-1 test-input-day-1)))))

(deftest test-day-1-2
  (testing "Day 1 2"
    (is (= #{241861950} (day-1-2 test-input-day-1)))))

(deftest test-day-2-1
  (testing "Day 2 1"
    (is (= 2 (day-2-1 test-input-day-2)))))

(deftest test-day-1-2
  (testing "Day 1 2"
    (is (= 1 (day-2-2 test-input-day-2)))))

(deftest test-day-3-1
  (testing "Day 3 1"
    (is (= 7 (day-3-1 test-input-day-3)))))

(deftest test-day-3-2
  (testing "Day 3 2"
    (is (= 336 (day-3-2 test-input-day-3)))))

(deftest test-day-4-1
  (testing "Day 4 1"
    (is (= 2 (day-4-1 test-input-day-4)))))

;; (deftest test-day-4-2
;;   (testing "Day 4 2"
;;     (is (= 2 (day-4-2 test-input-day-4)))))

(deftest test-day-6-1
  (testing "Day 6 1"
    (is (= 4 (day-6-1 test-input-day-6)))))

(deftest test-day-6-2
  (testing "Day 6 2"
    (is (= 0 (day-6-2 test-input-day-6)))))
