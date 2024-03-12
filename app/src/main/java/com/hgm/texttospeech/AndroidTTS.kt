package com.hgm.texttospeech

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.Locale

/**
 * @author：HGM
 * @created：2024/3/6 0006
 * @description：
 **/
class AndroidTTS(
      private val context: Context
) {

      private var textToSpeech: TextToSpeech? = null

      // 语音初始化
      fun ttsCreate() {
            textToSpeech = TextToSpeech(context) {
                  if (it == TextToSpeech.SUCCESS) {
                        textToSpeech?.let { tts ->
                              tts.language = Locale.getDefault()
                        }
                  }
            }
      }

      // 中止朗读
      private fun ttsStop() {
            textToSpeech?.stop()
      }

      // 销毁朗读
      fun ttsDestroy() {
            if (textToSpeech?.isSpeaking == false) {
                  // 不管是否正在朗读TTS都被打断
                  textToSpeech?.stop()
                  textToSpeech?.shutdown()
            }
      }

      // 朗读内容
      fun ttsSpeaking(content: String) {
            if (textToSpeech?.isSpeaking == true) {
                  ttsStop()
            }

            textToSpeech?.let { txtToSpeech ->
                  txtToSpeech.setSpeechRate(1.0f)
                  txtToSpeech.speak(
                        content, TextToSpeech.QUEUE_ADD, null, null
                  )
            }
      }

}