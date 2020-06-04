package Calc.calculator;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    boolean Action_To_Result = false;
    boolean Float = false;
    boolean Positive = true;
    boolean Access_Float = true;
    boolean After_Result = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView TV = (TextView) findViewById(R.id.Result_View);
        final Button Div_2 = (Button) findViewById(R.id.B_Div_2);
        final Button Div = (Button) findViewById(R.id.B_Div);
        final Button Mul = (Button) findViewById(R.id.B_Mul);
        TV.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                if (TV.getText().toString().equals("0")) {
                    Div.setEnabled(false);
                    Div_2.setEnabled(false);
                    Mul.setEnabled(false);
                } else {
                    Div.setEnabled(true);
                    Div_2.setEnabled(true);
                    Mul.setEnabled(true);
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
    }

    void Action(int Button_Num) {
        TextView TV = (TextView) findViewById(R.id.Result_View);
        if (After_Result)
        {
            TV.setText(String.valueOf(Button_Num));
            After_Result = false;
            return;
        }
        if (TV.getText().toString().equals("0")) {
            TV.setText(String.valueOf(Button_Num));
        } else {
            TV.setText(TV.getText().toString() + Button_Num);
        }
    }

    public void B_7(View view) {
        Check_Error();
        Action(7);
    }

    public void B_8(View view) {
        Check_Error();
        Action(8);
    }

    public void B_9(View view) {
        Check_Error();
        Action(9);
    }

    public void B_4(View view) {
        Check_Error();
        Action(4);
    }

    public void B_5(View view) {
        Check_Error();
        Action(5);
    }

    public void B_6(View view) {
        Check_Error();
        Action(6);
    }

    public void B_1(View view) {
        Check_Error();
        Action(1);
    }

    public void B_2(View view) {
        Check_Error();
        Action(2);
    }

    public void B_3(View view) {
        Check_Error();
        Action(3);
    }

    public void B_0(View view) {
        Check_Error();
        Action(0);
    }

    public void B_Point(View view) {
        Check_Error();
        if (Access_Float) {
            TextView TV = (TextView) findViewById(R.id.Result_View);
            TV.setText(TV.getText().toString() + ".");
            Access_Float = false;
            Float = true;
        }
    }
    public void B_AC(View view) {
        TextView TV = (TextView) findViewById(R.id.Result_View);
        TV.setText("0");
        Action_To_Result = false;
        Access_Float = true;
        Float = false;
    }

    void Check_Error()
    {
        TextView TV = (TextView) findViewById(R.id.Result_View);
        if (TV.getText().toString().equals("ERROR"))
        {
            Button AC = (Button) findViewById(R.id.B_AC);
            AC.performClick();
        }
    }



    public void B_Plus_Minus(View view) {
        Check_Error();
        TextView TV = (TextView) findViewById(R.id.Result_View);
        if (Positive && TV.getText().toString().equals("0") == false) {
            TV.setText("-" + TV.getText().toString());
            Positive = false;
        } else if (TV.getText().toString().equals("0") == false) {
            TV.setText(TV.getText().toString().substring(1));
            Positive = true;
        }
    }

    public void B_Addict(View view) {
        Check_Error();
        TextView TV = (TextView) findViewById(R.id.Result_View);
        if (Action_To_Result) {
            if (Character.isDigit(TV.getText().toString().toCharArray()[TV.getText().length() - 1]) == false)
            {
                TV.setText(TV.getText().toString().substring(0, TV.getText().length() - 1) + '+');
                return;
            }
            Button EXECUTE = (Button) findViewById(R.id.B_EXECUTE);
            EXECUTE.performClick();
            TV.setText(TV.getText().toString() + "+");
            After_Result = false;
            Action_To_Result = true;
            Access_Float = true;
            return;
        }
        if (Character.isDigit(TV.getText().toString().toCharArray()[TV.getText().length() - 1]) == false)
        {
            TV.setText(TV.getText().toString().substring(0, TV.getText().length() - 1) + '+');
            return;
        }
        else
        {
            TV.setText(TV.getText().toString() + "+");
        }
        After_Result = false;
        Action_To_Result = true;
        Access_Float = true;
    }

    void Result(char Action) {
        TextView TV = (TextView) findViewById(R.id.Result_View);
        try {
            String Var = TV.getText().toString();
            After_Result = true;
            Action_To_Result = false;
            int Adr = 0;
            for (int i = 0; i < Var.length(); i++) {
                if (Var.toCharArray()[i] == Action) {
                    Adr = i;
                    break;
                }
            }
            switch (Action) {
                case '+':
                    if (Float) {
                        double Result = Double.valueOf(Var.substring(0, Adr)) + Double.valueOf(Var.substring(Adr + 1));
                        TV.setText(String.valueOf(Result));
                    } else {
                        int Result = Integer.valueOf(Var.substring(0, Adr)) + Integer.valueOf(Var.substring(Adr + 1));
                        TV.setText(String.valueOf(Result));
                    }
                    break;
                case '-':
                    if (Float) {
                        double Result = Double.valueOf(Var.substring(0, Adr)) - Double.valueOf(Var.substring(Adr + 1));
                        TV.setText(String.valueOf(Result));
                    } else {
                        int Result = Integer.valueOf(Var.substring(0, Adr)) - Integer.valueOf(Var.substring(Adr + 1));
                        TV.setText(String.valueOf(Result));
                    }
                    break;
                case '*':
                    if (Float) {
                        double Result = Double.valueOf(Var.substring(0, Adr)) * Double.valueOf(Var.substring(Adr + 1));
                        TV.setText(String.valueOf(Result));
                    } else {
                        long Result = Integer.valueOf(Var.substring(0, Adr)) * Integer.valueOf(Var.substring(Adr + 1));
                        TV.setText(String.valueOf(Result));
                    }
                    break;
                case '÷':
                    double Result = Double.valueOf(Var.substring(0, Adr)) / Double.valueOf(Var.substring(Adr + 1));
                    TV.setText(String.valueOf(Result));
                    break;
                case '%':
                    double Result2 = Double.valueOf(Var.substring(0, Adr)) % Double.valueOf(Var.substring(Adr + 1));
                    TV.setText(String.valueOf(Result2));
                    break;
            }
        }
        catch (Exception s)
        {
            TV.setText("ERROR");
            Action_To_Result = false;
            After_Result = true;
        }
    }

    public void B_Sub(View view) {
        Check_Error();
        TextView TV = (TextView) findViewById(R.id.Result_View);
        if (Action_To_Result) {
            if (Character.isDigit(TV.getText().toString().toCharArray()[TV.getText().length() - 1]) == false)
            {
                TV.setText(TV.getText().toString().substring(0, TV.getText().length() - 1) + '-');
                return;
            }
            Button EXECUTE = (Button) findViewById(R.id.B_EXECUTE);
            EXECUTE.performClick();
            TV.setText(TV.getText().toString() + "-");
            After_Result = false;
            Action_To_Result = true;
            Access_Float = true;
            return;
        }
        if (Character.isDigit(TV.getText().toString().toCharArray()[TV.getText().length() - 1]) == false)
        {
            TV.setText(TV.getText().toString().substring(0, TV.getText().length() - 1) + '-');
            return;
        }
        else
        {
            TV.setText(TV.getText().toString() + "-");
        }
        After_Result = false;
        Action_To_Result = true;
        Access_Float = true;
    }

    public void B_Mul(View view) {
        Check_Error();
        TextView TV = (TextView) findViewById(R.id.Result_View);
        if (Action_To_Result) {
            if (Character.isDigit(TV.getText().toString().toCharArray()[TV.getText().length() - 1]) == false)
            {
                TV.setText(TV.getText().toString().substring(0, TV.getText().length() - 1) + '*');
                return;
            }
            Button EXECUTE = (Button) findViewById(R.id.B_EXECUTE);
            EXECUTE.performClick();
            TV.setText(TV.getText().toString() + "*");
            After_Result = false;
            Action_To_Result = true;
            Access_Float = true;
            return;
        }
        if (Character.isDigit(TV.getText().toString().toCharArray()[TV.getText().length() - 1]) == false)
        {
            TV.setText(TV.getText().toString().substring(0, TV.getText().length() - 1) + '*');
            return;
        }
        else
        {
            TV.setText(TV.getText().toString() + "*");
        }
        After_Result = false;
        Action_To_Result = true;
        Access_Float = true;
    }

    public void B_EXECUTE(View view) {
        Check_Error();
        TextView TV = (TextView) findViewById(R.id.Result_View);
		if (Character.isDigit(TV.getText().toString().toCharArray()[TV.getText().length() - 1]) == false)return;           
        if (Action_To_Result)
        {
            String Var = TV.getText().toString();
            for (int i = 1; i < Var.length(); i++) {
                if (Var.toCharArray()[i] == '+' || Var.toCharArray()[i] == '-' || Var.toCharArray()[i] == '*' || Var.toCharArray()[i] == '÷' || Var.toCharArray()[i] == '%') {
                    Result(Var.toCharArray()[i]);
                    break;
                }
            }
        }
    }

    public void B_Div(View view) {
        Check_Error();
        TextView TV = (TextView) findViewById(R.id.Result_View);
        if (Action_To_Result) {
            if (Character.isDigit(TV.getText().toString().toCharArray()[TV.getText().length() - 1]) == false)
            {
                TV.setText(TV.getText().toString().substring(0, TV.getText().length() - 1) + '÷');
                return;
            }
            Button EXECUTE = (Button) findViewById(R.id.B_EXECUTE);
            EXECUTE.performClick();
            TV.setText(TV.getText().toString() + "÷");
            After_Result = false;
            Action_To_Result = true;
            Access_Float = true;
            return;
        }
        if (Character.isDigit(TV.getText().toString().toCharArray()[TV.getText().length() - 1]) == false)
        {
            TV.setText(TV.getText().toString().substring(0, TV.getText().length() - 1) + '÷');
            return;
        }
        else
        {
            TV.setText(TV.getText().toString() + "÷");
        }
        After_Result = false;
        Action_To_Result = true;
        Access_Float = true;
    }

    public void B_Div_2(View view) {
        Check_Error();
        TextView TV = (TextView) findViewById(R.id.Result_View);
        if (Action_To_Result) {
            if (Character.isDigit(TV.getText().toString().toCharArray()[TV.getText().length() - 1]) == false)
            {
                TV.setText(TV.getText().toString().substring(0, TV.getText().length() - 1) + '%');
                return;
            }
            Button EXECUTE = (Button) findViewById(R.id.B_EXECUTE);
            EXECUTE.performClick();
            TV.setText(TV.getText().toString() + "%");
            After_Result = false;
            Action_To_Result = true;
            Access_Float = true;
            return;
        }
        if (Character.isDigit(TV.getText().toString().toCharArray()[TV.getText().length() - 1]) == false)
        {
            TV.setText(TV.getText().toString().substring(0, TV.getText().length() - 1) + '%');
            return;
        }
        else
        {
            TV.setText(TV.getText().toString() + "%");
        }
        After_Result = false;
        Action_To_Result = true;
        Access_Float = true;
    }
}
