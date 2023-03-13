package com.sibtech.dinlipi.presentation.views.screens

import android.window.SplashScreen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sibtech.twofifty.R

@Composable
fun SplashScreen(navController:NavHostController) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.notepad))
    val animationState = animateLottieCompositionAsState(composition = composition)
    
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        LottieAnimation(composition = composition, progress = {animationState.progress})
    }

    if(animationState.isAtEnd && animationState.isPlaying){
        navController.popBackStack()
        navController.navigate("login")
    }
}