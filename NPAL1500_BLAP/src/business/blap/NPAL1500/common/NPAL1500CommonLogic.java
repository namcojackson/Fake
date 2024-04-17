/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1500.common;

import static business.blap.NPAL1500.constant.NPAL1500Constant.BIZ_ID;
import static business.blap.NPAL1500.constant.NPAL1500Constant.COMMA;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DB_COLUMN_COA_ACCT_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DB_COLUMN_COA_AFFL_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DB_COLUMN_COA_BR_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DB_COLUMN_COA_CC_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DB_COLUMN_COA_CH_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DB_COLUMN_COA_CMPY_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DB_COLUMN_COA_EXTN_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DB_COLUMN_COA_PROD_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DB_COLUMN_COA_PROJ_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DB_COLUMN_LOC_NM;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DB_COLUMN_MAX_LINE_NUM;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DB_COLUMN_PRNT_VND_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DB_COLUMN_PRNT_VND_NM;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DB_COLUMN_RTL_SWH_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DB_COLUMN_RTL_WH_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DB_COLUMN_RTL_WH_NM;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DB_COLUMN_VND_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DB_DEAL_CCY_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DB_MDSE_DESC_SHORT_TXT;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DB_VND_PMT_TERM_DESC_TXT;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DISPLAY_DEST_RTL_WH_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DISPLAY_VND_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_ADD_NEWLINE;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_CMN_SAVE;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_CMN_SUBMIT;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_GET_MDSE_INFO;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_NMAL6800;
import static business.blap.NPAL1500.constant.NPAL1500Constant.FIRST_DTL_LINE_NUM;
import static business.blap.NPAL1500.constant.NPAL1500Constant.MANUAL_DIRECT_SHIP_CUST_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.MSG_PARAM_ACCT;
import static business.blap.NPAL1500.constant.NPAL1500Constant.MSG_PARAM_AFFL;
import static business.blap.NPAL1500.constant.NPAL1500Constant.MSG_PARAM_BR;
import static business.blap.NPAL1500.constant.NPAL1500Constant.MSG_PARAM_CC;
import static business.blap.NPAL1500.constant.NPAL1500Constant.MSG_PARAM_CH;
import static business.blap.NPAL1500.constant.NPAL1500Constant.MSG_PARAM_CMPY;
import static business.blap.NPAL1500.constant.NPAL1500Constant.MSG_PARAM_EXTN;
import static business.blap.NPAL1500.constant.NPAL1500Constant.MSG_PARAM_PROD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.MSG_PARAM_PROJ;
import static business.blap.NPAL1500.constant.NPAL1500Constant.MSG_PARAM_SEGMENT;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAL1496W;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM0023E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM0076E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1193E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1320E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1363E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1430E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1488E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1602E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1611E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1613E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPZM0140E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NZZM0000E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.PCT_100;
import static business.blap.NPAL1500.constant.NPAL1500Constant.PERIOD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.PO_DTL_LINE_NUM_000;
import static business.blap.NPAL1500.constant.NPAL1500Constant.PO_LINE_STS_TXT_OPEN;
import static business.blap.NPAL1500.constant.NPAL1500Constant.SEGMENT_ELEMENT_LENGTH_ACCT;
import static business.blap.NPAL1500.constant.NPAL1500Constant.SEGMENT_ELEMENT_LENGTH_AFFL;
import static business.blap.NPAL1500.constant.NPAL1500Constant.SEGMENT_ELEMENT_LENGTH_BR;
import static business.blap.NPAL1500.constant.NPAL1500Constant.SEGMENT_ELEMENT_LENGTH_CC;
import static business.blap.NPAL1500.constant.NPAL1500Constant.SEGMENT_ELEMENT_LENGTH_CH;
import static business.blap.NPAL1500.constant.NPAL1500Constant.SEGMENT_ELEMENT_LENGTH_CMPY;
import static business.blap.NPAL1500.constant.NPAL1500Constant.SEGMENT_ELEMENT_LENGTH_EXTN;
import static business.blap.NPAL1500.constant.NPAL1500Constant.SEGMENT_ELEMENT_LENGTH_PROD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.SEGMENT_ELEMENT_LENGTH_PROJ;
import static business.blap.NPAL1500.constant.NPAL1500Constant.SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.SEGMENT_TOKEN_LIST_SIZE;
import static business.blap.NPAL1500.constant.NPAL1500Constant.VAR_CHAR_KEY_DELIMITER;
import static business.blap.NPAL1500.constant.NPAL1500Constant.VAR_CHAR_KEY_MANUAL_DROPSHIP_WAREHOUSE_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.ZERO;
import static business.blap.NPAL1500.constant.NPAL1500Constant.ZZM9000E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.ZZM9037E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.ZERO_STRING;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DOT;
import static business.blap.NPAL1500.constant.NPAL1500Constant.MDSE_TP_SET;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1329E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.common.EZDSMsg;
import parts.common.EZDSMsgArray;
import parts.common.EZDSStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NPAL1500.NPAL1500CMsg;
import business.blap.NPAL1500.NPAL1500Query;
import business.blap.NPAL1500.NPAL1500SMsg;
import business.blap.NPAL1500.NPAL1500_ASMsg;
import business.blap.NPAL1500.constant.NPAL1500Constant;
import business.db.DEF_DPLY_COA_INFOTMsg;
import business.db.MDSETMsg;
import business.db.PO_DTLTMsg;
import business.db.RCV_ASN_VNDTMsg;
import business.parts.NFZC102001PMsg;
import business.parts.NPZC113001PMsg;
import business.parts.NPZC129001PMsg;

import com.canon.cusa.s21.api.NFA.NFZC102001.NFZC102001;
import com.canon.cusa.s21.api.NPZ.NPZC113001.NPZC113001;
import com.canon.cusa.s21.api.NPZ.NPZC129001.NPZC129001;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCost;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCostBean;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_PO_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GLBL_CMPY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MATCH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_SYS_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_OWNR;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 * <pre>
 * Business ID : NPAL1500 PO Entry
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/18/2015   CITS            N.Akaishi       Create          n/a
 * 2016/03/29   CITS            T.Tokutomi      Update          QC#5774
 * 2016/03/29   CITS            T.Tokutomi      Update          QC#6155
 * 2016/03/29   CITS            T.Tokutomi      Update          QC#6157
 * 2016/03/30   CITS            T.Tokutomi      Update          QC#6293
 * 2016/04/14   CSAI            K.Lee           Update          QC#7090
 * 2016/05/03   CSAI            K.Lee           Update          QC#7300
 * 2016/05/04   CSAI            D.Fukaya        Update          QC#7926
 * 2016/05/04   CSAI            D.Fukaya        Update          QC#7896
 * 2016/05/04   CSAI            K.Lee           Update          QC#5761
 * 2016/09/09   CITS            R.Shimamoto     Update          QC#13985
 * 2016/09/15   CITS            S.Endo          Update          N/A(Refactoring)
 * 2016/10/03   CITS            S.Endo          Update          N/A(Refactoring)
 * 2016/10/05   CITS            S.Endo          Update          QC#12768
 * 2016/10/11   CITS            S.Endo          Update          QC#14285
 * 2016/10/20   CITS            S.Endo          Update          QC#15222,Refactoring
 * 2016/10/31   CITS            S.Endo          Update          QC#14882
 * 2016/12/13   CITS            R.Shimamoto     Update          QC#15817
 * 2016/12/20   CITS            R.Shimamoto     Update          QC#16341
 * 2017/02/21   CITS            Y.IWASAKI       Update          QC#17487
 * 2017/02/27   CITS            R.Shimamoto     Update          QC#17785
 * 2017/05/22   CITS            R.Shimamoto     Update          QC#18176
 * 2017/06/19   CITS            S.Endo          Update          QC#19212
 * 2017/06/23   CITS            S.Endo          Update          QC#19485
 * 2017/06/30   CITS            R.Shimamoto     Update          QC#19693
 * 2017/08/04   CITS            R.Shimamoto     Update          QC#18761
 * 2017/11/07   Hitachi         Y.Takeno        Update          QC#21849
 * 2017/12/04   CITS            K.Kameoka       Update          QC#14858(Sol#060)
 * 01/09/2018   CITS            K.Kameoka       Update          QC#18602(Sol#102)
 * 01/25/2018   CITS            K.Ogino         Update          QC#23007
 * 01/30/2018   CITS            K.Ogino         Update          QC#23616
 * /04/062018   CITS            K.Fukumura      Update          QC#21170
 * 04/20/2018   CITS            Y.Iwasaki       Update          QC#25019
 * 05/31/2018   CITS            Y.Iwasaki       Update          QC#26231
 * 06/11/2018   CITS            K.Kameoka       Update          QC#25714
 * 06/14/2018   CITS            K.Kameoka       Update          QC#26474
 * 06/19/2018   CITS            K.Kameoka       Update          QC#21358
 * 06/20/2018   CITS            K.Kameoka       Update          QC#18420
 * 07/03/2018   CITS            T.Tokutomi      Update          QC#23726
 * 07/06/2018   CITS            K.Kameoka       Update          QC#25024
 * 08/01/2018   CITS            K.Kameoka       Update          QC#27474
 * 08/07/2018   CITS            K.Ogino         Update          QC#27024
 * 09/25/2018   CITS            K.Kameoka       Update          QC#28226
 * 11/09/2018   CITS            K.Kameoka       Update          QC#29064
 * 11/16/2018   CITS            K.Ogino         Update          QC#29155
 * 12/19/2018   Fujitsu         S.Takami        Update          QC#29456
 * 2019/01/15   Fujitsu         S.Takami        Update          QC#29778
 * 2019/01/25   Fujitsu         S.Takami        Update          QC#29778-2 => Delete Logic At QC#30181
 * 2019/02/06   Fujitsu         S.Takami        Update          QC#30181
 * 2019/03/20   Fujitsu         T.Ogura         Update          QC#30769
 * 2019/03/25   CITS            K.Ogino         Update          QC#30768
 * 2019/08/02   Fujitsu         T.Ogura         Update          QC#51448
 * 2019/08/12   CITS            R.Kurahashi     Update          QC#52442
 * 2019/09/17   CITS            R.Shimamoto     Update          QC#53271
 * 2019/10/02   CITS            R.Shimamoto     Update          QC#52460
 * 2019/10/04   CITS            R.Shimamoto     Update          QC#53392
 * 2019/12/02   Fujitsu         T.Ogura         Update          QC#54813
 * 02/18/2020   Fujitsu         T.Ogura         Update          QC#55916
 * 03/10/2020   CITS            K.Ogino         Update          QC#56195
 * 2021/04/21   CITS            J.Evangelista   Update          QC#57102
 * 2021/04/23   CITS            M.Furugoori     Update          QC#58645
 * 2022/05/16   Hitachi         A.Kohinata      Update          QC#57934
 * 2022/12/09   Hitachi         M.Kikushima     Update          QC#60604
 * 2023/02/14   Hitachi         S.Dong          Update          QC#60966
 * 2023/04/28   Hitachi         S.Dong          Update          QC#60966
 * 2024/1/4     CITS            K.Iwamoto       Update          QC#62443
 * 2024/3/1     CITS            S.Okamoto       Update          QC#62443
 * </pre>
 */
/**
 * @author Q10728
 */
public class NPAL1500CommonLogic {

    /**
     * AccountType
     */
    public enum AccountType { CH, AC, VA }

    // 2019/01/25 QC#29778-2 Add Start
    /** Account Defaulting Mode */
    private enum AcctDefMode { NO_OPE, SHIP_ITEM, ITEM }
    // 2019/01/25 QC#29778-2 Add End

