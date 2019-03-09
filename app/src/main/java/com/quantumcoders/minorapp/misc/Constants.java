package com.quantumcoders.minorapp.misc;

public class Constants {
    public static final String NAME_REGEX="[a-zA-Z]+";    //name contains only letters
    public static final String PWD_REGEX="[a-zA-Z0-9!@#$%^&*.]{8,}";   //password consists of letter, number,atleast 8 character length
    public static final String SIGNUP_URL_CITIZEN = "http://quantumcoders.000webhostapp.com/demowork.php";
    public static final String SIGNUP_URL_AGENT = "http://quantumcoders.000webhostapp.com/demowork.php";

    public static final String CTZ_SIGN_UP_METHOD="signUpCitizen";
    public static final String AGT_SIGN_UP_METHOD="signUpAgent";
}

