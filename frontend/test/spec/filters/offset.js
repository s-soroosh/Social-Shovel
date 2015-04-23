'use strict';

describe('Filter: offset', function () {

  // load the filter's module
  beforeEach(module('zssApp'));

  // initialize a new instance of the filter before each test
  var offset;
  beforeEach(inject(function ($filter) {
    offset = $filter('offset');
  }));

  it('should return the input prefixed with "offset filter:"', function () {
    var text = 'angularjs';
    expect(offset(text)).toBe('offset filter: ' + text);
  });

});