    /**
     * <pre>
     * Add QC#27127 Check MDSE Info Cache
     * </pre>
     * @param bizMsg NPAL1500CMsg
     * @param glblMsg NPAL1500SMsg
     * @param index int
     * @param deriveItemInfoCache S21LRUMap<Object, S21SsmEZDResult>
     * @return boolean
     */
    public static boolean checkMdseInfoCache(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg, int index, S21LRUMap<Object, S21SsmEZDResult> deriveItemInfoCache) {

        Map<String, Object> resultMap = null;
        String mdseCd = glblMsg.A.no(index).mdseCd_A1.getValue();

        S21SsmEZDResult ssmResult = deriveItemInfoCache.get(mdseCd);
        if (ssmResult == null) {
            ssmResult = NPAL1500Query.getInstance().getDeriveItemInfo(bizMsg, glblMsg.A.no(index).mdseCd_A1.getValue());
        }

        if (ssmResult.isCodeNormal()) {

            resultMap = (Map<String, Object>) ssmResult.getResultObject();

            if (resultMap != null) {

            	// QC#53271 Mod Start
//            	ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).mdseDescShortTxt_A1, (String) resultMap.get(DB_MDSE_DESC_SHORT_TXT));
            	if (ZYPCommonFunc.hasValue(glblMsg.A.no(index).mdseDescShortTxt_A1) &&
            			!PO_LINE_TP.EXPENSE.equals(glblMsg.A.no(index).poLineTpCd_A1.getValue()) &&
            					!PO_LINE_TP.EXPENSE_WITH_RECEIPT.equals(glblMsg.A.no(index).poLineTpCd_A1.getValue())) {
            		ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).mdseDescShortTxt_A1, (String) resultMap.get(DB_MDSE_DESC_SHORT_TXT));
            	}
                // QC#53271 Mod End
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).rcvSerTakeFlg_IB, (String) resultMap.get("RCV_SER_TAKE_FLG"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).instlBaseCtrlFlg_IB, (String) resultMap.get("INSTL_BASE_CTRL_FLG"));

                if (!DS_PO_TP.BUYBACK_PO.equals(glblMsg.dsPoTpCd.getValue()) && !PO_MDSE_CMPSN_TP.CHILD.equals(glblMsg.A.no(index).poMdseCmpsnTpCd_HD.getValue())) {
                    setPoMdseCmpsnTp(glblMsg, index, resultMap);
                }
            }
        }

        if (resultMap == null) {

            MDSETMsg mdseTMsg = new MDSETMsg();

            ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, glblMsg.A.no(index).mdseCd_A1);

            mdseTMsg = (MDSETMsg) EZDTBLAccessor.findByKey(mdseTMsg);
            if (mdseTMsg == null) {
                
                glblMsg.A.no(index).mdseCd_A1.setErrorInfo(1, NPAM0076E, new String[] {glblMsg.A.no(index).mdseCd_A1.getValue() });
                bizMsg.setMessageInfo(NPAM0076E, new String[] {"Item#" });
                return false;
            } else if (MDSE_ITEM_TP.TEXT_ITEM.equals(mdseTMsg.mdseItemTpCd.getValue())) {
                glblMsg.A.no(index).xxCntDplyFlg_A2.setValue(ZYPConstant.FLG_ON_Y);
                glblMsg.A.no(index).mdseCd_A1.clearErrorInfo();
                return true;
            }

        } else if (ZYPConstant.FLG_ON_Y.equals((String) resultMap.get("DSCTN_FLG"))) {
            //QC#27474 Mod Start
            if(!DS_PO_TP.BUYBACK_PO.equals(glblMsg.dsPoTpCd.getValue()))
            {
                glblMsg.A.no(index).mdseCd_A1.setErrorInfo(1, NPZM0140E);
                bizMsg.setMessageInfo(NPZM0140E);
                return false;
            }
            //QC#27474 Mod End
        }

        return true;
    }

    /**
     * <pre>
     * Check MDSE Info
     * </pre>
     * @param bizMsg NPAL1500CMsg
     * @param glblMsg NPAL1500SMsg
     * @param index int
     * @return boolean
     */
    public static boolean checkMdseInfo(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg, int index) {

        Map<String, Object> resultMap = null;

        S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getDeriveItemInfo(bizMsg, glblMsg.A.no(index).mdseCd_A1.getValue());

        if (ssmResult.isCodeNormal()) {

            resultMap = (Map<String, Object>) ssmResult.getResultObject();

            if (resultMap != null) {

                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).mdseDescShortTxt_A1, (String) resultMap.get(DB_MDSE_DESC_SHORT_TXT));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).rcvSerTakeFlg_IB, (String) resultMap.get("RCV_SER_TAKE_FLG"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).instlBaseCtrlFlg_IB, (String) resultMap.get("INSTL_BASE_CTRL_FLG"));

                if (!DS_PO_TP.BUYBACK_PO.equals(glblMsg.dsPoTpCd.getValue()) && !PO_MDSE_CMPSN_TP.CHILD.equals(glblMsg.A.no(index).poMdseCmpsnTpCd_HD.getValue())) {
                    setPoMdseCmpsnTp(glblMsg, index, resultMap);
                }
            }
        }

        if (resultMap == null) {

            MDSETMsg mdseTMsg = new MDSETMsg();

            ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, glblMsg.A.no(index).mdseCd_A1);

            mdseTMsg = (MDSETMsg) EZDTBLAccessor.findByKey(mdseTMsg);
            if (mdseTMsg == null) {
                
                glblMsg.A.no(index).mdseCd_A1.setErrorInfo(1, NPAM0076E, new String[] {glblMsg.A.no(index).mdseCd_A1.getValue() });
                bizMsg.setMessageInfo(NPAM0076E, new String[] {"Item#" });
                return false;
            } else if (MDSE_ITEM_TP.TEXT_ITEM.equals(mdseTMsg.mdseItemTpCd.getValue())) {
                glblMsg.A.no(index).xxCntDplyFlg_A2.setValue(ZYPConstant.FLG_ON_Y);
                glblMsg.A.no(index).mdseCd_A1.clearErrorInfo();
                return true;
            }

        } else if (ZYPConstant.FLG_ON_Y.equals((String) resultMap.get("DSCTN_FLG"))) {

            //QC#27474 Mod Start
            if(!DS_PO_TP.BUYBACK_PO.equals(glblMsg.dsPoTpCd.getValue()))
            {
                glblMsg.A.no(index).mdseCd_A1.setErrorInfo(1, NPZM0140E);
                bizMsg.setMessageInfo(NPZM0140E);
                return false;
            }
            //QC#27474 Mod End
        }

        return true;
    }

    /**
     * setPoMdseCmpsnTp
     * @param glblMsg NPAL1500SMsg
     * @param index int
     * @param resultMap Map<String, Object>
     */
    private static void setPoMdseCmpsnTp(NPAL1500SMsg glblMsg, int index, Map<String, Object> resultMap) {
        if (ZYPConstant.FLG_ON_Y.equals((String) resultMap.get("PRNT_CMPY_SET_MDSE_FLG"))) {

            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).poMdseCmpsnTpCd_HD, PO_MDSE_CMPSN_TP.PARENT);

        } else {

            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).poMdseCmpsnTpCd_HD, PO_MDSE_CMPSN_TP.REGULAR);
        }
    }

    /**
     * Get Primary Vendor from ASL
     * @param bizMsg NPAL1500CMsg
     * @param glblMsg NPAL1500SMsg
     * @param index int
     * @return NPZC113001PMsg
     */
    public static NPZC113001PMsg execGetPrimVndFromAsl(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg, int index) {

        NPZC113001PMsg npzc113001PMsg = new NPZC113001PMsg();
        ZYPEZDItemValueSetter.setValue(npzc113001PMsg.glblCmpyCd, glblMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(npzc113001PMsg.slsDt, ZYPDateUtil.getSalesDate(glblMsg.glblCmpyCd.getValue()));
        ZYPEZDItemValueSetter.setValue(npzc113001PMsg.shipToCustCd, glblMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(npzc113001PMsg.vndCd, glblMsg.vndCd);
        ZYPEZDItemValueSetter.setValue(npzc113001PMsg.prntVndCd, glblMsg.prntVndCd);
        ZYPEZDItemValueSetter.setValue(npzc113001PMsg.mdseCd, glblMsg.A.no(index).mdseCd_A1);
        NPZC113001 npzc113001 = new NPZC113001();
        npzc113001.execute(npzc113001PMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(npzc113001PMsg)) {

            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(npzc113001PMsg);

            for (String xxMsgId : xxMsgIdList) {

                S21InfoLogOutput.println(xxMsgId);
                glblMsg.A.no(index).mdseCd_A1.setErrorInfo(1, xxMsgId);
                bizMsg.setMessageInfo(xxMsgId);
            }

            return null;
        }

        return npzc113001PMsg;
    }

    /**
     * Call Change Vendor UOM API
     * @param bizMsg NPAL1500CMsg
     * @param glblMsg NPAL1500SMsg
     * @param index int
     * @param poDispQty BigDecimal
     * @return NPZC129001PMsg
     */
    public static NPZC129001PMsg execChgVndUom(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg, int index, BigDecimal poDispQty) {

        NPZC129001PMsg npzc129001PMsg = new NPZC129001PMsg();

        ZYPEZDItemValueSetter.setValue(npzc129001PMsg.glblCmpyCd, glblMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(npzc129001PMsg.slsDt, ZYPDateUtil.getSalesDate(glblMsg.glblCmpyCd.getValue()));
        ZYPEZDItemValueSetter.setValue(npzc129001PMsg.prntVndCd, glblMsg.prntVndCd);
        ZYPEZDItemValueSetter.setValue(npzc129001PMsg.vndCd, glblMsg.vndCd);
        ZYPEZDItemValueSetter.setValue(npzc129001PMsg.mdseCd, glblMsg.A.no(index).mdseCd_A1);

        if (ZYPCommonFunc.hasValue(poDispQty)) {
            ZYPEZDItemValueSetter.setValue(npzc129001PMsg.poDispQty, poDispQty);
        }
        ZYPEZDItemValueSetter.setValue(npzc129001PMsg.adjQtyIncrFlg, ZYPConstant.FLG_ON_Y);
        NPZC129001 npzc129001 = new NPZC129001();
        npzc129001.execute(npzc129001PMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(npzc129001PMsg)) {

            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(npzc129001PMsg);

            for (String xxMsgId : xxMsgIdList) {

                S21InfoLogOutput.println(xxMsgId);
                if (xxMsgId.equals("NPAM1364E")) {
                    glblMsg.A.no(index).mdseCd_A1.setErrorInfo(1, xxMsgId);
                } else {
                    glblMsg.A.no(index).poDispQty_A1.setErrorInfo(1, xxMsgId);
                }
                bizMsg.setMessageInfo(xxMsgId);
            }

            return null;
        }

        return npzc129001PMsg;
    }

    /**
     * <pre>
     * get Account Info
     * </pre>
     * @param bizMsg NPAL1500CMsg
     * @param glblMsg NPAL1500SMsg
     * @param index int
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    public static boolean getAccountInfo(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg, int index, boolean overwrite) {

        NPAL1500_ASMsg curMsg=glblMsg.A.no(index);

        if(!overwrite) {
            if (!(!ZYPCommonFunc.hasValue(glblMsg.vndCd_HG) && ZYPCommonFunc.hasValue(glblMsg.vndCd.getValue()))
                    && ZYPCommonFunc.hasValue(curMsg.xxScrItem130Txt_CH)) {
                ZYPEZDItemValueSetter.setValue(glblMsg.vndCd_HG, glblMsg.vndCd);
                return true;
            }
        }

        // COA will be blank if PRCH_REQ_LINE_TP is not Expense.
        if (!(ZYPCommonFunc.hasValue(curMsg.poLineTpCd_A1)
                && (PO_LINE_TP.EXPENSE.equals(curMsg.poLineTpCd_A1.getValue())
                || PO_LINE_TP.EXPENSE_WITH_RECEIPT.equals(curMsg.poLineTpCd_A1.getValue())))) {
            curMsg.splyCoaCmpyCd.clear();
            curMsg.splyCoaBrCd.clear();
            curMsg.splyCoaCcCd.clear();
            curMsg.splyCoaAcctCd.clear();
            curMsg.splyCoaProdCd.clear();
            curMsg.splyCoaChCd.clear();
            curMsg.splyCoaAfflCd.clear();
            curMsg.splyCoaProjCd.clear();
            curMsg.splyCoaExtnCd.clear();
            curMsg.xxScrItem130Txt_CH.clear();
            ZYPEZDItemValueSetter.setValue(glblMsg.vndCd_HG, glblMsg.vndCd);
            return true;
        }

        // 2019/02/06 QC#30181 Add Start
        String shipToCustCd = glblMsg.shipToCustCd.getValue();
        AcctDefMode acctDefMode = getAccountDefaultingMode(bizMsg, curMsg);
        if (AcctDefMode.ITEM.equals(acctDefMode)) {
            shipToCustCd = "";
        }
        // 2019/02/06 QC#30181 Add End
        int last=index-1;
        for(; last>=0; --last) {
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(last).poLineTpCd_A1)
                    && (PO_LINE_TP.EXPENSE.equals(glblMsg.A.no(last).poLineTpCd_A1.getValue())
                    || PO_LINE_TP.EXPENSE_WITH_RECEIPT.equals(glblMsg.A.no(last).poLineTpCd_A1.getValue()))) {
                break;
            }
        }

        if(last>=0) {
            // Copy account from previous Expense record if exists.
            NPAL1500_ASMsg lastMsg = glblMsg.A.no(last);

            applyChrgAccount(lastMsg, lastMsg.xxScrItem130Txt_CH.getValue());

            ZYPEZDItemValueSetter.setValue(curMsg.splyCoaCmpyCd, lastMsg.splyCoaCmpyCd);
            ZYPEZDItemValueSetter.setValue(curMsg.splyCoaBrCd, lastMsg.splyCoaBrCd);
            ZYPEZDItemValueSetter.setValue(curMsg.splyCoaCcCd, lastMsg.splyCoaCcCd);
            ZYPEZDItemValueSetter.setValue(curMsg.splyCoaAcctCd, lastMsg.splyCoaAcctCd);
            ZYPEZDItemValueSetter.setValue(curMsg.splyCoaProdCd, lastMsg.splyCoaProdCd);
            ZYPEZDItemValueSetter.setValue(curMsg.splyCoaChCd, lastMsg.splyCoaChCd);
            ZYPEZDItemValueSetter.setValue(curMsg.splyCoaAfflCd, lastMsg.splyCoaAfflCd);
            ZYPEZDItemValueSetter.setValue(curMsg.splyCoaProjCd, lastMsg.splyCoaProjCd);
            ZYPEZDItemValueSetter.setValue(curMsg.splyCoaExtnCd, lastMsg.splyCoaExtnCd);

            // 2019/08/12 QC#52442 Del Start
            // 2019/02/06 QC#30181 Add Start
//            NPXC001001GetDefNineSegDataBean defNineSegDataBean = NPXC001001GetDefNineSegData.getInstance().getDefNineSegData(//
//                    glblMsg.glblCmpyCd.getValue(), //
//                    shipToCustCd, //
//                    curMsg.mdseCd_A1.getValue(), //
//                    lastMsg.xxScrItem130Txt_CH.getValue());
//            ZYPEZDItemValueSetter.setValue(curMsg.splyCoaProdCd, getValidStr(curMsg.splyCoaProdCd, defNineSegDataBean.getCoaProdCd()));
//            ZYPEZDItemValueSetter.setValue(curMsg.splyCoaChCd, getValidStr(curMsg.splyCoaChCd, defNineSegDataBean.getCoaChCd()));
//            ZYPEZDItemValueSetter.setValue(curMsg.splyCoaProjCd, getValidStr(curMsg.splyCoaProjCd, defNineSegDataBean.getCoaProjCd()));
            // 2019/08/12 QC#52442 Del End
            fillChrgAccountText(curMsg);
            // 2019/02/06 QC#30181 Add End
            // 2019/02/06 QC#30181 Del Start
//            // 2018/12/19 QC#29456 Mod Start
////            ZYPEZDItemValueSetter.setValue(curMsg.xxScrItem130Txt_CH, lastMsg.xxScrItem130Txt_CH);
//            if (!overwrite) {
//                NPXC001001GetDefNineSegDataBean defNineSegDataBean = NPXC001001GetDefNineSegData.getInstance().getDefNineSegData(//
//                        glblMsg.glblCmpyCd.getValue(), //
//                        glblMsg.shipToCustCd.getValue(), //
//                        curMsg.mdseCd_A1.getValue());
//                if (defNineSegDataBean != null) {
//                    // 2019/01/25 QC#29778-2 Mod Start
////                    ZYPEZDItemValueSetter.setValue(curMsg.splyCoaProdCd, defNineSegDataBean.getCoaProdCd());
////                    ZYPEZDItemValueSetter.setValue(curMsg.splyCoaChCd, defNineSegDataBean.getCoaChCd());
////                    ZYPEZDItemValueSetter.setValue(curMsg.splyCoaProjCd, defNineSegDataBean.getCoaProjCd());
//
//                    ZYPEZDItemValueSetter.setValue(curMsg.splyCoaProdCd, getValidStr(curMsg.splyCoaProdCd, defNineSegDataBean.getCoaProdCd()));
//                    ZYPEZDItemValueSetter.setValue(curMsg.splyCoaChCd, getValidStr(curMsg.splyCoaChCd, defNineSegDataBean.getCoaChCd()));
//                    ZYPEZDItemValueSetter.setValue(curMsg.splyCoaProjCd, getValidStr(curMsg.splyCoaProjCd, defNineSegDataBean.getCoaProjCd()));
//                    // 2019/01/25 QC#29778-2 Mod End
//                }
//                fillChrgAccountText(curMsg);
//            } else {
//                // 2019/01/15 QC#29778 Add Start
//                NPXC001001GetDefNineSegDataBean defNineSegDataBean = NPXC001001GetDefNineSegData.getInstance().getNineSegDataFromItem(//
//                        glblMsg.glblCmpyCd.getValue(), //
//                        curMsg.splyCoaAcctCd.getValue(), //
//                        curMsg.mdseCd_A1.getValue());
//                if (defNineSegDataBean != null) {
//                    // 2019/01/25 QC#29778-2 Mod Start
////                    if (ZYPCommonFunc.hasValue(defNineSegDataBean.getCoaProdCd())) {
////                        ZYPEZDItemValueSetter.setValue(curMsg.splyCoaProdCd, defNineSegDataBean.getCoaProdCd());
////                    }
////                    if (ZYPCommonFunc.hasValue(defNineSegDataBean.getCoaProjCd())) {
////                        ZYPEZDItemValueSetter.setValue(curMsg.splyCoaProjCd, defNineSegDataBean.getCoaProjCd());
////                    }
//
//                    ZYPEZDItemValueSetter.setValue(curMsg.splyCoaProdCd, getValidStr(curMsg.splyCoaProdCd, defNineSegDataBean.getCoaProdCd()));
//                    ZYPEZDItemValueSetter.setValue(curMsg.splyCoaProjCd, getValidStr(curMsg.splyCoaProjCd, defNineSegDataBean.getCoaProjCd()));
//                    // 2019/01/25 QC#29778-2 Mod End
//                }
//                fillChrgAccountText(curMsg);
//                // 2019/01/15 QC#29778 Add End
//                // 2019/01/15 QC#29778 Del Start
////                ZYPEZDItemValueSetter.setValue(curMsg.xxScrItem130Txt_CH, lastMsg.xxScrItem130Txt_CH);
//                // 2019/01/15 QC#29778 Del End
//            }
//            // 2018/12/19 QC#29456 Mod End
            // 2019/02/06 QC#30181 Del End
        } else {
            //QC#62443 2024/3/1 Del Start
//            // 2018/12/19 QC#29456 Add Start
//            if (!ZYPCommonFunc.hasValue(glblMsg.shipToCustCd.getValue()) //
//                    && !ZYPCommonFunc.hasValue(curMsg.mdseCd_A1)) { // 2019/02/06 QC#30181 Add Condition
//                curMsg.splyCoaCmpyCd.clear();
//                curMsg.splyCoaBrCd.clear();
//                curMsg.splyCoaCcCd.clear();
//                curMsg.splyCoaAcctCd.clear();
//                curMsg.splyCoaProdCd.clear();
//                curMsg.splyCoaChCd.clear();
//                curMsg.splyCoaAfflCd.clear();
//                curMsg.splyCoaProjCd.clear();
//                curMsg.splyCoaExtnCd.clear();
//                curMsg.xxScrItem130Txt_CH.clear();
//                ZYPEZDItemValueSetter.setValue(glblMsg.vndCd_HG, glblMsg.vndCd);
//                return true;
//            }
//            // 2018/12/19 QC#29456 Add End
            //QC#62443 2024/3/1 Del End
            applyChrgAccount(curMsg, curMsg.xxScrItem130Txt_CH.getValue());

            ZYPEZDItemValueSetter.setValue(curMsg.splyCoaCmpyCd, glblMsg.glblCmpyCd.getValue());

            // 2018/12/19 QC#29456 Del Start
//            // Create account from header values.
//            S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getAcctInfoFromHeader(glblMsg.glblCmpyCd.getValue(), glblMsg.destRtlWhCd_DS.getValue(), glblMsg.prchGrpCd.getValue(), glblMsg.shipToCustCd.getValue());
//            Map<String, String> result = null;
//            if(ssmResult.isCodeNormal()) {
//                result = (Map<String, String>)ssmResult.getResultObject();
//            }
//            if(result==null) {
//                //curMsg.splyCoaCmpyCd.clear();
//                curMsg.splyCoaBrCd.clear();
//                curMsg.splyCoaCcCd.clear();
//                curMsg.splyCoaAcctCd.clear();
//                curMsg.splyCoaProdCd.clear();
//                curMsg.splyCoaChCd.clear();
//                curMsg.splyCoaAfflCd.clear();
//                curMsg.splyCoaProjCd.clear();
//                curMsg.splyCoaExtnCd.clear();
//                curMsg.xxScrItem130Txt_CH.clear();
//                fillChrgAccountText(curMsg);
//                
//                curMsg.xxChkBox_A1.setErrorInfo(1, NPAM0076E, new String[] {"COA information of Charge Account" });
//                return false;
//            }
            // 2018/12/19 QC#29456 Del End

            ZYPEZDItemValueSetter.setValue(curMsg.poAcctTpCd_CH, PO_ACCT_TP.CHARGE);
            // 2018/12/19 QC#29456 Del Start
//            ZYPEZDItemValueSetter.setValue(curMsg.splyCoaBrCd, result.get("COA_BR_CD"));
////            ZYPEZDItemValueSetter.setValue(curMsg.splyCoaCcCd, result.get("COA_CC_CD"));
//            ZYPEZDItemValueSetter.setValue(curMsg.splyCoaAfflCd, result.get("COA_AFFL_CD"));
            // 2018/12/19 QC#29456 Del End
            // 2019/08/12 QC#52442 Del Start
            // 2018/12/19 QC#29456 Add Start
//            NPXC001001GetDefNineSegDataBean defNineSegDataBean = NPXC001001GetDefNineSegData.getInstance().getDefNineSegData(//
//                    glblMsg.glblCmpyCd.getValue(), //
//                    shipToCustCd, //
//                    curMsg.mdseCd_A1.getValue(), //
//                    curMsg.xxScrItem130Txt_CH.getValue());
//            if (defNineSegDataBean == null) {
//                curMsg.splyCoaBrCd.clear();
//                curMsg.splyCoaCcCd.clear();
//                curMsg.splyCoaAcctCd.clear();
//                curMsg.splyCoaProdCd.clear();
//                curMsg.splyCoaChCd.clear();
//                curMsg.splyCoaAfflCd.clear();
//                curMsg.splyCoaProjCd.clear();
//                curMsg.splyCoaExtnCd.clear();
//                curMsg.xxScrItem130Txt_CH.clear();
//                fillChrgAccountText(curMsg);
//
//                curMsg.xxChkBox_A1.setErrorInfo(1, NPAM0076E, new String[] {"COA information of Charge Account" });
//                return false;
//            } else {
//                ZYPEZDItemValueSetter.setValue(curMsg.splyCoaBrCd, defNineSegDataBean.getCoaBrCd());
//                ZYPEZDItemValueSetter.setValue(curMsg.splyCoaCcCd, defNineSegDataBean.getCoaCcCd());
//                ZYPEZDItemValueSetter.setValue(curMsg.splyCoaAcctCd, defNineSegDataBean.getCoaAcctCd());
//                ZYPEZDItemValueSetter.setValue(curMsg.splyCoaProdCd, defNineSegDataBean.getCoaProdCd());
//                ZYPEZDItemValueSetter.setValue(curMsg.splyCoaChCd, defNineSegDataBean.getCoaChCd());
//                ZYPEZDItemValueSetter.setValue(curMsg.splyCoaAfflCd, defNineSegDataBean.getCoaAfflCd());
//                ZYPEZDItemValueSetter.setValue(curMsg.splyCoaProjCd, defNineSegDataBean.getCoaProjCd());
//                ZYPEZDItemValueSetter.setValue(curMsg.splyCoaExtnCd, defNineSegDataBean.getCoaExtnCd());
//
//                ZYPEZDItemValueSetter.setValue(glblMsg.vndCd_HG, glblMsg.vndCd);
//
//                fillChrgAccountText(curMsg);
//
//                if (defNineSegDataBean.getMsgIdList() != null //
//                        && !defNineSegDataBean.getMsgIdList().isEmpty()) {
//                    String errMsg = defNineSegDataBean.getMsgId(ZERO);
//                    if (ZYPCommonFunc.hasValue(errMsg)) {
//                        curMsg.xxScrItem130Txt_CH.setErrorInfo(1, errMsg);
//                    }
//                }
//            }
            // 2018/12/19 QC#29456 Add End
            // 2019/08/12 QC#52442 Del End
        }
        // 2018/12/19 QC#29456 Del Start
//        boolean ret=getMdseAccount(bizMsg, glblMsg, index);
//        if(ret) {
//            fillChrgAccountText(curMsg);
//            ZYPEZDItemValueSetter.setValue(glblMsg.vndCd_HG, glblMsg.vndCd);
//        }
//
//        return ret;
        // 2018/12/19 QC#29456 Del End
        // 2018/12/19 QC#29456 Add Start
        return true;
        // 2018/12/19 QC#29456 Add End
    }

    // 2018/12/19 QC#29456 Del Start
//    public static boolean getMdseAccount(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg, int index) {
//        
//        NPAL1500_ASMsg curMsg=glblMsg.A.no(index);
//        
//        // COA will be blank if PRCH_REQ_LINE_TP is not Expense.
//        if (!(ZYPCommonFunc.hasValue(curMsg.poLineTpCd_A1)
//                && (PO_LINE_TP.EXPENSE.equals(curMsg.poLineTpCd_A1.getValue())
//                || PO_LINE_TP.EXPENSE_WITH_RECEIPT.equals(curMsg.poLineTpCd_A1.getValue())))) {
//            curMsg.splyCoaCmpyCd.clear();
//            curMsg.splyCoaBrCd.clear();
//            curMsg.splyCoaCcCd.clear();
//            curMsg.splyCoaAcctCd.clear();
//            curMsg.splyCoaProdCd.clear();
//            curMsg.splyCoaChCd.clear();
//            curMsg.splyCoaAfflCd.clear();
//            curMsg.splyCoaProjCd.clear();
//            curMsg.splyCoaExtnCd.clear();
//            curMsg.xxScrItem130Txt_CH.clear();
//            ZYPEZDItemValueSetter.setValue(glblMsg.vndCd_HG, glblMsg.vndCd);
//            return true;
//        }
//
//        // Skip if MDSE is blank.
//        if(!ZYPCommonFunc.hasValue(curMsg.mdseCd_A1)) {
//            ZYPEZDItemValueSetter.setValue(glblMsg.vndCd_HG, glblMsg.vndCd);
//            return true;
//        }
//        
//        applyChrgAccount(curMsg, curMsg.xxScrItem130Txt_CH.getValue());
//        
//        MDSETMsg tMsg = new MDSETMsg();
//        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblMsg.glblCmpyCd.getValue());
//        ZYPEZDItemValueSetter.setValue(tMsg.ezCancelFlag, ZYPConstant.FLG_OFF_0);
//        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, curMsg.mdseCd_A1.getValue());
//
//        tMsg = (MDSETMsg) EZDTBLAccessor.findByKey(tMsg);
//        if(tMsg==null) {
//            //curMsg.splyCoaCmpyCd.clear();
//            //curMsg.splyCoaBrCd.clear();
//            //curMsg.splyCoaCcCd.clear();
//            curMsg.splyCoaAcctCd.clear();
//            curMsg.splyCoaProdCd.clear();
//            curMsg.splyCoaChCd.clear();
//            //curMsg.splyCoaAfflCd.clear();
//            curMsg.splyCoaProjCd.clear();
//            //curMsg.splyCoaExtnCd.clear();
//            curMsg.xxScrItem130Txt_CH.clear();
//            fillChrgAccountText(curMsg);
//            
//            curMsg.xxChkBox_A1.setErrorInfo(1, NPAM0076E, new String[] {"COA information of Charge Account" });
//            return false;
//            //return true;
//        }
//
//        // See QC#25019 for filling rules from MDSE.
//        ZYPEZDItemValueSetter.setValue(curMsg.splyCoaAcctCd, tMsg.cogsCoaAcctCd.getValue());
//        if (!ZYPCommonFunc.hasValue(tMsg.cogsCoaAcctCd)) {
//        } else if (tMsg.cogsCoaAcctCd.getValue().startsWith("5")) {
//            ZYPEZDItemValueSetter.setValue(curMsg.splyCoaProdCd, tMsg.coaProdCd.getValue());
//            ZYPEZDItemValueSetter.setValue(curMsg.splyCoaChCd, COA_CH.PRODUCT_ADMINI); // Channel
//            // ZYPEZDItemValueSetter.setValue(curMsg.splyCoaAfflCd, ); //Intercompany(manual)
//            ZYPEZDItemValueSetter.setValue(curMsg.splyCoaProjCd, tMsg.coaMdseTpCd.getValue()); // MDSE_TP
//            // ZYPEZDItemValueSetter.setValue(curMsg.splyCoaExtnCd, ); //Business Unit(manual)
//        } else if (tMsg.cogsCoaAcctCd.getValue().startsWith("6")) {
//            ZYPEZDItemValueSetter.setValue(curMsg.splyCoaProdCd, COA_PROD.ADMINISTRATION);
//            ZYPEZDItemValueSetter.setValue(curMsg.splyCoaChCd, COA_CH.CORPORATE_ADMINI);
//            // ZYPEZDItemValueSetter.setValue(curMsg.splyCoaAfflCd, ); //Intercompany(manual)
//            ZYPEZDItemValueSetter.setValue(curMsg.splyCoaProjCd, COA_PROJ.DEFAULT);
//            // ZYPEZDItemValueSetter.setValue(curMsg.coaExtnCd_A1, ); //Business Unit(manual)
//        }
//        
//        fillChrgAccountText(curMsg);
//        ZYPEZDItemValueSetter.setValue(glblMsg.vndCd_HG, glblMsg.vndCd);
//        return true;
//    }
    // 2018/12/19 QC#29456 Del End

    /**
     * fillAccountText Set xxScrItem130Txt_A1
     * @param asMsg
     */
    private static void fillChrgAccountText(NPAL1500_ASMsg asMsg) {
        StringBuilder sb = new StringBuilder();
        sb.append(asMsg.splyCoaCmpyCd.getValue());
        sb.append(PERIOD);
        sb.append(asMsg.splyCoaBrCd.getValue());
        sb.append(PERIOD);
        sb.append(asMsg.splyCoaCcCd.getValue());
        sb.append(PERIOD);
        sb.append(asMsg.splyCoaAcctCd.getValue());
        sb.append(PERIOD);
        sb.append(asMsg.splyCoaProdCd.getValue());
        sb.append(PERIOD);
        sb.append(asMsg.splyCoaChCd.getValue());
        sb.append(PERIOD);
        sb.append(asMsg.splyCoaAfflCd.getValue());
        sb.append(PERIOD);
        sb.append(asMsg.splyCoaProjCd.getValue());
        sb.append(PERIOD);
        sb.append(asMsg.splyCoaExtnCd.getValue());

        ZYPEZDItemValueSetter.setValue(asMsg.xxScrItem130Txt_CH, sb.toString());
    }

    /**
     * Apply to Charge Account fields from 9 Segment
     * @param asMsg
     * @param chrgAcctText
     */
    private static void applyChrgAccount(NPAL1500_ASMsg asMsg, String chrgAcctText) {
        if (!ZYPCommonFunc.hasValue(chrgAcctText)) {
            return;
        }
        String[] values = chrgAcctText.split(Pattern.quote("."));
        if (values == null || values.length <= 0) {
            return;
        }

        EZDSStringItem[] fields = new EZDSStringItem[] {asMsg.splyCoaCmpyCd, asMsg.splyCoaBrCd, asMsg.splyCoaCcCd, asMsg.splyCoaAcctCd, asMsg.splyCoaProdCd, asMsg.splyCoaChCd, asMsg.splyCoaAfflCd, asMsg.splyCoaProjCd, asMsg.splyCoaExtnCd };
        for (int n = 0; n < fields.length; ++n) {
            fields[n].clear();
        }
        for (int n = 0; n < values.length; ++n) {
            String val = values[n];
            if (ZYPCommonFunc.hasValue(val)) {
                ZYPEZDItemValueSetter.setValue(fields[n], val);
            }
        }
    }

    /**
     * copyFromSmsgOntoCmsg Copy data From Smsg Onto Cmsg
     * @param bizMsg NPAL1500CMsg
     * @param glblMsg NPAL1500SMsg
     */
    public static void copyFromSMsgOntoCmsg(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {
        // copy data from SMsg onto CMsg

        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, glblMsg.glblCmpyCd);
        // Header
        ZYPEZDItemValueSetter.setValue(bizMsg.poNum, glblMsg.poNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsPoTpCd, glblMsg.dsPoTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.poHdrStsDescTxt, glblMsg.poHdrStsDescTxt);
        ZYPEZDItemValueSetter.setValue(bizMsg.poApvlStsDescTxt, glblMsg.poApvlStsDescTxt);
        ZYPEZDItemValueSetter.setValue(bizMsg.poApvlDt, glblMsg.poApvlDt);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxOrdTs, glblMsg.xxOrdTs);
        ZYPEZDItemValueSetter.setValue(bizMsg.rqstRcvDt, glblMsg.rqstRcvDt);
        ZYPEZDItemValueSetter.setValue(bizMsg.rqstRcvTm, glblMsg.rqstRcvTm);
        // START 2023/02/14 S.Dong [QC#60966, ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.rqstShipDt, glblMsg.rqstShipDt);
        // END 2023/02/14 S.Dong [QC#60966, ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDtTm, glblMsg.xxDtTm);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTpCd_SL, glblMsg.xxTpCd_SL);
        ZYPEZDItemValueSetter.setValue(bizMsg.poOrdSrcDescTxt, glblMsg.poOrdSrcDescTxt);
        ZYPEZDItemValueSetter.setValue(bizMsg.trxRefNum, glblMsg.trxRefNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.poQlfyCd, glblMsg.poQlfyCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.fullPsnNm, glblMsg.fullPsnNm);
        ZYPEZDItemValueSetter.setValue(bizMsg.prchGrpCd, glblMsg.prchGrpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.poOrdCmntTxt, glblMsg.poOrdCmntTxt);
        ZYPEZDItemValueSetter.setValue(bizMsg.prntVndCd, glblMsg.prntVndCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.prntVndNm, glblMsg.prntVndNm);
        ZYPEZDItemValueSetter.setValue(bizMsg.vndCd, glblMsg.vndCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.vndCd_HG, glblMsg.vndCd_HG);
        ZYPEZDItemValueSetter.setValue(bizMsg.vndNm, glblMsg.vndNm);
        ZYPEZDItemValueSetter.setValue(bizMsg.srcRtlWhCd_SC, glblMsg.srcRtlWhCd_SC);
        ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhNm_SC, glblMsg.rtlWhNm_SC);
        ZYPEZDItemValueSetter.setValue(bizMsg.destRtlWhCd_DS, glblMsg.destRtlWhCd_DS);
        ZYPEZDItemValueSetter.setValue(bizMsg.destRtlSwhCd_DS, glblMsg.destRtlSwhCd_DS);
        ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhNm_DS, glblMsg.rtlWhNm_DS);
        ZYPEZDItemValueSetter.setValue(bizMsg.poStsCd_H, glblMsg.poStsCd_H);

        // Addl Hed
        // Ship To Location
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustCd, glblMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToLocNm, glblMsg.shipToLocNm_ST);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToAddlLocNm_ST, glblMsg.shipToAddlLocNm_ST);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_ST, glblMsg.xxAllLineAddr_ST);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToPostCd_ST, glblMsg.shipToPostCd_ST);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtyAddr_ST, glblMsg.shipToCtyAddr_ST);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCntyNm_ST, glblMsg.shipToCntyNm_ST);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToStCd_ST, glblMsg.shipToStCd_ST);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToProvNm_ST, glblMsg.shipToProvNm_ST);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtryCd_ST, glblMsg.shipToCtryCd_ST);
        ZYPEZDItemValueSetter.setValue(bizMsg.ctryDescTxt_ST, glblMsg.ctryDescTxt_ST);
        ZYPEZDItemValueSetter.setValue(bizMsg.ctacPsnNm, glblMsg.ctacPsnNm);

        // Bill To Customer
        ZYPEZDItemValueSetter.setValue(bizMsg.varCharConstVal_B1, glblMsg.varCharConstVal_B1);
        ZYPEZDItemValueSetter.setValue(bizMsg.varCharConstVal_B2, glblMsg.varCharConstVal_B2);
        ZYPEZDItemValueSetter.setValue(bizMsg.varCharConstVal_B3, glblMsg.varCharConstVal_B3);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_BT, glblMsg.xxAllLineAddr_BT);
        ZYPEZDItemValueSetter.setValue(bizMsg.varCharConstVal_B4, glblMsg.varCharConstVal_B4);
        ZYPEZDItemValueSetter.setValue(bizMsg.varCharConstVal_B5, glblMsg.varCharConstVal_B5);
        ZYPEZDItemValueSetter.setValue(bizMsg.varCharConstVal_B6, glblMsg.varCharConstVal_B6);
        ZYPEZDItemValueSetter.setValue(bizMsg.varCharConstVal_B7, glblMsg.varCharConstVal_B7);
        ZYPEZDItemValueSetter.setValue(bizMsg.varCharConstVal_B8, glblMsg.varCharConstVal_B8);
        ZYPEZDItemValueSetter.setValue(bizMsg.varCharConstVal_B9, glblMsg.varCharConstVal_B9);
        ZYPEZDItemValueSetter.setValue(bizMsg.ctryDescTxt_BT, glblMsg.ctryDescTxt_BT);

        // CSA Return Address
        ZYPEZDItemValueSetter.setValue(bizMsg.rtrnShipToRtlWhCd_RW, glblMsg.rtrnShipToRtlWhCd_RW);
        ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhNm_RW, glblMsg.rtlWhNm_RW);
        ZYPEZDItemValueSetter.setValue(bizMsg.addlLocNm_RW, glblMsg.addlLocNm_RW);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_RW, glblMsg.xxAllLineAddr_RW);
        ZYPEZDItemValueSetter.setValue(bizMsg.postCd_RW, glblMsg.postCd_RW);
        ZYPEZDItemValueSetter.setValue(bizMsg.ctyAddr_RW, glblMsg.ctyAddr_RW);
        ZYPEZDItemValueSetter.setValue(bizMsg.cntyNm_RW, glblMsg.cntyNm_RW);
        ZYPEZDItemValueSetter.setValue(bizMsg.stCd_RW, glblMsg.stCd_RW);
        ZYPEZDItemValueSetter.setValue(bizMsg.provNm_RW, glblMsg.provNm_RW);
        ZYPEZDItemValueSetter.setValue(bizMsg.ctryCd_RW, glblMsg.ctryCd_RW);
        ZYPEZDItemValueSetter.setValue(bizMsg.ctryDescTxt_RW, glblMsg.ctryDescTxt_RW);

        // Freight Information
        ZYPEZDItemValueSetter.setValue(bizMsg.frtCondCd, glblMsg.frtCondCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.shpgSvcLvlCd, glblMsg.shpgSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.carrCd, glblMsg.carrCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.carrNm, glblMsg.carrNm);
        ZYPEZDItemValueSetter.setValue(bizMsg.carrAcctNum, glblMsg.carrAcctNum);

        // Header Details
        ZYPEZDItemValueSetter.setValue(bizMsg.vndPmtTermDescTxt, glblMsg.vndPmtTermDescTxt);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDsMultMsgDplyTxt_SI, glblMsg.xxDsMultMsgDplyTxt_SI);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDsMultMsgDplyTxt_RN, glblMsg.xxDsMultMsgDplyTxt_RN);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDsMultMsgDplyTxt_SN, glblMsg.xxDsMultMsgDplyTxt_SN);
        ZYPEZDItemValueSetter.setValue(bizMsg.poMsgPk_SI, glblMsg.poMsgPk_SI);
        ZYPEZDItemValueSetter.setValue(bizMsg.poMsgPk_RN, glblMsg.poMsgPk_RN);
        ZYPEZDItemValueSetter.setValue(bizMsg.poMsgPk_SN, glblMsg.poMsgPk_SN);
        ZYPEZDItemValueSetter.setValue(bizMsg.poMsgSubmtPsnCd_SI, glblMsg.poMsgSubmtPsnCd_SI);
        ZYPEZDItemValueSetter.setValue(bizMsg.poMsgSubmtPsnCd_RN, glblMsg.poMsgSubmtPsnCd_RN);
        ZYPEZDItemValueSetter.setValue(bizMsg.poMsgSubmtPsnCd_SN, glblMsg.poMsgSubmtPsnCd_SN);

        //QC#19485
        ZYPEZDItemValueSetter.setValue(bizMsg.poSendFlg_HD, glblMsg.poSendFlg_HD);
        ZYPEZDItemValueSetter.setValue(bizMsg.vndIssOrdNum_HD, glblMsg.vndIssOrdNum_HD);
        ZYPEZDItemValueSetter.setValue(bizMsg.poSendTs_HD, glblMsg.poSendTs_HD);
        //QC#19485
        
        // Details
        ZYPEZDItemValueSetter.setValue(glblMsg.svcConfigMstrPk, glblMsg.svcConfigMstrPk);

        // Pagenation
        pagenation(bizMsg, glblMsg);

        // Pagenation
        pagenationAP(bizMsg, glblMsg);

        // Detail Footer
        ZYPEZDItemValueSetter.setValue(bizMsg.trsmtMethTpCd, glblMsg.trsmtMethTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.sendPoEmlAddr, glblMsg.sendPoEmlAddr);
        ZYPEZDItemValueSetter.setValue(bizMsg.sendPoFaxNum, glblMsg.sendPoFaxNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.rptDestId, glblMsg.rptDestId);

        // set Comment/Text Popup Data
        EZDMsg.copy(glblMsg.Q, null, bizMsg.Q, null);

        // set Hidden Item
        ZYPEZDItemValueSetter.setValue(bizMsg.xxSrchTrfFlg_HD, glblMsg.xxSrchTrfFlg_HD);
    }

    /**
     * copyFromCmsgOntoSmsg Copy data From CMsg Onto SMsg
     * @param glblMsg NPAL1500SMsg
     * @param bizMsg NPAL1500CMsg
     */
    public static void copyFromCmsgOntoSmsg(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {
        ZYPEZDItemValueSetter.setValue(glblMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        // Header
        ZYPEZDItemValueSetter.setValue(glblMsg.poNum, bizMsg.poNum);
        ZYPEZDItemValueSetter.setValue(glblMsg.dsPoTpCd, bizMsg.dsPoTpCd);
        ZYPEZDItemValueSetter.setValue(glblMsg.poHdrStsDescTxt, bizMsg.poHdrStsDescTxt);
        ZYPEZDItemValueSetter.setValue(glblMsg.poApvlStsCd, bizMsg.poApvlStsCd);
        ZYPEZDItemValueSetter.setValue(glblMsg.poApvlStsDescTxt, bizMsg.poApvlStsDescTxt);
        ZYPEZDItemValueSetter.setValue(glblMsg.poApvlDt, bizMsg.poApvlDt);
        ZYPEZDItemValueSetter.setValue(glblMsg.xxOrdTs, bizMsg.xxOrdTs);
        ZYPEZDItemValueSetter.setValue(glblMsg.rqstRcvDt, bizMsg.rqstRcvDt);
        ZYPEZDItemValueSetter.setValue(glblMsg.rqstRcvTm, bizMsg.rqstRcvTm);
        // START 2023/02/14 S.Dong [QC#60966, ADD]
        ZYPEZDItemValueSetter.setValue(glblMsg.rqstShipDt, bizMsg.rqstShipDt);
        // END 2023/02/14 S.Dong [QC#60966, ADD]
        ZYPEZDItemValueSetter.setValue(glblMsg.xxDtTm, bizMsg.xxDtTm);
        ZYPEZDItemValueSetter.setValue(glblMsg.xxTpCd_SL, bizMsg.xxTpCd_SL);
        ZYPEZDItemValueSetter.setValue(glblMsg.poOrdSrcDescTxt, bizMsg.poOrdSrcDescTxt);
        ZYPEZDItemValueSetter.setValue(glblMsg.trxRefNum, bizMsg.trxRefNum);
        ZYPEZDItemValueSetter.setValue(glblMsg.poQlfyCd, bizMsg.poQlfyCd);
        ZYPEZDItemValueSetter.setValue(glblMsg.fullPsnNm, bizMsg.fullPsnNm);
        ZYPEZDItemValueSetter.setValue(glblMsg.prchGrpCd, bizMsg.prchGrpCd);
        ZYPEZDItemValueSetter.setValue(glblMsg.poOrdCmntTxt, bizMsg.poOrdCmntTxt);
        ZYPEZDItemValueSetter.setValue(glblMsg.prntVndCd, bizMsg.prntVndCd);
        ZYPEZDItemValueSetter.setValue(glblMsg.prntVndNm, bizMsg.prntVndNm);
        ZYPEZDItemValueSetter.setValue(glblMsg.vndCd, bizMsg.vndCd);
        ZYPEZDItemValueSetter.setValue(glblMsg.vndCd_HG, bizMsg.vndCd_HG);
        ZYPEZDItemValueSetter.setValue(glblMsg.vndNm, bizMsg.vndNm);
        ZYPEZDItemValueSetter.setValue(glblMsg.srcRtlWhCd_SC, bizMsg.srcRtlWhCd_SC);
        ZYPEZDItemValueSetter.setValue(glblMsg.rtlWhNm_SC, bizMsg.rtlWhNm_SC);
        ZYPEZDItemValueSetter.setValue(glblMsg.destRtlWhCd_DS, bizMsg.destRtlWhCd_DS);
        ZYPEZDItemValueSetter.setValue(glblMsg.destRtlSwhCd_DS, bizMsg.destRtlSwhCd_DS);
        ZYPEZDItemValueSetter.setValue(glblMsg.rtlWhNm_DS, bizMsg.rtlWhNm_DS);
        ZYPEZDItemValueSetter.setValue(glblMsg.poStsCd_H, bizMsg.poStsCd_H);
        // START 2017/09/04 S.Katsuma [QC#20688,ADD]
        ZYPEZDItemValueSetter.setValue(glblMsg.poOrdSrcCd, bizMsg.poOrdSrcCd);
        // END 2017/09/04 S.Katsuma [QC#20688,ADD]

        // Addl Hed
        // Ship To Location
        ZYPEZDItemValueSetter.setValue(glblMsg.shipToCustCd, bizMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(glblMsg.shipToLocNm_ST, bizMsg.shipToLocNm);
        ZYPEZDItemValueSetter.setValue(glblMsg.shipToAddlLocNm_ST, bizMsg.shipToAddlLocNm_ST);
        ZYPEZDItemValueSetter.setValue(glblMsg.xxAllLineAddr_ST, bizMsg.xxAllLineAddr_ST);
        ZYPEZDItemValueSetter.setValue(glblMsg.shipToPostCd_ST, bizMsg.shipToPostCd_ST);
        ZYPEZDItemValueSetter.setValue(glblMsg.shipToCtyAddr_ST, bizMsg.shipToCtyAddr_ST);
        ZYPEZDItemValueSetter.setValue(glblMsg.shipToCntyNm_ST, bizMsg.shipToCntyNm_ST);
        ZYPEZDItemValueSetter.setValue(glblMsg.shipToStCd_ST, bizMsg.shipToStCd_ST);
        ZYPEZDItemValueSetter.setValue(glblMsg.shipToProvNm_ST, bizMsg.shipToProvNm_ST);
        ZYPEZDItemValueSetter.setValue(glblMsg.shipToCtryCd_ST, bizMsg.shipToCtryCd_ST);
        ZYPEZDItemValueSetter.setValue(glblMsg.ctryDescTxt_ST, bizMsg.ctryDescTxt_ST);
        ZYPEZDItemValueSetter.setValue(glblMsg.ctacPsnNm, bizMsg.ctacPsnNm);

        // Bill To Customer
        ZYPEZDItemValueSetter.setValue(glblMsg.varCharConstVal_B1, bizMsg.varCharConstVal_B1);
        ZYPEZDItemValueSetter.setValue(glblMsg.varCharConstVal_B2, bizMsg.varCharConstVal_B2);
        ZYPEZDItemValueSetter.setValue(glblMsg.varCharConstVal_B3, bizMsg.varCharConstVal_B3);
        ZYPEZDItemValueSetter.setValue(glblMsg.xxAllLineAddr_BT, bizMsg.xxAllLineAddr_BT);
        ZYPEZDItemValueSetter.setValue(glblMsg.varCharConstVal_B4, bizMsg.varCharConstVal_B4);
        ZYPEZDItemValueSetter.setValue(glblMsg.varCharConstVal_B5, bizMsg.varCharConstVal_B5);
        ZYPEZDItemValueSetter.setValue(glblMsg.varCharConstVal_B6, bizMsg.varCharConstVal_B6);
        ZYPEZDItemValueSetter.setValue(glblMsg.varCharConstVal_B7, bizMsg.varCharConstVal_B7);
        ZYPEZDItemValueSetter.setValue(glblMsg.varCharConstVal_B8, bizMsg.varCharConstVal_B8);
        ZYPEZDItemValueSetter.setValue(glblMsg.varCharConstVal_B9, bizMsg.varCharConstVal_B9);
        ZYPEZDItemValueSetter.setValue(glblMsg.ctryDescTxt_BT, bizMsg.ctryDescTxt_BT);

        // CSA Return Address
        ZYPEZDItemValueSetter.setValue(glblMsg.rtrnShipToRtlWhCd_RW, bizMsg.rtrnShipToRtlWhCd_RW);
        ZYPEZDItemValueSetter.setValue(glblMsg.rtlWhNm_RW, bizMsg.rtlWhNm_RW);
        ZYPEZDItemValueSetter.setValue(glblMsg.addlLocNm_RW, bizMsg.addlLocNm_RW);
        ZYPEZDItemValueSetter.setValue(glblMsg.xxAllLineAddr_RW, bizMsg.xxAllLineAddr_RW);
        ZYPEZDItemValueSetter.setValue(glblMsg.postCd_RW, bizMsg.postCd_RW);
        ZYPEZDItemValueSetter.setValue(glblMsg.ctyAddr_RW, bizMsg.ctyAddr_RW);
        ZYPEZDItemValueSetter.setValue(glblMsg.cntyNm_RW, bizMsg.cntyNm_RW);
        ZYPEZDItemValueSetter.setValue(glblMsg.stCd_RW, bizMsg.stCd_RW);
        ZYPEZDItemValueSetter.setValue(glblMsg.provNm_RW, bizMsg.provNm_RW);
        ZYPEZDItemValueSetter.setValue(glblMsg.ctryCd_RW, bizMsg.ctryCd_RW);
        ZYPEZDItemValueSetter.setValue(glblMsg.ctryDescTxt_RW, bizMsg.ctryDescTxt_RW);

        // Freight Information
        ZYPEZDItemValueSetter.setValue(glblMsg.frtCondCd, bizMsg.frtCondCd);
        ZYPEZDItemValueSetter.setValue(glblMsg.shpgSvcLvlCd, bizMsg.shpgSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(glblMsg.carrCd, bizMsg.carrCd);
        ZYPEZDItemValueSetter.setValue(glblMsg.carrNm, bizMsg.carrNm);
        ZYPEZDItemValueSetter.setValue(glblMsg.carrAcctNum, bizMsg.carrAcctNum);

        // Header Details
        // QC#28226 Add Start
        ZYPEZDItemValueSetter.setValue(glblMsg.vndPmtTermCd, bizMsg.vndPmtTermCd);
        // QC#28226 Add End
        ZYPEZDItemValueSetter.setValue(glblMsg.vndPmtTermDescTxt, bizMsg.vndPmtTermDescTxt);
        ZYPEZDItemValueSetter.setValue(glblMsg.xxDsMultMsgDplyTxt_SI, bizMsg.xxDsMultMsgDplyTxt_SI);
        ZYPEZDItemValueSetter.setValue(glblMsg.xxDsMultMsgDplyTxt_RN, bizMsg.xxDsMultMsgDplyTxt_RN);
        ZYPEZDItemValueSetter.setValue(glblMsg.xxDsMultMsgDplyTxt_SN, bizMsg.xxDsMultMsgDplyTxt_SN);
        ZYPEZDItemValueSetter.setValue(glblMsg.poMsgPk_SI, bizMsg.poMsgPk_SI);
        ZYPEZDItemValueSetter.setValue(glblMsg.poMsgPk_RN, bizMsg.poMsgPk_RN);
        ZYPEZDItemValueSetter.setValue(glblMsg.poMsgPk_SN, bizMsg.poMsgPk_SN);
        ZYPEZDItemValueSetter.setValue(glblMsg.poMsgSubmtPsnCd_SI, bizMsg.poMsgSubmtPsnCd_SI);
        ZYPEZDItemValueSetter.setValue(glblMsg.poMsgSubmtPsnCd_RN, bizMsg.poMsgSubmtPsnCd_RN);
        ZYPEZDItemValueSetter.setValue(glblMsg.poMsgSubmtPsnCd_SN, bizMsg.poMsgSubmtPsnCd_SN);
        ZYPEZDItemValueSetter.setValue(glblMsg.poDtlDiscPct, bizMsg.poDtlDiscPct);

        //QC#19485
        ZYPEZDItemValueSetter.setValue(glblMsg.poSendFlg_HD, bizMsg.poSendFlg_HD);
        ZYPEZDItemValueSetter.setValue(glblMsg.vndIssOrdNum_HD, bizMsg.vndIssOrdNum_HD);
        ZYPEZDItemValueSetter.setValue(glblMsg.poSendTs_HD, bizMsg.poSendTs_HD);
        //QC#19485
        // Details
        ZYPEZDItemValueSetter.setValue(glblMsg.svcConfigMstrPk, bizMsg.svcConfigMstrPk);

        int pageShowFromNum = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            EZDMsg.copy(bizMsg.A.no(i), null, glblMsg.A.no(pageShowFromNum + i), null);
        }

        // Detail Footer
        ZYPEZDItemValueSetter.setValue(glblMsg.trsmtMethTpCd, bizMsg.trsmtMethTpCd);
        ZYPEZDItemValueSetter.setValue(glblMsg.sendPoEmlAddr, bizMsg.sendPoEmlAddr);
        ZYPEZDItemValueSetter.setValue(glblMsg.sendPoFaxNum, bizMsg.sendPoFaxNum);
        ZYPEZDItemValueSetter.setValue(glblMsg.rptDestId, bizMsg.rptDestId);

        // set Comment/Text Popup Data
        EZDMsg.copy(bizMsg.Q, null, glblMsg.Q, null);

        // set Hidden Item
        ZYPEZDItemValueSetter.setValue(glblMsg.xxSrchTrfFlg_HD, bizMsg.xxSrchTrfFlg_HD);
    }

    /**
     * get PO_DTL.PO_ORD_DTL_LINE_NUM value incremented
     * @param maxPoOrdDtlLineNum String
     * @return String
     */
    public static String addDtlLineNum(String maxPoOrdDtlLineNum) {
        int i = Integer.parseInt(maxPoOrdDtlLineNum);
        i++;
        return ZYPCommonFunc.leftPad(String.valueOf(i), 3, "0");
    }

    /**
     * checkStateInMaster
     * @param glblCmpyCd String
     * @param stCd String
     * @return true:Exist false:not Exist
     */
    public static boolean checkStateInMaster(String glblCmpyCd, String stCd) {
        S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getStNm(glblCmpyCd, stCd.toUpperCase());

        if (ssmResult.isCodeNormal()) {
            // Exist Master
            return true;
        } else {
            // Not Exist
            return false;
        }
    }

    /**
     * pagenation
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     */
    public static void pagenation(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg) {
        int pagenationFromIndex = cMsg.xxPageShowFromNum.getValueInt() - 1;
        int i = pagenationFromIndex;
        for (; i < pagenationFromIndex + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFromIndex), null);
                // START 2017/12/06 [QC#14858, ADD]. QC#23616 Mod
                if ((PO_LINE_TP.EXPENSE.equals(cMsg.A.no(i - pagenationFromIndex).poLineTpCd_A1.getValue()) // 
                || PO_LINE_TP.EXPENSE_WITH_RECEIPT.equals(cMsg.A.no(i - pagenationFromIndex).poLineTpCd_A1.getValue()) //
                || PO_LINE_TP.ASSET.equals(cMsg.A.no(i - pagenationFromIndex).poLineTpCd_A1.getValue()) //
                        && ZYPCommonFunc.hasValue(cMsg.A.no(i - pagenationFromIndex).mdseCd_A1.getValue()))) {
                    // check TEXT ITEM
                    S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getAvailableTextItem(cMsg.glblCmpyCd.getValue(), cMsg.A.no(i - pagenationFromIndex).mdseCd_A1.getValue());
                    if (ssmResult.isCodeNormal()) {
                        String textItem = (String) ssmResult.getResultObject();
                        if (textItem != null && !textItem.isEmpty()) {
                            cMsg.A.no(i - pagenationFromIndex).mdseCd_A1.clear();
                            cMsg.A.no(i - pagenationFromIndex).xxCntDplyFlg_A2.setValue(ZYPConstant.FLG_OFF_N);
                        } else {
                            cMsg.A.no(i - pagenationFromIndex).xxCntDplyFlg_A2.setValue(ZYPConstant.FLG_ON_Y);
                        }
                    }
                }
                // QC#25024 Start
                if(PO_LINE_TP.ASSET.equals(cMsg.A.no(i - pagenationFromIndex).poLineTpCd_A1.getValue()) //
                        || PO_LINE_TP.GOODS.equals(cMsg.A.no(i - pagenationFromIndex).poLineTpCd_A1.getValue()) //
                        || PO_LINE_TP.ITT.equals(cMsg.A.no(i - pagenationFromIndex).poLineTpCd_A1.getValue())){
                    cMsg.A.no(i - pagenationFromIndex).xxScrItem130Txt_CH.clear();
                }
                // QC#25024 End
                // END 2017/12/06 [QC#14858, ADD]
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFromIndex);
        // set value to pageNation items
        cMsg.xxPageShowToNum.setValue(pagenationFromIndex + cMsg.A.getValidCount());
    }

    /**
     * getDefaultAccountInfo
     * @param bizMsg NPAL1500CMsg
     * @param glblMsg NPAL1500SMsg
     * @param glblCmpyCd String
     * @param index int
     * @return boolean
     */
    public static boolean getDefaultAccountInfo(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg, String glblCmpyCd, int index) {

        // 9seg Default
        DEF_DPLY_COA_INFOTMsg chTMsg = getDefDplyCoaInfo(BIZ_ID + "01" + glblMsg.A.no(index).poLineTpCd_A1.getValue(), glblCmpyCd);
        // QC#15817 Delete.
//        DEF_DPLY_COA_INFOTMsg acTMsg = getDefDplyCoaInfo(BIZ_ID + "02" + glblMsg.A.no(index).poLineTpCd_A1.getValue(), glblCmpyCd);
//        DEF_DPLY_COA_INFOTMsg vaTMsg = getDefDplyCoaInfo(BIZ_ID + "03" + glblMsg.A.no(index).poLineTpCd_A1.getValue(), glblCmpyCd);

        if (chTMsg == null) {
            bizMsg.setMessageInfo(NZZM0000E);
            glblMsg.A.no(index).xxChkBox_A1.setErrorInfo(1, NPAM0076E, new String[] {"Default COA information of Charge Account" });
            return false;
        } else {
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).coaEnblFlg_CH, ZYPConstant.FLG_OFF_N);
            if (!ZYPConstant.FLG_OFF_N.equals(chTMsg.coaCmpyDplyFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).coaEnblFlg_CH, ZYPConstant.FLG_ON_Y);
            }
            if (!ZYPConstant.FLG_OFF_N.equals(chTMsg.coaAfflDplyFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).coaEnblFlg_CH, ZYPConstant.FLG_ON_Y);
            }
            if (!ZYPConstant.FLG_OFF_N.equals(chTMsg.coaBrDplyFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).coaEnblFlg_CH, ZYPConstant.FLG_ON_Y);
            }
            if (!ZYPConstant.FLG_OFF_N.equals(chTMsg.coaCcDplyFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).coaEnblFlg_CH, ZYPConstant.FLG_ON_Y);
            }
            if (!ZYPConstant.FLG_OFF_N.equals(chTMsg.coaAcctDplyFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).coaEnblFlg_CH, ZYPConstant.FLG_ON_Y);
            }
            if (!ZYPConstant.FLG_OFF_N.equals(chTMsg.coaProdDplyFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).coaEnblFlg_CH, ZYPConstant.FLG_ON_Y);
            }
            if (!ZYPConstant.FLG_OFF_N.equals(chTMsg.coaChDplyFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).coaEnblFlg_CH, ZYPConstant.FLG_ON_Y);
            }
            if (!ZYPConstant.FLG_OFF_N.equals(chTMsg.coaProjDplyFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).coaEnblFlg_CH, ZYPConstant.FLG_ON_Y);
            }
            if (!ZYPConstant.FLG_OFF_N.equals(chTMsg.coaExtnDplyFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).coaEnblFlg_CH, ZYPConstant.FLG_ON_Y);
            }
        }

        return true;
    }

    /**
     * Get Default Display COA Information
     * @param appFuncId String
     * @param glblCmpyCd String
     * @return DEF_DPLY_COA_INFOTMsg
     */
    public static DEF_DPLY_COA_INFOTMsg getDefDplyCoaInfo(String appFuncId, String glblCmpyCd) {

        DEF_DPLY_COA_INFOTMsg tMsg = new DEF_DPLY_COA_INFOTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.appFuncId.setValue(appFuncId);

        return (DEF_DPLY_COA_INFOTMsg) S21FastTBLAccessor.findByKey(tMsg);
    }

    /**
     * checkManualSegmentElement
     * @param cMsg NPAL1500CMsg
     * @param sMsg NAL1500SMsg
     * @param glblCmpyCd String
     * @param accountType AccountType
     * @return boolean
     */
    public static boolean checkManualSegmentElement(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg, String glblCmpyCd, AccountType accountType) {

        int idx = cMsg.xxNum.getValueInt();
        // Length Check
        List<String> tokenList = null;
        // QC#15817 Delete.
//        if (accountType == AccountType.AC) {
//            tokenList = tokenListSplit(cMsg.A.no(idx).xxScrItem130Txt_AC.getValue(), glblCmpyCd);
//        } else if (accountType == AccountType.CH) {
//            tokenList = tokenListSplit(cMsg.A.no(idx).xxScrItem130Txt_CH.getValue(), glblCmpyCd);
//        } else if (accountType == AccountType.VA) {
//            tokenList = tokenListSplit(cMsg.A.no(idx).xxScrItem130Txt_VA.getValue(), glblCmpyCd);
//        }
        if (accountType == AccountType.CH) {
            tokenList = tokenListSplit(cMsg.A.no(idx).xxScrItem130Txt_CH.getValue(), glblCmpyCd);
        }
        if (0 == tokenList.size()) {
            return false;
        } else if (tokenList.size() < SEGMENT_TOKEN_LIST_SIZE) {
            cMsg.setMessageInfo(NPAM1193E);
            // QC#15817 Delete.
//            if (accountType == AccountType.AC) {
//                cMsg.A.no(idx).xxScrItem130Txt_AC.setErrorInfo(1, NPAM1193E, new String[] {MSG_PARAM_SEGMENT });
//            } else if (accountType == AccountType.CH) {
//                cMsg.A.no(idx).xxScrItem130Txt_CH.setErrorInfo(1, NPAM1193E, new String[] {MSG_PARAM_SEGMENT });
//            } else if (accountType == AccountType.VA) {
//                cMsg.A.no(idx).xxScrItem130Txt_VA.setErrorInfo(1, NPAM1193E, new String[] {MSG_PARAM_SEGMENT });
//            }
            if (accountType == AccountType.CH) {
                cMsg.A.no(idx).xxScrItem130Txt_CH.setErrorInfo(1, NPAM1193E, new String[] {MSG_PARAM_SEGMENT });
            }
            return false;
        }

        // window PopUp
        tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD), SEGMENT_ELEMENT_LENGTH_CMPY));
        tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD), SEGMENT_ELEMENT_LENGTH_EXTN));
        tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD), SEGMENT_ELEMENT_LENGTH_CC));
        tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD), SEGMENT_ELEMENT_LENGTH_ACCT));
        tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD), SEGMENT_ELEMENT_LENGTH_PROJ));
        tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD), SEGMENT_ELEMENT_LENGTH_PROD));
        tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD), SEGMENT_ELEMENT_LENGTH_AFFL));
        tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD), SEGMENT_ELEMENT_LENGTH_CH));
        tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD), SEGMENT_ELEMENT_LENGTH_BR));

        // PopUp Param set
        ZYPEZDItemValueSetter.setValue(cMsg.xxPopPrm_P1, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPopPrm_P2, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPopPrm_P3, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPopPrm_P4, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPopPrm_P5, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPopPrm_P6, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPopPrm_P7, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPopPrm_P8, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPopPrm_P9, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD));

        // DB COLUMN SET
        // QC#15817 Mod.
