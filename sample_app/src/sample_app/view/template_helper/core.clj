(ns sample-app.view.template-helper.core)

(defn full-title [title]
  (cond->> "Ruby on Rails Tutorial Sample App"
    title (str title " | ")))
