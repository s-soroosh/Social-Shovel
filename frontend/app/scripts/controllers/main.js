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
                    if(message.country=="Deutschland") {
                        $scope.countryData[0]+=1;
                    } else if(message.country=="United Kingdom") {
                        $scope.countryData[1]+=1;
                    }
                    else if(message.country=="France") {
                        $scope.countryData[2]+=1;
                    }
                    else if(message.country=="Spain") {
                        $scope.countryData[3]+=1;
                    }
                    else if(message.country=="Italy") {
                        $scope.countryData[4]+=1;
                    }
                    if(message.userOpinion && message.userOpinion.toLowerCase()=="satisfied") {
                        $scope.data[0]+=1;

                    } else if(message.userOpinion && message.userOpinion.toLowerCase()=="neutral") {
                        $scope.data[1]+=1;
                    } else if(message.userOpinion && message.userOpinion.toLowerCase()=="unsatisfied") {
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
                        if(message.country=="Deutschland") {
                            $scope.countryData[0]+=1;
                        } else if(message.country=="United Kingdom") {
                            $scope.countryData[1]+=1;
                        }
                        else if(message.country=="France") {
                            $scope.countryData[2]+=1;
                        }
                        else if(message.country=="Spain") {
                            $scope.countryData[3]+=1;
                        }
                        else if(message.country=="Italy") {
                            $scope.countryData[4]+=1;
                        }
                        if(message.userOpinion && message.userOpinion.toLowerCase()=="satisfied") {
                            $scope.data[0]+=1;

                        } else if(message.userOpinion && message.userOpinion.toLowerCase()=="neutral") {
                            $scope.data[1]+=1;
                        } else if(message.userOpinion && message.userOpinion.toLowerCase()=="unsatisfied") {
                            $scope.data[2]+=1;
                        }
                    }
                });
            });
        }
        $scope.processMessage = function(message) {
            console.dir(message);
            if(message.topics.length && message.topics.join().match(/zalando/ig)) {
                if(message.country=="Deutschland") {
                    $scope.countryData[0]+=1;
                } else if(message.country=="United Kingdom") {
                    $scope.countryData[1]+=1;
                }
                else if(message.country=="France") {
                    $scope.countryData[2]+=1;
                }
                else if(message.country=="Spain") {
                    $scope.countryData[3]+=1;
                }
                else if(message.country=="Italy") {
                    $scope.countryData[4]+=1;
                }
                if(message.userOpinion && message.userOpinion.toLowerCase()=="satisfied") {
                    $scope.data[0]+=1;
                } else if(message.userOpinion && message.userOpinion.toLowerCase()=="neutral") {
                    $scope.data[1]+=1;
                } else if(message.userOpinion && message.userOpinion.toLowerCase()=="unsatisfied") {
                    $scope.data[2]+=1;
                }
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

        $scope.labels = ["Satisfied", "Neutral", "Unsatisfied"];
        $scope.countryLabels = ["Deutschland", "United Kingdom", "France","Spain","Italy"];
        $scope.colors=['rgba(151,187,205,1)','rgba(220,220,220,1)','rgba(247,70,74,1)'];

    });