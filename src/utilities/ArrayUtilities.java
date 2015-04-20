package utilities;


public class ArrayUtilities {
	public static <E> void reverse(E[] array){
		for(int i=0; i < array.length / 2; i++){
			E temp = array[i];
			array[i] = array[array.length - 1 - i];
			array[array.length - 1 - i] = temp;
		}
	}
	
	public static void reverse(double[] array){
		for(int i=0; i < array.length / 2; i++){
			double temp = array[i];
			array[i] = array[array.length - 1 - i];
			array[array.length - 1 - i] = temp;
		}
	}
	
	public static void reverse(int[] array){
		for(int i=0; i < array.length / 2; i++){
			int temp = array[i];
			array[i] = array[array.length - 1 - i];
			array[array.length - 1 - i] = temp;
		}
	}
	
	public static void reverse(float[] array){
		for(int i=0; i < array.length / 2; i++){
			float temp = array[i];
			array[i] = array[array.length - 1 - i];
			array[array.length - 1 - i] = temp;
		}
	}
	
	public static void reverse(long[] array){
		for(int i=0; i < array.length / 2; i++){
			long temp = array[i];
			array[i] = array[array.length - 1 - i];
			array[array.length - 1 - i] = temp;
		}
	}

	public static void reverse(short[] array){
		for(int i=0; i < array.length / 2; i++){
			short temp = array[i];
			array[i] = array[array.length - 1 - i];
			array[array.length - 1 - i] = temp;
		}
	}
	
	public static void reverse(byte[] array){
		for(int i=0; i < array.length / 2; i++){
			byte temp = array[i];
			array[i] = array[array.length - 1 - i];
			array[array.length - 1 - i] = temp;
		}
	}
}
