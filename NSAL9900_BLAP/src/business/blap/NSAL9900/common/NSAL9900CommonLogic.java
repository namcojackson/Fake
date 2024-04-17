package business.blap.NSAL9900.common;

import static business.blap.NSAL9900.constant.NSAL9900Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.leftPad;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parts.common.EZDCItem;
import parts.common.EZDCSVInFile;
import parts.common.EZDCommonFunc;
import parts.common.EZDItemAttribute;
import parts.common.EZDMsg;
import parts.common.EZDSItem;
import parts.common.EZDSStringItemArray;
import parts.common.EZDTMsg;
import parts.common.EZDValidatorException;
import parts.dbcommon.EZDTBLAccessor;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NSAL9900.NSAL9900CMsg;
import business.blap.NSAL9900.NSAL9900Query;
import business.blap.NSAL9900.NSAL9900SMsg;
import business.blap.NSAL9900.NSAL9900_ACMsg;
import business.blap.NSAL9900.NSAL9900_BCMsg;
import business.blap.NSAL9900.NSAL9900_BSMsg;
import business.blap.NSAL9900.NSAL9900_CCMsg;
import business.blap.NSAL9900.NSAL9900_CSMsg;
import business.blap.NSAL9900.NSAL9900_CSMsgArray;
import business.blap.NSAL9900.NSAL9900_DSMsg;
import business.blap.NSAL9900.addon.Addon;
import business.file.NSAL9900F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;


/**
 *<pre>
 * Master Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/11   Hitachi         T.Aoyagi        Create          N/A
 * 2016/03/09   Hitachi         T.Aoyagi        Update          QC#5213
 * 2016/03/10   Hitachi         T.Aoyagi        Update          QC#5258
 * 2016/05/10   Hitachi         A.Kohinata      Update          QC#5389
 * 2016/05/11   Hitachi         K.Yamada        Update          QC#7471
 * 2016/06/14   Hitachi         T.Aoyagi        Update          QC#9682
 * 2016/06/15   Hitachi         T.Aoyagi        Update          QC#9685
 * 2016/07/12   Fujitsu         M.Ugaki         Update          QC#11674
 * 2016/09/02   Hitachi         K.Yamada        Update          QC#10767
 * 2017/07/12   Hitachi         M.Kidokoro      Update          QC#18659,19534
 * 2018/11/26   Fujitsu         K.Ishizuka      Update          QC#29126
 * 2021/07/02   CITS            K.Ogino         Update          QC#58926
 *</pre>
 */
public class NSAL9900CommonLogic implements ZYPConstant {

    /**
     * Initial Data
     * @param cMsg NSAL9900CMsg
     * @param sMsg NSAL9900SMsg
     */
    public static void initData(NSAL9900CMsg cMsg, NSAL9900SMsg sMsg) {

        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

        String glblCmpyCd = cMsg.glblCmpyCd.getValue();
        setValue(sMsg.glblCmpyCd, glblCmpyCd);
        String tblNm = cMsg.tblCd.getValue();
        // Get Table Info
        Map<String, Object> tblInfo = NSAL9900Query.getInstance().getTblInfo(glblCmpyCd, tblNm);
        if (tblInfo == null) {
            cMsg.setMessageInfo(MSG_ID_ZZZM9006E, new String[]{"Table Information"});
            return;
        }
        setValue(sMsg.tblCd, (String) tblInfo.get("PHYS_MAINT_TRGT_TBL_NM"));
        String logicMaintTrgtTblNm = labelConv.convLabel2i18nLabel(SCREEN_ID, (String) tblInfo.get("LOGIC_MAINT_TRGT_TBL_NM"));
        setValue(sMsg.tblNm, logicMaintTrgtTblNm);
        setValue(sMsg.bizFuncGrpId, (String) tblInfo.get("BIZ_FUNC_GRP_ID"));
        setValue(sMsg.bizFuncId, (String) tblInfo.get("BIZ_FUNC_ID"));
        // START 2016/06/14 T.Aoyagi [QC#9682, ADD]
        setValue(sMsg.delFuncAvalFlg, (String) tblInfo.get("DEL_FUNC_AVAL_FLG"));
        // END 2016/06/14 T.Aoyagi [QC#9682, ADD]
        setValue(sMsg.insrFuncAvalFlg, (String) tblInfo.get("INSR_FUNC_AVAL_FLG")); // 2018/11/26 S21_NA#29126 Add
        // Get Column Info
        List<Map<String, Object>> colInfoList = NSAL9900Query.getInstance().getColInfo(glblCmpyCd, tblNm);
        if (colInfoList.isEmpty()) {
            cMsg.setMessageInfo(MSG_ID_ZZZM9006E, new String[]{"Column Information"});
            return;
        }

        int colInfoIdx;
        Map<String, Object> colInfo;
        String colTp;
        String xxColTp;
        String zeroPadColInfoIdx;
        String dtlItemNm;
        String colNmXxLiscCd;
        String colNmXxLiscNm;
        String srchItemFlg;
        String srchItemNm;
        String dplyCtrlCd;
        for (colInfoIdx = 0; colInfoIdx < colInfoList.size(); colInfoIdx++) {
            colInfo = colInfoList.get(colInfoIdx);
            colTp = (String) colInfo.get("COL_TP_CD");
            xxColTp = convColTp(colTp);
            zeroPadColInfoIdx = leftPad(String.valueOf(colInfoIdx), PAD_NUM, STR_ZERO);
            dtlItemNm = xxColTp + zeroPadColInfoIdx;
            colNmXxLiscCd = STR_XX_LIST_CD + STR_UNDER_BAR + zeroPadColInfoIdx;
            colNmXxLiscNm = STR_XX_LIST_NM + STR_UNDER_BAR + zeroPadColInfoIdx;

            // Set Column Info
            setValue(sMsg.C.no(colInfoIdx).physMaintTrgtTblNm, (String) colInfo.get("PHYS_MAINT_TRGT_TBL_NM"));
            setValue(sMsg.C.no(colInfoIdx).physMaintTrgtColNm, (String) colInfo.get("PHYS_MAINT_TRGT_COL_NM"));
            String logicMaintTrgtColNm = labelConv.convLabel2i18nLabel(SCREEN_ID, (String) colInfo.get("LOGIC_MAINT_TRGT_COL_NM"));
            setValue(sMsg.C.no(colInfoIdx).logicMaintTrgtColNm, logicMaintTrgtColNm);
            setValue(sMsg.C.no(colInfoIdx).uniqKeyFlg, (String) colInfo.get("UNIQ_KEY_FLG"));
            setValue(sMsg.C.no(colInfoIdx).colTpCd, (String) colInfo.get("COL_TP_CD"));
            setValue(sMsg.C.no(colInfoIdx).itemMaxLg, (BigDecimal) colInfo.get("ITEM_MAX_LG"));
            setValue(sMsg.C.no(colInfoIdx).itemDplyLg, (BigDecimal) colInfo.get("ITEM_DPLY_LG"));
            setValue(sMsg.C.no(colInfoIdx).itemSclNum, (BigDecimal) colInfo.get("ITEM_SCL_NUM"));
            setValue(sMsg.C.no(colInfoIdx).dplyCtrlCd, (String) colInfo.get("DPLY_CTRL_CD"));
            setValue(sMsg.C.no(colInfoIdx).dplySortNum, (BigDecimal) colInfo.get("DPLY_SORT_NUM"));
            setValue(sMsg.C.no(colInfoIdx).toUpperFlg, (String) colInfo.get("TO_UPPER_FLG"));
            setValue(sMsg.C.no(colInfoIdx).commaSeptFlg, (String) colInfo.get("COMMA_SEPT_FLG"));
            setValue(sMsg.C.no(colInfoIdx).defValTxt, (String) colInfo.get("DEF_VAL_TXT"));
            setValue(sMsg.C.no(colInfoIdx).srchItemFlg, (String) colInfo.get("SRCH_ITEM_FLG"));
            setValue(sMsg.C.no(colInfoIdx).likeSrchFlg, (String) colInfo.get("LIKE_SRCH_FLG"));
            setValue(sMsg.C.no(colInfoIdx).itemSortNum, (BigDecimal) colInfo.get("ITEM_SORT_NUM"));
            setValue(sMsg.C.no(colInfoIdx).sortTpCd, (String) colInfo.get("SORT_TP_CD"));
            setValue(sMsg.C.no(colInfoIdx).physRelnTblNm, (String) colInfo.get("PHYS_RELN_TBL_NM"));
            setValue(sMsg.C.no(colInfoIdx).physRelnColCd, (String) colInfo.get("PHYS_RELN_COL_CD"));
            setValue(sMsg.C.no(colInfoIdx).physRelnColNm, (String) colInfo.get("PHYS_RELN_COL_NM"));
            setValue(sMsg.C.no(colInfoIdx).physSrcColCd, (String) colInfo.get("PHYS_SRC_COL_CD"));
            setValue(sMsg.C.no(colInfoIdx).itemInacFlg, (String) colInfo.get("ITEM_INAC_FLG"));
            setValue(sMsg.C.no(colInfoIdx).mndFlg, (String) colInfo.get("MND_FLG"));
            setValue(sMsg.C.no(colInfoIdx).logicPopupColNm, (String) colInfo.get("LOGIC_POPUP_COL_NM"));
            setValue(sMsg.C.no(colInfoIdx).physPopupColNm, (String) colInfo.get("PHYS_POPUP_COL_NM"));
            setValue(sMsg.C.no(colInfoIdx).detailItemNm, dtlItemNm);

            // Pulldown
            dplyCtrlCd = sMsg.C.no(colInfoIdx).dplyCtrlCd.getValue();
            if (DPLY_CTRL_PULL_DOWN.equals(dplyCtrlCd)) {
                // mod start 2016/05/11 CSA Defect#7471
                String physRelnTblNm = (String) colInfo.get("PHYS_RELN_TBL_NM");
                String physRelnColCd = (String) colInfo.get("PHYS_RELN_COL_CD");
                String physRelnColNm = (String) colInfo.get("PHYS_RELN_COL_NM");
                String physMaintTrgtTblNm = (String) colInfo.get("PHYS_MAINT_TRGT_TBL_NM");
                String physMaintTrgtColNm = (String) colInfo.get("PHYS_MAINT_TRGT_COL_NM");

                // Get Pulldown Code/Name
                //List<Map<String, Object>> pullDownData = NSAL9900Query.getInstance().getPullDownData(glblCmpyCd, physRelnTblNm, physRelnColCd, physRelnColNm);
                List<Map<String, Object>> pullDownData = NSAL9900Query.getInstance().getPullDownData(glblCmpyCd, physRelnTblNm, physRelnColCd, physRelnColNm, physMaintTrgtTblNm, physMaintTrgtColNm);
                // mod end 2016/05/11 CSA Defect#7471

                for (int pullDownDataIdx = 0; pullDownDataIdx < pullDownData.size(); pullDownDataIdx++) {
                    sMsg.setValue(colNmXxLiscCd, pullDownDataIdx, (String) pullDownData.get(pullDownDataIdx).get(physRelnColCd));
                    sMsg.setValue(colNmXxLiscNm, pullDownDataIdx, (String) pullDownData.get(pullDownDataIdx).get(physRelnColNm));
                }
            }

            // Search Condition
            srchItemFlg = sMsg.C.no(colInfoIdx).srchItemFlg.getValue();
            if (FLG_ON_Y.equals(srchItemFlg)) {
                // Set Pulldown Code/Name to A
                if (DPLY_CTRL_PULL_DOWN.equals(dplyCtrlCd)) {
                    try {
                        Field listCode = sMsg.getClass().getField(colNmXxLiscCd);
                        EZDSStringItemArray listCodes = (EZDSStringItemArray) listCode.get(sMsg);
                        Field listName = sMsg.getClass().getField(colNmXxLiscNm);
                        EZDSStringItemArray listNames = (EZDSStringItemArray) listName.get(sMsg);

                        int validCnt = sMsg.A.getValidCount();
                        for (int i = 0; i < listCodes.length(); i++) {
                            sMsg.A.no(validCnt).xxListCd_A.no(i).setValue(listCodes.no(i).getValue());
                            sMsg.A.no(validCnt).xxListNm_A.no(i).setValue(listNames.no(i).getValue());
                        }

                    } catch (Exception ex) {
                        throw new S21AbendException(MSG_ID_NSAM0219E, new String[]{ex.getCause().toString()});
                    }
                }
                // Set Column Info Index
                setValue(sMsg.A.no(colInfoIdx).xxRowNum_A, getSearchRowNum(sMsg, colInfoIdx));

                // Set Search Item Name
                srchItemNm = xxColTp + SRCH_COND_SFX;
                setValue(sMsg.C.no(colInfoIdx).searchItemNm, srchItemNm);
            }

            setWidth(sMsg, colInfoIdx);
        }

        sMsg.C.setValidCount(colInfoIdx);
        EZDMsg.copy(sMsg, null, cMsg, null);
    }

