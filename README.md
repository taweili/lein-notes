lein-notes
======

Simple Leiningen plugin to read inline notes in the source codes. Just
came to Clojure from a few years in Ruby and I miss the 'rake notes'
to show inline notes in the source. I don't seem to find a Clojure
replacement for it so I decided to write one. This is one of my very
first Clojure project so the codes are a bit messy but it gets the job
done.

Install
-----

## Leiningen 1.x
```
% lein plugin install lein-notes 0.0.2
```
## Leiningen 2.x

add it to ~/.lein/profiles.clj:
```clojure
{:user {:plugins [[lein-notes "0.0.2"]]}}
```

Usage
-----

Right now, this reads FIXME, TODO and OPTIMIZE in all source codes and
skip the classes/, .git/ and lib/ directories. The output is a bit
rough

$ lein notes

License
-----

Copyright (C) 2012 David Li and Hoang Minh Thang

Distributed under the Eclipse Public License, the same as Clojure.
