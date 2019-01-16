var wafepaApp = angular.module('wafepaApp', ["ngRoute"]);

wafepaApp.controller('MyController', function($scope) {
	$scope.message = "Hello JWD!";
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
		.when('/records', {
			templateUrl : '/app/html/records.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);