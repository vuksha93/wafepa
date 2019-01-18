wafepaApp.controller("activitiesCtrl", function($scope, $http, $location, activityService) {
	
	var url = "/api/activities";
	
	$scope.activities = [];
	$scope.showAlert = false;
	
	var getActivities = function() {
	
		activityService.getActivities().then(
			function(data) {
				$scope.activities = data;
				$scope.showAlert = data.length == 0;
			}	
		);
	}
	getActivities();
	
	$scope.goToEdit = function(id) {
		$location.path("/activities/edit/" + id);
	}
	
	$scope.deleteActivity = function(id) {
		activityService.deleteActivity(id).then(getActivities);
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