(ns sample-app.security.hash-test
  (:require [clojure.string :as str]
            [clojure.test :as t]
            [sample-app.security.hash :as sut]
            [sample-app.test-helper.core :as helper]))

(t/use-fixtures :once helper/instrument-specs)

(t/deftest encrypt-algorithm-test
  (t/testing "should use bcrypt+sha512"
    (t/is (contains? sut/trusted-algs :bcrypt+sha512))
    (t/is (str/starts-with? (sut/hash-password "test")
                            "bcrypt+sha512"))))

(t/deftest hash-check-test
  (t/testing "should return true if given passwords are same"
    (let [password  "password123"
          encrypted (sut/hash-password password)]
      (t/is (true? (sut/check-password password encrypted)))))

  (t/testing "should return false if given passwords are not same"
    (let [encrypted (sut/hash-password "passwordA")]
      (t/is (false? (sut/check-password "passwordB" encrypted))))))

(t/deftest md5-test
  (t/testing "should generate md5 hash from string"
    (t/is (= "b45cffe084dd3d20d928bee85e7b0f21"
             (sut/md5 "string")))))
