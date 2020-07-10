(ns sample-app.view.shared.partial.spec
  (:require [clojure.spec.alpha :as s]
            [sample-app.boundary.db.user.spec :as user]
            [sample-app.validation.user.schema.spec :as v.user]
            [sample-app.view.shared.partial :as sut]))

(s/def ::errors (s/nilable
                 (s/keys :opt-un [::user/name
                                  ::user/email
                                  ::v.user/pasword
                                  ::v.user/password-confirmation])))

(s/fdef sut/error-messages
  :args (s/cat :errors ::errors)
  :ret (s/nilable string?))