//        if (accountType == AccountType.AC) {
//            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).coaCmpyCd_AC, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD));
//            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).coaAfflCd_AC, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD));
//            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).coaBrCd_AC, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD));
//            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).coaCcCd_AC, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD));
//            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).coaAcctCd_AC, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD));
//            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).coaProdCd_AC, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD));
//            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).coaChCd_AC, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD));
//            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).coaProjCd_AC, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD));
//            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).coaExtnCd_AC, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD));
//        } else if (accountType == AccountType.CH) {
//            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).splyCoaCmpyCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD));
//            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).splyCoaAfflCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD));
//            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).splyCoaBrCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD));
//            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).splyCoaCcCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD));
//            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).splyCoaAcctCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD));
//            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).splyCoaProdCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD));
//            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).splyCoaChCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD));
//            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).splyCoaProjCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD));
//            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).splyCoaExtnCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD));
//        } else if (accountType == AccountType.VA) {
//            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).coaCmpyCd_VA, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD));
//            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).coaAfflCd_VA, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD));
//            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).coaBrCd_VA, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD));
//            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).coaCcCd_VA, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD));
//            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).coaAcctCd_VA, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD));
//            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).coaProdCd_VA, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD));
//            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).coaChCd_VA, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD));
//            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).coaProjCd_VA, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD));
//            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).coaExtnCd_VA, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD));
//        }
        if (accountType == AccountType.CH) {
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).splyCoaCmpyCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).splyCoaAfflCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD));
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).splyCoaBrCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD));
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).splyCoaCcCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD));
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).splyCoaAcctCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD));
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).splyCoaProdCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD));
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).splyCoaChCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD));
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).splyCoaProjCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD));
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).splyCoaExtnCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD));
        }

        return true;
    }

    /**
     * checkManualSegmentElementForSMsg
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     * @param glblCmpyCd String
     * @param bizAppId String
     * @param rowIndex int
     * @return boolean
     */
    public static boolean checkManualSegmentElementForSMsg(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg, String glblCmpyCd, String bizAppId, int rowIndex) {

//        List<EZDSStringItem> checkTarget = Arrays.asList(sMsg.A.no(rowIndex).xxScrItem130Txt_AC, sMsg.A.no(rowIndex).xxScrItem130Txt_CH, sMsg.A.no(rowIndex).xxScrItem130Txt_VA);
        List<EZDSStringItem> checkTarget = Arrays.asList(sMsg.A.no(rowIndex).xxScrItem130Txt_CH);
        for (EZDSStringItem target : checkTarget) {
            
            //QC#26474 ADD Start
            if(PO_LINE_TP.EXPENSE.equals(sMsg.A.no(rowIndex).poLineTpCd_A1.getValue()) || PO_LINE_TP.EXPENSE_WITH_RECEIPT.equals(sMsg.A.no(rowIndex).poLineTpCd_A1.getValue())){
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaEnblFlg_CH, ZYPConstant.FLG_ON_Y);
            }
            //QC#26474 ADD End
            //QC#29064 Add Start
            else {
                sMsg.A.no(rowIndex).xxScrItem130Txt_CH.clear();
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaEnblFlg_CH, ZYPConstant.FLG_OFF_N);
            }
            //QC#29064 Add End

            if (ZYPCommonFunc.hasValue(target)) {
                // QC#15817 Mod.
//                if ((target.getValue().equals(sMsg.A.no(rowIndex).xxScrItem130Txt_AC.getValue())) && (!ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(rowIndex).coaEnblFlg_AC.getValue()))) {
//                    continue;
//                }

                if ((target.getValue().equals(sMsg.A.no(rowIndex).xxScrItem130Txt_CH.getValue())) && (!ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(rowIndex).coaEnblFlg_CH.getValue()))) {
                    List<String> tokenList = tokenListSplit(target.getValue(), glblCmpyCd);
                    sMsg.A.no(rowIndex).splyCoaCmpyCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD));
                    sMsg.A.no(rowIndex).splyCoaAfflCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD));
                    sMsg.A.no(rowIndex).splyCoaBrCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD));
                    sMsg.A.no(rowIndex).splyCoaCcCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD));
                    sMsg.A.no(rowIndex).splyCoaAcctCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD));
                    sMsg.A.no(rowIndex).splyCoaProdCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD));
                    sMsg.A.no(rowIndex).splyCoaChCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD));
                    sMsg.A.no(rowIndex).splyCoaProjCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD));
                    sMsg.A.no(rowIndex).splyCoaExtnCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD));
                    continue;
                }
