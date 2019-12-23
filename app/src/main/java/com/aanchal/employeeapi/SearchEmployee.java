package com.aanchal.employeeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aanchal.employeeapi.API.EmployeeAPI;
import com.aanchal.employeeapi.Model.Employee;
import com.aanchal.employeeapi.URL.URL;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchEmployee extends AppCompatActivity {
    EditText etName;
    Button btnSearch;
    TextView tvData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_employee);
        tvData=findViewById(R.id.tvData);
        etName=findViewById(R.id.etName);
        btnSearch=findViewById(R.id.btnSearch);


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    loadData();

            }
        });


    }
    private  void loadData(){

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        EmployeeAPI employeeAPI =retrofit.create(EmployeeAPI.class);

        Call<Employee> listCall=employeeAPI.getEmployeeByID(Integer.parseInt(etName.getText().toString()));

        listCall.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                Toast.makeText(SearchEmployee.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                String content="";
                content +="Id: "+response.body().getId()+"\n";
                content +="Name: "+response.body().getEmployee_name()+"\n";
                content +="Age: "+response.body().getEmployee_age()+"\n";
                content +="salary: "+response.body().getEmployee_salary()+"\n";
                tvData.setText(content);

            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(SearchEmployee.this, "error", Toast.LENGTH_SHORT).show();

            }
        });


    }
}
