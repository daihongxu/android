package com.example.calculator;

import java.util.Stack;

public class calculator {

    private static double Calculate(double num1,char symbol,double num2){//简单加减乘除
        switch (symbol){
            case '+':
                return num1+num2;
            case '-':
                return num1-num2;
            case '*':
                return num1*num2;
            case '/':
                if(num2!=0){
                    return num1/num2;}
                else {
                    return Double.MAX_VALUE;}
            default:
                return Double.MAX_VALUE;
        }
    }

    public static int symbolPriority(char symbol,int pri){//符号优先级 （最高   */第二    +-第三 ）  最小
        int priority = 0;
        if(symbol=='+'||symbol=='-')
            priority = 1+pri*3;
        if (symbol=='*'||symbol=='/')
            priority = 2+pri*3;
        if (symbol=='(')
            priority = 3+(pri-1)*3;
        if(symbol==')')
            priority = 0;
        return priority;
    }

    public static double complexCalculate(String x){//带优先级的计算  x为输入字符串
        Stack<Character> symbolStack = new Stack<>();//符号栈
        Stack<Double> numStack = new Stack<>();//数字栈
        symbolStack.push('!');
        char thisSymbol=0;//记录
        int flag = 0;
//为x的y次方写一个函数
        int tempDivision = 0;

        String c = x.substring(x.length()-1, x.length());//如果最后一个符号为+—*/返回0
        if(c.charAt(0)=='+'||c.charAt(0)=='-'||c.charAt(0)=='*'||c.charAt(0)=='/'||c.charAt(0)=='('){
        return Double.MAX_VALUE;
        }

        for (int j=0;j<x.length();j++){
            char ch =x.charAt(j);
            if(ch=='#'){
                tempDivision = j;
                String a = x.substring(0,j);
                String b = x.substring(j+1,x.length());
                Double a1 = Double.parseDouble(a);
                Double b1 = Double.parseDouble(b);
                double result = 1;
                for (int i=0;i<b1;i++){
                    result = result * a1;
                }
                return result;
            }
        }
        //判断第一个字符是否为正负号，如果是正负号的话在前面加一个0防止栈出现问题
        if(x.charAt(0)=='+'||x.charAt(0)=='-'){
            x = '0' + x;
        }

        for(int i=0;i<x.length();i++){
            char ch = x.charAt(i);
            if(ch=='+'||ch=='-'||ch=='*'||ch=='/'){
                thisSymbol = ch;      //当一个符号被读进来的时候为了和下一个符号对比而记录自己的符号
                symbolStack.push(ch);//入符号栈
            }
            if(ch == '('){
                thisSymbol = ch;
                symbolStack.push(ch);
                flag = flag + 1;//（入栈优先级标记加1
            }
            if (ch == ')'){
                while(symbolStack.peek()!='('){//查看栈顶是否（
                    double first = numStack.pop();
                    double second = numStack.pop();
                    char sym = symbolStack.pop();
                    double result = Calculate(first,sym,second);
                    numStack.push(result);//运算结果入数字栈
                }
                flag = flag - 1;//优先级标记减1
                symbolStack.pop();//）出栈
            }
            if(ch=='0'||ch=='1'||ch=='2'||ch=='3'||ch=='4'||ch=='5'||ch=='6'||ch=='7'||ch=='8'||ch=='9'||ch=='.'){
                String string = "";
                string = string + ch;
                int j = i+1;//数字下一位
                if(j<=x.length()-1) {
                    char temp = x.charAt(j);
                    if(temp=='+'||temp=='-'||temp=='*'||temp=='/'||temp==')'){//如果符号
                        numStack.push(Double.parseDouble(string));//数字进数字栈
                        string = "";

                        if(symbolPriority(temp,flag)<=symbolPriority(thisSymbol,flag)){//数字后符号优先级小于等于前面的符号
                            double first = numStack.pop();
                            double second = numStack.pop();
                            char sym = symbolStack.pop();
                            double Result = Calculate(second,sym,first);//取出数字栈栈顶两数字，字符栈栈顶字符出栈，运算后将结果送到数字栈
                            numStack.push(Result);
                        }
                    }
                }else if(i==x.length()-1) {//最后一个字符

                    numStack.push(Double.parseDouble(string));
                    string = "";

                    double first = numStack.pop();
                    double second = numStack.pop();
                    char sym = symbolStack.pop();
                    double Result = Calculate(second,sym,first);
                    numStack.push(Result);
                }
            }
        }

        double  lastJudge = numStack.pop();//取数字栈栈顶
        if(!numStack.empty()) {//数字栈不空
            double second = numStack.pop();
            char sym= symbolStack.pop();
            double Result = Calculate(second,sym,lastJudge);
            numStack.push(Result);
            lastJudge = numStack.pop();
        }
        return lastJudge;
    }

}