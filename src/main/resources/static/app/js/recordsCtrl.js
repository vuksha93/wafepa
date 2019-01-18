wafepaApp.controller("recordsCtrl", function($scope, $http, $location) {
	
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
	
	$scope.searchParams = {};
	$scope.searchParams.activityName = "";
	$scope.searchParams.minimumDuration = "";
	$scope.searchParams.intensity = "";
	
	$scope.page = 0;
	$scope.totalPages = 1;
	
	$scope.showForm = false;
	
	var getRecords = function() {
		
		var config = {params: {}};
		
		if($scope.searchParams.activityName != ""){
			config.params.activityName = $scope.searchParams.activityName;
		}
		
		if($scope.searchParams.minimumDuration != ""){
			config.params.minDuration = $scope.searchParams.minimumDuration;
		}
		
		if($scope.searchParams.intensity != ""){
			config.params.intensity = $scope.searchParams.intensity;
		}
		
		config.params.page = $scope.page;
		
		$http.get(recordsUrl, config).then(
			function success(res) {
				$scope.records = res.data;
				$scope.totalPages = res.headers("totalPages");
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
	
	$scope.goToEdit = function(id) {
		$location.path("/records/edit/" + id);
	}
	
	$scope.search = function() {
		//console.log($scope.searchParams);
		$scope.page = 0;
		getRecords();
	}
	
	$scope.changePage = function(direction) {
		$scope.page = $scope.page + direction;
		getRecords();
	}
	
	$scope.fastSearch = function() {
		$scope.searchParams.minimumDuration = 30;
		$scope.page = 0;
		getRecords();
	}
	
});