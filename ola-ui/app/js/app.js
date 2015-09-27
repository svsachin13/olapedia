'use strict';

/* App Module */

var olaApp = angular.module('olaApp', [
  'ngRoute','localytics.directives',
   'ui.bootstrap',
  'olaControllers',
   'phonecatServices'
]);

olaApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/ride', {
        templateUrl: 'partials/weekendActivities.html',
        controller: 'olaWeekendPlannerCtrl'
      }).

      otherwise({
        redirectTo: '/ride'
      });
  }]);
