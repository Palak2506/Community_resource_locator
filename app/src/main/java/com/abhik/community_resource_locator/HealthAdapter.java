package com.abhik.community_resource_locator;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class HealthAdapter extends RecyclerView.Adapter<HealthAdapter.ViewHolder> {

    private List<HealthService> serviceList3;

    public HealthAdapter(List<HealthService> serviceList) {
        this.serviceList3 = serviceList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_healthservice
                , parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HealthService service = serviceList3.get(position);
        holder.tvName.setText("Service Provider: " + service.getName());
        holder.tvAddress.setText("Address: " + service.getAddress());
        holder.tvPhone.setText("Phone: " + service.getPhone());
        holder.tvEmail.setText("Email: " + service.getEmail());
        //holder.tvvehicleCount.setText("Fire vehicles: " + service.getvehiclecount());
        holder.tvServiceHours.setText("Service Hours: " + service.getServiceHours());
        //holder.tvLocation.setText("Location: " + service.getLocation().getLatitude() + ", " + service.getLocation().getLongitude());

        holder.btnViewLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();

                Intent intent = new Intent(context, On_FireServiceView.class);

                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return serviceList3.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvAddress, tvPhone, tvEmail, tvvehicleCount, tvServiceHours, tvLocation;
        Button btnViewLocation;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName= itemView.findViewById(R.id.tvName);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            //tvvehicleCount = itemView.findViewById(R.id.tvvehicleCount);
            tvServiceHours = itemView.findViewById(R.id.tvServiceHours);
            //tvLocation = itemView.findViewById(R.id.tvLocation);
            btnViewLocation= itemView.findViewById(R.id.btnviewlocation);

        }
    }
}
