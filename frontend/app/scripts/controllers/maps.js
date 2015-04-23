'use strict';

/**
 * @ngdoc function
 * @name zssApp.controller:MapsCtrl
 * @description
 * # MapsCtrl
 * Controller of the zssApp
 */
angular.module('zssApp')
  .controller('MapsCtrl', function ($scope,DataService) {
        $scope.mapRate=DataService.mapRate;

      function drawRegionsMap() {
      console.log("drawed");
        var data = google.visualization.arrayToDataTable($scope.mapRate);

        var options = {region: 'world'};

        var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));

        chart.draw(data, options);

      }
      drawRegionsMap();
  });
