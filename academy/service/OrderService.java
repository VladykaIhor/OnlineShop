package mate.academy.service;

import mate.academy.model.Code;
import mate.academy.model.Order;
import mate.academy.model.Product;
import mate.academy.model.User;

import java.util.ArrayList;

public interface OrderService {

    void addOrder(Order order);

    Long getOrderIdByUser(User user);
}
