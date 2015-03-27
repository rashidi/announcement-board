'use strict';

var module = angular.module('announcementBoardApp.UserService', ['ngResource']);

module.factory('SignupService', ['$resource', function($resource) {

    return {
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