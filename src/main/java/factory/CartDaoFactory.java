package factory;

import dao.CartDao;
import dao.impl.hib.CartDaoHibImpl;

public class CartDaoFactory {

    private static CartDao cartDaoInstance;

    public CartDaoFactory() {
    }

    public static CartDao getCartDaoInstance() {
        if(cartDaoInstance == null){
            cartDaoInstance = new CartDaoHibImpl();
        }
        return cartDaoInstance;
    }
}
