(ns sample-app.view.template-test
  (:require [clojure.test :as t]
            [kerodon.core :refer [within]]
            [kerodon.impl :as ki]
            [kerodon.test :refer [has text?]]
            [sample-app.test-helper.core :as helper]
            [sample-app.view.template :as sut]))

(t/use-fixtures :once helper/instrument-specs)

(t/deftest page-title-test
  (let [base-title (:sample-app.view.template/title (helper/test-data))]
    (t/testing "Base Title"
      (-> {:response {:body (sut/page {})}}
          ki/include-parse
          (within [:title]
                  (has (text? base-title)))))

    (t/testing "Custom Title"
      (-> {:response {:body (sut/page {:title "Custom"})}}
          ki/include-parse
          (within [:title]
                  (has (text? (str "Custom | " base-title))))))))
