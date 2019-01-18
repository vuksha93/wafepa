wafepaApp.controller("usersCtrl", function($scope, $http, $location) {
	
	var url = "/api/users";
	
	$scope.users = [];
	
	$scope.searchParams = {};
	$scope.searchParams.name = "";
	
	$scope.page = 0;
	$scope.totalPages = 1;
	
	var getUsers = function() {
		
		var config = {params: {}};
		
		if($scope.searchParams.name != ""){
			config.params.name = $scope.searchParams.name; 
		}
		
		config.params.page = $scope.page;
		
		var promise = $http.get(url, config);
		promise.then(
			function success(res) {
				$scope.users = res.data;
				$scope.totalPages = res.headers("totalPages");
			},
			function error(res) {
				$scope.users = [];
				alert("Could not fetch users!");
			}
		);
		
	}
	
	getUsers();
	
	$scope.goToEdit = function(id) {
		$location.path("users/edit/" + id);
	}
	
	$scope.goToAdd = function() {
		$location.path("users/add");
	}
	
	$scope.deleteUser = function(id) {
		$http.delete("api/users/" + id).then(
			function success() {
				getUsers();
			},
			function error() {
				alert("Could not delete user!");
			}
		);
	}
	
	$scope.search = function() {
		//console.log($scope.searchParams);
		$scope.page = 0;
		getUsers();
	}
	
	$scope.changePage = function(direction) {
		$scope.page = $scope.page + direction;
		getUsers();
	}
	
});