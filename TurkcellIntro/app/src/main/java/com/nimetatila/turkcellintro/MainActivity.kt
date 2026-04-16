package com.nimetatila.turkcellintro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.WhitePoint
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nimetatila.turkcellintro.ui.theme.Pink40
import com.nimetatila.turkcellintro.ui.theme.Purple40
import com.nimetatila.turkcellintro.ui.theme.PurpleGrey40
import com.nimetatila.turkcellintro.ui.theme.TurkcellIntroTheme

//Class => :ComponentActivitty() -> Kalıtım almak
//Kendi oluşturduğumuz MainActivity classına Component olma çzelliğini ekledik.

//mainActivity: subclass
//componentActivity: superclass
class MainActivity : ComponentActivity() {

    //override => kalıtım aldığımız classdaki bir methodu değiştirmek/geliştirmek
    override fun onCreate(savedInstanceState: Bundle?) {
        // super => kalıtım aldığım class
        // bu method normalde çalışıtığı gibi çalışmaya devam etsin
        super.onCreate(savedInstanceState)

        var name: String = "Carlos"
        //kendi istediğim (bu sayfaya özel) kodlar çalışsın
        //ekranın UI burada yazılır
        enableEdgeToEdge()
        setContent {
         Scaffold(){
             paddingValues -> MyAppStart(Modifier.padding(paddingValues))
         }
        }
    }
}

//bir fonksiyon eğer UI fonksiyonu ise => @Composable olması zorunludur.
@Composable
fun MyAppStart(modifier: Modifier){
    //Normal tanımlamam => Ekranda değişiklik görülmez
    //var count: Int = 0

    val todos = listOf<String>(
        "Todo1",
        "Todo2",
        "Todo3",

    )
    Column(modifier = modifier) {
        //Sayac()
        //Test()
        Liste(todos)
    }
}

@Composable
fun Liste(todos: List<String>){
    LazyColumn(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(todos){
            todo -> ListeElemanı("yapılacak iş = $todo")
        }
    }
}

@Composable
fun ListeElemanı(todo: String){
Box(modifier = Modifier.fillMaxWidth().padding(15.dp).background(Color.Gray).border(1.dp, Color.Red)){
    Text(todo)
}
}
@Composable
fun Test(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Pink40))
    {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .background(Purple40),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text("merhaba")
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
                horizontalArrangement = Arrangement.SpaceEvenly) {
                Text("merhaba 4")
                Text("merhaba 5")
            }
            Text("merhaba 2")
            Text("merhaba 3")
        }
    }
}
@Composable
fun Sayac(){
    //Androidde ekranı etkileyecek her türlü değişken bu şekilde tanımlanır.
    var count = remember { mutableStateOf(0) }
    //Android değişkeni..
    Text("Sayı ${count.value}")
    Button (onClick = {
        count.value++
    }) {
        Text("Tıkla")
    }
}

//Recomposition => Ekranın ilgili kısmının tekrar çalıştırılmasına denir