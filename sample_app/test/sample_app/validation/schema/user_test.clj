(ns sample-app.validation.schema.user-test
  (:require [sample-app.validation.schema.user :as sut]
            [clojure.test :as t]))

(t/deftest validation-test
  (let [user {:name "Example User" :email "user@example.com"}]
    (t/testing "should be valid"
      (t/is (sut/valid-user? user)))

    (t/testing "name should be present"
      (t/is (not (sut/valid-user? (assoc user :name "     ")))))

    (t/testing "email should be present"
      (t/is (not (sut/valid-user? (assoc user :email "    ")))))))
