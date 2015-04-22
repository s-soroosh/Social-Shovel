'use strict';

/**
 * @ngdoc function
 * @name zssApp.controller:MapsCtrl
 * @description
 * # MapsCtrl
 * Controller of the zssApp
 */
angular.module('zssApp')
  .controller('MapsCtrl', function ($scope) {


      function drawRegionsMap() {
      console.log("drawed");
        var data = google.visualization.arrayToDataTable([
          ['Country', 'Popularity'],
          ['Germany', 200],
          ['France', 600]
        ]);

        var options = {region: '150'};

        var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));

        chart.draw(data, options);

      }
      drawRegionsMap();
  });
