/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0440.common;

import static business.blap.NSBL0440.constant.NSBL0440Constant.FIELD_SVC_MOD_OPT_CD;
import static business.blap.NSBL0440.constant.NSBL0440Constant.FIELD_SVC_MOD_OPT_DT;
import static business.blap.NSBL0440.constant.NSBL0440Constant.NSAM0189E;
import static business.blap.NSBL0440.constant.NSBL0440Constant.NSAM0197E;
import static business.blap.NSBL0440.constant.NSBL0440Constant.NZZM0000E;
import static business.blap.NSBL0440.constant.NSBL0440Constant.NZZM0001W;
import static business.blap.NSBL0440.constant.NSBL0440Constant.SLS_DT;
import static business.blap.NSBL0440.constant.NSBL0440Constant.SCREEN_ID;
import static business.blap.NSBL0440.constant.NSBL0440Constant.LIMIT_DL_ROWNUM;
import static business.blap.NSBL0440.constant.NSBL0440Constant.COL_NM;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.common.NSX.NSXC002001.NSXC002001SerialRangeCheck;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MOD_OPT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MOD_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;


import parts.common.EZDMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NSBL0440.NSBL0440CMsg;
import business.blap.NSBL0440.NSBL0440Query;
import business.blap.NSBL0440.NSBL0440SMsg;
import business.file.NSBL0440F00FMsg;

/**
 *<pre>
 * Mods Machine Level Status
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Hitachi         O.Okuma         Create          N/A
 * 2016/07/19   Hitachi         O.Okuma         Update          QC#11647
 * 2018/02/02   Hitachi         M.Kidokoro      Update          QC#18150
 * 2018/06/01   Hitachi         U.Kim           Update          QC#22393
 * 2022/09/08   CITS            E.Sanchez       Update          QC#60527
 *</pre>
 */
public class NSBL0440CommonLogic {

    /**
     * Clear Message
     * @param cMsg NSBL0440CMsg
     * @param sMsg NSBL0440SMsg
     */
    public static void clearMsg(NSBL0440CMsg cMsg, NSBL0440SMsg sMsg) {
        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
    }

    /**
     * Create Pull Down
     * @param cMsg NSBL0440CMsg
     */
    public static void createPullDown(NSBL0440CMsg cMsg) {

        ZYPCodeDataUtil.createPulldownList(SVC_MOD_OPT.class, cMsg.svcModOptCd_AO, cMsg.svcModOptDescTxt_AO);
        // START 2018/06/01 U.Kim [QC#, ADD]
        ZYPCodeDataUtil.createPulldownList(SVC_MACH_MSTR_STS.class, cMsg.svcMachMstrStsCd_01, cMsg.svcMachMstrStsDescTxt_01);
        ZYPCodeDataUtil.createPulldownList(SVC_MOD_PROC_STS.class, cMsg.svcModProcStsCd_01, cMsg.svcModProcStsDescTxt_01);
        // END 2018/06/01 U.Kim [QC#, ADD]
    }

    /**
     * Set Initialize Parameters
     * @param cMsg NSBL0440CMsg
     * @param sMsg NSBL0440SMsg
     */
    public static void setInitParams(NSBL0440CMsg cMsg, NSBL0440SMsg sMsg) {
    }

    /**
     * setPagenation <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NSBL0440CMsg
     * @param sMsg NSBL0440SMsg
     * @param pagenationFrom int
     */
    public static void setPagenation(NSBL0440CMsg cMsg, NSBL0440SMsg sMsg, int pagenationFrom) {

        int cnt = pagenationFrom;
        for (; cnt < pagenationFrom + cMsg.A.length(); cnt++) {
            if (cnt < pagenationFrom + cMsg.A.getValidCount()) {
                EZDMsg.copy(cMsg.A.get(cnt - pagenationFrom), null, sMsg.A.get(cnt), null);
            } else {
                break;
            }
        }
    }

