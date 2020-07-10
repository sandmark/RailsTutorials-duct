(ns sample-app.view.users.new
  (:require [hiccup.form
             :as
             form
             :refer
             [email-field form-to label password-field submit-button text-field]]
            [ring.util.anti-forgery :as anti-forgery]
            [sample-app.view.shared.partial :refer [error-messages]]
            [sample-app.view.template :refer [page]]))

(defn render
  ([]
   (render nil nil))

  ([user errors]
   (page
    {:title "Sign up"}
    [:h1 "Sign up"]
    [:div.row
     [:div.col-md-6.col-md-offset-3
      (form-to
       [:post "/users"]
       (error-messages errors)
       #_(anti-forgery/anti-forgery-field)
       (label :name "Name")
       (text-field :name (:name user))
       (label :email "Email")
       (email-field :email (:email user))
       (label :password "Password")
       (password-field :password)
       (label :password-confirmation "Password Confirmation")
       (password-field :password-confirmation)
       (submit-button {:class "btn btn-primary"}
                      "Create my account"))]])))
