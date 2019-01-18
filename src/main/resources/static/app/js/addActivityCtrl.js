wafepaApp.controller("addActivityCtrl", function($scope, $http, $location, activityService) {
	
	$scope.newActivity = {};
	$scope.newActivity.name = "";
	
	$scope.add = activityService.addActivity;
	
});