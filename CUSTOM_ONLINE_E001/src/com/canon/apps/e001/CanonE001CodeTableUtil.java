package com.canon.apps.e001;
import static com.canon.apps.e001.CanonE001CommonUtil.checkErrors;
import static com.canon.apps.e001.CanonE001CommonUtil.checkNull;
import static com.canon.apps.e001.CanonE001CommonUtil.isEmpty;
import static com.canon.apps.e001.CanonE001CommonUtil.setCdValValue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.text.NumberFormat;
import java.util.HashMap;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.Writer;

import com.ibm.json.java.JSONObject;
import com.ibm.json.java.JSONArray;

import canon.apps.common.CanonAsposeUtil;
import canon.apps.common.CanonFileImportUtil.FileUploadStream;
import canon.apps.common.CanonS21SessionValidate;

import com.canon.apps.e001.CanonE001CommonUtil.E001Exception;
import com.canon.apps.e001.CanonE001CodeTableDAO.CodeTableColumn;

import canon.excel.aspose.WorkbookImpl;
import canon.excel.cells.Workbook;
import canon.excel.cells.Worksheet;

public class CanonE001CodeTableUtil {

	final static Charset CHARSET_UTF8=Charset.forName("UTF-8");
	final static Pattern PATTERN_COL_NAME = Pattern.compile("^[a-zA-Z0-9_\\$#]+$");
	final static Pattern PATTERN_COL_TYPE = Pattern.compile("^CHAR|NUMBER|DATE$");
	final static Pattern PATTERN_COL_RESULT_COL= Pattern.compile("^VAL(\\d+)$");
	final static Pattern PATTERN_COL_MANDATORY= Pattern.compile("^Y|N$");
	final static Pattern PATTERN_WHITESPACES = Pattern.compile("\\s+");
	final static Pattern NON_INT_PATTERN = Pattern.compile("[^0-9]");
	
	final static String LBL_CD_NAME="Custom Code Table Name";
	final static String LBL_CD_VIEW="Custom Code Table View";
	final static String LBL_CD_DESC="Custom Code Table Description";
	final static String LBL_NUM_COL="Number Of Columns";
	final static String LBL_COL_NAME="Database Column Name";
	final static String LBL_COL_DISP="Display Name";
	final static String LBL_COL_TYPE="Column Type";
	final static String LBL_RESULT_COL="Result Column";
	final static String LBL_VAL_MANDATORY="Value Mandatory";
	final static String COMMENT_BASIC="Basic Information";
	final static String COMMENT_COLUMN="Column Information";
	final static String COMMENT_DATA="Data Information";
	
	final static String[] SUM_HEADERS=new String[]{LBL_CD_NAME,LBL_CD_VIEW,LBL_CD_DESC,LBL_NUM_COL};
	final static String[] COL_HEADERS=new String[]{LBL_COL_NAME,LBL_COL_DISP,LBL_COL_TYPE,LBL_RESULT_COL,LBL_VAL_MANDATORY};
	
	public static CdOutputStream exportCSV(final BigDecimal cdID) throws E001Exception{
		String userName=CanonS21SessionValidate.getUserName();
		Object[] result=CanonE001CodeTableDAO.getCodeTabAndCols(userName, cdID);
        checkErrors(result, 4,5);
        final CanonE001CodeTableDAO.CodeTableInfo codeTableInfo=(CanonE001CodeTableDAO.CodeTableInfo)CanonE001CommonUtil.first((List)CanonE001CommonUtil.first(result));
        if(codeTableInfo==null ){
            throw new E001Exception("Database exception.", null);
        }
		final String cdName=codeTableInfo.getCdName();
		final List<CodeTableColumn> columnList=(List<CodeTableColumn>)CanonE001CommonUtil.second(result);
        if(codeTableInfo==null ){
            throw new E001Exception("Database exception.", null);
        }
		result=CanonE001CodeTableDAO.getCodeTabVals(userName, cdID, null, null, new BigDecimal(1), new BigDecimal(10000), columnList);
        checkErrors(result, 2,3);
        final List<CanonE001CodeTableDAO.CanonS21CdVal> valuesList=(List<CanonE001CodeTableDAO.CanonS21CdVal>)CanonE001CommonUtil.first(result);
        
		final CdWriter csv=new ExcelCdWriter(cdName); // replace with CsvCdWriter for using csv files.
		return new CdOutputStream(){
			public void save(OutputStream os) throws Exception{
				csv.setWriter(os,',',CHARSET_UTF8);
				int j;
				if(csv.writeSummary()){
					csv.writeComment(COMMENT_BASIC);
					csv.writeRecord(SUM_HEADERS);
					csv.write(codeTableInfo.getCdName());
					csv.write(codeTableInfo.getCdViewName());
					csv.write(codeTableInfo.getCdDescription());
					csv.write(""+columnList.size());
					csv.endRecord();
					j=0;
					csv.endRecord();
				}
				if(csv.writeColumns()){
					csv.writeComment(COMMENT_COLUMN);
					csv.writeRecord(COL_HEADERS);
					for(CodeTableColumn c:columnList){
						csv.writeRecord(new String[]{
								checkNull(c.getDbColName()),
								checkNull(c.getColPrompt()),
								checkNull(c.getColFormat()),
								checkNull(c.getResultCol()),
								checkNull(c.getIsMandatory())});
					}
					csv.endRecord();
				}
				if(csv.writeDataHeader()){
					csv.writeComment(COMMENT_DATA);
					for(CodeTableColumn c:columnList){
						csv.write(c.getColPrompt());
					}
					csv.endRecord();
				}
				if(csv.writeData()){
					j=0;
					for(CanonE001CodeTableDAO.CanonS21CdVal s21Val:valuesList){
						for(int i=0;i<columnList.size();i++){
							String val=s21Val.values[i];
							csv.write(CanonE001CommonUtil.checkNull(val));
						}
						csv.endRecord();
					}
				}
				csv.flush();
				csv.close();
			}
			public String getFileName() {
				return csv.getFileName();
			}
			public String getContentType(){
				return csv.getContentType();
			}
		};
	}
	
    public static interface CdOutputStream {
        void save(OutputStream os)throws Exception;
        String getFileName();
        String getContentType();
    }
	
	public static void importNew(final PrintWriter printWriter, final FileUploadStream fileUploadStream){
		doImport(null,printWriter,fileUploadStream,true);
	}
	
	public static void importCSV(BigDecimal cdID, final PrintWriter printWriter,
			final FileUploadStream fileUploadStream)
	{
		doImport(cdID,printWriter,fileUploadStream,false);
	}

	public static void doImport(BigDecimal cdID, final PrintWriter printWriter,
			final FileUploadStream fileUploadStream,boolean importNew)
	{
		printWriter.write("__CANON__");
		final String fileName=fileUploadStream.getFileName();
		final String userName=CanonS21SessionValidate.getUserName();
        try {
        	Summary sum=null;
        	CanonE001CodeTableDAO.CodeTableInfo codeTableInfo=null;
        	List<CodeTableColumn> columnList=null;
        	CdReader cr=new ExcelCdReader(fileUploadStream.getInputStream(),','); // replace with CsvCdReader for using csv files.
        	cr.setUseComments(true);
        	if(importNew){
	        	sum=readSummary(cr);
	        	Object[] result=CanonE001CodeTableDAO.codeNameSearch(userName,sum.cdName,"CD_NAME","asc",new BigDecimal(1),new BigDecimal(10));
	            checkErrors(result, 2,3);
	    		
	    	    int totalRecords = 0;
	    		if(CanonE001CommonUtil.second(result)!=null){
	    			totalRecords = ((BigDecimal)CanonE001CommonUtil.second(result)).intValue();
	    		}
	    		if(totalRecords==0){
	    			result=CanonE001CodeTableDAO.createUpdateCodeTable(userName,
	    					sum.cdName,
	    					sum.cdDescription, null);
	    			if(result==null){
		                throw new E001Exception("Unknown database error",null);
	    			}else{
	    		        String status=(String)CanonE001CommonUtil.second(result);
	    		        if("E".equals(status)){
			                throw new E001Exception("database error "+(String)CanonE001CommonUtil.third(result),null);
	    		        }else{
	    		        	BigDecimal newcdID=(BigDecimal)CanonE001CommonUtil.first(result);
	    			        System.out.println("newcdID is " +newcdID);
	    			        cdID=newcdID;
	    			        sum.newCodeTable=true;
	    		        }
	    			}
	    		}else if(totalRecords==1){
		    		codeTableInfo =((List<CanonE001CodeTableDAO.CodeTableInfo>) CanonE001CommonUtil.first(result)).get(0);
		    		cdID=codeTableInfo.getCdId();
	    		}else{
	                throw new E001Exception("Code Table "+sum.cdName+" Not Found",null);
	    		}
	    		result=CanonE001CodeTableDAO.getCodeTabAndCols(userName, cdID);
	    		if(codeTableInfo==null){
		            codeTableInfo=(CanonE001CodeTableDAO.CodeTableInfo)CanonE001CommonUtil.first((List)CanonE001CommonUtil.first(result));
	    		}
	            if(codeTableInfo==null ){
	                throw new E001Exception("Database exception.", null);
	            }
	    		columnList=(List<CodeTableColumn>)CanonE001CommonUtil.second(result);
	            if(columnList==null ){
	                throw new E001Exception("Database exception.", null);
	            }
	            sum.isDescriptionChanged=!sum.cdDescription.equals(codeTableInfo.getCdDescription());
	        	
        	}else{
	    		Object[] result=CanonE001CodeTableDAO.getCodeTabAndCols(userName, cdID);
	            codeTableInfo=(CanonE001CodeTableDAO.CodeTableInfo)CanonE001CommonUtil.first((List)CanonE001CommonUtil.first(result));
	            if(codeTableInfo==null ){
	                throw new E001Exception("Database exception.", null);
	            }
	    		columnList=(List<CodeTableColumn>)CanonE001CommonUtil.second(result);
	            if(columnList==null ){
	                throw new E001Exception("Database exception.", null);
	            }
	            if(cr.readSummary()){
		        	sum=checkSummary(cr,codeTableInfo,columnList.size());
		        	System.out.println(sum);
	            }
        	}
        	
        	if(cr.readSummary() && sum.isDescriptionChanged){
        		updateSummary(userName,cdID, sum);
        	}
        	
        	Columns cols=null;
        	if(cr.readColumns()){
	        	cols=checkColumns(cr,sum.numCols,columnList);
	        	if(cols.isNew){
	        		saveColumns(userName,cdID,cols);
	        		Object[] result=CanonE001CodeTableDAO.getCodeTabAndCols(userName, cdID);
		    		columnList=(List<CodeTableColumn>)CanonE001CommonUtil.second(result);
		            if(columnList==null ){
		                throw new E001Exception("Database exception.", null);
		            }
	        	}
	        	System.out.println(cols);
        	}
        	
			if(cr instanceof ColumnInfo){
				((ColumnInfo)cr).setColumnList(columnList);
			}
        	
        	if(cr.readDataHeader()){
        		checkDataHeader(cr,columnList);
        	}
        	CdValues cdvs=null;
        	if(cr.readData()){
        		cdvs=saveCdValues(userName,cdID,sum,columnList,cr,importNew);
        		System.out.println(cdvs);
        	}
	        success(printWriter,sum,cols,cdvs,fileName,importNew);
        } catch (Exception e) {
        	e.printStackTrace();
            try {
                error(printWriter, e);
            } catch (Exception e1) {
            	e1.printStackTrace();
            }
        }finally{
        	printWriter.write("__CANON__");
        }
	}
	
