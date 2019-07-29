package org.pokemonrun.info;

public class MomentInfo {
    public String text;

    public String timestamp;

    public String username;

    public byte[] picture;

    public MomentInfo(String text, String timestamp, String username, byte[] picture)
    {
        this.text=text;
        this.timestamp=timestamp;
        this.username=username;
        this.picture=picture;
    }
}
