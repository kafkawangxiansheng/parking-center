
function lockMonthlyCard() {
	var confirmation = confirm("Bạn có chắc muốn khóa thẻ hay không?");
	
	if(confirmation) {
		var selectedIds = $("input[name=cardId]:checked").map(function() {
			return this.value;
		}).get().join(",");
		
		$.get("/monthlyCard/lock-card/?ids=" + selectedIds, function() {
			alert("Lưu thành công");
			location.reload();
		});
	}
	
}