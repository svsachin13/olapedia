'use strict';

/* App Module */

var olaApp = angular.module('olaApp', [
  'ngRoute','localytics.directives',
   'ui.bootstrap',
  'olaControllers',
   'phonecatServices',
   'restangular'
]);

olaApp.config(['$routeProvider','RestangularProvider',
  function($routeProvider,RestangularProvider) {
    $routeProvider.
      when('/ride', {
        templateUrl: 'partials/weekendActivities.html',
        controller: 'olaWeekendPlannerCtrl'
      }).

      otherwise({
        redirectTo: '/ride'
      });

   RestangularProvider.setBaseUrl('http://localhost:8080');

  }]);
