/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7280;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.BIZ_ID;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.NMAM8133E;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.SCRN_ID_00;
import java.io.Serializable;
import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7280.NMAL7280CMsg;
import business.servlet.NMAL7280.common.NMAL7280CommonLogic;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NMAL7280_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   Fujitsu         M.Suzuki        Create          N/A
 * 2017/02/27   Fujitsu         W.Honda         Update          QC#16011
 *</pre>
 */
public class NMAL7280_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7280BMsg scrnMsg = (NMAL7280BMsg) bMsg;
        NMAL7280CMsg bizMsg = new NMAL7280CMsg();

        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {

            Object[] params = (Object[]) arg;
            EZDBBigDecimalItem param00 = (EZDBBigDecimalItem) params[0];
            if (ZYPCommonFunc.hasValue(param00)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.prcRuleHdrPk, param00);
            } else {
                scrnMsg.setMessageInfo(NMAM8133E, new String[] {"Rule ID" });
                return null;
            }
        } else {
            scrnMsg.setMessageInfo(NMAM8133E, new String[] {"Rule ID" });
            return null;
        }
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7280BMsg scrnMsg = (NMAL7280BMsg) bMsg;
        scrnMsg.prcRuleNm.setInputProtected(true);
        scrnMsg.prcRuleHdrPk.setInputProtected(true);

        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
            // Mod Start 2017/02/27 W.Honda S21_NA#16011
//          NMAL7280CommonLogic.initCmnBtnProp(this);
            NMAL7280CommonLogic.setScrnCtrl(this, scrnMsg, getUserProfileService());
            // Mod End 2017/02/27 W.Honda S21_NA#16011
            return;
        }

        NMAL7280CMsg bizMsg = (NMAL7280CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Mod Start 2017/02/27 W.Honda S21_NA#16011
//        NMAL7280CommonLogic.initCmnBtnProp(this);
        NMAL7280CommonLogic.setScrnCtrl(this, scrnMsg, getUserProfileService());
        // Mod End 2017/02/27 W.Honda S21_NA#16011
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        tblColor.setAlternateRowsBG("A1", scrnMsg.A, 1);
        tblColor.setAlternateRowsBG("A2", scrnMsg.A, 1);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL7280BMsg scrnMsg = (NMAL7280BMsg) bMsg;

        // Header
        scrnMsg.prcRuleNm.setNameForMessage("Rule Name");
        scrnMsg.prcRuleHdrPk.setNameForMessage("Rule ID");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox_A.setNameForMessage("Check Box");
            scrnMsg.A.no(i).prcRuleCondNum_A1.setNameForMessage("Condition#");
            scrnMsg.A.no(i).prcRuleOpTpCd_A1.setNameForMessage("Operator");

            scrnMsg.A.no(i).prcRuleCondNum_A2.setNameForMessage("Condition#");
            scrnMsg.A.no(i).prcRuleOpTpCd_A2.setNameForMessage("Operator");

            scrnMsg.A.no(i).prcRuleCondNum_A3.setNameForMessage("Condition#");
            scrnMsg.A.no(i).prcRuleOpTpCd_A3.setNameForMessage("Operator");

            scrnMsg.A.no(i).prcRuleCondNum_A4.setNameForMessage("Condition#");
            scrnMsg.A.no(i).prcRuleOpTpCd_A4.setNameForMessage("Operator");

            scrnMsg.A.no(i).prcRuleCondNum_A5.setNameForMessage("Condition#");
            scrnMsg.A.no(i).prcRuleOpTpCd_A5.setNameForMessage("Operator");

            scrnMsg.A.no(i).prcRuleCondNum_A6.setNameForMessage("Condition#");
            scrnMsg.A.no(i).prcRuleOpTpCd_A6.setNameForMessage("Operator");

            scrnMsg.A.no(i).prcRuleCondNum_A7.setNameForMessage("Condition#");
            scrnMsg.A.no(i).prcRuleOpTpCd_A7.setNameForMessage("Operator");

            scrnMsg.A.no(i).prcRuleCondNum_A8.setNameForMessage("Condition#");
            scrnMsg.A.no(i).prcRuleOpTpCd_A8.setNameForMessage("Operator");

            scrnMsg.A.no(i).prcRuleCondNum_A9.setNameForMessage("Condition#");
            scrnMsg.A.no(i).prcRuleOpTpCd_A9.setNameForMessage("Operator");

            scrnMsg.A.no(i).prcRuleCondNum_AA.setNameForMessage("Condition#");
            scrnMsg.A.no(i).prcRuleOpTpCd_AA.setNameForMessage("Operator");

            scrnMsg.A.no(i).prcRuleCondNum_AB.setNameForMessage("Condition#");
            scrnMsg.A.no(i).prcRuleOpTpCd_AB.setNameForMessage("Operator");

            scrnMsg.A.no(i).prcRuleCondNum_AC.setNameForMessage("Condition#");

            scrnMsg.A.no(i).effFromDt_A.setNameForMessage("Effective Date From");
            scrnMsg.A.no(i).effThruDt_A.setNameForMessage("Effective Date To");

            // Mod Start 2017/02/27 W.Honda S21_NA#16011
//            scrnMsg.A.no(i).prcRuleCondGrpCd_A.setInputProtected(true);
//            scrnMsg.A.no(i).prcRuleOpTpCd_A2.setInputProtected(true);
//            scrnMsg.A.no(i).prcRuleOpTpCd_A3.setInputProtected(true);
//            scrnMsg.A.no(i).prcRuleOpTpCd_A4.setInputProtected(true);
//            scrnMsg.A.no(i).prcRuleOpTpCd_A5.setInputProtected(true);
//            scrnMsg.A.no(i).prcRuleOpTpCd_A6.setInputProtected(true);
//            scrnMsg.A.no(i).prcRuleOpTpCd_A7.setInputProtected(true);
//            scrnMsg.A.no(i).prcRuleOpTpCd_A8.setInputProtected(true);
//            scrnMsg.A.no(i).prcRuleOpTpCd_A9.setInputProtected(true);
//            scrnMsg.A.no(i).prcRuleOpTpCd_AA.setInputProtected(true);
//            scrnMsg.A.no(i).prcRuleOpTpCd_AB.setInputProtected(true);
            // Mod End 2017/02/27 W.Honda S21_NA#16011
        }
    }

}
