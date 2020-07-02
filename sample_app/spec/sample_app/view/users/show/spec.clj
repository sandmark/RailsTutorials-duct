(ns sample-app.view.users.show.spec
  (:require [sample-app.boundary.db.user.spec :as user]
            [sample-app.view.users.show :as sut]
            [clojure.spec.alpha :as s]))

(s/fdef sut/render
  :args (s/cat :user ::user/user))
