function paging(page) {
	var currentURL = window.location.href;
	var replacePattern = new RegExp("page=\\d+");
	
	if(currentURL.indexOf("?") < 0 ){
		currentURL = currentURL+"/?page="+page;
	} else {
		if(currentURL.indexOf("page=") >= 0) {
			currentURL =  currentURL.replace(replacePattern, "page="+page);
		} else {
			currentURL = currentURL+"&page="+page;
		}
		
	}
	window.location.href = currentURL;
}