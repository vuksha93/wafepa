wafepaApp.controller("editActivityCtrl", function($scope, $routeParams, $http, $location) {
	
	var activityUrl = "/api/activities/" + $routeParams.id;
	
	$scope.activity = {};
	$scope.activity.name = "";
	
	var getActivity = function() {
		
		var promise = $http.get(activityUrl);
		promise.then(
			function success(res) {
				$scope.activity = res.data;
			},
			function error(res) {
				alert("Could not fetch activity!");
			}
		);
	}
	
	getActivity();
	
	$scope.edit = function() {
		$http.put(activityUrl, $scope.activity).then(
			function success() {
				$location.path("/activities");
			},
			function error() {
				alert("Could not edit activity!");
			}
		);
	}
	
});