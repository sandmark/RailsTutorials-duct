(ns sample-app.view.users.new-test
  (:require [clojure.test :as t]
            [kerodon.core :refer [within session visit fill-in press follow-redirect]]
            [kerodon.test :refer [has status? text?]]
            [sample-app.test-helper.core :as helper]
            [sample-app.view.users.new :as sut]))

(t/use-fixtures :once helper/instrument-specs)

(t/deftest create-with-errors-test
  (helper/with-system [system (helper/test-system)]
    (let [routes (:duct.router/ataraxy system)
          user   (get-in (helper/test-data) [:sample-app.validation.schema.user-test/users :example-user])]
      (t/testing "should reject without :name"
        (-> (session routes)
            (visit "/signup")
            (fill-in :#email (:email user))
            (fill-in :#password (:password user))
            (fill-in :#password-confirmation (:password user))
            (press :.btn.btn-primary)
            (has (text? "The form contains 1 error")))))))