//                if ((target.getValue().equals(sMsg.A.no(rowIndex).xxScrItem130Txt_VA.getValue())) && (!ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(rowIndex).coaEnblFlg_VA.getValue()))) {
//                    continue;
//                }
            } else {
                continue;
            }

            // Length Check
            List<String> tokenList = tokenListSplit(target.getValue(), glblCmpyCd);
            if (tokenList == null || tokenList.size() != SEGMENT_TOKEN_LIST_SIZE) {
                target.setErrorInfo(1, NPAM1193E, new String[] {MSG_PARAM_SEGMENT });
                return false;
            }

            // coaCmpyCd check
            if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD), SEGMENT_ELEMENT_LENGTH_CMPY)) {
                target.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_CMPY });
                return false;
            }

            // coaExtnCd check
            if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD), SEGMENT_ELEMENT_LENGTH_EXTN)) {
                target.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_EXTN });
                return false;
            }

            // coaCcCd check
            if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD), SEGMENT_ELEMENT_LENGTH_CC)) {
                target.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_CC });
                return false;
            }

            // coaAcctCd check
            if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD), SEGMENT_ELEMENT_LENGTH_ACCT)) {
                target.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_ACCT });
                return false;
            }

            // coaProjCd check
            if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD), SEGMENT_ELEMENT_LENGTH_PROJ)) {
                target.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_PROJ });
                return false;
            }

            // coaProdCd check
            if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD), SEGMENT_ELEMENT_LENGTH_PROD)) {
                target.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_PROD });
                return false;
            }

            // coaAfflCd check
            if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD), SEGMENT_ELEMENT_LENGTH_AFFL)) {
                target.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_AFFL });
                return false;
            }

            // coaChCd check
            if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD), SEGMENT_ELEMENT_LENGTH_CH)) {
                target.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_CH });
                return false;
            }

            // coaBrCd check
            if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD), SEGMENT_ELEMENT_LENGTH_BR)) {
                target.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_BR });
                return false;
            }

            // GL Code Combination Check API NFZC102001
            NFZC102001 afzc102001 = new NFZC102001();
            NFZC102001PMsg paramMsg = new NFZC102001PMsg();

            paramMsg.glblCmpyCd.setValue(glblCmpyCd);
            paramMsg.xxMstChkFlg.setValue(ZYPConstant.FLG_ON_Y);
            paramMsg.xxGlCmbnChkFlg.setValue(ZYPConstant.FLG_ON_Y);
            paramMsg.xxArcsApiChkFlg.setValue("");
            paramMsg.coaCmpyCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD));
            paramMsg.coaAfflCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD));
            paramMsg.coaBrCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD));
            paramMsg.coaCcCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD));
            paramMsg.coaAcctCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD));
            paramMsg.coaProdCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD));
            paramMsg.coaChCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD));
            paramMsg.coaProjCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD));
            paramMsg.coaExtnCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD));
            //QC#14858 MOD START            
//            if (target == sMsg.A.no(rowIndex).xxScrItem130Txt_AC) {
//                paramMsg.resrcObjNm.setValue(bizAppId + "02" + sMsg.A.no(rowIndex).poLineTpCd_A1.getValue());
//            } else if (target == sMsg.A.no(rowIndex).xxScrItem130Txt_CH) {
//                paramMsg.resrcObjNm.setValue(bizAppId + "01" + sMsg.A.no(rowIndex).poLineTpCd_A1.getValue());
//            } else if (target == sMsg.A.no(rowIndex).xxScrItem130Txt_VA) {
//                paramMsg.resrcObjNm.setValue(bizAppId + "03" + sMsg.A.no(rowIndex).poLineTpCd_A1.getValue());
//            }
            if (target == sMsg.A.no(rowIndex).xxScrItem130Txt_CH 
                    && PO_LINE_TP.EXPENSE.equals(sMsg.A.no(rowIndex).poLineTpCd_A1.getValue())) {
                paramMsg.resrcObjNm.setValue(bizAppId + sMsg.A.no(rowIndex).poLineTpCd_A1.getValue());
            } else if(target == sMsg.A.no(rowIndex).xxScrItem130Txt_CH 
                    && PO_LINE_TP.EXPENSE_WITH_RECEIPT.equals(sMsg.A.no(rowIndex).poLineTpCd_A1.getValue())) {
                paramMsg.resrcObjNm.setValue(bizAppId + sMsg.A.no(rowIndex).poLineTpCd_A1.getValue());
            }
            //QC#14858 MOD START
            afzc102001.execute(paramMsg, ONBATCH_TYPE.ONLINE);

            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(paramMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();

                if (msgPrms != null && msgPrms.length > 0) {
                    if (msgPrms[0].equals(DB_COLUMN_COA_CMPY_CD)) {
                        target.setErrorInfo(1, msgId, new String[] {MSG_PARAM_CMPY });
                    }
                    if (msgPrms[0].equals(DB_COLUMN_COA_BR_CD)) {
                        target.setErrorInfo(1, msgId, new String[] {MSG_PARAM_BR });
                    }
                    if (msgPrms[0].equals(DB_COLUMN_COA_CC_CD)) {
                        target.setErrorInfo(1, msgId, new String[] {MSG_PARAM_CC });
                    }
                    if (msgPrms[0].equals(DB_COLUMN_COA_ACCT_CD)) {
                        target.setErrorInfo(1, msgId, new String[] {MSG_PARAM_ACCT });
                    }
                    if (msgPrms[0].equals(DB_COLUMN_COA_PROD_CD)) {
                        target.setErrorInfo(1, msgId, new String[] {MSG_PARAM_PROD });
                    }
                    if (msgPrms[0].equals(DB_COLUMN_COA_CH_CD)) {
                        target.setErrorInfo(1, msgId, new String[] {MSG_PARAM_CH });
                    }
                    if (msgPrms[0].equals(DB_COLUMN_COA_AFFL_CD)) {
                        target.setErrorInfo(1, msgId, new String[] {MSG_PARAM_AFFL });
                    }
                    if (msgPrms[0].equals(DB_COLUMN_COA_PROJ_CD)) {
                        target.setErrorInfo(1, msgId, new String[] {MSG_PARAM_PROJ });
                    }
                    if (msgPrms[0].equals(DB_COLUMN_COA_EXTN_CD)) {
                        target.setErrorInfo(1, msgId, new String[] {MSG_PARAM_EXTN });
                    } else {
                        target.setErrorInfo(1, msgId, new String[] {msgPrms[0] });
                    }
                } else {
                    // QC#30768
                    target.setErrorInfo(1, msgId, null);
                    cMsg.setMessageInfo(msgId);
                }

                return false;
            }
// TODO : remove this comment before commit
            
            // DB COLUMN SET
            // QC#15817 Mod.
