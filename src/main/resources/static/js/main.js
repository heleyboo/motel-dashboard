$(function() {
	// Initialize Select2 Elements
	$('.select2').select2();
	$("input[data-bootstrap-switch]").each(function() {
		$(this).bootstrapSwitch('state', $(this).prop('checked'));
	});
});
