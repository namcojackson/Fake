/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLEL0060;

import static business.servlet.NLEL0060.constant.NLEL0060Constant.OPENWIN_COA_MDSE_TP_CD;
import static business.servlet.NLEL0060.constant.NLEL0060Constant.OPENWIN_VENDER;
import static business.servlet.NLEL0060.constant.NLEL0060Constant.OPENWIN_VND_CODE;
import static business.servlet.NLEL0060.constant.NLEL0060Constant.OPENWIN_SR;
import static business.servlet.NLEL0060.constant.NLEL0060Constant.BIZ_ID;
import static business.servlet.NLEL0060.constant.NLEL0060Constant.FUNC_CD_SRCH;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NLEL0060.NLEL0060CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NLEL0060_NWAL1130
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/10   Fujitsu         C.Tanaka        Create          N/A
 * 2016/06/03   Hitachi         T.Tsuchida      Update          QC#8981
 * 2016/09/14   Fujitsu         C.Tanaka        Update          QC#12697
 * 2017/05/15   CITS            M.Naito         Update          Merge DS Lv2
 * 2018/05/17   Hitachi         J.Kim           Update          QC#25879
 * 2018/08/28   Fujitsu         S.Ohki          Update          QC#28000
 *</pre>
 */
public class NLEL0060_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLEL0060BMsg scrnMsg = (NLEL0060BMsg) bMsg;

        if (OPENWIN_SR.equals(scrnMsg.P.no(0).xxPopPrm.getValue())) {

            if ("CMN_Close".equals(getLastGuard())) {
                return null;
            }

            NLEL0060CMsg bizMsg = new NLEL0060CMsg();
            bizMsg.setBusinessID(BIZ_ID);
            bizMsg.setFunctionCode(FUNC_CD_SRCH);

            int idx = getButtonSelectNumber();
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum, BigDecimal.valueOf(idx));
            ZYPEZDItemValueSetter.setValue(scrnMsg.psnNum, scrnMsg.Q.no(0).xxComnScrColValTxt); // 2018/08/28 S.Ohki [QC#28000,MOD] slsRepTocCd -> psnNum
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

        NLEL0060BMsg scrnMsg = (NLEL0060BMsg) bMsg;

        if (OPENWIN_VENDER.equals(scrnMsg.P.no(0).xxPopPrm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.vndCd_H1, scrnMsg.Q.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.vndNm_H1, scrnMsg.Q.no(1).xxComnScrColValTxt);
            scrnMsg.setFocusItem(scrnMsg.vndCd_H1);
        } else if (OPENWIN_VND_CODE.equals(scrnMsg.P.no(0).xxPopPrm.getValue())) {
            int idx = getButtonSelectNumber();
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).vndCd_A1, scrnMsg.Q.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).prntVndNm_A1, scrnMsg.Q.no(1).xxComnScrColValTxt);
            scrnMsg.setFocusItem(scrnMsg.A.no(idx).vndCd_A1);
        } else if (OPENWIN_COA_MDSE_TP_CD.equals(scrnMsg.P.no(0).xxPopPrm.getValue())) {
            int idx = getButtonSelectNumber();
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).coaMdseTpCd_A1, scrnMsg.Q.no(0).xxComnScrColValTxt);
            scrnMsg.setFocusItem(scrnMsg.A.no(idx).coaMdseTpCd_A1);
         // START 2018/05/17 J.Kim [QC#25879,ADD]
        } else if (OPENWIN_SR.equals(scrnMsg.P.no(0).xxPopPrm.getValue())) {

            NLEL0060CMsg bizMsg = (NLEL0060CMsg) cMsg;
            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            int idx = getButtonSelectNumber();
            // START 2018/08/28 S.Ohki [QC#28000,MOD]
//            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).slsRepTocCd_B, scrnMsg.Q.no(0).xxComnScrColValTxt);
//            scrnMsg.setFocusItem(scrnMsg.B.no(idx).slsRepTocCd_B);
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).psnNum_B, scrnMsg.Q.no(0).xxComnScrColValTxt);
            scrnMsg.setFocusItem(scrnMsg.B.no(idx).psnNum_B);
            // END 2018/08/28 S.Ohki [QC#28000,MOD]
        }
        // END 2018/05/17 J.Kim [QC#25879,ADD]
    }
}
