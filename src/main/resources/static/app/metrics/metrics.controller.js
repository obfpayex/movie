(function () {
    'use strict';

    angular
        .module('app')
        .controller('MetricsMonitoringController', MetricsMonitoringController);

    MetricsMonitoringController.$inject = ['$scope', 'MetricsService', '$uibModal'];

    function MetricsMonitoringController($scope, MetricsService, $uibModal) {
        var vm = this;

        vm.cachesStats = {};
        vm.metrics = {};
        vm.refresh = refresh;
        vm.refreshThreadDumpData = refreshThreadDumpData;
        vm.servicesStats = {};
        vm.updatingMetrics = true;

        vm.refresh();

        $scope.$watch('vm.metrics', function (newValue) {
            vm.servicesStats = {};
            angular.forEach(newValue.timers, function (value, key) {
                if (key.indexOf('rest') !== -1 || key.indexOf('service') !== -1) {
                    vm.servicesStats[key] = value;
                }
            });

            vm.cachesStats = {};
            angular.forEach(newValue.gauges, function (value, key) {
                if (key.indexOf('jcache.statistics') !== -1) {
                    // remove gets or puts
                    var index = key.lastIndexOf('.');
                    var newKey = key.substr(0, index);

                    // Keep the name of the domain
                    vm.cachesStats[newKey] = {
                        'name': newKey.substr(18),
                        'value': value
                    };
                }
            });
        });

        function refresh() {
            vm.updatingMetrics = true;
            MetricsService.getMetrics().then(function (promise) {
                vm.metrics = promise;
                vm.updatingMetrics = false;
            }, function (promise) {
                vm.metrics = promise.data;
                vm.updatingMetrics = false;
            });
        }

        function refreshThreadDumpData() {
            MetricsService.threadDump().then(function (data) {
                $uibModal.open({
                    templateUrl: 'app/metrics/metrics.modal.html',
                    controller: 'MetricsMonitoringModalController',
                    controllerAs: 'vm',
                    size: 'lg',
                    resolve: {
                        threadDump: function () {
                            return data;
                        }

                    }
                });
            });
        }


    }
})();
