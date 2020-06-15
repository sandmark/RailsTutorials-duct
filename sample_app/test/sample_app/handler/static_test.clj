(ns sample-app.handler.static-test
  (:require [clojure.test :as t]
            [kerodon.core :as k]
            [kerodon.test :as kt]
            [sample-app.test-helper.core :as helper :refer [with-system]]))

(t/deftest static-pages-with-kerodon-test
  (with-system [system (helper/test-system)]
    (let [routes (:duct.router/ataraxy system)]
      (t/testing "should get home"
        (-> (k/session routes)
            (k/visit "/static_pages/home")
            (kt/has (kt/status? 200))))

      (t/testing "should get help"
        (-> (k/session routes)
            (k/visit "/static_pages/help")
            (kt/has (kt/status? 200))))

      (t/testing "should get about"
        (-> (k/session routes)
            (k/visit "/static_pages/about")
            (kt/has (kt/status? 200)))))))
