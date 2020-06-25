(ns sample-app.test-helper.core.spec
  (:require [sample-app.test-helper.core :as sut]
            [clojure.spec.alpha :as s]))

(s/fdef sut/instrument-specs
  :args (s/cat :f fn?)
  :ret any?)

(s/fdef sut/with-system
  :args (s/cat :bindings (s/coll-of any?
                                    :kind vector?
                                    :count 2)
               :body (s/* any?)))

(s/fdef sut/with-db
  :args (s/cat :bindings (s/coll-of any?
                                    :kind vector?
                                    :count 2)
               :body (s/* any?)))

(s/fdef sut/with-db-data
  :args (s/cat :bindings (s/coll-of any?
                                    :kind vector?
                                    :count 2)
               :body (s/* any?)))
