package org.pokemonrun.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "mount")
public class Moment {
    @Id
    public String momentid;

    public String text;

    public String timestamp;

    public String username;

    public List<byte[]> picture;

    public Moment(){}
    public Moment(String text, String timestamp, String username, List<byte[]> picture)
    {
        this.text=text;
        this.timestamp=timestamp;
        this.username=username;
        this.picture=picture;
    }
}
