package com.example.barbercornerproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.barbercornerproj.model.CustomerModel;
import com.example.barbercornerproj.model.DataModel;

public class RegisterActivity extends AppCompatActivity {

    EditText edtUserName, edtPassword, edtAddress, edtName, edtAge;
    private DatabaseHelper databaseHelper;
    Button btnSignUp, btnLoginReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        databaseHelper = new DatabaseHelper(this);

        edtUserName = findViewById(R.id.editTextUserName);
        edtPassword = findViewById(R.id.editTextPassWord);
        edtAddress = findViewById(R.id.editTextAddress);
        edtName = findViewById(R.id.editTextTextPersonName);
        edtAge = findViewById(R.id.editTextAge);
        btnSignUp = findViewById(R.id.signupbtn2);
        btnLoginReturn = findViewById(R.id.loginreturn);

        btnLoginReturn.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        });
        btnSignUp.setOnClickListener(v -> {
            // get user inputs
            String userName = edtUserName.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();
            String name = edtName.getText().toString().trim();
            String address = edtAddress.getText().toString().trim();
            String age = edtAge.getText().toString().trim();

            validateInput(userName, password, name);

            registerUser(userName, password, name, address, age);
        });
    }

    private void registerUser(String userName, String password, String name, String address, String age) {

        CustomerModel customer = new CustomerModel(age, address, userName);
        String role = "Customer";
        DataModel data = new DataModel(userName, password, name, role);

        // save user to db
        if(databaseHelper.addUser(data)){ // user was added successfully
            // add patient's data
            if(databaseHelper.addCustomerInfo(customer)){
                Toast.makeText(this, "Registration was successful", Toast.LENGTH_SHORT).show();
                finish();
            }
        } else {
            Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateInput(String userName, String password, String name) {
        if (userName.isEmpty()) {
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.isEmpty()) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (name.isEmpty()) {
            Toast.makeText(this, "Name is required", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (userName.trim().contains(" ")) {
            Toast.makeText(this, "Username must not contain spaces.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}