wafepaApp.service("activityService", function($http, $location) {

	
	this.getActivity = function(id) {
		return $http.get("/api/activities/" + id).then(
			function success(res) {
				return res.data;
			},
			function error(res) {
				alert("Could not fetch activity!");
				return null;
			}
		);
	}

	this.getActivities = function() {
		return $http.get("/api/activities/").then(
			function success(res) {
				return res.data;
			},
			function error(res) {
				return [];
			}
		);
		
	}
	
	this.addActivity = function(newActivity) {
		$http.post("/api/activities", newActivity).then(
			function success() {
				$location.path("/activities");
			},
			function error() {
				alert("Could not add activity!");
			}
		);
			
	}
	
	this.editActivity = function(activity) {
		$http.put("/api/activities/" + activity.id, activity).then(
			function success() {
				$location.path("/activities");
			},
			function error() {
				alert("Could not edit activity!");
			}
		);
	}
	
	this.deleteActivity = function(id) {
		return $http.delete("api/activities/" + id).then(
			function success() {
			},
			function error() {
				alert("Could not delete activity!");
			}
		);	
		
	}
	
});