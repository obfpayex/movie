(function () {
    'use strict';

    angular
        .module('app', [
            'ui.router', 'ui.bootstrap', 'ngResource'
        ])
        .run(run);

    run.$inject = ['stateHandler']; //Handles i.e page title

    function run(stateHandler) {
        stateHandler.initialize();
    }
})();
