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
    EditText username1, password1, address, cusname, age;
    private DatabaseHelper dataBaseHelper;
    Button signupbtn, loginreturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dataBaseHelper = new DatabaseHelper(this);

        username1 = (EditText) findViewById(R.id.editTextUserName);
        password1 = (EditText) findViewById(R.id.editTextPassWord);
        address = (EditText) findViewById(R.id.editTextAddress);
        cusname = (EditText) findViewById(R.id.editTextTextPersonName);
        age = (EditText) findViewById(R.id.editTextAge);
        signupbtn = (Button) findViewById(R.id.signupbtn2);
        loginreturn = (Button) findViewById(R.id.loginreturn);


        loginreturn.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        });
        signupbtn.setOnClickListener(v -> {
            // get user inputs
            String userId = username1.getText().toString().trim();
            String pw = password1.getText().toString().trim();
            String name = cusname.getText().toString().trim();
            String adrress = address.getText().toString().trim();
            String age1 = age.getText().toString().trim();
            // validate input
            if(userId.isEmpty() || pw.isEmpty()){
                Toast.makeText(this, "Username and password are required", Toast.LENGTH_SHORT).show();
                return;
            }

            registerUser(userId, pw, name, adrress, age1);

        });

    }

    private void registerUser(String username, String pw, String name, String address, String age) {


        CustomerModel customer = new CustomerModel( age, address, username);
        String name1 = name + "";
        String role = "Customer";
        DataModel data = new DataModel(name, role, pw, username);

        // save user to db
        if(dataBaseHelper.addUser(data)){ // user was added successfully
            // add patient's data
            if(dataBaseHelper.addCustomerInfo(customer)){
                Toast.makeText(this, "Registration was successful", Toast.LENGTH_SHORT).show();
                finish();
            }
        }else {
            Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();

        }
    }

}