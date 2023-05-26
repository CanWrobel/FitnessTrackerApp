package sampleViews;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidjava.MainActivity;
import com.example.androidjava.R;

import java.util.Calendar;

public class InputActivity extends AppCompatActivity {
    TextView tvSelectedDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSelectedDate = findViewById(R.id.tvSelectedDate);

        // Create a Date Picker
        tvSelectedDate.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(InputActivity.this,
                    (view, year, monthOfYear, dayOfMonth) -> {
                        String selectedDate = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;

                        // Display Selected date in TextView
                        tvSelectedDate.setText(selectedDate);

                        // TODO: Save selectedDate in the database or other storage

                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        });
    }
}
