package com.example.calc.ui;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class CalculatorData implements Parcelable {


    private String lastClick;
    private String btnClick;
    private ArrayList<String> expStr;

    public CalculatorData(){
        this.lastClick = "";
        this.btnClick = "";
        this.expStr = new ArrayList<String>();

    }

    protected CalculatorData(Parcel in) {
        lastClick = in.readString();
        btnClick = in.readString();
        expStr = in.createStringArrayList();
    }

    public static final Creator<CalculatorData> CREATOR = new Creator<CalculatorData>() {
        @Override
        public CalculatorData createFromParcel(Parcel in) {
            return new CalculatorData(in);
        }

        @Override
        public CalculatorData[] newArray(int size) {
            return new CalculatorData[size];
        }
    };

    public String getLastClick() {
        return lastClick;
    }

    public void setLastClick(String lastClick) {
        this.lastClick = lastClick;
    }

    public String getBtnClick() {
        return btnClick;
    }

    public void setBtnClick(String btnClick) {
        this.btnClick = btnClick;
    }

    public ArrayList<String> getExpStr() {
        return expStr;
    }

    public void setExpStr(ArrayList<String> expStr) {
        this.expStr = expStr;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(arrlist2arrstr(expStr));
        dest.writeString(lastClick);
        dest.writeString(btnClick);

    }

    private String[] arrlist2arrstr(ArrayList<String> expStr) {
        String[] strArr = expStr.toArray(new String[expStr.size()]);
        return strArr;

        }
}
