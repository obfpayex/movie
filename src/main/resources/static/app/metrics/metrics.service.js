(function () {
    'use strict';

    angular
        .module('app')
        .factory('MetricsService', MetricsService);

    MetricsService.$inject = ['$rootScope', '$http'];

    function MetricsService($rootScope, $http) {
        var service = {
            getMetrics: getMetrics,
            threadDump: threadDump
        };

        return service;

        function getMetrics() {
            return $http.get('management/metrics').then(function (response) {
                return response.data;
            });
        }

        function threadDump() {
            return $http.get('management/dump').then(function (response) {
                return response.data;
            });
        }
    }
})();
