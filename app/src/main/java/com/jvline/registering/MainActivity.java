package com.jvline.registering;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Calendar;
public class MainActivity extends AppCompatActivity
{
    private Button buttonReg;
    private TextView textError;
    private EditText myName;
    private EditText surName;
    private Button myDate;
    private TextView textDate;
    private EditText password1;
    private EditText password2;
    private Calendar calendar;
    private DatePickerDialog datePickDialog;
    public int dateCounter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonReg = findViewById(R.id.button_reg);
        textError = findViewById(R.id.text_error);
        myName = findViewById(R.id.my_name);
        surName = findViewById(R.id.sur_name);
        myDate = findViewById(R.id.button_date);
        textDate = findViewById(R.id.text_date);
        password1 = findViewById(R.id.password_1);
        password2 = findViewById(R.id.password_2);
        myDate.setOnClickListener (new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                {
                    datePickDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener()
                    {
                        @Override
                        public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay)
                        {
                            textDate.setText(mDay + "/" + (mMonth + 1) + "/" + mYear);
                        }
                    }, day, month, year);
                    datePickDialog.show();
                    dateCounter = 1;
                }
            }
        });
        buttonReg.setOnClickListener (new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int counter = 0;
                String pass1 = password1.getText().toString();
                String pass2 = password2.getText().toString();
                String name1 = myName.getText().toString();
                String name2 = surName.getText().toString();
                if ((myName.length() < 1) && (counter == 0))
                {
                    textError.setText("Введите имя");
                    counter = 1;
                } else if (((myName.length() <= 2) || !(isFirstUpReg(name1))) && (counter == 0))
                {
                    textError.setText("Имя не корректно");
                    counter = 1;
                } else if ((surName.length() < 1) && (counter == 0))
                {
                    textError.setText("Введите фамилию.");
                    counter = 1;
                } else if (((surName.length() <= 2) || !(isFirstUpReg(name2))) && (counter == 0))
                {
                    textError.setText("Фамилия не корректна");
                    counter = 1;
                }
                else if ((dateCounter == 0) && (counter == 0))
                {
                    textError.setText("Введите дату");
                    counter = 1;
                }
                else if ((password1.length() < 1) && (counter == 0))
                {
                    textError.setText("Заполните первое поле ввода пароля");
                    counter = 1;
                }
                else if ((counter == 0) && (((password1.length() <= 7) && (password1.length() >= 1)) || ((isFindNumber(pass1) == 0) || (isFindUpReg(pass1) == 0))))
                {
                    textError.setText("Пароль не корректен");
                    counter = 1;
                }
                else if ((password2.length() < 1)  && (counter == 0))
                {
                    textError.setText("Заполните второе поле ввода пароля");
                    counter = 1;
                }
                else if (!(pass1.equals(pass2)) && (counter == 0))
                {
                    textError.setText("Пароли не совпадают");
                    counter = 1;
                }
                else if (counter == 0)
                {
                    textError.setText("...");
                    openActivity();
                }
            }
        });
    }
    public void openActivity()
    {
        Intent intent = new Intent(this, WindowEnter.class);
        intent.putExtra("name", myName.getText().toString());
        startActivity(intent);
    }
    public static int isFindNumber(String str)
    {
        char[] data = str.toCharArray();
        int counter = 0;
        for (int i = 0; i < data.length; ++i)
        {
            if (Character.isDigit(data[i]))
            {
                counter = 1;
                break;
            } else
            {
                counter = 0;
            }
        }
        return counter;
    }
    public static int isFindUpReg(String str)
    {
        int counter = 0;
        for(int i=str.length()-1; i>=0; i--)
        {
            if(Character.isUpperCase(str.charAt(i)))
            {
                counter = 1;
                break;
            } else
            {
                counter = 0;
            }
        }
        return counter;
    }
    public static boolean isFirstUpReg(String str)
    {
        return (Character.isUpperCase(str.charAt(0)));
    }
}