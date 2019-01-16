wafepaApp.controller("recordsCtrl", function($scope, $http) {
	
	var recordsUrl = "/api/records"
	var usersUrl = "/api/users"
	var activitiesUrl = "/api/activities"
	
	$scope.records = [];
	$scope.users = [];
	$scope.activities = [];
	
	$scope.newRecord = {};
	$scope.newRecord.time = "";
	$scope.newRecord.duration = "";
	$scope.newRecord.intensity = "";
	$scope.newRecord.userId = "";
	$scope.newRecord.activityId = "";
	
	
	var getRecords = function() {
		$http.get(recordsUrl).then(
			function success(res) {
				$scope.records = res.data;
			},
			function error(res) {
				alert("Could not fetch records!")
			}
		);
		
	}
	
	getRecords();
	
	var getUsers = function() {
		var promise = $http.get(usersUrl);
		promise.then(
			function success(res) {
				$scope.users = res.data;
			},
			function error(res) {
				alert("Could not fetch users!");
			}
		);
		
	}
	
	getUsers();
	
	var getActivities = function() {
		
		var promise = $http.get(activitiesUrl);
		promise.then(
			function success(res) {
				$scope.activities = res.data;
			},
			function error(res) {
				$scope.activities = [];
				alert("Could not fetch activities!");
			}
		);
		
	}
	
	getActivities();
	
	$scope.doAdd = function() {
//		console.log($scope.newRecord);
		$http.post(recordsUrl, $scope.newRecord).then(
			function success() {
				getRecords();
				
				$scope.newRecord.time = "";
				$scope.newRecord.duration = "";
				$scope.newRecord.intensity = "";
				$scope.newRecord.userId = "";
				$scope.newRecord.activityId = "";
			},
			function error() {
				alert("Could not add record!");
			}
		);
	}
	
});