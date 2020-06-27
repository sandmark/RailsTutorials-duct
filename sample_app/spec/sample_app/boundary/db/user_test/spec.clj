(ns sample-app.boundary.db.user-test.spec
  (:require [clojure.spec.alpha :as s]
            [sample-app.boundary.db.core.spec :as db]
            [sample-app.boundary.db.user.spec :as user]
            [sample-app.boundary.db.user-test :as sut]))

(s/fdef sut/create-user
  :args (s/cat :db ::db/db
               :user ::user/user)
  :ret ::user/user)
