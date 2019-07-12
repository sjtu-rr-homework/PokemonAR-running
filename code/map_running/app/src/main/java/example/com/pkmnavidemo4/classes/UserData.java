package example.com.pkmnavidemo4.classes;

public class UserData {
    private static String userName;
    public static String getUserName(){
        return userName;
    }
    public static void setUserName(String name){
        userName=name;
    }
}
