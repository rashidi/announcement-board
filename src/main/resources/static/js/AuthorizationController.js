'use strict';

var module = angular.module('announcementBoardApp.AuthorizationController',[]);

module.controller('LoginCtrl', ['$scope', '$location', '$mdToast', 'Scopes', 'Authorization', function($scope, $location, $mdToast, Scopes, Authorization) {

     Scopes.store('LoginCtrl', $scope);

     $scope.showSimpleToast = function(content) {
         $mdToast.show(
           $mdToast.simple()
             .content(content)
             .position('top right')
             .hideDelay(3000)
         );
       };

      $scope.submitUserLogin = function(auth) {
         Authorization.login(auth.username, auth.password).$promise.then(function success(response) {
             if(response === undefined) {
                $scope.showSimpleToast('There is an ERROR! You are not authorized for login!');
             } else {
                $scope.loginMessages = {usernamePassword: false};
                Authorization.setCurrentAuthorization(response.data);
                $scope.showSimpleToast('You are successfully logged in!');
                angular.copy({}, auth);
                $scope.loggedIn = true;
                $location.path('/announcement');
             }

         }).catch(function(error) {
            if(error.status === 403){
                $scope.loginMessages = {usernamePassword: true};
            }
         });
      };
}]);