package com.epam.borysenko.listener;

import com.epam.borysenko.dao.ProductDao;
import com.epam.borysenko.dao.UserDao;
import com.epam.borysenko.dao.impl.OrderDaoImpl;
import com.epam.borysenko.dao.impl.ProductDaoImpl;
import com.epam.borysenko.dao.impl.UserDaoImpl;
import com.epam.borysenko.entity.User;
import com.epam.borysenko.factory.impl.CaptchaFactoryImpl;
import com.epam.borysenko.factory.impl.UserFactoryImpl;
import com.epam.borysenko.handler.DefaultResultSetHandler;
import com.epam.borysenko.model.Captcha;
import com.epam.borysenko.service.CaptchaService;
import com.epam.borysenko.service.OrderService;
import com.epam.borysenko.service.ProductService;
import com.epam.borysenko.service.UserService;
import com.epam.borysenko.service.impl.CaptchaServiceImpl;
import com.epam.borysenko.service.impl.OrderServiceImpl;
import com.epam.borysenko.service.impl.ProductServiceImpl;
import com.epam.borysenko.service.impl.UserServiceImpl;
import com.epam.borysenko.strategy.CaptchaStorageStrategy;
import com.epam.borysenko.strategy.imp.CookiesCaptchaStorageImpl;
import com.epam.borysenko.strategy.imp.HiddenFieldStorageImpl;
import com.epam.borysenko.strategy.imp.SessionCaptchaStorageImpl;
import com.epam.borysenko.validation.impl.CaptchaValidator;
import com.epam.borysenko.validation.impl.RegistrationValidator;
import com.epam.borysenko.validation.impl.SearchFormValidator;
import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.epam.borysenko.constants.ContextConstants.*;


@WebListener
public class ApplicationContextListener implements ServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationContextListener.class);

    private DataSource dataSource;
    private QueryRunner queryRunner;
    private ProductDao productDao;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        setupDataSource();
        ServletContext servletContext = sce.getServletContext();
        queryRunner = new QueryRunner();
        servletContext.setAttribute(USER_SERVICE, initUserService());
        servletContext.setAttribute(PRODUCT_SERVICE, initProductService());
        servletContext.setAttribute(CAPTCHA_SERVICE, initCaptchaService(servletContext));
        servletContext.setAttribute(ORDER_SERVICE, initOrderService());
        servletContext.setAttribute(REGISTRATION_VALIDATION_UTIL, new RegistrationValidator(servletContext));
        servletContext.setAttribute(CAPTCHA_VALIDATION_UTIL, new CaptchaValidator());
        servletContext.setAttribute(SEARCH_FORM_VALIDATION_UTIL, new SearchFormValidator());

        LOGGER.debug("Application context has been initialized");
    }

    private OrderService initOrderService() {
        return new OrderServiceImpl(new OrderDaoImpl(), productDao, dataSource);
    }

    private UserService initUserService() {
        DefaultResultSetHandler userResultSetHandler = new DefaultResultSetHandler(User.class, false);
        UserDao userDao = new UserDaoImpl(queryRunner, userResultSetHandler);
        return new UserServiceImpl(userDao, dataSource);
    }

    private ProductService initProductService() {
        productDao = new ProductDaoImpl();
        return new ProductServiceImpl(productDao, dataSource);
    }

    private CaptchaService initCaptchaService(ServletContext servletContext) {
        Map<String, Captcha> captchaMap = new ConcurrentHashMap<>();
        servletContext.setAttribute(CAPTCHA_MAP, captchaMap);
        servletContext.setAttribute(USER_FACTORY, new UserFactoryImpl() {
        });

        initCaptchaStrategy(servletContext);
        initCaptchaFactory(servletContext);
        return new CaptchaServiceImpl(servletContext);
    }

    private void initCaptchaStrategy(ServletContext servletContext) {
        Map<String, CaptchaStorageStrategy> captchaStorageStrategyMap = new HashMap<>();
        captchaStorageStrategyMap.put(COOKIES, new CookiesCaptchaStorageImpl());
        captchaStorageStrategyMap.put(SESSION, new SessionCaptchaStorageImpl());
        captchaStorageStrategyMap.put(HIDDEN_FIELD, new HiddenFieldStorageImpl());
        String captchaStorageStrategy = servletContext.getInitParameter(CAPTCHA_STORAGE_STRATEGY);
        servletContext.setAttribute(CAPTCHA_STORAGE_STRATEGY, captchaStorageStrategyMap.get(captchaStorageStrategy));
    }

    private void initCaptchaFactory(ServletContext servletContext) {
        servletContext.setAttribute(CAPTCHA_FACTORY, new CaptchaFactoryImpl());
    }

    private void setupDataSource() {
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/ishop");
            dataSource.getConnection();
        } catch (NamingException | SQLException e) {
            LOGGER.error("Error during init connection to DB ");
        }
        LOGGER.debug("Set DB connection");
    }
}
