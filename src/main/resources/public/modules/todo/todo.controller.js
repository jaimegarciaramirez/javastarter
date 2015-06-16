angular.module('starter').controller('todoController', ['$scope', '$http', function($scope, $http) {
	$scope.myField = 'haha!';
	$scope.PassObject = function() { 
		$http.post('/AddTask', $scope.myField).then(
			function(response) {
				console.log(response);
				$scope.ListTasks();
			}
		);
	};
	
	$scope.ListTasks = function() {
		$http.get('/listTasks').then(
			function(response) {
				console.log(response);
				$scope.tasks = response.data;
			}
		);
	};
	$scope.ListTasks();
}])