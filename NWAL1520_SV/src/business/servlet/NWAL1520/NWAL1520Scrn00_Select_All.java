/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1520;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.CHKBOX_ON_Y;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;
import business.servlet.NWAL1520.common.NWAL1520CommonLogic;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1520Scrn00_Select_All
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/30   Fujitsu         A.Suda          Create          N/A
 * 2023/10/10   CITS            K.Ikeda         Update          QC#61940
 *</pre>
 */
public class NWAL1520Scrn00_Select_All extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1520BMsg scrnMsg = (NWAL1520BMsg) bMsg;
        // START 10/10/2023 [QC#61940, Add]
        // ZYPTableUtil.selectAll(scrnMsg,L, xxChkBox, CHKBOX_ON_Y);
        NWAL1520_LBMsgArray lineMsgArray = scrnMsg.L;
        if (lineMsgArray.getValidCount() > 0) {
            for (int i = 0; i < lineMsgArray.getValidCount(); i++) {
                NWAL1520_LBMsg lineMsg = lineMsgArray.no(i);
                if (!(FLG_ON_Y.equals(lineMsg.relFlg.getValue()) || !NWAL1520CommonLogic.isPermission(scrnMsg.B, lineMsg.relFuncTpCd.getValue()))) {
                    lineMsg.xxChkBox.setValue(CHKBOX_ON_Y);
                }
            }
        }
        // END 10/10/2023 [QC#61940, Add]
    }
}
