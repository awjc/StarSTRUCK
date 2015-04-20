package skeleton;

public class Platform {
	public static boolean isLinux(){
		return System.getProperty("os.name").contains("Linux");
	}
}
