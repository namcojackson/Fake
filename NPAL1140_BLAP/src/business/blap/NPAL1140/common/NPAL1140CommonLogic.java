/**
 * <Pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NPAL1140.common;

import static business.blap.NPAL1140.constant.NPAL1140Constant.DELIMITER_HYPHEN;
import static business.blap.NPAL1140.constant.NPAL1140Constant.DISP_PO_ORD_LINE_NUM_LENGTH;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import business.blap.NPAL1140.NPAL1140CMsg;
import business.blap.NPAL1140.NPAL1140Query;
import business.blap.NPAL1140.NPAL1140SMsg;
import business.blap.NPAL1140.NPAL1140_ACMsg;
import business.blap.NPAL1140.NPAL1140_BSMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * ACK Error Correction
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/30/2013   Hitachi         K.Kishimoto     Create          N/A
 * 06/11/2013   Hitachi         K.Kishimoto     Update          1233
 * 07/25/2013   Hitachi         T.Kawazu        Update          1419
 * 07/26/2013   Hitachi         T.Kawazu        Update          1388
 * 07/30/2013   Hitachi         A.Kohinata      Update          1388
 * 08/14/2013   Hitachi         K.Kishimoto     Update          1233
 * 04/11/2017   CITS            R.Shimamoto     Update          QC#18205
 * 08/24/2017   CITS            R.Shimamoto     Update          QC#18631
 * 10/27/2017   CITS            M.Naito         Update          QC#20380
 * 11/20/2018   CITS            M.Naito         Update          QC#29239
 * 09/09/2019   Fujitsu         T.Ogura         Update          QC#53232
 *</pre>
 */
public class NPAL1140CommonLogic {

    /**
     * <pre>
     * underTabClear
     * Clear data Under tab Item
     * </pre>
     * @param globalMsg NPAL1140SMsg
     */
    public static void underTabClear(NPAL1140SMsg globalMsg) {
        // 2017/10/27 QC20380 M.Naito Mod Start
//        globalMsg.vndNm_HT.clear();
        globalMsg.dplyVndNm_HT.clear();
        // 2017/10/27 QC20380 M.Naito Mod End
        globalMsg.ackEdiProcStsNm.clear();
        globalMsg.ediRcvDateTs.clear();
        globalMsg.ediPoOrdNum_HT.clear();
        globalMsg.poOrdNum_HT.clear();
        globalMsg.poAckNum_HT.clear();
        globalMsg.vndCpoOrdNum.clear();
        globalMsg.batErrMsgTxt_HD.clear();
        globalMsg.batErrMsgTxt_HC.clear();
    }

