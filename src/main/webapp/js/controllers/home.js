'use strict';

angular.module('app')
    .controller("HomeCtrl", ['$scope', '$http', function ($scope, $http) {

        $scope.debug = true;
        $scope.title = 'Hello ';

        $http.get("service/home").success(function (data) {
            alert(data);
        });

        $scope.toggleDebug = function () {
            $scope.debug = !$scope.debug;
        };
    }]);