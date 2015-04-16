'use strict';

var module = angular.module('announcementBoardApp.AuthorizationController',[]);

module.controller('LoginCtrl', ['$scope', '$location', '$mdToast', 'store', 'Scopes', 'Authorization', function($scope, $location, $mdToast, store, Scopes, Authorization) {

     $scope.auth = {};
     Scopes.store('LoginCtrl', $scope);
     store.set('loggedIn', false);
     var authorization = store.get('authorization');
     $scope.auth.username = authorization ? authorization.username : "" ;

     $scope.showSimpleToast = function(content) {
         $mdToast.show(
           $mdToast.simple()
             .content(content)
             .position('top right')
             .hideDelay(3000)
         );
       };


      $scope.isLoggedIn = function() {
           return angular.equals(store.get('loggedIn'),true);
      }

      $scope.$on("$destroy", function() {
          store.set('loggedIn', false);
          store.set('authorization', null);
       });

      $scope.submitUserLogin = function(auth) {
         Authorization.login(auth.username, auth.password).$promise.then(function success(response) {
             if(response === undefined) {
                $scope.showSimpleToast('There is an ERROR! You are not authorized for login!');
             } else {
                $scope.loginMessages = {usernamePassword: false};
                store.set('authorization', response);
                store.set('authorization_header', "Basic "+ window.btoa(response.username +":"+ response.token));
                $scope.showSimpleToast('You are successfully logged in!');
                angular.copy({}, auth);
                store.set('loggedIn', true);
                $location.path('/announcement');
             }

         }).catch(function(error) {
            if(error.status === 403){
                $scope.loginMessages = {usernamePassword: true};
            }
         });
      };

}]);
