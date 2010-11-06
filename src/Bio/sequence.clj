(ns Bio.sequence)

;; The heuristic below is borrowed from BioPerl's Bio::PrimarySeq::_guess_alphabet

(defn guess-alphabet [sequence]
  "Guess the alphabet (:dna, :rna or :protein) of sequence; returns nil for
  empty sequence."
  (when-let [s (seq (filter (comp not #{\. \-}) (seq sequence)))]
    (let [total (count s)
	  u     (count (filter #{\U \u} s))
          acgt  (count (filter #{\A \C \G \T \N \a \c \g \t \n} s))]
      (cond
       (> (/ acgt total) 0.85) :dna
       (> (/ (+ acgt u) total) 0.85) :rna
       :else :protein))))
