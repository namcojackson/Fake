/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1270;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NPAL1270.NPAL1270CMsg;
//import business.servlet.NPAL1270.constant.NPAL1270Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * PO Requisition List
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/12/2016   CITS            R Shimamoto     Create          N/A
 * 03/01/2016   CITS            K.Ogino         Update          QC#4605
 *</pre>
 */
public class NPAL1270_NPAL1010 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if (!"CMN_Close".equals(getLastGuard())) {

            NPAL1270BMsg scrnMsg = (NPAL1270BMsg) bMsg;

            ZYPEZDItemValueSetter.setValue(scrnMsg.destRtlWhCd, scrnMsg.xxPopPrm_P6);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm, scrnMsg.xxPopPrm_P7);
            ZYPEZDItemValueSetter.setValue(scrnMsg.destRtlSwhCd, scrnMsg.xxPopPrm_P8);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhNm, scrnMsg.xxPopPrm_P9);

            scrnMsg.setFocusItem(scrnMsg.destRtlSwhCd);
        }

    }
}
