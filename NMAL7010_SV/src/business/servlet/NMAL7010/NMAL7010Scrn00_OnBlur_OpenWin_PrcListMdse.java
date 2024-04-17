/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7010;

import static business.servlet.NMAL7010.constant.NMAL7010Constant.BIZ_ID;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.ZZM9000E;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7010.NMAL7010CMsg;
import business.servlet.NMAL7010.common.NMAL7010CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/25   Fujitsu         R.Nakamura      Create          QC#3934
 *</pre>
 */
public class NMAL7010Scrn00_OnBlur_OpenWin_PrcListMdse extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;
        int idx = getButtonSelectNumber();

        scrnMsg.addCheckItem(scrnMsg.B.no(idx).prcListMdseCd_PB);

        if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(idx).prcListMdseCd_PB)) {
            scrnMsg.B.no(idx).prcListMdseCd_PB.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.B.no(idx).prcListMdseCd_PB.getNameForMessage() });
            scrnMsg.addCheckItem(scrnMsg.B.no(idx).prcListMdseCd_PB);
        }

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;

        int idx = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx, new BigDecimal(idx));

        NMAL7010CMsg bizMsg = new NMAL7010CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;
        NMAL7010CMsg bizMsg = (NMAL7010CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        setResult(ZYPConstant.FLG_OFF_N);

        int idx = getButtonSelectNumber();

        scrnMsg.addCheckItem(scrnMsg.B.no(idx).prcListMdseCd_PB);
        scrnMsg.putErrorScreen();

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg_H1.getValue())) {
            setResult(ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm_DH, "OpenWin_PrcListMdse");
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx, new BigDecimal(idx));

            setArgForSubScreen(NMAL7010CommonLogic.setArgumentNMAL6800(scrnMsg, scrnMsg.xxScrEventNm_DH.getValue(), idx));

            return;
        }

        scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcSvcPlnTpCd_PB);
        scrnMsg.xxScrEventNm_DH.clear();
        scrnMsg.xxCellIdx.clear();

    }
}
