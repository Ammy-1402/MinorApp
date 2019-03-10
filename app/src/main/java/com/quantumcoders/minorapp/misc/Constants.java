package com.quantumcoders.minorapp.misc;

public interface Constants {
    public static final String NAME_REGEX="[a-zA-Z]+";    //name contains only letters
    public static final String PWD_REGEX="[a-zA-Z0-9!@#$%^&*.]{8,}";   //password consists of letter, number,atleast 8 character length

    public static final String SESSION_FILE = "SessionFile";
    public static final String TYPE_KEY="Type";
    public static final String EMAIL_ID_KEY="Email";
    public static final String PWD_KEY="Password";
    public static final String CITIZEN="Citizen";
    public static final String AGENT="Agent";

    public static final String SERVER_URL = "http://quantumcoders.000webhostapp.com";   //doesn't includes a / at the end
    public static final String SIGNUP_URL_CITIZEN = SERVER_URL + "/citizen_signup.php"; //demo php for citizen
    public static final String SIGNUP_URL_AGENT = SERVER_URL+"/agent_signup.php"; //demo php for agent
    public static final String LOGIN_URL_AGENT = SERVER_URL+"/agent_login.php";
    public static final String LOGIN_URL_CITIZEN = SERVER_URL+"/citizen_login.php";

    public static final String CTZ_SIGN_UP_METHOD="signUpCitizen";
    public static final String AGT_SIGN_UP_METHOD="signUpAgent";
    public static final String CTZ_LOGIN_METHOD="loginCitizen";
    public static final String AGT_LOGIN_METHOD="loginAgent";

    public static final String CTZ_SIGN_UP_SUCCESS="CitizenSignupSuccess";
    public static final String AGT_SIGN_UP_SUCCESS="AgentSignupSuccess";
    public static final String CTZ_LOGIN_SUCCESS="CitizenLoginSuccess";
    public static final String AGT_LOGIN_SUCCESS="AgentLoginSuccess";


    public static final String CTZ_SIGN_UP_FAILED="CitizenSignupFailed";
    public static final String AGT_SIGN_UP_FAILED="AgentSignupFailed";
    public static final String CTZ_LOGIN_FAILED="CitizenLoginFailed";
    public static final String AGT_LOGIN_FAILED="AgentLoginFailed";
}

