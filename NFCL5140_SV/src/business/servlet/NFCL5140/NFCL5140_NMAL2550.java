/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL5140;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFCL5140.NFCL5140CMsg;
//import business.servlet.NFCL5140.constant.NFCL5140Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/11/10   Hitachi            R.Takau         Create          N/A
 *</pre>
 */
public class NFCL5140_NMAL2550 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL5140BMsg scrnMsg = (NFCL5140BMsg) bMsg;

    	 StringBuffer sb = new StringBuffer();
    	 
    	 if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_I1) && ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_I2) && ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_I3) &&
                 ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_I4) && ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_I5) && ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_I6) &&
                 ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_I7) && ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_I8) && ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_I9)) {
    	     
    	     sb.append(scrnMsg.xxPopPrm_I1.getValue());
    	     sb.append(".");
    	     sb.append(scrnMsg.xxPopPrm_I2.getValue());
    	     sb.append(".");
    	     sb.append(scrnMsg.xxPopPrm_I3.getValue());
    	     sb.append(".");
    	     sb.append(scrnMsg.xxPopPrm_I4.getValue());
    	     sb.append(".");
    	     sb.append(scrnMsg.xxPopPrm_I5.getValue());
             sb.append(".");
             sb.append(scrnMsg.xxPopPrm_I6.getValue());
             sb.append(".");
             sb.append(scrnMsg.xxPopPrm_I7.getValue());
             sb.append(".");
             sb.append(scrnMsg.xxPopPrm_I8.getValue());
             sb.append(".");
             sb.append(scrnMsg.xxPopPrm_I9.getValue());
    	 }

         ZYPEZDItemValueSetter.setValue(scrnMsg.xxCmntTxt, sb.toString());
    }
}
