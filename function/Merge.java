package function;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.eclipse.swt.widgets.ProgressBar;

import function.Function;

public class Merge {
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
			
			FileOutputStream origin = new FileOutputStream(fullOriginPath);
			
			int percent = 0;
			bar.setMaximum(size);
			
			for (int i=1; i<n+1; i++) {
				String fin = fullOriginPath + ".part" + i;
				FileInputStream fis = new FileInputStream(fin);
				
				int b;
				while ((b=fis.read()) != -1) {
					origin.write(b);
					percent++;
				}
				
				bar.setSelection(percent);
				fis.close();
			}
			
			origin.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Done!");
	}
}
