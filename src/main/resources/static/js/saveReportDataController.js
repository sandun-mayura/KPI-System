app.controller("saveController", function ($scope, $http) {
    $scope.save = function() {
        var teamId = localStorage.getItem('Team_Id');
var teamId=teamId;
    var data = {
        "team":teamId,
        "bugCount":$scope.qABug,
        "clientBug":$scope.clientBug,
        "onTimeDelivered":$scope.onTimeDelivered,
        "notDelivered":$scope.notDelivered,
        "misses":$scope.misses
    };

        $http.post("api/saveReportValues", data)
    .then(function(response) {
            // success
        alert("Data adding successfully")
        },
        function(response) { // optional
            // failed
            alert("Data adding fail!!")
        });}

    $("#divhead").load(" #divhead > *");
});