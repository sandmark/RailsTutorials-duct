(ns sample-app.validation.schema.user
  (:require [struct.core :as struct]
            [clojure.string :as str]))

(def presented
  {:message  "must be presented"
   :optional true
   :validate (comp not str/blank?)})

(def validator
  [[:name struct/required struct/string presented]])
