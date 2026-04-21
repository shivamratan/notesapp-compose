package com.ratanapps.notesapp.ui.notes.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ratanapps.notesapp.ui.notes.features.home.viewmodel.MainViewModel
import com.ratanapps.notesapp.ui.notes.navigation.AppNavHost
import com.ratanapps.notesapp.ui.notes.util.ComposeUtil
import com.ratanapps.notesapp.ui.theme.NotesAppTheme
import com.ratanapps.notesapp.utils.NotesConstant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition { viewModel.isLoading.value }

        enableEdgeToEdge()
        setContent {

            val isDark by viewModel.isDark

            NotesAppTheme(darkTheme = isDark) {
                ComposeUtil.showErrorLogs(NotesConstant.MAINACTIVITY_TAG, "Theme is ${if (isDark) "isDark" else "isLight"}")
                AppNavHost()
            }
        }
    }
}