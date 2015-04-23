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
            $scope.socialMediaMessages = DataService.socialMediaMessages;
            $scope.collection = [];


        $scope.processMessage = function(message) {
            if(message.type=="statistic") {
                if(message.category=="positive") {
                    $scope.data[0]+=message.count;
                    if(message.country=="DEU") {
                        $scope.countryData[0]+=message.count;
                    } else if(message.country=="NLD") {
                        $scope.countryData[1]+=message.count;
                    } else {
                        $scope.countryData[2]+=message.count;
                    }
                } else if(message.category=="neutral") {
                    $scope.data[1]+=message.count;
                } else if(message.category=="negative") {
                    $scope.data[2]+=message.count;
                }
            } else {
                $scope.socialMediaMessages.unshift(message);
            }
            console.dir(message);

            $scope.collection.push(message);
        };
        $scope.checkClass = function(opinion) {
            if(opinion=='positive') {
                return 'fa-thumbs-o-up';
            } else if(opinion=='negative') {
                return 'fa-thumbs-o-down';
            }
        };
        dataStream.onMessage(function(message) {
            $scope.processMessage(JSON.parse(message.data));
        });

        $scope.labels = ["Positive", "Neutral", "Negative"];
        $scope.countryLabels = ["Deutschland", "Netherlands", "France"];
        $scope.colors=['rgba(151,187,205,1)','rgba(220,220,220,1)','rgba(247,70,74,1)'];

    });