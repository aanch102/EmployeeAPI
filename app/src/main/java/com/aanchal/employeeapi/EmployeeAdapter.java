package com.aanchal.employeeapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aanchal.employeeapi.Model.Employee;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.employeeViewHolder> {

    List<Employee> employeeList;

    public EmployeeAdapter( List<Employee> employeeList) {
        this.employeeList=employeeList;
    }

    @NonNull
    @Override
    public EmployeeAdapter.employeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.showallemployee, parent, false);
        return new employeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.employeeViewHolder holder, int position) {

        Employee employee=employeeList.get(position);
        holder.tvid.setText(Integer.toString(employee.getId()));
        holder.tvname.setText(employee.getEmployee_name());
        holder.tvage.setText(Integer.toString(employee.getEmployee_age()));
        holder.tvsalary.setText(employee.getEmployee_salary());
        holder.tvprofile.setText(employee.getProfile_image());


    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }


    public class employeeViewHolder extends  RecyclerView.ViewHolder{
        TextView tvid,tvname,tvage,tvsalary,tvprofile;

        public employeeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvid=itemView.findViewById(R.id.tvid);
            tvname=itemView.findViewById(R.id.tvname);
            tvage=itemView.findViewById(R.id.tvage);
            tvsalary=itemView.findViewById(R.id.tvsalary);
            tvprofile=itemView.findViewById(R.id.tvprofilepic);

        }
    }
}
