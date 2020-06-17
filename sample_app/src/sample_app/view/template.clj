(ns sample-app.view.template
  (:require [hiccup.element :refer [link-to]]
            [hiccup.page :refer [html5 include-css include-js]]))

(defn- full-title [title]
  (cond->> "Ruby on Rails Tutorial Sample App"
    title (str title " | ")))

(defn page [{:keys [title]} & contents]
  (html5
   [:head
    [:title (full-title title)]
    (include-css "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
                 "/css/style.css")
    "<!--[If lt IE 9]>"
    (include-js "https://cdnjs.cloudflare.com/ajax/libs/html5shiv/r29/html5.min.js")
    "<![endif]-->"]
   [:body
    [:header.navbar.navbar-fixed-top.navbar-inverse
     [:div.container
      (link-to {:id "logo"} "#" "sample app")
      [:nav
       [:ul.nav.navbar-nav.navbar-right
        [:li (link-to "#" "Home")]
        [:li (link-to "#" "Help")]
        [:li (link-to "#" "Log in")]]]]]
    [:div.container contents]
    [:footer.footer
     [:small
      "The " (link-to "https://railstutorial.jp" "Ruby on Rails Tutorial") " by "
      (link-to "http://www.michaelhartl.com/" "Michael Hartl")]
     [:nav
      [:ul
       [:li (link-to "#" "About")]
       [:li (link-to "#" "Contact")]
       [:li (link-to "http://news.railstutorial.org/" "News")]]]]]))
