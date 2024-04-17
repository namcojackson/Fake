/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */

package business.blap.NPAL1070;

import static business.blap.NPAL1070.constant.NPAL1070Constant.EVENT_NM_NPAL1070_CMN_COL_CLEAR;
import static business.blap.NPAL1070.constant.NPAL1070Constant.EVENT_NM_NPAL1070_CMN_COL_SAVE;
import static business.blap.NPAL1070.constant.NPAL1070Constant.EVENT_NM_NPAL1070_CMN_SUBMIT;
import static business.blap.NPAL1070.constant.NPAL1070Constant.EVENT_NM_NPAL1070_DELETEROW;
import static business.blap.NPAL1070.constant.NPAL1070Constant.EVENT_NM_NPAL1070_DISABLE;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDMsgCommons;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSMsg;
import parts.common.EZDSStringItem;
import parts.common.EZDTBigDecimalItem;
import parts.common.EZDTStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NPAL1070.common.NPAL1070CommonLogic;
import business.db.MRP_INFOTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MRP_INFO_RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Business ID : NPAL1070 Min-Max Planning Entry
 * Function Name : Update
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/24/2016   CITS            Keiichi Masaki  Create          N/A
 * 02/17/2017   CITS     Takeshi Tokutomi       Update          QC#17573
 * 11/07/2017   CITS            S.Katsuma       Update          Sol#014(QC#18401)
 * 01/09/2018   CITS            T.Tokutomi      Update          QC#17571
 * 2018/04/14   CITS            K.Ogino         Update          QC#24796
 * 2019/07/08   CITS            T.Wada          Update          QC#50988
 * 2019/08/31   CITS            M.Naito         Update          QC#53094
 * 03/02/2020   Fujits          R.Nakamura      Update          QC#56100
 * 2022/10/05   Hitachi         M.Kikushima     Update          QC#60560
 *</pre>
 */

