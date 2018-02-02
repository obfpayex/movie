(function () {
    'use strict';
    angular.module('app')
        .config(stateConfig);


    stateConfig.$inject = ['$stateProvider', '$urlRouterProvider'];

    function stateConfig($stateProvider, $urlRouterProvider) {
        $stateProvider
            .state('metrics', { //state for metrics
                title: 'Metrics',
                url: '/',
                views: {
                    'content@': {
                        templateUrl: 'app/metrics/metrics.html',
                        controller: 'MetricsMonitoringController',
                        controllerAs: 'vm'
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }

})();
