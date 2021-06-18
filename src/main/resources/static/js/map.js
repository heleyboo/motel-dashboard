
// Initialize and add the map
function initMap() {

	let geoCodeUrl = `https://maps.googleapis.com/maps/api/geocode/json?address=${fullAddress}&key=AIzaSyAXxvVzSSOfKjHOcCipX7WTNMBU5kba7D4`;
	console.log(geoCodeUrl);

	//const directionsService = new google.maps.DirectionsService();
	//const directionsRenderer = new google.maps.DirectionsRenderer({ map: map });



	// The location of Uluru
	const uluru = { lat: -25.344, lng: 131.036 };
	// The map, centered at Uluru


	$.getJSON(geoCodeUrl, function(data) {

		// data
		console.log(data);
		let mapData = data['results'][0];

		const map = new google.maps.Map(document.getElementById("map"), {
			zoom: 17,
			center: mapData.geometry.location
		});
		const marker = new google.maps.Marker({
			position: mapData.geometry.location,
			map: map,
		});
		const infowindow = new google.maps.InfoWindow();
		infowindow.setContent(mapData.formatted_address);
        infowindow.open(map, marker);
	});
}