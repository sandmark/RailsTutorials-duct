(ns sample-app.view.users.helper
  (:require [clojure.string :as str]
            [hiccup.element :as e]
            [sample-app.security.hash :as hash]))

(defn gravatar-for [user]
  (let [id  (-> user :email str/lower-case hash/md5)
        url (str "https://secure.gravatar.com/avatar/" id)]
    (e/image {:class "gravatar"} url (:name user))))