'use strict';

/**
 * @ngdoc overview
 * @name zssApp
 * @description
 * # zssApp
 *
 * Main module of the application.
 */
angular
  .module('zssApp', [
    'ngAnimate',
    'ngAria',
    'ngCookies',
    'ngMessages',
    'ngResource',
    'ngRoute',
    'ngSanitize',
      'ngWebSocket',
    'ngTouch',
      'chart.js'
  ])
  .config(function ($routeProvider,$locationProvider,$httpProvider) {
      $httpProvider.defaults.useXDomain = true;


      $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl'
      })
      .when('/maps', {
        templateUrl: 'views/maps.html',
        controller: 'MapsCtrl'
      })
      .when('/statistics', {
        templateUrl: 'views/statistics.html',
        controller: 'StatisticsCtrl'
      })
      .when('/messages', {
        templateUrl: 'views/messages.html',
        controller: 'MessagesCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
      $locationProvider.html5Mode=true;
  }).run(function($rootScope) {
      $rootScope.page="Dashboard";

      $rootScope.$on('$routeChangeSuccess', function(next, current) {
        $rootScope.page=current.$$route.originalPath;
      });

    });
