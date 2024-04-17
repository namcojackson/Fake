/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1150.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASN_EDI_PROC_STS;

import business.blap.NPAL1150.NPAL1150CMsg;
import business.blap.NPAL1150.NPAL1150Query;
import business.blap.NPAL1150.NPAL1150SMsg;
import business.blap.NPAL1150.NPAL1150_CSMsg;
import static business.blap.NPAL1150.constant.NPAL1150Constant.*;
import business.db.EDI_ASN_HDR_WRKTMsg;
import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;

/** 
 *<pre>
 * NPAL1150 ASN Error Correction
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/15   Hitachi         T.Kawazu        Create          N/A
 * 2013/06/13   Hitachi         T.Tomita        Update          QC1233
 * 2013/06/19   Hitachi         T.Kawazu        Update          QC1388
 * 2013/10/28   Hitachi         T.Aoyagi        Update          QC2852
 *</pre>
 */

public class NPAL1150CommonLogic {
    /**
     * <pre>
     * copyFromSmsgOntoCmsg
     * Copy data From Smsg Onto Cmsg
     * </pre>
     * @param cMsg NPAL1150CMsg
     * @param sMsg NPAL1150SMsg
     */
    public static void copyFromSMsgOntoCMsg(NPAL1150CMsg cMsg, NPAL1150SMsg sMsg) {

        // copy data from SMsg onto CMsg
        int pagenationFromIndex = cMsg.xxPageShowFromNum.getValueInt() - 1;
        int i = pagenationFromIndex;
        for (; i < pagenationFromIndex + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFromIndex), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFromIndex);

