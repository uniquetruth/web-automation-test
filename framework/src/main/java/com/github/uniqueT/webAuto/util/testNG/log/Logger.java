package com.github.uniqueT.webAuto.util.testNG.log;

import org.testng.Reporter;

/**
 * <p>static log utility, each method turns to a line in the report</p>
 * <p>there are 6 types of log:</p>
 * <p><b>event log</b>: as every event's return value</p>
 * <p><b>exception log</b>: anything caught by exception block</p>
 * <p><b>screen shot log</b>: created by {@linkplain com.github.uniqueT.webAuto.util.CommonUtil#takeScreenShot CommonUtil.takeScreenShot()}</p>
 * <p><b>text log</b>: normal string log</p>
 * <p><b>case log</b>: a case log, indicates the start of a case</p>
 * <p><b>case end log</b>: indicates the end of a case, information of this log display in the same line of case log</p>
 * @author uniqueT
 *
 */
public class Logger {
	
	/**
	 * write event log
	 * @param log every event's return value
	 */
	public static void eventLog(EventLog log) {
		if(log!=null) {
			StringBuilder s = new StringBuilder("#event##").append(log.getEventName()).append("##")
				.append(log.getmethodName()).append("##").append(log.getBy()).append("##")
				.append(log.getValue()).append("#");
			Reporter.log(s.toString());
		}
	}
	
	/**
	 * write text log
	 * @param message
	 */
	public static void stringLog(String message) {
		Reporter.log(message);
	}
	
	/**
	 * write exception log
	 * @param e
	 */
	public static void exceptionLog(Exception e) {
		StringBuilder s = new StringBuilder("#exception#").append(e.getMessage()).append("<br/>");
		for(StackTraceElement ele : e.getStackTrace()) {
			s.append("&nbsp;&nbsp;&nbsp;&nbsp;").append(ele.toString()).append("<br/>");
		}
		Reporter.log(s.toString());
	}
	
	/**
	 * write screen shot log
	 * @param filename file name of the screen shot
	 */
	public static void screenShotLog(String filename) {
		if(filename==null)
			return;
		StringBuilder s = new StringBuilder("#screenshot#").append(filename);
		Reporter.log(s.toString());
	}

	/**
	 * write a case log
	 * @param log case log
	 * @return uid of the case used in framework
	 */
	public static long caseLog(CaseLog log) {
		long caseId = System.nanoTime();
		if(log!=null) {
			StringBuilder s = new StringBuilder("#case_start##").append(log.getCaseName())
					.append("##").append(log.getCaseComment()).append("##")
					.append(caseId).append("#");
			Reporter.log(s.toString());
		}
		return caseId;
	}

	/**
	 * write a case end log with some attached information
	 * @param caseId return value of {@link #caseLog(CaseLog)}
	 * @param costTime time cost of the case
	 */
	public static void caseEndLog(long caseId, float costTime) {
		Reporter.log("#case_end#"+caseId+"#"+String.format("%.2f", costTime));
	}

}
