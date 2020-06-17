(ns sample-app.view.template
  (:require [hiccup.page :refer [html5]]))

(defn page [{:keys [title]} & contents]
  (html5
   [:head
    [:title (str title " | Ruby on Rails Tutorial Sample App")]]
   [:body contents]))
