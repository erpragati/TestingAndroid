/* $Id: $
 */
package net.callmeike.android.testing;

import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import net.callmeike.android.testing.calc.Calculator;
import net.callmeike.android.testing.calc.Calculator.CalculationException;


/**
 * TestTargetActivity
 *
 * @version $Revision: $
 * @author <a href="mailto:blake.meike@gmail.com">G. Blake Meike</a>
 */
public class TestTargetActivity extends Activity implements Calculator.StackChangeListener {
    Calculator calc;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        calc = new Calculator();
        calc.setStackChangeListener(this);

        setContentView(R.layout.main);

        ((Button) findViewById(R.id.enter)).setOnClickListener(
            new Button.OnClickListener() {
                @Override
                public void onClick(View v) { calc.push(getAndClear()); }
            });

        ((Button) findViewById(R.id.add)).setOnClickListener(
            new Button.OnClickListener() {
                @Override public void onClick(View v) { eval(Calculator.Op.ADD); }
            });

        ((Button) findViewById(R.id.sub)).setOnClickListener(
            new Button.OnClickListener() {
                @Override public void onClick(View v) { eval(Calculator.Op.SUB); }
            });

        ((Button) findViewById(R.id.mul)).setOnClickListener(
            new Button.OnClickListener() {
                @Override public void onClick(View v) { eval(Calculator.Op.MUL); }
            });


        ((Button) findViewById(R.id.div)).setOnClickListener(
            new Button.OnClickListener() {
                @Override public void onClick(View v) { eval(Calculator.Op.DIV); }
            });
    }

    /**
     * @see net.callmeike.android.testing.calc.Calculator.StackChangeListener#stackChanged(java.util.List)
     */
    @Override
    public void stackChanged(List<Integer> contents) {
        Collections.reverse(contents);
        StringBuilder str = new StringBuilder();
        for (Integer n: contents) { str.append(n).append("\n"); }
        ((TextView) findViewById(R.id.top)).setText(str.toString());
    }

    Integer getAndClear() {
        EditText enter = (EditText) findViewById(R.id.input);
        String text = enter.getText().toString();
        if (0 >= text.length()) { return null; }
        enter.setText("");
        return Integer.valueOf(Integer.parseInt(text));
    }

    void eval(Calculator.Op op) {
        Integer n = getAndClear();
        if (null != n) { calc.push(n); }
        try { calc.eval(op); }
        catch (CalculationException e) {
            ((TextView) findViewById(R.id.top)).setText(e.getMessage());
        }
    }
}