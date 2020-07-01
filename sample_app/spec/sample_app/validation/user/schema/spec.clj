(ns sample-app.validation.user.schema.spec
  (:require [clojure.spec.alpha :as s]
            [sample-app.boundary.db.user.spec :as user]
            [sample-app.validation.schema.user :as sut]))

(s/def ::password string?)
(s/def ::password-confirmation string?)
(s/def ::user-schema (s/keys :opt-un [::user/name ::user/email ::password ::password-confirmation
                                      ::user/created_at ::user/password_digest]))
(s/def ::errors (s/nilable ::user-schema))

(s/fdef sut/valid-user?
  :args (s/cat :user ::user-schema)
  :ret boolean?)

(s/fdef sut/validate-user
  :args (s/cat :user ::user-schema)
  :ret (s/tuple ::errors ::user-schema))
