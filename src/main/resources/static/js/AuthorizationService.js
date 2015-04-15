'use strict';

var module = angular.module('announcementBoardApp.AuthorizationService', []);

module.factory('Authorization', ['$resource', function($resource) {

    return {
        login: function(username, password) {
            return $resource('./authorization/login').save({
                username: username,
                password: password
            });
        }
    };
}]);