//            if (target == sMsg.A.no(rowIndex).xxScrItem130Txt_AC) {
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaCmpyCd_AC, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD));
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaAfflCd_AC, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD));
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaBrCd_AC, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD));
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaCcCd_AC, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD));
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaAcctCd_AC, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD));
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaProdCd_AC, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD));
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaChCd_AC, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD));
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaProjCd_AC, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD));
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaExtnCd_AC, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD));
//            } else if (target == sMsg.A.no(rowIndex).xxScrItem130Txt_CH) {
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).splyCoaCmpyCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD));
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).splyCoaAfflCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD));
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).splyCoaBrCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD));
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).splyCoaCcCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD));
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).splyCoaAcctCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD));
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).splyCoaProdCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD));
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).splyCoaChCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD));
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).splyCoaProjCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD));
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).splyCoaExtnCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD));
//            } else if (target == sMsg.A.no(rowIndex).xxScrItem130Txt_VA) {
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaCmpyCd_VA, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD));
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaAfflCd_VA, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD));
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaBrCd_VA, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD));
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaCcCd_VA, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD));
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaAcctCd_VA, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD));
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaProdCd_VA, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD));
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaChCd_VA, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD));
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaProjCd_VA, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD));
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaExtnCd_VA, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD));
//            }
            if (target == sMsg.A.no(rowIndex).xxScrItem130Txt_CH) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).splyCoaCmpyCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).splyCoaAfflCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).splyCoaBrCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).splyCoaCcCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).splyCoaAcctCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).splyCoaProdCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).splyCoaChCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).splyCoaProjCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).splyCoaExtnCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD));
            }
        }
        return true;
    }

    private static String splitSegmentElement(String element, int len) {
        String returnString = element;
        if (!hasValue(element)) {
            return null;
        }
        if (element.length() > len) {
            returnString = element.substring(0, len);
        }
        return returnString;
    }

    /**
     * validateSegmentElement
     * @param element String
     * @param len int
     * @return boolean
     */
    private static boolean validateSegmentElement(String element, int len) {
        if (!hasValue(element)) {
            return true;
        }
        if (element.length() > len) {
            return false;
        }
        return true;
    }

    /**
     * tokenListSplit
     * @param token String
     * @param glblCmpyCd String
     * @return tokenList List<String>
     */
    private static List<String> tokenListSplit(String token, String glblCmpyCd) {
        String delimiter = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_KEY_DELIMITER, glblCmpyCd);

        if (!hasValue(token)) {
            return new ArrayList<String>();
        }
        String[] list = token.split(Pattern.quote(delimiter), -1);
        List<String> tokenList = new ArrayList<String>();
        for (String val : list) {
            tokenList.add(val);
        }

        return tokenList;
    }

    /**
     * check has array of EZDMsgArray.
     * @param msgArray EZDMsgArray
     * @return true/has array, false/not has array.
     */
    public static boolean hasValidCount(EZDMsgArray msgArray) {
        return msgArray != null && msgArray.getValidCount() > 0;
    }

    /**
     * Set PRNT_CMPY_SET_MDSE_FLG
     * @param bizMsg NPAL1500CMsg
     * @param glblMsg NPAL1500SMsg
     * @param index index of SMsg Line
     * @return boolean true:Success false:Failure
     */
    @SuppressWarnings("unchecked")
    public static boolean setPrntCmpySetMdseFlg(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg, int index) {

        Map<String, Object> resultMap = null;

        S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getDeriveItemInfo(bizMsg, glblMsg.A.no(index).mdseCd_A1.getValue());

        if (ssmResult.isCodeNormal()) {
            resultMap = (Map<String, Object>) ssmResult.getResultObject();
            if (resultMap != null) {
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).prntCmpySetMdseFlg_HD, (String) resultMap.get("PRNT_CMPY_SET_MDSE_FLG"));
            }
        }

        if (resultMap == null) {
            glblMsg.A.no(index).mdseCd_A1.setErrorInfo(1, NPAM0076E, new String[] {glblMsg.A.no(index).mdseCd_A1.getValue() });
            bizMsg.setMessageInfo(NPAM0076E, new String[] {"Item#" });
            return false;
        }
        return true;
    }
    public static boolean setMdseInfo(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg, int indexS, boolean hasErr, boolean submitFlg) {
        boolean result = hasErr;
        if (ZYPConstant.FLG_ON_Y.equals(glblMsg.A.no(indexS).prntCmpySetMdseFlg_HD.getValue())) {

            // Set Parent Item
            if (setDeriveItemInfo(bizMsg, glblMsg, indexS, submitFlg)) {

                // Set Component Item(s)
                if (!deriveSetComponent(bizMsg, glblMsg, indexS)) {

                    result = true;
                }

            } else {

                result = true;
            }

        } else {

            // Other
            if (!setDeriveItemInfo(bizMsg, glblMsg, indexS, submitFlg)) {

                result = true;
            }
        }
        return result;
    }
    /**
     * set Derive Item Info
     * @param glblMsg NPAL1500SMsg
     * @param bizMsg NPAL1500CMsg
     * @return boolean
     */
    private static boolean setDeriveItemInfo(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg, int index, boolean submitFlg) {

        if (!NPAL1500CommonLogic.checkMdseInfo(bizMsg, glblMsg, index)) {

            return false;
        }

        // Get Primary Vendor from ASL
        NPZC113001PMsg npzc113001PMsg = NPAL1500CommonLogic.execGetPrimVndFromAsl(bizMsg, glblMsg, index);

        if (npzc113001PMsg == null) {

            return false;
        }

        // set Info
        if (ZYPCommonFunc.hasValue(npzc113001PMsg.splyItemNum)) {
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).aslMdseCd_A1, npzc113001PMsg.splyItemNum);
        } else {
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).aslMdseCd_A1, npzc113001PMsg.mdseCd);
        }
        
        // QC#21358 Add Start
        if (!ZYPCommonFunc.hasValue(glblMsg.A.no(index).aslUnitPrcAmt_A1)) {
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).aslUnitPrcAmt_A1, npzc113001PMsg.unitPrcAmt);
        }
        // QC#21358 Add End

        if (!ZYPCommonFunc.hasValue(glblMsg.A.no(index).entDealNetUnitPrcAmt_A1)) {
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).entDealNetUnitPrcAmt_A1, npzc113001PMsg.unitPrcAmt);
        }

        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).poDispUomCd_A1, npzc113001PMsg.vndUomCd);

        // Get PKG_UOM_CLS_DESC_TXT
        if (ZYPCommonFunc.hasValue(npzc113001PMsg.vndUomCd)) {
            S21SsmEZDResult result = NPAL1500Query.getInstance()//
                    .getPkgUomClsDescTxt(glblMsg.glblCmpyCd.getValue(), npzc113001PMsg.vndUomCd.getValue());

            if (result.isCodeNormal()) {
                String pkgUomClsDescTxt = (String) result.getResultObject();
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).pkgUomClsDescTxt_A1, pkgUomClsDescTxt);
            }
        }

        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).aslDtlPk_HD, npzc113001PMsg.aslDtlPk);
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).aslUnitPrcAmt_HD, npzc113001PMsg.unitPrcAmt);

        // QC#21170 Start
        if (ZYPCommonFunc.hasValue(npzc113001PMsg.vndLtDaysNum)) {
            setRqstRcvDt(bizMsg, glblMsg, npzc113001PMsg.vndLtDaysNum.getValueInt(), index);
        } else {
            setRqstRcvDt(bizMsg, glblMsg, 0, index);
        }
        // QC#21170 End
        // START 2023/02/14 S.Dong [QC#60966, ADD]
        setRqstShipDtForGetMdse(glblMsg, npzc113001PMsg, index);
        // END 2023/02/14 S.Dong [QC#60966, ADD]
        // Call Change Vendor UOM API
        NPZC129001PMsg npzc129001PMsg;
        if (submitFlg) {
            npzc129001PMsg = NPAL1500CommonLogic.execChgVndUom(bizMsg, glblMsg, index, glblMsg.A.no(index).poDispQty_A1.getValue());
        } else {
            npzc129001PMsg = NPAL1500CommonLogic.execChgVndUom(bizMsg, glblMsg, index, npzc113001PMsg.vndMinOrdQty.getValue());
        }

        if (npzc129001PMsg == null) {

        	// QC#19693 Add.
        	String noAslCheckPoTp = "";
            noAslCheckPoTp = ZYPCodeDataUtil.getVarCharConstValue("NO_ASL_CHECK_PO_TP", bizMsg.glblCmpyCd.getValue());
            if (ZYPCommonFunc.hasValue(noAslCheckPoTp)) {
                String[] array = noAslCheckPoTp.split(",");
                for (int i = 0; i < array.length; i++) {
                    String poTp = array[i];
                    if (!poTp.equals(bizMsg.dsPoTpCd.getValue())) {
                    	glblMsg.A.no(index).entDealNetUnitPrcAmt_A1.clear();
                    	glblMsg.A.no(index).aslMdseCd_A1.clear();
                    	glblMsg.A.no(index).poDispQty_A1.clear();
                    }
                }
            }

            return false;
        }

        // set Info
        if (!ZYPCommonFunc.hasValue(glblMsg.A.no(index).poDispQty_A1) || (glblMsg.A.no(index).poDispQty_A1.getValueInt() < npzc129001PMsg.poDispQty_O1.getValueInt())) {
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).poDispQty_A1, npzc129001PMsg.poDispQty_O1);
        }

        // Quantity is scale zero.
        BigDecimal qty = glblMsg.A.no(index).poDispQty_A1.getValue().setScale(0);
        BigDecimal extTotal = npzc113001PMsg.unitPrcAmt.getValue().multiply(qty);
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).entPoDtlDealNetAmt_A1, extTotal);

        return true;
    }
    /**
     * Derive_SetComponent
     * @param bizMsg NPAL1500CMsg
     * @param glblMsg NPAL1500SMsg
     * @param index index of SMsg Line
     * @return boolean true:Success false:Failure
     */
    @SuppressWarnings("unchecked")
    private static boolean deriveSetComponent(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg, int index) {

        List<Map<String, Object>> resultListComp = null;

        // child item delete
        String lineNum = glblMsg.A.no(index).dispPoDtlLineNum_A1.getValue();
        String sameNum = lineNum.substring(0, lineNum.indexOf(".") + 1);
        List<Integer> delList = new ArrayList<Integer>();
        for (int i = index + 1; i < glblMsg.A.getValidCount(); i++) {
            if (glblMsg.A.no(i).dispPoDtlLineNum_A1.getValue().startsWith(sameNum)) {
                // QC#16108 Mod.
                delList.add(i);
            }
        }
        ZYPTableUtil.deleteRows(glblMsg.A, delList);

        // Get Components
        S21SsmEZDResult ssmResultForComp = NPAL1500Query.getInstance().getComponent(glblMsg.glblCmpyCd.getValue(), glblMsg.A.no(index).mdseCd_A1.getValue());
        if (ssmResultForComp.isCodeNormal()) {
            resultListComp = (List<Map<String, Object>>) ssmResultForComp.getResultObject();

            for (int i = 0; i < resultListComp.size(); i++) {

                // add child component Default Info
                addNewLine(bizMsg, glblMsg);

                int curIndex = glblMsg.A.getValidCount() - 1;
                // set Item Info
                String prntLinNum = glblMsg.A.no(index).poOrdDtlLineNum_A1.getValue();
                String prntDispLineNum = glblMsg.A.no(index).dispPoDtlLineNum_A1.getValue();
                prntDispLineNum = prntDispLineNum.substring(0, prntDispLineNum.indexOf(PERIOD));
                int subNum = i + 1;
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).dispPoDtlLineNum_A1, prntDispLineNum.replaceFirst("^0+", "") + PERIOD + subNum);
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).setPoOrdDtlLineNum, prntLinNum);
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).poLineTpCd_A1, glblMsg.A.no(index).poLineTpCd_A1);

                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).mdseCd_A1, (String) resultListComp.get(i).get("CHILD_MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).aslMdseCd_A1, (String) resultListComp.get(i).get("CHILD_MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).mdseDescShortTxt_A1, (String) resultListComp.get(i).get("MDSE_DESC_SHORT_TXT"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).entDealNetUnitPrcAmt_A1, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).poQty_HD, (BigDecimal) resultListComp.get(i).get("CHILD_MDSE_QTY"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).rcvSerTakeFlg_IB, (String) resultListComp.get(i).get("RCV_SER_TAKE_FLG"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).instlBaseCtrlFlg_IB, (String) resultListComp.get(i).get("INSTL_BASE_CTRL_FLG"));

                BigDecimal chldQty = glblMsg.A.no(curIndex).poQty_HD.getValue();
                BigDecimal prntQty = glblMsg.A.no(index).poDispQty_A1.getValue();
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).poDispQty_A1, chldQty.multiply(prntQty));

                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).poDispUomCd_A1, VND_UOM.EACH);

                glblMsg.A.no(index).aslDtlPk_HD.clear();
                glblMsg.A.no(index).aslUnitPrcAmt_HD.clear();
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).thisMthFobCostAmt_HD, BigDecimal.ZERO);

                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).rqstRcvDt_A1, glblMsg.A.no(index).rqstRcvDt_A1);
                // START 2023/02/14 S.Dong [QC#60966, ADD]
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).rqstShipDt_A1, glblMsg.A.no(index).rqstShipDt_A1);
                // END 2023/02/14 S.Dong [QC#60966, ADD]
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).destRtlSwhCd_A1, glblMsg.A.no(index).destRtlSwhCd_A1);

                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).poLineStsCd_A1, glblMsg.A.no(index).poLineStsCd_A1);
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).poLineStsDescTxt_A1, glblMsg.A.no(index).poLineStsDescTxt_A1);

                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).entPoDtlDealNetAmt_A1, BigDecimal.ZERO);

                glblMsg.A.no(curIndex).poRcvQty_A1.clear();
                glblMsg.A.no(curIndex).poCancQty_A1.clear();
                glblMsg.A.no(curIndex).poInvQty_A1.clear();
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).vndInvtyLocCd_A1, glblMsg.A.no(index).vndInvtyLocCd_A1);
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).srcRtlSwhCd_A1, glblMsg.A.no(index).srcRtlSwhCd_A1);
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).stkStsCd_A1, glblMsg.A.no(index).stkStsCd_A1);
                glblMsg.A.no(curIndex).serNumListTxt_A1.clear();
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).svcConfigMstrPk_A1, glblMsg.A.no(index).svcConfigMstrPk_A1);
                glblMsg.A.no(curIndex).poOrdDtlCmntTxt_A1.clear();
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).prchReqNum_A1, glblMsg.A.no(index).prchReqNum_A1);
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).prchReqLineNum_A1, glblMsg.A.no(index).prchReqLineNum_A1);
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).poMdseCmpsnTpCd_HD, PO_MDSE_CMPSN_TP.CHILD);
                // add start 2022/05/16 QC#57934
                glblMsg.A.no(curIndex).poRcvQty_WO.clear();
                glblMsg.A.no(curIndex).poInvQty_WO.clear();
                // add end 2022/05/16 QC#57934

                // 2018/12/19 QC#29456 Del Start