        // set value to pageNation items
        cMsg.xxPageShowToNum.setValue(pagenationFromIndex + cMsg.A.getValidCount());
    }

    /**
     * formatDateTs
     * @param inStr String
     * @return String
     */
    public static String formatDateTs(String inStr) {
        String rtnStr = "";
        if (!ZYPCommonFunc.hasValue(inStr)) {
            return rtnStr;
        }
        if (inStr.length() < TS_LENGTH) {
            return rtnStr;
        }
        String dateStr = inStr.substring(0, TS_LENGTH);
        // START 10/28/2013 T.Aoyagi QC2852 Update
        //SimpleDateFormat formatterIn = new SimpleDateFormat(TS_FORMAT);
        //SimpleDateFormat formatterOut = new SimpleDateFormat(TS_DISP_FORMAT);

        //Date inDate = formatterIn.parse(dateStr, new ParsePosition(0));

        //rtnStr = formatterOut.format(inDate);
        
        //return rtnStr;
        return ZYPDateUtil.formatEzd14ToDisp(dateStr, true, true, true);
        // END 10/28/2013 T.Aoyagi QC2852 QC2852 Update
    }

    /**
     * copyFromCMsgOntoSMsg
     * @param cMsg NPAL1150CMsg
     * @param sMsg NPAL1150SMsg
     */
    public static void copyFromCMsgOntoSMsg(NPAL1150CMsg cMsg, NPAL1150SMsg sMsg) {
        int fromNo = cMsg.xxPageShowFromNum.getValueInt();
        int toNo = cMsg.xxPageShowToNum.getValueInt();

        // cMsg -> sMsg
        for (int i = 0; i < (toNo - fromNo + 1); i++) {
            EZDMsg.copy(cMsg.A.no(i), null, sMsg.A.no(fromNo + i - 1), null);
        }

    }

    /**
     * 
     * @param cMsg NPAL1150CMsg
     * @param sMsg NPAL1150SMsg
     * @param sMsgIndex int
     * @param headerTMsg EDI_ASN_HDR_WRKTMsg
     * @return boolean
     */
    public static boolean editEdiAsnHdrWrkToReprocess(NPAL1150CMsg cMsg, NPAL1150SMsg sMsg, int sMsgIndex, EDI_ASN_HDR_WRKTMsg headerTMsg) {
        headerTMsg = (EDI_ASN_HDR_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(headerTMsg);
        if (headerTMsg == null) {
            cMsg.setMessageInfo(NPAM1297E);
            return false;
        }
        if (!ZYPDateUtil.isSameTimeStamp(sMsg.A.no(sMsgIndex).ezUpTime_B1.getValue(), sMsg.A.no(sMsgIndex).ezUpTimeZone_B1.getValue(), headerTMsg.ezUpTime.getValue(), headerTMsg.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(NPAM1297E);
            return false;
        }
        setValue(headerTMsg.asnEdiProcStsCd, ASN_EDI_PROC_STS.REPROCESS);
        setValue(headerTMsg.procFlg, ZYPConstant.FLG_OFF_0);
        EZDTBLAccessor.update(headerTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(headerTMsg.getReturnCode())) {
            cMsg.setMessageInfo(ZZZM9013E, new String[] {headerTMsg.getReturnCode() });
            return false;
        }
        return true;
    }

    // 2013/06/13 QC1233 T.Tomita Add Start
    /**
     * @param cMsg NPAL1150CMsg
     * @param sMsg NPAL1150SMsg
     */
    public static void detailListDisp(NPAL1150CMsg cMsg, NPAL1150SMsg sMsg) {
        clearCMsgCList(cMsg);
        boolean allDisp = false;
        if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.xxChkBox_H1.getValue())) {
            allDisp = true;
        }

        int bizIdx = 0;
        if (allDisp) {
            for (int idx = 0; idx < sMsg.C.getValidCount(); idx++) {
                EZDMsg.copy(sMsg.C.no(idx), null, cMsg.C.no(bizIdx), null);
                bizIdx++;
            }
            cMsg.C.setValidCount(bizIdx);
            return;
        }

        boolean blockErrFlg = false;
        String preEdiLineNum = "";
        List<NPAL1150_CSMsg> blockEdiLineCSMsgList = new ArrayList<NPAL1150_CSMsg>();
        for (int idx = 0; idx < sMsg.C.getValidCount(); idx++) {
            if (!isSameEdiLineNum(preEdiLineNum, sMsg.C.no(idx).ediPoOrdDtlLineNum_HD.getValue())) {
                if (blockErrFlg) {
                    for (NPAL1150_CSMsg blockEdiLineCSMsg : blockEdiLineCSMsgList) {
                        EZDMsg.copy(blockEdiLineCSMsg, null, cMsg.C.no(bizIdx), null);
                        bizIdx++;
                    }
                }
                blockErrFlg = false;
                preEdiLineNum = sMsg.C.no(idx).ediPoOrdDtlLineNum_HD.getValue();
                blockEdiLineCSMsgList = new ArrayList<NPAL1150_CSMsg>();
            }
            if (sMsg.C.no(idx).xxNum_D1.getValueInt() != 0) {
                blockErrFlg = true;
            }
            blockEdiLineCSMsgList.add(sMsg.C.no(idx));
        }
        if (blockEdiLineCSMsgList.size() > 0) {
            if (blockErrFlg) {
                for (NPAL1150_CSMsg blockEdiLineCSMsg : blockEdiLineCSMsgList) {
                    EZDMsg.copy(blockEdiLineCSMsg, null, cMsg.C.no(bizIdx), null);
                    bizIdx++;
                }
            }
        }
        cMsg.C.setValidCount(bizIdx);
    }

    /**
     * copyFromCCListOntoSCList
     * @param cMsg NPAL1150CMsg
     * @param sMsg NPAL1150SMsg
     */
    public static void copyFromCCListOntoSCList(NPAL1150CMsg cMsg, NPAL1150SMsg sMsg) {
        // Copy from cMsg.C to sMsg.C
        for (int cIdx = 0; cIdx < cMsg.C.getValidCount(); cIdx++) {
            for (int sIdx = 0; sIdx < sMsg.C.getValidCount(); sIdx++) {
                if (cMsg.C.no(cIdx).asnLineNum_D1.getValue().equals(sMsg.C.no(sIdx).asnLineNum_D1.getValue())) {
                    Map<String, Object> resultMap = NPAL1150Query.getInstance().getLogicLineNum(cMsg.B.no(0).poOrdNum_H1.getValue(), sMsg.C.no(sIdx).dispPoDtlLineNum_D1.getValue());
                    if (resultMap.size() != 0) {
                        ZYPEZDItemValueSetter.setValue(cMsg.C.no(sIdx).dispPoDtlLineNum_HD, (String) resultMap.get("PO_ORD_DTL_LINE_NUM"));
                    }

                    ZYPEZDItemValueSetter.setValue(sMsg.C.no(sIdx).dispPoDtlLineNum_D1, cMsg.C.no(cIdx).dispPoDtlLineNum_D1);
                    ZYPEZDItemValueSetter.setValue(sMsg.C.no(sIdx).dispPoDtlLineNum_HD, cMsg.C.no(cIdx).dispPoDtlLineNum_HD);
                    ZYPEZDItemValueSetter.setValue(sMsg.C.no(sIdx).dispPoDtlLineNum_BK, cMsg.C.no(cIdx).dispPoDtlLineNum_BK);
                    break;
                }
            }
        }
    }

    /**
     * isSameEdiLineNum
     * @param lineNum1 String
     * @param lineNum2 String
     * @return boolean
     */
    public static boolean isSameEdiLineNum(String lineNum1, String lineNum2) {
        if (ZYPCommonFunc.hasValue(lineNum1)) {
            if (!lineNum1.equals(lineNum2)) {
                return false;
            }
        } else {
            return false;
        }

        return true;
    }

    /**
     * clearCMsgCList
     * @param cMsg
     */
    private static void clearCMsgCList(NPAL1150CMsg cMsg) {
        for (int i = 0; i < cMsg.C.getValidCount(); i++) {
            cMsg.C.no(i).ezUpTime_DD.clear();
            cMsg.C.no(i).ezUpTimeZone_DD.clear();
            cMsg.C.no(i).dispPoDtlLineNum_D1.clear();
            cMsg.C.no(i).dispPoDtlLineNum_D2.clear();
            cMsg.C.no(i).dispPoDtlLineNum_HD.clear();
            cMsg.C.no(i).ediPoOrdDtlLineNum_D1.clear();
            cMsg.C.no(i).ediPoOrdDtlLineNum_D2.clear();
            cMsg.C.no(i).ediPoOrdDtlLineNum_HD.clear();
            cMsg.C.no(i).asnSoNum_D1.clear();
            cMsg.C.no(i).asnLineNum_D1.clear();
            cMsg.C.no(i).asnSoSlpNum_D1.clear();
            cMsg.C.no(i).mdseCd_D1.clear();
            cMsg.C.no(i).mdseCdUpdFlg_D1.clear();
            cMsg.C.no(i).asnQty_D1.clear();
            // Update QC1388 Start
            cMsg.C.no(i).asnCarrCd_D1.clear();
            // Update QC1388 End
            cMsg.C.no(i).asnProNum_D1.clear();
            cMsg.C.no(i).asnBolNum_D1.clear();
            cMsg.C.no(i).asnPlnDelyDt_D1.clear();
            cMsg.C.no(i).xxNum_D1.clear();
            cMsg.C.no(i).batErrMsgTxt_DV.clear();
            cMsg.C.no(i).batErrMsgTxt_DC.clear();
            cMsg.C.no(i).batErrMsgTxt_DD.clear();

        }
    }
    // 2013/06/13 QC1233 T.Tomita Add End
}
