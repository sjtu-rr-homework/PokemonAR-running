package org.pokemonrun.petservice.entity;

import javax.persistence.*;

@Entity
public class Pet {
    private long ownerUid;
    private long petId;
    private long typeId;
    private int level;
    private int exp;
    private int stamina;
    private int offense;
    private int defense;

    public Pet(){
        // empty
    }

    @Basic
    @Column(name = "ownerUid")
    public long getOwnerUid() {
        return ownerUid;
    }

    public void setOwnerUid(long ownerUid) {
        this.ownerUid = ownerUid;
    }

    @Id
    @Column(name = "petId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getPetId() {
        return petId;
    }

    public void setPetId(long petId) {
        this.petId = petId;
    }

    @Basic
    @Column(name = "typeId")
    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    @Basic
    @Column(name = "level")
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Basic
    @Column(name = "exp")
    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    @Basic
    @Column(name = "stamina")
    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    @Basic
    @Column(name = "offense")
    public int getOffense() {
        return offense;
    }

    public void setOffense(int offense) {
        this.offense = offense;
    }

    @Basic
    @Column(name = "defense")
    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
}