    private static String convColTp(String colTp) {
        String rtrnVal = "";
        if (colTp.equals(COL_TP_STRING)) {
            rtrnVal = STR_XX_STRING;
        } else if (colTp.equals(COL_TP_NUMBER)) {
            rtrnVal = STR_XX_NUMBER;
        } else if (colTp.equals(COL_TP_DATE)) {
            rtrnVal = STR_XX_DATE;
        } else if (colTp.equals(COL_TP_YEARMONTH)) {
            rtrnVal = STR_XX_YEARMONTH;
        } else if (colTp.equals(COL_TP_YEAR)) {
            rtrnVal = STR_XX_YEAR;
        } else if (colTp.equals(COL_TP_TIME)) {
            rtrnVal = STR_XX_TIME;
        } else if (colTp.equals(COL_TP_TS)) {
            rtrnVal = STR_XX_TS;
        }
        return rtrnVal + STR_UNDER_BAR;
    }

    private static BigDecimal getSearchRowNum(NSAL9900SMsg sMsg, int idx) {
        int validCnt = sMsg.A.getValidCount();
        sMsg.A.no(validCnt).xxRowNum_A.setValue(idx);
        sMsg.A.setValidCount(validCnt + 1);
        return BigDecimal.valueOf(validCnt);
    }

    private static void setWidth(NSAL9900SMsg sMsg, int idx) {

        NSAL9900_CSMsg cSMsg = sMsg.C.no(idx);
        String colTp = cSMsg.colTpCd.getValue();
        String dplyCtrl = cSMsg.dplyCtrlCd.getValue();
        int width = 0;

        if (COL_TP_DATE.equals(colTp)) {
            width = cSMsg.itemDplyLg.getValueInt() * WIDTH_8 + CALENDAR_WIDTH;
            setValue(cSMsg.xxWidth, BigDecimal.valueOf(width));

        } else if (DPLY_CTRL_PULL_DOWN.equals(dplyCtrl)) {
            width = cSMsg.itemDplyLg.getValueInt() * WIDTH_8 + PULLDOWN_WIDTH;
            setValue(cSMsg.xxWidth, BigDecimal.valueOf(width));

        } else if (DPLY_CTRL_POP_UP.equals(dplyCtrl)) {
            width = cSMsg.itemDplyLg.getValueInt() * WIDTH_8 + POPUP_WIDTH;
            setValue(cSMsg.xxWidth, BigDecimal.valueOf(width));

        } else if (DPLY_CTRL_CHECK_BOX.equals(dplyCtrl)) {
            // START 2016/03/09 T.Aoyagi [QC#5213, MOD]
//            width = cSMsg.logicMaintTrgtColNm.getValue().length() * WIDTH_8;
            width = cSMsg.itemDplyLg.getValueInt() * WIDTH_8 + CHECKBOX_WIDTH;
            // END 2016/03/09 T.Aoyagi [QC#5213, MOD]
            setValue(cSMsg.xxWidth, BigDecimal.valueOf(width));

        } else {
            width = cSMsg.itemDplyLg.getValueInt() * WIDTH_8 + WIDTH_8;
            setValue(cSMsg.xxWidth, BigDecimal.valueOf(width));
        }
    }

    /**
     * Get Search Data
     * @param cMsg NSAL9900CMsg
     * @param sMsg NSAL9900SMsg
     */
    public static void getSearchData(NSAL9900CMsg cMsg, NSAL9900SMsg sMsg) {

        EZDMsg.copy(cMsg.A, null, sMsg.A, null);

        String glblCmpyCd = cMsg.glblCmpyCd.getValue();
        String searchInfo = getSearchInfo(cMsg);
        String tblNm = getTableNm(cMsg);
        String whereInfo = getWhereInfo(cMsg);
        String sortInfo = getSortInfo(cMsg);
        List<Map<String, Object>> searchResultList = NSAL9900Query.getInstance().getSearchInfo(glblCmpyCd, searchInfo, tblNm, whereInfo, sortInfo, Integer.toString(sMsg.B.length() + 1));

        if (searchResultList.isEmpty()) {
            cMsg.setMessageInfo(MSG_ID_NZZM0000E);
            return;
        }

        int idx = 0;
        for (idx = 0; idx < searchResultList.size(); idx++) {
            Map<String, Object> resultMap = searchResultList.get(idx);
            if (idx == sMsg.B.length()) {
            cMsg.setMessageInfo(MSG_ID_ZZZM9002W, new String[] {Integer.toString(sMsg.C.getValidCount()) });
                break;
            }

            for (int i = 0; i < sMsg.C.getValidCount(); i++) {

                String colNm = sMsg.C.no(i).detailItemNm.getValue();

                if (COL_TP_NUMBER.equals(sMsg.C.no(i).colTpCd.getValue())) {
                    setValueForBigDecimal(sMsg, idx, colNm, (BigDecimal) resultMap.get(colNm.toUpperCase()));
                } else {
                    setValueForString(sMsg, idx, colNm, (String) resultMap.get(colNm.toUpperCase()));
                }

                String rowNum = "";
                rowNum = STR_XX_ROW_NUM + STR_UNDER_BAR + leftPad(String.valueOf(i), PAD_NUM, STR_ZERO);
                setValueForBigDecimal(sMsg, idx, rowNum, BigDecimal.valueOf(i));
            }

            setValue(sMsg.B.no(idx).ezUpTime, (String) resultMap.get("EZUPTIME"));
            setValue(sMsg.B.no(idx).ezUpTimeZone, (String) resultMap.get("EZUPTIMEZONE"));
            // add start 2016/09/02 CSA Defect#10767
            setValue(sMsg.B.no(idx).xxRecHistCratTs_B, (String) resultMap.get("EZINTIME"));
            setValue(sMsg.B.no(idx).xxRecHistCratByNm_B, (String) resultMap.get("EZINUSERID"));
            setValue(sMsg.B.no(idx).xxRecHistUpdTs_B, (String) resultMap.get("EZUPTIME"));
            setValue(sMsg.B.no(idx).xxRecHistUpdByNm_B, (String) resultMap.get("EZUPUSERID"));
            setValue(sMsg.B.no(idx).xxRecHistTblNm_B, (String) resultMap.get("EZTABLEID"));
            // add end 2016/09/02 CSA Defect#10767
            setValue(sMsg.B.no(idx).updateFlg, FLG_OFF_N);
            setValue(sMsg.B.no(idx).xxChkBox, FLG_OFF_N);
        }
        sMsg.B.setValidCount(idx);
        EZDMsg.copy(sMsg, null, cMsg, null);
    }

