(ns sample-app.view.template-helper.core-test
  (:require [clojure.test :as t]
            [sample-app.test-helper.core :as helper]
            [sample-app.view.template-helper.core :as sut]))

(t/use-fixtures :once helper/instrument-specs)

(t/deftest template-helper-test
  (t/testing "full title helper"
    (let [base-title (:sample-app.view.template/title (helper/test-data))]
      (t/is (= (sut/full-title)
               base-title))
      (t/is (= (sut/full-title "Help")
               (str "Help | " base-title))))))
