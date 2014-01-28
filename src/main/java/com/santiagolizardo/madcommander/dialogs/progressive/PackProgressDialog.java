/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.dialogs.progressive;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.jar.JarOutputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.components.filelisting.FileListing;
import com.santiagolizardo.madcommander.controller.PackTypes;

public class PackProgressDialog extends AbstractProgressDialog {

	private static final long serialVersionUID = 4615507200075446020L;

	private String fileName;

	private PackTypes type;

	private MadCommander mainWindow;

	public PackProgressDialog(MadCommander mainWindow, String fileName,
			PackTypes type) {
		super(mainWindow);

		this.mainWindow = mainWindow;

		this.fileName = fileName;
		this.type = type;
	}

	public void run() {
		FileListing listing = mainWindow.getSource();
		final String currentPath = listing.getPath() + File.separator;
		List<File> files = listing.getSelectedFiles();

		switch (type) {
		case ZIP:
			createZIP(currentPath, fileName + ".zip", files);
			break;
		case JAR:
			createJAR(currentPath, fileName + ".jar", files);
			break;
		case GZIP:
			createGZIP(currentPath, fileName + ".gzip", files);
			break;
		}
	}

	private void createZIP(String currentPath, String fullName, List<File> files) {
		try {
			FileOutputStream fos = new FileOutputStream(fullName);
			ZipOutputStream zos = new ZipOutputStream(fos);

			for (int i = 0; i < files.size(); i++) {
				myProcess.currentFile = currentPath + files.get(i);
				myProcess.currentProgress = 0;
				myProcess.totalProgress = ((i * 100) / files.size());
				final long length = new File(myProcess.currentFile).length();
				FileInputStream fis = new FileInputStream(myProcess.currentFile);
				// zos.putNextEntry(new ZipEntry(myProcess.currentFile));
				zos.putNextEntry(new ZipEntry(files.get(i).getName()));
				byte[] buf = new byte[1024];
				int len = 0;
				int total = 0;
				while ((len = fis.read(buf)) > 0) {
					total += len;
					zos.write(buf, 0, len);
					myProcess.currentProgress = (int) (total * 100 / length);
					if (myProcess.cancel) {
						logger.info("Cancel packing.");
						fis.close();
						zos.close();
						fos.close();
						return;
					}
				}
				zos.closeEntry();
				fis.close();
			}

			zos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		myProcess.totalProgress = 100;

		mainWindow.getSource().refreshFiles();
	}

	private void createJAR(String currentPath, String fullName, List<File> files) {
		try {
			FileOutputStream fos = new FileOutputStream(fullName);
			JarOutputStream jos = new JarOutputStream(fos);

			for (int i = 0; i < files.size(); i++) {
				myProcess.currentFile = currentPath + files.get(i);
				myProcess.currentProgress = 0;
				myProcess.totalProgress = ((i * 100) / files.size());
				final long length = new File(myProcess.currentFile).length();
				FileInputStream fis = new FileInputStream(myProcess.currentFile);
				jos.putNextEntry(new ZipEntry(myProcess.currentFile));
				byte[] buf = new byte[1024];
				int len = 0;
				int total = 0;
				while ((len = fis.read(buf)) > 0) {
					total += len;
					jos.write(buf, 0, len);
					myProcess.currentProgress = (int) (total * 100 / length);
					if (myProcess.cancel) {
						logger.info("Cancel packing.");
						fis.close();
						jos.close();
						fos.close();
						return;
					}
				}
				jos.closeEntry();
				fis.close();
			}

			jos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		myProcess.totalProgress = 100;

		mainWindow.getSource().refreshFiles();
	}

	private void createGZIP(String currentPath, String fullName,
			List<File> files) {
		try {
			FileOutputStream fos = new FileOutputStream(fullName);
			GZIPOutputStream gos = new GZIPOutputStream(fos);

			for (int i = 0; i < files.size(); i++) {
				myProcess.currentFile = currentPath + files.get(i);
				myProcess.currentProgress = 0;
				myProcess.totalProgress = ((i * 100) / files.size());
				final long length = new File(myProcess.currentFile).length();
				FileInputStream fis = new FileInputStream(myProcess.currentFile);
				// gos.putNextEntry(new ZipEntry(myProcess.currentFile));
				byte[] buf = new byte[1024];
				int len = 0;
				int total = 0;
				while ((len = fis.read(buf)) > 0) {
					total += len;
					gos.write(buf, 0, len);
					myProcess.currentProgress = (int) (total * 100 / length);
					if (myProcess.cancel) {
						logger.info("Cancel packing.");
						fis.close();
						gos.close();
						fos.close();
						return;
					}
				}
				// zos.closeEntry();
				fis.close();
			}

			gos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		myProcess.totalProgress = 100;

		mainWindow.getSource().refreshFiles();
	}
}