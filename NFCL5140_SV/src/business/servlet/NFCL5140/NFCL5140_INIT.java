/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/15/2010   Fujitsu         I.Kondo         Create          N/A
 * 09/29/2010   Fujitsu         I.Kondo         Update          DefID:8207 No.410
 * 11/04/2010   Fujitsu         I.Kondo         Update          DefID:M15
 * 03/17/2011   Fujitsu         K.Kimura        Update          DefID:1875
 * 2018/03/28   Fujitsu         H.Ikeda         Update          QC#21738
 * 2018/11/01   Fujitsu         S.Takami        Update          QC#28289
 * 2022/12/03   Hitachi         R.Takau         Update          QC#57252
 * </pre>
 */
package business.servlet.NFCL5140;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL5140.NFCL5140CMsg;
import business.servlet.NFCL5140.common.NFCL5140CommonLogic;
import business.servlet.NFCL5140.constant.NFCL5140Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * NFCL5140_INIT class.
 */

public class NFCL5140_INIT extends S21INITCommonHandler implements NFCL5140Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL5140BMsg scrnMsg = (NFCL5140BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();

//        if (null == params || PARAMS.LENGTH.getValue() != params.length) {
        if (null == params || PARAMS.LENGTH.getValue() > params.length) {
            scrnMsg.setMessageInfo("NFCM0031E");
            return null;
        } else {
            String ccyCd = (String) params[PARAMS.NUM_8.getValue()];
            scrnMsg.ccyCd_H1.setValue(ccyCd);
            String exptFlg = (String) params[PARAMS.NUM_9.getValue()];
            scrnMsg.exptFlg.setValue(exptFlg);
            // START 2018/03/28 H.Ikeda [QC#21738,ADD]
            BigDecimal amount = (BigDecimal) params[PARAMS.NUM_7.getValue()];
            scrnMsg.xxInpAmtNum.setValue(amount);
            // END   2018/03/28 H.Ikeda [QC#21738,ADD]
            // START 2018/11/01 S.Takami [QC#28289, Add]
            try {
                if (PARAMS.ADD_LENGTH.getValue() == params.length && ZYPConstant.FLG_ON_Y.equals((String) params[PARAMS.ADD_NUM_10.getValue()])) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxYesNoCd_OF, (String) params[PARAMS.ADD_NUM_10.getValue()]);
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxYesNoCd_OF, ZYPConstant.FLG_OFF_N);
                }
            } catch(Exception ex) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxYesNoCd_OF, ZYPConstant.FLG_OFF_N);
            }
            // End 2018/11/01 S.Takami [QC#28289, Add]
        }

        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        scrnMsg.P.clear();
        scrnMsg.P.setValidCount(0);

        String transScrnId = getSubmitedScrnId(ctx);
        scrnMsg.xxScrId.setValue(transScrnId);

        NFCL5140CMsg bizMsg = new NFCL5140CMsg();
        bizMsg.setBusinessID("NFCL5140");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL5140BMsg scrnMsg = (NFCL5140BMsg) bMsg;
        NFCL5140CMsg bizMsg = (NFCL5140CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFCL5140CommonLogic.initialize(this, scrnMsg);

        NFCL5140CommonLogic.setAppFracDigit(scrnMsg);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NFCL5140BMsg scrnMsg = (NFCL5140BMsg) bMsg;

        scrnMsg.xxInpAmtNum.setNameForMessage("Amount");
        scrnMsg.arCustRefNum.setNameForMessage("Customer REF Num");
        //scrnMsg.tocCd.setNameForMessage("Dept Code");
        //scrnMsg.coaProdCd.setNameForMessage("Prod Code");
        scrnMsg.arAdjTpCd_P1.setNameForMessage("Adjustment Type");
        // START 2018/03/28 H.Ikeda [QC#21738,DEL]
        //scrnMsg.xxInvCmntTxt.setNameForMessage("Comments");
        // END   2018/03/28 H.Ikeda [QC#21738,DEL]
        // START 2022/12/5 R.Takau [QC#57252,ADD]
        scrnMsg.xxCmntTxt.setNameForMessage("Charge Account");
        // END   2022/12/5 R.Takau [QC#57252,ADD]

    }

}
