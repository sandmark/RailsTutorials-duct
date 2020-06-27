(ns sample-app.validation.schema.user-test
  (:require [sample-app.validation.schema.user :as sut]
            [clojure.test :as t]
            [clojure.string :as str]))

(t/deftest validation-test
  (let [user {:name "Example User" :email "user@example.com"}]
    (t/testing "should be valid"
      (t/is (sut/valid-user? user)))

    (t/testing "name should be present"
      (t/is (not (sut/valid-user? (assoc user :name "     ")))))

    (t/testing "email should be present"
      (t/is (not (sut/valid-user? (assoc user :email "    ")))))

    (t/testing "name should not be too long"
      (let [long-name (str/join (repeat 51 \a))]
        (t/is (not (sut/valid-user? (assoc user :name long-name))))))

    (t/testing "email should not be too long"
      (let [long-email (format "%s@example.com" (str/join (repeat 244 \a)))]
        (t/is (not (sut/valid-user? (assoc user :email long-email))))))

    (t/testing "email validation should accept valid addresses"
      (t/are [address] (sut/valid-user? (assoc user :email address))
        "user@example.com"
        "USER@foo.COM"
        "A_US-ER@foo.bar.org"
        "first.last@foo.jp"
        "alice+bob@baz.cn"))

    (t/testing "email validation should reject invalid addresses"
      (t/are [address] (not (sut/valid-user? (assoc user :email address)))
        "user@example,com"
        "user_at_foo.org"
        "user.name@example."
        "foo@bar_baz.com"
        "foo@bar+baz.com"))))
