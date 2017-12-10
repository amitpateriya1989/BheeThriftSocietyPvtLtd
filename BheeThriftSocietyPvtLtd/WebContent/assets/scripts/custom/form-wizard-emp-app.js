var FormWizardMember = function () {

    return {
        //main function to initiate the module
            init: function () {
            if (!jQuery().bootstrapWizard) {
                return;
            }
            
            
          
            
            //For Wizard setting

            var form = $('#frmEmployeeReg');
            var error = $('.alert-danger', form);
            var success = $('.alert-success', form);
            
            
            		
            jQuery.validator.addMethod("validAddress", function(value, element) {
              return this.optional(element) || /^[a-zA-Z0-9,-\/] ?([a-zA-Z0-9,-\/\s]|[a-zA-Z0-9,-\/\s] )*[a-zA-Z0-9-\/]$/.test(value);
            }, "Please enter Letters, dot(.) and space only.");
            
            jQuery.validator.addMethod("Alphaspacedot", function(value, element) {
      		    return this.optional(element) || /^[a-zA-Z.\s]*$/.test(value);
      		}, "Please enter Letters, dot(.) and space only.");
            
            jQuery.validator.addMethod("Alphabet", function(value, element) {
      		    return this.optional(element) || /^[a-zA-Z]*$/.test(value);
      		}, "Please enter Letters, dot(.) and space only.");
            
            jQuery.validator.addMethod("Alphaspacecomma", function(value, element) {
        		return this.optional(element) || /^[a-zA-Z0-1,\/\s]*$/.test(value);
        	}, "Please enter Letters,comma,backword salsh and space only.");
            
            jQuery.validator.addMethod("Alphanumspace", function(value, element) {
        		return this.optional(element) || /^[a-zA-Z0-1\s]*$/.test(value);
        	}, "Please enter Letters, numbers and space only.");
            
            jQuery.validator.addMethod("Alphaspace", function(value, element) {
        		return this.optional(element) || /^[a-zA-Z0-1\s]*$/.test(value);
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
            
           
            
            $('#frmEmployeeReg').validate({
                doNotHideMessage: true, //this option enables to show the error/success messages on tab switch.
                errorElement: 'label', //default input error message container
                errorClass: 'error', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    //personal
                	employee_id: {
                        required: true,
                        digits:true
                    },emp_category: {
                    	required: true
                    },
                	ad_member_status_id:{
                    	required: true,
                    	digits:true
                    },
                    ad_gender_id:{
                    	required: true,
                    	digits:true
                    },
                    ad_category_id:{
                    	digits:true,
                    	required:true
                    },
                    ad_salutation_id:{
                    	required: true,
                    	digits:true
                    },
                    name:{
                    	required: true,
                    	Alphaspacedot:true,
                        minlength: 3,
                        maxlength: 150
                    },
                    father:{
                    	required: true,
                    	Alphaspacedot:true,
                        minlength: 3,
                        maxlength: 150
                    },
                    dob:{
                    	required: true,
                        validDate:true
                    },
                    marital_status: {
                    	required: true
                    },
                    ad_religion_id: {
                    	required: true,
                    	digits:true
                    },
                    blood_group: {
                    	required: true
                    },
                    identity_marks: {
                    	required: true,
                    	Alphaspacedot:true
                    },
                    height: {
                    	required: true,
                    	number:true
                    },
                    remarks: {
                    	maxlength:100
                    },
                    photo:{
                    	extension: "jpg|jpeg|JPG|JPEG|png|PNG",
                    },
                    id_proof:{
                    	extension: "jpg|jpeg|JPG|JPEG|png|PNG",
                    },
                    sign:{
                    	extension: "jpg|jpeg|JPG|JPEG|png|PNG",
                    },
                  //contact present address
                    mobile:{
                    	required: true,
                    	digits:true,
                    	minlength:10,
                    	maxlength:10
                    	
                    },
                    alt_mobile: {
                    	digits:true,
                    	minlength:10,
                    	maxlength:10
                    },
                    phone:{
                    	digits:true,
                    	maxlength:12
                    },
                    email:{
                    	email:true
                    },
                    c_address:{
                    	required: true,
                    	maxlength:100
                    },
                    p_address:{
                    	required: true,
                    	maxlength:100
                    },
                    ad_country_id:{
                    	required: true,
                    	digits:true
                    },
                    ad_state_id:{
                    	required: true,
                    	digits:true
                    },
                    ad_district_id:{
                    	required: true,
                    	digits:true
                    },
                    ad_city_id:{
                    	required: true,
                    	digits:true
                    },
                    pincode:{
                    	digits:true,
                    	minlength:6,
                    	maxlength:6
                    },
                    
                    //service Details
                    ad_bank_id:{
                    	required: true,
                    	digits:true
                    },
                    ad_branch_id:{
                    	required: true,
                    	digits:true
                    },
                    pan_no:{
                    	validPancard:true
                    },
                    aadhar_no:{
                    	minlength: 12,
                        maxlength: 12,
                        digits: true
                    },
                    ad_department_id:{
                    	required: true,
                    	digits:true
                    },
                    ad_designation_id:{
                    	required: true,
                    	digits:true
                    },
                    ad_grade_id:{
                    	required: true,
                    	digits:true
                    },
                    pf_no:{
                    	
                    	digits:true
                    },
                    doa:{
                    	required: true,
                    	validDate:true
                    },
                    doj:{
                    	required: true,
                    	validDate:true
                    },
                    saving_ac_no:{
                    	required: true,
                    	digits:true,
                    	minlength:11,
                    	maxlength:15
                    },
                    monthly_pay: {
                    	required: true,
                    	digits:true,
                    	minlength:4,
                    	maxlength:6
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
                	success.show();
                    error.hide();
                   
                    //var formData = new FormData( $(form)[0]);
                
                    
                    //add here some ajax code to submit your form or just call form.submit() if you want to submit the form without ajax
                }

            });
            
            function clearFrom(){
     		   $('input[type=text]').each(function() {
     	        $(this).val('');
     	       });
     	       $("#frmSalutation").trigger("reset");
     		}//end clearForm
            
            $('.button-show-modal').click(function(){
            	$('.memberRegModel').modal('show');
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
            	frmEmployeeReg.submit();
            }).hide();
            }

    };

}();