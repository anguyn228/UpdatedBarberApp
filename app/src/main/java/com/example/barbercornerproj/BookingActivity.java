package com.example.barbercornerproj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.barbercornerproj.adapter.BarberAdapter;
import com.example.barbercornerproj.fragment.SelectBarberDialogFragment;
import com.example.barbercornerproj.model.StaffModel;

import java.util.Calendar;

public class BookingActivity extends AppCompatActivity implements SelectBarberDialogFragment.SelectBarberDialogListener {

    private Toolbar toolbar;
    private TextView txtSelectTime;
    private TextView txtBarberName;
    private RelativeLayout relSelectBarber;
    private Button btnBook;

    private int mDay, mMonth, mYear;
    private int mHour, mMinute, mSecond;
    private CalendarView calendarView;
    private DatabaseHelper databaseHelper = new DatabaseHelper(this);
    private StaffModel barber;

    private int userId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        databaseHelper.insertTestBarber();


        //  Get current date
        Calendar calendar = Calendar.getInstance();
        mDay = calendar.get(Calendar.DATE);
        mMonth = calendar.get(Calendar.MONTH) + 1;
        mYear = calendar.get(Calendar.YEAR);

        initViews();
        initViewsEventHandle();

        //  Action bar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        txtSelectTime = findViewById(R.id.txt_select_time);
        txtBarberName = findViewById(R.id.txt_barber_name);
        relSelectBarber = findViewById(R.id.rel_barber_select);
        calendarView = findViewById(R.id.calendar_view);
        toolbar = findViewById(R.id.action_bar);
        btnBook = findViewById(R.id.btn_book);
    }

    private void initViewsEventHandle() {
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                mYear = year;
                mMonth = month + 1;
                mDay = day;
                System.out.println(day + " - " + month + " - " + year);
            }
        });

        txtSelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                mHour = calendar.get(Calendar.HOUR);
                mMinute = calendar.get(Calendar.MINUTE);
                System.out.println(mHour +"/"+ mMinute);
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        BookingActivity.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                                txtSelectTime.setText(hour + ":" + minute);
                                txtSelectTime.setTextColor(getResources().getColor(R.color.white));
                                txtSelectTime.setBackground(getResources().getDrawable(R.drawable.rounded_corner_light_blue));
                            }
                        },
                        mHour, mMinute, true
                );
                timePickerDialog.show();
            }
        });

        relSelectBarber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectBarberDialogFragment fragment = new SelectBarberDialogFragment();
                fragment.show(getSupportFragmentManager(), null);
            }
        });

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateInput()) return;
                databaseHelper.addBooking(userId, barber.getId(), mDay, mMonth, mYear, mHour, mMinute);
                Toast.makeText(BookingActivity.this, "Booking added", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    public void onBarberClick(DialogFragment dialogFragment, StaffModel clickedBarber) {
        //System.out.println("Name: " + clickedBarber.getName());
        this.barber = clickedBarber;
        txtBarberName.setText(barber.getName());
        dialogFragment.dismiss();
    }

    private boolean validateInput() {
        if (txtSelectTime.getText().toString().equals("Click here")) {
            Toast.makeText(this, "Time is not set.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (txtBarberName.getText().toString().equals("Click here")) {
            Toast.makeText(this, "Select your barber please.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}