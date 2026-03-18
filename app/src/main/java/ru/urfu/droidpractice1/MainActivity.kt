package ru.urfu.droidpractice1

import android.content.Intent
import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Lifecycle", "MainActivity onCreate")
        setContent {
            ArticleScreen()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "MainActivity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", "MainActivity onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle", "MainActivity onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "MainActivity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "MainActivity onDestroy")
    }
}

@Composable
fun ArticleScreen() {
    var likes by rememberSaveable { mutableIntStateOf(0) }
    var dislikes by rememberSaveable { mutableIntStateOf(0) }
    var isSecondRead by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val isRead = result.data?.getBooleanExtra("is_read", false) ?: false
            isSecondRead = isRead
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        Text(
            text = "У Леброна — две блестящие юбилейные отметки. И обе будто недостижимые!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(context)
                .data("https://img.championat.com/s/1350x900/news/big/w/i/u-lebrona-dve-blestyaschie-yubilejnye-otmetki_1771824238127542852.jpg")
                .build()
        )
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "«Лейкерс» это не помогло — поражение от «Бостона». " +
                    "Потерпели «Лейкерс» сокрушительное поражение не из-за единственного неудачного момента, а потому что «Бостон» весь матч был сильнее — и физически, и тактически, и в плане дисциплины. Испытание выдержать не удалось — 89:111. Только Леброн Джеймс заставил говорить о себе хорошо (со статистической точки зрения), но к этому мы подойдём чуть позже. " +
                    "В начале встречи команды шли ровно. Леброн быстро набрал четыре очка, «Селтикс» сначала мазали, однако вскоре восстановили порядок. Затем Бэйлор Шейерман забросил «трёху», и атакой завладели Джейлен Браун с Пэйтоном Притчардом. У «Лейкерс» в атаке выделялся Лука Дончич, иногда помогал Остин Ривз, но «Бостон» постепенно перекрывал им все зоны и крепко защищался. " +
                    "К большому перерыву хозяева уже уступали 10 очков (50:60): «Селтикс» безупречно держали оборону и не позволяли «Лейкерс» как следует разыграться, заставив их семь раз ошибиться. После травмы Джексона Хэйса у «Лейкерс» оказалось ещё меньше шансов — его отсутствие сразу сказалось и под щитом, и в защите. А под занавес первой половины Притчард реализовал трёхочковый.",
            fontSize = 16.sp,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = { likes++ }) {
                Text("👍 $likes")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { dislikes++ }) {
                Text("👎 $dislikes")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, "Прочитал классную статью про баскетбол!")
                }
                context.startActivity(shareIntent)
            }
        ) {
            Text("Поделиться")
        }
        Spacer(modifier = Modifier.height(16.dp))

        if (isSecondRead) {
            Text(
                text = "Вторая статья прочитана",
                color = Color.Green,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
        Button(
            onClick = {
                val intent = Intent(context, SecondActivity::class.java)
                intent.putExtra("was_read", isSecondRead)
                launcher.launch(intent)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (isSecondRead) "Открыть статью (уже прочитано)" else "Читать другую статью")
        }
    }
}