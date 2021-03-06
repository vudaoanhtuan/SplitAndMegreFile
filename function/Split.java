package function;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.eclipse.swt.widgets.ProgressBar;


public class Split {
//	static void split(String filename, int n) {
//		try {
//			File file = new File(filename);
//			long partSize = file.length() / n;
//			
//			FileInputStream fis = new FileInputStream(file);
//			
//			for (int i=1; i<n; i++) {
//				String fout = filename + ".part" + i;
//				FileOutputStream fos = new FileOutputStream(fout);
//				for (int j=0; j<partSize; j++) {
//					int b = fis.read();
//					fos.write(b);
//				}
//				fos.close();
//			}
//			
//			String fout = filename + ".part" + n;
//			FileOutputStream fos = new FileOutputStream(fout);
//			int b;
//			while ((b = fis.read()) != -1)
//				fos.write(b);
//			fos.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public static void splitByPart(String filePath, String folder, int nPart, ProgressBar bar) throws IOException {
		try {
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			
			long partSize = file.length() / nPart;
			bar.setMaximum((int) file.length());
			
			int percent = 0;
			
			String filename = Function.getFileNameFromPath(filePath);
			
			String partName = folder;
			if (folder.charAt(folder.length() - 1) != '/')
				partName = partName + '/';
			partName = partName + filename;
			
			for (int i=1; i<nPart; i++) {
				String fout = partName + ".part" + i;
				FileOutputStream fos = new FileOutputStream(fout);
				for (int j=0; j<partSize; j++) {
					int b = fis.read();
					fos.write(b);
				}
				fos.close();
				percent += partSize;
				bar.setSelection(percent);
			}
			
			String fout = partName + ".part" + nPart;
			FileOutputStream fos = new FileOutputStream(fout);
			int b;
			while ((b = fis.read()) != -1) {
				fos.write(b);
				percent++;
			}
			bar.setSelection(percent);
			
			fos.close();
			
			fis.close();
		} catch (IOException e) {
			throw e;
		}
		System.out.println("Done!");
		
	}
	
	public static void splitBySize(String filePath, String folder, int partSize, ProgressBar bar) throws IOException {
		try {
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			
			int nPart = (int) file.length() / partSize + 1;
			bar.setMaximum((int) file.length());
			
			int percent = 0;
			
			String filename = Function.getFileNameFromPath(filePath);
			
			String partName = folder;
			if (folder.charAt(folder.length() - 1) != '/')
				partName = partName + '/';
			partName = partName + filename;
			
			for (int i=1; i<nPart; i++) {
				String fout = partName + ".part" + i;
				FileOutputStream fos = new FileOutputStream(fout);
				for (int j=0; j<partSize; j++) {
					int b = fis.read();
					fos.write(b);
				}
				fos.close();
				percent += partSize;
				bar.setSelection(percent);
			}
			
			String fout = partName + ".part" + nPart;
			FileOutputStream fos = new FileOutputStream(fout);
			int b;
			while ((b = fis.read()) != -1) {
				fos.write(b);
				percent++;
			}
			bar.setSelection(percent);
			
			fos.close();
			
			fis.close();
		} catch (IOException e) {
			throw e;
		}
		System.out.println("Done!");
		
	}
}