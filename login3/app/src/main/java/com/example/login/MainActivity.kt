package com.example.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var login: Button? = null
    internal lateinit var username: EditText
    internal lateinit var password: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        login = findViewById<View>(R.id.login) as Button
        username = super.findViewById<View>(R.id.username) as EditText//获取用户输入的用户名
        password = super.findViewById<View>(R.id.password) as EditText//获取用户密码
        login!!.setOnClickListener {
            //验证用户名密码是否符合要求
            if (username.text.toString() == "dai" && password.text.toString() == "123456") {
                Toast.makeText(applicationContext, "登录成功", Toast.LENGTH_SHORT).show()//提示用户登陆成功
                val t = Intent(this@MainActivity, index::class.java)//从login页面跳转到index界面
                startActivity(t)
            }
            if (username.text.toString() != "dai" || password.text.toString() != "123456") {
                Toast.makeText(applicationContext, "用户名或密码错误", Toast.LENGTH_SHORT).show()//提示用户用户名或密码错误
            }
        }
    }
}