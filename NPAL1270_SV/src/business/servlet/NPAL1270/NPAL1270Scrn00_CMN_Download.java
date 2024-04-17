/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1270;

import static business.servlet.NPAL1270.constant.NPAL1270Constant.BIZ_APP_ID;
import static business.servlet.NPAL1270.constant.NPAL1270Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1270.constant.NPAL1270Constant.NMAM0288E;
import static business.servlet.NPAL1270.constant.NPAL1270Constant.SCRN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1270.NPAL1270CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * PO Requisition List
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/12/2016   CITS            R Shimamoto     Create          N/A
 * 08/10/2017   CITS            H.Naoi          Update          Sol#097(QC#18398)
 * 01/29/2018   CITS            T.Gotoda        Update          QC#22521
 * 02/01/2023   Hitachi         S.Dong          Update          QC#60966
 *</pre>
 */
public class NPAL1270Scrn00_CMN_Download extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1270BMsg scrnMsg = (NPAL1270BMsg) bMsg;

        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        scrnMsg.addCheckItem(scrnMsg.prchReqNum);
        scrnMsg.addCheckItem(scrnMsg.prchReqTpCd_SL);
        scrnMsg.addCheckItem(scrnMsg.prchReqStsCd_SL);
        scrnMsg.addCheckItem(scrnMsg.prchReqCratDt_FR);
        scrnMsg.addCheckItem(scrnMsg.prchReqCratDt_TO);
        scrnMsg.addCheckItem(scrnMsg.prchReqSrcTpCd_SL);
        scrnMsg.addCheckItem(scrnMsg.trxRefNum);
        //08/10/2017 CITS H.Naoi Add Sol#097(QC#18398) START
        scrnMsg.addCheckItem(scrnMsg.mrpPlnNm);
        //08/10/2017 CITS H.Naoi Add Sol#097(QC#18398) END
        scrnMsg.addCheckItem(scrnMsg.prchReqCratByPsnCd);
        scrnMsg.addCheckItem(scrnMsg.prchGrpCd_SL);
        scrnMsg.addCheckItem(scrnMsg.shipToCustCd);
        scrnMsg.addCheckItem(scrnMsg.rqstRcvDt_FR);
        scrnMsg.addCheckItem(scrnMsg.rqstRcvDt_TO);
        // START 2023/02/01 S.Dong [QC#60966, ADD]
        scrnMsg.addCheckItem(scrnMsg.rqstShipDt_FR);
        scrnMsg.addCheckItem(scrnMsg.rqstShipDt_TO);
        // END 2023/02/01 S.Dong [QC#60966, ADD]
        scrnMsg.addCheckItem(scrnMsg.prchReqLineStsCd_SL);
        scrnMsg.addCheckItem(scrnMsg.shpgSvcLvlCd_SL);
        scrnMsg.addCheckItem(scrnMsg.carrCd);
        scrnMsg.addCheckItem(scrnMsg.prntVndCd);
        scrnMsg.addCheckItem(scrnMsg.vndCd);
        scrnMsg.addCheckItem(scrnMsg.destRtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.destRtlSwhCd);
        scrnMsg.addCheckItem(scrnMsg.poOrdNum);
        scrnMsg.addCheckItem(scrnMsg.prchReqApvlStsCd_SL);
        scrnMsg.addCheckItem(scrnMsg.fullPsnNm);
        scrnMsg.addCheckItem(scrnMsg.carrNm);
        scrnMsg.addCheckItem(scrnMsg.mdseCd);
        scrnMsg.addCheckItem(scrnMsg.prntVndNm);
        scrnMsg.addCheckItem(scrnMsg.vndNm);
        scrnMsg.addCheckItem(scrnMsg.rtlWhNm);
        scrnMsg.addCheckItem(scrnMsg.rtlSwhNm);
        scrnMsg.addCheckItem(scrnMsg.shipToLocNm);
        scrnMsg.addCheckItem(scrnMsg.coaMdseTpCd);
        scrnMsg.addCheckItem(scrnMsg.coaProdCd);

        if (!ZYPCommonFunc.hasValue(scrnMsg.prchReqNum)
                && !ZYPCommonFunc.hasValue(scrnMsg.prchReqTpCd_SL)
                && !ZYPCommonFunc.hasValue(scrnMsg.prchReqStsCd_SL)
                && !ZYPCommonFunc.hasValue(scrnMsg.prchReqApvlStsCd_SL)
                && !ZYPCommonFunc.hasValue(scrnMsg.prchReqCratDt_FR)
                && !ZYPCommonFunc.hasValue(scrnMsg.prchReqCratDt_TO)
                && !ZYPCommonFunc.hasValue(scrnMsg.prchReqSrcTpCd_SL)
                && !ZYPCommonFunc.hasValue(scrnMsg.trxRefNum)
                //08/22/2017 CITS H.Naoi Add Sol#097(QC#18398) START
                && !ZYPCommonFunc.hasValue(scrnMsg.mrpPlnNm)
                //08/22/2017 CITS H.Naoi Add Sol#097(QC#18398) END
                && !ZYPCommonFunc.hasValue(scrnMsg.prchReqCratByPsnCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.fullPsnNm)
                && !ZYPCommonFunc.hasValue(scrnMsg.carrNm)
                && !ZYPCommonFunc.hasValue(scrnMsg.mdseCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.prntVndNm)
                && !ZYPCommonFunc.hasValue(scrnMsg.vndNm)
                && !ZYPCommonFunc.hasValue(scrnMsg.rtlWhNm)
                && !ZYPCommonFunc.hasValue(scrnMsg.rtlSwhNm)
                && !ZYPCommonFunc.hasValue(scrnMsg.shipToLocNm)
                && !ZYPCommonFunc.hasValue(scrnMsg.prchGrpCd_SL)
                && !ZYPCommonFunc.hasValue(scrnMsg.shipToCustCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.rqstRcvDt_FR)
                && !ZYPCommonFunc.hasValue(scrnMsg.rqstRcvDt_TO)
                // START 2023/02/01 S.Dong [QC#60966, ADD]
                && !ZYPCommonFunc.hasValue(scrnMsg.rqstShipDt_FR)
                && !ZYPCommonFunc.hasValue(scrnMsg.rqstShipDt_TO)
                // END 2023/02/01 S.Dong [QC#60966, ADD]
                && !ZYPCommonFunc.hasValue(scrnMsg.shpgSvcLvlCd_SL)
                && !ZYPCommonFunc.hasValue(scrnMsg.prchReqLineStsCd_SL)
                && !ZYPCommonFunc.hasValue(scrnMsg.carrCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.prntVndCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.vndCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.destRtlWhCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.destRtlSwhCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.poOrdNum)
                && !ZYPCommonFunc.hasValue(scrnMsg.coaMdseTpCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.coaProdCd)) {

            scrnMsg.prchReqNum.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.prchReqTpCd_SL.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.prchReqStsCd_SL.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.prchReqApvlStsCd_SL.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.prchReqCratDt_FR.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.prchReqCratDt_TO.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.prchReqLineStsCd_SL.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.prchReqSrcTpCd_SL.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.trxRefNum.setErrorInfo(1, NMAM0288E, new String[] {});
            //08/22/2017 CITS H.Naoi Add Sol#097(QC#18398) START
            scrnMsg.mrpPlnNm.setErrorInfo(1, NMAM0288E, new String[] {});
            //08/22/2017 CITS H.Naoi Add Sol#097(QC#18398) START
            scrnMsg.prchReqCratByPsnCd.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.prchGrpCd_SL.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.shipToCustCd.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.rqstRcvDt_FR.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.rqstRcvDt_TO.setErrorInfo(1, NMAM0288E, new String[] {});
            // START 2023/02/01 S.Dong [QC#60966, ADD]
            scrnMsg.rqstShipDt_FR.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.rqstShipDt_TO.setErrorInfo(1, NMAM0288E, new String[] {});
            // END 2023/02/01 S.Dong [QC#60966, ADD]
            scrnMsg.shpgSvcLvlCd_SL.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.carrCd.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.prntVndCd.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.vndCd.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.destRtlWhCd.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.destRtlSwhCd.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.poOrdNum.setErrorInfo(1, NMAM0288E, new String[] {});

            scrnMsg.fullPsnNm.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.carrNm.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.mdseCd.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.prntVndNm.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.vndNm.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.rtlWhNm.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.rtlSwhNm.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.shipToLocNm.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.coaMdseTpCd.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.coaProdCd.setErrorInfo(1, NMAM0288E, new String[] {});
        }

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1270BMsg scrnMsg = (NPAL1270BMsg) bMsg;
        NPAL1270CMsg bizMsg = new NPAL1270CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1270BMsg scrnMsg = (NPAL1270BMsg) bMsg;
        NPAL1270CMsg bizMsg = (NPAL1270CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (!"E".equals(bizMsg.getMessageKind())) {
            // execute file download
            String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
            executeDownload(tempFilePath, true, ZYPCSVOutFile.getDialogFileName(tempFilePath));
        }

        // Set focus.
        scrnMsg.setFocusItem(scrnMsg.prchReqNum);
    }
}
