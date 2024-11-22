package model.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class User230 {
    private String id;
    private String fullName;
    private Date dateOfBirth;
    private String email;
    private String password;
    private String address;
    private boolean gender;
    private String phoneNumber;
    private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    public User230(
            String id, 
            String fullName, 
            String dateOfBirth, 
            String email, 
            String password, 
            String address, 
            boolean gender
    ) {
        this.id = id;
        this.fullName = fullName;
        try {
            this.dateOfBirth = format.parse(dateOfBirth);
        } catch (ParseException ex) {
            this.dateOfBirth = null;
        }
        this.email = email;
        this.password = password;
        this.address = address;
        this.gender = gender;
    }

    public User230() {
    }

    // Getters and Setters
    public String getId() { 
        return id; 
    }
    public User230 setId(String id) {
        this.id = id; 
        return this;
    }

    public String getFullName() { 
        return fullName; 
    }
    public User230 setFullName(String fullName) {
        this.fullName = fullName; 
        return this;
    }

    public Date getDateOfBirth() { 
        return dateOfBirth; 
    }
    public User230 setDateOfBirth(String dateOfBirth) throws ParseException {
        this.dateOfBirth = format.parse(dateOfBirth);
        return this;
    }

    public String getEmail() { 
        return email; 
    }
    public User230 setEmail(String email) {
        this.email = email; 
        return this;
    }

    public String getPassword() { 
        return password; 
    }
    public User230 setPassword(String password) {
        this.password = password; 
        return this;
    }

    public String getAddress() {
        return address; 
    }
    public User230 setAddress(String address) {
        this.address = address; 
        return this;
    }

    public boolean getGender() {
        return gender;
    }
    public User230 setGender(boolean gender) {
        this.gender = gender;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public User230 setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
    
    
}

