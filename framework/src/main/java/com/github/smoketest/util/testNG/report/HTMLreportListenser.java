package com.github.smoketest.util.testNG.report;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.internal.TestResult;
import org.testng.xml.XmlSuite;

import com.github.smoketest.util.CommonUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * used in the listener property of xml file
 * @author uniqueT
 *
 */
public class HTMLreportListenser implements IReporter {
	
	private String DEFAULT_REPORT_DIR = "report";
	
	private String reportFilename;
	private String rootPath;
	private String outputDir;
	private boolean hasScreenShot = false;

	/**
	 * <p>see also {@link org.testng.IReporter}</p>
	 * <p>get result from {@link SceneResultList}, get operation log from {@link org.testng.Reporter}, log is created by {@link com.github.smoketest.util.testNG.log.Logger}</p>
	 * <p>html file is created by <a href="https://freemarker.apache.org/" target="_blank">freemarker</a></p>
	 */
	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		ISuite s = suites.get(0);
		outputDir = s.getParameter("outputDir");
		if(outputDir==null || "".equals(outputDir)) {
			outputDir = DEFAULT_REPORT_DIR;
		}
		System.out.println("outputDir="+outputDir);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String dateStr = sdf.format(new Date());
		
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
		//System.out.println("path1 : "+HTMLreportListenser.class.getClassLoader().getResource(""));
		//root path of project
		rootPath = HTMLreportListenser.class.getClassLoader().getResource("").getPath();
		try {
			cfg.setClassForTemplateLoading(HTMLreportListenser.class, "/template/");
			//cfg.setDirectoryForTemplateLoading(new File(rootPath));
			cfg.setDefaultEncoding("UTF-8");
			Template template = cfg.getTemplate("report.ftl");
	        reportFilename = dateStr + "-REPORT-" + s.getName();
	        File file = new File(outputDir + File.separator + reportFilename + ".html");
	        //get test result data
			Map<String, Object> root = getResultData(s, dateStr);
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			//put into template
	        template.process(root, out); 
	        out.flush();  
	        out.close();
	        
	        //clean screen shots
	        if(hasScreenShot) {
	        	FileUtils.deleteDirectory(new File(rootPath + File.separator + CommonUtil.ScreenShotTMP));
	        }
	        
		} catch (IOException | TemplateException e) {
			e.printStackTrace();
		}
		
	}

	private Map<String, Object> getResultData(ISuite s, String dateStr) throws IOException {
		//head
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("reportTime", dateStr);
		root.put("suiteName", s.getName());
		root.put("browserName", s.getAllMethods().get(0).getMethodName());
		root.put("runIP", getRunIP(s));
		
		//summary
		List<ITestResult> list = SceneResultList.getResultList();
		root.put("sceneCount", list.size());
		int passCount = getPassedSceneCount(list);
		root.put("scenePassCount", passCount);
		long startTime1 = (long)list.get(0).getAttribute("startTime");
		long endTime1 = list.get(list.size()-1).getEndMillis();
		root.put("totalDuration", String.format("%.2f", ((endTime1 - startTime1)/1000d/60d)));
		
		//scene summary list
		List<Map<String, String>> sceneInfo = getSceneSummaryList(list);
		root.put("sceneInfo", sceneInfo);
		
		//scene detail list
		List<Map<String, Object>> sceneDetail = new ArrayList<Map<String, Object>>();
		for(ITestResult tr : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sceneName", tr.getName());
			map.put("logList", getParsedLog(Reporter.getOutput(tr)));
			sceneDetail.add(map);
		}
		root.put("sceneDetail", sceneDetail);
		
		return root;
	}

	private String getRunIP(ISuite s) {
		String runIP = null;
		String testClassName = s.getAllMethods().get(0).getTestClass().getName();
		if(testClassName.endsWith("LocalExecuteTest")){
			try {
				runIP = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				e.printStackTrace();
				runIP = "get ip address error";
			}
		}else {
			runIP = s.getParameter("remoteIp");
		}
		return runIP;
	}

	//get amount of executed scenes
	private int getPassedSceneCount(List<ITestResult> list) {
		long count = list.stream().filter((tr) -> tr.getStatus()==TestResult.SUCCESS).count();
		return (int)count;
	}
	
	private List<Map<String, String>> getSceneSummaryList(List<ITestResult> list){
		List<Map<String, String>> sceneInfo = new ArrayList<Map<String, String>>();
		list.forEach((tr) -> {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("name", tr.getName());
			map.put("state", tr.getStatus()==TestResult.SUCCESS ? "SUCCESS" : "FAIL");
			long startTime2 = (long)tr.getAttribute("startTime");
			long endTime2 = tr.getEndMillis();
			map.put("duration", String.format("%.2f", ((endTime2 - startTime2)/1000d)));
			sceneInfo.add(map);
		});
		return sceneInfo;
	}
	
	private List<Map<String, String>> getParsedLog(List<String> rawLog) throws IOException{
		List<Map<String, String>> logList = new ArrayList<Map<String, String>>();
		Pattern p = Pattern.compile("#(.+?)##(.+?)##(.+?)##(.+?)##(.+?)#");
		for(String oneLine : rawLog) {
			//System.out.println("oneLine : "+oneLine);
			Map<String, String> map = new HashMap<String, String>();
			if(oneLine.startsWith("#event#")) {
				map.put("logType", "eventLog");
				Matcher m = p.matcher(oneLine);
				m.find();
				map.put("className", "["+m.group(2)+"]");
				map.put("method", m.group(3));
				map.put("location", m.group(4));
				map.put("parameter", m.group(5));
			}else if(oneLine.startsWith("#exception#")) {
				map.put("logType", "exceptionLog");
				map.put("message", oneLine.substring(11));
			}else if(oneLine.startsWith("#screenshot#")) {
				hasScreenShot = true;
				map.put("logType", "screenShotLog");
				map.put("filename", reportFilename + File.separator +oneLine.substring(12));
				map.put("imgId", oneLine.substring(0, oneLine.length()-4));
				moveImage(oneLine.substring(12));
			}else {
				map.put("logType", "customLog");
				map.put("message", oneLine);
			}
			logList.add(map);
		}
		return logList;
	}

	//move screen shots
	private void moveImage(String filename) throws IOException {
		File dir = new File(outputDir + File.separator + reportFilename);
		if(!dir.exists()) {
			dir.mkdir();
		}
		File src = new File(rootPath + File.separator + CommonUtil.ScreenShotTMP + File.separator + filename);
		File desc = new File(outputDir + File.separator + reportFilename + File.separator + filename);
		FileUtils.copyFile(src, desc);
	}

}
