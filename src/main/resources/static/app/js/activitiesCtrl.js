wafepaApp.controller("activitiesCtrl", function($scope, $http, $location) {
	
	var url = "/api/activities";
	
	$scope.activities = [];
	$scope.showAlert = false;
	
	var getActivities = function() {
		
		var promise = $http.get(url);
		promise.then(
			function success(res) {
				$scope.activities = res.data;
			},
			function error(res) {
				$scope.activities = [];
				$scope.showAlert = true;
//				alert("Could not fetch activities!");
			}
		);
		
	}
	
	getActivities();
	
	$scope.goToEdit = function(id) {
		$location.path("/activities/edit/" + id);
	}
	
	$scope.deleteActivity = function(id) {
		
		$http.delete("api/activities/" + id).then(
			function success() {
				getActivities();
			},
			function error() {
				alert("Could not delete activity!");
			}
		);	
		
	}
	
	$scope.view = function(id) {
		$location.path("/activities/view/" + id);
	}
	
	$scope.goToAdd = function() {
		$location.path("/activities/add");
	}
	
	$scope.reverse = function() {
		$scope.activities.reverse();
	}
	
});