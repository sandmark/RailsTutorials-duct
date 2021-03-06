(ns sample-app.handler.users-test
  (:require [clojure.test :as t]
            [kerodon.core :refer [session visit within]]
            [kerodon.test :refer [has status? text?]]
            [sample-app.test-helper.core :as helper :refer [with-system]]))

(t/use-fixtures :once helper/instrument-specs)

(t/deftest users-handler-test
  (with-system [system (helper/test-system)]
    (let [routes     (:duct.router/ataraxy system)
          base-title (:sample-app.view.template/title (helper/test-data))]
      (t/testing "should get new"
        (-> (session routes)
            (visit "/signup")
            (has (status? 200))
            (within [:title] (has (text? (str "Sign up | " base-title)))))))))
