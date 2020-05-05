;$(function () {
    let init = function () {
        initLocalisation();

    };
    let initLocalisation = function () {
        $('.flag-link').click(changeLocale)
    };

    let changeLocale = function () {
        let local = $(this).val();
        let url;
        if (location.search.indexOf('lang=') === -1) {
            if (location.search.indexOf('?') === -1){
                url = location.pathname + location.search + '?lang=' + local + '&';
            } else {
                url = location.pathname + location.search + 'lang=' + local + '&';
            }
        }else {
            url = location.pathname + location.search.replace(/lang=\w+/, 'lang=' + local);
        }
        location.replace(url)
    };

    init();
});