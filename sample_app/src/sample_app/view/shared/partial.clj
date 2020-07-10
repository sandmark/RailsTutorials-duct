(ns sample-app.view.shared.partial
  (:require [hiccup.core :as hiccup]
            [clojure.string :as str]))

(defn- camel-case [s]
  (str/join " " (map str/capitalize (str/split s #"\s"))))

(defn- field-name [field]
  (-> field
      name
      (str/replace #"-" " ")
      camel-case))

(defn error-messages [errors]
  (hiccup/html
   (when errors
     [:div#error_explanation
      [:div.alert.alert-danger
       "The form contains " (count errors) " error(s)."]
      [:ul
       (for [[field msg] (vec errors)]
         [:li (str (field-name field) ": " (str/capitalize msg))])]])))
