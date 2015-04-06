'use strict';

var module = angular.module('announcementBoardApp.AuthorizationService', ['ngResource']);

module.factory('Authorization', ['$resource', function($resource) {
    var currentAuthorization = {};

    return {
        login: function(username, password) {
            return $resource('./authorization/login').save({
                username: username,
                password: password
            });
        },
        setCurrentAuthorization: function(authorization) {
          currentAuthorization = authorization;
        },
        getCurrentAuthorization: function() {
            return currentAuthorization;
        }
    };
}]);