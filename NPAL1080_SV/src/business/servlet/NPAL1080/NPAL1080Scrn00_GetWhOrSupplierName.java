/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1080;

import static business.servlet.NPAL1080.constant.NPAL1080Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.NPAM1329E;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1080.NPAL1080CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/12/2016   CITS            Hisashi         Create          N/A
 *</pre>
 */
public class NPAL1080Scrn00_GetWhOrSupplierName extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;

        int idx = this.getButtonSelectNumber();

        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).prntVndCd_D1)) {
            scrnMsg.A.no(idx).prntVndCd_D1.setErrorInfo(1, NPAM1329E, new String[] {"WH/Supplier Code", " Please select." });
            scrnMsg.addCheckItem(scrnMsg.A.no(idx).prntVndCd_D1);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;

        int idx = this.getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum_D1, new BigDecimal(idx));

        NPAL1080CMsg bizMsg = new NPAL1080CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
        NPAL1080CMsg bizMsg = (NPAL1080CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int idx = scrnMsg.xxNum_D1.getValueInt();

        scrnMsg.addCheckItem(scrnMsg.A.no(idx).prntVndCd_D1);
        scrnMsg.putErrorScreen();

        scrnMsg.setFocusItem(scrnMsg.A.no(idx).prntVndCd_D1);
    }
}
