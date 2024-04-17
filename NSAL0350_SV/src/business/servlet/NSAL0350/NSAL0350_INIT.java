/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0350;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0350.NSAL0350CMsg;
import business.servlet.NSAL0350.common.NSAL0350CommonLogic;
import business.servlet.NSAL0350.constant.NSAL0350Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/10   CUSA            Fujitsu         Create          N/A
 * 2015/10/15   Hitachi         A.Kohinata      Update          N/A
 * 2017/08/07   Hitachi         K.Kitachi       Update          QC#20048
 *</pre>
 */
public class NSAL0350_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), NSAL0350Constant.BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0350BMsg scrnMsg = (NSAL0350BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length > 0) {
            // START 2015/10/15 [N/A, MOD]
            if (params.length == 3) {
                EZDBBigDecimalItem param01 = (EZDBBigDecimalItem) params[0];
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrDtlPk_H1, param01);

                EZDBBigDecimalItem param02 = (EZDBBigDecimalItem) params[1];
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrPrcEffPk_H1, param02);

                EZDBStringItem param03 = (EZDBStringItem) params[2];
                ZYPEZDItemValueSetter.setValue(scrnMsg.svcInvChrgTpCd_H1, param03);
            }
            // END 2015/10/15 [N/A, MOD]
        }

        NSAL0350CMsg bizMsg = new NSAL0350CMsg();
        bizMsg.setBusinessID(NSAL0350Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0350BMsg scrnMsg = (NSAL0350BMsg) bMsg;
        NSAL0350CMsg bizMsg = (NSAL0350CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0350CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg, bizMsg.getUserID());
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL0350BMsg scrnMsg = (NSAL0350BMsg) bMsg;

        // Header
        scrnMsg.dsContrNum_H1.setNameForMessage("Contract#");
        scrnMsg.serNum_H1.setNameForMessage("Serial#");
        // START 2017/08/07 K.Kitachi [QC#20048, ADD]
        scrnMsg.bllgSchdFromDt_HD.setNameForMessage("Bill From Date");
        scrnMsg.bllgSchdThruDt_HD.setNameForMessage("Bill To Date");
        scrnMsg.dsBllgSchdStsCd_HD.setNameForMessage("Schedule Status");
        scrnMsg.skipRecovTpCd_HD.setNameForMessage("Skip Type");
        // END 2017/08/07 K.Kitachi [QC#20048, ADD]

        for (int i = 0; i < scrnMsg.O.length(); i++) {
            scrnMsg.O.no(i).skipRecovTpCd_O3.setNameForMessage("Skip Type");
        }

        for (String tblNm : NSAL0350Constant.USAGE_TBL_NM_ARRAY) {
            EZDMsgArray tblArray = NSAL0350CommonLogic.getTableArrayFromEZDMsg(scrnMsg, tblNm);
            for (int i = 0; i < tblArray.length(); i++) {
                EZDMsg ezdMsg = tblArray.get(i);
                NSAL0350CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "skipRecovTpCd_A3").setNameForMessage("Skip Type");
            }
        }
    }
}
