package com.bachelorshelter.nasir.medicaladviser.app;

public class AppConfig {
    public static String localhost = "http://vitualmedical.pythonanywhere.com/api";


    public static String URL_REGISTER = localhost+"/register";
    public static String URL_LOGIN = localhost+"/login";



    public static String GET_ADDRESS = localhost+"/getaddress";
    public static String GET_SEARCH_RESULT = localhost+"/tests?format=json";
    public static String GET_TIPS = localhost+"/gettips";
    public static String GET_DISEASE = localhost+"/getdisease";
    public static String GET_TIPS_DESC = localhost+"/gettipsdesc";
    public static String GET_DISEASE_DESC = localhost+"/getdiseasedesc";
}