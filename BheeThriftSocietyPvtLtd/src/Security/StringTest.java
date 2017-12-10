package Security;

public class StringTest {
	
@SuppressWarnings("finally")
public int strLenght(String s){
	int length=0;
	try{
		
		length=s.length();
		return length;
	}catch(Exception e){
		
	}finally{
		return length;
	}
}	


public String strPatternMatch(String s){
	return "success";
	
}
}
