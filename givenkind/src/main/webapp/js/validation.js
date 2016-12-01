$(document)
		.ready(
				function() {
					$("#back").click(function() {
						parent.history.back();
						return false;
					});
					// Donorlist and edit donor list fairmarket value validation
					$("#fairMarketValue")
							.keypress(
									function(evt) {

										var charCode = (evt.which) ? evt.which
												: event.keyCode;
										if ((!((charCode > 47 && charCode < 58) || charCode == 46))
												|| (($(this).val().indexOf('.') > -1) && (charCode == 46)))
											return false;

										return true;

									});
					$("#fairMarketValue").blur(function(){		
						var fairMrktValue = document.getElementById('fairMarketValue').value;			
						var newValue=parseFloat(fairMrktValue).toFixed(2);	
						
						$("#fairMarketValue").val(newValue);
						
					});
					
					// Donorlist and edit donor list description validation
					/*$("#description").keypress(
							function(evt) {

								var charCode = (evt.which) ? evt.which
										: event.keyCode;

								if ((charCode > 64 && charCode < 91)
										|| (charCode > 96 && charCode < 123))
									return true;
								else
									return false;

							});*/
					// Donorlist and edit donor list Quantity validation
					$("#quantityAvailable").keypress(function(evt) {

						var charCode = (evt.which) ? evt.which : event.keyCode;
						if (!(charCode > 47 && charCode < 58))
							return false;

						return true;

					});
					// Wishlist and edit wish list quantity validation
					$("#quantityDesired").keypress(function(evt) {

						var charCode = (evt.which) ? evt.which : event.keyCode;
						if (!(charCode > 47 && charCode < 58))
							return false;

						return true;

					});
					// Wishlist and edit wish list description validation
					/*$("#note").keypress(
							function(evt) {

								var charCode = (evt.which) ? evt.which
										: event.keyCode;

								if ((charCode > 64 && charCode < 91)
										|| (charCode > 96 && charCode < 123))
									return true;
								else
									return false;

							});*/
					var last_valid_selection = null;
				    
				    $("#itemCategories").change(function (event) {	    	 
				    	
				        if($(this).val().length > 3) {	        	
				        	$('#selectError').removeClass("hidden");
				        	 $(this).val(last_valid_selection);
			            }	        
				        else{	    		
				        	$('#selectError').addClass("hidden");
				        	last_valid_selection = $(this).val();
				        	
				    	}
				    });

				});