	private static Columns checkColumns(CdReader cr,int numCols, List<CodeTableColumn> columnList)throws Exception{
		if(!cr.readRecord()){
			throw new InvalidFormatException();
		}
		int c=0;
		for(String colheader:COL_HEADERS){
			checkString(cr,colheader,c++);
		}
		int r=1;
		Iterator<CodeTableColumn>it=columnList.iterator();
		List<CodeTableColumn> newCols=new ArrayList<CodeTableColumn>();
		for(int i=0;i<numCols;i++){
			if(!cr.readRecord()){
				throw new InvalidFormatException();
			}
			CodeTableColumn col=it.hasNext()? it.next():null;
			CodeTableColumn newcol=new CodeTableColumn();
			newcol.setDbColName(col==null?readString(cr,0):checkColumn(cr,0,r,col.getDbColName()));
			newcol.setColPrompt(col==null?readString(cr,1):checkColumn(cr,1,r,col.getColPrompt()));
			newcol.setColFormat(col==null?readString(cr,2):checkColumn(cr,2,r,col.getColFormat()));
			newcol.setResultCol(col==null?readString(cr,3):checkColumn(cr,3,r,col.getResultCol()));
			newcol.setIsMandatory(col==null?readString(cr,4):checkColumn(cr,4,r,col.getIsMandatory()));
			newcol.setColId(col==null?null:col.getColId());
			newCols.add(newcol);
			r++;
		}
		Columns cs=new Columns();
		if(columnList.size()==0){
			cs.isNew=true;
			// validate new columns
			c=1;
			Set<String> colnames=new HashSet<String>();
			Set<String> resultcols=new HashSet<String>();
			for(CodeTableColumn col:newCols){
				if(isEmpty(col.getDbColName())) throw new InvalidFormatException("Column# "+c+": "+COL_HEADERS[1] +" is required.");			
				if(isEmpty(col.getColPrompt())) throw new InvalidFormatException("Column# "+c+": "+COL_HEADERS[2] +" is required.");			
				if(isEmpty(col.getColFormat())) throw new InvalidFormatException("Column# "+c+": "+COL_HEADERS[3] +" is required.");			
				if(isEmpty(col.getResultCol())) throw new InvalidFormatException("Column# "+c+": "+COL_HEADERS[4] +" is required.");			
				if(isEmpty(col.getIsMandatory())) throw new InvalidFormatException("Column# "+c+": "+COL_HEADERS[5] +" is required.");
				
			    if(!PATTERN_COL_NAME.matcher(col.getDbColName()).matches()){
			    	 throw new InvalidFormatException("Column# "+c+": "+"Only alphanumeric characters, underscores, '$', and '#' are allowed in Database Column Name");
			    }
			    if(col.getDbColName().length()>30){
			    	throw new InvalidFormatException("Column# "+c+": "+COL_HEADERS[1] +" length exceeds 30 characters.");
			    }
			    if(!PATTERN_COL_TYPE.matcher(col.getColFormat()).matches()){
			    	 throw new InvalidFormatException("Column# "+c+": "+"Only CHAR, NUMBER, and DATE are allowed in Column Type");
			    }
			    
			    checkResultCol(col.getColFormat(),col.getResultCol(),c);
			    
			    if(!PATTERN_COL_MANDATORY.matcher(col.getIsMandatory()).matches()){
			    	 throw new InvalidFormatException("Column# "+c+": "+"Only Y, N are allowed in Value Mandatory");
			    }
			    
			    if(colnames.contains(col.getDbColName())){
			    	 throw new InvalidFormatException("Column# "+c+": "+"duplicate column name found.");
			    }else{
			    	colnames.add(col.getDbColName());
			    }
			    if(resultcols.contains(col.getResultCol())){
			    	 throw new InvalidFormatException("Column# "+c+": "+"duplicate result column found.");
			    }else{
			    	resultcols.add(col.getResultCol());
			    }
			    c++;
			}
		}
		cs.columns=newCols;
		return cs;
	}

	private static void checkResultCol(String type,String restcol,int c)throws Exception{
		Matcher m=PATTERN_COL_RESULT_COL.matcher(restcol);
		if(!m.matches() || m.groupCount()!=1){
	    	 throw new InvalidFormatException("Column# "+c+": "+"Invalid Result Column");
		}
		String colIdx=m.group(1);
		int validx=Integer.parseInt(colIdx);
		if(!(validx+"").equals(colIdx)){
	    	 throw new InvalidFormatException("Column# "+c+": "+"Invalid Result Column");
		}
		if("CHAR".equals(type) && (validx<1 ||validx>50)){
	    	 throw new InvalidFormatException("Column# "+c+": "+"Invalid Result Column CHAR should be VAL1 to VAL50");
		}else if("NUMBER".equals(type) && (validx<51 ||validx>75)){
	    	 throw new InvalidFormatException("Column# "+c+": "+"Invalid Result Column NUMBER should be VAL51 to VAL75");
		}else if("DATE".equals(type) && (validx<76 ||validx>100)){
	    	 throw new InvalidFormatException("Column# "+c+": "+"Invalid Result Column DATE should be VAL76 to VAL100");
		}
		
	}
	
	private static String checkColumn(CdReader cr,int c,int r,String expect)throws Exception{
		String str=readString(cr,c);
		if(str==null || !str.equals(expect)){
			throw new InvalidFormatException("Column# "+r+": expect "+COL_HEADERS[c]+" " +expect+ " but found "+str);
		}
		return str;
	}
	
	private static void checkDataHeader(CdReader cr, List<CodeTableColumn> columnList)throws Exception{
		
		if(!cr.readRecord()){
			throw new InvalidFormatException();
		}
		int c=0;
		for(CodeTableColumn col:columnList){
			checkString( cr,col.getColPrompt(),c++);
		}
	}
	
	private static void updateSummary(String userName,BigDecimal cdID, Summary sum)throws Exception{
		Object[] result=CanonE001CodeTableDAO.createUpdateCodeTable(
				userName, sum.cdName,
				sum.cdDescription, cdID);
		if(result==null){
	    	throw new Exception("Unknown database error");
		}else{
	        String status=(String)CanonE001CommonUtil.second(result);
	    	System.out.println("status : " + status);
	        if("E".equals(status)){
		    	String errMsg=(String)CanonE001CommonUtil.third(result);
		    	throw new Exception(errMsg);
	        }
		}
	}
	
	private static void saveColumns(String userName,BigDecimal cdID,Columns columns)throws Exception{
 		List<CanonE001CodeTableDAO.CanonE001CodeTabColRec> recs=new ArrayList<CanonE001CodeTableDAO.CanonE001CodeTabColRec>();
 		for(int i=0; i<columns.columns.size();i++){
 			CodeTableColumn col=columns.columns.get(i);
 			CanonE001CodeTableDAO.CanonE001CodeTabColRec rec=new CanonE001CodeTableDAO.CanonE001CodeTabColRec(
 					cdID,
 					col.getColId(),
 					col.getDbColName(),
 					col.getColFormat(),
 					col.getColPrompt(),
 					new BigDecimal(i),
 					col.getIsMandatory(),
 					col.getResultCol());
 			recs.add(rec);
 		}
 		Object []result=CanonE001CodeTableDAO.createUpdateCodeTabCol(userName, cdID, recs);
        checkErrors(result, 0,1);
	}

	private static Summary readSummary(CdReader cr)throws Exception{
		Summary sum=new Summary();
		if(!cr.readRecord()){
			throw new InvalidFormatException();
		}
		int c=0;
		for(String sumheader:SUM_HEADERS){
			checkString(cr,sumheader,c++);
		}
		if(!cr.readRecord()){
			throw new InvalidFormatException();
		}
		c=0;
		sum.cdName=readString(cr,c++);
		if(CanonE001CommonUtil.isEmpty(sum.cdName)){
			throw new InvalidFormatException(LBL_CD_NAME+" is required");
		}
		sum.cdViewName=readString(cr,c++);
		sum.cdDescription=readString(cr,c++);
		if(CanonE001CommonUtil.isEmpty(sum.cdDescription)){
			throw new InvalidFormatException(LBL_CD_DESC+" is required");
		}
		String numCols=readString(cr,c++);
		if(CanonE001CommonUtil.isEmpty(numCols)){
			throw new InvalidFormatException(LBL_NUM_COL+" is required");
		}
		sum.numCols=CanonE001CommonUtil.toInt(numCols);
		return sum;
	}
	
	private static Summary checkSummary(CdReader cr,CanonE001CodeTableDAO.CodeTableInfo info,int colSize)throws Exception{
		Summary sum=new Summary();
		if(!cr.readRecord()){
			throw new InvalidFormatException();
		}
		int c=0;
		for(String sumheader:SUM_HEADERS){
			checkString(cr,sumheader,c++);
		}
		if(!cr.readRecord()){
			throw new InvalidFormatException();
		}
		c=0;
		sum.cdName=readLabelString(cr,LBL_CD_NAME,c++,info.getCdName());
		sum.cdViewName=readLabelString(cr,LBL_CD_VIEW,c++,info.getCdViewName());
		sum.cdDescription=readString(cr,c++);
		if(CanonE001CommonUtil.isEmpty(sum.cdDescription)){
			throw new InvalidFormatException("Custom Code Table Description is required");
		}
		sum.numCols=colSize==0?readNumber(cr,c).intValue() : checkNumber(cr,LBL_NUM_COL,c,new BigDecimal(colSize)).intValue();
		sum.isDescriptionChanged=!sum.cdDescription.equals(info.getCdDescription());
		return sum;
	}

	  public static CdValues saveCdValues(String userName, BigDecimal cdID, Summary sum, List<CodeTableColumn> columnList, CdReader cr, boolean importNew)throws E001Exception{
		  List<CanonE001CodeTableDAO.CanonE001CodeTabColRec> cdColumns=new ArrayList<CanonE001CodeTableDAO.CanonE001CodeTabColRec>();
		  for(int j=0;j<columnList.size();j++){
			  CanonE001CodeTableDAO.CanonE001CodeTabColRec cdcol=new CanonE001CodeTableDAO.CanonE001CodeTabColRec();
			  cdcol.setResultCol(columnList.get(j).getResultCol());
			  cdcol.setColPrompt(columnList.get(j).getColPrompt());
			  cdColumns.add(cdcol);
		  }
		  Object[] result=CanonE001CodeTableDAO.getCodeTabVals(userName, cdID, null, null, new BigDecimal(1), new BigDecimal(10000), columnList);
	      try {
			checkErrors(result, 2,3);
	      } catch (E001Exception e) {
			e.printStackTrace();
			throw e;
	      }
	      final List<CanonE001CodeTableDAO.CanonS21CdVal> valuesList=(List<CanonE001CodeTableDAO.CanonS21CdVal>)CanonE001CommonUtil.first(result);
	      List<BigDecimal> valIDs=new ArrayList<BigDecimal>();
		  CdValues cv=new CdValues();
		  List<String []> loadedRecord=new ArrayList<String []>();
		  while(true){
			  try{
				  if(!cr.readRecord()){
					  break;
				  }
				  String []csvRecord=cr.getValues();
				  cv.numRecordRead++;
				  int lineNumber=cv.numRecordRead;
				  validateRecord(columnList,csvRecord,lineNumber,loadedRecord);
				  loadedRecord.add(csvRecord);
				  BigDecimal cdValId=cdValId(valuesList,csvRecord,sum==null?columnList.size() : sum.numCols);
				  if(cdValId==null){
					  CanonE001CodeTableDAO.CanonE001CodeTabValRec valRec=new CanonE001CodeTableDAO.CanonE001CodeTabValRec();
					  for(int j=0;j<columnList.size();j++){
						  CodeTableColumn col=columnList.get(j); 
						  int colIndex=toInt(col.getResultCol().substring(3));
						  String val=csvRecord[j];
						  if(isEmpty(val)){
							  setCdValValue(colIndex,null,valRec);
						  }else{
							  setCdValValue(colIndex,val,valRec);
						  }
						  valRec.setCdId(cdID);
					  }
					  result=CanonE001CodeTableDAO.createUpdateCodeTabVal(userName, cdID, cdColumns, Arrays.asList(valRec));
				      try {
				    	  checkErrors(result, 0, 1);
						  cv.numRecordImported++;
				      } catch (E001Exception ex) {
						  throw new DataLineSaveError(lineNumber);
				    	  
					  }
				  }else{
					  cv.numRecordNoChange++;
					  valIDs.add(cdValId);
				  }
			  }catch(IOException ex){
				  cv.numRecordFailed++;
				  cv.lineMessages.add(ex.getMessage());
				  ex.printStackTrace();
			  }
		  }
		  if(importNew){
			  for(CanonE001CodeTableDAO.CanonS21CdVal value:valuesList){
				  if(!valIDs.contains(value.valID)){
				 		result=CanonE001CodeTableDAO.deleteCodeTabVal(userName, cdID, value.valID);
					    try{
					    	checkErrors(result, 0,1);
					    	cv.numRecordDeleted++;
					    }catch(Exception e){
					    	e.printStackTrace();
					    }
				  }
			  }
		  }
		  cv.isChanged=(valuesList.size()!=cv.numRecordRead) || (cv.numRecordRead!=cv.numRecordNoChange) || cv.numRecordDeleted>0;
		  return cv;
	  }
	  
