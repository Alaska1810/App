package com.example.app.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.example.app.R
import com.example.app.ui.navigation.Screens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navHost: NavHostController
){
    val scale = remember{
        androidx.compose.animation.core.Animatable(0f)
    }
    LaunchedEffect(key1 = true){
        scale.animateTo(targetValue = 0.9f,
            animationSpec = tween(durationMillis =  100,
                easing = {
                    OvershootInterpolator(8f)
                        .getInterpolation(it)
                }
            ),
        )
        delay(1000L)
        //navController.navigate(Screens.LoginScreen.name)
        if(FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()){
            navHost.navigate(Screens.Login.route)
        }else{
            navHost.navigate(Screens.Login.route){
                popUpTo(Screens.SplashScreen.route){
                    inclusive = true
                }
            }
        }
    }

    val color = MaterialTheme.colorScheme.primary
    Surface(modifier = Modifier
        .padding()
        .size(3000.dp)
        .scale(scale.value),
        //shape = CircleShape,
        color = Color.White,
        //border = BorderStroke(width = 2.dp, color = color)
    ) {
        Column(modifier = Modifier
            .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.size(280.dp),painter = painterResource(id = R.drawable.splash1) ,
                contentDescription = "Icono")

            Text("VitaAlarms",fontSize = 25.sp,
                style = MaterialTheme.typography.titleMedium,
                color = Color.DarkGray.copy(alpha = 0.7f,
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text("¡¡Mantente ocupado", fontSize = 20.sp,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.DarkGray
            )
            Text(text = "cuidando tu salud!!", fontSize = 20.sp,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.DarkGray
            )
        }
    }
}