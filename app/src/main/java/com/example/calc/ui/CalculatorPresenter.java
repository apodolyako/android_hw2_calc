package com.example.calc.ui;

import android.bluetooth.le.ScanSettings;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.calc.domain.Calculator;
import com.example.calc.domain.Operation;

import java.util.ArrayList;


public class CalculatorPresenter {

    private CalculatorView view;

    private Calculator calculator;

    private final String[] btnOperation = {"-", "+", "*", "/"};
    private final String[] btnNum = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};

    private Operation operator;
    private CalculatorData data;

    public CalculatorPresenter(CalculatorView view, Calculator calculator) {
        this.view = view;
        this.calculator = calculator;
        this.data = new CalculatorData();
    }


    public CalculatorData getData(){
        return this.data;
    }

    public CalculatorView getView(){
        return this.view;
    }
    public void setData(CalculatorData data){
        this.data = data;
    }

    public String[] getBtnOperation(){
        return this.btnOperation;
    }

//--------------Обрабатываем нажатие кнопок с цифрами и операторы + - * /

    public void onBtn_Clicked(String number){
        //как-то реагируем на событие что пользователь нажал кнопку 1
        if(checkIsOperation(number, btnOperation)){
            if (checkFirst()){
                return;
            }
        }
        data.setBtnClick(number);
        enterOperand(data.getBtnClick());
        view.showExpression((getExpStr()));
    }

    public boolean checkFirst(){
        if (data.getExpStr().size() == 0){
            return true;
        }
        return false;
    }

//-----------логика Нажатия-------------------

    public String getExpStr() {
        String str ="";
        for (String val : data.getExpStr()) {
            str+=val;
        }
        return str;
    }

    private void enterOperand(String btnClick){

        if(checkIsOperation(data.getLastClick(), btnOperation) && checkIsOperation(btnClick, btnOperation)){
            data.getExpStr().set(data.getExpStr().size()-1, btnClick);
        }else if(checkIsOperation(data.getLastClick(), btnNum) && checkIsOperation(btnClick, btnNum)) {
            setMultiNum(btnClick);
        }else{
            data.getExpStr().add(btnClick);
        }
        data.setLastClick(btnClick);

        if(checkIsOperation(btnClick, btnNum)){ //если нажата цыфра = считаю и вывожу результат
            calculate();
        }
    }

    private void setMultiNum(String btnClick) {
        data.getExpStr().set(data.getExpStr().size()-1, data.getExpStr().get(data.getExpStr().size()-1)+btnClick);
    }

    public void calculate() {
        ArrayList<String> expResult = (ArrayList<String>)data.getExpStr().clone();
        calculatorRun(expResult);
    }

    public void calculate(ArrayList<String> expResult) {
        calculatorRun(expResult);
    }

    private void calculatorRun(ArrayList<String> exp){
        exp = doOperation(exp,"*","/");
        exp = doOperation(exp,"+","-");
        view.showResult(exp.get(0));
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

        val = val*100;
        int result =(int)Math.round(val);
        float result2 = (float)result/100;
        arr.set(i, String.valueOf(result2));

        arr.remove(i-1);
        arr.remove(i);
    }

    public boolean checkIsOperation(String val, String[] btnClick) {
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

    //----------Обрабатываем  =  СЕ <-- -----------

    public void onBtn_CE_Clicked() {
        delElemExpStr();
        view.showResult("0");
        view.showExpression("");
    }

    private void delElemExpStr() {
        data.getExpStr().removeAll(data.getExpStr());
        data.setLastClick("");
        data.setBtnClick("");
    }

    public void onBtn_EQ_Clicked(){
        delElemExpStr();
        view.showExpression("");
    }

    public  void onBtn_Back_Clicked(){
 //символ перед стираемым -если оператор тогда нужно посчитать выражение до него

        if(checkFirst()){
            return;
        }

        if(data.getExpStr().size() == 1){
            onBtn_CE_Clicked();
            return;
        }

        if (checkIsOperation(getExpStr().split("")[getExpStr().split("").length-2], btnOperation)){
            calculate(exprStrArrListFromStr(getExpStr().substring(0, getExpStr().length()-2)));
            data.setLastClick(getExpStr().split("")[getExpStr().split("").length-2]);
            data.getExpStr().remove(data.getExpStr().size()-1);
            view.showExpression(getExpStr());

        }else{
            data.setLastClick(getExpStr().split("")[getExpStr().split("").length-2]);
            data.setExpStr(exprStrArrListFromStr(getExpStr().substring(0, getExpStr().length()-1)));
            calculate();
            view.showExpression(getExpStr());
        }
    }

    public ArrayList<String> exprStrArrListFromStr(String str) {

        ArrayList<String> tmpArrList = new ArrayList<String>();
        String[] strArr = str.split("");

        String tmpStr= "";
        for (String s : strArr) {
            switch (s){
                case "+":
                case "-":
                case "*":
                case "/":
                    tmpArrList.add(tmpStr);
                    tmpStr = "";
                    tmpArrList.add(s);
                    break;
                default:
                    tmpStr = tmpStr + s;
            }
        }
        tmpArrList.add(tmpStr);
        return tmpArrList;

    }


}
