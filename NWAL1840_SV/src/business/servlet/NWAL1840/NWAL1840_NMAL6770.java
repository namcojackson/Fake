/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1840;

import static business.servlet.NWAL1840.constant.NWAL1840Constant.BIZ_ID;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.FUNC_CD_SRCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NWAL1840.NWAL1840CMsg;
import business.servlet.NWAL1840.common.NWAL1840CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/29   Fujitsu         T.Murai         Create          N/A
 * 2017/08/04   Fujitsu         H.Nagashima     Update          QC#16452
 * 2017/10/18   Fujitsu         S.Yamamoto      Update          S21_NA#20246
 * 2018/03/09   Fujitsu         A.Kosai         Update          S21_NA#22387
 *</pre>
 */
public class NWAL1840_NMAL6770 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return null;
        }

        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;
        NWAL1840CMsg bizMsg = new NWAL1840CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;
        int slctLine = scrnMsg.xxCellIdx.getValueInt();
        NWAL1840_DBMsg contactLineMsg = scrnMsg.D.no(slctLine);

        if ("CMN_Close".equals(getLastGuard())) {
            scrnMsg.setFocusItem(contactLineMsg.ctacPsnFirstNm_D);
            return;
        }

        NWAL1840CMsg bizMsg = (NWAL1840CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Set Contact Information
        // 2018/03/09 S21_NA#22387 Del Start
//        ZYPEZDItemValueSetter.setValue(contactLineMsg.ctacPsnTpCd_D, scrnMsg.xxPopPrm_01);
        // 2018/03/09 S21_NA#22387 Del End
        ZYPEZDItemValueSetter.setValue(contactLineMsg.ctacPsnTelNum_D, scrnMsg.xxPopPrm_02);
        ZYPEZDItemValueSetter.setValue(contactLineMsg.ctacPsnExtnNum_D, scrnMsg.xxPopPrm_04);
        ZYPEZDItemValueSetter.setValue(contactLineMsg.ctacPsnEmlAddr_D, scrnMsg.xxPopPrm_03);
        ZYPEZDItemValueSetter.setValue(contactLineMsg.ctacPsnFaxNum_D, scrnMsg.xxPopPrm_05);

        NWAL1840CommonLogic.setProtect(this, scrnMsg);        // QC#16452 add

        scrnMsg.setFocusItem(contactLineMsg.ctacPsnTpCd_D);
        //QC#16452 add start
        if (contactLineMsg.ctacPsnTpCd_D.isInputProtected()) {
            scrnMsg.setFocusItem(contactLineMsg.ctacPsnTelNum_D);
        }
        //QC#16452 add End

        // S21_NA ADD QC#20246
        NWAL1840CommonLogic.setContactToAttention(scrnMsg, slctLine);

    }
}
