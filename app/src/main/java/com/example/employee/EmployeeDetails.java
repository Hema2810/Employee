package com.example.employee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.content.Intent;
import android.content.IntentFilter;

import com.squareup.picasso.Picasso;

public class EmployeeDetails extends AppCompatActivity {

    EmployeeDatabase mDb;
    String avatarString;
    public static final int PICK_IMAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);
        Button bAdd = findViewById(R.id.add);
        final EditText name = findViewById(R.id.tv_EmployeeName);
        final EditText age = findViewById(R.id.tv_EmployeeAge);
        final ImageView avatar = findViewById(R.id.iv_EmployeeAvatar);


         String nameString =  name.getText().toString();
         String ageString = age.getText().toString();

         mDb = EmployeeDatabase.getInstance(getApplicationContext());

         avatar.setOnClickListener(new View.OnClickListener() {


             @Override
             public void onClick(View view) {

                 Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                 getIntent.setType("image/*");

                 Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                 pickIntent.setType("image/*");

                 Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                 chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});
                 startActivityForResult(chooserIntent, PICK_IMAGE);

             }
        });

        bAdd.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameString =  name.getText().toString();
                String ageString = (age.getText().toString());
                int empAge = Integer.parseInt(ageString);
                final Employee emp = new Employee(nameString, empAge,avatarString);
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        mDb.employeeDao().insertEmployee(emp);
                        finish();
                    }
                });

            }
        }));

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        final ImageView avatar = findViewById(R.id.iv_EmployeeAvatar);

        if (resultCode == PICK_IMAGE) {
            avatarString=data.getDataString();
            Picasso.with(avatar.getContext())
                    .load(avatarString)
                    .placeholder(R.drawable.download)
                    .error(R.drawable.download)
                    .into(avatar);
            //TODO: action

        }
    }

}
