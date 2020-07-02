(ns sample-app.view.users.show
  (:require [hiccup.core :as hiccup]
            [sample-app.view.template :refer [page]]
            [sample-app.view.users.helper :as helper :refer [gravatar-for]]))

(defn render [user]
  (let [name (hiccup/h (:name user))]
    (page
     {:title name}
     [:div.row
      [:aside.col-md-4
       [:section.user_info
        [:h1
         (gravatar-for user)
         name]]]])))
