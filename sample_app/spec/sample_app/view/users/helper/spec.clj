(ns sample-app.view.users.helper.spec
  (:require [clojure.spec.alpha :as s]
            [sample-app.boundary.db.user.spec :as user]
            [sample-app.view.users.helper :as sut]))

(s/def ::image-tag vector?)
(s/def ::size integer?)
(s/def ::opts (s/keys :req-un [::size]))

(s/fdef sut/gravatar-for
  :args (s/cat :user ::user/user
               :opts (s/? ::opts))
  :ret ::image-tag)
