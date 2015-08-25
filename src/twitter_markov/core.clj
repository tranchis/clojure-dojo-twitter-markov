(ns twitter-markov.core)

(defn occurrences [seq elem]
  (count (filter (fn [other] (= other elem)) seq)))

(defn one-word-structure [word pairs phrase]
  (let [matches (filter (fn [pair] (= (first pair) word)) pairs)
        next-words (map second matches)]
    {word {:count (occurrences phrase word)
           :children (zipmap next-words
                             (map (fn [n] (occurrences next-words n))
                                  next-words))}}))

(defn phrase->structure [phrase]
  "Phrase = vector of lower-case words
  Output: map of :count and :children"
  (let [phrase-1 (drop-last 1 phrase)
        phrase-2 (drop 1 phrase)
        pairs (map vector phrase-1 phrase-2)
        all-words (into #{} phrase)]
    (apply merge
           (map (fn [word]
                  (one-word-structure word pairs phrase))
                all-words))))


