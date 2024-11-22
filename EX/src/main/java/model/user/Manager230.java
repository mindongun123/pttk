package model.user;

import java.text.ParseException;


public class Manager230 extends Employee230 {
    public Manager230(
            String id, 
            String fullName, 
            String dateOfBirth, 
            String email, 
            String password, 
            String address, 
            boolean gender 
    ) throws ParseException{
        super(id, fullName, dateOfBirth, email, password, address, gender, "Manager");
    }

    public Manager230() {
        this.setPosition("Manager");
    }
    
    
    
}
