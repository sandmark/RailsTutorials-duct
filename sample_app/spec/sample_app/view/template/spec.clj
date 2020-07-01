(ns sample-app.view.template.spec
  (:require [sample-app.view.template :as sut]
            [clojure.spec.alpha :as s]))

(s/def ::title string?)
(s/def ::props (s/keys :opt-un [::title]))

(s/fdef sut/page
  :args (s/cat :props ::props
               :body (s/* any?)))
