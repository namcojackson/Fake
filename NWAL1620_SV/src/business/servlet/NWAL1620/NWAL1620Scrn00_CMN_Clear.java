/* <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre> */
package business.servlet.NWAL1620;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL1620.common.NWAL1620CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/02/20   Fujitsu         S.Yoshida       Update          QC#2177
 * 2017/09/26   Fujitsu         T.murai         Update          S21_NA#18859(Sol#154)
 *</pre>
 */
public class NWAL1620Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1620BMsg scrnMsg = (NWAL1620BMsg) bMsg;
        ZYPEZDItemValueSetter.setValue(scrnMsg.cpoOrdNum, scrnMsg.cpoOrdNum_BK);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox, scrnMsg.xxChkBox_BK);
        ZYPEZDItemValueSetter.setValue(scrnMsg.configCatgCd, scrnMsg.configCatgCd_BK);
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdPosnNum, scrnMsg.dsOrdPosnNum_BK);
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsCpoLineNum, scrnMsg.dsCpoLineNum_BK);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxQty10Num, scrnMsg.xxQty10Num_BK);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_R, scrnMsg.xxChkBox_BR); // Add 2017/09/26 S21_NA#18859

        //Add Start NA QC#2177
        NWAL1620CommonLogic.setDispLineNum(scrnMsg);
        //Add End   NA QC#2177
    }
}
