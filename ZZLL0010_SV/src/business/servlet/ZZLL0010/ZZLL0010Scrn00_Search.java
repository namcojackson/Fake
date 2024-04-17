/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZLL0010;


import parts.common.*;
import parts.servletcommon.*;
import business.blap.ZZLL0010.ZZLL0010CMsg;

import business.servlet.ZZLL0010.common.ZZLL0010CommonLogic;
import business.servlet.ZZLL0010.constant.ZZLL0010Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

public class ZZLL0010Scrn00_Search extends S21CommonHandler implements ZZLL0010Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		ZZLL0010BMsg scrnMsg = (ZZLL0010BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd);
        scrnMsg.addCheckItem(scrnMsg.ezInUserID);
        scrnMsg.addCheckItem(scrnMsg.ezInAplID);
        scrnMsg.addCheckItem(scrnMsg.mntTrxTp);
        scrnMsg.addCheckItem(scrnMsg.mntTblNm);
        scrnMsg.addCheckItem(scrnMsg.mntColumnNm);
        scrnMsg.addCheckItem(scrnMsg.mntOldVal);
        scrnMsg.addCheckItem(scrnMsg.mntNewVal);
        scrnMsg.addCheckItem(scrnMsg.mntPrimaryKey);
        scrnMsg.addCheckItem(scrnMsg.xxFromDt);
        scrnMsg.addCheckItem(scrnMsg.xxHrs_FS);
        scrnMsg.addCheckItem(scrnMsg.xxMn_FS);
        scrnMsg.addCheckItem(scrnMsg.xxToDt);
        scrnMsg.addCheckItem(scrnMsg.xxHrs_TS);
        scrnMsg.addCheckItem(scrnMsg.xxMn_TS);
   
        correlativeTimeCheck(scrnMsg);
        
        
        scrnMsg.putErrorScreen();

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 		ZZLL0010BMsg scrnMsg = (ZZLL0010BMsg) bMsg;
        ZZLL0010CMsg bizMsg = new ZZLL0010CMsg();
        
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();
        
        // From Time
        if (!scrnMsg.xxFromDt.isClear()) {   
            String frDate = scrnMsg.xxFromDt.getValue();
            String frHour = null;
            String frMin = null;
            
            if (!scrnMsg.xxHrs_FS.isClear()) {
                frHour = scrnMsg.xxHrs_FS.getValue();
            } else {
                frHour = DEF_HRS_FROM;
            }
            
            if (!scrnMsg.xxMn_FS.isClear()) {
                frMin = scrnMsg.xxMn_FS.getValue();
            } else {
                frMin = DEF_MIN_FROM;
            }
            bizMsg.ezInTime_FR.setValue(frDate + frHour + frMin + DEF_SEC_MILLI_FROM);
        }
        
        // To Time
        if (!scrnMsg.xxToDt.isClear()) {   
            String frDate = scrnMsg.xxToDt.getValue();
            String frHour = null;
            String frMin = null;
     
            if (!scrnMsg.xxHrs_TS.isClear()) {
                frHour = scrnMsg.xxHrs_TS.getValue();
            } else {
                frHour = DEF_HRS_TO;
            }
            
            if (!scrnMsg.xxMn_TS.isClear()) {
                frMin = scrnMsg.xxMn_TS.getValue();
            } else {
                frMin = DEF_MIN_TO;
            }
            bizMsg.ezInTime_TO.setValue(frDate + frHour + frMin + DEF_SEC_MILLI_TO);
        }
		
		bizMsg.setBusinessID("ZZLL0010");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;
	}

    
	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZLL0010BMsg scrnMsg = (ZZLL0010BMsg) bMsg;
		ZZLL0010CMsg bizMsg  = (ZZLL0010CMsg) cMsg;

        S21SortColumnIMGController.clearIMG("ZZLL0010Scrn00", scrnMsg, scrnMsg.A.no(0).getBaseContents());
		EZDMsg.copy(bizMsg, null, scrnMsg, null);
        ZZLL0010CommonLogic.convertTimeToDisplay(scrnMsg, bizMsg);
        
        // Table Color Controll
        S21TableColorController tblColor = new S21TableColorController(ZZLL0010Constant.pageID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        scrnMsg.A.setInputProtected(true);
        
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
	}
    
    
    
    /**
     * correlative check for date time fields.
     * @param ZZLL0010BMsg scrnMsg
     */
    protected void correlativeTimeCheck(ZZLL0010BMsg scrnMsg) {
        String frDateNm = scrnMsg.xxFromDt.getNameForMessage();
        String toDateNm = scrnMsg.xxToDt.getNameForMessage();
        String frHourNm = scrnMsg.xxHrs_FS.getNameForMessage();
        String frMinNm = scrnMsg.xxMn_FS.getNameForMessage();
        String toHourNm = scrnMsg.xxHrs_TS.getNameForMessage();
        String toMinNm = scrnMsg.xxMn_TS.getNameForMessage();
        
        // If time fields are filled, the date field should be filled.
        if (scrnMsg.xxFromDt.isClear() && !scrnMsg.xxHrs_FS.isClear()) {
            scrnMsg.xxHrs_FS.setErrorInfo(1, "ZZZM9024E", new String[]{frDateNm});
        }
        if (scrnMsg.xxFromDt.isClear() && !scrnMsg.xxMn_FS.isClear()) {
            scrnMsg.xxMn_FS.setErrorInfo(1, "ZZZM9024E", new String[]{frDateNm});
        }
        if (scrnMsg.xxHrs_FS.isClear() && !scrnMsg.xxMn_FS.isClear()) {
            scrnMsg.xxMn_FS.setErrorInfo(1, "ZZZM9024E", new String[]{frHourNm});
        }
        if (scrnMsg.xxToDt.isClear() && !scrnMsg.xxHrs_TS.isClear()) {
            scrnMsg.xxHrs_TS.setErrorInfo(1, "ZZZM9024E", new String[]{toDateNm});
        }
        if (scrnMsg.xxToDt.isClear() && !scrnMsg.xxMn_TS.isClear()) {
            scrnMsg.xxMn_TS.setErrorInfo(1, "ZZZM9024E", new String[]{toDateNm});
        }
        if (scrnMsg.xxHrs_TS.isClear() && !scrnMsg.xxMn_TS.isClear()) {
            scrnMsg.xxMn_TS.setErrorInfo(1, "ZZZM9024E", new String[]{toHourNm});
        }
        
        
        if (!scrnMsg.xxFromDt.isClear() && !scrnMsg.xxToDt.isClear()) {
            int cmpDate = ZYPDateUtil.compare(scrnMsg.xxFromDt.getValue(), scrnMsg.xxToDt.getValue());
            
            // Bigger the 'From Date' is error
            if (cmpDate > 0) {
                scrnMsg.xxFromDt.setErrorInfo(1, "ZZZM9010E", new String[]{frDateNm, toDateNm});
                scrnMsg.xxToDt.setErrorInfo(1, "ZZZM9010E", new String[]{frDateNm, toDateNm});
                
            } else if (cmpDate == 0) {
                
                if (!scrnMsg.xxHrs_FS.isClear() && !scrnMsg.xxHrs_TS.isClear()) {
                    int frHrs = Integer.parseInt(scrnMsg.xxHrs_FS.getValue());
                    int toHrs = Integer.parseInt(scrnMsg.xxHrs_TS.getValue());
                    
                    //Bigger the 'From Hour' is error
                    if (frHrs > toHrs) {
                        scrnMsg.xxHrs_FS.setErrorInfo(1, "ZZZM9010E", new String[]{frHourNm, toHourNm});
                        scrnMsg.xxHrs_TS.setErrorInfo(1, "ZZZM9010E", new String[]{frHourNm, toHourNm});
                    
                    } else if(frHrs == toHrs) {
                        if (!scrnMsg.xxMn_FS.isClear() && !scrnMsg.xxMn_TS.isClear()) {
                            int frMin = Integer.parseInt(scrnMsg.xxMn_FS.getValue());
                            int toMin = Integer.parseInt(scrnMsg.xxMn_TS.getValue());
                            
                            //Bigger the 'From Minutes' is error
                            if(frMin > toMin){
                                scrnMsg.xxMn_FS.setErrorInfo(1, "ZZZM9010E", new String[]{frMinNm, toMinNm});
                                scrnMsg.xxMn_TS.setErrorInfo(1, "ZZZM9010E", new String[]{frMinNm, toMinNm});
                            }
                        }
                    }
                }
            }
        }

    }

}
