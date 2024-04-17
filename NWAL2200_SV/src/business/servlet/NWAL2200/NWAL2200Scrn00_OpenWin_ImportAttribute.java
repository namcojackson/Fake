/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2200;

import static business.servlet.NWAL2200.constant.NWAL2200Constant.NWAM0666E;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.TAB_LINE_CONFIG;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NWAL2200.common.NWAL2200CommonLogicForScreenFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2200Scrn00_OpenWin_ImportAttribute
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         T.Ishii         Create          N/A
 *</pre>
 */
public class NWAL2200Scrn00_OpenWin_ImportAttribute extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;

        NWAL2200CommonLogicForScreenFields.addCheckItem(scrnMsg, false, scrnMsg.xxDplyTab.getValue());
        scrnMsg.putErrorScreen();

        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {

            List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);

            if (selectedRows.size() > 1) {
                scrnMsg.setMessageInfo(NWAM0666E, new String[] {"Component Line" });
                throw new EZDFieldErrorException();
            }
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;
        ZYPTableUtil.clear(scrnMsg.P);
        scrnMsg.dsImptOrdDtlPk_IA.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm, ZYPConstant.FLG_OFF_N);

        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {

            List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);

            if (selectedRows.size() == 1) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsImptOrdDtlPk_IA, scrnMsg.B.no(selectedRows.get(0)).dsImptOrdDtlPk_LL);
                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, scrnMsg.B.no(selectedRows.get(0)).ordSrcRefLineNum_LL);
                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm, scrnMsg.B.no(selectedRows.get(0)).ordSrcRefLineSubNum_LL);
            }
        }

        Object[] params = new Object[7];
        params[0] = scrnMsg.ordSrcRefNum;
        params[1] = scrnMsg.cpoSrcTpCd;
        params[2] = scrnMsg.dsImptOrdPk;
        params[3] = scrnMsg.P.no(1).xxPopPrm; // read only flag
        params[4] = scrnMsg.dsImptOrdDtlPk_IA;
        params[5] = scrnMsg.P.no(2).xxPopPrm; // line number
        params[6] = scrnMsg.P.no(3).xxPopPrm; // line sub number

        setArgForSubScreen(params);
    }
}
