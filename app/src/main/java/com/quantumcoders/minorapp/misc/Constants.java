package com.quantumcoders.minorapp.misc;

public interface Constants {
    public static final String NAME_REGEX="[a-zA-Z]+";    //name contains only letters
    public static final String PWD_REGEX="[a-zA-Z0-9!@#$%^&*.]{8,}";   //password consists of letter, number,atleast 8 character length

    public static final String SESSION_FILE = "SessionFile";
    public static final String TYPE_KEY="Type";
    public static final String EMAIL_ID_KEY="Email";
    public static final String PWD_KEY="Password";
    public static final String USER_ID_KEY = "UserId";
    public static final String CITIZEN="Citizen";
    public static final String AGENT="Agent";

    public static final String SERVER_URL = "http://quantumcoders.000webhostapp.com";   //doesn't includes a / at the end
    public static final String SIGNUP_URL_CITIZEN = SERVER_URL + "/citizen_signup.php"; //demo php for citizen
    public static final String SIGNUP_URL_AGENT = SERVER_URL+"/agent_signup.php"; //demo php for agent
    public static final String LOGIN_URL_AGENT = SERVER_URL+"/agent_login.php";
    public static final String LOGIN_URL_CITIZEN = SERVER_URL+"/citizen_login.php";
    public static final String FILE_COMPLAINT_URL = SERVER_URL+"/demo_complaint.php";
    public static final String RELOAD_COMPLAINTS_URL = SERVER_URL+"/demo_complaint_list_citizen.php";
    public static final String RELOAD_COMPLAINTS_AGENT_URL = SERVER_URL+"/demo_complaint_list_agent.php";

    public static final String CTZ_SIGN_UP_METHOD="signUpCitizen";
    public static final String AGT_SIGN_UP_METHOD="signUpAgent";
    public static final String CTZ_LOGIN_METHOD="loginCitizen";
    public static final String AGT_LOGIN_METHOD="loginAgent";
    public static final String FILE_COMPLAINT_METHOD="fileComplaint";
    public static final String CTZ_RELOAD_COMPLAINT_LIST_METHOD = "reloadComplaintListCitizen";
    public static final String AGT_RELOAD_COMPLAINT_LIST_METHOD = "reloadComplaintListAgent";

    public static final String CTZ_SIGN_UP_SUCCESS="CitizenSignupSuccess";
    public static final String AGT_SIGN_UP_SUCCESS="AgentSignupSuccess";
    public static final String CTZ_LOGIN_SUCCESS="CitizenLoginSuccess";
    public static final String AGT_LOGIN_SUCCESS="AgentLoginSuccess";
    public static final String NO_INTERNET="NoInternet";
    public static final String COMPLAINT_REG_SUCCESS="ComplaintRegSuccess";
    public static final String CTZ_COMPLAINT_LIST_OBTAINED ="ComplaintListObtainedCitizen";
    public static final String AGT_COMPLAINT_LIST_OBTAINED ="ComplaintListObtainedAgent";

    public static final String CTZ_SIGN_UP_FAILED="CitizenSignupFailed";
    public static final String AGT_SIGN_UP_FAILED="AgentSignupFailed";
    public static final String CTZ_LOGIN_FAILED="CitizenLoginFailed";
    public static final String AGT_LOGIN_FAILED="AgentLoginFailed";

    public static final String NO_SUCH_USER="NoSuchUser";
    public static final String INVALID_CREDENTIALS="InvalidCredentials";

    //
    public static final int PICK_IMAGE=100;

    public static final String STATUS_PENDING="PENDING";
    public static final String STATUS_WORK_IN_PROGRESS="WORK_IN_PROGRESS";
    public static final String STATUS_COMPLETED="COMPLETED";


}

