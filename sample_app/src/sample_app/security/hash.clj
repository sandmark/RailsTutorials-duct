(ns sample-app.security.hash
  (:require [buddy.hashers :as buddy.hash]))

(def trusted-algs #{:bcrypt+sha512})

(defn hash-password [password]
  (buddy.hash/derive password {:alg :bcrypt+sha512}))

(defn check-password [attempt encrypted]
  (buddy.hash/check attempt encrypted {:limit trusted-algs}))
