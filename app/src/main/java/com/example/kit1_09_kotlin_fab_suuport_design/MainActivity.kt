package com.example.kit1_09_kotlin_fab_suuport_design

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Устанавливаем разметку и меню из шаблона
        setContentView(R.layout.activity_main)
        // стандартный текст шаблона с верхним меню
        setSupportActionBar(toolbar)

        // Объявляем слушателя для кнопки ДА: нужен ниже для setActoin это обратный вызов
        val snackbarOnClickListener =
            { v: View -> Toast.makeText(applicationContext, "Молодец!", Toast.LENGTH_SHORT).show() }

        // Сооружаем нижний  Snackbar без вызова: вызов будет по нажатию на кнопку ниже: show()

        // Объявляем слушателя для кнопки с лапой который показывает Snack
        fab.setOnClickListener { view ->
            var mSnackbar: Snackbar = make(view, "Тяпнул лапой БАНДИТ", LENGTH_INDEFINITE) //  SnackBar.make
                //  .setAction("Action", null) //Климов - Устарело можно не указывать
                .setDuration(8000)  // 8 секунд необяз
                .setAction("Да", snackbarOnClickListener)  // обратный вызов необяз
                .setActionTextColor(Color.WHITE) // цвет текста у кнопки действия доп

            // Меняем ему цвет и цвет буковок
            val snackbarView = mSnackbar.view
            snackbarView.setBackgroundColor(Color.BLUE)
            val snackTextView = snackbarView.findViewById<View>(R.id.snackbar_text) as TextView
            snackTextView.setTextColor(Color.RED)

            // и наконец показ нижнего Snackbar
            mSnackbar.show()

            hello.text = "SnackBar BANDIT  Тяпнул лапой"

            // Ставим слушателя на snackbar просто так для примера:
            mSnackbar.addCallback(object : Snackbar.Callback() {
                override fun onDismissed(snackbar: Snackbar?, event: Int) {
                    when (event) {
                        Callback.DISMISS_EVENT_TIMEOUT -> Toast.makeText(applicationContext, "Закрыт по истечении таймаута DO", Toast.LENGTH_SHORT).show()
                        Callback.DISMISS_EVENT_SWIPE -> Toast.makeText(applicationContext, "Смахнули DO", Toast.LENGTH_SHORT).show()
                        else -> Toast.makeText(applicationContext, "другой Dimiss DO", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onShown(snackbar: Snackbar?) {
                    Toast.makeText(applicationContext, "Показ DO", Toast.LENGTH_SHORT).show()
                }
            })

            buttonDismiss.setOnClickListener { view ->
                // кнопка убирает SnackBar
                mSnackbar.dismiss()
                hello.text = "SnackBar BANDIT    Dimiss DO"
                Toast.makeText(applicationContext, "Dimiss DO", Toast.LENGTH_SHORT).show()
            }
        }
    }    // Конец OnCreate

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        // Накачайте меню; это добавляет элементы в панели действий, если он присутствует.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        // Элемент панели действий ручки щелкает здесь. Панель действий
        // автоматически обрабатывать щелчки по кнопке Home / Up, так долго
        // как указать родительское действие в AndroidManifest.XML.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}