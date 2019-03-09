package com.quantumcoders.minorapp.misc;

public class Constants {
    public static final String NAME_REGEX="[a-zA-Z]+";    //name contains only letters
    public static final String PWD_REGEX="[a-zA-Z0-9!@#$%^&*.]{8,}";   //password consists of letter, number,atleast 8 character length
    public static final String SERVER_URL = "http://quantumcoders.000webhostapp.com";   //doesn't includes a / at the end
    public static final String SIGNUP_URL_CITIZEN = SERVER_URL + "/signup_citizen.php";
    public static final String SIGNUP_URL_AGENT = SERVER_URL+"/signup_agent.php";

    public static final String CTZ_SIGN_UP_METHOD="signUpCitizen";
    public static final String AGT_SIGN_UP_METHOD="signUpAgent";

    public static final String CTZ_SIGN_UP_SUCCESS="CitizenSignUpSuccess";
    public static final String AGT_SIGN_UP_SUCCESS="AgentSignUpSuccess";
}

