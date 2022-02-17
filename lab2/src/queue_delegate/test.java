package queue_delegate;

public class test {

	public static double computeCapital(double capital, int years, double rate) {
	    if (years >0){
	        double kr=capital*rate+capital;
	        return computeCapital(kr,years-1,rate);
	    }
	    return capital;
	}
	
	public static void main(String args[]) {
		System.out.println(computeCapital(1000,3,0.1));
	}
	
}
