package com.van;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.model.Resource;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * @goal count
 * @author van
 *
 */
//@Mojo(name = "count")
public class CountMojo extends AbstractMojo {

	private static final String[] INCLUDES_DEFAULT = { "java", "xml", "properties" };

	/**
	 * @parameter expression = "${project.basedir}"
	 * @readonly
	 * @required
	 */
	private File baseDir;

	/**
	 * @parameter expression = "${project.build.sourceDirectory}"
	 * @readonly
	 * @required
	 */
	private File sourceDirectory;

	/**
	 * @parameter expression = "${project.build.testSourceDirectory}"
	 * @readonly
	 * @required
	 */
	private File testSourceDirectory;

	/**
	 * @parameter expression = "${project.build.resources}"
	 * @readonly
	 * @required
	 */
	private List<Resource> resources;

	/**
	 * @parameter expression = "${project.build.testResources}"
	 * @readonly
	 * @required
	 */
	private List<Resource> testResources;

	/**
	 * The file types which will be incluede for counting
	 * 
	 * @parameter
	 */
	private String[] includes;

	public void execute() throws MojoExecutionException, MojoFailureException {
		if (includes == null || includes.length == 0) { // 如果没有在pom.xml中说明includes，就是用默认的includes。
			includes = INCLUDES_DEFAULT;
		}
		try {
			// 分别统计四种目录下的代码行数。
			countDir(sourceDirectory);
			countDir(testSourceDirectory);
			for (Resource resource : resources) {
				countDir(new File(resource.getDirectory()));
			}
			for (Resource resource : testResources) {
				countDir(new File(resource.getDirectory()));
			}
		} catch (Exception e) {
			throw new MojoExecutionException("Unable to count lines of code.", e);
		}
	}

	/**
	 * 统计该目录下所有文件的代码行数之和。
	 * 
	 * @param dir
	 * @throws IOException
	 */
	private void countDir(File dir) throws IOException {
		if (!dir.exists()) {
			return;
		}
		List<File> collected = new ArrayList<File>();
		collectFiles(collected, dir);
		int lines = 0;
		for (File sourceFile : collected) {
			lines += countLine(sourceFile);
		}
		String path = dir.getAbsolutePath().substring(baseDir.getAbsolutePath().length());
		getLog().info(path + ":" + lines + " lines of code in " + collected.size() + " files");
	}
	
	/**
	 * 递归的统计该目录下的所有文件，并放入list
	 * @param collected
	 * @param file
	 */
	private void collectFiles(List<File> collected, File file){
		if(file.isFile()){
			for(String include : includes){
				if(file.getName().endsWith("."+include)){
					collected.add(file);
					break;
				}
			}
		}else{
			for(File sub : file.listFiles()){
				collectFiles(collected,sub);
			}
		}
}
	
	/**
	 * 统计代码行数。
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	private int countLine(File file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		int line = 0;
		try {
			while (reader.ready()) {
				reader.readLine();
				line++;
			}
		} finally {
			reader.close();
		}
		return line;
	}
}