    /**
     * <pre>
     * underDetailTabset
     * Set Under tab Item
     * </pre>
     * @param globalMsg NPAL1140SMsg
     * @param bizMsg NPAL1140CMsg
     * @param poAckHdrWrkPk BigDecimal
     * @return boolean
     */
    public static boolean underTabset(NPAL1140SMsg globalMsg, NPAL1140CMsg bizMsg, NPAL1140_ACMsg bizAMsg) {

        // Get Header Info
        List<Map<String, Object>> resultHeader = NPAL1140Query.getInstance().getAckHeader(bizAMsg.poAckHdrWrkPk_A0.getValue(), bizAMsg.ediPoOrdNum_A0.getValue());
        if (resultHeader.size() == 0) {
            bizMsg.setMessageInfo("NZZM0000E");
            NPAL1140CommonLogic.claerWarningLine(bizMsg);
            return false;
        }
        ZYPEZDItemValueSetter.setValue(globalMsg.poAckHdrWrkPk_HT, (BigDecimal) resultHeader.get(0).get("PO_ACK_HDR_WRK_PK"));
        ZYPEZDItemValueSetter.setValue(globalMsg.itrlIntfcId, (String) resultHeader.get(0).get("ITRL_INTFC_ID"));
        // 2017/10/27 QC20380 M.Naito Mod Start
//        ZYPEZDItemValueSetter.setValue(globalMsg.vndNm_HT, (String) resultHeader.get(0).get("LOC_NM"));
        ZYPEZDItemValueSetter.setValue(globalMsg.dplyVndNm_HT, (String) resultHeader.get(0).get("LOC_NM"));
        // 2017/10/27 QC20380 M.Naito Mod End
        ZYPEZDItemValueSetter.setValue(globalMsg.ackEdiProcStsNm, (String) resultHeader.get(0).get("ACK_EDI_PROC_STS_NM"));
        ZYPEZDItemValueSetter.setValue(globalMsg.openPoAckWrkFlg, (String) resultHeader.get(0).get("OPEN_PO_ACK_WRK_FLG"));
        ZYPEZDItemValueSetter.setValue(globalMsg.ediRcvDateTs, (String) resultHeader.get(0).get("EDI_RCV_DATE_TS"));
        ZYPEZDItemValueSetter.setValue(globalMsg.ediPoOrdNum_HT, (String) resultHeader.get(0).get("EDI_PO_ORD_NUM"));
        ZYPEZDItemValueSetter.setValue(globalMsg.poOrdNum_HT, (String) resultHeader.get(0).get("PO_ORD_NUM"));
        ZYPEZDItemValueSetter.setValue(globalMsg.poAckNum_HT, (String) resultHeader.get(0).get("PO_ACK_NUM"));
        ZYPEZDItemValueSetter.setValue(globalMsg.vndCpoOrdNum, (String) resultHeader.get(0).get("VND_CPO_ORD_NUM"));

        int errMsgCnt = 0;
        for (int i = 0; i < resultHeader.size(); i++) {
            if (ZYPCommonFunc.hasValue((String) resultHeader.get(i).get("BAT_ERR_MSG_TXT"))) {
                ZYPEZDItemValueSetter.setValue(globalMsg.batErrMsgTxt_HD.no(errMsgCnt), (String) resultHeader.get(i).get("BAT_ERR_MSG_TXT"));
                ZYPEZDItemValueSetter.setValue(globalMsg.batErrMsgTxt_HC.no(errMsgCnt), (String) resultHeader.get(i).get("BAT_ERR_MSG_TXT"));
                errMsgCnt++;
            }
        }

        ZYPEZDItemValueSetter.setValue(globalMsg.vndCd_HT, (String) resultHeader.get(0).get("VND_CD"));
        ZYPEZDItemValueSetter.setValue(globalMsg.ezUpTime_HT, (String) resultHeader.get(0).get("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(globalMsg.ezUpTimeZone_HT, (String) resultHeader.get(0).get("EZUPTIMEZONE"));

        // Get Detail Info
        globalMsg.B.clear();

        List<Map<String, Object>> resultDetail = NPAL1140Query.getInstance().getAckDetial(bizAMsg.poAckHdrWrkPk_A0.getValue(), bizAMsg.ediPoOrdNum_A0.getValue(), bizAMsg.ediPoOrdDtlLineNum_A0.getValue());

        if (resultDetail.size() == 0) {
            bizMsg.setMessageInfo("NZZM0000E");
            NPAL1140CommonLogic.claerWarningLine(bizMsg);
            return false;
        }

        int idx = 0;
        String preEdiLine = "";
        String preShpgPlnNum = "";
        String preShpgPlnDplyLine = "";
        String  wkPoOrdDtlLineNum = "";
        String  wkDispPoOrdDtlLineNum = "";

        List<String> errMsgList = new ArrayList<String>();

        int lineIdx = 0;

        for (Map<String, Object> ackDetail : resultDetail) {
            String curEdiLine = (String) ackDetail.get("HDN_EDI_PO_ORD_DTL_LINE_NUM");
            String curShpgPlnNum = (String) ackDetail.get("SHPG_PLN_NUM");
            String curShpgPlnDplyLine = (String) ackDetail.get("SHPG_PLN_DPLY_LINE_NUM");

            if (isSameLine(preEdiLine, preShpgPlnNum, preShpgPlnDplyLine, curEdiLine, curShpgPlnNum, curShpgPlnDplyLine)) {
                if (ZYPCommonFunc.hasValue((String) ackDetail.get("BAT_ERR_MSG_TXT"))) {
                    // START 2018/11/20 M.Naito [QC#29239,MOD]
                    boolean existErrMsgFlg = false;
                    if (errMsgList != null && errMsgList.size() != 0) {
                        for (String errMsg : errMsgList) {
                            if (errMsg.equals((String) ackDetail.get("BAT_ERR_MSG_TXT"))) {
                                existErrMsgFlg = true;
                                break;
                            }
                        }
                    }
                    if (!existErrMsgFlg) {
                        errMsgList.add((String) ackDetail.get("BAT_ERR_MSG_TXT"));
                    }
                    // END 2018/11/20 M.Naito [QC#29239,MOD]
                }

            } else {
                ZYPEZDItemValueSetter.setValue(globalMsg.B.no(lineIdx).xxNum_B0, BigDecimal.ZERO);
                if (idx != 0 && errMsgList.size() != 0) {
                    int msgIdx = 0;
                    ZYPEZDItemValueSetter.setValue(globalMsg.B.no(lineIdx - 1).batErrMsgTxt_BV, errMsgList.get(0));
                    // START 2018/11/20 M.Naito [QC#29239,MOD]
                    int limitErrMsg = globalMsg.B.no(lineIdx - 1).batErrMsgTxt_BC.length();
                    for (String errMsg : errMsgList) {
                        if (limitErrMsg < msgIdx + 1) {
                            break;
                        }
                        // END 2018/11/20 M.Naito [QC#29239,MOD]
                        ZYPEZDItemValueSetter.setValue(globalMsg.B.no(lineIdx - 1).batErrMsgTxt_BC.no(msgIdx), errMsg);
                        ZYPEZDItemValueSetter.setValue(globalMsg.B.no(lineIdx - 1).batErrMsgTxt_BD.no(msgIdx), errMsg);
                        msgIdx++;
                    }
                    ZYPEZDItemValueSetter.setValue(globalMsg.B.no(lineIdx - 1).xxNum_B0, BigDecimal.valueOf(msgIdx));
                }

                ZYPEZDItemValueSetter.setValue(globalMsg.B.no(lineIdx).poAckDtlWrkPk_B0, (BigDecimal) ackDetail.get("PO_ACK_DTL_WRK_PK"));
                ZYPEZDItemValueSetter.setValue(globalMsg.B.no(lineIdx).ediPoOrdDtlLineNum_B0, (String) ackDetail.get("EDI_PO_ORD_DTL_LINE_NUM"));
                ZYPEZDItemValueSetter.setValue(globalMsg.B.no(lineIdx).ediPoOrdDtlLineNum_HD, (String) ackDetail.get("HDN_EDI_PO_ORD_DTL_LINE_NUM"));

                if (isSameEdiLineNum(preEdiLine, curEdiLine)) {
                    // QC#18205 Mod.
//                    ZYPEZDItemValueSetter.setValue(globalMsg.B.no(lineIdx).poOrdDtlLineNum_B0, "");
                    ZYPEZDItemValueSetter.setValue(globalMsg.B.no(lineIdx).dispPoDtlLineNum_B0, "");

                } else  {
                    if (ZYPCommonFunc.hasValue((String) ackDetail.get("PO_ORD_DTL_LINE_NUM"))) {
                        wkPoOrdDtlLineNum = (String) ackDetail.get("PO_ORD_DTL_LINE_NUM");

                    } else {
                        wkPoOrdDtlLineNum = "";
                    }

                    if (wkPoOrdDtlLineNum.length() > DISP_PO_ORD_LINE_NUM_LENGTH) {
                        wkDispPoOrdDtlLineNum = wkPoOrdDtlLineNum.substring(wkPoOrdDtlLineNum.length() - DISP_PO_ORD_LINE_NUM_LENGTH , wkPoOrdDtlLineNum.length());

                    } else {
                        wkDispPoOrdDtlLineNum = wkPoOrdDtlLineNum;
                    }
                    // QC#18205 Mod.
//                    setValue(globalMsg.B.no(lineIdx).poOrdDtlLineNum_B0, wkDispPoOrdDtlLineNum);
                    setValue(globalMsg.B.no(lineIdx).dispPoDtlLineNum_B0, wkDispPoOrdDtlLineNum);
                }
                ZYPEZDItemValueSetter.setValue(globalMsg.B.no(lineIdx).poOrdDtlLineNum_HD, (String) ackDetail.get("HDN_PO_ORD_DTL_LINE_NUM"));
                ZYPEZDItemValueSetter.setValue(globalMsg.B.no(lineIdx).xxRef14Nm_B0, editShpgPln((String) ackDetail.get("SHPG_PLN_NUM"), (String) ackDetail.get("SHPG_PLN_DPLY_LINE_NUM")));
                ZYPEZDItemValueSetter.setValue(globalMsg.B.no(lineIdx).poAckLineStsCd_B0, (String) ackDetail.get("PO_ACK_LINE_STS_CD"));
                ZYPEZDItemValueSetter.setValue(globalMsg.B.no(lineIdx).mdseCd_B0, (String) ackDetail.get("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(globalMsg.B.no(lineIdx).mdseDescShortTxt_B0, (String) ackDetail.get("MDSE_DESC_SHORT_TXT"));
                ZYPEZDItemValueSetter.setValue(globalMsg.B.no(lineIdx).origCusaMdseCd_B0, (String) ackDetail.get("ORIG_VND_MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(globalMsg.B.no(lineIdx).ordQty_B0, (BigDecimal) ackDetail.get("ORD_QTY"));
                ZYPEZDItemValueSetter.setValue(globalMsg.B.no(lineIdx).uomCd_B0, (String) ackDetail.get("UOM_CD"));
                ZYPEZDItemValueSetter.setValue(globalMsg.B.no(lineIdx).etdDt_B0, (String) ackDetail.get("ETD_DT"));
                ZYPEZDItemValueSetter.setValue(globalMsg.B.no(lineIdx).etaDt_B0, (String) ackDetail.get("ETA_DT"));
                ZYPEZDItemValueSetter.setValue(globalMsg.B.no(lineIdx).ordDtlLastUpdTs_B0, (String) ackDetail.get("ORD_DTL_LAST_UPD_TS"));
                ZYPEZDItemValueSetter.setValue(globalMsg.B.no(lineIdx).ezUpTime_B0, (String) ackDetail.get("EZUPTIME"));
                ZYPEZDItemValueSetter.setValue(globalMsg.B.no(lineIdx).ezUpTimeZone_B0, (String) ackDetail.get("EZUPTIMEZONE"));

                preEdiLine = curEdiLine;
                preShpgPlnNum = curShpgPlnNum;
                preShpgPlnDplyLine = curShpgPlnDplyLine;

                lineIdx++;

                errMsgList = new ArrayList<String>();
                if (ZYPCommonFunc.hasValue((String) ackDetail.get("BAT_ERR_MSG_TXT"))) {
                    errMsgList.add((String) ackDetail.get("BAT_ERR_MSG_TXT"));
                }
            }
            idx++;
        }

        if (lineIdx != 0 && errMsgList.size() != 0) {
            int msgIdx = 0;

            ZYPEZDItemValueSetter.setValue(globalMsg.B.no(lineIdx - 1).batErrMsgTxt_BV, errMsgList.get(0));
         // START 2018/11/20 M.Naito [QC#29239,MOD]
            int limitErrMsg = globalMsg.B.no(lineIdx - 1).batErrMsgTxt_BC.length();
            for (String errMsg : errMsgList) {
                if (limitErrMsg < msgIdx + 1) {
                    break;
                }
                // END 2018/11/20 M.Naito [QC#29239,MOD]
                ZYPEZDItemValueSetter.setValue(globalMsg.B.no(lineIdx - 1).batErrMsgTxt_BC.no(msgIdx), errMsg);
                ZYPEZDItemValueSetter.setValue(globalMsg.B.no(lineIdx - 1).batErrMsgTxt_BD.no(msgIdx), errMsg);
                msgIdx++;
            }
            ZYPEZDItemValueSetter.setValue(globalMsg.B.no(lineIdx - 1).xxNum_B0, BigDecimal.valueOf(msgIdx));
        }

        globalMsg.B.setValidCount(lineIdx);

        return true;
    }

    private static boolean isSameLine(String preEdiLine, String preShpgPlnNum, String preShpgPlnDplyLine, String curEdiLine, String curShpgPlnNum, String curShpgPlnDplyLine) {
        if (!isSameEdiLineNum(preEdiLine, curEdiLine)) {
            return false;
        }
        if (!isSame(preShpgPlnNum, curShpgPlnNum)) {
            return false;
        }
        if (!isSame(preShpgPlnDplyLine, curShpgPlnDplyLine)) {
            return false;
        }
        return true;
    }

    private static boolean isSame(String preStr, String curStr) {
        if (ZYPCommonFunc.hasValue(preStr)) {
            if (!preStr.equals(curStr)) {
                return false;
            }
        } else {
            if (ZYPCommonFunc.hasValue(curStr)) {
                return false;
            }
        }
        return true;
    }

    private static String editShpgPln(String shpgPlnNum, String shpgPlnDplyLine) {
        if (!ZYPCommonFunc.hasValue(shpgPlnNum) || !ZYPCommonFunc.hasValue(shpgPlnDplyLine)) {
            return null;
        }
        StringBuilder shpgPln = new StringBuilder();
        shpgPln.append(shpgPlnNum);
        shpgPln.append(DELIMITER_HYPHEN);
        shpgPln.append(shpgPlnDplyLine);
        return shpgPln.toString();
    }

    /**
     * <pre>
     * save
     * save search condition
     * </pre>
     * @param globalMsg NPAL1140SMsg
     */
    public static void saveSearchCondition(NPAL1140SMsg globalMsg) {
        ZYPEZDItemValueSetter.setValue(globalMsg.intfcId_SV, globalMsg.intfcId_0V);
        ZYPEZDItemValueSetter.setValue(globalMsg.ediProcStsCd_SV, globalMsg.ediProcStsCd_0V);
        ZYPEZDItemValueSetter.setValue(globalMsg.ediPoOrdNum_SV, globalMsg.ediPoOrdNum);
        ZYPEZDItemValueSetter.setValue(globalMsg.batErrMsgTxt_SV, globalMsg.batErrMsgTxt_H1);
        ZYPEZDItemValueSetter.setValue(globalMsg.xxCratDt_S1, globalMsg.xxCratDt_H1);
        ZYPEZDItemValueSetter.setValue(globalMsg.xxCratDt_S2, globalMsg.xxCratDt_H2);
    }

    /**
     * <pre>
     * saveSearchCondition
     * </pre>
     * @param bizMsg NPAL1140CMsg
     * @param globalMsg NPAL1140SMsg
     */
    public static void restorationSearchCondition(NPAL1140CMsg bizMsg, NPAL1140SMsg globalMsg) {
        ZYPEZDItemValueSetter.setValue(bizMsg.intfcId_0V, globalMsg.intfcId_SV);
        ZYPEZDItemValueSetter.setValue(bizMsg.ediProcStsCd_0V, globalMsg.ediProcStsCd_SV);
        ZYPEZDItemValueSetter.setValue(bizMsg.ediPoOrdNum, globalMsg.ediPoOrdNum_SV);
        ZYPEZDItemValueSetter.setValue(globalMsg.batErrMsgTxt_H1, globalMsg.batErrMsgTxt_SV);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxCratDt_H1, globalMsg.xxCratDt_S1);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxCratDt_H2, globalMsg.xxCratDt_S2);
    }

    /**
     * <pre>
     * claerWarningLine
     * </pre>
     * @param bizMsg NPAL1140CMsg
     */
    public static void claerWarningLine(NPAL1140CMsg bizMsg) {
        for (int idx = 0; idx < bizMsg.B.getValidCount(); idx++) {
            bizMsg.B.no(idx).poOrdDtlLineNum_BS.clear();
        }
    }

    /**
     * <pre>
     * searchPoOrdNumAarray
     * search poOrdNum from A Array
     * </pre>
     * @param globalMsg NPAL1140SMsg
     * @param ediPoOrdNum String
     * @return int
     */
    public static int searchPoOrdNumAarray(NPAL1140SMsg globalMsg, String ediPoOrdNum) {
        int selectLine = -1;
        for (int idx = 0; idx < globalMsg.A.getValidCount(); idx++) {
            if (ediPoOrdNum.equals(globalMsg.A.no(idx).ediPoOrdNum_A0.getValue())) {
                selectLine = idx;
                break;
            }
        }
        return selectLine;
    }

    /**
     * <pre>
     * copyFromCmsgOntoSmsg
     * Copy data From CMsg Onto SMsg
     * </pre>
     * @param sMsg NPAL1140SMsg
     * @param cMsg NPAL1140CMsg
     */
    public static void copyFromCmsgOntoSmsg(NPAL1140CMsg cMsg, NPAL1140SMsg sMsg) {
        if (cMsg.A.getValidCount() != 0) {
            int nowIndex = cMsg.xxPageShowFromNum.getValueInt();
            int nowLastIndex = cMsg.xxPageShowToNum.getValueInt();
            int q = 0;

            for (int j = nowIndex - 1; j < nowLastIndex; j++) {
                EZDMsg.copy(cMsg.A.no(q), null, sMsg.A.no(j), null);
                q++;
            }
        }
        ZYPEZDItemValueSetter.setValue(sMsg.poOrdNum_HT, cMsg.poOrdNum_HT);
        if (cMsg.B.getValidCount() != 0) {
            for (int cIdx = 0; cIdx < cMsg.B.getValidCount(); cIdx++) {
                for (int sIdx = 0; sIdx < sMsg.B.getValidCount(); sIdx++) {
                    if (cMsg.B.no(cIdx).poAckDtlWrkPk_B0.getValue().compareTo(sMsg.B.no(sIdx).poAckDtlWrkPk_B0.getValue()) == 0) {
                        // QC#18205 Mod.
//                        Map<String, Object> resultMap = NPAL1140Query.getInstance().getLogicLineNum(cMsg.poOrdNum_HT.getValue(), cMsg.B.no(cIdx).poOrdDtlLineNum_B0.getValue());
                        Map<String, Object> resultMap = NPAL1140Query.getInstance().getLogicLineNum(cMsg.poOrdNum_HT.getValue(), cMsg.B.no(cIdx).dispPoDtlLineNum_B0.getValue());
                        if (resultMap.size() != 0) {
                            ZYPEZDItemValueSetter.setValue(cMsg.B.no(sIdx).poOrdDtlLineNum_HD, (String) resultMap.get("PO_ORD_DTL_LINE_NUM"));
                        }

//                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(sIdx).poOrdDtlLineNum_B0, cMsg.B.no(cIdx).poOrdDtlLineNum_B0);
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(sIdx).dispPoDtlLineNum_B0, cMsg.B.no(cIdx).dispPoDtlLineNum_B0);
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(sIdx).poOrdDtlLineNum_HD, cMsg.B.no(cIdx).poOrdDtlLineNum_HD);
                    }
                }
            }
        }
    }

    /**
     * <pre>
     * copyFromSmsgOntoCmsg
     * Copy data From Smsg Onto Cmsg
     * </pre>
     * @param cMsg NPAL1280CMsg
     * @param sMsg NPAL1280SMsg
     */
    public static void copyFromSMsgOntoCmsg(NPAL1140CMsg cMsg, NPAL1140SMsg sMsg) {
        ZYPTableUtil.clear(cMsg.A);
        setPagePos(cMsg, sMsg);

        if (0 < sMsg.A.getValidCount()) {
            int cMsgCount = 0;
            for (int i = cMsg.xxPageShowFromNum.getValueInt() - 1; i < cMsg.xxPageShowToNum.getValueInt(); i++) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(cMsgCount), null);
                cMsgCount++;
            }
            cMsg.A.setValidCount(cMsgCount);
        }
    }

