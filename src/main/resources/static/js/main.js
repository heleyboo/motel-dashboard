$(function () {
	// Initialize Select2 Elements
	$('.select2').select2();
	$("input[data-bootstrap-switch]").each(function () {
		$(this).bootstrapSwitch('state', $(this).prop('checked'));
	});


	$("#province").on("change", function () {
		let provinceId = this.value;
		fetch(`/api/v1/provinces/${provinceId}/districts`)
			.then(response => response.json())
			.then(data => {
				$('#district').empty();
				$('#ward').empty();
				data.forEach(district => {
					$('#district').append($("<option></option>").attr("value", district.code).text(district.nameWithType));
				});
			});
	});

	$("#district").on("change", function () {
		let districtId = this.value;
		fetch(`/api/v1/provinces/${districtId}/wards`)
			.then(response => response.json())
			.then(data => {
				$('#ward').empty();
				data.forEach(district => {
					$('#ward').append($("<option></option>").attr("value", district.code).text(district.nameWithType));
				});
			});
	});

});
