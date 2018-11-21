app.controller("MainController", function ($scope, $http) {
    console.log("main")
    $scope.save = function(qABug,clientBug,onTimeDelivered,notDelivered,misses) {
        console.log($scope.qABug)
    var data = {
        "team":3,
        "bugCount":$scope.qABug,
        "clientBug":$scope.clientBug,
        "onTimeDelivered":$scope.onTimeDelivered,
        "notDelivered":$scope.notDelivered,
        "misses":$scope.misses
    };

        $http.post("api/saveReportValues", data)
    }
});