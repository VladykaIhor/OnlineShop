package mate.academy.factory;

import mate.academy.dao.CartDao;
import mate.academy.dao.impl.jdbc.CartDaoJDBCImpl;

public class CartDaoFactory {

    private static CartDao cartDaoInstance;

    public CartDaoFactory() {
    }

    public static CartDao getCartDaoInstance() {
        if(cartDaoInstance == null){
            cartDaoInstance = new CartDaoJDBCImpl();
        }
        return cartDaoInstance;
    }
}
