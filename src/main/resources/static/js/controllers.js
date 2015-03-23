'use strict';

var module = angular.module('announcementBoardApp', ['ngMaterial']);

module.config(function($mdThemingProvider) {
    $mdThemingProvider.theme('default')
        .primaryPalette('deep-orange');
});

module.controller('HeaderCtrl', ['$scope', function($scope) {
}]);