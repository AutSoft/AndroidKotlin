package hu.aut.android.layoutinflatedemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.note_row.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun saveClick(view: View) {
        val noteRow = layoutInflater.inflate(R.layout.note_row, null)

        noteRow.tvNote.text = etNote.text.toString()

        if (radioLow.isChecked) {
            noteRow.ivIcon.setImageResource(R.drawable.low)
        } else if (radioMedium.isChecked) {
            noteRow.ivIcon.setImageResource(R.drawable.medium)
        } else if (radioHigh.isChecked) {
            noteRow.ivIcon.setImageResource(R.drawable.high)
        }

        noteRow.btnDel.setOnClickListener{
            layoutContent.removeView(noteRow)
        }

        etNote.setText("")

        layoutContent.addView(noteRow, 0)
    }

}
