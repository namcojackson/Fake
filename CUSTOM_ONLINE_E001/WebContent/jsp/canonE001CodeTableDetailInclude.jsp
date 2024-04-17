<%@page import="canon.apps.pci.util.CanonPCISecurityUtil"%>
<%@page import="com.canon.apps.e001.CanonE001CodeTableDAO.CodeTableInfo"%>
<%@page import="canon.apps.common.CanonS21SessionValidate"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.canon.apps.e001.CanonE001CodeTableDAO"%>
<%@page import="com.canon.apps.e001.CanonE001CommonUtil"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*"%>
<%@page import="java.util.Date"%>
<%@page import="static com.canon.apps.e001.CanonE001CommonUtil.checkResult"%>
<%@page import="static com.canon.apps.e001.CanonE001CommonUtil.checkErrors"%>
<%@page import="static com.canon.apps.e001.CanonE001CommonUtil.E001Exception"%>
<%@page errorPage="canonE001ErrorPage.jsp"%>

<%!
final static int ALL_ROWS = Integer.MAX_VALUE;
static String LEVEL_NAMES[]={"APPLICATION","ROLE","USER"};
static String toLevelName(BigDecimal levelNum){
	return levelNum!=null && levelNum.intValue()<4? LEVEL_NAMES[levelNum.intValue()-1] : null;
}

static int[] optValRange(String colFormat){
	if("CHAR".equals(colFormat)){
		return new int[]{1, 50};
	}if("NUMBER".equals(colFormat)){
		return new int[]{51, 75};
	}else if("DATE".equals(colFormat)){
		return new int[]{76, 100};
	}else{
		return new int[]{1, 100};
	}
}

private String safeHtmlAttr(String str){
	return CanonPCISecurityUtil.htmlAttrEncode(CanonE001CommonUtil.checkNull(str));
}

%>
<%
	String p_order_by=null;
	String p_sort_order=null;
	int total_record=0;
	int totalLks=0;
	String pageLinkMsg=null;
	
	String p_rows_per_page = request.getParameter("rowsPerPage");
	int rowsPerPage = p_rows_per_page == null ? 15 : CanonE001CommonUtil.toInt(p_rows_per_page);
	int page_no = CanonE001CommonUtil.isEmpty(request.getParameter("page_no")) ? 1 : CanonE001CommonUtil.toInt(request.getParameter("page_no"));
	System.out.println("page_no : " + page_no);
	List<CanonE001CodeTableDAO.CanonS21CdVal> valuesList=null;

	String strAction = request.getParameter("action");
	String sortBy = "";
	String sortOrder = "asc";
	if (request.getParameter("sortBy") != null) {
		sortBy = request.getParameter("sortBy");
	}
	if (request.getParameter("sortOrder") != null) {
		sortOrder = request.getParameter("sortOrder");
	}
	System.out.println("strAction : " + strAction);
	String cdName=null;
	String cdDescription=null;
	CanonE001CodeTableDAO.CodeTableInfo codeTableInfo=null;
	List<CanonE001CodeTableDAO.CodeTableColumn> columnList=null; 
	BigDecimal cdID=CanonE001CommonUtil.toBigDecimal(request.getParameter("cd_id"), false);
	int tabIndex=CanonE001CommonUtil.toBigDecimal(request.getParameter("tab_index"), true).intValue();
	System.out.println("code table ID : " + cdID);
	System.out.println("tabIndex : " + tabIndex);
	boolean isCreateNew=false;
	boolean isChange=false;
	boolean isCreated=false;
	boolean hasData=false;
	String createCodeTableError=null;
	if ("create_new".equals(strAction)) {
		isCreateNew=true;
	}else if ("create".equals(strAction)) {
		cdName = request.getParameter("cdName");
		cdDescription = request.getParameter("cdDescription");
		Object[] result=CanonE001CodeTableDAO.createUpdateCodeTable(
				CanonS21SessionValidate.getUserName(), cdName,
				cdDescription, null);
		if(result==null){
	    	createCodeTableError="Unknown database error";
	    	isCreateNew=true;
		}else{
	        String status=(String)CanonE001CommonUtil.second(result);
	    	System.out.println("status : " + status);
	        if("E".equals(status)){
		    	createCodeTableError=(String)CanonE001CommonUtil.third(result);
		    	System.out.println("error message : " + createCodeTableError);
		    	isCreateNew=true;
	        }else{
	        	BigDecimal newcdID=(BigDecimal)CanonE001CommonUtil.first(result);
		        System.out.println("newcdID is " +newcdID);
		        cdID=newcdID;
		        isCreated=true;
				isChange=true;
	        }
		}
	}else if("change".equals(strAction)){
		isChange=true;
	}else if("save".equals(strAction)){
		isChange=true;
		
		cdName = request.getParameter("cdName");
		cdDescription = request.getParameter("cdDescription");
        System.out.println("cdName is " +cdName);
        System.out.println("cdDescription is " +cdDescription);
        System.out.println("cdID is " +cdID);
        Object[] result=CanonE001CodeTableDAO.createUpdateCodeTable(
				CanonS21SessionValidate.getUserName(), cdName,
				cdDescription, cdID);
        checkErrors(result, 1,2);
		
 		String[] col_ids=request.getParameterValues("col_id");
 		String[] db_col_names=request.getParameterValues("db_col_name");
 		String[] col_display_names=request.getParameterValues("col_display_name");
 		String[] col_formats=request.getParameterValues("col_format");
 		String[] col_is_mandatorys=request.getParameterValues("is_mandatory");
 		String[] result_cols=request.getParameterValues("result_col");
 		List<CanonE001CodeTableDAO.CanonE001CodeTabColRec> cols=new ArrayList<CanonE001CodeTableDAO.CanonE001CodeTabColRec>();
 		int seq=0;
 		for(int i=0; db_col_names!=null && i<db_col_names.length;i++){
 			String dbColName=db_col_names[i];
 			if(dbColName!=null && !dbColName.trim().isEmpty()){
 				dbColName=dbColName.toUpperCase();
	 			BigDecimal colId=CanonE001CommonUtil.toBigDecimal(col_ids[i],false);
	 			String colFormat=col_formats[i];
	 			String colPrompt=col_display_names[i];
	 			String isMandatory=col_is_mandatorys[i];
	 			String resultCol=result_cols[i];
	 			seq++;
	 			BigDecimal colSeq=new BigDecimal(seq);
	 			CanonE001CodeTableDAO.CanonE001CodeTabColRec col=new CanonE001CodeTableDAO.CanonE001CodeTabColRec(
	 					cdID,
	 					colId,
	 					dbColName,
	 					colFormat,
	 					colPrompt,
	 					colSeq,
	 					isMandatory,
	 					resultCol);
	 			cols.add(col);
 			}
 		}
 		//System.out.println(cols);
 		result=CanonE001CodeTableDAO.createUpdateCodeTabCol(CanonS21SessionValidate.getUserName(), cdID, cols);
        checkErrors(result, 0,1);
	}else if("delete".equals(strAction)){
		BigDecimal colID=CanonE001CommonUtil.toBigDecimal(request.getParameter("delete_column_id"),false);
 		Object[] result=CanonE001CodeTableDAO.deleteCodeTabCol(CanonS21SessionValidate.getUserName(), cdID, colID);
        checkErrors(result, 0,1);
		isChange=true;
	}else if("reload".equals(strAction)){
		isChange=true;
	}else if("delete_cdval".equals(strAction)){
		BigDecimal cdvalID=CanonE001CommonUtil.toBigDecimal(request.getParameter("delete_cdval_id"),false);
 		Object[] result=CanonE001CodeTableDAO.deleteCodeTabVal(CanonS21SessionValidate.getUserName(), cdID, cdvalID);
        checkErrors(result, 0,1);
		isChange=true;
	}else if("save_cdval".equals(strAction)){
       	// save cd description
		cdName = request.getParameter("cdName");
		cdDescription = request.getParameter("cdDescription");
        System.out.println("cdName is " +cdName);
        System.out.println("cdDescription is " +cdDescription);
        System.out.println("cdID is " +cdID);
        Object[] result=CanonE001CodeTableDAO.createUpdateCodeTable(
				CanonS21SessionValidate.getUserName(), cdName,
				cdDescription, cdID);
        checkErrors(result, 1,2);
		isChange=true;
		// save values logic inside isChange - save code table values
	}
	
	
	if(isChange){
		Object[] result=CanonE001CodeTableDAO.getCodeTabAndCols(CanonS21SessionValidate.getUserName(), cdID);
        checkErrors(result, 4,5);
        codeTableInfo=(CanonE001CodeTableDAO.CodeTableInfo)CanonE001CommonUtil.first((List)CanonE001CommonUtil.first(result));
        if(codeTableInfo==null ){
            throw new E001Exception("Database exception.", null);
        }
        columnList=(List<CanonE001CodeTableDAO.CodeTableColumn>)CanonE001CommonUtil.second(result);
        if(codeTableInfo==null ){
            throw new E001Exception("Database exception.", null);
        }
        hasData= ((BigDecimal)CanonE001CommonUtil.nth(result,4)).intValue()>0;
        if("save_cdval".equals(strAction)){
        	// save code table values
        	CanonE001CommonUtil.saveCdValues(CanonS21SessionValidate.getUserName(), cdID, columnList, request);
        }
        
		if(tabIndex==1){		
			result=CanonE001CodeTableDAO.getCodeTabVals(CanonS21SessionValidate.getUserName(), cdID, p_order_by, p_sort_order, new BigDecimal(page_no), new BigDecimal(rowsPerPage), columnList);
	        checkErrors(result, 2,3);
	    	valuesList=(List<CanonE001CodeTableDAO.CanonS21CdVal>)CanonE001CommonUtil.first(result);
	    	BigDecimal valCount=(BigDecimal)CanonE001CommonUtil.second(result);
	    	total_record=valCount!=null ? valCount.intValue(): 0;
		}
        
	}
	
