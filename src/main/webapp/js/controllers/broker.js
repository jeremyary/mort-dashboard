'use strict';

angular.module('app')
    .controller("BrokerCtrl", ['$scope', '$http', function ($scope, $http) {

        $scope.role = "broker";
    }]);