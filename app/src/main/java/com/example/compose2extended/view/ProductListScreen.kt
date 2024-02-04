package com.example.compose2extended.view

// ProductListScreen.kt
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose2extended.navigation.BottomBarScreen
import com.example.compose2extended.navigation.BottomNavGraph
import com.example.compose2extended.viewmodel.UserViewModel

/*
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.compose2extended.model.data.ProductX
import com.example.compose2extended.viewmodel.ProductViewModel
*/

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProductListScreen(navController: NavController,userViewModel: UserViewModel) {
    //val currentUser = userViewModel.getCurrentUser()
    Text(text = "hello")
    val navController = rememberNavController()
    androidx.compose.material.Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        BottomNavGraph(navController = navController,userViewModel = userViewModel)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Profile,
       // BottomBarScreen.Cart,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(modifier = Modifier
        .graphicsLayer {
            shape= RoundedCornerShape(
                topStart = 20.dp,
                topEnd = 20.dp)
        clip= true}
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            androidx.compose.material.Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        //unselectedContentColor = androidx.compose.material.LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}


//    val products by productViewModel.productsState.collectAsState()
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        // Your other UI elements can go here
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Display list of products
//        LazyColumn {
//            items(products) { product ->
//                ProductItem(product = product)
//                Spacer(modifier = Modifier.height(16.dp))
//            }
//        }
//    }
//}
//
//@OptIn(ExperimentalCoilApi::class)
//@Composable
//fun ProductItem(product: ProductX) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(200.dp),
//        shape = MaterialTheme.shapes.medium
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp)
//        ) {
//            // Load product image
//            Image(
//                painter = rememberImagePainter(data = product.thumbnail),
//                contentDescription = null,
//                modifier = Modifier
//                    .size(100.dp)
//                    .clip(MaterialTheme.shapes.medium),
//                contentScale = ContentScale.Crop
//            )
//
//            Spacer(modifier = Modifier.width(16.dp))
//
//            // Display product details
//            Column {
//                Text(text = product.title, style = MaterialTheme.typography.headlineMedium)
//                Spacer(modifier = Modifier.height(8.dp))
//                Text(text = "$${product.price}", style = MaterialTheme.typography.headlineMedium)
//                Spacer(modifier = Modifier.height(8.dp))
//                Text(text = "${product.discountPercentage}% Off", style = MaterialTheme.typography.displayMedium)
//                Spacer(modifier = Modifier.height(8.dp))
//                Text(text = "Rating: ${product.rating}", style = MaterialTheme.typography.bodyMedium)
//            }
//        }
//    }
//}
