(ns clojure-presentation.core)

;;Some brief examples
(+ 1 2)
(+ (+ 1 2) (+ 3 4))
(+ 1 2 3 4)

;; Short intro to immutability
(def a 4)
(inc a)
(a)

;;Symbol bindings
(let [a 4              ; bind symbol a to 4
      b 8]             ; bind symbol b to 8
  (+ (* a a) (* b b))) ; use symbols

;; Functions
(defn hypot
  [a b]
  (let [a (* a a)
        b (* b b)
        c (+ a b)]
    (Math/sqrt c)))

;; map
(map (fn [x] (* x x)) [1 2 3 4 5])
(map #(* % %) [1 2 3 4 5])

;;multiple arities
(defn argcount
  ([] 0)
  ([x] 1)
  ([x y] 2)
  ([x y & more] (+ (argcount x y) (count more))))


;;Metadata
(defn make-greetings
  "Takes a single argument (a greeting) and returns a fn which also
  takes a single arg (a name). When the returned fn is
  called, prints out a greeting message to stdout and returns nil."
  [^String greeting]
  (fn [^String name]
    (str greeting " " name "!")))

;;Truthiness, conditionals & predicates
(def age 16)

(if (>= age 21)
  "beer"
  "lemonade")

(when (and (>= age 21) (< age 25))
  (println "Are you sure you're 21?")
  (println "Okay, here's your beer. Cheers!"))

;; Data Structures

;; Lists

; first construct a function call using a list of individually quoted symbols
(def a-plus-b (list '+ 'a 'b))
; #'user/a-plus-b

; a-plus-b ; show resulting list
; (+ a b)

(eval a-plus-b) ; treat data as code: evaluate...
                ; 12

;; Vectors
(def v (vector 1 2 3 4)) ;; same as (def v [1 2 3 4])
(get v 2) ;; 3

;; hash maps
(def m (hash-map :k1 "Bla" :k2 "Yo"))

(def db {:toxi    {:name "Karsten"
                   :address {:city "London"}
                   :urls ["http://toxiclibs.org" "http://thi.ng"]}
         :nardove {:name "Ricardo"
                   :urls ["http://nardove.com"]}})

(get-in db [:toxi :address :city])
                                        ; "London"
(get-in db [:nardove :address :city] "I think Bournemouth")
                                        ; "I think Bournemouth"
(get-in db [:nardove :urls 0])
                                        ; "http://nardove.com"


;; Sets
(def lucky-numbers [1 2 3 4 4 2 1 3])
(set lucky-numbers)
(into #{} lucky-numbers)

(def g
  #{#{:toxi :ricardo}
    #{:mia :toxi}
    #{:filip :toxi}
    #{:filip :ricardo}})


;; Adding elements
(conj [1 2 3] 4 5 6)
(conj {:a 1 :b 2} [:c 3] [:d 4])
(conj #{1 2 3} 1 2 4 5)
(cons [1 2 3] 4)

;; Updating
(assoc {:a 23} :b 42 :a 88)

(def db {:toxi {}
         :nardove
         {:name "Ricardo"
          :urls ["http://nardove.com"]
          :address {:city "Glasgow"}}})
(assoc-in db [:nardove :address :city] "Aberdeen")


;;Removing
(dissoc {:a 23 :b 42} :b)
(disj #{10 20 30} 20)
(pop [1 2 3 4])


;;Immutability
(def v [1 2 3 4])
(def v2 (conj v 5))

;;Destructering
(defn f [v]
  (let [[x y z] v]
    (* x y z)))

(f [1 2 3])


;;Sequence
(first [1 2 3 4])
(rest [1 2 3 4])

;; Looping
(defn sum [a-seq]
  (let [sum-helper (fn [acc a-seq]
                     (if (empty? a-seq)
                       acc
                       (recur (+ acc (first a-seq))
                              (rest a-seq))))]
    (sum-helper 0 a-seq)))

(defn product [a-seq]
  (let [product-helper (fn [acc a-seq]
                         (if (empty? a-seq)
                           acc
                           (recur (* acc (first a-seq))
                                  (rest a-seq))))]
    (product-helper 1 a-seq)))

(defn sum2 [a-seq]
  (reduce + 0 a-seq))
(defn product2 [a-seq]
  (reduce * 1 a-seq))

;; map
(map (fn [x] (* x x)) [1 2 3 4 5])
(map #(* % %) [1 2 3 4 5])
(#(* % %2) 10 2) ; call anon fn with args: 10, 2

;; filter
(filter even? (range 10))

(interleave [:clojure :lisp :scheme] [2007 1958 1970])
(interpose "," #{"cyan" "black" "yellow" "magenta"})


;; Polymorphism
(defmulti encounter (fn [x y] [(:Species x) (:Species y)]))
(defmethod encounter [:Bunny :Lion] [b l] :run-away)
(defmethod encounter [:Lion :Bunny] [l b] :eat)
(defmethod encounter [:Lion :Lion] [l1 l2] :fight)
(defmethod encounter [:Bunny :Bunny] [b1 b2] :mate)

(def b1 {:Species :Bunny :other :stuff})
(def b2 {:Species :Bunny :other :stuff})
(def l1 {:Species :Lion :other :stuff})
(def l2 {:Species :Lion :other :stuff})
