<html>
  <head>
    <title>Automation Test Report</title>
    <style>
  body {
		text-align: center;
		margin: 0;
		padding: 0;
		border: 0;
		font-size:14px;
		}
  div#wrapper {
		margin: 0 auto;
		padding: 0;
		border: 0;
		width: 1110px;
		text-align: left;
		}
  div#header {
		margin: 0;
		padding: 0;
		border: 0;
		width:100%;
		height: 90px;
		background-color:#efefef;
		}
  div#header h1 {
	margin:15px 0 0 0px;
	font-family:'Microsoft YaHei';
	font-weight:bold;
	font-size: 26px;
	text-align:center;
	}
  div#header p {
	margin: 12px 0 0 0;
	line-height: 100%;
	font-family:'Microsoft YaHei';
	font-size: 16px;
	text-align:right;
	}
  div.summary {
		margin: 0;
		padding: 0;
		border: 0;
		width:100%;
		background: transparent;
		}
		
div.summary h2 {
	margin:15px 0 0 15px;
	font-family:'Microsoft YaHei';
	font-weight:bold;
	font-size: 20px;
	color:#2C79E5;
	}
	
div.summary p {
	margin: 12px 0 0 0;
	line-height: 150%;
	}

table.dataintable {
	margin-top:10px;
	border-collapse:collapse;
	border:1px solid #aaa;
	width:100%;
	}

table.dataintable th {
	vertical-align:baseline;
	padding:5px 15px 5px 5px;
	background-color:#d5d5d5;
	border:1px solid #aaa;
	text-align:left;
	}

table.dataintable td {
	vertical-align:text-top;
	padding:5px 15px 5px 5px;
	background-color:#efefef;
	border:1px solid #aaa;
	}
table.dataintable td.fail {
	background-color:#FF5353;
	}

table.dataintable pre {
	width:auto;
	margin:0;
	padding:0;
	border:0;
	background-color:transparent;
	}
	#footer {
		margin: 0;
		padding: 0;
		width: 100%;
		height: 20px;
}
div.image{
	position: absolute;
	left: 30px;
}
    </style>
    <script type="text/javascript">
    	var lastImgId;
    	function showImage(id){
    		var i = document.getElementById(id);
    		if(i.style.display=='none'){
    			var lasImg = document.getElementById(lastImgId);
    			if(lasImg){
    				lasImg.style.display = 'none';
    			}
    			i.style.display = 'block';
    			lastImgId = id;
    		}else{
    			i.style.display = 'none';
    		}
    	}
    </script>
  </head> 
  <body> 
    <div id="wrapper">
      <div id="header">
        <h1>Test Results</h1>
        <p>report time: ${reportTime}</p>
      </div>
      <div class="summary">
        <h2>Summary</h2>
        <table class="dataintable">
          <tbody>
            <tr>
              <th width="25%">Test name</th>
              <th width="10%">Browser</th>
              <th width="15%">executor IP</th>
			  <th width="10%">Scene</th>
			  <th width="10%">Passed</th>
			  <th width="15%">Passed rate</th>
			  <th width="15%">Total duration</th>
		    </tr>
		    <tr>
		      <td>${suiteName}</td>
		      <td>${browserName}</td>
		      <td>${runIP}</td>
			  <td>${sceneCount}</td>
			  <td>${scenePassCount}</td>
			  <td>${(scenePassCount * 100 / sceneCount)?string('#.##')}%</td>
			  <td>${totalDuration}</td>
		    </tr>
		  </tbody>
	    </table>
      </div>
      <div class="summary">
				<h2>Test Scenes Results</h2>
				<table class="dataintable">
					<tbody>
						<tr>
						  <th width="45%">Scene name</th>
						  <th width="25%">Result</th>
						  <th width="30%">Duration(s)</th>
						</tr>
						<#list sceneInfo as s>
	          <tr>
						  <td <#if (s.state!="SUCCESS")>class="fail"</#if>><a href="#${s.name}">${s.name}</a></td>
						  <td <#if (s.state!="SUCCESS")>class="fail"</#if>>${s.state}</td>
						  <td <#if (s.state!="SUCCESS")>class="fail"</#if>>${s.duration}</td>
					  </tr>                     
            </#list>
					</tbody>
				</table>
			</div>
			<#list sceneDetail as d>
			<div class="summary">
				<h2><a name="${d.sceneName}"></a>${d.sceneName}</h2>
			</div>
			<div class="details">
				<table class="dataintable">
					<tbody>
						<tr>
							<th>Operation</th>
							<th>Invoke</th>
							<th>By</th>
							<th>Data</th>
						</tr>
						<#list d.logList as dlist>
						<#if (dlist.logType=="eventLog")>
	          <tr>
						  <td>${dlist.className}</td>
						  <td>${dlist.method}</td>
						  <td>${dlist.location}</td>
						  <td>${dlist.parameter!}</td>
					  </tr>
					  <#elseif (dlist.logType=="exceptionLog")>
					  <tr>
					  	<td class="fail">Exception</td>
						  <td colspan="3">${dlist.message}</td>
						</tr>
					  <#elseif (dlist.logType=="screenShotLog")>
					  <tr>
					  	<td>screen shot</td>
						  <td colspan="3">see screen shot&nbsp;<a onclick="showImage('${dlist.imgId}')" href="javascript:void(0)">${dlist.filename}</a></td>
						</tr>
						<tr><td colspan="4" style="padding:0;border:0"><div id="${dlist.imgId}" class="image" style="display:none"><img src="${dlist.filename}"/></div></td></tr>
					  <#else>
					  <tr>
						  <td>text log</td>
						  <td colspan="3">${dlist.message}</td>
					  </tr>
					  </#if>                    
            </#list>
					</tbody>
				</table>
		  </div>
		  </#list>
		  <div id="footer">		
	    </div>
    </div>
  </body> 
</html>