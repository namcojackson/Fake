/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0870;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0870.NSAL0870CMsg;
import business.servlet.NSAL0870.common.NSAL0870CommonLogic;
import static business.servlet.NSAL0870.constant.NSAL0870Constant.*;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Meter Interface Status Inquiry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Hitachi         T.Mizuki        Create          N/A
 *</pre>
 */
public class NSAL0870Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0870BMsg scrnMsg = (NSAL0870BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.serNum_01);
        scrnMsg.addCheckItem(scrnMsg.mtrReadDt_FR);
        scrnMsg.addCheckItem(scrnMsg.mtrReadDt_TO);

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0870BMsg scrnMsg = (NSAL0870BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

        scrnMsg.mtrReadSrcTpCd_H.clear();
        scrnMsg.serNum_H.clear();
        scrnMsg.dsMtrProcStsCd_H.clear();
        scrnMsg.mtrReadDt_FH.clear();
        scrnMsg.mtrReadDt_TH.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.mtrReadSrcTpCd_H, scrnMsg.mtrReadSrcTpCd_1V);
        ZYPEZDItemValueSetter.setValue(scrnMsg.serNum_H, scrnMsg.serNum_01);
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsMtrProcStsCd_H, scrnMsg.dsMtrProcStsCd_1V);
        ZYPEZDItemValueSetter.setValue(scrnMsg.mtrReadDt_FH, scrnMsg.mtrReadDt_FR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.mtrReadDt_TH, scrnMsg.mtrReadDt_TO);

        NSAL0870CMsg bizMsg = new NSAL0870CMsg();
        bizMsg.setBusinessID("NSAL0870");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0870BMsg scrnMsg = (NSAL0870BMsg) bMsg;
        NSAL0870CMsg bizMsg = (NSAL0870CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        if (scrnMsg.A.getValidCount() > 0) {
            NSAL0870CommonLogic.protectFields(scrnMsg);
            NSAL0870CommonLogic.setRowColors(scrnMsg);
        }
        scrnMsg.putErrorScreen();
    }
}
