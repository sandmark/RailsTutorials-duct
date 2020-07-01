(ns sample-app.security.hash.spec
  (:require [sample-app.security.hash :as sut]
            [clojure.spec.alpha :as s]))

(s/def ::password string?)
(s/def ::encrypted string?)

(s/def ::md5 (s/and string?
                    #(= 32 (count %))))

(s/fdef sut/hash-password
  :args (s/cat :password ::password)
  :ret ::encrypted)

(s/fdef sut/check-password
  :args (s/cat :attempt ::password
               :encrypted ::encrypted)
  :ret boolean?)

(s/fdef sut/md5
  :args (s/cat :s string?)
  :ret ::md5)
