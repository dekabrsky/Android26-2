package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {

    //Ключевые переменные
    private var SecondRead by mutableStateOf(false) // статус прочтения 2 статьи
    private var CountLike by mutableStateOf(0) // счетчик для лайков
    private var CountDislike by mutableStateOf(0) // счетчик для дизлайков

    private val getSecondResult = registerForActivityResult( // приватная переменная, сохраняет объект управляющий запуском и обработкой результата Activity
        ActivityResultContracts.StartActivityForResult() // запуск Activity и получение результата
    ) { result -> // вызывается при завершении Activity
        if (result.resultCode == RESULT_OK) { // проверяем что Activity завершилась успешно
            SecondRead = result.data?.getBooleanExtra("isRead", false) ?: false // извлекаем флаг, если флага нет -> false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Востанавливаем данные после поворота экрана
        if (savedInstanceState != null) { // проверяем есть ли сохраненное состояние
            SecondRead = savedInstanceState.getBoolean("second_read", false) // получаем была ли прочитана 2 статья
            CountLike = savedInstanceState.getInt("count_like", 0) // получаем кол-во лайков
            CountDislike = savedInstanceState.getInt("count_dislike", 0) // получаем кол-во дизлайков
        }

        setContent {
            MainActivityScreen(
                SecondRead = SecondRead,
                CountLike = CountLike,
                CountDislike = CountDislike,
                LikeClick = {CountLike++},// обработчик нажатия на кнопку лайк
                DislikeClick = {CountDislike++}, // обработчик нажатия на кнопку дизлайк
                ShareClick = {text -> shareClick(text)}, // обработчик нажатия на кнопку поделиться
                SecondClick = { // обработчик нажатия на кнопку вторая статья
                    val intent = Intent(this, SecondActivity::class.java)
                    getSecondResult.launch(intent)
                }
            )
        }
    }

    // сохранение состояния
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState) // система сохраняет данные
        outState.putBoolean("second_read", SecondRead) // сохраняем текущие статус статьи 2
        outState.putInt("count_like", CountLike) // сохраняем текущие значение лайков
        outState.putInt("count_dislike", CountDislike) // сохраняем текущие значение дизлайков
    }

    // функция поделиться
    private fun shareClick(text: String) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND // указываем действие -> отправка данных
            putExtra(Intent.EXTRA_TEXT, text) // указываем текс, который должен быть передан
            type = "text/plain" // определяем тип передаваемых данных -> обычный текст
        }
        startActivity(Intent.createChooser(shareIntent, "Поделиться статьей"))
    }
}