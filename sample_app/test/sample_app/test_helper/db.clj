(ns sample-app.test-helper.db
  (:require [sample-app.boundary.db.core :as db]
            [honeysql.core :as sql]
            [clojure.java.jdbc :as jdbc]))

(defn select-tables [db]
  (db/select db (sql/build :select [:schemaname :tablename :tableowner]
                           :from [:pg_tables]
                           :where [:and
                                   [:not-like :schemaname "pg_%"]
                                   [:!= :schemaname "information_schema"]
                                   [:!= :tablename "ragtime_migrations"]])))

(defn truncate-table! [{:keys [spec]} table]
  (jdbc/execute! spec [(str "TRUNCATE TABLE " (:tablename table))]))

(defn insert-db-data! [db db-data-map]
  (doseq [[table records] db-data-map]
    (db/insert-multi! db table records)))

(defn truncate-all-tables! [db]
  (doseq [table (select-tables db)]
    (truncate-table! db table)))
