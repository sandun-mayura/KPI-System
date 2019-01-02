var app = angular.module('myApp', ['zingchart-angularjs']);
app.controller("MainController", function ($scope, $http) {

    $scope.team = function (selected_team) {
        var result = selected_team.split('+');
        var teamId=result[0];
        var teamName=result[1];
        localStorage.setItem('Team_Id', teamId);
        localStorage.setItem('Team_Name', teamName);
        history.go(0);

    }

    $scope.swtichTeam = function () {
        localStorage.removeItem('Team_Id');
        localStorage.removeItem('Team_Name');
        window.location.href = '/';
    }

    //set session name
    var teamName = "Active Team : "+ localStorage.getItem('Team_Name');
    document.getElementById("TName").innerHTML = teamName;


    $scope.teams = [{
        teamId: '1',
        label: 'Cronus'
    },
    {
        teamId: '2',
        label: 'Appollo'
    }, {
        teamId: '3',
        label: 'Nimess'
    }];
   // console.log($scope.RequestTypeDescription = $scope.selected_team.label)

});