    private static void setValueForString(NSAL9900SMsg sMsg, int idx, String colNm, String value) {
        if (hasValue(value)) {
            sMsg.B.no(idx).setValue(colNm, -1, value);
        }
    }

    private static void setValueForBigDecimal(NSAL9900SMsg sMsg, int idx, String colNm, BigDecimal value) {
        if (hasValue(value)) {
            sMsg.B.no(idx).setValue(colNm, -1, value);
        }
    }

    private static String getTableNm(NSAL9900CMsg cMsg) {
        return cMsg.tblCd.getValue();
    }

    private static String getSearchInfo(NSAL9900CMsg cMsg) {
        StringBuffer sb = new StringBuffer();
        sb.append("A.EZUPTIME, A.EZUPTIMEZONE");
        // add start 2016/09/02 CSA Defect#10767
        sb.append(", A.EZINTIME, A.EZINUSERID, A.EZUPTIME, A.EZUPUSERID, A.EZTABLEID");
        // add end 2016/09/02 CSA Defect#10767

        for (int i = 0; i < cMsg.C.getValidCount(); i++) {
            if (hasValueAll(cMsg.C.no(i).physRelnTblNm, cMsg.C.no(i).physRelnColCd, cMsg.C.no(i).physRelnColNm, cMsg.C.no(i).physSrcColCd)
                    && !DPLY_CTRL_PULL_DOWN.equals(cMsg.C.no(i).dplyCtrlCd.getValue())
                    && !DPLY_CTRL_POP_UP.equals(cMsg.C.no(i).dplyCtrlCd.getValue())) {
                String sql = GET_RELN_COL_NM_SQL;
                sql = sql.replace("$physRelnColNm$", cMsg.C.no(i).physRelnColNm.getValue());
                sql = sql.replace("$physRelnTblNm$", cMsg.C.no(i).physRelnTblNm.getValue());
                sql = sql.replace("$glblCmpyCd$", cMsg.glblCmpyCd.getValue());
                sql = sql.replace("$physRelnColCd$", cMsg.C.no(i).physRelnColCd.getValue());
                sql = sql.replace("$physMaintTrgtColNm$", cMsg.C.no(i).physSrcColCd.getValue());
                sql = sql.replace("$aliasColNm$", cMsg.C.no(i).detailItemNm.getValue());
                sb.append(COMMA_DEV);
                sb.append(sql);
            } else {
                sb.append(COMMA_DEV);
                sb.append((String) TBL_ALIAS + PERIOD_DEV + cMsg.C.no(i).physMaintTrgtColNm.getValue() + " AS " + cMsg.C.no(i).detailItemNm.getValue());
            }
        }
        return sb.toString();
    }

    private static boolean hasValueAll(EZDCItem... items) {
        for (EZDCItem item : items) {
            if (!hasValue(item)) {
                return false;
            }
        }
        return true;
    }

    private static String getSearchKey(NSAL9900_ACMsg acMsg, String colTpCd) {
        String rtnValue = "";
        if (COL_TP_STRING.equals(colTpCd)) {
            if (hasValue(acMsg.xxString_A)) {
                rtnValue = acMsg.xxString_A.getValue();
            }
        } else if (COL_TP_NUMBER.equals(colTpCd)) {
            if (hasValue(acMsg.xxNumber_A)) {
                rtnValue = acMsg.xxNumber_A.getValue().toString();
            }
        } else if (COL_TP_DATE.equals(colTpCd)) {
            if (hasValue(acMsg.xxDate_A)) {
                rtnValue = acMsg.xxDate_A.getValue();
            }
        } else if (COL_TP_TIME.equals(colTpCd)) {
            if (hasValue(acMsg.xxTime_A)) {
                rtnValue = acMsg.xxTime_A.getValue();
            }
        } else if (COL_TP_TS.equals(colTpCd)) {
            if (hasValue(acMsg.xxTs_A)) {
                rtnValue = acMsg.xxTs_A.getValue();
            }
        } else if (COL_TP_YEAR.equals(colTpCd)) {
            if (hasValue(acMsg.xxYear_A)) {
                rtnValue = acMsg.xxYear_A.getValue();
            }
        } else if (COL_TP_YEARMONTH.equals(colTpCd)) {
            if (hasValue(acMsg.xxYearmonth_A)) {
                rtnValue = acMsg.xxYearmonth_A.getValue();
            }
        }
        return rtnValue;
    }

    private static String getSortInfo(NSAL9900CMsg cMsg) {
        ArrayList<NSAL9900_CCMsg> list = new ArrayList<NSAL9900_CCMsg>();
        for (int i = 0; i < cMsg.C.getValidCount(); i++) {
            // START 2016/03/10 T.Aoyagi [QC#5258, MOD]
            if (hasValue(cMsg.C.no(i).itemSortNum)) {
                list.add(cMsg.C.no(i));
            }
            // END 2016/03/10 T.Aoyagi [QC#5258, MOD]
        }
        Collections.sort(list, new Comparator<NSAL9900_CCMsg>() {
            public int compare(NSAL9900_CCMsg ccMsg1, NSAL9900_CCMsg ccMsg2) {
                if (hasValue(ccMsg1.itemSortNum)
                        && hasValue(ccMsg2.itemSortNum)) {
                    return ccMsg1.itemSortNum.getValueInt() - ccMsg2.itemSortNum.getValueInt();
                }
                return 0;
            }
        });

        StringBuffer sb = new StringBuffer();
        for (NSAL9900_CCMsg ccMsg : list) {

            if (hasValue(ccMsg.physSrcColCd)) {
                continue;
            }

            if (hasValue(ccMsg.itemSortNum)) {
                if (sb.length() != 0) {
                    sb.append(COMMA_DEV);
                }
                sb.append(TBL_ALIAS + PERIOD_DEV  + ccMsg.physMaintTrgtColNm.getValue());
                sb.append(SPACE_DEV);
                sb.append(ccMsg.sortTpCd.getValue());
            }
        }
        return sb.toString();
    }

