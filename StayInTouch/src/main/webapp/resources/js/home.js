/**
 * 
 */

var jq = jQuery.noConflict();

function monthChange() {
	if(document.getElementById("year").selectedIndex > 0){
		jq(function() {
			jq.get("/stayintouch/app/spring/ajax/getDays", {
				year : jq("#year").val(),
				month : jq("#month").val()
			}, function(data) {
				jq("#dayTableCell").replaceWith(data);

			});
		});
	}
}

function checkEmail() {
	if(document.getElementById("entryEmail").value != ""){
		jq(function() {
			jq.get("/stayintouch/app/spring/ajax/checkForExistingId", {
				entryEmail : jq("#entryEmail").val()
			}, function(data) {
				jq("#errorRow").replaceWith(data);
			});
		});
	}
}

function yearChange(){
	document.getElementById("month").selectedIndex = 0;
	document.getElementById("day").selectedIndex = 0;
}

function login(){
	document.forms["login"].submit();
}

function signup(){
	document.forms["signup"].submit();
}