(ns sample-app.view.users.helper.spec
  (:require [clojure.spec.alpha :as s]
            [sample-app.boundary.db.user.spec :as user]
            [sample-app.view.users.helper :as sut]))

(s/def ::image-tag vector?)

(s/fdef sut/gravatar-for
  :args (s/cat :user ::user/user)
  :ret ::image-tag)
