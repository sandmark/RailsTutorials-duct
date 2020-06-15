(ns sample-app.view.static.about
  (:require [hiccup.core :as hiccup]))

(defn render-about []
  (hiccup/html
   {:mode :html}
   [:html
    [:head
     [:title "About | Ruby on Rails Tutorial Sample App"]]
    [:body
     [:h1 "About"]
     [:p
      [:a {:href "https://railstutorial.jp/"} "Ruby on Rails Tutorial"]
      " is a "
      [:a {:href "https://railstutorial.jp/#ebook"} "book"]
      " and "
      [:a {:href "https://railstutorial.jp/#screencast"} "screencast"]
      " to teach web development with "
      [:a {:href "http://rubyonrails.org/"} "Ruby on Rails"]
      ". This is the sample application for the tutorial."]]]))
