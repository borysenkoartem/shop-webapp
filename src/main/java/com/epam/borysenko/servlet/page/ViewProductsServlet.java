package com.epam.borysenko.servlet.page;

import com.epam.borysenko.entity.product.Category;
import com.epam.borysenko.entity.product.Producer;
import com.epam.borysenko.entity.product.Product;
import com.epam.borysenko.exception.ServiceException;
import com.epam.borysenko.model.form.SearchForm;
import com.epam.borysenko.service.ProductService;
import com.epam.borysenko.servlet.AbstractServlet;
import com.epam.borysenko.validation.impl.SearchFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.epam.borysenko.constants.ContextConstants.*;
import static com.epam.borysenko.constants.PathConstants.PRODUCTS_PAGE;
import static com.epam.borysenko.constants.PathConstants.PRODUCTS_SERVLET;
import static com.epam.borysenko.constants.SearchFormConstant.*;
import static com.epam.borysenko.constants.ValidationConstants.ERRORS;

@WebServlet(urlPatterns = PRODUCTS_SERVLET)
public class ViewProductsServlet extends AbstractServlet {

    private static final Logger PRODUCT_SERVLET_LOGGER = LoggerFactory.getLogger(ViewProductsServlet.class);

    private ProductService productService;
    private SearchFormValidator searchFormValidator;

    @Override
    public void init() throws ServletException {
        productService = (ProductService) getServletContext().getAttribute(PRODUCT_SERVICE);
        searchFormValidator = (SearchFormValidator) getServletContext().getAttribute(SEARCH_FORM_VALIDATION_UTIL);
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        moveErrorsFromSessionToRequest(req);
        SearchForm searchForm = createSearchForm(req);
        req.setAttribute("searchForm", searchForm);
        if (isValidSearchForm(searchForm, req)) {
            showProducts(searchForm, req, resp);
        } else {
            resp.sendRedirect(PRODUCTS_SERVLET);
        }
    }

    private void moveErrorsFromSessionToRequest(HttpServletRequest req) {
        Map<String, String> errors = (Map<String, String>) req.getSession().getAttribute(ERRORS);
        req.getSession().removeAttribute(ERRORS);
        req.setAttribute(ERRORS, errors);
        req.setAttribute(SUCCESS_MASSAGE,req.getSession().getAttribute(SUCCESS_MASSAGE));
        req.getSession().removeAttribute(SUCCESS_MASSAGE);
    }

    private SearchForm createSearchForm(HttpServletRequest req) {
        return new SearchForm(req.getParameter(QUERY),
                req.getParameterValues(CATEGORY),
                req.getParameterValues(PRODUCER),
                req.getParameter(PAGE),
                req.getParameter(LIMIT),
                req.getParameter(MIN_PRICE),
                req.getParameter(MAX_PRICE),
                req.getParameter(SORT));
    }

    public boolean isValidSearchForm(SearchForm searchForm, HttpServletRequest req) {
        Map<String, String> errors = searchFormValidator.validate(searchForm);
        if (!errors.isEmpty()) {
            req.getSession().setAttribute(ERRORS, errors);
            return false;
        }
        return true;
    }

    public void setAttributesToRequest(HttpServletRequest req, int totalCount,
                                       List<Product> products, SearchForm searchForm) {
        try {
            List<Category> categories = productService.listAllCategories();
            List<Producer> producers = productService.listAllProducer();
            req.setAttribute(PAGE_COUNT, getPageCount(totalCount, Integer.parseInt(searchForm.getLimitPerPage())));
            req.setAttribute(CATEGORIES, categories);
            req.setAttribute(PRODUCERS, producers);
            req.setAttribute(PRODUCTS, products);
        } catch (ServiceException e) {
            PRODUCT_SERVLET_LOGGER.error(e.getMessage());
        }
    }

    public void showProducts(SearchForm searchForm, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int totalCount = productService.getCountProducts(searchForm);
            List<Product> products = productService.getListProducts(searchForm);
            setAttributesToRequest(req, totalCount, products, searchForm);
            forwardToPage(PRODUCTS_PAGE, req, resp);
        } catch (ServiceException e) {
            PRODUCT_SERVLET_LOGGER.error(e.getMessage());
        }
    }

    public final int getPageCount(int totalCount, int itemsPerPage) {
        int result = totalCount / itemsPerPage;
        if (result * itemsPerPage != totalCount) {
            result++;
        }
        return result;
    }
}
