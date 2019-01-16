wafepaApp.controller("usersCtrl", function($scope, $http, $location) {
	
	var url = "/api/users";
	
	$scope.users = [];
	
	var getUsers = function() {
		var promise = $http.get(url);
		promise.then(
			function success(res) {
				$scope.users = res.data;
			},
			function error(res) {
//				$scope.users = [];
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
	
});