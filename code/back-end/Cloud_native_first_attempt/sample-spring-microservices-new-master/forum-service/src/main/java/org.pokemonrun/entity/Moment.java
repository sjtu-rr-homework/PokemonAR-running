package org.pokemonrun.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "mount")
public class Moment {
    @Id
    public String momentid;

    public String text;

    public long timestamp;

    public String username;

    public byte[] pic1;
    public byte[] pic2;
    public byte[] pic3;
    public byte[] pic4;
    public byte[] pic5;
    public byte[] pic6;
    public byte[] pic7;
    public byte[] pic8;
    public byte[] pic9;

    public Moment(){}
    public Moment(String text, long timestamp, String username, byte[] pic1,byte[] pic2,byte[] pic3,byte[] pic4,byte[] pic5,byte[] pic6,byte[] pic7,byte[] pic8,byte[] pic9)
    {
        this.text=text;
        this.timestamp=timestamp;
        this.username=username;
        this.pic1=pic1;
        this.pic2=pic2;
        this.pic3=pic3;
        this.pic4=pic4;
        this.pic5=pic5;
        this.pic6=pic6;
        this.pic7=pic7;
        this.pic8=pic8;
        this.pic9=pic9;
    }
}
