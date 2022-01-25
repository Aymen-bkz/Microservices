am5.ready(function() {

    // Create root and chart
    var root = am5.Root.new("chartdiv-g");

    root.setThemes([
        am5themes_Animated.new(root)
    ]);

    var chart = root.container.children.push(
        am5radar.RadarChart.new(root, {
            panX: false,
            panY: false,
            radius: am5.percent(90),
            innerRadius: -20
        })
    );

    function createAxis(min, max, start, end, color) {
        var axisRenderer = am5radar.AxisRendererCircular.new(root, {
            strokeOpacity: 0.1,
            minGridDistance: 30,
            startAngle: start,
            endAngle: end,
            stroke: color
        });

        axisRenderer.ticks.template.setAll({
            visible: true,
            strokeOpacity: 0.8,
            stroke: color
        });

        axisRenderer.grid.template.setAll({
            visible: false
        });

        var axis = chart.xAxes.push(
            am5xy.ValueAxis.new(root, {
                maxDeviation: 0,
                min: min,
                max: max,
                strictMinMax: true,
                renderer: axisRenderer
            })
        );


        var rangeDataItem = axis.makeDataItem({
            value: min,
            endValue: max
        });

        var range = axis.createAxisRange(rangeDataItem);

        rangeDataItem.get("axisFill").setAll({
            visible: true,
            fill: color,
            fillOpacity: 0.8,
            stroke: color,
            strokeOpacity: 0.8,
            strokeWidth: 1
        });

        rangeDataItem.get("tick").setAll({
            visible: false
        });

        return axis;
    }

    function createHand(axis) {
        var color = axis.get("renderer").get("stroke");
        var handDataItem = axis.makeDataItem({
            value: 0
        });

        var hand = handDataItem.set("bullet", am5xy.AxisBullet.new(root, {
            sprite: am5radar.ClockHand.new(root, {
                radius: am5.percent(90),
                innerRadius: am5.percent(70)
            })
        }));

        hand.get("sprite").pin.setAll({
            forceHidden: true
        });

        hand.get("sprite").hand.setAll({
            fill: color,
            fillOpacity: 0.9
        });

        axis.createAxisRange(handDataItem);

        return hand;
    }

    var axis1 = createAxis(0, 4095, -85, -5, am5.color(0xEF6F6C));
    var axis2 = createAxis(0, 4095, 5, 85, am5.color(0x426A5A));
    var axis3 = createAxis(0, 4095, 95, 175, am5.color(0x7FB685));
    var axis4 = createAxis(0, 4095, 185, 265, am5.color(0xDDAE7E));

    var hand1 = createHand(axis1);
    var hand2 = createHand(axis2);
    var hand3 = createHand(axis3);
    var hand4 = createHand(axis4);

    var legands_tab = [{
            id: "id",
            uid: 0,
        },
        {
            id: "id",
            uid: 0,
        },
        {
            id: "id",
            uid: 0,
        },
        {
            id: "id",
            uid: 0,
        }
    ];

    let legend = chart.children.push(am5.Legend.new(root, {
        nameField: "name",
        fillField: "color",
        strokeField: "color",
        marginBottom: 100,
        centerX: am5.percent(40),
        x: am5.percent(50)
    }));



    setInterval(async function() {
        gaz_tab = service_data[1];
        legands_tab.forEach((e, i) => {
            e.uid = gaz_tab[i]["id"]
        })

        legend.data.setAll([{
            name: legands_tab[0]["id"] + "  " + legands_tab[0]["uid"],
            color: am5.color(0xEF6F6C)
        }, {
            name: legands_tab[1]["id"] + "  " + legands_tab[1]["uid"],
            color: am5.color(0x426A5A)
        }, {
            name: legands_tab[2]["id"] + "  " + legands_tab[2]["uid"],
            color: am5.color(0x7FB685)
        }, {
            name: legands_tab[3]["id"] + "  " + legands_tab[3]["uid"],
            color: am5.color(0xDDAE7E)
        }]);

        hand1.get("sprite").dataItem.animate({
            key: "value",
            to: gaz_tab[0]["data"],
            duration: 800,
            easing: am5.ease.out(am5.ease.cubic)
        });

        hand2.get("sprite").dataItem.animate({
            key: "value",
            to: gaz_tab[1]["data"],
            duration: 800,
            easing: am5.ease.out(am5.ease.cubic)
        });

        hand3.get("sprite").dataItem.animate({
            key: "value",
            to: gaz_tab[2]["data"],
            duration: 800,
            easing: am5.ease.out(am5.ease.cubic)
        });

        hand4.get("sprite").dataItem.animate({
            key: "value",
            to: gaz_tab[3]["data"],
            duration: 800,
            easing: am5.ease.out(am5.ease.cubic)
        });
    }, 2000);

}); // end am5.ready()