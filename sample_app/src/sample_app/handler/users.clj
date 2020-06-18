(ns sample-app.handler.users
  (:require [ataraxy.response :as response]
            [integrant.core :as ig]
            [sample-app.view.users.new :as view.new]))

(defmethod ig/init-key :sample-app.handler.users/new [_ _]
  (fn [_]
    [::response/ok (view.new/render)]))
