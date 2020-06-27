(ns sample-app.validation.schema.user-test
  (:require [sample-app.validation.schema.user :as sut]
            [clojure.test :as t]
            [struct.core :as struct]))

(defn valid-user? [user]
  (struct/valid? user sut/validator))

(t/deftest validation-test
  (let [user {:name "Example User" :email "user@example.com"}]
    (t/testing "should be valid"
      (t/is (valid-user? user)))

    (t/testing "name should be present"
      (t/is (not (valid-user? (assoc user :name "     ")))))))