    private static String getWhereInfo(NSAL9900CMsg cMsg) {
        int colInfoIdx = 0;
        String searchKey = "";
        String likeSrchFlg = "";
        String strColomnNm = "";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            colInfoIdx = cMsg.A.no(i).xxRowNum_A.getValueInt();

            if (hasValue(cMsg.C.no(colInfoIdx).physSrcColCd.getValue())) {
                continue;
            }

            if (FLG_ON_Y.equals(cMsg.C.no(colInfoIdx).srchItemFlg.getValue())) {
                likeSrchFlg = cMsg.C.no(colInfoIdx).likeSrchFlg.getValue();
                strColomnNm = cMsg.C.no(colInfoIdx).physMaintTrgtColNm.getValue();
                searchKey = getSearchKey(cMsg.A.no(i), cMsg.C.no(colInfoIdx).colTpCd.getValue());
                if (hasValue(searchKey)) {
                    sb.append(WHERE_AND);
                    sb.append(TBL_ALIAS + PERIOD_DEV + strColomnNm);
                    if (FLG_ON_Y.equals(likeSrchFlg)) {
                        sb.append(WHERE_LIKE);
                        sb.append(SINGLE_QUOTE_DEV);
                        sb.append(searchKey);
                        sb.append(SINGLE_QUOTE_DEV);
                    } else {
                        sb.append(WHERE_EQUAL);
                        sb.append(SINGLE_QUOTE_DEV);
                        sb.append(searchKey);
                        sb.append(SINGLE_QUOTE_DEV);
                    }
                }
            }
        }
        // QC#58926
        String condSqlTxt = NSAL9900Query.getInstance().getCondSqlTxt(cMsg.glblCmpyCd.getValue(), cMsg.tblCd.getValue());
        if (ZYPCommonFunc.hasValue(condSqlTxt)) {
            if(!condSqlTxt.trim().startsWith(WHERE_AND.trim())) {
                sb.append(WHERE_AND);
            }
            sb.append(condSqlTxt);
        }
        return sb.toString();
    }

    /**
     * Copy Search Data CMsg to SMsg
     * @param cMsg NSAL9900CMsg
     * @param sMsg NSAL9900SMsg
     */
    public static void copyCMsgToSMsg(NSAL9900CMsg cMsg, NSAL9900SMsg sMsg) {

        String updateFlg;

        int curCnt = cMsg.xxPageShowFromNum.getValueInt() - 1;
        int cMsgCnt = 0;
        for (int i = curCnt; i < (curCnt + cMsg.B.getValidCount()); i++) {

            updateFlg = sMsg.B.no(i).updateFlg.getValue();
            if (!FLG_ON_Y.equals(updateFlg) && isUpdate(sMsg.C, cMsg.B.no(cMsgCnt), sMsg.B.no(i))) {
                setValue(cMsg.B.no(cMsgCnt).updateFlg, FLG_ON_Y);
            }

            EZDMsg.copy(cMsg.B.no(cMsgCnt), null, sMsg.B.no(i), null);
            cMsgCnt++;
        }
    }

    /**
     * copyFromSMsgOntoCmsg
     * @param cMsg NSAL9900CMsg
     * @param sMsg NSAL9900SMsg
     */
    public static void copySMsgToCMsg(NSAL9900CMsg cMsg, NSAL9900SMsg sMsg) {
        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.B.length(); i++) {
            if (i < sMsg.B.getValidCount()) {
                EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.B.setValidCount(i - pagenationFrom);
        setPageNum(cMsg, (pagenationFrom + 1), (pagenationFrom + cMsg.B.getValidCount()), sMsg.B.getValidCount());
    }

    private static boolean isUpdate(NSAL9900_CSMsgArray colInfoList, NSAL9900_BCMsg bCMsg, NSAL9900_BSMsg bSMsg) {
        BigDecimal decimal1;
        BigDecimal decimal2;
        String str1;
        String str2;
        for (int i = 0; i < colInfoList.getValidCount(); i++) {
            if (COL_TP_NUMBER.equals(colInfoList.no(i).colTpCd.getValue())) {
                decimal1 = bCMsg.getValueBigDecimal(colInfoList.no(i).detailItemNm.getValue(), -1);
                decimal2 = bSMsg.getValueBigDecimal(colInfoList.no(i).detailItemNm.getValue(), -1);
                if (isDiff(decimal1, decimal2)) {
                    return true;
                }
            } else {
                str1 = bCMsg.getValueString(colInfoList.no(i).detailItemNm.getValue(), -1);
                str2 = bSMsg.getValueString(colInfoList.no(i).detailItemNm.getValue(), -1);
                if (DPLY_CTRL_CHECK_BOX.equals(colInfoList.no(i).dplyCtrlCd.getValue()) && !hasValue(str1)) {
                    bCMsg.setValue(colInfoList.no(i).detailItemNm.getValue(), -1, FLG_OFF_N);
                    str1 = FLG_OFF_N;
                }

                if (isDiff(str1, str2)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isDiff(BigDecimal num1, BigDecimal num2) {
        if (!hasValue(num1) && !hasValue(num2)) {
            return false;
        }

        if (!hasValue(num1) || !hasValue(num2)) {
            return true;
        }

        if (num1.compareTo(num2) == 0) {
            return false;
        }
        return true;
    }

    private static boolean isDiff(String str1, String str2) {
        if (!hasValue(str1) && !hasValue(str2)) {
            return false;
        }

        if (!hasValue(str1) || !hasValue(str2)) {
            return true;
        }

        if (str1.equals(str2)) {
            return false;
        }
        return true;
    }

    /**
     * 
     * setPageNum
     * 
     * @param cMsg NSAL9900CMsg
     * @param fromCnt PageShowFromNum
     * @param toCnt PageShowToNum
     * @param allCnt PageShowOfNum
     */
    public static void setPageNum(NSAL9900CMsg cMsg, int fromCnt, int toCnt, int allCnt) {
        cMsg.xxPageShowFromNum.setValue(fromCnt);
        cMsg.xxPageShowToNum.setValue(toCnt);
        cMsg.xxPageShowOfNum.setValue(allCnt);
    }

    /**
     * getErrPageFromNum
     * @param cMsg NSAL9900CMsg
     * @param sMsg NSAL9900SMsg
     * @return int
     */
    public static int getErrPageFromNum(NSAL9900CMsg cMsg, NSAL9900SMsg sMsg) {

        int errIndex = 0;
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            NSAL9900_BSMsg bSMsg = sMsg.B.no(i);

            // START 2016/06/15 T.Aoyagi [QC#9685, ADD]
            if (bSMsg.xxChkBox.isError()) {
                errIndex = i;
                break;
            }
            // END 2016/06/15 T.Aoyagi [QC#9685, ADD]

            for (int j = 0; j < sMsg.C.getValidCount(); j++) {
                NSAL9900_CSMsg cSMsg = sMsg.C.no(j);
                String detailItemNm = cSMsg.detailItemNm.getValue();

                try {
                    Field f = NSAL9900_BSMsg.class.getField(detailItemNm);
                    if (((EZDSItem) f.get(bSMsg)).isError()) {
                        errIndex = i;
                        break;
                    }
                } catch (Exception ex) {
                    throw new S21AbendException(MSG_ID_NSAM0219E, new String[]{ex.getCause().toString()});
                }
            }
        }

        return errIndex / cMsg.B.length() * cMsg.B.length();
    }

    /**
     * Set default row value
     * @param sMsg NSAL9900SMsg
     * @param addIdx int
     */
    public static void setDefaultRowValue(NSAL9900SMsg sMsg, int addIdx) {

        for (int i = 0; i < sMsg.C.getValidCount(); i++) {

            String colTp = sMsg.C.no(i).colTpCd.getValue();
            String colNm = sMsg.C.no(i).detailItemNm.getValue();
            String defVal = sMsg.C.no(i).defValTxt.getValue();

            if (hasValue(defVal)) {
                if (COL_TP_NUMBER.equals(colTp)) {
                    BigDecimal bdDefVal = new BigDecimal(defVal);
                    setValueForBigDecimal(sMsg, addIdx, colNm, bdDefVal);
                } else {
                    setValueForString(sMsg, addIdx, colNm, defVal);
                }
            }
        }
    }

    /**
     * Delete SMsg
     * @param cMsg NSAL9900CMsg
     * @param sMsg NSAL9900SMsg
     */
    public static void deleteRowSMsg(NSAL9900CMsg cMsg, NSAL9900SMsg sMsg) {

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.B, "xxChkBox", ZYPConstant.CHKBOX_ON_Y);
        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo(MSG_ID_NZZM0011E);
            return;
        }

        try {
            EZDItemAttribute[] item = new EZDItemAttribute[sMsg.C.getValidCount()];
            String tblClsNm = TMSG_PACKAGE + sMsg.tblCd.getValue() + TMSG_SFX;

            @SuppressWarnings("unchecked")
            Class<EZDTMsg> tblClass = (Class<EZDTMsg>) Class.forName(tblClsNm);
            EZDTMsg tMsg = (EZDTMsg) tblClass.newInstance();

            int delCnt;
            String strAttrCd;
            int pkCnt;
            String colKeySfx;
            String delKeySfx;
            List<Integer> delRows = new ArrayList<Integer>();
            for (int i = 0; i < sMsg.B.getValidCount(); i++) {
                if (!FLG_ON_Y.equals(sMsg.B.no(i).xxChkBox.getValue())) {
                    continue;
                }

                if (!hasValue(sMsg.B.no(i).ezUpTime)) {
                    // New line
                    delRows.add(i);
                    continue;
                }

                delCnt = sMsg.D.getValidCount();
                pkCnt = 0;
                for (int colCnt = 0; colCnt < sMsg.C.getValidCount(); colCnt++) {
                    strAttrCd = getAttrCd(sMsg.C.no(colCnt).physMaintTrgtColNm.getValue());
                    item[colCnt] = tMsg.getAttr(strAttrCd);
                    // START 2016/07/12 M.Ugaki [For Suffix, ADD]
                    if (item[colCnt] == null) {
                        strAttrCd = getAttrCdWithSuffix(sMsg.C.no(colCnt).physMaintTrgtColNm.getValue());
                        item[colCnt] = tMsg.getAttr(strAttrCd);
                    }
                    // END 2016/07/12 M.Ugaki [For Suffix, ADD]

                    if (item[colCnt] != null && item[colCnt].isDBPrimaryKey()) {
                        colKeySfx = leftPad(String.valueOf(colCnt), PAD_NUM, STR_ZERO);
                        delKeySfx = leftPad(String.valueOf(pkCnt), PAD_NUM, STR_ZERO);
                        // Set PK value
                        setDeleteData(sMsg.D.no(delCnt), delKeySfx, sMsg.B.no(i), colKeySfx);
                        pkCnt++;
                    }
                }
                sMsg.D.setValidCount(delCnt + 1);
                delRows.add(i);
            }

            if (delRows.size() > 0) {
                // delete line from SMsg
                ZYPTableUtil.deleteRows(sMsg.B, delRows);
            }
        } catch (Exception ex) {
            throw new S21AbendException(MSG_ID_NSAM0219E, new String[]{ex.getCause().toString()});
        }
    }

    private static void setDeleteData(NSAL9900_DSMsg delMsg, String delKeySfx, NSAL9900_BSMsg colMsg, String colKeySfx) throws EZDValidatorException {

        String strObj = colMsg.getValueString(STR_XX_STRING + STR_UNDER_BAR + colKeySfx, -1);
        if (hasValue(strObj)) {
            delMsg.setValueString(STR_XX_STRING_PK + STR_UNDER_BAR + delKeySfx, -1, strObj);
        }

        BigDecimal decmalObj = colMsg.getValueBigDecimal(STR_XX_NUMBER + STR_UNDER_BAR + colKeySfx, -1);
        if (hasValue(decmalObj)) {
            delMsg.setValueBigDecimal(STR_XX_NUMBER_PK + STR_UNDER_BAR + delKeySfx, -1, decmalObj);
        }

        strObj = colMsg.getValueString(STR_XX_DATE + STR_UNDER_BAR + colKeySfx, -1);
        if (hasValue(strObj)) {
            delMsg.setValueString(STR_XX_DATE_PK + STR_UNDER_BAR + delKeySfx, -1, strObj);
        }

        strObj = colMsg.getValueString(STR_XX_YEARMONTH + STR_UNDER_BAR + colKeySfx, -1);
        if (hasValue(strObj)) {
            delMsg.setValueString(STR_XX_YEARMONTH_PK + STR_UNDER_BAR + delKeySfx, -1, strObj);
        }

        strObj = colMsg.getValueString(STR_XX_YEAR + STR_UNDER_BAR + colKeySfx, -1);
        if (hasValue(strObj)) {
            delMsg.setValueString(STR_XX_YEAR_PK + STR_UNDER_BAR + delKeySfx, -1, strObj);
        }

        strObj = colMsg.getValueString(STR_XX_TIME + STR_UNDER_BAR + colKeySfx, -1);
        if (hasValue(strObj)) {
            delMsg.setValueString(STR_XX_TIME_PK + STR_UNDER_BAR + delKeySfx, -1, strObj);
        }

        strObj = colMsg.getValueString(STR_XX_TS + STR_UNDER_BAR + colKeySfx, -1);
        if (hasValue(strObj)) {
            delMsg.setValueString(STR_XX_TS_PK + STR_UNDER_BAR + delKeySfx, -1, strObj);
        }

        decmalObj = colMsg.getValueBigDecimal(STR_XX_ROW_NUM + STR_UNDER_BAR + colKeySfx, -1);
        if (hasValue(decmalObj)) {
            delMsg.setValueBigDecimal(STR_XX_ROW_NUM_PK + STR_UNDER_BAR + delKeySfx, -1, decmalObj);
        }
    }

    /**
     * Get Attribute Code
     * @param strPhysicalAttr String
     * @param suffixMode boolean
     * @return String
     */
    // START 2016/07/12 M.Ugaki [For Suffix, UPD]
    // public static String getAttrCd(String strPhysicalAttr) {
    private static String getAttrCd(String strPhysicalAttr, boolean suffixMode) {
    // END 2016/07/12 M.Ugaki [For Suffix, UPD]

            StringBuffer strAttrCd = new StringBuffer();
        String[] strAttrList = strPhysicalAttr.split(STR_UNDER_BAR);

        strAttrCd.append(strAttrList[0].toLowerCase());
        for (int i = 1; i < strAttrList.length; i++) {

            // START 2016/07/12 M.Ugaki [QC#11674, ADD]
            if (suffixMode && (i == strAttrList.length - 1)) {
                strAttrCd.append(STR_UNDER_BAR);
            }
            // END 2016/07/12 M.Ugaki [QC#11674, ADD]

            if (strAttrList[i].length() == 1) {
                strAttrCd.append(strAttrList[i].toLowerCase());
            } else {
                strAttrCd.append(strAttrList[i].substring(0, 1).toUpperCase() + strAttrList[i].substring(1).toLowerCase());
            }
        }
        return strAttrCd.toString();
    }

    // START 2016/07/12 M.Ugaki [QC#11674, ADD]
    // For w/o Suffix
    private static String getAttrCd(String strPhysicalAttr) {
        return getAttrCd(strPhysicalAttr, false);
    }

    // For with Suffix
    private static String getAttrCdWithSuffix(String strPhysicalAttr) {
        return getAttrCd(strPhysicalAttr, true);
    }
    // END 2016/07/12 M.Ugaki [QC#11674, ADD]

    /**
     * Common Validation
     * @param cMsg NSAL9900CMsg
     * @param sMsg NSAL9900SMsg
     * @return boolean
     */
    public static boolean commonValidation(NSAL9900CMsg cMsg, NSAL9900SMsg sMsg) {

        boolean result = true;
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            NSAL9900_BSMsg bSMsg = sMsg.B.no(i);

            for (int j = 0; j < sMsg.C.getValidCount(); j++) {
                NSAL9900_CSMsg cSMsg = sMsg.C.no(j);

                // inactive Check
                if (!isValidationCheck(cSMsg)) {
                    continue;
                }

                String detailItemNm = cSMsg.detailItemNm.getValue();
                String colTpCd = cSMsg.colTpCd.getValue();

                if (!hasValue(detailItemNm) || !hasValue(colTpCd)) {
                    continue;
                }

                try {
                    // Get validation target value
                    Field f = NSAL9900_BSMsg.class.getField(detailItemNm);
                    if (COL_TP_STRING.equals(colTpCd)) {
                        String targetValue = (String) bSMsg.getValueString(detailItemNm, -1);

                        // Relation check
                        if (!relationCheck(targetValue, cSMsg, sMsg)) {
                            ((EZDSItem) f.get(bSMsg)).setErrorInfo(1, MSG_ID_ZZZM9006E, new String[] {cSMsg.physRelnTblNm.getValue() });
                            result = false;
                            continue;
                        }
                    } else if (COL_TP_NUMBER.equals(colTpCd)) {
                        // Validation(NUMBER)
                        BigDecimal targetValue = (BigDecimal) bSMsg.getValueBigDecimal(detailItemNm, -1);

                        if (!hasValue(targetValue)) {
                            continue;
                        }

                        if (!checkNumber(targetValue, cSMsg, bSMsg, f)) {
                            result = false;
                            continue;
                        }

                        // Relation check
                        if (!relationCheck(targetValue.toString(), cSMsg, sMsg)) {
                            ((EZDSItem) f.get(bSMsg)).setErrorInfo(1, MSG_ID_ZZZM9006E, new String[] {cSMsg.physRelnTblNm.getValue() });
                            result = false;
                            continue;
                        }
                    } else if (COL_TP_DATE.equals(colTpCd)) {
                        continue;
                    } else if (COL_TP_YEARMONTH.equals(colTpCd)) {
                        continue;
                    } else if (COL_TP_YEAR.equals(colTpCd)) {
                        continue;
                    } else if (COL_TP_TIME.equals(colTpCd)) {
                        // Validation(Time)
                        String targetValue = (String) bSMsg.getValueString(detailItemNm, -1);
                        if (!checkDate(targetValue, colTpCd)) {
                            ((EZDSItem) f.get(bSMsg)).setErrorInfo(1, MSG_ID_NSAM0206E, new String[]{"Time"});
                            result = false;
                            continue;
                        }
                    } else if (COL_TP_TS.equals(colTpCd)) {
                        String targetValue = (String) bSMsg.getValueString(detailItemNm, -1);
                        if (!checkDate(targetValue, colTpCd)) {
                            ((EZDSItem) f.get(bSMsg)).setErrorInfo(1, MSG_ID_NSAM0206E, new String[]{"Date"});
                            result = false;
                            continue;
                        }
                    }
                } catch (Exception ex) {
                    throw new S21AbendException(MSG_ID_NSAM0219E, new String[]{ex.getCause().toString()});
                }
            }
        }
        return result;
    }

    private static boolean checkString(String targetValue, NSAL9900_CSMsg cSMsg, NSAL9900_BSMsg bSMsg, Field f) {
        // digit Check
        if (!isDigit(targetValue, cSMsg)) {
            try {
                ((EZDSItem) f.get(bSMsg)).setErrorInfo(1, MSG_ID_ZZM9001E, new String[] {cSMsg.logicMaintTrgtColNm.getValue() });
            } catch (Exception ex) {
                throw new S21AbendException(MSG_ID_NSAM0219E, new String[]{ex.getCause().toString()});
            }
            return false;
        }
        return true;
    }

    private static boolean checkNumber(BigDecimal targetValue, NSAL9900_CSMsg cSMsg, NSAL9900_BSMsg bSMsg, Field f) {
        // digit check
        if (!isDigitNumber(targetValue, cSMsg)) {
            try {
                //String format = getNumberFormat(cSMsg);
                ((EZDSItem) f.get(bSMsg)).setErrorInfo(1, MSG_ID_ZZM9001E, new String[] {cSMsg.logicMaintTrgtColNm.getValue() });
            } catch (Exception ex) {
                throw new S21AbendException(MSG_ID_NSAM0219E, new String[]{ex.getCause().toString()});
            }
            return false;
        }
        return true;
    }

    /**
     * Check Date
     * @param targetValue String
     * @param colTpCd colTpCd
     * @return boolean
     */
    public static boolean checkDate(String targetValue, String colTpCd) {
        String year = null;
        String month = null;
        String day = null;
        String time = null;
        String regexYYYY = "([0-9]{4})";
        String regexYYYYMM = "([0-9]{4})(0[1-9]|1[0-2])";
        String regexHHMMSS = "([0-1][0-9]|[2][0-3])([0-5][0-9])([0-5][0-9])";
        String regexHHMMSSSSS = "([0-1][0-9]|[2][0-3])([0-5][0-9])([0-5][0-9])([0-9]{3})";

        if (!hasValue(targetValue)) {
            return true;
        }
        if (!ZYPCommonFunc.isNumberCheck(targetValue)) {
            return false;
        }
        int targetValueLength = targetValue.length();

        if (COL_TP_DATE.equals(colTpCd)) {
            if (targetValueLength != IDX_8) {
                return false;
            }
            year = targetValue.substring(0, IDX_4);
            month = targetValue.substring(IDX_4, IDX_6);
            day = targetValue.substring(IDX_6, IDX_8);
            if (!EZDCommonFunc.checkDate(year, month, day)) {
                return false;
            }

        } else if (COL_TP_YEARMONTH.equals(colTpCd)) {
            if (targetValueLength != IDX_6) {
                return false;
            }
            Pattern p = Pattern.compile(regexYYYYMM);
            Matcher m = p.matcher(targetValue);
            if (!m.find()) {
                return false;
            }

        } else if (COL_TP_YEAR.equals(colTpCd)) {
            if (targetValueLength != IDX_4) {
                return false;
            }
            Pattern p = Pattern.compile(regexYYYY);
            Matcher m = p.matcher(targetValue);
            if (!m.find()) {
                return false;
            }

        } else if (COL_TP_TIME.equals(colTpCd)) {
            if (targetValueLength != IDX_6) {
                return false;
            }
            Pattern p = Pattern.compile(regexHHMMSS);
            Matcher m = p.matcher(targetValue);
            if (!m.find()) {
                return false;
            }

        } else if (COL_TP_TS.equals(colTpCd)) {
            if (targetValueLength != IDX_17) {
                return false;
            }
            year = targetValue.substring(0, IDX_4);
            month = targetValue.substring(IDX_4, IDX_6);
            day = targetValue.substring(IDX_6, IDX_8);
            if (!EZDCommonFunc.checkDate(year, month, day)) {
                return false;
            }
            time = targetValue.substring(IDX_8, IDX_17);
            Pattern p = Pattern.compile(regexHHMMSSSSS);
            Matcher m = p.matcher(time);
            if (!m.find()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check Validation Execute.
     * @param cSMsg NSAL9900_CCMsg
     * @return boolean true: validation execute. false: no validation check.
     */
    private static boolean isValidationCheck(NSAL9900_CSMsg cSMsg) {
        if (FLG_ON_Y.equals(cSMsg.itemInacFlg.getValue())) {
            return false;
        }
        if (!hasValue(cSMsg.dplyCtrlCd) || !hasValue(cSMsg.dplySortNum)) {
            return false;
        }
        return true;
    }

    /**
     * Mandatory check.
     * @param targetValue String
     * @param cSMsg NSAL9900_CCMsg
     * @return boolean true: Mandatory check OK false: Mandatory check NG.
     */
    private static boolean isMondatoryCheck(String targetValue, NSAL9900_CSMsg cSMsg) {
        if (FLG_ON_Y.equals(cSMsg.mndFlg.getValue())) {
            if (!hasValue(targetValue)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Digit check.
     * @param targetValue String
     * @param cSMsg NSAL9900_CCMsg
     * @return boolean true: Digit check OK false: Digit check NG.
     */
    private static boolean isDigit(String targetValue, NSAL9900_CSMsg cSMsg) {
        if (!hasValue(targetValue) || cSMsg == null) {
            return true;
        }
        // digit Check
        int maxLength = cSMsg.itemMaxLg.getValueInt();
        if (maxLength < targetValue.length()) {
            return false;
        }
        return true;
    }

    /**
     * Digit check with scale.
     * @param targetValue BigDecimal
     * @param cSMsg NSAL9900_CCMsg
     * @return boolean true: Digit check OK false: Digit check NG.
     */
    private static boolean isDigitNumber(BigDecimal targetValue, NSAL9900_CSMsg cSMsg) {
        if (!hasValue(targetValue) || cSMsg == null) {
            return true;
        }
        // digit Check
        BigDecimal defScaleNumber = getSclNum(cSMsg);
        BigDecimal defPrecisionNumber = cSMsg.itemMaxLg.getValue().subtract(defScaleNumber);

        BigDecimal targetdefScaleNumber = new BigDecimal(targetValue.scale());
        BigDecimal targetPrecisionNumber = new BigDecimal(targetValue.precision() - targetValue.scale());
        if (defPrecisionNumber.compareTo(targetPrecisionNumber) < 0) {
            return false;
        }
        if (defScaleNumber.compareTo(targetdefScaleNumber) < 0) {
            return false;
        }
        return true;
    }

    /**
     * Relation check.
     * @param targetValue BigDecimal
     * @param cSMsg NSAL9900_CCMsg
     * @param sMsg NSAL9900CMsg
     * @return boolean true: Relation check OK. false: Relation check NG.
     */
    private static boolean relationCheck(String targetValue, NSAL9900_CSMsg cSMsg, NSAL9900SMsg sMsg) {
        if (!hasValue(cSMsg.physRelnTblNm) || !hasValue(cSMsg.physRelnColCd) || !hasValue(targetValue)) {
            return true;
        }

        String tblClsNm = TMSG_PACKAGE + sMsg.tblCd.getValue() + TMSG_SFX;
        try {
            @SuppressWarnings("unchecked")
            Class<EZDTMsg> tblClass = (Class<EZDTMsg>) Class.forName(tblClsNm);
            EZDTMsg tMsg = (EZDTMsg) tblClass.newInstance();
            String strAttrCd = getAttrCd(cSMsg.physMaintTrgtColNm.getValue());
            if (tMsg.getAttr(strAttrCd) == null) {
                // START 2016/07/12 M.Ugaki [QC#11674, ADD]
                strAttrCd = getAttrCdWithSuffix(cSMsg.physMaintTrgtColNm.getValue());
                if (tMsg.getAttr(strAttrCd) == null) {
                    return true;
                }
                // END 2016/07/12 M.Ugaki [QC#11674, ADD]

                // START 2016/07/12 M.Ugaki [QC#11674, DEL]
                // return true;
                // END 2016/07/12 M.Ugaki [QC#11674, DEL]
            }
        } catch (Exception ex) {
            throw new S21AbendException(MSG_ID_NSAM0219E, new String[]{ex.getCause().toString()});
        }
        String glblCmpyCd = sMsg.glblCmpyCd.getValue();

        // START 2017/07/12 M.Kidokoro [QC#18659,19534, ADD]
        List<Map<String, Object>> relationInfoList = null;
        String colTpCd = cSMsg.colTpCd.getValue();

        if (colTpCd.equals(COL_TP_NUMBER)) {
            relationInfoList = NSAL9900Query.getInstance().getRelationInfoForNumber(glblCmpyCd, targetValue,cSMsg.physRelnTblNm.getValue(), cSMsg.physRelnColCd.getValue());
        } else {
        // END 2017/07/12 M.Kidokoro [QC#18659,19534, ADD]

        // mod start 2016/05/11 CSA Defect#7471
        //List<Map<String, Object>> relationInfoList = NSAL9900Query.getInstance().getRelationInfo(glblCmpyCd, targetValue, cSMsg.physRelnTblNm.getValue(), cSMsg.physRelnColCd.getValue());
        // START 2017/07/12 M.Kidokoro [QC#18659,19534, MOD]
//        List<Map<String, Object>> relationInfoList = NSAL9900Query.getInstance().getRelationInfo(glblCmpyCd, targetValue,
//                cSMsg.physMaintTrgtTblNm.getValue(), cSMsg.physMaintTrgtColNm.getValue(), cSMsg.physRelnTblNm.getValue(), cSMsg.physRelnColCd.getValue());
          relationInfoList = NSAL9900Query.getInstance().getRelationInfo(glblCmpyCd, targetValue,
                  cSMsg.physMaintTrgtTblNm.getValue(), cSMsg.physMaintTrgtColNm.getValue(), cSMsg.physRelnTblNm.getValue(), cSMsg.physRelnColCd.getValue());
        // END 2017/07/12 M.Kidokoro [QC#18659,19534, MOD]
        // mod end 2016/05/11 CSA Defect#7471

        // START 2017/07/12 M.Kidokoro [QC#18659,19534, ADD]
        }
        // END 2017/07/12 M.Kidokoro [QC#18659,19534, ADD]
        return !relationInfoList.isEmpty();
    }

    private static BigDecimal getSclNum(NSAL9900_CSMsg cSMsg) {

        if (!hasValue(cSMsg.itemSclNum)) {
            return BigDecimal.ZERO;
        }
        return cSMsg.itemSclNum.getValue();
    }

    // START 2016/06/15 T.Aoyagi [QC#9685, ADD]
    /**
     * @param cMsg NSAL9900CMsg
     * @param sMsg NSAL9900SMsg
     * @param bIdx int
     * @return boolean
     */
    public static boolean isDuplicate(NSAL9900CMsg cMsg, NSAL9900SMsg sMsg, int bIdx) {

        StringBuilder sbWhere = new StringBuilder();
        for (int colInfoIdx = 0; colInfoIdx < sMsg.C.getValidCount(); colInfoIdx++) {

            if (!FLG_ON_Y.equals(sMsg.C.no(colInfoIdx).uniqKeyFlg.getValue())) {
                continue;
            }
            String physColNm = sMsg.C.no(colInfoIdx).physMaintTrgtColNm.getValue();
            String detailItemNm = sMsg.C.no(colInfoIdx).detailItemNm.getValue();
            sbWhere.append(WHERE_AND);
            sbWhere.append(TBL_ALIAS);
            sbWhere.append(PERIOD_DEV);
            sbWhere.append(physColNm);

            if (COL_TP_NUMBER.equals(sMsg.C.no(colInfoIdx).colTpCd.getValue())) {
                BigDecimal inputNum = sMsg.B.no(bIdx).getValueBigDecimal(detailItemNm, -1);
                if (hasValue(inputNum)) {
                    sbWhere.append(WHERE_EQUAL);
                    sbWhere.append(inputNum.toString());
                } else {
                    sbWhere.append(WHERE_IS_NULL);
                }
            } else {
                String inputStr = sMsg.B.no(bIdx).getValueString(detailItemNm, -1);
                if (hasValue(inputStr)) {
                    sbWhere.append(WHERE_EQUAL);
                    sbWhere.append(SINGLE_QUOTE_DEV);
                    sbWhere.append(inputStr);
                    sbWhere.append(SINGLE_QUOTE_DEV);
                } else {
                    sbWhere.append(WHERE_IS_NULL);
                }
            }
        }

        if (hasValue(sbWhere.toString())) {
            String glblCmpyCd = sMsg.glblCmpyCd.getValue();
            String tblNm = getTableNm(cMsg);
            List<BigDecimal> resultList = (List<BigDecimal>) NSAL9900Query.getInstance().getRecordByUniqKey(glblCmpyCd, tblNm, sbWhere.toString());
            if (!resultList.isEmpty()) {
                return true;
            }
        }
        return false;
    }
    // END 2016/06/15 T.Aoyagi [QC#9685, ADD]

//    private static String getNumberFormat(NSAL9900_CSMsg cSMsg) {
//        BigDecimal defScaleNumber = cSMsg.itemSclNum.getValue();
//        BigDecimal defPrecisionNumber = cSMsg.itemMaxLg.getValue().subtract(defScaleNumber);
//        int scaleNum = defScaleNumber.intValue();
//        int precisionNumber = defPrecisionNumber.intValue();
//        StringBuffer numberFormat = new StringBuffer();
//        for (int i = 0; i < precisionNumber; i++) {
//            numberFormat.append("#");
//        }
//        if (scaleNum > 0) {
//            for (int i = 0; i < scaleNum; i++) {
//                if (i == 0) {
//                    numberFormat.append(".");
//                }
//                numberFormat.append("#");
//            }
//        }
//        return numberFormat.toString();
//    }

    /**
     * Get csv header list
     * @param cMsg NSAL9900CMsg
     * @return String[]
     */
    public static String[] getCsvHeaderList(NSAL9900CMsg cMsg) {

        String[] headerList = new String[cMsg.C.length()];
        int dplyCnt = 0;
        for (int i = 0; i < cMsg.C.getValidCount(); i++) {
            if (!hasValue(cMsg.C.no(i).dplySortNum)) {
                continue;
            }
            headerList[dplyCnt] = cMsg.C.no(i).logicMaintTrgtColNm.getValue();
            dplyCnt++;
        }
        while (dplyCnt < cMsg.C.length()) {
            headerList[dplyCnt] = " ";
            dplyCnt++;
        }
        return headerList;
    }

    /**
     * validationAndCopyToSMsgCsv
     * @param cMsg NSAL9900CMsg
     * @param sMsg NSAL9900SMsg
     * @param fMsg NSAL9900F00FMsg
     * @param keyItemList List<String[]>
     * @param inacItemList List<String[]>
     * @return int
     */
    public static int validationAndCopyToSMsgCsv(NSAL9900CMsg cMsg, NSAL9900SMsg sMsg, NSAL9900F00FMsg fMsg, List<String[]> keyItemList, List<String[]> inacItemList) {

        // Set FMsg value and Error Info to newDetailInfo
        boolean errFlg = false;
        int errIdx = -1;
        NSAL9900_BSMsg newDetailInfo = new NSAL9900_BSMsg();
        int curIdx = sMsg.B.getValidCount();

        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            NSAL9900_CSMsg targetColumnInfo = (NSAL9900_CSMsg) sMsg.C.get(i);

            if (!hasValue(targetColumnInfo.dplySortNum)) {
                continue;
            }
            String detailItemNm = targetColumnInfo.detailItemNm.getValue();
            String colTpCd = targetColumnInfo.colTpCd.getValue();
            if (!hasValue(detailItemNm) || !hasValue(colTpCd)) {
                continue;
            }
            String csvItemNm = STR_XX_STRING + STR_UNDER_BAR + leftPad(String.valueOf(i), PAD_NUM, STR_ZERO);

            // Get Csv ItemValue
            String targetValue = fMsg.getValueString(csvItemNm, -1).trim();
            BigDecimal targetValueB = null;

            try {
                // Get validation target value
                Field f = NSAL9900_BSMsg.class.getField(detailItemNm);
                // Mandatory Check
                if (!isMondatoryCheck(targetValue, targetColumnInfo)) {
                    ((EZDSItem) f.get(newDetailInfo)).setErrorInfo(1, MSG_ID_ZZZM9007E, new String[] {targetColumnInfo.logicMaintTrgtColNm.getValue() });
                    errFlg = true;
                    continue;
                }

                if (COL_TP_STRING.equals(colTpCd)) {

                    // Validation(String)
                    if (!checkString(targetValue, targetColumnInfo, newDetailInfo, f)) {
                        errFlg = true;
                        continue;
                    }
                    // Relation check
//                    if (!relationCheck(cMsg, targetValueS, targetColumnInfo)) {
//                        ((EZDSStringItem) f.get(newDetailInfo)).setErrorInfo(1, MSG_ID_ZZZM9006E, new String[] {targetColumnInfo.logicMaintTrgtColNm.getValue(), targetColumnInfo.physRelnTblNm.getValue() });
//                        errFlg = true;
//                        continue;
//                    }
                } else if (COL_TP_NUMBER.equals(colTpCd)) {

                    // START 2016/03/09 T.Aoyagi [QC#5213, ADD]
                    if (!hasValue(targetValue)) {
                        continue;
                    }
                    // END 2016/03/09 T.Aoyagi [QC#5213, ADD]

                    if (!ZYPCommonFunc.isNumberCheck(targetValue)) {
                        ((EZDSItem) f.get(newDetailInfo)).setErrorInfo(1, MSG_ID_NSAM0206E, new String[] {"Number"});
                        errFlg = true;
                        continue;
                    }
                    targetValueB = new BigDecimal(fMsg.getValueString(csvItemNm, -1));
                    if (!checkNumber(targetValueB, targetColumnInfo, newDetailInfo, f)) {
                        errFlg = true;
                        continue;
                    }
                } else if (COL_TP_DATE.equals(colTpCd)) {

                    if (!checkDate(targetValue, colTpCd)) {
                        ((EZDSItem) f.get(newDetailInfo)).setErrorInfo(1, MSG_ID_NSAM0206E, new String[] {"Date"});
                        errFlg = true;
                        continue;
                    }
                } else if (COL_TP_YEARMONTH.equals(colTpCd)) {

                    if (!checkDate(targetValue, colTpCd)) {
                        ((EZDSItem) f.get(newDetailInfo)).setErrorInfo(1, MSG_ID_NSAM0206E, new String[] {"Date"});
                        errFlg = true;
                        continue;
                    }
                } else if (COL_TP_YEAR.equals(colTpCd)) {

                    if (!checkDate(targetValue, colTpCd)) {
                        ((EZDSItem) f.get(newDetailInfo)).setErrorInfo(1, MSG_ID_NSAM0206E, new String[] {"Date"});
                        errFlg = true;
                        continue;
                    }
                } else if (COL_TP_TIME.equals(colTpCd)) {

                    if (!checkDate(targetValue, colTpCd)) {
                        ((EZDSItem) f.get(newDetailInfo)).setErrorInfo(1, MSG_ID_NSAM0206E, new String[] {"Time"});
                        errFlg = true;
                        continue;
                    }
                } else if (COL_TP_TS.equals(colTpCd)) {

                    if (!checkDate(targetValue, colTpCd)) {
                        ((EZDSItem) f.get(newDetailInfo)).setErrorInfo(1, MSG_ID_NSAM0206E, new String[] {"Date"});
                        errFlg = true;
                        continue;
                    }
                }

                if (COL_TP_NUMBER.equals(colTpCd)) {
                    newDetailInfo.setValue(detailItemNm, -1, targetValueB);
                } else {
                    newDetailInfo.setValue(detailItemNm, -1, targetValue);
                }
            } catch (Exception ex) {
                throw new S21AbendException(MSG_ID_NSAM0219E, new String[]{ex.getCause().toString()});
            }
        }

        // If same key exists in SMsg,
        // overwrite excepting inactive column.
        boolean existsFlg = false;
        int targetIdx = curIdx;
        NSAL9900_BSMsg targetDetailInfo = new NSAL9900_BSMsg();
        int i = 0;
        for (; i < sMsg.B.getValidCount(); i++) {
            targetDetailInfo = (NSAL9900_BSMsg) sMsg.B.get(i);
            for (int j = 0; j < keyItemList.size(); j++) {
                if (COL_TP_NUMBER.equals(keyItemList.get(j)[1])) {
                    BigDecimal targetValue = targetDetailInfo.getValueBigDecimal(keyItemList.get(j)[0], -1);
                    BigDecimal newValue = newDetailInfo.getValueBigDecimal(keyItemList.get(j)[0], -1);
                    if (hasValue(targetValue) && hasValue(newValue) && targetValue.compareTo(newValue) == 0) {
                        existsFlg = true;
                    } else {
                        existsFlg = false;
                        break;
                    }

                } else {
                    String targetValue = targetDetailInfo.getValueString(keyItemList.get(j)[0], -1);
                    String newValue = newDetailInfo.getValueString(keyItemList.get(j)[0], -1);
                    if (hasValue(targetValue) && hasValue(newValue) && targetValue.equals(newValue)) {
                        existsFlg = true;
                    } else {
                        existsFlg = false;
                        break;
                    }
                }
            }
            if (existsFlg) {
                targetIdx = i;
                break;
            }
        }

        if (existsFlg) {
            for (int j = 0; j < inacItemList.size(); j++) {
                if (COL_TP_NUMBER.equals(inacItemList.get(j)[1])) {
                    BigDecimal targetValue = targetDetailInfo.getValueBigDecimal(inacItemList.get(j)[0], -1);
                    newDetailInfo.setValue(inacItemList.get(j)[0], -1, targetValue);
                } else {
                    String targetValue = targetDetailInfo.getValueString(inacItemList.get(j)[0], -1);
                    newDetailInfo.setValue(inacItemList.get(j)[0], -1, targetValue);
                }

            }
            setValue(newDetailInfo.updateFlg, FLG_ON_Y);
            setValue(newDetailInfo.xxChkBox, targetDetailInfo.xxChkBox);
            setValue(newDetailInfo.ezUpTime, targetDetailInfo.ezUpTime);
            setValue(newDetailInfo.ezUpTimeZone, targetDetailInfo.ezUpTimeZone);
        }

        // add start 2016/05/10 QC#5389
        // If same key exists in Table, overwrite excepting inactive column.
        if (!existsFlg) {
            searchTableByUploadKey(cMsg, sMsg, newDetailInfo);
        }
        // add end 2016/05/10 QC#5389

        EZDMsg.copy(newDetailInfo, null, sMsg.B.get(targetIdx), null);

        if (!existsFlg) {
            sMsg.B.setValidCount(targetIdx + 1);
        }
        if (errFlg) {
            errIdx = targetIdx;
        }

        return errIdx;
    }

    // add start 2016/05/10 QC#5389
    private static void searchTableByUploadKey(NSAL9900CMsg cMsg, NSAL9900SMsg sMsg, NSAL9900_BSMsg newDetailInfo) {

        try {
            EZDItemAttribute[] item = new EZDItemAttribute[sMsg.C.getValidCount()];
            String tblClsNm = TMSG_PACKAGE + sMsg.tblCd.getValue() + TMSG_SFX;

            @SuppressWarnings("unchecked")
            Class<EZDTMsg> tblClass = (Class<EZDTMsg>) Class.forName(tblClsNm);
            EZDTMsg tMsg = (EZDTMsg) tblClass.newInstance();
            tMsg.clear();
            tMsg.setDBValue("glblCmpyCd", sMsg.glblCmpyCd.getValue());

            String strAttrCd = "";
            String detailItemNm = "";
            String colTpCd = "";
            for (int colCnt = 0; colCnt < sMsg.C.getValidCount(); colCnt++) {
                strAttrCd = getAttrCd(sMsg.C.no(colCnt).physMaintTrgtColNm.getValue());
                item[colCnt] = tMsg.getAttr(strAttrCd);
                // START 2016/07/12 M.Ugaki [QC#11674, ADD]
                if (item[colCnt] == null) {
                    strAttrCd = getAttrCdWithSuffix(sMsg.C.no(colCnt).physMaintTrgtColNm.getValue());
                    item[colCnt] = tMsg.getAttr(strAttrCd);
                }
                // END 2016/07/12 M.Ugaki [QC#11674, ADD]
                colTpCd = sMsg.C.no(colCnt).colTpCd.getValue();
                detailItemNm = sMsg.C.no(colCnt).detailItemNm.getValue();
                if (item[colCnt] != null && item[colCnt].isDBPrimaryKey()) {
                    if (COL_TP_NUMBER.equals(colTpCd)) {
                        tMsg.setDBValue(strAttrCd, newDetailInfo.getValueBigDecimal(detailItemNm, -1));
                    } else {
                        tMsg.setDBValue(strAttrCd, newDetailInfo.getValueString(detailItemNm, -1));
                    }
                }
            }

            tMsg = EZDTBLAccessor.findByKey(tMsg);

            if (tMsg != null) {
                for (int colCnt = 0; colCnt < cMsg.C.getValidCount(); colCnt++) {
                    if (FLG_ON_Y.equals(cMsg.C.no(colCnt).itemInacFlg.getValue())) {
                        strAttrCd = getAttrCd(sMsg.C.no(colCnt).physMaintTrgtColNm.getValue());
                        // START 2016/07/12 M.Ugaki [QC#11674, ADD]
                        if (tMsg.getAttr(strAttrCd) == null) {
                            strAttrCd = getAttrCdWithSuffix(sMsg.C.no(colCnt).physMaintTrgtColNm.getValue());
                        }
                        // END 2016/07/12 M.Ugaki [QC#11674, ADD]

                        colTpCd = sMsg.C.no(colCnt).colTpCd.getValue();
                        detailItemNm = sMsg.C.no(colCnt).detailItemNm.getValue();
                        if (COL_TP_NUMBER.equals(colTpCd)) {
                            newDetailInfo.setValue(detailItemNm, -1, (BigDecimal) tMsg.getDBValue(strAttrCd));
                        } else {
                            newDetailInfo.setValue(detailItemNm, -1, (String) tMsg.getDBValue(strAttrCd));
                        }
                    }
                }
                setValue(newDetailInfo.ezUpTime, (String) tMsg.getDBValue("ezUpTime"));
                setValue(newDetailInfo.ezUpTimeZone, (String) tMsg.getDBValue("ezUpTimeZone"));
                setValue(newDetailInfo.updateFlg, FLG_ON_Y);
                setValue(newDetailInfo.xxChkBox, FLG_OFF_N);
            }
        } catch (Exception ex) {
            throw new S21AbendException(MSG_ID_NSAM0219E, new String[] {ex.getCause().toString() });
        }
    }
    // add end 2016/05/10 QC#5389

    /**
     * Read csv header
     * @param cMsg NSAL9900CMsg
     * @param fMsg NSAL9900F00FMsg
     * @param mappedFile EZDCSVInFile
     */
    public static void readCsvHeader(NSAL9900CMsg cMsg, NSAL9900F00FMsg fMsg, EZDCSVInFile mappedFile) {
        /*
         * if ZYECSVUploadFMsg read the csv file header, some errors
         * be detected. because csv header is not consistent with FMsg
         * format. So if read the header, it has to clear() the FMsg.
         * if there is no header, it will be skipped the first row.
         */
        int header = mappedFile.read();
        if (header == 1) {
            cMsg.setMessageInfo(MSG_ID_ZYEM0004E);
        }
        fMsg.clear();
    }

    /**
     * Addon Validation
     * @param cMsg NSAL9900CMsg
     * @param sMsg NSAL9900SMsg
     * @return boolean
     */
    public static boolean addonValidation(NSAL9900CMsg cMsg, NSAL9900SMsg sMsg) {

        boolean result = true;

        try {
            String className = "business.blap.NSAL9900.addon." + cMsg.tblCd.getValue() + Addon.class.getSimpleName();

            Class< ? > addonClass = Class.forName(className);
            Addon addonObj = (Addon) addonClass.newInstance();
            if (!addonObj.validation(cMsg, sMsg)) {
                result = false;
            }
        } catch (ClassNotFoundException e) {
            result = true;
        } catch (Exception ex) {
            throw new S21AbendException(MSG_ID_NSAM0219E, new String[]{ex.getCause().toString()});
        }
        return result;
    }
}
