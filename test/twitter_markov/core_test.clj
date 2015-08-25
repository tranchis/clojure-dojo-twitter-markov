(ns twitter-markov.core-test
  (:use midje.sweet)
  (:require [clojure.test :refer :all]
            [twitter-markov.core :refer :all]))

(def simple-structure
  {"you" {:count 1
          :children {"know" 1}}
   "know" {:count 1
           :children {}}})

(def final-structure
  {
   "I" {
        "count" 2,
        "children" {
                    "know" 2
                    }
        "know" {
                "count" 3,
                "children" {
                            "exactly" 1,
                            "nothing" 1
                            }
                },
        "exactly" {
                   "count" 1,
                   "children" {
                               "what" 1
                               }
                   },
        "what" {
                "count" 1,
                "children" {
                            "happened" 1
                            }
                },
	"happened" {
                    "count" 1,
                    "children" {
                                "here" 1
                                }
                    },
	"here" {
                "count" 1,
                "children" {}
                },
	"nothing" {
                   "count" 1,
                   "children" {}
                   },
	"you" {
               "count" 1
               "children" {
                           "know" 1
                           }
               }}
   })

(def dirty-tweets
  ["I, know exactly, what,... happened here"
   "...   I #know nothing"
   "yOU KNoW"])

(def tweets
  ["I know exactly what happened here"
   "I know nothing"
   "You know"])

(def dummy-clean-tweets
  [["i" "know" "exactly" "what" "happened" "there"]
   ["i" "know" "nothing"]
   ["you" "know"]])

(fact "Structures are generated correctly wrt each phrase"
      (phrase->structure (last dummy-clean-tweets))
      =>
      simple-structure)

