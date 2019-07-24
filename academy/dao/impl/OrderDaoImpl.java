package mate.academy.dao.impl;

import mate.academy.dao.OrderDao;
import mate.academy.model.Order;
import mate.academy.model.Product;
import mate.academy.service.ProductService;

import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    private static List<Order> orders = new ArrayList<>();


    @Override
    public void addOrder(Order order) {
        orders.add(order);
    }
}
