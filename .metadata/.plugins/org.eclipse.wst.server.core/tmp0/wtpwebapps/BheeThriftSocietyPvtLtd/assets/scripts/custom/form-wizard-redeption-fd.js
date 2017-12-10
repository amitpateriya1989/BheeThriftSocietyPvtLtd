var FormWizardRedeptionFD = function () {

    return {
        //main function to initiate the module
            init: function () {
            if (!jQuery().bootstrapWizard) {
                return;
            }
            
            //For Wizard setting

            var form = $('#frmRedeptionFD');
            var error = $('.alert-danger', form);
            var success = $('.alert-success', form);
            var countTab = 0;
            
            
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

            jQuery.validator.addMethod("validDate", function (value, element) {
                return this.optional(element) || /^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/i.test(value);
            }, "Please enter valid Date.");//dd/mm/yyyy
            

            $('#frmRedeptionFD').validate({
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
                    rede_date:{
                    	required: true,
                    	validDate:true	
                    },
                    fd_no_rede:{
                    	required: true,
                        digits:true
                    },
                    rede_ad_type_of_fd_id:{
                    	required: true,
                        digits:true
                    },
                    rede_ad_fd_category_id:{
                    	required: true,
                        digits:true
                    },
                    redeption_type:{
                    	required: true,
                    	Alphaspace:true
                    },
                    rede_fd_amt:{
                		required: true,
                        number:true
                	},
                	rede_int_rate:{
                		required: true,
                		number:true
                	},
                	total_int:{
                		required: true,
                		number:true
                	},
                	total_fd_amt:{
                		required: true,
                        number:true
                	},
                	//payment validation
                    vtype:{
                    	required:true,
                    	Alphaspace:true
                    },
                    ins_from:{
                    	required:true
                    },
                    ins_no:{
                    	required:true,
                    	number:true,
                    	minlength:6,
                    	maxlength:6
                    },
                    ins_date:{
                    	required:true,
                    	validDate:true
                    },
                    v_amt:{
                    	number:true
                    },
                    voucher_type:{
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
                    //add here some ajax code to submit your form or just call form.submit() if you want to submit the form without ajax
                }

            });

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