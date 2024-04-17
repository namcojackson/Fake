/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2520;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2520.NMAL2520CMsg;
import business.servlet.NMAL2520.common.NMAL2520CommonLogic;
import business.servlet.NMAL2520.constant.NMAL2520Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/10   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/03/04   Fujitsu         M.suzuki        Update          S21_NA#4539 
 * 2016/03/31   Fujitsu         C.Yokoi         Update          CSA-QC#5945
 * 2018/09/20   Fujitsu         S.Kosaka        Update          QC#27724
 *</pre>
 */
public class NMAL2520Scrn00_Delete_Row_Hierarchy extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(scrnMsg.A, NMAL2520Constant.CHKBOX_A, ZYPConstant.CHKBOX_ON_Y);
        if (checkList.size() <= 0) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NMAL2520Constant.NZZM0011E);
            }
        }

        for (int index : checkList) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).gnrnTpCd_A1)) {
                // 2016/03/30 CSA-QC#5945 Delete Start
//                if (!GNRN_TP.FUTURE.equals(scrnMsg.A.no(index).gnrnTpCd_A1.getValue())) {
//                    scrnMsg.A.no(index).xxChkBox_A1.setErrorInfo(1, NMAL2520Constant.NMAM8230E);
//                } else {
//                    scrnMsg.setMessageInfo(NMAL2520Constant.NMAM8291W);
//                }
                // 2016/03/30 CSA-QC#5945 Delete End
                // 2016/03/30 CSA-QC#5945 Add Start
                // can not delete data which already deleted or expired
                if (GNRN_TP.PAST.equals(scrnMsg.C.no(index).gnrnTpCd_C1.getValue())) {
                    scrnMsg.C.no(index).xxChkBox_C1.setErrorInfo(1, NMAL2520Constant.NMAM8230E);
                }
                // 2016/03/30 CSA-QC#5945 Add End
            }
        }
        // 2016/03/04 S21_NA#4539 Add Start --------------
        //NMAL2520CommonLogic.addTabBuildCheckItem(scrnMsg);
        NMAL2520CommonLogic.clearMandantoryCheck(scrnMsg);
        NMAL2520CommonLogic.addCheckItems(scrnMsg);
        // 2016/03/04 S21_NA#4539 Add Start --------------
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;

        NMAL2520CMsg bizMsg = new NMAL2520CMsg();
        bizMsg.setBusinessID(NMAL2520Constant.BIZ_ID);
        bizMsg.setFunctionCode(NMAL2520Constant.FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;
        NMAL2520CMsg bizMsg = (NMAL2520CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // 2018/09/20 QC#27724,ADD Add Start
        if (scrnMsg.A.getValidCount() + 1 <= scrnMsg.A.length()) {
            setButtonEnabled(NMAL2520Constant.BTN_INSERT_HIERARCHY, true);
        }
        if (scrnMsg.A.getValidCount() == 0) {
            setButtonEnabled(NMAL2520Constant.BTN_DELETE_HIERARCHY, false);
        }
        NMAL2520CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, NMAL2520Constant.SCREEN_TABLE_NAME_HIERARCHY_UP);
        // 2018/09/20 QC#27724,ADD Add End
    }
}
