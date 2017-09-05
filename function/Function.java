package function;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Function {
	public static String getFileNameFromPath(String path) {
		String[] list = path.split("[/\\\\]");
		return list[list.length - 1];
	}
	
	public static String getFolderNameFromPath(String path) {
		int linux = path.lastIndexOf('/');
		int window = path.lastIndexOf('\\');
		if (linux == -1) {
			return path.substring(0, window);
		}
		return path.substring(0, linux);
	}
	
	public static String getOriginFileName(String name) {
		int pos = name.lastIndexOf(".part");
		return name.substring(0, pos);
	}
}

