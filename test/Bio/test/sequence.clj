(ns Bio.test.sequence
  (:use [Bio.sequence :only (guess-alphabet)]
	[lazytest.describe :only (describe it given)]))

(describe guess-alphabet 
  (it "returns nil for empty seq"
    (nil? (guess-alphabet '()))) 
  
  (it "returns nil for empty string"
    (nil? (guess-alphabet "")))
  
  (it "recognizes DNA"
    (= :dna (guess-alphabet "GGCGTAGACGCTGTCGTTACTGCCGGGCTGCTCAGCGGACAAACACGCCTGGTGCCCCGG")))

  (it "recognizes RNA"
    (= :rna (guess-alphabet "GGCGUAGACGCUGUCGUUACUGCCGGGCUGCUCAGCGGACAAACACGCCUGGUGCCCCGG")))

  (it "recognizes protein"
    (= :protein (guess-alphabet "MPVLSTPRPSRVTTLKRTAVVLALTAYGVHKIYPLVRQCLTPARGPQVPAGEPTQEASGA"))))


