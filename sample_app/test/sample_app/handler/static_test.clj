(ns sample-app.handler.static-test
  (:require [clojure.test :as t]
            [kerodon.core :refer [within session visit]]
            [kerodon.test :refer [has status? text? link?]]
            [sample-app.test-helper.core :as helper :refer [with-system]]))

(t/use-fixtures :once helper/instrument-specs)

(t/deftest static-pages-with-kerodon-test
  (with-system [system (helper/test-system)]
    (let [routes     (:duct.router/ataraxy system)
          base-title (:sample-app.view.template/title (helper/test-data))]
      (t/testing "should get root"
        (-> (session routes)
            (visit "/")
            (has (status? 200))
            (within [:title]
                    (has (text? base-title)))
            (has (link? "Sign up now!" "/signup"))))

      (t/testing "should get help"
        (-> (session routes)
            (visit "/help")
            (has (status? 200))
            (within [:title]
                    (has (text? (str "Help | " base-title))))))

      (t/testing "should get about"
        (-> (session routes)
            (visit "/about")
            (has (status? 200))
            (within [:title]
                    (has (text? (str "About | " base-title))))))

      (t/testing "should get contact"
        (-> (session routes)
            (visit "/contact")
            (has (status? 200))
            (within [:title]
                    (has (text? (str "Contact | " base-title)))))))))

(t/deftest site-layout-test
  (t/testing "layout link"
    (with-system [system (helper/test-system)]
      (let [routes (:duct.router/ataraxy system)]
        (-> (session routes)
            (visit "/")
            (has (link? "Home" "/"))
            (has (link? "Help" "/help"))
            (has (link? "About" "/about"))
            (has (link? "Contact" "/contact")))))))
