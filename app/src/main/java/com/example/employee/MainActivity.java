package com.example.employee;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinearLayoutManager layoutManager;
    private RecyclerView mRecyclerView;
    final EmployeeAdapter mAdapter = new EmployeeAdapter(this);
    private ArrayList<Employee> mEmployees = new ArrayList<>();
    EmployeeDatabase mDb;
    //Employee one = new Employee("xyz",22, "abc");
   // Employee two = new Employee("def",33, "abc");

    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.rv_employees);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mDb = EmployeeDatabase.getInstance(getApplicationContext());
        fab = findViewById(R.id.floating_action_button);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EmployeeDetails.class);

                startActivity(intent);
            }
        });
       // mEmployees.add(one);
       // mEmployees.add(two);
        /*AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mDb.employeeDao().insertMovie(one);
                mDb.employeeDao().insertMovie(two);

                }
            });*/
       // mAdapter.setEmployees(mEmployees);
        //mRecyclerView.setAdapter(mAdapter);

        EmployeeViewModel viewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);
        viewModel.getEmployees().observe(this, new Observer<List<Employee>>() {



            @Override
            public void onChanged(@Nullable List<Employee> employees) {
                if (employees != null) {
                    mEmployees.clear();
                    mEmployees.addAll(employees);

                }
                mAdapter.setEmployees(mEmployees);
                mRecyclerView.setAdapter(mAdapter);
            }
        });

    }
}
