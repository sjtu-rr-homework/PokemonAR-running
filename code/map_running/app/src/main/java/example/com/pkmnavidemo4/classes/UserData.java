package example.com.pkmnavidemo4.classes;

import java.util.List;

public class UserData {
    private static String userName;
    private static List<String> elfList;
    public static String getUserName(){
        return userName;
    }
    public static void setUserName(String name){
        userName=name;
    }
    public static List<String> getElfList(){
        return elfList;
    }
    public static void setElfList( List<String> list){
        elfList=list;
    }
}
