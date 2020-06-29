(ns sample-app.boundary.db.user
  (:require [clj-time.core :as time]
            clj-time.jdbc
            duct.database.sql
            [honeysql.core :as sql]
            [sample-app.boundary.db.core :as db]))

(defprotocol UserDatabase
  (get-user-by-id [db id])
  (create-user [db user]))

(extend-protocol UserDatabase
  duct.database.sql.Boundary
  (get-user-by-id [db id]
    (db/select-first db (sql/build :select :*
                                   :from :users
                                   :where [:= :id id])))
  (create-user [db user]
    (let [now (time/now)]
      (try
        (db/insert! db :users (assoc user
                                     :created_at now
                                     :updated_at now))
        (catch org.postgresql.util.PSQLException _
          nil)))))
