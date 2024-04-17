/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/10/2010   Fujitsu         K.Tajima        Create          N/A
 * 08/05/2010   Fujitsu         R.Nakamura      Update          QC#9078
 *</pre>
 */
package business.servlet.NWXL0010;

import static business.servlet.NWXL0010.common.NWXL0010CommonLogic.newCMsgForSearch;
import static business.servlet.NWXL0010.common.NWXL0010CommonLogic.setGuiAttr;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWXL0010.NWXL0010CMsg;
import business.servlet.NWXL0010.constant.NWXL0010Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

public class NWXL0010_INIT extends S21INITCommonHandler implements NWXL0010Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), MY_BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWXL0010BMsg scrnMsg = (NWXL0010BMsg) bMsg;

        return newCMsgForSearch(scrnMsg);
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWXL0010BMsg scrnMsg = (NWXL0010BMsg) bMsg;
        NWXL0010CMsg bizMsg = (NWXL0010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // set GUI attribute.
        setGuiAttr(ScrnId.NWXL0010Scrn00, scrnMsg, this);

        // set focus.
        scrnMsg.xxRadioBtn_A.setValue(0);
        scrnMsg.setFocusItem(scrnMsg.rptSqlId);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        
        NWXL0010BMsg scrnMsg = (NWXL0010BMsg) bMsg;

        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
        String label;

        // --------------------------------------------------
        // Scrn00
        // --------------------------------------------------
        // Report ID
        label = labelConv.convLabel2i18nLabel(ScrnId.NWXL0010Scrn00.toString(), "Report ID");
        scrnMsg.rptSqlId.setNameForMessage(label);
        
        // Report Name
        label = labelConv.convLabel2i18nLabel(ScrnId.NWXL0010Scrn00.toString(), "Report Name");
        scrnMsg.rptSqlNm.setNameForMessage(label);

        // Description
        label = labelConv.convLabel2i18nLabel(ScrnId.NWXL0010Scrn00.toString(), "Description");
        scrnMsg.rptSqlDescTxt.setNameForMessage(label);

        // --------------------------------------------------
        // Scrn01
        // --------------------------------------------------
        // Report ID
        label = labelConv.convLabel2i18nLabel(ScrnId.NWXL0010Scrn01.toString(), "Report ID");
        scrnMsg.rptSqlId_01.setNameForMessage(label);
        
        // Report Name
        label = labelConv.convLabel2i18nLabel(ScrnId.NWXL0010Scrn01.toString(), "Report Name");
        scrnMsg.rptSqlNm_01.setNameForMessage(label);
        
        // Function ID
        label = labelConv.convLabel2i18nLabel(ScrnId.NWXL0010Scrn01.toString(), "Function ID");
        scrnMsg.rptSqlFuncId_01.setNameForMessage(label);
        
        // Data Timing
        label = labelConv.convLabel2i18nLabel(ScrnId.NWXL0010Scrn01.toString(), "Data Timin");
        scrnMsg.rptSqlDataTmgTxt_01.setNameForMessage(label);
        
        // Requestor 
        label = labelConv.convLabel2i18nLabel(ScrnId.NWXL0010Scrn01.toString(), "Requestor");
        scrnMsg.rptSqlReqPsnCd_01.setNameForMessage(label);
        
        // Request Date
        label = labelConv.convLabel2i18nLabel(ScrnId.NWXL0010Scrn01.toString(), "Request Date");
        scrnMsg.rptSqlReqDt_01.setNameForMessage(label);

        // SQL Creator 
        label = labelConv.convLabel2i18nLabel(ScrnId.NWXL0010Scrn01.toString(), "SQL Creator");
        scrnMsg.rptSqlRgtnPsnCd_01.setNameForMessage(label);
        
        // SQL Create Date
        label = labelConv.convLabel2i18nLabel(ScrnId.NWXL0010Scrn01.toString(), "SQL Create Date");
        scrnMsg.rptSqlRgtnDt_01.setNameForMessage(label);

        // Description
        label = labelConv.convLabel2i18nLabel(ScrnId.NWXL0010Scrn01.toString(), "Description");
        scrnMsg.rptSqlDescTxt_01.setNameForMessage(label);
        
        // SQL
        label = labelConv.convLabel2i18nLabel(ScrnId.NWXL0010Scrn01.toString(), "SQL");
        scrnMsg.xxRptSqlTxt_01.setNameForMessage(label);
    }

}
