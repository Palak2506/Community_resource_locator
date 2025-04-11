package com.abhik.community_resource_locator;

public class HealthService {

    private String name,address, vehicleCount, email, phone, serviceHours;
    private Location location;  // Nested object

    public HealthService() {}  // Empty constructor for Firebase

    public HealthService(String name,String address, String vehicleCount, String email, Location location, String phone, String serviceHours) {
        this.name= name;
        this.address = address;
        //this.vehicleCount = vehicleCount;
        this.email = email;
        this.location = location;
        this.phone = phone;
        this.serviceHours = serviceHours;
    }

    public String getName() { return name; }
    public String getAddress() { return address; }
    //public String getvehiclecount() { return vehicleCount; }
    public String getEmail() { return email; }
    public Location getLocation() { return location; }
    public String getPhone() { return phone; }
    public String getServiceHours() { return serviceHours; }
}
