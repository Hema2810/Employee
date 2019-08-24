package com.example.employee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private static final float IMAGEVIEW_HEIGHT_RATIO = (float) 1.5;

    private ArrayList<Employee> mEmployeeList;
    private Context mContext;

    public EmployeeAdapter(Context context) {
        mContext = context;}


    @Override
    public void onBindViewHolder(final EmployeeViewHolder holder, int position) {

        final int ePosition = position;


        Picasso.with(holder.mAvatarImageView.getContext())
                .load(mEmployeeList.get(position).getAvatar())
                .placeholder(R.drawable.download)
                .error(R.drawable.download)
                .into(holder.mAvatarImageView);

        //holder.mAvatarImageView.setColorFilter(android.R.color.black);

        holder.mNameTextView.setText(mEmployeeList.get(position).getName());
        int i = mEmployeeList.get(position).getAge();
        holder.mAgeTextView.setText(""+ mEmployeeList.get(position).getAge());

    }



    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context mContext = parent.getContext();
        int layoutIdForEmployee = R.layout.employee_detail;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        boolean attachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForEmployee, parent, false);
        return new EmployeeViewHolder(view);
    }


    public void setEmployees(ArrayList<Employee> employeeList) {
        mEmployeeList = employeeList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mEmployeeList.size();
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder {

        final ImageView mAvatarImageView;
        final TextView mNameTextView;
        final TextView mAgeTextView;


        EmployeeViewHolder(View itemView) {
            super(itemView);
            mAvatarImageView = itemView.findViewById(R.id.iv_EmployeeAvatar);
            mNameTextView = itemView.findViewById(R.id.tv_EmployeeName);
            mAgeTextView = itemView.findViewById(R.id.tv_EmployeeAge);

        }


    }
}



