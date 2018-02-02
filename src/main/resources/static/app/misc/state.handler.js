(function () {
    'use strict';

    angular
        .module('app')
        .factory('stateHandler', stateHandler);

    stateHandler.$inject = ['$rootScope', '$state', '$window'];

    function stateHandler($rootScope, $state, $window) {
        return {
            initialize: initialize
        };

        function initialize() {

            var stateChangeStart = $rootScope.$on('$stateChangeStart', function (event, toState, toStateParams, fromState) {
                $rootScope.toState = toState;
                $rootScope.toStateParams = toStateParams;
                $rootScope.fromState = fromState;
            });

            var stateChangeSuccess = $rootScope.$on('$stateChangeSuccess', function (event, toState, toParams, fromState, fromParams) {
                var titleKey = 'global.title';

                // Set the page title key to the one configured in state or use default one
                updateTitle(toState.title);
            });

            $rootScope.$on('$destroy', function () {
                if (angular.isDefined(stateChangeStart) && stateChangeStart !== null) {
                    stateChangeStart();
                }
                if (angular.isDefined(stateChangeSuccess) && stateChangeSuccess !== null) {
                    stateChangeSuccess();
                }
            });

            // 3. 'global.title'
            function updateTitle(titleKey) {
                if (!titleKey && $state.$current.title) {
                    titleKey = $state.$current.title;
                }
                $window.document.title = titleKey;
            }
        }
    }
})();
