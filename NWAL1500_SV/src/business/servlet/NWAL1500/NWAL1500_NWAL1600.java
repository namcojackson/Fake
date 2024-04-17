/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_HEADER;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_RMA;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.servlet.NWAL1600.constant.NWAL1600Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/06   Fujitsu         T.Yoshida       Create          N/A
 * 2016/05/11   Fujitsu         T.Murai         update          S21_NA#7861
 * 2023/06/06   Hitachi         T.Doi           Update          CSA-QC#61465
 *</pre>
 */
public class NWAL1500_NWAL1600 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        if (S21StringUtil.isEquals(scrnMsg.xxPopPrm_P0.getValue(), NWAL1600Constant.MODE_REFERENCE)) {
            return null;
        }

        String writerPsnCd = scrnMsg.xxPopPrm_P4.getValue();
        if (ZYPCommonFunc.hasValue(writerPsnCd)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.psnNum, writerPsnCd); // 2016/05/11 S21NA#7861 slsRepPsnCd -> psnNum
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

        String dplyTab = scrnMsg.xxDplyTab.getValue();
        // START 2023/06/06 T.Doi [CSA-QC#61465, DEL]
        // int selectIndex = scrnMsg.xxCellIdx.getValueInt();
        // END 2023/06/06 T.Doi [CSA-QC#61465, DEL]

        if (bizMsg != null) {

            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        if (TAB_HEADER.equals(dplyTab)) {
            scrnMsg.setFocusItem(scrnMsg.prcCatgNm);
        } else if (TAB_LINE_CONFIG.equals(dplyTab)) {
            // START 2023/06/06 T.Doi [CSA-QC#61465, MOD]
            // scrnMsg.setFocusItem(scrnMsg.A.no(selectIndex).xxChkBox_LC);
            if (ZYPCommonFunc.hasValue(scrnMsg.xxCellIdx)) {
                scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).xxChkBox_LC);
            }
            // END 2023/06/06 T.Doi [CSA-QC#61465, MOD]
        } else if (TAB_RMA.equals(dplyTab)) {
            // START 2023/06/06 T.Doi [CSA-QC#61465, MOD]
            // scrnMsg.setFocusItem(scrnMsg.C.no(selectIndex).xxChkBox_RC);
            if (ZYPCommonFunc.hasValue(scrnMsg.xxCellIdx)) {
                scrnMsg.setFocusItem(scrnMsg.C.no(scrnMsg.xxCellIdx.getValueInt()).xxChkBox_RC);
            }
            // END 2023/06/06 T.Doi [CSA-QC#61465, MOD]
        }
    }
}
