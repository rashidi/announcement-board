'use strict';

var module = angular.module('announcementBoardServices', ['ngResource']);

module.factory('RESTService', ['$resource', function($resource) {

    return {
        login: function(username, password) {
            return $resource('./authorization/login').save({
                username: username,
                password: password
            });
        }
        signup: function(userdata) {
            return $resource('./users').save({
                name: userdata.fullname,
                username: userdata.username,
                password: userdata.password,
                email: userdata.email
            });
        }
    }
}]);