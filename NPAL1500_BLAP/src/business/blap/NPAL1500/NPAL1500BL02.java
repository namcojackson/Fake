/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1500;

import static business.blap.NPAL1500.constant.NPAL1500Constant.AM_PM_PULLDOWN_CD_AM;
import static business.blap.NPAL1500.constant.NPAL1500Constant.AM_PM_PULLDOWN_CD_PM;
import static business.blap.NPAL1500.constant.NPAL1500Constant.AM_PM_PULLDOWN_NM_AM;
import static business.blap.NPAL1500.constant.NPAL1500Constant.AM_PM_PULLDOWN_NM_PM;
import static business.blap.NPAL1500.constant.NPAL1500Constant.BIZ_ID;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DB_COLUMN_CARR_NM;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DB_COLUMN_LOC_NM;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DB_COLUMN_PRF_CARR_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DB_COLUMN_PRNT_VND_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DB_COLUMN_PRNT_VND_NM;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DB_COLUMN_VND_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DB_DEAL_CCY_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DB_VND_PMT_TERM_DESC_TXT;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DEF_CUSA_GLBL_CMPY_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DEF_PO_CUST_DROP_SHIP_QLFY_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DISPLAY_PRNT_VND_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_GET_ADDRESS;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_GET_SUPPLIER_NAME;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_GET_SUPPLIER_SITE_NAME;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_ADD_CONFIG;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_ADD_NEWLINE;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_APPLY;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_RQSTSHIPDT_APPLY;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_APPLY_DISC;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_CANCEL;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_CMN_CLEAR;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_CMN_RESET;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_CMN_SAVE;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_CMN_SUBMIT;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_COPY;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_GET_LINE_PRICE_INFO;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_GET_MDSE_INFO;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_GET_SHIP_TO_INFO;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_GET_SHIP_TO_INFO_CUSTOMER;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_INIT;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_MOVE_TO_COMPONET;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_NMAL2550;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_NMAL6760;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_NMAL6800;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_NPAL0170;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_NPAL1010;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_NSAL1240;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_NWAL1130;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_ONCHANGE_TRSMT_METH;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_ONCHNG_PO_TYPE;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_ON_CHANGE_LINE_TYPE;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_OPENWIN_ACCT_CHRG;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_OPENWIN_LOC_FROM_SWH;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_OPENWIN_LOC_FROM_WH;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_OPENWIN_LOC_TO_SWH;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_OPENWIN_SER_ENT;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_PAGE_NEXT;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_PAGE_PREV;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_PO_CLOSE;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_PRINT;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_SEARCH;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_TBL_COLUMN_SORT;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_RETURN_INVTY_REQ_SCRN;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_APPLY_ASL;
import static business.blap.NPAL1500.constant.NPAL1500Constant.MDSE_TP_SET;
import static business.blap.NPAL1500.constant.NPAL1500Constant.MSG_ERR_CD_NORMAL;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NLAM0077E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NMAM0039E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM0002E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM0009E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM0038I;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM0073E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM0076E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1323E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1351E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1361E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1370E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1510E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1511E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1585E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPZM0041E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.PERIOD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.PO_LINE_STS_TXT_OPEN;
import static business.blap.NPAL1500.constant.NPAL1500Constant.SCR_EVENT_NM_LOCATION_FROM_SWH;
import static business.blap.NPAL1500.constant.NPAL1500Constant.SCR_EVENT_NM_LOCATION_TO_SWH;
import static business.blap.NPAL1500.constant.NPAL1500Constant.SCR_EVENT_NM_LOCATION_TO_WH;
import static business.blap.NPAL1500.constant.NPAL1500Constant.SCR_NM_ACCOUNT_TYPE_CH;
import static business.blap.NPAL1500.constant.NPAL1500Constant.TAB_AP_INVOICE;
import static business.blap.NPAL1500.constant.NPAL1500Constant.TAB_DETAIL;
import static business.blap.NPAL1500.constant.NPAL1500Constant.VAR_CHAR_CUSA_GLBL_CMPY_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.VAR_CHAR_PO_CUST_DROP_SHIP_QLFY_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.ZERO_STRING;
import static business.blap.NPAL1500.constant.NPAL1500Constant.ZZZM9003I;
import static business.blap.NPAL1500.constant.NPAL1500Constant.VAR_CHAR_EXP_SPLY_COA_CMPY_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.VAR_CHAR_EXP_SPLY_COA_BR_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.VAR_CHAR_EXP_SPLY_COA_ACCT_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.VAR_CHAR_EXP_SPLY_COA_CH_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.VAR_CHAR_EXP_SPLY_COA_AFFL_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.VAR_CHAR_EXP_SPLY_COA_PROJ_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.VAR_CHAR_EXP_SPLY_COA_EXTN_CD;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NPAL1500.common.NPAL1500CommonLogic;
import business.blap.NPAL1500.constant.NPAL1500Constant;
import business.db.CTRYTMsg;
import business.db.DS_PO_TPTMsg;
import business.db.MDSETMsg;
import business.db.PO_MSGTMsg;
import business.db.PO_ORD_SRCTMsg;
import business.db.PRCH_REQTMsg;
import business.parts.NMZC610001PMsg;
import business.parts.NMZC610001_DefaultBillShipListPMsg;
import business.parts.NPZC113001PMsg;
import business.parts.NPZC115001_xxAslDtlListPMsg;
import business.parts.NPZC129001PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCost;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCostBean;
import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001PoMsg;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_PO_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_ORD_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRSMT_METH_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 * <pre>
 * Business ID : NPAL1500 PO Entry
 * Function Name : search business process
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/18/2015   CITS            N Akaishi       Create          n/a
 * 2016/03/29   CITS            T.Tokutomi      Update          QC#5774
 * 2016/03/30   CITS            T.Tokutomi      Update          QC#6293
 * 2016/04/14   CSAI            K.Lee           Update          QC#7090
 * 2016/05/03   CSAI            K.Lee           Update          QC#7300
 * 05/05/2016   CSAI            K.Lee           Update          QC#7896
 * 05/06/2016   CSAI            K.Lee           Update          QC#5761
 * 2016/09/12   CITS            R.Shimamoto     Update          QC#13985
 * 2016/09/23   CITS            S.Endo          Update          N/A(Refactoring)
 * 2016/09/27   CITS            S.Endo          Update          QC#12628
 * 2016/10/03   CITS            S.Endo          Update          N/A(Refactoring)/QC#14137
 * 2016/10/04   CITS            S.Endo          Update          QC#11983
 * 2016/10/05   CITS            S.Endo          Update          QC#12768
 * 2016/10/06   CITS            S.Endo          Update          QC#8429
 * 2016/10/12   CITS            S.Endo          Update          N/A(Refactoring)
 * 2016/10/14   CITS            S.Endo          Update          QC#15180/QC#15181
 * 2016/10/18   CITS            R.Shimamoto     Update          QC#6159
 * 2016/10/20   CITS            S.Endo          Update          QC#10456/QC#15183/Refactoring
 * 2016/10/21   CITS            S.Endo          Update          QC#15223/QC#15182
 * 2016/10/24   CITS            S.Endo          Update          QC#15226
 * 2016/10/25   CITS            S.Endo          Update          QC#14882
 * 2016/10/31   CITS            S.Endo          Update          QC#14882
 * 2016/12/12   CITS            R.Shimamoto     Update          QC#15817
 * 2016/12/12   CITS            R.Shimamoto     Update          QC#16341
 * 2016/12/27   CITS            S.Endo          Update          QC#14882
 * 2017/02/27   CITS            R.Shimamoto     Update          QC#17785
 * 2017/06/19   CITS            S.Endo          Update          QC#19212
 * 2017/06/21   CITS            S.Endo          Update          QC#19427
 * 2017/06/23   CITS            S.Endo          Update          QC#19485
 * 2017/07/04   CITS            S.Endo          Update          QC#19212
 * 2017/08/04   CITS            R.Shimamoto     Update          QC#18761
 * 2017/09/15   CITS            R.Shimamoto     Update          QC#20735
 * 2017/11/08   Hitachi         Y.Takeno        Update          QC#21849(Sol#218)
 * 12/04/2017   CITS            K.Kameoka       Update          QC#14858(Sol#060)
 * 01/09/2018   CITS            K.Kameoka       Update          QC#18602(Sol#102)
 * 01/25/2018   CITS            K.Ogino         Update          QC#23007
 * 01/30/2018   CITS            K.Ogino         Update          QC#23616
 * 2018/02/08   CITS            K.Ogino         Update          QC#21169
 * 2018/04/15   CITS            K.Fukumura      Update          QC#21170
 * 04/20/2018   CITS            Y.Iwasaki       Update          QC#25019
 * 05/30/2018   CITS            Y.Iwasaki       Update          QC#25342
 * 06/11/2018   CITS            K.Kameoka       Update          QC#25714
 * 06/14/2018   CITS            K.Kameoka       Update          QC#26474
 * 06/19/2018   CITS            K.Kameoka       Update          QC#21358
 * 06/20/2018   CITS            K.Kameoka       Update          QC#18420
 * 06/28/2018   CITS            K.Kameoka       Update          QC#26893
 * 07/20/2018   CITS            K.Kameoka       Update          QC#27422
 * 08/07/2018   CITS            K.Ogino         Update          QC#27024
 * 10/01/2018   CITS            K.Kameoka       Update          QC#27770/28146
 * 11/16/2018   CITS            K.Ogino         Update          QC#29155
 * 12/13/2018   CITS            M.Naito         Update          QC#29027
 * 2019/01/15   Fujitsu         S.Takami        Update          QC#29778
 * 2019/01/25   Fujitsu         S.Takami        Update          QC#29778-2
 * 2019/01/25   Fujitsu         S.Takami        Update          QC#30082
 * 2019/05/20   Fujitsu         T.Ogura         Update          QC#50055
 * 2019/08/08   CITS            R.Shimamoto     Update          QC#52547
 * 2019/09/17   CITS            R.Shimamoto     Update          QC#53384
 * 2019/11/11   Fujitsu         T.Ogura         Update          QC#54588
 * 2019/12/10   Fujitsu         R.Nakamura      Update          QC#55004
 * 2020/01/03   CITS            K.Ogino         Update          QC#55274
 * 02/01/2020   CITS            K.Ogino         Update          QC#55313
 * 02/18/2020   Fujitsu         T.Ogura         Update          QC#55916
 * 2021/01/21   CITS            J.Evangelista   Update          QC#58282
 * 2021/04/21   CITS            J.Evangelista   Update          QC#57102
 * 2022/05/16   Hitachi         A.Kohinata      Update          QC#57934
 * 2022/12/09   Hitachi         M.Kikushima     Update          QC#60604
 * 2023/01/26   CITS            A.Cullano       Update          QC#60975
 * 2023/02/14   Hitachi         S.Dong          Update          QC#60966
 * 2023/04/19   Hitachi         TZ.Win          Update          QC#61299
 * 2023/04/28   Hitachi         S.Dong          Update          QC#60966
 * 2023/08/14   Hitachi         S.Dong          Update          QC#61753
 * 2024/1/4     CITS            K.Iwamoto       Update          QC#62443
 * 2024/3/1     CITS            S.Okamoto       Update          QC#62443
 * 2024/3/19    CITS            S.Okamoto       Update          QC#63555
 * </pre>
 */
