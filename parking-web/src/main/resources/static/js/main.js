$(document).ready(function (){
	$('.table .eBtn').on('click', function(event){
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(monthlyCard, status){
			$('.myForm #id').val(monthlyCard.id);
			$('.myForm #cardCode').val(monthlyCard.cardCode);
			$('.myForm #customerName').val(monthlyCard.customerName);
			$('.myForm #startDateString').val(monthlyCard.startDateString);
			$('.myForm #endDateString').val(monthlyCard.endDateString);
		});
		
		$('.myForm #renewalMonthlyCardModal').modal();
	});
});