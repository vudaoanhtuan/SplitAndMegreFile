package function;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Function {
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