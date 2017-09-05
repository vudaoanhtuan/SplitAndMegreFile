package function;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

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
	
	public static void splitByPart(String file, String folder, int nPart) throws IOException {
		try {
			File f = new File(file);
			FileInputStream fis = new FileInputStream(f);
			
			long partSize = file.length() / nPart;
			
			String filename = Function.getFileNameFromPath(file);
			
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
			}
			
			String fout = partName + ".part" + nPart;
			FileOutputStream fos = new FileOutputStream(fout);
			int b;
			while ((b = fis.read()) != -1)
				fos.write(b);
			
			fos.close();
			
			fis.close();
		} catch (IOException e) {
			throw e;
		}
		System.out.println("Done!");
	}
}