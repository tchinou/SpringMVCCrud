
$("document").ready(function(){

	
	$(function(){
		
	    var pathname = (window.location.pathname.match(/[^\/]+$/)[0]);

	    $('#mainMenu tr	td a').each(function() {
	    	
	    	
	    if ($(this).attr('href') == pathname)
	    {
	    	$('.nav a').removeClass('highlight');
	        $(this).addClass('highlight');
	    }
	    });
	});

});