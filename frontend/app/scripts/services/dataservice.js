'use strict';

/**
 * @ngdoc service
 * @name zssApp.DataService
 * @description
 * # DataService
 * Service in the zssApp.
 */
angular.module('zssApp')
  .service('DataService', function () {
    // AngularJS will instantiate a singleton by calling "new" on this function
      this.data=[0,0,0];
      this.countryData=[0,0,0];
  });
