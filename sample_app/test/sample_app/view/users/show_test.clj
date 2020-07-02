(ns sample-app.view.users.show-test
  (:require [clojure.test :as t]
            [sample-app.test-helper.core :as helper]
            [sample-app.view.users.show :as sut]
            [clojure.string :as str]))

(t/deftest users-show-test
  (let [user (get-in (helper/test-data) [:sample-app.boundary.db.user-test/users :example-user])]
    (t/testing "should escape values"
      (t/is (not (str/includes? (sut/render (assoc user :name "><><"))
                                "><><"))))))
