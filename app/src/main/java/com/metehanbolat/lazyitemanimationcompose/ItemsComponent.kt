package com.metehanbolat.lazyitemanimationcompose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun ItemsComponent() {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center
    ) {
        items(list) {
            MakeContentAnimated {
                Card(
                    modifier = Modifier
                        .size(240.dp)
                        .padding(16.dp),
                    colors = CardDefaults.cardColors(containerColor = colorList.random()),
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "Box : $it",
                            color = Color.White
                        )

                    }
                }
            }

        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MakeContentAnimated(
    modifier: Modifier = Modifier,
    delay: Long = 1,
    enterAnimation: EnterTransition = scaleIn() + fadeIn(),
    content: @Composable () -> Unit
) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        delay(delay)
        visible = true
    }

    Box(modifier = modifier) {
        if (!visible) {
            Box(modifier = Modifier.alpha(0f)) {
                content()
            }
        }
        AnimatedVisibility(modifier = modifier, visible = visible, enter = enterAnimation) {
            content()
        }
    }
}

val list = (0..100).toList()
val colorList = listOf(
    Color.Black,
    Color.Gray,
    Color.Blue,
    Color.Red,
    Color.Green,
    Color.Cyan,
    Color.Magenta,
    Color.Yellow,
    Color.Red
)