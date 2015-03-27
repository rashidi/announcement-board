'use strict';

var module = angular.module('announcementBoardApp.UserController',[]);

module.controller('SignupCtrl', ['$scope', '$mdToast', 'SignupService', function($scope, $mdToast, SignupService) {
     $scope.showSimpleToast = function(content) {
         $mdToast.show(
           $mdToast.simple()
             .content(content)
             .position('top right')
             .hideDelay(3000)
         );
       };

      $scope.submitNewSignUp = function(user) {
         SignupService.signup(user).$promise.then(function(response) {
             if(response.value == 409)
                 $scope.showSimpleToast('Username / Email Address you entered is available!');
             else
                 $scope.showSimpleToast('New user is successfully registered!');
                 user = {};
                 $('#container form').get(0).reset();

         });
      };
}]);