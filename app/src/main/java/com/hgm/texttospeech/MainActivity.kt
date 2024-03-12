package com.hgm.texttospeech

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hgm.texttospeech.ui.theme.TextToSpeechTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

      private val viewModel: MainViewModel by viewModels(factoryProducer = {
            object : ViewModelProvider.Factory {
                  override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return MainViewModel(AndroidTTS(applicationContext)) as T
                  }
            }
      })

      override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                  TextToSpeechTheme {
                        Surface(
                              modifier = Modifier.fillMaxSize(),
                              color = MaterialTheme.colorScheme.background
                        ) {
                              Column(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                              ) {
                                    OutlinedTextField(
                                          value = viewModel.text,
                                          onValueChange = viewModel::onTextFieldChange
                                    )

                                    Spacer(modifier = Modifier.height(16.dp))

                                    Button(onClick = {
                                          viewModel.textToSpeech()
                                    }) {
                                          Text(text = "朗读")
                                    }
                              }
                        }
                  }
            }
      }
}
