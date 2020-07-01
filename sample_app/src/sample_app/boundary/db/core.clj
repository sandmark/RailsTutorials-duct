(ns sample-app.boundary.db.core
  (:require [clojure.java.jdbc :as jdbc]
            [honeysql.format :as sql.fmt]
            [honeysql.core :as sql]))

(def quoting :ansi)
(def identifier-quote \")

(defn select [{:keys [spec]} sql-map]
  (jdbc/query spec (sql.fmt/format sql-map :quoting quoting)))

(defn select-first [db sql-map]
  (first (select db sql-map)))

(defn select-count [db sql-map]
  (:row_count (select-first db (assoc sql-map :select [[:%count.* :row-count]]))))

(defn insert! [{:keys [spec]} table row-map]
  (-> spec
      (jdbc/insert! table row-map {:entities (jdbc/quoted identifier-quote)})
      first
      :id))

(defn insert-multi! [{:keys [spec]} table row-maps]
  (-> spec
      (jdbc/execute! (sql/format (sql/build :insert-into table
                                            :values row-maps)
                                 :quoting quoting))
      first))
