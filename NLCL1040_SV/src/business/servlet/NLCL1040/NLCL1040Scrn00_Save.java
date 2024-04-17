/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL1040;

import static business.servlet.NLCL1040.constant.NLCL1040Constant.BIZ_APP_ID;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.EVENT_NM_OPENWIN_SAVE;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.FUNCTION_CD_UPDATE;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.NLGM0070E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL1040.NLCL1040CMsg;
import business.servlet.NLCL1040.common.NLCL1040CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NLCL1040 Inventory ABC Analysis Setup
 * Function Name : NLCL1040Scrn00_Save
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/24/2016   CITS            T.Hakodate      Create          N/A
 * 11/26/2018   Fujitsu         T.Ogura         Update          QC#29124
 *</pre>
 */
public class NLCL1040Scrn00_Save extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL1040BMsg scrnMsg = (NLCL1040BMsg) bMsg;

        // checks the mandatory fields header
        scrnMsg.addCheckItem(scrnMsg.abcAsgNm);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCdSrchTxt_RW);
        scrnMsg.addCheckItem(scrnMsg.abcAnlsCritCd_H1);
        scrnMsg.addCheckItem(scrnMsg.abcAnlsClsNum_H1);

        // stock status check
        boolean stkStsNotSelect = true;
        for (int index = 0; index < scrnMsg.S.getValidCount(); index++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.S.no(index).xxChkBox_SS)) {
                stkStsNotSelect = false;
            }
        }

        if (stkStsNotSelect) {
            for (int index = 0; index < scrnMsg.S.getValidCount(); index++) {
                scrnMsg.S.no(index).xxChkBox_SS.setErrorInfo(1, NLGM0070E, new String[] {"Stock Status" });
                scrnMsg.addCheckItem(scrnMsg.S.no(index).xxChkBox_SS);
            }

        }

        // detail check
        for (int index = 0; index < scrnMsg.A.getValidCount(); index++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(index).mdseCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(index).rtlWhCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(index).abcAnlsClsTagCd_A);
        }

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL1040BMsg scrnMsg = (NLCL1040BMsg) bMsg;
        NLCL1040CMsg bizMsg = new NLCL1040CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxMntEventNm, EVENT_NM_OPENWIN_SAVE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL1040BMsg scrnMsg = (NLCL1040BMsg) bMsg;
        NLCL1040CMsg bizMsg = (NLCL1040CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLCL1040CommonLogic.ctrlScrnItemDisplay(this, scrnMsg);

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {

            scrnMsg.addCheckItem(scrnMsg.abcAsgNm);
            scrnMsg.addCheckItem(scrnMsg.rtlWhCdSrchTxt_RW);
            scrnMsg.addCheckItem(scrnMsg.rtlSwhCdSrchTxt_SW);    // 2018/11/26 T.Ogura [QC#29124,ADD]
            scrnMsg.addCheckItem(scrnMsg.abcAnlsCritCd_H1);
            scrnMsg.addCheckItem(scrnMsg.abcAnlsClsNum_H1);

            scrnMsg.addCheckItem(scrnMsg.effFromDt);
            scrnMsg.addCheckItem(scrnMsg.effThruDt);

            for (int index = 0; index < scrnMsg.A.getValidCount(); index++) {
                scrnMsg.addCheckItem(scrnMsg.A.no(index).mdseCd_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(index).rtlWhCd_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(index).abcAnlsClsTagCd_A);
            }

            scrnMsg.putErrorScreen();
            return;

        }

        scrnMsg.setFocusItem(scrnMsg.abcAsgNm);

    }
}
