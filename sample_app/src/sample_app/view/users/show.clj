(ns sample-app.view.users.show
  (:require [sample-app.view.template :refer [page]]))

(defn render [user]
  (page
   {}
   (str (:name user) ", " (:email user))))
