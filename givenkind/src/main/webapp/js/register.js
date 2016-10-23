$(document).ready(function() {	
		 $( "#email" ).blur(function() {
		 var email = $('#email').val();
		 var pattern=/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

		 if (pattern.test(email))  
		  {  
			 $('#emailError').addClass("hidden");
			 
		  }  
		 else{
			 $('#emailError').removeClass("hidden");
		     
		 }
			}); 
	
	
	    $( "#password" ).blur(function() {
	    	var pass = $('#password').val();
	    	if(pass.length < 8)  {  	
	    		$('#passwordError').removeClass("hidden");}
	    	else{
	    		$('#passwordError').addClass("hidden");
	    	}	 	
			}); 
	    
	    $( "#confirmPassword" ).blur(function() {
	    	var pass = $('#password').val();
	    	var cpass = $('#confirmPassword').val();
	    	if(pass != cpass)  {  	
	    		$('#confirmPasswordError').removeClass("hidden");}
	    	else{
	    		$('#confirmPasswordError').addClass("hidden");
	    	}
	    	
			}); 
	    
	    
		$(".zipMask").mask("99999-9999",{placeholder:""});
	    $( "#zip" ).blur(function() {
	    	    	
	    	var zipcode = $('#zip').val();
	    	if(zipcode.length == 5 || zipcode.length == 10 )  {  
	    		$('#zipError').addClass("hidden");
	    		}
	    	else{	    		
	    		$('#zipError').removeClass("hidden");
	    	}
	    	
			}); 
	
	    var last_valid_selection = null;
	    
	    $("#nonprofitCategories").change(function (event) {	    	 
	    	
	        if($(this).val().length > 3) {	        	
	        	$('#selectError').removeClass("hidden");
	        	 $(this).val(last_valid_selection);
            }	        
	        else{	    		
	        	$('#selectError').addClass("hidden");
	        	last_valid_selection = $(this).val();
	        	
	    	}
	    });
	  
	    $("#pickupService").change(function (event) {	    	 
	    	
	    	if ($("#pickupService").is(':checked')) {	    		
	    		$("#pickupDistance").prop('disabled', false);}
	        else{ 
	        	$("#pickupDistance").prop('disabled', true);
	        	$("#pickupDistance").val("");
	        	
	        }
	    });
	    
	    $( "#contactPhone" ).blur(function() {
	    	
		    	var phone = $('#contactPhone').val();
		    	if(phone.length < 10)  {  	
		    		$('#phoneError').removeClass("hidden");}
		    	else{
		    		$('#phoneError').addClass("hidden");
		    	}
	    	
			});
	    
	    $( "#employerIdentificationNumber" ).blur(function() {
	    	
		    	var ein = $('#employerIdentificationNumber').val();
		    	if(ein.length != 9)  {  	
		    		$('#einError').removeClass("hidden");}
		    	else{
		    		$('#einError').addClass("hidden");
		    	}	    	
			});
	    
	    $('input:radio[name="pickupService"]').change(function(){
	    	
	    	   if ($(this).is(':checked') && $(this).val() == 'Yes') {
	    		   $("#pickupDistance").prop('disabled', false); }
	    	   else{ 
		        	$("#pickupDistance").prop('disabled', true);
		        	$("#pickupDistance").val("");		        	
		        }
	    	 });
	

});
