(ns sample-app.view.static.help
  (:require [hiccup.core :as hiccup]))

(defn render-help []
  (hiccup/html
   {:mode :html}
   [:h1 "Help"]
   [:p "Get help on the Ruby on Rails Tutorial at the "
    [:a {:href "https://railstutorial.jp/help"} "Rails Tutorial help page"]
    ". "
    "To get help on this sample app, see the "
    [:a {:href "https://railstutorial.jp/#ebook"}
     [:em "Ruby on Rails Tutorial book"]]]))
