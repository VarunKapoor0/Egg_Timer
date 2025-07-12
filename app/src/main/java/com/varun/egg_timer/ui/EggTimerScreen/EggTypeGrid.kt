//package com.varun.egg_timer.ui
//
//import EggTypeCard
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.aspectRatio
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.lazy.grid.GridCells
//import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//import androidx.compose.foundation.lazy.grid.items
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import com.varun.egg_timer.models.EggType
//
//@Composable
//fun EggTypeGrid(eggTypes: List<EggType>, onClick: (EggType) -> Unit) {
//    LazyVerticalGrid(
//        columns = GridCells.Fixed(2),
//        contentPadding = PaddingValues(16.dp),
//        horizontalArrangement = Arrangement.spacedBy(16.dp),
//        verticalArrangement = Arrangement.spacedBy(16.dp),
//        modifier = Modifier.fillMaxSize()
//    ) {
//        items(eggTypes) { egg ->
//            EggTypeCard(
//                eggType = egg,
//                onClick = onClick,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .aspectRatio(1.3f)
//            )
//        }
//    }
//}