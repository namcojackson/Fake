/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2010;

import static business.servlet.NWAL2010.constant.NWAL2010Constant.BIZ_ID;
import static business.servlet.NWAL2010.constant.NWAL2010Constant.BTN_RETURN;
import static business.servlet.NWAL2010.constant.NWAL2010Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NWAL2010.constant.NWAL2010Constant.MODE_READ_ONLY;

import java.io.Serializable;
import java.math.BigDecimal;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2010.NWAL2010CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2010Scrn00_CMN_Return
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/06   Fujitsu         C.Yokoi         Create          N/A
 * 2016/09/23   Fujitsu         R.Nakamura      Update          QC#13958
 *</pre>
 */
public class NWAL2010Scrn00_CMN_Return extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2010BMsg scrnMsg = (NWAL2010BMsg) bMsg;

        // Add Start 2016/09/23 QC#13958
        if (MODE_READ_ONLY.equals(scrnMsg.xxModeCd_LK.getValue())) {
            return null;
        }
        // Add End 2016/09/23 QC#13958

        if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.acctCdFlg_LK.getValue())) {
            return null;
        }

        scrnMsg.xxScrEventNm.setValue(BTN_RETURN);

        NWAL2010CMsg bizMsg = new NWAL2010CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2010BMsg scrnMsg = (NWAL2010BMsg) bMsg;
        NWAL2010CMsg bizMsg = (NWAL2010CMsg) cMsg;

        // Add Start 2016/09/23 QC#13958
        if (MODE_READ_ONLY.equals(scrnMsg.xxModeCd_LK.getValue())) {
            return;
        }
        // Add End 2016/09/23 QC#13958

        if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.acctCdFlg_LK.getValue())) {
            return;
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxErrFlg.getValue())) {
            if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
                throw new EZDFieldErrorException();
            }
        }

        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            EZDBBigDecimalItem param12 = (EZDBBigDecimalItem) params[12];
//            int idxOfSelectedRow = scrnMsg.xxRadioBtn_A.getValueInt();
            BigDecimal idxOfSelectedRow = scrnMsg.xxRadioBtn_A.getValue();
            BigDecimal dsCrCardPk = null;
            if (idxOfSelectedRow != null) {
//                ZYPEZDItemValueSetter.setValue(param12, scrnMsg.A.no(idxOfSelectedRow.intValue()).dsCrCardPk_A);
                dsCrCardPk = scrnMsg.A.no(idxOfSelectedRow.intValue()).dsCrCardPk_A.getValue();
            } else {
                dsCrCardPk = scrnMsg.dsCrCardPk_SL.getValue();
            }

            if (dsCrCardPk != null) {
                ZYPEZDItemValueSetter.setValue(param12, dsCrCardPk);
            }
        }
    }
}
