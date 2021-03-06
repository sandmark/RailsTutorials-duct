(defproject sample_app "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [funcool/struct "1.4.0"]
                 [buddy/buddy-hashers "1.4.0"]
                 [duct/core "0.8.0"]
                 [duct/module.ataraxy "0.3.0"]
                 [duct/module.logging "0.5.0"]
                 [duct/module.web "0.7.0"]
                 [honeysql "1.0.444"]
                 [duct/module.sql "0.6.0"]
                 [org.postgresql/postgresql "42.2.14"]]
  :plugins [[duct/lein-duct "0.12.1"]
            [lein-garden "0.3.0"]]
  :main ^:skip-aot sample-app.main
  :resource-paths ["resources" "target/resources"]
  :prep-tasks     ["javac" "compile" ["run" ":duct/compiler"]]
  :middleware     [lein-duct.plugin/middleware]

  :garden
  {:builds [{:source-paths ["src"]
             :stylesheet   sample-app.styles.core/screen
             :compiler     {:output-to "resources/sample_app/public/css/style.css"}}]}

  :profiles
  {:dev          [:project/dev :profiles/dev]
   :repl         {:prep-tasks   ^:replace ["javac" "compile"]
                  :repl-options {:init-ns user}}
   :uberjar      {:aot :all}
   :profiles/dev {}
   :project/dev  {:source-paths   ["dev/src" "spec"]
                  :resource-paths ["dev/resources" "test/resources"]
                  :dependencies   [[integrant/repl "0.3.1"]
                                   [eftest "0.5.9"]
                                   [kerodon "0.9.1"]
                                   [hawk "0.2.11"]
                                   [orchestra "2019.02.06-1"]
                                   [garden "1.3.10"]]
                  :plugins        [[jonase/eastwood "0.3.11"]
                                   [lein-cljfmt "0.6.7"]
                                   [lein-cloverage "1.1.2"]
                                   [lein-kibit "0.1.8"]]
                  :aliases        {"test-coverage" ^{:doc "Execute cloverage."}
                                   ["cloverage" "--ns-exclude-regex" "^(:?dev|user)$" "--codecov" "--junit"]

                                   "lint" ^{:doc "Execute cljfmt check, eastwood and kibit."}
                                   ["do"
                                    ["cljfmt" "check"]
                                    ["eastwood" "{:source-paths [\"src\"]
                                                  :test-paths []}"]
                                    ["kibit"]]}}})
