package com.hgm.texttospeech

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/**
 * @author：HGM
 * @created：2024/2/28 0028
 * @description：
 **/
class MainViewModel(
      private val androidTTS: AndroidTTS
) : ViewModel() {

      var text by mutableStateOf("")
            private set


      fun onTextFieldChange(value: String) {
            text = value
      }

      init {
            // 初始化语音服务
            androidTTS.ttsCreate()
      }

      override fun onCleared() {
            super.onCleared()
            // 销毁语音服务
            androidTTS.ttsDestroy()
      }

      fun textToSpeech() {
            androidTTS.ttsSpeaking(text)
      }
}