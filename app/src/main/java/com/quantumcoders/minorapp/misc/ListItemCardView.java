package com.quantumcoders.minorapp.misc;

public class ListItemCardView  {

    private String complaintNo; //complaintNo is the complaintId
    private String registeredOn;
    private String category;
    private String description;

    public ListItemCardView(String complaintNo, String registeredOn, String category, String description) {
        this.complaintNo = complaintNo;
        this.registeredOn = registeredOn;
        this.category = category;
        this.description = description;
    }

    public String getComplaintNo() { //complaintNo is the complaintId AND getComplaintNo is the getComplaintId
        return complaintNo;
    }

    public String getRegisteredOn() {
        return registeredOn;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }
}
