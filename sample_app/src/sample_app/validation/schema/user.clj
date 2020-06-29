(ns sample-app.validation.schema.user
  (:require [struct.core :as struct]
            [clojure.string :as str]))

(def presented
  {:message  "must be presented"
   :optional true
   :validate (comp not str/blank?)})

(def downcase
  {:message  "must be downcase"
   :optional true
   :coerce   #(str/lower-case %)})

(def validator
  [[:name
    struct/required
    struct/string
    presented
    [struct/max-count 50]]

   [:email
    struct/required
    struct/string
    struct/email
    downcase
    presented
    [struct/max-count 255]]

   [:password
    struct/required
    struct/string]

   [:password-confirmation
    struct/required
    struct/string
    [struct/identical-to :password]]])

(defn valid-user? [user]
  (struct/valid? user validator))

(defn validate-user [user]
  (struct/validate user validator))
