package com.quantumcoders.minorapp.adapters;

public class ListItemComplaint {

    private String complaintNo; //complaintNo is the complaintId
    private String registeredOn;
    private String category;
    private String description;
    private String status;

    public ListItemComplaint(String complaintNo, String registeredOn, String category, String description, String status) {
        this.complaintNo = complaintNo;
        this.registeredOn = registeredOn;
        this.category = category;
        this.description = description.replace("<br/>","\n");
        this.status=status;
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

    public String getStatus(){ return  status; }
}
