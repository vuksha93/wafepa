wafepaApp.controller("editRecordCtrl", function($scope, $http, $routeParams, $location) {
	
	var recordUrl = "/api/records/" + $routeParams.id;
//	var usersUrl = "/api/users"
//	var activitiesUrl = "/api/activities"
	
//	$scope.users = [];
//	$scope.activities = [];	
		
	$scope.record = {};
	$scope.record.time = "";
	$scope.record.duration = "";
	$scope.record.intensity = "";
	$scope.record.userId = "";
	$scope.record.activityId = "";
	$scope.record.userName = "";
	$scope.record.activityName = "";
	
	var getRecord = function() {
		$http.get(recordUrl).then(
			function success(res) {
				$scope.record = res.data;
			},
			function error(res) {
				alert("Could not fetch record!");
			}
		);
	}
	
	getRecord();
	
//	var getUsers = function() {
//		var promise = $http.get(usersUrl);
//		promise.then(
//			function success(res) {
//				$scope.users = res.data;
//			},
//			function error(res) {
//				alert("Could not fetch users!");
//			}
//		);
//		
//	}
//	
//	getUsers();
//	
//	var getActivities = function() {
//		
//		var promise = $http.get(activitiesUrl);
//		promise.then(
//			function success(res) {
//				$scope.activities = res.data;
//			},
//			function error(res) {
//				$scope.activities = [];
//				alert("Could not fetch activities!");
//			}
//		);
//		
//	}
//	
//	getActivities();
	
	$scope.edit = function() {
//		console.log($scope.record);
		$http.put(recordUrl, $scope.record).then(
			function success() {
				$location.path("/records");
			},
			function error() {
				alert("Could not edit record!");
			}
		);
	}
	
});