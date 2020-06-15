(ns sample-app.handler.static
  (:require [ataraxy.response :as response]
            [integrant.core :as ig]
            [sample-app.view.static.about :as view.about]
            [sample-app.view.static.contact :as view.contact]
            [sample-app.view.static.help :as view.help]
            [sample-app.view.static.home :as view.home]))

(defmethod ig/init-key :sample-app.handler.static/home [_ _]
  (fn [_]
    [::response/ok (str (view.home/render-home))]))

(defmethod ig/init-key :sample-app.handler.static/help [_ _]
  (fn [_]
    [::response/ok (str (view.help/render-help))]))

(defmethod ig/init-key :sample-app.handler.static/about [_ _]
  (fn [_]
    [::response/ok (str (view.about/render-about))]))

(defmethod ig/init-key :sample-app.handler.static/contact [_ _]
  (fn [_]
    [::response/ok (view.contact/render-contact)]))
