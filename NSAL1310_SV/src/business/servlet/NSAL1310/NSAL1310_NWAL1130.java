/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1310;

import static business.servlet.NSAL1310.constant.NSAL1310Constant.*;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1310.NSAL1310CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TERM_ATTRB_DATA_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/31   Hitachi         K.Kojima        Create          QC#15136
 * 2018/06/25   Hitachi         A.Kohinata      Update          QC#17369
 *</pre>
 */
public class NSAL1310_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        if ("CMN_Close".equals(getLastGuard())) {
            return null;
        }

        NSAL1310BMsg scrnMsg = (NSAL1310BMsg) bMsg;

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if ("OpenWin_CondAttrb".equals(scrEventNm)) {
            int selectNumber = getButtonSelectNumber();
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectNumber).svcTermAttrbDescTxt_A1, scrnMsg.Z.no(0).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectNumber).svcTermAttrbSortNum_A1, new BigDecimal(scrnMsg.Z.no(1).xxComnScrColValTxt.getValue()));
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectNumber).svcTermCondCatgDescTxt_A1, scrnMsg.Z.no(2).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectNumber).svcTermCondAttrbPk_A1, new BigDecimal(scrnMsg.Z.no(4).xxComnScrColValTxt.getValue()));
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum, BigDecimal.valueOf(selectNumber));

            NSAL1310CMsg bizMsg = new NSAL1310CMsg();
            bizMsg.setBusinessID(BUSINESS_ID);
            bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }
        NSAL1310BMsg scrnMsg = (NSAL1310BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        // mod start 2018/06/25 QC#17369
        if ("OpenWin_CondAttrb".equals(scrEventNm)) {
            NSAL1310CMsg bizMsg = (NSAL1310CMsg) cMsg;
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
            int selectNumber = getButtonSelectNumber();
            if (SVC_TERM_ATTRB_DATA_TP.LOOKUP_POPUP.equals(scrnMsg.A.no(selectNumber).svcTermDataTpCd_A1.getValue())) {
                scrnMsg.A.no(selectNumber).svcTermAttrbDefValTxt_A1.setInputProtected(true);
            } else {
                scrnMsg.A.no(selectNumber).svcTermAttrbDefValTxt_A1.setInputProtected(false);
            }
            scrnMsg.setFocusItem(scrnMsg.A.no(selectNumber).svcTermAttrbDescTxt_A1);
        } else if ("OpenWin_DefValue".equals(scrEventNm)) {
            int selectNumber = getButtonSelectNumber();
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectNumber).svcTermCondDataValCd_L1, scrnMsg.Z.no(0).xxComnScrColValTxt.getValue());
            if (ZYPCommonFunc.hasValue(scrnMsg.Z.no(1).xxComnScrColValTxt)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectNumber).svcTermAttrbDefValTxt_A1, scrnMsg.Z.no(1).xxComnScrColValTxt.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectNumber).svcTermAttrbDefValTxt_A1, scrnMsg.Z.no(0).xxComnScrColValTxt.getValue());
            }
            scrnMsg.setFocusItem(scrnMsg.A.no(selectNumber).svcTermAttrbDefValTxt_A1);
        }
        // mod end 2018/06/25 QC#17369
    }
}
