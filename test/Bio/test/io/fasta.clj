(ns Bio.test.io.fasta  
  (:use [Bio.io.fasta :only (read-fasta)]
	[lazytest.describe :only (describe it given)]))

(def fasta-str
">MCHU - Calmodulin - Human, rabbit, bovine, rat, and chicken
ADQLTEEQIAEFKEAFSLFDKDGDGTITTKELGTVMRSLGQNPTEAELQDMINEVDADGNGTID
FPEFLTMMARKMKDTDSEEEIREAFRVFDKDGNGYISAAELRHVMTNLGEKLTDEEVDEMIREA
DIDGDGQVNYEEFVQMMTAK*
>gi|5524211|gb|AAD44166.1| cytochrome b [Elephas maximus maximus]
LCLYTHIGRNIYYGSYLYSETWNTGIMLLLITMATAFMGYVLPWGQMSFWGATVITNLFSAIPYIGTNLV
EWIWGGFSVDKATLNRFFAFHFILPFTMVALAGVHLTFLHETGSNNPLGLTSDSDKIPFHPYYTIKDFLG
LLILILLLLLLALLSPDMLGDPDNHMPADPLNTPLHIKPEWYFLFAYAILRSVPNKLGGVLALFLSIVIL
GLMPFLHTSKHRSMMLRPLSQALFWTLTMDLLTLTWIGSQPVEYPYTIIGQMASILYFSIILAFLPIAGX
IENY" )

(describe read-fasta
  (given [r (java.io.StringReader. fasta-str)    
	  f (read-fasta r)]
    (it "returns two entries"
      (= 2 (count f)))
    (it "has correct description for first entry"
      (= (:desc (first f)) "MCHU - Calmodulin - Human, rabbit, bovine, rat, and chicken"))
    (it "has correct alphabet for first entry"
      (= (:alphabet (first f)) :protein))
    (it "has correct sequence for first entry"
      (= (:seq (first f)) "ADQLTEEQIAEFKEAFSLFDKDGDGTITTKELGTVMRSLGQNPTEAELQDMINEVDADGNGTIDFPEFLTMMARKMKDTDSEEEIREAFRVFDKDGNGYISAAELRHVMTNLGEKLTDEEVDEMIREADIDGDGQVNYEEFVQMMTAK*"))
    (it "has correct description for second entry"
      (= (:desc (second f)) "gi|5524211|gb|AAD44166.1| cytochrome b [Elephas maximus maximus]"))
    (it "has correct alphabet for second entry"
      (= (:alphabet (second f)) :protein))
    (it "has correct sequence for second entry"
      (= (:seq (second f)) "LCLYTHIGRNIYYGSYLYSETWNTGIMLLLITMATAFMGYVLPWGQMSFWGATVITNLFSAIPYIGTNLVEWIWGGFSVDKATLNRFFAFHFILPFTMVALAGVHLTFLHETGSNNPLGLTSDSDKIPFHPYYTIKDFLGLLILILLLLLLALLSPDMLGDPDNHMPADPLNTPLHIKPEWYFLFAYAILRSVPNKLGGVLALFLSIVILGLMPFLHTSKHRSMMLRPLSQALFWTLTMDLLTLTWIGSQPVEYPYTIIGQMASILYFSIILAFLPIAGXIENY"))))
