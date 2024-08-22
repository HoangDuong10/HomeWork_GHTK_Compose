package com.example.homework_ghtk_compose

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider

import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.homework_ghtk_compose.ui.theme.HomeWork_GHTK_ComposeTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeWork_GHTK_ComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HomeWork_GHTK_ComposeTheme {
        MainScreen()
    }
}

@Composable
fun Header(
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically // Căn giữa các phần tử con theo chiều dọc
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = null,
            tint = colorResource(id = R.color.green),
            modifier = modifier.size(28.dp)
        )

        Box(
            modifier = Modifier
                .focusModifier()
                .size(80.dp)
                .background(
                    colorResource(id = R.color.green), shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "55",
                fontSize = 18.sp,
                color = colorResource(id = R.color.white)
            )
        }

        Column(modifier = modifier.padding(start = 4.dp)) {
            Row(modifier = modifier.fillMaxWidth()) {
                Text(
                    text = "843***8425",
                    fontSize = 18.sp,
                    modifier = modifier.weight(1f)
                )
                Row(
                    modifier = modifier
                        .background(color = colorResource(id = R.color.green))
                        .padding(start = 8.dp, end = 8.dp),
                    horizontalArrangement = Arrangement.End // Đẩy nội dung sang bên phải
                ) {
                    Icon(
                        imageVector = Icons.Filled.FavoriteBorder,
                        contentDescription = null,
                        tint = colorResource(id = R.color.white)
                    )
                    Text(
                        text = "Theo dõi",
                        fontSize = 18.sp,
                        color = colorResource(id = R.color.white)
                    )
                }
            }
            Row(modifier = modifier.padding(top = 4.dp)) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = colorResource(id = R.color.green),
                    modifier = modifier.size(28.dp)
                )
                Text(
                    text = "Đã xác thực SĐT & Địa chỉ",
                    fontSize = 18.sp
                )
            }
        }

    }

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ListTag(listTag: List<Tag>) {
    FlowRow(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        listTag.forEach { tag ->
            ItemTag(tag = tag)
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white))
            .padding(start = 8.dp, top = 8.dp, end = 8.dp)
    ) {
        Header(modifier = modifier)
        ListTag(listTag = getListTag())
        Body()
        TabLayout()
    }
}

fun getListTag(): List<Tag> =
    listOf(
        Tag("Đã mua", R.color.white),
        Tag("Thiết bị điện tử", R.color.teal_700),
        Tag("Voucher", R.color.purple_200),
        Tag("Thiết bị điện gia dụng", R.color.purple_500),
        Tag("Mẹ và bé", R.color.teal_200),
        Tag("Nhà cửa", R.color.yellow)
    )


@Composable
fun ItemTag(tag: Tag) {
    Text(
        text = tag.tagName,
        fontWeight = FontWeight.W500,
        fontSize = 18.sp,
        modifier =
        Modifier
            .background(colorResource(id = tag.color))
            .padding(2.dp),
    )
}

@Composable
fun Body() {
    Column(modifier = Modifier.fillMaxWidth().padding(start = 8.dp)) {
        HorizontalDivider(
            modifier = Modifier
                .padding(top = 4.dp)
                .background(colorResource(id = R.color.grey))
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .height(IntrinsicSize.Max),

        ) {
            Info(value = "--", title = "Độ cháy túi",modifier = Modifier.weight(1f), isDivider = true)
            Info(true, value = "80", title = "ĐH đã đặt", modifier = Modifier.weight(1f), isDivider = true)
            Info(value = "--", title = "Thành công",modifier = Modifier.weight(1f), isDivider = true)
            Info(true, value = "Tên  lửa", title = "Tốc độ nhận", true,modifier = Modifier.weight(1f), isDivider = false)
        }
        HorizontalDivider(
            modifier = Modifier
                .padding(top = 4.dp)
                .background(colorResource(id = R.color.grey))
        )
        Row( modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
            horizontalArrangement = Arrangement.SpaceAround) {
            Info(true,value = "", title = "Đánh giá",false,true,modifier = Modifier.weight(1f), isDivider = true)
            Info(true,value = "10 Shop", title = "Đã ghé thăm",modifier = Modifier.weight(1f), isDivider = true)
            Info(true,value = "11 Shop", title = "Đã mua",modifier = Modifier.weight(1f), isDivider = true)
            Info(value = "--", title = "Chu kì mua",modifier = Modifier.weight(1f), isDivider = false)
        }
        Box(
            modifier = Modifier
                .height(20.dp)
                .fillMaxWidth()
                .padding(top = 8.dp)
                .background(colorResource(id = R.color.grey))
        )
    }

}


