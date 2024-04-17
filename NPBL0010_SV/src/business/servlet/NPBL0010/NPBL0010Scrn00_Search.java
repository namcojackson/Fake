/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0010;

import static business.servlet.NPBL0010.constant.NPBL0010Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDCommonFunc;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPBL0010.NPBL0010CMsg;
import business.servlet.NPBL0010.common.NPBL0010CommonLogic; // import
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPBL0010 Inventory Request List
 * Function Name : Button Action to Search
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/13/2016   CITS            Makoto Okigami  Create          N/A
 * 04/05/2016   CITS            K.Ogino         Update          N/A
 * 08/23/2017   CITS            H.Naoi          Update          Sol#097(QC#18398)
 * 03/23/2020   Fujitsu         R.Nakamura      Update          QC#55940
 *</pre>
 */
public class NPBL0010Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPBL0010BMsg scrnMsg = (NPBL0010BMsg) bMsg;

        // Mod Start 2020/03/23 QC#55940
        if (!ZYPCommonFunc.hasValue(scrnMsg.prchReqNum) && !ZYPCommonFunc.hasValue(scrnMsg.prchReqTpCd_SL) && !ZYPCommonFunc.hasValue(scrnMsg.prchReqStsCd_SL) && !ZYPCommonFunc.hasValue(scrnMsg.prchReqApvlStsCd_SL)
                && !ZYPCommonFunc.hasValue(scrnMsg.prchReqCratDt_RF) && !ZYPCommonFunc.hasValue(scrnMsg.prchReqCratDt_RT) && !ZYPCommonFunc.hasValue(scrnMsg.rqstRcvDt_NF) && !ZYPCommonFunc.hasValue(scrnMsg.rqstRcvDt_NT)
                && !ZYPCommonFunc.hasValue(scrnMsg.prchReqSrcTpCd_SL) && !ZYPCommonFunc.hasValue(scrnMsg.trxRefNum) && !ZYPCommonFunc.hasValue(scrnMsg.mrpPlnNm) && !ZYPCommonFunc.hasValue(scrnMsg.fullPsnNm) && !ZYPCommonFunc.hasValue(scrnMsg.shpgSvcLvlCd_SL)
                && !ZYPCommonFunc.hasValue(scrnMsg.carrNm) && !ZYPCommonFunc.hasValue(scrnMsg.srcRtlWhCd) && !ZYPCommonFunc.hasValue(scrnMsg.rtlWhNm_SW) && !ZYPCommonFunc.hasValue(scrnMsg.srcRtlSwhCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.rtlSwhNm_SS) && !ZYPCommonFunc.hasValue(scrnMsg.destRtlWhCd) && !ZYPCommonFunc.hasValue(scrnMsg.rtlWhNm_DW) && !ZYPCommonFunc.hasValue(scrnMsg.destRtlSwhCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.rtlSwhNm_DS) && !ZYPCommonFunc.hasValue(scrnMsg.vndCd) && !ZYPCommonFunc.hasValue(scrnMsg.dplyVndNm) && !ZYPCommonFunc.hasValue(scrnMsg.shipToCustCd_EO)
//                && !ZYPCommonFunc.hasValue(scrnMsg.shipToLocNm_EO)) {
                && !ZYPCommonFunc.hasValue(scrnMsg.shipToLocNm_EO) && !ZYPCommonFunc.hasValue(scrnMsg.mdseCd)) {
            scrnMsg.prchReqNum.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.prchReqTpCd_SL.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.prchReqStsCd_SL.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.prchReqApvlStsCd_SL.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.prchReqCratDt_RF.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.prchReqCratDt_RT.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.rqstRcvDt_NF.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.rqstRcvDt_NT.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.prchReqSrcTpCd_SL.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.trxRefNum.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.mrpPlnNm.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.fullPsnNm.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.shpgSvcLvlCd_SL.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.carrNm.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.srcRtlWhCd.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.rtlWhNm_SW.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.srcRtlSwhCd.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.rtlSwhNm_SS.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.destRtlWhCd.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.rtlWhNm_DW.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.destRtlSwhCd.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.rtlSwhNm_DS.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.vndCd.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.dplyVndNm.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.shipToCustCd_EO.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.shipToLocNm_EO.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.mdseCd.setErrorInfo(1, NMAM0288E, new String[] {});
        }
        // Mod End 2020/03/23 QC#55940

        scrnMsg.addCheckItem(scrnMsg.prchReqNum);
        scrnMsg.addCheckItem(scrnMsg.prchReqTpCd_SL);
        scrnMsg.addCheckItem(scrnMsg.prchReqStsCd_SL);
        scrnMsg.addCheckItem(scrnMsg.prchReqApvlStsCd_SL);
        scrnMsg.addCheckItem(scrnMsg.prchReqCratDt_RF);
        scrnMsg.addCheckItem(scrnMsg.prchReqCratDt_RT);
        scrnMsg.addCheckItem(scrnMsg.rqstRcvDt_NF);
        scrnMsg.addCheckItem(scrnMsg.rqstRcvDt_NT);
        scrnMsg.addCheckItem(scrnMsg.prchReqSrcTpCd_SL);
        scrnMsg.addCheckItem(scrnMsg.trxRefNum);
        //08/10/2017 CITS H.Naoi Add Sol#097(QC#18398) START
        scrnMsg.addCheckItem(scrnMsg.mrpPlnNm);
        //08/10/2017 CITS H.Naoi Add Sol#097(QC#18398) END
        scrnMsg.addCheckItem(scrnMsg.fullPsnNm);
        scrnMsg.addCheckItem(scrnMsg.shpgSvcLvlCd_SL);
        scrnMsg.addCheckItem(scrnMsg.carrNm);
        // Add Start 2020/03/23 QC#55940
        scrnMsg.addCheckItem(scrnMsg.mdseCd);
        // Add End 2020/03/23 QC#55940
        scrnMsg.addCheckItem(scrnMsg.xxRadioBtn);
        scrnMsg.addCheckItem(scrnMsg.srcRtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.rtlWhNm_SW);
        scrnMsg.addCheckItem(scrnMsg.srcRtlSwhCd);
        scrnMsg.addCheckItem(scrnMsg.rtlSwhNm_SS);
        scrnMsg.addCheckItem(scrnMsg.destRtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.rtlWhNm_DW);
        scrnMsg.addCheckItem(scrnMsg.destRtlSwhCd);
        scrnMsg.addCheckItem(scrnMsg.rtlSwhNm_DS);
        scrnMsg.addCheckItem(scrnMsg.vndCd);
        scrnMsg.addCheckItem(scrnMsg.dplyVndNm);
        scrnMsg.addCheckItem(scrnMsg.shipToCustCd_EO);
        scrnMsg.addCheckItem(scrnMsg.shipToLocNm_EO);

        // Date Check
        if (ZYPCommonFunc.hasValue(scrnMsg.prchReqCratDt_RF) && ZYPCommonFunc.hasValue(scrnMsg.prchReqCratDt_RT)) {
            String poSubmtTsFr = scrnMsg.prchReqCratDt_RF.getValue();
            String poSubmtTsTo = scrnMsg.prchReqCratDt_RT.getValue();
            if (EZDCommonFunc.cmpDate(poSubmtTsFr, poSubmtTsTo) == 1) {
                scrnMsg.prchReqCratDt_RF.setErrorInfo(1, NPAM0225E, new String[] {DISPLAY_NM_PRCH_REQ_CRAT_DT_RF });
                scrnMsg.addCheckItem(scrnMsg.prchReqCratDt_RF);
                scrnMsg.prchReqCratDt_RT.setErrorInfo(1, NPAM0225E, new String[] {DISPLAY_NM_PRCH_REQ_CRAT_DT_RT });
                scrnMsg.addCheckItem(scrnMsg.prchReqCratDt_RT);
            }
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.rqstRcvDt_NF) && ZYPCommonFunc.hasValue(scrnMsg.rqstRcvDt_NT)) {
            String poSubmtTsFr = scrnMsg.rqstRcvDt_NF.getValue();
            String poSubmtTsTo = scrnMsg.rqstRcvDt_NT.getValue();
            if (EZDCommonFunc.cmpDate(poSubmtTsFr, poSubmtTsTo) == 1) {
                scrnMsg.rqstRcvDt_NF.setErrorInfo(1, NPAM0225E, new String[] {DISPLAY_NM_RQST_RCV_DT_NF });
                scrnMsg.addCheckItem(scrnMsg.rqstRcvDt_NF);
                scrnMsg.rqstRcvDt_NT.setErrorInfo(1, NPAM0225E, new String[] {DISPLAY_NM_RQST_RCV_DT_NT });
                scrnMsg.addCheckItem(scrnMsg.rqstRcvDt_NT);
            }
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPBL0010BMsg scrnMsg = (NPBL0010BMsg) bMsg;

        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        // set values to items of pagenation for next page pagenation
        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

        NPBL0010CMsg bizMsg = new NPBL0010CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPBL0010BMsg scrnMsg = (NPBL0010BMsg) bMsg;
        NPBL0010CMsg bizMsg = (NPBL0010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NPBL0010CommonLogic.setTableScreen(scrnMsg);

        // set focus
        scrnMsg.setFocusItem(scrnMsg.prchReqNum);

    }
}
