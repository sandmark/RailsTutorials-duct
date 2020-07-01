(ns sample-app.boundary.db.user.spec
  (:require [clojure.spec.alpha :as s]
            [sample-app.boundary.db.core.spec :as db]
            [sample-app.boundary.db.user :as sut]))

(s/def ::name string?)
(s/def ::email string?)
(s/def ::password_digest string?)
(s/def ::created_at #(instance? org.joda.time.DateTime %))
(s/def ::updated_at #(instance? org.joda.time.DateTime %))
(s/def ::user (s/keys :req-un [::name ::email ::password_digest]
                      :opt-un [::created_at ::updated_at]))
(s/def ::validated-user (s/keys :opt-un [::name ::email ::password_digest]))
(s/def ::errors (s/nilable ::validated-user))
(s/def ::validate-result (s/tuple ::errors ::validated-user))

(s/fdef sut/get-user-by-id
  :args (s/cat :db ::db/db
               :id integer?)
  :ret (s/nilable ::user))

(s/fdef sut/create-user
  :args (s/cat :db ::db/db
               :user ::user)
  :ret ::validate-result)

(s/fdef sut/email-exists?
  :args (s/cat :db ::db/db
               :email ::email)
  :ret boolean?)