public class NPAL1500BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NPAL1500CMsg bizMsg = (NPAL1500CMsg) cMsg;
            NPAL1500SMsg glblMsg = (NPAL1500SMsg) sMsg;

            if (EVENT_NM_NPAL1500_INIT.equals(screenAplID)) {
                if (ZYPCommonFunc.hasValue(bizMsg.poNum)) {
                    doProcess_NPAL1500Scrn00_Search(bizMsg, glblMsg);
                } else {
                    doProcess_NPAL1500_INIT(bizMsg, glblMsg);
                }
                // add start 2022/05/16 QC#57934
                ZYPGUITableColumn.getColData(bizMsg, glblMsg, "AHEAD");
                // add end 2022/05/16 QC#57934
            } else if (EVENT_NM_NPAL1500_ADD_NEWLINE.equals(screenAplID)) {
                doProcess_NPAL1500Scrn00_Add_NewLine(bizMsg, glblMsg);

            } else if (EVENT_NM_NPAL1500_ON_CHANGE_LINE_TYPE.equals(screenAplID)) {
                doProcess_NPAL1500Scrn00_OnChange_LineType(bizMsg, glblMsg);

            } else if (EVENT_NM_NPAL1500_SEARCH.equals(screenAplID) || EVENT_NM_RETURN_INVTY_REQ_SCRN.equals(screenAplID)) {
                doProcess_NPAL1500Scrn00_Search(bizMsg, glblMsg);

            } else if (EVENT_NM_NPAL1500_CMN_CLEAR.equals(screenAplID)) {
                doProcess_NPAL1500Scrn00_CMN_Clear(bizMsg, glblMsg);
                // add start 2022/05/16 QC#57934
                ZYPGUITableColumn.getColData(bizMsg, glblMsg, "AHEAD");
                // add end 2022/05/16 QC#57934

            } else if (EVENT_NM_NPAL1500_CMN_RESET.equals(screenAplID)) {
                doProcess_NPAL1500Scrn00_CMN_Reset(bizMsg, glblMsg);
                // add start 2022/05/16 QC#57934
                ZYPGUITableColumn.getColData(bizMsg, glblMsg, "AHEAD");
                // add end 2022/05/16 QC#57934

            } else if (EVENT_NM_NPAL1500_PAGE_NEXT.equals(screenAplID)) {
                doProcess_NPAL1500Scrn00_PageNext(bizMsg, glblMsg);

            } else if (EVENT_NM_NPAL1500_PAGE_PREV.equals(screenAplID)) {
                doProcess_NPAL1500Scrn00_PagePrev(bizMsg, glblMsg);

            } else if (EVENT_NM_NPAL1500_NWAL1130.equals(screenAplID)) {
                doProcess_NPAL1500_NWAL1130(bizMsg, glblMsg);

            } else if (EVENT_NM_NPAL1500_NPAL1010.equals(screenAplID)) {
                doProcess_NPAL1500_NPAL1010(bizMsg, glblMsg);

            } else if (EVENT_NM_NPAL1500_NMAL6800.equals(screenAplID)) {
                doProcess_NPAL1500Scrn00_Get_MdseInfo(bizMsg, glblMsg);

            } else if (EVENT_NM_NPAL1500_GET_MDSE_INFO.equals(screenAplID)) {
                doProcess_NPAL1500Scrn00_Get_MdseInfo(bizMsg, glblMsg);

            } else if ((EVENT_NM_NPAL1500_OPENWIN_LOC_FROM_WH.equals(screenAplID))
//                    || (EVENT_NM_NPAL1500_OPENWIN_LOC_TO_WH.equals(screenAplID))    // 2019/05/21 T.Ogura [QC#50138,DEL]
                    || (EVENT_NM_NPAL1500_OPENWIN_LOC_FROM_SWH.equals(screenAplID))
                    || (EVENT_NM_NPAL1500_OPENWIN_LOC_TO_SWH.equals(screenAplID))) {
                doProcess_NPAL1500_OpenWin_LocationWH(bizMsg, glblMsg);

            } else if (EVENT_NM_NPAL1500_OPENWIN_SER_ENT.equals(screenAplID)) {
                doProcess_NPAL1500Scrn00_OpenWin_SerEnt(bizMsg, glblMsg);

            } else if (EVENT_NM_NPAL1500_ONCHNG_PO_TYPE.equals(screenAplID)) {
                doProcess_NPAL1500Scrn00_OnChange_PoType(bizMsg, glblMsg);

            // QC#15817 Mod.
//            } else if ((EVENT_NM_NPAL1500_OPENWIN_ACCT_CHRG.equals(screenAplID))
//                    || (EVENT_NM_NPAL1500_OPENWIN_ACCT_ACRL.equals(screenAplID))
//                    || (EVENT_NM_NPAL1500_OPENWIN_ACCT_VAR.equals(screenAplID))) {
            } else if ((EVENT_NM_NPAL1500_OPENWIN_ACCT_CHRG.equals(screenAplID))) {
                doProcess_NPAL1500Scrn00_OpenWin_Account(bizMsg, glblMsg);

            } else if (EVENT_NM_NPAL1500_ONCHANGE_TRSMT_METH.equals(screenAplID)) {
                doProcess_NPAL1500Scrn00_OnChange_TrsmtMethod(bizMsg, glblMsg);

            } else if (EVENT_NM_NPAL1500_GET_SHIP_TO_INFO.equals(screenAplID)) {
                doProcess_NPAL1500Scrn00_Get_ShipToInfo(bizMsg, glblMsg);

            } else if (EVENT_NM_NPAL1500_COPY.equals(screenAplID)) {
                doProcess_NPAL1500Scrn00_Copy(bizMsg, glblMsg);

            } else if (EVENT_NM_NPAL1500_NSAL1240.equals(screenAplID)) {
                doProcess_NPAL1500Scrn00_ApplyConfig(bizMsg, glblMsg);

            } else if (EVENT_NM_NPAL1500_ADD_CONFIG.equals(screenAplID)) {
                doProcess_NPAL1500Scrn00_ApplyConfig(bizMsg, glblMsg);
            // QC#29155
            } else if ((EVENT_NM_NPAL1500_PO_CLOSE.equals(screenAplID)) || (EVENT_NM_NPAL1500_MOVE_TO_COMPONET.equals(screenAplID))) {

                doProcess_NPAL1500Scrn00_Search(bizMsg, glblMsg);

            } else if ((EVENT_NM_NPAL1500_CMN_SAVE.equals(screenAplID)) || (EVENT_NM_NPAL1500_CMN_SUBMIT.equals(screenAplID))) {

                if (bizMsg.shpgSvcLvlCd.getErrorCode() == MSG_ERR_CD_NORMAL) {
                    doProcess_NPAL1500Scrn00_Search(bizMsg, glblMsg);
                }

            } else if ((EVENT_NM_NPAL1500_NPAL0170.equals(screenAplID))) {
                doProcess_NPAL1500Scrn00_NPAL0170(bizMsg, glblMsg);

            } else if ((EVENT_NM_NPAL1500_CANCEL.equals(screenAplID))) {
                doProcess_NPAL1500_Cancel(bizMsg, glblMsg);

            } else if ((EVENT_NM_GET_SUPPLIER_NAME.equals(screenAplID))) {
                doProcess_Get_SupplierName(bizMsg, glblMsg);

            } else if ((EVENT_NM_GET_SUPPLIER_SITE_NAME.equals(screenAplID))) {
                doProcess_Get_SupplierSiteName(bizMsg, glblMsg);

            } else if ((EVENT_NM_GET_ADDRESS.equals(screenAplID))) {
                doProcess_GetAddress(bizMsg, glblMsg);

            } else if ((EVENT_NM_NPAL1500_PRINT.equals(screenAplID))) {
                if (!TRSMT_METH_TP.PDF_DOWNLOAD.equals(bizMsg.trsmtMethTpCd.getValue())) {
                    doProcess_NPAL1500Scrn00_Search(bizMsg, glblMsg);
                }
            } else if ((EVENT_NM_NPAL1500_NMAL2550.equals(screenAplID))) {
                doProcess_FromNMAL2550(bizMsg, glblMsg);

            // START 2017/11/08 [QC#21849, ADD]
            } else if ((EVENT_NM_NPAL1500_NMAL6760.equals(screenAplID))) {
                // QC#23007
                doProcess_NPAL1500Scrn00_Get_ShipToInfoCustomer(bizMsg, glblMsg);

            } else if ((EVENT_NM_NPAL1500_GET_SHIP_TO_INFO_CUSTOMER.equals(screenAplID))) {
                doProcess_NPAL1500Scrn00_Get_ShipToInfoCustomer(bizMsg, glblMsg);

            // END   2017/11/08 [QC#21849, ADD]
            // QC#21169
            } else if (EVENT_NM_NPAL1500_APPLY.equals(screenAplID)) {
                doProcess_NPAL1500Scrn00_Apply(bizMsg, glblMsg);
            // START 2023/02/14 S.Dong [QC#60966, ADD]
            } else if (EVENT_NM_NPAL1500_RQSTSHIPDT_APPLY.equals(screenAplID)) {
                doProcess_NPAL1500Scrn00_RqstShipDt_Apply(bizMsg, glblMsg);    
            // END 2023/02/14 S.Dong [QC#60966, ADD]
            //QC#18420 Add Start 
            } else if (EVENT_NM_NPAL1500_GET_LINE_PRICE_INFO.equals(screenAplID)) {
                doProcess_NPAL1500Scrn00_Get_LinePriceInfo(bizMsg, glblMsg);

            } else if (EVENT_NM_NPAL1500_APPLY_DISC.equals(screenAplID)) {
                doProcess_NPAL1500Scrn00_Apply_Disc(bizMsg, glblMsg);
            //QC#18420 Add End

            // START 2021/04/21 J.Evangelista [QC#57102,ADD]
            } else if (EVENT_NM_NPAL1500_TBL_COLUMN_SORT.equals(screenAplID)) {
                doProcess_NPAL1500Scrn00_TBLColumnSort(bizMsg, glblMsg);
            //   END 2021/04/21 J.Evangelista [QC#57102,ADD]
            // START 2023/04/19 TZ.Win [QC#61299, ADD]
            } else if (EVENT_NM_NPAL1500_APPLY_ASL.equals(screenAplID)) {
                doProcess_NPAL1500Scrn00_Apply_ASL(bizMsg, glblMsg);
                bizMsg.setCommitSMsg(true);
            // END 2023/04/19 TZ.Win [QC#61299, ADD]
            // add start 2022/05/16 QC#57934
            } else if ("NPAL1500Scrn00_CMN_ColSave".equals(screenAplID)) {
                return;

            } else if ("NPAL1500Scrn00_CMN_ColClear".equals(screenAplID)) {
                return;
            // add end 2022/05/16 QC#57934

            } else {
                return;
            }

            // START 2022/12/09 M.Kikushima[QC#60604, ADD]
            if (EVENT_NM_NPAL1500_SEARCH.equals(screenAplID) || EVENT_NM_NPAL1500_CMN_RESET.equals(screenAplID) || EVENT_NM_NPAL1500_CMN_CLEAR.equals(screenAplID)) {
                bizMsg.xxWrnSkipFlg_A.clear();
            }
            // END 2022/12/09 M.Kikushima[QC#60604, ADD]

            // Common Logic
            // set totalAmt
            calcurateTotalAmt(bizMsg, glblMsg);

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Init
     * @param cMsg NPAL1500CMsg
     */
    private void doProcess_NPAL1500_INIT(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        /**
         * Initial Screen Objects.
         */
        String hPoNum = bizMsg.poNum_G1.getValue();
        // add start 2022/05/16 QC#57934
        String xxComnColOrdTxt = bizMsg.xxComnColOrdTxt.getValue();
        // add end 2022/05/16 QC#57934
        bizMsg.clear();
        glblMsg.clear();
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(glblMsg.B);

        /**
         * Setting Initial Values.
         */
        ZYPEZDItemValueSetter.setValue(bizMsg.poNum_G1, hPoNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode());
        // add start 2022/05/16 QC#57934
        ZYPEZDItemValueSetter.setValue(bizMsg.xxComnColOrdTxt, xxComnColOrdTxt);
        // add end 2022/05/16 QC#57934

        setInitialData(bizMsg, false);
        ZYPEZDItemValueSetter.setValue(bizMsg.fullPsnNm, getContextUserInfo().getUserDetails().getUserName());
    }

    /**
     * Add_NewLine
     * @param bizMsg NPAL1500CMsg
     * @param glblMsg NPAL1500SMsg
     */
    private void doProcess_NPAL1500Scrn00_Add_NewLine(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        //QC#18602(Sol#102) ADD Start
        // Check Add New Line when PO is Clesed.
        if(!NPAL1500CommonLogic.checkAddNewLine(bizMsg, glblMsg))
        {
            return;
        }
        //QC#18602(Sol#102) ADD End

        // QC#23007
        if (ZYPCommonFunc.hasValue(bizMsg.destRtlWhCd_DS)) {

            NPAL1500CommonLogic.setDestWhInfo(bizMsg, glblMsg);

            if (!bizMsg.destRtlWhCd_DS.isError()) {
                if (!NPAL1500CommonLogic.isManualDropShipWHCd(bizMsg.glblCmpyCd.getValue(), bizMsg.destRtlWhCd_DS.getValue()) //
                        && !(RTL_WH_CATG.TECH_CAR_STOCK.equals(bizMsg.rtlWhCatgCd_DS.getValue()) || RTL_WH_CATG.CUSTOMER.equals(bizMsg.rtlWhCatgCd_DS.getValue()))) {

                    NPAL1500CommonLogic.setShipToAddressFromDestinationWH(bizMsg, glblMsg);
                }
            } else if (ZYPCommonFunc.hasValue(bizMsg.shipToCustCd)) {

                bizMsg.rtlWhNm_DS.clear();
                bizMsg.rtlWhCatgCd_DS.clear();

            }
        } else {
            if (ZYPCommonFunc.hasValue(bizMsg.shipToCustCd)) {
                //QC#27422 Mod Start
                String mdWhCd = NPAL1500CommonLogic.getFirstManualDropShipWHCd(bizMsg.glblCmpyCd.getValue());
                bizMsg.destRtlWhCd_DS.setValue(mdWhCd);
                //bizMsg.destRtlWhCd_DS.setValue(MANUAL_DIRECT_SHIP_CUST_CD);
                //QC#27422 Mod End
                NPAL1500CommonLogic.setDestWhInfo(bizMsg, glblMsg);
            }
        }

        // Copy Message
        NPAL1500CommonLogic.copyFromCmsgOntoSmsg(bizMsg, glblMsg);

        // add row to screen
        int index = glblMsg.A.getValidCount();

        // QC#21170
        // Set rqstRcvDt (Date & Time Needed)
        NPAL1500CommonLogic.setHeaderRqstRcvDt_SmsgMaxVal(bizMsg, glblMsg);
        // START 2023/02/14 S.Dong [QC#60966, ADD]
        // Set rqstShipDt (Vendor Ship Date)
        NPAL1500CommonLogic.setHeaderRqstShipDt(bizMsg, glblMsg);
        // END 2023/02/14 S.Dong [QC#60966, ADD]
        // paging after Add New Item
        NPAL1500CommonLogic.addNewLine(bizMsg, glblMsg);
        
        //QC#26474 Add Start
        // set line Type list for re-open
        if (PO_HDR_STS.CLOSED.equals(bizMsg.poHdrStsCd.getValue())){
            setPoLineTypeList(bizMsg, true);
        }
        //QC#26474 Add End

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NPAL1500CommonLogic.getDefaultAccountInfo(bizMsg, glblMsg, bizMsg.glblCmpyCd.getValue(), i);
            // QC#27770 Add Start
            if (!NPAL1500CommonLogic.checkManualSegmentElementForSMsg(bizMsg, glblMsg, bizMsg.glblCmpyCd.getValue(), BIZ_ID, i)) {
                if (!ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
                    bizMsg.setMessageInfo(NLAM0077E, new String[] {"Charge ACC" });
                }
                return;
            // QC#27770 Add End
            }
        }

        paginationForAddNewline(bizMsg, glblMsg);
    }

    /**
     * Search
     * @param cMsg NPAL1500CMsg
     * @param glblMsg NPAL1500SMsg
     */
    private void doProcess_NPAL1500Scrn00_Search(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        // Initial Screen Objects
        String hPoNum = bizMsg.poNum.getValue();
        String glblPoNum = bizMsg.poNum_G1.getValue();
        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
        String xxChkBoxFlg = bizMsg.xxChkBox_LO.getValue();
        // add start 2022/05/16 QC#57934
        String xxComnColOrdTxt = bizMsg.xxComnColOrdTxt.getValue();
        // add end 2022/05/16 QC#57934
        //QC#21358 Add Start
        String preTrsmtMethTpCd = bizMsg.trsmtMethTpCd.getValue();
        String preSendPoEmlAddr = bizMsg.sendPoEmlAddr.getValue();
        String preSendPoFaxNum = bizMsg.sendPoFaxNum.getValue();        
        //QC#21358 Add End
        // START 2022/12/09 M.Kikushima[QC#60604, ADD]
        String preWrnSkipFlg = bizMsg.xxWrnSkipFlg_A.getValue();
        // END 2022/12/09 M.Kikushima[QC#60604, ADD]
        bizMsg.clear();
        glblMsg.clear();
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(glblMsg.B);

        ZYPEZDItemValueSetter.setValue(bizMsg.poNum, hPoNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_LO, xxChkBoxFlg);
        // add start 2022/05/16 QC#57934
        ZYPEZDItemValueSetter.setValue(bizMsg.xxComnColOrdTxt, xxComnColOrdTxt);
        // add end 2022/05/16 QC#57934

        setInitialData(bizMsg, true);
        
        // Search Header/Additional header Tab
        setHeaderData(bizMsg);

        ZYPEZDItemValueSetter.setValue(bizMsg.poNum_HD, bizMsg.poNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.poNum_G1, bizMsg.poNum);

        // START 2021/01/21 J.Evangelista [QC#58282,MOD]
        //QC#21358 Add Start
//        if (preSendPoEmlAddr == "" && preSendPoFaxNum == "") {
//            setTrsmtMethInfo(bizMsg, glblMsg);
//        }
//        else{
//            ZYPEZDItemValueSetter.setValue(bizMsg.trsmtMethTpCd, preTrsmtMethTpCd);
//            ZYPEZDItemValueSetter.setValue(bizMsg.sendPoEmlAddr, preSendPoEmlAddr);
//            ZYPEZDItemValueSetter.setValue(bizMsg.sendPoFaxNum, preSendPoFaxNum);
//        }
        //QC#21358 Add End
        if (!EVENT_NM_NPAL1500_SEARCH.equals(bizMsg.getScreenAplID())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.trsmtMethTpCd, preTrsmtMethTpCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.sendPoEmlAddr, preSendPoEmlAddr);
            ZYPEZDItemValueSetter.setValue(bizMsg.sendPoFaxNum, preSendPoFaxNum);
            // START 2022/12/09 M.Kikushima[QC#60604, ADD]
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_A, preWrnSkipFlg);
            // END 2022/12/09 M.Kikushima[QC#60604, ADD]
        }

        if (!(ZYPCommonFunc.hasValue(bizMsg.sendPoEmlAddr) || ZYPCommonFunc.hasValue(bizMsg.sendPoFaxNum))) {
            setTrsmtMethInfo(bizMsg, glblMsg);
        }
        // END 2021/01/21 J.Evangelista [QC#58282,MOD]

        //QC#25024 Add Start
        bizMsg.xxDplyCtrlFlg_DS.clear();

        if (NPAL1500CommonLogic.isManualDropShipWHCd(bizMsg.glblCmpyCd.getValue(), bizMsg.destRtlWhCd_DS.getValue())){
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyCtrlFlg_DS, ZYPConstant.FLG_ON_Y);            
        }
        //QC#25024 Add End
        
        // Search Detail Tab
        setDetailData(bizMsg, glblMsg);

        // set line Type list for close or cancel
        if (PO_HDR_STS.CLOSED.equals(bizMsg.poHdrStsCd.getValue())//
                || PO_HDR_STS.CANCELLED.equals(bizMsg.poHdrStsCd.getValue())) {
            setPoLineTypeList(bizMsg, false);
        }

        // set line Type list for ITT Outbound PO
        if (DS_PO_TP.ITT_OUTBOUND_PO.equals(bizMsg.dsPoTpCd.getValue())) {
            setPoLineTypeList(bizMsg, false);
        }
        
        //QC#18602(Sol#102) ADD Start
        // Search AP Invoice Tab
        setAPInvoiceData(bizMsg, glblMsg);        
        //QC#18602(Sol#102) ADD End

        if ("E".equals(bizMsg.getMessageKind())) {
            bizMsg.clear();
            bizMsg.A.clear();

            ZYPEZDItemValueSetter.setValue(bizMsg.poNum, hPoNum);
            ZYPEZDItemValueSetter.setValue(bizMsg.poNum_G1, glblPoNum);
            ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, glblCmpyCd);
            // add start 2022/05/16 QC#57934
            ZYPEZDItemValueSetter.setValue(bizMsg.xxComnColOrdTxt, xxComnColOrdTxt);
            // add end 2022/05/16 QC#57934
            setInitialData(bizMsg, true);

            return;

        } else {
            // set Hidden Data
            ZYPEZDItemValueSetter.setValue(glblMsg.poNum, bizMsg.poNum);
            ZYPEZDItemValueSetter.setValue(glblMsg.poNum_HD, bizMsg.poNum_HD);
            ZYPEZDItemValueSetter.setValue(glblMsg.poNum_G1, bizMsg.poNum_G1);
            ZYPEZDItemValueSetter.setValue(glblMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        }

        // 2016/09/12 QC#13985 Add Start
        ZYPEZDItemValueSetter.setValue(bizMsg.vndCd_HG, bizMsg.vndCd);
        // 2016/09/12 QC#13985 Add End

        // QC#21170
        // Set rqstRcvDt (Date & Time Needed)
        NPAL1500CommonLogic.setHeaderRqstRcvDt_SmsgMaxVal(bizMsg, glblMsg);
        // START 2023/02/14 S.Dong [QC#60966, ADD]
        // Set rqstShipDt (Vendor Ship Date)
        NPAL1500CommonLogic.setHeaderRqstShipDt(bizMsg, glblMsg);
        // END 2023/02/14 S.Dong [QC#60966, ADD]
        NPAL1500CommonLogic.copyFromCmsgOntoSmsg(bizMsg, glblMsg);
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            NPAL1500CommonLogic.getDefaultAccountInfo(bizMsg, glblMsg, bizMsg.glblCmpyCd.getValue(), i);
        }
        NPAL1500CommonLogic.copyFromSMsgOntoCmsg(bizMsg, glblMsg);
    }

    /**
     * Return from NWAL1130(common popup. supplier, state, country)
     * @param cMsg NPAL1500CMsg
     * @param glblMsg NPAL1500SMsg
     */
    private void doProcess_NPAL1500_NWAL1130(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getVendorInfo(bizMsg);

        if (ssmResult.isCodeNormal()) {
            List<Map> resultList = (List<Map>) ssmResult.getResultObject();
            if (0 < resultList.size()) {
                Map recode = resultList.get(0);
                ZYPEZDItemValueSetter.setValue(bizMsg.prntVndCd, (String) recode.get(DB_COLUMN_PRNT_VND_CD));
                ZYPEZDItemValueSetter.setValue(bizMsg.prntVndNm, (String) recode.get(DB_COLUMN_PRNT_VND_NM));
                ZYPEZDItemValueSetter.setValue(bizMsg.vndCd, (String) recode.get(DB_COLUMN_VND_CD));
                ZYPEZDItemValueSetter.setValue(bizMsg.vndNm, (String) recode.get(DB_COLUMN_LOC_NM));
                ZYPEZDItemValueSetter.setValue(bizMsg.ccyCd, (String) recode.get(DB_DEAL_CCY_CD));
                ZYPEZDItemValueSetter.setValue(bizMsg.vndPmtTermDescTxt, (String) recode.get(DB_VND_PMT_TERM_DESC_TXT));
            }
        }
    }

    /**
     * Return from OpenWin_LocationWH
     * @param cMsg NPAL1500CMsg
     * @param glblMsg NPAL1500SMsg
     */
    private void doProcess_NPAL1500_OpenWin_LocationWH(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.dsPoTpCd) && !ZYPCommonFunc.hasValue(bizMsg.xxPopPrm)) {
            DS_PO_TPTMsg dsPoTpTMsg = new DS_PO_TPTMsg();

            ZYPEZDItemValueSetter.setValue(dsPoTpTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsPoTpTMsg.dsPoTpCd, bizMsg.dsPoTpCd);
            dsPoTpTMsg = (DS_PO_TPTMsg) EZDTBLAccessor.findByKey(dsPoTpTMsg);

            if (dsPoTpTMsg != null) {
                if (DS_PO_TP.BUYBACK_PO.equals(bizMsg.dsPoTpCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm, dsPoTpTMsg.fromBizAppLocChkKeyId);
                } else {
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm, dsPoTpTMsg.toBizAppLocChkKeyId);
                }
            }
        }
        String type = NMXC100001EnableWH.getLocRoleTpForPopup(bizMsg.glblCmpyCd.getValue(), BIZ_ID, bizMsg.xxPopPrm.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm, type);
    }

    /**
     * Return from NPAL1010(Location Popup)
     * @param cMsg NPAL1500CMsg
     * @param glblMsg NPAL1500SMsg
     */
    private void doProcess_NPAL1500_NPAL1010(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        if (SCR_EVENT_NM_LOCATION_TO_WH.equals(bizMsg.xxScrEventNm.getValue())) {

            S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getVendorInfo(bizMsg);

            if (ssmResult.isCodeNormal()) {
                List<Map> resultList = (List<Map>) ssmResult.getResultObject();

                if (0 < resultList.size()) {
                    Map recode = resultList.get(0);
                    ZYPEZDItemValueSetter.setValue(bizMsg.carrCd, (String) recode.get(DB_COLUMN_PRF_CARR_CD));
                    ZYPEZDItemValueSetter.setValue(bizMsg.carrNm, (String) recode.get(DB_COLUMN_CARR_NM));
                }
            }

            // Get WH Name & Address
            // START 2017/11/08 [QC#21849, MOD]
            // NPAL1500CommonLogic.setRtlWhInfo(bizMsg, glblMsg);
            if (!ZYPCommonFunc.hasValue(bizMsg.destRtlWhCd_DS)) {
                return;
            }
            NPAL1500CommonLogic.setDestWhInfo(bizMsg, glblMsg);
            if (bizMsg.destRtlWhCd_DS.isError()) {
                return;
            }
            // QC#23007
            if (!NPAL1500CommonLogic.isManualDropShipWHCd(bizMsg.glblCmpyCd.getValue(), bizMsg.destRtlWhCd_DS.getValue()) //
                    && !(RTL_WH_CATG.TECH_CAR_STOCK.equals(bizMsg.rtlWhCatgCd_DS.getValue()) || RTL_WH_CATG.CUSTOMER.equals(bizMsg.rtlWhCatgCd_DS.getValue()))) {

                NPAL1500CommonLogic.setShipToAddressFromDestinationWH(bizMsg, glblMsg);
            }
            NPAL1500CommonLogic.setCSARtrnAddr(bizMsg, glblMsg);
            // END   2017/11/08 [QC#21849, MOD]

        } else if ((SCR_EVENT_NM_LOCATION_FROM_SWH.equals(bizMsg.xxScrEventNm.getValue())) || (SCR_EVENT_NM_LOCATION_TO_SWH.equals(bizMsg.xxScrEventNm.getValue()))) {
            int selectIdx = bizMsg.xxNum.getValueInt();

            if (SCR_EVENT_NM_LOCATION_FROM_SWH.equals(bizMsg.xxScrEventNm.getValue())) {
                setFromSWH(bizMsg, glblMsg, selectIdx);
            } else if (SCR_EVENT_NM_LOCATION_TO_SWH.equals(bizMsg.xxScrEventNm.getValue())) {
                setToSWH(bizMsg, glblMsg, selectIdx);
            }

            // Buyback
            if (DS_PO_TP.BUYBACK_PO.equals(bizMsg.dsPoTpCd.getValue()) && (!ZYPCommonFunc.hasValue(bizMsg.A.no(selectIdx).entDealNetUnitPrcAmt_A1))) {

                NLXC001001GetInventoryItemCostBean bean = new NLXC001001GetInventoryItemCostBean();

                bean.setGlblCmpyCd(bizMsg.glblCmpyCd.getValue());
                bean.setInvtyLocCd(bizMsg.destRtlWhCd_DS.getValue() + bizMsg.A.no(selectIdx).destRtlSwhCd_A1.getValue());
                bean.setMdseCd(bizMsg.A.no(selectIdx).mdseCd_A1.getValue());

                bean = NLXC001001GetInventoryItemCost.getInventoryItemCost(bean);

                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(selectIdx).entDealNetUnitPrcAmt_A1, bean.getUnitPrcAmt());
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(selectIdx).entPoDtlDealNetAmt_A1, bean.getUnitPrcAmt().multiply(new BigDecimal(bizMsg.A.no(selectIdx).poDispQty_A1.getValueInt())));
            }
        }
    }

    /**
     * setToSWH (From Location Popup)
     * @param bizMsg NPAL1500CMsg
     * @param glblMsg NPAL1500SMsg
     * @param selectIdx int
     */
    private void setToSWH(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg, int selectIdx) {
        // check SetItem
        if (PO_MDSE_CMPSN_TP.PARENT.equals(bizMsg.A.no(selectIdx).poMdseCmpsnTpCd_HD.getValue())) {
            String swhCd = bizMsg.A.no(selectIdx).destRtlSwhCd_A1.getValue();
            String lineNum = bizMsg.A.no(selectIdx).dispPoDtlLineNum_A1.getValue();
            String checkNum = lineNum.substring(0, lineNum.indexOf(PERIOD) + 1);
            int pageNation = bizMsg.xxPageShowFromNum.getValueInt() - 1;

            // Set sMsg
            for (int i = selectIdx + pageNation + 1; i < glblMsg.A.getValidCount(); i++) {
                String chkLine = glblMsg.A.no(i).dispPoDtlLineNum_A1.getValue();

                if (chkLine.startsWith(checkNum)) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).destRtlSwhCd_A1, swhCd);
                } else {
                    break;
                }
            }

            // Set cMsg
            for (int i = selectIdx + 1; i < bizMsg.A.getValidCount(); i++) {
                String chkLine = bizMsg.A.no(i).dispPoDtlLineNum_A1.getValue();

                if (chkLine.startsWith(checkNum)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).destRtlSwhCd_A1, swhCd);
                } else {
                    break;
                }
            }
        }
    }

    /**
     * setFromSWH (From Location Popup)
     * @param bizMsg NPAL1500CMsg
     * @param glblMsg NPAL1500SMsg
     * @param selectIdx int
     */
    private void setFromSWH(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg, int selectIdx) {
        // check SetItem
        if (PO_MDSE_CMPSN_TP.PARENT.equals(bizMsg.A.no(selectIdx).poMdseCmpsnTpCd_HD.getValue())) {
            String swhCd = bizMsg.A.no(selectIdx).srcRtlSwhCd_A1.getValue();
            String lineNum = bizMsg.A.no(selectIdx).dispPoDtlLineNum_A1.getValue();
            String checkNum = lineNum.substring(0, lineNum.indexOf(PERIOD) + 1);
            int pageNation = bizMsg.xxPageShowFromNum.getValueInt() - 1;

            // Set sMsg
            for (int i = selectIdx + pageNation + 1; i < glblMsg.A.getValidCount(); i++) {
                String chkLine = glblMsg.A.no(i).dispPoDtlLineNum_A1.getValue();

                if (chkLine.startsWith(checkNum)) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).srcRtlSwhCd_A1, swhCd);
                } else {
                    break;
                }
            }

            // Set cMsg
            for (int i = selectIdx + 1; i < bizMsg.A.getValidCount(); i++) {
                String chkLine = bizMsg.A.no(i).dispPoDtlLineNum_A1.getValue();

                if (chkLine.startsWith(checkNum)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).srcRtlSwhCd_A1, swhCd);
                } else {
                    break;
                }
            }
        }
    }

    /**
     * doProcess_NPAL1500Scrn00_CMN_Clear
     * @param bizMsg NPAL1500CMsg
     */
    private void doProcess_NPAL1500Scrn00_CMN_Clear(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {
        doProcess_NPAL1500_INIT(bizMsg, glblMsg);
        bizMsg.poNum.clear();
    }

    /**
     * doProcess_NPAL1500Scrn00_CMN_Reset
     * @param bizMsg NPAL1500CMsg
     * @param bizMsg NPAL1500SMsg
     */
    private void doProcess_NPAL1500Scrn00_CMN_Reset(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.poNum)) {
            doProcess_NPAL1500Scrn00_Search(bizMsg, glblMsg);
        } else {
            doProcess_NPAL1500_INIT(bizMsg, glblMsg);
        }
    }

    /**
     * doProcess_NPAL1500Scrn00_PageNext
     * @param bizMsg NPAL1500CMsg
     * @param bizMsg NPAL1500SMsg
     */
    private void doProcess_NPAL1500Scrn00_PageNext(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        NPAL1500CommonLogic.copyFromCmsgOntoSmsg(bizMsg, glblMsg);

        if (TAB_DETAIL.equals(bizMsg.xxDplyTab.getValue())) {
            // set values to items of pageNation for next page
            // pageNation
            ZYPTableUtil.clear(bizMsg.A);
            bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
            bizMsg.xxPageShowToNum.clear();
            // QC#21170
            // Set rqstRcvDt (Date & Time Needed)
            NPAL1500CommonLogic.setHeaderRqstRcvDt_SmsgMaxVal(bizMsg, glblMsg);
            // START 2023/02/14 S.Dong [QC#60966, ADD]
            // Set rqstShipDt (Vendor Ship Date)
            NPAL1500CommonLogic.setHeaderRqstShipDt(bizMsg, glblMsg);
            // END 2023/02/14 S.Dong [QC#60966, ADD]
            //QC#26893 Add Start
            NPAL1500CommonLogic.clearAslUnitPrcAmt(bizMsg, glblMsg);
            //QC#26893 Add End
            NPAL1500CommonLogic.copyFromSMsgOntoCmsg(bizMsg, glblMsg);
            bizMsg.xxPageShowOfNum.setValue(glblMsg.A.getValidCount());
            
        } else if (TAB_AP_INVOICE.equals(bizMsg.xxDplyTab.getValue())) {
            // set values to items of pageNation for next page
            // pageNation
            ZYPTableUtil.clear(bizMsg.B);
            bizMsg.xxPageShowFromNum_B1.setValue(bizMsg.xxPageShowToNum_B1.getValueInt() + 1);
            bizMsg.xxPageShowToNum_B1.clear();

            NPAL1500CommonLogic.pagenationAP(bizMsg, glblMsg);
            bizMsg.xxPageShowOfNum_B1.setValue(glblMsg.B.getValidCount());
        }

    }

    /**
     * doProcess_NPAL1500Scrn00_PagePrev
     * @param bizMsg NPAL1500CMsg
     * @param bizMsg NPAL1500SMsg
     */
    private void doProcess_NPAL1500Scrn00_PagePrev(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        NPAL1500CommonLogic.copyFromCmsgOntoSmsg(bizMsg, glblMsg);

        if (TAB_DETAIL.equals(bizMsg.xxDplyTab.getValue())) {
            // set values to items of pageNation for next page
            // pageNation
            ZYPTableUtil.clear(bizMsg.A);
            bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
            bizMsg.xxPageShowToNum.clear();

            // QC#21170
            // Set rqstRcvDt (Date & Time Needed)
            NPAL1500CommonLogic.setHeaderRqstRcvDt_SmsgMaxVal(bizMsg, glblMsg);
            // START 2023/02/14 S.Dong [QC#60966, ADD]
            // Set rqstShipDt (Vendor Ship Date)
            NPAL1500CommonLogic.setHeaderRqstShipDt(bizMsg, glblMsg);
            // END 2023/02/14 S.Dong [QC#60966, ADD]
            NPAL1500CommonLogic.copyFromSMsgOntoCmsg(bizMsg, glblMsg);
            bizMsg.xxPageShowOfNum.setValue(glblMsg.A.getValidCount());

        } else if (TAB_AP_INVOICE.equals(bizMsg.xxDplyTab.getValue())) {
            // set values to items of pageNation for next page
            // pageNation
            ZYPTableUtil.clear(bizMsg.B);
            bizMsg.xxPageShowFromNum_B1.setValue(bizMsg.xxPageShowFromNum_B1.getValueInt() - bizMsg.B.length());
            bizMsg.xxPageShowToNum_B1.clear();

            NPAL1500CommonLogic.copyFromSMsgOntoCmsg(bizMsg, glblMsg);
            bizMsg.xxPageShowOfNum_B1.setValue(glblMsg.B.getValidCount());

        }
    }

    /**
     * doProcess_NPAL1500Scrn00_OpenWin_SerEnt
     * @param bizMsg NPAL1500CMsg
     * @param bizMsg NPAL1500SMsg
     */
    private void doProcess_NPAL1500Scrn00_OpenWin_SerEnt(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        NPAL1500CommonLogic.copyFromCmsgOntoSmsg(bizMsg, glblMsg);

        // Check PO Quantity
        if (!checkPOQty(bizMsg, glblMsg)) {
            return;
        }
        // QC#27024
        int idx = bizMsg.xxNum.getValueInt();

        // Index of SMsg Line
        int pageShowFromNum = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        int indexS = pageShowFromNum + idx;

        int serNumArrayLength = glblMsg.A.no(indexS).poDispQty_A1.getValueInt();
        String[] serNumTxt = glblMsg.A.no(indexS).serNumListTxt_A1.getValue().split(",");

        for (int i = 0; i < serNumArrayLength; i++) {
            String serNumber = "";
            if (serNumTxt.length > i) {
                serNumber = serNumTxt[i];
            }

            S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().chkRwsPutAwaySer(bizMsg.glblCmpyCd.getValue(), glblMsg.poNum.getValue(), glblMsg.A.no(indexS).poOrdDtlLineNum_A1.getValue(), serNumber);
            if (ssmResult.isCodeNormal()) {
                Map<String, Object> map = (Map<String, Object>) ssmResult.getResultObject();
                if (map == null || map.isEmpty()) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.T.no(i).xxEdtModeFlg_T, ZYPConstant.FLG_ON_Y);;
                }

                if (ZYPCommonFunc.hasValue((String) map.get("RWS_NUM_RPAS"))) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.T.no(i).xxEdtModeFlg_T, ZYPConstant.FLG_OFF_N);
                } else if (ZYPCommonFunc.hasValue((String) map.get("RWS_NUM_RSER"))) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.T.no(i).xxEdtModeFlg_T, ZYPConstant.FLG_OFF_N);
                } else {
                    ZYPEZDItemValueSetter.setValue(glblMsg.T.no(i).xxEdtModeFlg_T, ZYPConstant.FLG_ON_Y);
                }
            } else {
                ZYPEZDItemValueSetter.setValue(glblMsg.T.no(i).xxEdtModeFlg_T, ZYPConstant.FLG_ON_Y);
            }
        }

        // set Comment/Text Popup Data
        EZDMsg.copy(glblMsg.T, null, bizMsg.T, null);

    }

    /**
     * doProcess_NPAL1500Scrn00_OnChange_PoType
     * @param bizMsg NPAL1500CMsg
     * @param bizMsg NPAL1500SMsg
     */
    private void doProcess_NPAL1500Scrn00_OnChange_PoType(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        DS_PO_TPTMsg dsPoTpTMsg = new DS_PO_TPTMsg();

        ZYPEZDItemValueSetter.setValue(dsPoTpTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsPoTpTMsg.dsPoTpCd, bizMsg.dsPoTpCd);
        dsPoTpTMsg = (DS_PO_TPTMsg) EZDTBLAccessor.findByKey(dsPoTpTMsg);

        if (dsPoTpTMsg != null) {
            bizMsg.srcRtlWhCd_SC.clear();
            bizMsg.rtlWhNm_SC.clear();
            ZYPEZDItemValueSetter.setValue(bizMsg.fromBizAppLocChkKeyId, dsPoTpTMsg.fromBizAppLocChkKeyId);
            ZYPEZDItemValueSetter.setValue(bizMsg.toBizAppLocChkKeyId, dsPoTpTMsg.toBizAppLocChkKeyId);
            ZYPEZDItemValueSetter.setValue(bizMsg.enblAssetFlg_HD, dsPoTpTMsg.enblAssetFlg);
            bizMsg.srcRtlSwhCd_HD.clear();
        }
    }

    /**
     * doProcess_NPAL1500Scrn00_OpenWin_Account
     * @param bizMsg NPAL1500CMsg
     * @param bizMsg NPAL1500SMsg
     */
    private void doProcess_NPAL1500Scrn00_OpenWin_Account(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        // Copy Message
        NPAL1500CommonLogic.copyFromCmsgOntoSmsg(bizMsg, glblMsg);

        int idx = bizMsg.xxNum.getValueInt();
        int pageShowFromNum = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        // Index of SMsg Line
        int indexS = pageShowFromNum + idx;

        if (!ZYPCommonFunc.hasValue(glblMsg.A.no(indexS).mdseDescShortTxt_A1)) {
            glblMsg.A.no(indexS).mdseCd_A1.setErrorInfo(1, NPAM1585E);
            NPAL1500CommonLogic.copyFromSMsgOntoCmsg(bizMsg, glblMsg);
            return;
        }

        // START 02/18/2020 T.Ogura [QC#55916,ADD]
        String glblCmpyCd = glblMsg.glblCmpyCd.getValue();
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (!NPAL1500CommonLogic.checkManualSegmentElementForSMsg(bizMsg, glblMsg, glblCmpyCd, BIZ_ID, i)) {
                NPAL1500CommonLogic.copyFromSMsgOntoCmsg(bizMsg, glblMsg);
                if (!ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
                    bizMsg.setMessageInfo(NLAM0077E, new String[] {"Charge ACC" });
                }
                return;
            }
        }
        // END   02/18/2020 T.Ogura [QC#55916,ADD]

        // Set Account Info
        // QC#15817 Mod.
