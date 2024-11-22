package model.ordering;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.user.Customer230;
import model.user.Seller230;
import model.user.Shipper230;

public class Invoice230 {
    private String id;
    private Date orderingTime;
    private Date paymentTime;
    private Date deliveryTime;
    private Customer230 customer;
    private Seller230 seller;
    private Shipper230 shipper;
    private float totalPrice;
    private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public Invoice230(Customer230 client, float totalPrice) throws ParseException {
        this.customer = client;
        this.totalPrice = totalPrice;
//        this.orderingTime = Date.from(Instant.now()); //  sử dụng múi giờ UTC
        this.orderingTime = new Date();
    }

    public Invoice230() {
    }
    
    

    public Invoice230(
            String id, 
            Date orderingTime,
            Date paymentTime, 
            Date deliveryTime, 
            Customer230 client,
            Seller230 seller,
            Shipper230 shipper,
            float totalPrice
    ) {
        this.id = id;
        this.orderingTime = orderingTime;
        this.paymentTime = paymentTime;
        this.deliveryTime = deliveryTime;
        this.customer = client;
        this.seller = seller;
        this.shipper = shipper;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters
    public String getId() { 
        return id; 
    }
    public Invoice230 setId(String id) {
        this.id = id; 
        return this;
    }

    public Date getOrderingTime() { 
        return orderingTime;
    }
    public Invoice230 setOrderingTime(Date orderingTime) {
        this.orderingTime = orderingTime; 
        return this;
    }

    public Date getPaymentTime() { 
        return paymentTime; 
    }
    public Invoice230 setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime; 
        return this;
    }

    public Date getDeliveryTime() { 
        return deliveryTime;
    }
    public Invoice230 setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime; 
        return this;
    }

    public Customer230 getCustomer() {
        return customer; 
    }
    public Invoice230 setCustomer(Customer230 customer) {
        this.customer = customer;
        return this;
    }

    public Seller230 getSeller() {
        return seller;
    }
    public Invoice230 setSeller(Seller230 seller) {
        this.seller = seller; 
        return this;
    }

    public Shipper230 getShipper() {
        return shipper; 
    }
    public Invoice230 setShipper(Shipper230 shipper) {
        this.shipper = shipper;
        return this;
    }

    public float getTotalPrice() { 
        return totalPrice; 
    }
    public Invoice230 setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice; 
        return this;
    }
}

