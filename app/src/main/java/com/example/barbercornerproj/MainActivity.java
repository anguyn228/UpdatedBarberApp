package com.example.barbercornerproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //  For debug
    private static final String LOG_TAG = "SIGN IN";

    Button btnRegister, btnSignIn;
    EditText edtUserName, edtPassword;
    //private ArrayList<DataModel> users;
    private String USERID = "", TITLE = "", NAME = "";
    private DatabaseHelper databaseHelper;

    private void startTestActivity() {
        Intent intent = new Intent(this, BookingActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        startTestActivity();

        edtUserName = findViewById(R.id.username);
        edtPassword = findViewById(R.id.password);
        btnSignIn = findViewById(R.id.loginbtn1);
        btnRegister = findViewById(R.id.signupbtnreturn);

        //users = databaseHelper.retrieveAllUsers();
        btnRegister.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, RegisterActivity.class));
        });

        btnSignIn.setOnClickListener(v ->
        {
            String userName = edtUserName.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            // validate credentials
            if (userName.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Username and password required", Toast.LENGTH_SHORT).show();
                return;
            }

            if (userName.equalsIgnoreCase("admin") && password.equals("admin")) {
                // default user
                startActivity(new Intent(MainActivity.this, UserDashBoard.class));
                finish();
            } else {
                signIn(userName, password);
            }
        });
    }

    /*private void signIn(String userN, String passW) {

        boolean status = false;
        // find if this user exists
        for (DataModel d : users) {
            if (d.getUserId().equalsIgnoreCase(userN) && d.getPassword().equals(passW)) {

                String role = d.getTitle();
                USERID = d.getUserId();
                NAME = d.getName();
                signInByRole(role);
                status = true;
                break;
            }
        }

    }*/

    private boolean signIn(String userName, String userPassword) {
        Log.i(LOG_TAG, "user input username: " + userName);
        Log.i(LOG_TAG, "user input password: " + userPassword);

        Cursor user = databaseHelper.getUserByUserName(userName);
        if (user.getCount() == 0) {
            Toast.makeText(this, "User does not exists.", Toast.LENGTH_SHORT).show();
            return false;
        }

        user.moveToNext();
        int passwordColumnIndex = user.getColumnIndex(DatabaseHelper.COLUMN_PASSWORD);
        String passwordFromDB = user.getString(passwordColumnIndex);
        Log.i(LOG_TAG, "user password from database: " + passwordFromDB);
        if (!passwordFromDB.equals(userPassword)) {
            Toast.makeText(this, "Wrong password.", Toast.LENGTH_SHORT).show();
            return false;
        }

        int roleColumnIndex = user.getColumnIndex(DatabaseHelper.COLUMN_TITLE);
        String roleFromDB = user.getString(roleColumnIndex);
        Log.i(LOG_TAG, "role from database: " + roleFromDB);
        signInByRole(roleFromDB);
        return true;
    }

    private void signInByRole(String role) {
        switch (role.toLowerCase()) {
            case "admin":
                startActivity(new Intent(MainActivity.this, AdminDashBoard.class)
                        .putExtra("userid", USERID));
                finish();
                break;
            case "customer":
                 startActivity(new Intent(MainActivity.this, UserDashBoard.class)
                        .putExtra("userid", USERID)
                        .putExtra("name", NAME));
                finish();
                break;
            case "barber":
                startActivity(new Intent(MainActivity.this, StaffDashBoard.class)
                        .putExtra("userid", USERID));
                finish();
                break;

            default:
                break;
        }
    }
}