'use strict';

var module = angular.module('announcementBoardServices', ['ngResource']);

module.factory('UserService', ['$resource', function($resource) {

    return {
        login: function(username, password) {
            return $resource('./authorization/login').save({
                username: username,
                password: password
            });
        }
    }
}]);