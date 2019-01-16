wafepaApp.controller("editUserCtrl", function($scope, $http, $routeParams, $location) {
	
	var userUrl = "/api/users/" + $routeParams.id;
	
	$scope.user = {};
	$scope.user.email = "";
	$scope.user.firstName = "";
	$scope.user.lastName = "";
	$scope.user.password = "";
	$scope.user.repeatedPassword = "";
	
	var getUser = function() {
		
		var promise = $http.get(userUrl);
		promise.then(
			function success(res) {
				$scope.user = res.data;
			},
			function error(res) {
				alert("Could not fetch user!");
			}
		);
	}
	
	getUser();
	
	$scope.edit = function() {
		$http.put(userUrl, $scope.user).then(
				function success() {
					$location.path("/users");
				},
				function error() {
					alert("Could not edit user!");
				}
		);
	}
	
	
});