'use strict';

var module = angular.module('announcementBoardApp', ['ngMaterial']);

module.config(function($mdThemingProvider) {
    $mdThemingProvider.theme('default')
        .primaryPalette('deep-orange');
});

module.controller('HeaderCtrl', ['$scope', function($scope) {
}]);

module.controller('UserCtrl', ['$scope', function($scope) {
}]);

module.controller('AuthorizationCtrl', ['$scope', function($scope) {
}]);

module.controller('AnnouncementCtrl', ['$scope', function($scope) {
}]);