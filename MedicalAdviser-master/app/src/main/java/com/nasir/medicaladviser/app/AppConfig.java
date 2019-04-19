package com.nasir.medicaladviser.app;

/**
 * Created by Tanvir on 5/18/2017.
 */

public class AppConfig {
    // Server user login url
    public static String localhost = "http://vitualmedical.pythonanywhere.com/api";

    //public static String localhost = "http://34.209.74.130/Mess_Manager/mess_manager_android_api/v1/index.php";

    //public static String URL_GET_ALL_PRODUCT_INFO = "http://34.209.74.130/Mess_Manager/mess_manager_android_api/v1/getallproductsinfo.php";


    public static String URL_REGISTER = localhost+"/register";
    public static String URL_LOGIN = localhost+"/login";
    public static String GET_SEARCH_RESULT = localhost+"/tests?format=json";
    public static String GET_TIPS = localhost+"/gettips";
    public static String GET_DISEASE = localhost+"/getdisease";
    public static String GET_TIPS_DESC = localhost+"/gettipsdesc";
    public static String GET_DISEASE_DESC = localhost+"/getdiseasedesc";

}