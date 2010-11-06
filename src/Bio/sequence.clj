(ns Bio.sequence)

;; The heuristic below is borrowed from BioPerl's Bio::PrimarySeq->_guess_alphabet.
;; It suffers the same probem ("N" represents "aNy" in a DNA sequence or Asparagine
;; in a protein sequence).

(defn guess-alphabet [sequence]
  "Guess the alphabet (:dna, :rna or :protein) of sequence; returns nil for
  empty sequence."
  (when-let [s (seq (filter (comp not #{\. \-}) (seq sequence)))]
    (let [total (count s)
	  acgu  (filter #{\A \C \G \U \N \a \c \g \u \n} s)
          acgt  (filter #{\A \C \G \T \N \a \c \g \t \n} s)]
      (cond
       (> (/ (count acgt) total) 0.85) :dna
       (> (/ (count acgu) total) 0.85) :rna
       :else :protein))))
