package mate.academy.service.impl;

import mate.academy.factory.MailServiceFactory;
import mate.academy.factory.OrderServiceFactory;
import mate.academy.model.Code;
import mate.academy.model.Order;
import mate.academy.model.Product;
import mate.academy.model.User;
import mate.academy.service.CartService;
import mate.academy.service.MailService;
import mate.academy.service.OrderService;
import mate.academy.utils.CodeGeneratorUtil;

import java.util.ArrayList;
import java.util.Optional;

public class CartServiceImpl implements CartService {

    private static ArrayList<Product> cartProducts = new ArrayList<>();
    private static final MailService mailService = MailServiceFactory.getInstance();
    private static final OrderService orderService = OrderServiceFactory.getInstance();

    @Override
    public void addCartProduct(Product product) {
        cartProducts.add(product);
    }

    @Override
    public Optional<ArrayList<Product>> getCartProducts() {
        return cartProducts.size() == 0 ?
                Optional.empty()
                : Optional.of(cartProducts);
    }

    @Override
    public int cartSize() {
        return cartProducts.size();
    }

    @Override
    public Code sendConfirmationCode(String email) {
        String confirmationCode = CodeGeneratorUtil.generateCode();
        Code code = new Code(confirmationCode);
        mailService.sendConfirmCode(code, email);
        return code;
    }

    @Override
    public void createNewOrder(User user, String deliveryAddress, ArrayList<Product> products) {
        Order userOrder = new Order(user, deliveryAddress, products);
        orderService.addOrder(userOrder);
    }
}
