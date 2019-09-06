package org.pokemonrun.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cover")
public class Cover {
    @Id
    public String momentid;//generate automatically

    public String username;

    public byte[] pic;

    public Cover(){}
    public Cover(String username, byte[] pic)
    {

        this.username=username;
        this.pic=pic;

    }
}
