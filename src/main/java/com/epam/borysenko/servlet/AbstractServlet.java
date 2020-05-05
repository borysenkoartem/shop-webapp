package com.epam.borysenko.servlet;

import com.epam.borysenko.model.form.ProductForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.borysenko.constants.PathConstants.PAGE_TEMPLATE;

public class AbstractServlet extends HttpServlet {


    protected void forwardToPage(String pageName, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("currentPage", pageName);
        request.getRequestDispatcher(PAGE_TEMPLATE).forward(request, response);
    }

    public ProductForm createProductForm(HttpServletRequest request) {
        return new ProductForm(Integer.parseInt(request.getParameter("productId")),
                Integer.parseInt(request.getParameter("count")));
    }
}