	private static void validateRecord( List<CodeTableColumn> columnList, String []csvRecord, int lineNumber,List<String []> loadedRecord)throws IOException{
		for(int i=0;i<loadedRecord.size();i++){
			String []lrec=loadedRecord.get(i);
			if(Arrays.equals(lrec, csvRecord)){
				throw new DataLineDuplicateError(lineNumber,i);
			}
		}
		for(int i=0;i<columnList.size();i++){
			CodeTableColumn col=columnList.get(i);
			String val=csvRecord[i];
			if("Y".equals(col.getIsMandatory()) && isEmpty(val)){
				throw new DataLineValidationError("Value is Mandatory","",lineNumber,col.getColPrompt());
			}
			if(!isEmpty(val)){
				if("NUMBER".equals(col.getColFormat())){
					if(!CanonE001CommonUtil.isNumber(val)){
						throw new DataLineValidationError("Invalid Number format",val,lineNumber,col.getColPrompt());
					}
				}else if("DATE".equals(col.getColFormat())){
					if(!CanonE001CommonUtil.isEmpty(val) && CanonE001CommonUtil.toTimestamp5(val)==null ){
						throw new DataLineValidationError("Invalid Date format", val,lineNumber,col.getColPrompt());
					}
				}
			}
		}
	}
	
	private static BigDecimal cdValId(List<CanonE001CodeTableDAO.CanonS21CdVal> valuesList,String []csvRecord,int numCols){
		for(CanonE001CodeTableDAO.CanonS21CdVal val:valuesList){
		    boolean isSame = true;
			for(int i=0;i<numCols;i++){
			      if(isEmpty(csvRecord[i])){
			    	 if(!isEmpty(val.values[i])){
			    		 isSame = false;
			    		 break;
			    	 }
			      }else{
			    	  if(!(csvRecord[i].equals(val.values[i]))){
			    		 isSame = false;
			    		 break;
			    	  }
			      }
			}
			if(isSame){
				return val.valID; 
			}
		}
		return null;
	}
	
	private static String readString(CdReader cr,int col)throws Exception{
		return cr.get(col);
	}
	
	private static BigDecimal readNumber(CdReader cr,int col)throws Exception{
		return CanonE001CommonUtil.toBigDecimal(readString(cr,col),false);
	}
	
	private static String checkString(CdReader cr,String expected,int col)throws Exception{
		String v=readString(cr,col);
		if(!expected.equals(v)){
			throw new InvalidFormatException("expect '"+expected+ "' but found '"+v+"'");
		}
		return v;
	}

	private static String readLabelString(CdReader cr,String label,int col)throws Exception{
		checkString(cr,label,col);
		return readString(cr,col+1);
	}
	
	private static String checkLabelString(CdReader cr,String label,int col,String expected)throws Exception{
		String val=readLabelString(cr,label,col);
		if(expected!=null && !expected.equals(val)){
			throw new Exception("Invalid "+label +" expect "+expected+ " but found "+val);
		}
		return val;
	}

	private static String readLabelString(CdReader cr,String label,int col,String expected)throws Exception{
		String val=readString(cr,col);
		if(expected!=null && !expected.equals(val)){
			throw new Exception("Invalid "+label +" expect "+expected+ " but found "+val);
		}
		return val;
	}
	
	private static BigDecimal readLabelNumber(CdReader cr,String label,int col)throws Exception{
		checkString(cr,label,col);
		return readNumber(cr,col+1);
	}

	private static BigDecimal checkNumber(CdReader cr,String label,int col,BigDecimal expected)throws Exception{
		BigDecimal val=readNumber(cr,col);
		if(expected!=null && !expected.equals(val)){
			throw new Exception("Invalid "+label +" expect "+expected+ " but found "+val);
		}
		return val;
	}
	private static BigDecimal checkLabelNumber(CdReader cr,String label,int col,BigDecimal expected)throws Exception{
		BigDecimal val=readLabelNumber(cr,label,col);
		if(expected!=null && !expected.equals(val)){
			throw new Exception("Invalid "+label +" expect "+expected+ " but found "+val);
		}
		return val;
	}
	
