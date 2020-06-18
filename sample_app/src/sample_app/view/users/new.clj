(ns sample-app.view.users.new
  (:require [sample-app.view.template :refer [page]]))

(defn render []
  (page
   {:title "Sign up"}
   [:h1 "Sign up"]
   [:p "This will be a signup page for new users."]))
