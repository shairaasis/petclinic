package com.example.proj.model;

import java.sql.Date;

public class Pet {
    public static Pet petBean;
    private int petId;
    private int ownerId;
    private int healthRecordId;
    private String petName;
    private String dateOfBirth;
    private String breed;
    private String coatColor;

    public Pet() {}

    public Pet(int petId, int ownerId,int healthRecordId, String petName, String dateOfBirth, String breed, String coatColor) {
        this.petId = petId;
        this.ownerId = ownerId;
        this.healthRecordId = healthRecordId;
        this.petName = petName;
        this.dateOfBirth = dateOfBirth;
        this.breed = breed;
        this.coatColor = coatColor;
    }

    public Pet getPetBean() {
        return petBean;
    }

    public void setPetBean(Pet pet) {
        Pet.petBean = pet;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getHealthRecordId() {
        return healthRecordId;
    }

    public void setHealthRecordId(int healthRecordId) {
        this.healthRecordId = healthRecordId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getCoatColor() {
        return coatColor;
    }

    public void setCoatColor(String coatColor) {
        this.coatColor = coatColor;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}
