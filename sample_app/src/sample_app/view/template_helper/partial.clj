(ns sample-app.view.template-helper.partial
  (:require [hiccup.core :refer [html]]
            [hiccup.element :refer [link-to]]
            [hiccup.page :refer [include-css include-js]]))

(defn css []
  (include-css "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
               "/css/style.css"))

(defn footer []
  [:footer.footer
   [:small
    "The " (link-to "https://railstutorial.jp" "Ruby on Rails Tutorial") " by "
    (link-to "http://www.michaelhartl.com/" "Michael Hartl")]
   [:nav
    [:ul
     [:li (link-to "/about" "About")]
     [:li (link-to "/contact" "Contact")]
     [:li (link-to "http://news.railstutorial.org/" "News")]]]])

(defn header []
  [:header.navbar.navbar-fixed-top.navbar-inverse
   [:div.container
    (link-to {:id "logo"} "#" "sample app")
    [:nav
     [:ul.nav.navbar-nav.navbar-right
      [:li (link-to "/" "Home")]
      [:li (link-to "/help" "Help")]
      [:li (link-to "#" "Log in")]]]]])

(defn shim []
  (html
   "<!--[If lt IE 9]>"
   (include-js "https://cdnjs.cloudflare.com/ajax/libs/html5shiv/r29/html5.min.js")
   "<![endif]-->"))
