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
					// Donorlist and edit donor list description validation
					$("#description").keypress(
							function(evt) {

								var charCode = (evt.which) ? evt.which
										: event.keyCode;

								if ((charCode > 64 && charCode < 91)
										|| (charCode > 96 && charCode < 123))
									return true;
								else
									return false;

							});
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
					$("#note").keypress(
							function(evt) {

								var charCode = (evt.which) ? evt.which
										: event.keyCode;

								if ((charCode > 64 && charCode < 91)
										|| (charCode > 96 && charCode < 123))
									return true;
								else
									return false;

							});

				});
