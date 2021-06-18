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
				$('#district').append($("<option></option>").attr("value", "").text("Quận/huyện"));
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
				$('#ward').append($("<option></option>").attr("value", "").text("Phường/xã"));
				data.forEach(district => {
					$('#ward').append($("<option></option>").attr("value", district.code).text(district.nameWithType));
				});
			});
	});
	
	$('#priceRange').ionRangeSlider({
      min     : 0,
      max     : 20000000,
      from    : minPrice,
      to      : maxPrice,
      type    : 'double',
      step    : 500000,
      suffix  : ' VNĐ',
      hasGrid : true
    });
    
    $('#areaRange').ionRangeSlider({
      min     : 0,
      max     : 1000,
      from    : minArea,
      to      : maxArea,
      type    : 'double',
      step    : 10,
      prettify: false,
      hasGrid : true
    });
    
    

});


