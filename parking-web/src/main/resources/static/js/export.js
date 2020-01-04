
function exportExcel(module) {
	var currentURL = window.location.href;
	currentURL = currentURL.replace(module, module+"/export");
	window.location.href = currentURL;
}
