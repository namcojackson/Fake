/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/03/15   CUSA            FJ)A.Akabane    Create          N/A
 *</pre>
 */
package business.servlet.NLAL0070;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLAL0070.NLAL0070CMsg;
import business.servlet.NLAL0070.constant.NLAL0070Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NLAL0070Scrn00_CMN_Download extends S21CommonHandler implements NLAL0070Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLAL0070BMsg scrnMsg = (NLAL0070BMsg) bMsg;

        if (TB_SCHEDULE.equals(scrnMsg.xxDplyTab_BK.getValue()) ) {
            scrnMsg.addCheckItem(scrnMsg.xxOrdDt_F1);
            scrnMsg.addCheckItem(scrnMsg.xxOrdDt_T1);
            scrnMsg.addCheckItem(scrnMsg.rwsRefNum_P1);
            scrnMsg.addCheckItem(scrnMsg.mdseCd_P1);
            scrnMsg.addCheckItem(scrnMsg.imptInvNum_P1);
            
            scrnMsg.putErrorScreen();
            
            if (1 == ZYPDateUtil.compare(scrnMsg.xxOrdDt_F1.getValue(), scrnMsg.xxOrdDt_T1.getValue())) {
                
                scrnMsg.xxOrdDt_F1.setErrorInfo(1, "NLAM0028E", new String[] {"WH ETA From", "WH ETA To" });
                scrnMsg.xxOrdDt_T1.setErrorInfo(1, "NLAM0028E", new String[] {"WH ETA From", "WH ETA To" });
                scrnMsg.addCheckItem(scrnMsg.xxOrdDt_F1);
                scrnMsg.addCheckItem(scrnMsg.xxOrdDt_T1);           
            }

        } else if (TB_SUMMARY.equals(scrnMsg.xxDplyTab_BK.getValue())) {
            
            scrnMsg.addCheckItem(scrnMsg.xxOrdDt_F1);       
        }
        scrnMsg.putErrorScreen();
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NLAL0070BMsg scrnMsg = (NLAL0070BMsg) bMsg;

		NLAL0070CMsg bizMsg = new NLAL0070CMsg();
		bizMsg.setBusinessID("NLAL0070");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
		
		NLAL0070CMsg bizMsg  = (NLAL0070CMsg) cMsg;

		if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        } else {
            // no process
        }
		
        NLAL0070BMsg scrnMsg = (NLAL0070BMsg) bMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        String dlFileName = CSV_FILE_NAME_SCHEDULE;
        if (TB_SUMMARY.equals(scrnMsg.xxDplyTab_BK.getValue())) {
            dlFileName = CSV_FILE_NAME_SUMMARY;
        }
        
        String fileNm = ZYPCommonFunc.concatString(dlFileName, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS") + scrnMsg.usrId_BK.getValue(), ".csv");

        super.executeDownload(scrnMsg.xxFileData.getTempFilePath(), true, fileNm);
	}

}
