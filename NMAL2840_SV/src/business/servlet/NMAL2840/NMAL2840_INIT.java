/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2840;

import static business.servlet.NMAL2840.constant.NMAL2840Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2840.NMAL2840CMsg;
import business.servlet.NMAL2840.common.NMAL2840CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/25   Fujitsu         R.Nakamura      Create          N/A
 * 2016/06/14   Fujitsu         H.Ikeda         Update          QC#9960
 * 2016/07/01   Fujitsu         R.Nakamura      Update          QC#11316
 * 2016/07/12   Fujitsu         R.Nakamura      Update          QC#9536
 *</pre>
 */
public class NMAL2840_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // QC9960 CHG Start
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
        // QC9960 CHG End
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2840BMsg scrnMsg = (NMAL2840BMsg) bMsg;
        NMAL2840CMsg bizMsg = new NMAL2840CMsg();

        bizMsg.setBusinessID("NMAL2840");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2840BMsg scrnMsg = (NMAL2840BMsg) bMsg;
        NMAL2840CMsg bizMsg = (NMAL2840CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL2840CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.dunsCritCd_PS);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NMAL2840BMsg scrnMsg = (NMAL2840BMsg) bMsg;

        scrnMsg.xxDtTm_1.setNameForMessage("Extract Mode");
        scrnMsg.dunsCritDefValFlg_11.setNameForMessage("Include Customers");
        scrnMsg.dunsCritDefValFlg_12.setNameForMessage("Include Prospects");
        scrnMsg.dunsCritDefValFlg_21.setNameForMessage("Include Records Never Cleansed Before");
        scrnMsg.dunsCritDefValFlg_31.setNameForMessage("Include Records with Name Change since Last Extract");
        scrnMsg.dunsCritDefValFlg_32.setNameForMessage("Include Records with Address Change since Last Extract");
        scrnMsg.dunsCritDefValFlg_33.setNameForMessage("Include Records with Phone# Change since Last Extract");
        scrnMsg.effFromDt.setNameForMessage("Include records cleansed before Date");

        scrnMsg.xxDtTm_2.setNameForMessage("Import DNB Date");
        scrnMsg.dunsCritDescTxt_51.setNameForMessage("DNB Name Profile Code");
        scrnMsg.dunsCritDescTxt_52.setNameForMessage("DNB Street No Profile Code");
        scrnMsg.dunsCritDescTxt_53.setNameForMessage("DNB Street Name Profile Code");
        scrnMsg.dunsCritDescTxt_54.setNameForMessage("DNB Match Grade");
        scrnMsg.dunsCritDescTxt_55.setNameForMessage("DNB BEMFAB Code");
        scrnMsg.dunsCritDescTxt_56.setNameForMessage("DNB Nixie A");
        scrnMsg.dunsCritDescTxt_57.setNameForMessage("DNB Confidence Code");
        scrnMsg.dunsCritDefValFlg_51.setNameForMessage("DNB Name Profile Code Is Null");
        scrnMsg.dunsCritDefValFlg_52.setNameForMessage("DNB Street No Profile Code Is Null");
        scrnMsg.dunsCritDefValFlg_53.setNameForMessage("DNB Street Name Profile Code Is Null");
        scrnMsg.dunsCritDefValFlg_54.setNameForMessage("DNB Match Grade Is Null");
        scrnMsg.dunsCritDefValFlg_55.setNameForMessage("DNB BEMFAB Code Is Null");
        scrnMsg.dunsCritDefValFlg_56.setNameForMessage("DNB Nixie A Is Null");
        scrnMsg.dunsCritDefValFlg_57.setNameForMessage("DNB Confidence Code Is Null");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).dunsProcTpDescTxt.setNameForMessage("Process");
            scrnMsg.A.no(i).dunsFileNm_2.setNameForMessage("File Name");
            scrnMsg.A.no(i).dunsFileLineNum.setNameForMessage("No. of Recoreds");
            scrnMsg.A.no(i).xxDtTm_4.setNameForMessage("Processed On");
            // Mod Start 2016/07/01 QC#11316
            // scrnMsg.A.no(i).dunsProcById.setNameForMessage("Processed By");
            scrnMsg.A.no(i).fill103Txt.setNameForMessage("Processed By");
            // scrnMsg.A.no(i).dunsProcById.setNameForMessage("Comments");
            scrnMsg.A.no(i).xxDunsProcCmntTxt.setNameForMessage("Processed By");
            // Mod End 2016/07/01 QC#11316
        }
    }
}
