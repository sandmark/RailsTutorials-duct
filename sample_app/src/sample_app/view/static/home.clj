(ns sample-app.view.static.home
  (:require [hiccup.element :refer [link-to image]]
            [sample-app.view.template :refer [page]]))

(defn render-home []
  (page
   {}
   [:div.center.jumbotron
    [:h1 "Welcome to the Sample App"]
    [:h2
     "This is the home page for the "
     (link-to "https://railstutorial.jp/" "Ruby on Rails Tutorial")
     " sample application."]

    (link-to {:class "btn btn-lg btn-primary"} "/signup" "Sign up now!")]
   (link-to "http://rubyonrails.org/"
            (image {:alt "Rails logo"} "/images/rails.png"))))
