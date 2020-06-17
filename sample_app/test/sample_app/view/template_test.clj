(ns sample-app.view.template-test
  (:require [clojure.test :as t]
            [kerodon.core :as k]
            [kerodon.impl :as ki]
            [kerodon.test :as kt]
            [sample-app.test-helper.core :as helper]
            [sample-app.view.template :as sut]))

(t/deftest page-title-test
  (let [base-title (:sample-app.view.template/title (helper/test-data))]
    (t/testing "Base Title"
      (-> {:response {:body (sut/page {})}}
          ki/include-parse
          (k/within [:title]
                    (kt/has (kt/text? base-title)))))

    (t/testing "Custom Title"
      (-> {:response {:body (sut/page {:title "Custom"})}}
          ki/include-parse
          (k/within [:title]
                    (kt/has (kt/text? (str "Custom | " base-title))))))))
