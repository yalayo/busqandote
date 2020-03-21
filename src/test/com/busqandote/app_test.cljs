(ns com.busqandote.app-test
  (:require [cljs.test :refer-macros [deftest is testing run-tests]]
            [cljsjs.react]
            [cljsjs.react.dom]
            [cljs-react-test.utils :as tu]
            [cljs-react-test.simulate :as sim]
            [dommy.core :as dom :refer-macros [sel1 sel]]))

(enable-console-print!)

(def ^:dynamic c)

(def app-state (atom "Busqandote"))

(defn web-name []
  (js/React.createElement
   "div" #js {}
   (js/React.createElement
    "p" #js {}
    @app-state)))

(deftest web-name-component
  (testing "The initial state is displayed"
    (let [c (tu/new-container!)
          _ (js/ReactDOM.render (web-name) c)
          display-node (sel1 c [:p])]
      (is (re-find #"Arya" (.-innerHTML display-node)))
      (tu/unmount! c))))

(deftest test-numbers
  (is (= 1 1)))

(cljs.test/run-tests)
