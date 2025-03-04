package org.example;

public class Guest {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String rsvpStatus;
    private boolean plusOne;
    private String notes;

    // Constructor
    public Guest(int id, String name, String email, String phone, String rsvpStatus, boolean plusOne, String notes) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.rsvpStatus = rsvpStatus;
        this.plusOne = plusOne;
        this.notes = notes;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getRsvpStatus() {
        return rsvpStatus;
    }

    public boolean isPlusOne() {
        return plusOne;
    }

    public String getNotes() {
        return notes;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRsvpStatus(String rsvpStatus) {
        this.rsvpStatus = rsvpStatus;
    }

    public void setPlusOne(boolean plusOne) {
        this.plusOne = plusOne;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
