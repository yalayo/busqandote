(ns com.busqandote.app)

(defn handler [_ _ cb]
  (cb nil
      #js {:statusCode 200
           :body (js/JSON.stringify "Hello from Shadow")}))
