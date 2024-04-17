/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2510;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2510.NMAL2510CMsg;
import business.servlet.NMAL2510.common.NMAL2510CommonLogic;
import business.servlet.NMAL2510.constant.NMAL2510Constant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/19   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/02/08   Fujitsu         C.Yokoi         Update          CSA-QC#2869
 * 2016/03/04   Fujitsu         M.suzuki        Update          S21_NA#4539
 * 2018/09/14   Fujitsu         S.Kosaka        Update          QC#27723
 *</pre>
 */
public class NMAL2510Scrn00_Insert_Row_Hierarchy extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2510BMsg scrnMsg = (NMAL2510BMsg) bMsg;

        // 2016/02/08 CSA-QC#2869 Delete Start
        // if (!ZYPCommonFunc.hasValue(scrnMsg.psnNum_H1.getValue())) {
        // scrnMsg.psnNum_H1.setErrorInfo(1, NMAL2510Constant.ZZZM9007E, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_PSN_NUM });
        // }
        // scrnMsg.addCheckItem(scrnMsg.psnNum_H1);
        //
        // if (!ZYPCommonFunc.hasValue(scrnMsg.psnCd_H1.getValue())) {
        // if (PSN_TP.EMPLOYEE.equals(scrnMsg.psnTpCd_P1.getValue())) {
        // scrnMsg.psnCd_H1.setErrorInfo(1,
        // NMAL2510Constant.ZZZM9007E, new String[]
        // {NMAL2510Constant.NAME_FOR_MESSAGE_PSN_CD });
        // scrnMsg.addCheckItem(scrnMsg.psnCd_H1);
        // }
        // }
        // 2016/02/08 CSA-QC#2869 Delete End
        // 2016/03/04 S21_NA#4539 Add Start --------------
        NMAL2510CommonLogic.clearMandantoryCheckForHierarchy(scrnMsg);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).orgNm_A2);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A2);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A2);
        }
        // 2016/03/04 S21_NA#4539 Add Start --------------

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2510BMsg scrnMsg = (NMAL2510BMsg) bMsg;

        NMAL2510CMsg bizMsg = new NMAL2510CMsg();
        bizMsg.setBusinessID(NMAL2510Constant.BIZ_ID);
        bizMsg.setFunctionCode(NMAL2510Constant.FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2510BMsg scrnMsg = (NMAL2510BMsg) bMsg;
        NMAL2510CMsg bizMsg = (NMAL2510CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.A.getValidCount() + 1 > scrnMsg.A.length()) {
            setButtonEnabled(NMAL2510Constant.BTN_INSERT_HIERARCHY, false);
        }
        if (scrnMsg.A.getValidCount() > 0) {
            setButtonEnabled(NMAL2510Constant.BTN_DELETE_HIERARCHY, true);
        }
        // 2016/03/04 CSA-QC#4654 Add Start
        NMAL2510CommonLogic.controlBusinessAreaFields(scrnMsg);
        // 2016/03/04 CSA-QC#4654 Add End

        // START 2018/09/14 S.Kosaka [QC#27723,ADD]
        NMAL2510CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, NMAL2510Constant.SCREEN_TABLE_NAME_HIERARCHY_UP);
        // END 2018/09/14 S.Kosaka [QC#27723,ADD]
    }
}
