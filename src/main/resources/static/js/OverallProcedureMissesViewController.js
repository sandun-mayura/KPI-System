app.controller('OverallProcedureMissesViewController', function ($scope, $http) {


    $scope.weekList = [];
    $scope.missesList = [];
    $scope.missesBaselineList = [];
    $scope.missesBaselineTargetList = [];


    //Unshift Array For Table

    $scope.monthList = [];
    $scope.weekListUnshift = [];
    $scope.missesListUnshift = [];
    $scope.missesBaselineListUnshift = [];
    $scope.missesBaselineTargetListUnshift = [];


    var getOverallProcedureMisses = function () {
        var teamId = localStorage.getItem('Team_Id');
        if(teamId==null || teamId==="undefined") {
            window.location.href = '/';
        }


        $http.get('/api/reportView?teamId=' + teamId, {'cache': false}).then(function (response) {
            $scope.reportResults = response.data;



        $http.get('/api/calculateView?teamId=' + teamId, {'cache': false}).then(function (response) {
            $scope.results = response.data;


            for (var i = 0; i < $scope.results.length; i++) {

                // notDeliveredBaseline calculate


                $scope.missesList.push($scope.reportResults[i].misses);
                $scope.missesListUnshift.push($scope.reportResults[i].misses);
                $scope.missesBaselineList.push($scope.results[i].missesBaseline);
                $scope.missesBaselineListUnshift.push($scope.results[i].missesBaseline);
                $scope.missesBaselineTargetList.push($scope.results[i].missesBaselineTarget);
                $scope.missesBaselineTargetListUnshift.push($scope.results[i].missesBaselineTarget);

                $scope.monthList.push(moment($scope.results[i].date).format('MMM-YY'));//Get Month name
                $scope.weekList.push($scope.results[i].week);
                $scope.weekListUnshift.push($scope.results[i].week);

            }


            if ($scope.results.length > 0) {
                $scope.monthList.unshift("");
                $scope.weekListUnshift.unshift("Week");
                $scope.missesListUnshift.unshift("Misses");
                $scope.missesBaselineListUnshift.unshift("Misses Baseline");
                $scope.missesBaselineTargetListUnshift.unshift("Misses Baseline Target");
            }

            $scope.OverallProcedureMisses = {
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
                            type: 'mixed',
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
                                    effect: "4",
                                    method:"4",
                                    sequence:"1",
                                    speed:"1000 "
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
                                    text: ' Overall Procedure Misses Report',
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
                                label: {
                                    text: 'Misses',
                                    fontFamily: 'Lato',
                                    fontSize:14
                                },
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
                                    type: 'bar',
                                    text: "Misses",
                                    backgroundColor: '#96bec0',
                                    values: $scope.missesList


                                },
                                {
                                    type: 'line',
                                    text: "Misses Baseline",
                                    values: $scope.missesBaselineList
                                },
                                {
                                    type: 'line',
                                    text: "Misses Baseline Target",
                                    values: $scope.missesBaselineTargetList,
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
                                    values: $scope.missesListUnshift,
                                },
                                {
                                    values: $scope.missesBaselineListUnshift
                                },
                                {
                                    values: $scope.missesBaselineTargetListUnshift,
                                }


                            ]
                        }

                    ]
            }
            zingchart.render({
                id: 'chart-4',
                height:'100%',
                width:'100%',
                data: $scope.OverallProcedureMisses
            });
         });
        });
    };

    $scope.showOverallProcedureMisses = function () {
        getOverallProcedureMisses();
    };

    $scope.showOverallProcedureMisses();
});
