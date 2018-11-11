(ns clojure-presentation.functional)

(defn argcount
  ([] 0)
  ([x] 1)
  ([x y] 2)
  ([x y & more] (+ (argcount x y) (count more))))

(defn -main [& args]
  (do
    (println (argcount))
    (println (argcount 7))
    (println (argcount 89 "A"))
    (println (argcount "XC" 8 12 "j" :some-keyword))))
