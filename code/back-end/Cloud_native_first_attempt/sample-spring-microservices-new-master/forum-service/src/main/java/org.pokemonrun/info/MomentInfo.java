package org.pokemonrun.info;

import java.util.List;

public class MomentInfo {
    public String text;

    public String timestamp;

    public String username;

    public List<byte[]> picture;

    public MomentInfo(String text, String timestamp, String username, List<byte[]> picture)
    {
        this.text=text;
        this.timestamp=timestamp;
        this.username=username;
        this.picture=picture;
    }
}
