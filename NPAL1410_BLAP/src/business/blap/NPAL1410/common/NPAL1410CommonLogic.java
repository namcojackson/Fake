/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NPAL1410.common;

import static business.blap.NPAL1410.constant.NPAL1410Constant.NPAM0224E;
import static business.blap.NPAL1410.constant.NPAL1410Constant.NPAM1172E;
import static business.blap.NPAL1410.constant.NPAL1410Constant.NPAM1378E;
import static business.blap.NPAL1410.constant.NPAL1410Constant.NPAM1493E;
import static business.blap.NPAL1410.constant.NPAL1410Constant.NPAM1586E;
import static business.blap.NPAL1410.constant.NPAL1410Constant.NPAM1589E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NPAL1410.NPAL1410CMsg;
import business.blap.NPAL1410.NPAL1410Query;
import business.blap.NPAL1410.NPAL1410SMsg;
import business.db.MDL_NMTMsg;
import business.db.MDSETMsg;
import business.db.NLGI5100_01TMsg;
import business.db.RMNF_ORDTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RMNF_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;

/**
 * <pre>
 * Business ID : NPAL1410 Reman Workbench
 * Function Name : 
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/05/2016   CITS       Yasushi Nomura       Create          N/A
 * 08/16/2016   CSAI            Y.Imazu         Update          QC#13406
 * 08/26/2016   CITS            T.Wada          Update          QC#10915
 * 10/05/2016   CITS            T.Hakodate      Update          QC#13276
 * 10/07/2016   CITS            T.Wada          Update          QC#13807
 * 10/14/2016   CITS            T.Wada          Update          QC#14011
 * 10/17/2016   CITS            T.Wada          Update          QC#15221
 * 10/21/2016   CITS            T.Hakodate      Update          QC#15057
 * 10/24/2016   CITS            T.Wada          Update          QC#15449
 * 10/24/2016   CITS            T.Wada          Update          QC#13875
 * 09/20/2017   CITS            T.Kikuhara      Update          QC#18675(SOL#219)
 * 10/16/2017   CITS            K.Ogino         Update          QC#21805
 * 10/16/2017   CITS            T.Wada          Update          QC#21842
 * 11/09/2017   CITS            T.Tokutomi      Update          QC#22178
 * 12/15/2017   CITS            K.Ogino         Update          QC#22836
 * 06/07/2018   CITS            Y.Iwasaki       Update          QC#26351
 * 07/13/2018   CITS            T.hakodate      Update          QC#26868
 * 04/01/2019   CITS            K.Ogino         Update          QC#30851
 * 12/05/2019   Fujitsu         T.Ogura         Update          QC#54842
 * 03/09/2020   Fujitsu         T.Ogura         Update          QC#56058
 *</pre>
 */
public class NPAL1410CommonLogic {

