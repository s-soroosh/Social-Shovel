'use strict';

/**
 * @ngdoc function
 * @name zssApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the zssApp
 */
angular.module('zssApp')
  .controller('MainCtrl', function ($scope,$websocket,DataService) {
        var dataStream = $websocket('ws://localhost:9090/socket/simple');
            $scope.data = DataService.data;
            $scope.countryData = DataService.countryData;
            $scope.collection = [];


        $scope.processMessage = function(message) {
            if(message.category=="positive") {
                $scope.data[0]+=message.count;
            } else if(message.category=="neutrall") {
                $scope.data[1]+=message.count;
            } else {
                $scope.data[2]+=message.count;
            }
            if(message.category=="positive") {

                 if(message.country=="DEU") {
                 $scope.countryData[0]+=message.count;
                 } else if(message.country=="NLD") {
                 $scope.countryData[1]+=message.count;
                 } else {
                 $scope.countryData[2]+=message.count;
                 }

            }
            console.log(message);
            $scope.collection.push(message);
        };

        dataStream.onMessage(function(message) {
            $scope.processMessage(JSON.parse(message.data));
        });


        $scope.labels = ["Positive", "Neutral", "Negative"];
        $scope.countryLabels = ["Deutschland", "Netherlands", "France"];
        $scope.colors=['rgba(151,187,205,1)','rgba(220,220,220,1)','rgba(247,70,74,1)'];

    });
