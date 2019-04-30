package com.quantumcoders.minorapp.adapters;

public class AgentListItemComplaint {
    private String groupId;
    private String noOfComplaints;
    private String category;
    private String location;
    private String agentStatus;

    public AgentListItemComplaint(String groupId, String noOfComplaints, String category, String location, String agentStatus) {
        this.groupId = groupId;
        this.noOfComplaints = noOfComplaints;
        this.category = category;
        this.location = location;
        this.agentStatus = agentStatus;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getNoOfComplaints() {
        return noOfComplaints;
    }

    public String getCategory() {
        return category;
    }

    public String getLocation() {
        return location;
    }

    public String getAgentStatus() {
        return agentStatus;
    }
}