    /**
     * Paginate to item
     * @param cMsg NSBL0440CMsg
     * @param sMsg NSBL0440SMsg
     * @param itemIndex int
     */
    public static void pagenation(NSBL0440CMsg cMsg, NSBL0440SMsg sMsg, int itemIndex) {

        int startIndex = (itemIndex / cMsg.A.length()) * cMsg.A.length();
        int num = 0;
        ZYPTableUtil.clear(cMsg.A);
        for (int i = startIndex; i < sMsg.A.getValidCount(); i++) {
            if (num >= cMsg.A.length()) {
                break;
            }
            EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(num), null);
            num++;
        }
        cMsg.A.setValidCount(num);
        setValue(cMsg.xxPageShowFromNum, BigDecimal.valueOf(startIndex + 1));
        setValue(cMsg.xxPageShowToNum, BigDecimal.valueOf(startIndex + cMsg.A.getValidCount()));
        setValue(cMsg.xxPageShowOfNum, BigDecimal.valueOf(sMsg.A.getValidCount()));
        setValue(cMsg.xxPageShowCurNum, BigDecimal.valueOf(startIndex + 1).divide(BigDecimal.valueOf(cMsg.A.length()), 0, BigDecimal.ROUND_UP));
        setValue(cMsg.xxPageShowTotNum, BigDecimal.valueOf(sMsg.A.getValidCount()).divide(BigDecimal.valueOf(cMsg.A.length()), 0, BigDecimal.ROUND_UP));

    }

    /**
     * convertPageNumToFirstRowIndex
     * @param cMsg NSBL0440CMsg
     * @return int
     */
    public static int convertPageNumToFirstRowIndex(NSBL0440CMsg cMsg) {
        if (cMsg.xxPageShowCurNum.getValueInt() <= 0) {
            return 0;
        }
        if (cMsg.A.length() * (cMsg.xxPageShowCurNum.getValueInt() - 1) > (cMsg.xxPageShowOfNum.getValueInt())) {
            return cMsg.xxPageShowCurNum.getValueInt() - 1;
        }
        return cMsg.A.length() * (cMsg.xxPageShowCurNum.getValueInt() - 1);
    }

    /**
     * checkData
     * @param sMsg NSBL0440SMsg
     * @return boolean
     */
    public static boolean checkData(NSBL0440SMsg sMsg) {

        boolean isError = true;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) {

                // mod start 2016/07/19 CSA Defect#11647
                if (hasValue(sMsg.A.no(i).svcModOptCd_A) && !hasValue(sMsg.A.no(i).svcModOptDt_A)) {
                    sMsg.A.no(i).svcModOptCd_A.setErrorInfo(1, NSAM0189E, new String[] {FIELD_SVC_MOD_OPT_CD, FIELD_SVC_MOD_OPT_DT });
                    sMsg.A.no(i).svcModOptDt_A.setErrorInfo(1, NSAM0189E, new String[] {FIELD_SVC_MOD_OPT_CD, FIELD_SVC_MOD_OPT_DT });
                    isError = false;
                }
                if (hasValue(sMsg.A.no(i).svcModOptDt_A)) {
                    if (!hasValue(sMsg.A.no(i).svcModOptCd_A)) {
                        sMsg.A.no(i).svcModOptCd_A.setErrorInfo(1, NSAM0189E, new String[] {FIELD_SVC_MOD_OPT_DT, FIELD_SVC_MOD_OPT_CD });
                        sMsg.A.no(i).svcModOptDt_A.setErrorInfo(1, NSAM0189E, new String[] {FIELD_SVC_MOD_OPT_DT, FIELD_SVC_MOD_OPT_CD });
                        isError = false;
                    } else if (ZYPDateUtil.isPastDate(sMsg.A.no(i).svcModOptDt_A.getValue())) {
                        sMsg.A.no(i).svcModOptCd_A.setErrorInfo(1, NSAM0197E, new String[] {FIELD_SVC_MOD_OPT_DT, SLS_DT });
                        sMsg.A.no(i).svcModOptDt_A.setErrorInfo(1, NSAM0197E, new String[] {FIELD_SVC_MOD_OPT_DT, SLS_DT });
                        isError = false;
                    }
                }
                // mod end 2016/07/19 CSA Defect#11647
            }
        }
        return isError;
    }

    /**
     * getErrPageFromNum
     * @param cMsg NSBL0440CMsg
     * @param sMsg NSBL0440SMsg
     * @return int
     */
    public static int getErrPageFromNum(NSBL0440CMsg cMsg, NSBL0440SMsg sMsg) {

        int errIndex = 0;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            // Error
            if (sMsg.A.no(i).svcModOptCd_A.isError()) {
                errIndex = i;
                break;
            }
        }

        return errIndex / cMsg.A.length() * cMsg.A.length();
    }

    // START 2018/02/02 M.Kidokoro [QC#18150, ADD]
    /**
     * writeCsvFile
     * @param cMsg NSBL0440CMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    public static void writeCsvFile(NSBL0440CMsg cMsg, ResultSet rs) throws SQLException {

        NSBL0440F00FMsg fMsg = new NSBL0440F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(cMsg));

        // write header
        writeCsvFileHeader(csvOutFile, fMsg, cMsg);

        if (!rs.next()) {
            cMsg.setMessageInfo(NZZM0000E.toString(), null);
            csvOutFile.close();
            return;
        }

        // START 2022/09/08 E.Sanchez [QC#60527, ADD]
        List<String> procSerNum = new ArrayList<String>();
        List<Map<String, Object>> serNumRangesList = NSBL0440Query.getInstance().getSvcModSerRng(cMsg.glblCmpyCd.getValue(), cMsg.svcModPk.getValue());
        // END 2022/09/08 E.Sanchez [QC#60527, ADD]
        // write contents
        do {
            // resultSet -> fMsg
            // START 2022/09/08 E.Sanchez [QC#60527, MOD]
            BigDecimal svcModDtlPk = rs.getBigDecimal(COL_NM.SVC_MOD_DTL_PK.toString());
            String serNum = rs.getString(COL_NM.SER_NUM.toString());

            if (!isWithinRange(serNum, svcModDtlPk, serNumRangesList)) {
                continue;
            }

            procSerNum.add(serNum);
            // setValue(fMsg.serNum_A, rs.getString(COL_NM.SER_NUM.toString()));
            setValue(fMsg.serNum_A, serNum);
            // END 2022/09/08 E.Sanchez [QC#60527, MOD]
            setValue(fMsg.svcModPlnId_A, rs.getString(COL_NM.SVC_MOD_PLN_ID.toString()));
            setValue(fMsg.t_MdlNm_A, rs.getString(COL_NM.T_MDL_NM.toString()));
            setValue(fMsg.svcModProcStsDescTxt_A, rs.getString(COL_NM.SVC_MOD_PROC_STS_DESC_TXT.toString()));
            setValue(fMsg.fldSvcBrCd_A, rs.getString(COL_NM.FLD_SVC_BR_CD.toString()));
            setValue(fMsg.svcMnfModNum_A, rs.getString(COL_NM.SVC_MNF_MOD_NUM.toString()));
            setValue(fMsg.techNm_A, rs.getString(COL_NM.TECH_NM.toString()));
            setValue(fMsg.svcTaskNum_A, rs.getString(COL_NM.SVC_TASK_NUM.toString()));
            setValue(fMsg.xxDtTxt_A, convertDateFormat(rs.getString(COL_NM.SVC_TASK_CLO_DT.toString())));
            csvOutFile.write();
        } while (rs.next());

        // START 2022/09/08 E.Sanchez [QC#60527, ADD]
        if (procSerNum.size() == 0) {
            cMsg.setMessageInfo(NZZM0000E.toString(), null);
        }
        // END 2022/09/08 E.Sanchez [QC#60527, ADD]

        csvOutFile.close();
    }

    private static void writeCsvFileHeader(ZYPCSVOutFile csvOutFile, NSBL0440F00FMsg fMsg, NSBL0440CMsg cMsg) {
        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
        final String[] csvHeader = new String[] {
                labelConv.convLabel2i18nLabel(SCREEN_ID, "Serial#")
                , labelConv.convLabel2i18nLabel(SCREEN_ID, "Mod Plan ID")
                , labelConv.convLabel2i18nLabel(SCREEN_ID, "Model Name")
                , labelConv.convLabel2i18nLabel(SCREEN_ID, "Status(Open/Close)")
                , labelConv.convLabel2i18nLabel(SCREEN_ID, "Service Branch(Code)")
                , labelConv.convLabel2i18nLabel(SCREEN_ID, "Manufacture Mod#")
                , labelConv.convLabel2i18nLabel(SCREEN_ID, "Technician Name")
                , labelConv.convLabel2i18nLabel(SCREEN_ID, "Task#")
                , labelConv.convLabel2i18nLabel(SCREEN_ID, "Completion Date")
        };
        csvOutFile.writeHeader(csvHeader, fMsg, ZYPGUITableColumn.getColOrder(cMsg));
    }

    private static String convertDateFormat(String date) {
        if (hasValue(date)) {
            date = ZYPDateUtil.formatEzd8ToDisp(date);
        }
        return date;
    }
    // END 2018/02/02 M.Kidokoro [QC#18150, ADD]

    // START 2022/09/08 E.Sanchez [QC#60527, ADD]
    /**
     * isWithinRange
     * @param serNum String
     * @param svcModDtlPk BigDecimal
     * @param serNumRangesList List<Map<String, Object>>
     * @return boolean
     */
    public static boolean isWithinRange(String serNum, BigDecimal svcModDtlPk, List<Map<String, Object>> serNumRangesList) {
        boolean isWithinRange = false;
        BigDecimal lgSerNum = new BigDecimal(serNum.length());
        for (int j = 0; j < serNumRangesList.size(); j++) {
            Map<String, Object> serNumRange = serNumRangesList.get(j);
            String fromSerNumRange = (String) serNumRange.get("SVC_MOD_FROM_SER_NUM");
            String toSerNumRange = (String) serNumRange.get("SVC_MOD_TO_SER_NUM");
            BigDecimal lgSerNumRange = (BigDecimal) serNumRange.get("LG_SER_NUM");
            BigDecimal svcModDtlPkSerNumRange = (BigDecimal) serNumRange.get("SVC_MOD_DTL_PK");
            if (lgSerNum.compareTo(lgSerNumRange) != 0 || svcModDtlPk.compareTo(svcModDtlPkSerNumRange) != 0) {
                continue;
            }
            if (NSXC002001SerialRangeCheck.isCheckSerialNum(fromSerNumRange, toSerNumRange, serNum)) {
                isWithinRange = true;
                break;
            }
        }
        return isWithinRange;
    }
    // END 2022/09/08 E.Sanchez [QC#60527, ADD]
}
