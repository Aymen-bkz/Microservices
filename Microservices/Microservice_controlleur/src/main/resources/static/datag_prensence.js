am5.ready(async function() {

    // Create root element
    // https://www.amcharts.com/docs/v5/getting-started/#Root_element
    var root = am5.Root.new("chartdiv-p");


    // Set themes
    // https://www.amcharts.com/docs/v5/concepts/themes/
    root.setThemes([
        am5themes_Animated.new(root)
    ]);


    // Create chart
    // https://www.amcharts.com/docs/v5/charts/xy-chart/
    var chart = root.container.children.push(am5xy.XYChart.new(root, {
        panX: true,
        panY: true,
        wheelX: "none",
        wheelY: "none"
    }));

    // We don't want zoom-out button to appear while animating, so we hide it
    chart.zoomOutButton.set("forceHidden", true);


    // Create axes
    // https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
    var xRenderer = am5xy.AxisRendererX.new(root, {
        minGridDistance: 30
    });
    xRenderer.labels.template.setAll({
        rotation: -90,
        centerY: am5.p50,
        centerX: 0,
        paddingRight: 15
    });
    xRenderer.grid.template.set("visible", true);

    var xAxis = chart.xAxes.push(am5xy.CategoryAxis.new(root, {
        maxDeviation: 0.3,
        categoryField: "id",
        renderer: xRenderer
    }));

    var yAxis = chart.yAxes.push(am5xy.ValueAxis.new(root, {
        maxDeviation: 0.3,
        min: 0,
        renderer: am5xy.AxisRendererY.new(root, {})
    }));


    // Add series
    // https://www.amcharts.com/docs/v5/charts/xy-chart/series/
    var series = chart.series.push(am5xy.ColumnSeries.new(root, {
        name: "Series 1",
        xAxis: xAxis,
        yAxis: yAxis,
        valueYField: "value",
        categoryXField: "id"
    }));

    // Rounded corners for columns
    series.columns.template.setAll({
        cornerRadiusTL: 5,
        cornerRadiusTR: 5
    });

    // Make each column to be of a different color
    series.columns.template.adapters.add("fill", function(fill, target) {
        return chart.get("colors").getIndex(series.columns.indexOf(target));
    });

    series.columns.template.adapters.add("stroke", function(stroke, target) {
        return chart.get("colors").getIndex(series.columns.indexOf(target));
    });

    // Add Label bullet
    series.bullets.push(function() {
        return am5.Bullet.new(root, {
            locationY: 1,
            sprite: am5.Label.new(root, {
                text: "Présent",
                fill: root.interfaceColors.get("alternativeText"),
                centerY: 0,
                centerX: am5.p50,
                populateText: true
            })
        });
    });

    await sleep(1000);
    var presence_tab = service_data[2];
    console.log(presence_tab);


    // Set data
    var data = [{
        "id": "id " + presence_tab[0]["id"],
        "value": presence_tab[0]["etat"] ? 1 : 0
    }, {
        "id": "id " + presence_tab[1]["id"],
        "value": presence_tab[1]["etat"] ? 1 : 0
    }, {
        "id": "id " + presence_tab[2]["id"],
        "value": presence_tab[2]["etat"] ? 1 : 0
    }, {
        "id": "id " + presence_tab[3]["id"],
        "value": presence_tab[3]["etat"] ? 1 : 0
    }];

    xAxis.data.setAll(data);
    series.data.setAll(data);

    //updateData();

    // update data with random values each 1.5 sec
    // setInterval(function () {
    //     updateData();
    // }, 2000)

    async function updateData() {
        am5.array.each(series.dataItems, function(dataItem) {
            var value;
            presence_tab[count]["etat"] ? value = 1 : value = 0;
            count++;
            // both valueY and workingValueY should be changed, we only animate workingValueY
            dataItem.set("valueY", value);
            dataItem.animate({
                key: "valueYWorking",
                to: value,
                duration: 600,
                easing: am5.ease.out(am5.ease.cubic)
            });
        })

        //sortCategoryAxis();
    }


    // Get series item by category
    function getSeriesItem(category) {
        for (var i = 0; i < series.dataItems.length; i++) {
            var dataItem = series.dataItems[i];
            if (dataItem.get("categoryX") == category) {
                return dataItem;
            }
        }
    }


    // Axis sorting
    function sortCategoryAxis() {

        // Sort by value
        series.dataItems.sort(function(x, y) {
            return y.get("valueY") - x.get("valueY"); // descending
            //return y.get("valueY") - x.get("valueY"); // ascending
        })

        // Go through each axis item
        am5.array.each(xAxis.dataItems, function(dataItem) {
            // get corresponding series item
            var seriesDataItem = getSeriesItem(dataItem.get("category"));

            if (seriesDataItem) {
                // get index of series data item
                var index = series.dataItems.indexOf(seriesDataItem);
                // calculate delta position
                var deltaPosition = (index - dataItem.get("index", 0)) / series.dataItems.length;
                // set index to be the same as series data item index
                dataItem.set("index", index);
                // set deltaPosition instanlty
                dataItem.set("deltaPosition", -deltaPosition);
                // animate delta position to 0
                dataItem.animate({
                    key: "deltaPosition",
                    to: 0,
                    duration: 1000,
                    easing: am5.ease.out(am5.ease.cubic)
                })
            }
        });

        // Sort axis items by index.
        // This changes the order instantly, but as deltaPosition is set,
        // they keep in the same places and then animate to true positions.
        xAxis.dataItems.sort(function(x, y) {
            return x.get("index") - y.get("index");
        });
    }


    // Make stuff animate on load
    // https://www.amcharts.com/docs/v5/concepts/animations/
    series.appear(1000);
    chart.appear(1000, 100);

}); // end am5.ready()

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}