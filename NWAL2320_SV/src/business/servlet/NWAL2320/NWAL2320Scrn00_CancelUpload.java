/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2320;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NWAL2320.NWAL2320CMsg;
//import business.servlet.NWAL2320.constant.NWAL2320Constant;

import business.blap.NWAL2320.NWAL2320CMsg;
import business.servlet.NWAL2320.constant.NWAL2320Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/25/2016   Fujitsu         M.Hara          Create          N/A
 *</pre>
 */
public class NWAL2320Scrn00_CancelUpload extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2320BMsg scrnMsg = (NWAL2320BMsg) bMsg;

        NWAL2320CMsg bizMsg = new NWAL2320CMsg();
        bizMsg.setBusinessID(NWAL2320Constant.BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2320BMsg scrnMsg = (NWAL2320BMsg) bMsg;
        //NWAL2320CMsg bizMsg  = (NWAL2320CMsg) cMsg;

        //EZDMsg.copy(bizMsg, null, scrnMsg, null);

        ZYPTableUtil.clear(scrnMsg.A);
        ZYPTableUtil.clear(scrnMsg.B);
        ZYPTableUtil.clear(scrnMsg.C);
        ZYPTableUtil.clear(scrnMsg.D);

//        scrnMsg.A.clear();
//        scrnMsg.A.setValidCount(0);
//        scrnMsg.B.clear();
//        scrnMsg.B.setValidCount(0);
//        scrnMsg.C.clear();
//        scrnMsg.C.setValidCount(0);
//        scrnMsg.D.clear();
//        scrnMsg.D.setValidCount(0);

        scrnMsg.ordUpldTmplTpCd.setInputProtected(false);
        scrnMsg.xxFileData_UL.setInputProtected(false);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxErrNum_UP, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxErrNum_UL, BigDecimal.ZERO);

        this.setButtonEnabled(NWAL2320Constant.BTN_UPLOAD, true);
        this.setButtonEnabled(NWAL2320Constant.BTN_UPLOAD_LINES, false);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPageShowFromNum_CM, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPageShowToNum_CM, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPageShowOfNum_CM, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPageShowCurNum_CM, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPageShowTotNum_CM, BigDecimal.ZERO);

    }
}
