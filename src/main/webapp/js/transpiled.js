var Main = (function () {
    function Main() {
    }
    Main.initialize = function () {
        var dateTimeDiv = $('div.date-time');
        dateTimeDiv.text(dateTimeDiv.text().replace(/-/g, '.'));
    };
    return Main;
})();
Main.initialize();
