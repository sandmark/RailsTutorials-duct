(ns sample-app.test-helper.core
  (:require [clojure.java.io :as io]
            [duct.core :as duct]
            [integrant.core :as ig]))

(duct/load-hierarchy)

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
