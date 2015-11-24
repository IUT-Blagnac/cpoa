package de.mallox.doclet;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import com.sun.tools.javadoc.Main;

public class Runner {

	private static final FilenameFilter FILENAME_FILTER = new FilenameFilter() {
		@Override
		public boolean accept(File tFile, String tName) {
			if (tFile.isDirectory()) {
				return true;
			}
			if (tName.endsWith(".java")) {
				return true;
			}
			return false;
		}
	};

	public static void main(String[] pArgs) {

		List<File> tFiles = new ArrayList<File>();
		for (String tPathName : pArgs) {
			File tFile = new File(tPathName);
			if (tFile.exists()) {
				tFiles.add(tFile);
			} else {
				System.out.println("skip " + tPathName + "!");
			}
		}

		Runner tRunner = new Runner();
		tRunner.run(tFiles);
	}

	private void run(List<File> pFiles) {				
		List<String> tFileNameList = new ArrayList<String>();
		
		// add parameters:
		tFileNameList.add("-private");
		
		for (File tFile : pFiles) {
			creatFilelist(tFileNameList, tFile, FILENAME_FILTER);
		}
				
		String[] tParams = tFileNameList.toArray(new String[tFileNameList
				.size()]);
		Main.execute("javadoc", "de.mallox.doclet.PlantUMLDoclet", tParams);
	}

	private List<String> creatFilelist(List<String> pFileList, File pFile,
			FilenameFilter pFileNameFilter) {
		File[] tFiles = pFile.listFiles(pFileNameFilter);
		for (File tFile : tFiles) {
			if (tFile.isDirectory()) {
				creatFilelist(pFileList, tFile, pFileNameFilter);
			} else {
				pFileList.add(tFile.getAbsolutePath());
			}
		}

		return pFileList;
	}
}
