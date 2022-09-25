package com.github.uniqueT.webAuto.util.testNG.log;

/**
 * A log is a case invoking record, and will turn into one line in report. Contains level relations with other cases or events.
 * @author uniqueT
 *
 */
public class CaseLog {
	
	private String caseName;
	private String caseComment;
	
	private CaseLog() {
		
	}
	
	/**
	 * create a case log by its name
	 * @param _caseName usually it's methodName
	 * @return
	 */
	public static CaseLog getInstance(String _caseName) {
		CaseLog log = new CaseLog();
		log.caseName = _caseName;
		log.caseComment = _caseName;
		return log;
	}

	/**
	 * get case name
	 * @return case name
	 */
	public String getCaseName() {
		return caseName;
	}

	/**
	 * get case comments
	 * @return case comments
	 */
	public String getCaseComment() {
		return caseComment;
	}

	/**
	 * case's comment is same as its name by default. Use this method to specific anonther one
	 * @param caseComment
	 */
	public void setCaseComment(String caseComment) {
		this.caseComment = caseComment;
	}

}
