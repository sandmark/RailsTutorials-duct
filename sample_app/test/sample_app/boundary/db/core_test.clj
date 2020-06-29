(ns sample-app.boundary.db.core-test
  (:require [clojure.test :as t]
            [sample-app.boundary.db.core :as sut]
            [sample-app.test-helper.core :as helper :refer [with-db-data with-system]]
            [honeysql.core :as sql]))

(t/deftest select-test
  (with-system [system (helper/test-system)]
    (let [db-data-map  (get-in (helper/test-data) [:sample-app.boundary.db.user-test/users :multiple-users])
          example-user (get-in (helper/test-data) [:sample-app.boundary.db.user-test/users :example-user])
          db           (:duct.database.sql/hikaricp system)]
      (with-db-data [system {:users [example-user]}]
        (t/testing "select-first"
          (t/is (= example-user
                   (select-keys (sut/select-first db (sql/build :select :* :from :users))
                                [:name :email :password_digest])))))

      (with-db-data [system db-data-map]
        (t/testing "select"
          (let [users         (sut/select db (sql/build :select :* :from :users))
                example-users (:users db-data-map)]
            (t/is (= (map (juxt :name :email) users)
                     (map (juxt :name :email) example-users)))))

        (t/testing "select-count"
          (t/is (= 2 (sut/select-count db (sql/build :select :* :from :users)))))))))
