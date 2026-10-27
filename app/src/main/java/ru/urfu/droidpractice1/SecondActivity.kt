package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding
import android.util.Log

private const val TAG = "SecondActivity"
class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("switch_enabled", binding.switchRead.isChecked)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.AchichiMelaText.movementMethod = LinkMovementMethod.getInstance()
        binding.SorashinaSopiaText.movementMethod = LinkMovementMethod.getInstance()
        binding.SuzunaTsuzuriText.movementMethod = LinkMovementMethod.getInstance()
        binding.HyakutoKyokoText.movementMethod = LinkMovementMethod.getInstance()

        Glide.with(this)
            .load("https://hololive.hololivepro.com/wp-content/uploads/2023/09/Achichi-Mela_pr-img_01.webp")
            .into(binding.AchichiMelaImage)

        Glide.with(this)
            .load("https://hololive.hololivepro.com/wp-content/uploads/2023/09/Sorashina-Sopia_pr-img_01.webp")
            .into(binding.SorashinaSopiaImage)

        Glide.with(this)
            .load("https://hololive.hololivepro.com/wp-content/uploads/2023/09/Suzuna-Tsuzuri_pr-img_01.webp")
            .into(binding.SuzunaTsuzuriImage)

        Glide.with(this)
            .load("https://hololive.hololivepro.com/wp-content/uploads/2023/09/Hyakuto-Kyoko_pr-img_01.webp")
            .into(binding.HyakutoKyokoImage)

        binding.switchRead.isChecked = savedInstanceState?.getBoolean("switch_enabled", false) ?: false

        binding.switchRead.setOnCheckedChangeListener { _, isChecked ->
            val resultIntent = Intent().apply {
                putExtra("switch_result", isChecked)
            }
            setResult(RESULT_OK, resultIntent)
        }

        val shareText = buildString {
            append(getString(R.string.article_title))
            append("\n\n")
            append(getString(R.string.introduction))
            append("\n\n")
            append(getString(R.string.Achichi_Mela))
            append("\n\n")
            append(getString(R.string.Sorashina_Sopia))
            append("\n\n")
            append(getString(R.string.Suzuna_Tsuzuri))
            append("\n\n")
            append(getString(R.string.Hyakuto_Kyoko))
        }

        binding.buttonShare.setOnClickListener {
            val sendIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, shareText)
            }

            startActivity(Intent.createChooser(sendIntent, null))
        }

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }
}