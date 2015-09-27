		var map = L.map('map').setView([12.989980, 77.529264], 13);

		L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6IjZjNmRjNzk3ZmE2MTcwOTEwMGY0MzU3YjUzOWFmNWZhIn0.Y8bhBaUMqFiPrDRW9hieoQ', {
			maxZoom: 18,
			attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, ' +
				'<a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
				'Imagery © <a href="http://mapbox.com">Mapbox</a>',
			id: 'mapbox.streets'
		}).addTo(map);

		var shoppingList=[
		    {name:"Ub City",location:[12.9715128,77.5942093]},
		    {name:"garuda mall",location:[12.9699951,77.607428]},
		    {name:"forum mall",location:[12.9344387,77.6090822]},
		    {name:"pheonix marketcity",location:[12.9969579,77.6940293]}
		];

		var cinemaList=[
            {name:"inox value mall",location:[12.9595092,77.7457013]},
        	{name:"pvr forum mall",location:[12.9473114,77.5099069]},
        	{name:"fun cinemas",location:[12.9882352,77.5925242]},
        	{name:"fame shankarnag",location:[12.9744003,77.6074615]}
        	];

        for(var i=0,size=shoppingList.length;i<size;i++){
            L.marker(shoppingList[i].location).addTo(map)
            			.bindPopup(shoppingList[i].name);
        }

		L.circle([12.995333, 77.511412], 500, {
			color: 'red',
			fillColor: '#f03',
			fillOpacity: 0.5
		}).addTo(map).bindPopup("Your current location");

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