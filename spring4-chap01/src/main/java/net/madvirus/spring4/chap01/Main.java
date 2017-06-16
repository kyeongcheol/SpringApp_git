package net.madvirus.spring4.chap01;

import java.util.ArrayList;
import java.util.List;

public class Main 
{
	
	public static void main(String [] args)
	{
		//MavenBuildRunner 객체 생성
		MavenBuildRunner buildRunner = new MavenBuildRunner();
		
		//Maven 경로
		buildRunner.setMavenPath("C:\\java\\apache-maven-3.3.9");
		
		//Project 객체 생성
		Project sampleProject = new Project();
		
		List<String>srcDirs = new ArrayList<>();
		
		srcDirs.add("src");
		srcDirs.add("srcResources");
		sampleProject.setSrcDirs(srcDirs);
		sampleProject.setBinDir("bin");
		sampleProject.setBuildRunner(buildRunner);
		
		sampleProject.build();

	}

}
