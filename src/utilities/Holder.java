package utilities;

public class Holder<A, B> {
	private A a;
	private B b;
	
	public Holder(A a, B b){
		this.a = a;
		this.b = b;
	}
	
	public A getA(){
		return a;
	}
	
	public B getB(){
		return b;
	}
}
