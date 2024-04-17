/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZOL0010;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import parts.common.*;
import parts.servletcommon.*;

import business.blap.ZZOL0010.ZZOL0010CMsg;
import business.servlet.ZZOL0010.common.ZZOL0010CommonLogic;
import business.servlet.ZZOL0010.constant.ZZOL0010Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

public class ZZOL0010Scrn01_CMN_Submit extends S21CommonHandler implements ZZOL0010Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZOL0010BMsg scrnMsg = (ZZOL0010BMsg) bMsg;               
        
        scrnMsg.addCheckItem(scrnMsg.ezBusinessID_01);
        scrnMsg.addCheckItem(scrnMsg.ezCompanyCode_01);
        scrnMsg.addCheckItem(scrnMsg.xxHrs_SV);
        scrnMsg.addCheckItem(scrnMsg.xxMn_SV);
        scrnMsg.addCheckItem(scrnMsg.xxHrs_EV);
        scrnMsg.addCheckItem(scrnMsg.xxMn_EV);
        scrnMsg.addCheckItem(scrnMsg.ezOnlStopFlag_01);
        scrnMsg.addCheckItem(scrnMsg.ezAcctInfoMode_01);
        scrnMsg.addCheckItem(scrnMsg.ezDebugLevel_01);

        int stHr =  Integer.parseInt(scrnMsg.xxHrs_SV.getValue());
        int stMin = Integer.parseInt(scrnMsg.xxMn_SV.getValue());
        int enHr =  Integer.parseInt(scrnMsg.xxHrs_EV.getValue());
        int enMin = Integer.parseInt(scrnMsg.xxMn_EV.getValue());
        
        String[] st_en_time = new String[]{"Start Time", "End Time"};
        if (stHr > enHr) {
            scrnMsg.xxHrs_SV.setErrorInfo(1, "ZZZM9010E", st_en_time);
            scrnMsg.xxMn_SV.setErrorInfo(1, "ZZZM9010E", st_en_time);
            scrnMsg.xxHrs_EV.setErrorInfo(1, "ZZZM9010E", st_en_time);
            scrnMsg.xxMn_EV.setErrorInfo(1, "ZZZM9010E", st_en_time);

        } else if (stHr == enHr && stMin >= enMin) {
            scrnMsg.xxHrs_SV.setErrorInfo(1, "ZZZM9010E", st_en_time);
            scrnMsg.xxMn_SV.setErrorInfo(1, "ZZZM9010E", st_en_time);
            scrnMsg.xxHrs_EV.setErrorInfo(1, "ZZZM9010E", st_en_time);
            scrnMsg.xxMn_EV.setErrorInfo(1, "ZZZM9010E", st_en_time);
        }
             
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZOL0010BMsg scrnMsg = (ZZOL0010BMsg) bMsg;
        ZZOL0010CMsg bizMsg = new ZZOL0010CMsg();

        bizMsg.setBusinessID("ZZOL0010");
        bizMsg.setFunctionCode("40");
        
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        bizMsg.xxScrEventNm.setValue(scrnMsg.xxScrEventNm.getValue());
       
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZOL0010BMsg scrnMsg = (ZZOL0010BMsg) bMsg;
        ZZOL0010CMsg bizMsg = (ZZOL0010CMsg) cMsg;
       
        S21SortColumnIMGController.clearIMG("ZZOL0010Scrn00", scrnMsg, scrnMsg.A.no(0).getBaseContents());
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        if (!bizMsg.getMessageKind().equals("E")) {

            ZZOL0010CommonLogic.convertFlagDisplay(scrnMsg, bizMsg);
            ZZOL0010CommonLogic.convertTimeDisplay(scrnMsg);
            
            // Table Color Controll
            S21TableColorController tblColor = new S21TableColorController(ZZOL0010Constant.pageID, scrnMsg);
            tblColor.setAlternateRowsBG("A", scrnMsg.A);
        }
        
        scrnMsg.addCheckItem(scrnMsg.ezBusinessID_01);
        scrnMsg.putErrorScreen();
        
        scrnMsg.ezBusinessID_BK.setValue(scrnMsg.ezBusinessID_01.getValue());
        scrnMsg.ezCompanyCode_BK.setValue(scrnMsg.ezCompanyCode_01.getValue());
        scrnMsg.ezOnlStopFlag_BK.setValue(scrnMsg.ezOnlStopFlag_01.getValue());
        scrnMsg.ezAcctInfoMode_BK.setValue(scrnMsg.ezAcctInfoMode_01.getValue());
        scrnMsg.ezDebugLevel_BK.setValue(scrnMsg.ezDebugLevel_01.getValue());
        scrnMsg.xxHrs_SB.setValue(scrnMsg.xxHrs_SV.getValue());
        scrnMsg.xxMn_SB.setValue(scrnMsg.xxMn_SV.getValue());
        scrnMsg.xxHrs_EB.setValue(scrnMsg.xxHrs_EV.getValue());
        scrnMsg.xxMn_EB.setValue(scrnMsg.xxMn_EV.getValue());   
        
        
    }

}
