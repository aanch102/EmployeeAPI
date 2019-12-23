package com.aanchal.employeeapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
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

public class ShowEmployee extends AppCompatActivity {
    private  EmployeeAdapter employeeAdapter;
    private RecyclerView recyclerView;
    //TextView tvOutput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_employee);



        Retrofit retrofit=new Retrofit.Builder()
                            .baseUrl(URL.base_url)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();


        EmployeeAPI employeeAPI =retrofit.create(EmployeeAPI.class);
        Call<List<Employee>>listCall=employeeAPI.getAllEmployee();

        //asynchronous call

        listCall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(ShowEmployee.this, "Error code"+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                LoadDataList(response.body());
                /*List<Employee>employeeList=response.body();
                for (Employee emp:employeeList) {
                    String data = "";
                    data += "Name is: " + emp.getEmployee_name() + "\n";
                    data += "Salary is: " + emp.getEmployee_salary() + "\n";

                    data += "--------------------------------------------" + "\n";
                    tvOutput.append(data);

                }*/

            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {

                Log.d("Msg","onFailure"+t.getLocalizedMessage());
                Toast.makeText(ShowEmployee.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void LoadDataList(List<Employee>employeeList){
        recyclerView=findViewById(R.id.recyclerview);
        employeeAdapter=new EmployeeAdapter(employeeList);


        //using a linear layout manager
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(ShowEmployee.this);
        recyclerView.setLayoutManager(layoutManager);

        //set adapter to the recyclerview
        recyclerView.setAdapter(employeeAdapter);
    }
}
