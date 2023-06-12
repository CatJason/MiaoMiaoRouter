package com.miao.router
import android.app.Activity
import android.os.Bundle

@Route(path = "com.example.app.SecondActivity")
class RouterJumpActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.router_main) // 设置 Activity 的布局
    }
}