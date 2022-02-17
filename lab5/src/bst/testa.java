package bst;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class testa<G,M> implements Function {
	private HashMap<G,Set<M>> grupper;
	
	public testa() {
		grupper=new HashMap<G,Set<M>>();
	}
	
	public boolean addGrp(G group) {
		if (grupper.containsKey(group)) {
			return false;
		}
		else {
			grupper.put(group, new HashSet<M>());
			return true;
		}
	}
	
	public boolean addMem(G group, M mem) {
		if (grupper.containsKey(group)) {
			Set<M> sett = grupper.get(group);
			if (sett.contains(mem)) {
				return false;
			}
			else {
				sett.add(mem);
				grupper.put(group, sett);
				return true;
			}
		}
		else {
			return false;
		}
	}
	public  void printGrp() {
		if (grupper.size()==0) {
			System.out.print("Inga grupper");
		}
		else {
			Object[] sortg=grupper.keySet().toArray();
			Arrays.sort(sortg);
			for (int i=0;i<sortg.length;i++) {
				System.out.println(sortg[i]);
				Object[] sortm=grupper.get(sortg[i]).toArray();
				Arrays.sort(sortm);
				for (int j=0;j<sortm.length;j++) {
					System.out.println("  "+sortm[j]);
				}
			}
		}
	}
	
	public static double f(double x) {
		return Math.exp(x)-1+Math.cos(x);
	}
	
	public static double getRoot(double a, double b, double eps) {
		double m=(b-a)/2+a;
		double vala=f(a);
		//System.out.println(vala);
		double valb=f(b);
		//System.out.println(valb);
		double valm=f(m);
		//System.out.println(valm);
		if (Math.abs(vala-valb)<eps) {
			return m;
		}
		if (vala<0 && valm<0 && valb>0) {
			return getRoot(m, b, eps);
		}
		else if (vala>0 && valm>0 && valb<0) {
			return getRoot(m, b, eps);
		}
		else if (valb>0 && valm>0 && vala<0) {
			return getRoot(a, m, eps);
		}
		else if(valb<0 && valm<0 && vala>0) {
			return getRoot(a, m, eps);
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	public static void main(String args[]) {


		System.out.println(getRoot(-2, 0, 0.01));




	}

	@Override
	public Object apply(Object t) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
