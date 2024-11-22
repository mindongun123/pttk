package model.user;

import java.text.ParseException;


public class Shipper230 extends Employee230 {
    public Shipper230(
            String id, 
            String fullName, 
            String dateOfBirth, 
            String email, 
            String password, 
            String address, 
            boolean gender 
    ) throws ParseException{
        super(id, fullName, dateOfBirth, email, password, address, gender, "Shipper");
    }
}
