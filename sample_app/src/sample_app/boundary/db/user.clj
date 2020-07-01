(ns sample-app.boundary.db.user
  (:require [clj-time.core :as time]
            clj-time.jdbc
            duct.database.sql
            [honeysql.core :as sql]
            [sample-app.boundary.db.core :as db]
            [clojure.string :as str]))

(defprotocol UserDatabase
  (get-user-by-id [db id])
  (create-user [db user])
  (email-exists? [db email]))

(extend-protocol UserDatabase
  duct.database.sql.Boundary
  (get-user-by-id [db id]
    (db/select-first db (sql/build :select :*
                                   :from :users
                                   :where [:= :id id])))

  (create-user [db user]
    (let [now (time/now)]
      (try
        (let [row-id (db/insert! db :users (assoc user
                                                  :created_at now
                                                  :updated_at now))]
          [nil (assoc user :id row-id)])
        (catch org.postgresql.util.PSQLException e
          (let [message (.getMessage e)]
            (cond (str/includes? message "violates unique constraint \"users_email_key\"")
                  [{:email "must be unique"} (dissoc user :email)]

                  :else (throw e)))))))

  (email-exists? [db email]
    (not (zero? (db/select-count db (sql/build :select :id
                                               :from :users
                                               :where [:= :email email]))))))
