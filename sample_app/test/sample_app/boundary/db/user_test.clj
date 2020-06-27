(ns sample-app.boundary.db.user-test
  (:require [clojure.test :as t]
            [sample-app.boundary.db.user :as sut]
            [sample-app.test-helper.core :as helper :refer [with-system with-db]]
            [sample-app.boundary.db.core :as db]
            [honeysql.core :as sql]))

(t/use-fixtures :once helper/instrument-specs)

(defn create-user [db user]
  (let [id           (sut/create-user db user)
        created-user (sut/get-user-by-id db id)]
    created-user))

(t/deftest user-save-test
  (with-system [system (helper/test-system)]
    (let [{:keys [example-user]} (:sample-app.db/users (helper/test-data))]
      (with-db [db system]
        (t/testing "should save user"
          (t/is (zero? (db/select-count db (sql/build :from :users))))
          (sut/create-user db example-user)
          (t/is (= 1 (db/select-count db (sql/build :from :users))))))

      (with-db [db system]
        (t/testing "should save timestamps"
          (let [created-user (create-user db example-user)]
            (t/is (instance? org.joda.time.DateTime (:created_at created-user)))
            (t/is (instance? org.joda.time.DateTime (:updated_at created-user))))))

      (with-db [db system]
        (t/testing "should be same timestamps when created"
          (let [created-user (create-user db example-user)]
            (t/is (= (:created_at created-user)
                     (:updated_at created-user))))))

      (with-db [db system]
        (t/testing "should not be saved without validations"
          (sut/create-user db {:name "    " :email "invalid"})
          (t/is (zero? (db/select-count db (sql/build :from :users)))))))))
