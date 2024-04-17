/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1500_NLCL1000
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/09   Fujitsu         T.Yoshida       Create          N/A
 * 2016/11/16   Fujitsu         S.Takami        Update          S21_NA#16021
 * 2018/08/24   Fujitsu         T.Noguchi       Update          S21_NA#27202
 *</pre>
 */
public class NWAL1500_NLCL1000 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        // 2018/08/24 S21_NA#27202 Add Start
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NWAL1500_BBMsg itemLineMsg = scrnMsg.B.no(i);
            if (itemLineMsg.rtlWhNm_LL.isInputProtected()) {
                ZYPEZDItemValueSetter.setValue(itemLineMsg.xxReadOnlyFlg_LL, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(itemLineMsg.xxReadOnlyFlg_LL, ZYPConstant.FLG_OFF_N);
            }
        }
        // 2018/08/24 S21_NA#27202 Add End

        NWAL1500CMsg bizMsg = new NWAL1500CMsg();
        bizMsg.setBusinessID("NWAL1500");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // scrnMsg.setFocusItem(scrnMsg.B.no(scrnMsg.xxCellIdx.getValueInt()).xxChkBox_LL); 2016/11/16 S21_NA#16021 Del
    }
}
