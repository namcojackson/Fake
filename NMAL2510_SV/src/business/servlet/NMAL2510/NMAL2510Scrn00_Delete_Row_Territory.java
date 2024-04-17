/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2510;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2510.NMAL2510CMsg;
import business.servlet.NMAL2510.common.NMAL2510CommonLogic;
import business.servlet.NMAL2510.constant.NMAL2510Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/19   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/03/04   Fujitsu         M.suzuki        Update          S21_NA#4539 
 * 2016/03/31   Fujitsu         C.Yokoi         Update          S21_NA#5945
 * 2016/09/02   SRAA            Y.Chen          Update          QC#6402
 * 2018/09/14   Fujitsu         S.Kosaka        Update          QC#27723
 *</pre>
 */
public class NMAL2510Scrn00_Delete_Row_Territory extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2510BMsg scrnMsg = (NMAL2510BMsg) bMsg;

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(scrnMsg.B, NMAL2510Constant.CHKBOX_B, ZYPConstant.CHKBOX_ON_Y);
        if (checkList.size() <= 0) {
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                scrnMsg.B.no(i).xxChkBox_B2.setErrorInfo(1, NMAL2510Constant.NZZM0011E);
            }
        }

        for (int index : checkList) {
            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(index).gnrnTpCd_B2)) {
                // 2016/03/30 CSA-QC#5945 Delete Start
//                if (!GNRN_TP.FUTURE.equals(scrnMsg.B.no(index).gnrnTpCd_B2.getValue())) {
//                    scrnMsg.B.no(index).xxChkBox_B2.setErrorInfo(1, NMAL2510Constant.NMAM8230E);
//                } else {
//                    scrnMsg.setMessageInfo(NMAL2510Constant.NMAM8291W);
//                }
                // 2016/03/30 CSA-QC#5945 Delete End
                // 2016/03/30 CSA-QC#5945 Add Start
                // can not delete data which already deleted or expired
                if (GNRN_TP.PAST.equals(scrnMsg.B.no(index).gnrnTpCd_B2.getValue())) {
                    scrnMsg.B.no(index).xxChkBox_B2.setErrorInfo(1, NMAL2510Constant.NMAM8230E);
                }
                // 2016/03/30 CSA-QC#5945 Add End
            }
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.psnNum_H1.getValue())) {
            scrnMsg.psnNum_H1.setErrorInfo(1, NMAL2510Constant.ZZZM9007E, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_PSN_NUM });
        }
        scrnMsg.addCheckItem(scrnMsg.psnNum_H1);

// QC#6402
//        if (!ZYPCommonFunc.hasValue(scrnMsg.psnCd_H1.getValue())) {
//            if (PSN_TP.EMPLOYEE.equals(scrnMsg.psnTpCd_P1.getValue())) {
//                scrnMsg.psnCd_H1.setErrorInfo(1, NMAL2510Constant.ZZZM9007E, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_PSN_CD });
//                scrnMsg.addCheckItem(scrnMsg.psnCd_H1);
//            }
//        }

        NMAL2510CommonLogic.addTabTerritoryCheckItem(scrnMsg);
        // 2016/03/04 S21_NA#4539 Add Start --------------
        NMAL2510CommonLogic.clearMandantoryCheckForTerritory(scrnMsg);
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.B.no(i).orgNm_B2);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).effFromDt_B2);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).effThruDt_B2);
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

        // START 2018/09/14 S.Kosaka [QC#27723,ADD]
        if (scrnMsg.B.getValidCount() + 1 <= scrnMsg.A.length()) {
            setButtonEnabled(NMAL2510Constant.BTN_INSERT_TERRITORY, true);
        }
        if (scrnMsg.B.getValidCount() == 0) {
            setButtonEnabled(NMAL2510Constant.BTN_DELETE_TERRITORY, false);
        }
        NMAL2510CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.B, NMAL2510Constant.SCREEN_TABLE_NAME_TERRITORY);
        // END 2018/09/14 S.Kosaka [QC#27723,ADD]
    }
}
