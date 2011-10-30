(ns sqlkorma.views.common
  (:require [sqlkorma.models.examples :as examples]
            [clojure.string :as string])
  (:use [noir.core :only [defpartial]]
        [hiccup.page-helpers :only [include-css html5 link-to image]]))

(defpartial footer []
  [:div#footer
    [:p "Copyright © 2011 " (link-to "http://chris-granger.com" "Chris Granger") ". All rights reserved."]])

(defpartial header []
            [:div#header
             [:div
              [:h1 (link-to "/" (image "/img/korma-logo.png" "Korma"))]
              [:h2 "Tasty SQL for Clojure"]]
             [:ul
              [:li (link-to "http://groups.google.com/group/sqlkorma" "MailingList")]
              [:li (link-to "/docs" "Docs")]
              [:li (link-to "http://github.com/ibdknox/korma" "Source")]]])

(defpartial layout [& content]
            (html5
              [:head
               [:title "sqlkorma"]
               (include-css "/css/reset.css")
               (include-css "/css/default.css")]
              [:body
               [:div#wrapper
                [:div#contentWrapper
                 (header)
                 content
                 (footer)]]]))

(defpartial code [c & classes]
            [:code {:class (if (seq classes)
                             (string/join " " (map name classes))
                             "")}
             [:pre (examples/get c)]])
