'use strict';

var module = angular.module('announcementBoardApp.AuthorizationController',[]);

module.controller('LoginCtrl', ['$scope', '$mdToast', 'LoginService', function($scope, $mdToast, LoginService) {
     $scope.showSimpleToast = function(content) {
         $mdToast.show(
           $mdToast.simple()
             .content(content)
             .position('top right')
             .hideDelay(3000)
         );
       };

      $scope.submitUserLogin = function(auth) {
         LoginService.login(auth.username, auth.password).$promise.then(function(response) {
             if(response == undefined) {
                $scope.showSimpleToast('There is an ERROR! You are not authorized for login!');
             } else {
                 if(response.value == 409)
                     $scope.showSimpleToast('Username or Password you entered is available!');
                 else
                     $scope.showSimpleToast('You are successfully logged in!');
                     angular.copy({}, auth);
             }

         });
      };
}]);