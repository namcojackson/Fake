package business.blap.NWAL2320.common;

import static business.blap.NWAL2320.constant.NWAL2320Constant.TMPL_DL_HEADER_FOR_EXS_ORDER;
import static business.blap.NWAL2320.constant.NWAL2320Constant.TMPL_DL_HEADER_FOR_EXS_RMA;
import static business.blap.NWAL2320.constant.NWAL2320Constant.TMPL_DL_HEADER_FOR_NEW_ORDER;
import static business.blap.NWAL2320.constant.NWAL2320Constant.TMPL_DL_HEADER_FOR_NEW_RMA;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDPMsg;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSItem;
import parts.common.EZDSStringItem;
import parts.common.EZDTBigDecimalItem;
import parts.common.EZDTStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL2320.NWAL2320CMsg;
import business.blap.NWAL2320.NWAL2320Query;
import business.blap.NWAL2320.NWAL2320SMsg;
import business.blap.NWAL2320.NWAL2320_ASMsg;
import business.blap.NWAL2320.NWAL2320_BSMsg;
import business.blap.NWAL2320.NWAL2320_CSMsg;
import business.blap.NWAL2320.NWAL2320_DSMsg;
import business.blap.NWAL2320.bean.INWAL2320_ImptBean;
import business.blap.NWAL2320.bean.NWAL2320_ImptConfigBean;
import business.blap.NWAL2320.bean.NWAL2320_ImptDetailBean;
import business.blap.NWAL2320.bean.NWAL2320_ImptHeaderBean;
import business.blap.NWAL2320.bean.NWAL2320_ImptRtrnDetailBean;
import business.blap.NWAL2320.constant.NWAL2320Constant;
import business.db.BILL_TO_CUSTTMsg;
import business.db.CPOTMsg;
import business.db.DS_IMPT_ORDTMsg;
import business.db.DS_IMPT_ORD_CONFIGTMsg;
import business.db.DS_IMPT_ORD_DELY_INFOTMsg;
import business.db.DS_IMPT_ORD_DTLTMsg;
import business.db.DS_IMPT_ORD_RTRN_DTLTMsg;
import business.db.DS_IMPT_ORD_SLS_CRTMsg;
import business.db.DS_IMPT_PRC_CALC_BASETMsg;
import business.db.DS_IMPT_RTRN_PRC_CALCTMsg;
import business.db.DS_MDLTMsg;
import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.db.MDSETMsg;
import business.db.MDSE_STORE_PKGTMsg;
import business.db.PRC_CATGTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SVC_CONFIG_MSTRTMsg;
import business.parts.NSZC048001PMsg;
import business.parts.NSZC048001_xxChildMdseCdListPMsgArray;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157002PMsg;
import business.parts.NWZC157003PMsg;
import business.parts.NWZC157004PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC048001.NSZC048001;
import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001CustomerBean;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001ErrorInfo;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001ErrorInfoHelper;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001Result;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001Util;
import com.canon.cusa.s21.common.NWX.NWXC220001.constant.NWXC220001Constant;
import com.canon.cusa.s21.common.NWX.NWXC220001.constant.NWXC220001Constant.CUSTOMER_TABLE_ID;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_DELY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_UPLD_TMPL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.security.helpers.S21SessionHelper;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/25/2016   Fujitsu         M.Hara          Create          N/A
 * 06/16/2016   Fujitsu         M.Hara          Update          QC#10087
 * 06/21/2016   Fujitsu         M.Hara          Update          QC#10490
 * 06/28/2016   Fujitsu         M.Hara          Update          QC#10970
 * 07/13/2016   Fujitsu         M.Hara          Update          QC#11629
 * 07/14/2016   Fujitsu         M.Hara          Update          QC#3265
 * 07/19/2016   Fujitsu         M.Hara          Update          QC#11837
 * 07/28/2016   Fujitsu         M.Hara          Update          S21_NA#11852
 * 2016/11/29   Fujitsu         M.Yamada        Update          QC#16266
 * 2017/01/05   Fujitsu         S.Ohki          Update          QC#16757
 * 2017/06/29   Fujitsu         N.Aoyama        Update          QC#18984
 * 2017/12/05   Fujitsu         K.Ishizuka      Update          QC#22195
 * 2018/04/20   Fujitsu         R.Nakamura      Update          QC#22347
 * 2018/04/25   Fujitsu         S.Ohki,S.Takami Update          QC#22189
 * 2018/09/18   Fujitsu         T.Noguchi       Update          QC#9700
 * 2019/03/25   Fujitsu         K.Ishizuka      Update          QC#30924
 *</pre>
 */
public class NWAL2320CommonLogic {

    /**
     * getCMsgArray
     * @param bizMsg NWAL2320CMsg
     * @return EZDCMsgArray
     */
    public static EZDCMsgArray getCMsgArray(NWAL2320CMsg bizMsg) {
        String ordUpldTmplTpCd = bizMsg.ordUpldTmplTpCd.getValue();

        if (ORD_UPLD_TMPL_TP.NEW_SALES_ORDER.equals(ordUpldTmplTpCd)) {
            return bizMsg.A;
        } else if (ORD_UPLD_TMPL_TP.EXISTING_SALES_ORDER.equals(ordUpldTmplTpCd)) {
            return bizMsg.B;
        } else if (ORD_UPLD_TMPL_TP.NEW_RMA_ORDER.equals(ordUpldTmplTpCd)) {
            return bizMsg.C;
        } else if (ORD_UPLD_TMPL_TP.EXISTING_RMA_ORDER.equals(ordUpldTmplTpCd)) {
            return bizMsg.D;
        }
        return null;
    }

    /**
     * getRowsPerPage
     * @param bizMsg NWAL2320CMsg
     * @return int
     */
    public static int getRowsPerPage(NWAL2320CMsg bizMsg) {

        String rowsPerPageString = bizMsg.xxPageCd_CM.getValue();
        int rowsPerPage = bizMsg.A.length();

        if (isNumber(rowsPerPageString)) {
            rowsPerPage = Integer.valueOf(rowsPerPageString);
        } else {
            rowsPerPage = bizMsg.A.length();
        }

        return rowsPerPage;
    }

    /**
     * pagination
     * @param bizMsg NWAL2320CMsg
     * @param glblMsg NWAL2320SMsg
     * @param selRow int
     */
    public static void pagenation(NWAL2320CMsg bizMsg, NWAL2320SMsg glblMsg, int selRow) {
        String ordUpldTmplTpCd = bizMsg.ordUpldTmplTpCd.getValue();
        int rowsPerPage = getRowsPerPage(bizMsg);

        if (ORD_UPLD_TMPL_TP.NEW_SALES_ORDER.equals(ordUpldTmplTpCd)) {
            pagenationNewOrd(bizMsg, glblMsg, selRow, rowsPerPage);
        } else if (ORD_UPLD_TMPL_TP.EXISTING_SALES_ORDER.equals(ordUpldTmplTpCd)) {
            pagenationExsOrd(bizMsg, glblMsg, selRow, rowsPerPage);
        } else if (ORD_UPLD_TMPL_TP.NEW_RMA_ORDER.equals(ordUpldTmplTpCd)) {
            pagenationNewRma(bizMsg, glblMsg, selRow, rowsPerPage);
        } else if (ORD_UPLD_TMPL_TP.EXISTING_RMA_ORDER.equals(ordUpldTmplTpCd)) {
            pagenationExsRma(bizMsg, glblMsg, selRow, rowsPerPage);
        }
    }

