'use strict';

/* Controllers */

var olaControllers = angular.module('olaControllers', []);

olaControllers.controller('olaWeekendPlannerCtrl', ['$scope', 'Phone','Restangular',
  function($scope, Phone,Restangular) {

var baseBooking = Restangular.all('booking');
   $scope.shoppingMallList=[];
      $scope.saveShoppingMallsToList=function(sMall,t){
    $scope.shoppingMallList.push({name:sMall.name,time:t});
    $scope.location=sMall;
     }

     $scope.saveBookingData=function(){
        var  postObj={
          id:1,
          userName:"mailmrmanoj",
           startTime: "2012-11-10T18:30:00Z",
            endTime: "2012-11-10T18:30:00Z",
            lattitude:$scope.location.location[0],
            longitude:$scope.location.location[1],
            isRemoved:false
         }

     baseBooking.customPOST(postObj);
     }
    var markers = new L.FeatureGroup();
    $scope.malls=[
                            		    {name:"Ub City",location:[12.9715128,77.5942093]},
                            		    {name:"garuda mall",location:[12.9699951,77.607428]},
                            		    {name:"forum mall",location:[12.9344387,77.6090822]},
                            		    {name:"pheonix marketcity",location:[12.9969579,77.6940293]}
                            		];;
    $scope.cinemas=[
                                           {name:"inox value mall",location:[12.9595092,77.7457013]},
                                          	{name:"pvr forum mall",location:[12.9473114,77.5099069]},
                                          	{name:"fun cinemas",location:[12.9882352,77.5925242]},
                                          	{name:"fame shankarnag",location:[12.9744003,77.6074615]}
                                          	];;

    $scope.showShoppingList=()=>{
     markers.clearLayers();
       var shoppingList=[
           		    {name:"Ub City",location:[12.9715128,77.5942093]},
           		    {name:"garuda mall",location:[12.9699951,77.607428]},
           		    {name:"forum mall",location:[12.9344387,77.6090822]},
           		    {name:"pheonix marketcity",location:[12.9969579,77.6940293]}
           		];
           		for(var i=0,size=shoppingList.length;i<size;i++){
                    markers.addLayer(L.marker(shoppingList[i].location).bindPopup(shoppingList[i].name))
                }
                map.addLayer(markers)
    }
     $scope.showCinemaList=()=>{
          markers.clearLayers();
           var cinemaList=[
                        {name:"inox value mall",location:[12.9595092,77.7457013]},
                       	{name:"pvr forum mall",location:[12.9473114,77.5099069]},
                       	{name:"fun cinemas",location:[12.9882352,77.5925242]},
                       	{name:"fame shankarnag",location:[12.9744003,77.6074615]}
                       	];
               		for(var i=0,size=cinemaList.length;i<size;i++){
                          markers.addLayer(L.marker(cinemaList[i].location).bindPopup(cinemaList[i].name));
                    }
                    map.addLayer(markers)
        }

         $scope.showResortList=()=>{
                  markers.clearLayers();
                   var resortList=[
                                {name:"guhantara",location:[12.7999742,77.4894143]},
                               	{name:"mango mist",location:[12.7853355,77.6195624]},
                               	{name:"shakhti hill resort",location:[12.9115859,77.5268264]},
                               	{name:"elim resort",location:[12.7604486,77.4964831]}
                               	];
                       		for(var i=0,size=resortList.length;i<size;i++){
                                  markers.addLayer(L.marker(resortList[i].location).bindPopup(resortList[i].name));
                            }
                            map.addLayer(markers)
                }

    var map = L.map('map').setView([12.989980, 77.529264], 13);

    		L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6IjZjNmRjNzk3ZmE2MTcwOTEwMGY0MzU3YjUzOWFmNWZhIn0.Y8bhBaUMqFiPrDRW9hieoQ', {
    			maxZoom: 18,
    			attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, ' +
    				'<a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
    				'Imagery © <a href="http://mapbox.com">Mapbox</a>',
    			id: 'mapbox.streets'
    		}).addTo(map);

    		L.circle([12.995333, 77.511412], 500, {
    			color: 'red',
    			fillColor: '#f03',
    			fillOpacity: 0.5
    		}).addTo(map).bindPopup("My Current Location.");

    		L.polygon([
    			[12.942472, 77.556387],
    			[12.930760, 77.556730],
    			[12.952509, 77.571836]
    		]).addTo(map).bindPopup("I am a polygon.");


    		var popup = L.popup();

    		function onMapClick(e) {
    			popup
    				.setLatLng(e.latlng)
    				.setContent("You clicked the map at " + e.latlng.toString())
    				.openOn(map);
    		}

    		map.on('click', onMapClick);


  }]);

olaControllers.controller('PhoneDetailCtrl', ['$scope', '$routeParams', 'Phone',
  function($scope, $routeParams, Phone) {
    $scope.phone = Phone.get({phoneId: $routeParams.phoneId}, function(phone) {
      $scope.mainImageUrl = phone.images[0];
    });

    $scope.setImage = function(imageUrl) {
      $scope.mainImageUrl = imageUrl;
    };
  }]);