    /**
     * <pre>
     * Set page pos.
     * </pre>
     * @param cMsg NLBL3090CMsg
     * @param sMsg NLBL3090SMsg
     */
    public static void setPagePos(NPAL1140CMsg cMsg, NPAL1140SMsg sMsg) {
        if ((cMsg.A.length() == 0) || (sMsg.A.length() == 0) || (sMsg.A.getValidCount() <= 0)) {
            cMsg.xxPageShowFromNum.setValue(0);
            cMsg.xxPageShowToNum.setValue(0);
            cMsg.xxPageShowOfNum.setValue(0);
            return;
        }
        int startRowCount = 0;
        if (ZYPCommonFunc.hasValue(cMsg.xxPageShowFromNum)) {
            startRowCount = cMsg.xxPageShowFromNum.getValueInt();
        }
        int allRowCount = sMsg.A.getValidCount();
        if (startRowCount == 0) {
            cMsg.xxPageShowFromNum.setValue(1);
        } else if ((startRowCount < 0) || ((allRowCount <= startRowCount))) {
            // last page
            cMsg.xxPageShowFromNum.setValue(getLastPageStartCount(allRowCount, cMsg.A.length()));
        } else {
            if ((startRowCount % cMsg.A.length()) != 1) {
                startRowCount = startRowCount - (startRowCount % cMsg.A.length()) + 1;
            }
            cMsg.xxPageShowFromNum.setValue(startRowCount);
        }
        if ((cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1) < allRowCount) {
            cMsg.xxPageShowToNum.setValue(cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1);
        } else {
            cMsg.xxPageShowToNum.setValue(allRowCount);
        }
        cMsg.xxPageShowOfNum.setValue(allRowCount);
    }

