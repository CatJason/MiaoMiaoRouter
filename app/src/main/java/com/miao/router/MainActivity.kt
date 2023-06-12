package com.miao.router

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var btnClick: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 获取按钮控件
        btnClick = findViewById<Button?>(R.id.btn_click).apply {
            setOnClickListener { // 点击按钮时执行的逻辑
                Router.init(this@MainActivity)
                Router.navigate(this@MainActivity, "com.example.app.SecondActivity")
            }
        }
    }
}