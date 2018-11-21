app.config(function ($routeProvider) {
    $routeProvider

        .when('/QABugView', {
            templateUrl: 'report/QABugView.html'
        })
        .when('/OverallTimelineView', {
            templateUrl: 'report/OverallTimelineView.html',
            controller: 'OverallTimelineViewController'
        })
        .when('/OverallAverageBugView', {
            templateUrl: 'report/OverallAverageBugView.html',
            controller: 'OverallAverageBugViewController'
        })
        .when('/OverallProcedureMissesView', {
            templateUrl: 'report/OverallProcedureMissesView.html',
            controller: 'OverallProcedureMissesViewController'
        });


});
