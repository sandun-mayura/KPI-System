app.controller('OverallAverageBugViewController', function ($scope, $http) {


    $scope.weekList = [];
    $scope.averageBugList = [];
    $scope.totalBugAvgBaselineList = [];
    $scope.totalBugAvgBaselineTargetList = [];


    //Unshift Array For Table
    $scope.monthList = [];
    $scope.weekListUnshift = [];
    $scope.teamListUnshift = [];
    $scope.totalBugListUnshift = [];
    $scope.averageBugListUnshift = [];
    $scope.totalBugAvgBaselineListUnshift = [];
    $scope.totalBugAvgBaselineTargetListUnshift = [];




    console.log($scope.notDeliveredBaselinePercentageList);

    var getGraphDataOverallAverageBug = function (teamId) {

        $http.get('api/reportView?teamId=' + teamId, {'cache': false}).then(function (response) {
            $scope.reportResults = response.data;
        });

        $http.get('api/calculateView?teamId=' + teamId, {'cache': false}).then(function (response) {
            $scope.results = response.data;


            for (var i = 0; i < $scope.results.length; i++) {

                // notDeliveredBaseline calculate


                $scope.teamListUnshift.push($scope.reportResults[i].team.teamMembers);
                $scope.totalBugListUnshift.push($scope.results[i].totalBugCount)
                $scope.averageBugList.push($scope.results[i].averageBug);
                $scope.averageBugListUnshift.push($scope.results[i].averageBug);
                $scope.totalBugAvgBaselineList.push($scope.results[i].totalBugAvgBaseline);
                $scope.totalBugAvgBaselineListUnshift.push($scope.results[i].totalBugAvgBaseline);
                $scope.totalBugAvgBaselineTargetList.push($scope.results[i].totalBugAvgBaselineTarget);
                $scope.totalBugAvgBaselineTargetListUnshift.push($scope.results[i].totalBugAvgBaselineTarget);
                $scope.monthList.push(moment($scope.results[i].date).format('MMM-YY'));//Get Month name
                $scope.weekList.push($scope.results[i].week);
                $scope.weekListUnshift.push($scope.results[i].week);

            }


            if ($scope.results.length > 0) {
                $scope.monthList.unshift("");
                $scope.weekListUnshift.unshift("Week");
                $scope.teamListUnshift.unshift("Team Members");
                $scope.totalBugListUnshift.unshift("Total Bug List");
                $scope.averageBugListUnshift.unshift("Average Bug List");
                $scope.totalBugAvgBaselineListUnshift.unshift("Average Baseline");
                $scope.totalBugAvgBaselineTargetListUnshift.unshift("Average Baseline Target");


            }

            $scope.OverallAverageBug = {
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
                            zoom: {
                                shared: true,
                                backgroundColor: "#96bec0"
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
                                    text: 'Overall Average Bug Report',
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
                                zooming: true,
                                item: {
                                    padding: "0 10 0 0",
                                    fontColor: "#000000",
                                    fontFamily: 'Montserrat',
                                }
                            },

                            scaleX: {
                                values: $scope.weekList,
                                zooming: true,

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
                                    text: "Average Bug",
                                    values: $scope.averageBugList


                                },
                                {
                                    text: "Total Bug Average Baseline",
                                    values: $scope.totalBugAvgBaselineList
                                },
                                {
                                    text: "Total Bug Average Baseline Target",
                                    values: $scope.totalBugAvgBaselineTargetList,
                                    lineStyle: "dashed"
                                }]
                        },


                        {

                            type: 'grid',
                            plot: {
                                layout: 'auto'
                            },
                            plotarea: {
                                margin: "60 75 40 20 "
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

                            options: {
                                headerRow: true,
                                colWidths: ["10%"],
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
                                    values: $scope.totalBugListUnshift,
                                },
                                {
                                    values: $scope.teamListUnshift
                                },
                                {
                                    values: $scope.averageBugListUnshift,
                                },
                                {
                                    values: $scope.totalBugAvgBaselineListUnshift,

                                }, {
                                    values: $scope.totalBugAvgBaselineTargetListUnshift,

                                }


                            ]
                        }

                    ]
            }

        });
    };

    $scope.showOverallAverageBug = function (teamId) {
        getGraphDataOverallAverageBug(teamId);
    };


});
