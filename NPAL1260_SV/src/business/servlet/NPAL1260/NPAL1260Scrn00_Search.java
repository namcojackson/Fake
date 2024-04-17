/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1260;

import static business.servlet.NPAL1260.constant.NPAL1260Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NPAL1260.constant.NPAL1260Constant.NMAM0288E;
import static business.servlet.NPAL1260.constant.NPAL1260Constant.SCREEN_ID;
import static business.servlet.NPAL1260.constant.NPAL1260Constant.ZZM9010E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1260.NPAL1260CMsg;
import business.servlet.NPAL1260.common.NPAL1260CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1260 Tech Parts Request Inquiry
 * Function Name : Button Action to Serch
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/02   CITS            Yasushi Nomura  Create          N/A
 * 2018/12/04   Fujitsu         S.Takami        Update          QC#27093
 * 03/17/2020   Fujitsu         R.Nakamura      Update          QC#56104
 *</pre>
 */
public class NPAL1260Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1260BMsg scrnMsg = (NPAL1260BMsg) bMsg;
        if (!ZYPCommonFunc.hasValue(scrnMsg.prchReqNum_H1) && !ZYPCommonFunc.hasValue(scrnMsg.prchReqTpCd_SE) && !ZYPCommonFunc.hasValue(scrnMsg.mdseCd_H1) && !ZYPCommonFunc.hasValue(scrnMsg.prchReqLineTpCd_SE)
                && !ZYPCommonFunc.hasValue(scrnMsg.prchReqLineStsCd_SE) && !ZYPCommonFunc.hasValue(scrnMsg.prchReqCratDt_HF) && !ZYPCommonFunc.hasValue(scrnMsg.prchReqCratDt_HT) && !ZYPCommonFunc.hasValue(scrnMsg.rqstRcvDt_HF)
                && !ZYPCommonFunc.hasValue(scrnMsg.rqstRcvDt_HT) && !ZYPCommonFunc.hasValue(scrnMsg.shipDt_HF) && !ZYPCommonFunc.hasValue(scrnMsg.shipDt_HT) && !ZYPCommonFunc.hasValue(scrnMsg.lineBizTpCd_SE)
                && !ZYPCommonFunc.hasValue(scrnMsg.prchReqSrcTpCd_SE) && !ZYPCommonFunc.hasValue(scrnMsg.fsrNum_H1) && !ZYPCommonFunc.hasValue(scrnMsg.svcTaskNum_H1) && !ZYPCommonFunc.hasValue(scrnMsg.svcMachSerNum_H1)
                // Mod Start 2020/03/17 QC#56104
//                && !ZYPCommonFunc.hasValue(scrnMsg.poOrdNum_H1) && !ZYPCommonFunc.hasValue(scrnMsg.soNum_H1) && !ZYPCommonFunc.hasValue(scrnMsg.prchReqNum_H2) && !ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.poOrdNum_H1) && !ZYPCommonFunc.hasValue(scrnMsg.rwsRefNum_H1) && !ZYPCommonFunc.hasValue(scrnMsg.prchReqNum_H2) && !ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd_H1)
                // Mod End 2020/03/17 QC#56104
                && !ZYPCommonFunc.hasValue(scrnMsg.rtlSwhCd_H1) && !ZYPCommonFunc.hasValue(scrnMsg.prntVndNm_H1) && !ZYPCommonFunc.hasValue(scrnMsg.locNm_H1) && !ZYPCommonFunc.hasValue(scrnMsg.destRtlWhCd_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.destRtlSwhCd_H1) && !ZYPCommonFunc.hasValue(scrnMsg.techNm_H1) && !ZYPCommonFunc.hasValue(scrnMsg.dsAcctNm_H1) && !ZYPCommonFunc.hasValue(scrnMsg.carrNm_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.shpgSvcLvlCd_SE) && !ZYPCommonFunc.hasValue(scrnMsg.proNum_H1) && !ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_H1) && !ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_H2)
                && !ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_H3) && !ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_H4) //
                && !ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_H5)) { // 2018/12/04 QC#27093 Add xxChkBox_H5 Check
            scrnMsg.prchReqNum_H1.setErrorInfo(1, NMAM0288E);
            scrnMsg.prchReqTpCd_SE.setErrorInfo(1, NMAM0288E);
            scrnMsg.mdseCd_H1.setErrorInfo(1, NMAM0288E);
            scrnMsg.prchReqLineTpCd_SE.setErrorInfo(1, NMAM0288E);
            scrnMsg.prchReqLineStsCd_SE.setErrorInfo(1, NMAM0288E);
            scrnMsg.prchReqCratDt_HF.setErrorInfo(1, NMAM0288E);
            scrnMsg.prchReqCratDt_HT.setErrorInfo(1, NMAM0288E);
            scrnMsg.rqstRcvDt_HF.setErrorInfo(1, NMAM0288E);
            scrnMsg.rqstRcvDt_HT.setErrorInfo(1, NMAM0288E);
            scrnMsg.shipDt_HF.setErrorInfo(1, NMAM0288E);
            scrnMsg.shipDt_HT.setErrorInfo(1, NMAM0288E);
            scrnMsg.lineBizTpCd_SE.setErrorInfo(1, NMAM0288E);
            scrnMsg.prchReqSrcTpCd_SE.setErrorInfo(1, NMAM0288E);
            scrnMsg.fsrNum_H1.setErrorInfo(1, NMAM0288E);
            scrnMsg.svcTaskNum_H1.setErrorInfo(1, NMAM0288E);
            scrnMsg.svcMachSerNum_H1.setErrorInfo(1, NMAM0288E);
            scrnMsg.poOrdNum_H1.setErrorInfo(1, NMAM0288E);
            // Mod Start 2020/03/17 QC#56104
//            scrnMsg.soNum_H1.setErrorInfo(1, NMAM0288E);
            scrnMsg.rwsRefNum_H1.setErrorInfo(1, NMAM0288E);
            // Mod End 2020/03/17 QC#56104
            scrnMsg.prchReqNum_H2.setErrorInfo(1, NMAM0288E);
            scrnMsg.rtlWhCd_H1.setErrorInfo(1, NMAM0288E);
            scrnMsg.rtlSwhCd_H1.setErrorInfo(1, NMAM0288E);
            scrnMsg.prntVndNm_H1.setErrorInfo(1, NMAM0288E);
            scrnMsg.locNm_H1.setErrorInfo(1, NMAM0288E);
            scrnMsg.destRtlWhCd_H1.setErrorInfo(1, NMAM0288E);
            scrnMsg.destRtlSwhCd_H1.setErrorInfo(1, NMAM0288E);
            scrnMsg.techNm_H1.setErrorInfo(1, NMAM0288E);
            scrnMsg.dsAcctNm_H1.setErrorInfo(1, NMAM0288E);
            scrnMsg.carrNm_H1.setErrorInfo(1, NMAM0288E);
            scrnMsg.shpgSvcLvlCd_SE.setErrorInfo(1, NMAM0288E);
            scrnMsg.proNum_H1.setErrorInfo(1, NMAM0288E);
            scrnMsg.xxChkBox_H1.setErrorInfo(1, NMAM0288E);
            scrnMsg.xxChkBox_H2.setErrorInfo(1, NMAM0288E);
            scrnMsg.xxChkBox_H3.setErrorInfo(1, NMAM0288E);
            scrnMsg.xxChkBox_H4.setErrorInfo(1, NMAM0288E);
            // 2018/12/04 QC#27093 Add Start
            scrnMsg.xxChkBox_H5.setErrorInfo(1, NMAM0288E);
            // 2018/12/04 QC#27093 Add End
        }
        // Date check
        if (ZYPCommonFunc.hasValue(scrnMsg.prchReqCratDt_HF) && ZYPCommonFunc.hasValue(scrnMsg.prchReqCratDt_HT)) {
            if (scrnMsg.prchReqCratDt_HF.getValue().compareTo(scrnMsg.prchReqCratDt_HT.getValue()) > 0) {
                scrnMsg.prchReqCratDt_HF.setErrorInfo(1, ZZM9010E, new String[] {"Date Created From", "Date Created To" });
                scrnMsg.prchReqCratDt_HT.setErrorInfo(1, ZZM9010E, new String[] {"Date Created From", "Date Created To" });
            }
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.rqstRcvDt_HF) && ZYPCommonFunc.hasValue(scrnMsg.rqstRcvDt_HT)) {
            if (scrnMsg.rqstRcvDt_HF.getValue().compareTo(scrnMsg.rqstRcvDt_HT.getValue()) > 0) {
                scrnMsg.rqstRcvDt_HF.setErrorInfo(1, ZZM9010E, new String[] {"Date Needed From", "Date Needed To" });
                scrnMsg.rqstRcvDt_HT.setErrorInfo(1, ZZM9010E, new String[] {"Date Needed From", "Date Needed To" });
            }
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.shipDt_HF) && ZYPCommonFunc.hasValue(scrnMsg.shipDt_HT)) {
            if (scrnMsg.shipDt_HF.getValue().compareTo(scrnMsg.shipDt_HT.getValue()) > 0) {
                scrnMsg.shipDt_HF.setErrorInfo(1, ZZM9010E, new String[] {"Ship Date From", "Ship Date To" });
                scrnMsg.shipDt_HT.setErrorInfo(1, ZZM9010E, new String[] {"Ship Date From", "Ship Date To" });
            }
        }
        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);

        scrnMsg.addCheckItem(scrnMsg.prchReqNum_H1);
        scrnMsg.addCheckItem(scrnMsg.prchReqTpCd_SE);
        scrnMsg.addCheckItem(scrnMsg.mdseCd_H1);
        scrnMsg.addCheckItem(scrnMsg.prchReqLineTpCd_SE);
        scrnMsg.addCheckItem(scrnMsg.prchReqLineStsCd_SE);
        scrnMsg.addCheckItem(scrnMsg.prchReqCratDt_HF);
        scrnMsg.addCheckItem(scrnMsg.prchReqCratDt_HT);
        scrnMsg.addCheckItem(scrnMsg.rqstRcvDt_HF);
        scrnMsg.addCheckItem(scrnMsg.rqstRcvDt_HT);
        scrnMsg.addCheckItem(scrnMsg.shipDt_HF);
        scrnMsg.addCheckItem(scrnMsg.shipDt_HT);
        scrnMsg.addCheckItem(scrnMsg.lineBizTpCd_SE);
        scrnMsg.addCheckItem(scrnMsg.prchReqSrcTpCd_SE);
        scrnMsg.addCheckItem(scrnMsg.fsrNum_H1);
        scrnMsg.addCheckItem(scrnMsg.svcTaskNum_H1);
        scrnMsg.addCheckItem(scrnMsg.svcMachSerNum_H1);
        scrnMsg.addCheckItem(scrnMsg.poOrdNum_H1);
        // Mod Start 2020/03/17 QC#56104
