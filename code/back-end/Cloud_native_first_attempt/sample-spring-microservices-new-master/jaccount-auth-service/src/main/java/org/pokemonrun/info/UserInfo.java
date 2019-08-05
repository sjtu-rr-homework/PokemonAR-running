package org.pokemonrun.info;

public class UserInfo {
    private String idToken;
    private String accessToken;
    private String refreshToken;
    private String username;
    public UserInfo(String idToken, String accessToken, String refreshToken, String username){
        this.idToken = idToken;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.username = username;
    }
}
