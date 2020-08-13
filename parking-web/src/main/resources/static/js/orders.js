$(function() {
	$(".datepickerInOut").datepicker({
		dateFormat : "dd/mm/yy",
		changeMonth : true,
		changeYear : true
	});
});

function saveLostCard() {
	var confirmation = confirm("Bạn có chắc muốn lưu mất thẻ hay không?");
	
	if(confirmation) {
		var selectedIds = $("input[name=orderId]:checked").map(function() {
			return this.value;
		}).get().join(",");
		
		$.get("/in-out-logs/save-lost-card/?ids=" + selectedIds, function() {
			alert("Lưu thành công");
			location.reload();
		});
	}
	
}