//        if ((!ZYPCommonFunc.hasValue(bizMsg.A.no(bizMsg.xxNum.getValueInt()).xxScrItem130Txt_CH) && EVENT_NM_NPAL1500_OPENWIN_ACCT_CHRG.equals(bizMsg.getScreenAplID()))
//                || (!ZYPCommonFunc.hasValue(bizMsg.A.no(bizMsg.xxNum.getValueInt()).xxScrItem130Txt_AC) && EVENT_NM_NPAL1500_OPENWIN_ACCT_ACRL.equals(bizMsg.getScreenAplID()))
//                || (!ZYPCommonFunc.hasValue(bizMsg.A.no(bizMsg.xxNum.getValueInt()).xxScrItem130Txt_VA) && EVENT_NM_NPAL1500_OPENWIN_ACCT_VAR.equals(bizMsg.getScreenAplID()))) {
        if ((!ZYPCommonFunc.hasValue(bizMsg.A.no(bizMsg.xxNum.getValueInt()).xxScrItem130Txt_CH) && EVENT_NM_NPAL1500_OPENWIN_ACCT_CHRG.equals(bizMsg.getScreenAplID()))) {
            NPAL1500CommonLogic.getAccountInfo(bizMsg, glblMsg, indexS, false);
        }

        NPAL1500CommonLogic.copyFromSMsgOntoCmsg(bizMsg, glblMsg);
        // QC#15817 Mod.
//        if (EVENT_NM_NPAL1500_OPENWIN_ACCT_CHRG.equals(bizMsg.getScreenAplID())) {
//            NPAL1500CommonLogic.checkManualSegmentElement(bizMsg, glblMsg, bizMsg.glblCmpyCd.getValue(), NPAL1500CommonLogic.AccountType.CH);
//        } else if (EVENT_NM_NPAL1500_OPENWIN_ACCT_ACRL.equals(bizMsg.getScreenAplID())) {
//            NPAL1500CommonLogic.checkManualSegmentElement(bizMsg, glblMsg, bizMsg.glblCmpyCd.getValue(), NPAL1500CommonLogic.AccountType.AC);
//        } else if (EVENT_NM_NPAL1500_OPENWIN_ACCT_VAR.equals(bizMsg.getScreenAplID())) {
//            NPAL1500CommonLogic.checkManualSegmentElement(bizMsg, glblMsg, bizMsg.glblCmpyCd.getValue(), NPAL1500CommonLogic.AccountType.VA);
//        }
        if (EVENT_NM_NPAL1500_OPENWIN_ACCT_CHRG.equals(bizMsg.getScreenAplID())) {
            NPAL1500CommonLogic.checkManualSegmentElement(bizMsg, glblMsg, bizMsg.glblCmpyCd.getValue(), NPAL1500CommonLogic.AccountType.CH);
        }
    }

    /**
     * doProcess_NPAL1500Scrn00_Get_MdseInfo
     * @param bizMsg NPAL1500CMsg
     * @param bizMsg NPAL1500SMsg
     */
    private void doProcess_NPAL1500Scrn00_Get_MdseInfo(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {
        int idx = bizMsg.xxNum.getValueInt();


        // initialize
        bizMsg.A.no(idx).mdseDescShortTxt_A1.clear();
        bizMsg.A.no(idx).aslMdseCd_A1.clear();
        // QC#26893 Add Start
        bizMsg.A.no(idx).aslUnitPrcAmt_A1.clear();
        bizMsg.A.no(idx).poDtlDiscPct_A1.clear();
        bizMsg.A.no(idx).entDealNetUnitPrcAmt_A1.clear();
        // QC#26893 Add End
//        bizMsg.A.no(idx).entDealNetUnitPrcAmt_A1.clear();
        bizMsg.A.no(idx).entPoDtlDealNetAmt_A1.clear();
        //bizMsg.A.no(idx).rcvSerTakeFlg_IB.clear();
        bizMsg.A.no(idx).instlBaseCtrlFlg_IB.clear();
        //bizMsg.A.no(idx).serNumListTxt_A1.clear();
        //bizMsg.A.no(idx).serNumListTxt_HD.clear();

        if (!bizMsg.dsPoTpCd.equals(DS_PO_TP.BUYBACK_PO)) {

            bizMsg.A.no(idx).prntCmpySetMdseFlg_HD.clear();
            bizMsg.A.no(idx).poMdseCmpsnTpCd_HD.clear();
//            bizMsg.A.no(idx).entDealNetUnitPrcAmt_A1.clear();
            bizMsg.A.no(idx).poDispUomCd_A1.clear();
            bizMsg.A.no(idx).aslDtlPk_HD.clear();
            bizMsg.A.no(idx).aslUnitPrcAmt_HD.clear();
//            bizMsg.A.no(idx).poDispQty_A1.clear();
        }

        // Copy Message
        NPAL1500CommonLogic.copyFromCmsgOntoSmsg(bizMsg, glblMsg);

        // Index of SMsg Line
        int pageShowFromNum = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        int indexS = pageShowFromNum + idx;

        boolean hasErr = false;

        //QC#62443 2024/3/1 Add Start
        String varCharExpSplyCoaCmpyCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_EXP_SPLY_COA_CMPY_CD, bizMsg.glblCmpyCd.getValue());
        String varCharExpSplyCoaBrCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_EXP_SPLY_COA_BR_CD, bizMsg.glblCmpyCd.getValue());
        String varCharExpSplyCoaAcctCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_EXP_SPLY_COA_ACCT_CD, bizMsg.glblCmpyCd.getValue());
        String varCharExpSplyCoaChCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_EXP_SPLY_COA_CH_CD, bizMsg.glblCmpyCd.getValue());
        String varCharExpSplyCoaAfflCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_EXP_SPLY_COA_AFFL_CD, bizMsg.glblCmpyCd.getValue());
        String varCharExpSplyCoaProjCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_EXP_SPLY_COA_PROJ_CD, bizMsg.glblCmpyCd.getValue());
        String varCharExpSplyCoaExtnCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_EXP_SPLY_COA_EXTN_CD, bizMsg.glblCmpyCd.getValue());

        MDSETMsg mdseTMsg = new MDSETMsg();

        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, glblMsg.A.no(indexS).mdseCd_A1.getValue());
        mdseTMsg = (MDSETMsg) EZDTBLAccessor.findByKey(mdseTMsg);

        S21SsmEZDResult result = NPAL1500Query.getInstance().getCoaCcCd(bizMsg.glblCmpyCd.getValue(), glblMsg.A.no(indexS).mdseCd_A1.getValue());

        String splyCoaCcCd = (String) result.getResultObject();

        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(indexS).splyCoaCcCd, splyCoaCcCd);
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(indexS).splyCoaProdCd, mdseTMsg.coaProdCd.getValue());

        StringBuffer sb = new StringBuffer();
        //QC#62443 2024/3/1 Add End
        // START 2024/1/1 K.Iwamoto [QC#62443, ADD]]
        NPAL1500CommonLogic.checkLineType(bizMsg, glblMsg, indexS);
        // END 2024/1/1 K.Iwamoto [QC#62443, ADD]]
        //QC#62443 2024/3/1 Add Start
        if (PO_LINE_TP.EXPENSE.equals(glblMsg.A.no(indexS).poLineTpCd_A1.getValue()) && ZYPCommonFunc.hasValue(glblMsg.A.no(indexS).mdseCd_A1.getValue())) {

            sb.append(varCharExpSplyCoaCmpyCd);
            sb.append(PERIOD);
            sb.append(varCharExpSplyCoaBrCd);
            sb.append(PERIOD);
            sb.append(glblMsg.A.no(indexS).splyCoaCcCd.getValue());
            sb.append(PERIOD);
            sb.append(varCharExpSplyCoaAcctCd);
            sb.append(PERIOD);
            sb.append(glblMsg.A.no(indexS).splyCoaProdCd.getValue());
            sb.append(PERIOD);
            sb.append(varCharExpSplyCoaChCd);
            sb.append(PERIOD);
            sb.append(varCharExpSplyCoaAfflCd);
            sb.append(PERIOD);
            sb.append(varCharExpSplyCoaProjCd);
            sb.append(PERIOD);
            sb.append(varCharExpSplyCoaExtnCd);

            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(indexS).xxScrItem130Txt_CH, sb.toString());

        } else if (ZYPConstant.FLG_OFF_N.equals(mdseTMsg.invtyCtrlFlg.getValue()) && !MDSE_TP_SET.equals(mdseTMsg.mdseTpCd.getValue())) {

            sb.append(varCharExpSplyCoaCmpyCd);
            sb.append(PERIOD);
            sb.append(varCharExpSplyCoaBrCd);
            sb.append(PERIOD);
            sb.append(glblMsg.A.no(indexS).splyCoaCcCd.getValue());
            sb.append(PERIOD);
            sb.append(varCharExpSplyCoaAcctCd);
            sb.append(PERIOD);
            sb.append(glblMsg.A.no(indexS).splyCoaProdCd.getValue());
            sb.append(PERIOD);
            sb.append(varCharExpSplyCoaChCd);
            sb.append(PERIOD);
            sb.append(varCharExpSplyCoaAfflCd);
            sb.append(PERIOD);
            sb.append(varCharExpSplyCoaProjCd);
            sb.append(PERIOD);
            sb.append(varCharExpSplyCoaExtnCd);

            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(indexS).xxScrItem130Txt_CH, sb.toString());
        }
        //QC#62443 2024/3/1 Add End

        // START 2017/12/04 [QC#14858, ADD]
        // Check:Item# or Item Description is mandatory.
        if (NPAL1500CommonLogic.checkItemInfo(bizMsg, glblMsg, indexS)) {
            hasErr = true;
        }
        if (!hasErr) {

            // END 2017/12/04 [QC#14858, ADD]

            // Set Item Info
            if (DS_PO_TP.BUYBACK_PO.equals(bizMsg.dsPoTpCd.getValue())) {

                bizMsg.A.no(idx).mdseDescShortTxt_A1.clear();
                bizMsg.A.no(idx).aslMdseCd_A1.clear();
                // bizMsg.A.no(idx).entDealNetUnitPrcAmt_A1.clear();
                bizMsg.A.no(idx).entPoDtlDealNetAmt_A1.clear();

                if (!NPAL1500CommonLogic.setDeriveItemInfoBB(bizMsg, glblMsg, indexS)) {

                    hasErr = true;
                }

            } else {

                // Set PRNT_CMPY_SET_MDSE_FLG
                if (NPAL1500CommonLogic.setPrntCmpySetMdseFlg(bizMsg, glblMsg, indexS)) {

                    hasErr = NPAL1500CommonLogic.setMdseInfo(bizMsg, glblMsg, indexS, hasErr, false);

                } else {

                    hasErr = true;
                }
            }
            // START 2017/12/04 [QC#14858, ADD]
        }
        // END 2017/12/04 [QC#14858, ADD]

        // QC#18761 Add.
        if (!NPAL1500CommonLogic.chkMdseDuplicateFromAsl(bizMsg, glblMsg, bizMsg.glblCmpyCd.getValue(), glblMsg.A.no(indexS))) {
    		hasErr = true;
    	}

        // QC#27770 Add Start
        String glblCmpyCd = glblMsg.glblCmpyCd.getValue();
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (!NPAL1500CommonLogic.checkManualSegmentElementForSMsg(bizMsg, glblMsg, glblCmpyCd, BIZ_ID, i)) {
                if (!ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
                    bizMsg.setMessageInfo(NLAM0077E, new String[] {"Charge ACC" });
                }
                hasErr = true;
            }
        }
        // QC#27770 Add End

        // Set Account Info
        if (!hasErr) {

            // 2018/12/19 QC#29456 Add Start
//            NPAL1500CommonLogic.getMdseAccount(bizMsg, glblMsg, indexS);
            NPAL1500CommonLogic.getAccountInfo(bizMsg, glblMsg, indexS, true);

            // Charg ACC For Set Child.
            if (ZYPConstant.FLG_ON_Y.equals(glblMsg.A.no(indexS).prntCmpySetMdseFlg_HD.getValue())) {
                String prntLinNum = glblMsg.A.no(indexS).poOrdDtlLineNum_A1.getValue();
                for (int curIndex = 0; curIndex < glblMsg.A.getValidCount(); curIndex++) {
                    if (S21StringUtil.isEquals(prntLinNum, glblMsg.A.no(curIndex).setPoOrdDtlLineNum.getValue())) {
                        NPAL1500CommonLogic.getAccountInfo(bizMsg, glblMsg, curIndex, false);
                    }
                }
            }
            // 2018/12/19 QC#29456 Add End

            List<BigDecimal> ordDtlLineList = new ArrayList<BigDecimal>();
            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                ordDtlLineList.add(new BigDecimal(glblMsg.A.no(i).dispPoDtlLineNum_A1.getValue()));
            }
            
            List<BigDecimal> ordDtlLineListTemp = new ArrayList<BigDecimal>();
            String ordDtlLineNum;
            String integralPart;
            String fractionPart;
            
            for (BigDecimal ordDtlLine : ordDtlLineList) {
                
                ordDtlLineNum = ordDtlLine.toPlainString();
                integralPart = ordDtlLineNum.substring(0, ordDtlLineNum.indexOf(PERIOD));
                fractionPart = ordDtlLineNum.substring(ordDtlLineNum.indexOf(PERIOD)+1, ordDtlLineNum.length());
                if (fractionPart.length() == 1 && !ZERO_STRING.equals(fractionPart)) {
                    fractionPart = ZERO_STRING + fractionPart;
                }
                ordDtlLine = new BigDecimal(integralPart + PERIOD + fractionPart);
                ordDtlLineListTemp.add(ordDtlLine);
            }
            Collections.sort(ordDtlLineListTemp);
            
            ordDtlLineList.clear();
            for (BigDecimal ordDtlLine : ordDtlLineListTemp) {
                
                ordDtlLineNum = ordDtlLine.toPlainString();
                integralPart = ordDtlLineNum.substring(0, ordDtlLineNum.indexOf(PERIOD));
                fractionPart = ordDtlLineNum.substring(ordDtlLineNum.indexOf(PERIOD)+1, ordDtlLineNum.length());
                if (fractionPart.length() > 1 && fractionPart.startsWith(ZERO_STRING)) {
                    fractionPart = fractionPart.substring(1);
                }
                ordDtlLine = new BigDecimal(integralPart + PERIOD + fractionPart);
                ordDtlLineList.add(ordDtlLine);
            }

            // QC#16108
            List<Integer> excludeLineList = new ArrayList<Integer>();
            for (int j = 0; j < ordDtlLineList.size(); j++) {
                for (int idxPoDetail = 0; idxPoDetail < glblMsg.A.getValidCount(); idxPoDetail++) {
                    if (NPAL1500CommonLogic.sortSetPoOrdDtlLineNum(ordDtlLineList, glblMsg, j, idxPoDetail, excludeLineList)) {
                        break;
                    }
                }
            }
            // 2018/12/19 QC#29456 ADD Start
            if (glblMsg.A.no(indexS).xxScrItem130Txt_CH.isError()) {
                String prntPoOrdDtlLineNum = glblMsg.A.no(indexS).poOrdDtlLineNum_A1.getValue();
                EZDMessageInfo ezdMessageInfo = null;
                if (glblMsg.A.no(indexS).xxScrItem130Txt_CH.isError()) {
                    ezdMessageInfo = NPAL1500CommonLogic.getErrorInfo(glblMsg.A.no(indexS), "xxScrItem130Txt_CH");
                } else {
                    for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                        if (S21StringUtil.isEquals(prntPoOrdDtlLineNum, glblMsg.A.no(i).setPoOrdDtlLineNum.getValue()) //
                                && glblMsg.A.no(i).xxScrItem130Txt_CH.isError()) {
                            ezdMessageInfo = NPAL1500CommonLogic.getErrorInfo(glblMsg.A.no(i), "xxScrItem130Txt_CH");
                        }
                    }
                }
                if (ezdMessageInfo != null) {
                    String msgCode = ezdMessageInfo.getCode();
                    String[] msgPara = ezdMessageInfo.getParameter();
                    if (msgPara == null) {
                        bizMsg.setMessageInfo(msgCode);
                    } else {
                        bizMsg.setMessageInfo(msgCode, msgPara);
                    }
                }
            }
            // 2018/12/19 QC#29456 Add End

            S21SortTarget target = new S21SortTarget(glblMsg.A, glblMsg.A.no(0).getBaseContents());
            S21SortKey key = new S21SortKey();
            key.add("poOrdDtlLineNum_A1", "ASC");
            S21EZDMsgArraySort.sort(target, key, 0, glblMsg.A.getValidCount());
        }

        // QC#21170
        // Set rqstRcvDt (Date & Time Needed)
        NPAL1500CommonLogic.setHeaderRqstRcvDt_SmsgMaxVal(bizMsg, glblMsg);
        // START 2023/02/14 S.Dong [QC#60966, ADD]
        // Set rqstShipDt (Vendor Ship Date)
        NPAL1500CommonLogic.setHeaderRqstShipDt(bizMsg, glblMsg);
        // END 2023/02/14 S.Dong [QC#60966, ADD]
        EZDMsg.copy(glblMsg.A.no(indexS), null, bizMsg.A.no(idx), null);
        // renew details
        bizMsg.xxPageShowOfNum.setValue(glblMsg.A.getValidCount());

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).mdseDescShortTxt_A1)) {
                continue;
            }

            NPAL1500CommonLogic.getDefaultAccountInfo(bizMsg, glblMsg, bizMsg.glblCmpyCd.getValue(), i);
        }
        NPAL1500CommonLogic.copyFromSMsgOntoCmsg(bizMsg, glblMsg);

        NPAL1500CommonLogic.pagenation(bizMsg, glblMsg);
    }

//    /**
//     * setMdseInfo
//     * @param bizMsg NPAL1500CMsg
//     * @param glblMsg NPAL1500SMsg
//     * @param indexS int
//     * @param hasErr boolean
//     * @return boolean
//     */
//    private boolean setMdseInfo(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg, int indexS, boolean hasErr) {
//        boolean result = hasErr;
//        if (ZYPConstant.FLG_ON_Y.equals(glblMsg.A.no(indexS).prntCmpySetMdseFlg_HD.getValue())) {
//
//            // Set Parent Item
//            if (setDeriveItemInfo(bizMsg, glblMsg, indexS)) {
//
//                // Set Component Item(s)
//                if (!deriveSetComponent(bizMsg, glblMsg, indexS)) {
//
//                    result = true;
//                }
//
//            } else {
//
//                result = true;
//            }
//
//        } else {
//
//            // Other
//            if (!setDeriveItemInfo(bizMsg, glblMsg, indexS)) {
//
//                result = true;
//            }
//        }
//        return result;
//    }

    /**
     * doProcess_NPAL1500Scrn00_OnChange_TrsmtMethod
     * @param bizMsg NPAL1500CMsg
     * @param bizMsg NPAL1500SMsg
     */
    private void doProcess_NPAL1500Scrn00_OnChange_TrsmtMethod(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        // Get Account Info
        bizMsg.sendPoEmlAddr.clear();
        bizMsg.sendPoFaxNum.clear();
        glblMsg.sendPoEmlAddr.clear();
        glblMsg.sendPoFaxNum.clear();
        setTrsmtMethInfo(bizMsg, glblMsg);        
    }

    /**
     * doProcess_NPAL1500Scrn00_Get_ShipToInfo
     * @param bizMsg NPAL1500CMsg
     * @param glblMsg NPAL1500SMsg
     */
    private void doProcess_NPAL1500Scrn00_Get_ShipToInfo(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        // Get WH Name & Address
        // START 2017/11/08 [QC#21849, MOD]
        // NPAL1500CommonLogic.setRtlWhInfo(bizMsg, glblMsg);
        if (!ZYPCommonFunc.hasValue(bizMsg.destRtlWhCd_DS)) {
            return;
        }
        NPAL1500CommonLogic.setDestWhInfo(bizMsg, glblMsg);
        if (bizMsg.destRtlWhCd_DS.isError()) {
            return;
        }
        
        //QC#25024 Add Start
        bizMsg.xxDplyCtrlFlg_DS.clear();

        if (NPAL1500CommonLogic.isManualDropShipWHCd(bizMsg.glblCmpyCd.getValue(), bizMsg.destRtlWhCd_DS.getValue())){
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyCtrlFlg_DS, ZYPConstant.FLG_ON_Y);            
        }
        //QC#25024 Add End
        
        // QC#23007
        if (!NPAL1500CommonLogic.isManualDropShipWHCd(bizMsg.glblCmpyCd.getValue(), bizMsg.destRtlWhCd_DS.getValue()) //
                && !(RTL_WH_CATG.TECH_CAR_STOCK.equals(bizMsg.rtlWhCatgCd_DS.getValue()) || RTL_WH_CATG.CUSTOMER.equals(bizMsg.rtlWhCatgCd_DS.getValue()))) {

            NPAL1500CommonLogic.setShipToAddressFromDestinationWH(bizMsg, glblMsg);
        }
        NPAL1500CommonLogic.setCSARtrnAddr(bizMsg, glblMsg);
        // END   2017/11/08 [QC#21849, MOD]
    }

    /**
     * doProcess_NPAL1500Scrn00_Copy
     * @param bizMsg NPAL1500CMsg
     * @param bizMsg NPAL1500SMsg
     */
    private void doProcess_NPAL1500Scrn00_Copy(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        doProcess_NPAL1500Scrn00_Search(bizMsg, glblMsg);

        if ("E".equals(bizMsg.getMessageKind()) || "W".equals(bizMsg.getMessageKind())) {
            return;
        }
        // set line Type list for Entry mode
        setPoLineTypeList(bizMsg, true);
        setDsOrdTpList(bizMsg, true);

        // Set Default Value
        bizMsg.poNum.clear();
        bizMsg.poStsCd_H.clear();
        bizMsg.poHdrStsCd.clear();
        bizMsg.poHdrStsDescTxt.clear();
        bizMsg.poApvlStsCd.clear();
        bizMsg.poApvlStsDescTxt.clear();
        bizMsg.poApvlDt.clear();
        bizMsg.poQlfyCd.clear();
        bizMsg.xxDsMultMsgDplyTxt_SI.clear();
        bizMsg.trxRefNum.clear();
        bizMsg.poOrdCmntTxt.clear();
        bizMsg.xxDsMultMsgDplyTxt_RN.clear();
        bizMsg.xxDsMultMsgDplyTxt_SN.clear();
        bizMsg.Q.clear();
        glblMsg.poNum.clear();
        glblMsg.poStsCd_H.clear();
        glblMsg.poHdrStsCd.clear();
        glblMsg.poHdrStsDescTxt.clear();
        glblMsg.poApvlStsCd.clear();
        glblMsg.poApvlStsDescTxt.clear();
        glblMsg.poApvlDt.clear();
        glblMsg.xxDsMultMsgDplyTxt_SI.clear();
        glblMsg.xxDsMultMsgDplyTxt_RN.clear();
        glblMsg.xxDsMultMsgDplyTxt_SN.clear();
        glblMsg.poMsgSubmtPsnCd_SI.clear();
        glblMsg.poMsgSubmtPsnCd_RN.clear();
        glblMsg.poMsgSubmtPsnCd_SN.clear();
        glblMsg.poMsgPk_SI.clear();
        glblMsg.poMsgPk_RN.clear();
        glblMsg.poMsgPk_SN.clear();
        glblMsg.poQlfyCd.clear();
        glblMsg.vndCd_HG.clear();
        glblMsg.trxRefNum.clear();
        glblMsg.poOrdCmntTxt.clear();
        glblMsg.xxDsMultMsgDplyTxt_RN.clear();
        glblMsg.xxDsMultMsgDplyTxt_SN.clear();
        glblMsg.Q.clear();
        // QC#20735 Add.
        bizMsg.poDtlIntfcErrMsgTxt_SL.clear();
        bizMsg.poDtlIntfcErrMsgTxt_P0.clear();
        bizMsg.poDtlIntfcErrMsgTxt_P1.clear();
        bizMsg.xxDtTm_HP.clear();
        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(glblMsg.B);
        // QC#25714 Add Start
        bizMsg.xxTotAmt_AP.clear();
        // QC#25714 Add End
        
        String salesDt = ZYPDateUtil.getSalesDate(glblMsg.glblCmpyCd.getValue());
        if (ZYPDateUtil.compare(salesDt, bizMsg.rqstRcvDt.getValue()) > 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.rqstRcvDt, salesDt);
        }
        // START 2023/02/14 S.Dong [QC#60966, ADD]
        if (ZYPDateUtil.compare(salesDt, bizMsg.rqstShipDt.getValue()) > 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.rqstShipDt, salesDt);
        }
        // END 2023/02/14 S.Dong [QC#60966, ADD]
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            bizMsg.A.no(i).poStsCd_HD.clear();
            bizMsg.A.no(i).serNumListTxt_A1.clear();
            bizMsg.A.no(i).serNumListTxt_A2.clear();
            bizMsg.A.no(i).prchReqNum_A1.clear();
            bizMsg.A.no(i).prchReqLineNum_A1.clear();
            bizMsg.A.no(i).prchReqLineNum_HD.clear();
            bizMsg.A.no(i).prchReqLineSubNum.clear();
            bizMsg.A.no(i).poInvQty_A1.clear();
            bizMsg.A.no(i).svcConfigMstrPk_A1.clear();
            bizMsg.A.no(i).poOrdDtlCmntTxt_A1.clear();
            bizMsg.A.no(i).poSendTs.clear();
            bizMsg.A.no(i).poMatchTpCd_A1.clear();
            bizMsg.A.no(i).poMatchTpDescTxt_A1.clear();
            bizMsg.A.no(i).poRcvQty_A1.clear();
            bizMsg.A.no(i).poCancQty_A1.clear();
            bizMsg.A.no(i).vndInvtyLocCd_A1.clear();
            // QC#24413
            bizMsg.A.no(i).serNumFlg_A1.clear();
            // add start 2022/05/16 QC#57934
            bizMsg.A.no(i).poRcvQty_WO.clear();
            bizMsg.A.no(i).poInvQty_WO.clear();
            // add end 2022/05/16 QC#57934

            //QC#26893 Add Start
            if(PO_MDSE_CMPSN_TP.CHILD.equals(bizMsg.A.no(i).poMdseCmpsnTpCd_HD.getValue())){
                bizMsg.A.no(i).aslUnitPrcAmt_A1.clear();
            }
            //QC#26893 Add End
            
            // Line Status
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).poLineStsCd_A1, PO_LINE_STS.OPEN);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).poLineStsDescTxt_A1, PO_LINE_STS_TXT_OPEN);

            // START 2023/01/26 A.Cullano [QC#60975, ADD]
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).openPoFlg_A1, ZYPConstant.FLG_ON_Y);
            // END 2023/01/26 A.Cullano [QC#60975, ADD]

            //  Date Needed
            if (ZYPDateUtil.compare(salesDt, bizMsg.A.no(i).rqstRcvDt_A1.getValue()) > 0) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).rqstRcvDt_A1, salesDt);
            }
            // START 2023/02/14 S.Dong [QC#60966, ADD]
            // START 2023/08/14 S.Dong [QC#61753, MOD]
