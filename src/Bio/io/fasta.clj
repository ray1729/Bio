(ns bio.io.fasta
  (:use [clojure.contrib.io :only (read-lines)]))

(defn- split-multiseq
  [lines]
  (lazy-seq
   (when-let [s (seq lines)]
     (let [desc (subs (first s) 1)
	   run  (take-while #(not (re-seq #"^>" %)) (rest s))]
       (cons {:desc desc, :seq (apply str run)} (split-multiseq (drop (count run) (rest s))))))))

(defn read-fasta
  [f]
  (split-multiseq (read-lines f)))

(comment    
  ;; Fetch some FASTA from EnsEMBL and parse it
  (def abcd1 (read-fasta "http://www.ensembl.org/Mus_musculus/Gene/Export?db=core;g=ENSMUSG00000031378;output=fasta;r=X:70961936-70983873;strand=feature;param=cdna;param=coding;param=peptide;param=utr5;param=utr3;param=exon;param=intron;genomic=unmasked;_format=Text"))  
  
  ;; Print the description of each sequence
  (doseq [s abcd1] (println (:desc s)))  
  
  ;; Extract a named sequence
  (filter #(= "ENSMUSG00000031378:ENSMUST00000002084 ENSMUSE00000699444 exon:KNOWN_protein_coding" (:desc %)) abcd1)
  
  ;; Define a function to compute GC pct
  (defn gc-pct [s]
    (when-let [s (seq s)]
      (let [c  (count s)
	    gc (count (filter #{\G \C} s))]
	(/ (* 100.0 gc) c))))
  
  ;; Compute the GC pct of each sequence in the FASTA file, store the results in a hash keyed on description
  (into {} (map #(vector (:desc %) (gc-pct (:seq %))) abcd1))
  
  ;; Another way to do the same thing
  (zipmap (map :desc abcd1) (map (comp gc-pct :seq) abcd1)))

