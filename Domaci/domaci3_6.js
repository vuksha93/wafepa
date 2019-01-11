var app = angular.module("domaci3.6_App", []);

app.controller("appController", function($scope) {
	
	$scope.name = "";
	
	$scope.disableInsert = true;
	
	$scope.deleteInsert = function(){
		
		$scope.name = "";
		
	};
	
});