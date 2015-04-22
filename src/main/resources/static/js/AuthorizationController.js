'use strict';

var module = angular.module('announcementBoardApp.AuthorizationController',['ui.bootstrap', 'ngMdIcons']);

module.controller('LoginCtrl', ['$scope', '$location', '$mdToast', 'store', 'Scopes', 'Authorization', function($scope, $location, $mdToast, store, Scopes, Authorization) {

     Scopes.store('LoginCtrl', $scope);
     $scope.auth = {};
     $scope.isLoggedOut = store.get('loggedIn') ? angular.equals(store.get('loggedIn'),false) : true;

     if(!$scope.isLoggedOut){
        var path = $location.path();
        if(path !== "/announcement"){
            $location.path('/announcement');
            $location.replace();
        }
     }

//     $scope.auth.username = store.get('authorization') ? store.get('authorization').username : "" ;

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
                store.set('authorization', response);
                store.set('authorization_header', "Basic "+ window.btoa(response.username +":"+ response.token));

                angular.copy({}, auth);
                store.set('loggedIn', true);
                $scope.isLoggedOut = angular.equals(store.get('loggedIn'),false);

                Scopes.get('ProfileCtrl').isLoggedIn = angular.equals(store.get('loggedIn'),false);
                Scopes.get('ProfileCtrl').profile.username = store.get('authorization').username;

                $location.path('/announcement');
                $location.replace();
                $scope.showSimpleToast('You are successfully logged in!');
             }

         }).catch(function(error) {
            if(error.status === 403){
                $scope.loginMessages = {usernamePassword: true};
            }
         });
      };

}]);

module.controller('ProfileCtrl', ['$scope', '$location', '$mdToast', 'store', 'Scopes', 'Authorization', function($scope, $location, $mdToast, store, Scopes, Authorization) {
     Scopes.store('ProfileCtrl', $scope);
     $scope.isLoggedIn = store.get('loggedIn') ? angular.equals(store.get('loggedIn'),false) : true;
     $scope.profile = {};
     $scope.profile.username = store.get('authorization') ? store.get('authorization').username : "" ;

     $scope.showSimpleToast = function(content) {
         $mdToast.show(
           $mdToast.simple()
             .content(content)
             .position('top right')
             .hideDelay(3000)
         );
     };

     $scope.submitUserLogout = function(profile) {
        angular.copy({}, profile);
        store.set('loggedIn', false);
        $scope.isLoggedIn = angular.equals(store.get('loggedIn'),false);
        Scopes.get('LoginCtrl').isLoggedOut = angular.equals(store.get('loggedIn'),false);
        store.remove('authorization', null);
        store.remove('authorization_header', null);
        $location.path('/');
        $location.replace();
        $scope.showSimpleToast('You are successfully logged out!');
     }

}]);