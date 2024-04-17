/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2340;

import java.io.Serializable;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL2340.common.NWAL2340CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * SOM Address Mass Apply INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/22   CITS            S.Tanikawa      Create          N/A
 * 2017/09/07   Fujitsu         R.Nakamura      Update          QC#20974
 *</pre>
 */
public class NWAL2340_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2340BMsg scrnMsg = (NWAL2340BMsg) bMsg;

        Serializable arg = getArgForSubScreen();

        scrnMsg.clear();
        ZYPTableUtil.clear(scrnMsg.A);

        Object[] params = (Object[]) arg;
        List< ? > paramList = (List< ? >) params[0];
        int idx = 0;
        for (Object param : paramList) {
            int column = 0;
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).addrLbTxt_A, (EZDBStringItem) ((Object[]) param)[column++]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).shipToCustAcctNm_A, (String) ((Object[]) param)[column++]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).shipToCustAcctCd_A, (EZDBStringItem) ((Object[]) param)[column++]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).shipToCustCd_A, (EZDBStringItem) ((Object[]) param)[column++]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).shipToFirstLineAddr_A, (String) ((Object[]) param)[column++]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).shipToScdLineAddr_A, (String) ((Object[]) param)[column++]);
            // Mod Start 2017/09/07 QC#20974
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).shipToThirdLineAddr_A, (String) ((Object[]) param)[column++]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).shipToFrthLineAddr_A, (String) ((Object[]) param)[column++]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).shipToCtyAddr_A, (String) ((Object[]) param)[column++]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).shipToStCd_A, (String) ((Object[]) param)[column++]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).shipToPostCd_A, (String) ((Object[]) param)[column++]);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).xxLocAddrNm_A, scrnMsg.A.no(idx).shipToFirstLineAddr_A.getValue() + " " + scrnMsg.A.no(idx).shipToScdLineAddr_A.getValue());
            String allLineAddr = NWAL2340CommonLogic.setAllLineAddressInfo(scrnMsg, idx);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).xxAllLineAddr_A, allLineAddr);
            // Mod End 2017/09/07 QC#20974
            idx++;
        }
        scrnMsg.A.setValidCount(idx);
        NWAL2340CommonLogic.cntrlScrnItemDispInit(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {
        // NWAL2340CommonLogic.setNameForMessage((NWAL2340BMsg) arg0);

    }
}
