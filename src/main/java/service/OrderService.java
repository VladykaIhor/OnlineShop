package service;

import model.Order;
import model.User;

public interface OrderService {

    void addOrder(Order order);

    Long getOrderIdByUser(User user);
}