public class NPAL1070BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NPAL1070_DELETEROW.equals(screenAplID)) {
                doProcess_NPAL1070_DELETEROW((NPAL1070CMsg) cMsg, (NPAL1070SMsg) sMsg);
            } else if (EVENT_NM_NPAL1070_DISABLE.equals(screenAplID)) {
                doProcess_NPAL1070_DISABLE((NPAL1070CMsg) cMsg, (NPAL1070SMsg) sMsg);
            } else if (EVENT_NM_NPAL1070_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_NPAL1070_SUBMIT((NPAL1070CMsg) cMsg, (NPAL1070SMsg) sMsg);
            } else if (EVENT_NM_NPAL1070_CMN_COL_CLEAR.equals(screenAplID)) {
                ZYPGUITableColumn.clearColData((NPAL1070CMsg) cMsg, (NPAL1070SMsg) sMsg);
            } else if (EVENT_NM_NPAL1070_CMN_COL_SAVE.equals(screenAplID)) {
                ZYPGUITableColumn.setColData((NPAL1070CMsg) cMsg, (NPAL1070SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * DELETEROW
     * @param cMsg NPAL1070CMsg
     * @param sMsg NPAL1070SMsg
     */
    private void doProcess_NPAL1070_DELETEROW(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg) {

        NPAL1070CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

        int i = 0;
        int j = 0;
        int deleteRow = 0;
        cMsg.B.clear();
        sMsg.B.clear();
        for (; i < cMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).xxChkBox_A1)) {
                if (ZYPCommonFunc.hasValue(cMsg.A.no(i).mrpInfoPk_A1)) {
                    deleteRow++;
                }
            } else {
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).xxChkBox_B1, cMsg.A.no(i).xxChkBox_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).glblCmpyCd_B1, cMsg.A.no(i).glblCmpyCd_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).mrpInfoPk_B1, cMsg.A.no(i).mrpInfoPk_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).xxRqstTs_B1, cMsg.A.no(i).xxRqstTs_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).xxRqstTz_B1, cMsg.A.no(i).xxRqstTz_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).mrpPlnNm_B1, cMsg.A.no(i).mrpPlnNm_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).rtlWhCatgCd_B1, cMsg.A.no(i).rtlWhCatgCd_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).rtlWhCatgDescTxt_B1, cMsg.A.no(i).rtlWhCatgDescTxt_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).invtyLocCd_B1, cMsg.A.no(i).invtyLocCd_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).rtlWhCd_B1, cMsg.A.no(i).rtlWhCd_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).rtlWhNm_B1, cMsg.A.no(i).rtlWhNm_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).rtlSwhCd_B1, cMsg.A.no(i).rtlSwhCd_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).rtlSwhNm_B1, cMsg.A.no(i).rtlSwhNm_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).mdseCd_B1, cMsg.A.no(i).mdseCd_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).mdseDescShortTxt_B1, cMsg.A.no(i).mdseDescShortTxt_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).mdseTpCd_B1, cMsg.A.no(i).mdseTpCd_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).coaMdseTpCd_B1, cMsg.A.no(i).coaMdseTpCd_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).coaProdCd_B1, cMsg.A.no(i).coaProdCd_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).ropQty_B1, cMsg.A.no(i).ropQty_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).maxInvtyQty_B1, cMsg.A.no(i).maxInvtyQty_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).ovrdMaxInvtyQty_B1, cMsg.A.no(i).ovrdMaxInvtyQty_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).mrpEnblFlg_B1, cMsg.A.no(i).mrpEnblFlg_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).procrTpCd_BS, cMsg.A.no(i).procrTpCd_AS);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).procrTpDescTxt_B1, cMsg.A.no(i).procrTpDescTxt_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).srcLocCd_B1, cMsg.A.no(i).srcLocCd_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).srcRtlWhCd_B1, cMsg.A.no(i).srcRtlWhCd_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).rtlWhNm_B2, cMsg.A.no(i).rtlWhNm_A2);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).srcRtlSwhCd_B1, cMsg.A.no(i).srcRtlSwhCd_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).rtlSwhNm_B2, cMsg.A.no(i).rtlSwhNm_A2);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).rplshDlyFlg_B1, cMsg.A.no(i).rplshDlyFlg_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).rplshMonFlg_B1, cMsg.A.no(i).rplshMonFlg_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).rplshTueFlg_B1, cMsg.A.no(i).rplshTueFlg_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).rplshWedFlg_B1, cMsg.A.no(i).rplshWedFlg_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).rplshThuFlg_B1, cMsg.A.no(i).rplshThuFlg_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).rplshFriFlg_B1, cMsg.A.no(i).rplshFriFlg_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).supdFlg_B1, cMsg.A.no(i).supdFlg_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).mrpInfoCmntTxt_B1, cMsg.A.no(i).mrpInfoCmntTxt_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).mrpInfoRgtnStsCd_B1, cMsg.A.no(i).mrpInfoRgtnStsCd_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).xxRsltFlg_B1, cMsg.A.no(i).xxRsltFlg_A1);
                // START 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).calcOrdProcCd_B1, cMsg.A.no(i).calcOrdProcCd_A1);
                // END 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]

                j++;
            }
        }
        cMsg.B.setValidCount(j);

        i = 0;
        j = 0;
        for (; i < sMsg.A.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).xxChkBox_A1)) {
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).xxChkBox_B1, sMsg.A.no(i).xxChkBox_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).glblCmpyCd_B1, sMsg.A.no(i).glblCmpyCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).mrpInfoPk_B1, sMsg.A.no(i).mrpInfoPk_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).xxRqstTs_B1, sMsg.A.no(i).xxRqstTs_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).xxRqstTz_B1, sMsg.A.no(i).xxRqstTz_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).mrpPlnNm_B1, sMsg.A.no(i).mrpPlnNm_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).rtlWhCatgCd_B1, sMsg.A.no(i).rtlWhCatgCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).rtlWhCatgDescTxt_B1, sMsg.A.no(i).rtlWhCatgDescTxt_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).invtyLocCd_B1, sMsg.A.no(i).invtyLocCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).rtlWhCd_B1, sMsg.A.no(i).rtlWhCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).rtlWhNm_B1, sMsg.A.no(i).rtlWhNm_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).rtlSwhCd_B1, sMsg.A.no(i).rtlSwhCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).rtlSwhNm_B1, sMsg.A.no(i).rtlSwhNm_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).mdseCd_B1, sMsg.A.no(i).mdseCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).mdseDescShortTxt_B1, sMsg.A.no(i).mdseDescShortTxt_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).mdseTpCd_B1, sMsg.A.no(i).mdseTpCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).coaMdseTpCd_B1, sMsg.A.no(i).coaMdseTpCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).coaProdCd_B1, sMsg.A.no(i).coaProdCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).ropQty_B1, sMsg.A.no(i).ropQty_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).maxInvtyQty_B1, sMsg.A.no(i).maxInvtyQty_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).ovrdMaxInvtyQty_B1, sMsg.A.no(i).ovrdMaxInvtyQty_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).mrpEnblFlg_B1, sMsg.A.no(i).mrpEnblFlg_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).procrTpCd_BS, sMsg.A.no(i).procrTpCd_AS);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).procrTpDescTxt_B1, sMsg.A.no(i).procrTpDescTxt_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).srcLocCd_B1, sMsg.A.no(i).srcLocCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).srcRtlWhCd_B1, sMsg.A.no(i).srcRtlWhCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).rtlWhNm_B2, sMsg.A.no(i).rtlWhNm_A2);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).srcRtlSwhCd_B1, sMsg.A.no(i).srcRtlSwhCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).rtlSwhNm_B2, sMsg.A.no(i).rtlSwhNm_A2);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).rplshDlyFlg_B1, sMsg.A.no(i).rplshDlyFlg_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).rplshMonFlg_B1, sMsg.A.no(i).rplshMonFlg_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).rplshTueFlg_B1, sMsg.A.no(i).rplshTueFlg_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).rplshWedFlg_B1, sMsg.A.no(i).rplshWedFlg_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).rplshThuFlg_B1, sMsg.A.no(i).rplshThuFlg_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).rplshFriFlg_B1, sMsg.A.no(i).rplshFriFlg_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).supdFlg_B1, sMsg.A.no(i).supdFlg_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).mrpInfoCmntTxt_B1, sMsg.A.no(i).mrpInfoCmntTxt_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).mrpInfoRgtnStsCd_B1, sMsg.A.no(i).mrpInfoRgtnStsCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).xxRsltFlg_B1, sMsg.A.no(i).xxRsltFlg_A1);
                // START 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).calcOrdProcCd_B1, sMsg.A.no(i).calcOrdProcCd_A1);
                // END 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]

                j++;
            }
        }
        sMsg.B.setValidCount(j);

        if (deleteRow > 0) {
            // Mod Start 2020/03/02 QC#56100
//            insertUpdateLine(cMsg, sMsg, EVENT_NM_NPAL1070_DELETEROW);
            if (!insertUpdateLine(cMsg, sMsg, EVENT_NM_NPAL1070_DELETEROW)) {
                return;
            }
            // Mod End 2020/03/02 QC#56100
        }

        cMsg.A.clear();
        i = 0;
        for (; i < cMsg.B.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxChkBox_A1, cMsg.B.no(i).xxChkBox_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).glblCmpyCd_A1, cMsg.B.no(i).glblCmpyCd_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).mrpInfoPk_A1, cMsg.B.no(i).mrpInfoPk_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxRqstTs_A1, cMsg.B.no(i).xxRqstTs_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxRqstTz_A1, cMsg.B.no(i).xxRqstTz_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).mrpPlnNm_A1, cMsg.B.no(i).mrpPlnNm_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).rtlWhCatgCd_A1, cMsg.B.no(i).rtlWhCatgCd_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).rtlWhCatgDescTxt_A1, cMsg.B.no(i).rtlWhCatgDescTxt_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).invtyLocCd_A1, cMsg.B.no(i).invtyLocCd_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).rtlWhCd_A1, cMsg.B.no(i).rtlWhCd_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).rtlWhNm_A1, cMsg.B.no(i).rtlWhNm_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).rtlSwhCd_A1, cMsg.B.no(i).rtlSwhCd_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).rtlSwhNm_A1, cMsg.B.no(i).rtlSwhNm_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).mdseCd_A1, cMsg.B.no(i).mdseCd_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).mdseDescShortTxt_A1, cMsg.B.no(i).mdseDescShortTxt_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).mdseTpCd_A1, cMsg.B.no(i).mdseTpCd_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).coaMdseTpCd_A1, cMsg.B.no(i).coaMdseTpCd_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).coaProdCd_A1, cMsg.B.no(i).coaProdCd_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).ropQty_A1, cMsg.B.no(i).ropQty_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).maxInvtyQty_A1, cMsg.B.no(i).maxInvtyQty_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).ovrdMaxInvtyQty_A1, cMsg.B.no(i).ovrdMaxInvtyQty_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).mrpEnblFlg_A1, cMsg.B.no(i).mrpEnblFlg_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).procrTpCd_AS, cMsg.B.no(i).procrTpCd_BS);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).procrTpDescTxt_A1, cMsg.B.no(i).procrTpDescTxt_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).srcLocCd_A1, cMsg.B.no(i).srcLocCd_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).srcRtlWhCd_A1, cMsg.B.no(i).srcRtlWhCd_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).rtlWhNm_A2, cMsg.B.no(i).rtlWhNm_B2);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).srcRtlSwhCd_A1, cMsg.B.no(i).srcRtlSwhCd_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).rtlSwhNm_A2, cMsg.B.no(i).rtlSwhNm_B2);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).rplshDlyFlg_A1, cMsg.B.no(i).rplshDlyFlg_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).rplshMonFlg_A1, cMsg.B.no(i).rplshMonFlg_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).rplshTueFlg_A1, cMsg.B.no(i).rplshTueFlg_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).rplshWedFlg_A1, cMsg.B.no(i).rplshWedFlg_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).rplshThuFlg_A1, cMsg.B.no(i).rplshThuFlg_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).rplshFriFlg_A1, cMsg.B.no(i).rplshFriFlg_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).supdFlg_A1, cMsg.B.no(i).supdFlg_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).mrpInfoCmntTxt_A1, cMsg.B.no(i).mrpInfoCmntTxt_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).mrpInfoRgtnStsCd_A1, cMsg.B.no(i).mrpInfoRgtnStsCd_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxRsltFlg_A1, cMsg.B.no(i).xxRsltFlg_B1);
            // START 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).calcOrdProcCd_A1, cMsg.B.no(i).calcOrdProcCd_B1);
            // END 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]
        }
        cMsg.A.setValidCount(i);

        sMsg.A.clear();
        j = 0;
        for (; j < sMsg.B.getValidCount(); j++) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).xxChkBox_A1, sMsg.B.no(j).xxChkBox_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).glblCmpyCd_A1, sMsg.B.no(j).glblCmpyCd_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).mrpInfoPk_A1, sMsg.B.no(j).mrpInfoPk_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).xxRqstTs_A1, sMsg.B.no(j).xxRqstTs_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).xxRqstTz_A1, sMsg.B.no(j).xxRqstTz_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).mrpPlnNm_A1, sMsg.B.no(j).mrpPlnNm_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).rtlWhCatgCd_A1, sMsg.B.no(j).rtlWhCatgCd_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).rtlWhCatgDescTxt_A1, sMsg.B.no(j).rtlWhCatgDescTxt_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).invtyLocCd_A1, sMsg.B.no(j).invtyLocCd_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).rtlWhCd_A1, sMsg.B.no(j).rtlWhCd_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).rtlWhNm_A1, sMsg.B.no(j).rtlWhNm_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).rtlSwhCd_A1, sMsg.B.no(j).rtlSwhCd_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).rtlSwhNm_A1, sMsg.B.no(j).rtlSwhNm_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).mdseCd_A1, sMsg.B.no(j).mdseCd_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).mdseDescShortTxt_A1, sMsg.B.no(j).mdseDescShortTxt_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).mdseTpCd_A1, sMsg.B.no(j).mdseTpCd_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).coaMdseTpCd_A1, sMsg.B.no(j).coaMdseTpCd_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).coaProdCd_A1, sMsg.B.no(j).coaProdCd_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).ropQty_A1, sMsg.B.no(j).ropQty_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).maxInvtyQty_A1, sMsg.B.no(j).maxInvtyQty_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).ovrdMaxInvtyQty_A1, sMsg.B.no(j).ovrdMaxInvtyQty_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).mrpEnblFlg_A1, sMsg.B.no(j).mrpEnblFlg_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).procrTpCd_AS, sMsg.B.no(j).procrTpCd_BS);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).procrTpDescTxt_A1, sMsg.B.no(j).procrTpDescTxt_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).srcLocCd_A1, sMsg.B.no(j).srcLocCd_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).srcRtlWhCd_A1, sMsg.B.no(j).srcRtlWhCd_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).rtlWhNm_A2, sMsg.B.no(j).rtlWhNm_B2);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).srcRtlSwhCd_A1, sMsg.B.no(j).srcRtlSwhCd_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).rtlSwhNm_A2, sMsg.B.no(j).rtlSwhNm_B2);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).rplshDlyFlg_A1, sMsg.B.no(j).rplshDlyFlg_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).rplshMonFlg_A1, sMsg.B.no(j).rplshMonFlg_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).rplshTueFlg_A1, sMsg.B.no(j).rplshTueFlg_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).rplshWedFlg_A1, sMsg.B.no(j).rplshWedFlg_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).rplshThuFlg_A1, sMsg.B.no(j).rplshThuFlg_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).rplshFriFlg_A1, sMsg.B.no(j).rplshFriFlg_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).supdFlg_A1, sMsg.B.no(j).supdFlg_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).mrpInfoCmntTxt_A1, sMsg.B.no(j).mrpInfoCmntTxt_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).mrpInfoRgtnStsCd_A1, sMsg.B.no(j).mrpInfoRgtnStsCd_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).xxRsltFlg_A1, sMsg.B.no(j).xxRsltFlg_B1);
            // START 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).calcOrdProcCd_A1, sMsg.B.no(i).calcOrdProcCd_B1);
            // END 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]
        }
        sMsg.A.setValidCount(j);
    }

    /**
     * DISABLE
     * @param cMsg NPAL1070CMsg
     * @param sMsg NPAL1070SMsg
     */
    private void doProcess_NPAL1070_DISABLE(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg) {

        insertUpdateLine(cMsg, sMsg, EVENT_NM_NPAL1070_DISABLE);

    }
    /**
     * SUBMIT
     * @param cMsg NPAL1070CMsg
     * @param sMsg NPAL1070SMsg
     */
    private void doProcess_NPAL1070_SUBMIT(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg) {

        // copy from cMsg to sMsg
        NPAL1070CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

        if (NPAL1070CommonLogic.submitCheck(cMsg, sMsg)) {
            insertUpdateLine(cMsg, sMsg, EVENT_NM_NPAL1070_CMN_SUBMIT);
        } else {
            int pageShowFromNum = 0;
            pageShowFromNum = NPAL1070CommonLogic.getShowPageFrom(cMsg, sMsg);
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum, BigDecimal.valueOf(pageShowFromNum));
            // copy from sMsg to cMsg
            NPAL1070CommonLogic.copyFromSmsgOntoCmsg(cMsg, sMsg);
        }
    }

    /**
    * <pre>
    * Insert_Update_Line
    * </pre>
    * @param cMsg NPAL1070CMsg
    * @param sMsg NPAL1070SMsg
    * @param eventName String
    */
    // Mod Start 2020/03/02 QC#56100
