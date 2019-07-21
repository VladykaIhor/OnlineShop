package mate.academy.factory;

import mate.academy.service.OrderService;
import mate.academy.service.impl.OrderServiceImpl;

public class OrderServiceFactory {

    private static OrderService orderServiceInstance;

    public OrderServiceFactory() {
    }

    public static OrderService getInstance() {
        if (orderServiceInstance == null) {
            orderServiceInstance = new OrderServiceImpl();
        }
        return orderServiceInstance;
    }
}
