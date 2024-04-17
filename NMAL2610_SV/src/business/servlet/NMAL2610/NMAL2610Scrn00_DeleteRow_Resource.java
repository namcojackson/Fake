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
 * 2015/11/10   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/03/04   Fujitsu         M.suzuki        Update          S21_NA#4539
 * 2016/03/04   Fujitsu         C.Yokoi         Update          CSA-QC#5945
 * 2018/09/21   Fujitsu         S.Kosaka        Update          QC#27726
 *</pre>
 */
public class NMAL2610Scrn00_DeleteRow_Resource extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(scrnMsg.D, NMAL2610Constant.CHKBOX_D, ZYPConstant.CHKBOX_ON_Y);
        if (checkList.size() <= 0) {
            for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
                scrnMsg.D.no(i).xxChkBox_D1.setErrorInfo(1, NMAL2610Constant.NZZM0011E);
            }
        }

        for (int index : checkList) {
            if (ZYPCommonFunc.hasValue(scrnMsg.D.no(index).gnrnTpCd_D1)) {
                // 2016/03/30 CSA-QC#5945 Delete Start
//                if (!GNRN_TP.FUTURE.equals(scrnMsg.D.no(index).gnrnTpCd_D1.getValue())) {
//                    scrnMsg.D.no(index).xxChkBox_D1.setErrorInfo(1, NMAL2610Constant.NMAM8230E);
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

        NMAL2610CommonLogic.addTabResourceCheckItem(scrnMsg);
        // 2016/03/04 S21_NA#4539 Add Start --------------
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {

            if (scrnMsg.D.no(i).effFromDt_D1.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.D.no(i).getErrorInfo("effFromDt_D1");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.D.no(i).effFromDt_D1.clearErrorInfo();
                }
            }

            scrnMsg.addCheckItem(scrnMsg.D.no(i).effFromDt_D1);
            scrnMsg.addCheckItem(scrnMsg.D.no(i).effThruDt_D1);
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
        if (scrnMsg.D.getValidCount() + 1 <= scrnMsg.D.length()) {
            setButtonEnabled(NMAL2610Constant.BTN_INSERT_ROW_RESOURCE, true);
        }
        if (scrnMsg.D.getValidCount() == 0) {
            setButtonEnabled(NMAL2610Constant.BTN_DELETE_ROW_RESOURCE, false);
        }
        NMAL2610CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.D, NMAL2610Constant.SCREEN_TABLE_NAME_RESRC);
        // 2018/09/21 QC#27726,ADD Add End
    }
}