    /**
     * <pre>
     * copyFromSmsgOntoCmsg
     * Copy data From Smsg Onto Cmsg
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     */
    public static void copyFromSMsgOntoCmsg(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg) {

        ZYPEZDItemValueSetter.setValue(cMsg.rmnfRtlWhCd_H1, sMsg.rmnfRtlWhCd_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_H1, sMsg.rtlWhNm_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfOrdNum_H1, sMsg.rmnfOrdNum_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfOrdNum_BK, sMsg.rmnfOrdNum_BK);
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfOrdNum_TR, sMsg.rmnfOrdNum_TR);
        ZYPEZDItemValueSetter.setValue(cMsg.ownrTechTocCd_H1, sMsg.ownrTechTocCd_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.techNm_H1, sMsg.techNm_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfMainUnitSerNum_H1, sMsg.rmnfMainUnitSerNum_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfOrdStsNm_H1, sMsg.rmnfOrdStsNm_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfOrdTpCd_SE, sMsg.rmnfOrdTpCd_SE);
        ZYPEZDItemValueSetter.setValue(cMsg.whLoctrCd_H1, sMsg.whLoctrCd_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfRtlSwhCd_H1, sMsg.rmnfRtlSwhCd_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfStartDt_H1, sMsg.rmnfStartDt_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfEndDt_H1, sMsg.rmnfEndDt_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfMainUnitMdseCd_HH, sMsg.rmnfMainUnitMdseCd_HH);
        ZYPEZDItemValueSetter.setValue(cMsg.t_MdlNm_H1, sMsg.t_MdlNm_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfOrdCmntTxt_H1, sMsg.rmnfOrdCmntTxt_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfPrtUsgCostAmt_H1, sMsg.rmnfPrtUsgCostAmt_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfTotLborCostAmt_H1, sMsg.rmnfTotLborCostAmt_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfMachCostAmt_H1, sMsg.rmnfMachCostAmt_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfTotCostAmt_H1, sMsg.rmnfTotCostAmt_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfTotCostAmt_H2, sMsg.rmnfTotCostAmt_H2);
        ZYPEZDItemValueSetter.setValue(cMsg.svcConfigMstrPk_A1, sMsg.svcConfigMstrPk_A1);
        //QC#18675 ADD START
        ZYPEZDItemValueSetter.setValue(cMsg.t_MdlNm_H2, sMsg.t_MdlNm_H2);
        ZYPEZDItemValueSetter.setValue(cMsg.origMdlId_HH, sMsg.origMdlId_HH);
        ZYPEZDItemValueSetter.setValue(cMsg.rwsNum_A1, sMsg.rwsNum_A1);
        ZYPEZDItemValueSetter.setValue(cMsg.soNum_A1, sMsg.soNum_A1);
        //QC#18675 ADD END
        ZYPEZDItemValueSetter.setValue(cMsg.t_MdlNm_A1, sMsg.t_MdlNm_A1);
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfOrdStsCd_HH, sMsg.rmnfOrdStsCd_HH);
        ZYPEZDItemValueSetter.setValue(cMsg.aftDeclPntDigitNum_HH, sMsg.aftDeclPntDigitNum_HH);
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfInvtyLocCd_HH, sMsg.rmnfInvtyLocCd_HH);
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfUsgRtlWhCd_HH, sMsg.rmnfUsgRtlWhCd_HH);
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfUsgRtlSwhCd_HH, sMsg.rmnfUsgRtlSwhCd_HH);
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfMdlId_HH, sMsg.rmnfMdlId_HH);
        ZYPEZDItemValueSetter.setValue(cMsg.assetRecovCostAmt_H1, sMsg.assetRecovCostAmt_H1);
        // QC#30851
        ZYPEZDItemValueSetter.setValue(cMsg.svcConfigMstrPk_H1, sMsg.svcConfigMstrPk_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.mdseCd_H1, sMsg.mdseCd_H1);

        // copy data from SMsg onto CMsg
        // Config
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
        // StdParts
        ZYPTableUtil.clear(cMsg.B);
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i), null);
        }
        cMsg.B.setValidCount(sMsg.B.getValidCount());
        // NonStdParts
        ZYPTableUtil.clear(cMsg.C);
        int addRowCount = 0;
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            if (!ZYPConstant.FLG_ON_Y.equals(sMsg.C.no(i).xxEdtModeFlg_C1.getValue())) {
                EZDMsg.copy(sMsg.C.no(i), null, cMsg.C.no(addRowCount), null);
                addRowCount++;
            }
        }
        cMsg.C.setValidCount(addRowCount);
        // Task
        ZYPTableUtil.clear(cMsg.D);
        for (int i = 0; i < sMsg.D.getValidCount(); i++) {
            EZDMsg.copy(sMsg.D.no(i), null, cMsg.D.no(i), null);
        }
        cMsg.D.setValidCount(sMsg.D.getValidCount());

        //QC#18675 ADD START
        // So Rws
        ZYPTableUtil.clear(cMsg.E);
        for (int i = 0; i < sMsg.E.getValidCount(); i++) {
            EZDMsg.copy(sMsg.E.no(i), null, cMsg.E.no(i), null);
        }
        cMsg.E.setValidCount(sMsg.E.getValidCount());
        //QC#18675 ADD END
    }

    /**
     * <pre>
     * Set page pos.
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     */
    public static void setPagePos(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg) {

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
     * <pre>
     * getLastPageStartCount
     * </pre>
     * @param allRowCount int
     * @param pageRowCount int
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
     * <pre>
     * copyFromCmsgOntoSmsg
     * Copy data From CMsg Onto SMsg
     * </pre>
     * @param sMsg NPAL1410SMsg
     * @param cMsg NPAL1410CMsg
     */
    public static void copyFromCmsgOntoSmsg(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg) {

        ZYPEZDItemValueSetter.setValue(sMsg.rmnfRtlWhCd_H1, cMsg.rmnfRtlWhCd_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.rtlWhNm_H1, cMsg.rtlWhNm_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfOrdNum_H1, cMsg.rmnfOrdNum_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfOrdNum_BK, cMsg.rmnfOrdNum_BK);
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfOrdNum_TR, cMsg.rmnfOrdNum_TR);
        ZYPEZDItemValueSetter.setValue(sMsg.ownrTechTocCd_H1, cMsg.ownrTechTocCd_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.techNm_H1, cMsg.techNm_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfMainUnitSerNum_H1, cMsg.rmnfMainUnitSerNum_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfOrdStsNm_H1, cMsg.rmnfOrdStsNm_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfOrdTpCd_SE, cMsg.rmnfOrdTpCd_SE);
        ZYPEZDItemValueSetter.setValue(sMsg.whLoctrCd_H1, cMsg.whLoctrCd_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfRtlSwhCd_H1, cMsg.rmnfRtlSwhCd_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfStartDt_H1, cMsg.rmnfStartDt_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfEndDt_H1, cMsg.rmnfEndDt_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfMainUnitMdseCd_HH, cMsg.rmnfMainUnitMdseCd_HH);
        ZYPEZDItemValueSetter.setValue(sMsg.t_MdlNm_H1, cMsg.t_MdlNm_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfOrdCmntTxt_H1, cMsg.rmnfOrdCmntTxt_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfPrtUsgCostAmt_H1, cMsg.rmnfPrtUsgCostAmt_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfTotLborCostAmt_H1, cMsg.rmnfTotLborCostAmt_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfMachCostAmt_H1, cMsg.rmnfMachCostAmt_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfTotCostAmt_H1, cMsg.rmnfTotCostAmt_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfTotCostAmt_H2, cMsg.rmnfTotCostAmt_H2);
        ZYPEZDItemValueSetter.setValue(sMsg.svcConfigMstrPk_A1, cMsg.svcConfigMstrPk_A1);
        //QC#18675 ADD START
        ZYPEZDItemValueSetter.setValue(sMsg.t_MdlNm_H2, cMsg.t_MdlNm_H2);
        ZYPEZDItemValueSetter.setValue(sMsg.origMdlId_HH, cMsg.origMdlId_HH);
        ZYPEZDItemValueSetter.setValue(sMsg.rwsNum_A1, cMsg.rwsNum_A1);
        ZYPEZDItemValueSetter.setValue(sMsg.soNum_A1, cMsg.soNum_A1);
        //QC#18675 ADD END
        ZYPEZDItemValueSetter.setValue(sMsg.t_MdlNm_A1, cMsg.t_MdlNm_A1);
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfOrdStsCd_HH, cMsg.rmnfOrdStsCd_HH);
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfInvtyLocCd_HH, cMsg.rmnfInvtyLocCd_HH);
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfUsgRtlWhCd_HH, cMsg.rmnfUsgRtlWhCd_HH);
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfUsgRtlSwhCd_HH, cMsg.rmnfUsgRtlSwhCd_HH);
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfMdlId_HH, cMsg.rmnfMdlId_HH);
        ZYPEZDItemValueSetter.setValue(sMsg.assetRecovCostAmt_H1, cMsg.assetRecovCostAmt_H1);
        // QC#30851
        ZYPEZDItemValueSetter.setValue(sMsg.svcConfigMstrPk_H1, cMsg.svcConfigMstrPk_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.mdseCd_H1, cMsg.mdseCd_H1);

        // Config
        if (0 < cMsg.A.getValidCount()) {
            for (int j = cMsg.xxPageShowFromNum.getValueInt() - 1, k = 0; j < cMsg.xxPageShowToNum.getValueInt(); j++, k++) {
                EZDMsg.copy(cMsg.A.no(k), null, sMsg.A.no(j), null);
            }
        }
        // StdParts
        ZYPTableUtil.clear(sMsg.B);
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            EZDMsg.copy(cMsg.B.no(i), null, sMsg.B.no(i), null);
        }
        sMsg.B.setValidCount(cMsg.B.getValidCount());
        // NonStdParts
        int delRowCount = 0;
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            if (!ZYPConstant.FLG_ON_Y.equals(sMsg.C.no(i).xxEdtModeFlg_C1.getValue())) {
                delRowCount = i;
                break;
            }
        }
        for (int i = 0; i < cMsg.C.getValidCount(); i++) {
            EZDMsg.copy(cMsg.C.no(i), null, sMsg.C.no(delRowCount + i), null);
        }
        sMsg.C.setValidCount(cMsg.C.getValidCount() + delRowCount);
        // Task
        ZYPTableUtil.clear(sMsg.D);
        for (int i = 0; i < cMsg.D.getValidCount(); i++) {
            EZDMsg.copy(cMsg.D.no(i), null, sMsg.D.no(i), null);
        }
        sMsg.D.setValidCount(cMsg.D.getValidCount());
    }

    /**
     * getTechName
     * @param techTocCode techTocCode
     * @param glblCmpyCd glblCmpyCd
     * @return String
     */
    public static String getTechName(String techTocCode, String glblCmpyCd) {

        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("techTocCd", techTocCode);
        ssmParam.put("effFromDt", slsDt);
        ssmParam.put("effThruDt", slsDt);
        ssmParam.put("delFlg", ZYPConstant.FLG_OFF_N);
        S21SsmEZDResult result = NPAL1410Query.getInstance().getTechName(ssmParam);

        if (result.isCodeNormal()) {

            List<Map<String, Object>> resultMap = (List<Map<String, Object>>) result.getResultObject();

            if (0 < resultMap.size()) {

                Map<String, Object> recode = (Map<String, Object>) resultMap.get(0);
                return (String) recode.get("TECH_NM");
            }
        }

        return null;
    }

    /**
     * <pre>
     * getRtlSwhList
     * </pre>
     * @param rtlWhCd String
     * @param glblCmpyCd String
     * @return Rtl SWH Lists
     */
    public static Map<String, String> getRtlSwhList(String rtlWhCd, String glblCmpyCd) {

        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rtlWhCd", rtlWhCd);
        ssmParam.put("effFromDt", slsDt);
        ssmParam.put("effThruDt", slsDt);

        S21SsmEZDResult result = NPAL1410Query.getInstance().getRtlSwhList(ssmParam);

        Map<String, String> ret = new HashMap<String, String>();

        if (result.isCodeNormal()) {

            List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) result.getResultObject();

            for (Map<String, Object> resultMap : resultMapList) {

                ret.put((String) resultMap.get("RTL_SWH_CD"), (String) resultMap.get("INVTY_LOC_CD"));
            }
        }

        return ret;
    }

    /**
     * <pre>
     * existLocator
     * </pre>
     * @param rtlWhCd String
     * @param whLoctrCd String
     * @param glblCmpyCd String
     * @return existLocator
     */
    public static boolean existLocator(String rtlWhCd, String whLoctrCd, String glblCmpyCd) {

        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rtlWhCd", rtlWhCd);
        ssmParam.put("whLoctrCd", whLoctrCd);
        ssmParam.put("effFromDt", slsDt);
        ssmParam.put("effThruDt", slsDt);

        S21SsmEZDResult result = NPAL1410Query.getInstance().countLocator(ssmParam);

        if (result.isCodeNormal()) {

            return (Integer) result.getResultObject() > 0;
        }

        return false;
    }

    /**
     * <pre>
     * getItemName
     * </pre>
     * @param mdseCd String
     * @param glblCmpyCd String
     * @return Item Name
     */
    public static String getItemName(String mdseCd, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);

        S21SsmEZDResult result = NPAL1410Query.getInstance().getItemName(ssmParam);

        if (result.isCodeNormal()) {

            return (String) result.getResultObject();
        }

        return null;
    }

    /**
     * <pre>
     * findSameRemanOrder
     * </pre>
     * @param whLoctrCd String
     * @param rmnfOrdNum String
     * @param glblCmpyCd String
     * @return Same Reman order
     */
    public static String findSameRemanOrder(String rmnfRtlWhCd, String whLoctrCd, String rmnfOrdNum, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rmnfRtlWhCd", rmnfRtlWhCd);
        ssmParam.put("whLoctrCd", whLoctrCd);
        ssmParam.put("rmnfOrdNum", rmnfOrdNum);
        ssmParam.put("openStsFlg", ZYPConstant.FLG_ON_Y);

        S21SsmEZDResult result = NPAL1410Query.getInstance().findSameRemanOrder(ssmParam);

        if (result.isCodeNormal()) {

            return (String) result.getResultObject();
        }

        return null;
    }

    // QC#15057
    /**
     * chkMdseIntangible
     * @param mdseCd mdseCd
     * @param glblCmpyCd glblCmpyCd
     * @return boolean
     */
    public static boolean chkMdseIntangible(String mdseCd, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);

        S21SsmEZDResult result = NPAL1410Query.getInstance().chkMdseIntangible(ssmParam);

        if (!result.isCodeNormal() || (Integer) result.getResultObject() == 0) {
            return false;
        }

        return true;
    }

    // START 2019/12/05 T.Ogura [QC#54842,ADD]
    /**
     * chkIbTrackableItem
     * @param mdseCd
     * @param glblCmpyCd
     * @return true(Non IB Trackable Item) / false (IB Trackable Item)
     */
    public static boolean chkIbTrackableItem(String mdseCd, String glblCmpyCd) {

        MDSETMsg mdse = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdse.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdse.mdseCd, mdseCd);
        mdse = (MDSETMsg) EZDTBLAccessor.findByKey(mdse);

        if (mdse != null && ZYPConstant.FLG_ON_Y.equals(mdse.instlBaseCtrlFlg.getValue())) {
            // IB Trackable Item.
            return false;
        }

        return true;
    }
    // END   2019/12/05 T.Ogura [QC#54842,ADD]

    /**
     * <pre>
     * getItemInfo
     * </pre>
     * @param mdseCd String
     * @param glblCmpyCd String
     * @return Item info
     */
    public static Map<String, Object> getItemInfo(String mdseCd, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);

        List<String> rgtnStsCdList = new ArrayList<String>();
        rgtnStsCdList.add(RGTN_STS.READY_FOR_CUSTOMS_CLEARANCE_AND_RECEIVING);
        rgtnStsCdList.add(RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("rgtnStsCd", rgtnStsCdList);

        S21SsmEZDResult result = NPAL1410Query.getInstance().getItemInfo(ssmParam);

        if (result.isCodeNormal()) {

            return (Map<String, Object>) result.getResultObject();
        }

        return null;
    }

    /**
     * <pre>
     * getShippingOrderForSubmit
     * </pre>
     * @param soNum String
     * @param glblCmpyCd String
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> getShippingOrderForSubmit(String soNum, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("soNum", soNum);

        S21SsmEZDResult result = NPAL1410Query.getInstance().getShippingOrderForSubmit(ssmParam);

        if (result.isCodeNormal()) {

            return (List<Map<String, Object>>) result.getResultObject();
        }

        return null;
    }

    /**
     * <pre>
     * getUnusedParts
     * </pre>
     * @param rmnfOrdNum String
     * @param glblCmpyCd String
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> getUnusedParts(String rmnfOrdNum, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rmnfOrdNum", rmnfOrdNum);
        ssmParam.put("completed", ZYPConstant.FLG_ON_1);

        S21SsmEZDResult result = NPAL1410Query.getInstance().getUnusedParts(ssmParam);

        if (result.isCodeNormal()) {

            return (List<Map<String, Object>>) result.getResultObject();
        }

        return null;
    }

    /**
     * <pre>
     * getWhInfo
     * </pre>
     * @param rtlWhCd String
     * @param rtlSwhCd String
     * @param glblCmpyCd String
     * @return WH info
     */
    public static Map<String, Object> getWhInfo(String rtlWhCd, String rtlSwhCd, String glblCmpyCd) {

        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rtlWhCd", rtlWhCd);
        ssmParam.put("rtlSwhCd", rtlSwhCd);
        ssmParam.put("effFromDt", slsDt);
        ssmParam.put("effThruDt", slsDt);

        S21SsmEZDResult result = NPAL1410Query.getInstance().getWhInfo(ssmParam);

        if (result.isCodeNormal()) {

            List<Map<String, Object>> resultMap = (List<Map<String, Object>>) result.getResultObject();

            if (0 < resultMap.size()) {

                return (Map<String, Object>) resultMap.get(0);
            }
        }

        return null;
    }

    /**
     * <pre>
     * getShippingPlan
     * </pre>
     * @param trxSrcTpCd String
     * @param trxHdrNum String
     * @param glblCmpyCd String
     * @return ShippingPlan
     */
    public static Map<String, String> getShippingPlan(String trxSrcTpCd, String trxHdrNum, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("trxSrcTpCd", trxSrcTpCd);
        ssmParam.put("trxHdrNum", trxHdrNum);
        // START 03/09/2020 T.Ogura [QC#56058,MOD]
//        ssmParam.put("shpgStsCd", SHPG_STS.CANCELLED);
        ssmParam.put("shpgStsCd", SHPG_STS.HARD_ALLOCATED);
        // END   03/09/2020 T.Ogura [QC#56058,MOD]

        S21SsmEZDResult result = NPAL1410Query.getInstance().getShippingPlan(ssmParam);

        Map<String, String> ret = new HashMap<String, String>();

        if (result.isCodeNormal()) {

            List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) result.getResultObject();

            for (Map<String, Object> resultMap : resultMapList) {

                ret.put((String) resultMap.get("TRX_LINE_NUM"), (String) resultMap.get("SHPG_PLN_NUM"));
            }
        }

        return ret;
    }

    /**
     * <pre>
     * getOrderType
     * </pre>
     * @param glblCmpyCd String
     * @return Map
     */
    public static Map<String, String> getOrderType(String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);

        S21SsmEZDResult result = NPAL1410Query.getInstance().getOrderType(ssmParam);

        Map<String, String> ret = new HashMap<String, String>();

        if (result.isCodeNormal()) {

            List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) result.getResultObject();

            for (Map<String, Object> resultMap : resultMapList) {

                ret.put((String) resultMap.get("RMNF_ORD_TP_CD"), (String) resultMap.get("RMNF_ORD_TP_DESC_TXT"));
            }
        }

        return ret;
    }

    /**
     * <pre>
     * getShippingPlanForParts
     * </pre>
     * @param rmnfOrdNum String
     * @param trxSrcTpCd String
     * @param glblCmpyCd String
     * @param createFlg String
     * @return Map<String, String>
     */
    public static Map<String, String> getShippingPlanForParts(String rmnfOrdNum, String trxSrcTpCd, String glblCmpyCd, String createFlg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rmnfOrdNum", rmnfOrdNum);
        ssmParam.put("trxSrcTpCd", trxSrcTpCd);
        ssmParam.put("rmnfPrtRqstProcCdInComp", PROC_STS.IN_COMPLETED);
        ssmParam.put("rmnfPrtRqstProcCdComp", PROC_STS.COMPLEATED);
        ssmParam.put("createFlg", createFlg);
        ssmParam.put("shpgStsCd", SHPG_STS.CANCELLED);

        S21SsmEZDResult result = NPAL1410Query.getInstance().getShippingPlanForParts(ssmParam);

        Map<String, String> ret = new HashMap<String, String>();

        if (result.isCodeNormal()) {

            List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) result.getResultObject();

            for (Map<String, Object> resultMap : resultMapList) {
                ret.put((String) resultMap.get("RMNF_PRT_REQ_LINE_NUM"), (String) resultMap.get("SHPG_PLN_NUM"));
            }
        }

        return ret;
    }

    /**
     * <pre>
     * getShippingPlanForSubmit
     * </pre>
     * @param trxHdrNum String
     * @param trxLineNum String
     * @param trxLineSubNum String
     * @param glblCmpyCd String
     * @return ShippingPlanForSubmit
     */
    public static List<String> getShippingPlanForPartsUsage(String trxHdrNum, String trxLineNum, String trxLineSubNum, String glblCmpyCd) {

        List<String> ret = new ArrayList<String>();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("trxSrcTpCd", TRX_SRC_TP.REMAN_PARTS_USAGE);
        ssmParam.put("trxHdrNum", trxHdrNum);
        ssmParam.put("trxLineNum", trxLineNum);
        ssmParam.put("trxLineSubNum", trxLineSubNum);

        S21SsmEZDResult result = NPAL1410Query.getInstance().getShippingPlanForSubmit(ssmParam);

        if (result.isCodeNormal()) {

            List<Map<String, Object>> resultMap = (List<Map<String, Object>>) result.getResultObject();

            for (int i = 0; i < resultMap.size(); i++) {

                ret.add((String) ((Map<String, Object>) resultMap.get(i)).get("SHPG_PLN_NUM"));
            }
        }

        return ret;
    }

    /**
     * <pre>
     * getAftDeclPnt
     * </pre>
     * @param glblCmpyCd String
     * @return AftDeclPnt
     */
    public static int getAftDeclPnt(String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);

        S21SsmEZDResult result = NPAL1410Query.getInstance().getAftDeclPnt(ssmParam);

        if (result.isCodeNormal()) {

            return (Integer) result.getResultObject();
        }

        return 0;
    }

    /**
     * <pre>
     * getAllTask
     * </pre>
     * @param rmnfOrdNum String
     * @param glblCmpyCd String
     * @return All task
     */
    public static S21SsmEZDResult getAllTask(String rmnfOrdNum, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rmnfOrdNum", rmnfOrdNum);
        ssmParam.put("rmnfTaskStsCd", RMNF_TASK_STS.OPEN);

        return NPAL1410Query.getInstance().getAllTask(ssmParam);
    }

    /**
     * <pre>
     * getAllUsage
     * </pre>
     * @param rmnfOrdNum String
     * @param rmnfTaskNum String
     * @param glblCmpyCd String
     * @return all users
     */
    public static S21SsmEZDResult getAllUsage(String rmnfOrdNum, String rmnfTaskNum, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rmnfOrdNum", rmnfOrdNum);
        ssmParam.put("rmnfTaskNum", rmnfTaskNum);

        return NPAL1410Query.getInstance().getAllUsage(ssmParam);
    }

    /**
     * <pre>
     * getPartsUsage
     * </pre>
     * @param rmnfOrdNum String
     * @param rtlWhCd String
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public static S21SsmEZDResult getPartsUsage(String rmnfOrdNum, String rtlWhCd, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rmnfOrdNum", rmnfOrdNum);
        ssmParam.put("rtlWhCd", rtlWhCd);

        return NPAL1410Query.getInstance().getPartsUsage(ssmParam);
    }

    /**
     * 
     * @param sMsg NPAL1410SMsg
     * @return String
     */
    public static String getInvtyLocCd(NPAL1410SMsg sMsg) {

        String whCd = "";
        String swhCd = "";

        if (ZYPCommonFunc.hasValue(sMsg.rmnfRtlWhCd_H1)) {
            whCd = sMsg.rmnfRtlWhCd_H1.getValue();
        }

        if (ZYPCommonFunc.hasValue(sMsg.rmnfRtlSwhCd_H1)) {
            swhCd = sMsg.rmnfRtlSwhCd_H1.getValue();
        }

        return whCd + swhCd;
    }

    /**
     * getRmnfTotCostAmt
     * @param tMsg RMNF_ORDTMsg
     * @return BigDecimal
     */
    public static BigDecimal getRmnfTotCostAmt(RMNF_ORDTMsg tMsg) {

        BigDecimal rmnfTotCostAmt = BigDecimal.ZERO;
        BigDecimal assetRecovCostAmt = BigDecimal.ZERO;

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", tMsg.glblCmpyCd.getValue());
        ssmParam.put("rmnfOrdNum", tMsg.rmnfOrdNum.getValue());

        S21SsmEZDResult resultMach = NPAL1410Query.getInstance().getAssetRecovCostAmt(ssmParam);

        if (resultMach.isCodeNormal()) {

            assetRecovCostAmt = (BigDecimal) resultMach.getResultObject();
        }

        rmnfTotCostAmt = tMsg.rmnfPrtUsgCostAmt.getValue().add(tMsg.rmnfTotLborCostAmt.getValue()).add(tMsg.rmnfMachCostAmt.getValue()).subtract(assetRecovCostAmt);

        return rmnfTotCostAmt;

    }

    /**
     * getRmnfToCmptMdseCd
     * @param tMsg RMNF_ORDTMsg
     * @return String
     */
    public static String getRmnfToCmptMdseCd(RMNF_ORDTMsg tMsg) {

        String rmnfToCmptMdseCd = "";

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", tMsg.glblCmpyCd.getValue());
        ssmParam.put("rmnfOrdNum", tMsg.rmnfOrdNum.getValue());

        S21SsmEZDResult resultMach = NPAL1410Query.getInstance().getRmnfToCmptMdseCd(ssmParam);

        if (resultMach.isCodeNormal()) {

            rmnfToCmptMdseCd = (String) resultMach.getResultObject();
        }

        return rmnfToCmptMdseCd;

    }

    /**
     * 
     * @param glblCmpyCd String
     * @param rmnfOrdNum String
     * @param lineNum String
     * @return BigDecimal
     */
    public static BigDecimal getRmnTotLborCostAmt(String glblCmpyCd, String rmnfOrdNum, String lineNum) {

        BigDecimal rmnfLborCostAmt = BigDecimal.ZERO;

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rmnfOrdNum", rmnfOrdNum);
        ssmParam.put("rmnfTaskNum", lineNum);
        // QC#15221
        ssmParam.put("cancel", RMNF_TASK_STS.CANCEL);

        S21SsmEZDResult resultMach = NPAL1410Query.getInstance().getRmnTotLborCostAmt(ssmParam);

        if (resultMach.isCodeNormal()) {

            rmnfLborCostAmt = (BigDecimal) resultMach.getResultObject();
        }

        return rmnfLborCostAmt;

    }

    /**
     * 
     * @param glblCmpyCd String
     * @param rmnfOrdNum String
     * @param lineNum String
     * @return BigDecimal
     */
    public static BigDecimal getRmnTotLborCostAmtBeforeComp(String glblCmpyCd, String rmnfOrdNum, String lineNum) {

        BigDecimal rmnfLborCostAmt = BigDecimal.ZERO;

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rmnfOrdNum", rmnfOrdNum);
        ssmParam.put("rmnfTaskNum", lineNum);
        // QC#15221
        ssmParam.put("cancel", RMNF_TASK_STS.CANCEL);

        S21SsmEZDResult resultMach = NPAL1410Query.getInstance().getRmnTotLborCostAmtBeforeComp(ssmParam);

        if (resultMach.isCodeNormal()) {

            rmnfLborCostAmt = (BigDecimal) resultMach.getResultObject();
        }

        return rmnfLborCostAmt;

    }

    /**
     * 
     * @param glblCmpyCd String
     * @param rmnfOrdNum String
     * @param lineNum String
     * @return BigDecimal
     */
    public static BigDecimal getRmnfPrtUsgCostAmt(String glblCmpyCd, String rmnfOrdNum, String lineNum) {

        BigDecimal rmnfPrtUsgCostAmt = BigDecimal.ZERO;

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rmnfOrdNum", rmnfOrdNum);
        ssmParam.put("rmnfTaskNum", lineNum);

        S21SsmEZDResult resultMach = NPAL1410Query.getInstance().getRmnfPrtUsgCostAmt(ssmParam);

        if (resultMach.isCodeNormal()) {

            rmnfPrtUsgCostAmt = (BigDecimal) resultMach.getResultObject();
        }

        return rmnfPrtUsgCostAmt;

    }

    /**
     * getRmnfTotCostAmtBeforeComp
     * @param glblCmpyCd glblCmpyCd
     * @param sMsg sMsg
     * @return BigDecimal
     */
    public static BigDecimal getRmnfTotCostAmtBeforeComp(String glblCmpyCd, NPAL1410SMsg sMsg) {

        BigDecimal rmnfTotCostAmt = BigDecimal.ZERO;
        BigDecimal assetRecovCostAmt = BigDecimal.ZERO;

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rmnfOrdNum", sMsg.rmnfOrdNum_H1.getValue());

        S21SsmEZDResult resultMach = NPAL1410Query.getInstance().getAssetRecovCostAmt(ssmParam);

        if (resultMach.isCodeNormal()) {

            assetRecovCostAmt = (BigDecimal) resultMach.getResultObject();
        }

        rmnfTotCostAmt = sMsg.rmnfPrtUsgCostAmt_H1.getValue().add(sMsg.rmnfTotLborCostAmt_H1.getValue()).add(sMsg.rmnfMachCostAmt_H1.getValue()).subtract(assetRecovCostAmt);

        return rmnfTotCostAmt;

    }

    /**
     * <pre>
     * getPartsUsage
     * </pre>
     * @param rmnfOrdNum String
     * @param glblCmpyCd String
     * @return RemanParts
     */
    public static S21SsmEZDResult getRemanParts(String rmnfOrdNum, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rmnfOrdNum", rmnfOrdNum);
        ssmParam.put("notComp", ZYPConstant.FLG_OFF_0);
        ssmParam.put("completed", ZYPConstant.FLG_ON_1);
        ssmParam.put("locStsCd", LOC_STS.DC_STOCK);
        ssmParam.put("stkStsCd", STK_STS.GOOD);

        return NPAL1410Query.getInstance().getRemanParts(ssmParam);
    }

    /**
     * <pre>
     * getPartsUsage
     * </pre>
     * @param rmnfOrdNum String
     * @param rmnfPrtReqLineNum String
     * @param glblCmpyCd String
     * @return Reman ASL
     */
    public static S21SsmEZDResult getRemanASL(String rmnfOrdNum, String rmnfPrtReqLineNum, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rmnfOrdNum", rmnfOrdNum);
        ssmParam.put("rmnfPrtReqLineNum", rmnfPrtReqLineNum);

        return NPAL1410Query.getInstance().getRemanASL(ssmParam);
    }

    /**
     * <pre>
     * getSMsgIndexFromCMsg
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param index int
     * @return on SMsg index
     */
    public static int getSMsgIndexFromCMsg(NPAL1410CMsg cMsg, int index) {

        return cMsg.xxPageShowFromNum.getValueInt() + index - 1;
    }

    /**
     * getWmsAdjInfo
     * @param rmnfOrdNum rmnfOrdNum
     * @param glblCmpyCd glblCmpyCd
     * @return S21SsmEZDResult
     */
    public static S21SsmEZDResult getWmsAdjInfo(String rmnfOrdNum, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rmnfOrdNum", rmnfOrdNum);

        return NPAL1410Query.getInstance().getWmsAdjInfo(ssmParam);
    }

    /**
     * <pre>
     * searchSerial
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param glblCmpyCd glblCmpyCd String
     * @return serial
     */
    public static S21SsmEZDResult searchSerial(NPAL1410CMsg cMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("serNum", cMsg.rmnfMainUnitSerNum_H1);
        // QC#30851
        // popup event
        if("CONF_POPUP_H".equals(cMsg.eventNm.getValue()) && ZYPCommonFunc.hasValue(cMsg.svcConfigMstrPk_H1)) {
            ssmParam.put("svcConfigMstrPk", cMsg.svcConfigMstrPk_H1);
            ssmParam.put("mdseCd", cMsg.mdseCd_H1);
        } else if ("CONF_POPUP_H".equals(cMsg.eventNm.getValue()) && !ZYPCommonFunc.hasValue(cMsg.svcConfigMstrPk_H1)) {
            ssmParam.put("popUpFlg", ZYPConstant.FLG_ON_Y);
            ssmParam.put("mdseCd", cMsg.mdseCd_H1);
        // getConfig event
        } else if (ZYPCommonFunc.hasValue(cMsg.rmnfMainUnitMdseCd_HH)) {
            ssmParam.put("mdseCd", cMsg.rmnfMainUnitMdseCd_HH);
        }

        return NPAL1410Query.getInstance().searchSerial(ssmParam);
    }

    /**
     * <pre>
     * searchConfigration
     * </pre>
     * @param svcConfigMstrPk BigDecimal
     * @param glblCmpyCd glblCmpyCd String
     * @return configration
     */
    public static S21SsmEZDResult searchConfigration(BigDecimal svcConfigMstrPk, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcConfigMstrPk", svcConfigMstrPk);

        return NPAL1410Query.getInstance().searchConfigration(ssmParam);
    }

    /**
     * <pre>
     * searchConfigration
     * </pre>
     * @param mdseCd String
     * @param glblCmpyCd glblCmpyCd String
     * @return model
     */
    public static MDL_NMTMsg searchModel(String mdseCd, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prntMdseCd", mdseCd);
        S21SsmEZDResult result = NPAL1410Query.getInstance().searchModel(ssmParam);

        // QC#21805 Start
        MDL_NMTMsg ret = null;
        // QC#21805 End

        if (result.isCodeNormal()) {

            ret = new MDL_NMTMsg();
            List<Map<String, Object>> resultMap = (List<Map<String, Object>>) result.getResultObject();

            if (0 < resultMap.size()) {

                Map<String, Object> recode = (Map<String, Object>) resultMap.get(0);
                ZYPEZDItemValueSetter.setValue(ret.t_MdlId, (BigDecimal) recode.get("T_MDL_ID"));
                ZYPEZDItemValueSetter.setValue(ret.t_MdlNm, (String) recode.get("T_MDL_NM"));
            }
        }

        return ret;
    }

    /**
     * 
     * @param sMsg sMsg
     * @param glblCmpyCd glblCmpyCd glblCmpyCd
     * @return S21SsmEZDResult
     */
    public static S21SsmEZDResult getStarndardParts(NPAL1410SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rmnfMdlId", sMsg.rmnfMdlId_HH);
        ssmParam.put("techTocCd", sMsg.ownrTechTocCd_H1);
        ssmParam.put("rtlWhCatgCd", RTL_WH_CATG.TECH_CAR_STOCK);
        ssmParam.put("locStsCd", LOC_STS.DC_STOCK);
        ssmParam.put("stkStsCd", STK_STS.GOOD);
        ssmParam.put("procrTpCd", PROCR_TP.WAREHOUSE);
        ssmParam.put("rtlWhCd", sMsg.rmnfRtlWhCd_H1);

        return NPAL1410Query.getInstance().getStarndardParts(ssmParam);
    }

    /**
     * <pre>
     * checkGetConfigAndGetSerial
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     * @param glblCmpyCd glblCmpyCd String
     * @return serial
     */
    public static Map<String, Object> checkGetConfigAndGetSerial(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg, String glblCmpyCd) {

        if (!ZYPCommonFunc.hasValue(sMsg.rmnfMainUnitSerNum_H1)) {

            cMsg.rmnfMainUnitSerNum_H1.setErrorInfo(1, NPAM1378E);
            return null;
        }

        if (!sMsg.rmnfMainUnitSerNum_H1.getValue().equals(sMsg.rmnfMainUnitSerNum_HH.getValue())) {
            cMsg.rmnfMainUnitMdseCd_HH.clear();
        }
        
        S21SsmEZDResult resultSerial = searchSerial(cMsg, glblCmpyCd);

        if (!resultSerial.isCodeNormal()) {

            cMsg.rmnfMainUnitSerNum_H1.setErrorInfo(1, NPAM0224E, new String[] {"Serial#" });
            return null;
        }

        List<Map<String, Object>> resultSerialMap = (List<Map<String, Object>>) resultSerial.getResultObject();
        // QC#30851
        if ((1 < resultSerialMap.size())) {
            cMsg.rmnfMainUnitSerNum_H1.setErrorInfo(1, NPAM1589E, new String[] {"Main Unit Serial#"});
            return null;
        }

        Map<String, Object> recodeSerial = (Map<String, Object>) resultSerialMap.get(0);

        // QC#14011
        if (ZYPCommonFunc.hasValue(cMsg.rmnfRtlWhCd_H1)) {
            if (!cMsg.rmnfRtlWhCd_H1.getValue().equals((String) recodeSerial.get("RTL_WH_CD"))) {
                cMsg.rmnfMainUnitSerNum_H1.setErrorInfo(1, NPAM1586E, new String[] {"Serial#", "Reman Warehouse#" });
                cMsg.rmnfRtlWhCd_H1.setErrorInfo(1, NPAM1586E, new String[] {"Serial#", "Reman Warehouse#" });
                return null;
            }
        }

        if (ZYPCommonFunc.hasValue((BigDecimal) recodeSerial.get("SVC_CONFIG_MSTR_PK"))) {

            if (!SVC_MACH_TP.MACHINE.equals((String) recodeSerial.get("SVC_MACH_TP_CD"))) {

                cMsg.rmnfMainUnitSerNum_H1.setErrorInfo(1, NPAM1493E);
                return null;
            }
        }

        return recodeSerial;
    }

    /**
     * <pre>
     * checkGetConfigAndGetSerial
     * </pre>
     * @param sMsg NPAL1410SMsg
     * @param index int
     * @param recode Map<String, Object>
     * @param isGetConfig boolean
     */
    public static void setConfigDtl(NPAL1410SMsg sMsg, int index, Map<String, Object> recode, boolean isGetConfig) {

        if (sMsg.A.length() <= index) {

            return;
        }

        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).mdseCd_A1, (String) recode.get("CMPT_MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).mdseDescShortTxt_A1, (String) recode.get("MDSE_DESC_SHORT_TXT1"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).serNum_A1, (String) recode.get("CMPT_SER_NUM"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).mdseDescShortTxt_A2, (String) recode.get("MDSE_DESC_SHORT_TXT2"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).svcMachMstrPk_AH, (BigDecimal) recode.get("SVC_MACH_MSTR_PK"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).svcMachQty_AH, (BigDecimal) recode.get("SVC_MACH_QTY"));
        // QC#22836
        if (ZYPCommonFunc.hasValue((String) recode.get("SRC_RTL_SWH_CD"))) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).srcRtlSwhCd_A1, (String) recode.get("SRC_RTL_SWH_CD"));
        } else {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).srcRtlSwhCd_A1, (String) recode.get("RTL_SWH_CD"));
        }
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).stkStsCd_A1, (String) recode.get("STK_STS_CD"));

        if (SVC_MACH_TP.MACHINE.equals((String) recode.get("SVC_MACH_TP_CD"))) {

            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).mainUnitLineFlg_A1, ZYPConstant.FLG_ON_Y);

        } else {

            sMsg.A.no(index).mainUnitLineFlg_A1.clear();
        }

        if (isGetConfig) {

            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rmvConfigFlg_A1, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rmnfToCmptMdseCd_A1, (String) recode.get("CMPT_MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rmnfToCmptSerNum_A1, (String) recode.get("CMPT_SER_NUM"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).addConfigFlg_A1, ZYPConstant.FLG_OFF_N);

        } else {

            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rmvConfigFlg_A1, (String) recode.get("RMV_CONFIG_FLG"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rmnfToCmptMdseCd_A1, (String) recode.get("RMNF_TO_CMPT_MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rmnfToCmptSerNum_A1, (String) recode.get("RMNF_TO_CMPT_SER_NUM"));

            if (ZYPConstant.FLG_ON_Y.equals((String) recode.get("ADD_CONFIG_FLG"))) {

                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).addConfigFlg_A1, ZYPConstant.FLG_ON_Y);
                // QC#22178 Add Non Serial Item. and non svc_mach_mstr.
                if(!ZYPCommonFunc.hasValue(sMsg.A.no(index).svcMachMstrPk_AH)){
                    // set Default
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).svcMachQty_AH, BigDecimal.ONE);
                }

            } else {

                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).addConfigFlg_A1, ZYPConstant.FLG_OFF_N);
            }
        }

        sMsg.A.setValidCount(index + 1);
    }

    /**
     * <pre>
     * getSerial
     * </pre>
     * @param serNum String
     * @param mdseCd String
     * @param glblCmpyCd glblCmpyCd String
     * @return serial
     */
    public static Map<String, Object> getSerial(String serNum, String mdseCd, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("serNum", serNum);
        S21SsmEZDResult result = NPAL1410Query.getInstance().getSerial(ssmParam);

        if (result.isCodeNormal()) {

            return (Map<String, Object>) result.getResultObject();
        }

        return null;
    }

    /**
     * chkApiRslt
     * @param cMsg NPAL1410CMsg
     * @param msgIdList List<String>
     * @return boolean true : OK, false : NG
     */
    public static boolean chkApiRslt(NPAL1410CMsg cMsg, List<String> msgIdList) {

        if (!msgIdList.isEmpty()) {

            for (String msgId : msgIdList) {

                if (ZYPCommonFunc.hasValue(msgId)) {

                    cMsg.setMessageInfo(msgId);

                    if (msgId.endsWith("E")) {

                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * createAdjIf
     * @param cMsg cMsg
     * @param prtInfoList prtInfoList
     * @return boolean
     */
    public static boolean createAdjIf(NPAL1410CMsg cMsg, List<Map<String, Object>> prtInfoList) {

        BigDecimal tranId = null;
        int seqNumber = 1;
        NLGI5100_01TMsg tMsg = new NLGI5100_01TMsg();
        S21TransactionTableAccessor trxAccess = new S21TransactionTableAccessor();

        List<Map<String, Object>> transList = new ArrayList<Map<String, Object>>();

        String curIfId = "";
        for (Map<String, Object> prtInfo : prtInfoList) {

        	String interfaceId = (String) prtInfo.get("WMS_ADJ_DNLD_INTFC_ID");

            if (!ZYPCommonFunc.hasValue(interfaceId)) {
                continue;
            }

            if (!ZYPCommonFunc.hasValue(tranId) || !interfaceId.equals(curIfId)) {
                tranId = new S21TransactionTableAccessor().getNextTransactionId();
                curIfId = interfaceId;
                Map<String, Object> tran = new HashMap<String, Object>();
                tran.put("TR", tranId);
                tran.put("IF", interfaceId);
                transList.add(tran);
            }

            ZYPEZDItemValueSetter.setValue(tMsg.interfaceId, interfaceId);
            ZYPEZDItemValueSetter.setValue(tMsg.transactionId, tranId);
            ZYPEZDItemValueSetter.setValue(tMsg.segmentId, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(tMsg.unitId, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(tMsg.seqNumber, new BigDecimal(seqNumber));

            // WMS_WH_CD
            ZYPEZDItemValueSetter.setValue(tMsg.wmsWhCd, (String) prtInfo.get("WMS_WH_CD"));
            // STAGE_ACT_CD
            ZYPEZDItemValueSetter.setValue(tMsg.stageActCd, "1");
            // STAGE_REC_STS_CD
            ZYPEZDItemValueSetter.setValue(tMsg.stageRecStsCd, "2");
            // ITEM_CD_TXT
            ZYPEZDItemValueSetter.setValue(tMsg.itemCdTxt, (String) prtInfo.get("MDSE_CD"));
            // PACK_CD_TXT
            ZYPEZDItemValueSetter.setValue(tMsg.packCdTxt, (String) prtInfo.get("PACK_CD_TXT"));
            // HLD_CD_TXT
            ZYPEZDItemValueSetter.setValue(tMsg.hldCdTxt, STK_STS.GOOD);
            // QTY_ORD_TXT
            BigDecimal qtyOrdTxt = (BigDecimal) prtInfo.get("RMNF_PRT_QTY");

            if (ZYPCommonFunc.hasValue(qtyOrdTxt)) {
                // QC#15449
                String strQtyOrdTxt = qtyOrdTxt.multiply(new BigDecimal("-1")).toPlainString();
                ZYPEZDItemValueSetter.setValue(tMsg.qtyOrdTxt, strQtyOrdTxt);
            }
            // WMS_LOC_CD
            ZYPEZDItemValueSetter.setValue(tMsg.wmsLocCd, (String) prtInfo.get("WH_LOCTR_CD"));
            // ADJ_SRC_ORD_NUM
            ZYPEZDItemValueSetter.setValue(tMsg.adjSrcOrdNum, (String) prtInfo.get("RMNF_ORD_NUM"));
            // SER_NUM(Set Null)

            EZDTBLAccessor.insert(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                cMsg.setMessageInfo(NPAM1172E, new String[] {"NLGI5100_01" });
                return false;
            }
            seqNumber++;
        }
        // QC#21842
        String ifId = null;
        BigDecimal trxId = null;
        for (Map<String, Object> trans : transList) {
        	ifId = (String) trans.get("IF");
        	trxId = (BigDecimal) trans.get("TR");
        	trxAccess.createIntegrationRecordForBatch(ifId, trxId);
        }

        return true;
    }
    // QC#13875
    /**
     * getMaxPrtReqLineNum
     * @param rmnfOrdNum rmnfOrdNum
     * @param glblCmpyCd glblCmpyCd glblCmpyCd
     * @return int
     */
    public static int getMaxPrtReqLineNum(String rmnfOrdNum, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rmnfOrdNum", rmnfOrdNum);

        S21SsmEZDResult result = NPAL1410Query.getInstance().getMaxPrtReqLineNum(ssmParam);

        if (!result.isCodeNormal() || !(ZYPCommonFunc.hasValue((BigDecimal) result.getResultObject()))) {
            return 0;
        } else {
            BigDecimal ret = (BigDecimal) result.getResultObject();
            return ret.intValue();
        }
    }

    /**
     * isOpenedSoStdParts
     * @param rmnfOrdNum rmnfOrdNum
     * @param mdseCd mdseCd
     * @param glblCmpyCd glblCmpyCd glblCmpyCd
     * @return boolean
     */
    public static boolean isOpenedSoStdParts(String rmnfOrdNum, String mdseCd, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rmnfOrdNum", rmnfOrdNum);
        ssmParam.put("mdseCd", mdseCd);

        S21SsmEZDResult result = NPAL1410Query.getInstance().isOpenedSoStdParts(ssmParam);

        if (!result.isCodeNormal() || (Integer) result.getResultObject() == 0) {
            return false;
        }

        return true;
    }

    /**
     * isSerialTakeItem
     * @param glblCmpyCd
     * @param mdseCd
     * @return true(Serial Item) / false (Non Serial Item)
     */
    public static boolean isSerialTakeItem(String glblCmpyCd, String mdseCd) {

        MDSETMsg mdse = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdse.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdse.mdseCd, mdseCd);
        mdse = (MDSETMsg) EZDTBLAccessor.findByKey(mdse);

        if (mdse != null //
                && (ZYPConstant.FLG_ON_Y.equals(mdse.rcvSerTakeFlg.getValue()) //
                || ZYPConstant.FLG_ON_Y.equals(mdse.shpgSerTakeFlg.getValue()))) {
            return true;
        }

        return false;
    }

    /**
     * Add QC#22836
     * Create Pulldown Stock Status
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownStockStatus(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg, String glblCmpyCd) {

        // Clear Pulldown Data
        cMsg.stkStsCd_PD.clear();
        cMsg.stkStsDescTxt_PD.clear();

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);

        // Execute
        S21SsmEZDResult result = NPAL1410Query.getInstance().getStockStatusPulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map<String, String>> stkStsList = (List<Map<String, String>>) result.getResultObject();

            for (int i = 0; i < stkStsList.size(); i++) {
                Map<String, String> record = stkStsList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.stkStsCd_PD.no(i), (String) record.get("STK_STS_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.stkStsDescTxt_PD.no(i), (String) record.get("STK_STS_DESC_TXT"));
            }
        }
    }
}
