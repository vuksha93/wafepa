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
				alert("Could not fetch activities!");
			}
		);
		
	}
	
	getActivities();
	
	$scope.goToEdit = function(id) {
		$location.path("/activities/edit/" + id);
	}
	
	$scope.goToAdd = function() {
		$location.path("/activities/add");
	}
	
	$scope.deleteActivity = function(id) {
		
		$http.delete("api/activities/" + id).then(
			function success() {
				$location.path("/activities/delete/" + id);
			},
			function error() {
				alert("Could not delete activity!");
			}
		);	
		
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
		.when('/activities/add', {
			templateUrl : '/app/html/add_activity.html'
		})
		.when('/activities/delete/:id', {
			templateUrl : '/app/html/activities.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);