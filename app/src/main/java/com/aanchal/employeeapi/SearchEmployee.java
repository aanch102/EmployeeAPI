package com.aanchal.employeeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class SearchEmployee extends AppCompatActivity {
    EditText etName;
    Button btnSearch;
    ListView lstEmployee;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_employee);

        etName=findViewById(R.id.etName);
        btnSearch=findViewById(R.id.btnSearch);
        lstEmployee=findViewById(R.id.lstEmployee);
    }
}
