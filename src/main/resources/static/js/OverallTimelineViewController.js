app.controller('OverallTimelineViewController', function ($scope, $http) {


    $scope.weekList = [];
    $scope.notDeliveredPercentageList = [];
    $scope.notDeliveredBaselinePercentageList = [];
    $scope.notDeliveredBaselinePercentageTargetList = [];



    //Unshift Array For Table

    $scope.monthList = [];
    $scope.weekListUnshift = [];
    $scope.ontimeDeliveredListUnshift = [];
    $scope.notDeliveredListUnshift = [];
    $scope.totalDeliveredListUnshift = [];
    $scope.notDeliveredBaselinePercentageListUnshift = [];
    $scope.notDeliveredBaselinePercentageTargetListUnshift = [];
    $scope.notDeliveredPercentageListUnshift = [];

console.log(  $scope.notDeliveredBaselinePercentageList);

    var getGraphDataOverallTimeline = function (teamId) {

        $http.get('api/reportView?teamId=' + teamId, {'cache': false}).then(function (response) {
            $scope.reportResults = response.data;
        });

        $http.get('api/calculateView?teamId=' + teamId, {'cache': false}).then(function (response) {
            $scope.results = response.data;



            for (var i = 0; i < $scope.results.length; i++) {

               // notDeliveredBaseline calculate

                var percentage=($scope.reportResults[i].notDelivered/$scope.results[i].totalDelivered)*100;
                $scope.notDeliveredPercentageList.push(Math.round (percentage*100)/100);
                $scope.notDeliveredPercentageListUnshift.push(Math.round (percentage*100)/100+"%");

                $scope.ontimeDeliveredListUnshift.push($scope.reportResults[i].onTimeDelivered);
                $scope.notDeliveredListUnshift.push($scope.reportResults[i].notDelivered);
                $scope.totalDeliveredListUnshift.push($scope.results[i].totalDelivered);

                $scope.notDeliveredBaselinePercentageList.push($scope.results[i].notDeliveredBaseline);
                $scope.notDeliveredBaselinePercentageListUnshift.push($scope.results[i].notDeliveredBaseline+"%");

                $scope.notDeliveredBaselinePercentageTargetList.push($scope.results[i].notDeliveredBaselineTarget);
                $scope.notDeliveredBaselinePercentageTargetListUnshift.push($scope.results[i].notDeliveredBaselineTarget+"%");






                $scope.monthList.push(moment($scope.results[i].date).format('MMM-YY'));//Get Month name
                $scope.weekList.push($scope.results[i].week);
                $scope.weekListUnshift.push($scope.results[i].week);
            }



            if ($scope.results.length > 0) {
                $scope.monthList.unshift("");
                $scope.weekListUnshift.unshift("Week");
                $scope.ontimeDeliveredListUnshift.unshift("On time Delivered");
                $scope.notDeliveredListUnshift.unshift("Not Delivered");
                $scope.totalDeliveredListUnshift.unshift("Total Delivered");
                $scope.notDeliveredBaselinePercentageListUnshift.unshift("Not Delivered Baseline");
                $scope.notDeliveredBaselinePercentageTargetListUnshift.unshift("Not Delivered Baseline Target");
                $scope.notDeliveredPercentageListUnshift.unshift("Not Delivered Percentage");

            }

            $scope.graphDataOverallTimeline = {
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
                                text: "Week %kl<br><span style='color:%color'>%t: </span>%v%<br>",
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
                                    text: 'Overall Timeline Report',
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
                                zooming:true,
                                item: {
                                    padding: "0 10 0 0",
                                    fontColor: "#000000",
                                    fontFamily: 'Montserrat',
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
                                    text: "Not Delivered Percentage",
                                    values: $scope.notDeliveredPercentageList,
                                    color: "red"
                                },
                                {
                                    text: "Not Delivered Baseline Percentage",
                                    values: $scope.notDeliveredBaselinePercentageList
                                },
                                {
                                    text: "Not Delivered Baseline Percentage Target",
                                    values: $scope.notDeliveredBaselinePercentageTargetList,
                                    lineStyle: "dashed"
                                }]
                        },


                        {

                            type: 'grid',
                            adjustLayout: true,
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
                                colWidths:["10%"],
                                adjustLayout: true,
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
                                    values: $scope.ontimeDeliveredListUnshift
                                },
                                {
                                    values: $scope.notDeliveredListUnshift,
                                },
                                {
                                    values: $scope.totalDeliveredListUnshift,
                                },
                                {
                                    values: $scope.notDeliveredBaselinePercentageListUnshift,

                                },  {
                                    values: $scope.notDeliveredBaselinePercentageTargetListUnshift,

                                },
                                {
                                    values: $scope.notDeliveredPercentageListUnshift,
                                },



                            ]
                        }

                    ]
            }

        });
    };

    $scope.showOverallTimeline = function (teamId) {
        getGraphDataOverallTimeline(teamId);
    };


});