@Composable
fun Info(
    isValue: Boolean = false,
    value: String,
    title: String,
    isRocket: Boolean = false,
    isFeeling: Boolean = false,
    modifier: Modifier = Modifier,
    isDivider : Boolean =false
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterStart)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (isRocket) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_rocket),
                            contentDescription = null,
                            tint = colorResource(id = R.color.green),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    if (isFeeling) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "38",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            Image(
                                painter = painterResource(id = R.drawable.ic_face_heart),
                                contentDescription = null
                            )
                            Text(
                                text = "20",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            Image(
                                painter = painterResource(id = R.drawable.ic_face_disappointed),
                                contentDescription = null
                            )
                        }
                    } else {
                        Text(
                            text = value,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isValue) Color.Black else Color.Gray
                        )
                    }
                }
                Text(
                    text = title,
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
        }
       if(isDivider){
           VerticalDivider(
               modifier = Modifier
                   .padding(end = 10.dp, top = 16.dp, bottom = 16.dp)
                   .width(1.dp)
                   .align(Alignment.CenterEnd)
                   .background(color = Color.Gray)

           )
       }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
@OptIn(ExperimentalFoundationApi::class)
fun TabLayout(){
    val tabItems = listOf("Hoạt động","Nhận hàng","2lanstore")
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState {
        tabItems.size
    }

    LaunchedEffect(selectedTabIndex){
        pagerState.animateScrollToPage(selectedTabIndex)
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            edgePadding = 0.dp,
            divider = {
                
            },
            indicator = { tabPositions ->
                Box(modifier = Modifier
                    .tabIndicatorOffset(tabPositions[selectedTabIndex])
                    .height(2.dp)
                    .padding(horizontal = 16.dp)
                    .background(color = colorResource(id = R.color.green)),
                )
            },
        ) {
            tabItems.forEachIndexed { index, tabItem ->
                val select = pagerState.currentPage == index
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = {

                            Text(
                                text = tabItem,
                                color = if (select) colorResource(id = R.color.green) else Color.Black,
                                fontSize = 16.sp
                            )

                    },
                )
            }
        }

        HorizontalDivider(
            modifier = Modifier
                .background(colorResource(id = R.color.grey))
        )
        HorizontalPager(state = pagerState, userScrollEnabled = false) {page ->
            when(page){
                0-> ContentLayout()
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ContentLayout(){
    Row( modifier = Modifier.fillMaxSize()) {
        OrderList(modifier = Modifier.weight(1f))
        ColumnAction(modifier = Modifier.weight(1f))
    }

}
@Composable
fun ColumnAction(modifier: Modifier = Modifier){
    Column {
        Action(icon = Icons.Default.Phone,
            title = "Gọi điện", count = 3)
        Action(icon = Icons.Default.Chat,
            title = "Chat", count = 0)
        Action(icon = Icons.Default.Add,
            title = "Tạo ĐH", count = 0)
        Action(icon = Icons.Default.Star,
            title = "Đánh giá", count = 0)
    }
}
@Composable
fun Action(icon:ImageVector, title:String, count:Int){
    Column(modifier = Modifier
        .padding(bottom = 2.dp)
        .height(60.dp)
        .width(90.dp)
        .background(color = colorResource(id = R.color.green)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = colorResource(id = R.color.white),
            )
            if(count>0){
                Text(text = "(${count})",
                    fontSize = 14.sp, color = Color.White)
            }
        }
        Text(text = title,
            fontSize = 14.sp, color = Color.White)


    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ItemOrder(modifier: Modifier = Modifier){
    ConstraintLayout(modifier = modifier.fillMaxWidth()) {
        val (tvTime, tvValue,divider) = createRefs()
        val text = buildAnnotatedString {
            append("YEAH!")
            withStyle(style = SpanStyle(fontWeight = FontWeight.W500)) {
                append(" SuSu")
            }
            append(" đã đặt hàng sản phẩm ")
            withStyle(style = SpanStyle(fontWeight = FontWeight.W500)) {
                append("giá trị cao")
            }
        }
        Text(text = text,
            modifier =
            Modifier
                .padding(top = 4.dp, end = 4.dp)
                .constrainAs(tvValue) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(tvTime.start)
                    width = Dimension.fillToConstraints
                })
        Text(
            text = getRandomFormattedDate(),
            fontSize = 12.sp,
            color = Color.Gray,
            modifier =
            Modifier
                .padding(top = 4.dp, end = 8.dp)
                .constrainAs(tvTime) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                },
        )
        HorizontalDivider(
            modifier = Modifier
                .constrainAs(divider){
                    top.linkTo(tvValue.bottom)
                    start.linkTo(parent.start)
                }
                .background(colorResource(id = R.color.grey)))
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun OrderList(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.fillMaxWidth().padding(start = 10.dp)) {
        items(20) {
            ItemOrder(modifier)
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
fun getRandomFormattedDate(): String {
    val startDate = LocalDate.of(2020, 1, 1)
    val endDate = LocalDate.of(2024, 8, 30)
    val daysBetween = ChronoUnit.DAYS.between(startDate, endDate)
    val randomDays = Random.nextLong(daysBetween + 1) // +1 để bao gồm endDate
    val randomDate = startDate.plusDays(randomDays)
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return randomDate.format(formatter)
}




data class Tag(
    val tagName: String,
    val color: Int
)