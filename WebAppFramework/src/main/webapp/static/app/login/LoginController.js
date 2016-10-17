app.controller('loginController', [
		'$scope',
		'$http',
		'$location',
		function($scope, $http, $location) {
			
			$scope.login = function() {
				$scope.loginModel = {};
				$scope.loginModel.userName=$scope.login.userName;
				$scope.loginModel.password=$scope.login.password;
				console.log($scope.loginModel);
				$http.post(
						window.location.origin + '/'
								+ window.location.href.split('/')[3]
								+ '/user/validate/', $scope.loginModel, {
							headers : {
								'Content-Type' : 'application/json',
								'Accept' : 'application/json'
							}
						}).then(function(response) {
							$location.path('/home');
				}, function errorCallback(response) {
					return response;
				});
			};

		} ]);