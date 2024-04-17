/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7190;

import static business.servlet.NMAL7190.constant.NMAL7190Constant.BIZ_ID;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7190.NMAL7190CMsg;
import business.servlet.NMAL7190.common.NMAL7190CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUIFocusRule;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableFocusRule;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7190Scrn00_Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Fujitsu         W.Honda         Create          N/A
 * 2018/12/05   Fujitsu         T.Noguchi       Update          QC#29324
 * 2023/04/20   Hitachi         H.Watanabe      Update          QC#61200
 *</pre>
 */
public class NMAL7190Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7190BMsg scrnMsg = (NMAL7190BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.prcGrpPk)) {
            scrnMsg.prcGrpPk.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.prcGrpPk.getNameForMessage()});
        }

        scrnMsg.addCheckItem(scrnMsg.prcGrpPk);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7190BMsg scrnMsg = (NMAL7190BMsg) bMsg;

        NMAL7190CMsg bizMsg = new NMAL7190CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7190BMsg scrnMsg = (NMAL7190BMsg) bMsg;
        NMAL7190CMsg bizMsg  = (NMAL7190CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // 2018/12/05 QC#29324 Add Start
        NMAL7190CommonLogic.initControlScreen(this, scrnMsg);
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        // 2018/12/05 QC#29324 Add End

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            scrnMsg.setFocusItem(scrnMsg.prcGrpPk);
            throw new EZDFieldErrorException();
        }

        NMAL7190CommonLogic.controlScreen(this, scrnMsg);
        NMAL7190CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");

        if (scrnMsg.A.getValidCount() > 0) {
            scrnMsg.setFocusItem(scrnMsg.prcGrpNm);
        } else {
            scrnMsg.setFocusItem(scrnMsg.prcGrpPk);
        }

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
}
