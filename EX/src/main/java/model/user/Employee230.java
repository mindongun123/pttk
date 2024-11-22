package model.user;

import java.text.ParseException;


public class Employee230 extends User230 {
    private String position;

    public Employee230(
            String id, 
            String fullName, 
            String dateOfBirth, 
            String email, 
            String password, 
            String address, 
            boolean gender, 
            String position
    ) throws ParseException {
        super(id, fullName, dateOfBirth, email, password, address, gender);
        this.position = position;
    }

    public Employee230() {
    }
    
    

    // Getters and Setters
    public String getPosition() { 
        return position; 
    }
    public void setPosition(String position) { 
        this.position = position; 
    }
}
