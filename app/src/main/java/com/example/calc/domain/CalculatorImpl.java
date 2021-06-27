package com.example.calc.domain;

public class CalculatorImpl implements Calculator {

    @Override
    public double binaryOperation(double argOne, double argTwo, Operation operation) {
        switch (operation) {
            case ADD:
                return argOne + argTwo;
            case SUB:
                return argOne - argTwo;
            case MULT:
                return argOne * argTwo;
             case DIV:
                return argOne / argTwo;
        }
        return 0;
    }

}
