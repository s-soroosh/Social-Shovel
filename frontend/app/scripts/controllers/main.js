'use strict';

/**
 * @ngdoc function
 * @name zssApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the zssApp
 */
angular.module('zssApp')
  .controller('MainCtrl', function ($scope,$websocket,DataService,$http) {
        var dataStream = $websocket('ws://10.161.128.177:9090/socket/simple');
            $scope.data = DataService.data;
            $scope.countryData = DataService.countryData;
            $scope.socialMediaMessages = DataService.socialMediaMessages;
            $scope.collection = [];

        if($scope.socialMediaMessages.length) {
            $scope.socialMediaMessages.forEach(function(message) {
                if(message.topics.length && message.topics.join().match(/zalando/ig)) {
                    console.dir(message);
                    if(message.userOpinion.toLowerCase()=="positive") {
                        $scope.data[0]+=1;
                        if(message.country=="DEU") {
                            $scope.countryData[0]+=message.count;
                        } else if(message.country=="NLD") {
                            $scope.countryData[1]+=message.count;
                        } else {
                            $scope.countryData[2]+=message.count;
                        }
                    } else if(message.userOpinion.toLowerCase()=="neutral") {
                        $scope.data[1]+=1;
                    } else if(message.userOpinion.toLowerCase()=="negative") {
                        $scope.data[2]+=1;
                    }
                }
            });
        } else {
            $http.get("http://10.161.128.177:9090/messages/3000").success(function (data) {
                DataService.socialMediaMessages = data;
                $scope.socialMediaMessages=data;
                $scope.socialMediaMessages.forEach(function(message) {
                    if(message.topics.length && message.topics.join().match(/zalando/ig)) {
                        console.dir(message);
                        if(message.userOpinion && message.userOpinion.toLowerCase()=="positive") {
                            $scope.data[0]+=1;
                            if(message.country=="DEU") {
                                $scope.countryData[0]+=message.count;
                            } else if(message.country=="NLD") {
                                $scope.countryData[1]+=message.count;
                            } else {
                                $scope.countryData[2]+=message.count;
                            }
                        } else if(message.userOpinion && message.userOpinion.toLowerCase()=="neutral") {
                            $scope.data[1]+=1;
                        } else if(message.userOpinion && message.userOpinion.toLowerCase()=="negative") {
                            $scope.data[2]+=1;
                        }
                    }
                });
            });
        }
        $scope.processMessage = function(message) {
            console.dir(message);
            if(message.topics.length && message.topics.join().match(/zalando/ig)) {
                if(message.userOpinion && message.userOpinion=="positive") {
                    $scope.data[0]+=message.count;
                    if(message.country=="DEU") {
                        $scope.countryData[0]+=message.count;
                    } else if(message.country=="NLD") {
                        $scope.countryData[1]+=message.count;
                    } else {
                        $scope.countryData[2]+=message.count;
                    }
                } else if(message.userOpinion && message.userOpinion=="neutral") {
                    $scope.data[1]+=message.count;
                } else if(message.userOpinion && message.userOpinion=="negative") {
                    $scope.data[2]+=message.count;
                }
            } else {
                console.log('New twit');


            }
            $scope.socialMediaMessages.unshift(message);
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