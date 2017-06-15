 $(document).ready(function(){
      $("#monFormulaire").validate();
    });
jQuery(document).ready(function() {
   jQuery("#monFormulaire").validate({
      rules: {
         "firstName":{
            "required": true,
            "minlength": 2,
            "maxlength": 60000
         },
         "email": {
            "email": true,
            "maxlength": 255
         },
         "lastName": {
            "required": true
         }
      }})
});
Query.extend(jQuery.validator.messages, {
    required: "votre message",
    remote: "votre message",
    email: "votre message",
    url: "votre message",
    date: "votre message",
    dateISO: "votre message",
    number: "votre message",
    digits: "votre message",
    creditcard: "votre message",
    equalTo: "votre message",
    accept: "votre message",
    maxlength: jQuery.validator.format("votre message {0} caractéres."),
    minlength: jQuery.validator.format("votre message {0} caractéres."),
    rangelength: jQuery.validator.format("votre message  entre {0} et {1} caractéres."),
    range: jQuery.validator.format("votre message  entre {0} et {1}."),
    max: jQuery.validator.format("votre message  inférieur ou égal à {0}."),
    min: jQuery.validator.format("votre message  supérieur ou égal à {0}.")
  });

/*function radio(){

	  var val1=document.getElementsByName("oradio");
	  var val2=document.getElementById("id1");
	  for (var i=0; i < val1.length; i++)
	  {
	    if (val1[i].checked){
	    	
	    	cacher("texte1");
	    	return true;
	  }
//	    	alert("selectionnez votre sexe");
	  		afficher("texte1");
	  		return false;
	 
	}
	}

	function reinit(oForm){
		var elemt=oForm.elements;
		oForm.reset();
		for(i=0; i<elemt.length; i++){
		
			if(elemt[i].value!="valider" && elemt[i].value!="reinitialiser"){
				elemt[i].value="";
			}
		}
		for(i=1; i<=5; i++){
			cacher("texte"+i);
		}


	}

	function surligne(champ, erreur)
	{
		
	   if(erreur){
		   	champ.style.borderColor = "red";
	    	champ.style.borderSize="2px";
	    	
	    	
	   }
	   else{
	        champ.style.borderColor = "green";
	     	champ.style.borderSize="2px";}

	}
	function afficher(id){
		
		{
		    if(document.getElementById(id).style.display=="none")
		    {
		        document.getElementById(id).style.display="inline";
		    }
		    return true;
		}
	}

	function cacher(id){
		
		
		    if(document.getElementById(id).style.display=="inline")
		    {
		        document.getElementById(id).style.display="none";
		       // document.getElementById(id).innerHTML='Cacher le texte';
		    }
		    
		    return true;
		
	}
	function verifNom(){
		
		   var val2=document.getElementById("lastName");
		   
		   if(val2.value.length <2 || val2.value.length > 20 ){
			   surligne(val2, true);
			   afficher("texte2");
			   return false;
		   }
		   else {
			   surligne(val2, false);
			   cacher("texte2");
			   return true;
		   }
	}
	function verifPrenom(){
		
		   var val3=document.getElementById("firstName");
		   
		   if(val3.value.length < 2 || val3.value.length > 20 ){
			   surligne(val3, true);
			   afficher("texte3");
			   return false;
		   }
		   else {
			   surligne(val3, false);
			   cacher("texte3");

			   return true;
		   }
	}
	function verifLogin(){
		
		   var val3=document.getElementById("login");
		   
		   if(val3.value.length < 4 || val3.value.length > 20 ){
			   surligne(val3, true);
			   afficher("texte3");
			   return false;
		   }
		   else {
			   surligne(val3, false);
			   cacher("texte3");
			   return true;
		   }
	}
	function verifPassword(){
		
		   var val3=document.getElementById("password");
		   
		   if(val3.value.length < 4 || val3.value.length > 8 ){
			   surligne(val3, true);
			   afficher("texte4");
			   return false;
		   }
		   else {
			   surligne(val3, false);
			   cacher("texte4");
			   return true;
		   }
	}
	function verifEmail(){
		
		   var val3=document.getElementById("email");
		   
		   if(val3.value.length < 4 || val3.value.length > 20 ){
			   surligne(val3, true);
			   afficher("texte5");
			   return false;
		   }
		   else {
			   surligne(val3, false);
			   cacher("texte5");
			   return true;
		   }
	}
	function verifAge(){
		
		   var val4=document.getElementById("id4");
		   
		   if(val4.value <10 || val4.value > 100 ){
			   surligne(val4, true);
			   afficher("texte4");
			   return false;
		   }
		   else {
			   surligne(val4, false);
			   cacher("texte4");
			   return true;
		   }
	}
	

	function validate(){
		//var val1=document.getElementsByName("oradio");
		//var ageOk=verifAge();
		var verifPrenomOk=verifPrenom();
		var verifNomOk=verifNom();
		var verifLoginOk=verifLogin();
		var verifPasswordOk=verifPassword();
		var verifEmailOk = verifEmailOk();
		//var radioOk=radio();
		if(verifPrenomOk==true && verifNomOk==true 
				&& verifLoginOk==true && verifPasswordOk==true 
					&& verifEmailOk == true){
			return true;
			
		}
		else {
			
			alert("renseignez tous les champs svp");
			return false;
		}

	}*/