wafepaApp.controller("addActivityCtrl", function($scope, $http, $location) {
	
	var url = "/api/activities";
	
	$scope.newActivity = {};
	$scope.newActivity.name = "";
	
	$scope.add = function() {
		$http.post(url, $scope.newActivity).then(
			function success() {
				$location.path("/activities");
			},
			function error() {
				alert("Could not add activity!");
			}
		);
		
	}
	
});