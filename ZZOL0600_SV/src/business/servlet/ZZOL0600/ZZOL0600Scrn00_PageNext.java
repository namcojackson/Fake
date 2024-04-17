/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZOL0600;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZOL0600.ZZOL0600CMsg;
import business.servlet.ZZOL0600.common.ZZOL0600CommonLogic;
import business.servlet.ZZOL0600.constant.ZZOL0600Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

public class ZZOL0600Scrn00_PageNext extends S21CommonHandler implements ZZOL0600Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZOL0600BMsg scrnMsg = (ZZOL0600BMsg) bMsg;

        scrnMsg.xxPageShowFromNum.setValue(scrnMsg.xxPageShowToNum.getValueInt());
        scrnMsg.xxPageShowToNum.clear();

        ZZOL0600CMsg bizMsg = new ZZOL0600CMsg();
        bizMsg.setBusinessID("ZZOL0600");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZOL0600BMsg scrnMsg = (ZZOL0600BMsg) bMsg;
        ZZOL0600CMsg bizMsg = (ZZOL0600CMsg) cMsg;

        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int j = 0;
        for (; j < scrnMsg.A.getValidCount(); j++) {
            if (scrnMsg.A.no(j).almsMsgTxt_TR.getValue().length() > 50) {
                String truncStr = scrnMsg.A.no(j).almsMsgTxt_TR.getValue().substring(0, 50);
                scrnMsg.A.no(j).xxAbendMsgTxt_TR.setValue(truncStr);
            } else {
                scrnMsg.A.no(j).xxAbendMsgTxt_TR.setValue(scrnMsg.A.no(j).almsMsgTxt_TR.getValue());
            }

            if (ZZOL0600Constant.ONLINE_CHAR.equals(scrnMsg.almsOnlBatFlg_F2.getValue())) {
                scrnMsg.A.no(j).xxAlmsOnlBatFlgTxt_TR.setValue(ZZOL0600Constant.ONLINE_FLG);
            } else if (ZZOL0600Constant.BATCH_CHAR.equals(scrnMsg.almsOnlBatFlg_F2.getValue())) {
                scrnMsg.A.no(j).xxAlmsOnlBatFlgTxt_TR.setValue(ZZOL0600Constant.BATCH_FLG);
            } else if (ZZOL0600Constant.ALL_CHAR.equals(scrnMsg.almsOnlBatFlg_F2.getValue())) {
                    if (scrnMsg.A.no(j).almsBatProcId_TR.getValueInt() > 0) {
                    scrnMsg.A.no(j).xxAlmsOnlBatFlgTxt_TR.setValue(ZZOL0600Constant.BATCH_FLG);
                    } else {
                    scrnMsg.A.no(j).xxAlmsOnlBatFlgTxt_TR.setValue(ZZOL0600Constant.ONLINE_FLG);
                }
            }

        }

        ZZOL0600CommonLogic.convertTimeToDisplay(scrnMsg, bizMsg);

        S21TableColorController tblColor = new S21TableColorController(ZZOL0600Constant.pageID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
        scrnMsg.A.setInputProtected(true);

    }

}
