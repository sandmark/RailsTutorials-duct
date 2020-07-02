(ns sample-app.styles.core
  (:require [garden.core :as g]
            [garden.def :as gd :refer [defstyles]]))

(defstyles screen
  ;; base
  [:body {:padding-top "60px"}]
  [:section {:overflow :auto}]
  [:textarea {:resize :vertical}]
  [:.center {:text-align :center}
   [:h1 {:margin-bottom "10px"}]]

  ;; typography
  [:h1 :h2 :h3 :h4 :h5 :h6 {:line-height 1}]
  [:h1 {:font-size      "3em"
        :letter-spacing "-2px"
        :margin-bottom  "30px"
        :text-align     :center}]
  [:h2 {:font-size      "1.2em"
        :letter-spacing "-1px"
        :margin-bottom  "30px"
        :text-align     :center
        :font-weight    :normal
        :color          "#777"}]
  [:p {:font-size   "1.1em"
       :line-height "1.7em"}]

  ;; header
  [:#logo {:float          :left
           :margin-left    "10px"
           :font-size      "1.7em"
           :color          "#fff"
           :text-transform :uppercase
           :letter-spacing "-1px"
           :padding-top    "9px"
           :font-weight    :bold}
   [:&:hover {:color           "#fff"
              :text-decoration :none}]]

  ;; footer
  [:footer {:margin-top  "45px"
            :padding-top "5px"
            :border-top  [["1px" "solid" "#eaeaea"]]
            :color       "#777"}
   [:a {:color "#555"}
    [:&:hover {:color "#222"}]]
   [:small {:float :left}]
   [:ul {:float      :right
         :list-style :none}
    [:li {:float       :left
          :margin-left "15px"}]]]

  ;; sidebar
  [:aside
   [:section.user_info {:margin-top "20px"}]
   [:section {:padding    [["10px" 0]]
              :margin-top "20px"}
    [:&:first-child {:border      0
                     :padding-top 0}]
    [:span {:display       :block
            :margin-bottom "3px"
            :line-height   1}]
    [:h1 {:font-size      "1.4em"
          :text-align     :left
          :letter-spacing "-1px"
          :margin-bottom  "3px"
          :margin-top     "0px"}]]]
  [:.gravatar {:float        :left
               :margin-right "10px"}]
  [:.gravatar_edit {:margin-top "15px"}])
