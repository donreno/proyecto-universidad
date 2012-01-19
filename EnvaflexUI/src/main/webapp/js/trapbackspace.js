
function getKey(keyStroke) { 
	isNetscape=(document.layers); 
	eventChooser = (isNetscape) ? keyStroke.which : event.keyCode; 
	if (eventChooser==13) { 
		return false; 
	}
	return true;
} 
document.onkeypress = getKey; 

document.onkeydown = function(){
	if(window.event && window.event.keyCode == 116){
		window.event.keyCode = 505;
	}
	if(window.event && window.event.keyCode == 505){
		return false;
	}
	return true;
}