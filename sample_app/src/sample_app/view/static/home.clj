(ns sample-app.view.static.home
  (:require [hiccup.core :as hiccup]))

(defn render-home []
  (hiccup/html
   {:mode :html}
   [:html
    [:head
     [:title "Home | Ruby on Rails Tutorial Sample App"]]
    [:body
     [:h1 "Sample App"]
     [:p
      "This is the home page for the "
      [:a {:href "https://railstutorial.jp/"} "Ruby on Rails Tutorial"]
      " sample application."]]]))
