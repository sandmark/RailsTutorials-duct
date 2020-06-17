(ns sample-app.handler.static-test
  (:require [clojure.test :as t]
            [kerodon.core :as k]
            [kerodon.test :as kt]
            [sample-app.test-helper.core :as helper :refer [with-system]]))

(t/deftest static-pages-with-kerodon-test
  (with-system [system (helper/test-system)]
    (let [routes     (:duct.router/ataraxy system)
          base-title (:sample-app.view.template/title (helper/test-data))]
      (t/testing "should get root"
        (-> (k/session routes)
            (k/visit "/")
            (kt/has (kt/status? 200))
            (k/within [:title]
                      (kt/has (kt/text? base-title)))))

      (t/testing "should get home"
        (-> (k/session routes)
            (k/visit "/static_pages/home")
            (kt/has (kt/status? 200))
            (k/within [:title]
                      (kt/has (kt/text? base-title)))))

      (t/testing "should get help"
        (-> (k/session routes)
            (k/visit "/static_pages/help")
            (kt/has (kt/status? 200))
            (k/within [:title]
                      (kt/has (kt/text? (str "Help | " base-title))))))

      (t/testing "should get about"
        (-> (k/session routes)
            (k/visit "/static_pages/about")
            (kt/has (kt/status? 200))
            (k/within [:title]
                      (kt/has (kt/text? (str "About | " base-title))))))

      (t/testing "should get contact"
        (-> (k/session routes)
            (k/visit "/static_pages/contact")
            (kt/has (kt/status? 200))
            (k/within [:title]
                      (kt/has (kt/text? (str "Contact | " base-title)))))))))