//    private void insertUpdateLine(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg, String eventName) {
    private boolean insertUpdateLine(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg, String eventName) {
    // Mod End 2020/03/02 QC#56100

        int i = 0;
        int j = 0;
        List<MRP_INFOTMsg> insertMsgs = new ArrayList<MRP_INFOTMsg>();
        List<MRP_INFOTMsg> updateMsgs = new ArrayList<MRP_INFOTMsg>();

        // QC#50988 Add Start
        boolean rplshChgFlg = false;
        boolean alreadyChecked = false;
        String glblCmpyCd4RplshUpd = null;
        String mrpPlnNm4RplshUpd = null;
        String rplshDlyFlg = null;
        String rplshMonFlg = null;
        String rplshTueFlg = null;
        String rplshWedFlg = null;
        String rplshThuFlg = null;
        String rplshFriFlg = null;
        // QC#50988 Add End

        for (; i < sMsg.A.getValidCount(); i++) {
            NPAL1070_ASMsg bizMsg = new NPAL1070_ASMsg();

            if (eventName.equals(EVENT_NM_NPAL1070_CMN_SUBMIT)) {
                EZDMsg.copy(sMsg.A.no(i), null, bizMsg, null);
            } else if (eventName.equals(EVENT_NM_NPAL1070_DELETEROW) || eventName.equals(EVENT_NM_NPAL1070_DISABLE)) {
                if (j == cMsg.A.getValidCount()) {
                    break;
                }
                EZDMsg.copy(cMsg.A.no(j), null, bizMsg, null);
                j++;
            }

            ZYPEZDItemValueSetter.setValue(bizMsg.mrpPlnNm_A1, cMsg.mrpPlnNm);

            if (!ZYPCommonFunc.hasValue(bizMsg.mrpEnblFlg_A1)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.mrpEnblFlg_A1, ZYPConstant.FLG_OFF_N);
            }
            if (!ZYPCommonFunc.hasValue(cMsg.rplshDlyFlg)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.rplshDlyFlg_A1, ZYPConstant.FLG_OFF_N);
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.rplshDlyFlg_A1, ZYPConstant.FLG_ON_Y);
            }
            if (!ZYPCommonFunc.hasValue(cMsg.rplshMonFlg)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.rplshMonFlg_A1, ZYPConstant.FLG_OFF_N);
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.rplshMonFlg_A1, ZYPConstant.FLG_ON_Y);
            }
            if (!ZYPCommonFunc.hasValue(cMsg.rplshTueFlg)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.rplshTueFlg_A1, ZYPConstant.FLG_OFF_N);
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.rplshTueFlg_A1, ZYPConstant.FLG_ON_Y);
            }
            if (!ZYPCommonFunc.hasValue(cMsg.rplshWedFlg)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.rplshWedFlg_A1, ZYPConstant.FLG_OFF_N);
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.rplshWedFlg_A1, ZYPConstant.FLG_ON_Y);
            }
            if (!ZYPCommonFunc.hasValue(cMsg.rplshThuFlg)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.rplshThuFlg_A1, ZYPConstant.FLG_OFF_N);
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.rplshThuFlg_A1, ZYPConstant.FLG_ON_Y);
            }
            if (!ZYPCommonFunc.hasValue(cMsg.rplshFriFlg)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.rplshFriFlg_A1, ZYPConstant.FLG_OFF_N);
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.rplshFriFlg_A1, ZYPConstant.FLG_ON_Y);
            }
            // START 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]
            // START 2022/10/05 M.Kikushima [QC#60560,MOD]
            //if (!ZYPCommonFunc.hasValue(cMsg.calcOrdProcCd)) {
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).calcOrdProcCd_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.calcOrdProcCd_A1, ZYPConstant.FLG_OFF_0);
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.calcOrdProcCd_A1, ZYPConstant.FLG_ON_1);
            }
            // END 2022/10/05 M.Kikushima [QC#60560,MOD]
            // END 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]

            // START 2019/08/31 M.Naito [QC#53094,MOD]
            if (eventName.equals(EVENT_NM_NPAL1070_CMN_SUBMIT)) {
                if (!rplshChgFlg && !alreadyChecked) {
                    S21SsmEZDResult results = NPAL1070CommonLogic.findMrpInfoByPlnName(bizMsg.glblCmpyCd_A1.getValue(), bizMsg.mrpPlnNm_A1.getValue());
                    if (results.isCodeNormal()) {
                        List<Map> resultList = (List<Map>) results.getResultObject();
                        Map<String, Object> resultMap = (Map<String, Object>) resultList.get(0);
                        MRP_INFOTMsg mrpInfoTmsg = new MRP_INFOTMsg();
                        ZYPEZDItemValueSetter.setValue(mrpInfoTmsg.glblCmpyCd, bizMsg.glblCmpyCd_A1.getValue());
                        ZYPEZDItemValueSetter.setValue(mrpInfoTmsg.mrpInfoPk, (BigDecimal) resultMap.get("MRP_INFO_PK"));
                        mrpInfoTmsg = (MRP_INFOTMsg) EZDTBLAccessor.findByKeyForUpdate(mrpInfoTmsg);
                        if (mrpInfoTmsg != null) {
                            if (isChangeRplshFlg(bizMsg, mrpInfoTmsg)) {
                                rplshChgFlg = true;
                                glblCmpyCd4RplshUpd = bizMsg.glblCmpyCd_A1.getValue();
                                mrpPlnNm4RplshUpd = bizMsg.mrpPlnNm_A1.getValue();
                                rplshDlyFlg = bizMsg.rplshDlyFlg_A1.getValue();
                                rplshMonFlg = bizMsg.rplshMonFlg_A1.getValue();
                                rplshTueFlg = bizMsg.rplshTueFlg_A1.getValue();
                                rplshWedFlg = bizMsg.rplshWedFlg_A1.getValue();
                                rplshThuFlg = bizMsg.rplshThuFlg_A1.getValue();
                                rplshFriFlg = bizMsg.rplshFriFlg_A1.getValue();
                            }
                            alreadyChecked = true;
                        }
                    }
                }
            }
            // END 2019/08/31 M.Naito [QC#53094,MOD]
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).mrpInfoPk_A1)) {
                if (eventName.equals(EVENT_NM_NPAL1070_CMN_SUBMIT)
                    || (eventName.equals(EVENT_NM_NPAL1070_DISABLE) && ZYPCommonFunc.hasValue(bizMsg.xxChkBox_A1))) {
                    // register MRP_INFO
                    insertMsgs.add(insertMrpInfo(bizMsg, eventName));
                    // set PK value
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mrpInfoPk_A1, bizMsg.mrpInfoPk_A1);
                }
            } else {
                // update MRP_INFO
                MRP_INFOTMsg tMsg = lockMrpInfo(cMsg, bizMsg);
                if (tMsg == null) {
                    // Mod End 2020/03/02 QC#56100
//                    return;
                    return false;
                    // Mod End 2020/03/02 QC#56100
                }
                if (eventName.equals(EVENT_NM_NPAL1070_CMN_SUBMIT)) {
                    // START 2019/08/31 M.Naito [QC#53094,DEL]
                    // QC#50988 Add Start
//                    if (!rplshChgFlg && !alreadyChecked) {
//                        if (isChangeRplshFlg(bizMsg, tMsg)) {
//                            rplshChgFlg = true;
//                            glblCmpyCd4RplshUpd = bizMsg.glblCmpyCd_A1.getValue();
//                            mrpPlnNm4RplshUpd = bizMsg.mrpPlnNm_A1.getValue();
//                            rplshDlyFlg = bizMsg.rplshDlyFlg_A1.getValue();
//                            rplshMonFlg = bizMsg.rplshMonFlg_A1.getValue();
//                            rplshTueFlg = bizMsg.rplshTueFlg_A1.getValue();
//                            rplshWedFlg = bizMsg.rplshWedFlg_A1.getValue();
//                            rplshThuFlg = bizMsg.rplshThuFlg_A1.getValue();
//                            rplshFriFlg = bizMsg.rplshFriFlg_A1.getValue();
//                        }
//                        alreadyChecked = true;
//                    }
                    // QC#50988 Add End
                    // END 2019/08/31 M.Naito [QC#53094,DEL]
                    if (!compareUpdateData(bizMsg, tMsg)) {
                        updateMsgs.add(updateMrpInfo(bizMsg, eventName));
                        int ret = S21FastTBLAccessor.update(updateMsgs.toArray(new MRP_INFOTMsg[updateMsgs.size()]));
                        if (ret != updateMsgs.size()) {
                            cMsg.setMessageInfo("NLAM0014E");
                            cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                            // Mod Start 2020/03/02 QC#56100
//                            return;
                            return false;
                            // Mod End 2020/03/02 QC#56100
                        }
                        updateMsgs.clear();
                    }
                } else if (eventName.equals(EVENT_NM_NPAL1070_DELETEROW) || eventName.equals(EVENT_NM_NPAL1070_DISABLE)) {
                    if (ZYPCommonFunc.hasValue(bizMsg.xxChkBox_A1)) {
                        updateMsgs.add(updateMrpInfo(bizMsg, eventName));
                        int ret = S21FastTBLAccessor.update(updateMsgs.toArray(new MRP_INFOTMsg[updateMsgs.size()]));
                        if (ret != updateMsgs.size()) {
                            cMsg.setMessageInfo("NLAM0014E");
                            cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                            // Mod Start 2020/03/02 QC#56100
//                            return;
                            return false;
                            // Mod End 2020/03/02 QC#56100
                        }
                        updateMsgs.clear();
                    }
                }
            }
        }
        // QC#50988 Add Start
        if (eventName.equals(EVENT_NM_NPAL1070_CMN_SUBMIT)) {
            if (rplshChgFlg) {
                updateRplshFlg(glblCmpyCd4RplshUpd, mrpPlnNm4RplshUpd, rplshDlyFlg, rplshMonFlg, rplshTueFlg, rplshWedFlg, rplshThuFlg, rplshFriFlg);
            }
        }
        // QC#50988 Add End

        // Insert DB
        if (0 < insertMsgs.size()) {
            int ret = S21FastTBLAccessor.insert(insertMsgs.toArray(new MRP_INFOTMsg[insertMsgs.size()]));
            if (ret != insertMsgs.size()) {
                cMsg.setMessageInfo("NLAM1091E");
                cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                // Mod Start 2020/03/02 QC#56100
//                return;
                return false;
                // Mod End 2020/03/02 QC#56100
            }
        }

        // Normal End
        cMsg.setMessageInfo("ZZZM9003I", new String[] {"Submit" });

        // Add Start 2020/03/02 QC#56100
        return true;
        // Add End 2020/03/02 QC#56100
    }

    /**
    * <pre>
    * Insert MrpInfo
    * </pre>
    * @param bizMsg NPAL1070_ASMsg
    * @param eventName String
    * @return tMsg MRP_INFOTMsg
    */
    private MRP_INFOTMsg insertMrpInfo(NPAL1070_ASMsg bizMsg, String eventName) {
        MRP_INFOTMsg tMsg = new MRP_INFOTMsg();

        tMsg.mrpInfoPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal("MRP_INFO_SQ"));
        ZYPEZDItemValueSetter.setValue(bizMsg.mrpInfoPk_A1, tMsg.mrpInfoPk);

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, bizMsg.mdseCd_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.mrpPlnNm, bizMsg.mrpPlnNm_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCatgCd, bizMsg.rtlWhCatgCd_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.invtyLocCd, bizMsg.invtyLocCd_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, bizMsg.rtlWhCd_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.rtlSwhCd, bizMsg.rtlSwhCd_A1);
        tMsg.locRoleTpCd.clear();
        ZYPEZDItemValueSetter.setValue(tMsg.mrpInfoRgtnStsCd, bizMsg.mrpInfoRgtnStsCd_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.minOrdQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.incrOrdQty, BigDecimal.ZERO);
        // QC#17571 Update ovrdMaxInvtyQty.
        ZYPEZDItemValueSetter.setValue(tMsg.ovrdMaxInvtyQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.procrTpCd, bizMsg.procrTpCd_AS);
        ZYPEZDItemValueSetter.setValue(tMsg.srcLocCd, bizMsg.srcLocCd_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.srcRtlWhCd, bizMsg.srcRtlWhCd_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.srcRtlSwhCd, bizMsg.srcRtlSwhCd_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.rplshDlyFlg, bizMsg.rplshDlyFlg_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.rplshMonFlg, bizMsg.rplshMonFlg_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.rplshTueFlg, bizMsg.rplshTueFlg_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.rplshWedFlg, bizMsg.rplshWedFlg_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.rplshThuFlg, bizMsg.rplshThuFlg_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.rplshFriFlg, bizMsg.rplshFriFlg_A1);
        tMsg.origMrpInfoPk.clear();
        tMsg.mrpCtrlCd.clear();
        tMsg.plnDelyDaysAot.clear();
        tMsg.mrpPlnCd.clear();
        tMsg.mrpRunPrmPk.clear();
        ZYPEZDItemValueSetter.setValue(tMsg.supdFlg, bizMsg.supdFlg_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.mrpInfoCmntTxt, bizMsg.mrpInfoCmntTxt_A1);
        // START 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]
        ZYPEZDItemValueSetter.setValue(tMsg.calcOrdProcCd, bizMsg.calcOrdProcCd_A1);
        // END 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]

        if (eventName.equals(EVENT_NM_NPAL1070_CMN_SUBMIT)) {
            ZYPEZDItemValueSetter.setValue(tMsg.mrpEnblFlg, bizMsg.mrpEnblFlg_A1);
            ZYPEZDItemValueSetter.setValue(tMsg.ropQty, bizMsg.ropQty_A1);
            ZYPEZDItemValueSetter.setValue(tMsg.maxInvtyQty, bizMsg.maxInvtyQty_A1);
        } else if (eventName.equals(EVENT_NM_NPAL1070_DISABLE)) {
            ZYPEZDItemValueSetter.setValue(tMsg.mrpEnblFlg, ZYPConstant.FLG_OFF_N);
            // QC#24796
            if (ZYPCommonFunc.hasValue(bizMsg.ropQty_A1)) {
                ZYPEZDItemValueSetter.setValue(tMsg.ropQty, bizMsg.ropQty_A1);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.ropQty, BigDecimal.ZERO);
            }
            if (ZYPCommonFunc.hasValue(bizMsg.maxInvtyQty_A1)) {
                ZYPEZDItemValueSetter.setValue(tMsg.maxInvtyQty, bizMsg.maxInvtyQty_A1);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.maxInvtyQty, BigDecimal.ZERO);
            }
        }

        return tMsg;
    }

    /**
    * Note: record is supposed to be present. Even when record is not
    * found, it sets lock error message.
    * @param bizMsg NPAL1070CMsg
    * @param sMsg NPAL1070_ASMsg
    * @return tMsg if lock is successfully acquired.
    */
    private MRP_INFOTMsg lockMrpInfo(NPAL1070CMsg bizMsg, NPAL1070_ASMsg sMsg) {
        MRP_INFOTMsg tMsg = findMrpInfo(sMsg);
        if (tMsg == null) {
            bizMsg.setMessageInfo("NPAM0006E");
            return null;
        }

        String preUpTime = sMsg.xxRqstTs_A1.getValue();
        String preTimeZone = sMsg.xxRqstTz_A1.getValue();
        String currUpTime = tMsg.ezUpTime.getValue();
        String currTimeZone = tMsg.ezUpTimeZone.getValue();
        if (ZYPDateUtil.isSameTimeStamp(preUpTime, preTimeZone, currUpTime, currTimeZone)) {
            return tMsg;
        } else {
            bizMsg.setMessageInfo("NPAM0006E");
            return null;
        }
    }

    /**
     * Find MRP_INFO by primary key, for update.
     * @param bizMsg NPAL1070_ASMsg
     * @return outMsg
     */
    private MRP_INFOTMsg findMrpInfo(NPAL1070_ASMsg bizMsg) {

        MRP_INFOTMsg inMsg = new MRP_INFOTMsg();
        setValue(inMsg.mrpInfoPk, bizMsg.mrpInfoPk_A1);
        setValue(inMsg.glblCmpyCd, bizMsg.glblCmpyCd_A1);
        MRP_INFOTMsg outMsg = (MRP_INFOTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inMsg);
        return outMsg;
    }
    // QC#50988 Add Start
    /**
     * updateRplshFlg
     * @param bizMsg
     * @return
     */
    private void updateRplshFlg(String glblCmpyCd, String mrpPlnNm, String rplshDlyFlg, 
            String rplshMonFlg, String rplshTueFlg, String rplshWedFlg, String rplshThuFlg, String rplshFriFlg) {

        S21SsmEZDResult results = NPAL1070CommonLogic.findMrpInfoByPlnName(glblCmpyCd, mrpPlnNm);

        if (results.isCodeNormal()) {
            List<Map> resultList = (List<Map>) results.getResultObject();
            if (resultList != null) {
                for (int j = 0; j < resultList.size(); j++) {

                    Map<String, Object> resultMap = (Map<String, Object>) resultList.get(j);
                    MRP_INFOTMsg mrpInfoTmsg = new MRP_INFOTMsg();
                    ZYPEZDItemValueSetter.setValue(mrpInfoTmsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(mrpInfoTmsg.mrpInfoPk, (BigDecimal)resultMap.get("MRP_INFO_PK"));
                    mrpInfoTmsg = (MRP_INFOTMsg) EZDTBLAccessor.findByKeyForUpdate(mrpInfoTmsg);

                    if (mrpInfoTmsg != null) {
                        ZYPEZDItemValueSetter.setValue(mrpInfoTmsg.rplshDlyFlg, rplshDlyFlg);
                        ZYPEZDItemValueSetter.setValue(mrpInfoTmsg.rplshMonFlg, rplshMonFlg);
                        ZYPEZDItemValueSetter.setValue(mrpInfoTmsg.rplshTueFlg, rplshTueFlg);
                        ZYPEZDItemValueSetter.setValue(mrpInfoTmsg.rplshWedFlg, rplshWedFlg);
                        ZYPEZDItemValueSetter.setValue(mrpInfoTmsg.rplshThuFlg, rplshThuFlg);
                        ZYPEZDItemValueSetter.setValue(mrpInfoTmsg.rplshFriFlg, rplshFriFlg);
                        S21FastTBLAccessor.update(mrpInfoTmsg);
                    }
                }
            }
        }
        
    }
    /**
     * isChangeRplshFlg
     * @param bizMsg
     * @param tMsg
     * @return
     */
    private boolean isChangeRplshFlg(NPAL1070_ASMsg bizMsg, MRP_INFOTMsg tMsg) {
        boolean result = false;
        if (!compare(bizMsg.rplshDlyFlg_A1, tMsg.rplshDlyFlg)) {
            result = true;
        }
        if (!compare(bizMsg.rplshMonFlg_A1, tMsg.rplshMonFlg)) {
            result = true;
        }
        if (!compare(bizMsg.rplshTueFlg_A1, tMsg.rplshTueFlg)) {
            result = true;
        }
        if (!compare(bizMsg.rplshWedFlg_A1, tMsg.rplshWedFlg)) {
            result = true;
        }
        if (!compare(bizMsg.rplshThuFlg_A1, tMsg.rplshThuFlg)) {
            result = true;
        }
        if (!compare(bizMsg.rplshFriFlg_A1, tMsg.rplshFriFlg)) {
            result = true;
        }
        return result;
    }
    // QC#50988 Add End
    /**
     * compare Update Data
     * @param bizMsg NPAL1070_ASMsg
     * @param tMsg MRP_INFOTMsg
     * @return boolean
     */
    private boolean compareUpdateData(NPAL1070_ASMsg bizMsg, MRP_INFOTMsg tMsg) {
        if (!compare(bizMsg.mrpPlnNm_A1, tMsg.mrpPlnNm)) {
            return false;
        }
        if (!compare(bizMsg.ropQty_A1, tMsg.ropQty)) {
            return false;
        }
        if (!compare(bizMsg.maxInvtyQty_A1, tMsg.maxInvtyQty)) {
            return false;
        }
        if (!compare(bizMsg.ovrdMaxInvtyQty_A1, tMsg.ovrdMaxInvtyQty)) {
            return false;
        }
        if (!compare(bizMsg.mrpEnblFlg_A1, tMsg.mrpEnblFlg)) {
            return false;
        }
        if (!compare(bizMsg.procrTpCd_AS, tMsg.procrTpCd)) {
            return false;
        }
        if (!compare(bizMsg.srcRtlWhCd_A1, tMsg.srcRtlWhCd)) {
            return false;
        }
        if (!compare(bizMsg.srcRtlSwhCd_A1, tMsg.srcRtlSwhCd)) {
            return false;
        }
        if (!compare(bizMsg.rplshDlyFlg_A1, tMsg.rplshDlyFlg)) {
            return false;
        }
        if (!compare(bizMsg.rplshMonFlg_A1, tMsg.rplshMonFlg)) {
            return false;
        }
        if (!compare(bizMsg.rplshTueFlg_A1, tMsg.rplshTueFlg)) {
            return false;
        }
        if (!compare(bizMsg.rplshWedFlg_A1, tMsg.rplshWedFlg)) {
            return false;
        }
        if (!compare(bizMsg.rplshThuFlg_A1, tMsg.rplshThuFlg)) {
            return false;
        }
        if (!compare(bizMsg.rplshFriFlg_A1, tMsg.rplshFriFlg)) {
            return false;
        }
        if (!compare(bizMsg.mrpInfoCmntTxt_A1, tMsg.mrpInfoCmntTxt)) {
            return false;
        }
        // START 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]
        if (!compare(bizMsg.calcOrdProcCd_A1, tMsg.calcOrdProcCd)) {
            return false;
        }
        // END 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]
        return true;
    }

    /**
     * compare StringItem
     * @param item1 EZDSStringItem
     * @param item2 EZDTStringItem
     * @return boolean
     */
    private boolean compare(EZDSStringItem item1, EZDTStringItem item2) {
        if (ZYPCommonFunc.hasValue(item1) && ZYPCommonFunc.hasValue(item2)) {
            return item1.getValue().equals(item2.getValue());
        }
        if (!ZYPCommonFunc.hasValue(item1) && !ZYPCommonFunc.hasValue(item2)) {
            return true;
        }
        return false;
    }

    /**
     * compare BigDecimalItem
     * @param item1 EZDSBigDecimalItem
     * @param item2 EZDTBigDecimalItem
     * @return boolean
     */
    private boolean compare(EZDSBigDecimalItem item1, EZDTBigDecimalItem item2) {
        if (ZYPCommonFunc.hasValue(item1) && ZYPCommonFunc.hasValue(item2)) {
            return item1.getValue().equals(item2.getValue());
        }
        if (!ZYPCommonFunc.hasValue(item1) && !ZYPCommonFunc.hasValue(item2)) {
            return true;
        }
        return false;
    }

    /**
     * <pre>
     * Update MrpInfo
     * @param bizMsg NPAL1070_ASMsg
     * @param eventName String
     * @return tMsg MRP_INFOTMsg
     */
    private MRP_INFOTMsg updateMrpInfo(NPAL1070_ASMsg bizMsg, String eventName) {
        MRP_INFOTMsg tMsg = new MRP_INFOTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.mrpInfoPk, bizMsg.mrpInfoPk_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, bizMsg.mdseCd_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.mrpPlnNm, bizMsg.mrpPlnNm_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCatgCd, bizMsg.rtlWhCatgCd_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.invtyLocCd, bizMsg.invtyLocCd_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, bizMsg.rtlWhCd_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.rtlSwhCd, bizMsg.rtlSwhCd_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.minOrdQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.incrOrdQty, BigDecimal.ZERO);
        // QC#17571 Update.
        ZYPEZDItemValueSetter.setValue(tMsg.ovrdMaxInvtyQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.procrTpCd, bizMsg.procrTpCd_AS);
        ZYPEZDItemValueSetter.setValue(tMsg.srcLocCd, bizMsg.srcLocCd_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.srcRtlWhCd, bizMsg.srcRtlWhCd_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.srcRtlSwhCd, bizMsg.srcRtlSwhCd_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.rplshDlyFlg, bizMsg.rplshDlyFlg_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.rplshMonFlg, bizMsg.rplshMonFlg_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.rplshTueFlg, bizMsg.rplshTueFlg_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.rplshWedFlg, bizMsg.rplshWedFlg_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.rplshThuFlg, bizMsg.rplshThuFlg_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.rplshFriFlg, bizMsg.rplshFriFlg_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.supdFlg, bizMsg.supdFlg_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.mrpInfoCmntTxt, bizMsg.mrpInfoCmntTxt_A1);
        // START 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]
        ZYPEZDItemValueSetter.setValue(tMsg.calcOrdProcCd, bizMsg.calcOrdProcCd_A1);
        // END 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]
        if (eventName.equals(EVENT_NM_NPAL1070_CMN_SUBMIT)) {
            ZYPEZDItemValueSetter.setValue(tMsg.mrpInfoRgtnStsCd, bizMsg.mrpInfoRgtnStsCd_A1);
            ZYPEZDItemValueSetter.setValue(tMsg.mrpEnblFlg, bizMsg.mrpEnblFlg_A1);
            ZYPEZDItemValueSetter.setValue(tMsg.ropQty, bizMsg.ropQty_A1);
            ZYPEZDItemValueSetter.setValue(tMsg.maxInvtyQty, bizMsg.maxInvtyQty_A1);

        } else if (eventName.equals(EVENT_NM_NPAL1070_DELETEROW)) {
            ZYPEZDItemValueSetter.setValue(tMsg.mrpInfoRgtnStsCd, MRP_INFO_RGTN_STS.TERMINATED);
            ZYPEZDItemValueSetter.setValue(tMsg.mrpEnblFlg, bizMsg.mrpEnblFlg_A1);
            ZYPEZDItemValueSetter.setValue(tMsg.ropQty, bizMsg.ropQty_A1);
            ZYPEZDItemValueSetter.setValue(tMsg.maxInvtyQty, bizMsg.maxInvtyQty_A1);

        } else if (eventName.equals(EVENT_NM_NPAL1070_DISABLE)) {
            ZYPEZDItemValueSetter.setValue(tMsg.mrpInfoRgtnStsCd, bizMsg.mrpInfoRgtnStsCd_A1);
            ZYPEZDItemValueSetter.setValue(tMsg.mrpEnblFlg, ZYPConstant.FLG_OFF_N);
            /** QC#17573 2017/02/16 T.Tokutomi START **/
            ZYPEZDItemValueSetter.setValue(tMsg.ropQty, bizMsg.ropQty_A1);
            ZYPEZDItemValueSetter.setValue(tMsg.maxInvtyQty, bizMsg.maxInvtyQty_A1);
            /** QC#17573 2017/02/16 T.Tokutomi END **/
        }

        return tMsg;
    }
}
