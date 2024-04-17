/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1770;

import static business.servlet.NWAL1770.constant.NWAL1770Constant.BIZ_ID;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.FUNC_CD_SRCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1770.NWAL1770CMsg;
import business.servlet.NWAL1770.common.NWAL1770CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/21   Fujitsu         T.Yoshida       Create          N/A
 * 2018/08/24   Fujitsu         T.Noguchi       Update          S21_NA#27202
 * 2023/07/20   Hitachi         T.Fukuta        Update          CSA-QC#61467
 * </pre>
 */
public class NWAL1770_NLCL1000 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;

        // 2018/08/24 S21_NA#27202 Add Start
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NWAL1770_BBMsg itemLineMsg = scrnMsg.B.no(i);
            if (itemLineMsg.rtlWhNm_B.isInputProtected()) {
                ZYPEZDItemValueSetter.setValue(itemLineMsg.xxReadOnlyFlg_B, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(itemLineMsg.xxReadOnlyFlg_B, ZYPConstant.FLG_OFF_N);
            }
        }
        // 2018/08/24 S21_NA#27202 Add End

        NWAL1770CMsg bizMsg = new NWAL1770CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;
        NWAL1770CMsg bizMsg = (NWAL1770CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        NWAL1770CommonLogic.checkItemLineWarning(scrnMsg);
        // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]

        scrnMsg.setFocusItem(scrnMsg.B.no(scrnMsg.xxCellIdx.getValueInt()).xxChkBox_B);
    }
}
