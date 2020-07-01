(ns sample-app.boundary.db.user-test
  (:require [clojure.test :as t]
            [sample-app.boundary.db.user :as sut]
            [sample-app.test-helper.core :as helper :refer [with-system with-db]]
            [sample-app.boundary.db.core :as db]
            [honeysql.core :as sql]))

(t/use-fixtures :once helper/instrument-specs)

(t/deftest user-save-test
  (with-system [system (helper/test-system)]
    (let [example-user (get-in (helper/test-data) [::users :example-user])]
      (with-db [db system]
        (t/testing "should save user"
          (t/is (zero? (db/select-count db (sql/build :from :users))))
          (t/is (nil? (first (sut/create-user db example-user))))
          (t/is (= 1 (db/select-count db (sql/build :from :users))))))

      (with-db [db system]
        (t/testing "should save timestamps"
          (let [created-user (->> (sut/create-user db example-user)
                                  second
                                  :id
                                  (sut/get-user-by-id db))]
            (t/is (instance? org.joda.time.DateTime (:created_at created-user)))
            (t/is (instance? org.joda.time.DateTime (:updated_at created-user))))))

      (with-db [db system]
        (t/testing "should be same timestamps when created"
          (let [created-user (sut/create-user db example-user)]
            (t/is (= (:created_at created-user)
                     (:updated_at created-user))))))

      (with-db [db system]
        (t/testing "should not be saved with duplicated email"
          (t/is (nil? (first (sut/create-user db example-user))))
          (t/is (contains? (first (sut/create-user db example-user))
                           :email)))))))

(t/deftest user-select-test
  (let [user (get-in (helper/test-data) [::users :example-user])]
    (with-system [system (helper/test-system)]
      (with-db [db system]
        (t/testing "should return true if email exists"
          (sut/create-user db user)
          (t/is (sut/email-exists? db (:email user)))))

      (with-db [db system]
        (t/testing "should return false if email doesn't exist"
          (t/is (not (sut/email-exists? db "not@exist.com"))))))))
