(ns sample-app.boundary.db.user.spec
  (:require [clojure.spec.alpha :as s]))

(s/def ::name string?)
(s/def ::email string?)
(s/def ::created_at #(instance? org.joda.time.DateTime %))
(s/def ::updated_at #(instance? org.joda.time.DateTime %))
(s/def ::user (s/keys :req-un [::name ::email]
                      :opt-un [::created_at ::updated_at]))
