/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0010;

import static business.servlet.NPBL0010.constant.NPBL0010Constant.BIZ_APP_ID;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.NMAM0288E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPBL0010.NPBL0010CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPBL0010 Inventory Request List
 * Function Name : Download
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/19/2016   CITS            Makoto Okigami  Create          N/A
 * 04/05/2016   CITS            K.Ogino         Update          N/A
 * 08/10/2017   CITS            H.Naoi          Update          Sol#097(QC#18398)
 *</pre>
 */
public class NPBL0010Scrn00_CMN_Download extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPBL0010BMsg scrnMsg = (NPBL0010BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.prchReqNum) && !ZYPCommonFunc.hasValue(scrnMsg.prchReqTpCd_SL) && !ZYPCommonFunc.hasValue(scrnMsg.prchReqStsCd_SL) && !ZYPCommonFunc.hasValue(scrnMsg.prchReqApvlStsCd_SL)
                && !ZYPCommonFunc.hasValue(scrnMsg.prchReqCratDt_RF) && !ZYPCommonFunc.hasValue(scrnMsg.prchReqCratDt_RT) && !ZYPCommonFunc.hasValue(scrnMsg.rqstRcvDt_NF) && !ZYPCommonFunc.hasValue(scrnMsg.rqstRcvDt_NT)
                && !ZYPCommonFunc.hasValue(scrnMsg.prchReqSrcTpCd_SL) && !ZYPCommonFunc.hasValue(scrnMsg.trxRefNum) && !ZYPCommonFunc.hasValue(scrnMsg.mrpPlnNm) && !ZYPCommonFunc.hasValue(scrnMsg.fullPsnNm) && !ZYPCommonFunc.hasValue(scrnMsg.shpgSvcLvlCd_SL)
                && !ZYPCommonFunc.hasValue(scrnMsg.carrNm) && !ZYPCommonFunc.hasValue(scrnMsg.srcRtlWhCd) && !ZYPCommonFunc.hasValue(scrnMsg.rtlWhNm_SW) && !ZYPCommonFunc.hasValue(scrnMsg.srcRtlSwhCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.rtlSwhNm_SS) && !ZYPCommonFunc.hasValue(scrnMsg.destRtlWhCd) && !ZYPCommonFunc.hasValue(scrnMsg.rtlWhNm_DW) && !ZYPCommonFunc.hasValue(scrnMsg.destRtlSwhCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.rtlSwhNm_DS) && !ZYPCommonFunc.hasValue(scrnMsg.vndCd) && !ZYPCommonFunc.hasValue(scrnMsg.dplyVndNm) && !ZYPCommonFunc.hasValue(scrnMsg.shipToCustCd_EO)
                && !ZYPCommonFunc.hasValue(scrnMsg.shipToLocNm_EO)) {
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
        }

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
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPBL0010BMsg scrnMsg = (NPBL0010BMsg) bMsg;

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

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }

        // execute file down load
        String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
        executeDownload(tempFilePath, true, ZYPCSVOutFile.getDialogFileName(tempFilePath));
    }
}
