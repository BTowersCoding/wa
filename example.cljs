(ns example
  (:require
   ["fs" :as fs]
   ["speaker$default" :refer [Speaker]]
   ["web-audio-api$default" :as wa]))

(def ^:dynamic context (wa/AudioContext.))

(def Speaker (require 
(set!
  (.-outStream context)
  (new
    Speaker))



(set! (.-outStream context) 
      (Speaker. #js{:channels (.. context -format -numberOfChannels)
     :bitDepth (.. context -format -bitDepth)
     :sampleRate (.-sampleRate context)}))

(macroexpand '(Speaker. #js {:channels (.. context -format -numberOfChannels),
                     :bitDepth (.. context -format -bitDepth),
                     :sampleRate (.-sampleRate context)}))


(fs/readFile "outfoxing.mp3"
      (fn [err buffer]
        (when err (throw err))
        (.decodeAudioData
          context
          buffer
          (fn [audioBuffer]
            (let [bufferNode (.createBufferSource context)]
              (.connect bufferNode (.-destination context))
              (set! (.-buffer bufferNode) audioBuffer)
              (set! (.-loop bufferNode) true)
              (.start bufferNode 0))))))
