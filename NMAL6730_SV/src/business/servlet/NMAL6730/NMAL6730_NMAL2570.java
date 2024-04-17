/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6730;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6730.NMAL6730CMsg;
import business.servlet.NMAL6730.constant.NMAL6730Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/01   Fujitsu         Y.Kamide        Create          N/A
 * 2016/06/08   SRAA            Y.Chen          Update          QC#7781
 *</pre>
 */
public class NMAL6730_NMAL2570 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;

// QC#7781
//        if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_P1)) {
//            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).itrlRvwPsnCd_A1, scrnMsg.xxPopPrm_P1);
//        }
        StringBuffer psnCdList = new StringBuffer();
        for (int i = 0; i < scrnMsg.Q.length(); i++) {
            String psnCd = scrnMsg.Q.no(i).psnCd_Q2.getValue();
            if (ZYPCommonFunc.hasValue(psnCd)) {
                if (psnCdList.length() > 0) {
                    psnCdList.append(NMAL6730Constant.CHAR_COMMA);
                }
                psnCdList.append(psnCd);
            } else {
                break;
            }
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).xxGenlFldAreaTxt_A1, psnCdList.toString());

        NMAL6730CMsg bizMsg = new NMAL6730CMsg();
        bizMsg.setBusinessID("NMAL6730");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;
        NMAL6730CMsg bizMsg  = (NMAL6730CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // QC#7781
        scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).xxGenlFldAreaTxt_A1);
    }
}
