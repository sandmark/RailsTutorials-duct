(ns sample-app.handler.users
  (:require [ataraxy.response :as response]
            [integrant.core :as ig]
            [sample-app.boundary.db.user :as db.user]
            [sample-app.view.users.new :as view.new]
            [sample-app.view.users.show :as view.show]))

(defmethod ig/init-key :sample-app.handler.users/new [_ _]
  (fn [_]
    [::response/ok (view.new/render)]))

(defmethod ig/init-key ::show [_ {:keys [db]}]
  (fn [{[_ id] :ataraxy/result}]
    (let [user (db.user/get-user-by-id db id)]
      [::response/ok (view.show/render user)])))
