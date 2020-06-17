(ns sample-app.view.static.home
  (:require  [sample-app.view.template :refer [page]]))

(defn render-home []
  (page
   {:title "Home"}
   [:h1 "Sample App"]
   [:p
    "This is the home page for the "
    [:a {:href "https://railstutorial.jp/"} "Ruby on Rails Tutorial"]
    " sample application."]))