    /**
     * pagenation method
     * @param allRowCount int
     * @param pageRowCount int
     * @return
     */
    private static int getLastPageStartCount(int allRowCount, int pageRowCount) {
        if ((allRowCount <= 0) || (pageRowCount <= 0)) {
            return 0;
        }
        if (allRowCount <= pageRowCount) {
            return 1;
        }
        if (allRowCount % pageRowCount == 0) {
            return allRowCount - pageRowCount + 1;
        }
        return ((int) (allRowCount / pageRowCount)) * pageRowCount + 1;
    }

    /**
     * @param bizMsg    NPAL1140CMsg
     * @param globalMsg NPAL1140SMsg
     */
    public static void detailListDisp(NPAL1140CMsg bizMsg, NPAL1140SMsg globalMsg) {

        for (int idx = 0; idx < bizMsg.B.getValidCount(); idx++) {
            bizMsg.B.no(idx).poAckDtlWrkPk_B0.clear();
            bizMsg.B.no(idx).ediPoOrdDtlLineNum_B0.clear();
            // QC#18205 Mod.
//            bizMsg.B.no(idx).poOrdDtlLineNum_B0.clear();
            bizMsg.B.no(idx).dispPoDtlLineNum_B0.clear();
            bizMsg.B.no(idx).xxRef14Nm_B0.clear();
            bizMsg.B.no(idx).poAckLineStsCd_B0.clear();
            bizMsg.B.no(idx).mdseCd_B0.clear();
            bizMsg.B.no(idx).mdseDescShortTxt_B0.clear();
            bizMsg.B.no(idx).origCusaMdseCd_B0.clear();
            bizMsg.B.no(idx).ordQty_B0.clear();
            bizMsg.B.no(idx).uomCd_B0.clear();
            bizMsg.B.no(idx).etdDt_B0.clear();
            bizMsg.B.no(idx).etaDt_B0.clear();
            bizMsg.B.no(idx).ordDtlLastUpdTs_B0.clear();
            bizMsg.B.no(idx).xxNum_B0.clear();
            bizMsg.B.no(idx).batErrMsgTxt_BC.clear();
            bizMsg.B.no(idx).batErrMsgTxt_BD.clear();
            bizMsg.B.no(idx).batErrMsgTxt_BV.clear();
            bizMsg.B.no(idx).ezUpTime_B0.clear();
            bizMsg.B.no(idx).ezUpTimeZone_B0.clear();
            bizMsg.B.no(idx).ediPoOrdDtlLineNum_HD.clear();
            bizMsg.B.no(idx).poOrdDtlLineNum_HD.clear();
        }
        bizMsg.B.setValidCount(0);

        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_B0, globalMsg.xxChkBox_B0);

