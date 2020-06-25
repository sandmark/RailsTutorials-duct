(ns sample-app.test-helper.core
  (:require [clojure.java.io :as io]
            [duct.core :as duct]
            [integrant.core :as ig]
            [sample-app.test-helper.db :refer [insert-db-data! truncate-all-tables!]]
            [orchestra.spec.test :as stest]))

(duct/load-hierarchy)

(defn instrument-specs [f]
  (stest/instrument)
  (f))

(defn test-system []
  (-> (io/resource "sample_app/config.edn")
      duct/read-config
      (duct/prep-config [:duct.profile/dev :duct.profile/test])))

(defn test-data []
  (-> (io/resource "sample_app_test/data.edn")
      slurp
      read-string))

(defmacro with-system [[bound-var binding-expr] & body]
  `(let [~bound-var (ig/init ~binding-expr)]
     (try
       ~@body
       (finally (ig/halt! ~bound-var)))))

(defmacro with-db [[bound-var system] & body]
  `(let [~bound-var (:duct.database.sql/hikaricp ~system)]
     (try
       ~@body
       (finally (truncate-all-tables! ~bound-var)))))

(defmacro with-db-data [[system db-data-map] & body]
  `(let [db# (:duct.database.sql/hikaricp ~system)]
     (try
       (insert-db-data! db# ~db-data-map)
       ~@body
       (finally (truncate-all-tables! db#)))))
