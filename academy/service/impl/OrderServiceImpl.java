package mate.academy.service.impl;

import mate.academy.dao.OrderDao;
import mate.academy.factory.MailServiceFactory;
import mate.academy.factory.OrderDaoFactory;
import mate.academy.factory.OrderServiceFactory;
import mate.academy.model.Code;
import mate.academy.model.Order;
import mate.academy.model.Product;
import mate.academy.model.User;
import mate.academy.service.MailService;
import mate.academy.service.OrderService;
import mate.academy.utils.CodeGeneratorUtil;

import java.util.ArrayList;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    private static OrderDao orderDao = OrderDaoFactory.getInstance();

    @Override
    public void addOrder(Order order) {
        orderDao.addOrder(order);
    }
}