    /**
     * pagenationNewOrd
     * @param bizMsg NWAL2320CMsg
     * @param glblMsg NWAL2320SMsg
     * @param selRow int
     * @param rowPerPage int
     */
    public static void pagenationNewOrd(NWAL2320CMsg bizMsg, NWAL2320SMsg glblMsg, int selRow, int rowPerPage) {

        int startRow = (selRow / rowPerPage) * rowPerPage;

        ZYPTableUtil.clear(bizMsg.A);

        int rowCnt = 0;
        for (int rowIdx = startRow; rowIdx < glblMsg.A.getValidCount(); rowIdx++, rowCnt++) {
            if (rowCnt >= rowPerPage) {
                break;
            }
            EZDMsg.copy(glblMsg.A.no(rowIdx), null, bizMsg.A.no(rowCnt), null);
        }

        bizMsg.A.setValidCount(rowCnt);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum_CM, BigDecimal.valueOf(startRow + 1));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowToNum_CM, BigDecimal.valueOf(startRow + bizMsg.A.getValidCount()));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowOfNum_CM, BigDecimal.valueOf(glblMsg.A.getValidCount()));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowCurNum_CM, BigDecimal.valueOf(startRow + 1).divide(BigDecimal.valueOf(rowPerPage), 0, BigDecimal.ROUND_UP));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowTotNum_CM, BigDecimal.valueOf(glblMsg.A.getValidCount()).divide(BigDecimal.valueOf(rowPerPage), 0, BigDecimal.ROUND_UP));
    }

    /**
     * pagenationExsOrd
     * @param bizMsg NWAL2320CMsg
     * @param glblMsg NWAL2320SMsg
     * @param selRow int
     * @param rowPerPage int
     */
    public static void pagenationExsOrd(NWAL2320CMsg bizMsg, NWAL2320SMsg glblMsg, int selRow, int rowPerPage) {

        int startRow = (selRow / rowPerPage) * rowPerPage;

        ZYPTableUtil.clear(bizMsg.B);

        int rowCnt = 0;
        for (int rowIdx = startRow; rowIdx < glblMsg.B.getValidCount(); rowIdx++, rowCnt++) {
            if (rowCnt >= rowPerPage) {
                break;
            }
            EZDMsg.copy(glblMsg.B.no(rowIdx), null, bizMsg.B.no(rowCnt), null);
        }

        bizMsg.B.setValidCount(rowCnt);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum_CM, BigDecimal.valueOf(startRow + 1));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowToNum_CM, BigDecimal.valueOf(startRow + bizMsg.B.getValidCount()));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowOfNum_CM, BigDecimal.valueOf(glblMsg.B.getValidCount()));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowCurNum_CM, BigDecimal.valueOf(startRow + 1).divide(BigDecimal.valueOf(rowPerPage), 0, BigDecimal.ROUND_UP));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowTotNum_CM, BigDecimal.valueOf(glblMsg.B.getValidCount()).divide(BigDecimal.valueOf(rowPerPage), 0, BigDecimal.ROUND_UP));
    }

    /**
     * pagenationNewRma
     * @param bizMsg NWAL2320CMsg
     * @param glblMsg NWAL2320SMsg
     * @param selRow int
     * @param rowPerPage int
     */
    public static void pagenationNewRma(NWAL2320CMsg bizMsg, NWAL2320SMsg glblMsg, int selRow, int rowPerPage) {

        int startRow = (selRow / rowPerPage) * rowPerPage;

        ZYPTableUtil.clear(bizMsg.C);

        int rowCnt = 0;
        for (int rowIdx = startRow; rowIdx < glblMsg.C.getValidCount(); rowIdx++, rowCnt++) {
            if (rowCnt >= rowPerPage) {
                break;
            }
            EZDMsg.copy(glblMsg.C.no(rowIdx), null, bizMsg.C.no(rowCnt), null);
        }

        bizMsg.C.setValidCount(rowCnt);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum_CM, BigDecimal.valueOf(startRow + 1));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowToNum_CM, BigDecimal.valueOf(startRow + bizMsg.C.getValidCount()));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowOfNum_CM, BigDecimal.valueOf(glblMsg.C.getValidCount()));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowCurNum_CM, BigDecimal.valueOf(startRow + 1).divide(BigDecimal.valueOf(rowPerPage), 0, BigDecimal.ROUND_UP));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowTotNum_CM, BigDecimal.valueOf(glblMsg.C.getValidCount()).divide(BigDecimal.valueOf(rowPerPage), 0, BigDecimal.ROUND_UP));
    }

    /**
     * pagenationExsRma
     * @param bizMsg NWAL2320CMsg
     * @param glblMsg NWAL2320SMsg
     * @param selRow int
     * @param rowPerPage int
     */
    public static void pagenationExsRma(NWAL2320CMsg bizMsg, NWAL2320SMsg glblMsg, int selRow, int rowPerPage) {

        int startRow = (selRow / rowPerPage) * rowPerPage;

        ZYPTableUtil.clear(bizMsg.D);

        int rowCnt = 0;
        for (int rowIdx = startRow; rowIdx < glblMsg.D.getValidCount(); rowIdx++, rowCnt++) {
            if (rowCnt >= rowPerPage) {
                break;
            }
            EZDMsg.copy(glblMsg.D.no(rowIdx), null, bizMsg.D.no(rowCnt), null);
        }

        bizMsg.D.setValidCount(rowCnt);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum_CM, BigDecimal.valueOf(startRow + 1));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowToNum_CM, BigDecimal.valueOf(startRow + bizMsg.D.getValidCount()));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowOfNum_CM, BigDecimal.valueOf(glblMsg.D.getValidCount()));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowCurNum_CM, BigDecimal.valueOf(startRow + 1).divide(BigDecimal.valueOf(rowPerPage), 0, BigDecimal.ROUND_UP));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowTotNum_CM, BigDecimal.valueOf(glblMsg.D.getValidCount()).divide(BigDecimal.valueOf(rowPerPage), 0, BigDecimal.ROUND_UP));
    }

    /**
     * checkMandatory
     * @param errorInfoList List<NWXC220001ErrorInfo>
     * @param item EZDSItem
     * @param itemNm String
     * @return boolean
     */
    public static boolean checkMandatory(List<NWXC220001ErrorInfo> errorInfoList, EZDSItem item, String itemNm) {
        boolean isSuccess = true;

        if (!ZYPCommonFunc.hasValue(item)) {
            addErrorInfo(errorInfoList, NWXC220001Constant.MSG_ID.ZZZM9025E, itemNm);
            isSuccess = false;
        }

        return isSuccess;
    }

    /**
     * 
     * @param errorInfoList List<NWXC220001ErrorInfo>
     * @param src EZDSStringItem
     * @param dest EZDSStringItem
     * @param itemNm String
     * @return boolean
     */
    public static boolean checkSameSMsgValue(List<NWXC220001ErrorInfo> errorInfoList, EZDSStringItem src, EZDSStringItem dest, String itemNm) {
        boolean isSuccess = true;

        if (!src.getValue().equals(dest.getValue())) {
            addErrorInfo(errorInfoList, NWAL2320Constant.NWAM0856E, itemNm);
            isSuccess = false;
        }

        return isSuccess;
    }

    /**
     * checkSameSMsgValue
     * @param errorInfoList List<NWXC220001ErrorInfo>
     * @param src EZDSBigDecimalItem
     * @param dest EZDSBigDecimalItem
     * @param itemNm String
     * @return boolean
     */
    public static boolean checkSameSMsgValue(List<NWXC220001ErrorInfo> errorInfoList, EZDSBigDecimalItem src, EZDSBigDecimalItem dest, String itemNm) {
        boolean isSuccess = true;

        if (ZYPCommonFunc.hasValue(src) && ZYPCommonFunc.hasValue(dest)) {
            if (!src.getValue().equals(dest.getValue())) {
                isSuccess = false;
            }
        } else if (!(!ZYPCommonFunc.hasValue(src) && !ZYPCommonFunc.hasValue(dest))) {
            isSuccess = false;
        }

        if (!isSuccess) {
            addErrorInfo(errorInfoList, NWAL2320Constant.NWAM0856E, itemNm);
        }

        return isSuccess;
    }

    /**
     * createErrString
     * @param rowErrList List<NWXC220001ErrorInfo>
     * @param maxLength int
     * @return String
     */
    public static String createErrString(List<NWXC220001ErrorInfo> rowErrList, int maxLength) {

        StringBuilder sb = new StringBuilder();
        int msgLength = 0;
        String errString, splitString;

        for (NWXC220001ErrorInfo errorInfo : rowErrList) {
            errString = errorInfo.getErrMsgText();
            splitString = (sb.length() > 0 ? "," : "");

            if ((msgLength + splitString.length() + errString.length()) > maxLength) {
                break;
            }
            sb.append(splitString).append(errString);
            msgLength += (splitString.length() + errString.length());
        }

        return sb.toString();
    }

    /**
     * hasError
     * @param allRowErrMap Map<Integer, List<NWXC220001ErrorInfo>>
     * @return boolean
     */
    public static boolean hasError(Map<Integer, List<NWXC220001ErrorInfo>> allRowErrMap) {

        for (List<NWXC220001ErrorInfo> errInfo : allRowErrMap.values()) {
            if (errInfo.size() > 0) {
                return true;
            }
        }

        return false;
    }

    /**
     * createImptBeans
     * @param bizMsg NWAL2320CMsg
     * @param glblMsg NWAL2320SMsg
     * @param isLineUpdate boolean
     * @return LinkedHashMap<String, NWAL2320_ImptHeaderBean>
     */
    public static LinkedHashMap<String, NWAL2320_ImptHeaderBean> createImptBeans(NWAL2320CMsg bizMsg, NWAL2320SMsg glblMsg, boolean isLineUpdate) {
        LinkedHashMap<String, NWAL2320_ImptHeaderBean> imptBeansMap = null;
        String ordUpldTmplTpCd = bizMsg.ordUpldTmplTpCd.getValue();

        if (ORD_UPLD_TMPL_TP.NEW_SALES_ORDER.equals(ordUpldTmplTpCd)) {
            imptBeansMap = createImptBeansForNewOrd(bizMsg, glblMsg, isLineUpdate);
        } else if (ORD_UPLD_TMPL_TP.EXISTING_SALES_ORDER.equals(ordUpldTmplTpCd)) {
            imptBeansMap = createImptBeansForExsOrd(bizMsg, glblMsg, isLineUpdate);
        } else if (ORD_UPLD_TMPL_TP.NEW_RMA_ORDER.equals(ordUpldTmplTpCd)) {
            imptBeansMap = createImptBeansForNewRma(bizMsg, glblMsg, isLineUpdate);
        } else if (ORD_UPLD_TMPL_TP.EXISTING_RMA_ORDER.equals(ordUpldTmplTpCd)) {
            imptBeansMap = createImptBeansForExsRma(bizMsg, glblMsg, isLineUpdate);
        }

        return imptBeansMap;
    }

    private static LinkedHashMap<String, NWAL2320_ImptHeaderBean> createImptBeansForNewOrd(NWAL2320CMsg bizMsg, NWAL2320SMsg glblMsg, boolean isLineUpdate) {
        LinkedHashMap<String, NWAL2320_ImptHeaderBean> imptBeansMap = new LinkedHashMap<String, NWAL2320_ImptHeaderBean>();
        NWAL2320_ImptHeaderBean headerBean;
        NWAL2320_ImptConfigBean configBean;

        NWAL2320_ASMsg glblASMsg;
        String headerLevelKey, configLevelKey;

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            glblASMsg = glblMsg.A.no(i);
            //******************************************************************
            // Create Header Level Msg Map
            //******************************************************************
            headerLevelKey = glblASMsg.procGrpNum_CO.getValue();
            if (!imptBeansMap.containsKey(headerLevelKey)) {
                headerBean = new NWAL2320_ImptHeaderBean(bizMsg.ordUpldTmplTpCd.getValue(), isLineUpdate);
                imptBeansMap.put(headerLevelKey, headerBean);
            }
            headerBean = imptBeansMap.get(headerLevelKey);

            //******************************************************************
            // Create Config Level Msg Map
            //******************************************************************
            configLevelKey = String.format("%s_%s", headerLevelKey, glblASMsg.dsOrdPosnNum_CO.getValue());
            if (!headerBean.configBeanMap.containsKey(configLevelKey)) {
                configBean = new NWAL2320_ImptConfigBean(headerBean);
                headerBean.configBeanMap.put(configLevelKey, configBean);
            }
            configBean = headerBean.configBeanMap.get(configLevelKey);

            //**************************************************************
            // Create Detail Level Msg List
            //**************************************************************
            new NWAL2320_ImptDetailBean(configBean, glblASMsg);
        }

        return imptBeansMap;
    }

    private static LinkedHashMap<String, NWAL2320_ImptHeaderBean> createImptBeansForExsOrd(NWAL2320CMsg bizMsg, NWAL2320SMsg glblMsg, boolean isLineUpdate) {
        LinkedHashMap<String, NWAL2320_ImptHeaderBean> imptBeansMap = new LinkedHashMap<String, NWAL2320_ImptHeaderBean>();
        NWAL2320_ImptHeaderBean headerBean;
        NWAL2320_ImptConfigBean configBean;

        NWAL2320_BSMsg glblBSMsg;
        String headerLevelKey, configLevelKey;

        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            glblBSMsg = glblMsg.B.no(i);
            //******************************************************************
            // Create Header Level Msg Map
            //******************************************************************
            headerLevelKey = glblBSMsg.cpoOrdNum_EO.getValue();
            if (!imptBeansMap.containsKey(headerLevelKey)) {
                headerBean = new NWAL2320_ImptHeaderBean(bizMsg.ordUpldTmplTpCd.getValue(), isLineUpdate);
                imptBeansMap.put(headerLevelKey, headerBean);
            }
            headerBean = imptBeansMap.get(headerLevelKey);

            //******************************************************************
            // Create Config Level Msg Map
            //******************************************************************
            configLevelKey = String.format("%s_%s", headerLevelKey, glblBSMsg.dsOrdPosnNum_EO.getValue());
            if (!headerBean.configBeanMap.containsKey(configLevelKey)) {
                configBean = new NWAL2320_ImptConfigBean(headerBean);
                headerBean.configBeanMap.put(configLevelKey, configBean);
            }
            configBean = headerBean.configBeanMap.get(configLevelKey);

            //**************************************************************
            // Create Detail Level Msg List
            //**************************************************************
            new NWAL2320_ImptDetailBean(configBean, glblBSMsg);
        }

        return imptBeansMap;
    }

    private static LinkedHashMap<String, NWAL2320_ImptHeaderBean> createImptBeansForNewRma(NWAL2320CMsg bizMsg, NWAL2320SMsg glblMsg, boolean isLineUpdate) {
        LinkedHashMap<String, NWAL2320_ImptHeaderBean> imptBeansMap = new LinkedHashMap<String, NWAL2320_ImptHeaderBean>();
        NWAL2320_ImptHeaderBean headerBean;
        NWAL2320_ImptConfigBean configBean;

        NWAL2320_CSMsg glblCSMsg;
        String headerLevelKey, configLevelKey;

        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
            glblCSMsg = glblMsg.C.no(i);
            //******************************************************************
            // Create Header Level Msg Map
            //******************************************************************
            headerLevelKey = glblCSMsg.procGrpNum_NR.getValue();
            if (!imptBeansMap.containsKey(headerLevelKey)) {
                headerBean = new NWAL2320_ImptHeaderBean(bizMsg.ordUpldTmplTpCd.getValue(), isLineUpdate);
                imptBeansMap.put(headerLevelKey, headerBean);
            }
            headerBean = imptBeansMap.get(headerLevelKey);

            //******************************************************************
            // Create Config Level Msg Map
            //******************************************************************
            configLevelKey = String.format("%s_%s", headerLevelKey, glblCSMsg.dsOrdPosnNum_NR.getValue());
            if (!headerBean.configBeanMap.containsKey(configLevelKey)) {
                configBean = new NWAL2320_ImptConfigBean(headerBean);
                headerBean.configBeanMap.put(configLevelKey, configBean);
            }
            configBean = headerBean.configBeanMap.get(configLevelKey);

            //**************************************************************
            // Create Return Detail Msg List
            //**************************************************************
            new NWAL2320_ImptRtrnDetailBean(configBean, glblCSMsg);
        }

        return imptBeansMap;
    }

    private static LinkedHashMap<String, NWAL2320_ImptHeaderBean> createImptBeansForExsRma(NWAL2320CMsg bizMsg, NWAL2320SMsg glblMsg, boolean isLineUpdate) {
        LinkedHashMap<String, NWAL2320_ImptHeaderBean> imptBeansMap = new LinkedHashMap<String, NWAL2320_ImptHeaderBean>();
        NWAL2320_ImptHeaderBean headerBean;
        NWAL2320_ImptConfigBean configBean;

        NWAL2320_DSMsg glblDSMsg;
        String headerLevelKey, configLevelKey;

        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            glblDSMsg = glblMsg.D.no(i);
            //******************************************************************
            // Create Header Level Msg Map
            //******************************************************************
            headerLevelKey = glblDSMsg.cpoOrdNum_ER.getValue();
            if (!imptBeansMap.containsKey(headerLevelKey)) {
                headerBean = new NWAL2320_ImptHeaderBean(bizMsg.ordUpldTmplTpCd.getValue(), isLineUpdate);
                imptBeansMap.put(headerLevelKey, headerBean);
            }
            headerBean = imptBeansMap.get(headerLevelKey);

            //******************************************************************
            // Create Config Level Msg Map
            //******************************************************************
            // QC#10970
            configLevelKey = String.format("%s_%s", headerLevelKey, glblDSMsg.dsOrdPosnNum_ER.getValue());
            if (!headerBean.configBeanMap.containsKey(configLevelKey)) {
                configBean = new NWAL2320_ImptConfigBean(headerBean);
                headerBean.configBeanMap.put(configLevelKey, configBean);
            }
            configBean = headerBean.configBeanMap.get(configLevelKey);

            //**************************************************************
            // Create Return Detail Msg List
            //**************************************************************
            new NWAL2320_ImptRtrnDetailBean(configBean, glblDSMsg);
        }

        return imptBeansMap;
    }


    /**
     * derivingData
     * @param bizMsg NWAL2320CMsg
     * @param imptBeansMap LinkedHashMap<String, NWAL2320_ImptHeaderBean>
     */
    public static void derivingData(NWAL2320CMsg bizMsg, LinkedHashMap<String, NWAL2320_ImptHeaderBean> imptBeansMap) {

        for (NWAL2320_ImptHeaderBean hdrBean : imptBeansMap.values()) {
            hdrBean.slsDt = bizMsg.slsDt.getValue();

            //******************************************************************
            // Deriving Header Data
            //******************************************************************
            if (hdrBean.isUpldTpNew()) {
                if (!derivingHeaderDataForNew(bizMsg, hdrBean)) {
                    continue;
                }
            } else {
                if (!derivingHeaderDataForExist(bizMsg, hdrBean)) {
                    continue;
                }
            }

            //******************************************************************
            // Deriving Config & Detail Data
            //******************************************************************
            derivingConfigDtlData(bizMsg, hdrBean);

            if (!hdrBean.hasError()) {
                setPricingData(bizMsg, hdrBean);
            }
        }

        return;
    }

    private static boolean derivingHeaderDataForNew(NWAL2320CMsg bizMsg, NWAL2320_ImptHeaderBean hdrBean) {
        S21SsmEZDResult ssmResult;
        DS_IMPT_ORDTMsg imptTMsg = hdrBean.me;
        String slsDt = bizMsg.slsDt.getValue();
        NWAL2320_ASMsg glblASMsg;
        NWAL2320_CSMsg glblCSMsg;
        String resultString, billToCustLocCd, shipToCustLocCd;
        // 2017/01/05 S21_NA#16757 Add Start
        String soldToCustLocCd;
        // 2017/01/05 S21_NA#16757 Add End
        Map< ? ,  ? > resultMap;

        // Set Fixed Value
        hdrBean.setFixedValue();

        // Global Company Code
        ZYPEZDItemValueSetter.setValue(imptTMsg.glblCmpyCd, bizMsg.glblCmpyCd);

        // Order Source Import Date
        ZYPEZDItemValueSetter.setValue(imptTMsg.ordSrcImptTs, ZYPDateUtil.getCurrentSystemTime(S21SessionHelper.EZD_TIME_FORMAT));

        // Customer Issue PO Date
        ZYPEZDItemValueSetter.setValue(imptTMsg.custIssPoDt, slsDt);

        // Admin Person Code
        ZYPEZDItemValueSetter.setValue(imptTMsg.adminPsnCd, S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());

        // Price Base Date
        ZYPEZDItemValueSetter.setValue(imptTMsg.prcBaseDt, slsDt);

        // Oracle Sequence
        BigDecimal pk;
        if (hdrBean.doInsertTable) {
           pk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_SQ);
           ZYPEZDItemValueSetter.setValue(imptTMsg.dsImptOrdPk, pk);
           ZYPEZDItemValueSetter.setValue(imptTMsg.ordSrcRefNum, NWAL2320Constant.ORD_SRC_REF_PREFIX + pk.toPlainString());
        }

        //******************************************************************
        // Deriving Master
        //******************************************************************
        if (hdrBean.isUpldTpSlsOrd()) {
            glblASMsg = (NWAL2320_ASMsg) hdrBean.detailBeanList.get(0).rowData;

            // Order Category
            if (!ZYPCommonFunc.hasValue(glblASMsg.dsOrdCatgCd_CO)) {
                ssmResult = NWAL2320Query.getInstance().getDsOrdCatgCd(imptTMsg.glblCmpyCd.getValue(), glblASMsg.dsOrdCatgDescTxt_CO.getValue());
                if (checkAndAddMasterNotFoundError(hdrBean, ssmResult, TMPL_DL_HEADER_FOR_NEW_ORDER[4])) {
                    resultString = (String) ssmResult.getResultObject();
                    ZYPEZDItemValueSetter.setValue(glblASMsg.dsOrdCatgCd_CO, resultString);
                }
            }
            ZYPEZDItemValueSetter.setValue(imptTMsg.dsOrdCatgCd, glblASMsg.dsOrdCatgCd_CO);

            // Reason
            if (!ZYPCommonFunc.hasValue(glblASMsg.dsOrdTpCd_CO)) {
                ssmResult = NWAL2320Query.getInstance().getDsOrdTpCd(imptTMsg.glblCmpyCd.getValue(), glblASMsg.dsOrdTpDescTxt_CO.getValue());
                if (checkAndAddMasterNotFoundError(hdrBean, ssmResult, TMPL_DL_HEADER_FOR_NEW_ORDER[5])) {
                    resultString = (String) ssmResult.getResultObject();
                    ZYPEZDItemValueSetter.setValue(glblASMsg.dsOrdTpCd_CO, resultString);
                }
            }
            ZYPEZDItemValueSetter.setValue(imptTMsg.dsOrdTpCd, glblASMsg.dsOrdTpCd_CO);

            // Sales Rep
            if (!ZYPCommonFunc.hasValue(glblASMsg.slsRepTocCd_CO)) {
                ssmResult = NWAL2320Query.getInstance().getTocInfo(imptTMsg.glblCmpyCd.getValue(), glblASMsg.tocNm_CO.getValue());
                // 2017/01/05 S21_NA#16757 Mod Start
//                if (checkAndAddMasterNotFoundError(hdrBean, ssmResult, TMPL_DL_HEADER_FOR_NEW_ORDER[13])) {
                if (checkAndAddMasterNotFoundError(hdrBean, ssmResult, TMPL_DL_HEADER_FOR_NEW_ORDER[14])) {
                // 2017/01/05 S21_NA#16757 Mod End

                    resultMap = (Map< ? , ? >) ssmResult.getResultObject();
                    resultString = (String) resultMap.get("TOC_CD");
                    ZYPEZDItemValueSetter.setValue(glblASMsg.slsRepTocCd_CO, resultString);

//                    ZYPEZDItemValueSetter.setValue(glblASMsg.slsRepRoleTpCd_CO, (String) resultMap.get("ORG_FUNC_ROLE_TP_CD"));
                    ZYPEZDItemValueSetter.setValue(glblASMsg.coaExtnCd_CO, (String) resultMap.get("COA_EXTN_CD"));
                }
            }
            ZYPEZDItemValueSetter.setValue(imptTMsg.slsRepTocCd, glblASMsg.slsRepTocCd_CO);
            hdrBean.coaExtnCd = glblASMsg.coaExtnCd_CO.getValue();

            // Customer PO
            ZYPEZDItemValueSetter.setValue(imptTMsg.custIssPoNum, glblASMsg.custIssPoNum_CO);

            // Negotiated Deal Amount
            ZYPEZDItemValueSetter.setValue(imptTMsg.negoDealAmt, glblASMsg.negoDealAmt_CO);

            // Acquisition Number
            ZYPEZDItemValueSetter.setValue(imptTMsg.aquNum, glblASMsg.aquNum_CO);

            billToCustLocCd = glblASMsg.billToCustLocCd_CO.getValue();
            shipToCustLocCd = glblASMsg.shipToCustLocCd_CO.getValue();
            // 2017/01/05 S21_NA#16757 Add Start
            soldToCustLocCd = glblASMsg.soldToCustLocCd_CO.getValue();
            // 2017/01/05 S21_NA#16757 Add End

        } else {
            glblCSMsg = (NWAL2320_CSMsg) hdrBean.rtrnDtlBeanList.get(0).rowData;

            // Order Category
            if (!ZYPCommonFunc.hasValue(glblCSMsg.dsOrdCatgCd_NR)) {
                ssmResult = NWAL2320Query.getInstance().getDsOrdCatgCd(imptTMsg.glblCmpyCd.getValue(), glblCSMsg.dsOrdCatgDescTxt_NR.getValue());
                if (checkAndAddMasterNotFoundError(hdrBean, ssmResult, TMPL_DL_HEADER_FOR_NEW_RMA[4])) {
                    resultString = (String) ssmResult.getResultObject();
                    ZYPEZDItemValueSetter.setValue(glblCSMsg.dsOrdCatgCd_NR, resultString);
                }
            }
            ZYPEZDItemValueSetter.setValue(imptTMsg.dsOrdCatgCd, glblCSMsg.dsOrdCatgCd_NR);

            // Reason
            if (!ZYPCommonFunc.hasValue(glblCSMsg.dsOrdTpCd_NR)) {
                ssmResult = NWAL2320Query.getInstance().getDsOrdTpCd(imptTMsg.glblCmpyCd.getValue(), glblCSMsg.dsOrdTpDescTxt_NR.getValue());
                if (checkAndAddMasterNotFoundError(hdrBean, ssmResult, TMPL_DL_HEADER_FOR_NEW_RMA[5])) {
                    resultString = (String) ssmResult.getResultObject();
                    ZYPEZDItemValueSetter.setValue(glblCSMsg.dsOrdTpCd_NR, resultString);
                }
            }
            ZYPEZDItemValueSetter.setValue(imptTMsg.dsOrdTpCd, glblCSMsg.dsOrdTpCd_NR);

            // Sales Rep
            if (!ZYPCommonFunc.hasValue(glblCSMsg.slsRepTocCd_NR)) {
                ssmResult = NWAL2320Query.getInstance().getTocInfo(imptTMsg.glblCmpyCd.getValue(), glblCSMsg.tocNm_NR.getValue());
                // 2017/01/05 S21_NA#16757 Mod Start
//                if (checkAndAddMasterNotFoundError(hdrBean, ssmResult, TMPL_DL_HEADER_FOR_NEW_RMA[14])) {
                if (checkAndAddMasterNotFoundError(hdrBean, ssmResult, TMPL_DL_HEADER_FOR_NEW_RMA[15])) {
                // 2017/01/05 S21_NA#16757 Mod End
                    resultMap = (Map< ? , ? >) ssmResult.getResultObject();
                    resultString = (String) resultMap.get("TOC_CD");
                    ZYPEZDItemValueSetter.setValue(glblCSMsg.slsRepTocCd_NR, resultString);

//                    ZYPEZDItemValueSetter.setValue(glblCSMsg.slsRepRoleTpCd_NR, (String) resultMap.get("ORG_FUNC_ROLE_TP_CD"));
                    ZYPEZDItemValueSetter.setValue(glblCSMsg.coaExtnCd_NR, (String) resultMap.get("COA_EXTN_CD"));
                }
            }
            ZYPEZDItemValueSetter.setValue(imptTMsg.slsRepTocCd, glblCSMsg.slsRepTocCd_NR);
            hdrBean.coaExtnCd = glblCSMsg.coaExtnCd_NR.getValue();

            // Customer PO
            ZYPEZDItemValueSetter.setValue(imptTMsg.custIssPoNum, glblCSMsg.custIssPoNum_NR);

            // Negotiated Deal Amount
            ZYPEZDItemValueSetter.setValue(imptTMsg.negoDealAmt, glblCSMsg.negoDealAmt_NR);

            // Acquisition Number
            ZYPEZDItemValueSetter.setValue(imptTMsg.aquNum, glblCSMsg.aquNum_NR);

            billToCustLocCd = glblCSMsg.billToCustLocCd_NR.getValue();
            shipToCustLocCd = glblCSMsg.shipToCustLocCd_NR.getValue();
            // 2017/01/05 S21_NA#16757 Add Start
            soldToCustLocCd = glblCSMsg.soldToCustLocCd_NR.getValue();
            // 2017/01/05 S21_NA#16757 Add End
        }

        // Order Category Check
        NWXC220001Result<Map<String, String>> validDsOrdCatgCdMap = NWXC220001.getValidDsOrdCatgCdMap(bizMsg.glblCmpyCd.getValue(), NWAL2320Constant.BIZ_ID);

        if (!validDsOrdCatgCdMap.getResultObject().containsKey(imptTMsg.dsOrdCatgCd.getValue())) {
            NWXC220001ErrorInfo errorInfo = new NWXC220001ErrorInfo(NWAL2320Constant.NWAM0181E, "Category");
            hdrBean.addErrorInfo(errorInfo);
        }

        // Bill To Location
        BILL_TO_CUSTTMsg billToCustTMsg = new BILL_TO_CUSTTMsg();
        ZYPEZDItemValueSetter.setValue(billToCustTMsg.glblCmpyCd, imptTMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(billToCustTMsg.billToCustCd, billToCustLocCd);
        ZYPEZDItemValueSetter.setValue(billToCustTMsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);

        NWXC220001Result<NWXC220001CustomerBean> billInfo = NWXC220001.getBillToCustWithDsAcctFromCd(billToCustTMsg, slsDt, CUSTOMER_TABLE_ID.BILL_TO_CUST);

        if (billInfo.hasResultObject()) {
            billToCustTMsg = billInfo.getResultObject().billToCustTMsg;
            ZYPEZDItemValueSetter.setValue(imptTMsg.billToCustAcctCd, billToCustTMsg.sellToCustCd);
            ZYPEZDItemValueSetter.setValue(imptTMsg.billToCustCd, billToCustTMsg.billToCustCd);
            ZYPEZDItemValueSetter.setValue(imptTMsg.sellToCustCd, billToCustTMsg.sellToCustCd);
            // 2017/01/05 S21_NA#16757 Del Start
//            ZYPEZDItemValueSetter.setValue(imptTMsg.soldToCustLocCd, billToCustTMsg.billToCustCd);
            // 2017/01/05 S21_NA#16757 Del End
        } else {
            hdrBean.addErrorInfo(new NWXC220001ErrorInfo(NWAL2320Constant.NWAM0181E, TMPL_DL_HEADER_FOR_NEW_ORDER[11]));
        }

        // Ship To Location
        SHIP_TO_CUSTTMsg shipToCustTMsg = new SHIP_TO_CUSTTMsg();
        ZYPEZDItemValueSetter.setValue(shipToCustTMsg.glblCmpyCd, imptTMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(shipToCustTMsg.shipToCustCd, shipToCustLocCd);
        ZYPEZDItemValueSetter.setValue(shipToCustTMsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
        NWXC220001Result<NWXC220001CustomerBean> shipInfo = NWXC220001.getShipToCustWithDsAcctFromCd(shipToCustTMsg, slsDt, CUSTOMER_TABLE_ID.SHIP_TO_CUST);

        if (shipInfo.hasResultObject()) {
            shipToCustTMsg = shipInfo.getResultObject().shipToCustTMsg;
            ZYPEZDItemValueSetter.setValue(imptTMsg.shipToCustAcctCd, shipToCustTMsg.sellToCustCd);
            ZYPEZDItemValueSetter.setValue(imptTMsg.shipToCustCd, shipToCustLocCd);
            ZYPEZDItemValueSetter.setValue(imptTMsg.shipToLocNm, shipToCustTMsg.locNm);
            ZYPEZDItemValueSetter.setValue(imptTMsg.shipToAddlLocNm, shipToCustTMsg.addlLocNm);
            ZYPEZDItemValueSetter.setValue(imptTMsg.shipToFirstLineAddr, shipToCustTMsg.firstLineAddr);
            ZYPEZDItemValueSetter.setValue(imptTMsg.shipToScdLineAddr, shipToCustTMsg.scdLineAddr);
            ZYPEZDItemValueSetter.setValue(imptTMsg.shipToThirdLineAddr, shipToCustTMsg.thirdLineAddr);
            ZYPEZDItemValueSetter.setValue(imptTMsg.shipToFrthLineAddr, shipToCustTMsg.frthLineAddr);
            ZYPEZDItemValueSetter.setValue(imptTMsg.shipToCtyAddr, shipToCustTMsg.ctyAddr);
            ZYPEZDItemValueSetter.setValue(imptTMsg.shipToStCd, shipToCustTMsg.stCd);
            ZYPEZDItemValueSetter.setValue(imptTMsg.shipToProvNm, shipToCustTMsg.provNm);
            ZYPEZDItemValueSetter.setValue(imptTMsg.shipToCntyNm, shipInfo.getResultObject().shipCntyNm);
            ZYPEZDItemValueSetter.setValue(imptTMsg.shipToPostCd, shipToCustTMsg.postCd);
            ZYPEZDItemValueSetter.setValue(imptTMsg.shipToCtryCd, shipToCustTMsg.ctryCd);
            ZYPEZDItemValueSetter.setValue(imptTMsg.shipTo01RefCmntTxt, shipToCustTMsg.firstRefCmntTxt);
            ZYPEZDItemValueSetter.setValue(imptTMsg.shipTo02RefCmntTxt, shipToCustTMsg.scdRefCmntTxt);
        } else {
            hdrBean.addErrorInfo(new NWXC220001ErrorInfo(NWAL2320Constant.NWAM0181E, TMPL_DL_HEADER_FOR_NEW_ORDER[12]));
        }

        // 2017/01/05 S21_NA#16757 Add Start
        // Sold To Location
        BILL_TO_CUSTTMsg soldToLocBillToCustTMsg = new BILL_TO_CUSTTMsg();
        ZYPEZDItemValueSetter.setValue(soldToLocBillToCustTMsg.glblCmpyCd, imptTMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(soldToLocBillToCustTMsg.billToCustCd, soldToCustLocCd);
        ZYPEZDItemValueSetter.setValue(soldToLocBillToCustTMsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);

        NWXC220001Result<NWXC220001CustomerBean> soldBillInfo = NWXC220001.getBillToCustWithDsAcctFromCd(soldToLocBillToCustTMsg, slsDt, CUSTOMER_TABLE_ID.BILL_TO_CUST);

        if (soldBillInfo.hasResultObject()) {
            ZYPEZDItemValueSetter.setValue(imptTMsg.soldToCustLocCd, soldToCustLocCd);
        } else {
            hdrBean.addErrorInfo(new NWXC220001ErrorInfo(NWAL2320Constant.NWAM0181E, TMPL_DL_HEADER_FOR_NEW_ORDER[13]));
        }
        // 2017/01/05 S21_NA#16757 Add End

        // Freight Condition Code、Carrier Service Level Code、Shipping Service Level Code、Floor Price List Code
        if (ZYPCommonFunc.hasValue(imptTMsg.dsOrdTpCd)) {

            if (derivingDsORdTpProcDfnTMsg(hdrBean)) {

                DS_ORD_TP_PROC_DFNTMsg dsOrdTpProcDfnTMsg = hdrBean.dsOrdTpProcDfnTMsg;
                ZYPEZDItemValueSetter.setValue(imptTMsg.frtCondCd, dsOrdTpProcDfnTMsg.frtCondCd);
                ZYPEZDItemValueSetter.setValue(imptTMsg.carrSvcLvlCd, dsOrdTpProcDfnTMsg.defCarrSvcLvlCd);
                ZYPEZDItemValueSetter.setValue(imptTMsg.shpgSvcLvlCd, dsOrdTpProcDfnTMsg.defShpgSvcLvlCd);
                ZYPEZDItemValueSetter.setValue(imptTMsg.flPrcListCd, dsOrdTpProcDfnTMsg.defPrcCatgCd);

                if (hdrBean.isUpldTpSlsOrd()) {
                    glblASMsg = (NWAL2320_ASMsg) hdrBean.detailBeanList.get(0).rowData;
                    ZYPEZDItemValueSetter.setValue(glblASMsg.slsRepRoleTpCd_CO, getSlsRepRoleTpCd(dsOrdTpProcDfnTMsg.lineBizTpCd.getValue()));
                } else {
                    glblCSMsg = (NWAL2320_CSMsg) hdrBean.rtrnDtlBeanList.get(0).rowData;
                    ZYPEZDItemValueSetter.setValue(glblCSMsg.slsRepRoleTpCd_NR, getSlsRepRoleTpCd(dsOrdTpProcDfnTMsg.lineBizTpCd.getValue()));
                }

                // Price Category Code(call Pricing API )
                setPrcCatgCd(bizMsg, hdrBean);
            }

        }
        ZYPEZDItemValueSetter.setValue(imptTMsg.maintOnlyFlg, ZYPConstant.FLG_OFF_N); // QC#16266

        return !hdrBean.hasError();
    }

    private static boolean derivingDsORdTpProcDfnTMsg(NWAL2320_ImptHeaderBean hdrBean) {
        DS_ORD_TP_PROC_DFNTMsg dsOrdTpProcDfnTMsg = new DS_ORD_TP_PROC_DFNTMsg();

        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfnTMsg.glblCmpyCd, hdrBean.me.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfnTMsg.dsOrdTpCd, hdrBean.me.dsOrdTpCd);

        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfnTMsg.effFromDt, hdrBean.slsDt);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfnTMsg.effThruDt, hdrBean.slsDt);

        NWXC220001Result<DS_ORD_TP_PROC_DFNTMsg> result = NWXC220001.getDefDsOrdTpProcDfn(dsOrdTpProcDfnTMsg);

        if (result.hasError()) {
            hdrBean.addErrorInfo(result.getErrInfoList().get(0));
            return false;
        }

        hdrBean.dsOrdTpProcDfnTMsg = result.getResultObject();

        return true;
    }

    private static void setPrcCatgCd(NWAL2320CMsg bizMsg, NWAL2320_ImptHeaderBean hdrBean) {

        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxModeCd, "01");
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcCtxTpCd, PRC_CTX_TP.SALES_PRICING);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdCatgCd, hdrBean.me.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdTpCd, hdrBean.me.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd, hdrBean.dsOrdTpProcDfnTMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsAcctNum, hdrBean.me.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxPrcCatgOpFlg, ZYPConstant.FLG_ON_Y);

        new NWZC157001().execute(prcApiPMsg, ONBATCH_TYPE.ONLINE);
        if (addErrorInfoForEZDPMsg(hdrBean, prcApiPMsg) > 0) {
            return;
        }

        if (ZYPCommonFunc.hasValue(prcApiPMsg.defPrcCatgCd)) {
            ZYPEZDItemValueSetter.setValue(hdrBean.me.prcCatgCd, prcApiPMsg.defPrcCatgCd);
        } else {
            ZYPEZDItemValueSetter.setValue(hdrBean.me.prcCatgCd, prcApiPMsg.xxPrcList.no(0).prcCatgCd);
        }

    }

    private static boolean derivingHeaderDataForExist(NWAL2320CMsg bizMsg, NWAL2320_ImptHeaderBean hdrBean) {
        DS_IMPT_ORDTMsg imptTMsg = hdrBean.me;

        // Set Fixed Value
        hdrBean.setFixedValue();

        // Global Company Code
        ZYPEZDItemValueSetter.setValue(imptTMsg.glblCmpyCd, bizMsg.glblCmpyCd);

        // Order Source Import Date
        ZYPEZDItemValueSetter.setValue(imptTMsg.ordSrcImptTs, ZYPDateUtil.getCurrentSystemTime(S21SessionHelper.EZD_TIME_FORMAT));

        // Oracle Sequence
        BigDecimal pk;
        if (hdrBean.doInsertTable) {
           pk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_SQ);
           ZYPEZDItemValueSetter.setValue(imptTMsg.dsImptOrdPk, pk);
           ZYPEZDItemValueSetter.setValue(imptTMsg.ordSrcRefNum, NWAL2320Constant.ORD_SRC_REF_PREFIX + pk.toPlainString());
        }

        NWAL2320_BSMsg glblBSMsg;
        NWAL2320_DSMsg glblDSMsg;
        String cpoOrdNum;

        if (hdrBean.isUpldTpSlsOrd()) {
            glblBSMsg =  (NWAL2320_BSMsg) hdrBean.detailBeanList.get(0).rowData;
            cpoOrdNum = glblBSMsg.cpoOrdNum_EO.getValue();
        } else {
            glblDSMsg = (NWAL2320_DSMsg) hdrBean.rtrnDtlBeanList.get(0).rowData;
            cpoOrdNum = glblDSMsg.cpoOrdNum_ER.getValue();
        }

        hdrBean.cpoOrdNum = cpoOrdNum;

        //**********************************************************************
        // Get CPOTMsg
        //**********************************************************************
        CPOTMsg cpoTMsg = new CPOTMsg();
        ZYPEZDItemValueSetter.setValue(cpoTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoOrdNum, cpoOrdNum);

        cpoTMsg = (CPOTMsg) EZDTBLAccessor.findByKey(cpoTMsg);
        NWXC220001Util.checkTMsgDbAccess(cpoTMsg);

        if (cpoTMsg == null) {
            hdrBean.addErrorInfo(new NWXC220001ErrorInfo(NWAL2320Constant.NWAM0799E));
            return false;
        }
        hdrBean.cpoTMsg = cpoTMsg;

        // CPO Status Check
        if (NWXC220001Util.isTargetContents(cpoTMsg.ordHdrStsCd.getValue(), ORD_HDR_STS.CLOSED, ORD_HDR_STS.CANCELLED)) {
            hdrBean.addErrorInfo(new NWXC220001ErrorInfo(NWAL2320Constant.NWAM0862E));
            return false;
        }

        // Direct Sales Order Category Code
        ZYPEZDItemValueSetter.setValue(imptTMsg.dsOrdCatgCd, cpoTMsg.dsOrdCatgCd);
        // Direct Sales Order Type Code
        ZYPEZDItemValueSetter.setValue(imptTMsg.dsOrdTpCd, cpoTMsg.dsOrdTpCd);
        // QC#10490
        derivingDsORdTpProcDfnTMsg(hdrBean);

        // Direct Sales Order Reason Code
        ZYPEZDItemValueSetter.setValue(imptTMsg.dsOrdRsnCd, cpoTMsg.dsOrdRsnCd);
        // Customer Purchase Order Type Code
        ZYPEZDItemValueSetter.setValue(imptTMsg.cpoOrdTpCd, cpoTMsg.cpoOrdTpCd);
        // Customer Purchase Order Type Code
        ZYPEZDItemValueSetter.setValue(imptTMsg.custIssPoNum, cpoTMsg.custIssPoNum);
        // Customer Issue PO Date
        ZYPEZDItemValueSetter.setValue(imptTMsg.custIssPoDt, cpoTMsg.custIssPoDt);
        // Bill To Customer Account Code
        ZYPEZDItemValueSetter.setValue(imptTMsg.billToCustAcctCd, cpoTMsg.billToCustAcctCd);
        // Bill To Customer Code
        ZYPEZDItemValueSetter.setValue(imptTMsg.billToCustCd, cpoTMsg.billToCustCd);
        // Ship To Customer Account Code
        ZYPEZDItemValueSetter.setValue(imptTMsg.shipToCustAcctCd, cpoTMsg.shipToCustAcctCd);
        // Ship To Customer Code
        ZYPEZDItemValueSetter.setValue(imptTMsg.shipToCustCd, cpoTMsg.addShipToCustCd);
        // Sell To Customer Code
        ZYPEZDItemValueSetter.setValue(imptTMsg.sellToCustCd, cpoTMsg.sellToCustCd);
        // Sold To Customer Location Code
        ZYPEZDItemValueSetter.setValue(imptTMsg.soldToCustLocCd, cpoTMsg.soldToCustLocCd);
        // Drop Ship Flag
        ZYPEZDItemValueSetter.setValue(imptTMsg.dropShipFlg, cpoTMsg.addDropShipFlg);
        // Ship To Location Name
        ZYPEZDItemValueSetter.setValue(imptTMsg.shipToLocNm, cpoTMsg.addShipToLocNm);
        // Ship To Additional Location Name
        ZYPEZDItemValueSetter.setValue(imptTMsg.shipToAddlLocNm, cpoTMsg.addShipToAddlLocNm);
        // Ship To First Line Address
        ZYPEZDItemValueSetter.setValue(imptTMsg.shipToFirstLineAddr, cpoTMsg.addShipToFirstLineAddr);
        // Ship To Second Line Address
        ZYPEZDItemValueSetter.setValue(imptTMsg.shipToScdLineAddr, cpoTMsg.addShipToScdLineAddr);
        // Ship To Third Line Address
        ZYPEZDItemValueSetter.setValue(imptTMsg.shipToThirdLineAddr, cpoTMsg.addShipToThirdLineAddr);
        // Ship to Fourth Line Address
        ZYPEZDItemValueSetter.setValue(imptTMsg.shipToFrthLineAddr, cpoTMsg.addShipToFrthLineAddr);
        // Ship To City Address
        ZYPEZDItemValueSetter.setValue(imptTMsg.shipToCtyAddr, cpoTMsg.addShipToCtyAddr);
        // Ship To State Code
        ZYPEZDItemValueSetter.setValue(imptTMsg.shipToStCd, cpoTMsg.addShipToStCd);
        // Ship to Province Name
        ZYPEZDItemValueSetter.setValue(imptTMsg.shipToProvNm, cpoTMsg.addShipToProvNm);
        // Ship To County Name
        ZYPEZDItemValueSetter.setValue(imptTMsg.shipToCntyNm, cpoTMsg.addShipToCntyNm);
        // Ship To Postal Code
        ZYPEZDItemValueSetter.setValue(imptTMsg.shipToPostCd, cpoTMsg.addShipToPostCd);
        // Ship To Country Code
        ZYPEZDItemValueSetter.setValue(imptTMsg.shipToCtryCd, cpoTMsg.addShipToCtryCd);
        // Ship to 01 Reference Comment Text
        ZYPEZDItemValueSetter.setValue(imptTMsg.shipTo01RefCmntTxt, cpoTMsg.addShipTo01RefCmntTxt);
        // Ship to 02 Reference Comment Text
        ZYPEZDItemValueSetter.setValue(imptTMsg.shipTo02RefCmntTxt, cpoTMsg.addShipTo02RefCmntTxt);
        // Admin Person Code
        ZYPEZDItemValueSetter.setValue(imptTMsg.adminPsnCd, cpoTMsg.adminPsnCd);
        // RDD Date
        ZYPEZDItemValueSetter.setValue(imptTMsg.rddDt, cpoTMsg.addRddDt);
        // Freight Condition Code
        ZYPEZDItemValueSetter.setValue(imptTMsg.frtCondCd, cpoTMsg.frtCondCd);
        // Carrier Service Level Code
        ZYPEZDItemValueSetter.setValue(imptTMsg.carrSvcLvlCd, cpoTMsg.carrSvcLvlCd);
        // Shipping Service Level Code
        ZYPEZDItemValueSetter.setValue(imptTMsg.shpgSvcLvlCd, cpoTMsg.addShpgSvcLvlCd);
        // Carrier Account Number
        ZYPEZDItemValueSetter.setValue(imptTMsg.carrAcctNum, cpoTMsg.carrAcctNum);
        // Add Payment Term Cash Discount Code
        ZYPEZDItemValueSetter.setValue(imptTMsg.addPmtTermCashDiscCd, cpoTMsg.addPmtTermCashDiscCd);
        // Direct Sales Payment Method Code
        ZYPEZDItemValueSetter.setValue(imptTMsg.dsPmtMethCd, cpoTMsg.dsPmtMethCd);
        // Pre Payment Check Number
        ZYPEZDItemValueSetter.setValue(imptTMsg.prePmtChkNum, cpoTMsg.prePmtChkNum);
        // Pre Payment Amount
        ZYPEZDItemValueSetter.setValue(imptTMsg.prePmtAmt, cpoTMsg.prePmtAmt);
        // Pre Payment Type Code
        ZYPEZDItemValueSetter.setValue(imptTMsg.prePmtTpCd, cpoTMsg.prePmtTpCd);
        // Price Base Date
        ZYPEZDItemValueSetter.setValue(imptTMsg.prcBaseDt, cpoTMsg.prcBaseDt);
        // Price Category Code
        ZYPEZDItemValueSetter.setValue(imptTMsg.prcCatgCd, cpoTMsg.prcCatgCd);
        // Floor Price List Code
        ZYPEZDItemValueSetter.setValue(imptTMsg.flPrcListCd, cpoTMsg.flPrcListCd);
        // Price Contract Number
        ZYPEZDItemValueSetter.setValue(imptTMsg.prcContrNum, cpoTMsg.prcContrNum);
        // Canon Strategic Marketing Program Contract Number
        ZYPEZDItemValueSetter.setValue(imptTMsg.csmpContrNum, cpoTMsg.csmpContrNum);
        // Dealer Reference Number
        ZYPEZDItemValueSetter.setValue(imptTMsg.dlrRefNum, cpoTMsg.dlrRefNum);
        // Order Sign Date
        ZYPEZDItemValueSetter.setValue(imptTMsg.ordSgnDt, cpoTMsg.ordSgnDt);
        // Acquisition Number
        ZYPEZDItemValueSetter.setValue(imptTMsg.aquNum, cpoTMsg.aquNum);
        // Sales Rep TOC Code
        ZYPEZDItemValueSetter.setValue(imptTMsg.slsRepTocCd, cpoTMsg.slsRepTocCd);
        // Loan Period Days Amount of Time
        ZYPEZDItemValueSetter.setValue(imptTMsg.loanPerDaysAot, cpoTMsg.loanPerDaysAot);
        // Lease Company PO Number
        ZYPEZDItemValueSetter.setValue(imptTMsg.leaseCmpyPoNum, cpoTMsg.leaseCmpyPoNum);
        // Lease End of Term Purchase Option Code
        ZYPEZDItemValueSetter.setValue(imptTMsg.leasePrchOptCd, cpoTMsg.leasePrchOptCd);
        // Lease Term Code
        ZYPEZDItemValueSetter.setValue(imptTMsg.leaseTermCd, cpoTMsg.leaseTermCd);
        // Lease Payment Frequency Code
        ZYPEZDItemValueSetter.setValue(imptTMsg.leasePmtFreqCd, cpoTMsg.leasePmtFreqCd);
        // Order Log Type Code
        ZYPEZDItemValueSetter.setValue(imptTMsg.ordLogTpCd, cpoTMsg.ordLogTpCd);
        // Credit Rebill Reason Category Code
        ZYPEZDItemValueSetter.setValue(imptTMsg.crRebilRsnCatgCd, cpoTMsg.crRebilRsnCatgCd);
        // Original Order Number
        ZYPEZDItemValueSetter.setValue(imptTMsg.origOrdNum, hdrBean.cpoOrdNum);

        // Maintenance Only Flag
        ZYPEZDItemValueSetter.setValue(imptTMsg.maintOnlyFlg, ZYPConstant.FLG_OFF_N); // QC#16266

        // 2018/04/25 QC#22189 Add Start
        // Negotiated Deal Amount
        ZYPEZDItemValueSetter.setValue(imptTMsg.negoDealAmt, cpoTMsg.negoDealAmt); // QC#22189
        // 2018/04/25 QC#22189 Add End

        return !hdrBean.hasError();
    }

    private static String getSlsRepRoleTpCd(String lineBizTpCd) {
        String slsRepRoleTpCd;

        if (LINE_BIZ_TP.ESS.equals(lineBizTpCd)) {
            slsRepRoleTpCd = LINE_BIZ_ROLE_TP.ESS_WRITER;
        } else if (LINE_BIZ_TP.LFS.equals(lineBizTpCd)) {
            slsRepRoleTpCd = LINE_BIZ_ROLE_TP.LFS_WRITER;
        } else {
            slsRepRoleTpCd = LINE_BIZ_ROLE_TP.PPS_WRITER;
        }

        return slsRepRoleTpCd;
    }

    private static void derivingConfigDtlData(NWAL2320CMsg bizMsg, NWAL2320_ImptHeaderBean hdrBean) {
        DS_IMPT_ORDTMsg imptTMsg = hdrBean.me;
        DS_IMPT_ORD_CONFIGTMsg configTMsg;
        BigDecimal dsOrdPosnNumIn = BigDecimal.ZERO;    // Inbound
        BigDecimal dsOrdPosnNumOut = BigDecimal.ZERO;   // Outbound
        String  configCratTs = ZYPDateUtil.getCurrentSystemTime(S21SessionHelper.EZD_TIME_FORMAT);
//        String maxLineNum;

        // DS Order Position Number / OrdSrcRefLineNum
        if (!hdrBean.isUpldTpNew()) {
            // Outbound
            S21SsmEZDResult ssmResult = NWAL2320Query.getInstance().getMaxDsOrdPosnNum(imptTMsg.glblCmpyCd.getValue(), hdrBean.cpoOrdNum, CONFIG_CATG.OUTBOUND);

            if (ssmResult.isCodeNormal()) {
                dsOrdPosnNumOut = NWXC220001Util.convToBigDecimal((String) ssmResult.getResultObject(), BigDecimal.ZERO);
            }

            // S21_NA#11852
//            ssmResult = NWAL2320Query.getInstance().getMaxCpoDtlLineNum(imptTMsg.glblCmpyCd.getValue(), hdrBean.cpoOrdNum);
//            if (ssmResult.isCodeNormal()) {
//                maxLineNum = (String) ssmResult.getResultObject();
//                if (ZYPCommonFunc.hasValue(maxLineNum)) {
//                    hdrBean.lastOrdSrcRefLineNumOut = NWXC220001Util.convZZ9ToNum(maxLineNum);
//                }
//            }

            // Inbound
            ssmResult = NWAL2320Query.getInstance().getMaxDsOrdPosnNum(imptTMsg.glblCmpyCd.getValue(), hdrBean.cpoOrdNum, CONFIG_CATG.INBOUND);

            if (ssmResult.isCodeNormal()) {
                dsOrdPosnNumIn = NWXC220001Util.convToBigDecimal((String) ssmResult.getResultObject(), BigDecimal.ZERO);
            }

            // S21_NA#11852
//            ssmResult = NWAL2320Query.getInstance().getMaxDsCpoRtrnLineNum(imptTMsg.glblCmpyCd.getValue(), hdrBean.cpoOrdNum);
//            if (ssmResult.isCodeNormal()) {
//                maxLineNum = (String) ssmResult.getResultObject();
//                if (ZYPCommonFunc.hasValue(maxLineNum)) {
//                    hdrBean.lastOrdSrcRefLineNumIn = NWXC220001Util.convZZ9ToNum(maxLineNum);
//                }
//            }
        }

        BILL_TO_CUSTTMsg billToCustTMsg;
        SHIP_TO_CUSTTMsg shipToCustTMsg;
        NWXC220001Result<NWXC220001CustomerBean> billInfo;
        String billToCustLocCd, shipToCustLocCd;
        NWAL2320_BSMsg glblBSMsgTop;
        NWAL2320_DSMsg glblDSMsgTopMsg;
        S21SsmEZDResult ssmResult;
        String resultString;
        Map< ? ,  ? > resultMap;
        for (NWAL2320_ImptConfigBean configBean : hdrBean.configBeanMap.values()) {
            configTMsg = configBean.me;

            // Global Company Code
            ZYPEZDItemValueSetter.setValue(configTMsg.glblCmpyCd, bizMsg.glblCmpyCd);

            // DS Import Order Config Primary Key
            BigDecimal pk;
            if (hdrBean.doInsertTable) {
               pk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_CONFIG_SQ);
               ZYPEZDItemValueSetter.setValue(configTMsg.dsImptOrdConfigPk, pk);
            }

            // Direct Sales Import Order PK
            ZYPEZDItemValueSetter.setValue(configTMsg.dsImptOrdPk, hdrBean.me.dsImptOrdPk);

            // QC#10087
            // Configuration Category Code
            ZYPEZDItemValueSetter.setValue(configTMsg.configCatgCd, hdrBean.isUpldTpSlsOrd() ? CONFIG_CATG.OUTBOUND : CONFIG_CATG.INBOUND);

            // DS Order Position Number
            if (configBean.isOutbound()) {
                dsOrdPosnNumOut = dsOrdPosnNumOut.add(BigDecimal.ONE);
                ZYPEZDItemValueSetter.setValue(configTMsg.dsOrdPosnNum, dsOrdPosnNumOut.toPlainString());
            } else {
                dsOrdPosnNumIn = dsOrdPosnNumIn.add(BigDecimal.ONE);
                ZYPEZDItemValueSetter.setValue(configTMsg.dsOrdPosnNum, dsOrdPosnNumIn.toPlainString());
            }

            // Configuration Type Code
            ZYPEZDItemValueSetter.setValue(configTMsg.configTpCd, hdrBean.isUpldTpSlsOrd() ? CONFIG_TP.NEW : CONFIG_TP.RETURN_EXISTING_IB);

            // Customer Info
            // QC#10251
            if (hdrBean.isUpldTpNew()) {
                ZYPEZDItemValueSetter.setValue(configTMsg.billToCustAcctCd, imptTMsg.billToCustAcctCd);
                ZYPEZDItemValueSetter.setValue(configTMsg.billToCustLocCd, imptTMsg.billToCustCd);
                ZYPEZDItemValueSetter.setValue(configTMsg.shipToCustAcctCd, imptTMsg.shipToCustAcctCd);
                ZYPEZDItemValueSetter.setValue(configTMsg.shipToCustLocCd, imptTMsg.shipToCustCd);
                ZYPEZDItemValueSetter.setValue(configTMsg.dropShipFlg, imptTMsg.dropShipFlg);
                ZYPEZDItemValueSetter.setValue(configTMsg.shipToLocNm, imptTMsg.shipToLocNm);
                ZYPEZDItemValueSetter.setValue(configTMsg.shipToAddlLocNm, imptTMsg.shipToAddlLocNm);
                ZYPEZDItemValueSetter.setValue(configTMsg.shipToFirstLineAddr, imptTMsg.shipToFirstLineAddr);
                ZYPEZDItemValueSetter.setValue(configTMsg.shipToScdLineAddr, imptTMsg.shipToScdLineAddr);
                ZYPEZDItemValueSetter.setValue(configTMsg.shipToThirdLineAddr, imptTMsg.shipToThirdLineAddr);
                ZYPEZDItemValueSetter.setValue(configTMsg.shipToFrthLineAddr, imptTMsg.shipToFrthLineAddr);
                ZYPEZDItemValueSetter.setValue(configTMsg.shipToFirstRefCmntTxt, imptTMsg.shipTo01RefCmntTxt);
                ZYPEZDItemValueSetter.setValue(configTMsg.shipToScdRefCmntTxt, imptTMsg.shipTo02RefCmntTxt);
                ZYPEZDItemValueSetter.setValue(configTMsg.shipToCtyAddr, imptTMsg.shipToCtyAddr);
                ZYPEZDItemValueSetter.setValue(configTMsg.shipToStCd, imptTMsg.shipToStCd);
                ZYPEZDItemValueSetter.setValue(configTMsg.shipToProvNm, imptTMsg.shipToProvNm);
                ZYPEZDItemValueSetter.setValue(configTMsg.shipToCntyNm, imptTMsg.shipToCntyNm);
                ZYPEZDItemValueSetter.setValue(configTMsg.shipToPostCd, imptTMsg.shipToPostCd);
                ZYPEZDItemValueSetter.setValue(configTMsg.shipToCtryCd, imptTMsg.shipToCtryCd);
            } else {
                // Bill To Location
                billToCustTMsg = new BILL_TO_CUSTTMsg();
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.glblCmpyCd, configTMsg.glblCmpyCd);
                if (configBean.isOutbound()) {
                    billToCustLocCd = configBean.<NWAL2320_BSMsg>getDetailSMsg(0).billToCustLocCd_EO.getValue();
                } else {
                    billToCustLocCd = configBean.<NWAL2320_DSMsg>getDetailSMsg(0).billToCustLocCd_ER.getValue();
                }
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.billToCustCd,  billToCustLocCd);
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);

                billInfo = NWXC220001.getBillToCustWithDsAcctFromCd(billToCustTMsg, hdrBean.slsDt, CUSTOMER_TABLE_ID.BILL_TO_CUST);

                if (billInfo.hasResultObject()) {
                    billToCustTMsg = billInfo.getResultObject().billToCustTMsg;
                    ZYPEZDItemValueSetter.setValue(configTMsg.billToCustAcctCd, billToCustTMsg.sellToCustCd);
                    ZYPEZDItemValueSetter.setValue(configTMsg.billToCustLocCd, billToCustLocCd);
                } else {
                    configBean.addErrorInfo(new NWXC220001ErrorInfo(NWAL2320Constant.NWAM0181E, TMPL_DL_HEADER_FOR_NEW_ORDER[11]));
                }

                // Ship To Location
                shipToCustTMsg = new SHIP_TO_CUSTTMsg();
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.glblCmpyCd, configTMsg.glblCmpyCd);
                if (configBean.isOutbound()) {
                    shipToCustLocCd = configBean.<NWAL2320_BSMsg>getDetailSMsg(0).shipToCustLocCd_EO.getValue();
                } else {
                    shipToCustLocCd = configBean.<NWAL2320_DSMsg>getDetailSMsg(0).shipToCustLocCd_ER.getValue();
                }

                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.shipToCustCd, shipToCustLocCd);
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
                NWXC220001Result<NWXC220001CustomerBean> shipInfo = NWXC220001.getShipToCustWithDsAcctFromCd(shipToCustTMsg, hdrBean.slsDt, CUSTOMER_TABLE_ID.SHIP_TO_CUST);

                if (shipInfo.hasResultObject()) {
                    shipToCustTMsg = shipInfo.getResultObject().shipToCustTMsg;
                    ZYPEZDItemValueSetter.setValue(configTMsg.shipToCustAcctCd, shipToCustTMsg.sellToCustCd);
                    ZYPEZDItemValueSetter.setValue(configTMsg.shipToCustLocCd, shipToCustLocCd);
                    ZYPEZDItemValueSetter.setValue(configTMsg.dropShipFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(configTMsg.shipToLocNm, shipToCustTMsg.locNm);
                    ZYPEZDItemValueSetter.setValue(configTMsg.shipToAddlLocNm, shipToCustTMsg.addlLocNm);
                    ZYPEZDItemValueSetter.setValue(configTMsg.shipToFirstLineAddr, shipToCustTMsg.firstLineAddr);
                    ZYPEZDItemValueSetter.setValue(configTMsg.shipToScdLineAddr, shipToCustTMsg.scdLineAddr);
                    ZYPEZDItemValueSetter.setValue(configTMsg.shipToThirdLineAddr, shipToCustTMsg.thirdLineAddr);
                    ZYPEZDItemValueSetter.setValue(configTMsg.shipToFrthLineAddr, shipToCustTMsg.frthLineAddr);
                    ZYPEZDItemValueSetter.setValue(configTMsg.shipToCtyAddr, shipToCustTMsg.ctyAddr);
                    ZYPEZDItemValueSetter.setValue(configTMsg.shipToStCd, shipToCustTMsg.stCd);
                    ZYPEZDItemValueSetter.setValue(configTMsg.shipToProvNm, shipToCustTMsg.provNm);
                    ZYPEZDItemValueSetter.setValue(configTMsg.shipToCntyNm, shipInfo.getResultObject().shipCntyNm);
                    ZYPEZDItemValueSetter.setValue(configTMsg.shipToPostCd, shipToCustTMsg.postCd);
                    ZYPEZDItemValueSetter.setValue(configTMsg.shipToCtryCd, shipToCustTMsg.ctryCd);
                    ZYPEZDItemValueSetter.setValue(configTMsg.shipToFirstRefCmntTxt, shipToCustTMsg.firstRefCmntTxt);
                    ZYPEZDItemValueSetter.setValue(configTMsg.shipToScdRefCmntTxt, shipToCustTMsg.scdRefCmntTxt);
                } else {
                    configBean.addErrorInfo(new NWXC220001ErrorInfo(NWAL2320Constant.NWAM0181E, TMPL_DL_HEADER_FOR_NEW_ORDER[12]));
                }

                // QC#10490
                // Sales Rep
                if (configBean.isOutbound()) {
                    glblBSMsgTop = configBean.<NWAL2320_BSMsg>getDetailSMsg(0);
                    if (!ZYPCommonFunc.hasValue(glblBSMsgTop.slsRepTocCd_EO)) {
                        ssmResult = NWAL2320Query.getInstance().getTocInfo(imptTMsg.glblCmpyCd.getValue(), glblBSMsgTop.tocNm_EO.getValue());
                        if (checkAndAddMasterNotFoundError(hdrBean, ssmResult, TMPL_DL_HEADER_FOR_EXS_ORDER[8])) {
                            resultMap = (Map< ? , ? >) ssmResult.getResultObject();
                            resultString = (String) resultMap.get("TOC_CD");
                            ZYPEZDItemValueSetter.setValue(glblBSMsgTop.slsRepTocCd_EO, resultString);
                            ZYPEZDItemValueSetter.setValue(glblBSMsgTop.coaExtnCd_EO, (String) resultMap.get("COA_EXTN_CD"));
                        }
                    }
                    if (hdrBean.dsOrdTpProcDfnTMsg != null) {
                        ZYPEZDItemValueSetter.setValue(glblBSMsgTop.slsRepRoleTpCd_EO, getSlsRepRoleTpCd(hdrBean.dsOrdTpProcDfnTMsg.lineBizTpCd.getValue()));
                    }
                } else {
                    glblDSMsgTopMsg = configBean.<NWAL2320_DSMsg>getDetailSMsg(0);
                    if (!ZYPCommonFunc.hasValue(glblDSMsgTopMsg.slsRepTocCd_ER)) {
                        ssmResult = NWAL2320Query.getInstance().getTocInfo(imptTMsg.glblCmpyCd.getValue(), glblDSMsgTopMsg.tocNm_ER.getValue());
                        if (checkAndAddMasterNotFoundError(hdrBean, ssmResult, TMPL_DL_HEADER_FOR_EXS_RMA[9])) {
                            resultMap = (Map< ? , ? >) ssmResult.getResultObject();
                            resultString = (String) resultMap.get("TOC_CD");
                            ZYPEZDItemValueSetter.setValue(glblDSMsgTopMsg.slsRepTocCd_ER, resultString);
                            ZYPEZDItemValueSetter.setValue(glblDSMsgTopMsg.coaExtnCd_ER, (String) resultMap.get("COA_EXTN_CD"));
                        }
                    }
                    if (hdrBean.dsOrdTpProcDfnTMsg != null) {
                        ZYPEZDItemValueSetter.setValue(glblDSMsgTopMsg.slsRepRoleTpCd_ER, getSlsRepRoleTpCd(hdrBean.dsOrdTpProcDfnTMsg.lineBizTpCd.getValue()));
                    }
                }
            }

            // Config Create Timestamp
            ZYPEZDItemValueSetter.setValue(configTMsg.configCratTs, configCratTs);

            //******************************************************************
            // DS_IMPT_ORD_DELY_INFO
            //******************************************************************
            derivingDelyInfo(bizMsg, configBean);

            //******************************************************************
            // DS_IMPT_ORD_SLS_CR
            //******************************************************************
            derivingSlsCr(bizMsg, configBean);

            //******************************************************************
            // DS_IMPT_ORD_DTL / DS_IMPT_ORD_RTRN_DTL
            //******************************************************************
            if (configBean.headerBean.isUpldTpSlsOrd()) {
                derivingDetailData(bizMsg, configBean);
            } else {
                derivingRtnDetailData(bizMsg, configBean);
            }

            if (configBean.isOutbound()) {
                // Model Info
                derivingModelInfo(bizMsg, configBean);
            }

        }
    }

    private static void derivingModelInfo(NWAL2320CMsg bizMsg, NWAL2320_ImptConfigBean configBean) {
        List<String> mdseList = new ArrayList<String>();

        for (NWAL2320_ImptDetailBean dtlBean : configBean.detailBeanList) {
            mdseList.add(dtlBean.me.mdseCd.getValue());
        }

        // Get Base Component
        Map<String, List<String>> baseCmpntMap = NWXC220001.getBaseComponent(bizMsg.glblCmpyCd.getValue(), mdseList, null);

        List<String> baseMdseList = baseCmpntMap.get(NWXC220001Constant.BASE_MDSE);
        if (baseMdseList.size() == 0) {
            return;
        }

        // Set Api Parameters 
        NSZC048001PMsg svcMdlApiPMsg = new NSZC048001PMsg();
        ZYPEZDItemValueSetter.setValue(svcMdlApiPMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcMdlApiPMsg.slsDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(svcMdlApiPMsg.prntMdseCd, baseMdseList.get(0));
        NSZC048001_xxChildMdseCdListPMsgArray childMdseCdList = svcMdlApiPMsg.xxChildMdseCdList;

        int idx = 0;
        if (NWXC220001Util.hasValueList(baseCmpntMap.get(NWXC220001Constant.CHILD_MDSE))) {
            for (String mdse : baseCmpntMap.get(NWXC220001Constant.CHILD_MDSE)) {
                ZYPEZDItemValueSetter.setValue(childMdseCdList.no(idx).childMdseCd, mdse);
                idx++;
                // QC#11629
                if (idx >= childMdseCdList.length()) {
                    break;
                }
            }
        }
        svcMdlApiPMsg.xxChildMdseCdList.setValidCount(idx);

        // Call Api
        // call NSZC0480 Service Model API
        new NSZC048001().execute(svcMdlApiPMsg, ONBATCH_TYPE.ONLINE);
        if (NWXC220001ErrorInfoHelper.getErrorInfoForEZDPMsg(svcMdlApiPMsg).size() == 0) {
            ZYPEZDItemValueSetter.setValue(configBean.me.mdlId, svcMdlApiPMsg.mdlId);

            DS_MDLTMsg dsMdlTMsg = new DS_MDLTMsg();
            ZYPEZDItemValueSetter.setValue(dsMdlTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsMdlTMsg.mdlId, svcMdlApiPMsg.mdlId);

            dsMdlTMsg = (DS_MDLTMsg) EZDTBLAccessor.findByKey(dsMdlTMsg);
            NWXC220001Util.checkTMsgDbAccess(dsMdlTMsg);

            if (dsMdlTMsg != null) {
                ZYPEZDItemValueSetter.setValue(configBean.me.mdlDescTxt, dsMdlTMsg.mdlDescTxt);
            }
        }
    }

    private static void derivingDelyInfo(NWAL2320CMsg bizMsg, NWAL2320_ImptConfigBean configBean) {
        DS_IMPT_ORD_CONFIGTMsg configTMsg = configBean.me;
        DS_IMPT_ORD_DELY_INFOTMsg delyInfoTMsg = configBean.delyInfoTMsg;

        // DS Import Order Delivery Information PK
        BigDecimal pk;
        if (configBean.headerBean.doInsertTable) {
           pk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_DELY_INFO_SQ);
           ZYPEZDItemValueSetter.setValue(delyInfoTMsg.dsImptOrdDelyInfoPk, pk);
        }

        ZYPEZDItemValueSetter.setValue(delyInfoTMsg.glblCmpyCd, configTMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(delyInfoTMsg.dsImptOrdPk, configTMsg.dsImptOrdPk);
        ZYPEZDItemValueSetter.setValue(delyInfoTMsg.dsImptOrdConfigPk, configTMsg.dsImptOrdConfigPk);
        ZYPEZDItemValueSetter.setValue(delyInfoTMsg.dsDelyTpCd, DS_DELY_TP.INSTALLATION);
        ZYPEZDItemValueSetter.setValue(delyInfoTMsg.loadDockAvalFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(delyInfoTMsg.stairCrawReqFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(delyInfoTMsg.elevAvalFlg, ZYPConstant.FLG_OFF_N);

        String ordUpldTmplTpCd = bizMsg.ordUpldTmplTpCd.getValue();
        String delyAddlCmntTxt = "";

        if (ORD_UPLD_TMPL_TP.NEW_SALES_ORDER.equals(ordUpldTmplTpCd)) {
            delyAddlCmntTxt = configBean.<NWAL2320_ASMsg>getDetailSMsg(0).xxShpgOrdCmntTxt_CO.getValue();
        } else if (ORD_UPLD_TMPL_TP.EXISTING_SALES_ORDER.equals(ordUpldTmplTpCd)) {
            delyAddlCmntTxt = configBean.<NWAL2320_BSMsg>getDetailSMsg(0).xxShpgOrdCmntTxt_EO.getValue();
        } else if (ORD_UPLD_TMPL_TP.NEW_RMA_ORDER.equals(ordUpldTmplTpCd)) {
            delyAddlCmntTxt = configBean.<NWAL2320_CSMsg>getDetailSMsg(0).xxShpgOrdCmntTxt_NR.getValue();
        } else if (ORD_UPLD_TMPL_TP.EXISTING_RMA_ORDER.equals(ordUpldTmplTpCd)) {
            delyAddlCmntTxt = configBean.<NWAL2320_DSMsg>getDetailSMsg(0).xxShpgOrdCmntTxt_ER.getValue();
        }

        ZYPEZDItemValueSetter.setValue(delyInfoTMsg.delyAddlCmntTxt, delyAddlCmntTxt);
    }

    private static void derivingSlsCr(NWAL2320CMsg bizMsg, NWAL2320_ImptConfigBean configBean) {
        DS_IMPT_ORD_CONFIGTMsg configTMsg = configBean.me;
        DS_IMPT_ORD_SLS_CRTMsg slsCrTMsg = configBean.slsCrTMsg;

        // Direct Sales Import Order Sales Credit Primary Key
        BigDecimal pk;
        if (configBean.headerBean.doInsertTable) {
           pk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_SLS_CR_SQ);
           ZYPEZDItemValueSetter.setValue(slsCrTMsg.dsImptOrdSlsCrPk, pk);
        }

        ZYPEZDItemValueSetter.setValue(slsCrTMsg.glblCmpyCd, configTMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(slsCrTMsg.dsImptOrdPk, configTMsg.dsImptOrdPk);

        if (!configBean.headerBean.isUpldTpNew()) {
            ZYPEZDItemValueSetter.setValue(slsCrTMsg.dsImptOrdConfigPk, configTMsg.dsImptOrdConfigPk);
        }

        String ordUpldTmplTpCd = bizMsg.ordUpldTmplTpCd.getValue();
        String slsRepTocCd = "";
        String slsRepRoleTpCd = "";

        if (ORD_UPLD_TMPL_TP.NEW_SALES_ORDER.equals(ordUpldTmplTpCd)) {
            NWAL2320_ASMsg glblASMsg = configBean.<NWAL2320_ASMsg>getDetailSMsg(0);
            slsRepTocCd = glblASMsg.slsRepTocCd_CO.getValue();
            slsRepRoleTpCd = glblASMsg.slsRepRoleTpCd_CO.getValue();
        } else if (ORD_UPLD_TMPL_TP.EXISTING_SALES_ORDER.equals(ordUpldTmplTpCd)) {
            NWAL2320_BSMsg glblBSMsg = configBean.<NWAL2320_BSMsg>getDetailSMsg(0);
            slsRepTocCd = glblBSMsg.slsRepTocCd_EO.getValue();
            slsRepRoleTpCd = glblBSMsg.slsRepRoleTpCd_EO.getValue();
        } else if (ORD_UPLD_TMPL_TP.NEW_RMA_ORDER.equals(ordUpldTmplTpCd)) {
            NWAL2320_CSMsg glblCSMsg = configBean.<NWAL2320_CSMsg>getDetailSMsg(0);
            slsRepTocCd = glblCSMsg.slsRepTocCd_NR.getValue();
            slsRepRoleTpCd = glblCSMsg.slsRepRoleTpCd_NR.getValue();
        } else if (ORD_UPLD_TMPL_TP.EXISTING_RMA_ORDER.equals(ordUpldTmplTpCd)) {
            NWAL2320_DSMsg glblDSMsg = configBean.<NWAL2320_DSMsg>getDetailSMsg(0);
            slsRepTocCd = glblDSMsg.slsRepTocCd_ER.getValue();
            slsRepRoleTpCd = glblDSMsg.slsRepRoleTpCd_ER.getValue();
        }
        ZYPEZDItemValueSetter.setValue(slsCrTMsg.slsRepTocCd, slsRepTocCd);
        ZYPEZDItemValueSetter.setValue(slsCrTMsg.slsRepRoleTpCd, slsRepRoleTpCd);
        ZYPEZDItemValueSetter.setValue(slsCrTMsg.slsRepCrPct, BigDecimal.valueOf(100));
        ZYPEZDItemValueSetter.setValue(slsCrTMsg.slsCrQuotFlg, ZYPConstant.FLG_ON_Y);
    }

    private static void derivingDetailData(NWAL2320CMsg bizMsg, NWAL2320_ImptConfigBean configBean) {
        NWAL2320_ImptHeaderBean hdrBean = configBean.headerBean;
        DS_IMPT_ORDTMsg imptTMsg = hdrBean.me;
        DS_IMPT_ORD_CONFIGTMsg configTMsg;
        DS_IMPT_ORD_DTLTMsg dtlTMsg;
        BigDecimal pk;
        S21SsmEZDResult ssmResult;
        String resultString;
        NWXC220001Result<MDSE_STORE_PKGTMsg> mdseStorePkgResult;
        int dtlCnt = 0;

        for (NWAL2320_ImptDetailBean detailBean : configBean.detailBeanList) {
            configTMsg = configBean.me;
            dtlTMsg = detailBean.me;

            // Global Company Code
            ZYPEZDItemValueSetter.setValue(dtlTMsg.glblCmpyCd, bizMsg.glblCmpyCd);

            // Direct Sales Import Order Detail PK
            if (configBean.headerBean.doInsertTable) {
               pk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_DTL_SQ);
               ZYPEZDItemValueSetter.setValue(dtlTMsg.dsImptOrdDtlPk, pk);
            }

            // Direct Sales Import Order PK
            ZYPEZDItemValueSetter.setValue(dtlTMsg.dsImptOrdPk, imptTMsg.dsImptOrdPk);

            // Order Source Reference Line Number
            hdrBean.lastOrdSrcRefLineNumOut++;
            ZYPEZDItemValueSetter.setValue(dtlTMsg.ordSrcRefLineNum, NWXC220001Util.convNumToZ99(hdrBean.lastOrdSrcRefLineNumOut));

            // Order Source Reference Line Sub Number
            ZYPEZDItemValueSetter.setValue(dtlTMsg.ordSrcRefLineSubNum, "001");

            if (hdrBean.isUpldTpNew()) {
                NWAL2320_ASMsg glblASMsg = configBean.<NWAL2320_ASMsg>getDetailSMsg(dtlCnt);

                // MDSE Info
                ZYPEZDItemValueSetter.setValue(dtlTMsg.mdseCd, glblASMsg.mdseCd_CO);
                if (!ZYPCommonFunc.hasValue(glblASMsg.mdseNm_CO)) {
                    ssmResult = NWAL2320Query.getInstance().getMdseNm(imptTMsg.glblCmpyCd.getValue(), glblASMsg.mdseCd_CO.getValue());
                    if (checkAndAddMasterNotFoundError(detailBean, ssmResult, TMPL_DL_HEADER_FOR_NEW_ORDER[6])) {
                        resultString = (String) ssmResult.getResultObject();
                        ZYPEZDItemValueSetter.setValue(glblASMsg.mdseNm_CO, resultString);
                    }
                }
                ZYPEZDItemValueSetter.setValue(dtlTMsg.mdseNm, glblASMsg.mdseNm_CO);

                // Line Category
                if (!ZYPCommonFunc.hasValue(glblASMsg.dsOrdLineCatgCd_CO)) {
                    ssmResult = NWAL2320Query.getInstance().getDsOrdLineCatgCd(imptTMsg.glblCmpyCd.getValue(), glblASMsg.dsOrdLineCatgDescTxt_CO.getValue());
                    if (checkAndAddMasterNotFoundError(detailBean, ssmResult, TMPL_DL_HEADER_FOR_NEW_ORDER[10])) {
                        resultString = (String) ssmResult.getResultObject();
                        ZYPEZDItemValueSetter.setValue(glblASMsg.dsOrdLineCatgCd_CO, resultString);
                    }
                }
                ZYPEZDItemValueSetter.setValue(dtlTMsg.dsOrdLineCatgCd, glblASMsg.dsOrdLineCatgCd_CO);

                // Warehouse
                //if (!ZYPCommonFunc.hasValue(glblASMsg.rtlWhCd_CO)) { // 2019/03/25 S21_NA#30924 Mod
                if (!ZYPCommonFunc.hasValue(glblASMsg.rtlWhCd_CO) && !isNoInvtyItem(bizMsg.glblCmpyCd.getValue(), dtlTMsg.mdseCd.getValue())) {
                    ssmResult = NWAL2320Query.getInstance().getRtlWhCd(imptTMsg.glblCmpyCd.getValue(), glblASMsg.rtlWhNm_CO.getValue());
                    // 2017/01/05 S21_NA#16757 Mod Start
//                    if (checkAndAddMasterNotFoundError(detailBean, ssmResult, TMPL_DL_HEADER_FOR_NEW_ORDER[14])) {
                    if (checkAndAddMasterNotFoundError(detailBean, ssmResult, TMPL_DL_HEADER_FOR_NEW_ORDER[15])) {
                    // 2017/01/05 S21_NA#16757 Mod End
                        resultString = (String) ssmResult.getResultObject();
                        ZYPEZDItemValueSetter.setValue(glblASMsg.rtlWhCd_CO, resultString);
                    }
                }
                ZYPEZDItemValueSetter.setValue(dtlTMsg.rtlWhCd, glblASMsg.rtlWhCd_CO);

                // Sub Warehouse
                // if (!ZYPCommonFunc.hasValue(glblASMsg.rtlSwhCd_CO)) { // 2019/03/25 S21_NA#30924 Mod
                if (!ZYPCommonFunc.hasValue(glblASMsg.rtlSwhCd_CO) && !isNoInvtyItem(bizMsg.glblCmpyCd.getValue(), dtlTMsg.mdseCd.getValue())) {    
                    ssmResult = NWAL2320Query.getInstance().getRtlSwhCd(imptTMsg.glblCmpyCd.getValue(), glblASMsg.rtlSwhNm_CO.getValue());
                    //if (checkAndAddMasterNotFoundError(detailBean, ssmResult, TMPL_DL_HEADER_FOR_NEW_ORDER[15])) {
                        resultString = (String) ssmResult.getResultObject();
                        ZYPEZDItemValueSetter.setValue(glblASMsg.rtlSwhCd_CO, resultString);
                    //}
                }
                ZYPEZDItemValueSetter.setValue(dtlTMsg.rtlSwhCd, glblASMsg.rtlSwhCd_CO);

                // Order Quantity
                ZYPEZDItemValueSetter.setValue(dtlTMsg.ordQty, glblASMsg.ordQty_CO);
                // Ordered Customer UOM Quantity
                ZYPEZDItemValueSetter.setValue(dtlTMsg.ordCustUomQty, glblASMsg.ordQty_CO);
                // DS Order Line Category Code
                ZYPEZDItemValueSetter.setValue(dtlTMsg.dsOrdLineCatgCd, glblASMsg.dsOrdLineCatgCd_CO);

                // Customer Info
                ZYPEZDItemValueSetter.setValue(dtlTMsg.dropShipFlg, imptTMsg.dropShipFlg);
                ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToCustCd, imptTMsg.shipToCustCd);
                ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToLocNm, imptTMsg.shipToLocNm);
                ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToAddlLocNm, imptTMsg.shipToAddlLocNm);
                ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToFirstLineAddr, imptTMsg.shipToFirstLineAddr);
                ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToScdLineAddr, imptTMsg.shipToScdLineAddr);
                ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToThirdLineAddr, imptTMsg.shipToThirdLineAddr);
                ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToFrthLineAddr, imptTMsg.shipToFrthLineAddr);
                ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToFirstRefCmntTxt, imptTMsg.shipTo01RefCmntTxt);
                ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToScdRefCmntTxt, imptTMsg.shipTo02RefCmntTxt);
                ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToCtyAddr, imptTMsg.shipToCtyAddr);
                ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToStCd, imptTMsg.shipToStCd);
                ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToProvNm, imptTMsg.shipToProvNm);
                ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToCntyNm, imptTMsg.shipToCntyNm);
                ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToPostCd, imptTMsg.shipToPostCd);
                ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToCtryCd, imptTMsg.shipToCtryCd);

            } else {
                NWAL2320_BSMsg glblBSMsg = configBean.<NWAL2320_BSMsg>getDetailSMsg(dtlCnt);

                // MDSE Info
                ZYPEZDItemValueSetter.setValue(dtlTMsg.mdseCd, glblBSMsg.mdseCd_EO);
                if (!ZYPCommonFunc.hasValue(glblBSMsg.mdseNm_EO)) {
                    ssmResult = NWAL2320Query.getInstance().getMdseNm(imptTMsg.glblCmpyCd.getValue(), glblBSMsg.mdseCd_EO.getValue());
                    if (checkAndAddMasterNotFoundError(detailBean, ssmResult, TMPL_DL_HEADER_FOR_EXS_ORDER[1])) {
                        resultString = (String) ssmResult.getResultObject();
                        ZYPEZDItemValueSetter.setValue(glblBSMsg.mdseNm_EO, resultString);
                    }
                }
                ZYPEZDItemValueSetter.setValue(dtlTMsg.mdseNm, glblBSMsg.mdseNm_EO);

                // Line Category
                if (!ZYPCommonFunc.hasValue(glblBSMsg.dsOrdLineCatgCd_EO)) {
                    ssmResult = NWAL2320Query.getInstance().getDsOrdLineCatgCd(imptTMsg.glblCmpyCd.getValue(), glblBSMsg.dsOrdLineCatgDescTxt_EO.getValue());
                    if (checkAndAddMasterNotFoundError(detailBean, ssmResult, TMPL_DL_HEADER_FOR_EXS_ORDER[5])) {
                        resultString = (String) ssmResult.getResultObject();
                        ZYPEZDItemValueSetter.setValue(glblBSMsg.dsOrdLineCatgCd_EO, resultString);
                    }
                }
                ZYPEZDItemValueSetter.setValue(dtlTMsg.dsOrdLineCatgCd, glblBSMsg.dsOrdLineCatgCd_EO);

                // Warehouse
                // if (!ZYPCommonFunc.hasValue(glblBSMsg.rtlWhCd_EO)) { // 2019/03/25 S21_NA#30924 Mod
                if (!ZYPCommonFunc.hasValue(glblBSMsg.rtlWhCd_EO) && !isNoInvtyItem(bizMsg.glblCmpyCd.getValue(), dtlTMsg.mdseCd.getValue())) {
                    ssmResult = NWAL2320Query.getInstance().getRtlWhCd(imptTMsg.glblCmpyCd.getValue(), glblBSMsg.rtlWhNm_EO.getValue());
                    if (checkAndAddMasterNotFoundError(detailBean, ssmResult, TMPL_DL_HEADER_FOR_EXS_ORDER[9])) {
                        resultString = (String) ssmResult.getResultObject();
                        ZYPEZDItemValueSetter.setValue(glblBSMsg.rtlWhCd_EO, resultString);
                    }
                }
                ZYPEZDItemValueSetter.setValue(dtlTMsg.rtlWhCd, glblBSMsg.rtlWhCd_EO);

                // Sub Warehouse
                // if (!ZYPCommonFunc.hasValue(glblBSMsg.rtlSwhCd_EO)) { // 2019/03/25 S21_NA#30924 Mod
                if (!ZYPCommonFunc.hasValue(glblBSMsg.rtlSwhCd_EO) && !isNoInvtyItem(bizMsg.glblCmpyCd.getValue(), dtlTMsg.mdseCd.getValue())) {
                    ssmResult = NWAL2320Query.getInstance().getRtlSwhCd(imptTMsg.glblCmpyCd.getValue(), glblBSMsg.rtlSwhNm_EO.getValue());
                    //if (checkAndAddMasterNotFoundError(detailBean, ssmResult, TMPL_DL_HEADER_FOR_EXS_ORDER[10])) {
                        resultString = (String) ssmResult.getResultObject();
                        ZYPEZDItemValueSetter.setValue(glblBSMsg.rtlSwhCd_EO, resultString);
                    //}
                }
                ZYPEZDItemValueSetter.setValue(dtlTMsg.rtlSwhCd, glblBSMsg.rtlSwhCd_EO);

                // Order Quantity
                ZYPEZDItemValueSetter.setValue(dtlTMsg.ordQty, glblBSMsg.ordQty_EO);
                // Ordered Customer UOM Quantity
                ZYPEZDItemValueSetter.setValue(dtlTMsg.ordCustUomQty, glblBSMsg.ordQty_EO);
                // DS Order Line Category Code
                ZYPEZDItemValueSetter.setValue(dtlTMsg.dsOrdLineCatgCd, glblBSMsg.dsOrdLineCatgCd_EO);

                // Customer Info
                ZYPEZDItemValueSetter.setValue(dtlTMsg.dropShipFlg, configTMsg.dropShipFlg);
                ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToCustCd, configTMsg.shipToCustLocCd);
                ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToLocNm, configTMsg.shipToLocNm);
                ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToAddlLocNm, configTMsg.shipToAddlLocNm);
                ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToFirstLineAddr, configTMsg.shipToFirstLineAddr);
                ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToScdLineAddr, configTMsg.shipToScdLineAddr);
                ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToThirdLineAddr, configTMsg.shipToThirdLineAddr);
                ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToFrthLineAddr, configTMsg.shipToFrthLineAddr);
                ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToFirstRefCmntTxt, configTMsg.shipToFirstRefCmntTxt);
                ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToScdRefCmntTxt, configTMsg.shipToScdRefCmntTxt);
                ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToCtyAddr, configTMsg.shipToCtyAddr);
                ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToStCd, configTMsg.shipToStCd);
                ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToProvNm, configTMsg.shipToProvNm);
                ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToCntyNm, configTMsg.shipToCntyNm);
                ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToPostCd, configTMsg.shipToPostCd);
                ZYPEZDItemValueSetter.setValue(dtlTMsg.shipToCtryCd, configTMsg.shipToCtryCd);
            }

            // Detail MDSETMsg
            detailBean.mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(bizMsg.glblCmpyCd.getValue(), dtlTMsg.mdseCd.getValue());

            // Inventory Location Code
            String invtyLocCd = NWXC220001Util.convToString(dtlTMsg.rtlWhCd.getValue(), "") + NWXC220001Util.convToString(dtlTMsg.rtlSwhCd.getValue(), "");
            ZYPEZDItemValueSetter.setValue(dtlTMsg.invtyLocCd, invtyLocCd);

            // Price Category Code
            ZYPEZDItemValueSetter.setValue(dtlTMsg.prcCatgCd, imptTMsg.prcCatgCd);
            // Floor Price List Code
            ZYPEZDItemValueSetter.setValue(dtlTMsg.flPrcListCd, imptTMsg.flPrcListCd);
            // Price Base Date
            ZYPEZDItemValueSetter.setValue(dtlTMsg.prcBaseDt, bizMsg.slsDt);
            // RDD Date
            ZYPEZDItemValueSetter.setValue(dtlTMsg.rddDt, imptTMsg.rddDt);
            // UOM Complete Flag
            ZYPEZDItemValueSetter.setValue(dtlTMsg.uomCpltFlg, ZYPConstant.FLG_OFF_N);
            // Sales Rep or Sales Team TOC Code
            ZYPEZDItemValueSetter.setValue(dtlTMsg.slsRepOrSlsTeamTocCd, imptTMsg.slsRepTocCd);
            // Customer UOM Code
            ZYPEZDItemValueSetter.setValue(dtlTMsg.custUomCd, PKG_UOM.EACH);
            // Set Item Ship Complete Flag
            ZYPEZDItemValueSetter.setValue(dtlTMsg.setItemShipCpltFlg, ZYPConstant.FLG_OFF_N);
            // DS Order Position Number
            ZYPEZDItemValueSetter.setValue(dtlTMsg.dsOrdPosnNum, configTMsg.dsOrdPosnNum);
            // Configuration Item Flag
            ZYPEZDItemValueSetter.setValue(dtlTMsg.configItemFlg, ZYPConstant.FLG_OFF_N);
            // Base Component Flag
            ZYPEZDItemValueSetter.setValue(dtlTMsg.baseCmptFlg, ZYPConstant.FLG_OFF_N);
            // Customer Installable Flag
            ZYPEZDItemValueSetter.setValue(dtlTMsg.custIstlFlg, ZYPConstant.FLG_OFF_N);
            // DS Import Order Config PK
            ZYPEZDItemValueSetter.setValue(dtlTMsg.dsImptOrdConfigPk, configTMsg.dsImptOrdConfigPk);
            // Order Line Source Code
            ZYPEZDItemValueSetter.setValue(dtlTMsg.ordLineSrcCd, ORD_LINE_SRC.INTERNAL);
            // Supersede Lock Flag
            ZYPEZDItemValueSetter.setValue(dtlTMsg.supdLockFlg, ZYPConstant.FLG_OFF_N);

            // Unit Net Weight
            mdseStorePkgResult = NWXC220001.getMdseStorePkgTMsg(dtlTMsg.glblCmpyCd.getValue(), dtlTMsg.custUomCd.getValue(), dtlTMsg.mdseCd.getValue());
            if (!mdseStorePkgResult.hasError()) {
                ZYPEZDItemValueSetter.setValue(dtlTMsg.unitNetWt, mdseStorePkgResult.getResultObject().inPoundWt.getValue().multiply(dtlTMsg.ordCustUomQty.getValue()));
            } else {
                detailBean.addErrorInfo(mdseStorePkgResult.getErrInfoList());
            }

            // QC#3265
            ZYPEZDItemValueSetter.setValue(dtlTMsg.imptLineFlg, ZYPConstant.FLG_ON_Y);
            // QC#16266
            ZYPEZDItemValueSetter.setValue(dtlTMsg.preExistFlg, ZYPConstant.FLG_OFF_N);
            // Mod Start 2018/04/20 QC#22347
