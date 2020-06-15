(ns sample-app.handler.hello
  (:require [ataraxy.response :as response]
            [integrant.core :as ig]))

(defmethod ig/init-key :sample-app.handler/hello [_ _]
  (fn [_]
    [::response/ok "hello, world!"]))
