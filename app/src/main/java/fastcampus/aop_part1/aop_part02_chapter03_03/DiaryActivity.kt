package fastcampus.aop_part1.aop_part02_chapter03_03

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener

class DiaryActivity : AppCompatActivity() {

    private val handler = Handler(Looper.getMainLooper()) //MainThread핸들러 하나가 만들어진거다.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary)

        var diaryEditText = findViewById<EditText>(R.id.diaryEditText)
        val detailPreferences = getSharedPreferences("diary",Context.MODE_PRIVATE)

        diaryEditText.setText(detailPreferences.getString("detail",""))

        val runnable = Runnable {
            getSharedPreferences("diary", Context.MODE_PRIVATE).edit {
                putString("detail",diaryEditText.text.toString())
            }

            Log.d("DiaryActivity", "SAVE!!!!!! ${diaryEditText.text.toString()}")
        }

        diaryEditText.addTextChangedListener {

            Log.d("DiaryActivity", "TextChanged :: $it")
            handler.removeCallbacks(runnable)
            handler.postDelayed(runnable,500)
        }
    }

}