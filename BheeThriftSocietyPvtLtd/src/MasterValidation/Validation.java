package MasterValidation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/****************************
** Validation Lib. Developed By Pavan Sihore
**
****************************/

public class Validation {
	
	String regex ="";
	private Pattern pattern;
	private Matcher matcher;
	
	public boolean validDigits(String value){
		if(value==null){value="";}
		regex ="^[0-9]*$";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(value.trim());
		return matcher.matches();
	}//end validNumeric function for 0-9 number only
	
	public boolean validNumeric(String value){
		if(value==null){value="";}
		regex ="^-?(?:\\d+|\\d{1,3}(?:,\\d{3})+)?(?:\\.\\d+)?$";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(value);
		return matcher.matches();
	}//end validNumeric function for number and floating number also 
	
	public boolean validAlphabet(String value){
		if(value==null){value="";}
		regex ="^[a-zA-Z]*$";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(value);
		return matcher.matches();
	}//end validAlphabet function
	
	public boolean validAlphaNum(String value){
		regex ="^[a-zA-Z0-1\\s]*$";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(value);
		return matcher.matches();
	}//end validAlphaNum function
	
	public boolean validAlphaSpace(String value){
		regex ="^[a-zA-Z\\s]*$";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(value);
		return matcher.matches();
	}//end validAlphaSpace function

	public boolean validAlphaSpaceDot(String value){
		regex ="^[a-zA-Z.\\s]*$";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(value);
		return matcher.matches();
	}//end validAlphaSpace function

	
	public boolean validAlphaNumSpace(String value){
		regex ="^[a-zA-Z0-1\\s]*$";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(value);
		return matcher.matches();
	}//end validAlphaNumSpace function
	
	public boolean validAlphaNumSpaceDot(String value){
		regex ="^[a-zA-Z0-1.-\\s]*$";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(value);
		return matcher.matches();
	}//end validAlphaNumSpace function
	
	public boolean validRegEx(String value, String Regex){
		regex = Regex;
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(value);
		return matcher.matches();
	}//end validRegEx function
	
	public boolean validNotEmpty(String value){
		if(value.trim().equals("")==true){
			return false;
		}
		return true;
	}//end validNotEmpty function
	

	public boolean validNotNull(String value){
		if(value==null){
			return false;
		}
		return true;
	}//end validNotEmpty function
	
	public boolean validEmail(String value){
		if(value.equals("")!=true){
		regex ="^[A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(value.trim());
		return matcher.matches();
		}else{
			return true;
		}
	}//end validEmail function
	
	public boolean validAddress(String value){
		if(value.equals("")!=true){
			regex ="^[a-zA-Z0-9,-\\/] ?([a-zA-Z0-9,-\\/\\s]|[a-zA-Z0-9,-\\/\\s] )*[a-zA-Z0-9-\\/]$";
			pattern = Pattern.compile(regex);
			matcher = pattern.matcher(value.trim());
			return matcher.matches();
		}else{
			return true;
		}
	}//end validAddress function
	
	public boolean validPancard(String value){
		if(value.equals("")!=true){
			regex ="^([a-zA-Z]){5}([0-9]){4}([a-zA-Z]){1}?$";
			pattern = Pattern.compile(regex);
			matcher = pattern.matcher(value.trim());
			return matcher.matches();
		}else{
			return true;
		}
	}//end validPancard function
	
	public boolean validIfscCode(String value){
		if(value.equals("")!=true){
			regex ="^([a-zA-Z]){4}([0-9]){7}?$";
			pattern = Pattern.compile(regex);
			matcher = pattern.matcher(value.trim());
			return matcher.matches();
		}else{
			return true;
		}
	}//end validIfscCode function
	
	public boolean validPincode(String value){
		if(value.equals("")!=true){
			regex ="^([0-9]){6}?$";
			pattern = Pattern.compile(regex);
			matcher = pattern.matcher(value.trim());
			return matcher.matches();
		}else{
			return true;
		}
	}//end validPincode function

	public boolean validMinlength(String value, int length){
		if(value.trim().length()<length){
			return false;
		}
		return true;
	}//end validMinlength function
	
	public boolean validMaxlength(String value, int length){
		if(value.trim().length()>length){
			return false;
		}
		return true;
	}//end validMaxlength function
	
	public boolean validLengthEqual(String value, int length){
		if(value.equals("")!=true){
			if(value.trim().length()==length){
				return true;
			}
			return false;
		}else{
		   return true;
		}
		
	}//end validLengthEqual function
		
	public boolean validMin(String value, Double length){
		
		if(validNumeric(value)==true){
			double value1=Double.parseDouble(value);

			if(value1<length){
				return false;
			}
			return true;
		}else{
			//System.out.println("Invalid number type");
			return false;
		}

	}//end validMin function
	
	public boolean validMax(String value, Double length){
		if(validNumeric(value)==true){
			double value1=Double.parseDouble(value);

			if(value1<length){
				return false;
			}
			return true;
		}else{
			//System.out.println("Invalid number type");
			return false;
		}
	}//end validMax function
	
	public boolean validDate(String value, String DatePattern){
	  if(value.equals("")!=true){	
		if(DatePattern.equals("DD/MM/YYYY")){
			regex = "^[0-9]{2}/[0-9]{2}/[0-9]{4}$";
			pattern = Pattern.compile(regex);
			matcher = pattern.matcher(value);
			return matcher.matches();
		}else if(DatePattern.equals("YYYY/MM/DD")){
			regex = "^[0-9]{4}/[0-9]{2}/[0-9]{2}$";
			pattern = Pattern.compile(regex);
			matcher = pattern.matcher(value);
			return matcher.matches();
		}else if(DatePattern.equals("DD-MM-YYYY")){
			regex = "^[0-9]{2}-[0-9]{2}-[0-9]{4}$";
			pattern = Pattern.compile(regex);
			matcher = pattern.matcher(value);
			return matcher.matches();
		}else if(DatePattern.equals("YYYY-MM-DD")){
			regex = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$";
			pattern = Pattern.compile(regex);
			matcher = pattern.matcher(value);
			return matcher.matches();
		}else{
			//YYYY-MM-DD
			//default dateISO
			regex = "^\\d{4}[/-]\\d{1,2}[/-]\\d{1,2}$";
			pattern = Pattern.compile(regex);
			matcher = pattern.matcher(value);
			return matcher.matches();
		}
	  }else{
			return true;
	  }

	}//end validDate function
	
}//end Validation class