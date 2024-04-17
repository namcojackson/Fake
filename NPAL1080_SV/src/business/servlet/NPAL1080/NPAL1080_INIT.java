/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1080;

import static business.servlet.NPAL1080.constant.NPAL1080Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.LABEL_TXT_CTAC_PSN_NM;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.LABEL_TXT_FSR_NUM;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.LABEL_TXT_PRCH_REQ_NUM;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.LABEL_TXT_PRCH_REQ_TP;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.LABEL_TXT_RQST_RCV_DT;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.LABEL_TXT_RQST_TECH_TOC_CD;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.LABEL_TXT_SVC_MACH_SER_NUM;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.LABEL_TXT_SVC_TASK_NUM;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.LABEL_TXT_TECH_NM;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.MODE_BTN_INIT;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.MODE_BTN_OTHER;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.RUN_MODE_NEW;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.TAB_DETAIL;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1080.NPAL1080CMsg;
import business.servlet.NPAL1080.common.NPAL1080CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <pre>
 * Business ID : NPAL1080 Tech Parts Request Entry
 * Function Name : Init
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/16/2015   CITS       Yasushi Nomura       Create          N/A
 * 12/19/2016   CITS            K.Ogino         Update          QC#15820
 * 10/25/2017   CITS            S.Katsuma       Update          QC#21896
 * 2020/11/12   CITS            K.Ogino         Update          QC#57659
 *</pre>
 */
public class NPAL1080_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_APPLICATION_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length == 1) {
            Object param01 = (Object) params[0];

            if (param01 instanceof EZDBStringItem) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.prchReqNum_H1, (EZDBStringItem) param01);
                ZYPEZDItemValueSetter.setValue(scrnMsg.prchReqNum_HD, (EZDBStringItem) param01);

            } else {
                // String
                ZYPEZDItemValueSetter.setValue(scrnMsg.prchReqNum_H1, (String) param01);
                ZYPEZDItemValueSetter.setValue(scrnMsg.prchReqNum_HD, (String) param01);
            }
        }

        NPAL1080CMsg bizMsg = new NPAL1080CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("20");

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
        NPAL1080CMsg bizMsg = (NPAL1080CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // init
        NPAL1080CommonLogic.setAppFracDigit(scrnMsg);
        NPAL1080CommonLogic.initCommonButton(this);
        NPAL1080CommonLogic.commonInit(scrnMsg);
        NPAL1080CommonLogic.clearGUIAttribute(scrnMsg, scrnMsg.A.no(0).getBaseContents());

        scrnMsg.xxDplyTab.setValue(TAB_DETAIL);
        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_APPLICATION_ID);
        if (RUN_MODE_NEW.equals(scrnMsg.xxEdtModeFlg.getValue())) {
            NPAL1080CommonLogic.control(this, scrnMsg, MODE_BTN_INIT, funcList);
        } else {
            NPAL1080CommonLogic.control(this, scrnMsg, MODE_BTN_OTHER, funcList);
        }

        scrnMsg.setFocusItem(scrnMsg.prchReqNum_H1);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;

        scrnMsg.prchReqNum_H1.setNameForMessage(LABEL_TXT_PRCH_REQ_NUM);
        scrnMsg.prchReqTpCd_SE.setNameForMessage(LABEL_TXT_PRCH_REQ_TP);
        scrnMsg.rqstRcvDt_H1.setNameForMessage(LABEL_TXT_RQST_RCV_DT);
        scrnMsg.xxDtNm_H2.setNameForMessage(LABEL_TXT_RQST_RCV_DT);
        scrnMsg.fsrNum_H1.setNameForMessage(LABEL_TXT_FSR_NUM);
        scrnMsg.svcTaskNum_H1.setNameForMessage(LABEL_TXT_SVC_TASK_NUM);
        scrnMsg.svcMachSerNum_H1.setNameForMessage(LABEL_TXT_SVC_MACH_SER_NUM);
        scrnMsg.ctacPsnNm_H1.setNameForMessage(LABEL_TXT_CTAC_PSN_NM);
        scrnMsg.rqstTechTocCd_H1.setNameForMessage(LABEL_TXT_RQST_TECH_TOC_CD);
        scrnMsg.techNm_H1.setNameForMessage(LABEL_TXT_TECH_NM);
        scrnMsg.destRtlWhCd_H1.setNameForMessage("Tech WH / SWH");
        scrnMsg.destRtlSwhCd_H1.setNameForMessage("Tech WH / SWH");
        scrnMsg.shipToCustCd_H1.setNameForMessage("Ship To Customer");
        scrnMsg.carrNm_H1.setNameForMessage("Requested Carrier");
        scrnMsg.delyAddlCmntTxt_H2.setNameForMessage("Shipping Instructions");
        // QC#57659
        scrnMsg.xxDtTm_H1.setNameForMessage("Time Needed");

        scrnMsg.prchReqLineCmntTxt_D2.setNameForMessage("Freeze / Cancel Reason");
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox_D1.setNameForMessage("Check Box");
            scrnMsg.A.no(i).prchReqLineTpCd_SE.setNameForMessage("Line Type");
            scrnMsg.A.no(i).mdseCd_D1.setNameForMessage("Item Number");
            scrnMsg.A.no(i).prchReqQty_D1.setNameForMessage("Req Qty");
            // START 2017/10/25 S.Katsuma QC#21896 ADD
            scrnMsg.A.no(i).procrTpCd_SE.setNameForMessage("Source Type");
            // END 2017/10/25 S.Katsuma QC#21896 ADD
            scrnMsg.A.no(i).prntVndNm_D1.setNameForMessage("WH / Supplier");
            scrnMsg.A.no(i).locNm_D1.setNameForMessage("SWH / Site");
            scrnMsg.A.no(i).prchReqLineCmntTxt_D1.setNameForMessage("Line Comment");
        }
    }
}
