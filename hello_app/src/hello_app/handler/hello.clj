(ns hello-app.handler.hello
  (:require [ataraxy.response :as response]
            [integrant.core :as ig]))

(defmethod ig/init-key :hello-app.handler/hello [_ _]
  (fn [_]
    [::response/ok "hello, world!"]))
