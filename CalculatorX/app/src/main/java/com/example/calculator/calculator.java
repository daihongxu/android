package com.example.calculator;

import java.util.Stack;

public class calculator {

    private static double baseCalculate(double num1,char symbol,double num2){//简单加减乘除
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
                    return 0;}
            default:
                return 0;
        }
    }
    public static int symbolPriority(char symbol,int flag){//符号优先级 （最高   */第二    +-第三 ）  最小
        int priority = 0;
        if(symbol=='+'||symbol=='-')
            priority = 1+flag*3;
        if (symbol=='*'||symbol=='/')
            priority = 2+flag*3;
        if (symbol=='(')
            priority = 3+(flag-1)*3;
        if(symbol==')')
            priority = 0;
        return priority;
    }
    public static double stackCalculate(String statement){//带优先级的计算
        Stack<Character> symbolStack = new Stack<>();//符号栈
        Stack<Double> numStack = new Stack<>();//数字栈
        String temp = "";
        symbolStack.push('!');
        char chSymbol=0;//记录
        double lastJudge = 0;//结果
        int flag = 0;

        //为x的y次方写一个函数
        int tempDivision = 0;
        for (int j=0;j<statement.length();j++){
            char ch = statement.charAt(j);
            if(ch=='#'){
                tempDivision = j;
                String a = statement.substring(0,j);
                String b = statement.substring(j+1,statement.length());
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
        if(statement.charAt(0)=='+'||statement.charAt(0)=='-'){
            statement = '0' + statement;
        }

        for(int i=0;i<statement.length();i++){
            char ch = statement.charAt(i);
            if(ch=='+'||ch=='-'||ch=='*'||ch=='/'){
                chSymbol = ch;      //当一个符号被读进来的时候为了和下一个符号对比而记录自己的符号
                symbolStack.push(ch);//入符号栈
            }
            if(ch == '('){
                chSymbol = ch;
                symbolStack.push(ch);
                flag = flag + 1;//（入栈优先级加1
            }
            if (ch == ')'){
                while(symbolStack.peek()!='('){//查看栈顶是否（
                    double operand2 = numStack.pop();
                    double operand1 = numStack.pop();
                    char operate = symbolStack.pop();
                    double result = baseCalculate(operand1,operate,operand2);
                    numStack.push(result);//运算结果入数字栈
                }
                flag = flag - 1;//优先级减1
                symbolStack.pop();//）出栈
            }
            if(ch=='0'||ch=='1'||ch=='2'||ch=='3'||ch=='4'||ch=='5'||ch=='6'||ch=='7'||ch=='8'||ch=='9'||ch=='.'){
                temp = temp + ch;
                int k = i+1;//数字下一位
                if(k<=statement.length()-1) {
                    char tempCh = statement.charAt(k);
                    if(tempCh=='+'||tempCh=='-'||tempCh=='*'||tempCh=='/'||tempCh==')'){//如果符号
                        numStack.push(Double.parseDouble(temp));//数字进数字栈
                        temp = "";

                        if(symbolPriority(tempCh,flag)<=symbolPriority(chSymbol,flag)){//数字后符号优先级小于等于前面的符号
                            double operand1 = numStack.pop();
                            double operand2 = numStack.pop();
                            char operate = symbolStack.pop();
                            double tempResult = baseCalculate(operand2,operate,operand1);//取出数字栈栈顶两数字，字符栈栈顶字符出栈，运算后将结果送到数字栈
                            numStack.push(tempResult);
                        }
                    }
                }else if(i==statement.length()-1) {//最后一个字符

                    numStack.push(Double.parseDouble(temp));
                    temp = "";

                    double operand1 = numStack.pop();
                    double operand2 = numStack.pop();
                    char operate = symbolStack.pop();
                    double tempResult = baseCalculate(operand2,operate,operand1);
                    numStack.push(tempResult);
                }
            }
        }

        lastJudge = numStack.pop();//取数字栈栈顶
        while(!numStack.empty()) {//数字栈不空
            double operand1 = numStack.pop();
            char operate = symbolStack.pop();
            double Result = baseCalculate(operand1,operate,lastJudge);
            numStack.push(Result);
            lastJudge = numStack.pop();
        }
        return lastJudge;

    }

}