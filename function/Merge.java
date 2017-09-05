package function;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import function.Function;

public class Merge {
	public static void merge(String pathFile, String folder) {
		try {
			String fileName = Function.getFileNameFromPath(pathFile);
			String originName = Function.getOriginFileName(fileName);
			
			if (folder.charAt(folder.length() - 1) != '/')
				folder = folder + '/';
			
			String fullOriginPath = folder + originName;
			
			File file = new File(fullOriginPath);
			FileOutputStream origin = new FileOutputStream(file);
			
			try {
				int i = 1;
				while (true) {
					String fin = fullOriginPath + ".part" + i;
					FileInputStream fis = new FileInputStream(fin);
					int b;
					while ((b=fis.read()) != -1)
						origin.write(b);
					fis.close();
					i++;
				}
			} catch (Exception e) {
				System.out.println("Done!");
			}
			
			origin.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
