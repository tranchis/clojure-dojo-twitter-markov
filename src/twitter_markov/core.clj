(ns twitter-markov.core)

(defn phrase->structure [phrase]
  "Phrase = vector of lower-case words
  Output: map of :count and :children"
  (let [phrase-1 (drop-last 1 phrase)
        phrase-2 (drop 1 phrase)
        pairs (map vector phrase-1 phrase-2)
        all-words (into #{} phrase)]
    (apply merge
     (map (fn [word]
            (let [matches (filter #(= (first %) word) pairs)
                  next-words (map second matches)]
              {word {:count (count (filter #(= % word) phrase))
                     :children (zipmap next-words
                                       (map (fn [n] 1) next-words))}}))
          all-words))))


