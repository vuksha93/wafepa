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