//            ZYPEZDItemValueSetter.setValue(dtlTMsg.finItemLineFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(dtlTMsg.finItemLineFlg, ZYPConstant.FLG_ON_Y);
            // Mod Start 2018/04/20 QC#22347

            dtlCnt++;
        }
    }

    private static void derivingRtnDetailData(NWAL2320CMsg bizMsg, NWAL2320_ImptConfigBean configBean) {
        NWAL2320_ImptHeaderBean hdrBean = configBean.headerBean;
        DS_IMPT_ORDTMsg imptTMsg = hdrBean.me;
        DS_IMPT_ORD_CONFIGTMsg configTMsg;
        DS_IMPT_ORD_RTRN_DTLTMsg rtnDtlTMsg;
        BigDecimal pk;
        S21SsmEZDResult ssmResult;
        String resultString;
        NWXC220001Result<MDSE_STORE_PKGTMsg> mdseStorePkgResult;
        int dtlCnt = 0;

        for (NWAL2320_ImptRtrnDetailBean rtnDtlBean : configBean.rtrnDtlBeanList) {
            configTMsg = configBean.me;
            rtnDtlTMsg = rtnDtlBean.me;

            // Global Company Code
            ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.glblCmpyCd, bizMsg.glblCmpyCd);

            // Direct Sales Import Order Return Detail PK
            if (configBean.headerBean.doInsertTable) {
               pk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_RTRN_DTL_SQ);
               ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.dsImptOrdRtrnDtlPk, pk);
            }

            // Direct Sales Import Order PK
            ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.dsImptOrdPk, imptTMsg.dsImptOrdPk);

            // Order Source Reference Line Number
            hdrBean.lastOrdSrcRefLineNumIn++;
            ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.ordSrcRefLineNum, NWXC220001Util.convNumToZ99(hdrBean.lastOrdSrcRefLineNumIn));

            // Order Source Reference Line Sub Number
            ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.ordSrcRefLineSubNum, "001");

            // DS Import Order Config PK
            ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.dsImptOrdConfigPk, configBean.me.dsImptOrdConfigPk);
            // DS Order Position Number
            ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.dsOrdPosnNum, configBean.me.dsOrdPosnNum);
            // Order Line Source Code
            ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.ordLineSrcCd, ORD_LINE_SRC.RETURN);

            if (hdrBean.isUpldTpNew()) {
                NWAL2320_CSMsg glblCSMsg = configBean.<NWAL2320_CSMsg>getDetailSMsg(dtlCnt);

                // DS Order Line Category Code
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.dsCpoLineCatgCd, glblCSMsg.dsOrdLineCatgCd_NR);

                // MDSE Info
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.mdseCd, glblCSMsg.mdseCd_NR);
                if (!ZYPCommonFunc.hasValue(glblCSMsg.mdseNm_NR)) {
                    ssmResult = NWAL2320Query.getInstance().getMdseNm(imptTMsg.glblCmpyCd.getValue(), glblCSMsg.mdseCd_NR.getValue());
                    if (checkAndAddMasterNotFoundError(rtnDtlBean, ssmResult, TMPL_DL_HEADER_FOR_NEW_RMA[6])) {
                        resultString = (String) ssmResult.getResultObject();
                        ZYPEZDItemValueSetter.setValue(glblCSMsg.mdseNm_NR, resultString);
                    }
                }
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.mdseNm, glblCSMsg.mdseNm_NR);

                // Order Quantity
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.ordQty, glblCSMsg.ordQty_NR);
                // Ordered Customer UOM Quantity
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.ordCustUomQty, glblCSMsg.ordQty_NR);

                // Line Category
                if (!ZYPCommonFunc.hasValue(glblCSMsg.dsOrdLineCatgCd_NR)) {
                    ssmResult = NWAL2320Query.getInstance().getDsOrdLineCatgCd(imptTMsg.glblCmpyCd.getValue(), glblCSMsg.dsOrdLineCatgDescTxt_NR.getValue());
                    if (checkAndAddMasterNotFoundError(rtnDtlBean, ssmResult, TMPL_DL_HEADER_FOR_NEW_RMA[10])) {
                        resultString = (String) ssmResult.getResultObject();
                        ZYPEZDItemValueSetter.setValue(glblCSMsg.dsOrdLineCatgCd_NR, resultString);
                    }
                }
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.dsCpoLineCatgCd, glblCSMsg.dsOrdLineCatgCd_NR);

                // Warehouse
                // if (!ZYPCommonFunc.hasValue(glblCSMsg.rtlWhCd_NR)) { // 2019/03/25 S21_NA#30924 Mod
                if (!ZYPCommonFunc.hasValue(glblCSMsg.rtlWhCd_NR) && !isNoInvtyItem(bizMsg.glblCmpyCd.getValue(), rtnDtlTMsg.mdseCd.getValue())) {
                    ssmResult = NWAL2320Query.getInstance().getRtlWhCd(imptTMsg.glblCmpyCd.getValue(), glblCSMsg.rtlWhNm_NR.getValue());
                    // 2017/01/05 S21_NA#16757 Mod Start
//                    if (checkAndAddMasterNotFoundError(rtnDtlBean, ssmResult, TMPL_DL_HEADER_FOR_NEW_RMA[15])) {
                    if (checkAndAddMasterNotFoundError(rtnDtlBean, ssmResult, TMPL_DL_HEADER_FOR_NEW_RMA[16])) {
                    // 2017/01/05 S21_NA#16757 Mod End
                        resultString = (String) ssmResult.getResultObject();
                        ZYPEZDItemValueSetter.setValue(glblCSMsg.rtlWhCd_NR, resultString);
                    }
                }
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.rtlWhCd, glblCSMsg.rtlWhCd_NR);

                // Return Reason Code
                if (!ZYPCommonFunc.hasValue(glblCSMsg.rtrnRsnCd_NR)) {
                    ssmResult = NWAL2320Query.getInstance().getRtrnRsnCd(imptTMsg.glblCmpyCd.getValue(), glblCSMsg.rtrnRsnDescTxt_NR.getValue());
                    // 2017/01/05 S21_NA#16757 Mod Start
//                    if (checkAndAddMasterNotFoundError(rtnDtlBean, ssmResult, TMPL_DL_HEADER_FOR_NEW_RMA[16])) {
                    if (checkAndAddMasterNotFoundError(rtnDtlBean, ssmResult, TMPL_DL_HEADER_FOR_NEW_RMA[17])) {
                    // 2017/01/05 S21_NA#16757 Mod End
                        resultString = (String) ssmResult.getResultObject();
                        ZYPEZDItemValueSetter.setValue(glblCSMsg.rtrnRsnCd_NR, resultString);
                    }
                }
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.rtrnRsnCd, glblCSMsg.rtrnRsnCd_NR);

                setSerInfo(bizMsg.glblCmpyCd.getValue(), glblCSMsg.serNum_NR.getValue(), glblCSMsg.mdseCd_NR.getValue(), configTMsg, rtnDtlTMsg); // 2019/03/26 S21_NA#30924 Add
                // Serial Number
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.serNum, glblCSMsg.serNum_NR);

                // Price Base Date
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.prcBaseDt, hdrBean.slsDt);

                // Customer Info
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.dropShipFlg, imptTMsg.dropShipFlg);
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.shipToCustCd, imptTMsg.shipToCustCd);
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.shipToLocNm, imptTMsg.shipToLocNm);
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.shipToAddlLocNm, imptTMsg.shipToAddlLocNm);
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.shipToFirstLineAddr, imptTMsg.shipToFirstLineAddr);
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.shipToScdLineAddr, imptTMsg.shipToScdLineAddr);
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.shipToThirdLineAddr, imptTMsg.shipToThirdLineAddr);
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.shipToFrthLineAddr, imptTMsg.shipToFrthLineAddr);
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.shipToFirstRefCmntTxt, imptTMsg.shipTo01RefCmntTxt);
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.shipToScdRefCmntTxt, imptTMsg.shipTo02RefCmntTxt);
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.shipToCtyAddr, imptTMsg.shipToCtyAddr);
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.shipToStCd, imptTMsg.shipToStCd);
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.shipToProvNm, imptTMsg.shipToProvNm);
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.shipToCntyNm, imptTMsg.shipToCntyNm);
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.shipToPostCd, imptTMsg.shipToPostCd);
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.shipToCtryCd, imptTMsg.shipToCtryCd);
            } else {
                NWAL2320_DSMsg glblDSMsg = configBean.<NWAL2320_DSMsg>getDetailSMsg(dtlCnt);

                // DS Order Line Category Code
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.dsCpoLineCatgCd, glblDSMsg.dsOrdLineCatgCd_ER);

                // MDSE Info
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.mdseCd, glblDSMsg.mdseCd_ER);
                if (!ZYPCommonFunc.hasValue(glblDSMsg.mdseNm_ER)) {
                    ssmResult = NWAL2320Query.getInstance().getMdseNm(imptTMsg.glblCmpyCd.getValue(), glblDSMsg.mdseCd_ER.getValue());
                    if (checkAndAddMasterNotFoundError(rtnDtlBean, ssmResult, TMPL_DL_HEADER_FOR_EXS_RMA[1])) {
                        resultString = (String) ssmResult.getResultObject();
                        ZYPEZDItemValueSetter.setValue(glblDSMsg.mdseNm_ER, resultString);
                    }
                }
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.mdseNm, glblDSMsg.mdseNm_ER);

                // Order Quantity
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.ordQty, glblDSMsg.ordQty_ER);
                // Ordered Customer UOM Quantity
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.ordCustUomQty, glblDSMsg.ordQty_ER);

                // Line Category
                if (!ZYPCommonFunc.hasValue(glblDSMsg.dsOrdLineCatgCd_ER)) {
                    ssmResult = NWAL2320Query.getInstance().getDsOrdLineCatgCd(imptTMsg.glblCmpyCd.getValue(), glblDSMsg.dsOrdLineCatgDescTxt_ER.getValue());
                    if (checkAndAddMasterNotFoundError(rtnDtlBean, ssmResult, TMPL_DL_HEADER_FOR_EXS_RMA[5])) {
                        resultString = (String) ssmResult.getResultObject();
                        ZYPEZDItemValueSetter.setValue(glblDSMsg.dsOrdLineCatgCd_ER, resultString);
                    }
                }
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.dsCpoLineCatgCd, glblDSMsg.dsOrdLineCatgCd_ER);

                // Warehouse
                // if (!ZYPCommonFunc.hasValue(glblDSMsg.rtlWhCd_ER)) { // 2019/03/25 S21_NA#30924 Mod
                if (!ZYPCommonFunc.hasValue(glblDSMsg.rtlWhCd_ER) && !isNoInvtyItem(bizMsg.glblCmpyCd.getValue(), rtnDtlTMsg.mdseCd.getValue())) {
                    ssmResult = NWAL2320Query.getInstance().getRtlWhCd(imptTMsg.glblCmpyCd.getValue(), glblDSMsg.rtlWhNm_ER.getValue());
                    if (checkAndAddMasterNotFoundError(rtnDtlBean, ssmResult, TMPL_DL_HEADER_FOR_EXS_RMA[10])) {
                        resultString = (String) ssmResult.getResultObject();
                        ZYPEZDItemValueSetter.setValue(glblDSMsg.rtlWhCd_ER, resultString);
                    }
                }
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.rtlWhCd, glblDSMsg.rtlWhCd_ER);

                // Return Reason Code
                if (!ZYPCommonFunc.hasValue(glblDSMsg.rtrnRsnCd_ER)) {
                    ssmResult = NWAL2320Query.getInstance().getRtrnRsnCd(imptTMsg.glblCmpyCd.getValue(), glblDSMsg.rtrnRsnDescTxt_ER.getValue());
                    if (checkAndAddMasterNotFoundError(rtnDtlBean, ssmResult, TMPL_DL_HEADER_FOR_EXS_RMA[11])) {
                        resultString = (String) ssmResult.getResultObject();
                        ZYPEZDItemValueSetter.setValue(glblDSMsg.rtrnRsnCd_ER, resultString);
                    }
                }
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.rtrnRsnCd, glblDSMsg.rtrnRsnCd_ER);

                // 2019/03/26 S21_NA#30924 Mod Start
                // S21_NA#22195 ADD START
                // Map<String, Object> map = NWXC150001DsCheck.getSerNumInfo(
                //         bizMsg.glblCmpyCd.getValue()
                //         , glblDSMsg.serNum_ER.getValue()
                //         , glblDSMsg.mdseCd_ER.getValue());
                // if (map == null) {
                //     ZYPEZDItemValueSetter.setValue(configTMsg.configTpCd, CONFIG_TP.RETURN_NEW);
                // } else { //2018/04/25 QC#22189 Add Start
                    // Getting Model ID
                //     ZYPEZDItemValueSetter.setValue(configTMsg.svcConfigMstrPk, (BigDecimal) map.get("SVC_CONFIG_MSTR_PK"));
                //     ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.svcConfigMstrPk, (BigDecimal) map.get("SVC_CONFIG_MSTR_PK"));
                //     SVC_CONFIG_MSTRTMsg svcConfigMstrTMsg = new SVC_CONFIG_MSTRTMsg();
                //     ZYPEZDItemValueSetter.setValue(svcConfigMstrTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
                //     ZYPEZDItemValueSetter.setValue(svcConfigMstrTMsg.svcConfigMstrPk, (BigDecimal) map.get("SVC_CONFIG_MSTR_PK"));
                //     svcConfigMstrTMsg = (SVC_CONFIG_MSTRTMsg) S21FastTBLAccessor.findByKey(svcConfigMstrTMsg);
                //     if (svcConfigMstrTMsg != null) {
                //         ZYPEZDItemValueSetter.setValue(configTMsg.mdlId, svcConfigMstrTMsg.mdlId);
                //     }
                    // Getting Model Desc Text
                //     if (ZYPCommonFunc.hasValue(configTMsg.mdlId) && !ZYPCommonFunc.hasValue(configTMsg.mdlDescTxt)) {
                //         DS_MDLTMsg dsMdlTMsg = new DS_MDLTMsg();
                //         ZYPEZDItemValueSetter.setValue(dsMdlTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
                //         ZYPEZDItemValueSetter.setValue(dsMdlTMsg.mdlId, configTMsg.mdlId);
                //         dsMdlTMsg = (DS_MDLTMsg) S21CacheTBLAccessor.findByKey(dsMdlTMsg);
                //         if (dsMdlTMsg != null) {
                //             ZYPEZDItemValueSetter.setValue(configTMsg.mdlDescTxt, dsMdlTMsg.mdlDescTxt);
                //         }
                //     }
                // } // 2018/04/25 QC#22189 Add End
                // S21_NA#22195 ADD END
                setSerInfo(bizMsg.glblCmpyCd.getValue(), glblDSMsg.serNum_ER.getValue(), glblDSMsg.mdseCd_ER.getValue(), configTMsg, rtnDtlTMsg);
                // 2019/03/26 S21_NA#30924 Mod SEnd

                // Serial Number
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.serNum, glblDSMsg.serNum_ER);

                // Price Base Date
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.prcBaseDt, hdrBean.cpoTMsg.prcBaseDt);

                // Customer Info
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.dropShipFlg, configTMsg.dropShipFlg);
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.shipToCustCd, configTMsg.shipToCustLocCd);
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.shipToLocNm, configTMsg.shipToLocNm);
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.shipToAddlLocNm, configTMsg.shipToAddlLocNm);
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.shipToFirstLineAddr, configTMsg.shipToFirstLineAddr);
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.shipToScdLineAddr, configTMsg.shipToScdLineAddr);
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.shipToThirdLineAddr, configTMsg.shipToThirdLineAddr);
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.shipToFrthLineAddr, configTMsg.shipToFrthLineAddr);
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.shipToFirstRefCmntTxt, configTMsg.shipToFirstRefCmntTxt);
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.shipToScdRefCmntTxt, configTMsg.shipToScdRefCmntTxt);
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.shipToCtyAddr, configTMsg.shipToCtyAddr);
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.shipToStCd, configTMsg.shipToStCd);
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.shipToProvNm, configTMsg.shipToProvNm);
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.shipToCntyNm, configTMsg.shipToCntyNm);
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.shipToPostCd, configTMsg.shipToPostCd);
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.shipToCtryCd, configTMsg.shipToCtryCd);
            }

            // Base Component Flag
            ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.baseCmptFlg, ZYPConstant.FLG_OFF_N);

            // Customer UOM Code
            ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.custUomCd, PKG_UOM.EACH);

            // Inventory Location Code
            String invtyLocCd = NWXC220001Util.convToString(rtnDtlTMsg.rtlWhCd.getValue(), "");
            ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.invtyLocCd, invtyLocCd);

            // Price Category Code
            ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.prcCatgCd, imptTMsg.prcCatgCd);
            // Floor Price List Code
            ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.flPrcListCd, imptTMsg.flPrcListCd);
            // Sales Rep or Sales Team TOC Code
            ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.slsRepOrSlsTeamTocCd, imptTMsg.slsRepTocCd);

            // Unit Net Weight
            mdseStorePkgResult = NWXC220001.getMdseStorePkgTMsg(rtnDtlTMsg.glblCmpyCd.getValue(), rtnDtlTMsg.custUomCd.getValue(), rtnDtlTMsg.mdseCd.getValue());
            if (!mdseStorePkgResult.hasError()) {
                ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.unitNetWt, mdseStorePkgResult.getResultObject().inPoundWt.getValue().multiply(rtnDtlTMsg.ordCustUomQty.getValue()));
            } else {
                rtnDtlBean.addErrorInfo(mdseStorePkgResult.getErrInfoList());
            }
            dtlCnt++;
        }
    }


    private static void setPricingData(NWAL2320CMsg bizMsg, NWAL2320_ImptHeaderBean hdrBean) {

        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxModeCd, NWZC157001.GET_ORDER_ALL);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcCtxTpCd, PRC_CTX_TP.SALES_PRICING);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdCatgCd, hdrBean.me.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdTpCd, hdrBean.me.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd,  hdrBean.dsOrdTpProcDfnTMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsAcctNum, hdrBean.me.sellToCustCd);

        ZYPEZDItemValueSetter.setValue(prcApiPMsg.cpoSrcTpCd, hdrBean.me.cpoSrcTpCd);
        // 2017/06/29 QC#18984 ADD START
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsPmtMethCd, hdrBean.me.dsPmtMethCd);
        // 2017/06/29 QC#18984 ADD E N D
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.custIssPoNum, hdrBean.me.custIssPoNum);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.taxCalcFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.spclHdlgTpCd, hdrBean.me.spclHdlgTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.leasePrchOptCd, hdrBean.me.leasePrchOptCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxSmryLineFlg, ZYPConstant.FLG_ON_Y);

        // Call Mode 2 => 4
        NWZC157002PMsg prcApi2PMsg;
        DS_IMPT_ORD_DTLTMsg dtlTMsg;
        DS_IMPT_ORD_RTRN_DTLTMsg rtnDtlTMsg;
        DS_IMPT_ORD_CONFIGTMsg configTMsg;
        int cnt = 0;
        BigDecimal entDealNetUnitPrcAmt;

        if (hdrBean.isUpldTpSlsOrd()) {
            for (NWAL2320_ImptDetailBean dtlBean : hdrBean.detailBeanList) {
                prcApi2PMsg = prcApiPMsg.NWZC157002PMsg.no(cnt);
                dtlTMsg = dtlBean.me;
                configTMsg = dtlBean.configBean.me;

                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.trxLineNum, dtlTMsg.ordSrcRefLineNum);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.trxLineSubNum, dtlTMsg.ordSrcRefLineSubNum);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.configCatgCd, CONFIG_CATG.OUTBOUND);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.billToCustCd, configTMsg.billToCustLocCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.shipToCustCd, configTMsg.shipToCustLocCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.dsAcctNum_BL, configTMsg.billToCustAcctCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.dsAcctNum_SH, configTMsg.shipToCustAcctCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.prcCatgCd, hdrBean.me.prcCatgCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.ccyCd, dtlTMsg.ccyCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.mdseCd, dtlTMsg.mdseCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.pkgUomCd, dtlTMsg.custUomCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.dsOrdLineCatgCd, dtlTMsg.dsOrdLineCatgCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.ordQty, dtlTMsg.ordQty);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.ordCustUomQty, dtlTMsg.ordCustUomQty);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.invQty, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.mdlId, configTMsg.mdlId);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.ctyAddr_SH, dtlTMsg.shipToCtyAddr);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.stCd_SH, dtlTMsg.shipToStCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.ctryCd_SH, dtlTMsg.shipToCtryCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.slsRepOrSlsTeamTocCd, dtlTMsg.slsRepOrSlsTeamTocCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.rtlWhCd, dtlTMsg.rtlWhCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.frtCondCd, hdrBean.me.frtCondCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.shpgSvcLvlCd, hdrBean.me.shpgSvcLvlCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.coaExtnCd, hdrBean.coaExtnCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.postCd_SH, dtlTMsg.shipToPostCd);

                // QC#11837
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.dsOrdPosnNum, configTMsg.dsOrdPosnNum);

                if (hdrBean.isUpldTpNew()) {
                    entDealNetUnitPrcAmt = ((NWAL2320_ASMsg) dtlBean.rowData).entDealNetUnitPrcAmt_CO.getValue();
                } else {
                    entDealNetUnitPrcAmt = ((NWAL2320_BSMsg) dtlBean.rowData).entDealNetUnitPrcAmt_EO.getValue();
                }
                if (ZYPCommonFunc.hasValue(entDealNetUnitPrcAmt)) {
                    ZYPEZDItemValueSetter.setValue(prcApi2PMsg.prcCondManEntryFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(prcApi2PMsg.xxUnitPrc, entDealNetUnitPrcAmt);
                } else {
                    ZYPEZDItemValueSetter.setValue(prcApi2PMsg.prcCondManEntryFlg, ZYPConstant.FLG_OFF_N);
                }
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.entCpoDtlDealSlsAmt, prcApi2PMsg.xxUnitPrc); // S21_NA#6480

                cnt++;
            }

        } else {
            for (NWAL2320_ImptRtrnDetailBean rtndtlBean : hdrBean.rtrnDtlBeanList) {
                prcApi2PMsg = prcApiPMsg.NWZC157002PMsg.no(cnt);
                rtnDtlTMsg = rtndtlBean.me;
                configTMsg = rtndtlBean.configBean.me;

                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.trxLineNum, rtnDtlTMsg.ordSrcRefLineNum);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.trxLineSubNum, rtnDtlTMsg.ordSrcRefLineSubNum);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.configCatgCd, CONFIG_CATG.INBOUND);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.billToCustCd, configTMsg.billToCustLocCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.shipToCustCd, configTMsg.shipToCustLocCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.dsAcctNum_BL, configTMsg.billToCustAcctCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.dsAcctNum_SH, configTMsg.shipToCustAcctCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.prcCatgCd, hdrBean.me.prcCatgCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.ccyCd, rtnDtlTMsg.ccyCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.mdseCd, rtnDtlTMsg.mdseCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.pkgUomCd, rtnDtlTMsg.custUomCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.dsOrdLineCatgCd, rtnDtlTMsg.dsCpoLineCatgCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.ordQty, rtnDtlTMsg.ordQty);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.ordCustUomQty, rtnDtlTMsg.ordCustUomQty);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.invQty, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.mdlId, configTMsg.mdlId);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.ctyAddr_SH, rtnDtlTMsg.shipToCtyAddr);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.stCd_SH, rtnDtlTMsg.shipToStCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.ctryCd_SH, rtnDtlTMsg.shipToCtryCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.slsRepOrSlsTeamTocCd, rtnDtlTMsg.slsRepOrSlsTeamTocCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.rtlWhCd, rtnDtlTMsg.rtlWhCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.frtCondCd, hdrBean.me.frtCondCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.shpgSvcLvlCd, hdrBean.me.shpgSvcLvlCd);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.coaExtnCd, hdrBean.coaExtnCd);

                // QC#11837
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.dsOrdPosnNum, configTMsg.dsOrdPosnNum);

                if (hdrBean.isUpldTpNew()) {
                    entDealNetUnitPrcAmt = ((NWAL2320_CSMsg) rtndtlBean.rowData).entDealNetUnitPrcAmt_NR.getValue();
                } else {
                    entDealNetUnitPrcAmt = ((NWAL2320_DSMsg) rtndtlBean.rowData).entDealNetUnitPrcAmt_ER.getValue();
                }
                if (ZYPCommonFunc.hasValue(entDealNetUnitPrcAmt)) {
                    ZYPEZDItemValueSetter.setValue(prcApi2PMsg.prcCondManEntryFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(prcApi2PMsg.xxUnitPrc, entDealNetUnitPrcAmt);
                } else {
                    ZYPEZDItemValueSetter.setValue(prcApi2PMsg.prcCondManEntryFlg, ZYPConstant.FLG_OFF_N);
                }
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.entCpoDtlDealSlsAmt, prcApi2PMsg.xxUnitPrc);

                cnt++;
            }
        }

        prcApiPMsg.NWZC157002PMsg.setValidCount(cnt);

        if (!callPricingApi(prcApiPMsg, hdrBean)) {
            return;
        }

