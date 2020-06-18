(ns sample-app.view.template
  (:require [hiccup.page :refer [html5]]
            [sample-app.view.template-helper.core :as helper]
            [sample-app.view.template-helper.partial :refer [css footer header shim]]))

(defn page [{:keys [title]} & contents]
  (html5
   [:head
    [:title (helper/full-title title)]
    (css)
    (shim)]
   [:body
    (header)
    [:div.container contents]
    (footer)]))
