(ns sample-app.handler.static
  (:require [ataraxy.response :as response]
            [hiccup.core :as hiccup]
            [integrant.core :as ig]
            [sample-app.view.static.help :as view.help]
            [sample-app.view.static.home :as view.home]))

(defmethod ig/init-key :sample-app.handler.static/home [_ _]
  (fn [_]
    [::response/ok (str (view.home/render-home))]))

(defmethod ig/init-key :sample-app.handler.static/help [_ _]
  (fn [_]
    [::response/ok (str (view.help/render-help))]))
