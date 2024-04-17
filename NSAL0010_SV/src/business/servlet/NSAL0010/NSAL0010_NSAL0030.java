/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0010.NSAL0010CMsg;
import business.servlet.NSAL0010.common.NSAL0010CommonLogic;
import business.servlet.NSAL0010.constant.NSAL0010Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NSAL0010_NSAL0030 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
//        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
//
//        NSAL0010CMsg bizMsg = null;
//        if (ZYPCommonFunc.hasValue(scrnMsg.svcMachMstrPk_H1) && ZYPCommonFunc.hasValue(scrnMsg.serNum_H1) && ZYPCommonFunc.hasValue(scrnMsg.mdseCd_H1)) {
//            scrnMsg.xxSetFlg_MM.setValue(ZYPConstant.FLG_ON_Y);
//        }
//        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxSetFlg_MM.getValue())) {
//            bizMsg = new NSAL0010CMsg();
//            bizMsg.setBusinessID(NSAL0010Constant.BUSINESS_ID);
//            bizMsg.setFunctionCode("20");
//            EZDMsg.copy(scrnMsg, null, bizMsg, null);
//        }
//        return bizMsg;
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
//        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
//        NSAL0010CMsg bizMsg = (NSAL0010CMsg) cMsg;
//
//        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxSetFlg_MM.getValue())) {
//            ZYPEZDItemValueSetter.setValue(scrnMsg.xxSetFlg_MM, ZYPConstant.FLG_OFF_N);
//            EZDMsg.copy(bizMsg, null, scrnMsg, null);
//            if ("E".equals(bizMsg.getMessageKind()) || ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
//                return;
//            }
//            NSAL0010CommonLogic.controlScreenFields(this, scrnMsg, bizMsg.getUserID(), true);
//        }
//        scrnMsg.setFocusItem(scrnMsg.serNum_H1);
    }
}
