package com.epam.borysenko.constants;

public final class PathConstants {

    public static final String HOME_PAGE = "/WEB-INF/view/home.jsp";
    public static final String REGISTRATION_PAGE = "/WEB-INF/view/registration.jsp";
    public static final String ORDER_PAGE = "/WEB-INF/view/order.jsp";
    public static final String LOGIN_PAGE = "/WEB-INF/view/login.jsp";
    public static final String PRODUCTS_PAGE = "/WEB-INF/view/products.jsp";
    public static final String SHOPPING_CARD_PAGE = "/WEB-INF/view/shopping-cart.jsp";
    public static final String PAGE_TEMPLATE = "/WEB-INF/view/page-template.jsp";
    public static final String HOME_SERVLET = "/home";
    public static final String PRODUCTS_SERVLET = "/products";
    public static final String SHOPPING_CART_SERVLET = "/shopping-cart";
    public static final String REGISTRATION_SERVLET = "/registration";
    public static final String CAPTCHA_SERVLET = "/captcha";
    public static final String LOGOUT_SERVLET = "/logout";
    public static final String LOGIN_SERVLET = "/login";
    public static final String AVATAR_SERVLET = "/avatar";
    public static final String AVATAR_PATH = System.getProperty("user.dir") + "/src/main/webapp/upload/";
    public static final String ERROR_SERVLET = "/error";
    public static final String ERROR_PAGE = "/WEB-INF/view/error.jsp";
    public static final String ADMIN_PAGE = "/WEB-INF/view/admin.jsp";
    public static final String ADMIN_HOME_SERVLET = "/admin/home";
    private PathConstants() {
    }
}
