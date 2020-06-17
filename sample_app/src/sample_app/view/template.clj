(ns sample-app.view.template
  (:require [hiccup.page :refer [html5]]))

(defn- full-title [title]
  (cond->> "Ruby on Rails Tutorial Sample App"
    title (str title " | ")))

(defn page [{:keys [title]} & contents]
  (html5
   [:head
    [:title (full-title title)]]
   [:body contents]))
