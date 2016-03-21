function drawRow(rowData) {
	var row = $('<tr>');
	$('#dataTable').append(row);
	row.append($("<td class=col-md-1 id=dataId_" + rowData.dataId + ">" + rowData.dataId + "</td>"));
	row.append($("<td contenteditable=true id=dataValue_" + rowData.dataId + ">" + rowData.dataValue + "</td>"));
	var date = moment(new Date(rowData.dataTimestamp)).format('YYYYMMDD HH:mm:ss');
	row.append($('<td class="col-md-1">' + date + '</td>'));
	row.append($('<td class="col-md-1">' +
		"<button type=submit class='btn btn-default' id=updateBtn_" + rowData.dataId +
		" onclick=updateItem('" + rowData.dataId + "');>Update</button></td>"));
	row.append($('<td class="col-md-1">' +
		"<button type=submit class='btn btn-danger' id=deleteBtn_" + rowData.dataId +
		" onclick=deleteItem('" + rowData.dataId + "');>Delete</button></td>"));
	row.append('</tr>');
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

function deleteItem(data) {
	$.ajax({
		url: '/api/data/' + data,
		type: 'DELETE'
	});
	location.reload();
}

function updateItem(dataId) {
	$(this).siblings().on("click", updateItem);
	$(this).off("click");
	var dataValue = $('#dataValue_' + dataId).text();
	var text = {"dataId": dataId, "dataValue": dataValue, "dataTimestamp": 0};
	$.ajax({
		data: JSON.stringify(text),
		contentType: 'application/json',
		url: '/api/data',
		type: 'PUT'
	});
	location.reload();
}

$(document).ready(function () {
	$.get('http://localhost:8080/api/data', function (data) {
		$('#dataSizePar').append(data.length);
		drawTable(data);
	});
	$("button").on("click", updateItem);
});