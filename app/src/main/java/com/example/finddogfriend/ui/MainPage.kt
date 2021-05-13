package com.example.finddogfriend.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.finddogfriend.ui.theme.GrayF1
import com.example.finddogfriend.ui.theme.GrayF9
import com.example.finddogfriend.R
@Composable
fun MainPage(navController: NavController) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)) {
        TopItems(navController)
    }
}

@Composable
private fun TopItems(navController: NavController) {
    Column {
        MainTitle()
        MainSearchBar()
        ClassifyRows()
        SecondTitle()
        DogRows(navController)
    }
}

@Composable
private fun DogRows(navController: NavController) {
    val viewModel: DogFriendViewModel = viewModel()
    LazyRow {
        itemsIndexed(viewModel.dogs) { index, dog ->
            Image(
                painterResource(dog.picture), dog.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(top = 36.dp, bottom = 72.dp)
                    .padding(start = if (index == 0) 36.dp else 0.dp)
                    .padding(end = if (index == viewModel.dogs.size - 1) 36.dp else 24.dp)
                    .width(200.dp)
                    .height(280.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable {
                        navController.navigate("detail/${dog.id}")
                    }
            )
        }
    }
}

@Composable
private fun SecondTitle() {
    MontText(
        text = "Adopt Me", fontSize = 32.sp,
        modifier = Modifier
            .padding(top = 56.dp)
            .padding(horizontal = 36.dp)
    )
}

@Composable
private fun ClassifyRows() {
    val viewModel: DogFriendViewModel = viewModel()
    LazyRow(Modifier.padding(top = 20.dp)) {
        itemsIndexed(viewModel.classifies) { index, classify ->
            val bgColor = animateColorAsState(if (classify.selected) Color.Black else Color.White, TweenSpec(500))
            Box(
                Modifier
                    .padding(start = if (index == 0) 36.dp else 0.dp)
                    .padding(end = if (index == viewModel.classifies.size - 1) 36.dp else 20.dp)
                    .size(60.dp)
                    .padding(1.dp)
                    .drawBehind {
                        if (classify.selected) {
                            drawCircle(bgColor.value)
                        }
                        drawCircle(GrayF1, style = Stroke(width = 1.dp.toPx()))
                    }
                    .clip(CircleShape)
                    .clickable {
                        if (!classify.selected) {
                            viewModel.classifies.forEachIndexed { innerIndex, classify ->
                                classify.selected = innerIndex == index
                            }
                        }
                    }
            ) {
                MontText(
                    text = classify.name,
                    fontSize = 12.sp,
                    color = if (classify.selected) Color.White else Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
private fun MainSearchBar() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 36.dp)
            .padding(top = 20.dp)
            .height(56.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(color = GrayF9)

    ) {
        Icon(
            painterResource(R.drawable.ic_search),
            "Search bar",
            Modifier
                .padding(horizontal = 15.dp)
                .size(21.dp)
                .align(Alignment.CenterVertically),
            tint = Color.Black
        )
        MontText(
            text = "Search...", fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Composable
private fun MainTitle() {
    MontText(text = "Search Friend", fontSize = 38.sp, fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(top = 72.dp)
            .padding(horizontal = 36.dp))
}