package com.artspb.myapplication

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Здесь мы настраиваем UI. Я стараюсь следовать принципу DRY (Don't Repeat Yourself), 
        // поэтому вынес настройку каждой плитки в отдельный метод. 
        // В реальном проекте я бы использовал ViewBinding вместо findViewById, 
        // но здесь я использую классический подход для закрепления понимания того, как работает UI.
        
        setupTile(
            bgId = R.id.bg_tile_light,
            iconId = R.id.icon_light,
            statusId = R.id.status_light,
            activeColorRes = R.color.icon_active,
            inactiveColorRes = R.color.icon_inactive,
            activeTextRes = R.color.text_tile_active,
            inactiveTextRes = R.color.text_tile_inactive
        )
        
        setupTile(
            bgId = R.id.bg_tile_camera,
            iconId = R.id.icon_camera,
            statusId = R.id.status_camera,
            activeColorRes = R.color.icon_active,
            inactiveColorRes = R.color.icon_inactive,
            activeTextRes = R.color.text_tile_active,
            inactiveTextRes = R.color.text_tile_inactive
        )
        
        setupTile(
            bgId = R.id.bg_tile_humidity,
            iconId = R.id.icon_humidity,
            statusId = R.id.status_humidity,
            activeColorRes = R.color.icon_active,
            inactiveColorRes = R.color.icon_inactive,
            activeTextRes = R.color.text_tile_active,
            inactiveTextRes = R.color.text_tile_inactive
        )
        
        setupTile(
            bgId = R.id.bg_tile_socket,
            iconId = R.id.icon_socket,
            statusId = R.id.status_socket,
            activeColorRes = R.color.icon_active,
            inactiveColorRes = R.color.icon_inactive,
            activeTextRes = R.color.text_tile_active,
            inactiveTextRes = R.color.text_tile_inactive
        )

        // Простая логика для ползунка температуры.
        // Я обновляю текст рядом с ползунком при сдвиге ползунка.
        val tempValue = findViewById<TextView>(R.id.text_temp_value)
        val tempSeekBar = findViewById<SeekBar>(R.id.seekbar_temp)
        
        tempSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tempValue.text = "${progress}°C"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    /**
     * Этот метод настраивает обработку кликов.
     * При клике на фон плитки (view) мы меняем его состояние isSelected. 
     * Благодаря StateListDrawable (bg_tile_selector.xml), фон автоматически изменит цвет.
     * Но для иконки и текста я меняю цвета программно, чтобы показать работу с ContextCompat.
     */
    private fun setupTile(
        bgId: Int,
        iconId: Int,
        statusId: Int,
        activeColorRes: Int,
        inactiveColorRes: Int,
        activeTextRes: Int,
        inactiveTextRes: Int
    ) {
        val bgView = findViewById<View>(bgId)
        val iconView = findViewById<ImageView>(iconId)
        val statusView = findViewById<TextView>(statusId)
        
        val activeIconColor = ContextCompat.getColor(this, activeColorRes)
        val inactiveIconColor = ContextCompat.getColor(this, inactiveColorRes)
        val activeTextColor = ContextCompat.getColor(this, activeTextRes)
        val inactiveTextColor = ContextCompat.getColor(this, inactiveTextRes)

        bgView.setOnClickListener {
            // Переключаем состояние плитки
            val isNowActive = !bgView.isSelected
            bgView.isSelected = isNowActive

            if (isNowActive) {
                iconView.setColorFilter(activeIconColor)
                statusView.text = "On"
                statusView.setTextColor(activeTextColor)
            } else {
                iconView.setColorFilter(inactiveIconColor)
                statusView.text = "Off"
                statusView.setTextColor(inactiveTextColor)
            }
        }
    }
}