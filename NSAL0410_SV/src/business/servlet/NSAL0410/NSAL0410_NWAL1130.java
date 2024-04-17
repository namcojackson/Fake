/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0410;

import static business.servlet.NSAL0410.constant.NSAL0410Constant.BIZ_ID;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0410.NSAL0410CMsg;
import business.servlet.NSAL0410.common.NSAL0410CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2019/01/29   Hitachi         Y.Takeno        Create          QC#29949
 * 2019/02/01   Hitachi         K.Kitachi       Update          QC#29949
 * 2019/02/15   Hitachi         K.Kitachi       Update          QC#29949
 *</pre>
 */
public class NSAL0410_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0410BMsg scrnMsg = (NSAL0410BMsg) bMsg;

        // for Delete Event
        scrnMsg.xxPgFlg_DE.clear();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0410BMsg scrnMsg = (NSAL0410BMsg) bMsg;

        NSAL0410CMsg bizMsg = new NSAL0410CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        // Select Row Number
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRowNum_H, new BigDecimal(getButtonSelectNumber()));
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0410BMsg scrnMsg = (NSAL0410BMsg) bMsg;
        NSAL0410CMsg bizMsg = (NSAL0410CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2019/02/01 K.Kitachi [QC#29949, ADD]
        if (!"CMN_Close".equals(getLastGuard())) {
            int aBMsgIdx = getButtonSelectNumber();
            int zBMsgIdx = 0;
            // START 2019/02/15 K.Kitachi [QC#29949, MOD]
            // Serial#
            String serNum = scrnMsg.Z.no(zBMsgIdx++).xxComnScrColValTxt.getValue();
            // Machine Master
            String strSvcMachMstrPk = scrnMsg.Z.no(zBMsgIdx++).xxComnScrColValTxt.getValue();
            if (ZYPCommonFunc.hasValue(strSvcMachMstrPk)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(aBMsgIdx).svcMachMstrPk_A, new BigDecimal(strSvcMachMstrPk));
            } else {
                scrnMsg.A.no(aBMsgIdx).svcMachMstrPk_A.clear();
            }
            // Service Program
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(aBMsgIdx).mdseDescShortTxt_A, scrnMsg.Z.no(zBMsgIdx++).xxComnScrColValTxt);
            // Start
            zBMsgIdx++;
            // End
            zBMsgIdx++;
            // DS_CONTR_DTL_PK (hidden)
            String strDsContrDtlPk = scrnMsg.Z.no(zBMsgIdx++).xxComnScrColValTxt.getValue();
            if (ZYPCommonFunc.hasValue(strDsContrDtlPk)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(aBMsgIdx).dsContrDtlPk_A, new BigDecimal(strDsContrDtlPk));
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(aBMsgIdx).xxScrItem34Txt_A, serNum);
            } else {
                scrnMsg.A.no(aBMsgIdx).dsContrDtlPk_A.clear();
                scrnMsg.A.no(aBMsgIdx).xxScrItem34Txt_A.clear();
            }
            // LV (hidden)
            zBMsgIdx++;
            // CONTR_EFF_FROM_DT (hidden)
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(aBMsgIdx).effFromDt_A, scrnMsg.Z.no(zBMsgIdx++).xxComnScrColValTxt.getValue());
            // CONTR_EFF_THRU_DT (hidden)
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(aBMsgIdx).effThruDt_A, scrnMsg.Z.no(zBMsgIdx++).xxComnScrColValTxt.getValue());
            // END 2019/02/15 K.Kitachi [QC#29949, MOD]
        }
        // END 2019/02/01 K.Kitachi [QC#29949, ADD]

        NSAL0410CommonLogic.initialControlScreen(this, scrnMsg, bizMsg.getUserID(), getGlobalCompanyCode(), false);
        scrnMsg.setFocusItem(scrnMsg.A.no(getButtonSelectNumber()).xxScrItem34Txt_A);

        // has error
        if (scrnMsg.getMessageCode().endsWith("E")) {
            throw new EZDFieldErrorException();
        }
    }
}
