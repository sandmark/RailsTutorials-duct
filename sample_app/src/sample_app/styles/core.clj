(ns sample-app.styles.core
  (:require [garden.def :as gd :refer [defstyles]]))

(defstyles screen
  [:body {:padding-top "60px"}]
  [:section {:overflow :auto}]
  [:textarea {:resize :vertical}]
  [:.center {:text-align :center}
   :h1 {:margin-bottom "10px"}])
