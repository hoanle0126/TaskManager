package com.example.taskmanagement.ui.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.taskmanagement.R
import com.example.taskmanagement.data.ListNavItem
import com.example.taskmanagement.viewModel.AuthViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DrawerNavigation(navController: NavController, scope: CoroutineScope, drawerState: DrawerState) {
    val navCurrentBackEntry by navController.currentBackStackEntryAsState()
    var open by remember {
        mutableStateOf(false)
    }
    val rotateArrow by animateFloatAsState(if(open) -90f else 90f)

    ModalDrawerSheet(
        modifier = Modifier
        .width(300.dp)
    ) {
           Scaffold(
               topBar = {
                   //        Drawer Header
                   Row(
                       modifier = Modifier
                           .clickable { open = !open }
                           .fillMaxWidth()
                           .wrapContentHeight()
                           .padding(20.dp),
                       horizontalArrangement = Arrangement.SpaceBetween,
                       verticalAlignment = Alignment.Bottom
                   ) {
                       Column(
                           modifier = Modifier
                       ) {
                           Box(
                               modifier = Modifier
                                   .size(60.dp)
                                   .clip(RoundedCornerShape(99.dp))
                                   .background(Color.Black)
                           )
                           Text(
                               text = AuthViewModel().auth.currentUser?.displayName.toString(),
                               style = MaterialTheme.typography.displayMedium,
                           )
                           Text(
                               text = AuthViewModel().auth.currentUser?.email.toString(),
                               style = MaterialTheme.typography.displaySmall,
                               color = MaterialTheme.colorScheme.outline.copy(0.8f)
                           )
                       }
                       Icon(
                           modifier = Modifier
                               .size(16.dp)
                               .rotate(rotateArrow),
                           painter = painterResource(id = R.drawable.icon_arrow_left),
                           contentDescription = "",
                           tint = MaterialTheme.colorScheme.outline.copy(0.8f)
                       )
                   }
               },
//        End Drawer Header

//               Logout
               bottomBar = {
                   Row(
                       modifier = Modifier
                           .clickable {
                               AuthViewModel().auth.signOut()
                               navController.navigate("Welcome")
                           }
                           .fillMaxWidth()
                           .height(40.dp)
                           .padding(horizontal = 20.dp),
                       verticalAlignment = Alignment.CenterVertically,
                       horizontalArrangement = Arrangement.spacedBy(10.dp)
                   ) {
                       Icon(
                           modifier = Modifier
                               .size(18.dp),
                           painter = painterResource(id = R.drawable.icon_logout),
                           contentDescription = ""
                       )
                       Text(
                           text = "Logout",
                           style = MaterialTheme.typography.bodySmall
                       )
                   }
               }
//               End Logout
           ) {
               Column(
                   modifier = Modifier
                       .padding(it)
               ) {
                   //        Divider
                   Box(
                       modifier = Modifier
                           .fillMaxWidth()
                           .height(1.dp)
                           .background(MaterialTheme.colorScheme.surfaceTint.copy(0.3f))
                   )
//        End Divider
                   Box(
                       modifier = Modifier
                           .fillMaxSize()
                   ){
//        Account
                       Box(modifier = Modifier
                           .fillMaxWidth()
                           .let {
                               if (open) it.fillMaxHeight()
                               else it.height(0.dp)
                           }
                           .zIndex(100f)
                       ){
                           Column(
                               modifier = Modifier
                                   .fillMaxSize()
                                   .background(MaterialTheme.colorScheme.surface)
                           ) {
                               Row(
                                   modifier = Modifier
                                       .clickable { }
                                       .fillMaxWidth()
                                       .height(60.dp)
                                       .padding(horizontal = 20.dp),
                                   verticalAlignment = Alignment.CenterVertically,
                                   horizontalArrangement = Arrangement.spacedBy(10.dp)
                               ) {
                                   Box(
                                       modifier = Modifier
                                           .size(32.dp)
                                           .background(
                                               MaterialTheme.colorScheme.surfaceTint.copy(0.6f),
                                               RoundedCornerShape(99.dp)
                                           )
                                   )
                                   Text(
                                       text = "Hoan Le",
                                       style = MaterialTheme.typography.bodyMedium
                                   )
                               }
                               Row(
                                   modifier = Modifier
                                       .clickable { }
                                       .fillMaxWidth()
                                       .height(40.dp)
                                       .padding(horizontal = 20.dp),
                                   verticalAlignment = Alignment.CenterVertically,
                                   horizontalArrangement = Arrangement.spacedBy(10.dp)
                               ) {
                                   Icon(
                                       modifier = Modifier
                                           .size(18.dp),
                                       painter = painterResource(id = R.drawable.icon_add_outlined),
                                       contentDescription = ""
                                   )
                                   Text(
                                       text = "Add account",
                                       style = MaterialTheme.typography.bodySmall
                                   )
                               }
                           }
                       }
//        End Account


//        Navigation Items
                       Column(
                           modifier = Modifier
                               .fillMaxSize()
                               .background(MaterialTheme.colorScheme.surface)
                       ) {
                           ListNavItem().getAll().forEach {
                               Row(
                                   modifier = Modifier
                                       .clickable {
                                           navController.navigate(it.description.replace(" ","-"))
                                           scope.launch { drawerState.close() }
                                       }
                                       .fillMaxWidth()
                                       .height(60.dp)
                                       .padding(horizontal = 20.dp),
                                   verticalAlignment = Alignment.CenterVertically,
                                   horizontalArrangement = Arrangement.spacedBy(10.dp),
                               ) {
                                   Icon(
                                       modifier = Modifier.size(24.dp),
                                       painter = painterResource(
                                           if(navCurrentBackEntry?.destination?.route.toString()==it.description.replace(" ","-"))
                                               it.icon
                                           else it.iconOutline
                                       ),
                                       contentDescription = "",
                                       tint = if(navCurrentBackEntry?.destination?.route.toString()==it.description.replace(" ","-"))
                                                   MaterialTheme.colorScheme.onSurface
                                               else MaterialTheme.colorScheme.outline.copy(0.8f)
                                   )
                                   Text(
                                       text = it.description,
                                       style = if(navCurrentBackEntry?.destination?.route.toString()==it.description.replace(" ","-"))
                                                   MaterialTheme.typography.titleMedium
                                               else MaterialTheme.typography.bodyMedium,
                                       color = if(navCurrentBackEntry?.destination?.route.toString()==it.description.replace(" ","-"))
                                                   MaterialTheme.colorScheme.onSurface
                                               else MaterialTheme.colorScheme.outline.copy(0.8f)
                                   )
                               }
                           }
                       }
//            End Navigation Item
                   }

               }
           }

    }
}