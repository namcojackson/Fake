/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1390;

import static business.servlet.NSAL1390.constant.NSAL1390Constant.PRMS_00;
import static business.servlet.NSAL1390.constant.NSAL1390Constant.PRMS_01;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/21   Fujitsu         T.Murai         Create          S21_NA#8661(Sol#004)
 *</pre>
 */
public class NSAL1390_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NSAL1390BMsg scrnMsg = (NSAL1390BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        int selectIndex = scrnMsg.xxCellIdx.getValueInt();

        if ("OpenWin_Region".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.svcRgPk_H, new BigDecimal(scrnMsg.Y.no(PRMS_00).xxComnScrColValTxt.getValue()));
            ZYPEZDItemValueSetter.setValue(scrnMsg.svcRgDescTxt_H, scrnMsg.Y.no(PRMS_01).xxComnScrColValTxt.getValue());

        } else if ("OpenWin_RegionLine".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectIndex).svcRgPk_A, new BigDecimal(scrnMsg.Y.no(PRMS_00).xxComnScrColValTxt.getValue()));
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectIndex).svcRgDescTxt_A, scrnMsg.Y.no(PRMS_01).xxComnScrColValTxt.getValue());

        } else if ("OpenWin_Branch".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.svcContrBrCd_H, scrnMsg.Y.no(PRMS_00).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.svcContrBrDescTxt_H, scrnMsg.Y.no(PRMS_01).xxComnScrColValTxt.getValue());

        } else if ("OpenWin_BranchLine".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectIndex).svcContrBrCd_A, scrnMsg.Y.no(PRMS_00).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectIndex).svcContrBrDescTxt_A, scrnMsg.Y.no(PRMS_01).xxComnScrColValTxt.getValue());
        }

        scrnMsg.xxCellIdx.clear();
    }
}
