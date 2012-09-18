(ns leiningen.notes
  "Print all the FIXME, TODO, OPTIMIZE in the source files with line numbers"
  (:use [clojure.java.io :only [file reader]]))

;; copy from clojure.contrib.seq-utils
(defn- indexed
  "Returns a lazy sequence of [index, item] pairs, where items come
from 's' and indexes count up from zero.

(indexed '(a b c d)) => ([0 a] [1 b] [2 c] [3 d])"
  [s]
  (map vector (iterate inc 0) s))


(defn- print-note [file, line, count]
  (if (re-find #"FIXME|OPTIMIZE|TODO|REVISIT|XXX" line)
    (println (str (. file getPath) ":" (+ 1 count) "   " line))))

(defn- find-notes [f]
  (dorun (map #(print-note f (second %) (first %))
              (indexed (line-seq (reader f))))))

(defn notes
  [project & args]
  (let [files (filter #(not (or (. % isDirectory)
                                (re-find #"/\.git/|/lib/|/classes/" (. % getPath))))
                      (file-seq (file (project :root))))]
    (dorun (map find-notes files))))
