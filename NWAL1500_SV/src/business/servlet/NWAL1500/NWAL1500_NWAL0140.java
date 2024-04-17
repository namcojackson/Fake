/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.NO_SLCT_DTL;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.ENTER;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.SPACE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.servlet.NWAL1500.common.NWAL1500CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/13   Fujitsu         T.Yoshida       Create          N/A
 * 2016/02/23   Fujitsu         Y.Taoka         Update          QC#1694
 * 2017/12/08   Fujitsu         A.Kosai         Update          S21_NA#21621
 * 2018/01/26   Fujitsu         K.Ishizuka      Update          S21_NA#23140
 *</pre>
 */
public class NWAL1500_NWAL0140 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        if (NO_SLCT_DTL != scrnMsg.xxCellIdx.getValueInt()) {
            return null;
        }

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
        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        int selectIdx = scrnMsg.xxCellIdx.getValueInt();

        if (NO_SLCT_DTL == selectIdx) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxAllLineAddr_SH, NWAL1500CommonLogic.cmbnAddr(scrnMsg, SPACE));
            ZYPEZDItemValueSetter.setValue(scrnMsg.entShipToCustLocAddr, NWAL1500CommonLogic.cmbnAddr(scrnMsg, ENTER));
            scrnMsg.setFocusItem(scrnMsg.shipToCustAcctCd);
        } else {
            if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
                scrnMsg.setFocusItem(scrnMsg.A.no(selectIdx).billToCustCd_LC);
                scrnMsg.A.no(selectIdx).addShipToLocNm_LC.clear(); // 2018/01/26 S21_NA#23140 Add
                // 2017/12/08 S21_NA#21621 Add Start
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(selectIdx).dropShipFlg_LC.getValue())) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectIdx).addShipToLocNm_LC, scrnMsg.A.no(selectIdx).shipToLocNm_LC);
                }
                // 2017/12/08 S21_NA#21621 Add End
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectIdx).shipToCustLocAddr_LC, NWAL1500CommonLogic.cmbnAddrForConf(scrnMsg, SPACE, selectIdx)); // 2018/01/26 S21_NA#23140 Add
            } else {
                scrnMsg.setFocusItem(scrnMsg.C.no(selectIdx).billToCustCd_RC);
                scrnMsg.C.no(selectIdx).addShipToLocNm_RC.clear(); // 2018/01/26 S21_NA#23140 Add
                // 2017/12/08 S21_NA#21621 Add Start
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.C.no(selectIdx).dropShipFlg_RC.getValue())) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(selectIdx).addShipToLocNm_RC, scrnMsg.C.no(selectIdx).shipToLocNm_RC);
                }
                // 2017/12/08 S21_NA#21621 Add End
                ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(selectIdx).shipToCustLocAddr_RC, NWAL1500CommonLogic.cmbnAddrForConf(scrnMsg, SPACE, selectIdx)); // 2018/01/26 S21_NA#23140 Add
            }
        }
    }
}
