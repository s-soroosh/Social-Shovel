'use strict';

/**
 * @ngdoc function
 * @name zssApp.controller:MessagesCtrl
 * @description
 * # MessagesCtrl
 * Controller of the zssApp
 */
angular.module('zssApp')
  .controller('MessagesCtrl', function ($scope,DataService,$http) {
        $scope.socialMediaMessages = DataService.socialMediaMessages;
        if (!DataService.socialMediaMessages.length) {
            $scope.socialMediaMessages=[];
            $http.get("http://10.161.128.177:9090/messages/3000").success(function (data) {
                DataService.socialMediaMessages = data;
                $scope.socialMediaMessages=data;
            });
        }

        $scope.itemsPerPage = 20;
        $scope.currentPage = 0;

        $scope.prevPage = function() {
            if ($scope.currentPage > 0) {
                $scope.currentPage--;
            }
        };

        $scope.prevPageDisabled = function() {
            return $scope.currentPage === 0 ? "disabled" : "";
        };

        $scope.pageCount = function() {
            return Math.ceil($scope.socialMediaMessages.length/$scope.itemsPerPage)-1;
        };

        $scope.nextPage = function() {
            if ($scope.currentPage < $scope.pageCount()) {
                $scope.currentPage++;
            }
        };

        $scope.nextPageDisabled = function() {
            return $scope.currentPage === $scope.pageCount() ? "disabled" : "";
        };

        $scope.checkClass = function(opinion) {
        if(opinion=='POSITIVE') {
          return 'fa-thumbs-o-up';
        } else if(opinion=='NEGATIVE') {
          return 'fa-thumbs-o-down';
        }
      };
    });
