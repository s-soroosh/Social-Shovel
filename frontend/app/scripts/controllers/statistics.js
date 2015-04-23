'use strict';

/**
 * @ngdoc function
 * @name zssApp.controller:StatisticsCtrl
 * @description
 * # StatisticsCtrl
 * Controller of the zssApp
 */
angular.module('zssApp')
  .controller('StatisticsCtrl', function ($scope,DataService) {
      $scope.labels = [];
      $scope.data = [];
      $scope.type = 'PolarArea';
      $scope.trendsData=DataService.trendsData;
      $scope.socialMediaMessages = DataService.socialMediaMessages;

      $scope.trendsData.forEach(function(item,index) {
        $scope.data.push(item.posts);
        $scope.labels.push(item.name);
      });

      $scope.zLabels = ["January", "February", "March", "April", "May", "June", "July"];
      $scope.series = ['Zalando satisfaction rate'];
      $scope.zData = [
        [65, 59, 80, 81, 56, 55, 40]
      ];
      $scope.onClick = function (points, evt) {
        console.log(points, evt);
      };

      $scope.itemsPerPage = 5;
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
        return Math.ceil($scope.trendsData.length/$scope.itemsPerPage)-1;
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

      $scope.toggle = function () {
        $scope.type = $scope.type === 'PolarArea' ?
            'Pie' : 'PolarArea';
      };
  });
