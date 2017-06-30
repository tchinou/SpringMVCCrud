$(document).ready(function(){
	
    $("#hideShow").change(function(){
    	
    	if($(this).val() == "?lang=fr"){
            $('#fr').show('#fr');
            $('#uk').hide();

    	}
    	else {
            $('#uk').show();
            $('#fr').hide();

    	}
    });

});