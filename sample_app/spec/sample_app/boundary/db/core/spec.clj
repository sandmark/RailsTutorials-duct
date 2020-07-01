(ns sample-app.boundary.db.core.spec
  (:require [clojure.spec.alpha :as s]
            [sample-app.boundary.db.core :as sut]))

(s/def ::spec any?)
(s/def ::db (s/keys :req-un [::spec]))
(s/def ::sql-map (s/map-of keyword? any?))
(s/def ::table keyword?)
(s/def ::row-map (s/map-of keyword? any?))
(s/def ::row-count (s/and integer? (complement neg?)))
(s/def ::row-id (s/and integer? pos?))

(s/fdef sut/select
  :args (s/cat :db ::db
               :sql-map ::sql-map)
  :ret (s/coll-of ::row-map))

(s/fdef sut/select-first
  :args (s/cat :db ::db
               :sql-map ::sql-map)
  :ret (s/nilable ::row-map))

(s/fdef sut/select-count
  :args (s/cat :db ::db
               :sql-map ::sql-map)
  :ret ::row-count)

(s/fdef sut/insert!
  :args (s/cat :db ::db
               :table ::table
               :row-map ::row-map)
  :ret ::row-id)

(s/fdef sut/insert-multi!
  :args (s/cat :db ::db
               :table ::table
               :row-maps (s/coll-of ::row-map :min-count 1))
  :ret ::row-count)
