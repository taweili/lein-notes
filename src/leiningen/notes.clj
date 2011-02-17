(ns leiningen.notes
  "Print all the FIXME, TODO, OPTIMIZE in the source files with line numbers"
  (:use [clojure.contrib.duck-streams :only [read-lines]])
  (:use [clojure.contrib.seq-utils :only [indexed]])
  (:use [clojure.java.io :only [file]]))

(defn ^:internal print-note [file, line, count]
  (if (re-find #"FIXME|OPTIMIZE|TODO" line)
    (println (str (. file getPath) ":" (+ 1 count) "   " line))))

(defn ^:internal find-notes [f]
  (dorun (map #(print-note f (second %) (first %)) (indexed (read-lines f)))))

(defn notes 
  [project & args]
  (let [files (filter #(not (or (. % isDirectory)
				(re-find #"/\.git/|/lib/|/classes/" (. % getPath))))
		      (file-seq (file (project :root))))]
    (dorun (map find-notes files))))
