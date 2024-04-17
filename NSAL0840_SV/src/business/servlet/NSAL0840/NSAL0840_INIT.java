/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0840;

import static business.servlet.NSAL0840.common.NSAL0840CommonLogic.initialControlScreen;
import static business.servlet.NSAL0840.constant.NSAL0840Constant.BUSINESS_ID;
import static business.servlet.NSAL0840.constant.NSAL0840Constant.FUNCTION_SEARCH;
import static business.servlet.NSAL0840.constant.NSAL0840Constant.MODE_AFTER_SEARCH;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBMsgArray;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0840.NSAL0840CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Hitachi         Y.Takeno        Create          N/A
 * 2016/03/30   Hitachi         T.Iwamoto       Update          QC#5541
 * 2016/09/01   Hitachi         Y.Zhang         Update          QC#11846
 * 2016/09/23   Hitachi         Y.Zhang         Update          QC#12582
 *</pre>
 */
public class NSAL0840_INIT extends S21INITCommonHandler {


    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0840BMsg scrnMsg = (NSAL0840BMsg) bMsg;
        Serializable ser = getArgForSubScreen();
        if (ser instanceof Object[]) {
            Object[] prm = (Object[]) ser;
            if (prm != null) {
                if (prm.length > 0 && prm[0] != null && prm[0] instanceof EZDBMsgArray) {
                    EZDMsg.copy(((EZDBMsgArray) prm[0]), null, scrnMsg.T, null);
                }
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

        NSAL0840CMsg bizMsg = new NSAL0840CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0840BMsg scrnMsg = (NSAL0840BMsg) bMsg;
        NSAL0840CMsg bizMsg = (NSAL0840CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2016/09/01 Y.Zhang [QC#11846, MOD]
        // because focus set add,process execute sort change
        scrnMsg.putErrorScreen();
        initialControlScreen(this, scrnMsg);
        // END 2016/09/01 Y.Zhang [QC#11846, MOD]
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL0840BMsg scrnMsg = (NSAL0840BMsg) bMsg;
        scrnMsg.xxPageShowCurNum.setNameForMessage(S21BatchSearchPagenationScrnSupport.getCurrentPageFieldName());
        scrnMsg.contrIntfcSrcTpCd_SS.setNameForMessage("Source Type");
        scrnMsg.dsContrIntfcBatNum_S.setNameForMessage("Interface Bat#");
        scrnMsg.dsContrSrcRefNum_S.setNameForMessage("Source Ref#");
        scrnMsg.dsContrNum_S.setNameForMessage("Contract#");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).dsContrSrcRefNum_A.setNameForMessage("Source Ref#");
            scrnMsg.A.no(i).contrIntfcSrcTpCd_A.setNameForMessage("Source Type");
            scrnMsg.A.no(i).dsContrNum_A.setNameForMessage("Contract#");
            scrnMsg.A.no(i).serNum_A.setNameForMessage("Serial#");
            scrnMsg.A.no(i).svcMachMstrPk_A.setNameForMessage("IB ID");
            // START 2016/09/23 Y.Zhang [QC#12582, MOD]
            scrnMsg.A.no(i).mdseCd_A.setNameForMessage("Item Code");
            // END 2016/09/23 Y.Zhang [QC#12582, MOD]
            scrnMsg.A.no(i).chrgLvlTpCd_A.setNameForMessage("Charge Level");
            scrnMsg.A.no(i).addlChrgTpCd_A.setNameForMessage("Charge Type");
            scrnMsg.A.no(i).effFromDt_A.setNameForMessage("Start Date");
            scrnMsg.A.no(i).effThruDt_A.setNameForMessage("End Date");
            scrnMsg.A.no(i).bllgCycleCd_A.setNameForMessage("Frequency");
            scrnMsg.A.no(i).addlChrgFlatDealPrcAmt_A.setNameForMessage("Flat Rate");
            scrnMsg.A.no(i).addlChrgAplcPct_A.setNameForMessage("Applicable%");
            scrnMsg.A.no(i).addlChrgInvTpCd_A.setNameForMessage("Invoice Type");
            scrnMsg.A.no(i).printDtlFlg_A.setNameForMessage("Print on Invoice");
            scrnMsg.A.no(i).intfcErrMsgTxt_A.setNameForMessage("Proc Message");
        }
    }
}
