package com.abhik.community_resource_locator;

public class AmbulanceService {

    private String address, ambulanceCount, email, phone, serviceHours;
    private Location location;  // Nested object

    public AmbulanceService() {}  // Empty constructor for Firebase

    public AmbulanceService(String address, String ambulanceCount, String email, Location location, String phone, String serviceHours) {
        this.address = address;
        this.ambulanceCount = ambulanceCount;
        this.email = email;
        this.location = location;
        this.phone = phone;
        this.serviceHours = serviceHours;
    }

    public String getAddress() { return address; }
    public String getAmbulanceCount() { return ambulanceCount; }
    public String getEmail() { return email; }
    public Location getLocation() { return location; }
    public String getPhone() { return phone; }
    public String getServiceHours() { return serviceHours; }
}
