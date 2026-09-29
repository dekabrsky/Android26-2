package ru.urfu.droidpractice1.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.ui.theme.LikeRose
import ru.urfu.droidpractice1.ui.theme.SuccessEmerald

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainActivityScreen(
    likesCount: Int,
    dislikesCount: Int,
    isSecondArticleRead: Boolean,
    onLikeClick: () -> Unit,
    onDislikeClick: () -> Unit,
    onShareClick: () -> Unit,
    onNextArticleClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Финансовый Вестник", style = MaterialTheme.typography.titleMedium) },
                actions = {
                    IconButton(onClick = onShareClick) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Поделиться",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text(
                text = "Облигации «Самолета» рухнули почти на 40% из-за частичного погашения",
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Автор: Ольга Анохина • 3 мин чтения",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(16.dp))

            AsyncImage(
                model = "https://images.unsplash.com/photo-1590283603385-17ffb3a7f29f?w=800",
                contentDescription = "График биржи",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Облигации девелопера «Самолет» на торгах во вторник, 29 сентября, теряют в цене от 11,68 до 37,27%, следует из данных Московской биржи по состоянию на 17:10 мск. Падение происходит на фоне новостей о том, эмитент не смог полностью погасить выпуск биржевых облигаций. Речь идет о выпуске биржевых облигаций БО-П20 с датой погашения 28 сентября 2026 года и фиксированным купоном 21,25%.",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Согласно данным НРД, из ₽1 тыс., которые нужно было погасить, эмитент перечислил сумму в размере ₽269,104907468 на бумагу. «Если переводить в деньги, то из ₽2,5 млрд эмитент прислал порядка ₽670 млн», — подсчитали аналитики «Вектор Капитал».",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Уже после обвала котировок «Самолет» сообщил, что полностью погасил выпуск БО-П20 28 сентября, однако по техническим причинам на счет НРД часть денег была зачислена 29 сентября. «По установленной процедуре НРД зафиксирует реестр на следующий рабочий день после зачисления средств», — следует из сообщения компании. НРД подтвердил погашение облигаций «Самолета», раскрыв получение второго транша от эмитента ₽730,895092532 на бумагу.",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            Surface(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Всего в обращении у эмитента находится 15 выпусков биржевых облигаций общим объемом ₽104,9 млрд.",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "«Самолет» имеет действующие кредитные рейтинги:\n• A- (RU) от АКРА. Прогноз — стабильный.\n• A.ru от НКР. Прогноз — стабильный.",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Наибольшее падение наблюдается в выпуске СамолетP11 с датой оферты в феврале 2027 года. По состоянию на 17:07 мск одна бумага торгуется по цене ₽505, что на 37,27% ниже цены закрытия предыдущего дня. Доходность по последней сделке — 433,1%. На минимуме цена бумаги доходила до ₽486,4 (-39,58%).",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "На однодневных минимумах просадки в выпусках эмитента достигали (к уровню закрытия вчерашнего дня):",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
            )

            Spacer(modifier = Modifier.height(8.dp))

            val drops = listOf(
                "-29,6% в БО-П16", "-37,5% в БО-П15", "-38,4% в БО-П14",
                "-39,5% в БО-П13", "-39,6% в БО-П11", "-29,1% в БО-П06",
                "-26,2% в БО-П05", "-30,7% в 002P-03", "-36,9% в БО-П19",
                "-20,3% в БО-П21", "-39,2% в БО-П18"
            )

            Column(modifier = Modifier.padding(start = 8.dp)) {
                drops.forEach { drop ->
                    Text(
                        text = "•  $drop",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = LikeRose,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Ранее стало известно, что «Сбер» может получить в управление часть бизнеса компании при реструктуризации долгов девелопера, о чем со ссылкой на три источника на рынке недвижимости сообщил «Коммерсантъ». При этом еще в феврале «Самолет» обращался в правительство с просьбой о господдержке, в том числе обсуждался вариант о выделении льготного кредита на ₽50 млрд.",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Впоследствии глава «Самолета» Анна Акиньшина в интервью РБК рассказала, что девелопер получил официальный ответ правительства на запрос о предоставлении господдержки. По ее словам, межведомственная комиссия подтвердила устойчивость компании и решила, что группе не требуется прямая поддержка.",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(onClick = onLikeClick) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "Like",
                        tint = SuccessEmerald
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = "$likesCount", color = MaterialTheme.colorScheme.onSurface)
                }

                OutlinedButton(onClick = onDislikeClick) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Dislike",
                        tint = LikeRose
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = "$dislikesCount", color = MaterialTheme.colorScheme.onSurface)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (isSecondArticleRead) {
                Surface(
                    color = SuccessEmerald.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = SuccessEmerald
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Следующая статья уже прочитана вами",
                            style = MaterialTheme.typography.bodyMedium,
                            color = SuccessEmerald
                        )
                    }
                }
            }

            HorizontalDivider()
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onNextArticleClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(
                    text = if (isSecondArticleRead) "Перейти к статье (Прочитано)" else "Читать следующую статью"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
            }
        }
    }
}