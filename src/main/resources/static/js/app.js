'use strict';

/**
* @ngdoc overview
* @name announcementBoardApp
* @description
* # announcementBoardApp
*
* Main module of the application.
*/
var currentRequests = [];

var app = angular.module('announcementBoardApp', [
  'ngAnimate',
  'ngRoute',
  'ngMaterial',
  'ngMessages',
  'angular-storage',
  'announcementBoardApp.UserController',
  'announcementBoardApp.UserService',
  'announcementBoardApp.AuthorizationController',
  'announcementBoardApp.AuthorizationService'
]);

app.run(['$rootScope', function($rootScope) {

  /**
   * Cancels pending requests
   */
  $rootScope.$on('$routeChangeSuccess', function() {
    angular.forEach(currentRequests, function(request) {
      request.resolve(); // cancel
    });
    currentRequests = [];
  });

}]);

app.factory('authInterceptor', ['$q', '$rootScope', '$location', '$timeout', function($q, $rootScope, $location, $timeout) {

  return{
      request: function(config) {
//          delete $rootScope.errorKey;
          var currentAuthorization = null;//Authorization.getCurrentAuthorization();
          var access_token = currentAuthorization ? currentAuthorization.token : null;

          if (access_token) {
              config.headers.authorization = access_token;
          }

        if(!/\.html/.test(config.url)) {
            var defer = $q.defer();
            currentRequests.push(defer);
            config.timeout = defer.promise;
        }

          return config;
      },
      responseError: function(errorResponse) {
        if (errorResponse.status === 401) {
//            $rootScope.error = 'global.server_errors.unauthorized';
            $timeout(function () {
                $location.path('/');
            }, 3000);
        } else if(errorResponse.status !== 0) {
//          $rootScope.showErrorMsg = true; // general error message
//          $timeout(function() {
//            $rootScope.showErrorMsg = false;
//          }, 10000);
        }

        return $q.reject(errorResponse);
      }
  };

}]);

app.factory('Scopes', function () {
    var mem = {};

    return {
        store: function (key, value) {
            mem[key] = value;
        },
        get: function (key) {
            return mem[key];
        }
    };
});

app.config(['$routeProvider', '$httpProvider', function ($routeProvider, $httpProvider) {
  $routeProvider
    .when('/',
    {
      templateUrl: 'views/signup.html',
      controller: 'SignupCtrl'
    })
    .when('/announcement',
    {
      templateUrl: 'views/announcement.html',
      controller: 'AnnouncementCtrl'
    })
    .otherwise({
      redirectTo: '/'
    });

     $httpProvider.interceptors.push('authInterceptor');
}]);