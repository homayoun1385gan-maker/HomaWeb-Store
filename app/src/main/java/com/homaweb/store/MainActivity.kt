package com.homaweb.store

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.homaweb.store.ui.theme.MyApplicationTheme

private val HomaPurple = Color(0xFF7138E8)
private val HomaBackground = Color(0xFFF8F5FF)
private val HomaText = Color(0xFF241A35)
private val HomaGray = Color(0xFF77727F)

data class Product(
    val name: String,
    val description: String,
    val price: String
)

private val products = listOf(
    Product(
        "ربات اختصاصی",
        "طراحی و ساخت ربات حرفه‌ای برای کسب‌وکار",
        "تماس برای قیمت"
    ),
    Product(
        "تبلیغات حرفه‌ای",
        "معرفی کسب‌وکار و تبلیغات هدفمند",
        "تماس برای قیمت"
    ),
    Product(
        "طراحی سایت",
        "ساخت سایت حرفه‌ای و واکنش‌گرا",
        "تماس برای قیمت"
    ),
    Product(
        "طراحی اپلیکیشن",
        "ساخت اپلیکیشن اندروید اختصاصی",
        "تماس برای قیمت"
    )
)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = HomaBackground
                ) {
                    HomaWebStore()
                }
            }
        }
    }
}

@Composable
fun HomaWebStore() {

    var page by remember {
        mutableStateOf("خانه")
    }

    var search by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(HomaBackground)
    ) {

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {

            when (page) {

                "خانه" -> {
                    HomePage(
                        onProducts = {
                            page = "محصولات"
                        }
                    )
                }

                "محصولات" -> {
                    ProductsPage(
                        search = search,
                        onSearch = {
                            search = it
                        }
                    )
                }

                "سبد خرید" -> {
                    CartPage()
                }

                "حساب کاربری" -> {
                    AccountPage()
                }
            }
        }

        BottomBar(
            selected = page,
            onSelected = {
                page = it
            }
        )
    }
}

@Composable
fun HomePage(
    onProducts: () -> Unit
) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(18.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {

            Text(
                text = "HomaWeb Store",
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                color = HomaText
            )

            Spacer(
                modifier = Modifier.height(5.dp)
            )

            Text(
                text = "فروشگاه خدمات دیجیتال",
                fontSize = 16.sp,
                color = HomaGray
            )
        }

        item {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(
                    containerColor = HomaPurple
                )
            ) {

                Column(
                    modifier = Modifier.padding(24.dp)
                ) {

                    Text(
                        text = "همه چیز برای رشد کسب‌وکار شما",
                        color = Color.White,
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    Text(
                        text = "ربات، تبلیغات، سایت و اپلیکیشن در یک فروشگاه.",
                        color = Color.White,
                        fontSize = 14.sp
                    )

                    Spacer(
                        modifier = Modifier.height(18.dp)
                    )

                    Button(
                        onClick = onProducts,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = HomaPurple
                        ),
                        shape = RoundedCornerShape(15.dp)
                    ) {
                        Text("مشاهده خدمات")
                    }
                }
            }
        }

        item {

            Text(
                text = "خدمات پیشنهادی",
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                color = HomaText
            )
        }

        items(products) { product ->
            ProductCard(product)
        }
    }
}

@Composable
fun ProductsPage(
    search: String,
    onSearch: (String) -> Unit
) {

    val result = products.filter {
        it.name.contains(search, ignoreCase = true) ||
                it.description.contains(search, ignoreCase = true)
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(18.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {

        item {

            Text(
                text = "محصولات و خدمات",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = HomaText
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            OutlinedTextField(
                value = search,
                onValueChange = onSearch,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("جستجو")
                },
                placeholder = {
                    Text("مثلاً طراحی سایت")
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp)
            )
        }

        items(result) { product ->
            ProductCard(product)
        }

        if (result.isEmpty()) {

            item {

                Text(
                    text = "محصولی پیدا نشد.",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = HomaGray
                )
            }
        }
    }
}

@Composable
fun ProductCard(
    product: Product
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(58.dp)
                    .background(
                        HomaPurple.copy(alpha = 0.12f),
                        RoundedCornerShape(18.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "★",
                    color = HomaPurple,
                    fontSize = 26.sp
                )
            }

            Spacer(
                modifier = Modifier.width(14.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = product.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = HomaText
                )

                Spacer(
                    modifier = Modifier.height(5.dp)
                )

                Text(
                    text = product.description,
                    fontSize = 13.sp,
                    color = HomaGray
                )

                Spacer(
                    modifier = Modifier.height(7.dp)
                )

                Text(
                    text = product.price,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = HomaPurple
                )
            }
        }
    }
}

@Composable
fun CartPage() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "🛒",
            fontSize = 55.sp
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = "سبد خرید",
            fontSize = 27.sp,
            fontWeight = FontWeight.ExtraBold,
            color = HomaText
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "سبد خرید شما خالی است.",
            fontSize = 15.sp,
            color = HomaGray
        )
    }
}

@Composable
fun AccountPage() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box(
            modifier = Modifier
                .size(90.dp)
                .background(
                    HomaPurple.copy(alpha = 0.12f),
                    RoundedCornerShape(45.dp)
                ),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "👤",
                fontSize = 42.sp
            )
        }

        Spacer(
            modifier = Modifier.height(18.dp)
        )

        Text(
            text = "حساب کاربری",
            fontSize = 27.sp,
            fontWeight = FontWeight.ExtraBold,
            color = HomaText
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "برای ورود یا ساخت حساب کاربری اقدام کنید.",
            fontSize = 14.sp,
            color = HomaGray,
            textAlign = TextAlign.Center
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(
                containerColor = HomaPurple
            ),
            shape = RoundedCornerShape(15.dp)
        ) {

            Text(
                text = "ورود به حساب"
            )
        }
    }
}

@Composable
fun BottomBar(
    selected: String,
    onSelected: (String) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .navigationBarsPadding()
            .padding(
                horizontal = 8.dp,
                vertical = 10.dp
            ),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        BottomItem(
            title = "خانه",
            icon = "⌂",
            selected = selected == "خانه",
            onClick = {
                onSelected("خانه")
            }
        )

        BottomItem(
            title = "محصولات",
            icon = "▦",
            selected = selected == "محصولات",
            onClick = {
                onSelected("محصولات")
            }
        )

        BottomItem(
            title = "سبد خرید",
            icon = "🛒",
            selected = selected == "سبد خرید",
            onClick = {
                onSelected("سبد خرید")
            }
        )

        BottomItem(
            title = "حساب کاربری",
            icon = "●",
            selected = selected == "حساب کاربری",
            onClick = {
                onSelected("حساب کاربری")
            }
        )
    }
}

@Composable
fun BottomItem(
    title: String,
    icon: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(
                horizontal = 10.dp,
                vertical = 4.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = icon,
            fontSize = 22.sp,
            color = if (selected) {
                HomaPurple
            } else {
                HomaGray
            }
        )

        Spacer(
            modifier = Modifier.height(3.dp)
        )

        Text(
            text = title,
            fontSize = 11.sp,
            fontWeight = if (selected) {
                FontWeight.Bold
            } else {
                FontWeight.Normal
            },
            color = if (selected) {
                HomaPurple
            } else {
                HomaGray
            }
        )
    }
}
