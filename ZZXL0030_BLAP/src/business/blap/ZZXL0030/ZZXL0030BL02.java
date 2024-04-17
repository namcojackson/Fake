package business.blap.ZZXL0030;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.ZZXL0030.common.ZZXL0030CommonLogic;
import business.blap.ZZXL0030.constant.ZZXL0030Constant;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class ZZXL0030BL02 extends S21BusinessHandler implements ZZXL0030Constant {

	private int todayYear;
	private int todayMonth;
	private int thisYear;
	private int thisMonth;
	private int nextYear;
	private int nextMonth;	
	
	private GregorianCalendar calThisMonth;
	private GregorianCalendar calNextMonth;
	private String thisMonthString;
	private String nextMonthString;
	private String thisMonthFirstDayString;
	private String nextMonthLastDayString;
	private DecimalFormat dfMonth = new DecimalFormat("00");
	private DecimalFormat dfYear = new DecimalFormat("0000");
	
	/**
     * Do process.
     * @param cMsg the c msg
     * @param sMsg the s msg
     * @see parts.ejbcommon.EZDBusinessHandler#doProcess(parts.common.EZDCMsg,
     * parts.common.EZDSMsg)
     */
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        
        super.preDoProcess(cMsg, sMsg);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            if ("ZZXL0030_INIT".equals(screenAplID)) {
                doProcess_ZZXL0030_INIT(cMsg, sMsg);
            } else if ("ZZXL0030Scrn00_Search".equals(screenAplID)) { 
                doProcess_ZZXL0030Scrn00_Search(cMsg, sMsg);
            } else if ("ZZXL0030Scrn00_Today".equals(screenAplID)) { 
                doProcess_ZZXL0030Scrn00_Today(cMsg, sMsg);                
            } else if ("ZZXL0030Scrn00_NextMonth".equals(screenAplID)) {
                doProcess_ZZXL0030Scrn00_NextMonth(cMsg, sMsg);                
            } else if ("ZZXL0030Scrn00_PrevMonth".equals(screenAplID)) {
                doProcess_ZZXL0030Scrn00_PrevMonth(cMsg, sMsg);         
            } else if ("ZZXL0030Scrn00_CheckWeekDaysA".equals(screenAplID)) {
                doProcess_ZZXL0030Scrn00_CheckWeekDay(cMsg, true);  
            } else if ("ZZXL0030Scrn00_CheckWeekDaysB".equals(screenAplID)) {
                doProcess_ZZXL0030Scrn00_CheckWeekDay(cMsg, false);                                  
            } else if ("ZZXL0030Scrn00_CMN_Return".equals(screenAplID)) {
            	doProcess_ZZXL0030Scrn00_Return(cMsg, sMsg);  
            } else if ("ZZXL0030Scrn00_Find".equals(screenAplID)) {
            	doProcess_ZZXL0030Scrn00_Find(cMsg, sMsg);                 	
            } else {                
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
            // +++++ [E N D] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }
    
    /**
     * Method name: doProcess_ZZXL0030_INIT
     * <dd>The method explanation: Business processing for Init.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_ZZXL0030_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
    	
    	ZZXL0030CMsg bizMsg = (ZZXL0030CMsg) cMsg; 

    	//  Get global company code of user profile for S21 user 
        if ( SYS_FLG.equalsIgnoreCase(SYS_FLG_S21)){
        	bizMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        }
    	
    	// Set up Calendar Type pulldown 
    	int hitCount = ZZXL0030Query.getInstance().getCalendarType(cMsg);
    	if (hitCount > 0) {
    		for(int i=0; i < bizMsg.C.getValidCount(); i++) {
    			bizMsg.calTpCd_DP.no(i).setValue(bizMsg.C.no(i).calTpCd_C.getValue());
    			bizMsg.calTpNm_DP.no(i).setValue(bizMsg.C.no(i).calTpNm_C.getValue());
    		}
    	}
    	
    	setupTodayDate(cMsg);
    	// doProcess_ZZXL0030Scrn00_Today(cMsg, sMsg);  
    }        

    /**
     * Method name: doProcess_ZZXL0030Scrn00_Search
     * <dd>The method explanation: Business processing for
     * Search.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_ZZXL0030Scrn00_Search(EZDCMsg cMsg, EZDSMsg sMsg) {
        
    	ZZXL0030CMsg bizMsg = (ZZXL0030CMsg) cMsg;  
    	ZZXL0030SMsg sesMsg = (ZZXL0030SMsg) sMsg;
        
        try {
            // Check Global Copmany Code is exist or not.
            if (!ZZXL0030CommonLogic.checkExistGlbCmpCd(bizMsg)) {
                bizMsg.glblCmpyCd.setErrorInfo(1, "ZZZM9006E", new String[] {"Global Company Code"});
                return;
            }
        	// Check if the deta is modified or not.
        	if (!bizMsg.xxLastBtnNm.getValue().equals(SEARCH_BUTTON) && !ZZXL0030CommonLogic.checkModifiedData(cMsg,sMsg, CHECK_BOTH_MONTHS)) {
        		bizMsg.setMessageInfo(DIRTYDATA_MSG);
        		bizMsg.xxLastBtnNm.setValue(SEARCH_BUTTON);
           		return ;
        	} else {
        		bizMsg.xxLastBtnNm.clear();
        	}
        	
        	// Set up data range for select.
        	setThisAndNextMonth(bizMsg.xxMthDt.getValue(), bizMsg.xxYrDt.getValue());
            sesMsg.effFromDt.setValue(thisMonthFirstDayString);
            sesMsg.effToDt.setValue(nextMonthLastDayString);
            sesMsg.calTpCd.setValue(bizMsg.calTpCd.getValue());
            
            // Clear A & B arraies.
            clearMonthBuffers(cMsg, sMsg);
	        
	        // Get 2 months data.
            ZZXL0030Query.getInstance().getCalendar(cMsg, sMsg);
            
            // Copy S Msg to C Msg
            copySMsgToCMsg(sMsg, cMsg);
            
            bizMsg.xxYrMth_01.setValue(thisMonthString);
            bizMsg.xxYrMth_02.setValue(nextMonthString);
            
        } catch (Exception e) {
            EZDDebugOutput.println(1,e.getMessage(), null);
        }
    }

    /**
     * Set up today's date from system time.
     * @param cMsg
     */
    private void setupTodayDate(EZDCMsg cMsg) {
    	
    	ZZXL0030CMsg bizMsg = (ZZXL0030CMsg) cMsg;  
    	
    	GregorianCalendar calToday = new GregorianCalendar();
    	todayYear = calToday.get(Calendar.YEAR);
    	todayMonth = calToday.get(Calendar.MONTH);
    	
        bizMsg.xxYrDt.setValue(dfYear.format(calToday.get(Calendar.YEAR)));
        bizMsg.xxMthDt.setValue(new Integer(calToday.get(Calendar.MONTH)).toString());   
    }
    
    /**
     * Get today's date from system date and search today's & next months.
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_ZZXL0030Scrn00_Today(EZDCMsg cMsg, EZDSMsg sMsg) {
        
    	ZZXL0030CMsg bizMsg = (ZZXL0030CMsg) cMsg;  
    	ZZXL0030SMsg sesMsg = (ZZXL0030SMsg) sMsg;
        
        try {
        	
        	// Check if the data is modified or not.
        	if (!bizMsg.xxLastBtnNm.getValue().equals(TODAY_BUTTON) && !ZZXL0030CommonLogic.checkModifiedData(cMsg,sMsg, CHECK_BOTH_MONTHS)) {
        		bizMsg.setMessageInfo(DIRTYDATA_MSG);
        		bizMsg.xxLastBtnNm.setValue(TODAY_BUTTON);
        		return ;
        	} else {
        		bizMsg.xxLastBtnNm.clear();
        	}
        	
        	// Set today's date 
        	setupTodayDate(cMsg);
        	
        	// Calculate this & next month's information
        	setThisAndNextMonth(todayMonth, todayYear);
        	
        	// Set up data range for select.
            sesMsg.effFromDt.setValue(thisMonthFirstDayString);
            sesMsg.effToDt.setValue(nextMonthLastDayString);
            
            // Clear A & B arraies.
            clearMonthBuffers(cMsg, sMsg);
	        
	        // get 2 months data.
	        ZZXL0030Query.getInstance().getCalendar(cMsg, sMsg);
	        
	        // Copy S Msg to C Msg
            copySMsgToCMsg(sMsg, cMsg);
            
            bizMsg.xxYrMth_01.setValue(thisMonthString);
            bizMsg.xxYrMth_02.setValue(nextMonthString);            	
            	
        } catch (Exception e) {
            EZDDebugOutput.println(1,e.getMessage(), null);
        }
    }    
    
    /**
     * Set the following global variables. 
     * @param month
     * @param year
     */
    private void setThisAndNextMonth(String month, String year) {
    	
    	thisMonth = (int)Integer.parseInt(month.trim());
    	thisYear = (int)Integer.parseInt(year.trim());
    	setThisAndNextMonth(thisMonth,thisYear);
    }

    /**
     * Set the following global variables.
     *   - thisMonthString, thisMonthFirstDayString, nextMonthString, nextMonthLastDayString
     *   - thisMonth, thisYear, nextMonth, nextYear.
     * @param month
     * @param year
     */
    private void setThisAndNextMonth(int month, int year) {
        
        thisMonth = month;
        thisYear = year;
        
        // 
        thisMonthString = year+dfMonth.format(thisMonth+1);
        thisMonthFirstDayString = thisMonthString+"01";
       	calThisMonth = new GregorianCalendar(thisYear, thisMonth, 1);
        
        // Calculate the next month's value & year       
        if (thisMonth == 11 ) {
        	nextMonth = 0;
        	nextYear = thisYear +1 ;
        } else {
        	nextMonth = thisMonth+1;
        	nextYear = thisYear;
        }
        calNextMonth = new GregorianCalendar(nextYear, nextMonth, 1);
        nextMonthString = new Integer(nextYear).toString()+dfMonth.format(nextMonth+1);
        nextMonthLastDayString = new Integer(nextYear).toString()+dfMonth.format(nextMonth+1)+dfMonth.format(calNextMonth.getActualMaximum(Calendar.DAY_OF_MONTH));
    }

    /**
     * Do process for next month.
     * @param cMsg the c msg
     * @param sMsg the s msg
     */
    private void doProcess_ZZXL0030Scrn00_NextMonth(EZDCMsg cMsg, EZDSMsg sMsg) {
    	
    	ZZXL0030CMsg bizMsg = (ZZXL0030CMsg) cMsg;  
    	ZZXL0030SMsg sesMsg = (ZZXL0030SMsg) sMsg;
        
        try {
        	
        	// Check if this month deta is modified or not.
        	if (!bizMsg.xxLastBtnNm.getValue().equals(NEXT_BUTTON) && !ZZXL0030CommonLogic.checkModifiedData(cMsg,sMsg, CHECK_THIS_MONTH)) {
        		bizMsg.setMessageInfo(DIRTYDATA_MSG);
        		bizMsg.xxLastBtnNm.setValue(NEXT_BUTTON);
        		return ;
        	} else {
        		bizMsg.xxLastBtnNm.clear();
        	}
        	
        	//  OK clear. Now, I have to copy Biz' B array to Biz's A and Ses A'
        	EZDMsg.copy(sesMsg.B, "B", sesMsg.A, "A");
        	EZDMsg.copy(bizMsg.B, "B", bizMsg.A, "A");
        	bizMsg.xxMthOfsNum_A.setValue(bizMsg.xxMthOfsNum_B.getValue());
        	bizMsg.xxMthLg_A.setValue(bizMsg.xxMthLg_B.getValue());
        	
        	// Search next, next month only
        	int nYear = (int)Integer.parseInt(bizMsg.xxYrMth_01.getValue().substring(0,4));
        	int nMonth = (int)Integer.parseInt(bizMsg.xxYrMth_01.getValue().substring(4,6));
        	Integer searchMonth = nMonth;
        	Integer searchYear =nYear;
        	
        	// Calculate next, next month's number and year.
        	if (nMonth == 11 || nMonth == 12 ) {
        		if (nMonth == 11) {
        			searchMonth = 1; // Jan search
        		} else {  //  December
        			searchMonth = 2; // Feb search
        		}
        		searchYear++; // next year.
        	} else {
        		searchMonth += 2;  // next, next month
        	}
        	
        	// next month display settings
           	if (nMonth == 12 ) {
        		nYear++;
        		nMonth = 1;
        	} else {
        		nMonth++;
        	}
        	// Search table and get its result into S Msg D array.
        	setThisAndNextMonth(nMonth-1, nYear);
        	
        	// Search from next, next Month's first day to end of next. next month. 
        	Calendar calSearchMonth = new GregorianCalendar(searchYear, searchMonth-1, 1);
            sesMsg.effFromDt.setValue(searchYear.toString()+dfMonth.format(searchMonth)+"01");
            sesMsg.effToDt.setValue(searchYear.toString()+dfMonth.format(searchMonth)+dfMonth.format(calSearchMonth.getActualMaximum(Calendar.DAY_OF_MONTH)));
            
	        bizMsg.B.clear();
	        bizMsg.B.setValidCount(0);
            ZZXL0030Query.getInstance().getCalendar(cMsg, sMsg);

            // Copy S Msg to C Msg.
            copySMsgToCMsg(sMsg, cMsg);
            bizMsg.xxYrMth_01.setValue(thisMonthString);
            bizMsg.xxYrMth_02.setValue(nextMonthString);
            
        } catch (Exception e) {
            EZDDebugOutput.println(1,e.getMessage(), null);
        }
    }

    /**
     * Do process for previous month operation
     * @param cMsg the c msg
     * @param sMsg the s msg
     */
    private void doProcess_ZZXL0030Scrn00_PrevMonth(EZDCMsg cMsg, EZDSMsg sMsg) {
    	
       	ZZXL0030CMsg bizMsg = (ZZXL0030CMsg) cMsg;  
    	ZZXL0030SMsg sesMsg = (ZZXL0030SMsg) sMsg;
    	    	
        try {
        	
        	// Check if the next month deta is modified or not.
        	if (!bizMsg.xxLastBtnNm.getValue().equals(PREV_BUTTON) && !ZZXL0030CommonLogic.checkModifiedData(cMsg,sMsg, CHECK_NEXT_MONTH)) {
        		bizMsg.setMessageInfo(DIRTYDATA_MSG);
        		bizMsg.xxLastBtnNm.setValue(PREV_BUTTON);
        		return ;
           	} else {
        		bizMsg.xxLastBtnNm.clear();
        	}
        	
           	//  OK clear. Now, I have to copy Biz' A array to Biz's B and Ses B'
        	EZDMsg.copy(sesMsg.A, "A", sesMsg.B, "B");
        	EZDMsg.copy(bizMsg.A, "A", bizMsg.B, "B");
        	bizMsg.xxMthOfsNum_B.setValue(bizMsg.xxMthOfsNum_A.getValue());
        	bizMsg.xxMthLg_B.setValue(bizMsg.xxMthLg_A.getValue());
        	
        	int nYear = (int)Integer.parseInt(bizMsg.xxYrMth_01.getValue().substring(0,4));
        	int nMonth = (int)Integer.parseInt(bizMsg.xxYrMth_01.getValue().substring(4,6));
        	
        	if (nMonth == 1) {
        		nMonth =12;
        		nYear--;
        	} else {
        		nMonth--;
        	}
        	
        	// Search table and get its result into S Msg D array.
        	setThisAndNextMonth(nMonth-1, nYear);
            
        	// Search from previous month's first day to end of previous month, not next month. 
        	sesMsg.effFromDt.setValue(thisMonthFirstDayString);  // same 
            Calendar calSearchMonth = new GregorianCalendar(nYear, nMonth-1, 1);
            sesMsg.effToDt.setValue(dfYear.format(nYear)+dfMonth.format(nMonth)+dfMonth.format(calSearchMonth.getActualMaximum(Calendar.DAY_OF_MONTH)));
            
	        bizMsg.A.clear();
	        bizMsg.A.setValidCount(0);
	        
            ZZXL0030Query.getInstance().getCalendar(cMsg, sMsg);
        	copySMsgToCMsg(sMsg, cMsg);
        	
        	bizMsg.xxYrMth_01.setValue(thisMonthString);
        	bizMsg.xxYrMth_02.setValue(nextMonthString);
        	
        } catch (Exception e) {
            EZDDebugOutput.println(1,e.getMessage(), null);
        }
    }
    
  
    /**
     * Check if the data is modified or not.
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_ZZXL0030Scrn00_Return(EZDCMsg cMsg, EZDSMsg sMsg) {
    	
       	ZZXL0030CMsg bizMsg = (ZZXL0030CMsg) cMsg;  
        
       	if (!bizMsg.xxLastBtnNm.getValue().equals(RETURN_BUTTON) && !ZZXL0030CommonLogic.checkModifiedData(cMsg,sMsg,CHECK_BOTH_MONTHS)) {
    		bizMsg.setMessageInfo(DIRTYDATA_MSG);
    		bizMsg.xxLastBtnNm.setValue(RETURN_BUTTON);
       		return ;
    	} else {
    		bizMsg.xxLastBtnNm.clear();
    	}
    }
    
    
    /**
     * This function sets all weekdays check. 
     * @param cMsg
     * @param arrayA
     */
    private void doProcess_ZZXL0030Scrn00_CheckWeekDay(EZDCMsg cMsg, boolean arrayA) {
    	
      	ZZXL0030CMsg bizMsg = (ZZXL0030CMsg) cMsg;  

    	int nYear;
    	int nMonth;
      	if (arrayA) {
      	   	nYear = (int)Integer.parseInt(bizMsg.xxYrMth_01.getValue().substring(0,4));
        	nMonth = (int)Integer.parseInt(bizMsg.xxYrMth_01.getValue().substring(4,6));
      	} else {
     	   	nYear = (int)Integer.parseInt(bizMsg.xxYrMth_02.getValue().substring(0,4));
        	nMonth = (int)Integer.parseInt(bizMsg.xxYrMth_02.getValue().substring(4,6));
      	}
 
      	GregorianCalendar calSelectMonth = new GregorianCalendar(nYear, nMonth-1, 1);
    	
    	int offset =calSelectMonth.get(Calendar.DAY_OF_WEEK)-1; 
    	int length = calSelectMonth.getActualMaximum(Calendar.DAY_OF_MONTH);

    	int lastday = offset+length;
    	for (int i=offset; i < lastday; i++) {
    		
    		if (i != offset) {
    			calSelectMonth.add(Calendar.DAY_OF_YEAR, 1);
    		}

    		if (calSelectMonth.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY &&  calSelectMonth.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
    			// Check this date.
    			if (arrayA) {
    				bizMsg.A.no(i).xxChkBox_A.setValue("Y");
    			} else {
    				bizMsg.B.no(i).xxChkBox_B.setValue("Y");
    			}
    		}
    	}
    } 
    
    /**
     *  Copy S Msg D array to S and C Msg's A(This month) & B(Next month) array
     *  S Msg's A & B arraies are original information holders to check of the data 
     *  is modified by user or not.
     * @param sMsg
     * @param cMsg
     * @param offset
     */
     private int copySMsgToCMsg(EZDSMsg sMsg, EZDCMsg cMsg) {    	
    	
       	ZZXL0030CMsg bizMsg = (ZZXL0030CMsg) cMsg;  
    	ZZXL0030SMsg sesMsg = (ZZXL0030SMsg) sMsg;

    	int offsetA =calThisMonth.get(Calendar.DAY_OF_WEEK)-1; 
    	bizMsg.xxMthOfsNum_A.setValue(offsetA);
    	int lengthA = calThisMonth.getActualMaximum(Calendar.DAY_OF_MONTH);
    	bizMsg.xxMthLg_A.setValue(lengthA);
    	int offsetB =calNextMonth.get(Calendar.DAY_OF_WEEK)-1;
    	bizMsg.xxMthOfsNum_B.setValue(offsetB);
    	int lengthB = calNextMonth.getActualMaximum(Calendar.DAY_OF_MONTH);
    	bizMsg.xxMthLg_B.setValue(lengthB);
    	
    	int lastdayA = offsetA+lengthA;
    	sesMsg.A.setValidCount(lastdayA);
    	int lastdayB = offsetB+lengthB;
    	sesMsg.B.setValidCount(lastdayB);
    	
    	Integer dayNumA=1;
    	Integer dayNumB=1;
    	
    	// Set up date of this & next months.
    	for (int i=0; i < CALENDAR_NUM; i++ ) {
    		
    		if (i >= offsetA && i < lastdayA ) {
    			// Date display  
    			sesMsg.A.no(i).xxDay_A.setValue(dayNumA.toString());
    			bizMsg.A.no(i).xxDay_A.setValue(dayNumA.toString());
    			dayNumA++;
    		} else {
    			// disable check box.
    			bizMsg.A.no(i).xxChkBox_A.clear();
    			bizMsg.A.no(i).xxDay_A.clear();
    		}
    		
      		if (i >= offsetB && i < lastdayB ) {
      			//  Date display  
      			sesMsg.B.no(i).xxDay_B.setValue(dayNumB.toString());
      			bizMsg.B.no(i).xxDay_B.setValue(dayNumB.toString());
    			dayNumB++;
    		} else {
    			// disable check box.
    			bizMsg.B.no(i).xxChkBox_B.clear();
    			bizMsg.B.no(i).xxDay_B.clear();
    		}
    	}
    	
    	// Copy sesMsg.B to sesMsg.A & bizMsg.A/B
    	for (int i=0; i < sesMsg.D.getValidCount(); i++) {
    		String currentDate = sesMsg.D.no(i).calDt_D.getValue();
    		String yyyymm = currentDate.substring(0, 6);
    	
    		int ddInt = Integer.parseInt(currentDate.substring(6, 8).trim())-1;
    		if (yyyymm.equals(thisMonthString)) {
    			 sesMsg.A.no(offsetA+ddInt).xxChkBox_A.setValue( sesMsg.D.no(i).xxChkBox_D.getValue());
    			 bizMsg.A.no(offsetA+ddInt).xxChkBox_A.setValue( sesMsg.D.no(i).xxChkBox_D.getValue());
    		} else {
    			sesMsg.B.no(offsetB+ddInt).xxChkBox_B.setValue( sesMsg.D.no(i).xxChkBox_D.getValue());
    			bizMsg.B.no(offsetB+ddInt).xxChkBox_B.setValue( sesMsg.D.no(i).xxChkBox_D.getValue());
    		}
    	}
    	
    	sesMsg.A.setValidCount(CALENDAR_NUM);
    	sesMsg.B.setValidCount(CALENDAR_NUM);
    	bizMsg.A.setValidCount(CALENDAR_NUM);
    	bizMsg.B.setValidCount(CALENDAR_NUM);
    	
    	// Clear D Array
    	sesMsg.D.clear();
    	sesMsg.D.setValidCount(0);
    	return 0;
    }
     
     /**
      * Method name: doProcess_ZZXL0030Scrn00_Find
      * <dd>The method explanation: Fill Calendar Type pulldown list based on selected Global
      * company code.
      * @param cMsg Business Component Interface Message
      * @param sMsg Global area information
      */
     private void doProcess_ZZXL0030Scrn00_Find(EZDCMsg cMsg, EZDSMsg sMsg) {
     	
     	ZZXL0030CMsg bizMsg = (ZZXL0030CMsg) cMsg; 
//     	ZZXL0030SMsg sesMsg = (ZZXL0030SMsg) sMsg;
     	
        // Check Global Copmany Code is exist or not.
        if (!ZZXL0030CommonLogic.checkExistGlbCmpCd(bizMsg)) {
            bizMsg.glblCmpyCd.setErrorInfo(1, "ZZZM9006E", new String[] {"Global Company Code"});
            return;
        }
     	// Check if data is changed or not.
    	if (!bizMsg.xxLastBtnNm.getValue().equals(FIND_BUTTON) && !ZZXL0030CommonLogic.checkModifiedData(cMsg,sMsg, CHECK_BOTH_MONTHS)) {
    		bizMsg.setMessageInfo(DIRTYDATA_MSG);
    		bizMsg.xxLastBtnNm.setValue(FIND_BUTTON);
       		return ;
    	} else {
    		bizMsg.xxLastBtnNm.clear();
    	}
     	
     	bizMsg.calTpCd_DP.clear();
     	bizMsg.calTpNm_DP.clear();
     	
     	// Clear all buffers
     	clearMonthBuffers(cMsg, sMsg);
     	
     	// Set up Calendar Type pulldown 
     	int hitCount = ZZXL0030Query.getInstance().getCalendarType(cMsg);
     	if (hitCount > 0) {
     		for(int i=0; i < bizMsg.C.getValidCount(); i++) {
     			bizMsg.calTpCd_DP.no(i).setValue(bizMsg.C.no(i).calTpCd_C.getValue());
     			bizMsg.calTpNm_DP.no(i).setValue(bizMsg.C.no(i).calTpNm_C.getValue());
     		}
     	} else {  // Calendar Type does not exists for selected global company code.
     		// bizMsg.xxMthOfsNum_A.setValue(CALENDAR_NUM);
     		// bizMsg.xxMthOfsNum_B.setValue(CALENDAR_NUM);
     		bizMsg.setMessageInfo(NOCALTYPE_MSG);
     	}
     }
     
     /**
      * Clear all array buffers.
      * @param cMsg
      * @param sMsg
      */
     private void clearMonthBuffers(EZDCMsg cMsg, EZDSMsg sMsg) {    	
     	
         ZZXL0030CMsg bizMsg = (ZZXL0030CMsg) cMsg;  
     	 ZZXL0030SMsg sesMsg = (ZZXL0030SMsg) sMsg;
     	
         bizMsg.A.clear();
         bizMsg.A.setValidCount(0);
         bizMsg.B.clear();
         bizMsg.B.setValidCount(0);
         sesMsg.A.clear();
         sesMsg.A.setValidCount(0);
         sesMsg.B.clear();
         sesMsg.B.setValidCount(0);
         sesMsg.D.clear();
         sesMsg.D.setValidCount(0);
     }
}