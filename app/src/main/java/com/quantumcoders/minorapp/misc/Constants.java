package com.quantumcoders.minorapp.misc;

public interface Constants {
    String NAME_REGEX = "[a-zA-Z]+";    //name contains only letters
    String PWD_REGEX = "[a-zA-Z0-9!@#$%^&*.]{8,}";   //password consists of letter, number,atleast 8 character length

    String SESSION_FILE = "SessionFile";
    String TYPE_KEY = "Type";
    String EMAIL_ID_KEY = "Email";
    String PWD_KEY = "Password";
    String USER_ID_KEY = "UserId";
    String VERIFIED_KEY="Verified";
    String VAL_YES="YES";
    String VAL_NO="NO";
    String CITIZEN = "Citizen";
    String AGENT = "Agent";

    String TEMP_IMAGE_FILE_NAME = "tempImageFile";
    String TEMP_IMAGE_FILE_NAME_2 = "tempImageFile2";
    String COMPLT_IMAGE_PREFIX = "compltImage_";
    String RESP_IMAGE_PREFIX = "respImage_";


    /*  PARAMETERS  */
    String PARAM_COMPLT_ID = "Complain_no";
    String PARAM_GROUP_ID = "Group_id";
    String PARAM_CATEGORY = "Category";
    String PARAM_DESC = "desc";
    String PARAM_COMPLNT_NO = "COMPLNT_NO";
    String PARAM_GRP_ID = "Grp_id";
    String PARAM_REGISTERED_BY = "Registered_by";
    String PARAM_REGISTERED_ON = "Registered_on";
    String PARAM_STATUS = "Status";
    String PARAM_ADDRESS = "Address";
    String PARAM_AGT_ID = "Agent_d";
    String PARAM_CTZ_ID = "userid";

    /*  URLs  */
    String SERVER_URL = "http://quantumcoders.000webhostapp.com";   //doesn't includes a / at the end
    String SIGNUP_URL_CITIZEN = SERVER_URL + "/user_signup.php"; //demo php for citizen
    String SIGNUP_URL_AGENT = SERVER_URL + "/agent_signup.php"; //demo php for agent
    String LOGIN_URL_AGENT = SERVER_URL + "/agent_login.php";
    String LOGIN_URL_CITIZEN = SERVER_URL + "/citizen_login.php";
    String FILE_COMPLAINT_URL = SERVER_URL + "/complaint_register.php";
    String RELOAD_COMPLAINTS_CITIZEN_URL = SERVER_URL + "/citizen_complaint_list.php";
    String RELOAD_COMPLAINTS_AGENT_URL = SERVER_URL + "/agent_complaint_list.php";
    String LOAD_COMPLAINT_DETAILS_CITIZEN_URL = SERVER_URL + "/complaint_details_citizen.php";
    String LOAD_GROUP_ID_COMPLAINT_DETAILS_AGENT_URL = SERVER_URL + "/demo_GroupId_details.php";
    String LOAD_COMPLAINT_IMAGE_URL = SERVER_URL + "/complaint_image.php";
    String LOAD_RESPONSE_IMAGE_URL = SERVER_URL + "/response_image.php";
    String LOAD_COMPLAINT_DETAILS_AGENT_URL = SERVER_URL + "/demo_complaintdetailsAgent.php";
    String SEND_RESPONSE_URL = SERVER_URL + "/Add_response.php";
    String AGT_PROFILE_URL = SERVER_URL + "/agent_profile.php";
    String CTZ_PROFILE_URL = SERVER_URL + "/citizen_profile.php";
    String CTZ_EMAIL_VERIFICATION_URL = SERVER_URL + "/verified.php";


    /*  METHOD IDENTIFIERS  for AsyncTask*/
    String CTZ_SIGN_UP_METHOD = "signUpCitizen";
    String AGT_SIGN_UP_METHOD = "signUpAgent";
    String CTZ_LOGIN_METHOD = "loginCitizen";
    String AGT_LOGIN_METHOD = "loginAgent";
    String FILE_COMPLAINT_METHOD = "fileComplaint";
    String CTZ_RELOAD_COMPLAINT_LIST_METHOD = "reloadComplaintListCitizen";
    String AGT_RELOAD_COMPLAINT_LIST_METHOD = "reloadComplaintListAgent";
    String CTZ_LOAD_COMPLAINT_DETAILS = "loadComplaintDetailsCitizen";
    String AGT_LOAD_COMPLAINT_DETAILS = "loadComplaintDetailsAgent";
    String LOAD_COMPLAINT_IMAGE = "loadComplaintImage";
    String LOAD_RESPONSE_IMAGE = "loadResponseImage";
    String AGT_LOAD_GROUP_ID_COMPLAINT_DETAILS = "loadGroupIdComplaintDetailsAgent";
    String SEND_RESPONSE_METHOD = "sendResponseForComplaint";
    String AGT_PROFILE_METHOD = "loadAgentProfile";
    String CTZ_PROFILE_METHOD = "loadCitizenProfile";
    String CTZ_EMAIL_VERIFICATION_STATUS ="checkEmailVerificationStatus";


    /*  RESPONSE IDENTIFICATION KEYWORDS   */
    String CTZ_SIGN_UP_SUCCESS = "CitizenSignupSuccess";
    String AGT_SIGN_UP_SUCCESS = "AgentSignupSuccess";
    String CTZ_LOGIN_SUCCESS = "CitizenLoginSuccess";
    String AGT_LOGIN_SUCCESS = "AgentLoginSuccess";
    String NO_INTERNET = "NoInternet";
    String COMPLAINT_REG_SUCCESS = "UserComplaintRegistered";
    String COMPLAINT_REG_FAILED = "UserComplaintNotRegistered";
    String CTZ_COMPLAINT_LIST_OBTAINED = "ComplaintListObtainedCitizen";
    String AGT_COMPLAINT_LIST_OBTAINED = "ComplaintListObtainedAgent";
    String CTZ_COMPLAINT_DETAILS_OBTAINED = "ComplaintDetailsObtainedCitizen";
    String AGT_COMPLAINT_DETAILS_OBTAINED = "ComplaintDetailsObtainedAgent";
    String AGT_GROUP_ID_COMPLAINT_DETAILS_OBTAINED = "ComplaintGroupDetailsObtainedAgent";
    String COMPLAINT_IMAGE_OBTAINED = "ComplaintImageObtained";
    String AGT_PROFILE_OBTAINED = "AgentProfileObtained";
    String CTZ_PROFILE_OBTAINED = "CitizenProfileObtained";
    String RESPONSE_IMAGE_OBTAINED = "ResponseImageObtained";
    String RESPONSE_SENT = "AgentResponseAdded";
    String REQUEST_TIMEOUT = "timeout";
    String CTZ_SIGN_UP_FAILED = "CitizenSignupFailed";
    String AGT_SIGN_UP_FAILED = "AgentSignupFailed";
    String CTZ_LOGIN_FAILED = "CitizenLoginFailed";
    String AGT_LOGIN_FAILED = "AgentLoginFailed";
    String NO_SUCH_USER = "NoSuchUser";
    String INVALID_CREDENTIALS = "InvalidCredentials";
    String CTZ_EMAIL_VER_STATUS_OBTAINED ="EmailVerificationStatus";



    //activity request codes
    int PICK_IMAGE = 100;
    int REQ_RELOAD_LIST = 9988;
    int REQ_SIGNUP=8574;



    //status codes
    String STATUS_PENDING = "PENDING";
    String STATUS_WORK_IN_PROGRESS = "WORK_IN_PROGRESS";
    String STATUS_COMPLETED = "COMPLETED";


}

