package mate.academy.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cart {

    private Long id;
    private User user;
    private List<Product> products;

    public Cart(Long id, User user, List<Product> products) {
        this.id = id;
        this.user = user;
        this.products = products;
    }

    public Cart(Long id) {
        this.id = id;
        this.products = new ArrayList<>();
    }

    public Cart(User user, List<Product> products) {
        this.user = user;
        this.products = products;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Product> getCart() {
        return products;
    }

    public int getCartSize() {
        return products.size();
    }

    public Long getUserId() {
        return user.getId();
    }
}
