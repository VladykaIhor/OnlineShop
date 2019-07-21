package mate.academy.model;

import mate.academy.utils.IdCounterUtil;

import java.util.ArrayList;
import java.util.Objects;

public class Order {

    private Long id;
    private User user;
    private String deliveryAddress;
    private ArrayList<Product> orderProducts;

    public Order(User user, String deliveryAddress, ArrayList<Product> orderProducts) {
        this.id = IdCounterUtil.getProductId();
        this.user = user;
        this.deliveryAddress = deliveryAddress;
        this.orderProducts = orderProducts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public ArrayList<Product> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(ArrayList<Product> orderProducts) {
        this.orderProducts = orderProducts;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(user, order.user) &&
                Objects.equals(deliveryAddress, order.deliveryAddress) &&
                Objects.equals(orderProducts, order.orderProducts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, deliveryAddress, orderProducts);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", orderProducts=" + orderProducts +
                '}';
    }
}
