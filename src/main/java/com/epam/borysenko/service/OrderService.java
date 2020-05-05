package com.epam.borysenko.service;

import com.epam.borysenko.entity.ShoppingCart;
import com.epam.borysenko.entity.User;
import com.epam.borysenko.entity.order.Delivery;
import com.epam.borysenko.entity.order.Payment;
import com.epam.borysenko.exception.ServiceException;
import com.epam.borysenko.model.form.OrderForm;
import com.epam.borysenko.model.form.ProductForm;

import java.util.List;

public interface OrderService {


    /**
     * Add product to Shopping Cart.
     *
     * @param productForm  with information about product.
     * @param shoppingCart where we need to add product.
     * @throws ServiceException if was any problem during getting information from DB.
     */
    void addProductToShoppingCart(ProductForm productForm, ShoppingCart shoppingCart) throws ServiceException;

    /**
     * Remove product to Shopping Cart.
     *
     * @param productForm  with information about product.
     * @param shoppingCart from where we need to remove product.
     * @throws ServiceException if was any problem during getting information from DB.
     */
    void removeProductToShoppingCart(ProductForm productForm, ShoppingCart shoppingCart) throws ServiceException;


    /**
     * Service that insert order to data base.
     *
     * @param shoppingCart shopping cart with ordered items.
     * @param user         to whom this order belongs to.
     * @param orderForm    additional information about order.
     * @throws ServiceException if was any problem during work with DB.
     */
    void makeOrder(ShoppingCart shoppingCart, User user, OrderForm orderForm) throws ServiceException;


    /**
     * Get List Delivery types.
     *
     * @return null if no  delivery types at Data Base.
     * @throws ServiceException if was any problem during getting information from DB.
     */
    List<Delivery> getAllDeliveryTypes() throws ServiceException;

    /**
     * Get List Delivery types.
     *
     * @return null if no  payment types at Data Base.
     * @throws ServiceException if was any problem during getting information from DB.
     */
    List<Payment> getAllPaymentTypes() throws ServiceException;
}
