/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL0050;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFAL0050.constant.NFAL0050Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * Class name: Screen Component ID : NFAL0050Scrn00_CoaBrLink
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0050Scrn00_CoaBrLink extends S21CommonHandler implements NFAL0050Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;
        if (scrnMsg.ajeCoaBrCd.isInputProtected()) {
            scrnMsg.setMessageInfo("NFAM0056E", new String[] {"Br Code" });
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;

        // NFAL0050CMsg bizMsg = new NFAL0050CMsg();
        // bizMsg.setBusinessID("NFAL0050");
        // bizMsg.setFunctionCode("20");
        // EZDMsg.copy(scrnMsg, null, bizMsg, null);

        // return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;

        // Code Table Name
        scrnMsg.xxTblNm.setValue("COA_BR");
        // Code of Code Table
        scrnMsg.xxEdtCdNm.setValue("COA_BR_CD");
        // Desc of Code Table
        scrnMsg.xxDtlNm.setValue("COA_BR_NM");
        // Sort num of Code Table
        scrnMsg.xxSortItemNm.setValue("COA_BR_SORT_NUM");
        // Screen Title
        scrnMsg.xxTblItemTxt.setValue("Br Search");
        // ID label for search field
        scrnMsg.xxHdrCdLbNm.setValue("Br");
        // Description label for search field
        scrnMsg.xxHdrNmLbNm.setValue("Br Name");
        // ID filed for table header
        scrnMsg.xxDtlCdLbNm.setValue("COA_BR");
        // Desc filed for table header
        scrnMsg.xxDtlNmLbNm.setValue("COA_BR_NM");

        Object[] param = new Object[11];

        param[0] = scrnMsg.xxTblNm;
        param[1] = scrnMsg.xxEdtCdNm;
        param[2] = scrnMsg.xxDtlNm;
        param[3] = scrnMsg.xxSortItemNm;
        param[4] = scrnMsg.xxTblItemTxt;
        param[5] = scrnMsg.xxHdrCdLbNm;
        param[6] = scrnMsg.xxHdrNmLbNm;
        param[7] = scrnMsg.xxDtlCdLbNm;
        param[8] = scrnMsg.xxDtlNmLbNm;
        // Code for input filed
        //---- start mod 2016/01/21
        param[9] = scrnMsg.ajeCoaBrCd;
        //---- end 2016/01/21
        // Desc for input filed (Mock)
        scrnMsg.xxUpldFileNm.clear();
        param[10] = scrnMsg.xxUpldFileNm;

        setArgForSubScreen(param);

        scrnMsg.eventId.setValue(EVT_COA_BR);
    }

}
