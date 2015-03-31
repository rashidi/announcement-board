'use strict';

var module = angular.module('announcementBoardApp.AuthorizationService', ['ngResource']);

module.factory('LoginService', ['$resource', function($resource) {

    return {
        login: function(username, password) {
            return $resource('./authorization/login').save({
                username: username,
                password: password
            });
        }
    }
}]);