function drawRow(rowData) {
	var row = $('<tr/>');
	$('#dataTable').append(row);
	row.append($('<td>' + rowData.dataId + '</td>'));
	row.append($('<td>' + rowData.dataValue + '</td>'));
	var date = moment(new Date(rowData.dataTimestamp)).format('YYYYMMDD HH:mm:ss');
	row.append($('<td>' + date + '</td>'));
}

function drawTable(data) {
	for (var i = 0; i < data.length; i++) {
		drawRow(data[i]);
	}
}

function addItem() {
	var text = {"dataValue": $('#textInputField').val()};
	$.ajax({
		data: JSON.stringify(text),
		contentType: 'application/json',
		url: '/api/data/',
		type: 'POST'
	});
	location.reload();
}

$(document).ready(function () {
	$.get('http://localhost:8080/api/data', function (data) {
		$('#data-size').append(data.length);
		drawTable(data);
	});
});