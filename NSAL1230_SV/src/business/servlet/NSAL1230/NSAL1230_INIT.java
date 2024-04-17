/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1230;

import static business.servlet.NSAL1230.constant.NSAL1230Constant.*;

import java.io.Serializable;
import java.math.BigDecimal;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1230.NSAL1230CMsg;
import business.servlet.NSAL1230.common.NSAL1230CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/10   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSAL1230_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);

        Object[] params = (Object[]) getArgForSubScreen();
        if (params == null || params.length < PARAM_ARRAY_LENGTH_NSAL1230) {
            bMsg.setMessageInfo(NSAM0353E, new String[] {MSG_PARAM_NO_INPUT_PARAM });
            bMsg.putErrorScreen();
            return;

        }

        for (int i = 0; i < params.length; i++) {
            if (params[i] == null) {
                bMsg.setMessageInfo(NSAM0353E, new String[] {MSG_PARAM_NO_INPUT_PARAM });
                bMsg.putErrorScreen();
                return;
            }
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1230BMsg scrnMsg = (NSAL1230BMsg) bMsg;

        BigDecimal dsContrPk = null;
        BigDecimal dsContrDtlPk = null;
        String svcInvChrgTpCd = null;
        String coaCmpyCd = null;
        String coaExtnCd = null;
        String coaBrCd = null;
        String coaCcCd = null;
        String coaAcctCd = null;
        String coaProjCd = null;
        String coaProdCd = null;
        String coaAfflCd = null;
        String coaChCd = null;
        String xxModeCd = null;
        Serializable ser = getArgForSubScreen();

        if (ser instanceof Object[]) {
            Object[] prm = (Object[]) ser;
            if (prm != null && prm.length >= PARAM_ARRAY_LENGTH_NSAL1230) {
                int idx = 0;

                dsContrPk = ((EZDBBigDecimalItem) prm[idx++]).getValue();
                dsContrDtlPk = ((EZDBBigDecimalItem) prm[idx++]).getValue();
                svcInvChrgTpCd = ((EZDBStringItem) prm[idx++]).getValue();
                coaCmpyCd = ((EZDBStringItem) prm[idx++]).getValue();
                coaExtnCd = ((EZDBStringItem) prm[idx++]).getValue();
                coaBrCd = ((EZDBStringItem) prm[idx++]).getValue();
                coaCcCd = ((EZDBStringItem) prm[idx++]).getValue();
                coaAcctCd = ((EZDBStringItem) prm[idx++]).getValue();
                coaProjCd = ((EZDBStringItem) prm[idx++]).getValue();
                coaProdCd = ((EZDBStringItem) prm[idx++]).getValue();
                coaAfflCd = ((EZDBStringItem) prm[idx++]).getValue();
                coaChCd = ((EZDBStringItem) prm[idx++]).getValue();
                xxModeCd = ((EZDBStringItem) prm[idx++]).getValue();
            }
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrPk, dsContrPk);
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrDtlPk, dsContrDtlPk);

        ZYPEZDItemValueSetter.setValue(scrnMsg.svcInvChrgTpCd, svcInvChrgTpCd);

        ZYPEZDItemValueSetter.setValue(scrnMsg.coaCmpyCd, coaCmpyCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.coaExtnCd, coaExtnCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.coaBrCd, coaBrCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.coaCcCd, coaCcCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.coaAcctCd, coaAcctCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.coaProjCd, coaProjCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.coaProdCd, coaProdCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.coaAfflCd, coaAfflCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.coaChCd, coaChCd);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd, xxModeCd);

        NSAL1230CMsg bizMsg = new NSAL1230CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUCNTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1230BMsg scrnMsg = (NSAL1230BMsg) bMsg;
        NSAL1230CMsg bizMsg = (NSAL1230CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL1230CommonLogic.initialControlScreen(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NSAL1230BMsg scrnMsg = (NSAL1230BMsg) bMsg;

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).coaAfflAcctNm_A1.setNameForMessage("Segment");
            scrnMsg.A.no(i).prcAllocRate_A1.setNameForMessage("Percent");
        }
    }
}
