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

public class AmbulanceAdapter extends RecyclerView.Adapter<AmbulanceAdapter.ViewHolder> {

    private List<AmbulanceService> serviceList;

    public AmbulanceAdapter(List<AmbulanceService> serviceList) {
        this.serviceList = serviceList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ambulance, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AmbulanceService service = serviceList.get(position);
        holder.tvName.setText("Service Provider: " + service.getName());
        holder.tvAddress.setText("Address: " + service.getAddress());
        holder.tvPhone.setText("Phone: " + service.getPhone());
        holder.tvEmail.setText("Email: " + service.getEmail());
        holder.tvAmbulanceCount.setText("Ambulances: " + service.getAmbulanceCount());
        holder.tvServiceHours.setText("Service Hours: " + service.getServiceHours());
        //holder.tvLocation.setText("Location: " + service.getLocation().getLatitude() + ", " + service.getLocation().getLongitude());

        holder.btnViewLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();

                // Extract location data
//                double latitude = service.getLocation().getLatitude();
//                double longitude = service.getLocation().getLongitude();

                // Start On_AmbulanceView activity with location data
                Intent intent = new Intent(context, On_AmbulanceView.class);
//                intent.putExtra("latitude", latitude);
//                intent.putExtra("longitude", longitude);
//                intent.putExtra("name", service.getName());

                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvAddress, tvPhone, tvEmail, tvAmbulanceCount, tvServiceHours, tvLocation;
        Button btnViewLocation;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName= itemView.findViewById(R.id.tvName);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvAmbulanceCount = itemView.findViewById(R.id.tvAmbulanceCount);
            tvServiceHours = itemView.findViewById(R.id.tvServiceHours);
            //tvLocation = itemView.findViewById(R.id.tvLocation);
            btnViewLocation= itemView.findViewById(R.id.btnviewlocation);

        }
    }
}
