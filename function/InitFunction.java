package function;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class InitFunction {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = "D:\\Tool\\FFSJ\\FFSJ.exe";
		String[] list = path.split("[/\\\\]");
		System.out.println(list.length);
//		System.out.println(list[list.length - 1]);
//		System.out.println(path);
	}

}

class Fun {
	static String getFileNameFromPath(String path) {
		String[] list = path.split("[/\\\\]");
		return list[list.length - 1];
	}
}



class Merge {
	static void merge(String filename, int n) {
		
		try {
			File file = new File(filename);
			FileOutputStream origin = new FileOutputStream(file);
			
			for (int i=1; i<n+1; i++) {
				String fin = filename + ".part" + i;
				FileInputStream fis = new FileInputStream(fin);
				
				int b;
				while ((b=fis.read()) != -1)
					origin.write(b);
				fis.close();
			}
			
			origin.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}