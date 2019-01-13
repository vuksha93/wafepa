var app = angular.module("domaci3.6_App", []);

app.controller("appController", function($scope) {
	
	$scope.name = "";
	
	$scope.deleteInsert = function(){
		
		$scope.name = "";
		
	};
	
});