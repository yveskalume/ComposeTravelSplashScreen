package com.yvkalume.composesplashscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.yvkalume.composesplashscreen.ui.theme.ComposeSplashScreenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSplashScreenTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = Int.MAX_VALUE,
        isPlaying = true,
    )

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (headTexts, image, button) = createRefs()

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .constrainAs(headTexts) {
                    top.linkTo(parent.top, 24.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(horizontal = 16.dp)
        ) {
            Text(text = "Let's Travel &", style = MaterialTheme.typography.h6)
            Text(
                text = "Explore Beautiful World!",
                style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = "There's so much more to discover. New wave and eco-travel interesting to read",
                style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight(300))
            )
        }

        LottieAnimation(
            modifier = Modifier
                .constrainAs(image) {
                    top.linkTo(headTexts.bottom,32.dp)
                    start.linkTo(parent.start,32.dp)
                    end.linkTo(parent.end,32.dp)
                    bottom.linkTo(button.top, 32.dp)
                }.size(300.dp),
            composition = composition,
            progress = progress,
        )

        Button(
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 24.dp),
            modifier = Modifier
                .constrainAs(button) {
                    bottom.linkTo(parent.bottom, 24.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .height(48.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White, backgroundColor = Color(0xFF079685)
            ),
            onClick = {}
        ) {
            Text(text = "Travel Now", style = MaterialTheme.typography.button)
        }

    }
}