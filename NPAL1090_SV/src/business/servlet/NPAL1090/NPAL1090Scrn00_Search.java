/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1090;

import static business.servlet.NPAL1090.constant.NPAL1090Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.NMAM0288E;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.SCREEN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1090.NPAL1090CMsg;
import business.servlet.NPAL1090.common.NPAL1090CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1090 Tech Parts Request List
 * Function Name : Button Action to Serch
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/09/2015   CITS       Yasushi Nomura       Create          N/A
 *</pre>
 */
public class NPAL1090Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1090BMsg scrnMsg = (NPAL1090BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.prchReqNum_H1) && !ZYPCommonFunc.hasValue(scrnMsg.prchReqTpCd_SE) && !ZYPCommonFunc.hasValue(scrnMsg.prchReqStsCd_SE) && !ZYPCommonFunc.hasValue(scrnMsg.prchReqApvlStsCd_SE)
                && !ZYPCommonFunc.hasValue(scrnMsg.prchReqCratDt_HF) && !ZYPCommonFunc.hasValue(scrnMsg.prchReqCratDt_HT) && !ZYPCommonFunc.hasValue(scrnMsg.prchReqSrcTpCd_SE) && !ZYPCommonFunc.hasValue(scrnMsg.fsrNum_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.svcTaskNum_H1) && !ZYPCommonFunc.hasValue(scrnMsg.svcMachSerNum_H1) && !ZYPCommonFunc.hasValue(scrnMsg.rqstRcvDt_HF) && !ZYPCommonFunc.hasValue(scrnMsg.rqstRcvDt_HT)
                && !ZYPCommonFunc.hasValue(scrnMsg.techNm_H1) && !ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd_H1) && !ZYPCommonFunc.hasValue(scrnMsg.rtlSwhCd_H1) && !ZYPCommonFunc.hasValue(scrnMsg.shipToCustCd_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.locNm_H1) && !ZYPCommonFunc.hasValue(scrnMsg.shpgSvcLvlCd_SE) && !ZYPCommonFunc.hasValue(scrnMsg.carrNm_H1)) {
            scrnMsg.prchReqNum_H1.setErrorInfo(1, NMAM0288E);
            scrnMsg.prchReqTpCd_SE.setErrorInfo(1, NMAM0288E);
            scrnMsg.prchReqStsCd_SE.setErrorInfo(1, NMAM0288E);
            scrnMsg.prchReqApvlStsCd_SE.setErrorInfo(1, NMAM0288E);
            scrnMsg.prchReqCratDt_HF.setErrorInfo(1, NMAM0288E);
            scrnMsg.prchReqCratDt_HT.setErrorInfo(1, NMAM0288E);
            scrnMsg.prchReqSrcTpCd_SE.setErrorInfo(1, NMAM0288E);
            scrnMsg.fsrNum_H1.setErrorInfo(1, NMAM0288E);
            scrnMsg.svcTaskNum_H1.setErrorInfo(1, NMAM0288E);
            scrnMsg.svcMachSerNum_H1.setErrorInfo(1, NMAM0288E);
            scrnMsg.rqstRcvDt_HF.setErrorInfo(1, NMAM0288E);
            scrnMsg.rqstRcvDt_HT.setErrorInfo(1, NMAM0288E);
            scrnMsg.techNm_H1.setErrorInfo(1, NMAM0288E);
            scrnMsg.rtlWhCd_H1.setErrorInfo(1, NMAM0288E);
            scrnMsg.rtlSwhCd_H1.setErrorInfo(1, NMAM0288E);
            scrnMsg.shipToCustCd_H1.setErrorInfo(1, NMAM0288E);
            scrnMsg.locNm_H1.setErrorInfo(1, NMAM0288E);
            scrnMsg.shpgSvcLvlCd_SE.setErrorInfo(1, NMAM0288E);
            scrnMsg.carrNm_H1.setErrorInfo(1, NMAM0288E);
        }

        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);

        scrnMsg.addCheckItem(scrnMsg.prchReqNum_H1);
        scrnMsg.addCheckItem(scrnMsg.prchReqTpCd_SE);
        scrnMsg.addCheckItem(scrnMsg.prchReqStsCd_SE);
        scrnMsg.addCheckItem(scrnMsg.prchReqApvlStsCd_SE);
        scrnMsg.addCheckItem(scrnMsg.prchReqCratDt_HF);
        scrnMsg.addCheckItem(scrnMsg.prchReqCratDt_HT);
        scrnMsg.addCheckItem(scrnMsg.prchReqSrcTpCd_SE);
        scrnMsg.addCheckItem(scrnMsg.fsrNum_H1);
        scrnMsg.addCheckItem(scrnMsg.svcTaskNum_H1);
        scrnMsg.addCheckItem(scrnMsg.svcMachSerNum_H1);
        scrnMsg.addCheckItem(scrnMsg.rqstRcvDt_HF);
        scrnMsg.addCheckItem(scrnMsg.rqstRcvDt_HT);
        scrnMsg.addCheckItem(scrnMsg.techNm_H1);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd_H1);
        scrnMsg.addCheckItem(scrnMsg.rtlSwhCd_H1);
        scrnMsg.addCheckItem(scrnMsg.shipToCustCd_H1);
        scrnMsg.addCheckItem(scrnMsg.locNm_H1);
        scrnMsg.addCheckItem(scrnMsg.shpgSvcLvlCd_SE);
        scrnMsg.addCheckItem(scrnMsg.carrNm_H1);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1090BMsg scrnMsg = (NPAL1090BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

        NPAL1090CMsg bizMsg = new NPAL1090CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1090BMsg scrnMsg = (NPAL1090BMsg) bMsg;
        NPAL1090CMsg bizMsg = (NPAL1090CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NPAL1090CommonLogic.setTableScreen(scrnMsg);

        // set focus
        scrnMsg.setFocusItem(scrnMsg.prchReqNum_H1);

    }
}