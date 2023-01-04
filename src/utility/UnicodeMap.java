package utility;

public class UnicodeMap {
	
	static String getSubscript(char c){
		String output=null;
		switch(c) {

		case '0':
			
			output =  "N\u2080";
			break;


		case '1':
			output =  "\u2081";
			break;


		case '2':
			output =  "\u2082";
			break;


		case '3':
			output =  "\u2083";
			break;
		case '4':
			output =  "\u2084";
			break;


		case  '5':
			output =  "\u2085";
			break;


		case '6' :
			output =  "\u2086";
			break;
		case '7' :
			output =  "\u2087";
			break;


		case '8' :
			output =  "\u2088";
			break;


		case  '9':
			output =  "\u2089";
			break;

		}
		System.out.println(output);
		return output;


	}
	
	public static void main(String args[]) {
		
		String test ="0123456789";
		System.out.println("N\u2080");
		for(int i=0;i<test.length();i++) {
			
			System.out.println(getSubscript(test.charAt(i)));
		}
		
	}

}