//        // Call Mode 4
//        BigDecimal entDealNetUnitPrcAmt;
//        NWZC157003PMsg prcApi3PMsg;
//        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxModeCd, "04");
//
//        if (hdrBean.isUpldTpSlsOrd()) {
//            for (NWAL2320_ImptDetailBean dtlBean : hdrBean.detailBeanList) {
//                prcApi2PMsg = getNWZC157002PMsg(prcApiPMsg, dtlBean.me.ordSrcRefLineNum, dtlBean.me.ordSrcRefLineSubNum);
//                if (prcApi2PMsg != null) {
//                    for (int i = 0; i < prcApi2PMsg.NWZC157003PMsg.getValidCount(); i++) {
//                        prcApi3PMsg = prcApi2PMsg.NWZC157003PMsg.no(i);
//
//                        if (hdrBean.isUpldTpNew()) {
//                            entDealNetUnitPrcAmt = ((NWAL2320_ASMsg) dtlBean.rowData).entDealNetUnitPrcAmt_CO.getValue();
//                        } else {
//                            entDealNetUnitPrcAmt = ((NWAL2320_BSMsg) dtlBean.rowData).entDealNetUnitPrcAmt_EO.getValue();
//                        }
//                        if (ZYPCommonFunc.hasValue(entDealNetUnitPrcAmt)
//                                && PRC_DTL_GRP.BASE_PRICE.equals(prcApi3PMsg.prcDtlGrpCd.getValue())) {
//                            ZYPEZDItemValueSetter.setValue(prcApi3PMsg.prcCondManEntryFlg, ZYPConstant.FLG_ON_Y);
//                            ZYPEZDItemValueSetter.setValue(prcApi3PMsg.manPrcAmtRate, entDealNetUnitPrcAmt);
//                        }
//                    }
//                }
//            }
//        } else {
//            for (NWAL2320_ImptRtrnDetailBean rtnDtlBean : hdrBean.rtrnDtlBeanList) {
//                prcApi2PMsg = getNWZC157002PMsg(prcApiPMsg, rtnDtlBean.me.ordSrcRefLineNum, rtnDtlBean.me.ordSrcRefLineSubNum);
//                if (prcApi2PMsg != null) {
//                    for (int i = 0; i < prcApi2PMsg.NWZC157003PMsg.getValidCount(); i++) {
//                        prcApi3PMsg = prcApi2PMsg.NWZC157003PMsg.no(i);
//
//                        if (hdrBean.isUpldTpNew()) {
//                            entDealNetUnitPrcAmt = ((NWAL2320_CSMsg) rtnDtlBean.rowData).entDealNetUnitPrcAmt_NR.getValue();
//                        } else {
//                            entDealNetUnitPrcAmt = ((NWAL2320_DSMsg) rtnDtlBean.rowData).entDealNetUnitPrcAmt_ER.getValue();
//                        }
//
//                        if (ZYPCommonFunc.hasValue(entDealNetUnitPrcAmt)
//                                && PRC_DTL_GRP.BASE_PRICE.equals(prcApi3PMsg.prcDtlGrpCd.getValue())) {
//                            ZYPEZDItemValueSetter.setValue(prcApi3PMsg.prcCondManEntryFlg, ZYPConstant.FLG_ON_Y);
//                            ZYPEZDItemValueSetter.setValue(prcApi3PMsg.manPrcAmtRate, entDealNetUnitPrcAmt);
//                        }
//                    }
//                }
//            }
//        }
//
//        prcApiPMsg.NWZC157002PMsg.setValidCount(cnt);
//
//        if (!callPricingApi(prcApiPMsg, hdrBean)) {
//            return;
//        }

        setPricingApiResult(hdrBean, prcApiPMsg);

        return;
    }

    private static boolean callPricingApi(NWZC157001PMsg prcApiPMsg, NWAL2320_ImptHeaderBean hdrBean) {
        boolean isSuccess = true;
        NWZC157002PMsg prcApi2PMsg;

        new NWZC157001().execute(prcApiPMsg, ONBATCH_TYPE.ONLINE);
        if (addErrorInfoForEZDPMsg(hdrBean, prcApiPMsg) > 0) {
            isSuccess = false;
        }

        if (hdrBean.isUpldTpSlsOrd()) {
            for (NWAL2320_ImptDetailBean dtlBean : hdrBean.detailBeanList) {
                prcApi2PMsg = getNWZC157002PMsg(prcApiPMsg, dtlBean.me.ordSrcRefLineNum, dtlBean.me.ordSrcRefLineSubNum);
                if (prcApi2PMsg != null) {
                    if (addErrorInfoForEZDPMsg(dtlBean, prcApi2PMsg) > 0) {
                        isSuccess = false;
                    }
                }
            }
        } else {
            for (NWAL2320_ImptRtrnDetailBean rtnDtlBean : hdrBean.rtrnDtlBeanList) {
                prcApi2PMsg = getNWZC157002PMsg(prcApiPMsg, rtnDtlBean.me.ordSrcRefLineNum, rtnDtlBean.me.ordSrcRefLineSubNum);
                if (prcApi2PMsg != null) {
                    if (addErrorInfoForEZDPMsg(rtnDtlBean, prcApi2PMsg) > 0) {
                        isSuccess = false;
                    }
                }
            }
        }

        return isSuccess;
    }

    private static NWZC157002PMsg getNWZC157002PMsg(NWZC157001PMsg prcApiPMsg, EZDTStringItem ordSrcRefLineNum, EZDTStringItem ordSrcRefLineSubNum) {

        NWZC157002PMsg prcApi2PMsg;
        for (int i = 0; i < prcApiPMsg.NWZC157002PMsg.getValidCount(); i++) {
            prcApi2PMsg = prcApiPMsg.NWZC157002PMsg.no(i);

            if (prcApi2PMsg.trxLineNum.getValue().equals(ordSrcRefLineNum.getValue())
                    && prcApi2PMsg.trxLineSubNum.getValue().equals(ordSrcRefLineSubNum.getValue())) {
                return prcApi2PMsg;
            }
        }

        return null;
    }

    private static NWZC157004PMsg getNWZC157004PMsg(NWZC157001PMsg prcApiPMsg, EZDTStringItem ordSrcRefLineNum, EZDTStringItem ordSrcRefLineSubNum) {

        NWZC157004PMsg prcApi4PMsg;
        for (int i = 0; i < prcApiPMsg.NWZC157004PMsg.getValidCount(); i++) {
            prcApi4PMsg = prcApiPMsg.NWZC157004PMsg.no(i);

            if (prcApi4PMsg.trxLineNum.getValue().equals(ordSrcRefLineNum.getValue())
                    && prcApi4PMsg.trxLineSubNum.getValue().equals(ordSrcRefLineSubNum.getValue())) {
                return prcApi4PMsg;
            }
        }

        return null;
    }

    private static void setPricingApiResult(NWAL2320_ImptHeaderBean hdrBean, NWZC157001PMsg prcApiPMsg) {

        NWZC157002PMsg prcApi2PMsg;
        NWZC157004PMsg prcApi4PMsg;
        NWXC220001Result<PRC_CATGTMsg> prcCatgResult;
//        BigDecimal qty;
        String ccyCd;

        if (hdrBean.isUpldTpSlsOrd()) {
            DS_IMPT_PRC_CALC_BASETMsg prcCalcBaseMsg, basePrcCalcBaseMsg;
            for (NWAL2320_ImptDetailBean dtlBean : hdrBean.detailBeanList) {
                DS_IMPT_ORD_DTLTMsg dtlTMsg = dtlBean.me;
                basePrcCalcBaseMsg = null;

                prcApi2PMsg = getNWZC157002PMsg(prcApiPMsg, dtlTMsg.ordSrcRefLineNum, dtlTMsg.ordSrcRefLineSubNum);
                if (prcApi2PMsg != null) {
                    for (int i = 0; i < prcApi2PMsg.NWZC157003PMsg.getValidCount(); i++) {
                        prcCalcBaseMsg = createPriceingApiCalcBaseResult(hdrBean, dtlTMsg.dsImptOrdDtlPk, prcApi2PMsg.NWZC157003PMsg.no(i));
                        dtlBean.prcCalcBaseTMsgList.add(prcCalcBaseMsg);

                        // QC#11837
                        if (S21StringUtil.isEquals(prcCalcBaseMsg.prcCondTpCd.getValue(), PRC_DTL_GRP.BASE_PRICE)) {
                            basePrcCalcBaseMsg = prcCalcBaseMsg;
                        }
                    }
                }

                prcApi4PMsg = getNWZC157004PMsg(prcApiPMsg, dtlTMsg.ordSrcRefLineNum, dtlTMsg.ordSrcRefLineSubNum);
                if (prcApi4PMsg != null) {
//                    qty = dtlTMsg.ordQty.getValue();

                    prcCatgResult = NWXC220001.getPrcCatgTMsg(dtlTMsg.glblCmpyCd.getValue(), dtlTMsg.prcCatgCd.getValue());
                    if (!prcCatgResult.hasError()) {
                        ccyCd = prcCatgResult.getResultObject().ccyCd.getValue();
                        ZYPEZDItemValueSetter.setValue(dtlTMsg.ccyCd, ccyCd);

                        // QC#11837
//                        ZYPEZDItemValueSetter.setValue(dtlTMsg.dealPrcListPrcAmt, prcApi4PMsg.xxUnitGrsPrcAmt);
                        ZYPEZDItemValueSetter.setValue(dtlTMsg.dealPrcListPrcAmt, (basePrcCalcBaseMsg != null ? basePrcCalcBaseMsg.autoPrcAmtRate.getValue() : BigDecimal.ZERO));
                        ZYPEZDItemValueSetter.setValue(dtlTMsg.funcPrcListPrcAmt, exchFuncUnitPrice(hdrBean, dtlTMsg.ccyCd, dtlTMsg.dealPrcListPrcAmt));

                        ZYPEZDItemValueSetter.setValue(dtlTMsg.entDealNetUnitPrcAmt, prcApi4PMsg.xxUnitNetPrcAmt);
                        ZYPEZDItemValueSetter.setValue(dtlTMsg.entFuncNetUnitPrcAmt, exchFuncUnitPrice(hdrBean, dtlTMsg.ccyCd, dtlTMsg.entDealNetUnitPrcAmt));
//                        ZYPEZDItemValueSetter.setValue(dtlTMsg.cpoDtlDealNetAmt, dtlTMsg.dealPrcListPrcAmt.getValue().multiply(qty));
//                        ZYPEZDItemValueSetter.setValue(dtlTMsg.cpoDtlDealSlsAmt, dtlTMsg.entDealNetUnitPrcAmt.getValue().multiply(qty));
                        ZYPEZDItemValueSetter.setValue(dtlTMsg.cpoDtlDealNetAmt, prcApi4PMsg.xxUnitNetPrcAmt);
                        ZYPEZDItemValueSetter.setValue(dtlTMsg.cpoDtlDealSlsAmt, prcApi4PMsg.xxUnitGrsPrcAmt);

                        ZYPEZDItemValueSetter.setValue(dtlTMsg.cpoDtlFuncNetAmt, exchFuncUnitPrice(hdrBean, dtlTMsg.ccyCd, dtlTMsg.cpoDtlDealNetAmt));
                        ZYPEZDItemValueSetter.setValue(dtlTMsg.cpoDtlFuncSlsAmt, exchFuncUnitPrice(hdrBean, dtlTMsg.ccyCd, dtlTMsg.cpoDtlDealSlsAmt));
                    } else {
                        dtlBean.addErrorInfo(prcCatgResult.getErrInfoList());
                    }
                }
            }
        } else {
            DS_IMPT_RTRN_PRC_CALCTMsg rtnPrcCalcBaseMsg, baseRtnPrcCalcBaseMsg;
            for (NWAL2320_ImptRtrnDetailBean rtnDtlBean : hdrBean.rtrnDtlBeanList) {
                DS_IMPT_ORD_RTRN_DTLTMsg rtnDtlTMsg = rtnDtlBean.me;
                baseRtnPrcCalcBaseMsg = null;

                prcApi2PMsg = getNWZC157002PMsg(prcApiPMsg, rtnDtlBean.me.ordSrcRefLineNum, rtnDtlBean.me.ordSrcRefLineSubNum);
                if (prcApi2PMsg != null) {
                    for (int i = 0; i < prcApi2PMsg.NWZC157003PMsg.getValidCount(); i++) {
                        rtnPrcCalcBaseMsg = createPriceingApiRtnCalcBaseResult(hdrBean, rtnDtlBean.me.dsImptOrdRtrnDtlPk, prcApi2PMsg.NWZC157003PMsg.no(i));
                        rtnDtlBean.rtnPrcCalcBaseTMsgList.add(rtnPrcCalcBaseMsg);

                        // QC#11837
                        if (S21StringUtil.isEquals(rtnPrcCalcBaseMsg.prcCondTpCd.getValue(), PRC_DTL_GRP.BASE_PRICE)) {
                            baseRtnPrcCalcBaseMsg = rtnPrcCalcBaseMsg;
                        }
                    }
                }

                prcApi4PMsg = getNWZC157004PMsg(prcApiPMsg, rtnDtlBean.me.ordSrcRefLineNum, rtnDtlBean.me.ordSrcRefLineSubNum);
                if (prcApi4PMsg != null) {
//                    qty = rtnDtlTMsg.ordQty.getValue();

                    prcCatgResult = NWXC220001.getPrcCatgTMsg(rtnDtlTMsg.glblCmpyCd.getValue(), rtnDtlTMsg.prcCatgCd.getValue());
                    if (!prcCatgResult.hasError()) {
                        ccyCd = prcCatgResult.getResultObject().ccyCd.getValue();
                        ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.ccyCd, ccyCd);

                        // QC#11837
//                        ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.dealPrcListPrcAmt, prcApi4PMsg.xxUnitGrsPrcAmt);
                        ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.dealPrcListPrcAmt, (baseRtnPrcCalcBaseMsg != null ? baseRtnPrcCalcBaseMsg.autoPrcAmtRate.getValue() : BigDecimal.ZERO));
                        ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.funcPrcListPrcAmt, exchFuncUnitPrice(hdrBean, rtnDtlTMsg.ccyCd, rtnDtlTMsg.dealPrcListPrcAmt));

                        ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.entDealNetUnitPrcAmt, prcApi4PMsg.xxUnitNetPrcAmt);
                        ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.entFuncNetUnitPrcAmt, exchFuncUnitPrice(hdrBean, rtnDtlTMsg.ccyCd, rtnDtlTMsg.entDealNetUnitPrcAmt));

