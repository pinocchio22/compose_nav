package com.example.compose_nav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose_nav.ui.theme.Compose_navTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_navTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavigationGraph()
                }
            }
        }
    }
}
// navigation route enum(값을 가지는 enum)
enum class NAV_ROUTE(val routeName: String, val description: String, val btnColor: Color) {
    MAIN("MAIN", "메인 화면", Color(0xFFE91E63)),
    LOGIN("LOGIN", "로그인 화면", Color(0xFF673AB7)),
    REGISTER("REGISTER", "가입 화면", Color(0xFFCDDC39)),
    USER_PROFILE("USER_PROFILE", "프로필 화면", Color(0xFF4CAF50)),
    SETTING("SETTING", "설정 화면", Color(0xFF3F51B5))
}

// navigation route action
class RouteAction(navHostController: NavHostController) {
    // 특정 라우트로 이동
    val navTo: (NAV_ROUTE) -> Unit = { route ->
        navHostController.navigate(route.routeName)
    }

    // 뒤로가기
    val goBack: () -> Unit = {
        navHostController.navigateUp()
    }
}

@Composable
fun NavigationGraph(starting: String = NAV_ROUTE.MAIN.routeName) {
    // navigation controller
    val navController = rememberNavController()

    // navigation route action
    val routeAction = remember(navController) { RouteAction(navController) }

    // NavHost로 navigation 결정
    NavHost(navController,starting) {
        composable(NAV_ROUTE.MAIN.routeName) {
            // 메인 화면
            Main(routeAction = routeAction)
        }
        composable(NAV_ROUTE.LOGIN.routeName) {
            // 로그인 화면
            Login(routeAction = routeAction)
        }
        composable(NAV_ROUTE.REGISTER.routeName) {
            // 가입 화면
            Register(routeAction = routeAction)
        }
        composable(NAV_ROUTE.USER_PROFILE.routeName) {
            // 프로필 화면
            UserProfile(routeAction = routeAction)
        }
        composable(NAV_ROUTE.SETTING.routeName) {
            // 설정 화면
            Setting(routeAction = routeAction)
        }
    }
}

// 메인
@Composable
fun Main(routeAction: RouteAction) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            NavButton(route = NAV_ROUTE.LOGIN, routeAction = routeAction)
            NavButton(route = NAV_ROUTE.REGISTER, routeAction = routeAction)
            NavButton(route = NAV_ROUTE.USER_PROFILE, routeAction = routeAction)
            NavButton(route = NAV_ROUTE.SETTING, routeAction = routeAction)
        }
    }
}

// 로그인
@Composable
fun Login(routeAction: RouteAction) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.padding(8.dp), Alignment.Center) {
            Text(text = "로그인 화면", style = TextStyle(Color.Black, 22.sp))
            // 뒤로가기
            Button(onClick = routeAction.goBack,
            modifier = Modifier
                .padding(16.dp)
                .offset(y = 100.dp)) {
                Text(text = "go back")
            }
        }
    }
}

// 가입
@Composable
fun Register(routeAction: RouteAction) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.padding(8.dp), Alignment.Center) {
            Text(text = "가입 화면", style = TextStyle(Color.Black, 22.sp))
            // 뒤로가기
            Button(onClick = routeAction.goBack,
                modifier = Modifier
                    .padding(16.dp)
                    .offset(y = 100.dp)) {
                Text(text = "go back")
            }
        }
    }
}

// 로그인
@Composable
fun UserProfile(routeAction: RouteAction) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.padding(8.dp), Alignment.Center) {
            Text(text = "프로필 화면", style = TextStyle(Color.Black, 22.sp))
            // 뒤로가기
            Button(onClick = routeAction.goBack,
                modifier = Modifier
                    .padding(16.dp)
                    .offset(y = 100.dp)) {
                Text(text = "go back")
            }
        }
    }
}

// 로그인
@Composable
fun Setting(routeAction: RouteAction) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.padding(8.dp), Alignment.Center) {
            Text(text = "설정 화면", style = TextStyle(Color.Black, 22.sp))
            // 뒤로가기
            Button(onClick = routeAction.goBack,
                modifier = Modifier
                    .padding(16.dp)
                    .offset(y = 100.dp)) {
                Text(text = "go back")
            }
        }
    }
}

@Composable
// 컬럼에 있는 navigation 버튼
fun ColumnScope.NavButton(route: NAV_ROUTE, routeAction: RouteAction) {
    Button(onClick = {
        routeAction.navTo(route)
    }, colors = ButtonDefaults.buttonColors(route.btnColor),
        modifier = Modifier
            .weight(1f)
            .padding(8.dp)
            .fillMaxSize()
    ) {
        Text(text = "${route.description} 이동",
            style = TextStyle(Color.White, 22.sp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Compose_navTheme {
        NavigationGraph()
    }
}