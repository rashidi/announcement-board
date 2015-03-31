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
             if(response == undefined) {
                $scope.showSimpleToast('There is an ERROR! You are not registered!');
             } else {
                 $scope.showSimpleToast('You are successfully registered as new user!');
                 angular.copy({}, user);
                 $scope.signupForm.$setPristine();
             }

         }).catch(function(error) {
            $scope.showSimpleToast(error);
         });
      };
}]);