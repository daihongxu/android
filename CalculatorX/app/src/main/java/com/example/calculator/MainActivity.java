package com.example.calculator;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import java.util.Stack;

public class MainActivity extends Activity {

    Button clear ;
    Button delete;
    Button cheng;
    Button chu ;
    Button num7 ;
    Button num8 ;
    Button num9 ;
    Button jian ;
    Button num4 ;
    Button num5 ;
    Button num6 ;
    Button jia ;
    Button num1 ;
    Button num2 ;
    Button num3;
    Button num0;
    Button dian ;
    Button equal ;
    Button zuo ;
    Button you;
    EditText input ;
    boolean clear_flag ;//清空标识

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = (TextView) findViewById(R.id.input);//输入
        final TextView textView1 = (TextView) findViewById(R.id.output);//输出
        final calculator calculate = new calculator();


        clear = (Button) findViewById(R.id.clear) ;
        delete= (Button) findViewById(R.id.delete) ;
        cheng = (Button) findViewById(R.id.cheng) ;
        chu = (Button) findViewById(R.id.chu) ;
        num7 = (Button) findViewById(R.id.num7) ;
        num8= (Button) findViewById(R.id.num8) ;
        num9 = (Button) findViewById(R.id.num9) ;
        jian = (Button) findViewById(R.id.jian) ;
        num4 = (Button) findViewById(R.id.num4) ;
        num5 = (Button) findViewById(R.id.num5) ;
        num6 = (Button) findViewById(R.id.num6) ;
        jia = (Button) findViewById(R.id.jia) ;
        num1 = (Button) findViewById(R.id.num1) ;
        num2 = (Button) findViewById(R.id.num2) ;
        num3 = (Button) findViewById(R.id.num3) ;
        num0 = (Button) findViewById(R.id.num0) ;
        dian = (Button) findViewById(R.id.dian) ;
        equal = (Button) findViewById(R.id.equal) ;
        zuo=(Button) findViewById(R.id.zuo) ;
        you=(Button) findViewById(R.id.you) ;
//以上实例化按钮

        num0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText()+"0");
            }
        });
        num1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText()+"1");
            }
        });
        num2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText()+"2");
            }
        });
        num3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText()+"3");
            }
        });
        num4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText()+"4");
            }
        });
        num5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText()+"5");
            }
        });
        num6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText()+"6");
            }
        });
        num7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText()+"7");
            }
        });
        num8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText()+"8");
            }
        });
        num9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText()+"9");
            }
        });
        jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText()+"+");
            }
        });
        jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText()+"-");
            }
        });
        cheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textView.length()!=0){
                    textView.setText(textView.getText()+"*");
                }

            }
        });
        chu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textView.length()!=0){
                    textView.setText(textView.getText()+"/");
                }
            }
        });
        dian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText()+".");
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = textView.getText().toString();
                String tempReplace = temp.substring(0,temp.length()-1);
                textView.setText(tempReplace);

            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("");
                textView1.setText("");
            }
        });
        zuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText()+"(");
            }
        });
        you.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText()+")");
            }
        });
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = textView.getText().toString();//获取输入字符串
                Double result = calculate.stackCalculate(temp);//计算结果
                textView1.setText(result.toString());//将结果变为字符串并输出
            }
        });
    }
}
