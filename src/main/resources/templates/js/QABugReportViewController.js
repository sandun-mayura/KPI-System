app.controller('QABugReportViewController', function ($scope, $http) {

    $scope.IsVisible = false;

    $scope.qaBugList = [];
    $scope.weekList = [];
    $scope.qaBaselineList = [];
    $scope.qaBaselineTargetList = [];
    $scope.notDeliveredList = [];
    $scope.notDeliveredBaselineList = [];
    $scope.notDeliveredBaselineTargetList = [];

    //Unshift Array For Table
    $scope.monthList = [];
    $scope.weekListUnshift = [];
    $scope.qaBugListUnshift = [];
    $scope.clientBugList = [];
    $scope.totalBugCountList = [];
    $scope.qaBaselineListUnshift = [];
    $scope.qaBaselineTargetListUnshift = [];


    var getGraphData1 = function (teamId) {

        $http.get('api/reportView?teamId=' + teamId, {'cache': false}).then(function (response) {
            $scope.reportResults = response.data;
        });

        $http.get('api/calculateView?teamId=' + teamId, {'cache': false}).then(function (response) {
            $scope.results = response.data;


            for (var i = 0; i < $scope.reportResults.length; i++) {
                $scope.clientBugList.push($scope.reportResults[i].clientBug);
            }


            for (var i = 0; i < $scope.results.length; i++) {
                $scope.qaBugList.push($scope.results[i].qaBug);
                $scope.qaBugListUnshift.push($scope.results[i].qaBug);
                $scope.totalBugCountList.push($scope.results[i].totalBugCount);
                $scope.qaBaselineList.push($scope.results[i].qaBaseline);
                $scope.qaBaselineListUnshift.push($scope.results[i].qaBaseline);
                $scope.qaBaselineTargetList.push($scope.results[i].qaBaselineTarget);
                $scope.qaBaselineTargetListUnshift.push($scope.results[i].qaBaselineTarget);
                $scope.monthList.push(moment($scope.results[i].date).format('MMM-YY'));//Get Month name
                $scope.weekList.push($scope.results[i].week);
                $scope.weekListUnshift.push($scope.results[i].week);
            }



            if ($scope.results.length > 0) {
                $scope.monthList.unshift("");
                $scope.weekListUnshift.unshift("Week");
                $scope.qaBugListUnshift.unshift("QA Bug");
                $scope.clientBugList.unshift("Client Bug")
                $scope.totalBugCountList.unshift("Total Bug Count");
                $scope.qaBaselineListUnshift.unshift("QA Baseline");
                $scope.qaBaselineTargetListUnshift.unshift("QA Baseline Target");

            }
            $scope.graphData = {

                gui: {
                    contextMenu: {
                        position: "right",
                        backgroundColor: "#045659", /*sets background for entire contextMenu*/
                        button: {
                            visible: true,
                            lineColor: "#000000",
                            backgroundColor: "#045659"
                        },
                        item: {
                            backgroundColor: "#045659",
                            borderColor: "#96bec0",
                            borderWidth: 1,
                            color: "#fff"
                        },
                        gear: {

                            alpha: 1
                        }
                    }
                },

                graphset:
                    [
                        {
                            type: 'line',
                            zoom:{
                                shared:true,
                                backgroundColor:"#96bec0"
                            },
                            tooltip: {
                                visible: true,
                                text: "Week %kl<br><span style='color:%color'>%t: </span>%v<br>",
                                backgroundColor: "white",
                                borderColor: "#e3e3e3",
                                borderRadius: "5px",
                                bold: true,
                                fontSize: "12px",
                                fontColor: "#2f2f2f",
                                textAlign: 'left',
                                padding: '15px',
                                shadow: true,
                                shadowAlpha: 0.2,
                                shadowBlur: 5,
                                shadowDistance: 4,
                                shadowColor: "#a1a1a1"
                            },

                            plotArea: {
                                margin: "10 20 20 20"
                            },
                            plot: {
                                animation: {
                                    effect: "ANIMATION_EXPAND_LEFT",
                                    delay: 20,
                                    speed: "ANIMATION_SLOW"
                                }
                            },
                            globals: {
                                fontFamily: 'Lato'
                            },
                            noData: {
                                text: "Currently there Is no data In the chart",
                                backgroundColor: "#20b2db",
                                fontSize: 18,
                                textAlpha: .9,
                                alpha: .6,
                                bold: true
                            },
                            legend: {
                                layout: "h",
                                align: "center",
                                verticalAlign: "bottom",
                                borderWidth: "1",
                                item: {
                                    padding: "0 10 0 0",
                                    fontColor: "#000000",
                                    fontFamily: 'Lato'
                                },
                                marker: {
                                    showLine: true,
                                    type: "circle"
                                },
                            },
                            title:
                                {
                                    text: 'QA Bugs Report',
                                    color: "#000000",
                                    fontFamily: 'Lato',
                                    marginTop: 20
                                },
                            subtitle: {
                                color: "#045659",
                                text: "Team : " + $scope.reportResults[0].team.teamName,
                                size: "24",
                                fontFamily: 'Lato',
                                marginTop: 10
                            },
                            scaleY: {
                                item: {
                                    padding: "0 10 0 0",
                                    fontColor: "#000000",
                                    fontFamily: 'Montserrat',
                                    zooming:true,
                                }
                            },

                            scaleX: {
                                values: $scope.weekList,
                                zooming:true,

                                item: {
                                    padding: "0 10 0 0",
                                    fontColor: "#000000",
                                    fontFamily: 'Montserrat',
                                },
                                label: {
                                    text: "Week(s)",
                                    fontSize: 12,
                                    fontFamily: 'Lato'
                                }
                            },
                            series: [
                                {
                                    text: "QA Bug",
                                    values: $scope.qaBugList,
                                    color: "red"
                                },
                                {
                                    text: "QA Baseline",
                                    values: $scope.qaBaselineList
                                },
                                {
                                    text: "QA Baseline Target",
                                    values: $scope.qaBaselineTargetList,
                                    lineStyle: "dashed"
                                }]
                        },


                        {

                            type: 'grid',

                            globals: {
                                fontFamily: 'Lato'
                            },
                            plotarea: {
                                margin: "60 75 40 20 "
                            },

                            noData: {
                                text: "Currently there Is no data In the chart",
                                backgroundColor: "#20b2db",
                                fontSize: 18,
                                textAlpha: .9,
                                alpha: .6,
                                bold: true
                            },
                            options: {
                                headerRow: true,
                                colLabels: $scope.monthList,
                                style: {
                                    ".th": {
                                        y: "0px",
                                        backgroundColor: "#045659",
                                        fontColor: "#fff",
                                        fontSize: "15",
                                        fontWeight: "none",
                                        fontFamily: 'Lato',
                                        height: "40px",
                                        align: "center"
                                    },
                                    ".td": {
                                        y: "0px",
                                        backgroundColor: "#96bec0",
                                        fontColor: "#000000",
                                        fontFamily: 'Lato',
                                        fontSize: "13",
                                        fontWeight: "none",
                                        height: "40px",
                                        align: "center"
                                    }
                                }

                            },
                            series: [
                                {
                                    values: $scope.weekListUnshift,

                                },
                                {
                                    values: $scope.qaBugListUnshift
                                },
                                {
                                    values: $scope.clientBugList,
                                },
                                {
                                    values: $scope.totalBugCountList,
                                },
                                {
                                    values: $scope.qaBaselineListUnshift,

                                },
                                {
                                    values: $scope.qaBaselineTargetListUnshift,
                                }


                            ]
                        }

                    ]
            }

        });
    };

    $scope.show = function (teamId) {
        getGraphData1(teamId);
    };


});