//                NPAL1500CommonLogic.getAccountInfo(bizMsg, glblMsg, curIndex, false);
                // 2018/12/19 QC#29456 Del End
            }
        }

        if (resultListComp == null) {
            glblMsg.A.no(index).mdseCd_A1.setErrorInfo(1, NPAM0076E, new String[] {"Set Component Item" });
            bizMsg.setMessageInfo(NPAM0076E, new String[] {"Set Component Item" });
            return false;
        }
        return true;
    }
    /**
     * add New Line (for Screen)
     * @param glblMsg NPAL1500SMsg
     * @param bizMsg NPAL1500CMsg
     */
    public static void addNewLine(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        PO_DTLTMsg podTMsg = new PO_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(podTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(podTMsg.poOrdNum, bizMsg.poNum);

        podTMsg = (PO_DTLTMsg) EZDTBLAccessor.findByKey(podTMsg);
        String maxPoOrdDtlLineNum = FIRST_DTL_LINE_NUM;
        String poDtlLineNum = null;
        if (podTMsg == null) {
            if (glblMsg.A.getValidCount() > 0) {
                poDtlLineNum = glblMsg.A.no(glblMsg.A.getValidCount() - 1).poOrdDtlLineNum_A1.getValue();
                if (ZYPCommonFunc.hasValue(poDtlLineNum)) {
                    maxPoOrdDtlLineNum = NPAL1500CommonLogic.addDtlLineNum(poDtlLineNum);
                } else {
                    maxPoOrdDtlLineNum = NPAL1500CommonLogic.addDtlLineNum(maxPoOrdDtlLineNum);
                }
            }
        } else {
            maxPoOrdDtlLineNum = NPAL1500CommonLogic.addDtlLineNum(getMaxPoDtlLineNum(bizMsg.glblCmpyCd.getValue(), podTMsg.poOrdNum.getValue()));
        }

        // Get Max PO Disp Line#
        String maxDispPoDtlLineNum = Integer.parseInt(maxPoOrdDtlLineNum) + PERIOD + ZERO;

        if (glblMsg.A.getValidCount() > 0) {
            String tmpDispPoDtlLineNum = glblMsg.A.no(glblMsg.A.getValidCount() - 1).dispPoDtlLineNum_A1.getValue();
            if (ZYPCommonFunc.hasValue(tmpDispPoDtlLineNum)) {
                int dispLineNum = Integer.parseInt(tmpDispPoDtlLineNum.substring(0, tmpDispPoDtlLineNum.indexOf(PERIOD)));
                dispLineNum++;
                maxDispPoDtlLineNum = String.valueOf(dispLineNum) + PERIOD + ZERO;
            }
        }

        // Get Default SWH
        String srcRtlSwhCd = "";
        String destRtlSwhCd = "";

        // Get Default Source SWH
        if (ZYPCommonFunc.hasValue(bizMsg.srcRtlWhCd_SC)) {
            S21SsmEZDResult result = NPAL1500Query.getInstance().getRtlSwhCd(glblMsg.glblCmpyCd.getValue(), bizMsg.srcRtlWhCd_SC.getValue());

            if (result.isCodeNormal()) {
                srcRtlSwhCd = (String) result.getResultObject();
            }
        }
        // Get Default Destination SWH
        if (ZYPCommonFunc.hasValue(bizMsg.destRtlWhCd_DS)) {
            S21SsmEZDResult result = NPAL1500Query.getInstance().getRtlSwhCd(glblMsg.glblCmpyCd.getValue(), bizMsg.destRtlWhCd_DS.getValue());

            if (result.isCodeNormal()) {
                destRtlSwhCd = (String) result.getResultObject();
            }
        }
        // Add New Line
        glblMsg.A.setValidCount(glblMsg.A.getValidCount() + 1);
        int curIndex = glblMsg.A.getValidCount() - 1;

        // Set New Line Flag
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).xxDplyCtrlFlg_NL, ZYPConstant.FLG_ON_Y);

        // Set Default Value
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).poLineStsCd_A1, PO_LINE_STS.OPEN);
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).poLineStsDescTxt_A1, PO_LINE_STS_TXT_OPEN);

        // Date Needed(Pin) Date Needed
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).rqstRcvDt_A1, glblMsg.rqstRcvDt);

        // START 2023/02/14 S.Dong [QC#60966, ADD]
        // Vendor Ship Date
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).rqstShipDt_A1, glblMsg.rqstShipDt);
        // END 2023/02/14 S.Dong [QC#60966, ADD]
        
        // Source SWH(Pin) SRC_RTL_SWH_CD
        if (ZYPCommonFunc.hasValue(srcRtlSwhCd)) {
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).srcRtlSwhCd_A1, srcRtlSwhCd);
        } else {
            glblMsg.A.no(curIndex).srcRtlSwhCd_A1.clear();
        }

        // Destination SWH(Pin) DEST_RTL_SWH_CD
        // ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).destRtlSwhCd_A1,
        // glblMsg.destRtlSwhCd_DS);
        if (ZYPCommonFunc.hasValue(destRtlSwhCd)) {
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).destRtlSwhCd_A1, destRtlSwhCd);
        } else {
            glblMsg.A.no(curIndex).destRtlSwhCd_A1.clear();
        }

        // Stock Status(Pin)
        if (!DS_PO_TP.BUYBACK_PO.equals(glblMsg.dsPoTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).stkStsCd_A1, STK_STS.GOOD);
        } else {
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).stkStsCd_A1, STK_STS.WAITING_FOR_INSPECTION);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).poLineTpCd_A1, PO_LINE_TP.GOODS);
        }

        // Ext.Total Line
        BigDecimal extTotal = new BigDecimal(ZERO);
        if (ZYPCommonFunc.hasValue(glblMsg.A.no(curIndex).entDealNetUnitPrcAmt_A1) && ZYPCommonFunc.hasValue(glblMsg.A.no(curIndex).poDispQty_A1)) {
            extTotal = glblMsg.A.no(curIndex).entDealNetUnitPrcAmt_A1.getValue().multiply(glblMsg.A.no(curIndex).poDispQty_A1.getValue());
        }
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).entPoDtlDealNetAmt_A1, extTotal);

        // PO_MDSE_CMPSN_TP_CD
        if (!DS_PO_TP.BUYBACK_PO.equals(glblMsg.dsPoTpCd)) {
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).poMdseCmpsnTpCd_HD, PO_MDSE_CMPSN_TP.REGULAR);
        }
        // PO Line#
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).poOrdDtlLineNum_A1, maxPoOrdDtlLineNum);
        // PO Disp Line#
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).dispPoDtlLineNum_A1, maxDispPoDtlLineNum);
    }
    /**
     * get Max PO_DTL.PO_DTL_LINE_NUM
     * @param prchReqNum String
     * @param glblCmpyCd String
     * @return String
     */
    private static String getMaxPoDtlLineNum(String glblCmpyCd, String poOrdNum) {
        S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getMaxPoDtlLineNum(glblCmpyCd, poOrdNum);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> list = (List) ssmResult.getResultObject();
            for (int c = 0; c < list.size(); c++) {
                Map<String, Object> map = list.get(c);
                return (String) map.get(DB_COLUMN_MAX_LINE_NUM);
            }
        }
        return PO_DTL_LINE_NUM_000;
    }
    /**
     * <pre>
     * set Derive Item Info BB
     * </pre>
     * @param glblMsg NPAL1500SMsg
     * @param bizMsg NPAL1500CMsg
     * @param index index of SMsg Line
     * @return boolean true:Success false:Fale
     */
    public static boolean setDeriveItemInfoBB(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg, int index) {

        Map<String, Object> resultMap = null;

        S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getDeriveItemInfo(bizMsg, glblMsg.A.no(index).mdseCd_A1.getValue());

        if (ssmResult.isCodeNormal()) {

            resultMap = (Map<String, Object>) ssmResult.getResultObject();

            if (resultMap != null && !resultMap.isEmpty()) {

                // set Info
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).mdseCd_A1, (String) resultMap.get("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).mdseDescShortTxt_A1, (String) resultMap.get("MDSE_DESC_SHORT_TXT"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).aslMdseCd_A1, (String) resultMap.get("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).rcvSerTakeFlg_IB, (String) resultMap.get("RCV_SER_TAKE_FLG"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).instlBaseCtrlFlg_IB, (String) resultMap.get("INSTL_BASE_CTRL_FLG"));
            }
        }

        if (resultMap == null) {

            glblMsg.A.no(index).mdseCd_A1.setErrorInfo(1, NPAM0076E, new String[] {glblMsg.A.no(index).mdseCd_A1.getValue() });
            bizMsg.setMessageInfo(NPAM0076E, new String[] {"Item#" });
            return false;

        } else if (ZYPConstant.FLG_ON_Y.equals((String) resultMap.get("DSCTN_FLG"))) {

            //QC#27474 Mod Start
            if(!DS_PO_TP.BUYBACK_PO.equals(glblMsg.dsPoTpCd.getValue()))
            {
                glblMsg.A.no(index).mdseCd_A1.setErrorInfo(1, NPZM0140E);
                bizMsg.setMessageInfo(NPZM0140E);
                return false;
            }
            //QC#27474 Mod End

        } else if (ZYPConstant.FLG_ON_Y.equals((String) resultMap.get("PRNT_CMPY_SET_MDSE_FLG"))) {

            glblMsg.A.no(index).mdseCd_A1.setErrorInfo(1, NPAM1488E);
            bizMsg.setMessageInfo(NPAM1488E);
            return false;

        } else {

            NLXC001001GetInventoryItemCostBean nlxc001001Bean = new NLXC001001GetInventoryItemCostBean();
            nlxc001001Bean.setGlblCmpyCd(glblMsg.glblCmpyCd.getValue());
            nlxc001001Bean.setInvtyLocCd(glblMsg.destRtlWhCd_DS.getValue().concat(glblMsg.A.no(index).destRtlSwhCd_A1.getValue()));
            nlxc001001Bean.setMdseCd(glblMsg.A.no(index).mdseCd_A1.getValue());

            if (ZYPCommonFunc.hasValue(glblMsg.A.no(index).poDispQty_A1)) {

                nlxc001001Bean.setQty(glblMsg.A.no(index).poDispQty_A1.getValue());

            } else {

                nlxc001001Bean.setQty(new BigDecimal(1));
            }
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).poDispQty_A1, nlxc001001Bean.getQty());

            NLXC001001GetInventoryItemCost.getInventoryItemCost(nlxc001001Bean);

            if (!nlxc001001Bean.getErrList().isEmpty()) {
                String errMsgCd = nlxc001001Bean.getErrList().get(0);
                glblMsg.A.no(index).mdseCd_A1.setErrorInfo(1, errMsgCd);
                return false;
            }

            // set Info
            if (!ZYPCommonFunc.hasValue(glblMsg.A.no(index).entDealNetUnitPrcAmt_A1)) {
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).entDealNetUnitPrcAmt_A1, nlxc001001Bean.getDspUnitPrcAmt());
            }
            BigDecimal extTotal = glblMsg.A.no(index).entDealNetUnitPrcAmt_A1.getValue().multiply(glblMsg.A.no(index).poDispQty_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).entPoDtlDealNetAmt_A1, extTotal);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).poDispUomCd_A1, VND_UOM.EACH);
        }

        return true;
    }

    /** QC#15975 Add.
     * checkComponentQty 
     * @param glblMsg NPAL1500SMsg
     * @param glblCmpyCd String
     * @param prtIndex int
     * @return boolean
     */
    public static boolean checkComponentQty(NPAL1500SMsg glblMsg, String glblCmpyCd, int prtIndex) {
        List<Map<String, Object>> resultListComp = null;

        String prtMdseCd = glblMsg.A.no(prtIndex).mdseCd_A1.getValue();
        S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getComponent(glblCmpyCd, prtMdseCd);
        if (ssmResult.isCodeNormal()) {
            resultListComp = (List<Map<String, Object>>) ssmResult.getResultObject();

            String getCompMdseCd = "";
            String childMdseCd = "";
            BigDecimal prtDispQty = glblMsg.A.no(prtIndex).poDispQty_A1.getValue();
            String prtRqstRcvDt = glblMsg.A.no(prtIndex).rqstRcvDt_A1.getValue();
            String prtOrdDtlLineNum = glblMsg.A.no(prtIndex).poOrdDtlLineNum_A1.getValue();
            String setOrdDtlLineNum = "";
            BigDecimal chldQty = BigDecimal.ZERO;
            for (int i = 0; i < resultListComp.size(); i++) {
                getCompMdseCd = (String) resultListComp.get(i).get("CHILD_MDSE_CD");
                chldQty = (BigDecimal) resultListComp.get(i).get("CHILD_MDSE_QTY");

                for (int c = 0; c < glblMsg.A.getValidCount(); c++) {
                    childMdseCd = glblMsg.A.no(c).mdseCd_A1.getValue();
                    if (ZYPCommonFunc.hasValue(glblMsg.A.no(c).setPoOrdDtlLineNum)) {
                        setOrdDtlLineNum = glblMsg.A.no(c).setPoOrdDtlLineNum.getValue();
                    }
                    if (ZYPCommonFunc.hasValue(setOrdDtlLineNum) && childMdseCd.equals(getCompMdseCd) && prtOrdDtlLineNum.equals(setOrdDtlLineNum)) {

                        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(c).poDispQty_A1, chldQty.multiply(prtDispQty));
                        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(c).rqstRcvDt_A1, prtRqstRcvDt);

                    }
                }
            }
        }

        return true;
    }

    /** QC#16108
     * sortSetPoOrdDtlLineNum
     * @param ordDtlLineList List<BigDecimal>
     * @param sMsg NPAL1500SMsg
     * @param parentIndex int
     * @param childIndex int
     * @param excludeLineList List<Integer>
     * @return boolean 
     */
    public static boolean sortSetPoOrdDtlLineNum(List<BigDecimal> ordDtlLineList, NPAL1500SMsg sMsg, int parentIndex, int childIndex, List<Integer> excludeLineList) {

        String newPoOrdDtlLineNum = ZYPCommonFunc.leftPad(String.valueOf(parentIndex + 1), 3, "0");

        if (ordDtlLineList.get(parentIndex).toPlainString().equals(sMsg.A.no(childIndex).dispPoDtlLineNum_A1.getValue())) {

            for (int i = 0; i < sMsg.A.getValidCount(); i++) {

                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).setPoOrdDtlLineNum)
                        && sMsg.A.no(childIndex).poOrdDtlLineNum_A1.getValue().equals(sMsg.A.no(i).setPoOrdDtlLineNum.getValue())
                        && !excludeLineList.contains(i)) {

                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).setPoOrdDtlLineNum, newPoOrdDtlLineNum);
                    excludeLineList.add(i);
                }
            }

            ZYPEZDItemValueSetter.setValue(sMsg.A.no(childIndex).poOrdDtlLineNum_A1, newPoOrdDtlLineNum);
            return true;
        }

        return false;
    }

    /**
     * get Destination WH Info and set Ship To Address and CSA Return Address
     * @param glblMsg NPAL1500SMsg
     * @param bizMsg NPAL1500CMsg
     */
    public static boolean getRtlWhInfo(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        List<Map<String, Object>> resultMapList = null;

        S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getRtlWhNm(glblMsg.glblCmpyCd.getValue(), glblMsg.destRtlWhCd_DS.getValue());

        if (ssmResult.isCodeNormal()) {
            resultMapList = (List<Map<String, Object>>) ssmResult.getResultObject();

            if (!resultMapList.isEmpty()) {
                if (resultMapList.size() == 1) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.destRtlWhCd_DS, (String) resultMapList.get(0).get(DB_COLUMN_RTL_WH_CD));
                    ZYPEZDItemValueSetter.setValue(glblMsg.destRtlSwhCd_DS, (String) resultMapList.get(0).get(DB_COLUMN_RTL_SWH_CD));
                    ZYPEZDItemValueSetter.setValue(glblMsg.rtlWhNm_DS, (String) resultMapList.get(0).get(DB_COLUMN_RTL_WH_NM));
                    if (!getShipToAddress(bizMsg, glblMsg)) {
                        return false;
                    }

                    if (!getCSARtrnAddr(bizMsg, glblMsg)) {
                        return false;
                    }

                } else {
                    bizMsg.setMessageInfo(NPAL1496W);
                    return false;
                }
            }
        // QC#16341 Add.
        } else {
            bizMsg.destRtlWhCd_DS.setErrorInfo(1, NPAM0076E, new String[] {DISPLAY_DEST_RTL_WH_CD });
            return false;
        }
        return true;
    }

    /**
     * get ShipToAddress
     * @param glblMsg NPAL1500SMsg
     * @param bizMsg NPAL1500CMsg
     */
    private static boolean getShipToAddress(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        Map<String, String> resultMap = null;

        S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getShipToAddress(bizMsg);

        if (ssmResult.isCodeNormal()) {
            resultMap = (Map<String, String>) ssmResult.getResultObject();
            if (resultMap != null) {
                ZYPEZDItemValueSetter.setValue(glblMsg.shipToCustCd, bizMsg.destRtlWhCd_DS.getValue());
                ZYPEZDItemValueSetter.setValue(glblMsg.shipToLocNm, (String) resultMap.get("LOC_NM"));
                ZYPEZDItemValueSetter.setValue(glblMsg.shipToLocNm_ST, (String) resultMap.get("LOC_NM"));
                ZYPEZDItemValueSetter.setValue(glblMsg.shipToAddlLocNm_ST, (String) resultMap.get("ADDL_LOC_NM"));
                ZYPEZDItemValueSetter.setValue(glblMsg.xxAllLineAddr_ST, (String) resultMap.get("ALL_SHIP_TO_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(glblMsg.shipToFirstLineAddr_ST, (String) resultMap.get("FIRST_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(glblMsg.shipToScdLineAddr_ST, (String) resultMap.get("SCD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(glblMsg.shipToThirdLineAddr_ST, (String) resultMap.get("THIRD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(glblMsg.shipToFrthLineAddr_ST, (String) resultMap.get("FRTH_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(glblMsg.shipToCtryCd_ST, (String) resultMap.get("CTRY_CD"));
                ZYPEZDItemValueSetter.setValue(glblMsg.shipToPostCd_ST, (String) resultMap.get("POST_CD"));
                ZYPEZDItemValueSetter.setValue(glblMsg.shipToCtyAddr_ST, (String) resultMap.get("CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(glblMsg.shipToStCd_ST, (String) resultMap.get("ST_CD"));
                ZYPEZDItemValueSetter.setValue(glblMsg.shipToProvNm_ST, (String) resultMap.get("PROV_NM"));
                ZYPEZDItemValueSetter.setValue(glblMsg.ctryDescTxt_ST, (String) resultMap.get("CTRY_NM"));
                ZYPEZDItemValueSetter.setValue(glblMsg.shipToCntyNm_ST, (String) resultMap.get("CNTY_NM"));

            }
        }

        if (resultMap == null) {
            bizMsg.setMessageInfo(NPAM0076E, new String[] {"Ship To Customer Code" });
            return false;
        }

        ssmResult = NPAL1500Query.getInstance().countShipToCustAddr(bizMsg);
        BigDecimal count = (BigDecimal) ssmResult.getResultObject();
        if (BigDecimal.ZERO.compareTo(count) < 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyCtrlFlg, ZYPConstant.FLG_ON_Y);
        }
        return true;
    }

    /**
     * get CSA Return Address
     * @param glblMsg NPAL1500SMsg
     * @param bizMsg NPAL1500CMsg
     */
    private static boolean getCSARtrnAddr(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        String result = null;
        Map<String, String> resultMap = null;

        S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getRtrnShipToRtlWHCd(bizMsg);

        if (ssmResult.isCodeNormal()) {
            result = (String) ssmResult.getResultObject();

        } else {
            ssmResult = NPAL1500Query.getInstance().getDefRtrnShipToRtlWHCd(bizMsg);

            if (ssmResult.isCodeNormal()) {
                result = (String) ssmResult.getResultObject();
            }
        }

        if (result == null) {
            bizMsg.setMessageInfo(NPAM0076E, new String[] {"CSA Return Address" });
            return false;
        }

        ssmResult = NPAL1500Query.getInstance().getAddressInfoByRtlWh(bizMsg, result);

        if (ssmResult.isCodeNormal()) {
            resultMap = (Map<String, String>) ssmResult.getResultObject();
            if (resultMap != null) {
                ZYPEZDItemValueSetter.setValue(glblMsg.rtrnShipToRtlWhCd_RW, (String) resultMap.get("RTL_WH_CD"));
                ZYPEZDItemValueSetter.setValue(glblMsg.rtlWhNm_RW, (String) resultMap.get("RTL_WH_NM"));
                ZYPEZDItemValueSetter.setValue(glblMsg.xxAllLineAddr_RW, (String) resultMap.get("ALL_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(glblMsg.ctryCd_RW, (String) resultMap.get("CTRY_CD"));
                ZYPEZDItemValueSetter.setValue(glblMsg.postCd_RW, (String) resultMap.get("POST_CD"));
                ZYPEZDItemValueSetter.setValue(glblMsg.ctyAddr_RW, (String) resultMap.get("CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(glblMsg.stCd_RW, (String) resultMap.get("ST_CD"));
                ZYPEZDItemValueSetter.setValue(glblMsg.provNm_RW, (String) resultMap.get("PROV_NM"));
                ZYPEZDItemValueSetter.setValue(glblMsg.ctryDescTxt_RW, (String) resultMap.get("CTRY_NM"));
                ZYPEZDItemValueSetter.setValue(glblMsg.cntyNm_RW, (String) resultMap.get("CNTY_NM"));
                ZYPEZDItemValueSetter.setValue(glblMsg.addlLocNm_RW, (String) resultMap.get("ADDL_LOC_NM"));
            }
        }

        if (resultMap == null) {
            bizMsg.setMessageInfo(NPAM0076E, new String[] {"CSA Return Address" });
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * isPreApprovedWhOwnr
     * Set Approval Status to Pre-Approved if target WH is included in specified WH owners.
     * Condition:
     * + Target WH's owner is in "PO_PREAPVL_WH_OWNR_CD"
     * </pre>
     * @param sMsg NPAL1500SMsg
     * @return boolean
     */
    public static boolean isPreApprovedWhOwnr(NPAL1500SMsg sMsg) {
        String poPreapvlWhOwnrCd = ZYPCodeDataUtil.getVarCharConstValue(NPAL1500Constant.VAR_CHAR_PO_PREAPVL_WH_OWNR_CD, sMsg.glblCmpyCd.getValue());
        if (!ZYPCommonFunc.hasValue(poPreapvlWhOwnrCd)) {
            return false;
        }

        String glblCmpyCd = sMsg.glblCmpyCd.getValue();
        String rtlWhCd = sMsg.destRtlWhCd_DS.getValue();
        String[] whOwnrCdArray = poPreapvlWhOwnrCd.split(",");
        for (int n = 0; n < whOwnrCdArray.length; ++n) {
            if (ZYPCommonFunc.hasValue(whOwnrCdArray[n])) {
                whOwnrCdArray[n] = whOwnrCdArray[n].trim();
            }
        }
        int count = NPAL1500Query.getInstance().countRtlWhInWhOwnr(glblCmpyCd, rtlWhCd, whOwnrCdArray);
        return (count > 0);
    }

    /**
     * <pre>
     * isSvcLvlWhOwnr
     * Set Service Level to Ground if target WH is included in specified WH owners.
     * Condition:
     * + Service Level is not specified.
     * + PO Type is "Standard PO"
     * + Target WH's owner is in "PO_SVC_LVL_WH_OWNR_CD"
     * </pre>
     * @param sMsg NPAL1500SMsg
     * @return boolean
     */
    public static boolean isSvcLvlWhOwnr(NPAL1500SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(sMsg.shpgSvcLvlCd)) {
            return false;
        }
        if (!DS_PO_TP.STANDARD_PO.equals(sMsg.dsPoTpCd.getValue())) {
            return false;
        }
        String poPreapvlWhOwnrCd = ZYPCodeDataUtil.getVarCharConstValue(NPAL1500Constant.VAR_CHAR_PO_SVC_LVL_WH_OWNR_CD, sMsg.glblCmpyCd.getValue());
        if (!ZYPCommonFunc.hasValue(poPreapvlWhOwnrCd)) {
            return false;
        }

        String glblCmpyCd = sMsg.glblCmpyCd.getValue();
        String rtlWhCd = sMsg.destRtlWhCd_DS.getValue();
        String[] whOwnrCdArray = poPreapvlWhOwnrCd.split(",");
        for (int n = 0; n < whOwnrCdArray.length; ++n) {
            if (ZYPCommonFunc.hasValue(whOwnrCdArray[n])) {
                whOwnrCdArray[n] = whOwnrCdArray[n].trim();
            }
        }
        int count = NPAL1500Query.getInstance().countRtlWhInWhOwnr(glblCmpyCd, rtlWhCd, whOwnrCdArray);
        return (count > 0);
    }

    /**
     * get Destination WH Info and set Ship To Address and CSA Return Address
     * @param bizMsg NPAL1500CMsg
     * @param glblMsg NPAL1500SMsg
     */
    public static void setRtlWhInfo(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        // START 2017/11/08 [QC#21849, MOD]
        // List<Map<String, Object>> resultMapList = null;
        //
        // S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getRtlWhNm(bizMsg.glblCmpyCd.getValue(), bizMsg.destRtlWhCd_DS.getValue());
        // 
        // if (ssmResult.isCodeNormal()) {
        //     resultMapList = (List<Map<String, Object>>) ssmResult.getResultObject();
        //
        //     if (!resultMapList.isEmpty()) {
        //         if (resultMapList.size() == 1) {
        //             ZYPEZDItemValueSetter.setValue(bizMsg.destRtlWhCd_DS, (String) resultMapList.get(0).get(DB_COLUMN_RTL_WH_CD));
        //             ZYPEZDItemValueSetter.setValue(bizMsg.destRtlSwhCd_DS, (String) resultMapList.get(0).get(DB_COLUMN_RTL_SWH_CD));
        //             ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhNm_DS, (String) resultMapList.get(0).get(DB_COLUMN_RTL_WH_NM));
        //             ZYPEZDItemValueSetter.setValue(bizMsg.stCd_RW, (String) resultMapList.get(0).get("ST_CD"));
        //             ZYPEZDItemValueSetter.setValue(bizMsg.postCd_RW, (String) resultMapList.get(0).get("POST_CD"));
        //             setShipToAddress(bizMsg, glblMsg); // Set Ship To
        //             // Address
        //             setCSARtrnAddr(bizMsg, glblMsg); // Set CSA Return
        //             // Address
        //
        //         } else {
        //             bizMsg.setMessageInfo(NPAL1496W);
        //         }
        //     }
        // // QC#16341 Add.
        // } else {
        //     bizMsg.destRtlWhCd_DS.setErrorInfo(1, NPAM0076E, new String[] {DISPLAY_DEST_RTL_WH_CD });
        // }

        if (!ZYPCommonFunc.hasValue(bizMsg.destRtlWhCd_DS.getValue()) && hasAssetLine(bizMsg, glblMsg)) {
            // complement Destination WH
            String[] manualDsWhCds = NPAL1500CommonLogic.getManualDropShipWHCd(bizMsg.glblCmpyCd.getValue());
            if (manualDsWhCds != null && manualDsWhCds.length > 0) {
                String manualDsWhCd = manualDsWhCds[0];
                ZYPEZDItemValueSetter.setValue(bizMsg.destRtlWhCd_DS, manualDsWhCd);
            }
        }
        NPAL1500CommonLogic.setDestWhInfo(bizMsg, glblMsg);

        // QC#23007
        if (!NPAL1500CommonLogic.isManualDropShipWHCd(bizMsg.glblCmpyCd.getValue(), bizMsg.destRtlWhCd_DS.getValue()) 
                && ZYPCommonFunc.hasValue(bizMsg.destRtlWhCd_DS) && ZYPCommonFunc.hasValue(bizMsg.shipToCustCd)
                && !(RTL_WH_CATG.TECH_CAR_STOCK.equals(bizMsg.rtlWhCatgCd_DS.getValue()) || RTL_WH_CATG.CUSTOMER.equals(bizMsg.rtlWhCatgCd_DS.getValue()))
                && !bizMsg.shipToCustCd.getValue().equals(bizMsg.destRtlWhCd_DS.getValue())) {
            // set DestinationWH to Ship To Customer
            if (!PO_HDR_STS.CLOSED.equals(bizMsg.poHdrStsCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustCd, bizMsg.destRtlWhCd_DS);
                // clear location name & address info
                bizMsg.xxAllLineAddr_ST.clear();
                bizMsg.shipToAddlLocNm_ST.clear();
                bizMsg.shipToCtyAddr_ST.clear();
            }
        }

        if (ZYPCommonFunc.hasValue(bizMsg.destRtlWhCd_DS) && (!ZYPCommonFunc.hasValue(bizMsg.xxAllLineAddr_ST)
                || !ZYPCommonFunc.hasValue(bizMsg.shipToAddlLocNm_ST)
                || !ZYPCommonFunc.hasValue(bizMsg.shipToCtyAddr_ST)
                || !ZYPCommonFunc.hasValue(bizMsg.shipToCtyAddr_ST))) {

            if (ZYPCommonFunc.hasValue(bizMsg.shipToCustCd) 
                    && NPAL1500CommonLogic.isManualDropShipWHCd(bizMsg.glblCmpyCd.getValue(), bizMsg.destRtlWhCd_DS.getValue())) {
                if (!ZYPCommonFunc.hasValue(bizMsg.shipToLocNm)){
                    // derive Ship to Location (by ship to customer code)
                    NPAL1500CommonLogic.setShipToAddressFromShipToCustomer(bizMsg, glblMsg);
                }
            } else {
                // derive Ship to Location (by destination WH code) Mod by QC#23007
                if (!PO_HDR_STS.CLOSED.equals(bizMsg.poHdrStsCd.getValue()) //
                        && !(RTL_WH_CATG.TECH_CAR_STOCK.equals(bizMsg.rtlWhCatgCd_DS.getValue()) || RTL_WH_CATG.CUSTOMER.equals(bizMsg.rtlWhCatgCd_DS.getValue()))) {
                    NPAL1500CommonLogic.setShipToAddressFromDestinationWH(bizMsg, glblMsg);
                }
            }
        }

        setCSARtrnAddr(bizMsg, glblMsg);
        // END   2017/11/08 [QC#21849, MOD]
    }

// START 2017/11/08 [QC#21849, DEL]
//    /**
//     * set ShipToAddress
//     * @param glblMsg NPAL1500SMsg
//     * @param bizMsg NPAL1500CMsg
//     */
//    private static void setShipToAddress(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {
//
//        Map<String, String> resultMap = null;
//
//        S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getShipToAddress(bizMsg);
//
//        if (ssmResult.isCodeNormal()) {
//            resultMap = (Map<String, String>) ssmResult.getResultObject();
//            if (resultMap != null) {
//                ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustCd, bizMsg.destRtlWhCd_DS.getValue());
//                ZYPEZDItemValueSetter.setValue(bizMsg.shipToLocNm, (String) resultMap.get("LOC_NM"));
//                ZYPEZDItemValueSetter.setValue(bizMsg.shipToAddlLocNm_ST, (String) resultMap.get("ADDL_LOC_NM"));
//                ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_ST, (String) resultMap.get("ALL_SHIP_TO_LINE_ADDR"));
//                ZYPEZDItemValueSetter.setValue(bizMsg.shipToFirstLineAddr_ST, (String) resultMap.get("FIRST_LINE_ADDR"));
//                ZYPEZDItemValueSetter.setValue(bizMsg.shipToScdLineAddr_ST, (String) resultMap.get("SCD_LINE_ADDR"));
//                ZYPEZDItemValueSetter.setValue(bizMsg.shipToThirdLineAddr_ST, (String) resultMap.get("THIRD_LINE_ADDR"));
//                ZYPEZDItemValueSetter.setValue(bizMsg.shipToFrthLineAddr_ST, (String) resultMap.get("FRTH_LINE_ADDR"));
//                ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtryCd_ST, (String) resultMap.get("CTRY_CD"));
//                ZYPEZDItemValueSetter.setValue(bizMsg.shipToPostCd_ST, (String) resultMap.get("POST_CD"));
//                ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtyAddr_ST, (String) resultMap.get("CTY_ADDR"));
//                ZYPEZDItemValueSetter.setValue(bizMsg.shipToStCd_ST, (String) resultMap.get("ST_CD"));
//                ZYPEZDItemValueSetter.setValue(bizMsg.shipToProvNm_ST, (String) resultMap.get("PROV_NM"));
//                ZYPEZDItemValueSetter.setValue(bizMsg.ctryDescTxt_ST, (String) resultMap.get("CTRY_NM"));
//                ZYPEZDItemValueSetter.setValue(bizMsg.shipToCntyNm_ST, (String) resultMap.get("CNTY_NM"));
//            }
//        }
//
//        if (resultMap == null) {
//            bizMsg.setMessageInfo(NPAM0076E, new String[] {"Ship To Customer Code" });
//        }
//
//        ssmResult = NPAL1500Query.getInstance().countShipToCustAddr(bizMsg);
//        BigDecimal count = (BigDecimal) ssmResult.getResultObject();
//        if (BigDecimal.ZERO.compareTo(count) < 0) {
//            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyCtrlFlg, ZYPConstant.FLG_ON_Y);
//        }
//    }
// END   2017/11/08 [QC#21849, DEL]

    /**
     * set CSA Return Address
     * @param glblMsg NPAL1500SMsg
     * @param bizMsg NPAL1500CMsg
     */
    public static void setCSARtrnAddr(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        String result = null;
        Map<String, String> resultMap = null;

        S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getRtrnShipToRtlWHCd(bizMsg);

        if (ssmResult.isCodeNormal()) {
            result = (String) ssmResult.getResultObject();

        } else {
            ssmResult = NPAL1500Query.getInstance().getDefRtrnShipToRtlWHCd(bizMsg);

            if (ssmResult.isCodeNormal()) {
                result = (String) ssmResult.getResultObject();
            }
        }

        if (result == null) {
            bizMsg.setMessageInfo(NPAM0076E, new String[] {"CSA Return Address" });
            return;
        }

        ssmResult = NPAL1500Query.getInstance().getAddressInfoByRtlWh(bizMsg, result);

        if (ssmResult.isCodeNormal()) {
            resultMap = (Map<String, String>) ssmResult.getResultObject();
            if (resultMap != null) {
                ZYPEZDItemValueSetter.setValue(bizMsg.rtrnShipToRtlWhCd_RW, (String) resultMap.get("RTL_WH_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhNm_RW, (String) resultMap.get("RTL_WH_NM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_RW, (String) resultMap.get("ALL_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.ctryCd_RW, (String) resultMap.get("CTRY_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.postCd_RW, (String) resultMap.get("POST_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.ctyAddr_RW, (String) resultMap.get("CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.stCd_RW, (String) resultMap.get("ST_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.provNm_RW, (String) resultMap.get("PROV_NM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.ctryDescTxt_RW, (String) resultMap.get("CTRY_NM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.cntyNm_RW, (String) resultMap.get("CNTY_NM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.addlLocNm_RW, (String) resultMap.get("ADDL_LOC_NM"));
            }
        }

        if (resultMap == null) {
            bizMsg.setMessageInfo(NPAM0076E, new String[] {"CSA Return Address" });
        }
    }

    /**
     * get getSupplierName
     * @param glblMsg NPAL1500SMsg
     * @param bizMsg NPAL1500CMsg
     * @param glblCmpyCd String
     */
    public static void getSupplierName(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {
        if (ZYPCommonFunc.hasValue(bizMsg.vndCd)) {
            S21SsmEZDResult result = NPAL1500Query.getInstance().getVendorInfo(bizMsg);

            if (result.isCodeNormal()) {
                List<Map> vndInfo = (List<Map>) result.getResultObject();
                // Set First Record Data
                if (0 < vndInfo.size()) {
                    Map recode = vndInfo.get(0);
                    ZYPEZDItemValueSetter.setValue(bizMsg.prntVndCd, (String) recode.get(DB_COLUMN_PRNT_VND_CD));
                    ZYPEZDItemValueSetter.setValue(bizMsg.prntVndNm, (String) recode.get(DB_COLUMN_PRNT_VND_NM));
                    ZYPEZDItemValueSetter.setValue(bizMsg.vndCd, (String) recode.get(DB_COLUMN_VND_CD));
                    ZYPEZDItemValueSetter.setValue(bizMsg.vndNm, (String) recode.get(DB_COLUMN_LOC_NM));
                    ZYPEZDItemValueSetter.setValue(bizMsg.ccyCd, (String) recode.get(DB_DEAL_CCY_CD));
                    ZYPEZDItemValueSetter.setValue(bizMsg.vndPmtTermDescTxt, (String) recode.get(DB_VND_PMT_TERM_DESC_TXT));
                }
            } else {
                bizMsg.vndCd.setErrorInfo(1, NPAM0076E, new String[] {DISPLAY_VND_CD });
            }
        }
    }

    /**QC#18671 Add.
     * chkMdseDuplicateFromAsl
     * @param cMsg
     * @param sMsg
     * @param glblCmpyCd
     * @param lineIndex
     * @return
     */
    public static boolean chkMdseDuplicateFromAsl(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg, String glblCmpyCd, NPAL1500_ASMsg sMsgALine) {

        if (!ZYPCommonFunc.hasValue(sMsg.vndCd)) {
            // No Check.
            return true;
        }

        // VND check
        RCV_ASN_VNDTMsg tMsg = new RCV_ASN_VNDTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.rcvAsnVndCd, sMsg.vndCd);
        tMsg = (RCV_ASN_VNDTMsg) EZDTBLAccessor.findByKey(tMsg);
        if (!(tMsg != null && EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode()))) {
            return true;
        }

        if (!VND_SYS_TP.PARTS.equals(tMsg.vndSysTpCd.getValue())) {
            // Skip checking unless CUSA Parts
            return true;
        }

        // QC#52460 Mod Start
        if (PO_STS.CANCELLED.equals(sMsgALine.poStsCd_HD.getValue()) || PO_STS.CLOSED.equals(sMsgALine.poStsCd_HD.getValue())) {
        	return true;
        }
        // QC#52460 Mod End
        S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getMdseFromSupplierItem(sMsg, glblCmpyCd, sMsgALine);
        if (!ssmResult.isCodeNormal()) {
            // No Check.
            return true;
        }
        List<Map<String, String>> mdseMapList = (List<Map<String, String>>) ssmResult.getResultObject();
        for (Map<String, String> mdseMap : mdseMapList) {
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            	// QC#52460 Mod Start
                if ( (!PO_STS.CANCELLED.equals(sMsg.A.no(i).poStsCd_HD.getValue()) && !PO_STS.CLOSED.equals(sMsg.A.no(i).poStsCd_HD.getValue()) )
                		&& mdseMap.get("MDSE_CD").equals(sMsg.A.no(i).mdseCd_A1.getValue())) {

                    // Duplicated Error.
                    sMsgALine.mdseCd_A1.setErrorInfo(1, NPAM1602E, new String[] {sMsg.A.no(i).mdseCd_A1.getValue() });
                    return false;
                }
            }
        }
        return true;
    }

    /**QC#18671 Add.
     * chkMdseDuplicateFromAsl
     * @param cMsg
     * @param sMsg
     * @param glblCmpyCd
     * @param lineIndex
     * @param rcvAsnVndCache
     * @return
     */
    public static boolean chkMdseDuplicateFromAslCache(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg, String glblCmpyCd, NPAL1500_ASMsg sMsgALine, S21LRUMap<Object, RCV_ASN_VNDTMsg> rcvAsnVndCache) {

        // START 2019/08/02 T.Ogura [QC#51448,MOD]
//        if (!ZYPCommonFunc.hasValue(sMsg.vndCd)) {
        if (!ZYPCommonFunc.hasValue(sMsg.vndCd) || PO_STS.CANCELLED.equals(sMsgALine.poStsCd_HD.getValue())) {
        // END   2019/08/02 T.Ogura [QC#51448,MOD]
            // No Check.
            return true;
        }

        String vndCd = sMsg.vndCd.getValue();
        // VND check
        RCV_ASN_VNDTMsg tMsg = rcvAsnVndCache.get(vndCd);
        if (tMsg == null) {
            tMsg = new RCV_ASN_VNDTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.rcvAsnVndCd, sMsg.vndCd);
            tMsg = (RCV_ASN_VNDTMsg) EZDTBLAccessor.findByKey(tMsg);
            if (!(tMsg != null && EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode()))) {
                return true;
            } else {
                rcvAsnVndCache.put(vndCd, tMsg);
            }
        }

        if (!VND_SYS_TP.PARTS.equals(tMsg.vndSysTpCd.getValue())) {
            // Skip checking unless CUSA Parts
            return true;
        }

        // QC#52460 Mod Start
        if (PO_STS.CANCELLED.equals(sMsgALine.poStsCd_HD.getValue()) || PO_STS.CLOSED.equals(sMsgALine.poStsCd_HD.getValue())) {
        	return true;
        }
        // QC#52460 Mod End
        S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getMdseFromSupplierItem(sMsg, glblCmpyCd, sMsgALine);
        if (!ssmResult.isCodeNormal()) {
            // No Check.
            return true;
        }
        List<Map<String, String>> mdseMapList = (List<Map<String, String>>) ssmResult.getResultObject();
        for (Map<String, String> mdseMap : mdseMapList) {
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            	// QC#52460 Mod Start
                if ( (!PO_STS.CANCELLED.equals(sMsg.A.no(i).poStsCd_HD.getValue()) && !PO_STS.CLOSED.equals(sMsg.A.no(i).poStsCd_HD.getValue()))
                		&& mdseMap.get("MDSE_CD").equals(sMsg.A.no(i).mdseCd_A1.getValue())) {

                    // Duplicated Error.
                    sMsgALine.mdseCd_A1.setErrorInfo(1, NPAM1602E, new String[] {sMsg.A.no(i).mdseCd_A1.getValue() });
                    return false;
                }
            }
        }
        return true;
    }

    // START 2017/11/07 [QC#21849, ADD]
    /**
     * getManualDropShipWHCd
     * @param glblCmpyCd String
     * @return String[]
     */
    public static String[] getManualDropShipWHCd(String glblCmpyCd) {
        String constValue = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_KEY_MANUAL_DROPSHIP_WAREHOUSE_CD, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(constValue)) {
            return new String[] { MANUAL_DIRECT_SHIP_CUST_CD };
        }
        return constValue.split(COMMA);
    }

    /**
     * isManualDropShipWHCd
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return boolean
     */
    public static boolean isManualDropShipWHCd(String glblCmpyCd, String rtlWhCd) {
        String[] whCds = getManualDropShipWHCd(glblCmpyCd);
        return Arrays.asList(whCds).contains(rtlWhCd);
    }

    // QC#27422 Add Start
    /**
     * getManualDropShipWHCd
     * @param glblCmpyCd String
     * @return String[]
     */
    public static String getFirstManualDropShipWHCd(String glblCmpyCd) {
        String[] whCds = getManualDropShipWHCd(glblCmpyCd);
        return whCds[0];
    }
    // QC#27422 Add End
    
    /**
     * isFMCustomer
     * @param glblCmpyCd String
     * @param shipToCustCd String
     * @return boolean
     */
    public static boolean isFMCustomer(String glblCmpyCd, String shipToCustCd) {
        S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().checkFMCustomer(glblCmpyCd, shipToCustCd);
        if (ssmResult.isCodeNormal()) {
            String sellToCustCd = (String) ssmResult.getResultObject();
            if (sellToCustCd != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * checkDetailLineType
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     * @return boolean
     */
    public static boolean checkDetailLineType(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg) {

        boolean hasAsset = hasAssetLine(cMsg, sMsg);
        boolean hasError = false;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NPAL1500_ASMsg aSMsg = sMsg.A.no(i);
            if (!ZYPCommonFunc.hasValue(aSMsg.poLineTpCd_A1)) {
                continue;
            }
            // START 2017/12/12 [QC#14858, MOD]
            //if (hasAsset && !PO_LINE_TP.ASSET.equals(aSMsg.poLineTpCd_A1.getValue())) {
            // GOODS and ASSET can not be selected in PO.
            if (hasAsset && PO_LINE_TP.GOODS.equals(aSMsg.poLineTpCd_A1.getValue())) {
            // END 2017/12/12 [QC#14858, MOD]
                aSMsg.poLineTpCd_A1.setErrorInfo(1, NPAM1430E);
                hasError = true;
                continue;
            }
        }

        return hasError;
    }

    /**
     * hasAssetLine
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     * @return boolean
     */
    public static boolean hasAssetLine(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg) {
        boolean hasAsset = false;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NPAL1500_ASMsg aSMsg = sMsg.A.no(i);
            if (PO_LINE_TP.ASSET.equals(aSMsg.poLineTpCd_A1.getValue())) {
                hasAsset = true;
                break;
            }
        }
        return hasAsset;
    }

    /**
     * checkConsistencyDestWH
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     * @return boolean
     */
    public static boolean checkConsistencyDestWH(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg) {
        if (NPAL1500CommonLogic.hasAssetLine(cMsg, sMsg)) {
            return checkConsistencyDestWHAsset(cMsg, sMsg);
        } else {
            return checkConsistencyDestWHNotAsset(cMsg, sMsg);
        }
    }

    /**
     * checkConsistencyDestWHAsset
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     * @return boolean
     */
    private static boolean checkConsistencyDestWHAsset(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.shipToCustCd.getValue())) {
            cMsg.shipToCustCd.setErrorInfo(1, ZZM9000E, new String[] { "Ship To Customer"});
            return true;
        }

        if (NPAL1500CommonLogic.isManualDropShipWHCd(cMsg.glblCmpyCd.getValue(), cMsg.destRtlWhCd_DS.getValue())) {
            // Destination WH : Manual DropShip Warehouse
            if (!PO_HDR_STS.CLOSED.equals(cMsg.poHdrStsCd.getValue()) && !NPAL1500CommonLogic.isFMCustomer(cMsg.glblCmpyCd.getValue(), cMsg.shipToCustCd.getValue())) {
                cMsg.shipToCustCd.setErrorInfo(1, NPAM1363E, new String[] { "Destination WH", "Ship To Customer"});
                return true;
            }

        } else if (!ZYPCommonFunc.hasValue(cMsg.destRtlWhCd_DS.getValue())) {
            // Destination WH : empty
            if (!NPAL1500CommonLogic.isFMCustomer(cMsg.glblCmpyCd.getValue(), cMsg.shipToCustCd.getValue())) {
                cMsg.shipToCustCd.setErrorInfo(1, NPAM0023E);
                return true;
            }

        } else {
            // Destination WH : not Manual DropShip Warehouse
            cMsg.destRtlWhCd_DS.setErrorInfo(1, NPAM1363E, new String[] {"Ship To Customer", "Line Type"});
            return true;
        }
        return false;
    }

    /**
     * checkConsistencyDestWHNotAsset
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     * @return boolean
     */
    private static boolean checkConsistencyDestWHNotAsset(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg) {

        if (NPAL1500CommonLogic.isManualDropShipWHCd(cMsg.glblCmpyCd.getValue(), cMsg.destRtlWhCd_DS.getValue())) {
            // Destination WH : Manual DropShip Warehouse
            if (!ZYPCommonFunc.hasValue(cMsg.shipToCustCd.getValue())) {
                cMsg.shipToCustCd.setErrorInfo(1, ZZM9000E, new String[] { "Ship To Customer"});
                return true;
            }
        } else if (!ZYPCommonFunc.hasValue(cMsg.destRtlWhCd_DS.getValue())) {
            // Destination WH : empty
            cMsg.destRtlWhCd_DS.setErrorInfo(1, ZZM9000E, new String[] { "Destination WH"});

        }
        return false;
    }

    /**
     * set Destination WH Info.
     * @param bizMsg NPAL1500CMsg
     * @param glblMsg NPAL1500SMsg
     * @param destWhCd String
     */
    public static void setDestWhInfo(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {
        List<Map<String, Object>> resultMapList = null;

        S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getRtlWhNm(bizMsg.glblCmpyCd.getValue(), bizMsg.destRtlWhCd_DS.getValue());

        if (ssmResult.isCodeNormal()) {
            resultMapList = (List<Map<String, Object>>) ssmResult.getResultObject();

            if (!resultMapList.isEmpty()) {
                if (resultMapList.size() == 1) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.destRtlWhCd_DS, (String) resultMapList.get(0).get(DB_COLUMN_RTL_WH_CD));
                    ZYPEZDItemValueSetter.setValue(bizMsg.destRtlSwhCd_DS, (String) resultMapList.get(0).get(DB_COLUMN_RTL_SWH_CD));
                    ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhNm_DS, (String) resultMapList.get(0).get(DB_COLUMN_RTL_WH_NM));
                    ZYPEZDItemValueSetter.setValue(bizMsg.stCd_RW, (String) resultMapList.get(0).get("ST_CD"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.postCd_RW, (String) resultMapList.get(0).get("POST_CD"));
                    // QC#23007
                    ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhCatgCd_DS, (String) resultMapList.get(0).get("RTL_WH_CATG_CD"));
                }
            }

        } else {
            // QC#27024
            if (!PO_HDR_STS.CLOSED.equals(bizMsg.poHdrStsCd.getValue()) && !EVENT_NM_NPAL1500_ADD_NEWLINE.equals(bizMsg.getScreenAplID())) {
                bizMsg.destRtlWhCd_DS.setErrorInfo(1, NPAM0076E, new String[] {DISPLAY_DEST_RTL_WH_CD });
            }
        }
    }

    /**
     * setShipToAddressFromShipToCustomer
     * @param glblMsg NPAL1500SMsg
     * @param bizMsg NPAL1500CMsg
     */
    public static void setShipToAddressFromShipToCustomer(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        Map<String, String> resultMap = null;

        // START 2019/12/02 T.Ogura [QC#54813,MOD]
//        S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getShipToAddressFromShipToCustomer(bizMsg);
        S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getShipToAddressFromShipToCustomer(bizMsg.glblCmpyCd.getValue(), bizMsg.shipToCustCd.getValue());
        // END   2019/12/02 T.Ogura [QC#54813,MOD]

        if (ssmResult.isCodeNormal()) {
            resultMap = (Map<String, String>) ssmResult.getResultObject();
            if (resultMap != null) {
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToLocNm, (String) resultMap.get("LOC_NM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToAddlLocNm_ST, (String) resultMap.get("ADDL_LOC_NM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_ST, (String) resultMap.get("ALL_SHIP_TO_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToFirstLineAddr_ST, (String) resultMap.get("FIRST_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToScdLineAddr_ST, (String) resultMap.get("SCD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToThirdLineAddr_ST, (String) resultMap.get("THIRD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToFrthLineAddr_ST, (String) resultMap.get("FRTH_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtryCd_ST, (String) resultMap.get("CTRY_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToPostCd_ST, (String) resultMap.get("POST_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtyAddr_ST, (String) resultMap.get("CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToStCd_ST, (String) resultMap.get("ST_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToProvNm_ST, (String) resultMap.get("PROV_NM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.ctryDescTxt_ST, (String) resultMap.get("CTRY_NM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToCntyNm_ST, (String) resultMap.get("CNTY_NM"));
            }
        }

        if (resultMap == null) {
            bizMsg.shipToCustCd.setErrorInfo(1, NPAM0076E, new String[] {"Ship To Customer Code" });
        }

        ssmResult = NPAL1500Query.getInstance().countShipToCustAddr(bizMsg);
        BigDecimal count = (BigDecimal) ssmResult.getResultObject();
        if (BigDecimal.ZERO.compareTo(count) < 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyCtrlFlg, ZYPConstant.FLG_ON_Y);
        }
    }

    /**
     * setShipToAddressFromDestinationWH
     * @param glblMsg NPAL1500SMsg
     * @param bizMsg NPAL1500CMsg
     */
    public static void setShipToAddressFromDestinationWH(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        Map<String, String> resultMap = null;

        S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getShipToAddress(bizMsg);

        if (ssmResult.isCodeNormal()) {
            resultMap = (Map<String, String>) ssmResult.getResultObject();
            if (resultMap != null) {
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustCd, bizMsg.destRtlWhCd_DS.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToLocNm, (String) resultMap.get("LOC_NM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToAddlLocNm_ST, (String) resultMap.get("ADDL_LOC_NM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_ST, (String) resultMap.get("ALL_SHIP_TO_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToFirstLineAddr_ST, (String) resultMap.get("FIRST_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToScdLineAddr_ST, (String) resultMap.get("SCD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToThirdLineAddr_ST, (String) resultMap.get("THIRD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToFrthLineAddr_ST, (String) resultMap.get("FRTH_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtryCd_ST, (String) resultMap.get("CTRY_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToPostCd_ST, (String) resultMap.get("POST_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtyAddr_ST, (String) resultMap.get("CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToStCd_ST, (String) resultMap.get("ST_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToProvNm_ST, (String) resultMap.get("PROV_NM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.ctryDescTxt_ST, (String) resultMap.get("CTRY_NM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToCntyNm_ST, (String) resultMap.get("CNTY_NM"));
            }
        }

        if (resultMap == null) {
            bizMsg.setMessageInfo(NPAM0076E, new String[] {"Ship To Customer Code" });
        }

        ssmResult = NPAL1500Query.getInstance().countShipToCustAddr(bizMsg);
        BigDecimal count = (BigDecimal) ssmResult.getResultObject();
        if (BigDecimal.ZERO.compareTo(count) < 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyCtrlFlg, ZYPConstant.FLG_ON_Y);
        }
    }
    // END   2017/11/07 [QC#21849, ADD]
    
    // START 2017/12/06 [QC#14858, ADD]
    /**
     * checkItemInfo
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     * @return boolean
     */
    public static boolean checkItemInfo(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg, int i) {

        // START 02/18/2020 T.Ogura [QC#55916,ADD]
        if (ZYPConstant.FLG_OFF_N.equals(glblMsg.A.no(i).openPoFlg_A1.getValue())) {
            return false;
        }
        // END   02/18/2020 T.Ogura [QC#55916,ADD]

        // QC#23616
       if (PO_LINE_TP.EXPENSE.equals(glblMsg.A.no(i).poLineTpCd_A1.getValue()) //
               || PO_LINE_TP.EXPENSE_WITH_RECEIPT.equals(glblMsg.A.no(i).poLineTpCd_A1.getValue())
               || PO_LINE_TP.ASSET.equals(glblMsg.A.no(i).poLineTpCd_A1.getValue())) {
           
           // Error Item# or Item Description is mandatory.
            if (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).mdseCd_A1.getValue()) //
                    && !ZYPCommonFunc.hasValue(glblMsg.A.no(i).mdseDescShortTxt_A1.getValue())) {
                glblMsg.A.no(i).mdseCd_A1.setErrorInfo(1,ZZM9000E, new String[] {"Item# or Item Description" });
                glblMsg.A.no(i).mdseDescShortTxt_A1.setErrorInfo(1, ZZM9000E, new String[] {"Item# or Item Description" });
                bizMsg.setMessageInfo(ZZM9000E, new String[] {"Item# or Item Description" });
                glblMsg.A.no(i).xxCntDplyFlg_A2.setValue(ZYPConstant.FLG_OFF_N); 
                return true;
           } else {
               if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).mdseCd_A1.getValue())) {
                   glblMsg.A.no(i).xxCntDplyFlg_A2.setValue(ZYPConstant.FLG_OFF_N);
               } else {
                   glblMsg.A.no(i).xxCntDplyFlg_A2.setValue(ZYPConstant.FLG_ON_Y);
               }
           }
        } else {
            glblMsg.A.no(i).xxCntDplyFlg_A2.setValue(ZYPConstant.FLG_OFF_N);
            if (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).mdseCd_A1.getValue())) {
                glblMsg.A.no(i).mdseCd_A1.setErrorInfo(1, ZZM9000E, new String[] {"Item#" });
                bizMsg.setMessageInfo(ZZM9000E, new String[] {"Item# or Item Description" });
                return true;
            }
        }
        return false;
    }    
    // END   2017/12/06 [QC#14858, ADD]
    
    // START 2017/12/06 [QC#14858, ADD]
    /**
     * setTextItem
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     * @return boolean
     */
    public static boolean setTextItem(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        String resultMap = null;
        String[] textItemList = null;
        S21SsmEZDResult ssmResult = new S21SsmEZDResult();

        // get Available Text Item List.
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            // QC#56195
            // START 02/18/2020 T.Ogura [QC#55916,ADD]
//            if (ZYPConstant.FLG_OFF_N.equals(glblMsg.A.no(i).openPoFlg_A1.getValue())) {
//                continue;
//            }
            // END   02/18/2020 T.Ogura [QC#55916,ADD]
            if (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).mdseCd_A1) && !ZYPCommonFunc.hasValue(glblMsg.A.no(i).mdseDescShortTxt_A1)) {
                continue;
            }

            // QC#23616
            if ((PO_LINE_TP.EXPENSE.equals(glblMsg.A.no(i).poLineTpCd_A1.getValue()) //
            || PO_LINE_TP.EXPENSE_WITH_RECEIPT.equals(glblMsg.A.no(i).poLineTpCd_A1.getValue()))
            || PO_LINE_TP.ASSET.equals(glblMsg.A.no(i).poLineTpCd_A1.getValue()) ) {
                // check TEXT ITEM
                ssmResult = NPAL1500Query.getInstance().getAvailableTextItem(glblMsg.glblCmpyCd.getValue(), glblMsg.A.no(i).mdseCd_A1.getValue());
                if (ssmResult.isCodeNormal()) {
                    resultMap = (String) ssmResult.getResultObject();
                    textItemList = resultMap.split(",");
                    for (int n = 0; n < textItemList.length; ++n) {
                        if (ZYPCommonFunc.hasValue(textItemList[n])) {
                            textItemList[n] = textItemList[n].trim();
                        }
                    }
                }
            }
        }

        List<Map<String, String>> textItemMapList = null;

        // get Text Item except for used Text Item.
        ssmResult = NPAL1500Query.getInstance().getTextItem(glblMsg, textItemList);

        if (!ssmResult.isCodeNormal()) {
            bizMsg.setMessageInfo(ZZM9000E, new String[] {"Item#" });
            return true;
        }

        textItemMapList = (List<Map<String, String>>) ssmResult.getResultObject();

        // set Text Items
        int listIndex = 0;
        int textItem = 0;
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            // QC#56195
            // START 02/18/2020 T.Ogura [QC#55916,ADD]
//            if (ZYPConstant.FLG_OFF_N.equals(glblMsg.A.no(i).openPoFlg_A1.getValue())) {
//                continue;
//            }
            // END   02/18/2020 T.Ogura [QC#55916,ADD]

            if (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).mdseCd_A1) && !ZYPCommonFunc.hasValue(glblMsg.A.no(i).mdseDescShortTxt_A1)) {
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxCntDplyFlg_A2, ZYPConstant.FLG_OFF_N);
                continue;
            }
            // QC#23616
            if ((PO_LINE_TP.EXPENSE.equals(glblMsg.A.no(i).poLineTpCd_A1.getValue()) //
                    || PO_LINE_TP.EXPENSE_WITH_RECEIPT.equals(glblMsg.A.no(i).poLineTpCd_A1.getValue())
                    || PO_LINE_TP.ASSET.equals(glblMsg.A.no(i).poLineTpCd_A1.getValue())) //
                    && !ZYPCommonFunc.hasValue(glblMsg.A.no(i).mdseCd_A1)) {
                ++textItem;
                if (textItem <= textItemMapList.size()) {
                    // set Text Item
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).mdseCd_A1, textItemMapList.get(listIndex).get("MDSE_CD"));
                    ++listIndex;
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxCntDplyFlg_A2, ZYPConstant.FLG_ON_Y);
                } else {
                    glblMsg.A.no(i).mdseCd_A1.setErrorInfo(1, NPAM1611E);
                    bizMsg.setMessageInfo(ZZM9000E, new String[] {"Item#" });
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxCntDplyFlg_A2, ZYPConstant.FLG_OFF_N);
                }
            } else {
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxCntDplyFlg_A2, ZYPConstant.FLG_OFF_N);
            }
        }
        return false;
    }    
    // END   2017/12/06 [QC#14858, ADD]
    
    //QC#18602(Sol#102) ADD Start
    public static boolean checkAddNewLine(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        if (PO_HDR_STS.CLOSED.equals(bizMsg.poHdrStsCd.getValue())) {

            // check DS_PO_TP.RE_OPEN_FLG
            BigDecimal countDPT = (BigDecimal) NPAL1500Query.getInstance().getReOpenFlgDPT(bizMsg);
            if (BigDecimal.ZERO.compareTo(countDPT) < 0) {

                // check PO_ORD_SRC.RE_OPEN_FLG
                BigDecimal countPOS = (BigDecimal) NPAL1500Query.getInstance().getReOpenFlgPOS(bizMsg);

                if (BigDecimal.ZERO.compareTo(countPOS) < 0) {
                    // Add Line
                    return true;
                } else {
                    // Error
                    bizMsg.setMessageInfo(NPAM1613E);
                    return false;
                }
            } else {
                // Error
                bizMsg.setMessageInfo(NPAM1613E);
                return false;
            }
        } else {
            // Add Line
            return true;
        }
    }
    //QC#18602(Sol#102) ADD End
    //QC#21170 ADD Start
    /**
     * dtCompare
     * @param date1
     * @param date2
     * @return
     */
    private static boolean dtCompare (String date1, String date2) {
        if (ZYPCommonFunc.hasValue(date1) && ZYPCommonFunc.hasValue(date2)) {
            if (new BigDecimal(date1).compareTo(new BigDecimal(date2)) > 0 ) {
                return true;
            }
        }
        return false;
    }
    /**
     * getHeaderRqstRcvDt
     * @param cMsg
     * @param sMsg
     * @param salesDate
     */
    public static void setHeaderRqstRcvDt_SmsgMaxVal(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg) {
        // get Max RqstRcvDt
        String salesDate = ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue());
        String maxDt = salesDate;
        for (int i=0; i<sMsg.A.getValidCount(); i++) {
            if(!ZYPCommonFunc.hasValue(sMsg.A.no(i).rqstRcvDt_A1) || PO_LINE_STS.CANCELLED.equals(sMsg.A.no(i).poLineStsCd_A1.getValue())) {
                continue;
            }
            if(dtCompare(sMsg.A.no(i).rqstRcvDt_A1.getValue(), maxDt)) {
                maxDt = sMsg.A.no(i).rqstRcvDt_A1.getValue();
            }
        }
        ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, maxDt);
        ZYPEZDItemValueSetter.setValue(sMsg.rqstRcvDt, maxDt);
    }
    /**
     * setRqstRcvDt
     * @param sMsg
     * @param aslMap
     * @param glblCmpyCd
     * @param salesDate
     * @param lineIdx
     */
    public static void setRqstRcvDt(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg, int pNumRsVndLtDays, int lineIdx) {
        String salesDt = ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue());
        // START 2019/03/20 T.Ogura [QC#30769,MOD]
