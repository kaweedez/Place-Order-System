package lk.ijse.dep.pos.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer implements SuperEntity{

    @Id
    private String id;
    private String name;
    private String address;
    @OneToMany(mappedBy = "customer", cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE, CascadeType.REFRESH})
    private List<Order> orders = new ArrayList<>();
//    private Gender gender;

    public Customer() {
    }

    public Customer(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

//    public Customer(String customerId, String name, String address, Gender gender) {
//        this.customerId = customerId;
//        this.name = name;
//        this.address = address;
//        this.setGender(gender);
//    }

    //    public Customer(String customerId, String name, String address) {
//        this.customerId = customerId;
//        this.name = name;
//        this.address = address;
//    }



    public String getCustomerId() {
        return id;
    }

    public void setCustomerId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

//    public Gender getGender() {
//        return gender;
//    }
//
//    public void setGender(Gender gender) {
//        this.gender = gender;
//    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order){
        order.setCustomer(this);
        getOrders().add(order);
    }

    public void removeOrder(Order order){
        if(order.getCustomer() != this){
            throw new RuntimeException("Invalid Order");
        }
        order.setCustomer(null);
        this.getOrders().remove(order);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
//                ", gender=" + gender +
                '}';
    }
}
