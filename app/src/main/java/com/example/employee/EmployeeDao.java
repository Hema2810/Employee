package com.example.employee;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EmployeeDao {
    @Query("SELECT * FROM Employee")
    LiveData<List<Employee>> loadEmployees();

   /* @Query("SELECT * FROM MovieInfo WHERE id LIKE :id")
    MovieInfo findByMovieId(int id);*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertEmployee(Employee employeeList);

    @Delete
    void deleteEmployee(Employee employeeList);
}