//        String etaDt = ZYPDateUtil.addBusinessDay(cMsg.glblCmpyCd.getValue(), salesDt, pNumRsVndLtDays);
        String etaDt = ZYPDateUtil.addDays(salesDt, pNumRsVndLtDays);
        // END   2019/03/20 T.Ogura [QC#30769,MOD]
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineIdx).rqstRcvDt_A1, etaDt);
    }
    //QC#21170 ADD End
    // START 2023/02/14 S.Dong [QC#60966, ADD]
    /**
     * setHeaderRqstShipDt
     * @param cMsg
     * @param sMsg
     */
    public static void setHeaderRqstShipDt(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg) {
        // get Max RqstShipDt
        String salesDate = ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue());
        String maxDt = salesDate;
        for (int i=0; i<sMsg.A.getValidCount(); i++) {
            if(!ZYPCommonFunc.hasValue(sMsg.A.no(i).rqstShipDt_A1) || PO_LINE_STS.CANCELLED.equals(sMsg.A.no(i).poLineStsCd_A1.getValue())) {
                continue;
            }
            if(dtCompare(sMsg.A.no(i).rqstShipDt_A1.getValue(), maxDt)) {
                maxDt = sMsg.A.no(i).rqstShipDt_A1.getValue();
            }
        }
        ZYPEZDItemValueSetter.setValue(cMsg.rqstShipDt, maxDt);
        ZYPEZDItemValueSetter.setValue(sMsg.rqstShipDt, maxDt);
    }
    // START 2023/04/28 S.Dong [QC#60966, DEL]
