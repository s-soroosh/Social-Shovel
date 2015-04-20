'use strict';

/**
 * @ngdoc function
 * @name zssApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the zssApp
 */
angular.module('zssApp')
  .controller('AboutCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
    d3.select("body").append("p").text("New paragraph!");
  });
