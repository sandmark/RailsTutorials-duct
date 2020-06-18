(ns sample-app.view.static.contact
  (:require [sample-app.view.template :refer [page]]))

(defn render-contact []
  (page
   {:title "Contact"}
   [:h1 "Contact"]
   [:p
    "Contact the Ruby on Rails Tutorial about the sample app at the "
    [:a {:href "https://railstutorial.jp/contact"} "contact page"]
    "."]))