//    /**
//     * setRqstShipDt
//     * @param cMsg
//     * @param sMsg
//     * @param aslMap
//     * @param lineIdx
//     */
//    public static void setRqstShipDt(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg, int pNumRsVndShipLtDays, int lineIdx) {
//        String salesDt = ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue());
//        String etaDt = ZYPDateUtil.addDays(salesDt, pNumRsVndShipLtDays);
//        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineIdx).rqstShipDt_A1, etaDt);
//    }
    // END 2023/04/28 S.Dong [QC#60966, DEL]
    /**
     * setRqstShipDtForGetMdse
     * @param sMsg
     * @param npzc113001PMsg
     * @param lineIdx
     */
    public static void setRqstShipDtForGetMdse(NPAL1500SMsg glblMsg, NPZC113001PMsg npzc113001PMsg, int index) {
    String etaDt = "";
    int pNumRsVndShipLtDays = npzc113001PMsg.vndShipLtDaysNum.getValueInt();
    if (ZYPCommonFunc.hasValue(npzc113001PMsg.vndShipLtDaysNum) && 
        BigDecimal.ZERO.compareTo(npzc113001PMsg.vndShipLtDaysNum.getValue()) < 0) {
        etaDt = ZYPDateUtil.addDays(glblMsg.A.no(index).rqstRcvDt_A1.getValue(),pNumRsVndShipLtDays * -1);
      }
    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).rqstShipDt_A1, etaDt);
    }
    // END 2023/02/14 S.Dong [QC#60966, ADD]

    // START 2023/04/28 S.Dong [QC#60966, ADD]
    /**
     * setRqstShipDtRddApply
     * @param sMsg
     * @param npzc113001PMsg
     * @param lineIdx
     */
    public static void setRqstShipDtRddApply(NPAL1500SMsg glblMsg, NPZC113001PMsg npzc113001PMsg, int index) {
        String etaDt = "";
        int pNumRsVndShipLtDays = npzc113001PMsg.vndShipLtDaysNum.getValueInt();
        if (ZYPCommonFunc.hasValue(npzc113001PMsg.vndShipLtDaysNum) && 
            BigDecimal.ZERO.compareTo(npzc113001PMsg.vndShipLtDaysNum.getValue()) < 0) {
            etaDt = ZYPDateUtil.addDays(glblMsg.A.no(index).rqstRcvDt_A1.getValue(),pNumRsVndShipLtDays * -1);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).rqstShipDt_A1, etaDt);
        } else if (!ZYPCommonFunc.hasValue(npzc113001PMsg.vndShipLtDaysNum)
                || BigDecimal.ZERO.equals(npzc113001PMsg.vndShipLtDaysNum)) {
            return;
        }
    }
    // END 2023/04/28 S.Dong [QC#60966, ADD]

    //QC#25714 Add Start
    /**
     * pagenationAP
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     */
    public static void pagenationAP(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg) {
        int pagenationFromIndex = cMsg.xxPageShowFromNum_B1.getValueInt() - 1;
        
        if (cMsg.xxPageShowFromNum_B1.getValueInt() > 0) {
            int i = pagenationFromIndex;
            for (; i < pagenationFromIndex + cMsg.B.length(); i++) {
                if (i < sMsg.B.getValidCount()) {
                    EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i - pagenationFromIndex), null);
                } else {
                    break;
                }
            }
            cMsg.B.setValidCount(i - pagenationFromIndex);
            // set value to pageNation items
            cMsg.xxPageShowToNum_B1.setValue(pagenationFromIndex + cMsg.B.getValidCount());
        }
    }
    //QC#25714 Add End
    
    //QC#18420 Add Start
    /**
     * calcLinePrice
     * @param bizMsg NPAL1500CMsg
     * @param glblMsg NPAL1500SMsg
     */
     public static void calcLinePrice(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg, int idx) {

        BigDecimal pct = BigDecimal.ZERO;

        // QC#53392 2019/10/05 Mod Start
//        if (!ZYPCommonFunc.hasValue(glblMsg.A.no(idx).aslUnitPrcAmt_A1)) {
        if (!ZYPCommonFunc.hasValue(glblMsg.A.no(idx).poDtlDiscPrcAmt_A1) || pct.compareTo(glblMsg.A.no(idx).poDtlDiscPrcAmt_A1.getValue()) == 0) {
        	ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).poDtlDiscPrcAmt_A1, glblMsg.A.no(idx).aslUnitPrcAmt_A1);
        	if (!ZYPCommonFunc.hasValue(glblMsg.A.no(idx).poDtlDiscPrcAmt_A1)) {
        		return;
        	}
        }
        if (!ZYPCommonFunc.hasValue(glblMsg.A.no(idx).poDtlDiscPct_A1)) {
//            return;
        	pct = BigDecimal.ONE;
        	ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).entDealNetUnitPrcAmt_A1, glblMsg.A.no(idx).poDtlDiscPrcAmt_A1.getValue().multiply(pct).setScale(2, BigDecimal.ROUND_HALF_UP));
        } else {

            if (BigDecimal.ZERO.equals(glblMsg.A.no(idx).poDtlDiscPct_A1.getValue())) {
                pct = BigDecimal.ONE;
            } else {
                pct = PCT_100.subtract(glblMsg.A.no(idx).poDtlDiscPct_A1.getValue()).divide(PCT_100);

            }
//            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).entDealNetUnitPrcAmt_A1, glblMsg.A.no(idx).aslUnitPrcAmt_A1.getValue().multiply(pct).setScale(2, BigDecimal.ROUND_HALF_UP));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).entDealNetUnitPrcAmt_A1, glblMsg.A.no(idx).poDtlDiscPrcAmt_A1.getValue().multiply(pct).setScale(2, BigDecimal.ROUND_HALF_UP));
        }
        // QC#53392 2019/10/05 Mod End

    }
    //QC#18420 Add End
    
     //QC#26893 Add Start
     /**
      * clearAslUnitPrcAmt
      * @param cMsg
      * @param sMsg
      */
     public static void clearAslUnitPrcAmt(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg) {
         // get Max RqstRcvDt
         for (int i=0; i<sMsg.A.getValidCount(); i++) {
             if(PO_MDSE_CMPSN_TP.CHILD.equals(sMsg.A.no(i).poMdseCmpsnTpCd_HD.getValue())){
                 sMsg.A.no(i).aslUnitPrcAmt_A1.clear();
             }
         }
     }
     //QC#26893 Add End

     /**
      * checkCustCarrSvcLvlRelation. Mod QC#29155
      * QC#23726 Add method
      * @param cMsg NPAL1500CMsg
      * @param sMsg NPAL1500SMsg
      * @param isModifyShpgSvcLvlReln boolean
      * @return boolean if error then return true.
      */
     public static boolean checkCustCarrSvcLvlRelation(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg, boolean isModifyShpgSvcLvlReln) {

        // Get CPO
        S21SsmEZDResult resultCpoInfo = NPAL1500Query.getInstance().getCPOInfo(cMsg.glblCmpyCd.getValue(), cMsg.poNum.getValue());
        Map cpoInfo = null;
        if (resultCpoInfo.isCodeNormal()) {
            cpoInfo = (Map) resultCpoInfo.getResultObject();
        } else {
            // Original PO Data.
            return false;
        }

        // Get Carrier Service Level
        String carrSvcLvlCd = null;
        if (isModifyShpgSvcLvlReln && ZYPCommonFunc.hasValue(cMsg.carrCd) // 
                && ZYPCommonFunc.hasValue(cMsg.shpgSvcLvlCd)) {
            S21SsmEZDResult resultCarrSvcLvl = NPAL1500Query.getInstance().getCarrSvcLvlCd(cMsg.glblCmpyCd.getValue()//
                    , cMsg.carrCd.getValue(), cMsg.shpgSvcLvlCd.getValue());

            if (resultCarrSvcLvl.isCodeNormal()) {
                // Unique.
                carrSvcLvlCd = (String) resultCarrSvcLvl.getResultObject();
            } else {
                // Error.
                return true;
            }
        } else {
            // Not ship setting.
            return false;
        }

        // Get DsAcctNum
        String dsAcctNum = null;
        S21SsmEZDResult resultDsAcctNum = NPAL1500Query.getInstance().getDsAcctNum(cMsg.glblCmpyCd.getValue(), cMsg.poNum.getValue());
        if (resultDsAcctNum.isCodeNormal()) {
            dsAcctNum = (String) resultDsAcctNum.getResultObject();
        }

        // Check Customer Carrier Service Level Relation
        return NWXC150001DsCheck.checkCustCarrSvcLvlRelation(//
                cMsg.glblCmpyCd.getValue()//
                , (String) cpoInfo.get("DS_ORD_CATG_CD")//
                , (String) cpoInfo.get("DS_ORD_TP_CD")//
                , (String) cpoInfo.get("DS_ORD_RSN_CD")//
                , dsAcctNum//
                , carrSvcLvlCd//
                , cMsg.frtCondCd.getValue());
    }

     /**
     * isModifyShpgSvcLvlReln QC#29155 Add method.
     * @param cMsg NPAL1500SMsg
     * @param sMsg NPAL1500SMsg
     * @return true:Modify
     */
    public static boolean isModifyShpgSvcLvlReln(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg) {

        // Edit check shpgSvcLvl
        if (ZYPCommonFunc.hasValue(cMsg.shpgSvcLvlCd) && !ZYPCommonFunc.hasValue(sMsg.shpgSvcLvlCd)) {
            return true;
        } else if (!ZYPCommonFunc.hasValue(cMsg.shpgSvcLvlCd) && ZYPCommonFunc.hasValue(sMsg.shpgSvcLvlCd)) {
            return true;
        } else if (ZYPCommonFunc.hasValue(cMsg.shpgSvcLvlCd) && ZYPCommonFunc.hasValue(sMsg.shpgSvcLvlCd)) {
            String cMsgShpgSvcLvlCd = cMsg.shpgSvcLvlCd.getValue();
            String sMsgShpgSvcLvlCd = sMsg.shpgSvcLvlCd.getValue();
            if (!cMsgShpgSvcLvlCd.equals(sMsgShpgSvcLvlCd)) {
                return true;
            }
        }

        // Edit check carrir
        if (ZYPCommonFunc.hasValue(cMsg.carrCd) && !ZYPCommonFunc.hasValue(sMsg.carrCd)) {
            return true;
        } else if (!ZYPCommonFunc.hasValue(cMsg.carrCd) && ZYPCommonFunc.hasValue(sMsg.carrCd)) {
            return true;
        } else if (ZYPCommonFunc.hasValue(cMsg.carrCd) && ZYPCommonFunc.hasValue(sMsg.carrCd)) {
            String cMsgCarrCd = cMsg.carrCd.getValue();
            String sMsgCarrCd = sMsg.carrCd.getValue();
            if (!cMsgCarrCd.equals(sMsgCarrCd)) {
                return true;
            }
        }

        return false;
    }

    // 2018/12/19 QC#29456 Add Start
    /**
     * <pre>
     * Get Error information from item
     * @param msg EZDMessage
     * @param key item name
     * @return Error Information
     * </pre>
     */
    public static EZDMessageInfo getErrorInfo(EZDMsg msg, String key) {

        EZDMessageInfo info = null;
        try {
            // Invoke EZDSMsg#getErrorInfo(String, int)
            Method method = null;
            if (msg instanceof EZDSMsg) {
                method = EZDSMsg.class.getDeclaredMethod("getErrorInfo", String.class, int.class);
            } else if (msg instanceof EZDCMsg) {
                method = EZDCMsg.class.getDeclaredMethod("getErrorInfo", String.class, int.class);
            } else {
                return null;
            }
            method.setAccessible(true);
            info = (EZDMessageInfo) method.invoke(msg, key, -1);
        } catch (SecurityException e) {
            return null;
        } catch (NoSuchMethodException e) {
            return null;
        } catch (IllegalArgumentException e) {
            return null;
        } catch (IllegalAccessException e) {
            return null;
        } catch (InvocationTargetException e) {
            return null;
        }
        return info;
    }
    // 2018/12/19 QC#29456 Add End

    // 2019/01/25 QC#29778-2 Add Start
    /**
     * <pre>
     * Getting Account Defaulting Mode.
     * @param bizMsg Biz. Message
     * @param dtlMsg target detail line global message
     * @return 0: No Operation 1: Defaulting Ship To And Item 2: Defaulting Item only
     * </ore>
     */
    private static AcctDefMode getAccountDefaultingMode(NPAL1500CMsg bizMsg, NPAL1500_ASMsg dtlMsg) {

        AcctDefMode rslt = AcctDefMode.NO_OPE;
        if (!ZYPCommonFunc.hasValue(bizMsg.shipToCustCd) && !ZYPCommonFunc.hasValue(dtlMsg.mdseCd_A1)) {
            return AcctDefMode.NO_OPE;
        }
        String screenAplID = bizMsg.getScreenAplID();
        if (EVENT_NM_NPAL1500_NMAL6800.equals(screenAplID) //
                || EVENT_NM_NPAL1500_GET_MDSE_INFO.equals(screenAplID) //
                || EVENT_NM_NPAL1500_CMN_SAVE.equals(screenAplID) //
                || EVENT_NM_NPAL1500_CMN_SUBMIT.equals(screenAplID)) {
            rslt = AcctDefMode.ITEM;

            applyChrgAccount(dtlMsg, dtlMsg.xxScrItem130Txt_CH.getValue());
            EZDSStringItem[] checkItemList = new EZDSStringItem []{
                    dtlMsg.splyCoaCmpyCd,
                    dtlMsg.splyCoaBrCd,
                    dtlMsg.splyCoaCcCd,
                    dtlMsg.splyCoaAcctCd,
                    dtlMsg.splyCoaProdCd,
                    dtlMsg.splyCoaChCd,
                    dtlMsg.splyCoaAfflCd,
                    dtlMsg.splyCoaProjCd,
                    dtlMsg.splyCoaExtnCd,
                    dtlMsg.xxScrItem130Txt_CH
            };

            for (EZDSStringItem checkItem : checkItemList) {
                if (!ZYPCommonFunc.hasValue(checkItem)) {
                    rslt = AcctDefMode.SHIP_ITEM;
                    break;
                }
            }
        } else {
            rslt = AcctDefMode.SHIP_ITEM;
        }

        // 2019/02/05 QC#30181 Add Start
        if (AcctDefMode.SHIP_ITEM.equals(rslt) //
                && !ZYPCommonFunc.hasValue(bizMsg.shipToCustCd)) {
            rslt = AcctDefMode.ITEM;
        }
        // 2019/02/05 QC#30181 Add End

        return rslt;
    }

    private static String getValidStr(EZDSStringItem item, String val) {

        if (ZYPCommonFunc.hasValue(val)) {
            return val;
        } else {
            return item.getValue();
        }
    }
    // 2019/01/25 QC#29778-2 Add End

    // START 2021/04/21 J.Evangelista [QC#57102,ADD]
    /**
     * Sort Table
     * @param sMsgArray EZDSMsgArray
     * @param sortItemNm String
     * @param sortOrdBy String
     * @param baseContents String[][]
     */
    public static void sortTable(EZDSMsgArray sMsgArray, String sortItemNm, String sortOrdBy, String[][] baseContents) {

        S21SortTarget sortTarget = new S21SortTarget(sMsgArray, baseContents);
        S21SortKey sortKey = new S21SortKey();

        sortKey.add(sortItemNm, sortOrdBy);

        S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsgArray.getValidCount());
    }
    // END 2021/04/21 J.Evangelista [QC#57102,ADD]

    // START 2021/04/23 [QC#58645,ADD]
    /**
     * checkAutoApprove
     * @param sMsg NPAL1500SMsg
     * @return true:Auto Approve false:Need Approval
     */
    public static boolean checkAutoApprove(NPAL1500SMsg sMsg) {

        String str = ZYPCodeDataUtil.getVarCharConstValue(NPAL1500Constant.VAR_CHAR_NPAL1500_AUTO_APPROVE_AMT, sMsg.glblCmpyCd.getValue());
        BigDecimal autoApproveAmt = BigDecimal.ZERO;

        if (ZYPCommonFunc.hasValue(str)) {
            autoApproveAmt = new BigDecimal(str);
        }

        boolean rtrnCd = true;

        BigDecimal total = BigDecimal.ZERO;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (PO_LINE_STS.OPEN.equals(sMsg.A.no(i).poLineStsCd_A1.getValue()) || PO_LINE_STS.OPEN_FOR_RECEIPT.equals(sMsg.A.no(i).poLineStsCd_A1.getValue())
                    || PO_LINE_STS.OPEN_FOR_INVOICE.equals(sMsg.A.no(i).poLineStsCd_A1.getValue())) {

                total = total.add(sMsg.A.no(i).entDealNetUnitPrcAmt_A1.getValue());
                if (autoApproveAmt.compareTo(total) < 0) {
                    rtrnCd = false;
                    break;
                }
            }
        }
        return rtrnCd;
    }
    // END 2021/04/23 [QC#58645,ADD]

    // START 2022/12/09 M.Kikushima[QC#60604, ADD]
    public static boolean isCsaWh(String glblCmpyCd, String shipToCustCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("shipToCustCd", shipToCustCd);
        param.put("whOwnr", WH_OWNR.CSA);
        // Execute
        S21SsmEZDResult result = NPAL1500Query.getInstance().getCsaWhCount(param);

        BigDecimal count = BigDecimal.ZERO;
        if (result.isCodeNormal()) {
            count = (BigDecimal) result.getResultObject();
        }
        if (BigDecimal.ZERO.compareTo(count) != 0) {
            return true;
        }
        return false;
    }
    // END 2022/12/09 M.Kikushima[QC#60604, ADD]
    // START 2024/1/1 K.Iwamoto [QC#62443, ADD]]
    public static boolean checkAllLineType(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {
    	boolean hasChangeLine = false;
    	boolean checkresult = false;
        for(int i = 0; i < glblMsg.A.getValidCount(); i++) {
            checkresult = checkLineType(bizMsg, glblMsg, i);
            if (checkresult) {
                glblMsg.A.no(i).xxScrItem130Txt_CH.setErrorInfo(1, NPAM1329E, new String[] {"Change ACC", "Please click 'Acct' button." });
                if (!hasChangeLine){
                    hasChangeLine = true;
                    bizMsg.setMessageInfo(ZZM9037E);
                }
                for (int j = 0;j < bizMsg.A.getValidCount(); j++) {
                     if(glblMsg.A.no(i).dispPoDtlLineNum_A1.getValue().equals(bizMsg.A.no(j).dispPoDtlLineNum_A1.getValue())) {
                    	 bizMsg.A.no(j).xxScrItem130Txt_CH.setErrorInfo(1, NPAM1329E, new String[] {"Change ACC", "Please click 'Acct' button." });
                     }
                }
            }
        }

        return hasChangeLine;
    }
    
    public static boolean checkLineType(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg, int index) {
        boolean hasChangeLine = false;
        String mdseCd = glblMsg.A.no(index).mdseCd_A1.getValue();
        String glblCd = glblMsg.glblCmpyCd.getValue();
        if (!hasValue(glblCd)){
            return hasChangeLine;
        }
        if (!hasValue(mdseCd)){
            return hasChangeLine;
        }
        if (checkItemTp(glblCd, mdseCd)) {
            // Intangible Item
            if (checkSetItem(mdseCd, glblMsg.A.no(index).dispPoDtlLineNum_A1.getValue(), glblCd)) {
                // Intangible Reguler Item
                if(PO_LINE_TP.GOODS.equals(glblMsg.A.no(index).poLineTpCd_A1.getValue())){
                    glblMsg.A.no(index).poLineTpCd_A1.setValue(PO_LINE_TP.EXPENSE);
                    hasChangeLine = true;
                }
            }
        }
        return hasChangeLine;
    
    }
    
    private static boolean checkItemTp(String glblCd, String mdseCd) {
        MDSETMsg mdseTmsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseTmsg.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(mdseTmsg.glblCmpyCd, glblCd);
        mdseTmsg = (MDSETMsg) EZDTBLAccessor.findByKey(mdseTmsg);
        if (mdseTmsg != null && EZDTBLAccessor.RTNCD_NORMAL.equals(mdseTmsg.getReturnCode())) {
            if (ZYPConstant.FLG_OFF_N.equals(mdseTmsg.invtyCtrlFlg.getValue())) {
                // Intangible Item
                return true;
            }
        }
        return false;
    }
    
    private static boolean checkSetItem(String mdseCd, String dispPoDtlLineChar, String glblCd) {
        int indexNum = dispPoDtlLineChar.indexOf(DOT);
        if (ZERO_STRING.equals(dispPoDtlLineChar.substring(indexNum+1))) {
            MDSETMsg mdseTmsg = new MDSETMsg();
            ZYPEZDItemValueSetter.setValue(mdseTmsg.mdseCd, mdseCd);
            ZYPEZDItemValueSetter.setValue(mdseTmsg.glblCmpyCd, glblCd);
            mdseTmsg = (MDSETMsg) EZDTBLAccessor.findByKey(mdseTmsg);
            if (mdseTmsg != null && EZDTBLAccessor.RTNCD_NORMAL.equals(mdseTmsg.getReturnCode())) {
                if(!MDSE_TP_SET.equals(mdseTmsg.mdseTpCd.getValue())) {
                    //Not Set Item
                    return true;
                }
            }
        }
        return false;
    }
    // END 2024/1/1 K.Iwamoto [QC#62443, ADD]]
}