%>
<div id="page_top">
	<h1>Custom Code Table</h1>
</div>
<div id="errorWidget"
	style="<%=CanonE001CommonUtil.isEmpty(createCodeTableError) ?"display: none":""%>; padding-bottom: 5px; padding-left: 20px; padding-top: 5px; color: red;">
	<p id="eMsg"><%=CanonE001CommonUtil.checkNull(createCodeTableError)%></p>
</div>
<div class="table-inner">
	<form id="machSearchFrm" name="machSearchFrm"
		action="canonE001CodeTableDetail.jsp" method="post">
		<input type="hidden" name="cd_id" id="cd_id" value="<%=CanonE001CommonUtil.checkNull(cdID)%>">		
		<input type='hidden' name='sortOrder' id='sortOrder'
			value="<%=sortOrder%>" /> <input type='hidden' name='sortBy'
			id='sortBy' value="<%=sortBy%>" /> 
			<input type="hidden" name="action" id="action" value="">
			<input type="hidden" name="delete_column_id" id="delete_column_id" value="">
			<input type="hidden" name="tab_index" id="tab_index" value="<%=tabIndex%>">
		    <input type="hidden" name="page_no" id="page_no" value="<%=page_no%>"/>
		    <input type="hidden" name="delete_cdval_id" id="delete_cdval_id" value=""/>
		    <input type="hidden" name="isDuplicateRow" id="isDuplicateRow" value="N"/>
			
		<div class="service">
			<table id="createProfileTable" >
				<tr>
					<th colspan="4">Custom Code Table </th>
				</tr>
				<tr>
					<td>Custom Code Table Name</td>
					<td><input style="width: 300px" type="text" id="cdName"
						name="cdName"
						<% if(isChange) { %>
							value="<%=CanonE001CommonUtil.checkNull(codeTableInfo.getCdName())%>"
							readonly="readonly" class="cd_name rdl"
						<% }else { %>
							class="cd_name required" maxlength="28"
						<% }  %>
						></td>
					<% if(isChange) { %>
					<td>Custom Code Table View</td>
					<td><input readonly="readonly" class="rdl" style="width: 300px" type="text"
						value="<%=CanonE001CommonUtil.checkNull(codeTableInfo.getCdViewName())%>">
						</td>
					<% }  %>
				</tr>
				<tr>
					<td>Custom Code Table Description</td>
					<td><input class="required" style="width: 300px" type="text"
						id="cdDescription" name="cdDescription"
						value="<%=(isChange? CanonE001CommonUtil.checkNull(codeTableInfo.getCdDescription()) : "")%>">
						</td>
				</tr>
				<tr>
					<td style="text-align: right;">
					<% if(isCreateNew)  { %>
					<a href="#" id="createButton"
						class="btn">Create</a>
					<% } %>	
						</td>
				</tr>
			</table>
		<% if(isChange){  %>	
		<div style="clear: left;"></div>
		
		<div id="tabs-container">
		  <ul class="tabs-menu">
		    <% if(tabIndex==0) {%> 
		    	<li class="current"><span>Columns</span></li>
		    <%}else{ %>
		    	<li><a href="#tab-1" tabindex="-1">Columns</a></li>
		    <%}%>
		    <% if(columnList!=null && columnList.size()>0) { %>
		    
		    <% if(tabIndex==1) {%> 
		    	<li class="current"><span>Data</span></li>
		    <%}else{ %>
		    	<li><a href="#tab-2" tabindex="-1">Data</a></li>
		    <%}%>
		    
		    <% } %>
		  </ul>
		  
		<div class="tab">
		<div id="tab-1" class="tab-content">
		<% if (tabIndex==0 ) { %>	
		<table class="supplies-table" cellspacing="1" style="width:95%;float:left;background:none;">
			<tr>
				<th style="width: 15%;">Database Column Name</th>
				<th style="width: 30%;">Display Name</th>
				<th style="width: 10%;">Column Type</th>
				<th style="width: 10%;">Result Column</th>
				<th style="width: 5%;">Value Mandatory</th>
				<th style="width: 10%;">Change Order</th>
				<th style="width: 5%;">Remove</th>
				<th style="width: 5%;">Add New</th>
			</tr>
			<%
				if( columnList!=null && columnList.size()>0){
					for(int i=0;i<columnList.size();i++){
						CanonE001CodeTableDAO.CodeTableColumn column = columnList.get(i);
						int optVal[]=optValRange(column.getColFormat());
			%>
			<tr class="odd-row" data-col_id="<%=CanonE001CommonUtil.checkNull(column.getColId())%>">
				<input type="hidden" name="col_id" value="<%=CanonE001CommonUtil.checkNull(column.getColId())%>">
				<input type="hidden" name="col_result_col" class="col_result_col" value="<%=CanonE001CommonUtil.checkNull(column.getResultCol())%>">
				<input type="hidden" name="db_col_name" class="db_col_name" value="<%=CanonE001CommonUtil.checkNull(column.getDbColName())%>">
				<td nowrap><%=CanonE001CommonUtil.checkNull(column.getDbColName()) %></td>
				<td> 
					<input type="text" name="col_display_name"  class="col_display_name required" value="<%=CanonE001CommonUtil.checkNull(column.getColPrompt())%>">
				</td>
				<td>
					<select name="col_format" default-val="<%=column.getColFormat()%>" id="col_format<%=i%>"  class="col_format required">
					 	<option value="CHAR" <%=column.getColFormat().equals("CHAR") ? "selected=selected":"" %>>CHAR</option>
						<option value="NUMBER" <%=column.getColFormat().equals("NUMBER") ? "selected=selected":"" %>>NUMBER</option>
						<option value="DATE" <%=column.getColFormat().equals("DATE") ? "selected=selected":"" %>>DATE</option>
					</select> 
				</td>
				<td>
					<select name="result_col"  class="result_col required" data-old-value="<%=CanonE001CommonUtil.checkNull(column.getResultCol())%>">
						<option></option>
						<% for(int v=optVal[0];v<=optVal[1];v++) {
							String val="VAL"+v;%>
					 		<option value="<%=val%>" <%=val.equals(column.getResultCol()) ? "selected":"" %>><%=val%></option>
						<% } %>
					</select> 
				</td>
				<td>
					<select name="is_mandatory"  class="is_mandatory">
					 	<option value="Y" <%=column.getIsMandatory().equals("Y") ? "selected":"" %>>Yes</option>
						<option value="N" <%=column.getIsMandatory().equals("N") ? "selected":"" %>>No</option>
					</select>
				</td>
				<td class="center">
					<a href="#" class="btn moveUpButton"  title="Move up">&#8679;</a><a href="#" class="btn moveDownButton"  title="Move down">&#8681;</a>
				</td>
				<td class="center">
					<a href="#" class="btn deleteColumnButton"  title="Delete">x</a>
				</td>
				<td class="center">
					<a href="#" class="btn addNewColumnButton"  title="Add New">+</a>
				</td>
			</tr>
			<%
					}
				}else{
					for(int i=0;i<4;i++){
			%>

				<tr class="odd-row" data-col_id="">
				   <input type="hidden" name="col_id" value="">
				   <input type="hidden" name="col_result_col" class="col_result_col" value="">
				   <td>
				      <input type="text" maxlength="30" name="db_col_name" class="db_col_name required" value="">
				   </td>
				   <td>
				      <input type="text" name="col_display_name" class="col_display_name required" value="">
				   </td>
				   <td>
				      <select name="col_format" id="col_format<%=i%>"  class="col_format required">
				         <option value="CHAR" selected="">CHAR</option>
				         <option value="NUMBER">NUMBER</option>
				         <option value="DATE">DATE</option>
				      </select>
				   </td>
				   <td>
				   		<select name="result_col" class="result_col required" data-old-value="">
					      <option></option>
					      <option value="VAL1">VAL1</option>
					      <option value="VAL2">VAL2</option>
					      <option value="VAL3">VAL3</option>
					      <option value="VAL4">VAL4</option>
					      <option value="VAL5">VAL5</option>
					      <option value="VAL6">VAL6</option>
					      <option value="VAL7">VAL7</option>
					      <option value="VAL8">VAL8</option>
					      <option value="VAL9">VAL9</option>
					      <option value="VAL10">VAL10</option>
					      <option value="VAL11">VAL11</option>
					      <option value="VAL12">VAL12</option>
					      <option value="VAL13">VAL13</option>
					      <option value="VAL14">VAL14</option>
					      <option value="VAL15">VAL15</option>
					      <option value="VAL16">VAL16</option>
					      <option value="VAL17">VAL17</option>
					      <option value="VAL18">VAL18</option>
					      <option value="VAL19">VAL19</option>
					      <option value="VAL20">VAL20</option>
					      <option value="VAL21">VAL21</option>
					      <option value="VAL22">VAL22</option>
					      <option value="VAL23">VAL23</option>
					      <option value="VAL24">VAL24</option>
					      <option value="VAL25">VAL25</option>
					      <option value="VAL26">VAL26</option>
					      <option value="VAL27">VAL27</option>
					      <option value="VAL28">VAL28</option>
					      <option value="VAL29">VAL29</option>
					      <option value="VAL30">VAL30</option>
					      <option value="VAL31">VAL31</option>
					      <option value="VAL32">VAL32</option>
					      <option value="VAL33">VAL33</option>
					      <option value="VAL34">VAL34</option>
					      <option value="VAL35">VAL35</option>
					      <option value="VAL36">VAL36</option>
					      <option value="VAL37">VAL37</option>
					      <option value="VAL38">VAL38</option>
					      <option value="VAL39">VAL39</option>
					      <option value="VAL40">VAL40</option>
					      <option value="VAL41">VAL41</option>
					      <option value="VAL42">VAL42</option>
					      <option value="VAL43">VAL43</option>
					      <option value="VAL44">VAL44</option>
					      <option value="VAL45">VAL45</option>
					      <option value="VAL46">VAL46</option>
					      <option value="VAL47">VAL47</option>
					      <option value="VAL48">VAL48</option>
					      <option value="VAL49">VAL49</option>
					      <option value="VAL50">VAL50</option>
					   </select>
				   </td>
				   <td>
				      <select name="is_mandatory" class="is_mandatory">
				         <option value="Y">Yes</option>
				         <option value="N" selected="">No</option>
				      </select>
				   </td>
				   <td class="center">
				      <a href="#" class="btn moveUpButton" title="Move up">&#8679;</a>
				      <a href="#" class="btn moveDownButton" title="Move down">&#8681;</a>
				   </td>
				   <td class="center">
				      <a href="#" class="btn deleteColumnButton" title="Delete">x</a>
				   </td>
				   <td class="center">
				      <a href="#" class="btn addNewColumnButton" title="Add New">+</a>
				   </td>
				</tr>

				<%	
					}
				}
			%>
		</table>
		<table style="float:left; width:95%; border:none;">
			<tr>
				<td style="text-align: right;">
					<a href="#" id="saveButton" class="btn">Save</a>
				</td>
			</tr>
		</table>
		<% } %>
		</div> <!--  tab-1 -->	
		<div id="tab-2" class="tab-content">
		
		<% if (tabIndex==1 ) {
		totalLks = (total_record%rowsPerPage>0)?((total_record/rowsPerPage)+1):total_record/rowsPerPage;		
		pageLinkMsg="";
 	   
	    if(totalLks>1){
			int start = ((page_no-1)*rowsPerPage) + 1 ;
			int end= page_no*rowsPerPage;
	    	
	    	  pageLinkMsg= start+" to "+(end-(rowsPerPage-valuesList.size())) +" of "+ total_record +" records found.";
	    	  
	    }
	    %>
				<div id="pgLinks" style="float:left"><jsp:include
						page="canonE001Pagination.jsp">
						<jsp:param value="<%=totalLks%>" name="totalLinks" />
						<jsp:param value="<%=page_no%>" name="pageNum" />
					</jsp:include>
				</div>	
				<%
					if(pageLinkMsg!="" && pageLinkMsg!=null){
				%>
				<div style="float:right;padding-right: 20px;">
				<span> Showing</b>&nbsp;<%=pageLinkMsg%> </span>
				</div>
				<%
					}
				if(total_record>0){
				%>
					
				<div style="float:right;padding-right: 100px;font-size:smaller;color:#aaa">
					<span>Rows Per Page</span>
		            <select name="rowsPerPage" id="rowsPerPage" style="width:50px" title="Rows Per Page">
		                <option value="5"  <%=rowsPerPage == 5 ? "SELECTED" : ""%> >5</option>
		                <option value="10" <%=rowsPerPage == 10 ? "SELECTED" : ""%> >10</option>
		                <option value="15" <%=rowsPerPage == 15 ? "SELECTED" : ""%> >15</option>
		                <option value="20" <%=rowsPerPage == 20 ? "SELECTED" : ""%> >20</option>
		                <option value="25" <%=rowsPerPage == 25 ? "SELECTED" : ""%> >25</option>
		                <option value="50" <%=rowsPerPage == 50 ? "SELECTED" : ""%> >50</option>
		                <option value="<%=ALL_ROWS%>"  <%=rowsPerPage == ALL_ROWS ? "SELECTED" : ""%> >ALL</option>
		             </select>									
				</div>
				<% } %>
		<table class="supplies-table" cellspacing="1" style="width:95%;float:left;background:none;">
			<tr>
			<%
				if( columnList!=null && columnList.size()>0){
					for(int i=0;i<columnList.size();i++){
						CanonE001CodeTableDAO.CodeTableColumn column = columnList.get(i);
			%>
				<th title="<%=CanonE001CommonUtil.checkNull(column.getDbColName()) %>">  
					<a
					style="color: #FFFFFF; text-decoration: underline; text-align: left;"
					href="#" data-sort_by="<%=CanonE001CommonUtil.checkNull(column.getResultCol()) %>" class="sorting"><%=CanonE001CommonUtil.checkNull(column.getColPrompt()) %> 
						</a>
				</th>
			<%
					}
				}
			%>
				<th style="width:20px">Remove</th>				
			</tr>
			<%
				if( valuesList!=null && valuesList.size()>0){

	            	for(CanonE001CodeTableDAO.CanonS21CdVal s21Val:valuesList){
							BigDecimal valID=s21Val.valID;
				            try {
								%> 
								<tr class="odd-row" data-val_id="<%=CanonE001CommonUtil.checkNull(valID) %>" >
								 <input type="hidden" name="cd_val_id" value="<%=CanonE001CommonUtil.checkNull(valID) %>">
								<%
		    					for(int i=0;i<columnList.size();i++){
		    						CanonE001CodeTableDAO.CodeTableColumn column = columnList.get(i);
		    					    int colIndex=CanonE001CommonUtil.toInt(column.getResultCol().substring(3));
		    						String val=s21Val.values[i];
		    						int multiLine="CHAR".equals(column.getColFormat()) ? CanonE001CommonUtil.lineCount(val): 0;
		    						String cssclass="col-format-"+column.getColFormat().toLowerCase();
		    						
									if(CanonE001CommonUtil.isDateCol(colIndex)){
										cssclass=cssclass+" datePicker";
									}
									
									if("Y".equals(column.getIsMandatory())){
										cssclass=cssclass+" required";
									}%>
		    						
									<td>
									<%if(multiLine>1){%>
										<textarea maxlength="2000" style="width:90%;border: 1px solid #aac;border-radius: 0px;padding: 3px; margin: 0px 5px 0px 5px;" 
										rows="<%=multiLine%>" data-db_col_name="<%=CanonE001CommonUtil.checkNull(column.getDbColName()) %>" 
										name="<%=CanonE001CommonUtil.checkNull(column.getResultCol())%>" class="<%=cssclass%>"><%=safeHtmlAttr(val)%></textarea>
									<%}else{%>
										<input maxlength="2000" data-db_col_name="<%=CanonE001CommonUtil.checkNull(column.getDbColName()) %>" 
										type="text" name="<%=CanonE001CommonUtil.checkNull(column.getResultCol())%>" class="<%=cssclass%>"
										value="<%=safeHtmlAttr(val)%>">
									<%}%>
									</td><%
		    					}
								%>
								<td>
									<a href="#" class="btn deleteCdValButton"  title="Delete">X</a>
								</td>
								
								</tr>
								<%
				          } catch (Exception ex) {
				           	ex.printStackTrace();
				          }
	            	}
				}else{
					for(int j=0;j<4;j++){
			%>
					<tr class="odd-row">
	  				    <input type="hidden" name="cd_val_id" value="">
						<% for(int i=0;i<columnList.size();i++){
							CanonE001CodeTableDAO.CodeTableColumn column = columnList.get(i);
							String cssclass="col-format-"+column.getColFormat().toLowerCase();
							
							if("DATE".equals(column.getColFormat())){
								cssclass=cssclass+" datePicker";
							}
							
							if("Y".equals(column.getIsMandatory())){
								cssclass=cssclass+" required";
							}
						%>
					<td><input maxlength="2000" type="text" name="<%=CanonE001CommonUtil.checkNull(column.getResultCol())%>" class="<%=cssclass%>" data-db_col_name="<%=CanonE001CommonUtil.checkNull(column.getDbColName()) %>" value=""></td>
					<%
						}
					%>
					<td>
						<a href="#" class="btn deleteCdValButton"  title="Delete">X</a>
					</td>
					</tr>
			
			<%  	}
				} 
			%>
			
		</table>
		<table style="float:left; width:95%; border:none;">
			<tr>
				<td style="text-align: left;">
					<a href="#" class="downloadButton" title="Download Excel File">Download Excel</a><span style="color:#cc0000">&nbsp;|</span>
					<a href="#" class="uploadButton" title="Upload Excel File">Upload Excel</a>
				</td>
				<td style="text-align: right;">
					<a href="#" id="addNewValButton" class="btn">Add New</a>
					<a href="#" id="saveCdValButton" class="btn">Save</a>
				</td>
			</tr>
		</table>
		<% } %>
		
		</div> <!--  tab-2 -->	
		</div> <!--  tab -->
		</div> <!--  tabs-container -->
		
		
		<% } %>			
			
		</div>
	</form>
