$(function() {
	$(".datepickerInOut" ).datepicker({
		dateFormat: "dd/mm/yy",
		changeMonth: true,
		changeYear: true
	});
});

function saveLostCard(){
	var selectedIds = $("input[name=orderId]:checked").map(function () {return this.value;}).get().join(",");
	alert(selectedIds);
	
}