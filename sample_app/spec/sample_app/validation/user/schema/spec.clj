(ns sample-app.validation.user.schema.spec
  (:require [clojure.spec.alpha :as s]
            [sample-app.boundary.db.user.spec :as user]
            [sample-app.validation.schema.user :as sut]))

(s/fdef sut/valid-user?
  :args (s/cat :user ::user/user)
  :ret boolean?)
