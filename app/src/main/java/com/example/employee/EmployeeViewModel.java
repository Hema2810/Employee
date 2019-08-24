package com.example.employee;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class EmployeeViewModel  extends AndroidViewModel {

        private LiveData<List<Employee>> employees;

        public EmployeeViewModel(@NonNull Application application) {
            super(application);
            EmployeeDatabase mDb = EmployeeDatabase.getInstance(this.getApplication());
            employees = mDb.employeeDao().loadEmployees();
        }

        public LiveData<List<Employee>> getEmployees() {
            return employees;
        }
}
