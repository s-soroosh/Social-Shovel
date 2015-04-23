'use strict';

describe('Controller: StatisticsCtrl', function () {

  // load the controller's module
  beforeEach(module('zssApp'));

  var StatisticsCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    StatisticsCtrl = $controller('StatisticsCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
