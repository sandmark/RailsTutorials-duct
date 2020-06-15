(ns sample-app.handler.static-test
  (:require [ataraxy.response :as response]
            [clojure.test :as t]
            [integrant.core :as ig]
            [ring.mock.request :as mock]
            [sample-app.handler.static :as sut]))

(defn request
  ([handler-key uri opts]
   (let [{:keys [params method key location]} (merge {:params   {}
                                                      :method   :get
                                                      :key      handler-key
                                                      :location uri}
                                                     opts)
         handler                              (ig/init-key key params)]
     (handler (mock/request method location))))

  ([handler-key uri]
   (request handler-key uri {})))

(t/deftest static-pages-test
  (t/testing "should get home"
    (let [[response body] (request :sample-app.handler.static/home "/static_pages/home")]
      (t/is (= ::response/ok response))
      (t/is (= "Sample App"
               (re-find #"Sample App" body)))))

  (t/testing "should get help"
    (let [[response body] (request :sample-app.handler.static/help "/static_pages/help")]
      (t/is (= ::response/ok response))
      (t/is (= "Get help"
               (re-find #"Get help" body)))))

  (t/testing "should get about"
    (let [[response body] (request :sample-app.handler.static/about "/static_pages/about")]
      (t/is (= ::response/ok response))
      (t/is (= "This is the sample"
               (re-find #"This is the sample" body))))))
