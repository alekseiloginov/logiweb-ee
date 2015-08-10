<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
  <meta charset="utf-8">
  <title>Waypoints in directions</title>
  <style>
    html, body, #map-canvas {
      height: 100%;
      margin: 0;
      padding: 0;
    }

    #panel {
      position: absolute;
      top: 5px;
      left: 50%;
      margin-left: -180px;
      z-index: 5;
      background-color: #fff;
      padding: 5px;
      border: 1px solid #999;
    }

    /*
    Provide the following styles for both ID and class,
    where ID represents an actual existing "panel" with
    JS bound to its name, and the class is just non-map
    content that may already have a different ID with
    JS bound to its name.
    */

    #panel, .panel {
      font-family: 'Roboto','sans-serif';
      line-height: 30px;
      padding-left: 10px;
    }

    #panel select, #panel input, .panel select, .panel input {
      font-size: 15px;
    }

    #panel select, .panel select {
      width: 100%;
    }

    #panel i, .panel i {
      font-size: 12px;
    }

  </style>
  <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true"></script>
  <script>
    var directionsDisplay;
    var directionsService = new google.maps.DirectionsService();
    var map;

    function initialize() {
      directionsDisplay = new google.maps.DirectionsRenderer();
      var chicago = new google.maps.LatLng(41.850033, -87.6500523);
      var mapOptions = {
        zoom: 6,
        center: chicago
      }
      map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
      directionsDisplay.setMap(map);
    }

    function calcRoute() {
            var waypts = [];
            var checkboxArray = ${waypoints};
            for (var i = 0; i < checkboxArray.length; i++) {
              waypts.push({
                location:checkboxArray[i]
//                , stopover:true
              });
            }

            var request = {
              origin: "${mainCity}",
              destination: "${mainCity}",
//              waypoints: [{location:"Moscow"}, {location:"Peterburg"}],
              waypoints: waypts,
              optimizeWaypoints: true,
              travelMode: google.maps.TravelMode.DRIVING
            };

      directionsService.route(request, function(response, status) {
        if (status == google.maps.DirectionsStatus.OK) {
          directionsDisplay.setDirections(response);
          var route = response.routes[0];
          var summaryPanel = document.getElementById('directions_panel');
          summaryPanel.innerHTML = '';
          // For each route, display summary information.
          for (var i = 0; i < route.legs.length; i++) {
            var routeSegment = i + 1;
            summaryPanel.innerHTML += '<b>Route Segment: ' + routeSegment + '</b><br>';
            summaryPanel.innerHTML += route.legs[i].start_address + ' to ';
            summaryPanel.innerHTML += route.legs[i].end_address + '<br>';
            summaryPanel.innerHTML += route.legs[i].distance.text + '<br>';
            summaryPanel.innerHTML += route.legs[i].duration.text + '<br><br>';
          }
        }
      });
    }

    google.maps.event.addDomListener(window, 'load', initialize);

  </script>
</head>
<body onload="calcRoute();">
<div id="map-canvas" style="float:left;width:70%;height:100%;"></div>
<div id="control_panel" style="width:20%;float:left;text-align:left;padding-top:20px" class="panel">

  <div id="directions_panel" class="panel" style="margin:20px;background-color:#FFEE77;"></div>

  <button onclick="goBack()" style="margin:20px;">Go Back</button>

  <script>
    function goBack() {
      window.history.back();
    }
  </script>
</div>
</body>
</html>