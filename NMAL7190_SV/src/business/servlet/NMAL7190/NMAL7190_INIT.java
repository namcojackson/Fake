/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7190;

import static business.servlet.NMAL7190.constant.NMAL7190Constant.BIZ_ID;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.SCRN_ID_00;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.ZZM9000E;
import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7190.NMAL7190CMsg;
import business.servlet.NMAL7190.common.NMAL7190CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUIFocusRule;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableFocusRule;
import com.canon.cusa.s21.framework.online.pagenation.S21SequentialSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NMAL7190_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Fujitsu         W.Honda         Create          N/A
 * 2017/08/21   Fujitsu         M.Yamada        Update          QC#18785(L3)
 * 2023/04/20   Hitachi         H.Watanabe      Update          QC#61200
 *</pre>
 */
public class NMAL7190_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7190BMsg scrnMsg = (NMAL7190BMsg) bMsg;
        NMAL7190CMsg bizMsg = new NMAL7190CMsg();

        //When Initial, IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length == 1) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcGrpPk, (EZDBBigDecimalItem) params[0]);
            if (!ZYPCommonFunc.hasValue(scrnMsg.prcGrpPk)) {
                scrnMsg.setMessageInfo(ZZM9000E, new String[] {scrnMsg.prcGrpPk.getNameForMessage()});
                return null;
            }
        }

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7190BMsg scrnMsg = (NMAL7190BMsg) bMsg;
        NMAL7190CMsg bizMsg = (NMAL7190CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL7190CommonLogic.controlScreen(this, scrnMsg);
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A, 1);

        scrnMsg.setFocusItem(scrnMsg.prcGrpPk);

        // 2023/04/20 QC#61200 Add Start
        scrnMsg.clearGUIAttribute( SCRN_ID_00, ZYPGUITableFocusRule.PREFIX_KEY + NMAL7190Bean.A );

        ZYPGUITableFocusRule tblFocusRule = new ZYPGUITableFocusRule( SCRN_ID_00, NMAL7190Bean.A );
        scrnMsg.addGUIAttribute( tblFocusRule );
            
     // LEFT TBL - BOTTOM
        ZYPGUIFocusRule fRule = new ZYPGUIFocusRule( "LEFT TBL - BOTTOM" );
        fRule.setNextId( NMAL7190Bean.xxChkBox_A1 + "#0" );
        tblFocusRule.addRule( fRule );
            
        for( int i = 0; i < scrnMsg.A.getValidCount(); i++ ) {

            // xxChkBox_A1
            fRule = new ZYPGUIFocusRule( NMAL7190Bean.xxChkBox_A1 + "#" + i );
            if( i > 0 ) {
                fRule.setPrevId( NMAL7190Bean.effThruDt_A1 + "#" + (i-1) );
            }
            tblFocusRule.addRule( fRule );

            // prcGrpTrgtAttrbCd_A1
            fRule = new ZYPGUIFocusRule( NMAL7190Bean.prcGrpTrgtAttrbCd_A1 + "#" + i );
            fRule.setNextId( NMAL7190Bean.prcGrpOpCd_A1 + "#" + i );
            tblFocusRule.addRule( fRule );

            // prcGrpOpCd_A1
            fRule = new ZYPGUIFocusRule( NMAL7190Bean.prcGrpOpCd_A1 + "#" + i );
            fRule.setPrevId( NMAL7190Bean.prcGrpTrgtAttrbCd_A1 + "#" + i );
            tblFocusRule.addRule( fRule );

            // effThruDt_A1
            fRule = new ZYPGUIFocusRule(  NMAL7190Bean.effThruDt_A1 + "#" + i );
            if( (i+1) != scrnMsg.A.length() ) {
                fRule.setNextId( NMAL7190Bean.xxChkBox_A1 + "#" + (i+1) );
            }
            tblFocusRule.addRule( fRule );
        }
        // 2023/04/20 QC#61200 Add End
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL7190BMsg scrnMsg = (NMAL7190BMsg) bMsg;

        scrnMsg.prcGrpPk.setNameForMessage("Price Group ID");
        scrnMsg.prcGrpNm.setNameForMessage("Price Group Name");
        scrnMsg.prcGrpDescTxt.setNameForMessage("Group Description");
        scrnMsg.actvFlg.setNameForMessage("Active");
        scrnMsg.prcGrpTpCd.setNameForMessage("Pricing Group Type");
        scrnMsg.prcGrpTrxTpCd.setNameForMessage("Transaction Type");
        scrnMsg.effFromDt.setNameForMessage("Effective Date From");
        scrnMsg.effThruDt.setNameForMessage("Effective Date To");
        scrnMsg.xxFileData_UP.setNameForMessage("File Upload");
        // 2023/04/20 QC#61200 Add Start
        scrnMsg.effThruDt_MU.setNameForMessage("Date To");
        // 2023/04/20 QC#61200 Add End
        // page jump common setting
        scrnMsg.xxPageShowCurNum.setNameForMessage(S21SequentialSearchPagenationScrnSupport.getCurrentPageFieldName());

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox_A1.setNameForMessage("Check Box");
            scrnMsg.A.no(i).prcGrpTrgtTpCd_A1.setNameForMessage("Target Context");
            scrnMsg.A.no(i).prcGrpTrgtAttrbCd_A1.setNameForMessage("Target Attribute Name");
            scrnMsg.A.no(i).prcGrpOpCd_A1.setNameForMessage("Operator");
            scrnMsg.A.no(i).prcGrpFromTxt_A1.setNameForMessage("Target From");
            scrnMsg.A.no(i).prcGrpThruTxt_A1.setNameForMessage("Target To");
            scrnMsg.A.no(i).prcGrpPrcdNum_A1.setNameForMessage("Precedence#");
            scrnMsg.A.no(i).effFromDt_A1.setNameForMessage("Date From");
            scrnMsg.A.no(i).effThruDt_A1.setNameForMessage("Date To");
        }
    }
}
