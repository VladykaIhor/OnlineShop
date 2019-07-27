package mate.academy.model;

import mate.academy.utils.CodeGeneratorUtil;

public class Code {

    private String value;
    private Long orderId;

    public Code(Long orderID) {
        this.orderId = orderID;
        this.value = CodeGeneratorUtil.generateCode();
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getCode() {
        return value;
    }
}
