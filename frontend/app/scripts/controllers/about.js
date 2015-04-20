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
      var dataset = [
        { label: 'Abulia', count: 10 },
        { label: 'Betelgeuse', count: 20 },
        { label: 'Cantaloupe', count: 30 },
        { label: 'Dijkstra', count: 40 }
      ];
      var width = 360;
      var height = 360;
      var radius = Math.min(width, height) / 2;
      var color = d3.scale.category20b();

      var svg = d3.select('.dataHolder')
          .append('svg')
          .attr('width', width)
          .attr('height', height)
          .append('g')
          .attr('transform', 'translate(' + (width / 2) +  ',' + (height / 2) + ')');

  });
