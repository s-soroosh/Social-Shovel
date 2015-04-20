'use strict';

/**
 * @ngdoc function
 * @name zssApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the zssApp
 */
angular.module('zssApp')
  .controller('MainCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
