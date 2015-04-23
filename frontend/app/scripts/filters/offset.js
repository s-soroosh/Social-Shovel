'use strict';

/**
 * @ngdoc filter
 * @name zssApp.filter:offset
 * @function
 * @description
 * # offset
 * Filter in the zssApp.
 */
angular.module('zssApp')
    .filter('offset', function() {
      return function(input, start) {
        start = parseInt(start, 10);
        return input.slice(start);
      };
    });
