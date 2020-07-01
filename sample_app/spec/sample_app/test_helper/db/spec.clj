(ns sample-app.test-helper.db.spec
  (:require [clojure.spec.alpha :as s]
            [sample-app.boundary.db.core.spec :as db]
            [sample-app.test-helper.db :as sut]))

(s/def ::tablename string?)
(s/def ::table (s/keys :req-un [::tablename]))
(s/def ::db-data-map (s/map-of keyword?
                               (s/coll-of ::db/row-map :min-count 1)))

(s/fdef sut/select-tables
  :args (s/cat :db ::db/db)
  :ret (s/coll-of ::table))

(s/fdef sut/truncate-table!
  :args (s/cat :db ::db/db
               :table ::table)
  :ret any?)

(s/fdef sut/insert-db-data!
  :args (s/cat :db ::db/db
               :db-data-map ::db-data-map)
  :ret any?)

(s/fdef sut/truncate-all-tables!
  :args (s/cat :db ::db/db)
  :ret any?)
