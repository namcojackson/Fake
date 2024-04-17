/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1090;

import static business.servlet.NPAL1090.constant.NPAL1090Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.DISPLAY_NM_CARR_NM_H1;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.DISPLAY_NM_FSR_NUM_H1;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.DISPLAY_NM_LOC_NM_H1;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.DISPLAY_NM_PRCH_REQ_APVL_STS_CD_SE;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.DISPLAY_NM_PRCH_REQ_CRAT_DT_HF;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.DISPLAY_NM_PRCH_REQ_CRAT_DT_HT;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.DISPLAY_NM_PRCH_REQ_NUM_H1;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.DISPLAY_NM_PRCH_REQ_SRC_TP_CD_SE;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.DISPLAY_NM_PRCH_REQ_STS_CD_SE;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.DISPLAY_NM_PRCH_REQ_TP_CD_SE;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.DISPLAY_NM_RQST_RCV_DT_HF;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.DISPLAY_NM_RQST_RCV_DT_HT;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.DISPLAY_NM_RTL_SWH_CD_H1;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.DISPLAY_NM_RTL_WH_CD_H1;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.DISPLAY_NM_SHIP_TO_CUST_CD_H1;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.DISPLAY_NM_SHPG_SVC_LVL_CD_H1;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.DISPLAY_NM_SRCH_OPT_NM_H1;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.DISPLAY_NM_SVC_MACH_SER_NUM_H1;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.DISPLAY_NM_SVC_TASK_NUM_H1;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.DISPLAY_NM_TECH_NM_H1;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1090.NPAL1090CMsg;
import business.servlet.NPAL1090.common.NPAL1090CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <pre>
 * Business ID : NPAL1090 Tech Parts Request List
 * Function Name : Init
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/09/2015   CITS       Yasushi Nomura       Create          N/A
 * 01/24/2017   CITS            T.Kikuhara      Update          QC#10621
 *</pre>
 */
public class NPAL1090_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_APPLICATION_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1090BMsg scrnMsg = (NPAL1090BMsg) bMsg;

        NPAL1090CMsg bizMsg = new NPAL1090CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("20");

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        // Set UserID
        ZYPEZDItemValueSetter.setValue(bizMsg.srchOptUsrId_U1, getContextUserInfo().getUserId());

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1090BMsg scrnMsg = (NPAL1090BMsg) bMsg;
        NPAL1090CMsg bizMsg = (NPAL1090CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_APPLICATION_ID);

        NPAL1090CommonLogic.ctrlScrnItemDispInit(this, scrnMsg, funcList);

        scrnMsg.setFocusItem(scrnMsg.prchReqNum_H1);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NPAL1090BMsg scrnMsg = (NPAL1090BMsg) bMsg;
        // QC#10621 update start
        scrnMsg.prchReqNum_H1.setNameForMessage(DISPLAY_NM_PRCH_REQ_NUM_H1);
        scrnMsg.prchReqTpCd_SE.setNameForMessage(DISPLAY_NM_PRCH_REQ_TP_CD_SE);
        scrnMsg.prchReqStsCd_SE.setNameForMessage(DISPLAY_NM_PRCH_REQ_STS_CD_SE);
        scrnMsg.prchReqApvlStsCd_SE.setNameForMessage(DISPLAY_NM_PRCH_REQ_APVL_STS_CD_SE);
        scrnMsg.prchReqCratDt_HF.setNameForMessage(DISPLAY_NM_PRCH_REQ_CRAT_DT_HF);
        scrnMsg.prchReqCratDt_HT.setNameForMessage(DISPLAY_NM_PRCH_REQ_CRAT_DT_HT);
        scrnMsg.prchReqSrcTpCd_SE.setNameForMessage(DISPLAY_NM_PRCH_REQ_SRC_TP_CD_SE);
        scrnMsg.fsrNum_H1.setNameForMessage(DISPLAY_NM_FSR_NUM_H1);
        scrnMsg.svcTaskNum_H1.setNameForMessage(DISPLAY_NM_SVC_TASK_NUM_H1);
        scrnMsg.svcMachSerNum_H1.setNameForMessage(DISPLAY_NM_SVC_MACH_SER_NUM_H1);
        scrnMsg.rqstRcvDt_HF.setNameForMessage(DISPLAY_NM_RQST_RCV_DT_HF);
        scrnMsg.rqstRcvDt_HT.setNameForMessage(DISPLAY_NM_RQST_RCV_DT_HT);
        scrnMsg.techNm_H1.setNameForMessage(DISPLAY_NM_TECH_NM_H1);
        scrnMsg.rtlWhCd_H1.setNameForMessage(DISPLAY_NM_RTL_WH_CD_H1);
        scrnMsg.rtlSwhCd_H1.setNameForMessage(DISPLAY_NM_RTL_SWH_CD_H1);
        scrnMsg.shipToCustCd_H1.setNameForMessage(DISPLAY_NM_SHIP_TO_CUST_CD_H1);
        scrnMsg.locNm_H1.setNameForMessage(DISPLAY_NM_LOC_NM_H1);
        scrnMsg.shpgSvcLvlCd_SE.setNameForMessage(DISPLAY_NM_SHPG_SVC_LVL_CD_H1);
        scrnMsg.carrNm_H1.setNameForMessage(DISPLAY_NM_CARR_NM_H1);
        scrnMsg.srchOptNm_TX.setNameForMessage(DISPLAY_NM_SRCH_OPT_NM_H1);
        // QC#10621 update end
    }
}
