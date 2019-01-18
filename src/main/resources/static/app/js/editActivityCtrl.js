wafepaApp.controller("editActivityCtrl", function($scope, $routeParams, $http, $location,
		activityService) {
	
	var activityUrl = "/api/activities/" + $routeParams.id;
	
	$scope.activity = {};
	$scope.activity.name = "";
	
	activityService.getActivity($routeParams.id).then(
		function(data) {
			$scope.activity = data;
		}
	);
	
	$scope.edit = activityService.editActivity;
	
});