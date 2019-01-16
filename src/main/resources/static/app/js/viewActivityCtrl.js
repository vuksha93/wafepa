wafepaApp.controller("viewActivityCtrl", function($scope, $http, $routeParams) {
	
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
	
});