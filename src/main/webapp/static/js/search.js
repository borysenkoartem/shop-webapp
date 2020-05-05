;$(function () {
    let init = function () {
        initSearchForm();
        initPaginationBtn();
        initLimitBtn();
        initSortBtn();
        $('#goSearch').click(initGoSearchBtn);
    };

    let initGoSearchBtn = function () {

        let isAllSelected = function (selector) {
            let unchecked = 0;
            $(selector).each(function (index, value) {
                if (!$(value).is(':checked')) {
                    unchecked++;
                }
            });
            return unchecked === 0;
        };

        if (isAllSelected('.categories .search-option')) {
            $('.categories .search-option').prop('checked', false);
        }
        if (isAllSelected('.producers .search-option')) {
            $('.producers .search-option').prop('checked', false);
        }

        $('form.search').submit();
    };

    let initSearchForm = function () {
        $('#allCategories').click(function () {
            $('.categories .search-option').prop('checked', $(this).is(':checked'));
        });
        $('.categories .search-option').click(function () {
            $('#allCategories').prop('checked', false);
        });
        $('#allProducers').click(function () {
            $('.producers .search-option').prop('checked', $(this).is(':checked'));
        });
        $('.producers .search-option').click(function () {
            $('#allProducers').prop('checked', false);
        });

    };

    let initPaginationBtn = function () {
        $('.page-link').click(goToPage)
    };

    let goToPage = function () {
        let pageNumber = parseInt($(this).val());
        let url;
        if (location.search.indexOf('?page=') === -1) {
            url = location.pathname + '?page=' + (pageNumber) + '&' + location.search.substring(1);
        } else {
            url = location.pathname + '?page=' + (pageNumber) + '&' + location.search.replace(/\?page=\d+/, '').substring(1);
        }
        location.replace(url)
    };

    let initLimitBtn = function () {
        $('.limit').click(limitPerPage)
    };

    let limitPerPage = function () {
        let limit = parseInt($(this).val());
        let url;
        if (location.search.indexOf('limit=') === -1) {
            if (location.search.indexOf('?') === -1) {
                url = location.pathname + location.search + '?limit=' + limit + '&';
            } else {
                url = location.pathname + location.search + 'limit=' + limit + '&';
            }
        } else {
            url = location.pathname + location.search.replace(/limit=\d+/, 'limit=' + limit);
        }
        location.replace(url)
    };

    let initSortBtn = function(){
        $('.orderBy').click(orderBy)
    };

    let orderBy = function(){
      let sort = $(this).val();
      let url;
      if (location.search.indexOf('sort=')===-1){
          if (location.search.indexOf('?') === -1) {
              url = location.pathname + location.search + '?' + sort + '&';
          } else {
              url = location.pathname + location.search + '&' + sort + '&';
          }
      } else {
        url = location.pathname + location.search.replace(/sort=\w+/, sort);
    }
    location.replace(url)
    };
    init();
});