(ns sample-app.boundary.db.user.spec
  (:require [clojure.spec.alpha :as s]
            [sample-app.boundary.db.core.spec :as db]
            [sample-app.boundary.db.user :as sut]))

(s/def ::name string?)
(s/def ::email string?)
(s/def ::created_at #(instance? org.joda.time.DateTime %))
(s/def ::updated_at #(instance? org.joda.time.DateTime %))
(s/def ::user (s/keys :req-un [::name ::email]
                      :opt-un [::created_at ::updated_at]))

(s/fdef sut/get-user-by-id
  :args (s/cat :db ::db/db
               :id integer?)
  :ret (s/nilable ::db/row-map))

(s/fdef sut/create-user
  :args (s/cat :db ::db/db
               :user ::user)
  :ret (s/nilable ::db/row-id))
