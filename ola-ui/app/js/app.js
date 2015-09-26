'use strict';

/* App Module */

var phonecatApp = angular.module('phonecatApp', [
  'ngRoute','localytics.directives',
  'phonecatAnimations',
   'ui.bootstrap',
  'phonecatControllers',
  'phonecatFilters',
  'phonecatServices'
]);

phonecatApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/ride', {
        templateUrl: 'partials/phone-list.html',
        controller: 'PhoneListCtrl'
      }).
      when('/ride/:phoneId', {
        templateUrl: 'partials/phone-detail.html',
        controller: 'PhoneDetailCtrl'
      }).
      otherwise({
        redirectTo: '/ride'
      });
  }]);