//        scrnMsg.addCheckItem(scrnMsg.soNum_H1);
        scrnMsg.addCheckItem(scrnMsg.rwsRefNum_H1);
        // Mod End 2020/03/17 QC#56104
        scrnMsg.addCheckItem(scrnMsg.prchReqNum_H2);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd_H1);
        scrnMsg.addCheckItem(scrnMsg.rtlSwhCd_H1);
        scrnMsg.addCheckItem(scrnMsg.prntVndNm_H1);
        scrnMsg.addCheckItem(scrnMsg.locNm_H1);
        scrnMsg.addCheckItem(scrnMsg.destRtlWhCd_H1);
        scrnMsg.addCheckItem(scrnMsg.destRtlSwhCd_H1);
        scrnMsg.addCheckItem(scrnMsg.techNm_H1);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_H1);
        scrnMsg.addCheckItem(scrnMsg.carrNm_H1);
        scrnMsg.addCheckItem(scrnMsg.shpgSvcLvlCd_SE);
        scrnMsg.addCheckItem(scrnMsg.proNum_H1);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_H1);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_H2);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_H3);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_H4);
        // 2018/12/04 QC#27093 Add Start
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_H5);
        // 2018/12/04 QC#27093 Add End

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1260BMsg scrnMsg = (NPAL1260BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

        NPAL1260CMsg bizMsg = new NPAL1260CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1260BMsg scrnMsg = (NPAL1260BMsg) bMsg;
        NPAL1260CMsg bizMsg = (NPAL1260CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NPAL1260CommonLogic.setTableScreen(scrnMsg);

        // set focus
        scrnMsg.setFocusItem(scrnMsg.prchReqNum_H1);
    }
}