        boolean allDisp = false;

        if (ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.xxChkBox_B0.getValue())) {
            allDisp = true;
        }

        if (allDisp) {
            int bizIdx = 0;
            for (int idx = 0; idx < globalMsg.B.getValidCount(); idx++) {
                // START 2019/09/09 T.Ogura [QC#53232,ADD]
                if (bizIdx == bizMsg.B.length()) {
                    break;
                }
                // END   2019/09/09 T.Ogura [QC#53232,ADD]
                EZDMsg.copy(globalMsg.B.no(idx), null, bizMsg.B.no(bizIdx), null);
                bizIdx++;
            }
            bizMsg.B.setValidCount(bizIdx);
            return;
        }

        int bizIdx = 0;
        boolean errFlg = false;
        String preEdiLineNum = "";
        List<NPAL1140_BSMsg> blockEdiLineBSMsgList = new ArrayList<NPAL1140_BSMsg>();

        for (int idx = 0; idx < globalMsg.B.getValidCount(); idx++) {

			if (!isSameEdiLineNum(preEdiLineNum,
					globalMsg.B.no(idx).ediPoOrdDtlLineNum_HD.getValue())) {

				preEdiLineNum = globalMsg.B.no(idx).ediPoOrdDtlLineNum_HD.getValue();

				// QC#18631
				if (ZYPCommonFunc.hasValue(globalMsg.B.no(idx).batErrMsgTxt_BV)) {
					errFlg = true;
					blockEdiLineBSMsgList.add(globalMsg.B.no(idx));
				} else {
					errFlg = false;
				}

			} else {
				// QC#18631
				if (errFlg) {
					blockEdiLineBSMsgList.add(globalMsg.B.no(idx));
				}
			}

		}
		if (blockEdiLineBSMsgList.size() > 0) {
			for (NPAL1140_BSMsg blockEdiLineBSMsg : blockEdiLineBSMsgList) {
                // START 2019/09/09 T.Ogura [QC#53232,ADD]
                if (bizIdx == bizMsg.B.length()) {
                    break;
                }
                // END   2019/09/09 T.Ogura [QC#53232,ADD]
				EZDMsg.copy(blockEdiLineBSMsg, null, bizMsg.B.no(bizIdx), null);
				bizIdx++;
			}
		}
        bizMsg.B.setValidCount(bizIdx);
    }

    /**
     * 
     * @param preStr String
     * @param curStr String
     * @return boolean
     */
    public static boolean isSameEdiLineNum(String preStr, String curStr) {
        if (ZYPCommonFunc.hasValue(preStr)) {
            if (!preStr.equals(curStr)) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * @param globalMsg NPAL1140SMsg
     * @param ssmResult S21SsmEZDResult
     */
    public static void searchListSet(NPAL1140SMsg globalMsg, S21SsmEZDResult ssmResult) {
        globalMsg.A.clear();
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

        if (resultList.size() == 0) {
            globalMsg.A.setValidCount(0);
            return;
        }

        BigDecimal prePoAckHdrWrkPk = null;
        BigDecimal curPoAckHdrWrkPk = null;
        List<String> errMsgList = new ArrayList<String>();
        int idx = 0;
        int lineIdx = 0;
        for (Map<String, Object> result : resultList) {
            curPoAckHdrWrkPk = (BigDecimal) result.get("PO_ACK_HDR_WRK_PK");
            if (isSame(prePoAckHdrWrkPk, curPoAckHdrWrkPk)) {
                if (ZYPCommonFunc.hasValue((String) result.get("BAT_ERR_MSG_TXT"))) {
                    errMsgList.add((String) result.get("BAT_ERR_MSG_TXT"));
                }
            } else {
                if (idx != 0 && errMsgList.size() != 0) {
                    int msgIdx = 0;
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(lineIdx - 1).batErrMsgTxt_AV, errMsgList.get(0));
                    for (String errMsg : errMsgList) {
                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(lineIdx - 1).batErrMsgTxt_AC.no(msgIdx), errMsg);
                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(lineIdx - 1).batErrMsgTxt_AD.no(msgIdx), errMsg);
                        msgIdx++;
                    }
                }
                if (lineIdx > 998) {
                    break;
                }
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(lineIdx).poAckHdrWrkPk_A0, (BigDecimal) result.get("PO_ACK_HDR_WRK_PK"));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(lineIdx).itrlIntfcId_A0, (String) result.get("ITRL_INTFC_ID"));
                // 2017/10/27 QC20380 M.Naito Mod Start
