;$(function () {
    let init = function () {
        initBuyBtn();
        $('#addToCart').click(addProductToShoppingCard);
        $('#addProductPopup .count').change(calculateCost);
        $('.remove-product').click(removeProductFromCart);
        $('.post-request').click(function(){
            postRequest($(this).attr('data-url'));
        });
    };


        let postRequest = function(url){
            let form = '<form id="postRequestForm" action="'+url+'" method="post"></form>';
            $('body').append(form);
            $('#postRequestForm').submit();
        };

    let showAddProductPopup = function () {
        let idProduct = $(this).attr('data-id-product');
        let product = $('#product' + idProduct);
        $('#addProductPopup').attr('data-id-product', idProduct);
        $('#addProductPopup .product-image').attr('src', product.find('.thumbnail img').attr('src'));
        $('#addProductPopup .name').text(product.find('.name').text());
        let price = product.find('.price').text();
        $('#addProductPopup .price').text(price);
        $('#addProductPopup .category').text(product.find('.category').text());
        $('#addProductPopup .producer').text(product.find('.producer').text());
        $('#addProductPopup .count').val(1);
        $('#addProductPopup .cost').text(price);
        $('#addToCart').removeClass('hidden');
        $('#addToCartIndicator').addClass('hidden');
        $('#addToCart').removeClass('d-none')
        $('#addToCartIndicator').addClass('d-none')
        $('#addProductPopup').modal({
            show: true
        });
    };

    let convertButtonToLoader = function (btn, btnClass) {
        btn.removeClass(btnClass);
        btn.removeClass('btn');
        btn.addClass('load-indicator');
        let text = btn.text();
        btn.text('');
        btn.attr('data-btn-text', text);
        btn.off('click');
    };

    let convertLoaderToButton = function (btn, btnClass, actionClick) {
        btn.removeClass('load-indicator');
        btn.addClass('btn');
        btn.addClass(btnClass);
        btn.text(btn.attr('data-btn-text'));
        btn.removeAttr('data-btn-text');
        btn.click(actionClick);
    };


    let initBuyBtn = function () {
        $('.buy-btn').click(showAddProductPopup);
    };


    let addProductToShoppingCard = function () {
        let productId = $('#addProductPopup').attr('data-id-product');
        let count = $('#addProductPopup .count').val();

        let btn = $('#addToCart');
        convertButtonToLoader(btn, 'btn-primary');

        $.ajax({
            url: '/json/product/add',
            method: 'POST',
            data: {
                productId: productId,
                count: count
            },
            success: function (data) {
                $('#currentShoppingCart .total-count').text(data.totalCount);
                $('#currentShoppingCart .total-cost').text(data.totalCost);
                $('#currentShoppingCart').removeClass('d-none');
                convertLoaderToButton(btn, 'btn-primary', addProductToShoppingCard);
                $('#addProductPopup').modal('hide');
            },
            error: function (data) {
                convertLoaderToButton(btn, 'btn-primary', addProductToShoppingCard())
            }
        });
    };


    let calculateCost = function () {
        let priceStr = $('#addProductPopup .price').text();
        let price = parseFloat(priceStr.replace('$', ' '));
        let count = parseInt($('#addProductPopup .count').val());
        let min = parseInt($('#addProductPopup .count').attr('min'));
        let max = parseInt($('#addProductPopup .count').attr('max'));
        if (count >= min && count <= max) {
            let cost = price * count;
            $('#addProductPopup .cost').text('$ ' + cost);
        } else {
            $('#addProductPopup .count').val(1);
            $('#addProductPopup .cost').text(priceStr);
        }
    };

    let confirm = function (msg, okFunction) {
        if (window.confirm(msg)) {
            okFunction();
        }
    };


    let removeProductFromCart = function () {
        let btn = $(this);
        confirm('Are you sure?', function () {
            executeRemoveProduct(btn);
        });
    };

    let refreshTotalCost = function () {
        let total = 0;
        $('#shoppingCart .item').each(function (index, value) {
            let count = parseInt($(value).find('.count').text());
            let price = parseFloat($(value).find('.price').text().replace('$', ' '));
            let val = price * count;
            total = total + val;
        });
        $('#shoppingCart .total').text('$' + total);
    };

    let executeRemoveProduct = function (btn) {
        let productId = btn.attr('data-id-product');
        let count = btn.attr('data-count');
        convertButtonToLoader(btn, 'btn-danger');

        $.ajax({
            url: '/json/product/remove',
            method: 'POST',
            data: {
                productId: productId,
                count: count
            },
            success: function (data) {
                if (data.totalCount == 0) {
                    window.location.href = '/products';
                } else {

                    let prevCount = parseInt($('#product' + productId + ' .count').text());
                    let remCount = parseInt(count);
                    if (remCount >= prevCount) {
                        $('#product' + productId).remove();
                    } else {
                        convertLoaderToButton(btn, 'btn-danger', removeProductFromCart);
                        $('#product' + productId + ' .count').text(prevCount - remCount);
                        if (prevCount - remCount == 1) {
                            $('#product' + productId + ' a.remove-all').remove();
                        }
                    }
                    $('#currentShoppingCart .total-count').text(data.totalCount);
                    $('#currentShoppingCart .total-cost').text(data.totalCost);
                    refreshTotalCost();
                }
            },
            error: function (data) {
                convertLoaderToButton(btn, 'btn-danger', removeProductFromCart);
                alert('Error');
            }
        });
    };

    init();
});