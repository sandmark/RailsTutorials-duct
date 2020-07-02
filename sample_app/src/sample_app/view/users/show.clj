(ns sample-app.view.users.show
  (:require [sample-app.view.template :refer [page]]
            [sample-app.view.users.helper :as helper :refer [gravatar-for]]))

(defn render [user]
  (page
   {:title (:name user)}
   [:div.row
    [:aside.col-md-4
     [:section.user_info
      [:h1
       (gravatar-for user)
       (:name user)]]]]))
