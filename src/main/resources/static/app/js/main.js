var wafepaApp = angular.module('wafepaApp', ["ngRoute"]);

wafepaApp.controller('MyController', function($scope) {
	$scope.message = "Hello JWD!";
});

wafepaApp.controller("activitiesCtrl", function($scope, $http, $location) {
	
	var url = "/api/activities";
	
	$scope.activities = [];
	
	var getActivities = function() {
		
		var promise = $http.get(url);
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
	
	$scope.goToEdit = function(id) {
		$location.path("/activities/edit/" + id);
	}
	
	$scope.deleteActivity = function(id) {
		
		$http.delete("api/activities/" + id).then(
			function success() {
				getActivities();
			},
			function error() {
				alert("Could not delete activity!");
			}
		);	
		
	}
	
	$scope.view = function(id) {
		$location.path("/activities/view/" + id);
	}
	
	$scope.goToAdd = function() {
		$location.path("/activities/add");
	}
	
	$scope.reverse = function() {
		$scope.activities.reverse();
	}
	
});

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

wafepaApp.controller("viewUserCtrl", function($scope, $http, $routeParams) {
	
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

wafepaApp.controller("usersCtrl", function($scope, $http, $location) {
	
	var url = "/api/users?page=0";
	
	$scope.users = [];
	
	var getUsers = function() {
		var promise = $http.get(url);
		promise.then(
			function success(res) {
				$scope.users = res.data;
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
	
});

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

wafepaApp.controller("countriesCtrl", function($scope, $http) {
	
	var url = "https://restcountries.eu/rest/v2/all";
	
	$scope.countries = [];
	
	var getCountries = function() {
		var promise = $http.get(url);
		promise.then(
			function success(res) {
				$scope.countries = res.data;
			},
			function error(res) {
				alert("Could not fetch countries!")
			}
		);
	}
	
	getCountries();
	
});

wafepaApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/html/home.html'
		})
		.when('/activities', {
			templateUrl : '/app/html/activities.html'
		})
		.when('/activities/edit/:id', {
			templateUrl : '/app/html/edit_activity.html'
		})
		.when('/activities/view/:id', {
			templateUrl : '/app/html/view_activity.html'
		})
		.when('/activities/add', {
			templateUrl : '/app/html/add_activity.html'
		})
		.when('/users', {
			templateUrl : '/app/html/users.html'
		})
		.when('/users/edit/:id', {
			templateUrl : '/app/html/edit_user.html'
		})
		.when('/users/add', {
			templateUrl : '/app/html/add_user.html'
		})
		.when('/countries', {
			templateUrl : '/app/html/countries.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);