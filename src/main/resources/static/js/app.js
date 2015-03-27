'use strict';

/**
* @ngdoc overview
* @name announcementBoardApp
* @description
* # announcementBoardApp
*
* Main module of the application.
*/
angular
.module('announcementBoardApp', [
  'ngAnimate',
  'ngRoute',
  'ngMaterial',
  'ngMessages',
  'announcementBoardApp.UserController',
  'announcementBoardApp.UserService'
])
.config(function ($routeProvider) {
  $routeProvider
    .when('/', {
      templateUrl: 'views/signup.html',
      controller: 'SignupCtrl'
    })
    .otherwise({
      redirectTo: '/'
    });
});