function drawRow(rowData) {
	var row = $("<tr/>");
	$("#dataTable").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
	row.append($("<td>" + rowData.dataId + "</td>"));
	row.append($("<td>" + rowData.dataValue + "</td>"));
	var date = new Date(rowData.dataTimestamp).toLocaleString();
	row.append($("<td>" + date + "</td>"));
}

function drawTable(data) {
	for (var i = 0; i < data.length; i++) {
		drawRow(data[i]);
	}
}

$(document).ready(function () {
	$.get("http://localhost:8080/api/data", function (data) {
		$('.data-size').append(data.length);
		drawTable(data);
	});
});