/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0830;

import static business.servlet.NSAL0830.common.NSAL0830CommonLogic.initialControlScreen;
import static business.servlet.NSAL0830.constant.NSAL0830Constant.BUSINESS_ID;
import static business.servlet.NSAL0830.constant.NSAL0830Constant.FUNCTION_SEARCH;
import static business.servlet.NSAL0830.constant.NSAL0830Constant.MODE_AFTER_SEARCH;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBMsgArray;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0830.NSAL0830CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/21   Hitachi         Y.Takeno       Create          N/A
 * 2016/03/23   Hitachi         Y.Takeno       Update          QC#5450
 * 2016/03/30   Hitachi         T.Iwamoto       Update          QC#5541
 * 2016/09/01   Hitachi         Y.Zhang        Update          QC#11846
 * 2016/09/23   Hitachi         Y.Zhang        Update          QC#12582
 *</pre>
 */
public class NSAL0830_INIT extends S21INITCommonHandler {


    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0830BMsg scrnMsg = (NSAL0830BMsg) bMsg;
        Serializable ser = getArgForSubScreen();
        if (ser instanceof Object[]) {
            Object[] prm = (Object[]) ser;
            if (prm.length > 0 && prm[0] != null && prm[0] instanceof EZDBMsgArray) {
                EZDMsg.copy(((EZDBMsgArray) prm[0]), null, scrnMsg.T, null);
            }
        }

        scrnMsg.P.setValidCount(scrnMsg.T.getValidCount());
        for (int i = 0; i < scrnMsg.T.getValidCount(); i++) {
            setValue(scrnMsg.P.no(i).dsContrIntfcPk, scrnMsg.T.no(i).dsContrIntfcPk_T);
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrIntfcBatNum_S, scrnMsg.T.no(0).dsContrIntfcBatNum_T);
        // ADD start 2016/03/30 CSA Defect#5541
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrSrcRefNum_S, scrnMsg.T.no(0).dsContrSrcRefNum_T);
        // ADD end 2016/03/30 CSA Defect#5541
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd, MODE_AFTER_SEARCH);

        NSAL0830CMsg bizMsg = new NSAL0830CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0830BMsg scrnMsg = (NSAL0830BMsg) bMsg;
        NSAL0830CMsg bizMsg = (NSAL0830CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2016/09/01 Y.Zhang [QC#11846, MOD]
        // because focus set add,process execute sort change
        scrnMsg.putErrorScreen();
        initialControlScreen(this, scrnMsg);
        // END 2016/09/01 Y.Zhang [QC#11846, MOD]
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL0830BMsg scrnMsg = (NSAL0830BMsg) bMsg;
        scrnMsg.xxPageShowCurNum.setNameForMessage(S21BatchSearchPagenationScrnSupport.getCurrentPageFieldName());

        // START 2016/03/23 [QC#5450, ADD]
        scrnMsg.contrIntfcSrcTpCd_SS.setNameForMessage("Source Type");
        scrnMsg.dsContrIntfcBatNum_S.setNameForMessage("Interface Bat#");
        scrnMsg.dsContrSrcRefNum_S.setNameForMessage("Source Ref#");
        scrnMsg.dsContrNum_S.setNameForMessage("Contract#");
        scrnMsg.xxErrFlg_S.setNameForMessage("Errors Only");
        // END  2016/03/23 [QC#5450, ADD]
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).dsContrSrcRefNum_A.setNameForMessage("Source Ref#");
            scrnMsg.A.no(i).contrIntfcSrcTpCd_A.setNameForMessage("Source Type");
            scrnMsg.A.no(i).serNum_A.setNameForMessage("Serial# ");
            scrnMsg.A.no(i).svcMachMstrPk_A.setNameForMessage("IB ID");
            // START 2016/09/23 Y.Zhang [QC#12582, MOD]
            scrnMsg.A.no(i).mdseCd_A.setNameForMessage("Item Code");
            // END 2016/09/23 Y.Zhang [QC#12582, MOD]
            scrnMsg.A.no(i).xsMtrCopyQty_A.setNameForMessage("Allowance");
            scrnMsg.A.no(i).xsMtrAmtRate_A.setNameForMessage("Price");
        }
    }
}
