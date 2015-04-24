'use strict';

/**
 * @ngdoc function
 * @name zssApp.controller:MapsCtrl
 * @description
 * # MapsCtrl
 * Controller of the zssApp
 */
angular.module('zssApp')
  .controller('MapsCtrl', function ($scope,DataService,$http) {
        $scope.mapRate=DataService.mapRate;
        var serviceUpdate;
        var getMap= function() {
            $http.get('http://10.161.128.35:9090/aggregation/country').success(function(data) {
                if(data) {
                    for(var i in data) {
                        if(i!="" || i!="Undetermined") {
                            $scope.mapRate.push([i,data[i]]);
                        }
                    }
                    drawRegionsMap();
                    if(!serviceUpdate) {
                        serviceUpdate = setInterval(function () {
                            getMap();
                        }, 10000);
                    }
                }
            });
        }

        getMap();

      function drawRegionsMap() {
        var data = google.visualization.arrayToDataTable($scope.mapRate);

        var options = {region: 150};

        var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));

        chart.draw(data, options);

      }

  });
