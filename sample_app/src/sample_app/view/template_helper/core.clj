(ns sample-app.view.template-helper.core)

(defn full-title
  ([]
   (full-title nil))
  ([title]
   (cond->> "Ruby on Rails Tutorial Sample App"
     title (str title " | "))))
