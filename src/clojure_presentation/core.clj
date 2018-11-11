(ns clojure-presentation.core)

;;multiple arities
(defn argcount
  ([] 0)
  ([x] 1)
  ([x y] 2)
  ([x y & more] (+ (argcount x y) (count more))))


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
                                        ; #user/my-vals
(set lucky-numbers)
                                        ; #{1 2 3 4}
(into #{} lucky-numbers)
                                        ; #{1 2 3 4}
lucky-numbers
                                        ; [1 2 3 4 4 2 1 3]



(defmulti encounter (fn [x y] [(:Species x) (:Species y)]))
(defmethod encounter [:Bunny :Lion] [b l] :run-away)
(defmethod encounter [:Lion :Bunny] [l b] :eat)
(defmethod encounter [:Lion :Lion] [l1 l2] :fight)
(defmethod encounter [:Bunny :Bunny] [b1 b2] :mate)

(def b1 {:Species :Bunny :other :stuff})
(def b2 {:Species :Bunny :other :stuff})
(def l1 {:Species :Lion :other :stuff})
(def l2 {:Species :Lion :other :stuff})
