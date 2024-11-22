package model.user;

import java.text.ParseException;


public class Customer230 extends User230 {
    private boolean isMember = false;

    public Customer230(
            String id, 
            String fullName, 
            String dateOfBirth, 
            String email, 
            String password, 
            String address, 
            boolean gender
    ) throws ParseException {
        super(id, fullName, dateOfBirth, email, password, address, gender);
    }

    public Customer230() {
    }
    
    

    // Getters and Setters
    public boolean isMember() { 
        return isMember; 
    }
    public void setMember(boolean isMember) { 
        this.isMember = isMember; 
    }
}

