(ns com.busqandote.cdk
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]
            [stedi.cdk.alpha :as cdk]
            [uberdeps.api :as uberdeps]))

(cdk/import [[App Construct Duration Stack] :from "@aws-cdk/core"]
            [[Bucket] :from "@aws-cdk/aws-s3"]
            [[Code Function Runtime Tracing] :from "@aws-cdk/aws-lambda"]
            [[LambdaRestApi] :from "@aws-cdk/aws-apigateway"])

(def code
  (let [jarpath "build/lambda.zip"]
    (Code/fromAsset jarpath)))

(def app (App))

(def stack (Stack app "busqandote-web-lambda"))

(def bucket (Bucket stack "busqandote-web-lambda-bucket"))

(def busqandote-web-fn
  (Function stack
            "busqandote-fn"
            {:code        code
             :handler     "main.handler"
             :runtime     (:NODEJS_10_X Runtime)
             :environment {"BUCKET" (:bucketName bucket)}
             :memorySize 128
             :timeout (Duration/seconds 10)
             }))

(Bucket/grantWrite bucket busqandote-web-fn)

(def api (LambdaRestApi stack "busqandote-web" {:handler busqandote-web-fn}))
