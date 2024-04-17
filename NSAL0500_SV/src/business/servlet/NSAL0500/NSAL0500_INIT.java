/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0500;

import static business.servlet.NSAL0500.constant.NSAL0500Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.Serializable;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0500.NSAL0500CMsg;
import business.servlet.NSAL0500.common.NSAL0500CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Sub Contract Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/07   Hitachi         T.Aoyagi        Create          N/A
 * 2016/02/10   Hitachi         K.Kasai         Update          QC#3704
 * 2017/12/22   Hitachi         Y.Takeno        Update          QC#22831
 * 2018/12/10   Hitachi         K.Fujimoto      Update          QC#29079
 *</pre>
 */
public class NSAL0500_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0500BMsg scrnMsg = (NSAL0500BMsg) bMsg;
        NSAL0500CMsg bizMsg = NSAL0500CommonLogic.createCMsgForSearch();

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;

            if (params.length == 1) {
                EZDBBigDecimalItem dsContrDtlPk = (EZDBBigDecimalItem) params[0];
                setValue(scrnMsg.dsContrDtlPk, dsContrDtlPk);

            } else {
                EZDBBigDecimalItem dsContrDtlPk = (EZDBBigDecimalItem) params[0];
                EZDBBigDecimalItem dsSubContrPk = (EZDBBigDecimalItem) params[1];

                setValue(scrnMsg.dsContrDtlPk, dsContrDtlPk);
                setValue(scrnMsg.dsSubContrPk, dsSubContrPk);
                // keep argument
                setValue(scrnMsg.dsSubContrPk_BK, dsSubContrPk);
            }
        } else {
            scrnMsg.dsContrDtlPk.clear();
            scrnMsg.dsSubContrPk.clear();
            scrnMsg.dsSubContrPk_BK.clear();
        }
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0500BMsg scrnMsg = (NSAL0500BMsg) bMsg;
        NSAL0500CMsg bizMsg = (NSAL0500CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL0500CommonLogic.screenControlProcess(this, scrnMsg);
        // add start 2016/02/10 CSA Defect#3704
        NSAL0500CommonLogic.protectItemBasedOnContractStatus(this, scrnMsg);
        // add end 2016/02/10 CSA Defect#3704
        scrnMsg.setFocusItem(scrnMsg.vndCd);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NSAL0500BMsg scrnMsg = (NSAL0500BMsg) bMsg;
        // START 2017/12/22 [QC#22831, MOD]
        scrnMsg.vndCd.setNameForMessage("Supplier Site");
        // END   2017/12/22 [QC#22831, MOD]
        scrnMsg.techTocCd.setNameForMessage("Technician Code");
        scrnMsg.effFromDt.setNameForMessage("Effective Date From");
        scrnMsg.effThruDt.setNameForMessage("Effective Date Thru");
        scrnMsg.svcCmntTxt_AD.setNameForMessage("Additional Comment");
        scrnMsg.basePrcDealAmt.setNameForMessage("Base($)");
        scrnMsg.adminPrcDealAmt.setNameForMessage("Admin($)");
        // Del Start 2018/12/10 K.Fujimoto QC#29079
        // scrnMsg.bwMtrAlwncCnt.setNameForMessage("BW Copies (Allowance)");
        // scrnMsg.bwMtrPrcAmtRate.setNameForMessage("BW Copies (Rate($))");
        // scrnMsg.colorMtrAlwncCnt.setNameForMessage("CLR Copies (Allowance)");
        // scrnMsg.colorMtrPrcAmtRate.setNameForMessage("CLR Copies (Rate($))");
        // Del End   2018/12/10 K.Fujimoto QC#29079
        // Add Start 2018/12/10 K.Fujimoto QC#29079
        for(int i = 0; i < scrnMsg.E.length(); i++){
            NSAL0500_EBMsg msg = scrnMsg.E.no(i);
            msg.mtrAlwncCnt_E0.setNameForMessage("Allowance");
            msg.prcMtrRate_E0.setNameForMessage("Rate($)");
        }
        // Add End   2018/12/10 K.Fujimoto QC#29079
        scrnMsg.dlrFleetNum.setNameForMessage("Fleet#");
    }
}
