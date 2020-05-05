package com.epam.borysenko.constants;

public final class DaoQueryConstant {

    public static final String GET_ALL_PRODUCTS = "p.*, c.name as category, pr.name as producer ";
    public static final String PRODUCT_PRODUCER_CATEGORY_TABLE = " from product p, producer pr, category c where c.id=p.category_id and pr.id = p.producer_id ";
    public static final String GET_ALL_CATEGORY = "SELECT * FROM category";
    public static final String GET_ALL_PAYMENT = "SELECT * FROM ishop.payment";
    public static final String GET_ALL_DELIVERY = "SELECT * FROM ishop.delivery";
    public static final String GET_ALL_PRODUCER = "SELECT * FROM producer";
    public static final String ORDER_BY_NAME_ASC_COMMAND = " ORDER by name ASC";
    public static final String ORDER_BY_NAME_DESC_COMMAND = " ORDER by name DESC";
    public static final String ORDER_BY_PRICE_ASC_COMMAND = " ORDER by price ASC";
    public static final String ORDER_BY_PRICE_DESC_COMMAND = " ORDER by price DESC";
    public static final String ORDER_BY_NAME_ASC = "nameASC";
    public static final String ORDER_BY_NAME_DESC = "nameDESC";
    public static final String ORDER_BY_PRICE_ASC = "priceASC";
    public static final String ORDER_BY_PRICE_DESC = "priceDESC";
    public static final String COUNT_ALL = "count(*)";
    public static final String GET_PRODUCT = " SELECT p.*, c.name as category, pr.name as producer from product p, producer pr, category c WHERE c.id=p.category_id AND pr.id = p.producer_id AND p.id = ?";
    public static final String GET_USER_BY_LOGIN = "SELECT user.*, role.name as role FROM user,role where user.role_id=role.id and user.login = ?";
    public static final String CREATE_USER = "INSERT INTO user (`login`, `password`, `email`, `first_name`, " +
            "`last_name`,`avatar_link`,`newsletter_consent`)VALUES (?,?,?,?,?,?,?)";
    public static final String CREATE_ORDER = " insert into ishop.order(`id`,`address`, `phone_number`, `delivery_id`,`payment_id`,`user_id`) values (?,?,?,?,?,?) ";
    public static final String CREATE_ORDER_ITEM = "insert into ishop.order_item (order_id, product_id,price,count) values (?,?,?,?) ";

    private DaoQueryConstant() {
    }
}
