/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2610;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDCommonConst;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2610.NMAL2610CMsg;
import business.servlet.NMAL2610.common.NMAL2610CommonLogic;
import business.servlet.NMAL2610.constant.NMAL2610Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPEZDItemErrorUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/03/04   Fujitsu         M.suzuki        Update          S21_NA#4539
 * 2016/03/31   Fujitsu         C.Yokoi         Update          CSA-QC#5945
 * 2018/09/21   Fujitsu         S.Kosaka        Update          QC#27726
 *</pre>
 */
public class NMAL2610Scrn00_DeleteRow_Territory extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(scrnMsg.A, NMAL2610Constant.CHKBOX_A, ZYPConstant.CHKBOX_ON_Y);
        if (checkList.size() <= 0) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NMAL2610Constant.NZZM0011E);
            }
        }

        for (int index : checkList) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).gnrnTpCd_A1)) {
                // 2016/03/30 CSA-QC#5945 Delete End
//                if (!GNRN_TP.FUTURE.equals(scrnMsg.A.no(index).gnrnTpCd_A1.getValue())) {
//                    scrnMsg.A.no(index).xxChkBox_A1.setErrorInfo(1, NMAL2610Constant.NMAM8230E);
//                } else {
//                    scrnMsg.setMessageInfo(NMAL2610Constant.NMAM8291W);
//                }
                // 2016/03/30 CSA-QC#5945 Delete End
                // 2016/03/30 CSA-QC#5945 Add Start
                // can not delete data which already deleted or expired
                if (GNRN_TP.PAST.equals(scrnMsg.A.no(index).gnrnTpCd_A1.getValue())) {
                    scrnMsg.C.no(index).xxChkBox_C1.setErrorInfo(1, NMAL2610Constant.NMAM8230E);
                }
                // 2016/03/30 CSA-QC#5945 Add End
            }
        }

        NMAL2610CommonLogic.addTabTerritoryCheckItem(scrnMsg);
        // 2016/03/04 S21_NA#4539 Add Start --------------
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (scrnMsg.A.no(i).effFromDt_A1.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.A.no(i).getErrorInfo("effFromDt_A1");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.A.no(i).effFromDt_A1.clearErrorInfo();
                }
            }
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A1);
        }
        // 2016/03/04 S21_NA#4539 Add Start --------------
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;

        NMAL2610CMsg bizMsg = new NMAL2610CMsg();
        bizMsg.setBusinessID(NMAL2610Constant.BIZ_ID);
        bizMsg.setFunctionCode(NMAL2610Constant.FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;
        NMAL2610CMsg bizMsg  = (NMAL2610CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // 2018/09/21 QC#27726,ADD Add Start
        if (scrnMsg.A.getValidCount() + 1 <= scrnMsg.A.length()) {
            setButtonEnabled(NMAL2610Constant.BTN_INSERT_ROW_TERRITORY, true);
        }
        if (scrnMsg.A.getValidCount() == 0) {
            setButtonEnabled(NMAL2610Constant.BTN_DELETE_ROW_TERRITORY, false);
        }
        NMAL2610CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, NMAL2610Constant.SCREEN_TABLE_NAME_TERRITORY_UP);
        // 2018/09/21 QC#27726,ADD Add End
    }
}
