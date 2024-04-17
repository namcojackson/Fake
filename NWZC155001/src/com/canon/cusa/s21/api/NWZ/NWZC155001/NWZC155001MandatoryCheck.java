/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC155001;

import static com.canon.cusa.s21.api.NWZ.NWZC155001.constant.NWZC155001Constant.NWZM1945E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import parts.common.EZDTMsg;
import business.parts.NWZC155001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DI_CHK_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * DI Check API - Mandatory Check
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/08   SRA             E.Inada         Create          N/A
 * 2017/03/08   Fujitsu         T.Yoshida       Update          S21_NA#17638
 * 2017/07/20   Fujitsu         S.Takami        Update          S21_NA#20041
 * 2017/07/21   Fujitsu         S.Takami        Update          S21_NA#20041-2
 * 2017/07/24   Fujitsu         S.Takami        Update          S21_NA#20041-3
 * 2017/10/24   Fujitsu         S.Takami        Update          S21_NA#22035
 * 2017/12/08   Fujitsu         M.Yamada        Update          S21_NA#22874
 *</pre>
 */
public class NWZC155001MandatoryCheck extends NWZC155001 {

    /** TMsg package name */
    private static final String TMSG_PKG_NM = "business.db.";

    /** TMsg class name */
    private static final String TMSG_CLASS_NM = "TMsg";

    /** Mandatory Column */
    private static final String COL_CPO_ORD_NUM = "CPO_ORD_NUM";

    /** Select Column Name */
    private final String[] COL_SELECT_LIST = new String[] { //
    "DS_CPO_LINE_NUM" //
            , "DS_CPO_LINE_SUB_NUM" //
            , "DS_CPO_CONFIG_PK" //
            , "DS_ORD_POSN_NUM" //
            , "CONFIG_CATG_CD" //
    };

    /** Where Column Name (optional) */
    private final String[] COL_WHERE_DTL_LIST = new String[] { //
    "CPO_ORD_NUM" //
            , "CPO_DTL_LINE_NUM" //
            , "CPO_DTL_LINE_SUB_NUM" //
    };

    /** Where Column Name (optional) */
    private final String[] COL_WHERE_CONFIG_LIST = new String[] { //
    "DS_CPO_CONFIG_PK" //
    };

    /** Exclude list for DS_CPO_DTL */
    private final String[] COL_EXCL_DTL_LIST = new String[] { //
    "DS_CPO_LINE_NUM" //
            , "DS_CPO_LINE_SUB_NUM" //
    };

    /** Exclude list for DS_CPO_CONFIG */
    private final String[] COL_EXCL_CONFIG_LIST = new String[] { //
    "DS_CPO_CONFIG_PK" //
            , "DS_ORD_POSN_NUM" //
            , "CONFIG_CATG_CD" //
    };

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmClient;

    private Map<String, EZDTMsg> tblCache = null;

    // 2017/07/20 S21_NA#20041 Add Start
    /**  */
    private static final String CHK_LVL_CONFIG = "LevelConfig";

    /**  */
    private static final String CHK_LVL_LINE = "LevelLine";

    /**  */
    private static final String CHK_LVL_HDR = "LevelHeader";
    // 2017/07/20 S21_NA#20041 Add End