//            if (ZYPDateUtil.compare(salesDt, bizMsg.A.no(i).rqstShipDt_A1.getValue()) > 0) {
//                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).rqstShipDt_A1, salesDt);
//            }
            bizMsg.A.no(i).rqstShipDt_A1.clear();
            // END 2023/08/14 S.Dong [QC#61753, MOD]
            // END 2023/02/14 S.Dong [QC#60966, ADD]
        }

        for (int j = 0; j < glblMsg.A.getValidCount(); j++) {
            glblMsg.A.no(j).poStsCd_HD.clear();
            glblMsg.A.no(j).serNumListTxt_A1.clear();
            glblMsg.A.no(j).serNumListTxt_A2.clear();
            glblMsg.A.no(j).prchReqLineNum_A1.clear();
            glblMsg.A.no(j).prchReqLineNum_HD.clear();
            glblMsg.A.no(j).prchReqLineSubNum.clear();
            glblMsg.A.no(j).poInvQty_A1.clear();
            glblMsg.A.no(j).svcConfigMstrPk_A1.clear();
            glblMsg.A.no(j).poOrdDtlCmntTxt_A1.clear();
            glblMsg.A.no(j).poMatchTpCd_A1.clear();
            glblMsg.A.no(j).poMatchTpDescTxt_A1.clear();
            glblMsg.A.no(j).poRcvQty_A1.clear();
            glblMsg.A.no(j).poCancQty_A1.clear();
            glblMsg.A.no(j).serNumFlg_A1.clear();
            //QC#28146 Add Start
            glblMsg.A.no(j).prchReqNum_A1.clear();
            //QC#28146 Add End
            // QC#52547 Add Start
            glblMsg.A.no(j).poSendTs.clear();
            // QC#52547 Add End
            // add start 2022/05/16 QC#57934
            glblMsg.A.no(j).poRcvQty_WO.clear();
            glblMsg.A.no(j).poInvQty_WO.clear();
            // add end 2022/05/16 QC#57934
            //QC#26893 Add Start
            if(PO_MDSE_CMPSN_TP.CHILD.equals(glblMsg.A.no(j).poMdseCmpsnTpCd_HD.getValue())){
                glblMsg.A.no(j).aslUnitPrcAmt_A1.clear();
            }
            //QC#26893 Add End
            
            // Line Status
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(j).poLineStsCd_A1, PO_LINE_STS.OPEN);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(j).poLineStsDescTxt_A1, PO_LINE_STS_TXT_OPEN);

            //  Date Needed
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(j).rqstRcvDt_A1, bizMsg.rqstRcvDt);
            // START 2023/02/14 S.Dong [QC#60966, ADD]
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(j).rqstShipDt_A1, bizMsg.rqstShipDt);
            // END 2023/02/14 S.Dong [QC#60966, ADD]

            NPAL1500CommonLogic.getDefaultAccountInfo(bizMsg, glblMsg, bizMsg.glblCmpyCd.getValue(), j);
            
            // QC#21170 Start
            // Get Primary Vendor from ASL
            NPZC113001PMsg npzc113001PMsg = NPAL1500CommonLogic.execGetPrimVndFromAsl(bizMsg, glblMsg, j);
            if (ZYPCommonFunc.hasValue(npzc113001PMsg.vndLtDaysNum)) {
                NPAL1500CommonLogic.setRqstRcvDt(bizMsg, glblMsg, npzc113001PMsg.vndLtDaysNum.getValueInt(), j);
            } else {
                NPAL1500CommonLogic.setRqstRcvDt(bizMsg, glblMsg, 0, j);
            }
            // START 2023/02/14 S.Dong [QC#60966, ADD]
            // START 2023/04/28 S.Dong [QC#60966, MOD]
//            if (ZYPCommonFunc.hasValue(npzc113001PMsg.vndShipLtDaysNum)) {
//                NPAL1500CommonLogic.setRqstShipDt(bizMsg, glblMsg, npzc113001PMsg.vndShipLtDaysNum.getValueInt(), j);
//            } else {
//                NPAL1500CommonLogic.setRqstShipDt(bizMsg, glblMsg, 0, j);
//            }
            // START 2023/08/14 S.Dong [QC#61753, MOD]
            glblMsg.A.no(j).rqstShipDt_A1.clear();
//            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(j).rqstShipDt_A1, "");
//            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(j).rqstRcvDt_A1, glblMsg.A.no(j).rqstRcvDt_A1);
//            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(j).rqstShipDt_A1, glblMsg.A.no(j).rqstShipDt_A1);
            // Set rqstRcvDt (Date & Time Needed)
            NPAL1500CommonLogic.setHeaderRqstRcvDt_SmsgMaxVal(bizMsg, glblMsg);
            // Set rqstShipDt (Vendor Ship Date)
            NPAL1500CommonLogic.setHeaderRqstShipDt(bizMsg, glblMsg);
            // END 2023/08/14 S.Dong [QC#61753, MOD]
            // END 2023/04/28 S.Dong [QC#60966, MOD]
            // END 2023/02/14 S.Dong [QC#60966, ADD]
            // QC#21170 End

            // START 2023/01/26 A.Cullano [QC#60975, ADD]
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(j).openPoFlg_A1, ZYPConstant.FLG_ON_Y);
            // END 2023/01/26 A.Cullano [QC#60975, ADD]
        }

        PO_ORD_SRCTMsg poOrdSrcTMsg = new PO_ORD_SRCTMsg();

        ZYPEZDItemValueSetter.setValue(poOrdSrcTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(poOrdSrcTMsg.poOrdSrcCd, PO_ORD_SRC.PO_ENTRY);

        poOrdSrcTMsg = (PO_ORD_SRCTMsg) EZDTBLAccessor.findByKey(poOrdSrcTMsg);

        ZYPEZDItemValueSetter.setValue(bizMsg.poOrdSrcCd, PO_ORD_SRC.PO_ENTRY);
        ZYPEZDItemValueSetter.setValue(bizMsg.poOrdSrcDescTxt, poOrdSrcTMsg.poOrdSrcDescTxt.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.poSubmtPsnCd, getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(bizMsg.fullPsnNm, getContextUserInfo().getUserDetails().getUserName());
    }

    /**
     * doProcess_NPAL1500Scrn00_ApplyConfig
     * @param bizMsg NPAL1500CMsg
     * @param bizMsg NPAL1500SMsg
     */
    private void doProcess_NPAL1500Scrn00_ApplyConfig(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        // Copy Message
        NPAL1500CommonLogic.copyFromCmsgOntoSmsg(bizMsg, glblMsg);

        int startIndex = glblMsg.A.getValidCount();

        applyConfig(bizMsg, glblMsg, startIndex);
    }

    /**
     * doProcess_NPAL1500Scrn00_NPAL0170
     * @param bizMsg NPAL1500CMsg
     * @param bizMsg NPAL1500SMsg
     */
    private void doProcess_NPAL1500Scrn00_NPAL0170(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {
        // copy msg.
        NPAL1500CommonLogic.copyFromCmsgOntoSmsg(bizMsg, glblMsg);
    }

    /**
     * doProcess_NPAL1500_Cancel
     * @param bizMsg NPAL1500CMsg
     * @param glblMsg NPAL1500SMsg
     */
    private void doProcess_NPAL1500_Cancel(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {
        if (ZYPConstant.FLG_ON_Y.equals(glblMsg.xxSrchTrfFlg_HD.getValue())) {
            // Re Search.
            doProcess_NPAL1500Scrn00_Search(bizMsg, glblMsg);
        } else {
            NPAL1500CommonLogic.copyFromSMsgOntoCmsg(bizMsg, glblMsg);
        }
    }

    /**
     * doProcess_Get_SupplierName
     * @param bizMsg NPAL1500CMsg
     * @param glblMsg NPAL1500SMsg
     */
    private void doProcess_Get_SupplierName(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {
        if (ZYPCommonFunc.hasValue(bizMsg.prntVndCd)) {
            S21SsmEZDResult result = NPAL1500Query.getInstance().getPrtVndNm(getGlobalCompanyCode(), bizMsg.prntVndCd.getValue());

            if (result.isCodeNormal()) {
                String prntName = (String) result.getResultObject();
                ZYPEZDItemValueSetter.setValue(bizMsg.prntVndNm, prntName);
            } else {
                bizMsg.prntVndCd.setErrorInfo(1, NPAM0076E, new String[] {DISPLAY_PRNT_VND_CD });
            }
        }
    }

    /**
     * doProcess_Get_SupplierSiteName
     * @param bizMsg NPAL1500CMsg
     * @param glblMsg NPAL1500SMsg
     */
    private void doProcess_Get_SupplierSiteName(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        NPAL1500CommonLogic.getSupplierName(bizMsg, glblMsg);
    }

    /**
     * doProcess_GetAddress
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     */
    private void doProcess_GetAddress(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg) {
        List<Map<String, Object>> list = null;
        String postCd = cMsg.shipToPostCd_ST.getValue();

        S21SsmEZDResult res = NPAL1500Query.getInstance().getAddrByPost(getGlobalCompanyCode(), postCd);

        if (res.isCodeNormal()) {
            list = (List<Map<String, Object>>) res.getResultObject();
        } else {
            // Post Code xxxxx-yyyy => use xxxxx.
            if (postCd.length() > 5) {
                res = NPAL1500Query.getInstance().getAddrByPost(getGlobalCompanyCode(), postCd.substring(0, 5));
                if (res.isCodeNormal()) {
                    list = (List<Map<String, Object>>) res.getResultObject();
                }
            }
        }

        if (list == null) {
            cMsg.shipToPostCd_ST.setErrorInfo(1, NMAM0039E, new String[] {"Ship to Location Postal Code" });
        } else {
            List<String> listCtyAddr = getDistinctValueList(list, "CTY_ADDR");
            List<String> listStCd = getDistinctValueList(list, "ST_CD");
            List<String> listCntyNm = getDistinctValueList(list, "CNTY_NM");

            if (listCtyAddr.size() == 1) {
                ZYPEZDItemValueSetter.setValue(cMsg.shipToCtyAddr_ST, listCtyAddr.get(0));
            } else {
                cMsg.shipToCtyAddr_ST.clear();
            }
            if (listStCd.size() == 1) {
                ZYPEZDItemValueSetter.setValue(cMsg.shipToStCd_ST, listStCd.get(0));
            } else {
                cMsg.shipToStCd_ST.clear();
            }
            if (listCntyNm.size() == 1) {
                ZYPEZDItemValueSetter.setValue(cMsg.shipToCntyNm_ST, listCntyNm.get(0));
            } else {
                cMsg.shipToCntyNm_ST.clear();
            }
        }
    }

    /**
     * getDistinctValueList
     * @param list List<Map<String, Object>>
     * @param colNm String
     * @return listDistinct List<String>
     */
    private List<String> getDistinctValueList(List<Map<String, Object>> list, String colNm) {
        List<String> listDistinct = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            String value = (String) map.get(colNm);
            if (ZYPCommonFunc.hasValue(value)) {
                if (!listDistinct.contains(value)) {
                    listDistinct.add(value);
                }
            }
        }
        return listDistinct;
    }

    /**
     * doProcess_FromNMAL2550
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     */
    private void doProcess_FromNMAL2550(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg) {

        NPAL1500CMsg bizMsg = cMsg;
        NPAL1500SMsg glblMsg = sMsg;

        int idx = bizMsg.xxNum.getValueInt();

        NPAL1500CommonLogic.copyFromCmsgOntoSmsg(bizMsg, glblMsg);

        // Index of SMsg Line
        int pageShowFromNum = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        int indexS = pageShowFromNum + idx;

        // check SetItem
        if (PO_MDSE_CMPSN_TP.PARENT.equals(bizMsg.A.no(idx).poMdseCmpsnTpCd_HD.getValue())) {
            String lineNum = bizMsg.A.no(idx).dispPoDtlLineNum_A1.getValue();
            String checkNum = lineNum.substring(0, lineNum.indexOf(PERIOD) + 1);
            int pageNation = bizMsg.xxPageShowFromNum.getValueInt() - 1;
            String accountName = bizMsg.xxTblNm_P1.getValue();

            if (accountName.equals(SCR_NM_ACCOUNT_TYPE_CH)) {

                // Set sMsg
                setChargeAccount(bizMsg, glblMsg, idx, checkNum, pageNation);

            }
            // QC#15817 Mod.
//            else if (accountName.equals(SCR_NM_ACCOUNT_TYPE_AC)) {
//
//                // Set sMsg
//                setAccuralAccount(bizMsg, glblMsg, idx, checkNum, pageNation);
//
//            } else if (accountName.equals(SCR_NM_ACCOUNT_TYPE_VA)) {
//
//                // Set sMsg
//                setVarianceAccount(bizMsg, glblMsg, idx, checkNum, pageNation);
//            }

        }

        EZDMsg.copy(glblMsg.A.no(indexS), null, bizMsg.A.no(idx), null);
        // renew details
        bizMsg.xxPageShowOfNum.setValue(glblMsg.A.getValidCount());
        NPAL1500CommonLogic.pagenation(bizMsg, glblMsg);

    }
    
// START 2017/11/08 [QC#21849, ADD]
    /**
     * doProcess_NPAL1500_NMAL6760
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     */
    private void doProcess_NPAL1500_NMAL6760(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.shipToCustCd)) {
            return;
        }
        // Get Ship to Customer Location Information
        NPAL1500CommonLogic.setShipToAddressFromShipToCustomer(bizMsg, glblMsg);
    }

    /**
     * doProcess_NPAL1500Scrn00_Get_ShipToInfoCustomer
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     */
    private void doProcess_NPAL1500Scrn00_Get_ShipToInfoCustomer(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.shipToCustCd)) {
            return;
        }
        // Get Ship to Customer Location Information
        NPAL1500CommonLogic.setShipToAddressFromShipToCustomer(bizMsg, glblMsg);

        // QC#23007
        if (ZYPCommonFunc.hasValue(bizMsg.destRtlWhCd_DS)) {

            NPAL1500CommonLogic.setDestWhInfo(bizMsg, glblMsg);

            if (!bizMsg.destRtlWhCd_DS.isError()) {
                if (!NPAL1500CommonLogic.isManualDropShipWHCd(bizMsg.glblCmpyCd.getValue(), bizMsg.destRtlWhCd_DS.getValue()) //
                        && !(RTL_WH_CATG.TECH_CAR_STOCK.equals(bizMsg.rtlWhCatgCd_DS.getValue()) || RTL_WH_CATG.CUSTOMER.equals(bizMsg.rtlWhCatgCd_DS.getValue()))) {

                    NPAL1500CommonLogic.setShipToAddressFromDestinationWH(bizMsg, glblMsg);
                }
            } else if (ZYPCommonFunc.hasValue(bizMsg.shipToCustCd)) {

                bizMsg.rtlWhNm_DS.clear();
                bizMsg.rtlWhCatgCd_DS.clear();

            }
        } else {
            if (ZYPCommonFunc.hasValue(bizMsg.shipToCustCd)) {
                //QC#27422 Mod Start
                String mdWhCd = NPAL1500CommonLogic.getFirstManualDropShipWHCd(bizMsg.glblCmpyCd.getValue());
                bizMsg.destRtlWhCd_DS.setValue(mdWhCd);
                //bizMsg.destRtlWhCd_DS.setValue(MANUAL_DIRECT_SHIP_CUST_CD);
                //QC#27422 Mod End
                NPAL1500CommonLogic.setDestWhInfo(bizMsg, glblMsg);
            }
        }

        bizMsg.rtlWhCatgCd_DS.clear();
    }
// END   2017/11/08 [QC#21849, ADD]


// QC#15817 Delete.
//    /**
//     * setVarianceAccount
//     * @param bizMsg NPAL1500CMsg
//     * @param glblMsg NPAL1500SMsg
//     * @param idx int 
//     * @param checkNum String
//     * @param pageNation int
//     */
//    private void setVarianceAccount(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg, int idx, String checkNum, int pageNation) {
//        for (int i = idx + pageNation + 1; i < glblMsg.A.getValidCount(); i++) {
//            String chkLine = glblMsg.A.no(i).dispPoDtlLineNum_A1.getValue();
//
//            if (chkLine.startsWith(checkNum)) {
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).coaCmpyCd_VA, bizMsg.A.no(idx).coaCmpyCd_VA);
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).coaAfflCd_VA, bizMsg.A.no(idx).coaAfflCd_VA);
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).coaBrCd_VA, bizMsg.A.no(idx).coaBrCd_VA);
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).coaCcCd_VA, bizMsg.A.no(idx).coaCcCd_VA);
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).coaChCd_VA, bizMsg.A.no(idx).coaChCd_VA);
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).coaAcctCd_VA, bizMsg.A.no(idx).coaAcctCd_VA);
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).coaProdCd_VA, bizMsg.A.no(idx).coaProdCd_VA);
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).coaProjCd_VA, bizMsg.A.no(idx).coaProjCd_VA);
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).coaExtnCd_VA, bizMsg.A.no(idx).coaExtnCd_VA);
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxScrItem130Txt_VA, bizMsg.A.no(idx).xxScrItem130Txt_VA);
//            } else {
//                break;
//            }
//        }
//    }
//
//    /**
//     * setAccuralAccount
//     * @param bizMsg NPAL1500CMsg
//     * @param glblMsg NPAL1500SMsg
//     * @param idx int 
//     * @param checkNum String
//     * @param pageNation int
//     */
//    private void setAccuralAccount(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg, int idx, String checkNum, int pageNation) {
//        for (int i = idx + pageNation + 1; i < glblMsg.A.getValidCount(); i++) {
//            String chkLine = glblMsg.A.no(i).dispPoDtlLineNum_A1.getValue();
//
//            if (chkLine.startsWith(checkNum)) {
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).coaCmpyCd_AC, bizMsg.A.no(idx).coaCmpyCd_AC);
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).coaAfflCd_AC, bizMsg.A.no(idx).coaAfflCd_AC);
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).coaBrCd_AC, bizMsg.A.no(idx).coaBrCd_AC);
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).coaCcCd_AC, bizMsg.A.no(idx).coaCcCd_AC);
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).coaChCd_AC, bizMsg.A.no(idx).coaChCd_AC);
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).coaAcctCd_AC, bizMsg.A.no(idx).coaAcctCd_AC);
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).coaProdCd_AC, bizMsg.A.no(idx).coaProdCd_AC);
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).coaProjCd_AC, bizMsg.A.no(idx).coaProjCd_AC);
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).coaExtnCd_AC, bizMsg.A.no(idx).coaExtnCd_AC);
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxScrItem130Txt_AC, bizMsg.A.no(idx).xxScrItem130Txt_AC);
//            } else {
//                break;
//            }
//        }
//    }

    /**
     * setChargeAccount
     * @param bizMsg NPAL1500CMsg
     * @param glblMsg NPAL1500SMsg
     * @param idx int 
     * @param checkNum String
     * @param pageNation int
     */
    private void setChargeAccount(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg, int idx, String checkNum, int pageNation) {
        for (int i = idx + pageNation + 1; i < glblMsg.A.getValidCount(); i++) {
            String chkLine = glblMsg.A.no(i).dispPoDtlLineNum_A1.getValue();

            if (chkLine.startsWith(checkNum)) {

                if(PO_LINE_TP.EXPENSE.equals(bizMsg.A.no(idx).poLineTpCd_A1.getValue()) //
                        || PO_LINE_TP.EXPENSE_WITH_RECEIPT.equals(bizMsg.A.no(idx).poLineTpCd_A1.getValue())) { // 2019/01/15 QC#29778 Add Condition
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).splyCoaBrCd, bizMsg.A.no(idx).splyCoaBrCd.getValue());
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).splyCoaChCd, bizMsg.A.no(idx).splyCoaChCd.getValue());
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).splyCoaAfflCd, bizMsg.A.no(idx).splyCoaAfflCd.getValue());
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).splyCoaExtnCd, bizMsg.A.no(idx).splyCoaExtnCd.getValue());
                    
                    StringBuffer sb = new StringBuffer();
                    sb.append(glblMsg.A.no(i).splyCoaCmpyCd.getValue());
                    sb.append(PERIOD);
                    sb.append(glblMsg.A.no(i).splyCoaBrCd.getValue());
                    sb.append(PERIOD);
                    sb.append(glblMsg.A.no(i).splyCoaCcCd.getValue());
                    sb.append(PERIOD);
                    sb.append(glblMsg.A.no(i).splyCoaAcctCd.getValue());
                    sb.append(PERIOD);
                    sb.append(glblMsg.A.no(i).splyCoaProdCd.getValue());
                    sb.append(PERIOD);
                    sb.append(glblMsg.A.no(i).splyCoaChCd.getValue());
                    sb.append(PERIOD);
                    sb.append(glblMsg.A.no(i).splyCoaAfflCd.getValue());
                    sb.append(PERIOD);
                    sb.append(glblMsg.A.no(i).splyCoaProjCd.getValue());
                    sb.append(PERIOD);
                    sb.append(glblMsg.A.no(i).splyCoaExtnCd.getValue());

                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxScrItem130Txt_CH, sb.toString());
                } else {
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).splyCoaCmpyCd, bizMsg.A.no(idx).splyCoaCmpyCd.getValue());
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).splyCoaAfflCd, bizMsg.A.no(idx).splyCoaAfflCd.getValue());
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).splyCoaBrCd, bizMsg.A.no(idx).splyCoaBrCd.getValue());
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).splyCoaCcCd, bizMsg.A.no(idx).splyCoaCcCd.getValue());
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).splyCoaChCd, bizMsg.A.no(idx).splyCoaChCd.getValue());
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).splyCoaAcctCd, bizMsg.A.no(idx).splyCoaAcctCd.getValue());
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).splyCoaProdCd, bizMsg.A.no(idx).splyCoaProdCd.getValue());
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).splyCoaProjCd, bizMsg.A.no(idx).splyCoaProjCd.getValue());
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).splyCoaExtnCd, bizMsg.A.no(idx).splyCoaExtnCd.getValue());
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxScrItem130Txt_CH, bizMsg.A.no(idx).xxScrItem130Txt_CH.getValue());
                }
            } else {
                break;
            }
        }
    }


    /**
     * getCtry
     * @param glblCmpyCd String
     * @param ctryCd String
     * @return CTRYTMsg
     */
    private static CTRYTMsg getCtry(String glblCmpyCd, String ctryCd) {
        CTRYTMsg inMsg = new CTRYTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.ctryCd, ctryCd);
        CTRYTMsg outMsg = (CTRYTMsg) EZDTBLAccessor.findByKey(inMsg);
        return outMsg;
    }

    /**
     * getInitialData
     * @param cMsg NPAL1500CMsg
     * @param searchFlg boolean
     */
    private static void setInitialData(NPAL1500CMsg cMsg, boolean searchFlg) {

        String glblCmpyCd = cMsg.glblCmpyCd.getValue();

        // Create PullDown List
        createPullDownList(cMsg, searchFlg);

        // Qualifier Code for Customer Drop Ship PO
        String poCustDropShipQlfyCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_PO_CUST_DROP_SHIP_QLFY_CD, glblCmpyCd);
        if (ZYPCommonFunc.hasValue(poCustDropShipQlfyCd)) {
            ZYPEZDItemValueSetter.setValue(cMsg.varCharConstVal_VD, poCustDropShipQlfyCd);
        } else {
            ZYPEZDItemValueSetter.setValue(cMsg.varCharConstVal_VD, DEF_PO_CUST_DROP_SHIP_QLFY_CD);
        }
        ZYPEZDItemValueSetter.setValue(cMsg.varCharConstVal_VD, ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_PO_CUST_DROP_SHIP_QLFY_CD, glblCmpyCd));

        // CUSA Global Company Code
        String cusaGlblCmpyCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CUSA_GLBL_CMPY_CD, glblCmpyCd);
        if (ZYPCommonFunc.hasValue(cusaGlblCmpyCd)) {
            ZYPEZDItemValueSetter.setValue(cMsg.varCharConstVal_GC, cusaGlblCmpyCd);
        } else {
            ZYPEZDItemValueSetter.setValue(cMsg.varCharConstVal_GC, DEF_CUSA_GLBL_CMPY_CD);
        }

        String salesDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, salesDt);
        // START 2023/02/14 S.Dong [QC#60966, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.rqstShipDt, salesDt);
        // END 2023/02/14 S.Dong [QC#60966, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.xxOrdTs, ZYPDateUtil.formatEzd8ToDisp((String) salesDt));

        PO_ORD_SRCTMsg poOrdSrcTMsg = new PO_ORD_SRCTMsg();

        ZYPEZDItemValueSetter.setValue(poOrdSrcTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(poOrdSrcTMsg.poOrdSrcCd, PO_ORD_SRC.PO_ENTRY);

        poOrdSrcTMsg = (PO_ORD_SRCTMsg) EZDTBLAccessor.findByKey(poOrdSrcTMsg);

        ZYPEZDItemValueSetter.setValue(cMsg.poOrdSrcCd, PO_ORD_SRC.PO_ENTRY);
        ZYPEZDItemValueSetter.setValue(cMsg.poOrdSrcDescTxt, poOrdSrcTMsg.poOrdSrcDescTxt.getValue());

    }

    /**
     * createPullDownList
     * @param cMsg NPAL1500CMsg
     * @param searchFlg boolean
     */
    private static void createPullDownList(NPAL1500CMsg cMsg, boolean searchFlg) {

        if (searchFlg) {
            setDsOrdTpList(cMsg, false);
        } else {
            setDsOrdTpList(cMsg, true);
        }

        setPoLineTypeList(cMsg, true);
        setBusinessUnitList(cMsg);
        setFreightTermList(cMsg);
        setShippingMethodList(cMsg);
        setStockStatusList(cMsg);
        setTransmissionMethodTypeList(cMsg);
        setAmPmPulldownList(cMsg);
    }


    /**
     * getBusinessUnitList
     * @param cMsg NPAL1500CMsg
     */
    private static void setBusinessUnitList(NPAL1500CMsg cMsg) {

        S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getBusinessUnitList(cMsg);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.prchGrpCd_CD.no(i), (String) resultMap.get("PRCH_GRP_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.prchGrpDescTxt_NM.no(i), (String) resultMap.get("PRCH_GRP_DESC_TXT"));
            }
        }
    }

    /**
     * getFreightTermList
     * @param cMsg NPAL1500CMsg
     */
    private static void setFreightTermList(NPAL1500CMsg cMsg) {

        S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getFreightTermList(cMsg);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.frtCondCd_CD.no(i), (String) resultMap.get("FRT_COND_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.frtCondDescTxt_NM.no(i), (String) resultMap.get("FRT_COND_DESC_TXT"));
            }
        }
    }

    /**
     * getShippingMethodList
     * @param cMsg NPAL1500CMsg
     */
    private static void setShippingMethodList(NPAL1500CMsg cMsg) {

        S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getShippingMethodList(cMsg);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.shpgSvcLvlCd_CD.no(i), (String) resultMap.get("SHPG_SVC_LVL_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.shpgSvcLvlDescTxt_NM.no(i), (String) resultMap.get("SHPG_SVC_LVL_DESC_TXT"));
            }
        }
    }

    /**
     * getStockStatusList
     * @param cMsg NPAL1500CMsg
     */
    private static void setStockStatusList(NPAL1500CMsg cMsg) {

        S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getStockStatusList(cMsg);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.stkStsCd_CD.no(i), (String) resultMap.get("STK_STS_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.stkStsDescTxt_NM.no(i), (String) resultMap.get("STK_STS_DESC_TXT"));
            }
        }
    }

    /**
     * getTransmissionMethodTypeList
     * @param cMsg NPAL1500CMsg
     */
    private static void setTransmissionMethodTypeList(NPAL1500CMsg cMsg) {

        S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getTransmissionMethodTypeList(cMsg);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.trsmtMethTpCd_CD.no(i), (String) resultMap.get("TRSMT_METH_TP_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.trsmtMethTpDescTxt_NM.no(i), (String) resultMap.get("TRSMT_METH_TP_DESC_TXT"));
            }
        }
    }

    /**
     * <pre>
     * getPoLineTypeList
     * </pre>
     * @param cMsg NPAL1500CMsg
     */
    private static void setPoLineTypeList(NPAL1500CMsg cMsg, boolean scrEntAval) {

        S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getPoLineTypeList(cMsg, scrEntAval);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();

            // clear
            cMsg.poLineTpCd_CD.clear();
            cMsg.poLineTpDescTxt_NM.clear();

            for (int i = 0; i < resultList.size(); i++) {
                Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.poLineTpCd_CD.no(i), (String) resultMap.get("PO_LINE_TP_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.poLineTpDescTxt_NM.no(i), (String) resultMap.get("PO_LINE_TP_DESC_TXT"));
            }
        }
    }

    /**
     * Set AmPm Pulldown List
     * @param cMsg NPAL1500CMsg
     */
    private static void setAmPmPulldownList(NPAL1500CMsg cMsg) {
        cMsg.xxTpCd_PD.no(0).setValue(AM_PM_PULLDOWN_CD_AM);
        cMsg.xxTpNm_PD.no(0).setValue(AM_PM_PULLDOWN_NM_AM);
        cMsg.xxTpCd_PD.no(1).setValue(AM_PM_PULLDOWN_CD_PM);
        cMsg.xxTpNm_PD.no(1).setValue(AM_PM_PULLDOWN_NM_PM);
    }
    /**
     * getDsOrdTpList
     * @param cMsg NPAL1500CMsg
     * @param dplyFlg boolean
     */
    private static void setDsOrdTpList(NPAL1500CMsg cMsg, boolean dplyFlg) {

        S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getDsOrdTpList(cMsg, dplyFlg);

        // clear
        cMsg.dsPoTpCd_CD.clear();
        cMsg.dsPoTpDescTxt_NM.clear();

        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.dsPoTpCd_CD.no(i), (String) resultMap.get("DS_PO_TP_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.dsPoTpDescTxt_NM.no(i), (String) resultMap.get("DS_PO_TP_DESC_TXT"));
            }
        }
    }

