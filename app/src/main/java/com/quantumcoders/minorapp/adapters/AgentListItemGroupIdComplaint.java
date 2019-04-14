package com.quantumcoders.minorapp.adapters;

public class AgentListItemGroupIdComplaint {

    public String groupIdComplaintNo;
    public String registeredBy;
    public String registeredOn;

    public AgentListItemGroupIdComplaint(String groupIdComplaintNo, String registeredBy, String registeredOn) {
        this.groupIdComplaintNo = groupIdComplaintNo;
        this.registeredBy = registeredBy;
        this.registeredOn = registeredOn;
    }

    public String getGroupIdComplaintNo() {
        return groupIdComplaintNo;
    }

    public String getRegisteredBy() {
        return registeredBy;
    }

    public String getRegisteredOn() {
        return registeredOn;
    }

}
