(ns sample-app.validation.user.schema.spec
  (:require [clojure.spec.alpha :as s]
            [sample-app.boundary.db.user.spec :as user]
            [sample-app.validation.schema.user :as sut]))

(s/def ::errors (s/nilable (s/keys :opt-un [::user/name ::user/email])))
(s/def ::validated-user (s/keys :opt-un [::user/name ::user/email]))
(s/def ::validate-result (s/tuple ::errors ::validated-user))

(s/fdef sut/valid-user?
  :args (s/cat :user ::user/user)
  :ret boolean?)

(s/fdef sut/validate-user
  :args (s/cat :user ::user/user)
  :ret ::validate-result)
