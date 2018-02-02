(function () {
    'use strict';

    angular
        .module('app')
        .controller('AppController', AppController);

    AppController.$inject = ['$scope', '$rootScope', '$state'];

    function AppController($scope, $rootScope, $state) {
        $rootScope.title = $state.current.title;
    }
})();

