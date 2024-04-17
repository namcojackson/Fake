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
import business.servlet.NWAL1770.common.NWAL1770CommonLogicForScrnFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/12   Fujitsu         T.Yoshida       Create          N/A
 * 2017/08/04   Fujitsu         H.Nagashima     Update          QC#16452
 * 2017/10/18   Fujitsu         W.Honda         Create          S21_NA#20246-1(L3 Sol#454)
 * 2017/12/20   Fujitsu         Mz.Takahashi    Update          S21_NA#20164(L3 Sol#356)
 * 2018/03/08   Fujitsu         A.Kosai         Update          S21_NA#22387
 *</pre>
 */
public class NWAL1770_NMAL6770 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return null;
        }

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;
        NWAL1770CMsg bizMsg = new NWAL1770CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;
        int slctLine = scrnMsg.xxCellIdx.getValueInt();
        NWAL1770_ABMsg contactLineMsg = scrnMsg.A.no(slctLine);

        if ("CMN_Close".equals(getLastGuard())) {
            scrnMsg.setFocusItem(contactLineMsg.ctacPsnFirstNm_A);
            return;
        }
        NWAL1770CMsg bizMsg = (NWAL1770CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Set Contact Information
        // 2018/03/08 S21_NA#22387 Del Start
//        ZYPEZDItemValueSetter.setValue(contactLineMsg.ctacPsnTpCd_A, scrnMsg.xxPopPrm_01);
        // 2018/03/08 S21_NA#22387 Del End
        ZYPEZDItemValueSetter.setValue(contactLineMsg.ctacPsnTelNum_A, scrnMsg.xxPopPrm_02);
        ZYPEZDItemValueSetter.setValue(contactLineMsg.ctacPsnExtnNum_A, scrnMsg.xxPopPrm_04);
        ZYPEZDItemValueSetter.setValue(contactLineMsg.ctacPsnEmlAddr_A, scrnMsg.xxPopPrm_03);
        ZYPEZDItemValueSetter.setValue(contactLineMsg.ctacPsnFaxNum_A, scrnMsg.xxPopPrm_05);

        // Del Start 2017/12/10 QC#20164(L3 Sol#356)
        // Add Start 2017/10/13 QC#20246-1(L3 Sol#454)
        //NWAL1770CommonLogic.contactToAttention(scrnMsg, slctLine);
        // Add End 2017/10/13 QC#20246-1(L3 Sol#454)
        // Del End 2017/12/10 QC#20164(L3 Sol#356)

        NWAL1770CommonLogicForScrnFields.setProtect(this, scrnMsg);        // QC#16452 add

        scrnMsg.setFocusItem(contactLineMsg.ctacPsnTpCd_A);
        //QC#16452 add Start
        if (contactLineMsg.ctacPsnTpCd_A.isInputProtected()) {
            scrnMsg.setFocusItem(contactLineMsg.ctacPsnTelNum_A);
        }
        //QC#16452 add End
    }
}
