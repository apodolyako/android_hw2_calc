package com.example.calc.ui;

import com.example.calc.domain.Calculator;
import com.example.calc.domain.Operation;

import java.util.ArrayList;


public class CalculatorPresenter {

    private CalculatorView view;

    private Calculator calculator;

    private String lastClick ="";
    private String[] btnOperation = {"-", "+", "*", "/"};
    private String[] btnNum = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
    private String btnClick;
    private Operation operator;
    ArrayList<String> expStr = new ArrayList<>();

    public CalculatorPresenter(CalculatorView view, Calculator calculator) {
        this.view = view;
        this.calculator = calculator;
    }

//--------------Обрабатываем нажатие кнопок с цифрами

    public void onBtn_1_Clicked() {
        //как-то реагируем на событие что пользователь нажал кнопку 1
        btnClick = "1";
        enterOperand(btnClick);
        view.showExpression(getExpStr());
    }

    public void onBtn_2_Clicked() {
        btnClick = "2";
        enterOperand(btnClick);
        view.showExpression(getExpStr());
    }

    public void onBtn_3_Clicked() {
        btnClick = "3";
        enterOperand(btnClick);
        view.showExpression(getExpStr());
    }

    public void onBtn_4_Clicked() {
        btnClick = "4";
        enterOperand(btnClick);
        view.showExpression(getExpStr());
    }

    public void onBtn_5_Clicked() {
        btnClick = "5";
        enterOperand(btnClick);
        view.showExpression(getExpStr());
    }

    public void onBtn_6_Clicked() {
        btnClick = "6";
        enterOperand(btnClick);
        view.showExpression(getExpStr());
    }

    public void onBtn_7_Clicked() {
        btnClick = "7";
        enterOperand(btnClick);
        view.showExpression(getExpStr());
    }

    public void onBtn_8_Clicked() {
        btnClick = "8";
        enterOperand(btnClick);
        view.showExpression(getExpStr());
    }

    public void onBtn_9_Clicked() {
        btnClick = "9";
        enterOperand(btnClick);
        view.showExpression(getExpStr());
    }

    public void onBtn_0_Clicked() {
        btnClick = "0";
        enterOperand(btnClick);
        view.showExpression(getExpStr());
    }

//------------------Обрабатываем операторы + - * /

    public void onBtn_Plus_Clicked() {
        btnClick = "+";
        enterOperand(btnClick);
        view.showExpression(getExpStr());
    }
    public void onBtn_Sub_Clicked() {
        btnClick = "-";
        enterOperand(btnClick);
        view.showExpression(getExpStr());
    }
    public void onBtn_Mult_Clicked() {
        btnClick = "*";
        enterOperand(btnClick);
        view.showExpression(getExpStr());
    }
    public void onBtn_Div_Clicked() {
        btnClick = "/";
        enterOperand(btnClick);
        view.showExpression(getExpStr());
    }

//-----------логика Нажатия-------------------

    private String getExpStr() {
        String str ="";
        for (String val : expStr) {
            str+=val;
        }
        return str;
    }

    private void enterOperand(String btnClick){

        if(checkIsOperation(lastClick, btnOperation) && checkIsOperation(btnClick, btnOperation)){
            expStr.set(expStr.size()-1, btnClick);
        }else if(checkIsOperation(lastClick, btnNum) && checkIsOperation(btnClick, btnNum)) {
            setMultiNum(btnClick);
        }else{
            expStr.add(btnClick);
        }
        lastClick = btnClick;

        if(checkIsOperation(btnClick, btnNum)){ //если нажата цыфра = считаю и вывожу результат
            calculate();
        }
    }

    private void setMultiNum(String btnClick) {
        expStr.set(expStr.size()-1, expStr.get(expStr.size()-1)+btnClick);

    }

    private void calculate() {
        ArrayList<String> expResult = (ArrayList<String>)expStr.clone();
        expResult = doOperation(expResult,"*","/");
        expResult = doOperation(expResult,"+","-");
        view.showResult(expResult.get(0));
    }

    private ArrayList<String> doOperation(ArrayList<String> arr, String oper1, String oper2) {
        boolean flag = true;
        int count;

        while (flag) {
            count = 0;
            for (int i = 0; i < arr.size(); i++, count++) {

                if (arr.get(i).equals(oper1) || arr.get(i).equals(oper2)) {
                    removePair(arr, i);
                    break;
                }
            }
            if (count == arr.size()) {
                flag = false;
            }
        }
        return arr;

    }

    private void removePair(ArrayList<String> arr, int i) { //убираю пары чисел из ArrayList те после вычисления - меняю их на резудьтат
        double val;

        setOperation(arr.get(i));
        val = calculator.binaryOperation((Double.valueOf(arr.get(i-1))), (Double.valueOf(arr.get(i+1))), operator );

        arr.set(i, String.valueOf(val));

        arr.remove(i-1);
        arr.remove(i);

    }

    private boolean checkIsOperation(String val, String[] btnClick) {
        for (String s : btnClick) {
            if(s.equals(val)){
                return true;
            }
        }
        return  false;
    }

    private void setOperation(String lastClick) {
        switch (lastClick){
            case "+":
                operator = Operation.ADD;
                break;
            case "-":
                operator = Operation.SUB;
                break;
            case "*":
                operator = Operation.MULT;
                break;
            case "/":
                operator = Operation.DIV;
                break;
        }
    }


}
