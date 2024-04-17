/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0210;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/28   SRA             E.Inada         Create          N/A
 * 2016/05/18   Hitachi         Y.Takeno        Update          NA#CSA
 *</pre>
 */
public class NSBL0210_NMAL6040 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

//        NSBL0210BMsg scrnMsg = (NSBL0210BMsg) bMsg;
//
//        int selectIdx = getButtonSelectNumber();
//
//        if (selectIdx < 0) {
//            // Header
//            if (ZYPCommonFunc.hasValue(scrnMsg.mdseCd)) {
//                scrnMsg.setFocusItem(scrnMsg.mdseCd);
//
//            } else if (ZYPCommonFunc.hasValue(scrnMsg.fifthProdCtrlCd)) {
//                scrnMsg.setFocusItem(scrnMsg.fifthProdCtrlCd);
//
//            } else if (ZYPCommonFunc.hasValue(scrnMsg.frthProdCtrlCd)) {
//                scrnMsg.setFocusItem(scrnMsg.frthProdCtrlCd);
//
//            } else if (ZYPCommonFunc.hasValue(scrnMsg.thirdProdCtrlCd)) {
//                scrnMsg.setFocusItem(scrnMsg.thirdProdCtrlCd);
//
//            } else if (ZYPCommonFunc.hasValue(scrnMsg.scdProdCtrlCd)) {
//                scrnMsg.setFocusItem(scrnMsg.scdProdCtrlCd);
//
//            } else if (ZYPCommonFunc.hasValue(scrnMsg.firstProdCtrlCd)) {
//                scrnMsg.setFocusItem(scrnMsg.firstProdCtrlCd);
//
//            } else if (ZYPCommonFunc.hasValue(scrnMsg.zerothProdCtrlCd)) {
//                scrnMsg.setFocusItem(scrnMsg.zerothProdCtrlCd);
//            }
//        } else {
//            // Detail
//            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(selectIdx).mdseCd_A)) {
//                scrnMsg.setFocusItem(scrnMsg.A.no(selectIdx).mdseCd_A);
//
//            } else if (ZYPCommonFunc.hasValue(scrnMsg.A.no(selectIdx).fifthProdCtrlCd_A)) {
//                scrnMsg.setFocusItem(scrnMsg.A.no(selectIdx).fifthProdCtrlCd_A);
//
//            } else if (ZYPCommonFunc.hasValue(scrnMsg.A.no(selectIdx).frthProdCtrlCd_A)) {
//                scrnMsg.setFocusItem(scrnMsg.A.no(selectIdx).frthProdCtrlCd_A);
//
//            } else if (ZYPCommonFunc.hasValue(scrnMsg.A.no(selectIdx).thirdProdCtrlCd_A)) {
//                scrnMsg.setFocusItem(scrnMsg.A.no(selectIdx).thirdProdCtrlCd_A);
//
//            } else if (ZYPCommonFunc.hasValue(scrnMsg.A.no(selectIdx).scdProdCtrlCd_A)) {
//                scrnMsg.setFocusItem(scrnMsg.A.no(selectIdx).scdProdCtrlCd_A);
//
//            } else if (ZYPCommonFunc.hasValue(scrnMsg.A.no(selectIdx).firstProdCtrlCd_A)) {
//                scrnMsg.setFocusItem(scrnMsg.A.no(selectIdx).firstProdCtrlCd_A);
//
//            } else if (ZYPCommonFunc.hasValue(scrnMsg.A.no(selectIdx).zerothProdCtrlCd_A)) {
//                scrnMsg.setFocusItem(scrnMsg.A.no(selectIdx).zerothProdCtrlCd_A);
//            }
//        }
    }
}
