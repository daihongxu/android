package com.example.calculator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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
    EditText input ;
    boolean clear_flag ;//清空标识

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
//以上实例化按钮
        input = (EditText) findViewById(R.id.input);  //实例化之后的显示屏

        clear.setOnClickListener(this);
        delete.setOnClickListener(this);
        cheng.setOnClickListener(this);
        chu.setOnClickListener(this);
        num7.setOnClickListener(this);
        num8.setOnClickListener(this);
        num9.setOnClickListener(this);
        jian.setOnClickListener(this);
        num4.setOnClickListener(this);
        num5.setOnClickListener(this);
        num6 .setOnClickListener(this);
        jia.setOnClickListener(this);
        num1.setOnClickListener(this);
        num2.setOnClickListener(this);
        num3.setOnClickListener(this);
        num0.setOnClickListener(this);
        dian.setOnClickListener(this);
        equal.setOnClickListener(this);
        //设置以上按钮的点击事件


    }

    @Override
    public void onClick(View v) {
        String str = input.getText().toString();
        switch (v.getId()) {
            case R.id.num0:
            case R.id.num1:
            case R.id.num2:
            case R.id.num3:
            case R.id.num4:
            case R.id.num5:
            case R.id.num6:
            case R.id.num7:
            case R.id.num8:
            case R.id.num9:
            case R.id.dian:
                if (clear_flag) {
                    clear_flag =false ;
                    str ="" ;
                    input.setText("");
                }
                input.setText(str + ((Button)v).getText());
                break ;
            case R.id.jia:
            case R.id.jian:
            case R.id.cheng:
            case R.id.chu:
                if (clear_flag) {
                    clear_flag =false ;
                    str ="" ;
                    input.setText("");
                }
                input.setText(str+ " " + ((Button)v).getText()+" ");
                break;
            case R.id.delete:
                if (clear_flag) {
                    clear_flag =false ;
                    str ="" ;
                    input.setText("");
                }else if (str!=null&&!str.equals("")){
                    input.setText(str.substring(0,str.length()-1));
                }
                break;
            case R.id.clear:
                clear_flag =false ;
                str ="" ;
                input.setText("");
            case R.id.equal:
                getResult();
                break ;

        }
    }
    /* 单独的调用运算结果
     *
     *
     * */
    private void getResult(){
        String exp = input.getText().toString();
        if (exp == null||exp.equals("")){
            return;
        }
        if(!exp.contains(" ")) {
            return;
        }
        if (clear_flag){
            clear_flag = false ;
            return;

        }
        clear_flag = true ;
        double result = 0;
        String s1 = exp.substring(0,exp.indexOf(" ")); //运算符前面的字符串
        String op = exp.substring(exp.indexOf(" ")+1,exp.indexOf(" ")+2) ;
        String s2 = exp.substring(exp.indexOf(" ")+3) ;
        if (!s1.equals(" ")&&!s2.equals(" ")){
            double d1 = Double.parseDouble(s1) ;
            double d2 = Double.parseDouble(s2) ;
            switch (op)
            {
                case "＋": result=d1+d2;break;
                case "－": result=d1-d2;break;
                case "×": result=d1*d2;break;
                case "÷":
                {
                    if(d2==0)
                    {

                        break;
                    }
                    result=d1/d2*1.0;
                }
                break;
            }
            if (s1.contains(".")&&s2.contains(".")) {
                int r = (int) result;
                input.setText(r+"");
            }else {
                input.setText(result+"");

            }
        }else if (!s1.equals("")&&s2.equals("")){
            input.setText(exp);
        }else if (s1.equals("")&&!s2.equals("")){
            double d2 = Double.parseDouble(s2) ;
            if (op.equals("+")){
                result = 0 + d2 ;

            }else  if (op.equals("-")){
                result = 0 - d2 ;

            }else  if (op.equals("*")){
                result = 0 ;

            }else  if (op.equals("/")){
                result = 0 ;
            }
            if (s2.contains(".")) {
                int r = (int) result;
                input.setText(r+"");
            }else {
                input.setText(result+"");
            }
        }else {
            input.setText("");

        }
    }
}
