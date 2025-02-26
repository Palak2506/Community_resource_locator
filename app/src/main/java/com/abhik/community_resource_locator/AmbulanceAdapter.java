package com.abhik.community_resource_locator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        holder.tvAddress.setText("Address: " + service.getAddress());
        holder.tvPhone.setText("Phone: " + service.getPhone());
        holder.tvEmail.setText("Email: " + service.getEmail());
        holder.tvAmbulanceCount.setText("Ambulances: " + service.getAmbulanceCount());
        holder.tvServiceHours.setText("Service Hours: " + service.getServiceHours());
        holder.tvLocation.setText("Location: " + service.getLocation().getLatitude() + ", " + service.getLocation().getLongitude());
    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvAddress, tvPhone, tvEmail, tvAmbulanceCount, tvServiceHours, tvLocation;

        public ViewHolder(View itemView) {
            super(itemView);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvAmbulanceCount = itemView.findViewById(R.id.tvAmbulanceCount);
            tvServiceHours = itemView.findViewById(R.id.tvServiceHours);
            tvLocation = itemView.findViewById(R.id.tvLocation);
        }
    }
}