    public static void error(PrintWriter printWriter, Exception e)
			throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("error_flag", true);
		obj.put("error", e.getMessage());
		printWriter.write(obj.serialize(true));
	}
	
    private static StringBuilder append(StringBuilder sb,String s){
		sb.append(sb.length()==0? s : ", "+s );
		return sb;
    }
    
    public static void success(final PrintWriter printWriter,Summary sum,Columns cols,CdValues cdvs,String fileName, boolean importNew)
			throws Exception {
    	
    	StringBuilder  default_message=new StringBuilder();
		append(default_message, fileName);
    	if(sum!=null && sum.newCodeTable){
    		append(default_message, "New Code Table Created");
    	}
    	if(sum!=null && sum.isDescriptionChanged){
    		append(default_message, "'Custom Code Table Description' changed");
    	}
    	
    	boolean changed=(sum!=null && sum.isDescriptionChanged)|| (sum!=null && sum.newCodeTable) ||(cols!=null && cols.isNew)||cdvs.isChanged;
		if(changed){
			append(default_message,String.format("found %d record(s)",cdvs.numRecordRead));
			if(cdvs.numRecordImported>0) append(default_message,String.format("%d imported",cdvs.numRecordImported));
			if(cdvs.numRecordDeleted>0) append(default_message,String.format("%d deleted",cdvs.numRecordDeleted));
			if(cdvs.numRecordNoChange>0) append(default_message,String.format("%d has no changes",cdvs.numRecordNoChange));
			if(cdvs.numRecordFailed>0) append(default_message,String.format("%d failed",cdvs.numRecordFailed));
			if(!importNew && (cdvs.numRecordImported>0|| cdvs.numRecordDeleted>0 ||(sum!=null && sum.isDescriptionChanged))){
				append(default_message, "Refresh the page to see changes.");	
			}
			
		}else{
			append(default_message, "There is No Change.");
		}
		JSONObject obj = new JSONObject();
		obj.put("error_flag", false);
		obj.put("message", default_message.toString());
		if(cdvs.lineMessages.size()>0){
			JSONArray jr=new JSONArray();
			jr.addAll(cdvs.lineMessages);
			obj.put("line_messages",jr);
		}
		printWriter.write(obj.serialize(true));
	}
    
    static class InvalidFormatException extends Exception{
		InvalidFormatException(){
    		super("Invalid file format.");
    	}
    	InvalidFormatException(String s){
    		super("Invalid file format, "+s);
    	}
    }
    
    public static int toInt(String strInt) {
  	  return toInt(strInt,-1);
    }
    
    public static int toInt(String strInt,int defaultVal) {
        if (strInt != null) {
            try {
                Matcher matcher = NON_INT_PATTERN.matcher(strInt);
                if (matcher.find()) {
                    String cleanStr = matcher.replaceAll("");
                    return Integer.parseInt(cleanStr);
                } else {
                    return Integer.parseInt(strInt);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return defaultVal;
    }

    public static String trimAll(String str) {
        return PATTERN_WHITESPACES.matcher(str).replaceAll("");
    }

	private static class Summary{
        String cdName;
        String cdDescription;
        String cdViewName;
        int numCols;
        boolean isDescriptionChanged=false;
        boolean newCodeTable=false;
		public String toString() {
			return "Summary [cdName=" + cdName + ", cdDescription="
					+ cdDescription + ", cdViewName=" + cdViewName
					+ ", numCols=" + numCols + ", isDescriptionChanged=" + isDescriptionChanged
					+ ", newCodeTable=" + newCodeTable + "]";
		}
        
	}

	private static class Columns{
		boolean isNew=false;
		List<CodeTableColumn> columns;
		@Override
		public String toString() {
			return "Columns [isNew=" + isNew + ", columns=" + columns + "]";
		}
		
	}
	
	private static class CdValues{
        boolean isChanged=false;
        int numRecordRead=0;
        int numRecordImported=0;
        int numRecordNoChange=0;
        int numRecordFailed=0;
        int numRecordDeleted=0;
        List<String> lineMessages=new ArrayList<String>();
		public String toString() {
			return "CdValues [isChanged=" + isChanged + ", numRecordRead="
					+ numRecordRead + ", numRecordImported="
					+ numRecordImported + ", numRecordNoChange="
					+ numRecordNoChange + ", numRecordFailed="
					+ numRecordFailed + ", numRecordDeleted="
					+ numRecordDeleted + ", lineMessages=" + lineMessages + "]";
		}
	}
	
	private static class DataLineValidationError extends IOException{
		int lineNumber;
		String value;
		String colPrompt;
		public DataLineValidationError(String s,String val,int lineNumber,String colPrompt){
			super("line# "+lineNumber+" column "+colPrompt+" " +s);
			this.value=val;
			this.lineNumber=lineNumber;
			this.colPrompt=colPrompt;
		}
	}

	private static class DataLineSaveError extends IOException{
		int lineNumber;
		public DataLineSaveError(int lineNumber){
			super("line# "+lineNumber+" database error");
			this.lineNumber=lineNumber;
		}
	}

	private static class DataLineDuplicateError extends IOException{
		int lineNumber;
		public DataLineDuplicateError(int lineNumber,int which){
			super("line# "+lineNumber+" duplicate record");
			this.lineNumber=lineNumber;
		}
	}
	
	public interface CdWriter{

		void setWriter(OutputStream os,char del, Charset ch);
		
		String getContentType();

		void writeComment(String commentBasic) throws IOException;

		void close();

		void flush() throws IOException;

		void endRecord() throws IOException;

		void write(String cdName) throws IOException;

		void writeRecord(String[] sumHeaders) throws IOException;
		
		boolean writeSummary();
		boolean writeColumns();
		boolean writeDataHeader();
		boolean writeData();
		String getFileName();
	}
	
	public interface CdReader{
		void setUseComments(boolean b);
		String get(int col) throws IOException;
		String[] getValues() throws IOException;
		boolean readRecord() throws IOException;
		boolean readSummary();
		boolean readColumns();
		boolean readDataHeader();
		boolean readData();
	}
	
	public interface ColumnInfo{
		void setColumnList(List<CodeTableColumn> list);
	}
	
	static class CsvCdWriter implements CdWriter{
		String cdName=null;
		private CsvWriter c=null;
		public CsvCdWriter (String cdName){
			this.cdName=cdName;
		}
		public void setWriter (OutputStream os,char del, Charset ch){
			c=new CsvWriter(os,del,ch);
		}
		@Override
		public void writeComment(String commentText) throws IOException {
			c.writeComment(commentText);
		}
		@Override
		public void close() {
			c.close();
		}
		@Override
		public void flush() throws IOException {
			c.flush();
		}
		@Override
		public void endRecord() throws IOException {
			c.endRecord();
		}
		@Override
		public void write(String str) throws IOException {
			c.write(str);
		}
		@Override
		public void writeRecord(String[] values) throws IOException {
			c.writeRecord(values);
		}
		
		public boolean writeSummary(){
			return true;
		}
		@Override
		public boolean writeColumns() {
			return true;
		}
		@Override
		public boolean writeData() {
			return true;
		}
		@Override
		public boolean writeDataHeader() {
			return true;
		}
		@Override
		public String getFileName() {
			return cdName+".csv";
		}
		
		@Override
		public String getContentType() {
			return "text/csv";		
		}
		
	}
	
	static class ExcelCdWriter  implements CdWriter{
		String cdName=null;
		Workbook workbook;
        private Worksheet sheet;
        private OutputStream os;
		int row=0;
		int col=0;
		public ExcelCdWriter(String cdName) {
			this.cdName=cdName;
			workbook=new WorkbookImpl();
			sheet= workbook.getWorksheets().getSheet(0);
		}
		public boolean writeSummary(){
			return false;
		}
		@Override
		public boolean writeColumns() {
			return false;
		}
		@Override
		public boolean writeDataHeader() {
			return true;
		}
		@Override
		public boolean writeData() {
			return true;
		}
		@Override
		public String getFileName() {
			return cdName+".xls";
		}
		@Override
		public String getContentType() {
			return "application/vnd.ms-excel";
		}
		@Override
		public void setWriter(OutputStream os, char del, Charset ch) {
			this.os=os;
			CanonAsposeUtil.loadLicense();
		}
		@Override
		public void writeComment(String comment) throws IOException {
		}
		@Override
		public void close() {
		}
		@Override
		public void flush() throws IOException {
			workbook.save(os,canon.excel.cells.FileFormatType.EXCEL97TO2003);
		}
		@Override
		public void endRecord() throws IOException {
			row++;
			col=0;
		}
		@Override
		public void write(String str) throws IOException {
			sheet.getCells().getCell(row, col).setValue(str);
			col++;
		}
		@Override
		public void writeRecord(String[] recs) throws IOException {
			for(String rec:recs){
				write(rec);
			}
			endRecord();
		}
	}

	
	static class CsvCdReader implements CdReader{
		private CsvReader c=null;
		public CsvCdReader(InputStream is,char del){
        	BufferedReader r = new BufferedReader(new InputStreamReader(is, CHARSET_UTF8));
			c=new CsvReader(r,del);
		}
		@Override
		public void setUseComments(boolean b) {
			c.setUseComments(b);
		}
		@Override
		public String get(int col) throws IOException {
			return c.get(col);
		}
		@Override
		public String[] getValues() throws IOException {
			return c.getValues();
		}
		@Override
		public boolean readRecord() throws IOException {
			return c.readRecord();
		}
		@Override
		public boolean readSummary() {
			return true;
		}
		@Override
		public boolean readColumns() {
			return true;
		}
		@Override
		public boolean readData() {
			return true;
		}
		@Override
		public boolean readDataHeader() {
			return true;
		}
	}

	static class ExcelCdReader implements CdReader,ColumnInfo{
		private Workbook workbook;
        private Worksheet sheet;
        private int row=0;
        private int col=0;
		private List<CodeTableColumn> columnList;
		private String values[];
		public ExcelCdReader(InputStream is, char c) {
			workbook=new WorkbookImpl();
			try {
				workbook.open(is);
				sheet=workbook.getWorksheets().getSheet(0);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		public void setUseComments(boolean b) {
		}

		public String get(int c) throws IOException {
			return values[c];
		}

		@Override
		public String[] getValues() throws IOException {
			return values;
		}

		@Override
		public boolean readRecord() throws IOException {
			values=new String[columnList.size()];
			boolean eof=true;
			for(int i=0;i<values.length;i++){
				values[i]=sheet.getCells().getCell(row, i).getStringValue();
				eof=eof && isEmpty(values[i]);
			}
			row++;
			return !eof;
		}

		@Override
		public boolean readSummary() {
			return false;
		}

		@Override
		public boolean readColumns() {
			return false;
		}

		@Override
		public boolean readDataHeader() {
			return true;
		}

		@Override
		public boolean readData() {
			return true;
		}
		
		@Override
		public void setColumnList(List<CodeTableColumn> list) {
			this.columnList=list;
		}
		
	}
	
	/**
	 * A stream based parser for parsing delimited text data from a file or a
	 * stream.
	 */
	public static class CsvReader {
		private Reader inputStream = null;

		private String fileName = null;

		// this holds all the values for switches that the user is allowed to set
		private UserSettings userSettings = new UserSettings();

		private Charset charset = null;

		private boolean useCustomRecordDelimiter = false;

		// this will be our working buffer to hold data chunks
		// read in from the data file

		private DataBuffer dataBuffer = new DataBuffer();

		private ColumnBuffer columnBuffer = new ColumnBuffer();

		private RawRecordBuffer rawBuffer = new RawRecordBuffer();

		private boolean[] isQualified = null;

		private String rawRecord = "";

		private HeadersHolder headersHolder = new HeadersHolder();

		// these are all more or less global loop variables
		// to keep from needing to pass them all into various
		// methods during parsing

		private boolean startedColumn = false;

		private boolean startedWithQualifier = false;

		private boolean hasMoreData = true;

		private char lastLetter = '\0';

		private boolean hasReadNextLine = false;

		private int columnsCount = 0;

		private long currentRecord = 0;

		private String[] values = new String[StaticSettings.INITIAL_COLUMN_COUNT];

		private boolean initialized = false;

		private boolean closed = false;

		/**
		 * Double up the text qualifier to represent an occurance of the text
		 * qualifier.
		 */
		public static final int ESCAPE_MODE_DOUBLED = 1;

		/**
		 * Use a backslash character before the text qualifier to represent an
		 * occurance of the text qualifier.
		 */
		public static final int ESCAPE_MODE_BACKSLASH = 2;

		/**
		 * Creates a {@link com.csvreader.CsvReader CsvReader} object using a file
		 * as the data source.
		 * 
		 * @param fileName
		 *            The path to the file to use as the data source.
		 * @param delimiter
		 *            The character to use as the column delimiter.
		 * @param charset
		 *            The {@link java.nio.charset.Charset Charset} to use while
		 *            parsing the data.
		 */
		public CsvReader(String fileName, char delimiter, Charset charset)
				throws FileNotFoundException {
			if (fileName == null) {
				throw new IllegalArgumentException(
						"Parameter fileName can not be null.");
			}

			if (charset == null) {
				throw new IllegalArgumentException(
						"Parameter charset can not be null.");
			}

			if (!new File(fileName).exists()) {
				throw new FileNotFoundException("File " + fileName
						+ " does not exist.");
			}

			this.fileName = fileName;
			this.userSettings.Delimiter = delimiter;
			this.charset = charset;

			isQualified = new boolean[values.length];
		}

		/**
		 * Creates a {@link com.csvreader.CsvReader CsvReader} object using a file
		 * as the data source.&nbsp;Uses ISO-8859-1 as the
		 * {@link java.nio.charset.Charset Charset}.
		 * 
		 * @param fileName
		 *            The path to the file to use as the data source.
		 * @param delimiter
		 *            The character to use as the column delimiter.
		 */
		public CsvReader(String fileName, char delimiter)
				throws FileNotFoundException {
			this(fileName, delimiter, Charset.forName("ISO-8859-1"));
		}

		/**
		 * Creates a {@link com.csvreader.CsvReader CsvReader} object using a file
		 * as the data source.&nbsp;Uses a comma as the column delimiter and
		 * ISO-8859-1 as the {@link java.nio.charset.Charset Charset}.
		 * 
		 * @param fileName
		 *            The path to the file to use as the data source.
		 */
		public CsvReader(String fileName) throws FileNotFoundException {
			this(fileName, Letters.COMMA);
		}

		/**
		 * Constructs a {@link com.csvreader.CsvReader CsvReader} object using a
		 * {@link java.io.Reader Reader} object as the data source.
		 * 
		 * @param inputStream
		 *            The stream to use as the data source.
		 * @param delimiter
		 *            The character to use as the column delimiter.
		 */
		public CsvReader(Reader inputStream, char delimiter) {
			if (inputStream == null) {
				throw new IllegalArgumentException(
						"Parameter inputStream can not be null.");
			}

			this.inputStream = inputStream;
			this.userSettings.Delimiter = delimiter;
			initialized = true;

			isQualified = new boolean[values.length];
		}

		/**
		 * Constructs a {@link com.csvreader.CsvReader CsvReader} object using a
		 * {@link java.io.Reader Reader} object as the data source.&nbsp;Uses a
		 * comma as the column delimiter.
		 * 
		 * @param inputStream
		 *            The stream to use as the data source.
		 */
		public CsvReader(Reader inputStream) {
			this(inputStream, Letters.COMMA);
		}

		/**
		 * Constructs a {@link com.csvreader.CsvReader CsvReader} object using an
		 * {@link java.io.InputStream InputStream} object as the data source.
		 * 
		 * @param inputStream
		 *            The stream to use as the data source.
		 * @param delimiter
		 *            The character to use as the column delimiter.
		 * @param charset
		 *            The {@link java.nio.charset.Charset Charset} to use while
		 *            parsing the data.
		 */
		public CsvReader(InputStream inputStream, char delimiter, Charset charset) {
			this(new InputStreamReader(inputStream, charset), delimiter);
		}

		/**
		 * Constructs a {@link com.csvreader.CsvReader CsvReader} object using an
		 * {@link java.io.InputStream InputStream} object as the data
		 * source.&nbsp;Uses a comma as the column delimiter.
		 * 
		 * @param inputStream
		 *            The stream to use as the data source.
		 * @param charset
		 *            The {@link java.nio.charset.Charset Charset} to use while
		 *            parsing the data.
		 */
		public CsvReader(InputStream inputStream, Charset charset) {
			this(new InputStreamReader(inputStream, charset));
		}

		public boolean getCaptureRawRecord() {
			return userSettings.CaptureRawRecord;
		}

		public void setCaptureRawRecord(boolean captureRawRecord) {
			userSettings.CaptureRawRecord = captureRawRecord;
		}

		public String getRawRecord() {
			return rawRecord;
		}

		/**
		 * Gets whether leading and trailing whitespace characters are being trimmed
		 * from non-textqualified column data. Default is true.
		 * 
		 * @return Whether leading and trailing whitespace characters are being
		 *         trimmed from non-textqualified column data.
		 */
		public boolean getTrimWhitespace() {
			return userSettings.TrimWhitespace;
		}

		/**
		 * Sets whether leading and trailing whitespace characters should be trimmed
		 * from non-textqualified column data or not. Default is true.
		 * 
		 * @param trimWhitespace
		 *            Whether leading and trailing whitespace characters should be
		 *            trimmed from non-textqualified column data or not.
		 */
		public void setTrimWhitespace(boolean trimWhitespace) {
			userSettings.TrimWhitespace = trimWhitespace;
		}

		/**
		 * Gets the character being used as the column delimiter. Default is comma,
		 * ','.
		 * 
		 * @return The character being used as the column delimiter.
		 */
		public char getDelimiter() {
			return userSettings.Delimiter;
		}

		/**
		 * Sets the character to use as the column delimiter. Default is comma, ','.
		 * 
		 * @param delimiter
		 *            The character to use as the column delimiter.
		 */
		public void setDelimiter(char delimiter) {
			userSettings.Delimiter = delimiter;
		}

		public char getRecordDelimiter() {
			return userSettings.RecordDelimiter;
		}

		/**
		 * Sets the character to use as the record delimiter.
		 * 
		 * @param recordDelimiter
		 *            The character to use as the record delimiter. Default is
		 *            combination of standard end of line characters for Windows,
		 *            Unix, or Mac.
		 */
		public void setRecordDelimiter(char recordDelimiter) {
			useCustomRecordDelimiter = true;
			userSettings.RecordDelimiter = recordDelimiter;
		}

		/**
		 * Gets the character to use as a text qualifier in the data.
		 * 
		 * @return The character to use as a text qualifier in the data.
		 */
		public char getTextQualifier() {
			return userSettings.TextQualifier;
		}

		/**
		 * Sets the character to use as a text qualifier in the data.
		 * 
		 * @param textQualifier
		 *            The character to use as a text qualifier in the data.
		 */
		public void setTextQualifier(char textQualifier) {
			userSettings.TextQualifier = textQualifier;
		}

		/**
		 * Whether text qualifiers will be used while parsing or not.
		 * 
		 * @return Whether text qualifiers will be used while parsing or not.
		 */
		public boolean getUseTextQualifier() {
			return userSettings.UseTextQualifier;
		}

		/**
		 * Sets whether text qualifiers will be used while parsing or not.
		 * 
		 * @param useTextQualifier
		 *            Whether to use a text qualifier while parsing or not.
		 */
		public void setUseTextQualifier(boolean useTextQualifier) {
			userSettings.UseTextQualifier = useTextQualifier;
		}

		/**
		 * Gets the character being used as a comment signal.
		 * 
		 * @return The character being used as a comment signal.
		 */
		public char getComment() {
			return userSettings.Comment;
		}

		/**
		 * Sets the character to use as a comment signal.
		 * 
		 * @param comment
		 *            The character to use as a comment signal.
		 */
		public void setComment(char comment) {
			userSettings.Comment = comment;
		}

		/**
		 * Gets whether comments are being looked for while parsing or not.
		 * 
		 * @return Whether comments are being looked for while parsing or not.
		 */
		public boolean getUseComments() {
			return userSettings.UseComments;
		}

		/**
		 * Sets whether comments are being looked for while parsing or not.
		 * 
		 * @param useComments
		 *            Whether comments are being looked for while parsing or not.
		 */
		public void setUseComments(boolean useComments) {
			userSettings.UseComments = useComments;
		}

		/**
		 * Gets the current way to escape an occurance of the text qualifier inside
		 * qualified data.
		 * 
		 * @return The current way to escape an occurance of the text qualifier
		 *         inside qualified data.
		 */
		public int getEscapeMode() {
			return userSettings.EscapeMode;
		}

		/**
		 * Sets the current way to escape an occurance of the text qualifier inside
		 * qualified data.
		 * 
		 * @param escapeMode
		 *            The way to escape an occurance of the text qualifier inside
		 *            qualified data.
		 * @exception IllegalArgumentException
		 *                When an illegal value is specified for escapeMode.
		 */
		public void setEscapeMode(int escapeMode) throws IllegalArgumentException {
			if (escapeMode != ESCAPE_MODE_DOUBLED
					&& escapeMode != ESCAPE_MODE_BACKSLASH) {
				throw new IllegalArgumentException(
						"Parameter escapeMode must be a valid value.");
			}

			userSettings.EscapeMode = escapeMode;
		}

		public boolean getSkipEmptyRecords() {
			return userSettings.SkipEmptyRecords;
		}

		public void setSkipEmptyRecords(boolean skipEmptyRecords) {
			userSettings.SkipEmptyRecords = skipEmptyRecords;
		}

		/**
		 * Safety caution to prevent the parser from using large amounts of memory
		 * in the case where parsing settings like file encodings don't end up
		 * matching the actual format of a file. This switch can be turned off if
		 * the file format is known and tested. With the switch off, the max column
		 * lengths and max column count per record supported by the parser will
		 * greatly increase. Default is true.
		 * 
		 * @return The current setting of the safety switch.
		 */
		public boolean getSafetySwitch() {
			return userSettings.SafetySwitch;
		}

		/**
		 * Safety caution to prevent the parser from using large amounts of memory
		 * in the case where parsing settings like file encodings don't end up
		 * matching the actual format of a file. This switch can be turned off if
		 * the file format is known and tested. With the switch off, the max column
		 * lengths and max column count per record supported by the parser will
		 * greatly increase. Default is true.
		 * 
		 * @param safetySwitch
		 */
		public void setSafetySwitch(boolean safetySwitch) {
			userSettings.SafetySwitch = safetySwitch;
		}

		/**
		 * Gets the count of columns found in this record.
		 * 
		 * @return The count of columns found in this record.
		 */
		public int getColumnCount() {
			return columnsCount;
		}

		/**
		 * Gets the index of the current record.
		 * 
		 * @return The index of the current record.
		 */
		public long getCurrentRecord() {
			return currentRecord - 1;
		}

		/**
		 * Gets the count of headers read in by a previous call to
		 * {@link com.csvreader.CsvReader#readHeaders readHeaders()}.
		 * 
		 * @return The count of headers read in by a previous call to
		 *         {@link com.csvreader.CsvReader#readHeaders readHeaders()}.
		 */
		public int getHeaderCount() {
			return headersHolder.Length;
		}

		/**
		 * Returns the header values as a string array.
		 * 
		 * @return The header values as a String array.
		 * @exception IOException
		 *                Thrown if this object has already been closed.
		 */
		public String[] getHeaders() throws IOException {
			checkClosed();

			if (headersHolder.Headers == null) {
				return null;
			} else {
				// use clone here to prevent the outside code from
				// setting values on the array directly, which would
				// throw off the index lookup based on header name
				String[] clone = new String[headersHolder.Length];
				System.arraycopy(headersHolder.Headers, 0, clone, 0,
						headersHolder.Length);
				return clone;
			}
		}

		public void setHeaders(String[] headers) {
			headersHolder.Headers = headers;

			headersHolder.IndexByName.clear();

			if (headers != null) {
				headersHolder.Length = headers.length;
			} else {
				headersHolder.Length = 0;
			}

			// use headersHolder.Length here in case headers is null
			for (int i = 0; i < headersHolder.Length; i++) {
				headersHolder.IndexByName.put(headers[i], new Integer(i));
			}
		}

		public String[] getValues() throws IOException {
			checkClosed();

			// need to return a clone, and can't use clone because values.Length
			// might be greater than columnsCount
			String[] clone = new String[columnsCount];
			System.arraycopy(values, 0, clone, 0, columnsCount);
			return clone;
		}

		/**
		 * Returns the current column value for a given column index.
		 * 
		 * @param columnIndex
		 *            The index of the column.
		 * @return The current column value.
		 * @exception IOException
		 *                Thrown if this object has already been closed.
		 */
		public String get(int columnIndex) throws IOException {
			checkClosed();

			if (columnIndex > -1 && columnIndex < columnsCount) {
				return values[columnIndex];
			} else {
				return "";
			}
		}

		/**
		 * Returns the current column value for a given column header name.
		 * 
		 * @param headerName
		 *            The header name of the column.
		 * @return The current column value.
		 * @exception IOException
		 *                Thrown if this object has already been closed.
		 */
		public String get(String headerName) throws IOException {
			checkClosed();

			return get(getIndex(headerName));
		}

		/**
		 * Creates a {@link com.csvreader.CsvReader CsvReader} object using a string
		 * of data as the source.&nbsp;Uses ISO-8859-1 as the
		 * {@link java.nio.charset.Charset Charset}.
		 * 
		 * @param data
		 *            The String of data to use as the source.
		 * @return A {@link com.csvreader.CsvReader CsvReader} object using the
		 *         String of data as the source.
		 */
		public static CsvReader parse(String data) {
			if (data == null) {
				throw new IllegalArgumentException(
						"Parameter data can not be null.");
			}

			return new CsvReader(new StringReader(data));
		}

		/**
		 * Reads another record.
		 * 
		 * @return Whether another record was successfully read or not.
		 * @exception IOException
		 *                Thrown if an error occurs while reading data from the
		 *                source stream.
		 */
		public boolean readRecord() throws IOException {
			checkClosed();

			columnsCount = 0;
			rawBuffer.Position = 0;

			dataBuffer.LineStart = dataBuffer.Position;

			hasReadNextLine = false;

			// check to see if we've already found the end of data

			if (hasMoreData) {
				// loop over the data stream until the end of data is found
				// or the end of the record is found

				do {
					if (dataBuffer.Position == dataBuffer.Count) {
						checkDataLength();
					} else {
						startedWithQualifier = false;

						// grab the current letter as a char

						char currentLetter = dataBuffer.Buffer[dataBuffer.Position];

						if (userSettings.UseTextQualifier
								&& currentLetter == userSettings.TextQualifier) {
							// this will be a text qualified column, so
							// we need to set startedWithQualifier to make it
							// enter the seperate branch to handle text
							// qualified columns

							lastLetter = currentLetter;

							// read qualified
							startedColumn = true;
							dataBuffer.ColumnStart = dataBuffer.Position + 1;
							startedWithQualifier = true;
							boolean lastLetterWasQualifier = false;

							char escapeChar = userSettings.TextQualifier;

							if (userSettings.EscapeMode == ESCAPE_MODE_BACKSLASH) {
								escapeChar = Letters.BACKSLASH;
							}

							boolean eatingTrailingJunk = false;
							boolean lastLetterWasEscape = false;
							boolean readingComplexEscape = false;
							int escape = ComplexEscape.UNICODE;
							int escapeLength = 0;
							char escapeValue = (char) 0;

							dataBuffer.Position++;

							do {
								if (dataBuffer.Position == dataBuffer.Count) {
									checkDataLength();
								} else {
									// grab the current letter as a char

									currentLetter = dataBuffer.Buffer[dataBuffer.Position];

									if (eatingTrailingJunk) {
										dataBuffer.ColumnStart = dataBuffer.Position + 1;

										if (currentLetter == userSettings.Delimiter) {
											endColumn();
										} else if ((!useCustomRecordDelimiter && (currentLetter == Letters.CR || currentLetter == Letters.LF))
												|| (useCustomRecordDelimiter && currentLetter == userSettings.RecordDelimiter)) {
											endColumn();

											endRecord();
										}
									} else if (readingComplexEscape) {
										escapeLength++;

										switch (escape) {
										case ComplexEscape.UNICODE:
											escapeValue *= (char) 16;
											escapeValue += hexToDec(currentLetter);

											if (escapeLength == 4) {
												readingComplexEscape = false;
											}

											break;
										case ComplexEscape.OCTAL:
											escapeValue *= (char) 8;
											escapeValue += (char) (currentLetter - '0');

											if (escapeLength == 3) {
												readingComplexEscape = false;
											}

											break;
										case ComplexEscape.DECIMAL:
											escapeValue *= (char) 10;
											escapeValue += (char) (currentLetter - '0');

											if (escapeLength == 3) {
												readingComplexEscape = false;
											}

											break;
										case ComplexEscape.HEX:
											escapeValue *= (char) 16;
											escapeValue += hexToDec(currentLetter);

											if (escapeLength == 2) {
												readingComplexEscape = false;
											}

											break;
										}

										if (!readingComplexEscape) {
											appendLetter(escapeValue);
										} else {
											dataBuffer.ColumnStart = dataBuffer.Position + 1;
										}
									} else if (currentLetter == userSettings.TextQualifier) {
										if (lastLetterWasEscape) {
											lastLetterWasEscape = false;
											lastLetterWasQualifier = false;
										} else {
											updateCurrentValue();

											if (userSettings.EscapeMode == ESCAPE_MODE_DOUBLED) {
												lastLetterWasEscape = true;
											}

											lastLetterWasQualifier = true;
										}
									} else if (userSettings.EscapeMode == ESCAPE_MODE_BACKSLASH
											&& lastLetterWasEscape) {
										switch (currentLetter) {
										case 'n':
											appendLetter(Letters.LF);
											break;
										case 'r':
											appendLetter(Letters.CR);
											break;
										case 't':
											appendLetter(Letters.TAB);
											break;
										case 'b':
											appendLetter(Letters.BACKSPACE);
											break;
										case 'f':
											appendLetter(Letters.FORM_FEED);
											break;
										case 'e':
											appendLetter(Letters.ESCAPE);
											break;
										case 'v':
											appendLetter(Letters.VERTICAL_TAB);
											break;
										case 'a':
											appendLetter(Letters.ALERT);
											break;
										case '0':
										case '1':
										case '2':
										case '3':
										case '4':
										case '5':
										case '6':
										case '7':
											escape = ComplexEscape.OCTAL;
											readingComplexEscape = true;
											escapeLength = 1;
											escapeValue = (char) (currentLetter - '0');
											dataBuffer.ColumnStart = dataBuffer.Position + 1;
											break;
										case 'u':
										case 'x':
										case 'o':
										case 'd':
										case 'U':
										case 'X':
										case 'O':
										case 'D':
											switch (currentLetter) {
											case 'u':
											case 'U':
												escape = ComplexEscape.UNICODE;
												break;
											case 'x':
											case 'X':
												escape = ComplexEscape.HEX;
												break;
											case 'o':
											case 'O':
												escape = ComplexEscape.OCTAL;
												break;
											case 'd':
											case 'D':
												escape = ComplexEscape.DECIMAL;
												break;
											}

											readingComplexEscape = true;
											escapeLength = 0;
											escapeValue = (char) 0;
											dataBuffer.ColumnStart = dataBuffer.Position + 1;

											break;
										default:
											break;
										}

										lastLetterWasEscape = false;

										// can only happen for ESCAPE_MODE_BACKSLASH
									} else if (currentLetter == escapeChar) {
										updateCurrentValue();
										lastLetterWasEscape = true;
									} else {
										if (lastLetterWasQualifier) {
											if (currentLetter == userSettings.Delimiter) {
												endColumn();
											} else if ((!useCustomRecordDelimiter && (currentLetter == Letters.CR || currentLetter == Letters.LF))
													|| (useCustomRecordDelimiter && currentLetter == userSettings.RecordDelimiter)) {
												endColumn();

												endRecord();
											} else {
												dataBuffer.ColumnStart = dataBuffer.Position + 1;

												eatingTrailingJunk = true;
											}

											// make sure to clear the flag for next
											// run of the loop

											lastLetterWasQualifier = false;
										}
									}

									// keep track of the last letter because we need
									// it for several key decisions

									lastLetter = currentLetter;

									if (startedColumn) {
										dataBuffer.Position++;

										if (userSettings.SafetySwitch
												&& dataBuffer.Position
														- dataBuffer.ColumnStart
														+ columnBuffer.Position > 100000) {
											close();

											throw new IOException(
													"Maximum column length of 100,000 exceeded in column "
															+ NumberFormat
																	.getIntegerInstance()
																	.format(
																			columnsCount)
															+ " in record "
															+ NumberFormat
																	.getIntegerInstance()
																	.format(
																			currentRecord)
															+ ". Set the SafetySwitch property to false"
															+ " if you're expecting column lengths greater than 100,000 characters to"
															+ " avoid this error.");
										}
									}
								} // end else

							} while (hasMoreData && startedColumn);
						} else if (currentLetter == userSettings.Delimiter) {
							// we encountered a column with no data, so
							// just send the end column

							lastLetter = currentLetter;

							endColumn();
						} else if (useCustomRecordDelimiter
								&& currentLetter == userSettings.RecordDelimiter) {
							// this will skip blank lines
							if (startedColumn || columnsCount > 0
									|| !userSettings.SkipEmptyRecords) {
								endColumn();

								endRecord();
							} else {
								dataBuffer.LineStart = dataBuffer.Position + 1;
							}

							lastLetter = currentLetter;
						} else if (!useCustomRecordDelimiter
								&& (currentLetter == Letters.CR || currentLetter == Letters.LF)) {
							// this will skip blank lines
							if (startedColumn
									|| columnsCount > 0
									|| (!userSettings.SkipEmptyRecords && (currentLetter == Letters.CR || lastLetter != Letters.CR))) {
								endColumn();

								endRecord();
							} else {
								dataBuffer.LineStart = dataBuffer.Position + 1;
							}

							lastLetter = currentLetter;
						} else if (userSettings.UseComments && columnsCount == 0
								&& currentLetter == userSettings.Comment) {
							// encountered a comment character at the beginning of
							// the line so just ignore the rest of the line

							lastLetter = currentLetter;

							skipLine();
						} else if (userSettings.TrimWhitespace
								&& (currentLetter == Letters.SPACE || currentLetter == Letters.TAB)) {
							// do nothing, this will trim leading whitespace
							// for both text qualified columns and non

							startedColumn = true;
							dataBuffer.ColumnStart = dataBuffer.Position + 1;
						} else {
							// since the letter wasn't a special letter, this
							// will be the first letter of our current column

							startedColumn = true;
							dataBuffer.ColumnStart = dataBuffer.Position;
							boolean lastLetterWasBackslash = false;
							boolean readingComplexEscape = false;
							int escape = ComplexEscape.UNICODE;
							int escapeLength = 0;
							char escapeValue = (char) 0;

							boolean firstLoop = true;

							do {
								if (!firstLoop
										&& dataBuffer.Position == dataBuffer.Count) {
									checkDataLength();
								} else {
									if (!firstLoop) {
										// grab the current letter as a char
										currentLetter = dataBuffer.Buffer[dataBuffer.Position];
									}

									if (!userSettings.UseTextQualifier
											&& userSettings.EscapeMode == ESCAPE_MODE_BACKSLASH
											&& currentLetter == Letters.BACKSLASH) {
										if (lastLetterWasBackslash) {
											lastLetterWasBackslash = false;
										} else {
											updateCurrentValue();
											lastLetterWasBackslash = true;
										}
									} else if (readingComplexEscape) {
										escapeLength++;

										switch (escape) {
										case ComplexEscape.UNICODE:
											escapeValue *= (char) 16;
											escapeValue += hexToDec(currentLetter);

											if (escapeLength == 4) {
												readingComplexEscape = false;
											}

											break;
										case ComplexEscape.OCTAL:
											escapeValue *= (char) 8;
											escapeValue += (char) (currentLetter - '0');

											if (escapeLength == 3) {
												readingComplexEscape = false;
											}

											break;
										case ComplexEscape.DECIMAL:
											escapeValue *= (char) 10;
											escapeValue += (char) (currentLetter - '0');

											if (escapeLength == 3) {
												readingComplexEscape = false;
											}

											break;
										case ComplexEscape.HEX:
											escapeValue *= (char) 16;
											escapeValue += hexToDec(currentLetter);

											if (escapeLength == 2) {
												readingComplexEscape = false;
											}

											break;
										}

										if (!readingComplexEscape) {
											appendLetter(escapeValue);
										} else {
											dataBuffer.ColumnStart = dataBuffer.Position + 1;
										}
									} else if (userSettings.EscapeMode == ESCAPE_MODE_BACKSLASH
											&& lastLetterWasBackslash) {
										switch (currentLetter) {
										case 'n':
											appendLetter(Letters.LF);
											break;
										case 'r':
											appendLetter(Letters.CR);
											break;
										case 't':
											appendLetter(Letters.TAB);
											break;
										case 'b':
											appendLetter(Letters.BACKSPACE);
											break;
										case 'f':
											appendLetter(Letters.FORM_FEED);
											break;
										case 'e':
											appendLetter(Letters.ESCAPE);
											break;
										case 'v':
											appendLetter(Letters.VERTICAL_TAB);
											break;
										case 'a':
											appendLetter(Letters.ALERT);
											break;
										case '0':
										case '1':
										case '2':
										case '3':
										case '4':
										case '5':
										case '6':
										case '7':
											escape = ComplexEscape.OCTAL;
											readingComplexEscape = true;
											escapeLength = 1;
											escapeValue = (char) (currentLetter - '0');
											dataBuffer.ColumnStart = dataBuffer.Position + 1;
											break;
										case 'u':
										case 'x':
										case 'o':
										case 'd':
										case 'U':
										case 'X':
										case 'O':
										case 'D':
											switch (currentLetter) {
											case 'u':
											case 'U':
												escape = ComplexEscape.UNICODE;
												break;
											case 'x':
											case 'X':
												escape = ComplexEscape.HEX;
												break;
											case 'o':
											case 'O':
												escape = ComplexEscape.OCTAL;
												break;
											case 'd':
											case 'D':
												escape = ComplexEscape.DECIMAL;
												break;
											}

											readingComplexEscape = true;
											escapeLength = 0;
											escapeValue = (char) 0;
											dataBuffer.ColumnStart = dataBuffer.Position + 1;

											break;
										default:
											break;
										}

										lastLetterWasBackslash = false;
									} else {
										if (currentLetter == userSettings.Delimiter) {
											endColumn();
										} else if ((!useCustomRecordDelimiter && (currentLetter == Letters.CR || currentLetter == Letters.LF))
												|| (useCustomRecordDelimiter && currentLetter == userSettings.RecordDelimiter)) {
											endColumn();

											endRecord();
										}
									}

									// keep track of the last letter because we need
									// it for several key decisions

									lastLetter = currentLetter;
									firstLoop = false;

									if (startedColumn) {
										dataBuffer.Position++;

										if (userSettings.SafetySwitch
												&& dataBuffer.Position
														- dataBuffer.ColumnStart
														+ columnBuffer.Position > 100000) {
											close();

											throw new IOException(
													"Maximum column length of 100,000 exceeded in column "
															+ NumberFormat
																	.getIntegerInstance()
																	.format(
																			columnsCount)
															+ " in record "
															+ NumberFormat
																	.getIntegerInstance()
																	.format(
																			currentRecord)
															+ ". Set the SafetySwitch property to false"
															+ " if you're expecting column lengths greater than 100,000 characters to"
															+ " avoid this error.");
										}
									}
								} // end else
							} while (hasMoreData && startedColumn);
						}

						if (hasMoreData) {
							dataBuffer.Position++;
						}
					} // end else
				} while (hasMoreData && !hasReadNextLine);

				// check to see if we hit the end of the file
				// without processing the current record

				if (startedColumn || lastLetter == userSettings.Delimiter) {
					endColumn();

					endRecord();
				}
			}

			if (userSettings.CaptureRawRecord) {
				if (hasMoreData) {
					if (rawBuffer.Position == 0) {
						rawRecord = new String(dataBuffer.Buffer,
								dataBuffer.LineStart, dataBuffer.Position
										- dataBuffer.LineStart - 1);
					} else {
						rawRecord = new String(rawBuffer.Buffer, 0,
								rawBuffer.Position)
								+ new String(dataBuffer.Buffer,
										dataBuffer.LineStart, dataBuffer.Position
												- dataBuffer.LineStart - 1);
					}
				} else {
					// for hasMoreData to ever be false, all data would have had to
					// have been
					// copied to the raw buffer
					rawRecord = new String(rawBuffer.Buffer, 0, rawBuffer.Position);
				}
			} else {
				rawRecord = "";
			}

			return hasReadNextLine;
		}

		/**
		 * @exception IOException
		 *                Thrown if an error occurs while reading data from the
		 *                source stream.
		 */
		private void checkDataLength() throws IOException {
			if (!initialized) {
				if (fileName != null) {
					inputStream = new BufferedReader(new InputStreamReader(
							new FileInputStream(fileName), charset),
							StaticSettings.MAX_FILE_BUFFER_SIZE);
				}

				charset = null;
				initialized = true;
			}

			updateCurrentValue();

			if (userSettings.CaptureRawRecord && dataBuffer.Count > 0) {
				if (rawBuffer.Buffer.length - rawBuffer.Position < dataBuffer.Count
						- dataBuffer.LineStart) {
					int newLength = rawBuffer.Buffer.length
							+ Math.max(dataBuffer.Count - dataBuffer.LineStart,
									rawBuffer.Buffer.length);

					char[] holder = new char[newLength];

					System.arraycopy(rawBuffer.Buffer, 0, holder, 0,
							rawBuffer.Position);

					rawBuffer.Buffer = holder;
				}

				System.arraycopy(dataBuffer.Buffer, dataBuffer.LineStart,
						rawBuffer.Buffer, rawBuffer.Position, dataBuffer.Count
								- dataBuffer.LineStart);

				rawBuffer.Position += dataBuffer.Count - dataBuffer.LineStart;
			}

			try {
				dataBuffer.Count = inputStream.read(dataBuffer.Buffer, 0,
						dataBuffer.Buffer.length);
			} catch (IOException ex) {
				close();

				throw ex;
			}

			// if no more data could be found, set flag stating that
			// the end of the data was found

			if (dataBuffer.Count == -1) {
				hasMoreData = false;
			}

			dataBuffer.Position = 0;
			dataBuffer.LineStart = 0;
			dataBuffer.ColumnStart = 0;
		}

		/**
		 * Read the first record of data as column headers.
		 * 
		 * @return Whether the header record was successfully read or not.
		 * @exception IOException
		 *                Thrown if an error occurs while reading data from the
		 *                source stream.
		 */
		public boolean readHeaders() throws IOException {
			boolean result = readRecord();

			// copy the header data from the column array
			// to the header string array

			headersHolder.Length = columnsCount;

			headersHolder.Headers = new String[columnsCount];

			for (int i = 0; i < headersHolder.Length; i++) {
				String columnValue = get(i);

				headersHolder.Headers[i] = columnValue;

				// if there are duplicate header names, we will save the last one
				headersHolder.IndexByName.put(columnValue, new Integer(i));
			}

			if (result) {
				currentRecord--;
			}

			columnsCount = 0;

			return result;
		}

		/**
		 * Returns the column header value for a given column index.
		 * 
		 * @param columnIndex
		 *            The index of the header column being requested.
		 * @return The value of the column header at the given column index.
		 * @exception IOException
		 *                Thrown if this object has already been closed.
		 */
		public String getHeader(int columnIndex) throws IOException {
			checkClosed();

			// check to see if we have read the header record yet

			// check to see if the column index is within the bounds
			// of our header array

			if (columnIndex > -1 && columnIndex < headersHolder.Length) {
				// return the processed header data for this column

				return headersHolder.Headers[columnIndex];
			} else {
				return "";
			}
		}

		public boolean isQualified(int columnIndex) throws IOException {
			checkClosed();

			if (columnIndex < columnsCount && columnIndex > -1) {
				return isQualified[columnIndex];
			} else {
				return false;
			}
		}

		/**
		 * @exception IOException
		 *                Thrown if a very rare extreme exception occurs during
		 *                parsing, normally resulting from improper data format.
		 */
		private void endColumn() throws IOException {
			String currentValue = "";

			// must be called before setting startedColumn = false
			if (startedColumn) {
				if (columnBuffer.Position == 0) {
					if (dataBuffer.ColumnStart < dataBuffer.Position) {
						int lastLetter = dataBuffer.Position - 1;

						if (userSettings.TrimWhitespace && !startedWithQualifier) {
							while (lastLetter >= dataBuffer.ColumnStart
									&& (dataBuffer.Buffer[lastLetter] == Letters.SPACE || dataBuffer.Buffer[lastLetter] == Letters.TAB)) {
								lastLetter--;
							}
						}

						currentValue = new String(dataBuffer.Buffer,
								dataBuffer.ColumnStart, lastLetter
										- dataBuffer.ColumnStart + 1);
					}
				} else {
					updateCurrentValue();

					int lastLetter = columnBuffer.Position - 1;

					if (userSettings.TrimWhitespace && !startedWithQualifier) {
						while (lastLetter >= 0
								&& (columnBuffer.Buffer[lastLetter] == Letters.SPACE || columnBuffer.Buffer[lastLetter] == Letters.SPACE)) {
							lastLetter--;
						}
					}

					currentValue = new String(columnBuffer.Buffer, 0,
							lastLetter + 1);
				}
			}

			columnBuffer.Position = 0;

			startedColumn = false;

			if (columnsCount >= 100000 && userSettings.SafetySwitch) {
				close();

				throw new IOException(
						"Maximum column count of 100,000 exceeded in record "
								+ NumberFormat.getIntegerInstance().format(
										currentRecord)
								+ ". Set the SafetySwitch property to false"
								+ " if you're expecting more than 100,000 columns per record to"
								+ " avoid this error.");
			}

			// check to see if our current holder array for
			// column chunks is still big enough to handle another
			// column chunk

			if (columnsCount == values.length) {
				// holder array needs to grow to be able to hold another column
				int newLength = values.length * 2;

				String[] holder = new String[newLength];

				System.arraycopy(values, 0, holder, 0, values.length);

				values = holder;

				boolean[] qualifiedHolder = new boolean[newLength];

				System.arraycopy(isQualified, 0, qualifiedHolder, 0,
						isQualified.length);

				isQualified = qualifiedHolder;
			}

			values[columnsCount] = currentValue;

			isQualified[columnsCount] = startedWithQualifier;

			currentValue = "";

			columnsCount++;
		}

		private void appendLetter(char letter) {
			if (columnBuffer.Position == columnBuffer.Buffer.length) {
				int newLength = columnBuffer.Buffer.length * 2;

				char[] holder = new char[newLength];

				System.arraycopy(columnBuffer.Buffer, 0, holder, 0,
						columnBuffer.Position);

				columnBuffer.Buffer = holder;
			}
			columnBuffer.Buffer[columnBuffer.Position++] = letter;
			dataBuffer.ColumnStart = dataBuffer.Position + 1;
		}

		private void updateCurrentValue() {
			if (startedColumn && dataBuffer.ColumnStart < dataBuffer.Position) {
				if (columnBuffer.Buffer.length - columnBuffer.Position < dataBuffer.Position
						- dataBuffer.ColumnStart) {
					int newLength = columnBuffer.Buffer.length
							+ Math.max(
									dataBuffer.Position - dataBuffer.ColumnStart,
									columnBuffer.Buffer.length);

					char[] holder = new char[newLength];

					System.arraycopy(columnBuffer.Buffer, 0, holder, 0,
							columnBuffer.Position);

					columnBuffer.Buffer = holder;
				}

				System.arraycopy(dataBuffer.Buffer, dataBuffer.ColumnStart,
						columnBuffer.Buffer, columnBuffer.Position,
						dataBuffer.Position - dataBuffer.ColumnStart);

				columnBuffer.Position += dataBuffer.Position
						- dataBuffer.ColumnStart;
			}

			dataBuffer.ColumnStart = dataBuffer.Position + 1;
		}

		/**
		 * @exception IOException
		 *                Thrown if an error occurs while reading data from the
		 *                source stream.
		 */
		private void endRecord() throws IOException {
			// this flag is used as a loop exit condition
			// during parsing

			hasReadNextLine = true;

			currentRecord++;
		}

		/**
		 * Gets the corresponding column index for a given column header name.
		 * 
		 * @param headerName
		 *            The header name of the column.
		 * @return The column index for the given column header name.&nbsp;Returns
		 *         -1 if not found.
		 * @exception IOException
		 *                Thrown if this object has already been closed.
		 */
		public int getIndex(String headerName) throws IOException {
			checkClosed();

			Object indexValue = headersHolder.IndexByName.get(headerName);

			if (indexValue != null) {
				return ((Integer) indexValue).intValue();
			} else {
				return -1;
			}
		}

		/**
		 * Skips the next record of data by parsing each column.&nbsp;Does not
		 * increment
		 * {@link com.csvreader.CsvReader#getCurrentRecord getCurrentRecord()}.
		 * 
		 * @return Whether another record was successfully skipped or not.
		 * @exception IOException
		 *                Thrown if an error occurs while reading data from the
		 *                source stream.
		 */
		public boolean skipRecord() throws IOException {
			checkClosed();

			boolean recordRead = false;

			if (hasMoreData) {
				recordRead = readRecord();

				if (recordRead) {
					currentRecord--;
				}
			}

			return recordRead;
		}

		/**
		 * Skips the next line of data using the standard end of line characters and
		 * does not do any column delimited parsing.
		 * 
		 * @return Whether a line was successfully skipped or not.
		 * @exception IOException
		 *                Thrown if an error occurs while reading data from the
		 *                source stream.
		 */
		public boolean skipLine() throws IOException {
			checkClosed();

			// clear public column values for current line

			columnsCount = 0;

			boolean skippedLine = false;

			if (hasMoreData) {
				boolean foundEol = false;

				do {
					if (dataBuffer.Position == dataBuffer.Count) {
						checkDataLength();
					} else {
						skippedLine = true;

						// grab the current letter as a char

						char currentLetter = dataBuffer.Buffer[dataBuffer.Position];

						if (currentLetter == Letters.CR
								|| currentLetter == Letters.LF) {
							foundEol = true;
						}

						// keep track of the last letter because we need
						// it for several key decisions

						lastLetter = currentLetter;

						if (!foundEol) {
							dataBuffer.Position++;
						}

					} // end else
				} while (hasMoreData && !foundEol);

				columnBuffer.Position = 0;

				dataBuffer.LineStart = dataBuffer.Position + 1;
			}

			rawBuffer.Position = 0;
			rawRecord = "";

			return skippedLine;
		}

		/**
		 * Closes and releases all related resources.
		 */
		public void close() {
			if (!closed) {
				close(true);

				closed = true;
			}
		}

		/**
		 * 
		 */
		private void close(boolean closing) {
			if (!closed) {
				if (closing) {
					charset = null;
					headersHolder.Headers = null;
					headersHolder.IndexByName = null;
					dataBuffer.Buffer = null;
					columnBuffer.Buffer = null;
					rawBuffer.Buffer = null;
				}

				try {
					if (initialized) {
						inputStream.close();
					}
				} catch (Exception e) {
					// just eat the exception
				}

				inputStream = null;

				closed = true;
			}
		}

		/**
		 * @exception IOException
		 *                Thrown if this object has already been closed.
		 */
		private void checkClosed() throws IOException {
			if (closed) {
				throw new IOException(
						"This instance of the CsvReader class has already been closed.");
			}
		}

		/**
		 * 
		 */
		protected void finalize() {
			close(false);
		}

		private class ComplexEscape {
			private static final int UNICODE = 1;

			private static final int OCTAL = 2;

			private static final int DECIMAL = 3;

			private static final int HEX = 4;
		}

		private static char hexToDec(char hex) {
			char result;

			if (hex >= 'a') {
				result = (char) (hex - 'a' + 10);
			} else if (hex >= 'A') {
				result = (char) (hex - 'A' + 10);
			} else {
				result = (char) (hex - '0');
			}

			return result;
		}

		private class DataBuffer {
			public char[] Buffer;

			public int Position;

			// / <summary>
			// / How much usable data has been read into the stream,
			// / which will not always be as long as Buffer.Length.
			// / </summary>
			public int Count;

			// / <summary>
			// / The position of the cursor in the buffer when the
			// / current column was started or the last time data
			// / was moved out to the column buffer.
			// / </summary>
			public int ColumnStart;

			public int LineStart;

			public DataBuffer() {
				Buffer = new char[StaticSettings.MAX_BUFFER_SIZE];
				Position = 0;
				Count = 0;
				ColumnStart = 0;
				LineStart = 0;
			}
		}

		private class ColumnBuffer {
			public char[] Buffer;

			public int Position;

			public ColumnBuffer() {
				Buffer = new char[StaticSettings.INITIAL_COLUMN_BUFFER_SIZE];
				Position = 0;
			}
		}

		private class RawRecordBuffer {
			public char[] Buffer;

			public int Position;

			public RawRecordBuffer() {
				Buffer = new char[StaticSettings.INITIAL_COLUMN_BUFFER_SIZE
						* StaticSettings.INITIAL_COLUMN_COUNT];
				Position = 0;
			}
		}

		private class Letters {
			public static final char LF = '\n';

			public static final char CR = '\r';

			public static final char QUOTE = '"';

			public static final char COMMA = ',';

			public static final char SPACE = ' ';

			public static final char TAB = '\t';

			public static final char POUND = '#';

			public static final char BACKSLASH = '\\';

			public static final char NULL = '\0';

			public static final char BACKSPACE = '\b';

			public static final char FORM_FEED = '\f';

			public static final char ESCAPE = '\u001B'; // ASCII/ANSI escape

			public static final char VERTICAL_TAB = '\u000B';

			public static final char ALERT = '\u0007';
		}

		private class UserSettings {
			// having these as publicly accessible members will prevent
			// the overhead of the method call that exists on properties
			public boolean CaseSensitive;

			public char TextQualifier;

			public boolean TrimWhitespace;

			public boolean UseTextQualifier;

			public char Delimiter;

			public char RecordDelimiter;

			public char Comment;

			public boolean UseComments;

			public int EscapeMode;

			public boolean SafetySwitch;

			public boolean SkipEmptyRecords;

			public boolean CaptureRawRecord;

			public UserSettings() {
				CaseSensitive = true;
				TextQualifier = Letters.QUOTE;
				TrimWhitespace = true;
				UseTextQualifier = true;
				Delimiter = Letters.COMMA;
				RecordDelimiter = Letters.NULL;
				Comment = Letters.POUND;
				UseComments = false;
				EscapeMode = CsvReader.ESCAPE_MODE_DOUBLED;
				SafetySwitch = true;
				SkipEmptyRecords = true;
				CaptureRawRecord = true;
			}
		}

		private class HeadersHolder {
			public String[] Headers;

			public int Length;

			public HashMap IndexByName;

			public HeadersHolder() {
				Headers = null;
				Length = 0;
				IndexByName = new HashMap();
			}
		}

		private class StaticSettings {
			// these are static instead of final so they can be changed in unit test
			// isn't visible outside this class and is only accessed once during
			// CsvReader construction
			public static final int MAX_BUFFER_SIZE = 1024;

			public static final int MAX_FILE_BUFFER_SIZE = 4 * 1024;

			public static final int INITIAL_COLUMN_COUNT = 10;

			public static final int INITIAL_COLUMN_BUFFER_SIZE = 50;
		}
	}
	
	/**
	 * A stream based writer for writing delimited text data to a file or a stream.
	 */
	public static class CsvWriter {
		private Writer outputStream = null;
		
		private String fileName = null;

		private boolean firstColumn = true;

		private boolean useCustomRecordDelimiter = false;

		private Charset charset = null;

		// this holds all the values for switches that the user is allowed to set
		private UserSettings userSettings = new UserSettings();

		private boolean initialized = false;

		private boolean closed = false;
		
		private String systemRecordDelimiter = System.getProperty("line.separator");

		/**
		 * Double up the text qualifier to represent an occurrence of the text
		 * qualifier.
		 */
		public static final int ESCAPE_MODE_DOUBLED = 1;

		/**
		 * Use a backslash character before the text qualifier to represent an
		 * occurrence of the text qualifier.
		 */
		public static final int ESCAPE_MODE_BACKSLASH = 2;

		/**
		 * Creates a {@link com.csvreader.CsvWriter CsvWriter} object using a file
		 * as the data destination.
		 * 
		 * @param fileName
		 *            The path to the file to output the data.
		 * @param delimiter
		 *            The character to use as the column delimiter.
		 * @param charset
		 *            The {@link java.nio.charset.Charset Charset} to use while
		 *            writing the data.
		 */
		public CsvWriter(String fileName, char delimiter, Charset charset) {
			if (fileName == null) {
				throw new IllegalArgumentException("Parameter fileName can not be null.");
			}

			if (charset == null) {
				throw new IllegalArgumentException("Parameter charset can not be null.");
			}

			this.fileName = fileName;
			userSettings.Delimiter = delimiter;
			this.charset = charset;
		}

		/**
		 * Creates a {@link com.csvreader.CsvWriter CsvWriter} object using a file
		 * as the data destination.&nbsp;Uses a comma as the column delimiter and
		 * ISO-8859-1 as the {@link java.nio.charset.Charset Charset}.
		 * 
		 * @param fileName
		 *            The path to the file to output the data.
		 */
		public CsvWriter(String fileName) {
			this(fileName, Letters.COMMA, Charset.forName("ISO-8859-1"));
		}

		/**
		 * Creates a {@link com.csvreader.CsvWriter CsvWriter} object using a Writer
		 * to write data to.
		 * 
		 * @param outputStream
		 *            The stream to write the column delimited data to.
		 * @param delimiter
		 *            The character to use as the column delimiter.
		 */
		public CsvWriter(Writer outputStream, char delimiter) {
			if (outputStream == null) {
				throw new IllegalArgumentException("Parameter outputStream can not be null.");
			}

			this.outputStream = outputStream;
			userSettings.Delimiter = delimiter;
			initialized = true;
		}

		/**
		 * Creates a {@link com.csvreader.CsvWriter CsvWriter} object using an
		 * OutputStream to write data to.
		 * 
		 * @param outputStream
		 *            The stream to write the column delimited data to.
		 * @param delimiter
		 *            The character to use as the column delimiter.
		 * @param charset
		 *            The {@link java.nio.charset.Charset Charset} to use while
		 *            writing the data.
		 */
		public CsvWriter(OutputStream outputStream, char delimiter, Charset charset) {
			this(new OutputStreamWriter(outputStream, charset), delimiter);
		}

		/**
		 * Gets the character being used as the column delimiter.
		 * 
		 * @return The character being used as the column delimiter.
		 */
		public char getDelimiter() {
			return userSettings.Delimiter;
		}

		/**
		 * Sets the character to use as the column delimiter.
		 * 
		 * @param delimiter
		 *            The character to use as the column delimiter.
		 */
		public void setDelimiter(char delimiter) {
			userSettings.Delimiter = delimiter;
		}

		public char getRecordDelimiter() {
			return userSettings.RecordDelimiter;
		}

		/**
		 * Sets the character to use as the record delimiter.
		 * 
		 * @param recordDelimiter
		 *            The character to use as the record delimiter. Default is
		 *            combination of standard end of line characters for Windows,
		 *            Unix, or Mac.
		 */
		public void setRecordDelimiter(char recordDelimiter) {
			useCustomRecordDelimiter = true;
			userSettings.RecordDelimiter = recordDelimiter;
		}

		/**
		 * Gets the character to use as a text qualifier in the data.
		 * 
		 * @return The character to use as a text qualifier in the data.
		 */
		public char getTextQualifier() {
			return userSettings.TextQualifier;
		}

		/**
		 * Sets the character to use as a text qualifier in the data.
		 * 
		 * @param textQualifier
		 *            The character to use as a text qualifier in the data.
		 */
		public void setTextQualifier(char textQualifier) {
			userSettings.TextQualifier = textQualifier;
		}

		/**
		 * Whether text qualifiers will be used while writing data or not.
		 * 
		 * @return Whether text qualifiers will be used while writing data or not.
		 */
		public boolean getUseTextQualifier() {
			return userSettings.UseTextQualifier;
		}

		/**
		 * Sets whether text qualifiers will be used while writing data or not.
		 * 
		 * @param useTextQualifier
		 *            Whether to use a text qualifier while writing data or not.
		 */
		public void setUseTextQualifier(boolean useTextQualifier) {
			userSettings.UseTextQualifier = useTextQualifier;
		}

		public int getEscapeMode() {
			return userSettings.EscapeMode;
		}

		public void setEscapeMode(int escapeMode) {
			userSettings.EscapeMode = escapeMode;
		}

		public void setComment(char comment) {
			userSettings.Comment = comment;
		}

		public char getComment() {
			return userSettings.Comment;
		}

		/**
		 * Whether fields will be surrounded by the text qualifier even if the
		 * qualifier is not necessarily needed to escape this field.
		 * 
		 * @return Whether fields will be forced to be qualified or not.
		 */
		public boolean getForceQualifier() {
			return userSettings.ForceQualifier;
		}

		/**
		 * Use this to force all fields to be surrounded by the text qualifier even
		 * if the qualifier is not necessarily needed to escape this field. Default
		 * is false.
		 * 
		 * @param forceQualifier
		 *            Whether to force the fields to be qualified or not.
		 */
		public void setForceQualifier(boolean forceQualifier) {
			userSettings.ForceQualifier = forceQualifier;
		}

		/**
		 * Writes another column of data to this record.
		 * 
		 * @param content
		 *            The data for the new column.
		 * @param preserveSpaces
		 *            Whether to preserve leading and trailing whitespace in this
		 *            column of data.
		 * @exception IOException
		 *                Thrown if an error occurs while writing data to the
		 *                destination stream.
		 */
		public void write(String content, boolean preserveSpaces)
				throws IOException {
			checkClosed();

			checkInit();

			if (content == null) {
				content = "";
			}

			if (!firstColumn) {
				outputStream.write(userSettings.Delimiter);
			}

			boolean textQualify = userSettings.ForceQualifier;

			if (!preserveSpaces && content.length() > 0) {
				content = content.trim();
			}

			if (!textQualify
					&& userSettings.UseTextQualifier
					&& (content.indexOf(userSettings.TextQualifier) > -1
							|| content.indexOf(userSettings.Delimiter) > -1
							|| (!useCustomRecordDelimiter && (content
									.indexOf(Letters.LF) > -1 || content
									.indexOf(Letters.CR) > -1))
							|| (useCustomRecordDelimiter && content
									.indexOf(userSettings.RecordDelimiter) > -1)
							|| (firstColumn && content.length() > 0 && content
									.charAt(0) == userSettings.Comment) ||
					// check for empty first column, which if on its own line must
					// be qualified or the line will be skipped
					(firstColumn && content.length() == 0))) {
				textQualify = true;
			}

			if (userSettings.UseTextQualifier && !textQualify
					&& content.length() > 0 && preserveSpaces) {
				char firstLetter = content.charAt(0);

				if (firstLetter == Letters.SPACE || firstLetter == Letters.TAB) {
					textQualify = true;
				}

				if (!textQualify && content.length() > 1) {
					char lastLetter = content.charAt(content.length() - 1);

					if (lastLetter == Letters.SPACE || lastLetter == Letters.TAB) {
						textQualify = true;
					}
				}
			}

			if (textQualify) {
				outputStream.write(userSettings.TextQualifier);

				if (userSettings.EscapeMode == ESCAPE_MODE_BACKSLASH) {
					content = replace(content, "" + Letters.BACKSLASH, ""
							+ Letters.BACKSLASH + Letters.BACKSLASH);
					content = replace(content, "" + userSettings.TextQualifier, ""
							+ Letters.BACKSLASH + userSettings.TextQualifier);
				} else {
					content = replace(content, "" + userSettings.TextQualifier, ""
							+ userSettings.TextQualifier
							+ userSettings.TextQualifier);
				}
			} else if (userSettings.EscapeMode == ESCAPE_MODE_BACKSLASH) {
				content = replace(content, "" + Letters.BACKSLASH, ""
						+ Letters.BACKSLASH + Letters.BACKSLASH);
				content = replace(content, "" + userSettings.Delimiter, ""
						+ Letters.BACKSLASH + userSettings.Delimiter);

				if (useCustomRecordDelimiter) {
					content = replace(content, "" + userSettings.RecordDelimiter,
							"" + Letters.BACKSLASH + userSettings.RecordDelimiter);
				} else {
					content = replace(content, "" + Letters.CR, ""
							+ Letters.BACKSLASH + Letters.CR);
					content = replace(content, "" + Letters.LF, ""
							+ Letters.BACKSLASH + Letters.LF);
				}

				if (firstColumn && content.length() > 0
						&& content.charAt(0) == userSettings.Comment) {
					if (content.length() > 1) {
						content = "" + Letters.BACKSLASH + userSettings.Comment
								+ content.substring(1);
					} else {
						content = "" + Letters.BACKSLASH + userSettings.Comment;
					}
				}
			}

			outputStream.write(content);

			if (textQualify) {
				outputStream.write(userSettings.TextQualifier);
			}

			firstColumn = false;
		}

		/**
		 * Writes another column of data to this record.&nbsp;Does not preserve
		 * leading and trailing whitespace in this column of data.
		 * 
		 * @param content
		 *            The data for the new column.
		 * @exception IOException
		 *                Thrown if an error occurs while writing data to the
		 *                destination stream.
		 */
		public void write(String content) throws IOException {
			write(content, false);
		}

		public void writeComment(String commentText) throws IOException {
			checkClosed();

			checkInit();

			outputStream.write(userSettings.Comment);

			outputStream.write(commentText);

			if (useCustomRecordDelimiter) {
				outputStream.write(userSettings.RecordDelimiter);
			} else {
				outputStream.write(systemRecordDelimiter);
			}
			
			firstColumn = true;
		}

		/**
		 * Writes a new record using the passed in array of values.
		 * 
		 * @param values
		 *            Values to be written.
		 * 
		 * @param preserveSpaces
		 *            Whether to preserver leading and trailing spaces in columns
		 *            while writing out to the record or not.
		 * 
		 * @throws IOException
		 *             Thrown if an error occurs while writing data to the
		 *             destination stream.
		 */
		public void writeRecord(String[] values, boolean preserveSpaces)
				throws IOException {
			if (values != null && values.length > 0) {
				for (int i = 0; i < values.length; i++) {
					write(values[i], preserveSpaces);
				}

				endRecord();
			}
		}

		/**
		 * Writes a new record using the passed in array of values.
		 * 
		 * @param values
		 *            Values to be written.
		 * 
		 * @throws IOException
		 *             Thrown if an error occurs while writing data to the
		 *             destination stream.
		 */
		public void writeRecord(String[] values) throws IOException {
			writeRecord(values, false);
		}

		/**
		 * Ends the current record by sending the record delimiter.
		 * 
		 * @exception IOException
		 *                Thrown if an error occurs while writing data to the
		 *                destination stream.
		 */
		public void endRecord() throws IOException {
			checkClosed();

			checkInit();

			if (useCustomRecordDelimiter) {
				outputStream.write(userSettings.RecordDelimiter);
			} else {
				outputStream.write(systemRecordDelimiter);
			}

			firstColumn = true;
		}

		/**
		 * 
		 */
		private void checkInit() throws IOException {
			if (!initialized) {
				if (fileName != null) {
					outputStream = new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream(fileName), charset));
				}

				initialized = true;
			}
		}

		/**
		 * Clears all buffers for the current writer and causes any buffered data to
		 * be written to the underlying device.
		 * @exception IOException
		 *                Thrown if an error occurs while writing data to the
		 *                destination stream. 
		 */
		public void flush() throws IOException {
			outputStream.flush();
		}

		/**
		 * Closes and releases all related resources.
		 */
		public void close() {
			if (!closed) {
				close(true);

				closed = true;
			}
		}

		/**
		 * 
		 */
		private void close(boolean closing) {
			if (!closed) {
				if (closing) {
					charset = null;
				}

				try {
					if (initialized) {
						outputStream.close();
					}
				} catch (Exception e) {
					// just eat the exception
				}

				outputStream = null;

				closed = true;
			}
		}

		/**
		 * 
		 */
		private void checkClosed() throws IOException {
			if (closed) {
				throw new IOException(
				"This instance of the CsvWriter class has already been closed.");
			}
		}

		/**
		 * 
		 */
		protected void finalize() {
			close(false);
		}

		private class Letters {
			public static final char LF = '\n';

			public static final char CR = '\r';

			public static final char QUOTE = '"';

			public static final char COMMA = ',';

			public static final char SPACE = ' ';

			public static final char TAB = '\t';

			public static final char POUND = '#';

			public static final char BACKSLASH = '\\';

			public static final char NULL = '\0';
		}

		private class UserSettings {
			// having these as publicly accessible members will prevent
			// the overhead of the method call that exists on properties
			public char TextQualifier;

			public boolean UseTextQualifier;

			public char Delimiter;

			public char RecordDelimiter;

			public char Comment;

			public int EscapeMode;

			public boolean ForceQualifier;

			public UserSettings() {
				TextQualifier = Letters.QUOTE;
				UseTextQualifier = true;
				Delimiter = Letters.COMMA;
				RecordDelimiter = Letters.NULL;
				Comment = Letters.POUND;
				EscapeMode = ESCAPE_MODE_DOUBLED;
				ForceQualifier = false;
			}
		}

		public static String replace(String original, String pattern, String replace) {
			final int len = pattern.length();
			int found = original.indexOf(pattern);

			if (found > -1) {
				StringBuffer sb = new StringBuffer();
				int start = 0;

				while (found != -1) {
					sb.append(original.substring(start, found));
					sb.append(replace);
					start = found + len;
					found = original.indexOf(pattern, start);
				}

				sb.append(original.substring(start));

				return sb.toString();
			} else {
				return original;
			}
		}
	}
	
//	public static void main(String [] args){
//		
//		Workbook wb=new WorkbookImpl();
//		try {
//			wb.open("C:\\Users\\Administrator\\Downloads\\ABC (4).xls");
//			Worksheet ws=wb.getWorksheets().getSheet(0);
//			System.out.println("{"+ws.getCells().getCell(1, 0).getStringValue()+"}");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
}