</div>
<table>
	<tr>
		<td>&nbsp;&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;&nbsp;</td>
	</tr>
</table>

<script>

	$(document).ready(
			function() {

				function checkCdName(o, n, handler) {
					return checkRegexp(o, /^[a-zA-Z0-9_]+$/,
							"Only alphanumeric characters and underscores are allowed in "
									+ n, handler);
				}
				
				function checkViewName(o, n, handler) {
					return checkRegexp(o, /^[a-zA-Z_]/,
							"Custom Code Table Name must begin with a letter"
									+ n, handler);
				}
		        
				function validateInput(handler) {
					checkRequired($("#cdName"), "Custom Code Table Name", handler);
					handler.valid() && checkMaxLength($("#cdName"),"Custom Code Table Name", 28, handler);
					handler.valid() && checkViewName($("#cdName"),"", handler);
					handler.valid() && checkCdName($("#cdName"),"Custom Code Table Name", handler);
					handler.valid() && checkRequired($("#cdDescription"),"Custom Code Table Description", handler);

					$("#cdName").val($("#cdName").val().toUpperCase());
					
					return handler;
				}
				
				function createCodeTable() {
					clear_status();
					var handler = makeSingleErrorHandler();
					validateInput(handler);
					handler.done();
					if (!handler.valid()) {
						handler.showError();
					}else{
						$("#action").val("create");
						$("#cdName").val($("#cdName").val().toUpperCase());
						$("#machSearchFrm").submit();
					}
				}
				
				
				function dateHasConflict(s1,e1,s2,e2){
					var t_s1=s1==null? 0: s1.getTime();
					var t_e1=e1==null? 9007199254740991: e1.getTime();
					var t_s2=s2==null? 0: s2.getTime();
					var t_e2=e2==null? 9007199254740991: e2.getTime();
					
					return !(t_e2<t_s1 || t_s2>t_e1);
				}
				
				function checkDuplicateColumnName(tr1, handler){
					var dup=false;
					$(tr1).prevAll(".supplies-table .odd-row").each(function(){
		            	var tr2=$(this);
						if(valueEquals(tr1.find(".db_col_name"),tr2.find(".db_col_name"))){
							dup=true;
							handler.handle("Duplicate column name found", tr2);			
							return false;
						}
		            });
					return dup;
				}
				
				function checkDuplicateResultColumn(tr1, handler){
					var dup=false;
					$(tr1).prevAll(".supplies-table .odd-row").each(function(){
		            	var tr2=$(this);
						if(valueEquals(tr1.find(".result_col"),tr2.find(".result_col"))){
							dup=true;
							handler.handle("Duplicate result column found", tr2);			
							return false;
						}
		            });
					return dup;
				}
				
				function checkColumnName(o, n, handler) {
					return checkRegexp(o, /^[a-zA-Z0-9_\$#]+$/,
							"Only alphanumeric characters, underscores, '$', and '#' are allowed in "
									+ n, handler);
				}
				
				function isBlankRow(tr,dbColNameElm){
					return tr.find(".col_format").val()=="CHAR" && 
					tr.find(".is_mandatory").val()=="N"
					&& empty(tr.find(".col_display_name").val())
					&& empty(tr.find(".result_col").val())
					&& empty(dbColNameElm.val());
				}
				
				function validateSaveCodeTableInput(handler) {
					checkRequired($("#cdDescription"),"Custom Code Table Description", handler);
					if(handler.valid()){
			            $(".supplies-table .odd-row").each(function(){
			            	var tr=$(this);
							var dbColNameElm=tr.find(".db_col_name");
							if(!isBlankRow(tr,dbColNameElm)){
								if(dbColNameElm.hasClass("required")){
									checkRequired(dbColNameElm,"Database Column Name", handler);
									handler.valid() && checkColumnName(dbColNameElm,"Database Column Name", handler);
									dbColNameElm.val(dbColNameElm.val().toUpperCase());
									handler.valid() && checkDuplicateColumnName(tr, handler);
								}
								handler.valid() && checkRequired(tr.find(".col_display_name"),"Display Name", handler);
								handler.valid() && checkRequired(tr.find(".result_col"),"Result Column", handler);
								handler.valid() && checkDuplicateResultColumn(tr, handler);
							}
							if(!handler.valid()) return false;
			            });
					}
					return handler;
				}
				
				function saveCodeTable(showUpdateStatus) {
					clear_status();
					var handler = makeSingleErrorHandler();
					validateSaveCodeTableInput(handler);
					handler.done();
					if (!handler.valid()) {
						handler.showError();
					} else {
						var url = "canonE001CodeTableDetailInclude.jsp";
						$("#action").val("save");
						$.ajaxPost(url, "Failed to save code table.", showUpdateStatus?"All changes have been saved!": "");
					}
				}

				function deleteColumn(colID) {
			        askConfirmation("Delete Column","Are you sure to delete the selected column?",function(dialog){
						clear_status();
			            dialog.dialog( "close" );
						var url = "canonE001CodeTableDetailInclude.jsp";
						$("#action").val("delete");
						$("#delete_column_id").val(colID);
						$.ajaxPost(url, "Failed to delete the profile value.");
			        });
				}
				

				function moveColumnUp(row) {
					var tr2=row.prev();
					tr2.before(row);
					saveCodeTable();
				}

				function moveColumnDown(row) {
					var tr2=row.next();
					tr2.after(row);
					saveCodeTable();
				}
				
				function addNewColumn(tr){
					var newRow='<tr class="odd-row" data-col_id="">'+
					'<input type="hidden" name="col_id" value="">'+
					'<input type="hidden" name="col_result_col" class="col_result_col" value="">'+
					'<td>'+
					'		<input type="text" maxlength="30" name="db_col_name" class="db_col_name required" value="">'+
					'</td>'+
					'<td> '+
					'		<input type="text" name="col_display_name" class="col_display_name required" value="">'+
					'</td>'+
					'<td>'+
					'	<select name="col_format"  class="col_format required">'+
					'	 	<option value="CHAR" selected>CHAR</option>'+
					'		<option value="NUMBER">NUMBER</option>'+
					'		<option value="DATE">DATE</option>'+
					'	</select> '+
					'</td>'+
					'<td>'+
					   '<select name="result_col" class="result_col required" data-old-value="">'+
					      '<option></option>'+
					      '<option value="VAL1">VAL1</option>'+
					      '<option value="VAL2">VAL2</option>'+
					      '<option value="VAL3">VAL3</option>'+
					      '<option value="VAL4">VAL4</option>'+
					      '<option value="VAL5">VAL5</option>'+
					      '<option value="VAL6">VAL6</option>'+
					      '<option value="VAL7">VAL7</option>'+
					      '<option value="VAL8">VAL8</option>'+
					      '<option value="VAL9">VAL9</option>'+
					      '<option value="VAL10">VAL10</option>'+
					      '<option value="VAL11">VAL11</option>'+
					      '<option value="VAL12">VAL12</option>'+
					      '<option value="VAL13">VAL13</option>'+
					      '<option value="VAL14">VAL14</option>'+
					      '<option value="VAL15">VAL15</option>'+
					      '<option value="VAL16">VAL16</option>'+
					      '<option value="VAL17">VAL17</option>'+
					      '<option value="VAL18">VAL18</option>'+
					      '<option value="VAL19">VAL19</option>'+
					      '<option value="VAL20">VAL20</option>'+
					      '<option value="VAL21">VAL21</option>'+
					      '<option value="VAL22">VAL22</option>'+
					      '<option value="VAL23">VAL23</option>'+
					      '<option value="VAL24">VAL24</option>'+
					      '<option value="VAL25">VAL25</option>'+
					      '<option value="VAL26">VAL26</option>'+
					      '<option value="VAL27">VAL27</option>'+
					      '<option value="VAL28">VAL28</option>'+
					      '<option value="VAL29">VAL29</option>'+
					      '<option value="VAL30">VAL30</option>'+
					      '<option value="VAL31">VAL31</option>'+
					      '<option value="VAL32">VAL32</option>'+
					      '<option value="VAL33">VAL33</option>'+
					      '<option value="VAL34">VAL34</option>'+
					      '<option value="VAL35">VAL35</option>'+
					      '<option value="VAL36">VAL36</option>'+
					      '<option value="VAL37">VAL37</option>'+
					      '<option value="VAL38">VAL38</option>'+
					      '<option value="VAL39">VAL39</option>'+
					      '<option value="VAL40">VAL40</option>'+
					      '<option value="VAL41">VAL41</option>'+
					      '<option value="VAL42">VAL42</option>'+
					      '<option value="VAL43">VAL43</option>'+
					      '<option value="VAL44">VAL44</option>'+
					      '<option value="VAL45">VAL45</option>'+
					      '<option value="VAL46">VAL46</option>'+
					      '<option value="VAL47">VAL47</option>'+
					      '<option value="VAL48">VAL48</option>'+
					      '<option value="VAL49">VAL49</option>'+
					      '<option value="VAL50">VAL50</option>'+
					   '</select>'+
					'</td>'+
					'<td>'+
					'	<select name="is_mandatory"  class="is_mandatory">'+
					'	 	<option value="Y" >Yes</option>'+
					'		<option value="N" selected>No</option>'+
					'	</select>'+
					'</td>'+
					'<td class="center">'+
					'	<a href="#" class="btn moveUpButton" title="Move up">&#8679;</a><a href="#" class="btn moveDownButton" title="Move down">&#8681;</a>'+
					'</td>'+
					'<td class="center">'+
					'	<a href="#" class="btn deleteColumnButton" title="Delete">x</a>'+
					'</td>'+
					'<td class="center">'+
					'	<a href="#" class="btn addNewColumnButton" title="Add New">+</a>'+
					'</td>'+
					'</tr>';
					
					tr.after(newRow);
					tr.next().find("input:visible[type='text']:first").focus();
				}
				
				function reloadTab(tabIndex) {
					clear_status();
					var url = "canonE001CodeTableDetailInclude.jsp";
					$("#action").val("reload");
					$("#tab_index").val(tabIndex);
					return $.ajaxPost(url, "Failed to load code table.");
				}
				
				function deleteCdVal(valID) {
			        askConfirmation("Delete Code Table Value","Are you sure to delete the selected code table value?",function(dialog){
						clear_status();
			            dialog.dialog( "close" );
						var url = "canonE001CodeTableDetailInclude.jsp";
						$("#action").val("delete_cdval");
						$("#delete_cdval_id").val(valID);
						$.ajaxPost(url, "Failed to delete the code table value.");
			        });
				}
				
				<% if(isChange) { %>
				function addNewValue(){
					var newRow='<tr class="odd-row" data-val_id="" >'+
						'<input type="hidden" name="cd_val_id" value="">'+<%
						for(int i=0;i<columnList.size();i++){
							CanonE001CodeTableDAO.CodeTableColumn column = columnList.get(i);
							String cssclass="col-format-"+column.getColFormat().toLowerCase();
							
							if("DATE".equals(column.getColFormat())){
								cssclass=cssclass+" datePicker";
							}
							
							if("Y".equals(column.getIsMandatory())){
								cssclass=cssclass+" required";
							}
					%>'<td><input maxlength="2000" type="text" name="<%=CanonE001CommonUtil.checkNull(column.getResultCol())%>" class="<%=cssclass%>" data-db_col_name="<%=CanonE001CommonUtil.checkNull(column.getDbColName()) %>" value=""></td>'+
					<%
						}
					%>
					'<td>'+
					'	<a href="#" class="btn deleteCdValButton"  title="Delete">X</a>'+
					'</td>'+
					'</tr>';
					
					$(".supplies-table").append(newRow);
					$(".supplies-table tr:last").find("input:visible[type='text']:first").focus();

				}

				<% } %>
				
				function isBlankValRow(tr){
					var allempty=true;
					tr.find("input[type='text']").each(function(){
						if(!empty($(this).val())){
							allempty=false;
							return false;
						}
					});
					return allempty;					
				}
				
				function validateSaveCdValInput(handler) {
					checkRequired($("#cdDescription"),"Custom Code Table Description", handler);
		            $(".supplies-table .odd-row").each(function(){
		            	var tr=$(this);
		            	if(isBlankValRow(tr)){
		            		return true; // skip blank row
		            	}
		            	
						tr.find(".required").each(function(){
							var colname=$(this).data("db_col_name");
							handler.valid() && checkRequired($(this),colname, handler);
							if(!handler.valid()) return false;
						});
						
						if(!handler.valid()) return false;
						
						tr.find(".col-format-number").each(function(){
							var colname=$(this).data("db_col_name");
							handler.valid() && ( empty($(this).val()) || checkIsNumeric($(this),colname, handler) );
							if(!handler.valid()) return false;
						});
						
 						if(!handler.valid()) return false;
		            });
					return handler;
				}
				
				function checkDuplicateRows() {
					var qryStr=$("#machSearchFrm").serialize();
				     $.ajax({
				 		url : 'canonE001CheckCodeTableDuplicateValues.jsp',
				 		cache : false,
				 		type : "POST",
				 		data : qryStr,
				 		async: false,
				 		success : function(data) {
				 			var resultData = $.trim(data);
				 			var resultArr = resultData.split('~');
				 			var resultFlag = null;
				 			var resultMsg = null;
				 			if(resultArr!=null) {
				 				 resultFlag = resultArr[0];
				 				 resultMsg  = resultArr[1];
				 				 var html = '<div id="dialog-confirm" title="Validation Failed">' +
				                    '<p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>'
				                     +resultMsg+'</p></div>';
				 			    if (resultFlag == 'W'){
				 			    	$("#isDuplicateRow").val('Y');
				 			    	$(html).dialog({
	                                    height : 250,
	                                    overflow :"auto",
				      				    title : "Duplicate Validation Failed ",
				      				    modal : true,
				                                     zIndex : 1005,
				                                     width : 400,
				                                     resizable : false,
				      				    buttons : {
						      					"Ok" : function() {
						      					  $(this).dialog("close");
						      					  $(this).dialog("destroy");}
      					                          } 
	      				            });
				 			    	return false;
				 			   }
				 		    }   
				 		  }  
				 	    });
				}
				
				function saveCdVal(showUpdateStatus) {
					clear_status();
					var handler = makeSingleErrorHandler();
					validateSaveCdValInput(handler);
					handler.done();
					checkDuplicateRows();
					var isError=$("#isDuplicateRow").val();		
					if(isError=='Y')
						{
						$("#isDuplicateRow").val('N');
						return;
						}			
					else if (!handler.valid()) {
						handler.showError();
					} else {
						var url = "canonE001CodeTableDetailInclude.jsp";
						$("#action").val("save_cdval");
						$.ajaxPost(url, "Failed to save code table.", showUpdateStatus?"All changes have been saved!": "");
					}
				}

				// begin event handler
				
				$("#createButton").click(function(event) {
					createCodeTable();
					event.preventDefault();
				});
				
				$("#saveButton").click(function(event) {
					saveCodeTable(true);
					event.preventDefault();
				});
				
				$(".supplies-table").on("click",".addNewColumnButton", function(event) {
					tr=$(this).parent().parent();
					addNewColumn(tr);
					event.preventDefault();
				});
				
				$(".moveUpButton").click(function(event) {
					tr=$(this).parent().parent();
					var rowIndex=tr[0].rowIndex;
					if(rowIndex>1){
						moveColumnUp(tr);
					}
					event.preventDefault();
				});

				$(".moveDownButton").click(function(event){
					tr=$(this).parent().parent();
					var rowIndex=tr[0].rowIndex;
					var totalRow=$(".supplies-table .odd-row").length;
					if(rowIndex<totalRow){
						moveColumnDown(tr);
					}
					event.preventDefault();
				});
				
				$(".supplies-table").on("click",".deleteColumnButton",function(event) {
					event.preventDefault();
			        var tr = $(this).closest("tr");
					var colID=tr.data("col_id");
					if(empty(colID)){
						tr.remove();
					}else{
						<% if(hasData) {%>
							popupError("Removing a column is not allowed if data exists.","Delete Column");
						<%}else{%>
						deleteColumn(colID);
						<% } %>
					}
				});
				
				var optvals={"CHAR":[1,50],"NUMBER":[51,75],"DATE":[76,100]};
				$(".supplies-table").on("change",".col_format",function(event){
			        var tr = $(this).closest("tr");
					var colID=tr.data("col_id");
					if(<%=hasData? "true":"false"%> && !empty(colID) ) {
						 var defVal=$(this).attr("default-val");
						 var selVal=$(this).val();
						if( defVal==selVal){ 
							$(this).val(defVal).change();						
						} else{
							$(this).val(defVal);	
						  popupError("Changing the data type is not allowed if data exists.","Data type change Error");
						}
				   	}else{
						var colformat=$(this).val();
						var range=optvals[colformat];
						var resultColElm=$(this).closest("tr").find(".result_col");
						resultColElm.html("<option></option>");
						var v=0;
						for(v=range[0];v<=range[1];v++){
							var newVal="VAL"+v;
							var selected=oldVal==newVal;
							resultColElm.append($("<option></option>").attr("value",newVal).text(newVal));
						}
						var oldVal=resultColElm.data("old-value");
						resultColElm.val(oldVal);
						event.preventDefault();
					}
				});
				
					
				    $(".tabs-menu a").click(function(event) {
				        event.preventDefault();
				        var menu=$(this);
				        var switchTab=function(){
							$("#page_no").val("1");
					        var tab = menu.attr("href");
					        tab=tab.substr(tab.length - 6); // fix IE7 issue
					        var tabIndex=$(tab)[0].id=="tab-1" ? 0 : 1;
					        reloadTab(tabIndex).done(function() {
					        	menu.parent().addClass("current");
					        	menu.parent().siblings().removeClass("current");
					        });
				        }
				        
				        if(FormChanges($("#machSearchFrm")[0]).length>0){
					       askConfirmation("Unsaved Data","You have unsaved data, Are you sure to leave current tab?",function(dialog){
					            dialog.dialog("close");
					        	switchTab();
					        },function(dialog){
					        	$(':focus').blur();
					        });
				        }else{
				        	switchTab();
				        }
				        
				    });
				 	
					$(".supplies-table").on("click",".deleteCdValButton",function(event) {
						event.preventDefault();
				        var tr = $(this).closest("tr");
						var valID=tr.data("val_id");
						if(empty(valID)){
							tr.remove();
						}else{
							deleteCdVal(valID);
						}
					});
					
					$(".supplies-table").on("focus",".col_display_name",function(event) {
						event.preventDefault();
				        var tr = $(this).closest("tr");
						if(empty($(this).val())){
							var dispname=tr.find(".db_col_name").val().toProperCase();
							$(this).val(dispname);
						}
					});
					
					$(".pageNum").click(function(event) {
						event.preventDefault();
						var pageNum= $(this).data("page");
						$("#page_no").val(pageNum);
						reloadTab(1);
					});
					
					$(".sorting").click(function(event) {
						event.preventDefault();
						var sort_by= $(this).data("sort_by");
						$("#sortBy").val(sort_by);
						$("#sortOrder").val($("#sortOrder").val()=="asc" ? "desc" : "asc");
						reloadTab(1);
					});
					
					$("#rowsPerPage").on("change",function(){
						$("#page_no").val("1");
						reloadTab(1);
					});
					
					
					$("#addNewValButton").click(function(event) {
						event.preventDefault();
						addNewValue();
						setupDatePicker();
					});

					
					$("#saveCdValButton").click(function(event) {
						event.preventDefault();
						saveCdVal(true);
					});
					
					$(".downloadButton").click(function(event) {
						event.preventDefault();
					    var strPath = "canonE001CodeTableUtil.jsp?action=download&cd_id="+<%=cdID%>;
				        var iframe;
				        iframe = document.getElementById("hiddenDownloader");
				        if (iframe == null) {
				            iframe = document.createElement('iframe');
				            iframe.id = "hiddenDownloader";
				            iframe.style.visibility = 'hidden';
				            document.body.appendChild(iframe);
				        }
				        iframe.src = strPath;
					});
					
					$('.uploadButton').canonFileImport({
						action: "canonE001CodeTableUtil.jsp?action=upload&cd_id="+<%=cdID%>, 
						post : function() {
							clear_status();
						},
						complete : function(response) {
							if (response.error_flag) {
								popupError(response.error,"Import");
							} else {
								if (response.message) {
									if(response.line_messages){
										$.canon_line_messages=response.line_messages;
										var regex = /(\d*? failed)/g;
										var subst = '<a href="#" onclick="$.canonPopupLineMessages();return false;">$1</a>';
										var result = response.message.replace(regex, subst);
										update_status(result);
									}else{
										update_status(response.message);
									}
								}else{
									update_status("File Imported Successfully.");
								}
							}
						},
						error : function(err) {
							if (err && err != "") {
								popupError(err);
							}
						}
					
					});
					
					
					$('#main_content').on("keypress","input.col-format-char", function (e) {            
					    if (e.keyCode == 13) {
					        e.preventDefault(); 
						    var newChild=$('<textarea maxlength="2000" rows="2" style="width:90%;border: 1px solid #aac;border-radius: 0px;padding: 3px; margin: 0px 5px 0px 5px; resize:both;">')[0];
							var attrs = {};     
						    $.each(this.attributes, function() {
						       $(newChild).attr(this.name,this.value);
						    });
						    var parent=this.parentNode;
						    parent.replaceChild(newChild, this);
					    	$(newChild).val($(this).val()).focus();
							var elemLen = newChild.value.length;
						    newChild.selectionStart = elemLen;
						    newChild.selectionEnd = elemLen;
					    	newChild.focus();
					    }
					});
					
					// end event handler
					
					$(".rdl").each(function (){
						  var ele=$(this);
						  var tp = $(ele).attr("type");
						  if(tp=="text")
							  $(ele).addClass("rdl").attr("readonly","readonly"); 
						  else   
							  $(ele).addClass("rdl").attr("disabled","true");	  
					 });
					
					$("#page"+<%=page_no%>).css({"color":"white","background-color":"#A10304"});
					
					
				    $("#tab-<%=tabIndex+1%>").show();
				    
					 <% if(isCreated) {%>
					 	update_status("New code table has been created.");				 	
					 <% } %>
					 
					setupDatePicker();
					

				    
			});
</script>