//                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(lineIdx).vndNm_A0, (String) result.get("LOC_NM"));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(lineIdx).dplyVndNm_A0, (String) result.get("LOC_NM"));
                // 2017/10/27 QC20380 M.Naito Mod End
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(lineIdx).ediPoOrdNum_A0, (String) result.get("EDI_PO_ORD_NUM"));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(lineIdx).poAckNum_A0, (String) result.get("PO_ACK_NUM"));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(lineIdx).ackEdiProcStsNm_A0, (String) result.get("ACK_EDI_PROC_STS_NM"));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(lineIdx).openPoAckWrkFlg_A0, (String) result.get("OPEN_PO_ACK_WRK_FLG"));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(lineIdx).ediRcvDateTs_A0, (String) result.get("EDI_RCV_DATE_TS"));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(lineIdx).ordLastUpdTs_A0, (String) result.get("ORD_LAST_UPD_TS"));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(lineIdx).vndCpoOrdNum_A0, (String) result.get("VND_CPO_ORD_NUM"));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(lineIdx).ezUpTime_A0, (String) result.get("EZUPTIME"));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(lineIdx).ezUpTimeZone_A0, (String) result.get("EZUPTIMEZONE"));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(lineIdx).ediPoOrdDtlLineNum_A0, (String) result.get("EDI_PO_ORD_DTL_LINE_NUM"));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(lineIdx).poOrdDtlLineNum_A0, (String) result.get("PO_ORD_DTL_LINE_NUM"));
                prePoAckHdrWrkPk = curPoAckHdrWrkPk;
                lineIdx++;
                errMsgList = new ArrayList<String>();
                if (ZYPCommonFunc.hasValue((String) result.get("BAT_ERR_MSG_TXT"))) {
                    errMsgList.add((String) result.get("BAT_ERR_MSG_TXT"));
                }
            }
            idx++;
        }
        if (lineIdx != 0 && errMsgList.size() != 0) {
            int msgIdx = 0;
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(lineIdx - 1).batErrMsgTxt_AV, errMsgList.get(0));
            for (String errMsg : errMsgList) {
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(lineIdx - 1).batErrMsgTxt_AC.no(msgIdx), errMsg);
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(lineIdx - 1).batErrMsgTxt_AD.no(msgIdx), errMsg);
                msgIdx++;
            }
        }
        globalMsg.A.setValidCount(lineIdx);
    }

    private static boolean isSame(BigDecimal preStr, BigDecimal curStr) {
        if (ZYPCommonFunc.hasValue(preStr)) {
            if (ZYPCommonFunc.hasValue(curStr)) {
                if (preStr.compareTo(curStr) != 0) {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            if (ZYPCommonFunc.hasValue(curStr)) {
                return false;
            }
        }
        return true;
    }
}