//    /**
//     * add New Line (for Screen)
//     * @param glblMsg NPAL1500SMsg
//     * @param bizMsg NPAL1500CMsg
//     * @param index int
//     */
//    private static void addNewLine(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg, int index) {
//
//        PO_DTLTMsg podTMsg = new PO_DTLTMsg();
//        ZYPEZDItemValueSetter.setValue(podTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(podTMsg.poOrdNum, bizMsg.poNum);
//
//        podTMsg = (PO_DTLTMsg) EZDTBLAccessor.findByKey(podTMsg);
//        String maxPoOrdDtlLineNum = FIRST_DTL_LINE_NUM;
//        String poDtlLineNum = null;
//        if (podTMsg == null) {
//            if (glblMsg.A.getValidCount() > 0) {
//                poDtlLineNum = glblMsg.A.no(glblMsg.A.getValidCount() - 1).poOrdDtlLineNum_A1.getValue();
//                if (ZYPCommonFunc.hasValue(poDtlLineNum)) {
//                    maxPoOrdDtlLineNum = NPAL1500CommonLogic.addDtlLineNum(poDtlLineNum);
//                } else {
//                    maxPoOrdDtlLineNum = NPAL1500CommonLogic.addDtlLineNum(maxPoOrdDtlLineNum);
//                }
//            }
//        } else {
//            maxPoOrdDtlLineNum = NPAL1500CommonLogic.addDtlLineNum(getMaxPoDtlLineNum(bizMsg.glblCmpyCd.getValue(), podTMsg.poOrdNum.getValue()));
//        }
//
//        // Get Max PO Disp Line#
//        String maxDispPoDtlLineNum = Integer.parseInt(maxPoOrdDtlLineNum) + PERIOD + ZERO;
//
//        if (glblMsg.A.getValidCount() > 0) {
//            String tmpDispPoDtlLineNum = glblMsg.A.no(glblMsg.A.getValidCount() - 1).dispPoDtlLineNum_A1.getValue();
//            if (ZYPCommonFunc.hasValue(tmpDispPoDtlLineNum)) {
//                int dispLineNum = Integer.parseInt(tmpDispPoDtlLineNum.substring(0, tmpDispPoDtlLineNum.indexOf(PERIOD)));
//                dispLineNum++;
//                maxDispPoDtlLineNum = String.valueOf(dispLineNum) + PERIOD + ZERO;
//            }
//        }
//
//        // Get Default SWH
//        String srcRtlSwhCd = "";
//        String destRtlSwhCd = "";
//
//        // Get Default Source SWH
//        if (ZYPCommonFunc.hasValue(bizMsg.srcRtlWhCd_SC)) {
//            S21SsmEZDResult result = NPAL1500Query.getInstance().getRtlSwhCd(glblMsg.glblCmpyCd.getValue(), bizMsg.srcRtlWhCd_SC.getValue());
//
//            if (result.isCodeNormal()) {
//                srcRtlSwhCd = (String) result.getResultObject();
//            }
//        }
//        // Get Default Destination SWH
//        if (ZYPCommonFunc.hasValue(bizMsg.destRtlWhCd_DS)) {
//            S21SsmEZDResult result = NPAL1500Query.getInstance().getRtlSwhCd(glblMsg.glblCmpyCd.getValue(), bizMsg.destRtlWhCd_DS.getValue());
//
//            if (result.isCodeNormal()) {
//                destRtlSwhCd = (String) result.getResultObject();
//            }
//        }
//        // Add New Line
//        glblMsg.A.setValidCount(glblMsg.A.getValidCount() + 1);
//        int curIndex = glblMsg.A.getValidCount() - 1;
//
//        // Set New Line Flag
//        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).xxDplyCtrlFlg_NL, ZYPConstant.FLG_ON_Y);
//
//        // Set Default Value
//        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).poLineStsCd_A1, PO_LINE_STS.OPEN);
//        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).poLineStsDescTxt_A1, PO_LINE_STS_TXT_OPEN);
//
//        // Date Needed(Pin) Date Needed
//        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).rqstRcvDt_A1, glblMsg.rqstRcvDt);
//
//        // Source SWH(Pin) SRC_RTL_SWH_CD
//        if (ZYPCommonFunc.hasValue(srcRtlSwhCd)) {
//            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).srcRtlSwhCd_A1, srcRtlSwhCd);
//        } else {
//            glblMsg.A.no(curIndex).srcRtlSwhCd_A1.clear();
//        }
//
//        // Destination SWH(Pin) DEST_RTL_SWH_CD
//        // ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).destRtlSwhCd_A1,
//        // glblMsg.destRtlSwhCd_DS);
//        if (ZYPCommonFunc.hasValue(destRtlSwhCd)) {
//            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).destRtlSwhCd_A1, destRtlSwhCd);
//        } else {
//            glblMsg.A.no(curIndex).destRtlSwhCd_A1.clear();
//        }
//
//        // Stock Status(Pin)
//        if (!DS_PO_TP.BUYBACK_PO.equals(glblMsg.dsPoTpCd.getValue())) {
//            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).stkStsCd_A1, STK_STS.GOOD);
//        } else {
//            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).stkStsCd_A1, STK_STS.WAITING_FOR_INSPECTION);
//            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).poLineTpCd_A1, PO_LINE_TP.GOODS);
//        }
//
//        // Ext.Total Line
//        BigDecimal extTotal = new BigDecimal(ZERO);
//        if (ZYPCommonFunc.hasValue(glblMsg.A.no(curIndex).entDealNetUnitPrcAmt_A1) && ZYPCommonFunc.hasValue(glblMsg.A.no(curIndex).poDispQty_A1)) {
//            extTotal = glblMsg.A.no(index).entDealNetUnitPrcAmt_A1.getValue().multiply(glblMsg.A.no(curIndex).poDispQty_A1.getValue());
//        }
//        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).entPoDtlDealNetAmt_A1, extTotal);
//
//        // PO_MDSE_CMPSN_TP_CD
//        if (!DS_PO_TP.BUYBACK_PO.equals(glblMsg.dsPoTpCd)) {
//            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).poMdseCmpsnTpCd_HD, PO_MDSE_CMPSN_TP.REGULAR);
//        }
//        // PO Line#
//        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).poOrdDtlLineNum_A1, maxPoOrdDtlLineNum);
//        // PO Disp Line#
//        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(curIndex).dispPoDtlLineNum_A1, maxDispPoDtlLineNum);
//    }

    /**
     * applyConfig
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     * @param index int
     */
    private static void applyConfig(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg, int index) {

        // Check Duplicate Config
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).svcConfigMstrPk_A1)) {
                continue;
            }
            // QC#53384 Mod Start
            //if (cMsg.svcConfigMstrPk.getValue().compareTo((sMsg.A.no(i).svcConfigMstrPk_A1.getValue())) == 0) {
            if (ZYPCommonFunc.hasValue(cMsg.svcConfigMstrPk) && cMsg.svcConfigMstrPk.getValue().compareTo((sMsg.A.no(i).svcConfigMstrPk_A1.getValue())) == 0) {
            // QC#53384 Mod End
                cMsg.svcConfigMstrPk.setErrorInfo(1, NPAM0073E, new String[] {cMsg.svcConfigMstrPk.getValue().toPlainString() });
                return;
            }
        }

        if (ZYPCommonFunc.hasValue(sMsg.dsPoTpCd) && DS_PO_TP.BUYBACK_PO.equals(sMsg.dsPoTpCd.getValue())
                && ZYPCommonFunc.hasValue(sMsg.svcConfigMstrPk) && ZYPCommonFunc.hasValue(sMsg.srcRtlWhCd_SC)) {

            S21SsmEZDResult ssmRslt = NPAL1500Query.getInstance().getConfigComponents(sMsg);

            // The entered @ does not exist in Master.
            if (!ssmRslt.isCodeNormal()) {

                cMsg.svcConfigMstrPk.setErrorInfo(1, NPAM1361E, new String[] {cMsg.svcConfigMstrPk.getValue().toString() });
                cMsg.setMessageInfo("ZZM9037E");

                return;
            } else {

                // QC#23007
                if (ZYPCommonFunc.hasValue(cMsg.destRtlWhCd_DS)) {

                    NPAL1500CommonLogic.setDestWhInfo(cMsg, sMsg);

                    if (!cMsg.destRtlWhCd_DS.isError()) {
                        if (!NPAL1500CommonLogic.isManualDropShipWHCd(cMsg.glblCmpyCd.getValue(), cMsg.destRtlWhCd_DS.getValue()) //
                                && !(RTL_WH_CATG.TECH_CAR_STOCK.equals(cMsg.rtlWhCatgCd_DS.getValue()) || RTL_WH_CATG.CUSTOMER.equals(cMsg.rtlWhCatgCd_DS.getValue()))) {

                            NPAL1500CommonLogic.setShipToAddressFromDestinationWH(cMsg, sMsg);
                        }
                    } else if (ZYPCommonFunc.hasValue(cMsg.shipToCustCd)) {

                        cMsg.rtlWhNm_DS.clear();
                        cMsg.rtlWhCatgCd_DS.clear();

                    }
                } else {
                    if (ZYPCommonFunc.hasValue(cMsg.shipToCustCd)) {
                        //QC#27422 Mod Start
                        String mdWhCd = NPAL1500CommonLogic.getFirstManualDropShipWHCd(cMsg.glblCmpyCd.getValue());
                        cMsg.destRtlWhCd_DS.setValue(mdWhCd);
                        //bizMsg.destRtlWhCd_DS.setValue(MANUAL_DIRECT_SHIP_CUST_CD);
                        //QC#27422 Mod End
                        NPAL1500CommonLogic.setDestWhInfo(cMsg, sMsg);
                    }
                }

                List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmRslt.getResultObject();
                applyConfigDetail(cMsg, sMsg, index, rsltList);
            }

            // pagination after applyConfig
            paginationForAddNewline(cMsg, sMsg);
        }
    }

    /**
     * applyConfig Detail Lines
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     * @param index int
     * @param rsltList List<Map<String, Object>>
     */
    private static void applyConfigDetail(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg, int index, List<Map<String, Object>> rsltList) {
        for (Map<String, Object> rsltMap : rsltList) {

            String rtlWhCd = (String) rsltMap.get("RTL_WH_CD");
            String rtlSwhCd = (String) rsltMap.get("RTL_SWH_CD");
            String svcMachMaintAvalFlg = (String) rsltMap.get("SVC_MACH_MAINT_AVAL_FLG");
            String trxHdrNum = (String) rsltMap.get("TRX_HDR_NUM");
            String svcMachMstrStsCd = (String) rsltMap.get("SVC_MACH_MSTR_STS_CD");

            if (SVC_MACH_MSTR_STS.TERMINATED.equals(svcMachMstrStsCd)) {

                continue;

            } else if (!SVC_MACH_MSTR_STS.CREATED.equals(svcMachMstrStsCd) && !SVC_MACH_MSTR_STS.REMOVED.equals(svcMachMstrStsCd)) {

                cMsg.svcConfigMstrPk.setErrorInfo(1, NPAM1511E);
                cMsg.setMessageInfo("ZZM9037E");
                return;

            } else if (!ZYPConstant.FLG_ON_Y.equals(svcMachMaintAvalFlg) || (ZYPCommonFunc.hasValue(trxHdrNum) && !trxHdrNum.equals(sMsg.poNum.getValue()))) {

                cMsg.svcConfigMstrPk.setErrorInfo(1, NPAM1370E);
                cMsg.setMessageInfo("ZZM9037E");
                return;

            } else if (!ZYPCommonFunc.hasValue(rtlWhCd) || !ZYPCommonFunc.hasValue(rtlSwhCd) || !rtlWhCd.equals(sMsg.srcRtlWhCd_SC.getValue())) {

                cMsg.svcConfigMstrPk.setErrorInfo(1, NPAM1510E);
                cMsg.setMessageInfo("ZZM9037E");
                return;
            }

            NPAL1500CommonLogic.addNewLine(cMsg, sMsg);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).poLineTpCd_A1, PO_LINE_TP.GOODS);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).mdseCd_A1, (String) rsltMap.get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).srcRtlSwhCd_A1, rtlSwhCd);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).destRtlSwhCd_A1, rtlSwhCd);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).svcConfigMstrPk_A1, cMsg.svcConfigMstrPk);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).serNumListTxt_A1, (String) rsltMap.get("SER_NUM"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).serNumListTxt_HD, (String) rsltMap.get("SER_NUM"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).poDispQty_A1, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).poBalQty, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).poInvQty_A1, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).stkStsCd_A1, (String) rsltMap.get("STK_STS_CD"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).svcMachMstrPk_IB, (BigDecimal) rsltMap.get("SVC_MACH_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).ezUpTime_IB, (String) rsltMap.get("EZUPTIME"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).ezUpTimeZone_IB, (String) rsltMap.get("EZUPTIMEZONE"));
            // add start 2022/05/16 QC#57934
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).poRcvQty_WO, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).poInvQty_WO, BigDecimal.ZERO);
            // add end 2022/05/16 QC#57934

            if (!NPAL1500CommonLogic.setDeriveItemInfoBB(cMsg, sMsg, index)) {
                return;
            }
            if (!NPAL1500CommonLogic.getAccountInfo(cMsg, sMsg, index, false)) {
                return;
            }
            if (!NPAL1500CommonLogic.getDefaultAccountInfo(cMsg, sMsg, cMsg.glblCmpyCd.getValue(), index)) {
                return;
            }
            index++;
        }
    }

    /**
     * calcurateTotalAmt
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     */
    private static void calcurateTotalAmt(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg) {

        BigDecimal total = BigDecimal.ZERO;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (!PO_STS.CANCELLED.equals(sMsg.A.no(i).poStsCd_HD.getValue())) {
                BigDecimal unitPrice = sMsg.A.no(i).entDealNetUnitPrcAmt_A1.getValue();
                BigDecimal poQty = sMsg.A.no(i).poDispQty_A1.getValue();
                BigDecimal poCancQty = sMsg.A.no(i).poCancQty_A1.getValue();

                // value check
                if (!ZYPCommonFunc.hasValue(unitPrice)) {
                    unitPrice = BigDecimal.ZERO;
                }
                if (!ZYPCommonFunc.hasValue(poQty)) {
                    poQty = BigDecimal.ZERO;
                } else {
                    // Quantity is scale zero.
                    poQty = poQty.setScale(0);
                }
                if (!ZYPCommonFunc.hasValue(poCancQty)) {
                    poCancQty = BigDecimal.ZERO;
                } else {
                    // Quantity is scale zero.
                    poCancQty = poCancQty.setScale(0);
                }

                if (!PO_MDSE_CMPSN_TP.CHILD.equals(sMsg.A.no(i).poMdseCmpsnTpCd_HD.getValue())) {
                    // SUM(ENT_DEAL_NET_UNIT_PRC_AMT*(PO_DISP_QTY-PO_CANC_QTY))
                    total = total.add(unitPrice.multiply(poQty.add(poCancQty.negate())));
                }

            }
        }

        // set Total Amt
        ZYPEZDItemValueSetter.setValue(cMsg.xxTotAmt, total);
        ZYPEZDItemValueSetter.setValue(sMsg.xxTotAmt, total);
    }

    /**
     * Check PO Quantity
     * @param bizMsg NPAL1500CMsg
     * @param glblMsg NPAL1500SMsg
     */
    private static boolean checkPOQty(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

    	String noAslCheckPoTp = "";
        noAslCheckPoTp = ZYPCodeDataUtil.getVarCharConstValue("NO_ASL_CHECK_PO_TP", bizMsg.glblCmpyCd.getValue());

        if (ZYPCommonFunc.hasValue(noAslCheckPoTp)) {
            String[] array = noAslCheckPoTp.split(",");

            for (int i = 0; i < array.length; i++) {

                String poTp = array[i];

                if (poTp.equals(bizMsg.dsPoTpCd.getValue())) {

                    return true;

                }

            }
        }

        int idx = bizMsg.xxNum.getValueInt();

        // Calculate PO Qty
        BigDecimal poQty = BigDecimal.ZERO;

        // Index of SMsg Line
        int pageShowFromNum = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        int indexS = pageShowFromNum + idx;

        // Call Change Vendor UOM API
        NPZC129001PMsg result = NPAL1500CommonLogic.execChgVndUom(bizMsg, glblMsg, indexS, glblMsg.A.no(indexS).poDispQty_A1.getValue());

        if (result == null) {
            return false;
        }

        if (S21ApiUtil.isXxMsgId(result)) {
            // Error
            bizMsg.setMessageInfo(NPAM1323E
                    , new String[] {
                    "NPZC1290 Change Vendor UOM"
                            , result.xxMsgIdList.no(0).xxMsgId.getValue() });
            return false;
        } else {
            if (ZYPCommonFunc.hasValue(result.poQty_O1)) {
                poQty = result.poQty_O1.getValue();
            }
        }

        if (poQty.intValue() > 200) {
            bizMsg.A.no(idx).poDispQty_A1.setErrorInfo(3, NPAM0038I);
            return false;
        }

        return true;
    }
    /**
     * convertDateTimeAMPM
     * @param cMsg NPAL1500CMsg
     */
    private static void convertDateTimeAMPM(NPAL1500CMsg cMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.rqstRcvTm)) {
            String rqstRcvTm = cMsg.rqstRcvTm.getValue();
            String hh = rqstRcvTm.substring(0, 2);
            String mi = rqstRcvTm.substring(2, 4);
            int hhInt = Integer.parseInt(hh);
            String xxDtTm = "";
            if (hhInt > 12) {
                if (hhInt >= 22) {
                    xxDtTm = (hhInt - 12) + ":" + mi;
                } else {
                    xxDtTm = "0" + (hhInt - 12) + ":" + mi;
                }
                ZYPEZDItemValueSetter.setValue(cMsg.xxDtTm, xxDtTm);
                ZYPEZDItemValueSetter.setValue(cMsg.xxTpCd_SL, ZYPConstant.FLG_ON_1);
            } else {
                if (hhInt >= 10) {
                    xxDtTm = hhInt + ":" + mi;
                } else {
                    xxDtTm = "0" + (hhInt) + ":" + mi;
                }
                ZYPEZDItemValueSetter.setValue(cMsg.xxDtTm, xxDtTm);
                ZYPEZDItemValueSetter.setValue(cMsg.xxTpCd_SL, ZYPConstant.FLG_OFF_0);
            }
        }
    }

//    /**
//     * Derive_SetComponent
//     * @param bizMsg NPAL1500CMsg
//     * @param glblMsg NPAL1500SMsg
//     * @param index index of SMsg Line
//     * @return boolean true:Success false:Failure
//     */
//    @SuppressWarnings("unchecked")
//    private static boolean deriveSetComponent(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg, int index) {
//
//        List<Map<String, Object>> resultListComp = null;
//
//        // child item delete
//        String lineNum = glblMsg.A.no(index).dispPoDtlLineNum_A1.getValue();
//        String sameNum = lineNum.substring(0, lineNum.indexOf(".") + 1);
//        int deleteLineCnt = 0;
//        for (int i = index + 1; i < glblMsg.A.getValidCount(); i++) {
//            if (glblMsg.A.no(i).dispPoDtlLineNum_A1.getValue().startsWith(sameNum)) {
//                glblMsg.A.no(i).clear();
//                deleteLineCnt++;
//            }
//        }
//        glblMsg.A.setValidCount(glblMsg.A.getValidCount() - deleteLineCnt);
//
//        // Get Components
//        S21SsmEZDResult ssmResultForComp = NPAL1500Query.getInstance().getComponent(glblMsg.glblCmpyCd.getValue(), glblMsg.A.no(index).mdseCd_A1.getValue());
//        if (ssmResultForComp.isCodeNormal()) {
//            resultListComp = (List<Map<String, Object>>) ssmResultForComp.getResultObject();
//
//            for (int i = 0; i < resultListComp.size(); i++) {
//                int childIdx = glblMsg.A.getValidCount();
//
//                // add child component Default Info
//                addNewLine(bizMsg, glblMsg, childIdx);
//
//                // set Item Info
//                String prntLinNum = glblMsg.A.no(index).poOrdDtlLineNum_A1.getValue();
//                String prntDispLineNum = glblMsg.A.no(index).dispPoDtlLineNum_A1.getValue();
//                prntDispLineNum = prntDispLineNum.substring(0, prntDispLineNum.indexOf(PERIOD));
//                int subNum = i + 1;
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(childIdx).dispPoDtlLineNum_A1, prntDispLineNum.replaceFirst("^0+", "") + PERIOD + subNum);
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(childIdx).setPoOrdDtlLineNum, prntLinNum);
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(childIdx).poLineTpCd_A1, glblMsg.A.no(index).poLineTpCd_A1);
//
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(childIdx).mdseCd_A1, (String) resultListComp.get(i).get("CHILD_MDSE_CD"));
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(childIdx).aslMdseCd_A1, (String) resultListComp.get(i).get("CHILD_MDSE_CD"));
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(childIdx).mdseDescShortTxt_A1, (String) resultListComp.get(i).get("MDSE_DESC_SHORT_TXT"));
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(childIdx).entDealNetUnitPrcAmt_A1, BigDecimal.ZERO);
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(childIdx).poQty_HD, (BigDecimal) resultListComp.get(i).get("CHILD_MDSE_QTY"));
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(childIdx).rcvSerTakeFlg_IB, (String) resultListComp.get(i).get("RCV_SER_TAKE_FLG"));
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(childIdx).instlBaseCtrlFlg_IB, (String) resultListComp.get(i).get("INSTL_BASE_CTRL_FLG"));
//
//                BigDecimal chldQty = glblMsg.A.no(childIdx).poQty_HD.getValue();
//                BigDecimal prntQty = glblMsg.A.no(index).poDispQty_A1.getValue();
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(childIdx).poDispQty_A1, chldQty.multiply(prntQty));
//
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(childIdx).poDispUomCd_A1, VND_UOM.EACH);
//
//                glblMsg.A.no(index).aslDtlPk_HD.clear();
//                glblMsg.A.no(index).aslUnitPrcAmt_HD.clear();
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(childIdx).thisMthFobCostAmt_HD, BigDecimal.ZERO);
//
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(childIdx).rqstRcvDt_A1, glblMsg.A.no(index).rqstRcvDt_A1);
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(childIdx).destRtlSwhCd_A1, glblMsg.A.no(index).destRtlSwhCd_A1);
//
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(childIdx).poLineStsCd_A1, glblMsg.A.no(index).poLineStsCd_A1);
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(childIdx).poLineStsDescTxt_A1, glblMsg.A.no(index).poLineStsDescTxt_A1);
//
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(childIdx).entPoDtlDealNetAmt_A1, BigDecimal.ZERO);
//
//                glblMsg.A.no(childIdx).poRcvQty_A1.clear();
//                glblMsg.A.no(childIdx).poCancQty_A1.clear();
//                glblMsg.A.no(childIdx).poInvQty_A1.clear();
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(childIdx).vndInvtyLocCd_A1, glblMsg.A.no(index).vndInvtyLocCd_A1);
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(childIdx).srcRtlSwhCd_A1, glblMsg.A.no(index).srcRtlSwhCd_A1);
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(childIdx).stkStsCd_A1, glblMsg.A.no(index).stkStsCd_A1);
//                glblMsg.A.no(childIdx).serNumListTxt_A1.clear();
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).svcConfigMstrPk_A1, glblMsg.A.no(index).svcConfigMstrPk_A1);
//                glblMsg.A.no(childIdx).poOrdDtlCmntTxt_A1.clear();
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(childIdx).prchReqNum_A1, glblMsg.A.no(index).prchReqNum_A1);
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(childIdx).prchReqLineNum_A1, glblMsg.A.no(index).prchReqLineNum_A1);
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(childIdx).poMdseCmpsnTpCd_HD, PO_MDSE_CMPSN_TP.CHILD);
//
//                NPAL1500CommonLogic.getAccountInfo(bizMsg, glblMsg, childIdx);
//            }
//        }
//
//        if (resultListComp == null) {
//            glblMsg.A.no(index).mdseCd_A1.setErrorInfo(1, NPAM0076E, new String[] {"Set Component Item" });
//            bizMsg.setMessageInfo(NPAM0076E, new String[] {"Set Component Item" });
//            return false;
//        }
//        return true;
//    }

    /**
     * getDetailData
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     */
    private static void setDetailData(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg) {

        S21SsmEZDResult ssmResult = new S21SsmEZDResult();

        // Get PO DetailData
        S21SsmEZDResult poDetailList = NPAL1500Query.getInstance().getDetailListInfo(cMsg, sMsg);

        if (poDetailList.isCodeNormal()) {
            // Get ser number
            setSerNumber(cMsg, sMsg);

            // max row count check
            int queryResCnt = poDetailList.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo("NZZM0001W");
                queryResCnt = sMsg.A.length();
            }

            // Add Start 2019/12/10 QC#5500. Mod QC#55313
            setPoDetailInfo(cMsg, sMsg, cMsg.glblCmpyCd.getValue());
            // Add End 2019/12/10 QC#55004

            // copy current page data (SMsg -> CMsg)
            int i = 0;
            String preErrMsg = "";
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }

                // Del Start 2019/12/10 QC#55004
