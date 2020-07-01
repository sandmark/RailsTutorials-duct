(ns sample-app.security.hash
  (:require [buddy.hashers :as buddy.hash])
  (:import java.math.BigInteger
           java.security.MessageDigest))

(def trusted-algs #{:bcrypt+sha512})

(defn hash-password [password]
  (buddy.hash/derive password {:alg :bcrypt+sha512}))

(defn check-password [attempt encrypted]
  (buddy.hash/check attempt encrypted {:limit trusted-algs}))

(defn md5 [s]
  (let [alg (MessageDigest/getInstance "MD5")
        raw (.digest alg (.getBytes s))]
    (format "%032x" (BigInteger. 1 raw))))
