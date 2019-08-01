package org.pokemonrun.info;

public class ValidationInfo {
    private String idToken;
    private String accessToken;
    private String refreshToken;
    public ValidationInfo(String idToken, String accessToken, String refreshToken){
        this.idToken = idToken;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getIdToken() {
        return idToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