//                //QC#26893 Add Start
//                if(PO_MDSE_CMPSN_TP.CHILD.equals(sMsg.A.no(i).poMdseCmpsnTpCd_HD.getValue())){
//                    sMsg.A.no(i).aslUnitPrcAmt_A1.clear();
//                }
//                //QC#26893 Add End
//                
//                // QC#23616
//                if (!(PO_LINE_TP.EXPENSE.equals(sMsg.A.no(i).poLineTpCd_A1.getValue())// 
//                        || PO_LINE_TP.EXPENSE_WITH_RECEIPT.equals(sMsg.A.no(i).poLineTpCd_A1.getValue()))) {
//                    sMsg.A.no(i).xxScrItem130Txt_CH.clear();
//                }
//                // QC#15817 Delete.
////                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).coaCmpyCd_AC)) {
////                    sMsg.A.no(i).xxScrItem130Txt_AC.clear();
////                }
////                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).coaCmpyCd_VA)) {
////                    sMsg.A.no(i).xxScrItem130Txt_VA.clear();
////                }
//
//                //Get SVC_MACH_MSTR.
//                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).svcConfigMstrPk_A1) && ZYPCommonFunc.hasValue(sMsg.A.no(i).mdseCd_A1)) {
//                    ssmResult = NPAL1500Query.getInstance().getSvcMachMstr(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i).mdseCd_A1.getValue(), sMsg.A.no(i).svcConfigMstrPk_A1.getValue(), sMsg.A.no(i).serNumListTxt_A1);
//                    if (ssmResult.isCodeNormal()) {
//                        List<Map<String, Object>> resultMap = (List<Map<String, Object>>) ssmResult.getResultObject();
//                        if (resultMap != null && !resultMap.isEmpty()) {
//                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).svcMachMstrPk_IB, (BigDecimal) resultMap.get(0).get("SVC_MACH_MSTR_PK"));
//                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).ezUpTime_IB, (String) resultMap.get(0).get("EZUPTIME"));
//                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).ezUpTimeZone_IB, (String) resultMap.get(0).get("EZUPTIMEZONE"));
//                        }
//                    }
//                }
//
//                // QC#25024 Add Start
//                //get MDSE_ITEM_TP_CD
//                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).mdseItemTpCd_A1)) { // 2019/01/25 QC#30082 Add Condition
//                    MDSETMsg mdseTMsg = new MDSETMsg();
//
//                    ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
//                    ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, sMsg.A.no(i).mdseCd_A1.getValue());
//
//                    // 2019/01/25 QC#30082 Mod Start
////                    mdseTMsg = (MDSETMsg) EZDTBLAccessor.findByKey(mdseTMsg);
//                    mdseTMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(mdseTMsg);
//                    // 2019/01/25 QC#30082 Mod End
//                    if (mdseTMsg != null) {
//                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mdseItemTpCd_A1, mdseTMsg.mdseItemTpCd.getValue());
//                    }
//                }  // 2019/01/25 QC#30082 Add Condition
//                // QC#25024 Add End
                // Del End 2019/12/10 QC#55004

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
                // START QC#20735 Add
                String itfcErrTxt = "";
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).poDtlIntfcErrMsgTxt_A1)) {
                    if (sMsg.A.no(i).poDtlIntfcErrMsgTxt_A1.getValue().startsWith("AWZM") && !sMsg.A.no(i).poDtlIntfcErrMsgTxt_A1.getValue().equals(preErrMsg)) {
                        itfcErrTxt = sMsg.A.no(i).poDtlIntfcErrMsgTxt_A1.getValue();
                    } else if (!sMsg.A.no(i).poDtlIntfcErrMsgTxt_A1.getValue().startsWith("AWZM")) {
                        itfcErrTxt = "[" + sMsg.A.no(i).dispPoDtlLineNum_A1.getValue() + "] " + sMsg.A.no(i).poDtlIntfcErrMsgTxt_A1.getValue();
                    }
                    preErrMsg = itfcErrTxt;
                }
                ZYPEZDItemValueSetter.setValue(cMsg.poDtlIntfcErrMsgTxt_P0.no(i), itfcErrTxt);
                ZYPEZDItemValueSetter.setValue(cMsg.poDtlIntfcErrMsgTxt_P1.no(i), itfcErrTxt);
                ZYPEZDItemValueSetter.setValue(cMsg.xxDtTm_HP, cMsg.poSendTs_HD);
                if (ZYPCommonFunc.hasValue(cMsg.xxDtTm_HP)) {
                    // format yyyyMMddHHmmssSSS
                    String prchReqRelDtTmTsHp = cMsg.xxDtTm_HP.getValue();
                    // format yyyyMMddHHmmssSSS substring yyyy
                    String yyyy = prchReqRelDtTmTsHp.substring(0, 4);
                    // format yyyyMMddHHmmssSSS substring mm
                    String mm = prchReqRelDtTmTsHp.substring(4, 6);
                    // format yyyyMMddHHmmssSSS substring dd
                    String dd = prchReqRelDtTmTsHp.substring(6, 8);
                    // format yyyyMMddHHmmssSSS substring hh
                    String hh = prchReqRelDtTmTsHp.substring(8, 10);
                    // format yyyyMMddHHmmssSSS substring mi
                    String mi = prchReqRelDtTmTsHp.substring(10, 12);
                    String tmpPrchReqRelDtTmTsHP = "";
                    int hhInt = Integer.parseInt(hh);
                    // PM
                    if (hhInt > 12) {
                        // Conversion from HH24 to HH12
                        // hhint:13-23
                        tmpPrchReqRelDtTmTsHP = ZYPCommonFunc.leftPad((hhInt - 12) + ":" + mi + " PM", 8, "0");
                        // AM
                    } else {
                        tmpPrchReqRelDtTmTsHP = ZYPCommonFunc.leftPad(hhInt + ":" + mi + " AM", 8, "0");
                    }
                    cMsg.xxDtTm_HP.setValue(mm + "/" + dd + "/" + yyyy + " " + tmpPrchReqRelDtTmTsHP);
                }
                // END QC#20735 Add
                
                // START 2017/12/06 [QC#14858, ADD, QC#23616 Mod]
                if ((PO_LINE_TP.EXPENSE.equals(sMsg.A.no(i).poLineTpCd_A1.getValue()) // 
                        || PO_LINE_TP.EXPENSE_WITH_RECEIPT.equals(sMsg.A.no(i).poLineTpCd_A1.getValue()) //
                        || PO_LINE_TP.ASSET.equals(sMsg.A.no(i).poLineTpCd_A1.getValue()) //
                        && ZYPCommonFunc.hasValue(sMsg.A.no(i).mdseCd_A1.getValue()))) {
                    // check TEXT ITEM
                    ssmResult = NPAL1500Query.getInstance().getAvailableTextItem(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i).mdseCd_A1.getValue());
                    if (ssmResult.isCodeNormal()) {
                        String textItem = (String) ssmResult.getResultObject();
                        if (textItem != null && !textItem.isEmpty()) {
                            cMsg.A.no(i).mdseCd_A1.clear();
                        }
                    }
                }
                // END 2017/12/06 [QC#14858, ADD]
            }
            cMsg.A.setValidCount(i);

            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);

            // page data not found
        } else {
            cMsg.setMessageInfo("NZZM0000E");
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
        }
    }

    /** for search **/
    /**
     * getHeaderlData
     * @param cMsg NPAL1500CMsg
     */
    private static void setHeaderData(NPAL1500CMsg cMsg) {

        // Get PO Data
        S21SsmEZDResult poResult = NPAL1500Query.getInstance().getPoData(cMsg);

        if (poResult.isCodeNormal()) {

            List<Map<String, String>> resultList = (List<Map<String, String>>) poResult.getResultObject();
            Map<String, String> result = (Map<String, String>) resultList.get(0);

            ZYPEZDItemValueSetter.setValue(cMsg.poNum, (String) result.get("PO_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(cMsg.dsPoTpCd, (String) result.get("DS_PO_TP_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.poHdrStsCd, (String) result.get("PO_HDR_STS_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.poHdrStsDescTxt, (String) result.get("PO_HDR_STS_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(cMsg.poApvlStsCd, (String) result.get("PO_APVL_STS_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.poApvlStsDescTxt, (String) result.get("PO_APVL_STS_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(cMsg.poApvlDt, (String) result.get("PO_APVL_DT"));
            ZYPEZDItemValueSetter.setValue(cMsg.poSubmtTs, (String) result.get("PO_SUBMT_TS"));
            ZYPEZDItemValueSetter.setValue(cMsg.xxOrdTs, ZYPDateUtil.formatEzd8ToDisp((String) result.get("PO_SUBMT_TS").substring(0, 8)));
            ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, (String) result.get("RQST_RCV_DT"));
            ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvTm, (String) result.get("RQST_RCV_TM"));
            // START 2023/02/14 S.Dong [QC#60966, ADD]
            ZYPEZDItemValueSetter.setValue(cMsg.rqstShipDt, (String) result.get("RQST_SHIP_DT"));
            // END 2023/02/14 S.Dong [QC#60966, ADD]
            ZYPEZDItemValueSetter.setValue(cMsg.poOrdSrcCd, (String) result.get("PO_ORD_SRC_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.poOrdSrcDescTxt, (String) result.get("PO_ORD_SRC_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(cMsg.poQlfyCd, (String) result.get("PO_QLFY_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.poSubmtPsnCd, (String) result.get("PO_SUBMT_PSN_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.fullPsnNm, (String) result.get("FULL_PSN_NM"));
            ZYPEZDItemValueSetter.setValue(cMsg.prchGrpCd, (String) result.get("PRCH_GRP_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.poOrdCmntTxt, (String) result.get("PO_ORD_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(cMsg.prntVndCd, (String) result.get("PRNT_VND_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.prntVndNm, (String) result.get("PRNT_VND_NM"));
            ZYPEZDItemValueSetter.setValue(cMsg.vndCd, (String) result.get("VND_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.vndNm, (String) result.get("VND_NM"));
            ZYPEZDItemValueSetter.setValue(cMsg.poStsCd_H, (String) result.get("PO_STS_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.srcRtlWhCd_SC, (String) result.get("SRC_RTL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_SC, (String) result.get("SRC_RTL_WH_NM"));
            ZYPEZDItemValueSetter.setValue(cMsg.destRtlWhCd_DS, (String) result.get("DEST_RTL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_DS, (String) result.get("DEST_RTL_WH_NM"));
            ZYPEZDItemValueSetter.setValue(cMsg.rtrnShipToRtlWhCd_RW, (String) result.get("RTRN_SHIP_TO_RTL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_RW, (String) result.get("RTL_WH_NM"));
            ZYPEZDItemValueSetter.setValue(cMsg.addlLocNm_RW, (String) result.get("ADDL_LOC_NM"));
            ZYPEZDItemValueSetter.setValue(cMsg.xxAllLineAddr_RW, (String) result.get("ALL_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(cMsg.firstLineAddr_RW, (String) result.get("FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(cMsg.scdLineAddr_RW, (String) result.get("SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(cMsg.thirdLineAddr_RW, (String) result.get("THIRD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(cMsg.frthLineAddr_RW, (String) result.get("FRTH_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(cMsg.postCd_RW, (String) result.get("POST_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.ctyAddr_RW, (String) result.get("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(cMsg.cntyNm_RW, (String) result.get("CNTY_NM"));
            ZYPEZDItemValueSetter.setValue(cMsg.ctryDescTxt_RW, (String) result.get("CTRY_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(cMsg.stCd_RW, (String) result.get("ST_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.provNm_RW, (String) result.get("PROV_NM"));
            ZYPEZDItemValueSetter.setValue(cMsg.ctryCd_RW, (String) result.get("CTRY_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.carrAcctNum, (String) result.get("CARR_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(cMsg.vndPmtTermDescTxt, (String) result.get("VND_PMT_TERM_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(cMsg.ezUpTime_H1, (String) result.get("PH_EZUPTIME"));
            ZYPEZDItemValueSetter.setValue(cMsg.ezUpTimeZone_H1, (String) result.get("PH_EZUPTIMEZONE"));
            ZYPEZDItemValueSetter.setValue(cMsg.ezUpTime_H2, (String) result.get("DPH_EZUPTIME"));
            ZYPEZDItemValueSetter.setValue(cMsg.ezUpTimeZone_H2, (String) result.get("DPH_EZUPTIMEZONE"));
            //QC#19485
            ZYPEZDItemValueSetter.setValue(cMsg.poSendFlg_HD, (String) result.get("PO_SEND_FLG"));
            ZYPEZDItemValueSetter.setValue(cMsg.vndIssOrdNum_HD, (String) result.get("VND_ISS_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(cMsg.poSendTs_HD, (String) result.get("PO_SEND_TS"));
          //QC#19485
            // convert
            convertDateTimeAMPM(cMsg);
        } else {
            cMsg.setMessageInfo(NPAM0002E);
            return;
        }

        // Get First Data Of PO_DTL
        S21SsmEZDResult poDtlResult = NPAL1500Query.getInstance().getPoDtlData(cMsg);
        if (poDtlResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) poDtlResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.shipToCustCd, (String) resultMap.get("SHIP_TO_CUST_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.shipToLocNm, (String) resultMap.get("SHIP_TO_LOC_NM"));
                ZYPEZDItemValueSetter.setValue(cMsg.shipToAddlLocNm_ST, (String) resultMap.get("SHIP_TO_ADDL_LOC_NM"));
                ZYPEZDItemValueSetter.setValue(cMsg.xxAllLineAddr_ST, (String) resultMap.get("LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(cMsg.shipToFirstLineAddr_ST, (String) resultMap.get("SHIP_TO_FIRST_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(cMsg.shipToScdLineAddr_ST, (String) resultMap.get("SHIP_TO_SCD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(cMsg.shipToThirdLineAddr_ST, (String) resultMap.get("SHIP_TO_THIRD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(cMsg.shipToFrthLineAddr_ST, (String) resultMap.get("SHIP_TO_FRTH_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(cMsg.shipToPostCd_ST, (String) resultMap.get("SHIP_TO_POST_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.shipToCtyAddr_ST, (String) resultMap.get("SHIP_TO_CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(cMsg.shipToCntyNm_ST, (String) resultMap.get("SHIP_TO_CNTY_NM"));
                ZYPEZDItemValueSetter.setValue(cMsg.shipToStCd_ST, (String) resultMap.get("SHIP_TO_ST_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.shipToProvNm_ST, (String) resultMap.get("SHIP_TO_PROV_NM"));
                ZYPEZDItemValueSetter.setValue(cMsg.shipToCtryCd_ST, (String) resultMap.get("SHIP_TO_CTRY_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.ctryDescTxt_ST, (String) resultMap.get("CTRY_DESC_TXT"));

                ZYPEZDItemValueSetter.setValue(cMsg.billToCustCd_BT, (String) resultMap.get("BILL_TO_CUST_CD"));

                ZYPEZDItemValueSetter.setValue(cMsg.shpgSvcLvlCd, (String) resultMap.get("SHPG_SVC_LVL_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.carrCd, (String) resultMap.get("CARR_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.carrNm, (String) resultMap.get("CARR_NM"));
                break;
            }
        }

        // Get First Data Of PO_DTL
        S21SsmEZDResult poDsDtlResult = NPAL1500Query.getInstance().getAdditionalPoDtlData(cMsg);
        if (poDsDtlResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) poDsDtlResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.trxRefNum, (String) resultMap.get("TRX_REF_NUM"));
                ZYPEZDItemValueSetter.setValue(cMsg.frtCondCd, (String) resultMap.get("FRT_COND_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.ctacPsnNm, (String) resultMap.get("CTAC_PSN_NM"));
                break;
            }
        }

        // Get PO_MSG_TXT Data (Special Instructions)
        S21SsmEZDResult poMsg02Result = NPAL1500Query.getInstance().getPoMsgTxt(cMsg, PO_MSG_TP.INTERNAL_PO_MESSAGE);
        if (poMsg02Result.isCodeNormal()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) poMsg02Result.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.Q.no(i).poMsgPk, (BigDecimal) resultMap.get("PO_MSG_PK"));
                ZYPEZDItemValueSetter.setValue(cMsg.Q.no(i).poMsgSegId, (BigDecimal) resultMap.get("PO_MSG_SEG_ID"));
                ZYPEZDItemValueSetter.setValue(cMsg.Q.no(i).poMsgSubmtPsnCd, (String) resultMap.get("PO_MSG_SUBMT_PSN_CD"));
                String msg = (String) resultMap.get("PO_MSG_TXT");
                if (ZYPCommonFunc.hasValue(msg)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.Q.no(i).poMsgTxt, msg);
                }
            }
        }

        List<PO_MSGTMsg> poMsgList = null;
        String msg = null;
        // Get PO_MSG_TXT Data (Special Instructions)
        poMsgList = NPXC001001PoMsg.getPoMsg(cMsg.glblCmpyCd.getValue(), PO_MSG_TP.SPECIAL_INSTRUCTIONS, cMsg.poNum.getValue(), null);
        if (poMsgList != null) {
            msg = NPXC001001PoMsg.concatenatePoMsg(poMsgList);
            ZYPEZDItemValueSetter.setValue(cMsg.xxDsMultMsgDplyTxt_SI, msg);
        }

        // Get PO_MSG_TXT Data (Receiver Note)
        poMsgList = NPXC001001PoMsg.getPoMsg(cMsg.glblCmpyCd.getValue(), PO_MSG_TP.RECEIVER_NOTE, cMsg.poNum.getValue(), null);
        if (poMsgList != null) {
            msg = NPXC001001PoMsg.concatenatePoMsg(poMsgList);
            ZYPEZDItemValueSetter.setValue(cMsg.xxDsMultMsgDplyTxt_RN, msg);
        }

        // Get PO_MSG_TXT Data (Shipper Note)
        poMsgList = NPXC001001PoMsg.getPoMsg(cMsg.glblCmpyCd.getValue(), PO_MSG_TP.SHIPPER_NOTE, cMsg.poNum.getValue(), null);
        if (poMsgList != null) {
            msg = NPXC001001PoMsg.concatenatePoMsg(poMsgList);
            ZYPEZDItemValueSetter.setValue(cMsg.xxDsMultMsgDplyTxt_SN, msg);
        }

        if (ZYPCommonFunc.hasValue(cMsg.shipToCustCd)) {
            String glblCmpyCd = cMsg.glblCmpyCd.getValue();

            S21SsmEZDResult sellToCustResult = NPAL1500Query.getInstance().getSellToCustCd(glblCmpyCd, cMsg.destRtlWhCd_DS.getValue());
            if (sellToCustResult.isCodeNormal()) {
                NMZC610001PMsg pMsg = new NMZC610001PMsg();
                pMsg.glblCmpyCd.setValue(glblCmpyCd);
                pMsg.xxModeCd.setValue("04");
                pMsg.dsAcctNum_I1.setValue(sellToCustResult.getResultObject().toString());
                NMZC610001 nMZC610001 = new NMZC610001();
                nMZC610001.execute(pMsg, ONBATCH_TYPE.ONLINE);

                for (int i = pMsg.DefaultBillShipList.getValidCount() - 1; i >= 0; i--) {
                    NMZC610001_DefaultBillShipListPMsg dpMsg = pMsg.DefaultBillShipList.no(i);
                    S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getBillToLocation(glblCmpyCd, dpMsg.billToCustCd.getValue());
                    if (ssmResult.isCodeNormal()) {
                        List<Map<String, Object>> list = (List) ssmResult.getResultObject();
                        if (list != null && 0 < list.size()) {
                            Map<String, Object> map = list.get(0);
                            ZYPEZDItemValueSetter.setValue(cMsg.varCharConstVal_B1, dpMsg.billToCustCd.getValue());
                            ZYPEZDItemValueSetter.setValue(cMsg.varCharConstVal_B2, (String) map.get("LOC_NM"));
                            ZYPEZDItemValueSetter.setValue(cMsg.varCharConstVal_B3, (String) map.get("ADDL_LOC_NM"));
                            ZYPEZDItemValueSetter.setValue(cMsg.varCharConstVal_B4, (String) map.get("POST_CD"));
                            ZYPEZDItemValueSetter.setValue(cMsg.varCharConstVal_B5, (String) map.get("CTY_ADDR"));
                            ZYPEZDItemValueSetter.setValue(cMsg.varCharConstVal_B6, (String) map.get("CNTY_NM"));
                            ZYPEZDItemValueSetter.setValue(cMsg.varCharConstVal_B7, (String) map.get("ST_CD"));
                            ZYPEZDItemValueSetter.setValue(cMsg.varCharConstVal_B8, (String) map.get("PROV_NM"));
                            ZYPEZDItemValueSetter.setValue(cMsg.varCharConstVal_B9, (String) map.get("CTRY_CD"));
                            ZYPEZDItemValueSetter.setValue(cMsg.varCharConstVal_BA, (String) map.get("ST_CD"));
                            ZYPEZDItemValueSetter.setValue(cMsg.varCharConstVal_BB, (String) map.get("PROV_NM"));
                            ZYPEZDItemValueSetter.setValue(cMsg.xxAllLineAddr_BT, (String) map.get("XX_ALL_LINE_ADDR"));
                            ZYPEZDItemValueSetter.setValue(cMsg.ctryDescTxt_BT, (String) map.get("CTRY_NM"));
                            break;
                        }
                    }
                }
            }
        }
    }

//    /**
//     * get Max PO_DTL.PO_DTL_LINE_NUM
//     * @param prchReqNum String
//     * @param glblCmpyCd String
//     * @return String
//     */
//    private static String getMaxPoDtlLineNum(String glblCmpyCd, String poOrdNum) {
//        S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getMaxPoDtlLineNum(glblCmpyCd, poOrdNum);
//        if (ssmResult.isCodeNormal()) {
//            List<Map<String, Object>> list = (List) ssmResult.getResultObject();
//            for (int c = 0; c < list.size(); c++) {
//                Map<String, Object> map = list.get(c);
//                return (String) map.get(DB_COLUMN_MAX_LINE_NUM);
//            }
//        }
//        return PO_DTL_LINE_NUM_000;
//    }

    /**
     * getSerNumber
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     */
    private static void setSerNumber(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg) {
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getPoSerNum(cMsg.glblCmpyCd.getValue(), cMsg.poNum.getValue(), sMsg.A.no(i).poOrdDtlLineNum_A1.getValue());
            if (ssmResult.isCodeNormal()) {
                setSerNumberDetail(sMsg, i, (List<Map<String, Object>>) ssmResult.getResultObject());
            }
            // QC#24413
            BigDecimal rwsCnt = NPAL1500Query.getInstance().countValidRws(cMsg.glblCmpyCd.getValue(), cMsg.poNum.getValue(), sMsg.A.no(i).poOrdDtlLineNum_A1.getValue());
            if (ZYPCommonFunc.hasValue(rwsCnt) && rwsCnt.intValue() > 0) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).serNumFlg_A1, ZYPConstant.FLG_ON_Y);
            }
        }
    }

    /**
     * getSerNumberDetail
     * @param sMsg NPAL1500SMsg
     * @param i int
     * @param ssmResult S21SsmEZDResult
     */
    private static void setSerNumberDetail(NPAL1500SMsg sMsg, int i, List<Map<String, Object>> resultList) {
        StringBuilder sbNum = new StringBuilder();
        StringBuilder sbPk = new StringBuilder();
        for (int j = 0; j < resultList.size(); j++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(j);
            BigDecimal pk = (BigDecimal) resultMap.get("PO_SER_NUM_PK");
            String num = (String) resultMap.get("SER_NUM");
            if (ZYPCommonFunc.hasValue(pk) && ZYPCommonFunc.hasValue(num)) {
                if (sbPk.length() != 0) {
                    sbPk.append(",");
                    sbNum.append(",");
                }
                sbPk.append(pk.toString());
                sbNum.append(num);
            }
        }
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).serNumListTxt_A1, sbNum.toString());
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).serNumListTxt_A2, sbPk.toString());
    }

    /**
     * get Transmit Method Info
     * @param glblMsg NPAL1500SMsg
     * @param bizMsg NPAL1500CMsg
     */
    private static void setTrsmtMethInfo(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        // QC#21358 Mod Start
        //Map<String, Object> resultMap = null;
        List<Map<String, Object>> resultMap = null;
        List<Map<String, Object>> resultMapList = null;
        Map<String, Object> trsmtMap = null;
        S21SsmEZDResult ssmResult = null;

        if (!(ZYPCommonFunc.hasValue(bizMsg.sendPoEmlAddr) || ZYPCommonFunc.hasValue(bizMsg.sendPoFaxNum))) {
            // START 2018/12/13 M.Naito [QC#29027,MOD]
//            if (!ZYPCommonFunc.hasValue(bizMsg.trsmtMethTpCd)) {
            // S21SsmEZDResult ssmResult =
            // NPAL1500Query.getInstance().getVendorTrsmtInfo(bizMsg);
            ssmResult = NPAL1500Query.getInstance().getContactInfo(bizMsg, glblMsg);
            // QC#21358 Mod End

            if (ssmResult.isCodeNormal()) {
                resultMap = (List<Map<String, Object>>) ssmResult.getResultObject();

                // QC#21358 Mod Start
                trsmtMap = resultMap.get(0);

                if (!ZYPCommonFunc.hasValue(bizMsg.trsmtMethTpCd)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.trsmtMethTpCd, (String) trsmtMap.get("TRSMT_METH_TP_CD"));
                }
                // QC#21358 Mod End
            }
//            }
            // END 2018/12/13 M.Naito [QC#29027,MOD]
        }
        ssmResult = NPAL1500Query.getInstance().getEipRptInfo(bizMsg);

        if (ssmResult.isCodeNormal()) {
            resultMapList = (List<Map<String, Object>>) ssmResult.getResultObject();
        }

        if (TRSMT_METH_TP.PDF_DOWNLOAD.equals(bizMsg.trsmtMethTpCd.getValue())) {
            return; // disabled

        } else if (TRSMT_METH_TP.FAX.equals(bizMsg.trsmtMethTpCd.getValue())) {
            if (resultMap != null) {
                // QC#21358 Mod Start
                //ZYPEZDItemValueSetter.setValue(bizMsg.sendPoFaxNum, (String) resultMap.get("FAX_NUM"));
                setTrsmtMethInfoFAX(bizMsg, resultMap);
                // QC#21358 Mod End
            }

        } else if (TRSMT_METH_TP.EMAIL_PDF.equals(bizMsg.trsmtMethTpCd.getValue())) {
            setTrsmtMethInfoEmailPDF(bizMsg, resultMap);

        } else if (TRSMT_METH_TP.PRINTER.equals(bizMsg.trsmtMethTpCd.getValue())) {
            setTrsmtMethInfoPrinter(bizMsg, resultMapList);
        }
    }

    /**
     * getTrsmtMethInfoPrinter
     * @param bizMsg NPAL1500CMsg
     * @param resultMapList List<Map<String, Object>>
     */
    private static void setTrsmtMethInfoPrinter(NPAL1500CMsg bizMsg, List<Map<String, Object>> resultMapList) {
        Map<String, Object> resultMap;
        if (resultMapList != null) {
            for (int i = 0; i < resultMapList.size(); i++) {
                resultMap = resultMapList.get(i);

                ZYPEZDItemValueSetter.setValue(bizMsg.rptDestId_CD.no(i), (String) resultMap.get("RPT_DEST_ID"));
                ZYPEZDItemValueSetter.setValue(bizMsg.rptDestDescTxt_NM.no(i), (String) resultMap.get("RPT_DEST_DESC_TXT"));

                if (i >= bizMsg.rptDestId_CD.length()) {
                    break;
                }
            }
        }
    }

    /**
     * getTrsmtMethInfoEmailPDF
     * @param bizMsg NPAL1500CMsg
     * @param resultMap List<Map<String, Object>>
     */
    private static void setTrsmtMethInfoEmailPDF(NPAL1500CMsg bizMsg, List<Map<String, Object>> resultMapList) {
        Map<String, Object> resultMap;
        StringBuilder appSendPoEmlAddr = new StringBuilder();
        String sendPoEmlAddr = "";
        String inactiveDate = "";                                                   // 2019/11/11 T.Ogura [QC#54588,ADD]
        String salesDt = ZYPDateUtil.getSalesDate(bizMsg.glblCmpyCd.getValue());    // 2019/11/11 T.Ogura [QC#54588,ADD]
        if (resultMapList != null) {
            // QC#21358 Mod Start
            for (int i = 0; i < resultMapList.size(); i++) {
                // String sendPoEmlAddr = (String) resultMap.get("SEND_PO_EML_ADDR");
                resultMap = resultMapList.get(i);
                sendPoEmlAddr = (String) resultMap.get("CTAC_PSN_EML");
                inactiveDate = (String) resultMap.get("INAC_DT");    // 2019/11/11 T.Ogura [QC#54588,ADD]
                // START 2019/11/11 T.Ogura [QC#54588,MOD]
//                if (ZYPCommonFunc.hasValue(sendPoEmlAddr)) {
                if (ZYPCommonFunc.hasValue(sendPoEmlAddr)
                        && (!ZYPCommonFunc.hasValue(inactiveDate) || ZYPDateUtil.compare(salesDt, inactiveDate) < 0)) {
                // END   2019/11/11 T.Ogura [QC#54588,MOD]
                    if (appSendPoEmlAddr.length() != 0) {
                        appSendPoEmlAddr.append(",");
                    }
                    appSendPoEmlAddr.append(sendPoEmlAddr);
                }
            }

            if (appSendPoEmlAddr.length() > 700) {
                sendPoEmlAddr = appSendPoEmlAddr.substring(0, 700);
            } else {
                sendPoEmlAddr = appSendPoEmlAddr.toString();
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.sendPoEmlAddr, sendPoEmlAddr);
            // QC#21358 Mod End
        }
    }

    /**
     * pagination for Add New Line,AddConfig
     * @param glblMsg NPAL1500SMsg
     * @param bizMsg NPAL1500CMsg
     */
    private static void paginationForAddNewline(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {
        int recordLength = glblMsg.A.getValidCount();
        int pageLength = bizMsg.A.length();
        int index = 1;
        if (recordLength == 0) {
            index += (recordLength / pageLength) * pageLength;
        } else if (recordLength % pageLength == 0) {
            index += ((recordLength / pageLength) - 1) * pageLength;
        } else {
            index += (recordLength / pageLength) * pageLength;
        }

        // set value to pagenation items
        bizMsg.xxPageShowFromNum.setValue(index);
        bizMsg.xxPageShowOfNum.setValue(glblMsg.A.getValidCount());

        NPAL1500CommonLogic.pagenation(bizMsg, glblMsg);
    }

//    /**
//     * set Derive Item Info
//     * @param glblMsg NPAL1500SMsg
//     * @param bizMsg NPAL1500CMsg
//     * @return boolean
//     */
//    private static boolean setDeriveItemInfo(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg, int index) {
//
//        if (!NPAL1500CommonLogic.checkMdseInfo(bizMsg, glblMsg, index)) {
//
//            return false;
//        }
//
//        // Get Primary Vendor from ASL
//        NPZC113001PMsg npzc113001PMsg = NPAL1500CommonLogic.execGetPrimVndFromAsl(bizMsg, glblMsg, index);
//
//        if (npzc113001PMsg == null) {
//
//            return false;
//        }
//
//        // set Info
//        if (ZYPCommonFunc.hasValue(npzc113001PMsg.splyItemNum)) {
//            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).aslMdseCd_A1, npzc113001PMsg.splyItemNum);
//        } else {
//            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).aslMdseCd_A1, npzc113001PMsg.mdseCd);
//        }
//
//        if (!ZYPCommonFunc.hasValue(glblMsg.A.no(index).entDealNetUnitPrcAmt_A1)) {
//            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).entDealNetUnitPrcAmt_A1, npzc113001PMsg.unitPrcAmt);
//        }
//
//        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).poDispUomCd_A1, npzc113001PMsg.vndUomCd);
//
//        // Get PKG_UOM_CLS_DESC_TXT
//        if (ZYPCommonFunc.hasValue(npzc113001PMsg.vndUomCd)) {
//            S21SsmEZDResult result = NPAL1500Query.getInstance()//
//                    .getPkgUomClsDescTxt(glblMsg.glblCmpyCd.getValue(), npzc113001PMsg.vndUomCd.getValue());
//
//            if (result.isCodeNormal()) {
//                String pkgUomClsDescTxt = (String) result.getResultObject();
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).pkgUomClsDescTxt_A1, pkgUomClsDescTxt);
//            }
//        }
//
//        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).aslDtlPk_HD, npzc113001PMsg.aslDtlPk);
//        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).aslUnitPrcAmt_HD, npzc113001PMsg.unitPrcAmt);
//
//        // Call Change Vendor UOM API
//        NPZC129001PMsg npzc129001PMsg = NPAL1500CommonLogic.execChgVndUom(bizMsg, glblMsg, index, npzc113001PMsg.vndMinOrdQty.getValue());
//
//        if (npzc129001PMsg == null) {
//
//            return false;
//        }
//
//        // set Info
//        if (!ZYPCommonFunc.hasValue(glblMsg.A.no(index).poDispQty_A1) || (glblMsg.A.no(index).poDispQty_A1.getValueInt() < npzc129001PMsg.poDispQty_O1.getValueInt())) {
//            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).poDispQty_A1, npzc129001PMsg.poDispQty_O1);
//        }
//
//        // Quantity is scale zero.
//        BigDecimal qty = glblMsg.A.no(index).poDispQty_A1.getValue().setScale(0);
//        BigDecimal extTotal = npzc113001PMsg.unitPrcAmt.getValue().multiply(qty);
//        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).entPoDtlDealNetAmt_A1, extTotal);
//
//        return true;
//    }
//    /**
//     * <pre>
//     * set Derive Item Info BB
//     * </pre>
//     * @param glblMsg NPAL1500SMsg
//     * @param bizMsg NPAL1500CMsg
//     * @param index index of SMsg Line
//     * @return boolean true:Success false:Fale
//     */
//    private static boolean setDeriveItemInfoBB(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg, int index) {
//
//        Map<String, Object> resultMap = null;
//
//        S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getDeriveItemInfo(bizMsg, glblMsg.A.no(index).mdseCd_A1.getValue());
//
//        if (ssmResult.isCodeNormal()) {
//
//            resultMap = (Map<String, Object>) ssmResult.getResultObject();
//
//            if (resultMap != null && !resultMap.isEmpty()) {
//
//                // set Info
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).mdseCd_A1, (String) resultMap.get("MDSE_CD"));
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).mdseDescShortTxt_A1, (String) resultMap.get("MDSE_DESC_SHORT_TXT"));
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).aslMdseCd_A1, (String) resultMap.get("MDSE_CD"));
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).rcvSerTakeFlg_IB, (String) resultMap.get("RCV_SER_TAKE_FLG"));
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).instlBaseCtrlFlg_IB, (String) resultMap.get("INSTL_BASE_CTRL_FLG"));
//            }
//        }
//
//        if (resultMap == null) {
//
//            glblMsg.A.no(index).mdseCd_A1.setErrorInfo(1, NPAM0076E, new String[] {glblMsg.A.no(index).mdseCd_A1.getValue() });
//            bizMsg.setMessageInfo(NPAM0076E, new String[] {"Item#" });
//            return false;
//
//        } else if (ZYPConstant.FLG_ON_Y.equals((String) resultMap.get("DSCTN_FLG"))) {
//
//            glblMsg.A.no(index).mdseCd_A1.setErrorInfo(1, NPAM0076E);
//            bizMsg.setMessageInfo(NPZM0140E);
//            return false;
//
//        } else if (ZYPConstant.FLG_ON_Y.equals((String) resultMap.get("PRNT_CMPY_SET_MDSE_FLG"))) {
//
//            glblMsg.A.no(index).mdseCd_A1.setErrorInfo(1, NPAM1488E);
//            bizMsg.setMessageInfo(NPAM1488E);
//            return false;
//
//        } else {
//
//            NLXC001001GetInventoryItemCostBean nlxc001001Bean = new NLXC001001GetInventoryItemCostBean();
//            nlxc001001Bean.setGlblCmpyCd(glblMsg.glblCmpyCd.getValue());
//            nlxc001001Bean.setInvtyLocCd(glblMsg.destRtlWhCd_DS.getValue().concat(glblMsg.A.no(index).destRtlSwhCd_A1.getValue()));
//            nlxc001001Bean.setMdseCd(glblMsg.A.no(index).mdseCd_A1.getValue());
//
//            if (ZYPCommonFunc.hasValue(glblMsg.A.no(index).poDispQty_A1)) {
//
//                nlxc001001Bean.setQty(glblMsg.A.no(index).poDispQty_A1.getValue());
//
//            } else {
//
//                nlxc001001Bean.setQty(new BigDecimal(1));
//            }
//            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).poDispQty_A1, nlxc001001Bean.getQty());
//
//            NLXC001001GetInventoryItemCost.getInventoryItemCost(nlxc001001Bean);
//
//            if (!nlxc001001Bean.getErrList().isEmpty()) {
//                String errMsgCd = nlxc001001Bean.getErrList().get(0);
//                glblMsg.A.no(index).mdseCd_A1.setErrorInfo(1, errMsgCd);
//                return false;
//            }
//
//            // set Info
//            if (!ZYPCommonFunc.hasValue(glblMsg.A.no(index).entDealNetUnitPrcAmt_A1)) {
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).entDealNetUnitPrcAmt_A1, nlxc001001Bean.getDspUnitPrcAmt());
//            }
//            BigDecimal extTotal = glblMsg.A.no(index).entDealNetUnitPrcAmt_A1.getValue().multiply(glblMsg.A.no(index).poDispQty_A1.getValue());
//            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).entPoDtlDealNetAmt_A1, extTotal);
//            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).poDispUomCd_A1, VND_UOM.EACH);
//        }
//
//        return true;
//    }

//    /**
//     * Set PRNT_CMPY_SET_MDSE_FLG
//     * @param bizMsg NPAL1500CMsg
//     * @param glblMsg NPAL1500SMsg
//     * @param index index of SMsg Line
//     * @return boolean true:Success false:Failure
//     */
//    @SuppressWarnings("unchecked")
//    private static boolean setPrntCmpySetMdseFlg(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg, int index) {
//
//        Map<String, Object> resultMap = null;
//
//        S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getDeriveItemInfo(bizMsg, glblMsg.A.no(index).mdseCd_A1.getValue());
//
//        if (ssmResult.isCodeNormal()) {
//            resultMap = (Map<String, Object>) ssmResult.getResultObject();
//            if (resultMap != null) {
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).prntCmpySetMdseFlg_HD, (String) resultMap.get("PRNT_CMPY_SET_MDSE_FLG"));
//            }
//        }
//
//        if (resultMap == null) {
//            glblMsg.A.no(index).mdseCd_A1.setErrorInfo(1, NPAM0076E, new String[] {glblMsg.A.no(index).mdseCd_A1.getValue() });
//            bizMsg.setMessageInfo(NPAM0076E, new String[] {"Item#" });
//            return false;
//        }
//        return true;
//    }


    /**
     * OnChange_LineType
     * @param bizMsg NPAL1500CMsg
     * @param glblMsg NPAL1500SMsg
     */
    private void doProcess_NPAL1500Scrn00_OnChange_LineType(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        // Copy Message
        NPAL1500CommonLogic.copyFromCmsgOntoSmsg(bizMsg, glblMsg);

        int idx = bizMsg.xxNum.getValueInt();
        int pageShowFromNum = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        // Index of SMsg Line
        int indexS = pageShowFromNum + idx;

        //QC#62443 2024/3/1 Add Start
        String varCharExpSplyCoaCmpyCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_EXP_SPLY_COA_CMPY_CD, bizMsg.glblCmpyCd.getValue());
        String varCharExpSplyCoaBrCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_EXP_SPLY_COA_BR_CD, bizMsg.glblCmpyCd.getValue());
        String varCharExpSplyCoaAcctCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_EXP_SPLY_COA_ACCT_CD, bizMsg.glblCmpyCd.getValue());
        String varCharExpSplyCoaChCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_EXP_SPLY_COA_CH_CD, bizMsg.glblCmpyCd.getValue());
        String varCharExpSplyCoaAfflCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_EXP_SPLY_COA_AFFL_CD, bizMsg.glblCmpyCd.getValue());
        String varCharExpSplyCoaProjCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_EXP_SPLY_COA_PROJ_CD, bizMsg.glblCmpyCd.getValue());
        String varCharExpSplyCoaExtnCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_EXP_SPLY_COA_EXTN_CD, bizMsg.glblCmpyCd.getValue());

        StringBuffer sb = new StringBuffer();
        //QC#62443 2024/3/1 Add End
        // START 2024/1/1 K.Iwamoto [QC#62443, ADD]]
        NPAL1500CommonLogic.checkLineType(bizMsg, glblMsg, indexS);
        // END 2024/1/1 K.Iwamoto [QC#62443, ADD]]
        //QC#62443 2024/3/1 Add Start
        if (PO_LINE_TP.EXPENSE.equals(glblMsg.A.no(indexS).poLineTpCd_A1.getValue())) {

            if (ZYPCommonFunc.hasValue(glblMsg.A.no(indexS).mdseCd_A1.getValue())) {

                MDSETMsg mdseTMsg = new MDSETMsg();

                ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, glblMsg.A.no(indexS).mdseCd_A1.getValue());
                mdseTMsg = (MDSETMsg) EZDTBLAccessor.findByKey(mdseTMsg);

                S21SsmEZDResult result = NPAL1500Query.getInstance().getCoaCcCd(bizMsg.glblCmpyCd.getValue(), glblMsg.A.no(indexS).mdseCd_A1.getValue());

                String splyCoaCcCd = (String) result.getResultObject();

                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(indexS).splyCoaCcCd, splyCoaCcCd);
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(indexS).splyCoaProdCd, mdseTMsg.coaProdCd.getValue());

                sb.append(varCharExpSplyCoaCmpyCd);
                sb.append(PERIOD);
                sb.append(varCharExpSplyCoaBrCd);
                sb.append(PERIOD);
                sb.append(glblMsg.A.no(indexS).splyCoaCcCd.getValue());
                sb.append(PERIOD);
                sb.append(varCharExpSplyCoaAcctCd);
                sb.append(PERIOD);
                sb.append(glblMsg.A.no(indexS).splyCoaProdCd.getValue());
                sb.append(PERIOD);
                sb.append(varCharExpSplyCoaChCd);
                sb.append(PERIOD);
                sb.append(varCharExpSplyCoaAfflCd);
                sb.append(PERIOD);
                sb.append(varCharExpSplyCoaProjCd);
                sb.append(PERIOD);
                sb.append(varCharExpSplyCoaExtnCd);

                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(indexS).xxScrItem130Txt_CH, sb.toString());

        } else if (!ZYPCommonFunc.hasValue(glblMsg.A.no(indexS).mdseCd_A1.getValue())) {

            sb.append(varCharExpSplyCoaCmpyCd);
            sb.append(PERIOD);
            sb.append(varCharExpSplyCoaBrCd);
            sb.append(PERIOD);
            sb.append(PERIOD);
            sb.append(varCharExpSplyCoaAcctCd);
            sb.append(PERIOD);
            sb.append(PERIOD);
            sb.append(varCharExpSplyCoaChCd);
            sb.append(PERIOD);
            sb.append(varCharExpSplyCoaAfflCd);
            sb.append(PERIOD);
            sb.append(varCharExpSplyCoaProjCd);
            sb.append(PERIOD);
            sb.append(varCharExpSplyCoaExtnCd);

            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(indexS).xxScrItem130Txt_CH, sb.toString());
        }
        }
        //QC#62443 2024/3/1 Add End

        // 2019/01/15 QC#29778 Add Start
        boolean isChangeToExpense = false;
        if (ZYPCommonFunc.hasValue(glblMsg.A.no(indexS).poLineTpCd_A1)
                && (PO_LINE_TP.EXPENSE.equals(glblMsg.A.no(indexS).poLineTpCd_A1.getValue())
                        || PO_LINE_TP.EXPENSE_WITH_RECEIPT.equals(glblMsg.A.no(indexS).poLineTpCd_A1.getValue()))) {
            isChangeToExpense = true;
        }

        String prntLinNum = glblMsg.A.no(indexS).poOrdDtlLineNum_A1.getValue();
        for (int i = indexS + 1; i < glblMsg.A.getValidCount(); i++) {
            NPAL1500_ASMsg childSMsg = glblMsg.A.no(i);
            String setLineNum = childSMsg.setPoOrdDtlLineNum.getValue();
            if (PO_MDSE_CMPSN_TP.CHILD.equals(childSMsg.poMdseCmpsnTpCd_HD.getValue()) //
                    && S21StringUtil.isEquals(prntLinNum, setLineNum)) {
                ZYPEZDItemValueSetter.setValue(childSMsg.poLineTpCd_A1, glblMsg.A.no(indexS).poLineTpCd_A1);
                if (!isChangeToExpense) {
                    childSMsg.splyCoaCmpyCd.clear();
                    childSMsg.splyCoaBrCd.clear();
                    childSMsg.splyCoaCcCd.clear();
                    childSMsg.splyCoaAcctCd.clear();
                    childSMsg.splyCoaProdCd.clear();
                    childSMsg.splyCoaChCd.clear();
                    childSMsg.splyCoaAfflCd.clear();
                    childSMsg.splyCoaProjCd.clear();
                    childSMsg.splyCoaExtnCd.clear();
                    childSMsg.xxScrItem130Txt_CH.clear();
                    ZYPEZDItemValueSetter.setValue(glblMsg.vndCd_HG, glblMsg.vndCd);
                }
            }
        }
        // 2019/01/15 QC#29778 Add End
        // QC#27770 Add Start
        String glblCmpyCd = glblMsg.glblCmpyCd.getValue();
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (!NPAL1500CommonLogic.checkManualSegmentElementForSMsg(bizMsg, glblMsg, glblCmpyCd, BIZ_ID, i)) {
                ZYPEZDItemValueSetter.setValue(glblMsg.xxRsltFlg, NPAL1500Constant.RSLT_FLG_ERROR);
                if (!ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
                    bizMsg.setMessageInfo(NLAM0077E, new String[] {"Charge ACC" });
                }
                return;
            }
        }
        // QC#27770 Add End

        // paging after Add New Item
        NPAL1500CommonLogic.getAccountInfo(bizMsg, glblMsg, indexS, true);
        // 2019/01/15 QC#29778 Add Start
        for (int i = indexS + 1; i < glblMsg.A.getValidCount(); i++) {
            NPAL1500_ASMsg childSMsg = glblMsg.A.no(i);
            String setLineNum = childSMsg.setPoOrdDtlLineNum.getValue();
            if (PO_MDSE_CMPSN_TP.CHILD.equals(childSMsg.poMdseCmpsnTpCd_HD.getValue()) //
                    && S21StringUtil.isEquals(prntLinNum, setLineNum)) {
                if (isChangeToExpense) {
                    NPAL1500CommonLogic.getAccountInfo(bizMsg, glblMsg, i, true);
                    ZYPEZDItemValueSetter.setValue(glblMsg.vndCd_HG, glblMsg.vndCd);
                }
            }
        }
        // 2019/01/15 QC#29778 Add End

        NPAL1500CommonLogic.copyFromSMsgOntoCmsg(bizMsg, glblMsg);
    }
    
    //QC#18602(Sol#102) ADD Start    
    /**
     * setAPInvoiceData
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     */
    private static void setAPInvoiceData(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg) {

        // Get AP Invoice Detail Data
        S21SsmEZDResult apInvList = NPAL1500Query.getInstance().getAPInvoiceData(cMsg);

        BigDecimal total = BigDecimal.ZERO;

        if (apInvList.isCodeNormal()) {

            int queryResCnt = apInvList.getQueryResultCount();
            if (queryResCnt > sMsg.B.length()) {
                queryResCnt = sMsg.B.length();
            }

            List<Map<String, Object>> resultList = (List<Map<String, Object>>) apInvList.getResultObject();

            int i = 0;
            for (; i < resultList.size(); i++) {

                if (i == sMsg.B.length()) {
                    break;
                }

                Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).apVndInvNum_B1, (String) resultMap.get("AP_VND_INV_NUM"));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).delyOrdNum_B1, (String) resultMap.get("DELY_ORD_NUM"));
                // START 2021/04/21 J.Evangelista [QC#57102,ADD]
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).poOrdDtlLineNum_B1, (String) resultMap.get("PO_ORD_DTL_LINE_NUM"));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).dispPoDtlLineNum_B1, (String) resultMap.get("DISP_PO_DTL_LINE_NUM"));
                //   END 2021/04/21 J.Evangelista [QC#57102,ADD]
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).mdseCd_B1, (String) resultMap.get("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).mdseDescShortTxt_B1, (String) resultMap.get("MDSE_DESC_SHORT_TXT"));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).invDt_B1, (String) resultMap.get("INV_DT"));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).pmtDueDt_B1, (String) resultMap.get("PMT_DUE_DT"));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).acInvJrnlCostAmt_B1, (BigDecimal) resultMap.get("AC_INV_JRNL_COST_AMT"));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).apBillQty_B1, (BigDecimal) resultMap.get("AP_BILL_QTY"));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).invAuthDt_B1, (String) resultMap.get("INV_AUTH_DT"));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).pmtHldFlg_B1, (String) resultMap.get("PMT_HLD_FLG"));
                // AP Invoice Entry Parameter
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).apVndCd_B1, (String) resultMap.get("AP_VND_CD"));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).apVndInvSqNum_B1, (String) resultMap.get("AP_VND_INV_SQ_NUM"));

                sMsg.B.setValidCount(i + 1);

                // set Total Amount
                total = total.add(sMsg.B.no(i).acInvJrnlCostAmt_B1.getValue());

            }

            i = 0;
            for (; i <= sMsg.B.getValidCount(); i++) {
                if (i == cMsg.B.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i), null);
            }

            ZYPEZDItemValueSetter.setValue(sMsg.xxTotAmt_AP, total);
            ZYPEZDItemValueSetter.setValue(cMsg.xxTotAmt_AP, total);
            cMsg.B.setValidCount(i);
            
            // QC#25714 Add Start
            cMsg.xxPageShowFromNum_B1.setValue(1);
            cMsg.xxPageShowToNum_B1.setValue(cMsg.B.getValidCount());
            cMsg.xxPageShowOfNum_B1.setValue(queryResCnt);

            // page data not found
        } else {
            cMsg.xxPageShowFromNum_B1.clear();
            cMsg.xxPageShowToNum_B1.clear();
            cMsg.xxPageShowOfNum_B1.clear();
            // QC#25714 Add End
        }

    }
    //QC#18602(Sol#102) ADD End

    /**
     * Apply Add QC#21169
     * @param bizMsg NPAL1500CMsg
     * @param glblMsg NPAL1500SMsg
     */
    private void doProcess_NPAL1500Scrn00_Apply(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.rqstRcvDt.getValue())) {

            bizMsg.rqstRcvDt.setErrorInfo(1, NPAM0009E);
            return;
        }

        if (bizMsg.A.getValidCount() == 0) {
            bizMsg.setMessageInfo(NPAM1351E);
            return;
        }

        String slsDt = ZYPDateUtil.getSalesDate(bizMsg.glblCmpyCd.getValue());

        if (!bizMsg.rqstRcvDt.isError() && (!ZYPCommonFunc.hasValue(bizMsg.poStsCd_H) || PO_STS.SAVED.equals(bizMsg.poStsCd_H.getValue()))) {
            if (ZYPDateUtil.compare(slsDt, bizMsg.rqstRcvDt.getValue()) > 0) {
                bizMsg.rqstRcvDt.setErrorInfo(1, NPZM0041E);
                return;
            }
        }

        // Copy Message
        NPAL1500CommonLogic.copyFromCmsgOntoSmsg(bizMsg, glblMsg);

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (PO_LINE_STS.OPEN.equals(glblMsg.A.no(i).poLineStsCd_A1.getValue()) //
                    && (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).poSendTs)) || !PO_MDSE_CMPSN_TP.CHILD.equals(glblMsg.A.no(i).poMdseCmpsnTpCd_HD.getValue())) {
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).rqstRcvDt_A1, bizMsg.rqstRcvDt);
            }
            // START 2023/04/28 S.Dong [QC#60966, ADD]
            // Get Primary Vendor from ASL
            NPZC113001PMsg npzc113001PMsg = NPAL1500CommonLogic.execGetPrimVndFromAsl(bizMsg, glblMsg, i);
            NPAL1500CommonLogic.setRqstShipDtRddApply(glblMsg, npzc113001PMsg, i);
        //QC#63555 Mod Start
        }
        // Set rqstRcvDt (Date & Time Needed)
        NPAL1500CommonLogic.setHeaderRqstRcvDt_SmsgMaxVal(bizMsg, glblMsg);
        // Set rqstShipDt (Vendor Ship Date)
        NPAL1500CommonLogic.setHeaderRqstShipDt(bizMsg, glblMsg);
        // END 2023/04/28 S.Dong [QC#60966, ADD]
        //QC#63555 Mod End

        NPAL1500CommonLogic.copyFromSMsgOntoCmsg(bizMsg, glblMsg);
        bizMsg.setMessageInfo(ZZZM9003I, new String[] {"Apply" });
    }
    // START 2023/02/14 S.Dong [QC#60966, ADD]
    /**
     * Apply Add For Vendor Ship Date QC#60966
     * @param bizMsg NPAL1500CMsg
     * @param glblMsg NPAL1500SMsg
     */
    private void doProcess_NPAL1500Scrn00_RqstShipDt_Apply(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.rqstShipDt.getValue())) {

            bizMsg.rqstShipDt.setErrorInfo(1, NPAM0009E);
            return;
        }

        if (bizMsg.A.getValidCount() == 0) {
            bizMsg.setMessageInfo(NPAM1351E);
            return;
        }

        String slsDt = ZYPDateUtil.getSalesDate(bizMsg.glblCmpyCd.getValue());

        if (!bizMsg.rqstShipDt.isError() && (!ZYPCommonFunc.hasValue(bizMsg.poStsCd_H) || PO_STS.SAVED.equals(bizMsg.poStsCd_H.getValue()))) {
            if (ZYPDateUtil.compare(slsDt, bizMsg.rqstShipDt.getValue()) > 0) {
                bizMsg.rqstShipDt.setErrorInfo(1, NPZM0041E);
                return;
            }
        }

        // Copy Message
        NPAL1500CommonLogic.copyFromCmsgOntoSmsg(bizMsg, glblMsg);

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (PO_LINE_STS.OPEN.equals(glblMsg.A.no(i).poLineStsCd_A1.getValue()) //
                    && (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).poSendTs)) || !PO_MDSE_CMPSN_TP.CHILD.equals(glblMsg.A.no(i).poMdseCmpsnTpCd_HD.getValue())) {
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).rqstShipDt_A1, bizMsg.rqstShipDt);
            }
        }

        NPAL1500CommonLogic.copyFromSMsgOntoCmsg(bizMsg, glblMsg);
        bizMsg.setMessageInfo(ZZZM9003I, new String[] {"Vendor Ship Date Apply" });
    }
    // END 2023/02/14 S.Dong [QC#60966, ADD]    
    // QC#21358 Add Start
    /**
     * setTrsmtMethInfoFAX
     * @param bizMsg NPAL1500CMsg
     * @param resultMap List<Map<String, Object>>
     */
    private static void setTrsmtMethInfoFAX(NPAL1500CMsg bizMsg, List<Map<String, Object>> resultMapList) {
        Map<String, Object> resultMap;
        StringBuilder appSendPoFaxNum = new StringBuilder();
        String sendPoFaxNum = "";
        if (resultMapList != null) {
            for (int i = 0; i < resultMapList.size(); i++) {
                resultMap = resultMapList.get(i);
                sendPoFaxNum = (String) resultMap.get("CTAC_PSN_FAX");
                if (ZYPCommonFunc.hasValue(sendPoFaxNum)) {
                    if (appSendPoFaxNum.length() != 0) {
                        appSendPoFaxNum.append(",");
                    }
                    appSendPoFaxNum.append(sendPoFaxNum);
                }
            }
            if (appSendPoFaxNum.length() > 20) {
                sendPoFaxNum = appSendPoFaxNum.substring(0, 20);
            } else {
                sendPoFaxNum = appSendPoFaxNum.toString();
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.sendPoFaxNum, sendPoFaxNum);
        }
    }
    // QC#21358 Add End
    
    //QC#18420 Add Start
    /**
     * doProcess_NPAL1500Scrn00_Get_LinePriceInfo
     * @param bizMsg NPAL1500CMsg
     * @param glblMsg NPAL1500SMsg
     */
    private void doProcess_NPAL1500Scrn00_Get_LinePriceInfo(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        //set Line Price
        int idx = bizMsg.xxNum.getValueInt();

        // Copy Message
        NPAL1500CommonLogic.copyFromCmsgOntoSmsg(bizMsg, glblMsg);

        // Index of SMsg Line
        int pageShowFromNum = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        int indexS = pageShowFromNum + idx;

        NPAL1500CommonLogic.calcLinePrice(bizMsg, glblMsg, indexS);

        EZDMsg.copy(glblMsg.A.no(indexS), null, bizMsg.A.no(idx), null);
    }
    
    /**
     * doProcess_NPAL1500Scrn00_Apply
     * @param bizMsg NPAL1500CMsg
     * @param glblMsg NPAL1500SMsg
     */
    private void doProcess_NPAL1500Scrn00_Apply_Disc(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        // Copy Message
        NPAL1500CommonLogic.copyFromCmsgOntoSmsg(bizMsg, glblMsg);

        if (!ZYPCommonFunc.hasValue(bizMsg.poDtlDiscPct.getValue())) {

            bizMsg.poDtlDiscPct.setErrorInfo(1, NPAM0009E);
            return;
        }

        if (bizMsg.A.getValidCount() == 0) {
            bizMsg.setMessageInfo(NPAM1351E);
            return;
        }

        if (bizMsg.poDtlDiscPct.isError()) {
                return;
        }

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if ((PO_LINE_STS.OPEN.equals(glblMsg.A.no(i).poLineStsCd_A1.getValue()) || PO_LINE_STS.OPEN_FOR_RECEIPT.equals(glblMsg.A.no(i).poLineStsCd_A1.getValue())
                    || PO_LINE_STS.OPEN_FOR_INVOICE.equals(glblMsg.A.no(i).poLineStsCd_A1.getValue()))
                    && (!PO_MDSE_CMPSN_TP.CHILD.equals(glblMsg.A.no(i).poMdseCmpsnTpCd_HD.getValue()))
                    && (ZYPCommonFunc.hasValue(glblMsg.A.no(i).aslUnitPrcAmt_A1.getValue()))) {
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).poDtlDiscPct_A1, bizMsg.poDtlDiscPct);
                //QC#26893 Del Start
                NPAL1500CommonLogic.calcLinePrice(bizMsg, glblMsg, i);
                //QC#26893 Del End
            }
        }


        NPAL1500CommonLogic.copyFromSMsgOntoCmsg(bizMsg, glblMsg);
        bizMsg.setMessageInfo(ZZZM9003I, new String[] {"Apply" });
    }
    //QC#18420 Add End

    // Add Start 2019/12/10 QC#55004
    /**
     * setPoDetailInfo Mod QC#55313
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     * @param glblCmpyCd String
     */
    private static void setPoDetailInfo(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg, String glblCmpyCd) {

        S21SsmEZDResult ssmResult = new S21SsmEZDResult();
        // QC#55274
        List<BigDecimal> chkIBList = new ArrayList<BigDecimal>(); 

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            if(PO_MDSE_CMPSN_TP.CHILD.equals(sMsg.A.no(i).poMdseCmpsnTpCd_HD.getValue())){
                sMsg.A.no(i).aslUnitPrcAmt_A1.clear();
            }

            if (!(PO_LINE_TP.EXPENSE.equals(sMsg.A.no(i).poLineTpCd_A1.getValue())// 
                    || PO_LINE_TP.EXPENSE_WITH_RECEIPT.equals(sMsg.A.no(i).poLineTpCd_A1.getValue()))) {
                sMsg.A.no(i).xxScrItem130Txt_CH.clear();
            }

            // Get SVC_MACH_MSTR.
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).svcConfigMstrPk_A1) && ZYPCommonFunc.hasValue(sMsg.A.no(i).mdseCd_A1)) {
                ssmResult = NPAL1500Query.getInstance().getSvcMachMstr(glblCmpyCd, sMsg.A.no(i).mdseCd_A1.getValue(), sMsg.A.no(i).svcConfigMstrPk_A1.getValue(), sMsg.A.no(i).serNumListTxt_A1);
                if (ssmResult.isCodeNormal()) {
                    List<Map<String, Object>> resultMap = (List<Map<String, Object>>) ssmResult.getResultObject();
                    // QC#55274
//                    if (resultMap != null && !resultMap.isEmpty()) {
//                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).svcMachMstrPk_IB, (BigDecimal) resultMap.get(0).get("SVC_MACH_MSTR_PK"));
//                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).ezUpTime_IB, (String) resultMap.get(0).get("EZUPTIME"));
//                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).ezUpTimeZone_IB, (String) resultMap.get(0).get("EZUPTIMEZONE"));
//                    }
                    if (resultMap != null) {
                        for (Map<String, Object> ibMap : resultMap) {
                            if (chkIBList.contains((BigDecimal) ibMap.get("SVC_MACH_MSTR_PK"))) {
                                continue;
                            } else {
                                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).svcMachMstrPk_IB, (BigDecimal) ibMap.get("SVC_MACH_MSTR_PK"));
                                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).ezUpTime_IB, (String) ibMap.get("EZUPTIME"));
                                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).ezUpTimeZone_IB, (String) ibMap.get("EZUPTIMEZONE"));
                            }
                            chkIBList.add((BigDecimal) ibMap.get("SVC_MACH_MSTR_PK"));
                            break;
                        }
                    }
                }
            }

            // Get MDSE_ITEM_TP_CD
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).mdseItemTpCd_A1)) { // 2019/01/25 QC#30082 Add Condition
                MDSETMsg mdseTMsg = new MDSETMsg();

                ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, sMsg.A.no(i).mdseCd_A1.getValue());
                mdseTMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(mdseTMsg);

                if (mdseTMsg != null) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mdseItemTpCd_A1, mdseTMsg.mdseItemTpCd.getValue());
                }
            }

            // QC#55313
            if (DS_PO_TP.SUBCONTRACT_PO.equals(cMsg.dsPoTpCd.getValue()) && ZYPCommonFunc.hasValue(sMsg.A.no(i).prchReqNum_A1)) {
                PRCH_REQTMsg prTMsg = new PRCH_REQTMsg();
                ZYPEZDItemValueSetter.setValue(prTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(prTMsg.prchReqNum, sMsg.A.no(i).prchReqNum_A1.getValue());

                prTMsg = (PRCH_REQTMsg) S21CacheTBLAccessor.findByKey(prTMsg);

                if (prTMsg != null && PRCH_REQ_STS.CANCELLED.equals(prTMsg.prchReqStsCd.getValue())) {
                    sMsg.A.no(i).prchReqNum_A1.clear();
                    sMsg.A.no(i).prchReqLineNum_A1.clear();
                    sMsg.A.no(i).prchReqLineSubNum.clear();
                }
            }
        }
    }
    // Add End 2019/12/10 QC#55004

    // START 2021/04/21 J.Evangelista [QC#57102,ADD]
    /**
     * Table Column Sort
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     */
    private void doProcess_NPAL1500Scrn00_TBLColumnSort(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg) {

        String tabName = cMsg.xxDplyTab.getValue();

        if (NPAL1500Constant.TAB_AP_INVOICE.equals(tabName)) {

            String sortTblNm = cMsg.xxSortTblNm_B1.getValue();
            String sortItemNm = cMsg.xxSortItemNm_B1.getValue();
            String sortOrdBy = cMsg.xxSortOrdByTxt_B1.getValue();

            if ("B".equals(sortTblNm)) {

                NPAL1500CommonLogic.sortTable(sMsg.B, sortItemNm, sortOrdBy, sMsg.B.no(0).getBaseContents());

                ZYPTableUtil.clear(cMsg.B);
                cMsg.xxPageShowFromNum_B1.setValue(1);
                cMsg.xxPageShowToNum_B1.clear();
                cMsg.xxPageShowOfNum_B1.setValue(sMsg.B.getValidCount());
                NPAL1500CommonLogic.pagenationAP(cMsg, sMsg);
            }
        }

    }
    // END 2021/04/21 J.Evangelista [QC#57102,ADD]

    // START 2023/04/19 TZ.Win [QC#61299,ADD]
    /**
     * doProcess_NPAL1500Scrn00_Apply_ASL
     * @param bizMsg NPAL1500CMsg
     * @param bizMsg NPAL1500SMsg
     */
    private void doProcess_NPAL1500Scrn00_Apply_ASL(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        if (bizMsg.A.getValidCount() == 0) {
            bizMsg.setMessageInfo(NPAM1351E);
            return;
        }

        // Copy Message
        NPAL1500CommonLogic.copyFromCmsgOntoSmsg(bizMsg, glblMsg);
        boolean hasErr = false;

        if(ZYPCommonFunc.hasValue(bizMsg.poStsCd_H.getValue())) {

            for (int idx = 0; idx < glblMsg.A.getValidCount(); idx++) {

                if (!PO_LINE_STS.CANCELLED.equals(glblMsg.A.no(idx).poLineStsCd_A1.getValue())
                        && !PO_LINE_STS.CLOSED.equals(glblMsg.A.no(idx).poLineStsCd_A1.getValue())) {

                    glblMsg.A.no(idx).aslUnitPrcAmt_A1.clear();
                    glblMsg.A.no(idx).poDtlDiscPct_A1.clear();
                    glblMsg.A.no(idx).entDealNetUnitPrcAmt_A1.clear();
                    glblMsg.A.no(idx).entPoDtlDealNetAmt_A1.clear();

                    if (DS_PO_TP.BUYBACK_PO.equals(bizMsg.dsPoTpCd.getValue())) {

                        NLXC001001GetInventoryItemCostBean nlxc001001Bean = new NLXC001001GetInventoryItemCostBean();
                        nlxc001001Bean.setGlblCmpyCd(glblMsg.glblCmpyCd.getValue());
                        nlxc001001Bean.setInvtyLocCd(glblMsg.destRtlWhCd_DS.getValue().concat(glblMsg.A.no(idx).destRtlSwhCd_A1.getValue()));
                        nlxc001001Bean.setMdseCd(glblMsg.A.no(idx).mdseCd_A1.getValue());

                        if (ZYPCommonFunc.hasValue(glblMsg.A.no(idx).poDispQty_A1)) {

                            nlxc001001Bean.setQty(glblMsg.A.no(idx).poDispQty_A1.getValue());

                        } else {

                            nlxc001001Bean.setQty(new BigDecimal(1));
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).poDispQty_A1, nlxc001001Bean.getQty());

                        NLXC001001GetInventoryItemCost.getInventoryItemCost(nlxc001001Bean);

                        // set Info
                        if (!ZYPCommonFunc.hasValue(glblMsg.A.no(idx).entDealNetUnitPrcAmt_A1)) {
                            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).entDealNetUnitPrcAmt_A1, nlxc001001Bean.getDspUnitPrcAmt());
                        }
                        BigDecimal extTotal = glblMsg.A.no(idx).entDealNetUnitPrcAmt_A1.getValue().multiply(glblMsg.A.no(idx).poDispQty_A1.getValue());
                        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).entPoDtlDealNetAmt_A1, extTotal);

                    } else {

                        // Get Primary Vendor from ASL
                        NPZC113001PMsg npzc113001PMsg = NPAL1500CommonLogic.execGetPrimVndFromAsl(bizMsg, glblMsg, idx);
                        if (npzc113001PMsg == null) {

                            hasErr = true;
                            continue;
                        }

                        if (!ZYPCommonFunc.hasValue(glblMsg.A.no(idx).aslUnitPrcAmt_A1)) {
                            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).aslUnitPrcAmt_A1, npzc113001PMsg.unitPrcAmt);
                        }

                        if (!ZYPCommonFunc.hasValue(glblMsg.A.no(idx).entDealNetUnitPrcAmt_A1)) {
                            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).entDealNetUnitPrcAmt_A1, npzc113001PMsg.unitPrcAmt);
                        }

                        NPZC129001PMsg npzc129001PMsg;
                        npzc129001PMsg = NPAL1500CommonLogic.execChgVndUom(bizMsg, glblMsg, idx, npzc113001PMsg.vndMinOrdQty.getValue());

                        if (npzc129001PMsg == null) {

                            String noAslCheckPoTp = "";
                            noAslCheckPoTp = ZYPCodeDataUtil.getVarCharConstValue("NO_ASL_CHECK_PO_TP", bizMsg.glblCmpyCd.getValue());
                            if (ZYPCommonFunc.hasValue(noAslCheckPoTp)) {
                                String[] array = noAslCheckPoTp.split(",");
                                for (int i = 0; i < array.length; i++) {
                                    String poTp = array[i];
                                    if (!poTp.equals(bizMsg.dsPoTpCd.getValue())) {
                                        glblMsg.A.no(idx).entDealNetUnitPrcAmt_A1.clear();
                                        glblMsg.A.no(idx).aslMdseCd_A1.clear();
                                        glblMsg.A.no(idx).poDispQty_A1.clear();
                                    }
                                }
                            }

                            hasErr = true;
                            continue;
                        }
                        // set Info
                        if (!ZYPCommonFunc.hasValue(glblMsg.A.no(idx).poDispQty_A1) || (glblMsg.A.no(idx).poDispQty_A1.getValueInt() < npzc129001PMsg.poDispQty_O1.getValueInt())) {
                            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).poDispQty_A1, npzc129001PMsg.poDispQty_O1);
                        }

                        // Quantity is scale zero.
                        BigDecimal qty = glblMsg.A.no(idx).poDispQty_A1.getValue().setScale(0);
                        BigDecimal extTotal = npzc113001PMsg.unitPrcAmt.getValue().multiply(qty);
                        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).entPoDtlDealNetAmt_A1, extTotal);
                    }

                    String lineNum = glblMsg.A.no(idx).dispPoDtlLineNum_A1.getValue();
                    String sameNum = lineNum.substring(0, lineNum.indexOf(".") + 1);
                    for (int i = idx + 1; i < glblMsg.A.getValidCount(); i++) {
                        if (glblMsg.A.no(i).dispPoDtlLineNum_A1.getValue().startsWith(sameNum)) {
                            idx++;
                        }
                    }
                }
            }

        } else {

            for (int idx = 0; idx < glblMsg.A.getValidCount(); idx++) {

                // initialize
                glblMsg.A.no(idx).mdseDescShortTxt_A1.clear();
                glblMsg.A.no(idx).aslMdseCd_A1.clear();
                glblMsg.A.no(idx).aslUnitPrcAmt_A1.clear();
                glblMsg.A.no(idx).poDtlDiscPct_A1.clear();
                glblMsg.A.no(idx).entDealNetUnitPrcAmt_A1.clear();
                glblMsg.A.no(idx).entPoDtlDealNetAmt_A1.clear();
                glblMsg.A.no(idx).instlBaseCtrlFlg_IB.clear();

                if (!bizMsg.dsPoTpCd.equals(DS_PO_TP.BUYBACK_PO)) {

                    glblMsg.A.no(idx).prntCmpySetMdseFlg_HD.clear();
                    glblMsg.A.no(idx).poMdseCmpsnTpCd_HD.clear();
                    glblMsg.A.no(idx).poDispUomCd_A1.clear();
                    glblMsg.A.no(idx).aslDtlPk_HD.clear();
                    glblMsg.A.no(idx).aslUnitPrcAmt_HD.clear();
                }

                // Check:Item# or Item Description is mandatory.
                if (NPAL1500CommonLogic.checkItemInfo(bizMsg, glblMsg, idx)) {
                    hasErr = true;
                }

                // Set Item Info
                if (DS_PO_TP.BUYBACK_PO.equals(bizMsg.dsPoTpCd.getValue())) {

                    if (!NPAL1500CommonLogic.setDeriveItemInfoBB(bizMsg, glblMsg, idx)) {

                        hasErr = true;
                    }

                } else {

                    // Set PRNT_CMPY_SET_MDSE_FLG
                    if (NPAL1500CommonLogic.setPrntCmpySetMdseFlg(bizMsg, glblMsg, idx)) {

                        hasErr = NPAL1500CommonLogic.setMdseInfo(bizMsg, glblMsg, idx, hasErr, false);

                    } else {

                        hasErr = true;
                    }
                }

                if (!NPAL1500CommonLogic.chkMdseDuplicateFromAsl(bizMsg, glblMsg, bizMsg.glblCmpyCd.getValue(), glblMsg.A.no(idx))) {
                    hasErr = true;
                }

                String glblCmpyCd = glblMsg.glblCmpyCd.getValue();
                for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                    if (!NPAL1500CommonLogic.checkManualSegmentElementForSMsg(bizMsg, glblMsg, glblCmpyCd, BIZ_ID, i)) {
                        if (!ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
                            bizMsg.setMessageInfo(NLAM0077E, new String[] {"Charge ACC" });
                        }
                        hasErr = true;
                    }
                }

                // Set Account Info
                if (!hasErr) {

                    NPAL1500CommonLogic.getAccountInfo(bizMsg, glblMsg, idx, true);

                    // Charg ACC For Set Child.
                    if (ZYPConstant.FLG_ON_Y.equals(glblMsg.A.no(idx).prntCmpySetMdseFlg_HD.getValue())) {
                        String prntLinNum = glblMsg.A.no(idx).poOrdDtlLineNum_A1.getValue();
                        for (int curIndex = 0; curIndex < glblMsg.A.getValidCount(); curIndex++) {
                            if (S21StringUtil.isEquals(prntLinNum, glblMsg.A.no(curIndex).setPoOrdDtlLineNum.getValue())) {
                                NPAL1500CommonLogic.getAccountInfo(bizMsg, glblMsg, curIndex, false);
                            }
                        }
                    }

                    List<BigDecimal> ordDtlLineList = new ArrayList<BigDecimal>();
                    for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                        ordDtlLineList.add(new BigDecimal(glblMsg.A.no(i).dispPoDtlLineNum_A1.getValue()));
                    }
                    
                    List<BigDecimal> ordDtlLineListTemp = new ArrayList<BigDecimal>();
                    String ordDtlLineNum;
                    String integralPart;
                    String fractionPart;
                    
                    for (BigDecimal ordDtlLine : ordDtlLineList) {
                        
                        ordDtlLineNum = ordDtlLine.toPlainString();
                        integralPart = ordDtlLineNum.substring(0, ordDtlLineNum.indexOf(PERIOD));
                        fractionPart = ordDtlLineNum.substring(ordDtlLineNum.indexOf(PERIOD)+1, ordDtlLineNum.length());
                        if (fractionPart.length() == 1 && !ZERO_STRING.equals(fractionPart)) {
                            fractionPart = ZERO_STRING + fractionPart;
                        }
                        ordDtlLine = new BigDecimal(integralPart + PERIOD + fractionPart);
                        ordDtlLineListTemp.add(ordDtlLine);
                    }
                    Collections.sort(ordDtlLineListTemp);
                    
                    ordDtlLineList.clear();
                    for (BigDecimal ordDtlLine : ordDtlLineListTemp) {
                        
                        ordDtlLineNum = ordDtlLine.toPlainString();
                        integralPart = ordDtlLineNum.substring(0, ordDtlLineNum.indexOf(PERIOD));
                        fractionPart = ordDtlLineNum.substring(ordDtlLineNum.indexOf(PERIOD)+1, ordDtlLineNum.length());
                        if (fractionPart.length() > 1 && fractionPart.startsWith(ZERO_STRING)) {
                            fractionPart = fractionPart.substring(1);
                        }
                        ordDtlLine = new BigDecimal(integralPart + PERIOD + fractionPart);
                        ordDtlLineList.add(ordDtlLine);
                    }

                    List<Integer> excludeLineList = new ArrayList<Integer>();
                    for (int j = 0; j < ordDtlLineList.size(); j++) {
                        for (int idxPoDetail = 0; idxPoDetail < glblMsg.A.getValidCount(); idxPoDetail++) {
                            if (NPAL1500CommonLogic.sortSetPoOrdDtlLineNum(ordDtlLineList, glblMsg, j, idxPoDetail, excludeLineList)) {
                                break;
                            }
                        }
                    }

                    if (glblMsg.A.no(idx).xxScrItem130Txt_CH.isError()) {
                        String prntPoOrdDtlLineNum = glblMsg.A.no(idx).poOrdDtlLineNum_A1.getValue();
                        EZDMessageInfo ezdMessageInfo = null;
                        if (glblMsg.A.no(idx).xxScrItem130Txt_CH.isError()) {
                            ezdMessageInfo = NPAL1500CommonLogic.getErrorInfo(glblMsg.A.no(idx), "xxScrItem130Txt_CH");
                        } else {
                            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                                if (S21StringUtil.isEquals(prntPoOrdDtlLineNum, glblMsg.A.no(i).setPoOrdDtlLineNum.getValue()) //
                                        && glblMsg.A.no(i).xxScrItem130Txt_CH.isError()) {
                                    ezdMessageInfo = NPAL1500CommonLogic.getErrorInfo(glblMsg.A.no(i), "xxScrItem130Txt_CH");
                                }
                            }
                        }
                        if (ezdMessageInfo != null) {
                            String msgCode = ezdMessageInfo.getCode();
                            String[] msgPara = ezdMessageInfo.getParameter();
                            if (msgPara == null) {
                                bizMsg.setMessageInfo(msgCode);
                            } else {
                                bizMsg.setMessageInfo(msgCode, msgPara);
                            }
                        }
                    }

                    S21SortTarget target = new S21SortTarget(glblMsg.A, glblMsg.A.no(0).getBaseContents());
                    S21SortKey key = new S21SortKey();
                    key.add("poOrdDtlLineNum_A1", "ASC");
                    S21EZDMsgArraySort.sort(target, key, 0, glblMsg.A.getValidCount());
                }

                // Set縺ｮ蝣ｴ蜷医∝ｭ舌ョ繝ｼ繧ｿ繧偵せ繧ｭ繝・・
                String lineNum = glblMsg.A.no(idx).dispPoDtlLineNum_A1.getValue();
                String sameNum = lineNum.substring(0, lineNum.indexOf(".") + 1);
                for (int i = idx + 1; i < glblMsg.A.getValidCount(); i++) {
                    if (glblMsg.A.no(i).dispPoDtlLineNum_A1.getValue().startsWith(sameNum)) {
                       idx++;
                    }
                }
            }

            // Set rqstRcvDt (Date & Time Needed)
            NPAL1500CommonLogic.setHeaderRqstRcvDt_SmsgMaxVal(bizMsg, glblMsg);
            // Set rqstShipDt (Vendor Ship Date)
            NPAL1500CommonLogic.setHeaderRqstShipDt(bizMsg, glblMsg);

            // renew details
            bizMsg.xxPageShowOfNum.setValue(glblMsg.A.getValidCount());

            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                if (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).mdseDescShortTxt_A1)) {
                    continue;
                }

                NPAL1500CommonLogic.getDefaultAccountInfo(bizMsg, glblMsg, bizMsg.glblCmpyCd.getValue(), i);
            }
        }
        
        NPAL1500CommonLogic.copyFromSMsgOntoCmsg(bizMsg, glblMsg);

        if(hasErr) {
            boolean error = false;
            int index = 0;
            for (; index < glblMsg.A.getValidCount(); index++) {
                if (glblMsg.A.no(index).mdseCd_A1.isError()
                        || glblMsg.A.no(index).poDispQty_A1.isError()) {
                    error = true;
                    break;
                }
            }
            if (error) {
                int length = bizMsg.A.length();
                int start = (index / length) * length + 1;
                ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum, new BigDecimal(start));
            }
        }

        NPAL1500CommonLogic.pagenation(bizMsg, glblMsg);
    }
    // END 2023/04/19 TZ.Win [QC#61299,ADD]
    
}
