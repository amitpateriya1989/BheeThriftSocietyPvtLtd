var FormWizardMember = function () {

    return {
        //main function to initiate the module
            init: function () {
            if (!jQuery().bootstrapWizard) {
                return;
            }
            
            //for form setting only
            if($("#ad_pf_id").val().trim()==""){
            	$('#ad_member_type_id option[value='+1+']').attr('selected','selected');
            	$('#ad_member_satus_id option[value='+1+']').attr('selected','selected');
            }
            
            $('#ad_gender_id').change(function(){             
            	if($('#ad_gender_id option:selected').text().toLowerCase()=="male"){
            		$('#husband').val("none");
            	    $('#husband').attr('readonly', 'readonly');
            	    $('.husband').addClass('hide');
            	}else{
            		$('#husband').val("");
            		$('#husband').removeAttr('readonly');
            		 $('.husband').removeClass('hide');
            	}
            });
            
            //For Wizard setting

            var form = $('#frmSalutation');
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
            
            $('#frmSalutation').validate({
                doNotHideMessage: true, //this option enables to show the error/success messages on tab switch.
                errorElement: 'label', //default input error message container
                errorClass: 'error', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    //personal
                	ad_member_type_id: {
                        required: true,
                        digits:true
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
                    	digits:true
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
                    husband:{
                    	required: true,
                    	Alphaspacedot:true,
                        minlength: 3,
                        maxlength: 150
                    },
                    dob:{
                    	required: true,
                        validDate:true
                    },
                    photo:{
                    	extension: "jpg|jpeg|JPG|JPEG|png|PNG",
                    },
                    id:{
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
                    phone:{
                    	digits:true,
                    	maxlength:10
                    },
                    email:{
                    	email:true
                    },
                    line1:{
                    	required: true,
                    	validAddress: true,
                    	maxlength:100
                    },
                    line2:{
                    	validAddress: true,
                    	maxlength:100
                    },
                    ad_country_id:{
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
                    //contact permanent address
                    mobile1:{
                    	required: true,
                    	minlength:10,
                    	maxlength:10,
                    	digits:true
                    },
                    phone1:{
                    	digits:true,
                    	maxlength:10
                    },
                    email1:{
                    	email:true
                    },
                    line1_1:{
                    	required: true,
                    	validAddress: true,
                    	maxlength:100
                    },
                    line2_1:{
                    	validAddress: true,
                    	maxlength:100
                    },
                    ad_country_id_1:{
                    	required: true,
                    	digits:true
                    },
                    ad_district_id_1:{
                    	required: true,
                    	digits:true
                    },
                    ad_city_id_1:{
                    	required: true,
                    	digits:true
                    },
                    pincode1:{
                    	digits:true,
                    	minlength:6,
                    	maxlength:6
                    },
                    //service Details
                    branch_id:{
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
                    ad_designation_level_id:{
                    	required: true,
                    	digits:true
                    },
                    ad_designation_type_id:{
                    	required: true,
                    	digits:true
                    },
                    doa:{
                    	validDate:true
                    },
                    h_dor:{
                    	validDate:true
                    },
                    h_service_duration:{
                    	digits:true
                    },
                    saving_ac_no:{
                    	required: true,
                    	digits:true,
                    	minlength:11,
                    	maxlength:15
                    },
                    //Nominee 1
                    nominee_ad_salutation_id_1:{
                    	required: true,
                    	digits:true
                    },
                    nominee_name_1:{
                    	required: true,
                    	Alphaspace:true,
                    	maxlength:150
                    	
                    },
                    guardian1:{
                    	Alphaspace:true,
                    	maxlength:40
                    },
                    nominee_ad_relation_id_1:{
                    	required: true,
                    	digits:true
                    },
                    nominee_ad_gender_id_1:{
                    	digits:true
                    },
                    nominee_dob_1:{
                    	validDate:true
                    },
                    nominee_age_1:{
                    	digits:true
                    },
                    nominee_photo_1 :{
                    	extension: "jpg|jpeg|JPG|JPEG|png|PNG",
                    },
                    nominee_id_proof_1:{
                    	extension: "jpg|jpeg|JPG|JPEG|png|PNG",
                    },
                    nominee_sign_1:{
                    	extension: "jpg|jpeg|JPG|JPEG|png|PNG",
                    },
                    //Nominee 2
                    nominee_ad_salutation_id_2:{
                    	required: true,
                    	digits:true
                    },
                    nominee_name_2:{
                    	required: true,
                    	Alphaspace:true,
                    	maxlength:150
                    	
                    },
                    nominee_ad_relation_id_2:{
                    	required: true,
                    	digits:true
                    },
                    guardian2:{
                    	Alphaspace:true,
                    	maxlength:40
                    },
                    nominee_ad_gender_id_2:{
                    	required: true,
                    	digits:true
                    },
                    nominee_dob_2:{
                    	required: true,
                    	validDate:true
                    },
                    nominee_age_2:{
                    	digits:true
                    },
                    nominee_photo_2:{
                    	extension: "jpg|jpeg|JPG|JPEG|png|PNG",
                    },
                    nominee_id_proof_2:{
                    	extension: "jpg|jpeg|JPG|JPEG|png|PNG",
                    },
                    nominee_sign_2:{
                    	extension: "jpg|jpeg|JPG|JPEG|png|PNG",
                    },
                    //Witness
                    witness_ad_member_id:{
                    	digits:true
                    },
                    witness_ad_society_id:{
                    	digits:true
                    },
                    witness_ad_salutation_id:{
                    	digits:true
                    },
                    witness_name:{
                    	Alphaspace:true,
                    	maxlength:150
                    },
                    witness_mobile:{
                    	digits:true,
                    	maxlength:10
                    },
                    witness_address:{
                    	validAddress:true,
                    	maxlength:250
                    },
                    //payment validation
                    vtype:{
                    	required:true,
                    	Alphabet:true,
                    },
                    ins_form:{
                    	Alphaspace:true,
                    },
                    ins_no:{
                    	required:true,
                    	number:true,
                    	minlength:6
                    },
                    ins_date:{
                    	required:true,
                    	validDate:true
                    },
                    h_v_amt:{
                    	number:true
                    },
                    voucher_type:{
                    	Alphabet:true,
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
                   
                    //var formData = new FormData( $(form)[0]);
                  $.ajax({
                		type: $(form).attr("method"),
                		url: $(form).attr("action"),
                		data: $(form).serialize(),
                		dataType: "json",
                        cache: false,
                		success: function(data){
                			if(data.message=="true"){
                				var dataId =0;
                				
                				dataId = parseInt(data.dataId);
                				
                				if(dataId>0){
                					$('#h_member_id_up').val(dataId);
                					$('#custom-page-message1').html("<div class='note note-success'>"+data.successMessage+"</div>");
                      			    $('.memberRegModel').modal('show');
                				}else{
                					$('#custom-page-message1').html("<div class='note note-warning'>Member Not Register Try again latter</div>");
                      			    $('.memberRegModel').modal('show');
                				}
                				
                			}else{
                				$('#custom-page-message1').html("<div class='note note-warning'>"+data.errorMessage+"</div>");
                  			    $('.memberRegModel').modal('show');
                			} 
                	          
                        },
                        error: function(jqXHR, textStatus, errorThrown){
                            console.log(textStatus);
                        }
                	});//end ajax
                    
                    //add here some ajax code to submit your form or just call form.submit() if you want to submit the form without ajax
                }

            });
            
            function clearFrom(){
     		   $('input[type=text]').each(function() {
     	        $(this).val('');
     	       });
     	       $("#frmSalutation").trigger("reset");
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