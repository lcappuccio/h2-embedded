$(document).ready(function() {
	$.get("http://localhost:8080/api/data", function(data) {
		console.log("data loaded");
		$('.data-size').append(data.length);
		drawTable(data);
	});
});

function drawTable(data) {
	// console.log(data);
    for (var i = 0; i < data.length; i++) {
        drawRow(data[i]);
    }
}

function drawRow(rowData) {
    var row = $("<tr/>")
    $("#dataTable").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
    row.append($("<td>" + rowData.dataId + "</td>"));
    row.append($("<td>" + rowData.dataValue + "</td>"));
    row.append($("<td>" + Date.parse(rowData.dataTimestamp) + "</td>"));
}