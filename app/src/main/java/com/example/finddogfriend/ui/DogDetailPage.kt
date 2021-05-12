package com.example.finddogfriend.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finddogfriend.R
import com.example.finddogfriend.data.Dog
import com.example.finddogfriend.ui.theme.GrayA1
import kotlin.math.roundToInt

@Composable
fun DogDetail() {
    val viewModel: DogFriendViewModel = viewModel()
    val offsetPercent = animateFloatAsState(if (viewModel.detailShowing) 0f else 1f)
    Box(modifier = Modifier
        .fillMaxSize()
        .percentOffsetY(offsetPercent.value)
        .background(Color.White)) {
        val currentDog = viewModel.currentDog
        if(currentDog != null){
            DetailTop(currentDog)
            DetailBottom(currentDog)
        }
    }
}

@Composable
private fun DetailBottom(currentDog: Dog) {
    Column(
        Modifier
            .offset(y = 300.dp)
            .fillMaxSize()
            .clip(RoundedCornerShape(topStart = 45.dp, topEnd = 45.dp))
            .background(Color.White)
            .padding(top = 36.dp)
    ) {
        Row(
            Modifier
                .padding(horizontal = 36.dp)
                .padding(bottom = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MontText(
                text = currentDog.name, fontSize = 36.sp, color = Color.Black,
                modifier = Modifier.weight(1f)
            )
            Icon(
                painterResource(R.drawable.ic_dog_park), "dog",
                modifier = Modifier.size(36.dp)
            )
        }
        MontText(
            text = "Any diet should be appropriate to the dog's age. Clean, fresh water" +
                    "should be available at all times.",
            color = GrayA1,
            lineHeight = 24.sp,
            modifier = Modifier
                .padding(horizontal = 36.dp)
                .padding(top = 10.dp, bottom = 40.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 36.dp)
                .fillMaxWidth()
                .padding(1.dp)
                .height(72.dp)
//            .clip(RoundedCornerShape(36.dp))
                .drawBehind {
                    val cornerRadius = CornerRadius(36.dp.toPx(), 36.dp.toPx())
                    drawRoundRect(
                        GrayA1,
                        style = Stroke(width = 1.dp.toPx()),
                        cornerRadius = cornerRadius
                    )
                    drawRoundRect(
                        Color.Black,
                        cornerRadius = cornerRadius,
                        size = Size(size.width * 0.8f, size.height)
                    )
                }
        ) {
            MontText(text = "Meet with Pet", fontSize = 22.sp, color = Color.White,
            modifier = Modifier
                .padding(start = 32.dp)
                .weight(1f))
            Icon(painterResource(id = R.drawable.ic_right), contentDescription = "go",
            Modifier.padding(horizontal = 20.dp).size(18.dp))
        }
    }
}

@Composable
private fun DetailTop(currentDog: Dog) {
    Image(
        painterResource(currentDog.picture), currentDog.name,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
    )
}

fun Modifier.percentOffsetY(percent: Float): Modifier = this.layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)
    layout(placeable.width, placeable.height) {
        val offset = (percent * placeable.height).roundToInt()
        placeable.placeRelative(0, offset)
    }
}