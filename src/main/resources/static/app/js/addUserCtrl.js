wafepaApp.controller("addUserCtrl", function($scope, $location, $http) {
	
	var url = "/api/users";
	
	$scope.newUser = {};
	$scope.newUser.email = "";
	$scope.newUser.firstName = "";
	$scope.newUser.lastName = "";
	$scope.newUser.password = "";
	$scope.newUser.repeatedPassword = "";
	
	$scope.add = function() {
		$http.post(url, $scope.newUser).then(
			function success() {
				$location.path("/users");
			},
			function error() {
				alert("Could not add user!");
			}
		);
	}
	
});