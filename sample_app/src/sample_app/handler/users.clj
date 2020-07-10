(ns sample-app.handler.users
  (:require [ataraxy.response :as response]
            [integrant.core :as ig]
            [sample-app.boundary.db.user :as db.user]
            [sample-app.security.hash :as hash]
            [sample-app.validation.schema.user :as validate.user]
            [sample-app.view.users.new :as view.new]
            [sample-app.view.users.show :as view.show]))

(defn- user-params [params]
  (select-keys params [:name :email :password :password-confirmation]))

(defn- validate-user [db {:keys [email password] :as user}]
  (let [email-error?   (when (db.user/email-exists? db email)
                         {:email "This address is already used."})
        [errors user'] (validate.user/validate-user (user-params user))
        errors'        (merge errors email-error?)]
    [errors' (assoc user' :password_digest (hash/hash-password password))]))

(defmethod ig/init-key :sample-app.handler.users/new [_ _]
  (fn [_]
    [::response/ok (view.new/render)]))

(defmethod ig/init-key ::show [_ {:keys [db]}]
  (fn [{[_ id] :ataraxy/result}]
    (let [user (db.user/get-user-by-id db id)]
      [::response/ok (view.show/render user)])))

(defmethod ig/init-key ::create [_ {:keys [db]}]
  (fn [{[_ params] :ataraxy/result}]
    (let [user                            (user-params params)
          [validate-error validated-user] (validate-user db user)]
      (if validate-error
        [::response/conflict (view.new/render validated-user validate-error)]
        (let [[db-error db-user] (db.user/create-user db validated-user)]
          (if db-error
            [::response/conflict (view.new/render db-user db-error)]
            [::response/created (view.show/render db-user)]))))))