//                        ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.cpoDtlDealNetAmt, rtnDtlTMsg.dealPrcListPrcAmt.getValue().multiply(qty));
//                        ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.cpoDtlDealSlsAmt, rtnDtlTMsg.entDealNetUnitPrcAmt.getValue().multiply(qty));
                        ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.cpoDtlDealNetAmt, prcApi4PMsg.xxUnitNetPrcAmt);
                        ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.cpoDtlDealSlsAmt, prcApi4PMsg.xxUnitGrsPrcAmt);

                        ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.cpoDtlFuncNetAmt, exchFuncUnitPrice(hdrBean, rtnDtlTMsg.ccyCd, rtnDtlTMsg.cpoDtlDealNetAmt));
                        ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.cpoDtlFuncSlsAmt, exchFuncUnitPrice(hdrBean, rtnDtlTMsg.ccyCd, rtnDtlTMsg.cpoDtlDealSlsAmt));
                    } else {
                        rtnDtlBean.addErrorInfo(prcCatgResult.getErrInfoList());
                    }
                }
            }
        }
    }


    private static DS_IMPT_PRC_CALC_BASETMsg createPriceingApiCalcBaseResult(NWAL2320_ImptHeaderBean hdrBean, EZDTBigDecimalItem dtlPk, NWZC157003PMsg prcApi3PMsg) {
        DS_IMPT_PRC_CALC_BASETMsg tMsg = new DS_IMPT_PRC_CALC_BASETMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, hdrBean.me.glblCmpyCd);

        BigDecimal pk;
        if (hdrBean.doInsertTable) {
           pk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_PRC_CALC_BASE_SQ);
           ZYPEZDItemValueSetter.setValue(tMsg.dsImptPrcCalcBasePk, pk);
        }

        ZYPEZDItemValueSetter.setValue(tMsg.dsImptOrdDtlPk, dtlPk);
        ZYPEZDItemValueSetter.setValue(tMsg.dsImptOrdPk, hdrBean.me.dsImptOrdPk);

        ZYPEZDItemValueSetter.setValue(tMsg.prcCondTpCd, prcApi3PMsg.prcCondTpCd);
        ZYPEZDItemValueSetter.setValue(tMsg.prcDtlGrpCd, prcApi3PMsg.prcDtlGrpCd);
        ZYPEZDItemValueSetter.setValue(tMsg.prcJrnlGrpCd, prcApi3PMsg.prcJrnlGrpCd);
        ZYPEZDItemValueSetter.setValue(tMsg.pkgUomCd, prcApi3PMsg.pkgUomCd);
        ZYPEZDItemValueSetter.setValue(tMsg.prcCondUnitCd, prcApi3PMsg.prcCondUnitCd);
        ZYPEZDItemValueSetter.setValue(tMsg.prcCalcMethCd, prcApi3PMsg.prcCalcMethCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsMdsePrcPk, prcApi3PMsg.dsMdsePrcPk);
        ZYPEZDItemValueSetter.setValue(tMsg.specCondPrcPk, prcApi3PMsg.specCondPrcPk);
        ZYPEZDItemValueSetter.setValue(tMsg.frtPerWtPk, prcApi3PMsg.frtPerWtPk);
        ZYPEZDItemValueSetter.setValue(tMsg.prcCondManEntryFlg, prcApi3PMsg.prcCondManEntryFlg);
        ZYPEZDItemValueSetter.setValue(tMsg.prcCondManAddFlg, prcApi3PMsg.prcCondManAddFlg);
        ZYPEZDItemValueSetter.setValue(tMsg.prcCondManDelFlg, prcApi3PMsg.prcCondManDelFlg);
        ZYPEZDItemValueSetter.setValue(tMsg.autoPrcAmtRate, prcApi3PMsg.autoPrcAmtRate);
        ZYPEZDItemValueSetter.setValue(tMsg.maxPrcAmtRate, prcApi3PMsg.maxPrcAmtRate);
        ZYPEZDItemValueSetter.setValue(tMsg.minPrcAmtRate, prcApi3PMsg.minPrcAmtRate);
        ZYPEZDItemValueSetter.setValue(tMsg.manPrcAmtRate, prcApi3PMsg.manPrcAmtRate);
        ZYPEZDItemValueSetter.setValue(tMsg.calcPrcAmtRate, prcApi3PMsg.calcPrcAmtRate);
        ZYPEZDItemValueSetter.setValue(tMsg.unitPrcAmt, prcApi3PMsg.unitPrcAmt);
        ZYPEZDItemValueSetter.setValue(tMsg.autoPrcCcyCd, prcApi3PMsg.autoPrcCcyCd);
        // 2018/09/18 QC#9700 Add Start
        ZYPEZDItemValueSetter.setValue(tMsg.prcRuleApplyFlg, prcApi3PMsg.prcRuleApplyFlg);
        ZYPEZDItemValueSetter.setValue(tMsg.prcRulePrcdPk, prcApi3PMsg.prcRulePrcdPk);
        // 2018/09/18 QC#9700 Add End

        return tMsg;
    }

    private static DS_IMPT_RTRN_PRC_CALCTMsg createPriceingApiRtnCalcBaseResult(NWAL2320_ImptHeaderBean hdrBean, EZDTBigDecimalItem rtnDtlPk, NWZC157003PMsg prcApi3PMsg) {
        DS_IMPT_RTRN_PRC_CALCTMsg tMsg = new DS_IMPT_RTRN_PRC_CALCTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, hdrBean.me.glblCmpyCd);

        BigDecimal pk;
        if (hdrBean.doInsertTable) {
           pk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_RTRN_PRC_CALC_SQ);
           ZYPEZDItemValueSetter.setValue(tMsg.dsImptRtrnPrcCalcPk, pk);
        }

        ZYPEZDItemValueSetter.setValue(tMsg.dsImptOrdRtrnDtlPk, rtnDtlPk);
        ZYPEZDItemValueSetter.setValue(tMsg.dsImptOrdPk, hdrBean.me.dsImptOrdPk);

        ZYPEZDItemValueSetter.setValue(tMsg.prcCondTpCd, prcApi3PMsg.prcCondTpCd);
        ZYPEZDItemValueSetter.setValue(tMsg.prcDtlGrpCd, prcApi3PMsg.prcDtlGrpCd);
        ZYPEZDItemValueSetter.setValue(tMsg.prcJrnlGrpCd, prcApi3PMsg.prcJrnlGrpCd);
        ZYPEZDItemValueSetter.setValue(tMsg.pkgUomCd, prcApi3PMsg.pkgUomCd);
        ZYPEZDItemValueSetter.setValue(tMsg.prcCondUnitCd, prcApi3PMsg.prcCondUnitCd);
        ZYPEZDItemValueSetter.setValue(tMsg.prcCalcMethCd, prcApi3PMsg.prcCalcMethCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsMdsePrcPk, prcApi3PMsg.dsMdsePrcPk);
        ZYPEZDItemValueSetter.setValue(tMsg.specCondPrcPk, prcApi3PMsg.specCondPrcPk);
        ZYPEZDItemValueSetter.setValue(tMsg.frtPerWtPk, prcApi3PMsg.frtPerWtPk);
        ZYPEZDItemValueSetter.setValue(tMsg.prcCondManEntryFlg, prcApi3PMsg.prcCondManEntryFlg);
        ZYPEZDItemValueSetter.setValue(tMsg.prcCondManAddFlg, prcApi3PMsg.prcCondManAddFlg);
        ZYPEZDItemValueSetter.setValue(tMsg.prcCondManDelFlg, prcApi3PMsg.prcCondManDelFlg);
        ZYPEZDItemValueSetter.setValue(tMsg.autoPrcAmtRate, prcApi3PMsg.autoPrcAmtRate);
        ZYPEZDItemValueSetter.setValue(tMsg.maxPrcAmtRate, prcApi3PMsg.maxPrcAmtRate);
        ZYPEZDItemValueSetter.setValue(tMsg.minPrcAmtRate, prcApi3PMsg.minPrcAmtRate);
        ZYPEZDItemValueSetter.setValue(tMsg.manPrcAmtRate, prcApi3PMsg.manPrcAmtRate);
        ZYPEZDItemValueSetter.setValue(tMsg.calcPrcAmtRate, prcApi3PMsg.calcPrcAmtRate);
        ZYPEZDItemValueSetter.setValue(tMsg.unitPrcAmt, prcApi3PMsg.unitPrcAmt);
        ZYPEZDItemValueSetter.setValue(tMsg.autoPrcCcyCd, prcApi3PMsg.autoPrcCcyCd);
        // 2018/09/18 QC#9700 Add Start
        ZYPEZDItemValueSetter.setValue(tMsg.prcRuleApplyFlg, prcApi3PMsg.prcRuleApplyFlg);
        ZYPEZDItemValueSetter.setValue(tMsg.prcRulePrcdPk, prcApi3PMsg.prcRulePrcdPk);
        // 2018/09/18 QC#9700 Add End

        return tMsg;
    }

    private static BigDecimal exchFuncUnitPrice(NWAL2320_ImptHeaderBean hdrBean, EZDTStringItem ccyCd, EZDTBigDecimalItem dealAmt) {

        return NWXC220001.exchFuncUnitPrice(hdrBean.me.glblCmpyCd.getValue(), hdrBean.slsDt, ccyCd.getValue(), dealAmt.getValue(), dealAmt.getValue());
//        BigDecimal funcAmt = null;
//        NWXC001001ExchangeData exchData = new NWXC001001ExchangeData();
//        exchData.setGlblCmpyCd(hdrBean.me.glblCmpyCd.getValue());
//        exchData.setSlsDt(hdrBean.slsDt);
//        exchData.setCcyCd(ccyCd.getValue());
//        List<NWXC001001ExchangePriceData> priceDataList = new ArrayList<NWXC001001ExchangePriceData>();
//        ExchangeAmoutData exchAmtData = new ExchangeAmoutData();
//
//        NWXC001001ExchangeAmount grsAmt = new NWXC001001ExchangeAmount();
//        grsAmt.setDealAmt(amt.getValue());
//
//        exchAmtData.setGrsAmt(grsAmt);
//        priceDataList.add(exchAmtData);
//
//        exchData.setPriceData(priceDataList);
//
//        NWXC001001Exchange.exchFuncUnitPrice(exchData);
//        if (!exchData.getXxMsgIdList().isEmpty()) {
//            return null;
//        }
//        for (int i = 0; i < exchData.getPriceData().size(); i++) {
//            NWXC001001ExchangePriceData prcData = exchData.getPriceData().get(i);
//            for (int j = 0; j < prcData.getAmountList().size(); j++) {
//                funcAmt = prcData.getAmountList().get(j).getFuncAmt();
//            }
//        }
//        return funcAmt;
    }

    private static boolean checkAndAddMasterNotFoundError(INWAL2320_ImptBean iImptBean, S21SsmEZDResult ssmResult, Object... param) {
        if (ssmResult.isCodeNotFound()) {
            NWXC220001ErrorInfo errorInfo = new NWXC220001ErrorInfo(NWAL2320Constant.NWAM0181E, param);
            iImptBean.addErrorInfo(errorInfo);
        }

        return !ssmResult.isCodeNotFound();
    }

    private static int addErrorInfoForEZDPMsg(INWAL2320_ImptBean iImptBean, EZDPMsg pMsg) {
        List<NWXC220001ErrorInfo> errInfoList = NWXC220001ErrorInfoHelper.getErrorInfoForEZDPMsg(pMsg);

        for (NWXC220001ErrorInfo errorInfo : errInfoList) {
            iImptBean.addErrorInfo(errorInfo);
        }

        return errInfoList.size();
    }

    private static void addErrorInfo(List<NWXC220001ErrorInfo> errorInfoList, NWXC220001Constant.MSG_ID errMsgId, Object... param) {
        addErrorInfo(errorInfoList, errMsgId.toString(), param);
    }

    private static void addErrorInfo(List<NWXC220001ErrorInfo> errorInfoList, String errMsgId, Object... param) {
        if (errorInfoList == null) {
            return;
        }

        NWXC220001ErrorInfo errorInfo = new NWXC220001ErrorInfo(errMsgId, param);
        errorInfoList.add(errorInfo);
    }

    /**
     * isNumber
     * @param val String
     * @return boolean
     */
    public static boolean isNumber(String val) {
        String regex = "\\A[-]?[0-9]+\\z";
        Pattern p = Pattern.compile(regex);
        Matcher m1 = p.matcher(val);
        return m1.find();
    }
    
    // 2019/03/26 S21_NA#30924 Add Start
    public static boolean isNoInvtyItem(String glblCmpyCd, String mdseCd) {
        if (!ZYPCommonFunc.hasValue(mdseCd)) {
            return false;
        }

        MDSETMsg mdseTmsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);

        if (ZYPConstant.FLG_OFF_N.equals(mdseTmsg.invtyCtrlFlg.getValue())) {
            return true;
        }
        return false;
    }

    private static void setSerInfo(String glblCmpyCd, String serNum, String mdseCd, DS_IMPT_ORD_CONFIGTMsg configTMsg, DS_IMPT_ORD_RTRN_DTLTMsg rtnDtlTMsg) {
        Map<String, Object> map = null;
        if (ZYPCommonFunc.hasValue(serNum)) {
            map = NWXC150001DsCheck.getSerNumInfo(glblCmpyCd, serNum, mdseCd);
        }
        // if (map == null) {
        if (map == null && !ZYPCommonFunc.hasValue(configTMsg.svcConfigMstrPk)) {
        // 2019/03/26 S21_NA#30924 Mod End
            ZYPEZDItemValueSetter.setValue(configTMsg.configTpCd, CONFIG_TP.RETURN_NEW);
        } else { //2018/04/25 QC#22189 Add Start
            // Getting Model ID
            ZYPEZDItemValueSetter.setValue(configTMsg.svcConfigMstrPk, (BigDecimal) map.get("SVC_CONFIG_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(rtnDtlTMsg.svcConfigMstrPk, (BigDecimal) map.get("SVC_CONFIG_MSTR_PK"));
            SVC_CONFIG_MSTRTMsg svcConfigMstrTMsg = new SVC_CONFIG_MSTRTMsg();
            ZYPEZDItemValueSetter.setValue(svcConfigMstrTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(svcConfigMstrTMsg.svcConfigMstrPk, (BigDecimal) map.get("SVC_CONFIG_MSTR_PK"));
            svcConfigMstrTMsg = (SVC_CONFIG_MSTRTMsg) S21FastTBLAccessor.findByKey(svcConfigMstrTMsg);
            if (svcConfigMstrTMsg != null) {
                ZYPEZDItemValueSetter.setValue(configTMsg.mdlId, svcConfigMstrTMsg.mdlId);
            }
            // Getting Model Desc Text
            if (ZYPCommonFunc.hasValue(configTMsg.mdlId) && !ZYPCommonFunc.hasValue(configTMsg.mdlDescTxt)) {
                DS_MDLTMsg dsMdlTMsg = new DS_MDLTMsg();
                ZYPEZDItemValueSetter.setValue(dsMdlTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsMdlTMsg.mdlId, configTMsg.mdlId);
                dsMdlTMsg = (DS_MDLTMsg) S21CacheTBLAccessor.findByKey(dsMdlTMsg);
                if (dsMdlTMsg != null) {
                    ZYPEZDItemValueSetter.setValue(configTMsg.mdlDescTxt, dsMdlTMsg.mdlDescTxt);
                }
            }
            ZYPEZDItemValueSetter.setValue(configTMsg.configTpCd, CONFIG_TP.RETURN_EXISTING_IB);
        }
    }
    // 2019/03/26 S21_NA#30924 Add End

}
