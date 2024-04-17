/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6700;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6700.NMAL6700CMsg;
import business.servlet.NMAL6700.constant.NMAL6700Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/15   Fujitsu         T.Murai         Create          #3784
 *</pre>
 */
public class NMAL6700_NMAL2570 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //do nothing 
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;

        StringBuffer psnCdList = new StringBuffer();
        for (int i = 0; i < scrnMsg.Q.length(); i++) {
            String psnCd = scrnMsg.Q.no(i).psnCd_Q2.getValue();
            if (ZYPCommonFunc.hasValue(psnCd)) {
                if (psnCdList.length() > 0) {
                    psnCdList.append(NMAL6700Constant.CHAR_COMMA);
                }
                psnCdList.append(psnCd);
            } else {
                break;
            }
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.G.no(scrnMsg.xxCellIdx.getValueInt()).xxGenlFldAreaTxt_G1, psnCdList.toString());


        NMAL6700CMsg bizMsg = new NMAL6700CMsg();
        bizMsg.setBusinessID(NMAL6700Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;
        NMAL6700CMsg bizMsg = (NMAL6700CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg , null);
        scrnMsg.setFocusItem(scrnMsg.G.no(scrnMsg.xxCellIdx.getValueInt()).xxGenlFldAreaTxt_G1);
    }
}
