(ns sample-app.view.users.helper-test
  (:require [sample-app.view.users.helper :as sut]
            [clojure.test :as t]
            [clojure.string :as str]))

(t/deftest gravatar-for-test
  (let [user {:name            "test"
              :email           "test@example.com"
              :password_digest "test"}]
    (t/testing "email should be lower-case"
      (t/is (= (sut/gravatar-for user)
               (sut/gravatar-for (assoc user :email "TEST@EXAMPLE.COM")))))

    (t/testing "default size should be 80"
      (t/is (str/includes? (sut/gravatar-for user)
                           "?s=80")))

    (t/testing "should take options"
      (t/is (str/includes? (sut/gravatar-for user {:size 100})
                           "?s=100")))))
