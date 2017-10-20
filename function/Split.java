package function;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.text.AbstractDocument.LeafElement;

import org.eclipse.swt.widgets.ProgressBar;


public class Split {
	static final int bufferSize = 1000000;
	static byte[] buffer = new byte[bufferSize];
	static int lenBuffer;
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
	
	public static void splitByPart(String filePath, String folder, int nPart) throws IOException {
		
		try {
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			
			long partSize = file.length() / nPart;
			
			String filename = Function.getFileNameFromPath(filePath);
			
			String partName = folder;
			if (folder.charAt(folder.length() - 1) != '/')
				partName = partName + '/';
			partName = partName + filename;
			
			for (int i=1; i<nPart+1; i++) {
				lenBuffer = 0;
				
				String fout = partName + ".part" + i;
				FileOutputStream fos = new FileOutputStream(fout);

				long curPartSize = partSize;
				
				while (curPartSize > 1) {
					fis.read(buffer, 0, (int) Math.min(curPartSize, bufferSize));
					fos.write(buffer, 0, (int) Math.min(curPartSize, bufferSize));
					curPartSize -= bufferSize;
				}
				if (i == nPart) {
					int lenRead;
					while ((lenRead = fis.read(buffer)) != -1) {
						fos.write(buffer, 0, lenRead);
					}
				}
				
				fos.close();
			}
	
			fis.close();
		} catch (IOException e) {
			throw e;
		}
		System.out.println("Done!");
		
	}
	
	public static void splitBySize(String filePath, String folder, int partSize) throws IOException {
		try {
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			
			int nPart = (int) file.length() / partSize + 1;

			
			String filename = Function.getFileNameFromPath(filePath);
			
			String partName = folder;
			if (folder.charAt(folder.length() - 1) != '/')
				partName = partName + '/';
			partName = partName + filename;
			
			for (int i=1; i<nPart; i++) {
				String fout = partName + ".part" + i;
				FileOutputStream fos = new FileOutputStream(fout);
				long curPartSize = partSize;				
				while (curPartSize > 1) {
					fis.read(buffer, 0, (int) Math.min(curPartSize, bufferSize));
					fos.write(buffer, 0, (int) Math.min(curPartSize, bufferSize));
					curPartSize -= bufferSize;
				}
				fos.close();
			}
			
			String fout = partName + ".part" + nPart;
			FileOutputStream fos = new FileOutputStream(fout);
			int lenRead;
			while ((lenRead = fis.read(buffer)) != -1) {
				fos.write(buffer, 0, lenRead);
			}			
			fos.close();
			fis.close();
		} catch (IOException e) {
			throw e;
		}
		System.out.println("Done!");

	}
}