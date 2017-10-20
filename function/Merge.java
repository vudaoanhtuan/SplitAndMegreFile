package function;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.eclipse.swt.widgets.ProgressBar;

import function.Function;

public class Merge {
	static final int bufferSize = 1000000;
	static byte[] buffer = new byte[bufferSize];
	public static void merge(String pathFile, String folder, ProgressBar bar) {
		try {
			String fileName = Function.getFileNameFromPath(pathFile);
			String originName = Function.getOriginFileName(fileName);
			
			if (folder.charAt(folder.length() - 1) != '/')
				folder = folder + '/';
			
			String fullOriginPath = folder + originName;
			
			int n = 1;			
			int size = 0;
			File file = new File(fullOriginPath + ".part" + n);
			while (file.exists()) {
				n++;
				size += file.length();
				file = new File(fullOriginPath + ".part" + n);
			}
			n--;
			
			
			FileOutputStream originFile = new FileOutputStream(fullOriginPath);
			
			int percent = 0;
			bar.setMaximum(size);
			
			
			int lenBuffer = 0;
			
			for (int i=1; i<n+1; i++) {
				String fin = fullOriginPath + ".part" + i;
				FileInputStream fis = new FileInputStream(fin);
				int lenRead;
				while ((lenRead = fis.read(buffer)) != -1) {
					originFile.write(buffer, 0, lenRead);
				}
				fis.close();
			}
			originFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Done!");
	}
}