    public NWZC155001MandatoryCheck() {
        this.ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    @Deprecated
    @Override
    public void execute(final NWZC155001PMsg pMsg, final ONBATCH_TYPE onBatch) {
        // none
    };

    public void doProcessCheck(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean, S21ApiMessageMap msgMap) {
        Map<String, String> descMap = new HashMap<String, String>();
        // search definition
        Map<String, Set<String>> tblMap = getTableMap(cpoBean, diChkBean, descMap);

        if (tblMap == null) {
            return;
        }

        // check table and column name
        for (Entry<String, Set<String>> tblEntry : tblMap.entrySet()) {
//            Set<String> colList = tblMap.get(tblName);
            String tblName = tblEntry.getKey();

            // 2017/07/21 S21_NA#20041-2 Add Start
            boolean isTableForHeader = isTableForHeader(tblName);
            boolean isDiChkHdr = DI_CHK_LVL.HEADER.equals(diChkBean.getDiChkLvlCd());
            boolean isDiChkLine = DI_CHK_LVL.LINE.equals(diChkBean.getDiChkLvlCd());
            if ((isDiChkHdr && !isTableForHeader) //
                   || (isDiChkLine && isTableForHeader)) {
                continue;
            }
            // 2017/07/21 S21_NA#20041-2 Add End

            Set<String> colList = tblEntry.getValue();
            // add search target (CPO#)

            if (!checkColName(tblName, colList)) {
                // system error
                msgMap.addXxMsgId(NWZM1945E);
                S21InfoLogOutput.println("Error: Table or Columns don't exist in " + tblName);
                return;
            }

            Set<String> selectList = createSelectColumns(tblName, colList);
            // 2017/07/20 S21_NA#20041 Add Start
            String checkLevel = hasDetailColmns(selectList);
            // 2017/07/20 S21_NA#20041 Add End

            if (selectList == null) {
                // system error
                msgMap.addXxMsgId(NWZM1945E);
                S21InfoLogOutput.println("Error: Select columns don't exist in " + tblName);
                return;
            }

            List<Map<String, Object>> targetList = searchMandatoryColumns(cpoBean, tblName, selectList, diChkBean.getDiChkLvlCd());

            // S21_NA#17638 Add Start
            if (targetList.size() == 0) {
                targetList.add(new HashMap<String, Object>());
            }
            // S21_NA#17638 Add End

            // check mandatory
            // 2017/07/20 S21_NA#20041 Mod Start
//            for (Map<String, Object> map : targetList) {
//                checkMandatory(tblName, map, colList, cpoBean, diChkBean, descMap);
//            }
            if (CHK_LVL_HDR.equals(checkLevel)) {
                for (Map<String, Object> map : targetList) {
                    checkMandatory(tblName, map, colList, cpoBean, diChkBean, descMap);
                }
            } else {
                checkMandatoryForLine(tblName, targetList, colList, cpoBean, diChkBean, descMap, checkLevel);
            }
            // 2017/07/20 S21_NA#20041 Mod End
        }
    }

    private void checkMandatory(String tblName, Map<String, Object> map, Collection<String> selectList, NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean, Map<String, String> descMap) {

        for (String colName : selectList) {
            if (map.get(colName) == null) {
                // get description text
                String descTxt = colName;
                String key = createTblCacheKey(tblName, colName);
                if (descMap.get(key) != null) {
                    descTxt = descMap.get(key);
                }

                // error
                setError(map, descTxt, cpoBean, diChkBean);
            }
        }
    }

    /**
     * Set Error Message to Result table.
     * @param map
     * @param colName
     * @param cpoBean
     * @param diChkBean
     */
    private void setError(Map<String, Object> map, String descTxt, NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {

        String diChkCd = diChkBean.getDiChckCd();
        String diChkLvlCd = diChkBean.getDiChkLvlCd();
        String diChkRsltTpCd = diChkBean.getDiChkRsltTpCd();

        List<NWZC155001CpoDtlBean> cpoDtlBeanList = cpoBean.getCpoDtlBeanList();
        String diChkErrTxt = getErrMsg(descTxt, diChkLvlCd);

        if (DI_CHK_LVL.HEADER.equals(diChkLvlCd)) {
            // Header Level
            setErrToDiRsltBean(cpoBean, null, diChkCd, diChkLvlCd, diChkRsltTpCd, diChkErrTxt);

        } else {

            // get column value
            BigDecimal dsCpoDtlLineNum = (BigDecimal) getColumnValue(map, "DS_CPO_LINE_NUM", "B_DS_CPO_LINE_NUM");
            BigDecimal dsCpoDtlLineSubNum = (BigDecimal) getColumnValue(map, "DS_CPO_LINE_SUB_NUM", "B_DS_CPO_LINE_SUB_NUM");
            String dsOrdPosnNum = (String) getColumnValue(map, "DS_ORD_POSN_NUM", "C_DS_CPO_POSN_NUM");
            String confiCatgCd = (String) getColumnValue(map, "CONFIG_CATG_CD", "C_CONFIG_CATG_CD");
            BigDecimal dsCpoConfigPk = (BigDecimal) getColumnValue(map, "DS_CPO_CONFIG_PK", "C_DS_CPO_CONFIG_PK");

            NWZC155001CpoDtlBean cpoDtlBean = getCpoDtlBean(cpoDtlBeanList, dsCpoDtlLineNum, dsCpoDtlLineSubNum);

            if (cpoDtlBean != null) {
                // Detail Level
                setErrToDiRsltBean(cpoBean, cpoDtlBean, diChkCd, diChkLvlCd, diChkRsltTpCd, diChkErrTxt);

                return;
            }

            if (dsCpoConfigPk != null) {
                // Config Level
                setErrToDiRsltBeanConfigLvl(cpoBean, diChkCd, diChkLvlCd, diChkRsltTpCd, diChkErrTxt, dsCpoConfigPk, confiCatgCd, dsOrdPosnNum);

                return;
            }

            // Header Level
            setErrToDiRsltBean(cpoBean, null, diChkCd, diChkLvlCd, diChkRsltTpCd, diChkErrTxt);
        }
    }

    public String getErrMsg(String descTxt, String diChkLvlCd) {
        StringBuilder sb = new StringBuilder("'").append(descTxt).append("' is mandatory to be entered on the order ");

        if (DI_CHK_LVL.HEADER.equals(diChkLvlCd)) {
            sb.append("header.");
        } else {
            sb.append("line.");
        }
        return sb.toString();
    }

    private NWZC155001CpoDtlBean getCpoDtlBean(List<NWZC155001CpoDtlBean> cpoDtlBeanList, BigDecimal dsCpoDtlLineNum, BigDecimal dsCpoDtlLineSubNum) {

        if (dsCpoDtlLineNum == null) {
            return null;
        }

        String strDsCpoDtlLineNum = dsCpoDtlLineNum.toString();
        String strDsCpoDtlLineSubNum = dsCpoDtlLineSubNum != null ? dsCpoDtlLineSubNum.toString() : null;

        for (NWZC155001CpoDtlBean cpoDtlBean : cpoDtlBeanList) {
            if (strDsCpoDtlLineNum.equals(cpoDtlBean.getDsCpoLineNum()) //
                    && S21StringUtil.isEquals(strDsCpoDtlLineSubNum, cpoDtlBean.getDsCpoLineSubNum())) {
                return cpoDtlBean;
            }
        }
        return null;
    }

    private Object getColumnValue(Map<String, Object> map, String... colList) {
        for (String colName : colList) {
            Object val = map.get(colName);
            if (val != null) {
                return val;
            }
        }
        return null;
    }

    private List<Map<String, Object>> searchMandatoryColumns(NWZC155001CpoBean cpoBean, String tblName, Collection<String> selectList, String diChkLvlCd) {
        // create sql parameter
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        param.put("cpoOrdNum", cpoBean.getCpoOrdNum());
        param.put("tblName", tblName);
        param.put("colList", selectList);

        if (!DI_CHK_LVL.HEADER.equals(diChkLvlCd)) {
            // combine DS_CPO_DTL if line# exits.
            if (checkColName(tblName, COL_WHERE_DTL_LIST)) {
                if (!checkColName(tblName, COL_EXCL_DTL_LIST)) {
                    param.put("isDsCpoDtl", ZYPConstant.FLG_ON_Y);
                }
            }
            // combine DS_CPO_CONFIG if configPk exists.
            if (checkColName(tblName, COL_WHERE_CONFIG_LIST)) {
                if (!checkColName(tblName, COL_EXCL_CONFIG_LIST)) {
                    // 2017/07/20 S21_NA#20041 Mod Start
//                    param.put("isDsConfig", ZYPConstant.FLG_ON_Y);
                    param.put("isDsCpoConfig", ZYPConstant.FLG_ON_Y);
                    // 2017/07/20 S21_NA#20041 Mod Start
                }
            }
        }

        // execute sql
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> list = (List<Map<String, Object>>) ssmClient.queryObjectList("searchMandatoryColumns", param);
        return list;
    }

    private Map<String, Set<String>> getTableMap(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean, Map<String, String> descMap) {

        List<Map<String, String>> tblList = getDiMndChkDfn(cpoBean, diChkBean, descMap);
        if (tblList == null || tblList.isEmpty()) {
            return null;
        }

        Map<String, Set<String>> tblMap = new LinkedHashMap<String, Set<String>>();

        for (Map<String, String> map : tblList) {
            // get definition value
            String descTxt = map.get("DI_MND_CHK_DFN_NM");
            String tblName = map.get("DI_MND_CHK_TBL_NM");
            String colName = map.get("DI_MND_CHK_COL_NM");

            // create column list
            Set<String> colList = null;
            if (tblMap.containsKey(tblName)) {
                colList = tblMap.get(tblName);
            } else {
                colList = new LinkedHashSet<String>();
                tblMap.put(tblName, colList);
            }
            colList.add(colName);

            // create description mapping
            String key = createTblCacheKey(tblName, colName);
            descMap.put(key, descTxt);
        }
        return tblMap;
    }

    private String createTblCacheKey(String tblName, String colName) {
        return S21StringUtil.concatStrings(tblName, ":", colName);
    }

    protected List<Map<String, String>> getDiMndChkDfn(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean, Map<String, String> descMap) {
        if (!ZYPCommonFunc.hasValue(diChkBean.getDiMndChkPrflCd())) {
            return null;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        param.put("diMndChkPrflCd", diChkBean.getDiMndChkPrflCd());

        @SuppressWarnings("unchecked")
        List<Map<String, String>> tblList = (List<Map<String, String>>) ssmClient.queryObjectList("getDiMndChkDfn", param);
        return tblList;
    }

    /**
     * check column name for list
     * @param tblName
     * @param colList
     * @return boolean
     */
    private boolean checkColName(String tblName, String[] colList) {
        for (String colName : colList) {
            if (!checkColName(tblName, colName)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColName(String tblName, Collection<String> colList) {
        if (colList != null) {
            return checkColName(tblName, colList.toArray(new String[0]));
        }
        return true;
    }

    /**
     * check column name by TMsg.
     * @param tblName
     * @param colName
     * @return boolean
     */
    private boolean checkColName(String tblName, String colName) {
        EZDTMsg tMsg = null;

        if (tblCache == null) {
            tblCache = new HashMap<String, EZDTMsg>();
        }

        if (tblCache.containsKey(tblName)) {
            tMsg = tblCache.get(tblName);

        } else {
            try {
                // create TMsg object.
                String tMsgName = TMSG_PKG_NM + tblName + TMSG_CLASS_NM;
                tMsg = (EZDTMsg) Class.forName(tMsgName).newInstance();
                tblCache.put(tblName, tMsg);

            } catch (ClassNotFoundException e) {
                return false;
            } catch (InstantiationException e) {
                return false;
            } catch (IllegalAccessException e) {
                return false;
            }
        }

        @SuppressWarnings("unchecked")
        List<String>[] selectColList = tMsg.getSelectColumnList();

        return findColName(selectColList[1], colName);
    }

    /**
     * Exist : true
     * @param selectColList
     * @param colName
     * @return boolean
     */
    private boolean findColName(Collection<String> selectColList, String colName) {
        for (String tMsgColName : selectColList) {
            if (colName.equals(tMsgColName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * <pre>
     * create Select Column List
     *  - Define columns & CPO# & Line#'s
     * </pre>
     * @param tblName
     * @param colList
     * @return Set<String>
     */
    private Set<String> createSelectColumns(String tblName, Set<String> colList) {
        // copy columns of definition
        Set<String> selectList = new LinkedHashSet<String>(colList);

        // CPO# is mandatory.
        if (!checkColName(tblName, COL_CPO_ORD_NUM)) {
            return null;
        }
        selectList.add(COL_CPO_ORD_NUM);

        // CPO Line#'s are used for error message
        for (String dtlColName : COL_SELECT_LIST) {
            if (checkColName(tblName, dtlColName)) {
                selectList.add(dtlColName);
            }
        }
        return selectList;
    }

    // 2017/07/20 S21_NA#20041 Add Start
    private void checkMandatoryForLine(String tblName, //
            List<Map<String, Object>> targetList, //
            Collection<String> selectList, //
            NWZC155001CpoBean cpoBean, //
            NWZC155001DiChkBean diChkBean, //
            Map<String, String> descMap, //
            String checkLevel) {

        // 2017/07/24 S21_NA#20041-3 Add Start
        List<Map<String, Object>> checkedMapList = new ArrayList<Map<String, Object>>(0);
        // 2017/07/24 S21_NA#20041-3 Add End

        // 2017/10/24 S21_NA#22035 Add Start
        String crAndBillOnlyWhCd = ZYPCodeDataUtil.getVarCharConstValue("CR_AND_BILL_ONLY_DUMMY_WH_CD", cpoBean.getGlblCmpyCd());
        List<String> crAndBillOnlyDummyWhCdList = null;
        if (crAndBillOnlyWhCd == null) {
            crAndBillOnlyDummyWhCdList = new ArrayList<String>(0);
        } else {
            String[] crAndBillOnlyDummyWhCdArray = crAndBillOnlyWhCd.split(",");
            crAndBillOnlyDummyWhCdList = Arrays.asList(crAndBillOnlyDummyWhCdArray);
        }
        // 2017/10/24 S21_NA#22035 Add End
        for (NWZC155001CpoDtlBean cpoDtlBean : cpoBean.getCpoDtlBeanList()) {
            // QC#22874
            if (ORD_LINE_STS.CANCELLED.equals(cpoDtlBean.getOrdLineStsCd()) //
                    || ORD_LINE_STS.CLOSED.equals(cpoDtlBean.getOrdLineStsCd()) //
                    || RTRN_LINE_STS.CANCELLED.equals(cpoDtlBean.getRtrnLineStsCd()) //
                    || RTRN_LINE_STS.CLOSED.equals(cpoDtlBean.getRtrnLineStsCd())) {
                continue;
            }
            String dsCpoLineNum = cpoDtlBean.getDsCpoLineNum();
            String dsCpoLineSubNum = cpoDtlBean.getDsCpoLineSubNum();
            BigDecimal dsCpoConfigPk = cpoDtlBean.getDsCpoConfigPk();
            String dsOrdPosnNum = cpoDtlBean.getDsOrdPosnNum();
            String configCatgCd = cpoDtlBean.getConfigCatgCd();

            // 2017/10/24 S21_NA#22035 Add Start
            String rtlWhCd = cpoDtlBean.getRtlWhCd();
            if (rtlWhCd == null) {
                rtlWhCd = "";
            }
            if (crAndBillOnlyDummyWhCdList.contains(rtlWhCd)) {
                continue;
            }
            // 2017/10/24 S21_NA#22035 Add End

            Map<String, Object> checkMap = null;
            for (Map<String, Object> map : targetList) {
                String bDsCpoLineNum = (String) map.get("B_DS_CPO_LINE_NUM");
                String bDsCpoLineSubNum = (String) map.get("B_DS_CPO_LINE_SUB_NUM");
                BigDecimal cDsCpoConfigPk = (BigDecimal) map.get("C_DS_CPO_CONFIG_PK");
                String cDsCpoPosnNum = (String) map.get("C_DS_CPO_POSN_NUM");
                String cConfigCatgCd = (String) map.get("C_CONFIG_CATG_CD");

                if (ZYPCommonFunc.hasValue(bDsCpoLineNum) //
                        && ZYPCommonFunc.hasValue(bDsCpoLineSubNum) //
                        && S21StringUtil.isEquals(dsCpoLineNum, bDsCpoLineNum) //
                        && S21StringUtil.isEquals(dsCpoLineSubNum, bDsCpoLineSubNum)
                        ) {
                    checkMap = map;
                    break;
                } else if (ZYPCommonFunc.hasValue(cDsCpoConfigPk) //
                        && ZYPCommonFunc.hasValue(cDsCpoPosnNum) //
                        && ZYPCommonFunc.hasValue(cConfigCatgCd) //
                        && dsCpoConfigPk.compareTo(cDsCpoConfigPk) == 0 //
                        && S21StringUtil.isEquals(dsOrdPosnNum, cDsCpoPosnNum) //
                        && S21StringUtil.isEquals(configCatgCd, cConfigCatgCd)) {
                    checkMap = map;
                    break;
                }
            }
            if (checkMap == null) {
                checkMap = new HashMap<String, Object>();
                if (CHK_LVL_LINE.equals(checkLevel)) {
                    checkMap.put("B_DS_CPO_LINE_NUM", cpoDtlBean.getDsCpoLineNum());
                    checkMap.put("B_DS_CPO_LINE_SUB_NUM", cpoDtlBean.getDsCpoLineSubNum());
                } else if (CHK_LVL_CONFIG.equals(checkLevel)) {
                    checkMap.put("C_DS_CPO_CONFIG_PK", cpoDtlBean.getDsCpoConfigPk());
                    checkMap.put("C_DS_CPO_POSN_NUM", cpoDtlBean.getDsOrdPosnNum());
                    checkMap.put("C_CONFIG_CATG_CD", cpoDtlBean.getConfigCatgCd());
                }
            }
            for (String colName : selectList) {

                if (checkMap.get(colName) == null) {
                    // get description text
                    String descTxt = colName;
                    String key = createTblCacheKey(tblName, colName);
                    if (descMap.get(key) != null) {
                        descTxt = descMap.get(key);
                    }

                    // 2017/07/24 S21_NA#20041-3 Add Start
                    Map<String, Object> alreadyCheckedMap = getCheckedMap(checkMap, colName);
                    if (isAlreadyCheced(checkedMapList, alreadyCheckedMap)) {
                        continue;
                    }
                    checkedMapList.add(alreadyCheckedMap);
                    // 2017/07/24 S21_NA#20041-3 Add End
                    // error
                    setError(checkMap, descTxt, cpoBean, diChkBean);
                }
            }
        }
    }

    private String hasDetailColmns(Set<String> selectList) {

        List<String> lineCols = new ArrayList<String>(0);
        lineCols.add("DS_CPO_LINE_NUM");
        lineCols.add("DS_CPO_LINE_SUB_NUM");

        List<String> configCols = new ArrayList<String>(0);
        configCols.add("DS_CPO_CONFIG_PK");
        configCols.add("DS_ORD_POSN_NUM");
        configCols.add("CONFIG_CATG_CD");

        for (String col : selectList) {
            if (lineCols.contains(col)) {
                return CHK_LVL_LINE;
            }
            if (configCols.contains(col)) {
                return CHK_LVL_CONFIG;
            }
        }
        return CHK_LVL_HDR;
    }
    // 2017/07/20 S21_NA#20041 Add End
    // 2017/07/21 S21_NA#20041-2 Add Start
    /**
     * check column name by TMsg.
     * @param tblName
     * @param colName
     * @return boolean
     */
    private boolean isTableForHeader(String tblName) {

        boolean isTableForHeader = true;
        EZDTMsg tMsg = null;

        if (tblCache == null) {
            tblCache = new HashMap<String, EZDTMsg>();
        }

        if (tblCache.containsKey(tblName)) {
            tMsg = tblCache.get(tblName);

        } else {
            try {
                // create TMsg object.
                String tMsgName = TMSG_PKG_NM + tblName + TMSG_CLASS_NM;
                tMsg = (EZDTMsg) Class.forName(tMsgName).newInstance();
                tblCache.put(tblName, tMsg);

            } catch (ClassNotFoundException e) {
                return false;
            } catch (InstantiationException e) {
                return false;
            } catch (IllegalAccessException e) {
                return false;
            }
        }

        @SuppressWarnings("unchecked")
        List<String>[] selectColList = tMsg.getSelectColumnList();

        List<String> lineColList = new ArrayList<String>(0);
        for (String lineCol : COL_SELECT_LIST) {
            lineColList.add(lineCol);
        }
        for (String colName : selectColList[1]) {
            if (lineColList.contains(colName)) {
                isTableForHeader = false;
                break;
            }
        }
        return isTableForHeader;
    }
    // 2017/07/21 S21_NA#20041-2 Add End
    // 2017/07/24 S21_NA#20041-3 Add Start
    private Map<String, Object> getCheckedMap(Map<String, Object>checkMap, String colName) {

        Map<String, Object> alreadyCheckedMap = new HashMap<String, Object>();
        Set<String> keys = checkMap.keySet();
        for (String key : keys) {
            if (S21StringUtil.isEquals(key, colName)) {
                continue;
            }
            alreadyCheckedMap.put(key, checkMap.get(key));
        }
        alreadyCheckedMap.put("colName", colName);

        return alreadyCheckedMap;
    }

    private boolean isAlreadyCheced(List<Map<String, Object>> checkedMapList, Map<String, Object> alreadyCheckedMap) {

        boolean isAlreadyCheced = false;
        for (Map<String, Object> listObj : checkedMapList) {
            boolean checked = true;
            for (String key : listObj.keySet()) {
                Object value1 = listObj.get(key);
                Object value2 = alreadyCheckedMap.get(key);
                if (value2 == null) {
                    checked = false;
                    break;
                }
                if (value1 instanceof String) {
                    if (!S21StringUtil.isEquals((String) value1, (String) value2)) {
                        checked = false;
                        break;
                    }
                } else if (value1 instanceof BigDecimal) {
                    BigDecimal val1 = (BigDecimal) value1;
                    BigDecimal val2 = (BigDecimal) value2;
                    if (val1.compareTo(val2) != 0) {
                        checked = false;
                    }
                }
            }
            if (checked) {
                isAlreadyCheced = true;
                break;
            }
        }
        return isAlreadyCheced;
    }
    // 2017/07/24 S21_NA#20041-3 Add End
}
