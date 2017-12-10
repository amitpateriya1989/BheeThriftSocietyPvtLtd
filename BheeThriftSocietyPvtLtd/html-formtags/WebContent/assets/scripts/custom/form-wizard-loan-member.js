var FormWizardMember = function () {

    return {
        //main function to initiate the module
            init: function () {
            if (!jQuery().bootstrapWizard) {
                return;
            }
            
            //For Wizard setting

            var form = $('#frmLoanTransaction');
            var error = $('.alert-danger', form);
            var success = $('.alert-success', form);
            var countTab = 0;
            
            jQuery.validator.addMethod("validAddress", function(value, element) {
                return this.optional(element) || /^[a-zA-Z0-9,-\/] ?([a-zA-Z0-9,-\/\s]|[a-zA-Z0-9,-\/\s] )*[a-zA-Z0-9-\/]$/.test(value);
              }, "Please enter Letters, dot(.) and space only.");
            
            jQuery.validator.addMethod("Alphaspacedot", function(value, element) {
      		    return this.optional(element) || /^[a-zA-Z.\s]*$/.test(value);
      		}, "Please enter Letters, dot(.) and space only.");
            jQuery.validator.addMethod("Alphaspace", function(value, element) {
      		    return this.optional(element) || /^[a-zA-Z\s]*$/.test(value);
      		}, "Please enter Letters  and space only.");
            
            jQuery.validator.addMethod("Alphaspacecomma", function(value, element) {
        		return this.optional(element) || /^[a-zA-Z0-1,\/\s]*$/.test(value);
        	}, "Please enter Letters,comma,backword salsh and space only.");
            
            jQuery.validator.addMethod("Alphanumspace", function(value, element) {
        		return this.optional(element) || /^[a-zA-Z0-1\s]*$/.test(value);
        	}, "Please enter Letters, numbers and space only.");
            
            jQuery.validator.addMethod("Alphaspace", function(value, element) {
        		return this.optional(element) || /^[a-zA-Z\s]*$/.test(value);
        	}, "Please enter Letters  and space only.");
            
            jQuery.validator.addMethod("Alphanumspacedot", function(value, element) {
      		  return this.optional(element) || /^[a-zA-Z0-1.\s]*$/.test(value);
      		}, "Please enter Letters,number and space only.");
            
            jQuery.validator.addMethod("validPancard", function (value, element) {
                return this.optional(element) || /^([a-zA-Z]){5}([0-9]){4}([a-zA-Z]){1}?$/i.test(value);
            }, "Please enter valid Pan Card No.");	

            jQuery.validator.addMethod("validDate", function (value, element) {
                return this.optional(element) || /^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/i.test(value);
            }, "Please enter valid Date.");
            
            jQuery.validator.addMethod("validIFSC", function (value, element) {
                return this.optional(element) || /^([a-zA-Z]){4}([0-9]){7}?$/i.test(value);
            }, "Please enter valid IFSC Code."); 
            
            $.validator.addMethod("extension", function(value, element, param) {
            	param = typeof param === "string" ? param.replace(/,/g, "|") : "png|jpe?g|gif";
            	return this.optional(element) || value.match(new RegExp(".(" + param + ")$", "i"));
            }, $.validator.format("Please enter a value with a valid extension."));

            $('#frmLoanTransaction').validate({
                doNotHideMessage: true, //this option enables to show the error/success messages on tab switch.
                errorElement: 'label', //default input error message container
                errorClass: 'error', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    //personal
                	ad_member_id: {
                        required: true,
                        digits:true
                    },
                    gross_sel:{
                    	required: true,
                        digits:true
                    },
                    total_deduction:{
                    	required: true,
                        digits:true
                    },
                    ad_type_of_loan_id:{
                		required: true,
                        digits:true
                	},
                	ad_loan_category_id:{
                		required: true,
                        digits:true
                	},
                	loan_purpose:{
                		required: true,
                		Alphaspace:true
                	},
                	req_loan_amt:{
                		required: true,
                        digits:true,
                        max:1000000
                	},
                	show_max:{
                		required: true,
                        number:true,
                	},
                	int_rate:{
                		number:true,
                		required:true
                	},
                	period_month:{
                		required:true,
                		digits:true
                	},
                	loan_date:{
                		required:true,
                		validDate:true
                	},
                	emi:{
                		required:true,
                		number:true
                	},
                	guar_name:{
                		required:true,
                		Alphanumspace:true
                	},
                	guar_ad_society_id:{
                		digits:true
                	},
                	pf_no:{
                		digits:true
                	},
                	guar_mobile:{
                		digits:true,
                		maxlength:10,
                		minlength:10
                	},
                	guar_address:{
                		validAddress:true,
                		maxlength:400
                	},
                	chk_qnt:{
                		required:true,
                		digits:true
                	},
                	guar_cheque_no1:{
                		digits:true,
                		required:true,
                		maxlength:6
                	},
                	guar_branch_name1:{
                		required:true,
                		maxlength:150,
                		Alphaspace:true
                	},
                	guar_bank_name1:{
                		required:true,
                		maxlength:200,
                		Alphaspace:true
                	},
                	guar_cheque_no2:{
                		digits:true,
                		required:true,
                		maxlength:6
                	},
                	guar_branch_name2:{
                		required:true,
                		maxlength:150,
                		Alphaspace:true
                	},
                	guar_bank_name2:{
                		required:true,
                		maxlength:200,
                		Alphaspace:true
                	}
                },

                messages: {
                	ad_member_type_id: {
                		required:"Member type field is required."
                	}
                },

                errorPlacement: function (error, element) { // render error placement for each input type
                	error.insertAfter(element); // for other inputs, just perform default behavior
                },

                invalidHandler: function (event, validator) { //display error alert on form submit   
                    success.hide();
                    error.show();
                    App.scrollTo(error, -200);
                },

                /*highlight: function (element) { // hightlight error inputs
                    $(element)
                        .closest('.form-group').removeClass('has-success').addClass('has-error'); // set error class to the control group
                },

                unhighlight: function (element) { // revert the change done by hightlight
                    $(element)
                        .closest('.form-group').removeClass('has-error'); // set error class to the control group
                },*/

                success: function (label) {
                	
                },

                submitHandler: function (form) {
                	//success.show();
                    error.hide();
                    form.submit();
                    /*
                    var formData = $(form).serialize()
                    $.ajax({
                		type: $(form).attr("method"),
                		url: $(form).attr("action"),
                		data: formData,
                		success: function(result){
                	          console.log('success');     	
                        },
                        error: function(jqXHR, textStatus, errorThrown){
                            console.log(textStatus);
                        }
                	});//end ajax
                    */
                    //add here some ajax code to submit your form or just call form.submit() if you want to submit the form without ajax
                }

            });
            
            function clearFrom(){
     		   $('input[type=text]').each(function() {
     	        $(this).val('');
     	       });
     	       $("#frmLoanTransaction").trigger("reset");
     		}//end clearForm

            var displayConfirm = function() {
              
            }

            var handleTitle = function(tab, navigation, index) {
                var total = navigation.find('li').length;
                var current = index + 1;
                // set wizard title
                $('.step-title', $('#form_wizard_1')).text('Step ' + (index + 1) + ' of ' + total);
                // set done steps
                jQuery('li', $('#form_wizard_1')).removeClass("done");
                var li_list = navigation.find('li');
                for (var i = 0; i < index; i++) {
                    jQuery(li_list[i]).addClass("done");
                }

                if (current == 1) {
                    $('#form_wizard_1').find('.button-previous').hide();
                } else {
                    $('#form_wizard_1').find('.button-previous').show();
                }
                
                if (current == 6 || current == 7) {
                	$('#btnContinue').attr('disabled','disabled');
                }

                if (current >= total) {
                    $('#form_wizard_1').find('.button-next').hide();
                    $('#form_wizard_1').find('.button-submit').show();
                    displayConfirm();
                } else {
                    $('#form_wizard_1').find('.button-next').show();
                    $('#form_wizard_1').find('.button-submit').hide();
                }
                App.scrollTo($('.page-title'));
            }

            // default form wizard
            $('#form_wizard_1').bootstrapWizard({
                'nextSelector': '.button-next',
                'previousSelector': '.button-previous',
                onTabClick: function (tab, navigation, index, clickedIndex) {
                    success.hide();
                    error.hide();
                    return false;
                    if (form.valid() == false) {
                        return false;
                    }
                    handleTitle(tab, navigation, clickedIndex);
                },
                onNext: function (tab, navigation, index) {
                    success.hide();
                    error.hide();

                   if (form.valid() == false) {
                        return false;
                    }
					
                    handleTitle(tab, navigation, index);
                },
                onPrevious: function (tab, navigation, index) {
                    success.hide();
                    error.hide();

                    handleTitle(tab, navigation, index);
                },
                onTabShow: function (tab, navigation, index) {
                    var total = navigation.find('li').length;
                    var current = index + 1;
                    var $percent = (current / total) * 100;
                    $('#form_wizard_1').find('.progress-bar').css({
                        width: $percent + '%'
                    });
                }
            });

            $('#form_wizard_1').find('.button-previous').hide();
            $('#form_wizard_1 .button-submit').click(function () {
            }).hide();
        }

    };

}();