package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_result, tv_solution;
    MaterialButton btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_dot;
    MaterialButton btn_divide, btn_multiply, btn_plus, btn_minus, btn_equal;
    MaterialButton btn_c, btn_ac, btn_open_bracket, btn_close_bracket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_result = findViewById(R.id.tv_result);
        tv_solution = findViewById(R.id.tv_solution);

        assignId(btn_0, R.id.btn_0);
        assignId(btn_1, R.id.btn_1);
        assignId(btn_2, R.id.btn_2);
        assignId(btn_3, R.id.btn_3);
        assignId(btn_4, R.id.btn_4);
        assignId(btn_5, R.id.btn_5);
        assignId(btn_6, R.id.btn_6);
        assignId(btn_7, R.id.btn_7);
        assignId(btn_8, R.id.btn_8);
        assignId(btn_9, R.id.btn_9);
        assignId(btn_dot, R.id.btn_dot);

        assignId(btn_divide, R.id.btn_divide);
        assignId(btn_multiply, R.id.btn_multiply);
        assignId(btn_plus, R.id.btn_plus);
        assignId(btn_minus, R.id.btn_minus);
        assignId(btn_equal, R.id.btn_equal);

        assignId(btn_c, R.id.btn_c);
        assignId(btn_ac, R.id.btn_ac);
        assignId(btn_open_bracket, R.id.btn_open_bracket);
        assignId(btn_close_bracket, R.id.btn_close_bracket);

    }

    @Override
    public void onClick(View view) {
        MaterialButton button =(MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = tv_solution.getText().toString();

        if(buttonText.equals("AC")){
            tv_solution.setText("");
            tv_result.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            tv_solution.setText(tv_result.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }
        tv_solution.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Err")){
            tv_result.setText(finalResult);
        }

    }
    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if (finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        } catch (Exception e) {
            return "Err";
        }
    }

    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }
}