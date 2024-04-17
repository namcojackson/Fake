/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1500;

import static business.blap.NPAL1500.constant.NPAL1500Constant.BIZ_ID;
import static business.blap.NPAL1500.constant.NPAL1500Constant.COMMA;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DB_DEAL_CCY_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DB_ETA_ETD_DT;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DISPLAY_DEST_RTL_WH_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DISPLAY_DEST_SWH_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DISPLAY_PRNT_VND_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DISPLAY_SER_NUM;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DISPLAY_VND_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_CANCEL;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_CMN_SAVE;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_CMN_SUBMIT;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_MOVE_TO_COMPONET;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_PO_CLOSE;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_PRINT;
import static business.blap.NPAL1500.constant.NPAL1500Constant.MAIL_ADDRESS_LENGTH;
import static business.blap.NPAL1500.constant.NPAL1500Constant.MAXIMUM_AMT;
import static business.blap.NPAL1500.constant.NPAL1500Constant.MESSAGE_KIND_W;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NMZC0030_ERROR;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAL1500_ADDR_CHK_FLG;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM0006E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM0032E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM0039E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM0046E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM0049E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM0076E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1183E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1199E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1217E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1268E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1311E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1319E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1329E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1351E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1361E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1367E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1369E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1370E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1429E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1431E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1506E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1514E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1578E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1579E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1588E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1598E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1610E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1612E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1620E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1636W;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1639E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1643E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1645E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1654E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1655W;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPZM0041E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPZM0081E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1657E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM1658E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.PERIOD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.PO_MSG_TP_INTERNAL_MESSAGE;
import static business.blap.NPAL1500.constant.NPAL1500Constant.PO_MSG_TP_RECEIVER_NOTE;
import static business.blap.NPAL1500.constant.NPAL1500Constant.PO_MSG_TP_SHIPPER_NOTE;
import static business.blap.NPAL1500.constant.NPAL1500Constant.PO_MSG_TP_SPECIAL_INSTRUCTION;
import static business.blap.NPAL1500.constant.NPAL1500Constant.PO_TYPE_SUB_CONTRACT;
import static business.blap.NPAL1500.constant.NPAL1500Constant.SER_VAL_API_MODE_BUYBACK;
import static business.blap.NPAL1500.constant.NPAL1500Constant.SER_VAL_API_MODE_INBOUND;
import static business.blap.NPAL1500.constant.NPAL1500Constant.TAB_ADDL_HEADER;
import static business.blap.NPAL1500.constant.NPAL1500Constant.TAB_DETAIL;
import static business.blap.NPAL1500.constant.NPAL1500Constant.TECH_INSRC_PO_QLFY_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.VAR_CHAR_KEY_DROP_SHIP_RTL_WH_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.VAR_CONST_CREATE_MATERIAL_PARTS;
import static business.blap.NPAL1500.constant.NPAL1500Constant.VAR_CONST_NOT_CHANGE_FOR_ROSS_PO_TP;
import static business.blap.NPAL1500.constant.NPAL1500Constant.ZERO;
import static business.blap.NPAL1500.constant.NPAL1500Constant.ZERO_STRING;
import static business.blap.NPAL1500.constant.NPAL1500Constant.ZZM9000E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.ZZM9037E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.ZZZM9003I;
import static parts.dbcommon.EZDTBLAccessor.findByCondition;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import parts.common.EZDCItem;
import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDMsgCommons;
import parts.common.EZDPMsg;
import parts.common.EZDPStringItem;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSItem;
import parts.common.EZDSMsg;
import parts.common.EZDSStringItem;
import parts.common.EZDTMsgArray;
import parts.dbcommon.EZDDBRecordLockedException;
import parts.dbcommon.EZDDBRetryRequestException;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NPAL1500.common.NPAL1500CommonLogic;
import business.blap.NPAL1500.constant.NPAL1500Constant;
import business.db.ALL_MDSE_VTMsg;
import business.db.DS_PO_TPTMsg;
import business.db.INBD_VISTMsg;
import business.db.MDSETMsg;
import business.db.OTBD_CARR_VTMsg;
import business.db.OTBD_CARR_VTMsgArray;
import business.db.POTMsg;
import business.db.PO_DTLTMsg;
import business.db.RCV_ASN_VNDTMsg;
import business.db.RTL_WHTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHPG_PLNTMsg;
import business.db.SHPG_SVC_LVL_CARR_RELNTMsg;
import business.db.SHPG_SVC_LVL_CARR_RELNTMsgArray;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SWHTMsg;
import business.parts.NLZC403001PMsg;
import business.parts.NMZC003001PMsg;
import business.parts.NPZC004001PMsg;
import business.parts.NPZC005001PMsg;
import business.parts.NPZC007001PMsg;
import business.parts.NPZC104001PMsg;
import business.parts.NPZC104001_poInfoPMsgArray;
import business.parts.NPZC109001PMsg;
import business.parts.NPZC113001PMsg;
import business.parts.NPZC129001PMsg;
import business.parts.NWZC003001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC403001.NLZC403001;
import com.canon.cusa.s21.api.NMZ.NMZC003001.NMZC003001;
import com.canon.cusa.s21.api.NPZ.NPZC004001.NPZC004001;
import com.canon.cusa.s21.api.NPZ.NPZC005001.NPZC005001;
import com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001;
import com.canon.cusa.s21.api.NPZ.NPZC104001.NPZC104001;
import com.canon.cusa.s21.api.NPZ.NPZC104001.constant.NPZC104001Constant;
import com.canon.cusa.s21.api.NPZ.NPZC109001.NPZC109001;
import com.canon.cusa.s21.api.NPZ.NPZC109001.constant.NPZC109001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC003001.NWZC003001;
import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWHData;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001GetDefNineSegData;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001GetDefNineSegDataBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_PO_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_COND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_OWNR;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MATCH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_ORD_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_QLFY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRSMT_METH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_UOM;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 * <pre>
 * Business ID : NPAL1500 PO Entry
 * Function Name : update business process
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/18/2015   CITS            N Akaishi       Create          n/a
 * 2016/03/29   CITS            T.Tokutomi      Update          QC#5774
 * 2016/09/23   CITS            S.Endo          Update          N/A(Refactoring)
 * 2016/09/27   CITS            S.Endo          Update          QC#11985/QC#14144
 * 2016/09/28   CITS            S.Endo          Update          QC#14137/QC#11985
 * 2016/10/03   CITS            S.Endo          Update          N/A(Refactoring)
 * 2016/10/05   CITS            S.Endo          Update          QC#12768/QC#14144
 * 2016/10/11   CITS            S.Endo          Update          QC#14285
 * 2016/10/12   CITS            S.Endo          Update          QC#14285
 * 2016/10/13   CITS            S.Endo          Update          QC#14882,N/A(Refactoring)
 * 2016/10/20   CITS            S.Endo          Update          QC#15222/Refactoring
 * 2016/10/25   CITS            S.Endo          Update          QC#14882
 * 2016/10/31   CITS            S.Endo          Update          QC#14882
 * 2016/11/10   CITS            R.Shimamoto     Update          QC#15908
 * 2016/11/28   CITS            Y.Fujii         Update          R350
 * 2016/12/13   CITS            R.Shimamoto     Update          QC#15817
 * 2016/12/19   CITS            R.Shimamoto     Update          QC#16407
 * 2016/12/20   CITS            R.Shimamoto     Update          QC#16341
 * 2016/12/27   CITS            S.Endo          Update          QC#14882
 * 2017/02/21   CITS            Y.IWASAKI       Update          QC#17487
 * 2017/02/27   CITS            R.Shimamoto     Update          QC#17785
 * 2017/02/28   CITS            Y.Fujii         Update          QC#16915
 * 2017/05/22   CITS            R.Shimamoto     Update          QC#18176
 * 2017/06/07   CITS            S.Endo          Update          QC#17952
 * 2017/06/08   CITS            S.Endo          Update          QC#18494
 * 2017/06/20   CITS            S.Endo          Update          QC#19207
 * 2017/06/23   CITS            S.Endo          Update          QC#19485
 * 2017/06/30   CITS            R.Shimamoto     Update          QC#19693
 * 2017/08/04   CITS            R.Shimamoto     Update          QC#18761
 * 2017/09/04   CITS            S.Katsuma       Update          QC#20688
 * 2017/09/27   CITS            S.Endo          Update          QC#21206
 * 2017/10/10   CITS            S.Katsuma       Update          QC#21394
 * 2017/10/13   CITS            S.Katsuma       Update          QC#21845
 * 2017/11/07   Hitachi         Y.Takeno        Update          QC#21849(Sol#218)
 * 2017/12/04   CITS            K.Kameoka       Update          QC#14858(Sol#060)
 * 01/09/2018   CITS            K.Kameoka       Update          QC#18602(Sol#102)
 * 01/30/2018   CITS            K.Ogino         Update          QC#23616
 * 04/16/2018   CITS            K.Fukumura      Update          QC#21170
 * 04/20/2018   CITS            Y.Iwasaki       Update          QC#25019
 * 06/19/2018   CITS            T.Hakodate      Update          QC#24932
 * 06/20/2018   CITS            K.Kameoka       Update          QC#18420
 * 06/22/2018   CITS            K.Ogino         Update          QC#19811
 * 06/26/2018   CITS            K.Kameoka       Update          QC#25314
 * 07/03/2018   CITS            T.Tokutomi      Update          QC#23726
 * 07/09/2018   CITS            K.Ogino         Update          QC#24918
 * 07/12/2018   CITS            S.Katsuma       Update          QC#26867
 * 07/10/2018   CITS            K.Kameoka       Update          QC#25024
 * 07/26/2018   CITS            K.Ogino         Update          QC#27127
 * 07/30/2018   CITS            K.Kameoka       Update          QC#27024
 * 09/25/2018   CITS            K.Kameoka       Update          QC#28226
 * 10/01/2018   CITS            K.Kameoka       Update          QC#28460/28146
 * 10/10/2018   CITS            T.Wada          Update          QC#28685
 * 10/12/2018   CITS            T.Tokutomi      Update          QC#28729
 * 11/09/2018   CITS            K.Kameoka       Update          QC#29064
 * 11/16/2018   CITS            K.Ogino         Update          QC#29155
 * 12/13/2018   CITS            M.Naito         Update          QC#29027
 * 2018/12/19   Fujitsu         S.Takami        Update          QC#29456
 * 2019/02/04   CITS            K.Ogino         Update          QC#30039
 * 2019/02/20   CITS            K.Ogino         Update          QC#29600
 * 2019/02/21   CITS            T.Hakodate      Update          QC#30436
 * 2019/02/26   Fujitsu         T.Ogura         Update          QC#30518
 * 2019/03/05   CITS            K.Ogino         Update          QC#30566
 * 2019/04/17   CITS            K.Ogino         Update          QC#31087
 * 2019/07/04   CITS            K.Ogino         Update          QC#51076
 * 2019/07/17   CITS            M.Naito         Update          QC#51695
 * 2019/08/02   Fujitsu         T.Ogura         Update          QC#51448
 * 2019/08/09   Fujitsu         T.Ogura         Update          QC#52359
 * 2019/08/20   CITS            M.Naito         Update          QC#52857
 * 2019/08/26   CITS            K.Ogino         Update          QC#52971
 * 2019/09/17   CITS            R.Shimamoto     Update          QC#53271
 * 2019/09/24   CITS            R.Shimamoto     Update          QC#53422
 * 2019/09/27   CITS            R.Shimamoto     Update          QC#52460
 * 2019/10/09   CITS            R.Shimamoto     Update          QC#53392
 * 2019/10/28   Fujitsu         T.Ogura         Update          QC#51802
 * 2019/10/30   CITS            M.Naito         Update          QC#53811
 * 2019/11/15   Fujitsu         T.Ogura         Update          QC#54678
 * 2019/12/02   Fujitsu         T.Ogura         Update          QC#54813
 * 2020/02/18   Fujitsu         T.Ogura         Update          QC#55916
 * 2020/03/02   Fujitsu         T.Ogura         Update          QC#55920
 * 2021/04/23   CITS            M.Furugoori     Update          QC#58645
 * 2022/05/16   Hitachi         A.Kohinata      Update          QC#57934
 * 2022/09/08   Hitachi         T.NEMA          Update          QC#60413
 * 2022/12/09   Hitachi         M.Kikushima     Update          QC#60604
 * 2023/02/14   Hitachi         S.Dong          Update          QC#60966
 * 2023/04/03   Hitachi         TZ.Win          Update          QC#60966
 * 2024/1/4     CITS            K.Iwamoto       Update          QC#62443
 * </pre>
 */
public class NPAL1500BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NPAL1500CMsg bizMsg = (NPAL1500CMsg) cMsg;
            NPAL1500SMsg glblMsg = (NPAL1500SMsg) sMsg;

            bizMsg.setCommitSMsg(true);

            if (EVENT_NM_NPAL1500_CANCEL.equals(screenAplID)) {
                doProcess_NPAL1500Scrn00_Cancel(bizMsg, glblMsg);

            } else if (EVENT_NM_NPAL1500_PO_CLOSE.equals(screenAplID)) {
                doProcess_NPAL1500Scrn00_PoClose(bizMsg, glblMsg);

            } else if (EVENT_NM_NPAL1500_CMN_SAVE.equals(screenAplID)) {
                doProcess_NPAL1500Scrn00_CMN_Save(bizMsg, glblMsg);

            } else if (EVENT_NM_NPAL1500_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_NPAL1500Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if (EVENT_NM_NPAL1500_MOVE_TO_COMPONET.equals(screenAplID)) {
                doProcess_NPAL1500Scrn00_MoveTo_Componet(bizMsg, glblMsg);

            } else if (EVENT_NM_NPAL1500_PRINT.equals(screenAplID)) {
                doProcess_NPAL1500Scrn00_Print(bizMsg, glblMsg);

            // add start 2022/05/16 QC#57934
            } else if ("NPAL1500Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(bizMsg, glblMsg);

            } else if ("NPAL1500Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(bizMsg, glblMsg);
            // add end 2022/05/16 QC#57934

            } else {
                return;
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * doProcess_NPAL1500Scrn00_Cancel
     * @param bizMsg NPAL1500CMsg
     * @param bizMsg NPAL1500SMsg
     */
    private void doProcess_NPAL1500Scrn00_Cancel(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        // set values
        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode());

        if (!ZYPCommonFunc.hasValue(bizMsg.poNum_HD)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.poNum_HD, bizMsg.poNum);
        }

        // Copy Message
        NPAL1500CommonLogic.copyFromCmsgOntoSmsg(bizMsg, glblMsg);

        // CheckBox should be selected at least one.
        boolean checkFlg = false;
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).xxChkBox_A1)) {
                checkFlg = true;
            }
        }
        if (!checkFlg) {
            bizMsg.setMessageInfo(NPAM0049E);
            return;
        }

        if (!validatePoForCancel(bizMsg, glblMsg)) {
            return;
        }

        // processing change status to cancel
        cancel(bizMsg, glblMsg);

        // QC#21170
        // Set rqstRcvDt (Date & Time Needed)
        NPAL1500CommonLogic.setHeaderRqstRcvDt_SmsgMaxVal(bizMsg, glblMsg);
        // START 2023/02/14 S.Dong [QC#60966, ADD]
        // Set rqstShipDt (Vendor Ship Date)
        NPAL1500CommonLogic.setHeaderRqstShipDt(bizMsg, glblMsg);
        // END 2023/02/14 S.Dong [QC#60966, ADD]

        bizMsg.setMessageInfo(ZZZM9003I, new String[] {"Cancel" });
    }

    /**
     * validatePO for cancel
     * @param bizMsg NPAL1500CMsg
     * @param glblMsg NPAL1500SMsg
     * @return boolean
     */
    private boolean validatePoForCancel(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {
        // Validation Check
        // Set Item Line Check QC#15908
        boolean setItemFlg = false;
        boolean errFlg = false;
        int maxDisplayRows = bizMsg.A.length();
        int errIndex = 0;
        String parentPoOrdDtlLineNum = "";
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

            NPAL1500_ASMsg asMsg = glblMsg.A.no(i);

            if (PO_MDSE_CMPSN_TP.PARENT.equals(asMsg.poMdseCmpsnTpCd_HD.getValue())) {
                if (ZYPConstant.CHKBOX_ON_Y.equals(asMsg.xxChkBox_A1.getValue())) {
                    setItemFlg = true;
                } else {
                    setItemFlg = false;
                }
                parentPoOrdDtlLineNum = asMsg.poOrdDtlLineNum_A1.getValue();

            } else if (ZYPCommonFunc.hasValue(asMsg.setPoOrdDtlLineNum) && parentPoOrdDtlLineNum.equals(asMsg.setPoOrdDtlLineNum.getValue())) {
                if (ZYPConstant.CHKBOX_ON_Y.equals(asMsg.xxChkBox_A1.getValue())) {
                    if (setItemFlg) {
                        continue;
                    } else {
                        errIndex = i;
                        asMsg.xxChkBox_A1.setErrorInfo(1, NPAM1588E);
                        bizMsg.setMessageInfo(NPAM1319E);
                        errFlg = true;
                    }
                }
            } else {
                setItemFlg = false;
            }

            // if not PO Status is (Null and Saved)
            if (ZYPConstant.FLG_ON_Y.equals(glblMsg.A.no(i).xxChkBox_A1.getValue()) //
                    && (!(ZYPCommonFunc.hasValue(glblMsg.A.no(i).poLineStsCd_A1.getValue())) //
                    || !(glblMsg.A.no(i).poLineStsCd_A1.getValue().equals(PO_STS.SAVED)))) {

                NPAL1500Query query = NPAL1500Query.getInstance();

                // check RWS
                BigDecimal rwsCnt = query.countValidRws(glblMsg.glblCmpyCd.getValue() //
                        , glblMsg.poNum.getValue() //
                        , glblMsg.A.no(i).poOrdDtlLineNum_A1.getValue());
                if (ZYPCommonFunc.hasValue(rwsCnt) && rwsCnt.intValue() > 0) {
                    bizMsg.setMessageInfo(NPAM1598E,  new String[] {"Cancel"});
                    return false;
                }

                // check Received RWS
                BigDecimal poRcvQty = glblMsg.A.no(i).poRcvQty_A1.getValue();
                if (!ZYPCommonFunc.hasValue(poRcvQty)) {
                    poRcvQty = BigDecimal.ZERO;
                }
                if (BigDecimal.ZERO.compareTo(poRcvQty) < 0) {
                    bizMsg.setMessageInfo(NPAM1319E);
                    return false;
                }

                if (!checkSetComponentRWSForCancel(bizMsg, glblMsg, i, query)) {
                    return false;
                }
                //QC#17952 Check PO_STS in PO_DTL
                if (glblMsg.A.no(i).poStsCd_HD.getValue().equals(PO_STS.RECEIVING)){
                    bizMsg.setMessageInfo(NPAM1319E);
                    return false;
                }
                //QC#17952 END
                // START 2022/09/12 T.NEMA [QC#60413, ADD]
                if (setItemFlg) { // SET(Parent)
                    BigDecimal APInvSetItemCnt = query.countValidAPInvoiceSetItemForCancel(glblMsg.glblCmpyCd.getValue() //
                            , glblMsg.poNum.getValue() //
                            , glblMsg.A.no(i).poOrdDtlLineNum_A1.getValue());
                    if (glblMsg.A.no(i).poLineStsCd_A1.getValue().equals(PO_LINE_STS.OPEN_FOR_INVOICE) && BigDecimal.ZERO.compareTo(APInvSetItemCnt) < 0) {
                        bizMsg.setMessageInfo(NPAM1654E);
                        return false;
                    }
                } else if (ZYPCommonFunc.hasValue(asMsg.setPoOrdDtlLineNum) && parentPoOrdDtlLineNum.equals(asMsg.setPoOrdDtlLineNum.getValue())) {
                    continue;
                } else {
                    BigDecimal APInvCnt = query.countValidAPInvoiceForCancel(glblMsg.glblCmpyCd.getValue() //
                            , glblMsg.poNum.getValue() //
                            , glblMsg.A.no(i).poOrdDtlLineNum_A1.getValue());
                    if (glblMsg.A.no(i).poLineStsCd_A1.getValue().equals(PO_LINE_STS.OPEN) && BigDecimal.ZERO.compareTo(APInvCnt) < 0) {
                        bizMsg.setMessageInfo(NPAM1654E);
                        return false;
                    }
                }
                // END   2022/09/12 T.NEMA [QC#60413, ADD]
            } else {
                continue;
            }
        }

        if (errFlg) {
            int errScrnInex = (errIndex / maxDisplayRows) * maxDisplayRows + 1;
            bizMsg.xxPageShowFromNum.setValue(errScrnInex);
            NPAL1500CommonLogic.copyFromSMsgOntoCmsg(bizMsg, glblMsg);
            return false;
        }

        return true;
    }

    /**
     * check SetComponent RWS for Cancel
     * @param bizMsg NPAL1500CMsg
     * @param glblMsg NPAL1500SMsg
     * @param i int
     * @param query NPAL1500Query
     * @return boolean
     */
    private boolean checkSetComponentRWSForCancel(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg, int i, NPAL1500Query query) {
        // check RWS (Set Component)
        // PO_DTL.PO_MDSE_CMPSN_TP_CD=1:Parent
        if (glblMsg.A.no(i).poMdseCmpsnTpCd_HD.getValue().equals(PO_MDSE_CMPSN_TP.PARENT)) {
            BigDecimal rwsCntSet = query.countValidRwsSet(glblMsg.glblCmpyCd.getValue() //
                    , glblMsg.poNum.getValue() //
                    , glblMsg.A.no(i).poOrdDtlLineNum_A1.getValue());
            if (ZYPCommonFunc.hasValue(rwsCntSet) && rwsCntSet.intValue() > 0) {
                bizMsg.setMessageInfo(NPAM1598E,  new String[] {"Cancel" });
                return false;
            }
            //QC#17952 Check child's information
            String parentPoOrdDtlLineNum = glblMsg.A.no(i).poOrdDtlLineNum_A1.getValue();
            //Check child's received Qty.
            for (int j = 0; j < glblMsg.A.getValidCount(); j++) {
                if (ZYPCommonFunc.hasValue(glblMsg.A.no(j).setPoOrdDtlLineNum) && parentPoOrdDtlLineNum.equals(glblMsg.A.no(j).setPoOrdDtlLineNum.getValue())) {
                    //check child's PO_STS
                    if (glblMsg.A.no(j).poStsCd_HD.getValue().equals(PO_STS.RECEIVING)){
                        bizMsg.setMessageInfo(NPAM1319E);
                        return false;
                    }
                }
            }
            //QC#17952 END
        }
        return true;
    }

    /**
     * doProcess_NPAL1500Scrn00_PoClose
     * @param bizMsg NPAL1500CMsg
     * @param bizMsg NPAL1500SMsg
     */
    private void doProcess_NPAL1500Scrn00_PoClose(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        // set values
        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode());

        if (!ZYPCommonFunc.hasValue(bizMsg.poNum_HD)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.poNum_HD, bizMsg.poNum);
        }

        // Copy Message
        NPAL1500CommonLogic.copyFromCmsgOntoSmsg(bizMsg, glblMsg);

        // CheckBox should be selected at least one.
        boolean checkFlg = false;
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).xxChkBox_A1)) {
                checkFlg = true;
            }
        }
        if (!checkFlg) {
            bizMsg.setMessageInfo(NPAM0049E);
            return;
        }

        if (!validatePoForClose(bizMsg, glblMsg)) {
            return;
        }

        // QC#21170
        // Set rqstRcvDt (Date & Time Needed)
        NPAL1500CommonLogic.setHeaderRqstRcvDt_SmsgMaxVal(bizMsg, glblMsg);
        // START 2023/02/14 S.Dong [QC#60966, ADD]
        // Set rqstShipDt (Vendor Ship Date)
        NPAL1500CommonLogic.setHeaderRqstShipDt(bizMsg, glblMsg);
        // END 2023/02/14 S.Dong [QC#60966, ADD]
        // processing change status to close
        close(bizMsg, glblMsg);

        // QC#28685 Add Start
        removeInbdVis(bizMsg, glblMsg);
        // QC#28685 Add End

        bizMsg.setMessageInfo(ZZZM9003I, new String[] {"Close" });
    }

    /**
     * validate PO for Close
     * @param bizMsg NPAL1500CMsg
     * @param glblMsg NPAL1500SMsg
     * @return boolean
     */
    private boolean validatePoForClose(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {
        // Validation Check
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).xxChkBox_A1) || !ZYPConstant.FLG_ON_Y.equals(glblMsg.A.no(i).xxChkBox_A1.getValue())) {
                continue;
            }
            // if PO Status is cancelled or closed
            if (bizMsg.A.no(i).poLineStsCd_A1.getValue().equals(PO_STS.CANCELLED) || bizMsg.A.no(i).poLineStsCd_A1.getValue().equals(PO_STS.CLOSED)) {

                bizMsg.setMessageInfo(NPAM1311E);
                return false;

                // if not PO Status is (Null and Saved)
            } else if (!(ZYPCommonFunc.hasValue(bizMsg.A.no(i).poLineStsCd_A1.getValue())) || !(bizMsg.A.no(i).poLineStsCd_A1.getValue().equals(PO_STS.SAVED))) {

                NPAL1500Query query = NPAL1500Query.getInstance();

                // check RWS
                BigDecimal rwsCnt = query.countValidRws(bizMsg.glblCmpyCd.getValue(), bizMsg.poNum.getValue(), bizMsg.A.no(i).poOrdDtlLineNum_A1.getValue());
                if (ZYPCommonFunc.hasValue(rwsCnt) && rwsCnt.intValue() > 0) {
                    bizMsg.setMessageInfo(NPAM1598E, new String[] {"Close" });
                    return false;
                }

                // check Received RWS
                BigDecimal poRcvQty = glblMsg.A.no(i).poRcvQty_A1.getValue();
                if (!ZYPCommonFunc.hasValue(poRcvQty)) {
                    poRcvQty = BigDecimal.ZERO;
                }
                if (BigDecimal.ZERO.compareTo(poRcvQty) == 0) {
                    bizMsg.setMessageInfo(NPAM1311E);
                    return false;
                }

                if (!checkSetComponentRWSForClose(bizMsg, i, query)) {
                    return false;
                }
            } else {
                continue;
            }
        }
        return true;
    }

    /**
     * check SetComponent RWS for Close
     * @param bizMsg NPAL1500CMsg
     * @param i int
     * @param query NPAL1500Query
     * @return boolean
     */
    private boolean checkSetComponentRWSForClose(NPAL1500CMsg bizMsg, int i, NPAL1500Query query) {
        // check RWS (Set Component)
        // PO_DTL.PO_MDSE_CMPSN_TP_CD=1:Parent
        String parentPoOrdDtlLineNum = null;
        BigDecimal prevReceivedRatio = null;
        int scale = 2;
        if (PO_MDSE_CMPSN_TP.PARENT.equals(bizMsg.A.no(i).poMdseCmpsnTpCd_HD.getValue())) {
            BigDecimal rwsCntSet = query.countValidRwsSet(bizMsg.glblCmpyCd.getValue(), bizMsg.poNum.getValue(), bizMsg.A.no(i).poOrdDtlLineNum_A1.getValue());
            if (ZYPCommonFunc.hasValue(rwsCntSet) && rwsCntSet.intValue() > 0) {
                bizMsg.setMessageInfo(NPAM1598E, new String[] {"Close" });
                return false;
            }
            parentPoOrdDtlLineNum = bizMsg.A.no(i).poOrdDtlLineNum_A1.getValue();
        } else if (PO_MDSE_CMPSN_TP.CHILD.equals(bizMsg.A.no(i).poMdseCmpsnTpCd_HD.getValue())) {
            parentPoOrdDtlLineNum = bizMsg.A.no(i).setPoOrdDtlLineNum.getValue();
        }
        // QC#17952 Check child's information.
        //Check child's received Qty. 
        if (ZYPCommonFunc.hasValue(parentPoOrdDtlLineNum)) {
            PO_DTLTMsg poDtl = new PO_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(poDtl.poOrdNum, bizMsg.poNum_HD.getValue());
            ZYPEZDItemValueSetter.setValue(poDtl.poOrdDtlLineNum, parentPoOrdDtlLineNum);
            ZYPEZDItemValueSetter.setValue(poDtl.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            
            poDtl = (PO_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(poDtl);
            if (poDtl == null) {
                bizMsg.setMessageInfo(NPAM1311E);
                return false;
            }
            List<Map<String, Object>> resultList;
            S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getSetChildMasterQty(bizMsg.glblCmpyCd.getValue(),poDtl.mdseCd.getValue());
            if (!ssmResult.isCodeNormal()) {
                bizMsg.setMessageInfo(NPAM1311E);
                return false;
            } else {
                resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                if (0 == resultList.size()) {
                    bizMsg.setMessageInfo(NPAM1311E);
                    return false;
                }
            }

            BigDecimal masterQty = null;
            for (int j = 0; j < bizMsg.A.getValidCount(); j++) {
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(j).setPoOrdDtlLineNum) && parentPoOrdDtlLineNum.equals(bizMsg.A.no(j).setPoOrdDtlLineNum.getValue())) {
			        // check Set Master information
                    for (Map<String, Object> master : resultList) {
                        if (bizMsg.A.no(j).mdseCd_A1.getValue().equals((String) master.get("MDSE_CD"))){
                            masterQty =(BigDecimal) master.get("CHILD_MDSE_QTY");
                            break;
                        }
                    }
                    if (masterQty == null){
                        bizMsg.setMessageInfo(NPAM1311E);
                        return false;
                    }
                    // check received Qty is (n *  master Qty) , n as Integer(0,1,2,...)
                    try{
                        int masterCheck = bizMsg.A.no(j).poRcvQty_A1.getValue().divide(masterQty,scale,RoundingMode.HALF_UP).intValueExact();
                    } catch (ArithmeticException e) {
                        bizMsg.setMessageInfo(NPAM1311E);
                        return false;
                    }
                    
                    // check child's received Qty
                    if (prevReceivedRatio == null) {
                        prevReceivedRatio = bizMsg.A.no(j).poRcvQty_A1.getValue().divide(bizMsg.A.no(j).poDispQty_A1.getValue(), scale, RoundingMode.HALF_UP);
                    }
                    if (prevReceivedRatio.compareTo(bizMsg.A.no(j).poRcvQty_A1.getValue().divide(bizMsg.A.no(j).poDispQty_A1.getValue(), scale, RoundingMode.HALF_UP)) != 0) {
                        bizMsg.setMessageInfo(NPAM1311E);
                        return false;
                    }
                    masterQty = null;
                }
            // QC#17952 END
            }
        }
        return true;
    }

    /**
     * doProcess_NPAL1500Scrn00_CMN_Save
     * @param bizMsg NPAL1500CMsg
     * @param bizMsg NPAL1500SMsg
     */
    private void doProcess_NPAL1500Scrn00_CMN_Save(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        boolean isErr = false;

        // QC#29155 Add.
        boolean isModifyShpgSvcLvlReln = NPAL1500CommonLogic.isModifyShpgSvcLvlReln(bizMsg, glblMsg);


        // set values
        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode());

        if (!ZYPCommonFunc.hasValue(bizMsg.poNum_HD)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.poNum_HD, bizMsg.poNum);
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.ccyCd)) {
            S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getVendorInfo(bizMsg);

            if (ssmResult.isCodeNormal()) {
                List<Map> resultList = (List<Map>) ssmResult.getResultObject();
                if (0 < resultList.size()) {
                    Map recode = resultList.get(0);
                    ZYPEZDItemValueSetter.setValue(bizMsg.ccyCd, (String) recode.get(DB_DEAL_CCY_CD));
                }
            }
        }

        // QC#17785 Add. Supplier Code and name auto derivation.
        if ((ZYPCommonFunc.hasValue(bizMsg.vndCd) && !ZYPCommonFunc.hasValue(bizMsg.prntVndCd)) || (!ZYPCommonFunc.hasValue(bizMsg.vndPmtTermDescTxt))) {
            NPAL1500CommonLogic.getSupplierName(bizMsg, glblMsg);
        }
        // START 2017/11/07 [QC#21849, DEL]
        // Ship to location auto derivation.
        // if (ZYPCommonFunc.hasValue(bizMsg.destRtlWhCd_DS) && (!ZYPCommonFunc.hasValue(bizMsg.xxAllLineAddr_ST)
        //         || !ZYPCommonFunc.hasValue(bizMsg.shipToAddlLocNm_ST)
        //         || !ZYPCommonFunc.hasValue(bizMsg.shipToCtyAddr_ST)
        //         || !ZYPCommonFunc.hasValue(bizMsg.shipToCtyAddr_ST))) {
        // 
        //     NPAL1500CommonLogic.setRtlWhInfo(bizMsg, glblMsg);
        // }
        // END   2017/11/07 [QC#21849, DEL]

        String bkDsPoTpCd = bizMsg.dsPoTpCd.getValue();    // 2019/10/28 T.Ogura [QC#51802,ADD]

        // START 2019/07/17 M.Naito [QC#51695,ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.dsPoTpCd, chkInvtyOwnrCd(bizMsg.glblCmpyCd.getValue(), bizMsg.dsPoTpCd.getValue(), bizMsg.destRtlWhCd_DS.getValue()));
        // END 2019/07/17 M.Naito [QC#51695,ADD]

        // Copy Message
        NPAL1500CommonLogic.copyFromCmsgOntoSmsg(bizMsg, glblMsg);
        // START 2024/1/1 K.Iwamoto [QC#62443, ADD]]
        if (NPAL1500CommonLogic.checkAllLineType(bizMsg, glblMsg)) {
            NPAL1500CommonLogic.copyFromSMsgOntoCmsg(bizMsg, glblMsg);
            isErr = true;
        }
        // END 2024/1/1 K.Iwamoto [QC#62443, ADD]]
        // START 2017/11/07 [QC#21849, ADD]
        if (NPAL1500CommonLogic.checkDetailLineType(bizMsg, glblMsg)) {
            ZYPEZDItemValueSetter.setValue(glblMsg.xxRsltFlg, NPAL1500Constant.RSLT_FLG_ERROR);
            if (!ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
                bizMsg.setMessageInfo(ZZM9037E);
            }
            isErr = true;
        }

        // QC#24932 MOD START
        if (PO_APVL_STS.ENTERED.equals(glblMsg.poApvlStsCd.getValue()) && (ZYPCommonFunc.hasValue(glblMsg.poQlfyCd) && PO_QLFY.DROPSHIP.equals(glblMsg.poQlfyCd.getValue()))) {

            // do nothing

        } else {

            if (!NPAL1500CommonLogic.checkConsistencyDestWH(bizMsg, glblMsg)) {
                NPAL1500CommonLogic.setRtlWhInfo(bizMsg, glblMsg);
            } else {
                ZYPEZDItemValueSetter.setValue(glblMsg.xxRsltFlg, NPAL1500Constant.RSLT_FLG_ERROR);
                if (!ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
                    bizMsg.setMessageInfo(ZZM9037E);
                }
                isErr = true;
            }
        }
        // QC#24932 MOD END
        // END   2017/11/07 [QC#21849, ADD]

        //QC#28460 Add Start
        if (DS_PO_TP.SUBCONTRACT_PO.equals(bizMsg.dsPoTpCd.getValue()) && ZYPCommonFunc.hasValue(bizMsg.destRtlWhCd_DS)) {
            S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().checkWhOwnrCdForSc(bizMsg);

            if (ssmResult.isCodeNormal()) {
                List<Map> resultList = (List<Map>) ssmResult.getResultObject();
                if (0 < resultList.size()) {
                    //error
                    bizMsg.dsPoTpCd.setErrorInfo(1, NPAM1217E);
                    bizMsg.destRtlWhCd_DS.setErrorInfo(1, NPAM1217E);
                    bizMsg.setMessageInfo(ZZM9037E);
                    isErr = true;
                }
            }
        }
        //QC#28460 Add End
        
        // START 2017/12/04 [QC#14858, ADD]
        // Check:Item# or Item Description is mandatory.
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (NPAL1500CommonLogic.checkItemInfo(bizMsg, glblMsg, i)) {
                ZYPEZDItemValueSetter.setValue(glblMsg.xxRsltFlg, NPAL1500Constant.RSLT_FLG_ERROR);
                if (!ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
                    bizMsg.setMessageInfo(ZZM9037E);
                }
                isErr = true;
            }
        }
        // END 2017/12/04 [QC#14858, ADD]

        // START 2017/12/04 [QC#14858, ADD]
        // Set Items for TEXT ITEM.
        if (NPAL1500CommonLogic.setTextItem(bizMsg, glblMsg)) {
            ZYPEZDItemValueSetter.setValue(glblMsg.xxRsltFlg, NPAL1500Constant.RSLT_FLG_ERROR);
            if (!ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
                bizMsg.setMessageInfo(ZZM9037E);
            }
            isErr = true;
        }
        
        // END 2017/12/04 [QC#14858, ADD]
        
        // Check Item Master For Set
        // QC#27127
        S21LRUMap<Object, S21SsmEZDResult> deriveItemInfoCache = new S21LRUMap<Object, S21SsmEZDResult>();
        if (!checkItemMasterForSet(bizMsg, glblMsg, deriveItemInfoCache)) {
            ZYPEZDItemValueSetter.setValue(glblMsg.xxRsltFlg, NPAL1500Constant.RSLT_FLG_ERROR);
            if (!ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
                bizMsg.setMessageInfo(ZZM9037E);
            }
            isErr = true;
        }

        // Set Derive Item
        if (setDeriveItemForSaveAndSubmit(bizMsg, glblMsg)) {
            ZYPEZDItemValueSetter.setValue(glblMsg.xxRsltFlg, NPAL1500Constant.RSLT_FLG_ERROR);
            if (!ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
                bizMsg.setMessageInfo(ZZM9037E);
            }
            isErr = true;
        }
        
        // Calculate PO Qty
        BigDecimal poQty = BigDecimal.ZERO;
        String salesDt = ZYPDateUtil.getSalesDate(glblMsg.glblCmpyCd.getValue());

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            poQty = getVndOrdQty(glblMsg, salesDt, glblMsg.A.no(i));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).poQty_HD, poQty);

            // QC#14858. set entDealNetUnitPrcAmt_A1 & UOM, QC#23616 Mod
            if ((PO_LINE_TP.EXPENSE.equals(glblMsg.A.no(i).poLineTpCd_A1.getValue()) //
                    || PO_LINE_TP.EXPENSE_WITH_RECEIPT.equals(glblMsg.A.no(i).poLineTpCd_A1.getValue()) //
                    || PO_LINE_TP.ASSET.equals(glblMsg.A.no(i).poLineTpCd_A1.getValue())) //
                    && ZYPConstant.FLG_ON_Y.equals(glblMsg.A.no(i).xxCntDplyFlg_A2.getValue())) {

                BigDecimal amt = glblMsg.A.no(i).entDealNetUnitPrcAmt_A1.getValue().multiply(poQty);
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).entPoDtlDealNetAmt_A1, amt);
            }
        }
        // check Msg
        // QC#27127. Mod QC#29155
        if (!checkCommonLogic(bizMsg.glblCmpyCd.getValue(), bizMsg, glblMsg, deriveItemInfoCache, isModifyShpgSvcLvlReln)) {
            ZYPEZDItemValueSetter.setValue(glblMsg.xxRsltFlg, NPAL1500Constant.RSLT_FLG_ERROR);
            if (!ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
                bizMsg.setMessageInfo(ZZM9037E);
            }
            isErr = true;
        }
        // set ccy code
        S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getVendorInfo(bizMsg);
        if (ssmResult.isCodeNormal()) {
            List<Map> resultList = (List<Map>) ssmResult.getResultObject();
            if (0 < resultList.size()) {
                Map recode = resultList.get(0);
                ZYPEZDItemValueSetter.setValue(bizMsg.ccyCd, (String) recode.get(DB_DEAL_CCY_CD));
                ZYPEZDItemValueSetter.setValue(glblMsg.ccyCd, (String) recode.get(DB_DEAL_CCY_CD));
            }
        }

        // 2019/09/24 QC#53422 Add Start
        if (!isErr) {
        	// Get Account

            //QC#29064 Add Start
            if (!bizMsg.destRtlWhCd_DS.isError()) {
            //QC#29064 Add End
                for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                    NPAL1500CommonLogic.getAccountInfo(bizMsg, glblMsg, i, false);
                }    
            }
            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                if (!NPAL1500CommonLogic.getDefaultAccountInfo(bizMsg, glblMsg, getGlobalCompanyCode(), i)) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.xxRsltFlg, NPAL1500Constant.RSLT_FLG_ERROR);
                    if (!ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
                        bizMsg.setMessageInfo(ZZM9037E);
                    }
                    isErr = true;
                }
            }
        }
        // 2019/09/24 QC#53422 Add End

        // QC#21170
        // Set rqstRcvDt (Date & Time Needed)
        NPAL1500CommonLogic.setHeaderRqstRcvDt_SmsgMaxVal(bizMsg, glblMsg);
        // START 2023/02/14 S.Dong [QC#60966, ADD]
        // Set rqstShipDt (Vendor Ship Date)
        NPAL1500CommonLogic.setHeaderRqstShipDt(bizMsg, glblMsg);
        // END 2023/02/14 S.Dong [QC#60966, ADD]
        // QC#23726 Add. Check Customer Service Relation. Mod QC#29155.
        if (NPAL1500CommonLogic.checkCustCarrSvcLvlRelation(bizMsg, glblMsg, isModifyShpgSvcLvlReln)) {
            bizMsg.carrCd.setErrorInfo(2, NPAM1636W);
            bizMsg.shpgSvcLvlCd.setErrorInfo(2, NPAM1636W);
            isErr = true;
        }

        // processing registration
        if(!isErr) {
            // START 2019/10/28 T.Ogura [QC#51802,MOD]
//            regist(bizMsg, glblMsg, false, getUserProfileService());
            regist(bizMsg, glblMsg, false, getUserProfileService(), bkDsPoTpCd);
            // END   2019/10/28 T.Ogura [QC#51802,MOD]
        
            bizMsg.setMessageInfo(ZZZM9003I, new String[] {"Save" });
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.dsPoTpCd, bkDsPoTpCd);    // 2019/10/28 T.Ogura [QC#51802,ADD]
            moveErrPage(bizMsg, glblMsg);
        }
    }

    /**
     * doProcess_NPAL1500Scrn00_CMN_Submit
     * @param bizMsg NPAL1500CMsg
     * @param bizMsg NPAL1500SMsg
     */
    private void doProcess_NPAL1500Scrn00_CMN_Submit(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        boolean isErr = false;

        // QC#29155 Add.
        boolean isModifyShpgSvcLvlReln = NPAL1500CommonLogic.isModifyShpgSvcLvlReln(bizMsg, glblMsg);

        // set values
        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode());

        if (!ZYPCommonFunc.hasValue(bizMsg.poNum_HD)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.poNum_HD, bizMsg.poNum);
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.ccyCd)) {
            S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getVendorInfo(bizMsg);

            if (ssmResult.isCodeNormal()) {
                List<Map> resultList = (List<Map>) ssmResult.getResultObject();
                if (0 < resultList.size()) {
                    Map recode = resultList.get(0);
                    ZYPEZDItemValueSetter.setValue(bizMsg.ccyCd, (String) recode.get(DB_DEAL_CCY_CD));
                }
            }
        }

        // QC#17785 Add. Supplier Code and name auto derivation.
        if ((ZYPCommonFunc.hasValue(bizMsg.vndCd) && !ZYPCommonFunc.hasValue(bizMsg.prntVndCd)) || (!ZYPCommonFunc.hasValue(bizMsg.vndPmtTermDescTxt))) {
            NPAL1500CommonLogic.getSupplierName(bizMsg, glblMsg);
        }
        // START 2017/11/07 [QC#21849, DEL]
        // Ship to location auto derivation.
        // if (ZYPCommonFunc.hasValue(bizMsg.destRtlWhCd_DS) && (!ZYPCommonFunc.hasValue(bizMsg.xxAllLineAddr_ST)
        //         || !ZYPCommonFunc.hasValue(bizMsg.shipToAddlLocNm_ST)
        //         || !ZYPCommonFunc.hasValue(bizMsg.shipToCtyAddr_ST)
        //         || !ZYPCommonFunc.hasValue(bizMsg.shipToCtyAddr_ST))) {
        // 
        //     NPAL1500CommonLogic.setRtlWhInfo(bizMsg, glblMsg);
        // }
        // END   2017/11/07 [QC#21849, DEL]

        String bkDsPoTpCd = bizMsg.dsPoTpCd.getValue();    // 2019/10/28 T.Ogura [QC#51802,ADD]

        // START 2019/07/17 M.Naito [QC#51695,ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.dsPoTpCd, chkInvtyOwnrCd(bizMsg.glblCmpyCd.getValue(), bizMsg.dsPoTpCd.getValue(), bizMsg.destRtlWhCd_DS.getValue()));
        // END 2019/07/17 M.Naito [QC#51695,ADD]

        // Copy Message
        NPAL1500CommonLogic.copyFromCmsgOntoSmsg(bizMsg, glblMsg);
        // START 2024/1/1 K.Iwamoto [QC#62443, ADD]]
        if (NPAL1500CommonLogic.checkAllLineType(bizMsg, glblMsg)) {
            NPAL1500CommonLogic.copyFromSMsgOntoCmsg(bizMsg, glblMsg);
            isErr = true;
        }
        // END 2024/1/1 K.Iwamoto [QC#62443, ADD]]
        //QC#28460 Add Start
        if (DS_PO_TP.SUBCONTRACT_PO.equals(bizMsg.dsPoTpCd.getValue()) && ZYPCommonFunc.hasValue(bizMsg.destRtlWhCd_DS)) {
            S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().checkWhOwnrCdForSc(bizMsg);

            if (ssmResult.isCodeNormal()) {
                List<Map> resultList = (List<Map>) ssmResult.getResultObject();
                if (0 < resultList.size()) {
                    //error
                    bizMsg.dsPoTpCd.setErrorInfo(1, NPAM1217E);
                    bizMsg.destRtlWhCd_DS.setErrorInfo(1, NPAM1217E);
                    bizMsg.setMessageInfo(ZZM9037E);
                    isErr = true;
                }
            }
        }
        //QC#28460 Add End
        
        // START 2017/11/07 [QC#21849, ADD]
        if(NPAL1500CommonLogic.checkDetailLineType(bizMsg, glblMsg)) {
            ZYPEZDItemValueSetter.setValue(glblMsg.xxRsltFlg, NPAL1500Constant.RSLT_FLG_ERROR);
            if (!ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
                bizMsg.setMessageInfo(ZZM9037E);
            }
            isErr = true;
        }

        // START 2022/12/09 M.Kikushima[QC#60604, ADD]
        if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxWrnSkipFlg_A.getValue())) {
            if (NPAL1500CommonLogic.isManualDropShipWHCd(bizMsg.glblCmpyCd.getValue(), bizMsg.destRtlWhCd_DS.getValue())) {
                if (NPAL1500CommonLogic.isCsaWh(bizMsg.glblCmpyCd.getValue(), bizMsg.shipToCustCd.getValue())) {
                    bizMsg.setMessageInfo(NPAM1655W);
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_A, ZYPConstant.FLG_ON_Y);
                    return;
                }
            }
        }
        // END 2022/12/09 M.Kikushima[QC#60604, ADD]

        // QC#24932 MOD START
        if (PO_APVL_STS.ENTERED.equals(glblMsg.poApvlStsCd.getValue()) && (ZYPCommonFunc.hasValue(glblMsg.poQlfyCd) && PO_QLFY.DROPSHIP.equals(glblMsg.poQlfyCd.getValue()))) {

            // do nothing

        } else {

            if (!NPAL1500CommonLogic.checkConsistencyDestWH(bizMsg, glblMsg)) {
                NPAL1500CommonLogic.setRtlWhInfo(bizMsg, glblMsg);
            } else {
                ZYPEZDItemValueSetter.setValue(glblMsg.xxRsltFlg, NPAL1500Constant.RSLT_FLG_ERROR);
                if (!ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
                    bizMsg.setMessageInfo(ZZM9037E);
                }
                isErr = true;
            }
        }
        // QC#24932 MOD END

        // END   2017/11/07 [QC#21849, ADD]

        // START 2017/12/04 [QC#14858, ADD]
        // Check:Item# or Item Description is mandatory.
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (NPAL1500CommonLogic.checkItemInfo(bizMsg, glblMsg, i)) {
                ZYPEZDItemValueSetter.setValue(glblMsg.xxRsltFlg, NPAL1500Constant.RSLT_FLG_ERROR);
                if (!ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
                    bizMsg.setMessageInfo(ZZM9037E);
                }
                isErr = true;
            }
        }

        // Set Items for TEXT ITEM.
        if (NPAL1500CommonLogic.setTextItem(bizMsg, glblMsg)) {
            ZYPEZDItemValueSetter.setValue(glblMsg.xxRsltFlg, NPAL1500Constant.RSLT_FLG_ERROR);
            if (!ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
                bizMsg.setMessageInfo(ZZM9037E);
            }
            isErr = true;
        }
        
        // END 2017/12/04 [QC#14858, ADD]
        
        // Check Item Master For Set
        // QC#27127
        S21LRUMap<Object, S21SsmEZDResult> deriveItemInfoCache = new S21LRUMap<Object, S21SsmEZDResult>();
        if (!checkItemMasterForSet(bizMsg, glblMsg, deriveItemInfoCache)) {
            ZYPEZDItemValueSetter.setValue(glblMsg.xxRsltFlg, NPAL1500Constant.RSLT_FLG_ERROR);
            if (!ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
                bizMsg.setMessageInfo(ZZM9037E);
            }
            isErr = true;
        }

        // Set Derive Item
        if (setDeriveItemForSaveAndSubmit(bizMsg, glblMsg)) {
            ZYPEZDItemValueSetter.setValue(glblMsg.xxRsltFlg, NPAL1500Constant.RSLT_FLG_ERROR);
            if (!ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
                bizMsg.setMessageInfo(ZZM9037E);
            }
            isErr = true;
        }

        // Calculate PO Qty
        BigDecimal poQty = BigDecimal.ZERO;
        String salesDt = ZYPDateUtil.getSalesDate(glblMsg.glblCmpyCd.getValue());

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            poQty = getVndOrdQty(glblMsg, salesDt, glblMsg.A.no(i));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).poQty_HD, poQty);
            
            // QC#14858. set entDealNetUnitPrcAmt_A1 & UOM. QC#23616 Mod
            if ((PO_LINE_TP.EXPENSE.equals(glblMsg.A.no(i).poLineTpCd_A1.getValue()) //
                    || PO_LINE_TP.EXPENSE_WITH_RECEIPT.equals(glblMsg.A.no(i).poLineTpCd_A1.getValue()) //
                    || PO_LINE_TP.ASSET.equals(glblMsg.A.no(i).poLineTpCd_A1.getValue())) //
                    && ZYPConstant.FLG_ON_Y.equals(glblMsg.A.no(i).xxCntDplyFlg_A2.getValue())) {

                BigDecimal amt = glblMsg.A.no(i).entDealNetUnitPrcAmt_A1.getValue().multiply(poQty);
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).entPoDtlDealNetAmt_A1, amt);
            
            }
        }

        // START 2017/12/04 [QC#14858, ADD]
        //Received Qty check
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

            // START 02/18/2020 T.Ogura [QC#55916,ADD]
            if (ZYPConstant.FLG_OFF_N.equals(glblMsg.A.no(i).openPoFlg_A1.getValue())) {
                continue;
            }
            // END   02/18/2020 T.Ogura [QC#55916,ADD]

            if ((glblMsg.A.no(i).poRcvQty_A1.getValueInt() < 0 //
                    || glblMsg.A.no(i).poQty_HD.getValueInt() < glblMsg.A.no(i).poRcvQty_A1.getValueInt()) 
                    && PO_LINE_TP.EXPENSE_WITH_RECEIPT.equals(glblMsg.A.no(i).poLineTpCd_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(glblMsg.xxRsltFlg, NPAL1500Constant.RSLT_FLG_ERROR);
                bizMsg.A.no(i).poRcvQty_A1.setErrorInfo(1, NPAM1610E);
                if (!ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
                    bizMsg.setMessageInfo(ZZM9037E);
                }
                isErr = true;
            }
        }
        // END 2017/12/04 [QC#14858, ADD]

        // check Msg
        // QC#27127. Mod QC#29155
        if (!checkCommonLogic(bizMsg.glblCmpyCd.getValue(), bizMsg, glblMsg, deriveItemInfoCache, isModifyShpgSvcLvlReln)) {
            ZYPEZDItemValueSetter.setValue(glblMsg.xxRsltFlg, NPAL1500Constant.RSLT_FLG_ERROR);
            if (!ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
                bizMsg.setMessageInfo(ZZM9037E);
            }
            isErr = true;
        }
        // set ccy code
        S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getVendorInfo(bizMsg);
        if (ssmResult.isCodeNormal()) {
            List<Map> resultList = (List<Map>) ssmResult.getResultObject();
            if (0 < resultList.size()) {
                Map recode = resultList.get(0);
                ZYPEZDItemValueSetter.setValue(bizMsg.ccyCd, (String) recode.get(DB_DEAL_CCY_CD));
                ZYPEZDItemValueSetter.setValue(glblMsg.ccyCd, (String) recode.get(DB_DEAL_CCY_CD));
            }
        }

        // 2019/09/24 QC#53422 Add Start
        if (!isErr) {
        	// Get Account
            //QC#29064 Add Start
            if (!bizMsg.destRtlWhCd_DS.isError()) {
            //QC#29064 Add End
                for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                    NPAL1500CommonLogic.getAccountInfo(bizMsg, glblMsg, i, false);
                }
            }
            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

                // START 02/18/2020 T.Ogura [QC#55916,ADD]
                if (ZYPConstant.FLG_OFF_N.equals(glblMsg.A.no(i).openPoFlg_A1.getValue())) {
                    continue;
                }
                // END   02/18/2020 T.Ogura [QC#55916,ADD]

                if (!NPAL1500CommonLogic.getDefaultAccountInfo(bizMsg, glblMsg, getGlobalCompanyCode(), i)) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.xxRsltFlg, NPAL1500Constant.RSLT_FLG_ERROR);
                    if (!ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
                        bizMsg.setMessageInfo(ZZM9037E);
                    }
                    isErr = true;
                }
            }
        }
        // 2019/09/24 QC#53422 Add End

        // QC#21170
        // Set rqstRcvDt (Date & Time Needed)
        NPAL1500CommonLogic.setHeaderRqstRcvDt_SmsgMaxVal(bizMsg, glblMsg);
        // START 2023/02/14 S.Dong [QC#60966, ADD]
        // Set rqstShipDt (Vendor Ship Date)
        NPAL1500CommonLogic.setHeaderRqstShipDt(bizMsg, glblMsg);
        // END 2023/02/14 S.Dong [QC#60966, ADD]
        // QC#23726 Check Customer Service Relation. Mod QC#29155.
        if (NPAL1500CommonLogic.checkCustCarrSvcLvlRelation(bizMsg, glblMsg, isModifyShpgSvcLvlReln)) {
            bizMsg.carrCd.setErrorInfo(2, NPAM1636W);
            bizMsg.shpgSvcLvlCd.setErrorInfo(2, NPAM1636W);
            isErr = true;
        }

        // processing registration
        if (!isErr) {
            // START 2019/10/28 T.Ogura [QC#51802,MOD]
//            regist(bizMsg, glblMsg, true, getUserProfileService());
            regist(bizMsg, glblMsg, true, getUserProfileService(), bkDsPoTpCd);
            // END   2019/10/28 T.Ogura [QC#51802,MOD]

            if (!MESSAGE_KIND_W.equals(bizMsg.getMessageKind())) {

                bizMsg.setMessageInfo(ZZZM9003I, new String[] {"Submit" });

            }
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.dsPoTpCd, bkDsPoTpCd);    // 2019/10/28 T.Ogura [QC#51802,ADD]
            moveErrPage(bizMsg, glblMsg);
        }

    }

    /**
     * doProcess_NPAL1500Scrn00_MoveTo_Componet
     * @param bizMsg NPAL1500CMsg
     * @param bizMsg NPAL1500SMsg
     */
    private void doProcess_NPAL1500Scrn00_MoveTo_Componet(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.poNum)) {
            doProcess_NPAL1500Scrn00_CMN_Save(bizMsg, glblMsg);
        }
    }

    /**
     * doProcess_NPAL1500Scrn00_Print
     * @param bizMsg NPAL1500CMsg
     * @param bizMsg NPAL1500SMsg
     */
    private void doProcess_NPAL1500Scrn00_Print(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        // QC#21170
        // Set rqstRcvDt (Date & Time Needed)
        NPAL1500CommonLogic.copyFromCmsgOntoSmsg(bizMsg, glblMsg);
        NPAL1500CommonLogic.setHeaderRqstRcvDt_SmsgMaxVal(bizMsg, glblMsg);
        // START 2023/02/14 S.Dong [QC#60966, ADD]
        // Set rqstShipDt (Vendor Ship Date)
        NPAL1500CommonLogic.setHeaderRqstShipDt(bizMsg, glblMsg);
        // END 2023/02/14 S.Dong [QC#60966, ADD]

        checkPrintParam(bizMsg, glblMsg);

        lockPo(bizMsg, glblMsg);

        print(bizMsg, glblMsg);

        bizMsg.setMessageInfo(ZZZM9003I, new String[] {"Print" });
    }


    /**
     * lockPo
     * @param cMsg NPAL1500CMsg
     * @param glblMsg NPAL1500SMsg
     * @param dslockFlg boolean true : add lock target DS_PO,
     * PO_DTL
     * @return boolean true : normal end, false : abnormal end
     */
    private static boolean lockPo(NPAL1500CMsg cMsg, NPAL1500SMsg glblMsg) {

        POTMsg poTMsg = findPoByKeyForUpdate(cMsg);

        if (poTMsg == null) {
            cMsg.setMessageInfo(NPAM0006E);
            return false;
        }

        for (int idxPoDetail = 0; idxPoDetail < glblMsg.A.getValidCount(); idxPoDetail++) {
            if (!lockPoDetail(cMsg, glblMsg, idxPoDetail)) {
                return false;
            }
        }

        // START 2017/09/04 S.Katsuma [QC#20688,ADD]
        ZYPEZDItemValueSetter.setValue(glblMsg.vndDropShipFlg, poTMsg.vndDropShipFlg);
        // QC#28226 Delete Start
        //ZYPEZDItemValueSetter.setValue(glblMsg.vndPmtTermCd, poTMsg.vndPmtTermCd);
        // QC#28226 Delete End
        ZYPEZDItemValueSetter.setValue(glblMsg.rqstTechTocCd, poTMsg.rqstTechTocCd);
        ZYPEZDItemValueSetter.setValue(glblMsg.lineBizTpCd, poTMsg.lineBizTpCd);
        // END 2017/09/04 S.Katsuma [QC#20688,ADD]

        return true;
    }

    /**
     * lock PO Detail
     * @param cMsg NPAL1500CMsg
     * @param glblMsg NPAL1500SMsg
     * @param dslockFlg boolean
     * @param idxPoDetail int
     * @return boolean
     */
    private static boolean lockPoDetail(NPAL1500CMsg cMsg, NPAL1500SMsg glblMsg, int idxPoDetail) {
        if (ZYPCommonFunc.hasValue(glblMsg.A.no(idxPoDetail).poStsCd_HD)) {
            PO_DTLTMsg poDtlTMsg = findPoDtlByKeyForUpdate(cMsg, glblMsg.A.no(idxPoDetail));
            if (poDtlTMsg == null) {
                cMsg.setMessageInfo(NPAM0006E);
                return false;
            }

            // START 2017/09/04 S.Katsuma [QC#20688,ADD]
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idxPoDetail).entFuncNetUnitPrcAmt_A1, poDtlTMsg.entFuncNetUnitPrcAmt);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idxPoDetail).entPoDtlFuncNetAmt_A1, poDtlTMsg.entPoDtlFuncNetAmt);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idxPoDetail).exchRate_A1, poDtlTMsg.exchRate);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idxPoDetail).custUomCd_A1, poDtlTMsg.custUomCd);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idxPoDetail).adminPsnCd_A1, poDtlTMsg.adminPsnCd);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idxPoDetail).ordQty_A1, poDtlTMsg.ordQty);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idxPoDetail).cpoOrdNum_A1, poDtlTMsg.cpoOrdNum);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idxPoDetail).cpoDtlLineNum_A1, poDtlTMsg.cpoDtlLineNum);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idxPoDetail).cpoDtlLineSubNum_A1, poDtlTMsg.cpoDtlLineSubNum);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idxPoDetail).custIssPoNum_A1, poDtlTMsg.custIssPoNum);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idxPoDetail).custIssPoDt_A1, poDtlTMsg.custIssPoDt);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idxPoDetail).cpoOrdTpCd_A1, poDtlTMsg.cpoOrdTpCd);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idxPoDetail).sellToCustCd_A1, poDtlTMsg.sellToCustCd);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idxPoDetail).shpgPlnNum_A1, poDtlTMsg.shpgPlnNum);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idxPoDetail).trxRefLineNum_A1, poDtlTMsg.trxRefLineNum);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idxPoDetail).trxRefLineSubNum_A1, poDtlTMsg.trxRefLineSubNum);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idxPoDetail).vndIssPoOrdNum_A1, poDtlTMsg.vndIssPoOrdNum);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idxPoDetail).proNum_A1, poDtlTMsg.proNum);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idxPoDetail).shipToAcctNm_A1, poDtlTMsg.shipToAcctNm);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idxPoDetail).shipToFirstRefCmntTxt_A1, poDtlTMsg.shipToFirstRefCmntTxt);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idxPoDetail).shipToScdRefCmntTxt_A1, poDtlTMsg.shipToScdRefCmntTxt);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idxPoDetail).shipFromSoNumListTxt_A1, poDtlTMsg.shipFromSoNumListTxt);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idxPoDetail).frtChrgToCd_A1, poDtlTMsg.frtChrgToCd);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idxPoDetail).frtChrgMethCd_A1, poDtlTMsg.frtChrgMethCd);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idxPoDetail).origPoOrdNum_A1, poDtlTMsg.origPoOrdNum);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idxPoDetail).origPoOrdDtlLineNum_A1, poDtlTMsg.origPoOrdDtlLineNum);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idxPoDetail).origDispPoDtlLineNum_A1, poDtlTMsg.origDispPoDtlLineNum);
            // END 2017/09/04 S.Katsuma [QC#20688,ADD]
        }
        return true;
    }
    /**
     * cancel
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     */
    private static void cancel(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg) {

        // Delete Line check
        ArrayList<NPAL1500_ASMsg> tmpArray = new ArrayList<NPAL1500_ASMsg>();

        // copy
        String sameNum = "00";
        boolean lineDeleteFlg = true;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).xxChkBox_A1)) {
                if (PO_MDSE_CMPSN_TP.PARENT.equals(sMsg.A.no(i).poMdseCmpsnTpCd_HD.getValue())) {
                    String lineNum = sMsg.A.no(i).dispPoDtlLineNum_A1.getValue();
                    sameNum = lineNum.substring(0, lineNum.indexOf(".") + 1);
                }
                // status change record?
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).xxDplyCtrlFlg_NL) && ZYPCommonFunc.hasValue(sMsg.poStsCd_H) //
                        // 2017/09/27 S.Endo DEL QC#21206 START
                        // && !PO_STS.SAVED.equals(sMsg.poStsCd_H.getValue())
                        // 2017/09/27 S.Endo DEL QC#21206 END
                        ) {
                    lineDeleteFlg = false;
                }
            }

            if (sMsg.A.no(i).dispPoDtlLineNum_A1.getValue().startsWith(sameNum)) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxChkBox_A1, ZYPConstant.FLG_ON_Y);
            }

            tmpArray.add(sMsg.A.no(i));
        }

        // New PO or Save PO.
        if (lineDeleteFlg) {

            cancelNewOrSavedPO(cMsg, sMsg, tmpArray);

        } else {
            cancelProcessingPO(cMsg, sMsg);
        }
    }

    /**
     * cancelProcessingPO
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     */
    private static void cancelProcessingPO(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg) {
        // lock(PO Header, PO Detail 4tbl)
        lockPo(cMsg, sMsg);

        // lock(Shipping Plan)
        lockShpgPln(cMsg, sMsg);

        // line cancel
        NPZC004001PMsg pMsg = new NPZC004001PMsg();

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).xxChkBox_A1) && !ZYPCommonFunc.hasValue(sMsg.A.no(i).xxDplyCtrlFlg_NL)) {
                // PO Status is not (Null and Saved)
                if (!(ZYPCommonFunc.hasValue(sMsg.A.no(i).poLineStsCd_A1.getValue())) || !(sMsg.A.no(i).poLineStsCd_A1.getValue().equals(PO_STS.SAVED))) {

                    // execute PO Status Update API (NPZC0040)
                    ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, sMsg.glblCmpyCd.getValue());
                    ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum, sMsg.poNum_HD.getValue());
                    ZYPEZDItemValueSetter.setValue(pMsg.poStsCd, PO_STS.CANCELLED);
                    ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, sMsg.A.no(i).mdseCd_A1.getValue());
                    ZYPEZDItemValueSetter.setValue(pMsg.poOrdDtlLineNum, sMsg.A.no(i).poOrdDtlLineNum_A1.getValue());

                    NPZC004001 api = new NPZC004001();
                    api.execute(pMsg, ONBATCH_TYPE.ONLINE);

                    if (S21ApiUtil.isXxMsgId(pMsg)) {
                        cMsg.setMessageInfo(pMsg.xxMsgIdList.no(0).xxMsgId.getValue());
                        cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                        return;
                    }

                    // START 2019/11/15 T.Ogura [QC#54678,MOD]
//                    if (isDropShip(cMsg) && !PO_LINE_TP.ITT.equals((cMsg.A.no(i).poLineTpCd_A1.getValue()))) {
                    if (isDropShip(cMsg) && !PO_LINE_TP.ITT.equals((sMsg.A.no(i).poLineTpCd_A1.getValue()))) {
                    // END   2019/11/15 T.Ogura [QC#54678,MOD]
                        // QC#29600
                        if (PO_MDSE_CMPSN_TP.PARENT.equals(sMsg.A.no(i).poMdseCmpsnTpCd_HD.getValue())) {
                            continue;
                        }
                        List<String> shpgPlnNumList = new ArrayList<String>();
                        shpgPlnNumList.add(sMsg.A.no(i).shpgPlnNum_D3.getValue());
                        executeShippingPlanUpdateApi(cMsg, cMsg.glblCmpyCd.getValue(), shpgPlnNumList);
                    }
                }
            }
        }

        // set re search flg
        ZYPEZDItemValueSetter.setValue(sMsg.xxSrchTrfFlg_HD, ZYPConstant.FLG_ON_Y);
    }

    /**
     * cancel PO in New/Saved Status
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     * @param tmpArray ArrayList<NPAL1500_ASMsg>
     */
    private static void cancelNewOrSavedPO(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg, ArrayList<NPAL1500_ASMsg> tmpArray) {
        ArrayList<NPAL1500_ASMsg> tmp = new ArrayList<NPAL1500_ASMsg>();

        // Line Delete
        if (ZYPCommonFunc.hasValue(sMsg.poStsCd_H) //
                && PO_STS.SAVED.equals(sMsg.poStsCd_H.getValue()) //
                && DS_PO_TP.SUBCONTRACT_PO.equals(sMsg.dsPoTpCd.getValue())) {

            // Inventory Req Check

            S21SsmEZDResult ssmRslt = NPAL1500Query.getInstance().getInventoryReq(sMsg);
            if (ssmRslt.isCodeNormal()) {
                Map<String, String> rsltMap = (Map<String, String>) ssmRslt.getResultObject();
                String prchReqStsCd = rsltMap.get("PRCH_REQ_STS_CD");

                if (ZYPCommonFunc.hasValue(prchReqStsCd) //
                        && !PRCH_REQ_STS.CANCELLED.equals(prchReqStsCd)) {
                    // Error
                    setErrorInventoryRequestExistsDetail(sMsg);
                    // copy and pagination
                    NPAL1500CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);

                    cMsg.setMessageInfo(ZZM9037E);

                    return;
                }
            }
        }
        // get not cancel line
        Iterator<NPAL1500_ASMsg> ite = tmpArray.iterator();

        while (ite.hasNext()) {
            NPAL1500_ASMsg tmpMsg = ite.next();

            if (!ZYPCommonFunc.hasValue(tmpMsg.xxChkBox_A1)) {
                tmp.add(tmpMsg);
            }
        }

        // Relocation
        relocateDetailLines(sMsg, tmp);
        sMsg.A.setValidCount(tmp.size());
        // Set showPageOf
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());

        // set re search flg
        ZYPEZDItemValueSetter.setValue(sMsg.xxSrchTrfFlg_HD, ZYPConstant.FLG_OFF_N);

        // Pagination
        int index = cMsg.xxPageShowFromNum.getValueInt();
        int recordLength = sMsg.A.getValidCount();
        int pageLength = cMsg.A.length();
        // Check index
        while (index > recordLength) {
            index = index - pageLength;
        }
        if (index < 0) {
            // First Page
            index = 1;
        }
        cMsg.xxPageShowFromNum.setValue(index);
        NPAL1500CommonLogic.pagenation(cMsg, sMsg);
    }

    /**
     * relocateDetailLines
     * @param sMsg NPAL1500SMsg
     * @param tmp ArrayList<NPAL1500_ASMsg>
     */
    private static void relocateDetailLines(NPAL1500SMsg sMsg, ArrayList<NPAL1500_ASMsg> tmp) {
        int subLineNum = 0;
        int lineNum = 0;
        int parentNum = 0;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (i < tmp.size()) {
                NPAL1500_ASMsg msg = tmp.get(i);

                if (!PO_MDSE_CMPSN_TP.CHILD.equals(msg.poMdseCmpsnTpCd_HD.getValue())) {
                    subLineNum = 0;
                    lineNum++;
                    String dispNum = lineNum + PERIOD + subLineNum;
                    msg.dispPoDtlLineNum_A1.setValue(dispNum);
                    msg.poOrdDtlLineNum_A1.setValue(NPAL1500CommonLogic.addDtlLineNum(String.valueOf(i)));
                    if (PO_MDSE_CMPSN_TP.PARENT.equals(msg.poMdseCmpsnTpCd_HD.getValue())) {
                        parentNum = i;
                    }
                } else {
                    subLineNum++;
                    String dispNum = lineNum + PERIOD + subLineNum;
                    msg.dispPoDtlLineNum_A1.setValue(dispNum);
                    msg.poOrdDtlLineNum_A1.setValue(NPAL1500CommonLogic.addDtlLineNum(String.valueOf(i)));
                    msg.setPoOrdDtlLineNum.setValue(NPAL1500CommonLogic.addDtlLineNum(String.valueOf(parentNum)));
                }

                EZDMsg.copy(msg, null, sMsg.A.no(i), null);

            } else {
                sMsg.A.no(i).clear();
            }
        }
    }

    /**
     * setErrorInventoryRequestExistsDetail
     * @param sMsg NPAL1500SMsg
     */
    private static void setErrorInventoryRequestExistsDetail(NPAL1500SMsg sMsg) {
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A1.getValue())) {
                sMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NPAM1514E);
            }
        }
    }

    /** for close **/
    /**
     * close
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     * @param glblCmpyCd String
     */
    private static void close(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg) {

        // lock(PO Header, Detai)
        if (!lockPo(cMsg, sMsg)) {
            return;
        }

        NPZC004001PMsg pMsg = new NPZC004001PMsg();

        for (int idxPoDetail = 0; idxPoDetail < sMsg.A.getValidCount(); idxPoDetail++) {

            if (ZYPConstant.FLG_ON_Y.equalsIgnoreCase(sMsg.A.no(idxPoDetail).xxChkBox_A1.getValue())) {
                if (!closeDetailLine(cMsg, sMsg, pMsg, idxPoDetail)) {
                    return;
                }
            }
        }
    }

    /**
     * closeDetailLine
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     * @param pMsg NPZC004001PMsg
     * @param idxPoDetail int
     * @return boolean
     */
    private static boolean closeDetailLine(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg, NPZC004001PMsg pMsg, int idxPoDetail) {
        // if PO Line is not "creation RWS target", dummy
        // Revceiving
        if (PO_MATCH_TP.NO_GOODS_RECEIPT.equals(sMsg.A.no(idxPoDetail).poMatchTpCd_A1.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, sMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum, sMsg.poNum_HD.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, sMsg.A.no(idxPoDetail).mdseCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.poInvQty, sMsg.A.no(idxPoDetail).poQty_HD.getValue().subtract(sMsg.A.no(idxPoDetail).poRcvQty_A1.getValue()));
            ZYPEZDItemValueSetter.setValue(pMsg.poOrdDtlLineNum, sMsg.A.no(idxPoDetail).poOrdDtlLineNum_A1.getValue());

            NPZC004001 api = new NPZC004001();
            api.execute(pMsg, ONBATCH_TYPE.ONLINE);

            if (S21ApiUtil.isXxMsgId(pMsg)) {
                cMsg.setMessageInfo(pMsg.xxMsgIdList.no(0).xxMsgId.getValue());
                cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                return false;
            }

            pMsg.clear();
        } else {

            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, sMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum, sMsg.poNum_HD.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.poStsCd, PO_STS.CLOSED);
            ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, sMsg.A.no(idxPoDetail).mdseCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.poOrdDtlLineNum, sMsg.A.no(idxPoDetail).poOrdDtlLineNum_A1.getValue());

            NPZC004001 api = new NPZC004001();
            api.execute(pMsg, ONBATCH_TYPE.ONLINE);

            if (S21ApiUtil.isXxMsgId(pMsg)) {
                cMsg.setMessageInfo(pMsg.xxMsgIdList.no(0).xxMsgId.getValue());
                cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                return false;
            }

            pMsg.clear();
        }

        // START 2018/12/13 M.Naito [QC#29027,ADD]
        PO_DTLTMsg poDtlTMsg = new PO_DTLTMsg();
        poDtlTMsg.glblCmpyCd.setValue(sMsg.glblCmpyCd.getValue());
        poDtlTMsg.poOrdNum.setValue(sMsg.poNum.getValue());
        poDtlTMsg.poOrdDtlLineNum.setValue(sMsg.A.no(idxPoDetail).poOrdDtlLineNum_A1.getValue());
        poDtlTMsg = (PO_DTLTMsg) EZDTBLAccessor.findByKey(poDtlTMsg);

        if (poDtlTMsg != null && ZYPCommonFunc.hasValue(poDtlTMsg.poCancQty) && BigDecimal.ZERO.compareTo(poDtlTMsg.poCancQty.getValue()) != 0) {
            // START 2019/11/15 T.Ogura [QC#54678,MOD]
//            if (isDropShip(cMsg) && !PO_LINE_TP.ITT.equals((cMsg.A.no(idxPoDetail).poLineTpCd_A1.getValue()))) {
            if (isDropShip(cMsg) && !PO_LINE_TP.ITT.equals((sMsg.A.no(idxPoDetail).poLineTpCd_A1.getValue()))) {
            // END   2019/11/15 T.Ogura [QC#54678,MOD]
                List<String> shpgPlnNumList = new ArrayList<String>();
                shpgPlnNumList.add(sMsg.A.no(idxPoDetail).shpgPlnNum_D3.getValue());
                executeShippingPlanUpdateApi(cMsg, cMsg.glblCmpyCd.getValue(), shpgPlnNumList);
            }
        }
        // END 2018/12/13 M.Naito [QC#29027,ADD]

        return true;
    }
    /**
     * regist
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     * @param isSubmitFlg boolean
     * @param userProfileService S21UserProfileService
     * @param bkDsPoTpCd String
     */
    // START 2019/10/28 T.Ogura [QC#51802,MOD]
//    private static void regist(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg, boolean isSubmitFlg, S21UserProfileService userProfileService) {
    private static void regist(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg, boolean isSubmitFlg, S21UserProfileService userProfileService, String bkDsPoTpCd) {
    // END   2019/10/28 T.Ogura [QC#51802,MOD]

        // lock and exclusive check for PO, PO_DTL
        // tables
        if (ZYPCommonFunc.hasValue(cMsg.poHdrStsCd)) {
            if (!lockPo(cMsg, sMsg)) {
                ZYPEZDItemValueSetter.setValue(cMsg.dsPoTpCd, bkDsPoTpCd);    // 2019/10/28 T.Ogura [QC#51802,ADD]
                return;
            }
        }

        // setting PO Create API params
        String npzc104001Mode = NPZC104001Constant.EVENT_SAVE;
        if (isSubmitFlg) {
            npzc104001Mode = NPZC104001Constant.EVENT_SUBMIT;
        }
        NPZC104001PMsg param = setNPZC104001PMsgForSaveOrSubmit(cMsg, sMsg, npzc104001Mode, userProfileService);

        // execute PO Create API
        // START 2019/08/02 T.Ogura [QC#51448,MOD]
//        executePOCreateApi(cMsg, param);
        executePOCreateApi(cMsg, sMsg, param);
        // END   2019/08/02 T.Ogura [QC#51448,MOD]

        if (isSubmitFlg) {
            // QC#24413
            ZYPEZDItemValueSetter.setValue(sMsg.poNum, cMsg.poNum);
            NPZC004001PMsg pMsg = new NPZC004001PMsg();
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                // START 2017/12/12 [QC#14858, ADD] registDetailForExpenseWReceipt
                // if Expense w/Receipt, update Received Qty.
                if(PO_LINE_TP.EXPENSE_WITH_RECEIPT.equals(sMsg.A.no(i).poLineTpCd_A1.getValue()) && ZYPCommonFunc.hasValue(sMsg.A.no(i).poRcvQty_A1) && BigDecimal.ZERO.compareTo(sMsg.A.no(i).poRcvQty_A1.getValue()) < 0){
                    if (!registDetailForExpenseWReceipt(cMsg, sMsg, pMsg, i)) {
                        ZYPEZDItemValueSetter.setValue(cMsg.dsPoTpCd, bkDsPoTpCd);    // 2019/10/28 T.Ogura [QC#51802,ADD]
                        return;
                    }                    
                }
                // END 2017/12/12 [QC#14858, ADD]

                if (!registDetail(cMsg, sMsg, pMsg, i)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.dsPoTpCd, bkDsPoTpCd);    // 2019/10/28 T.Ogura [QC#51802,ADD]
                    return;
                }
                // QC#25024 Add Start
                // Update INBD_VIS
                    if(!updateInbdVis(cMsg, sMsg, i)){
                        ZYPEZDItemValueSetter.setValue(cMsg.dsPoTpCd, bkDsPoTpCd);    // 2019/10/28 T.Ogura [QC#51802,ADD]
                        return;
                    }
                // QC#25024 Add End
            }
        }
    }

    /**
     * registeDetail
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     * @param pMsg NPZC004001PMsg
     * @param i int
     * @return boolean
     */
    private static boolean registDetail(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg, NPZC004001PMsg pMsg, int i) {
        if (ZYPCommonFunc.hasValue(sMsg.A.no(i).poBalQty) //
                && sMsg.A.no(i).poBalQty.getValue().intValue() == ZERO) {
            // execute PO Status update API (NPZC0040)
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, sMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum, sMsg.poNum.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.poStsCd, PO_STS.CLOSED);
            ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, sMsg.A.no(i).mdseCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.poOrdDtlLineNum, sMsg.A.no(i).poOrdDtlLineNum_A1.getValue());

            NPZC004001 api = new NPZC004001();
            api.execute(pMsg, ONBATCH_TYPE.ONLINE);

            if (S21ApiUtil.isXxMsgId(pMsg)) {
                cMsg.setMessageInfo(pMsg.xxMsgIdList.no(0).xxMsgId.getValue());
                cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                return false;
            }
        }
        return true;
    }

    /**
     * <pre>
     * setNPZC104001PMsgForSaveOrSubmit
     * </pre>
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     * @param eventId String
     * @param userProfileService S21UserProfileService
     * @return NPZC104001PMsg
     */
    private static NPZC104001PMsg setNPZC104001PMsgForSaveOrSubmit(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg, String eventId, S21UserProfileService userProfileService) {

        List<BigDecimal> ordDtlLineList = new ArrayList<BigDecimal>();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            ordDtlLineList.add(new BigDecimal(sMsg.A.no(i).dispPoDtlLineNum_A1.getValue()));
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
            for (int idxPoDetail = 0; idxPoDetail < sMsg.A.getValidCount(); idxPoDetail++) {
                if (NPAL1500CommonLogic.sortSetPoOrdDtlLineNum(ordDtlLineList, sMsg, j, idxPoDetail, excludeLineList)) {
                    break;
                }
            }
        }

        S21SortTarget target = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
        S21SortKey key = new S21SortKey();
        key.add("poOrdDtlLineNum_A1", "ASC");
        S21EZDMsgArraySort.sort(target, key, 0, sMsg.A.getValidCount());

        NPZC104001PMsg pMsg = new NPZC104001PMsg();

        String glblCmpyCd = cMsg.glblCmpyCd.getValue();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);

        if (eventId.equals(NPZC104001Constant.EVENT_SAVE)) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC104001Constant.MODE_CREATE);
        } else if (eventId.equals(NPZC104001Constant.EVENT_SUBMIT)) {
            if (!ZYPCommonFunc.hasValue(cMsg.poStsCd_H) || PO_STS.SAVED.equals(cMsg.poStsCd_H.getValue())) {
                ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC104001Constant.MODE_CREATE);
                //QC#19485
                ZYPEZDItemValueSetter.setValue(pMsg.poSendFlg, ZYPConstant.FLG_OFF_N);
                pMsg.poSendTs.clear();
                pMsg.vndIssOrdNum.clear();
                //QC#19485
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC104001Constant.MODE_UPDATE);
                //QC#19485
                ZYPEZDItemValueSetter.setValue(pMsg.poSendFlg, sMsg.poSendFlg_HD);
                ZYPEZDItemValueSetter.setValue(pMsg.poSendTs, sMsg.poSendTs_HD);
                ZYPEZDItemValueSetter.setValue(pMsg.vndIssOrdNum, sMsg.vndIssOrdNum_HD);
                //QC#19485
            }
        }
        ZYPEZDItemValueSetter.setValue(pMsg.eventId, eventId);
        String salesDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        String procStartTs = ZYPDateUtil.getCurrentSystemTime("HHmmssSSS");
        ZYPEZDItemValueSetter.setValue(pMsg.procDt, salesDt);
        ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTs, salesDt + procStartTs);
        if (ZYPCommonFunc.hasValue(cMsg.poStsCd_H)) {
            ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum, sMsg.poNum_HD);
        } else {
            pMsg.poOrdNum.clear();
        }
        ZYPEZDItemValueSetter.setValue(pMsg.dsPoTpCd, sMsg.dsPoTpCd);
        String dsPoTpNm = getDsPoTpNm(glblCmpyCd, sMsg.dsPoTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.dsPoTpNm, dsPoTpNm);

        // Set PO QLFY CD (new registration)
        if (!ZYPCommonFunc.hasValue(sMsg.poStsCd_H)) {
            // Technician warehouse:PC / Not technician warehouse:
            // null
            if (isTechnicianWh(glblCmpyCd, sMsg.destRtlWhCd_DS.getValue())) {
                ZYPEZDItemValueSetter.setValue(pMsg.poQlfyCd, ZYPCodeDataUtil.getVarCharConstValue(TECH_INSRC_PO_QLFY_CD, glblCmpyCd));
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.poQlfyCd, sMsg.poQlfyCd);
            }
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.poQlfyCd, sMsg.poQlfyCd);
        }

        ZYPEZDItemValueSetter.setValue(pMsg.poSubmtPsnCd, userProfileService.getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(pMsg.poSubmtTs, salesDt + procStartTs);
        pMsg.poApvlPsnCd.clear();
        pMsg.poApvlDt.clear();
        pMsg.poApvlTs.clear();
        if (NPZC104001Constant.EVENT_SAVE.equals(eventId)) {
            ZYPEZDItemValueSetter.setValue(pMsg.poApvlStsCd, sMsg.poApvlStsCd);
            // START 2021/04/23 [QC#58645,MOD]
//        } else if (NPAL1500CommonLogic.isPreApprovedWhOwnr(sMsg)) {
        } else if (NPAL1500CommonLogic.isPreApprovedWhOwnr(sMsg) || NPAL1500CommonLogic.checkAutoApprove(sMsg)) {
            // END 2021/04/23 [QC#58645,MOD]
            // QC#17487
            // Set Pre-Approved if target WH is included in specified WH owners.
            ZYPEZDItemValueSetter.setValue(pMsg.poApvlStsCd, PO_APVL_STS.PRE_APPROVED);
            ZYPEZDItemValueSetter.setValue(pMsg.poApvlPsnCd, pMsg.poSubmtPsnCd);
            ZYPEZDItemValueSetter.setValue(pMsg.poApvlDt, salesDt);
            ZYPEZDItemValueSetter.setValue(pMsg.poApvlTs, pMsg.poSubmtTs);
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.poApvlStsCd, PO_APVL_STS.AWAITING_APPROVAL);
        }

        ZYPEZDItemValueSetter.setValue(pMsg.destRtlWhCd, sMsg.destRtlWhCd_DS);
        if (DS_PO_TP.BUYBACK_PO.equals(sMsg.dsPoTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.srcRtlWhCd, sMsg.srcRtlWhCd_SC);
        } else {
            pMsg.srcRtlWhCd.clear();
        }
        // START 2017/09/04 S.Katsuma [QC#20688,ADD]
        if (ZYPCommonFunc.hasValue(sMsg.poOrdSrcCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.poOrdSrcCd, sMsg.poOrdSrcCd);
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.poOrdSrcCd, PO_ORD_SRC.PO_ENTRY);
        }
        // END 2017/09/04 S.Katsuma [QC#20688,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.prntVndCd, sMsg.prntVndCd);
        ZYPEZDItemValueSetter.setValue(pMsg.prntVndNm, sMsg.prntVndNm);
        ZYPEZDItemValueSetter.setValue(pMsg.vndCd, sMsg.vndCd);
        ZYPEZDItemValueSetter.setValue(pMsg.vndNm, sMsg.vndNm);
        ZYPEZDItemValueSetter.setValue(pMsg.dealCcyCd, sMsg.ccyCd);
        // START 2017/09/04 S.Katsuma [QC#20688,ADD]
        if (ZYPCommonFunc.hasValue(sMsg.vndDropShipFlg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.vndDropShipFlg, sMsg.vndDropShipFlg);
        } else {
            pMsg.vndDropShipFlg.clear();
        }


        // QC#24932 ADD START
        // check 3rd pty supplier. sales order
        S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().searchSupplier(sMsg);
        // QC#30436 MOD START
        S21SsmEZDResult searchPrchReqSsmResult = NPAL1500Query.getInstance().searchPrchReq(sMsg);

        // 3rdPty vendor and source from prch req.
        if (!ssmResult.isCodeNormal() && searchPrchReqSsmResult.isCodeNormal()) {
            // QC#52971
            if (NPZC104001Constant.EVENT_SUBMIT.equals(eventId) && !DS_PO_TP.SUBCONTRACT_PO.equals(sMsg.dsPoTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(pMsg.poApvlStsCd, PO_APVL_STS.APPROVED);
                ZYPEZDItemValueSetter.setValue(pMsg.poApvlPsnCd, pMsg.poSubmtPsnCd);
                ZYPEZDItemValueSetter.setValue(pMsg.poApvlDt, salesDt);
                ZYPEZDItemValueSetter.setValue(pMsg.poApvlTs, pMsg.poSubmtTs);
            }
        }

//        if (!ssmResult.isCodeNormal() && ZYPConstant.FLG_ON_Y.equals(sMsg.vndDropShipFlg.getValue())) {
//            // 3rdPty
//            if (NPZC104001Constant.EVENT_SUBMIT.equals(eventId)) {
//                ZYPEZDItemValueSetter.setValue(pMsg.poApvlStsCd, PO_APVL_STS.APPROVED);
//                ZYPEZDItemValueSetter.setValue(pMsg.poApvlPsnCd, pMsg.poSubmtPsnCd);
//                ZYPEZDItemValueSetter.setValue(pMsg.poApvlDt, salesDt);
//                ZYPEZDItemValueSetter.setValue(pMsg.poApvlTs, pMsg.poSubmtTs);
//            } else {
//                ZYPEZDItemValueSetter.setValue(pMsg.poApvlStsCd, sMsg.poApvlStsCd);
//            }
//            pMsg.vndDropShipFlg.clear();
//        }

        // QC#24932 ADD END
        // QC#30436 MOD END

        // END 2017/09/04 S.Katsuma [QC#20688,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.prchGrpCd, sMsg.prchGrpCd);
        // START 2017/09/04 S.Katsuma [QC#20688,ADD]
        if (ZYPCommonFunc.hasValue(sMsg.vndPmtTermCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.vndPmtTermCd, sMsg.vndPmtTermCd);
        } else {
            pMsg.vndPmtTermCd.clear();
        }
        // END 2017/09/04 S.Katsuma [QC#20688,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.vndPmtTermDescTxt, sMsg.vndPmtTermDescTxt);
        // START 2017/09/04 S.Katsuma [QC#20688,ADD]
        if (ZYPCommonFunc.hasValue(sMsg.rqstTechTocCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.rqstTechTocCd, sMsg.rqstTechTocCd);
        } else {
            pMsg.rqstTechTocCd.clear();
        }
        // END 2017/09/04 S.Katsuma [QC#20688,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.rqstRcvDt, sMsg.rqstRcvDt);
        ZYPEZDItemValueSetter.setValue(pMsg.rqstRcvTm, sMsg.rqstRcvTm);
        // START 2023/02/14 S.Dong [QC#60966, ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.rqstShipDt, sMsg.rqstShipDt);
        // END 2023/02/14 S.Dong [QC#60966, ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, sMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(pMsg.shipToLocNm, sMsg.shipToLocNm_ST);
        // START 2017/09/04 S.Katsuma [QC#20688,DEL]
//        pMsg.shipToAcctNm.clear();
        // END 2017/09/04 S.Katsuma [QC#20688,DEL]
        ZYPEZDItemValueSetter.setValue(pMsg.shipToAddlLocNm, sMsg.shipToAddlLocNm_ST);
        int lineAddrLength = sMsg.xxAllLineAddr_ST.getValue().length();
        // QC#30566
        if (lineAddrLength < 61) {
            ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstLineAddr, sMsg.xxAllLineAddr_ST.getValue());
        } else if (lineAddrLength < 121) {
            // START 2019/02/26 T.Ogura [QC#30518,MOD]
            String lineAddr = sMsg.xxAllLineAddr_ST.getValue();
            StringBuilder sb = new StringBuilder(lineAddr);
            if(sb.substring(60, 61).equals(" ")) {
                sb.replace(60, 61, "\t");
            }
            lineAddr = sb.toString().replaceAll("\t", "");
            if (lineAddrLength > lineAddr.length()) {
                lineAddrLength = lineAddr.length();
            }
            ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstLineAddr, lineAddr.substring(0, 60));
            // QC#30566
            ZYPEZDItemValueSetter.setValue(pMsg.shipToScdLineAddr, lineAddr.substring(60, lineAddrLength));
            // END   2019/02/26 T.Ogura [QC#30518,MOD]
        } else if (lineAddrLength < 181) {
            // START 2019/02/26 T.Ogura [QC#30518,MOD]
            String lineAddr = sMsg.xxAllLineAddr_ST.getValue();
            StringBuilder sb = new StringBuilder(lineAddr);
            if(sb.substring(60, 61).equals(" ")) {
                sb.replace(60, 61, "\t");
            }
            if (lineAddrLength > 121) {
                if(sb.substring(121, 122).equals(" ")) {
                    sb.replace(121, 122, "\t");
                }
            }
            lineAddr = sb.toString().replaceAll("\t", "");
            if (lineAddrLength > lineAddr.length()) {
                lineAddrLength = lineAddr.length();
            }
            ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstLineAddr, lineAddr.substring(0, 60));
            // QC#30566
            ZYPEZDItemValueSetter.setValue(pMsg.shipToScdLineAddr, lineAddr.substring(60, 120));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToThirdLineAddr, lineAddr.substring(120, lineAddrLength));
            // END   2019/02/26 T.Ogura [QC#30518,MOD]
        } else {
            // START 2019/02/26 T.Ogura [QC#30518,MOD]
            String lineAddr = sMsg.xxAllLineAddr_ST.getValue();
            StringBuilder sb = new StringBuilder(lineAddr);
            if(sb.substring(60, 61).equals(" ")) {
                sb.replace(60, 61, "\t");
            }
            if(sb.substring(121, 122).equals(" ")) {
                sb.replace(121, 122, "\t");
            }
            if (lineAddrLength > 182) {
                if(sb.substring(182, 183).equals(" ")) {
                    sb.replace(182, 183, "\t");
                }
            }
            lineAddr = sb.toString().replaceAll("\t", "");
            if (lineAddrLength > lineAddr.length()) {
                lineAddrLength = lineAddr.length();
            }
            ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstLineAddr, lineAddr.substring(0, 60));
            
            // QC#30566
            ZYPEZDItemValueSetter.setValue(pMsg.shipToScdLineAddr, lineAddr.substring(60, 120));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToThirdLineAddr,lineAddr.substring(120, 180));
            if (lineAddrLength < 241) {
                ZYPEZDItemValueSetter.setValue(pMsg.shipToFrthLineAddr, lineAddr.substring(180, lineAddrLength));
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.shipToFrthLineAddr, lineAddr.substring(180, 240));
            }
            // END   2019/02/26 T.Ogura [QC#30518,MOD]
        }
        // START 2017/09/04 S.Katsuma [QC#20688,DEL]
//        pMsg.shipToFirstRefCmntTxt.clear();
//        pMsg.shipToScdRefCmntTxt.clear();
        // END 2017/09/04 S.Katsuma [QC#20688,DEL]
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCtyAddr, sMsg.shipToCtyAddr_ST);
        ZYPEZDItemValueSetter.setValue(pMsg.shipToStCd, sMsg.shipToStCd_ST);
        ZYPEZDItemValueSetter.setValue(pMsg.shipToProvNm, sMsg.shipToProvNm_ST);
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCntyNm, sMsg.shipToCntyNm_ST);
        ZYPEZDItemValueSetter.setValue(pMsg.shipToPostCd, sMsg.shipToPostCd_ST);
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCtryCd, sMsg.shipToCtryCd_ST);
        // START 2017/09/04 S.Katsuma [QC#20688,ADD]
        if (ZYPCommonFunc.hasValue(sMsg.ctacPsnNm)) {
            ZYPEZDItemValueSetter.setValue(pMsg.ctacPsnNm, sMsg.ctacPsnNm);
        } else {
            pMsg.ctacPsnNm.clear();
        }
        // END 2017/09/04 S.Katsuma [QC#20688,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.rtrnShipToRtlWhCd, sMsg.rtrnShipToRtlWhCd_RW);
        // START 2017/09/04 S.Katsuma [QC#20688,DEL]
//        pMsg.shipFromSoNumListTxt.clear();
        // END 2017/09/04 S.Katsuma [QC#20688,DEL]
        ZYPEZDItemValueSetter.setValue(pMsg.carrCd, sMsg.carrCd);
        ZYPEZDItemValueSetter.setValue(pMsg.carrAcctNum, sMsg.carrAcctNum);
        if (NPZC104001Constant.EVENT_SUBMIT.equals(eventId)) {
            // QC#17487
            // Set Service Level to Ground if target WH is included in specified WH owners.
            if (NPAL1500CommonLogic.isSvcLvlWhOwnr(sMsg)) {
                ZYPEZDItemValueSetter.setValue(sMsg.shpgSvcLvlCd, SHPG_SVC_LVL.GROUND_SERVICE);
            }
        }
        ZYPEZDItemValueSetter.setValue(pMsg.shpgSvcLvlCd, sMsg.shpgSvcLvlCd);
        // START 2017/09/04 S.Katsuma [QC#20688,ADD]
//        pMsg.frtChrgToCd.clear();
//        pMsg.frtChrgMethCd.clear();
        if (ZYPCommonFunc.hasValue(sMsg.lineBizTpCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.lineBizTpCd, sMsg.lineBizTpCd);
        } else {
            pMsg.lineBizTpCd.clear();
        }
        // END 2017/09/04 S.Katsuma [QC#20688,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.poOrdCmntTxt, sMsg.poOrdCmntTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.trsmtMethTpCd, sMsg.trsmtMethTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.sendPoFaxNum, sMsg.sendPoFaxNum);
        ZYPEZDItemValueSetter.setValue(pMsg.sendPoEmlAddr, sMsg.sendPoEmlAddr);
        ZYPEZDItemValueSetter.setValue(pMsg.dsctnInd, ZYPConstant.FLG_ON_Y);
        // START 2021/04/23 [QC#58645,MOD]
        // START 2019/08/09 T.Ogura [QC#52359,MOD]
//        if (eventId.equals(NPZC104001Constant.EVENT_SUBMIT) && (!ZYPCommonFunc.hasValue(cMsg.poStsCd_H) || !PO_STS.WAITING_FOR_APPROVAL.equals(cMsg.poStsCd_H))) {
//        if (!NPAL1500CommonLogic.isPreApprovedWhOwnr(sMsg) && eventId.equals(NPZC104001Constant.EVENT_SUBMIT) && (!ZYPCommonFunc.hasValue(cMsg.poStsCd_H) || !PO_STS.WAITING_FOR_APPROVAL.equals(cMsg.poStsCd_H))) {
        // END   2019/08/09 T.Ogura [QC#52359,MOD]
        if (!NPAL1500CommonLogic.isPreApprovedWhOwnr(sMsg) && eventId.equals(NPZC104001Constant.EVENT_SUBMIT) && (!ZYPCommonFunc.hasValue(cMsg.poStsCd_H) || !PO_STS.WAITING_FOR_APPROVAL.equals(cMsg.poStsCd_H))
                && !NPAL1500CommonLogic.checkAutoApprove(sMsg)) {
        // END   2021/04/23 [QC#58645,MOD]
            ZYPEZDItemValueSetter.setValue(pMsg.wfFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.wfFlg, ZYPConstant.FLG_OFF_N);
        }
        // START 2017/09/04 S.Katsuma [QC#20688,ADD]
        if (ZYPCommonFunc.hasValue(sMsg.eipRptRqstPk)) {
            ZYPEZDItemValueSetter.setValue(pMsg.eipRptRqstPk, sMsg.eipRptRqstPk);
        } else {
            pMsg.eipRptRqstPk.clear();
        }
        // END 2017/09/04 S.Katsuma [QC#20688,ADD]

        // po header message info
        int poMsgValidCount = 0;

        for (int i = 0; i < sMsg.Q.length(); i++) {
            // 02: Internal Message

            ZYPEZDItemValueSetter.setValue(pMsg.poInfo.no(poMsgValidCount).poMsgPk, sMsg.Q.no(i).poMsgPk);
            ZYPEZDItemValueSetter.setValue(pMsg.poInfo.no(poMsgValidCount).poMsgTpCd, PO_MSG_TP_INTERNAL_MESSAGE);
            ZYPEZDItemValueSetter.setValue(pMsg.poInfo.no(poMsgValidCount).poMsgSubmtPsnCd, sMsg.Q.no(i).poMsgSubmtPsnCd);
            ZYPEZDItemValueSetter.setValue(pMsg.poInfo.no(poMsgValidCount).xxDsMultMsgDplyTxt, sMsg.Q.no(i).poMsgTxt);
            pMsg.poInfo.no(poMsgValidCount).prchReqNum.clear();
            pMsg.poInfo.no(poMsgValidCount).prchReqLineNum.clear();
            pMsg.poInfo.no(poMsgValidCount).prchReqLineSubNum.clear();

            poMsgValidCount++;
        }
        // Special Instructions
        if (ZYPCommonFunc.hasValue(sMsg.xxDsMultMsgDplyTxt_SI)) {
            setPoHdrMsgInfo(pMsg.poInfo, poMsgValidCount, sMsg, PO_MSG_TP_SPECIAL_INSTRUCTION);
            poMsgValidCount++;
        }
        // Receiver Note
        if (ZYPCommonFunc.hasValue(sMsg.xxDsMultMsgDplyTxt_RN)) {
            setPoHdrMsgInfo(pMsg.poInfo, poMsgValidCount, sMsg, PO_MSG_TP_RECEIVER_NOTE);
            poMsgValidCount++;
        }
        // Shipper Note
        if (ZYPCommonFunc.hasValue(sMsg.xxDsMultMsgDplyTxt_SN)) {
            setPoHdrMsgInfo(pMsg.poInfo, poMsgValidCount, sMsg, PO_MSG_TP_SHIPPER_NOTE);
            poMsgValidCount++;
        }
        pMsg.poInfo.setValidCount(poMsgValidCount);

        // po line info
        int idxTotalSerial = 0;
        int idxTotalSvcMach = 0;
        int idxTotalAcc = 0;
        int validCnt = 0;

        for (int idxPoDetail = 0; idxPoDetail < sMsg.A.getValidCount(); idxPoDetail++) {
            for (int j = 0; j < ordDtlLineList.size(); j++) {
                if (sMsg.A.no(idxPoDetail).dispPoDtlLineNum_A1.getValue().equals(ordDtlLineList.get(j).toPlainString())) {
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).poOrdDtlLineNum, ZYPCommonFunc.leftPad(String.valueOf(j + 1), 3, "0"));
                    break;
                }
            }

            // START 2017/09/04 S.Katsuma [QC#20688,ADD]
            if (idxPoDetail == 0) {
                if (ZYPCommonFunc.hasValue(sMsg.A.no(idxPoDetail).shipToAcctNm_A1)) {
                    ZYPEZDItemValueSetter.setValue(pMsg.shipToAcctNm, sMsg.A.no(idxPoDetail).shipToAcctNm_A1);
                } else {
                    pMsg.shipToAcctNm.clear();
                }
                if (ZYPCommonFunc.hasValue(sMsg.A.no(idxPoDetail).shipToFirstRefCmntTxt_A1)) {
                    ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstRefCmntTxt, sMsg.A.no(idxPoDetail).shipToFirstRefCmntTxt_A1);
                } else {
                    pMsg.shipToFirstRefCmntTxt.clear();
                }
                if (ZYPCommonFunc.hasValue(sMsg.A.no(idxPoDetail).shipToScdRefCmntTxt_A1)) {
                    ZYPEZDItemValueSetter.setValue(pMsg.shipToScdRefCmntTxt, sMsg.A.no(idxPoDetail).shipToScdRefCmntTxt_A1);
                } else {
                    pMsg.shipToScdRefCmntTxt.clear();
                }
                if (ZYPCommonFunc.hasValue(sMsg.A.no(idxPoDetail).shipFromSoNumListTxt_A1)) {
                    ZYPEZDItemValueSetter.setValue(pMsg.shipFromSoNumListTxt, sMsg.A.no(idxPoDetail).shipFromSoNumListTxt_A1);
                } else {
                    pMsg.shipFromSoNumListTxt.clear();
                }
                if (ZYPCommonFunc.hasValue(sMsg.A.no(idxPoDetail).frtChrgToCd_A1)) {
                    ZYPEZDItemValueSetter.setValue(pMsg.frtChrgToCd, sMsg.A.no(idxPoDetail).frtChrgToCd_A1);
                } else {
                    pMsg.frtChrgToCd.clear();
                }
                if (ZYPCommonFunc.hasValue(sMsg.A.no(idxPoDetail).frtChrgMethCd_A1)) {
                    ZYPEZDItemValueSetter.setValue(pMsg.frtChrgMethCd, sMsg.A.no(idxPoDetail).frtChrgMethCd_A1);
                } else {
                    pMsg.frtChrgMethCd.clear();
                }
            }
            // END 2017/09/04 S.Katsuma [QC#20688,ADD]
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).dispPoDtlLineNum, sMsg.A.no(idxPoDetail).dispPoDtlLineNum_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).poLineTpCd, sMsg.A.no(idxPoDetail).poLineTpCd_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).poMdseCmpsnTpCd, sMsg.A.no(idxPoDetail).poMdseCmpsnTpCd_HD);
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).setPoOrdDtlLineNum, sMsg.A.no(idxPoDetail).setPoOrdDtlLineNum);
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).mdseCd, sMsg.A.no(idxPoDetail).mdseCd_A1);
            String mdseNm = getMdseNm(glblCmpyCd, sMsg.A.no(idxPoDetail).mdseCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).mdseNm, mdseNm);
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).mdseDescShortTxt, sMsg.A.no(idxPoDetail).mdseDescShortTxt_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).poQty, sMsg.A.no(idxPoDetail).poQty_HD);
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).poDispQty, sMsg.A.no(idxPoDetail).poDispQty_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).poInvQty, sMsg.A.no(idxPoDetail).poInvQty_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).poDispUomCd, sMsg.A.no(idxPoDetail).poDispUomCd_A1);
            //QC#18420 Add Start
            //QC#26893 Del Start
            //NPAL1500CommonLogic.calcLinePrice(cMsg, sMsg, idxPoDetail);
            //QC#26893 Del End
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).poDtlDiscPct, sMsg.A.no(idxPoDetail).poDtlDiscPct_A1);
            //QC#18420 Add End 
            // QC#53392 2019/10/05 Add Start
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).poDtlDiscPrcAmt, sMsg.A.no(idxPoDetail).poDtlDiscPrcAmt_A1);
            // QC#53392 2019/10/05 Add End
            //QC#27474 Add Start
            if (ZYPCommonFunc.hasValue(sMsg.A.no(idxPoDetail).entPoDtlDealNetAmt_A1)) {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).thisMthFobCostAmt, sMsg.A.no(idxPoDetail).entPoDtlDealNetAmt_A1.getValue().divide(sMsg.A.no(idxPoDetail).poQty_HD.getValue(), 2, BigDecimal.ROUND_HALF_UP));
            }
            //QC#27474 Add End
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).entDealNetUnitPrcAmt, sMsg.A.no(idxPoDetail).entDealNetUnitPrcAmt_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).entPoDtlDealNetAmt, sMsg.A.no(idxPoDetail).entPoDtlDealNetAmt_A1);
            // START 2017/09/04 S.Katsuma [QC#20688,ADD]
            if (ZYPCommonFunc.hasValue(sMsg.A.no(idxPoDetail).entFuncNetUnitPrcAmt_A1)) {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).entFuncNetUnitPrcAmt, sMsg.A.no(idxPoDetail).entFuncNetUnitPrcAmt_A1);
            } else {
                pMsg.poLineInfo.no(idxPoDetail).entFuncNetUnitPrcAmt.clear();
            }
            if (ZYPCommonFunc.hasValue(sMsg.A.no(idxPoDetail).entPoDtlFuncNetAmt_A1)) {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).entPoDtlFuncNetAmt, sMsg.A.no(idxPoDetail).entPoDtlFuncNetAmt_A1);
            } else {
                pMsg.poLineInfo.no(idxPoDetail).entPoDtlFuncNetAmt.clear();
            }
            if (ZYPCommonFunc.hasValue(sMsg.A.no(idxPoDetail).exchRate_A1)) {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).exchRate, sMsg.A.no(idxPoDetail).exchRate_A1);
            } else {
                pMsg.poLineInfo.no(idxPoDetail).exchRate.clear();
            }
            if (ZYPCommonFunc.hasValue(sMsg.A.no(idxPoDetail).custUomCd_A1)) {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).custUomCd, sMsg.A.no(idxPoDetail).custUomCd_A1);
            } else {
                pMsg.poLineInfo.no(idxPoDetail).custUomCd.clear();
            }
            // END 2017/09/04 S.Katsuma [QC#20688,ADD]
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).destRtlSwhCd, sMsg.A.no(idxPoDetail).destRtlSwhCd_A1);
            if (DS_PO_TP.BUYBACK_PO.equals(sMsg.dsPoTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).srcRtlSwhCd, sMsg.A.no(idxPoDetail).srcRtlSwhCd_A1);
            } else {
                pMsg.poLineInfo.no(idxPoDetail).srcRtlSwhCd.clear();
            }
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).invtyLocCd, sMsg.destRtlWhCd_DS.getValue() + pMsg.poLineInfo.no(idxPoDetail).destRtlSwhCd.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).rqstRcvDt, sMsg.A.no(idxPoDetail).rqstRcvDt_A1);
            // START 2023/02/14 S.Dong [QC#60966, ADD]
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).rqstShipDt, sMsg.A.no(idxPoDetail).rqstShipDt_A1);
            // END 2023/02/14 S.Dong [QC#60966, ADD]
            // START 2017/09/04 S.Katsuma [QC#20688,ADD]
            if (ZYPCommonFunc.hasValue(sMsg.rqstRcvTm)) {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).rqstRcvTm, sMsg.rqstRcvTm);
            } else {
                pMsg.poLineInfo.no(idxPoDetail).rqstRcvTm.clear();
            }
            // END 2017/09/04 S.Katsuma [QC#20688,ADD]
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).frtCondCd, sMsg.frtCondCd);
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).origMdseCd, sMsg.A.no(idxPoDetail).mdseCd_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).fromStkStsCd, sMsg.A.no(idxPoDetail).stkStsCd_A1);
            // QC#28729 Update. Delete status close check condition.
            if (!ZYPCommonFunc.hasValue( sMsg.A.no(idxPoDetail).stkStsCd_A1)) {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).toStkStsCd, STK_STS.GOOD);
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).toStkStsCd, sMsg.A.no(idxPoDetail).stkStsCd_A1);
            }
            // START 2017/09/04 S.Katsuma [QC#20688,ADD]
            if (ZYPCommonFunc.hasValue(sMsg.A.no(idxPoDetail).adminPsnCd_A1)) {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).adminPsnCd, sMsg.A.no(idxPoDetail).adminPsnCd_A1);
            } else {
                pMsg.poLineInfo.no(idxPoDetail).adminPsnCd.clear();
            }
            // END 2017/09/04 S.Katsuma [QC#20688,ADD]
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).poMatchTpCd, sMsg.A.no(idxPoDetail).poMatchTpCd_A1);
            // START 2017/09/04 S.Katsuma [QC#20688,ADD]
            if (ZYPCommonFunc.hasValue(sMsg.A.no(idxPoDetail).ordQty_A1)) {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).ordQty, sMsg.A.no(idxPoDetail).ordQty_A1);
            } else {
                pMsg.poLineInfo.no(idxPoDetail).ordQty.clear();
            }
            if (ZYPCommonFunc.hasValue(sMsg.A.no(idxPoDetail).cpoOrdNum_A1)) {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).cpoOrdNum, sMsg.A.no(idxPoDetail).cpoOrdNum_A1);
            } else {
                pMsg.poLineInfo.no(idxPoDetail).cpoOrdNum.clear();
            }
            if (ZYPCommonFunc.hasValue(sMsg.A.no(idxPoDetail).cpoDtlLineNum_A1)) {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).cpoDtlLineNum, sMsg.A.no(idxPoDetail).cpoDtlLineNum_A1);
            } else {
                pMsg.poLineInfo.no(idxPoDetail).cpoDtlLineNum.clear();
            }
            if (ZYPCommonFunc.hasValue(sMsg.A.no(idxPoDetail).cpoDtlLineSubNum_A1)) {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).cpoDtlLineSubNum, sMsg.A.no(idxPoDetail).cpoDtlLineSubNum_A1);
            } else {
                pMsg.poLineInfo.no(idxPoDetail).cpoDtlLineSubNum.clear();
            }
            if (ZYPCommonFunc.hasValue(sMsg.A.no(idxPoDetail).custIssPoNum_A1)) {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).custIssPoNum, sMsg.A.no(idxPoDetail).custIssPoNum_A1);
            } else {
                pMsg.poLineInfo.no(idxPoDetail).custIssPoNum.clear();
            }
            if (ZYPCommonFunc.hasValue(sMsg.A.no(idxPoDetail).custIssPoDt_A1)) {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).custIssPoDt, sMsg.A.no(idxPoDetail).custIssPoDt_A1);
            } else {
                pMsg.poLineInfo.no(idxPoDetail).custIssPoDt.clear();
            }
            if (ZYPCommonFunc.hasValue(sMsg.A.no(idxPoDetail).cpoOrdTpCd_A1)) {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).cpoOrdTpCd, sMsg.A.no(idxPoDetail).cpoOrdTpCd_A1);
            } else {
                pMsg.poLineInfo.no(idxPoDetail).cpoOrdTpCd.clear();
            }
            // END 2017/09/04 S.Katsuma [QC#20688,ADD]
            if (ZYPCommonFunc.hasValue(sMsg.billToCustCd_BT)) {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).billToCustCd, sMsg.billToCustCd_BT);
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).billToCustCd, cMsg.billToCustCd_BT);
            }
            // START 2017/09/04 S.Katsuma [QC#20688,ADD]
            if (ZYPCommonFunc.hasValue(sMsg.A.no(idxPoDetail).sellToCustCd_A1)) {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).sellToCustCd, sMsg.A.no(idxPoDetail).sellToCustCd_A1);
            } else {
                pMsg.poLineInfo.no(idxPoDetail).sellToCustCd.clear();
            }
            if (ZYPCommonFunc.hasValue(sMsg.A.no(idxPoDetail).shpgPlnNum_A1)) {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).shpgPlnNum, sMsg.A.no(idxPoDetail).shpgPlnNum_A1);
            } else {
                pMsg.poLineInfo.no(idxPoDetail).shpgPlnNum.clear();
            }
            // END 2017/09/04 S.Katsuma [QC#20688,ADD]
            // START 2019/08/20 M.Naito [QC#52857,ADD]
            PO_DTLTMsg poDtlTMsg = new PO_DTLTMsg();
            poDtlTMsg.glblCmpyCd.setValue(sMsg.glblCmpyCd.getValue());
            poDtlTMsg.poOrdNum.setValue(sMsg.poNum.getValue());
            poDtlTMsg.poOrdDtlLineNum.setValue(sMsg.A.no(idxPoDetail).poOrdDtlLineNum_A1.getValue());
            poDtlTMsg = (PO_DTLTMsg) EZDTBLAccessor.findByKey(poDtlTMsg);
            if (poDtlTMsg != null && ZYPCommonFunc.hasValue(poDtlTMsg.prchReqNum)) {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).prchReqNum, poDtlTMsg.prchReqNum.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).prchReqNum, sMsg.A.no(idxPoDetail).prchReqNum_A1);
            }
            if (poDtlTMsg != null && ZYPCommonFunc.hasValue(poDtlTMsg.prchReqLineNum)) {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).prchReqLineNum, poDtlTMsg.prchReqLineNum.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).prchReqLineNum, sMsg.A.no(idxPoDetail).prchReqLineNum_A1);
            }
            if (poDtlTMsg != null && ZYPCommonFunc.hasValue(poDtlTMsg.prchReqLineSubNum)) {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).prchReqLineSubNum, poDtlTMsg.prchReqLineSubNum.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).prchReqLineSubNum, sMsg.A.no(idxPoDetail).prchReqLineSubNum);
            }
            // END 2019/08/20 M.Naito [QC#52857,ADD]
            // START 2017/09/04 S.Katsuma [QC#20688,ADD]
            if (ZYPCommonFunc.hasValue(sMsg.trxRefNum)) {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).trxRefNum, sMsg.trxRefNum);
            } else {
                pMsg.poLineInfo.no(idxPoDetail).trxRefNum.clear();
            }
            if (ZYPCommonFunc.hasValue(sMsg.A.no(idxPoDetail).trxRefLineNum_A1)) {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).trxRefLineNum, sMsg.A.no(idxPoDetail).trxRefLineNum_A1);
            } else {
                pMsg.poLineInfo.no(idxPoDetail).trxRefLineNum.clear();
            }
            if (ZYPCommonFunc.hasValue(sMsg.A.no(idxPoDetail).trxRefLineSubNum_A1)) {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).trxRefLineSubNum, sMsg.A.no(idxPoDetail).trxRefLineSubNum_A1);
            } else {
                pMsg.poLineInfo.no(idxPoDetail).trxRefLineSubNum.clear();
            }
            // END 2017/09/04 S.Katsuma [QC#20688,ADD]
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).aslDtlPk, sMsg.A.no(idxPoDetail).aslDtlPk_HD);
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).aslMdseCd, sMsg.A.no(idxPoDetail).aslMdseCd_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).aslUnitPrcAmt, sMsg.A.no(idxPoDetail).aslUnitPrcAmt_HD);
            // START 2017/09/04 S.Katsuma [QC#20688,ADD]
            if (ZYPCommonFunc.hasValue(sMsg.A.no(idxPoDetail).shipFromSoNumListTxt_A1)) {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).shipFromSoNumListTxt, sMsg.A.no(idxPoDetail).shipFromSoNumListTxt_A1);
            } else {
                pMsg.poLineInfo.no(idxPoDetail).shipFromSoNumListTxt.clear();
            }
            // END 2017/09/04 S.Katsuma [QC#20688,ADD]
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).vndInvtyLocCd, sMsg.A.no(idxPoDetail).vndInvtyLocCd_A1);
            // START 2017/09/04 S.Katsuma [QC#20688,ADD]
            if (ZYPCommonFunc.hasValue(sMsg.A.no(idxPoDetail).vndIssPoOrdNum_A1)) {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).vndIssPoOrdNum, sMsg.A.no(idxPoDetail).vndIssPoOrdNum_A1);
            } else {
                pMsg.poLineInfo.no(idxPoDetail).vndIssPoOrdNum.clear();
            }
            if (ZYPCommonFunc.hasValue(sMsg.A.no(idxPoDetail).proNum_A1)) {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).proNum, sMsg.A.no(idxPoDetail).proNum_A1);
            } else {
                pMsg.poLineInfo.no(idxPoDetail).proNum.clear();
            }
            // END 2017/09/04 S.Katsuma [QC#20688,ADD]
            //QC#19485
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).vndPoAckStsCd, sMsg.A.no(idxPoDetail).vndPoAckStsCd);
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).poSendTs, sMsg.A.no(idxPoDetail).poSendTs);
            //QC#19485
            // START 2017/09/04 S.Katsuma [QC#20688,ADD]
            if (ZYPCommonFunc.hasValue(sMsg.A.no(idxPoDetail).origPoOrdNum_A1)) {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).origPoOrdNum, sMsg.A.no(idxPoDetail).origPoOrdNum_A1);
            } else {
                pMsg.poLineInfo.no(idxPoDetail).origPoOrdNum.clear();
            }
            if (ZYPCommonFunc.hasValue(sMsg.A.no(idxPoDetail).origPoOrdDtlLineNum_A1)) {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).origPoOrdDtlLineNum, sMsg.A.no(idxPoDetail).origPoOrdDtlLineNum_A1);
            } else {
                pMsg.poLineInfo.no(idxPoDetail).origPoOrdDtlLineNum.clear();
            }
            if (ZYPCommonFunc.hasValue(sMsg.A.no(idxPoDetail).origDispPoDtlLineNum_A1)) {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).origDispPoDtlLineNum, sMsg.A.no(idxPoDetail).origDispPoDtlLineNum_A1);
            } else {
                pMsg.poLineInfo.no(idxPoDetail).origDispPoDtlLineNum.clear();
            }
            // END 2017/09/04 S.Katsuma [QC#20688,ADD]
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).svcConfigMstrPk, sMsg.A.no(idxPoDetail).svcConfigMstrPk_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).poOrdDtlCmntTxt, sMsg.A.no(idxPoDetail).poOrdDtlCmntTxt_A1);

            // serNumList
            if (ZYPCommonFunc.hasValue(sMsg.A.no(idxPoDetail).serNumListTxt_A1)) {
                idxTotalSerial = setSerNumListDetail(sMsg, pMsg, idxTotalSerial, idxPoDetail);
            }

            if (ZYPCommonFunc.hasValue(sMsg.A.no(idxPoDetail).svcMachMstrPk_IB)) {
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrList.no(idxTotalSvcMach).poOrdDtlLineNum, sMsg.A.no(idxPoDetail).poOrdDtlLineNum_A1);
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrList.no(idxTotalSvcMach).svcMachMstrPk, sMsg.A.no(idxPoDetail).svcMachMstrPk_IB);
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrList.no(idxTotalSvcMach).svcConfigMstrPk, sMsg.A.no(idxPoDetail).svcConfigMstrPk_A1);
                idxTotalSvcMach++;
            }

            boolean chAccountRegFlg = false;
            // QC#15817 Delete.
//            boolean vaAccountRegFlg = false;

            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(idxPoDetail).coaEnblFlg_CH.getValue())) {
                chAccountRegFlg = true;
            }
            // QC#15817 Delete.
//            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(idxPoDetail).coaEnblFlg_VA.getValue())) {
//                vaAccountRegFlg = true;
//            }
//
//            // poAcctInfo PO_ACCT_TP 02:Accrual
//            String accrualAcc = sMsg.A.no(idxPoDetail).xxScrItem130Txt_AC.getValue();
//            if (ZYPCommonFunc.hasValue(accrualAcc)) {
//                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(idxTotalAcc).poOrdDtlLineNum, sMsg.A.no(idxPoDetail).poOrdDtlLineNum_A1);
//                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(idxTotalAcc).poAcctTpCd, PO_ACCT_TP.ACCRUAL);
//                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(idxTotalAcc).coaCmpyCd, sMsg.A.no(idxPoDetail).coaCmpyCd_AC);
//                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(idxTotalAcc).coaAfflCd, sMsg.A.no(idxPoDetail).coaAfflCd_AC);
//                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(idxTotalAcc).coaBrCd, sMsg.A.no(idxPoDetail).coaBrCd_AC);
//                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(idxTotalAcc).coaChCd, sMsg.A.no(idxPoDetail).coaChCd_AC);
//                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(idxTotalAcc).coaCcCd, sMsg.A.no(idxPoDetail).coaCcCd_AC);
//                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(idxTotalAcc).coaAcctCd, sMsg.A.no(idxPoDetail).coaAcctCd_AC);
//                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(idxTotalAcc).coaProdCd, sMsg.A.no(idxPoDetail).coaProdCd_AC);
//                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(idxTotalAcc).coaProjCd, sMsg.A.no(idxPoDetail).coaProjCd_AC);
//                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(idxTotalAcc).coaExtnCd, sMsg.A.no(idxPoDetail).coaExtnCd_AC);
//
//                idxTotalAcc++;
//            }
            // poAcctInfo PO_ACCT_TP 01:Charge
            String chargeAcc = sMsg.A.no(idxPoDetail).xxScrItem130Txt_CH.getValue();
            if (ZYPCommonFunc.hasValue(chargeAcc) && chAccountRegFlg) {
                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(idxTotalAcc).poOrdDtlLineNum, sMsg.A.no(idxPoDetail).poOrdDtlLineNum_A1);
                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(idxTotalAcc).poAcctTpCd, PO_ACCT_TP.CHARGE);
                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(idxTotalAcc).coaCmpyCd, sMsg.A.no(idxPoDetail).splyCoaCmpyCd);
                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(idxTotalAcc).coaAfflCd, sMsg.A.no(idxPoDetail).splyCoaAfflCd);
                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(idxTotalAcc).coaBrCd, sMsg.A.no(idxPoDetail).splyCoaBrCd);
                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(idxTotalAcc).coaChCd, sMsg.A.no(idxPoDetail).splyCoaChCd);
                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(idxTotalAcc).coaCcCd, sMsg.A.no(idxPoDetail).splyCoaCcCd);
                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(idxTotalAcc).coaAcctCd, sMsg.A.no(idxPoDetail).splyCoaAcctCd);
                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(idxTotalAcc).coaProdCd, sMsg.A.no(idxPoDetail).splyCoaProdCd);
                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(idxTotalAcc).coaProjCd, sMsg.A.no(idxPoDetail).splyCoaProjCd);
                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(idxTotalAcc).coaExtnCd, sMsg.A.no(idxPoDetail).splyCoaExtnCd);

                idxTotalAcc++;
            }
            // QC#15817 Delete.
            // poAcctInfo PO_ACCT_TP 03:Variance
//            String varianceAcc = sMsg.A.no(idxPoDetail).xxScrItem130Txt_VA.getValue();
//            if (ZYPCommonFunc.hasValue(varianceAcc) && vaAccountRegFlg) {
//                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(idxTotalAcc).poOrdDtlLineNum, sMsg.A.no(idxPoDetail).poOrdDtlLineNum_A1);
//                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(idxTotalAcc).poAcctTpCd, PO_ACCT_TP.VARIANCE);
//                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(idxTotalAcc).coaCmpyCd, sMsg.A.no(idxPoDetail).coaCmpyCd_VA);
//                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(idxTotalAcc).coaAfflCd, sMsg.A.no(idxPoDetail).coaAfflCd_VA);
//                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(idxTotalAcc).coaBrCd, sMsg.A.no(idxPoDetail).coaBrCd_VA);
//                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(idxTotalAcc).coaChCd, sMsg.A.no(idxPoDetail).coaChCd_VA);
//                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(idxTotalAcc).coaCcCd, sMsg.A.no(idxPoDetail).coaCcCd_VA);
//                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(idxTotalAcc).coaAcctCd, sMsg.A.no(idxPoDetail).coaAcctCd_VA);
//                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(idxTotalAcc).coaProdCd, sMsg.A.no(idxPoDetail).coaProdCd_VA);
//                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(idxTotalAcc).coaProjCd, sMsg.A.no(idxPoDetail).coaProjCd_VA);
//                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(idxTotalAcc).coaExtnCd, sMsg.A.no(idxPoDetail).coaExtnCd_VA);
//
//                idxTotalAcc++;
//            }

            // 2019/09/27 QC#52460 Add Start
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(idxPoDetail).poLineStsCd, sMsg.A.no(idxPoDetail).poLineStsCd_A1);
            // 2019/09/27 QC#52460 Add End

            validCnt++;
        }
        pMsg.poLineInfo.setValidCount(validCnt);
        pMsg.serNumList.setValidCount(idxTotalSerial);
        pMsg.svcMachMstrList.setValidCount(idxTotalSvcMach);
        pMsg.poAcctInfo.setValidCount(idxTotalAcc);
        return pMsg;
    }

    /**
     * setSerNumListDetail
     * @param sMsg NPAL1500SMsg
     * @param pMsg NPZC104001PMsg
     * @param idxTotalSerial int
     * @param idxPoDetail int
     * @return int idxTotalSerial
     */
    private static int setSerNumListDetail(NPAL1500SMsg sMsg, NPZC104001PMsg pMsg, int idxTotalSerial, int idxPoDetail) {
        String serialNo = sMsg.A.no(idxPoDetail).serNumListTxt_A1.getValue();
        // divide by ","
        String[] serialNoArray = serialNo.split(",");
        String[] serNumPkArray = sMsg.A.no(idxPoDetail).serNumListTxt_A2.getValue().split(",");
        for (int idxSerial = 0; idxSerial < serialNoArray.length; idxSerial++) {
            ZYPEZDItemValueSetter.setValue(pMsg.serNumList.no(idxTotalSerial).poOrdDtlLineNum, sMsg.A.no(idxPoDetail).poOrdDtlLineNum_A1);

            if (idxSerial < serNumPkArray.length && ZYPCommonFunc.hasValue(serNumPkArray[idxSerial])) {
                ZYPEZDItemValueSetter.setValue(pMsg.serNumList.no(idxTotalSerial).poSerNumPk, new BigDecimal(serNumPkArray[idxSerial]));
            } else {
                pMsg.serNumList.no(idxTotalSerial).poSerNumPk.clear();
            }

            ZYPEZDItemValueSetter.setValue(pMsg.serNumList.no(idxTotalSerial).serNum, serialNoArray[idxSerial]);
            ZYPEZDItemValueSetter.setValue(pMsg.serNumList.no(idxTotalSerial).mdseCd , sMsg.A.no(idxPoDetail).mdseCd_A1);    // 03/02/2020 T.Ogura [QC#55920,ADD]
            idxTotalSerial++;
        }
        return idxTotalSerial;
    }

    /**
     * getMdseNm
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return String
     */
    private static String getMdseNm(String glblCmpyCd, String mdseCd) {

        String mdseNm = "";

        MDSETMsg mdseTMsg = new MDSETMsg();

        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, mdseCd);

        mdseTMsg = (MDSETMsg) EZDTBLAccessor.findByKey(mdseTMsg);
        mdseNm = mdseTMsg.mdseNm.getValue();

        return mdseNm;
    }

    /**
     * setPoHdrMsgInfo
     * @param pMsgArray NPZC104001_poInfoPMsgArray
     * @param idx int
     * @param sMsg NPAL1500SMsg
     * @param poMsgTpCd String
     * @return NPZC104001_poInfoPMsgArray
     */
    private static NPZC104001_poInfoPMsgArray setPoHdrMsgInfo(NPZC104001_poInfoPMsgArray pMsgArray, int idx, NPAL1500SMsg sMsg, String poMsgTpCd) {

        EZDSBigDecimalItem poMsgPk = new EZDSBigDecimalItem();
        EZDSStringItem poMsgSubmtPsnCd = new EZDSStringItem();
        EZDSStringItem xxDsMultMsgDplyTxt = new EZDSStringItem();

        if (PO_MSG_TP_SPECIAL_INSTRUCTION.equals(poMsgTpCd)) {
            poMsgPk = sMsg.poMsgPk_SI;
            poMsgSubmtPsnCd = sMsg.poMsgSubmtPsnCd_SI;
            xxDsMultMsgDplyTxt = sMsg.xxDsMultMsgDplyTxt_SI;
        }
        // Receiver Note
        if (PO_MSG_TP_RECEIVER_NOTE.equals(poMsgTpCd)) {
            poMsgPk = sMsg.poMsgPk_RN;
            poMsgSubmtPsnCd = sMsg.poMsgSubmtPsnCd_RN;
            xxDsMultMsgDplyTxt = sMsg.xxDsMultMsgDplyTxt_RN;
        }
        // Shipper Note
        if (PO_MSG_TP_SHIPPER_NOTE.equals(poMsgTpCd)) {
            poMsgPk = sMsg.poMsgPk_SN;
            poMsgSubmtPsnCd = sMsg.poMsgSubmtPsnCd_SN;
            xxDsMultMsgDplyTxt = sMsg.xxDsMultMsgDplyTxt_SN;
        }

        ZYPEZDItemValueSetter.setValue(pMsgArray.no(idx).poMsgPk, poMsgPk);
        ZYPEZDItemValueSetter.setValue(pMsgArray.no(idx).poMsgTpCd, poMsgTpCd);
        ZYPEZDItemValueSetter.setValue(pMsgArray.no(idx).poMsgSubmtPsnCd, poMsgSubmtPsnCd);
        ZYPEZDItemValueSetter.setValue(pMsgArray.no(idx).xxDsMultMsgDplyTxt, xxDsMultMsgDplyTxt);
        pMsgArray.no(idx).prchReqNum.clear();
        pMsgArray.no(idx).prchReqLineNum.clear();
        pMsgArray.no(idx).prchReqLineSubNum.clear();

        return pMsgArray;
    }
    /**
     * Is technician warehouse
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return boolean true:Technician warehouse / false: Not Technician warehouse
     */
    private static boolean isTechnicianWh(String glblCmpyCd, String rtlWhCd) {

        S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().chkTechnicianWh(glblCmpyCd, rtlWhCd);

        if (ssmResult.getQueryResultCount() > 0) {
            // Technician warehouse
            return true;
        } else {
            // Not Technician warehouse
            return false;
        }
    }
    /**
     * getDsPoTpNm
     * @param glblCmpyCd String
     * @param dsPoTpCd String
     * @return String
     */
    private static String getDsPoTpNm(String glblCmpyCd, String dsPoTpCd) {

        String dsPoTpNm = "";

        DS_PO_TPTMsg dsPoTpTMsg = new DS_PO_TPTMsg();

        ZYPEZDItemValueSetter.setValue(dsPoTpTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsPoTpTMsg.dsPoTpCd, dsPoTpCd);

        dsPoTpTMsg = (DS_PO_TPTMsg) EZDTBLAccessor.findByKey(dsPoTpTMsg);
        dsPoTpNm = dsPoTpTMsg.dsPoTpNm.getValue();

        return dsPoTpNm;
    }

    /**
     * isDropShip
     * @param cMsg NPAL1500CMsg
     * @return boolean
     */
    private static boolean isDropShip(NPAL1500CMsg cMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.varCharConstVal_VD) //
                && cMsg.varCharConstVal_VD.getValue().equals(cMsg.poQlfyCd.getValue())) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * lockShpgPln
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     * @return boolean true : normal end, false : abnormal end
     */
    private static boolean lockShpgPln(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg) {

        if (!isDropShip(cMsg)) {
            return true;
        }

        for (int idxPoDetail = 0; idxPoDetail < sMsg.A.getValidCount(); idxPoDetail++) {
            if (ZYPCommonFunc.hasValue(sMsg.A.no(idxPoDetail).poLineStsCd_A1) && !PO_LINE_STS.CANCELLED.equals(sMsg.A.no(idxPoDetail).poLineStsCd_A1.getValue()) && !PO_LINE_TP.ITT.equals(sMsg.A.no(idxPoDetail).poLineTpCd_A1.getValue())) {

                SHPG_PLNTMsg shpgPlnPoDtlTMsg = findShpgPlnPoDtlByKeyForUpdate(cMsg, sMsg.A.no(idxPoDetail));
                if (shpgPlnPoDtlTMsg == null) {
                    cMsg.setMessageInfo(NPAM0006E);
                    return false;
                }

            }
        }

        return true;
    }

    /**
     * findPoByKeyForUpdate (For Lock)
     * @param cMsg NPAL1500CMsg
     * @return POTMsg
     */
    private static POTMsg findPoByKeyForUpdate(NPAL1500CMsg cMsg) {

        POTMsg poTMsg = new POTMsg();
        poTMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        poTMsg.poOrdNum.setValue(cMsg.poNum.getValue());

        poTMsg = (POTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(poTMsg);
        if (poTMsg == null) {
            return null;
        }

        String findEzUpTime = cMsg.ezUpTime_H1.getValue();
        String findEzUpTimeZone = cMsg.ezUpTimeZone_H1.getValue();
        String currentEzUpTime = poTMsg.ezUpTime.getValue();
        String currentEzUpTimeZone = poTMsg.ezUpTimeZone.getValue();

        if (!ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
            return null;
        }
        return poTMsg;
    }
    /**
     * findPoDtlByKeyForUpdate (For Lock)
     * @param headMsg NPAL1500CMsg
     * @param detailMsg NPAL1500_ASMsg
     * @return PO_DTLTMsg
     */
    private static PO_DTLTMsg findPoDtlByKeyForUpdate(NPAL1500CMsg headMsg, NPAL1500_ASMsg detailMsg) {

        PO_DTLTMsg podtlTMsg = new PO_DTLTMsg();
        podtlTMsg.glblCmpyCd.setValue(headMsg.glblCmpyCd.getValue());
        podtlTMsg.poOrdNum.setValue(headMsg.poNum.getValue());
        podtlTMsg.poOrdDtlLineNum.setValue(detailMsg.poOrdDtlLineNum_A1.getValue());

        podtlTMsg = (PO_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(podtlTMsg);

        if (podtlTMsg == null) {
            return null;
        }

        String findEzUpTime = detailMsg.ezUpTime_D1.getValue();
        String findEzUpTimeZone = detailMsg.ezUpTimeZone_D1.getValue();
        String currentEzUpTime = podtlTMsg.ezUpTime.getValue();
        String currentEzUpTimeZone = podtlTMsg.ezUpTimeZone.getValue();

        if (!ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
            return null;
        }
        return podtlTMsg;
    }
    /**
     * findShpgPlnByKeyForUpdate (For Lock)
     * @param cMsg NPAL1500CMsg
     * @param detailMsg NPAL1500_ASMsg
     * @return SHPG_PLNTMsg
     */
    private static SHPG_PLNTMsg findShpgPlnPoDtlByKeyForUpdate(NPAL1500CMsg cMsg, NPAL1500_ASMsg detailMsg) {

        SHPG_PLNTMsg tMsg = new SHPG_PLNTMsg();
        tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        tMsg.shpgPlnNum.setValue(detailMsg.shpgPlnNum_D3.getValue());

        tMsg = (SHPG_PLNTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);

        if (tMsg == null) {
            return null;
        }

        String findEzUpTime = detailMsg.ezUpTime_D3.getValue();
        String findEzUpTimeZone = detailMsg.ezUpTimeZone_D3.getValue();
        String currentEzUpTime = tMsg.ezUpTime.getValue();
        String currentEzUpTimeZone = tMsg.ezUpTimeZone.getValue();

        if (!ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
            return null;
        }

        return tMsg;
    }

    /**
     * Get Service Machine Master
     * @param glblCmpyCd String
     * @param svcMachMstrPk BigDecimal
     * @param errDplyItem EZDSStringItem
     * @param errDplyMsg String
     * @return SVC_MACH_MSTRTMsg
     */
    private static SVC_MACH_MSTRTMsg getSvcMachMstr(String glblCmpyCd, BigDecimal svcMachMstrPk, EZDSStringItem errDplyItem, String errDplyMsg) {

        SVC_MACH_MSTRTMsg svcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.svcMachMstrPk, svcMachMstrPk);

        try {

            svcMachMstrTMsg = (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(svcMachMstrTMsg);

        } catch (EZDDBRecordLockedException rle) {

            errDplyItem.setErrorInfo(1, "NLBM1087E", new String[] {errDplyMsg });
            return null;

        } catch (EZDDBRetryRequestException rre) {

            errDplyItem.setErrorInfo(1, "NLBM1077E");
            return null;
        }

        if (svcMachMstrTMsg == null) {

            errDplyItem.setErrorInfo(1, NPAM1361E, new String[] {errDplyMsg });
            return null;
        }

        return svcMachMstrTMsg;
    }

    /**
     * Check Service Machine Master
     * @param sMsg NPAL1500SMsg
     * @param index int
     * @return boolean
     */
    private static boolean checkSvcMachMstr(NPAL1500SMsg sMsg, int index) {

        NPAL1500_ASMsg sMsgALine = sMsg.A.no(index);
        // QC#30039
        if(!PO_LINE_STS.OPEN.equals(sMsgALine.poLineStsCd_A1.getValue())) {
            return true;
        }
        String[] serialNoArray = sMsgALine.serNumListTxt_A1.getValue().split(",");

        for (int serIndex = 0; serIndex < serialNoArray.length; serIndex++) {

            if (!serialNoArray[serIndex].trim().equals("") && serialNoArray[serIndex].length() > 0) {

                S21SsmEZDResult ssmRslt = NPAL1500Query.getInstance().getSvcMachMstrForSer(sMsg, serialNoArray[serIndex].trim(), index);

                // The entered @ does not exist in Master.
                if (!ssmRslt.isCodeNormal()) {

                    sMsgALine.serNumListTxt_A1.setErrorInfo(1, "NPAM1361E", new String[] {serialNoArray[serIndex].trim() });
                    return false;
                }

                List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmRslt.getResultObject();

                // Multiple active IB exist
                if (rsltList.size() > 1) {

                    sMsgALine.serNumListTxt_A1.setErrorInfo(1, "NLZM2433E");
                    return false;
                }

                if (!checkMachMstr(sMsg, sMsgALine, rsltList)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * checkMachMstr
     * @param sMsg NPAL1500SMsg
     * @param sMsgALine NPAL1500_ASMsg
     * @param rsltList List<Map<String, Object>>
     * @return boolean
     */
    private static boolean checkMachMstr(NPAL1500SMsg sMsg, NPAL1500_ASMsg sMsgALine, List<Map<String, Object>> rsltList) {
        Map<String, Object> rsltMap = rsltList.get(0);

        String rtlSwhCd = (String) rsltMap.get("RTL_SWH_CD");
        String svcMachMaintAvalFlg = (String) rsltMap.get("SVC_MACH_MAINT_AVAL_FLG");
        String trxHdrNum = (String) rsltMap.get("TRX_HDR_NUM");
        String stkStsCd = (String) rsltMap.get("STK_STS_CD");
        String locStsCd = (String) rsltMap.get("SVC_MACH_MSTR_LOC_STS_CD");
        BigDecimal svcMachMstrPk = (BigDecimal) rsltMap.get("SVC_MACH_MSTR_PK");

        // Check Machine Master
        if (!ZYPConstant.FLG_ON_Y.equals(svcMachMaintAvalFlg) && ZYPCommonFunc.hasValue(trxHdrNum) && !trxHdrNum.equals(sMsg.poNum.getValue())) {

            sMsgALine.serNumListTxt_A1.setErrorInfo(1, "NLZM2317E");
            return false;

        } else if (!ZYPCommonFunc.hasValue(rtlSwhCd) || !rtlSwhCd.equals(sMsgALine.srcRtlSwhCd_A1.getValue())) {

            sMsgALine.serNumListTxt_A1.setErrorInfo(1, "NPZM0264E");
            return false;

        } else if (!ZYPCommonFunc.hasValue(stkStsCd) || !stkStsCd.equals(sMsgALine.stkStsCd_A1.getValue())) {

            sMsgALine.serNumListTxt_A1.setErrorInfo(1, "NLCM0153E");
            return false;

        } else if (!ZYPCommonFunc.hasValue(locStsCd) || !LOC_STS.DC_STOCK.equals(locStsCd)) {

            sMsgALine.serNumListTxt_A1.setErrorInfo(1, "NLCM0152E");
            return false;

        } else {

            SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstr(sMsg.glblCmpyCd.getValue(), svcMachMstrPk, sMsgALine.serNumListTxt_A1, "Serial");

            if (svcMachMstrTMsg == null) {

                return false;
            }
        }
        return true;
    }

    /**
     * checkMdse Mod QC#27127
     * @param glblCmpyCd String
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     * @param deriveItemInfoCache S21LRUMap<Object, S21SsmEZDResult>
     * @return true:OK false:Error
     */
    private static boolean checkMdse(String glblCmpyCd, NPAL1500CMsg cMsg, NPAL1500SMsg sMsg, S21LRUMap<Object, S21SsmEZDResult> deriveItemInfoCache) {
        // master check MdseCd
        boolean rtrnCd = true;

        // QC#24918
        String[] materialParts = ZYPCodeDataUtil.getVarCharConstValue(VAR_CONST_CREATE_MATERIAL_PARTS, cMsg.glblCmpyCd.getValue()).split(",");
        // QC#27127
        S21LRUMap<Object, RCV_ASN_VNDTMsg> rcvAsnVndMsgCache = new S21LRUMap<Object, RCV_ASN_VNDTMsg>(1);

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            // START 02/18/2020 T.Ogura [QC#55916,ADD]
            if (ZYPConstant.FLG_OFF_N.equals(sMsg.A.no(i).openPoFlg_A1.getValue())) {
                continue;
            }
            // END   02/18/2020 T.Ogura [QC#55916,ADD]

            NPAL1500_ASMsg sMsgALine = sMsg.A.no(i);

            MDSETMsg mdseTMsg = new MDSETMsg();

            ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, sMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, sMsg.A.no(i).mdseCd_A1);

            mdseTMsg = (MDSETMsg) EZDTBLAccessor.findByKey(mdseTMsg);
            if (mdseTMsg != null && "16".equals(mdseTMsg.mdseItemTpCd.getValue())) {
                sMsgALine.xxCntDplyFlg_A2.setValue(ZYPConstant.FLG_ON_Y);
                sMsgALine.mdseCd_A1.clearErrorInfo();
            }

            // START 2017/12/06 [QC#14858, ADD]. QC#23616 Mod
            if ((PO_LINE_TP.EXPENSE.equals(sMsg.A.no(i).poLineTpCd_A1.getValue()) // 
                    || PO_LINE_TP.EXPENSE_WITH_RECEIPT.equals(sMsg.A.no(i).poLineTpCd_A1.getValue()) //
                    || PO_LINE_TP.ASSET.equals(sMsg.A.no(i).poLineTpCd_A1.getValue())) //
                    && (!ZYPCommonFunc.hasValue(sMsg.A.no(i).xxCntDplyFlg_A2) || ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxCntDplyFlg_A2.getValue()))) {
                // Non ASL Item.
                ZYPEZDItemValueSetter.setValue(sMsgALine.poDispUomCd_A1, VND_UOM.EACH);
                continue;
            } else {

            // END 2017/12/06 [QC#14858, ADD]

                if (!hasExistMdseCd(sMsg.glblCmpyCd.getValue(), sMsgALine.mdseCd_A1.getValue())) {

                    sMsgALine.mdseCd_A1.setErrorInfo(1, NPAM0076E, new String[] {sMsgALine.mdseCd_A1.getValue() });

                    rtrnCd = false;
                }
                // QC#27127
                if (!NPAL1500CommonLogic.checkMdseInfoCache(cMsg, sMsg, i, deriveItemInfoCache) || !checkMdseAslAndVndUom(cMsg, sMsg, i)) {

                    rtrnCd = false;
                }

                // Machine Master Check for Buy Back
                if (DS_PO_TP.BUYBACK_PO.equals(sMsg.dsPoTpCd.getValue())) {
                    rtrnCd = checkMachineMasterForBuyBack(sMsg, rtrnCd, i, sMsgALine);
                }

                // QC#18761 Add. Mod QC#27127
                if (!NPAL1500CommonLogic.chkMdseDuplicateFromAslCache(cMsg, sMsg, cMsg.glblCmpyCd.getValue(), sMsgALine, rcvAsnVndMsgCache)) {

                    return false;
                }
                // QC#24918
                if (materialParts != null) {

                    if (Arrays.asList(materialParts).contains(sMsgALine.mdseCd_A1.getValue())) {

                        if (!ZYPCommonFunc.hasValue(cMsg.xxDsMultMsgDplyTxt_SI)) {
                            cMsg.xxDsMultMsgDplyTxt_SI.setErrorInfo(1, NPAM1579E, new String[] {"Special Instructionst" });
                            rtrnCd = false;
                        }
                    }
                }

                // START 2018/12/13 M.Naito [QC#29027,ADD]
                if (isOnlyCsaSetItem(sMsg.glblCmpyCd.getValue(), sMsgALine.mdseCd_A1.getValue())) {

                    sMsgALine.mdseCd_A1.setErrorInfo(1, NPAM1639E, new String[] {sMsgALine.mdseCd_A1.getValue() });

                    rtrnCd = false;
                }
                // END 2018/12/13 M.Naito [QC#29027,ADD]
            }
        }

        return rtrnCd;
    }

    /**
     * checkMachineMaster For BuyBack
     * @param sMsg NPAL1500SMsg
     * @param rtrnCd boolean
     * @param i int
     * @param sMsgALine NPAL1500_ASMsg
     * @return boolean
     */
    private static boolean checkMachineMasterForBuyBack(NPAL1500SMsg sMsg, boolean rtrnCd, int i, NPAL1500_ASMsg sMsgALine) {
        boolean result = rtrnCd;
        if (ZYPCommonFunc.hasValue(sMsgALine.svcConfigMstrPk_A1)) {

            SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstr(sMsg.glblCmpyCd.getValue(), sMsgALine.svcMachMstrPk_IB.getValue(), sMsgALine.mdseCd_A1, "Config Component");

            if (svcMachMstrTMsg == null) {

                result = false;

            } else {

                if (!ZYPDateUtil.isSameTimeStamp(sMsgALine.ezUpTime_IB.getValue(), sMsgALine.ezUpTimeZone_IB.getValue(), svcMachMstrTMsg.ezUpTime.getValue(), svcMachMstrTMsg.ezUpTimeZone.getValue())) {

                    sMsgALine.mdseCd_A1.setErrorInfo(1, "NLBM0009E");
                    result = false;
                }
            }

        } else if (ZYPConstant.FLG_ON_Y.equals(sMsgALine.instlBaseCtrlFlg_IB.getValue())) {

            if (ZYPCommonFunc.hasValue(sMsgALine.serNumListTxt_A1) && !checkSvcMachMstr(sMsg, i)) {

                result = false;
            }
        }
        return result;
    }

    /**
     * checkCommonLogic. Mod QC#27127. Mod QC#29155
     * @param glblCmpyCd String
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     * @param deriveItemInfoCache S21LRUMap<Object, S21SsmEZDResult>
     * @param isModifyShpgSvcLvlReln boolean
     * @return true:OK false:Error
     */
    private static boolean checkCommonLogic(String glblCmpyCd, NPAL1500CMsg cMsg, NPAL1500SMsg sMsg, S21LRUMap<Object, S21SsmEZDResult> deriveItemInfoCache, boolean isModifyShpgSvcLvlReln) {

        // Mandatory check
        boolean mandatoryOk = true;
        if (!checkMandatoryHeaderTab(cMsg, sMsg)) {
            mandatoryOk = false;
        }
        if (!checkMandatoryAddlHeaderTab(cMsg, sMsg)) {
            mandatoryOk = false;
        }
        if (!checkMandatoryDetailTab(cMsg, sMsg)) {
            mandatoryOk = false;
        }
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);

        if (!ZYPCommonFunc.hasValue(cMsg.poStsCd_H) || PO_STS.SAVED.equals(cMsg.poStsCd_H.getValue())) {
            if (ZYPDateUtil.compare(slsDt, cMsg.rqstRcvDt.getValue()) > 0) {
                cMsg.rqstRcvDt.setErrorInfo(1, NPZM0041E);
                return false;
            }

            // START 2023/04/03 TZ.Win [QC#60966, ADD]
            if (ZYPCommonFunc.hasValue(cMsg.rqstShipDt)) {
                if (ZYPDateUtil.compare(slsDt, cMsg.rqstShipDt.getValue()) > 0) {
                    cMsg.rqstShipDt.setErrorInfo(1, NPAM1657E);
                    return false;
                }
                if (ZYPDateUtil.compare(cMsg.rqstShipDt.getValue(), cMsg.rqstRcvDt.getValue()) > 0) {
                    cMsg.rqstShipDt.setErrorInfo(1, NPAM1658E);
                    return false;
                }
            }
            // END 2023/04/03 TZ.Win [QC#60966, ADD]
        }
        //check SourceWH in Buyback
        if (DS_PO_TP.BUYBACK_PO.equals(sMsg.dsPoTpCd.getValue()) && !ZYPCommonFunc.hasValue(cMsg.poStsCd_H)) {
            S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getSourceWH(glblCmpyCd, cMsg.srcRtlWhCd_SC.getValue());

            if (!ssmResult.isCodeNormal()) {
                // Not Exist
                cMsg.srcRtlWhCd_SC.setErrorInfo(1, NPAM1578E);
                return false;

            }
        }
        //QC#18602(Sol#102) ADD Start
        //check Line Type For Blanket PO
        if (DS_PO_TP.BLANKET_PO.equals(sMsg.dsPoTpCd.getValue()) && !ZYPCommonFunc.hasValue(cMsg.poStsCd_H)) {
            if (!checkPoLineForBP(sMsg, true)) {
                 
                return false;
            }
        }
        //QC#18602(Sol#102) ADD End

        if (!mandatoryOk) {
            // Error
            return false;
        }

        // Header Data Check
        if (!checkSupplierInMaster(glblCmpyCd, sMsg.prntVndCd.getValue())) {
            // Error
            cMsg.prntVndCd.setErrorInfo(1, NPAM0076E, new String[] {DISPLAY_PRNT_VND_CD });
            return false;
        }

        if (!checkSupplierSiteInMaster(glblCmpyCd, sMsg.vndCd.getValue())) {
            // Error
            cMsg.vndCd.setErrorInfo(1, NPAM0076E, new String[] {DISPLAY_VND_CD });
            return false;
        }

        // QC#31087
        if (ZYPCommonFunc.hasValue(sMsg.destRtlWhCd_DS)) {
            
            RTL_WHTMsg rwTMsg = new RTL_WHTMsg();
            ZYPEZDItemValueSetter.setValue(rwTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(rwTMsg.rtlWhCd, sMsg.destRtlWhCd_DS);

            rwTMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(rwTMsg);

            if (rwTMsg == null) {
                return true;
            }
            
            RCV_ASN_VNDTMsg inMsg = new RCV_ASN_VNDTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.rcvAsnVndCd, sMsg.vndCd);
            inMsg = (RCV_ASN_VNDTMsg) EZDTBLAccessor.findByKey(inMsg);
            // QC#51076
            if (inMsg != null && INVTY_OWNR.GMD.equals(rwTMsg.invtyOwnrCd.getValue()) && ZYPConstant.FLG_ON_Y.equals(inMsg.poSendFlg.getValue())){
                cMsg.vndCd.setErrorInfo(1, NPAM1643E, null);
                cMsg.prntVndCd.setErrorInfo(1, NPAM1643E, null);
                return false;
            }
        }

        // Check Exist Line
        if (sMsg.A.getValidCount() < 1) {
            // Error
            cMsg.setMessageInfo(NPAM1351E);
            return false;
        } else {
            // Line Exist.
            // Check PO Line Type
            if (!checkPoLineType(glblCmpyCd, sMsg)) {
                // Error
                return false;
            }

            // Check Rtl Warehouse in master
            if (!checkRtlWh(glblCmpyCd, sMsg, cMsg)) {
                // Error
                return false;
            }

            // Check Mdse Cd Mod QC#27127
            if (!checkMdse(glblCmpyCd, cMsg, sMsg, deriveItemInfoCache)) {
                // Error
                return false;
            }

            // Check RDD
            if (!checkRdd(glblCmpyCd, cMsg, sMsg)) {
                // Error
                return false;
            }

            // Check Order Qty
            if (!checkOrderQty(cMsg, sMsg)) {
                // Error
                return false;
            }

            // Check unit price
            if (!checkUnitPrice(sMsg)) {
                // Error
                return false;
            }

            // check maximum price
            if (!checkExtTotalPrice(sMsg)) {
                // Error
                return false;
            }

            // Check Serial
            if (!checkSerialItem(glblCmpyCd, sMsg)) {
                // Error
                return false;
            }
            boolean segErr = false;
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {

                // START 02/18/2020 T.Ogura [QC#55916,ADD]
                if (ZYPConstant.FLG_OFF_N.equals(sMsg.A.no(i).openPoFlg_A1.getValue())) {
                    continue;
                }
                // END   02/18/2020 T.Ogura [QC#55916,ADD]

                if (!NPAL1500CommonLogic.checkManualSegmentElementForSMsg(cMsg, sMsg, glblCmpyCd, BIZ_ID, i)) {
                    segErr = true;
                    continue;
                }
                // 2018/12/19 QC#29456 Add Start
                NPAL1500_ASMsg curMsg = sMsg.A.no(i);
                NPXC001001GetDefNineSegDataBean defNineSegDataBean = NPXC001001GetDefNineSegData.getInstance().checkDefault(//
                        glblCmpyCd, //
                        curMsg.splyCoaAcctCd.getValue(), //
                        curMsg.splyCoaProdCd.getValue(), //
                        curMsg.splyCoaProjCd.getValue());
                if (defNineSegDataBean != null //
                        && defNineSegDataBean.getMsgIdList() != null //
                        && !defNineSegDataBean.getMsgIdList().isEmpty()) {
                    String errMsgId = defNineSegDataBean.getMsgId(ZERO);
                    curMsg.xxScrItem130Txt_CH.setErrorInfo(1, errMsgId);
                    return false;
                }
                // 2018/12/19 QC#29456 Add End
            }
            if(segErr) {
                return false;
            }
        }
        // Check Addl Header
        // Check Ship to code & address
        if (!checkShipTo(glblCmpyCd, cMsg, sMsg)) {
            // Error
            return false;
        }

        // Check Carry Cd And Shpg Service level. Mod QC#29155
        if (!checkCarryAndShpgSrvLvl(glblCmpyCd, cMsg, sMsg, isModifyShpgSvcLvlReln)) {
            // Error
            return false;
        }

        // Check Inventory
        if (!checkInventory(glblCmpyCd, cMsg, sMsg)) {
            // Error
            return false;
        }

        // Check Configuration
        if (!checkConfiguration(glblCmpyCd, cMsg, sMsg)) {
            // Error
            return false;
        }

        if (ZYPCommonFunc.hasValue(sMsg.poNum)) {
            if (!checkSaveData(glblCmpyCd, cMsg, sMsg)) {
                // Error
                cMsg.setMessageInfo(NPAM0032E);
                return false;
            }

            if (!checkRWS(glblCmpyCd, sMsg)) {
                // START 2019/10/30 M.Naito [QC#53811,MOD]
//                cMsg.setMessageInfo(NPAM1509E);
                // END 2019/10/30 M.Naito [QC#53811,MOD]
                return false;
            }
        }

        return true;
    }

    /**
     * checkConfiguration
     * @param glblCmpyCd String
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     * @return true:OK false:Error
     */
    private static boolean checkConfiguration(String glblCmpyCd, NPAL1500CMsg cMsg, NPAL1500SMsg sMsg) {
        boolean rtrnCd = true;

        if (ZYPCommonFunc.hasValue(sMsg.dsPoTpCd) && DS_PO_TP.BUYBACK_PO.equals(sMsg.dsPoTpCd.getValue()) && ZYPCommonFunc.hasValue(sMsg.svcConfigMstrPk.getValue())) {
            S21SsmEZDResult ssmRslt = NPAL1500Query.getInstance().getConfigComponents(sMsg);

            if (!ssmRslt.isCodeNormal()) {
                cMsg.svcConfigMstrPk.setErrorInfo(1, NPAM1361E, new String[] {sMsg.svcConfigMstrPk.getValue().toString() });
                rtrnCd = false;
            // QC#30039
            } else if (ZYPCommonFunc.hasValue(sMsg.poStsCd_H) && PO_STS.CLOSED.equals(sMsg.poStsCd_H.getValue())) {
                return true;
            } else {
                rtrnCd = checkConfigurationInMaster(cMsg, sMsg, rtrnCd, ssmRslt);
            }
        }

        return rtrnCd;
    }

    /**
     * checkConfigurationInMaster
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     * @param rtrnCd boolean
     * @param ssmRslt S21SsmEZDResult
     * @return boolean
     */
    private static boolean checkConfigurationInMaster(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg, boolean rtrnCd, S21SsmEZDResult ssmRslt) {
        boolean result = rtrnCd;
        List<Map<String, String>> rsltList = (List<Map<String, String>>) ssmRslt.getResultObject();

        String svcMaachMaintAvalFlg = rsltList.get(0).get("SVC_MACH_MAINT_AVAL_FLG");
        String trxHdrNum = rsltList.get(0).get("TRX_HDR_NUM");
        // The machine of entered Config# is allocated
        // with other order.
        if ((!ZYPCommonFunc.hasValue(sMsg.poHdrStsCd)) && (svcMaachMaintAvalFlg.equals(ZYPConstant.FLG_OFF_N))) {
            cMsg.svcConfigMstrPk.setErrorInfo(1, NPAM1370E);
            result = false;
        } else if ((ZYPCommonFunc.hasValue(sMsg.poHdrStsCd)) && (svcMaachMaintAvalFlg.equals(ZYPConstant.FLG_OFF_N)) && !(sMsg.poNum.equals(trxHdrNum))) {
            // The machine of entered Config# is allocated
            // with other order.
            cMsg.svcConfigMstrPk.setErrorInfo(1, NPAM1370E);
            result = false;
        }
        return result;
    }
    /**
     * Mandatory Check for Header TAB
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     * @return true: normal false: has error
     */
    private static boolean checkMandatoryHeaderTab(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg) {

        NPAL1500ItemNameList itemNameList = NPAL1500ItemNameList.getInstance();
        itemNameList.setMsgInfo(cMsg, sMsg);

        List<EZDCItem> itemList = new ArrayList<EZDCItem>();
        itemList.add(cMsg.dsPoTpCd);
        itemList.add(cMsg.rqstRcvDt);
        itemList.add(cMsg.prchGrpCd);
        itemList.add(cMsg.prntVndCd);
        itemList.add(cMsg.vndCd);
        if (DS_PO_TP.BUYBACK_PO.equals(sMsg.dsPoTpCd.getValue())) {
            itemList.add(cMsg.srcRtlWhCd_SC);
        }
        itemList.add(cMsg.destRtlWhCd_DS);

        boolean isNormal = true;
        for (EZDCItem item : itemList) {
            if (!ZYPCommonFunc.hasValue(item)) {
                item.setErrorInfo(1, ZZM9000E, new String[] {itemNameList.getItemName(item) });
                isNormal = false;
            }
        }

        return isNormal;
    }

    /**
     * Mandatory Check for Additional Header TAB
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     * @return true: normal false: has error
     */
    private static boolean checkMandatoryAddlHeaderTab(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg) {

        NPAL1500ItemNameList itemNameList = NPAL1500ItemNameList.getInstance();
        itemNameList.setMsgInfo(cMsg, sMsg);

//        List<EZDCItem> itemList = new ArrayList<EZDCItem>();
//        itemList.add(cMsg.xxAllLineAddr_ST);
//        itemList.add(cMsg.shipToPostCd_ST);
//        itemList.add(cMsg.shipToCtyAddr_ST);
//        itemList.add(cMsg.shipToCtryCd_ST);
        List<EZDSItem> itemList = new ArrayList<EZDSItem>();
        itemList.add(sMsg.xxAllLineAddr_ST);
        itemList.add(sMsg.shipToPostCd_ST);
        itemList.add(sMsg.shipToCtyAddr_ST);
        itemList.add(sMsg.shipToCtryCd_ST);

        boolean isNormal = true;
//        for (EZDCItem item : itemList) {
        for (EZDSItem item : itemList) {
            if (!ZYPCommonFunc.hasValue(item)) {
                item.setErrorInfo(1, ZZM9000E, new String[] {itemNameList.getItemName(item) });
                isNormal = false;
            }
        }

        return isNormal;
    }

    /**
     * Mandatory Check for Detail TAB
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     * @return true: normal false: has error
     */
    private static boolean checkMandatoryDetailTab(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg) {

        NPAL1500ItemNameList itemNameList = NPAL1500ItemNameList.getInstance();
        itemNameList.setMsgInfo(cMsg, sMsg);

        List<EZDSItem> itemList = new ArrayList<EZDSItem>();

        boolean isNormal = true;
        String swhCd = "";
        String srcSwhCd = "";
        String lineNum = "";
        String checkNum = "";

        String dropShipRtlWhCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_KEY_DROP_SHIP_RTL_WH_CD, cMsg.glblCmpyCd.getValue());

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            // Set Item
            String chkLine = sMsg.A.no(i).dispPoDtlLineNum_A1.getValue();
            if (PO_MDSE_CMPSN_TP.PARENT.equals(sMsg.A.no(i).poMdseCmpsnTpCd_HD.getValue())) {
                swhCd = sMsg.A.no(i).destRtlSwhCd_A1.getValue();
                srcSwhCd = sMsg.A.no(i).srcRtlSwhCd_A1.getValue();
                lineNum = sMsg.A.no(i).dispPoDtlLineNum_A1.getValue();
                checkNum = lineNum.substring(0, lineNum.indexOf(PERIOD) + 1);
            } else if (PO_MDSE_CMPSN_TP.CHILD.equals(sMsg.A.no(i).poMdseCmpsnTpCd_HD.getValue())) {
                if (chkLine.startsWith(checkNum)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).destRtlSwhCd_A1, swhCd);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).srcRtlSwhCd_A1, srcSwhCd);
                }
            }

            // START 02/18/2020 T.Ogura [QC#55916,ADD]
            if (ZYPConstant.FLG_OFF_N.equals(sMsg.A.no(i).openPoFlg_A1.getValue())) {
                continue;
            }
            // END   02/18/2020 T.Ogura [QC#55916,ADD]

            itemList.add(sMsg.A.no(i).poLineTpCd_A1);
            // QC#14858 START. QC#23616 Mod
            if ((PO_LINE_TP.EXPENSE.equals(sMsg.A.no(i).poLineTpCd_A1.getValue()) // 
                    || PO_LINE_TP.EXPENSE_WITH_RECEIPT.equals(sMsg.A.no(i).poLineTpCd_A1.getValue()) //
                    || PO_LINE_TP.ASSET.equals(sMsg.A.no(i).poLineTpCd_A1.getValue())) //
                    && !ZYPCommonFunc.hasValue(sMsg.A.no(i).mdseCd_A1.getValue())) {
                itemList.add(sMsg.A.no(i).mdseCd_A1);
            }
            // QC#14858 END
            itemList.add(sMsg.A.no(i).entDealNetUnitPrcAmt_A1);
            itemList.add(sMsg.A.no(i).poDispQty_A1);
            // QC#16407 Mod.
            //QC#28146 Mod Start
//            if (!dropShipRtlWhCd.equals(sMsg.destRtlWhCd_DS.getValue())) {
            if (dropShipRtlWhCd.equals(sMsg.destRtlWhCd_DS.getValue())) {
                if(!PO_QLFY.DROPSHIP.equals(sMsg.poQlfyCd.getValue())) {
            
            //QC#28146 Mod End
                itemList.add(sMsg.A.no(i).destRtlSwhCd_A1);
                }
            }
            if (DS_PO_TP.BUYBACK_PO.equals(sMsg.dsPoTpCd.getValue())) {
                itemList.add(sMsg.A.no(i).srcRtlSwhCd_A1);
                itemList.add(sMsg.A.no(i).stkStsCd_A1);
            }

        }

        for (EZDSItem item : itemList) {
            if (!ZYPCommonFunc.hasValue(item)) {
                if (item.isError()) {
                    isNormal = false;
                } else {
                    item.setErrorInfo(1, ZZM9000E, new String[] {itemNameList.getItemName(item) });
                    isNormal = false;
                }
            }
        }

        return isNormal;
    }

    /**
     * chkSupplierInMaster
     * @param glblCmpyCd String
     * @param prtVndCd String
     * @return true:OK false:Error
     */
    private static boolean checkSupplierInMaster(String glblCmpyCd, String prtVndCd) {
        S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getPrtVndNm(glblCmpyCd, prtVndCd);

        if (ssmResult.isCodeNormal()) {
            // Exist Master
            return true;
        } else {
            // Not Exist
            return false;
        }
    }

    /**
     * chkSupplierSiteInMaster
     * @param glblCmpyCd String
     * @param vndCd String
     * @return true:Exist false:not Exist
     */
    private static boolean checkSupplierSiteInMaster(String glblCmpyCd, String vndCd) {
        S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getVndNm(glblCmpyCd, vndCd);

        if (ssmResult.isCodeNormal()) {
            // Exist Master
            return true;
        } else {
            // Not Exist
            return false;
        }
    }

    /**
     * checkPoLineType
     * @param glblCmpyCd String
     * @param sMsg NPAL1500SMsg
     * @return true:OK false:Error
     */
    private static boolean checkPoLineType(String glblCmpyCd, NPAL1500SMsg sMsg) {
        boolean rtrnCd = true;

        // chek enable Asset Flag
        S21SsmEZDResult result = NPAL1500Query.getInstance().getEnableAssetFlg(glblCmpyCd, sMsg.dsPoTpCd.getValue());

        if (result.isCodeNormal()) {
            String enableAssetFlg = (String) result.getResultObject();

            if (ZYPConstant.FLG_OFF_N.equals(enableAssetFlg)) {
                rtrnCd = checkPoLineTypeAsset(sMsg, rtrnCd);
            }
        }

        return rtrnCd;
    }

    /**
     * checkPoLineTypeAsset
     * @param sMsg NPAL1500SMsg
     * @param rtrnCd boolean
     * @return boolean rtrnCd
     */
    private static boolean checkPoLineTypeAsset(NPAL1500SMsg sMsg, boolean rtrnCd) {
        boolean result = rtrnCd;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            // START 02/18/2020 T.Ogura [QC#55916,ADD]
            if (ZYPConstant.FLG_OFF_N.equals(sMsg.A.no(i).openPoFlg_A1.getValue())) {
                continue;
            }
            // END   02/18/2020 T.Ogura [QC#55916,ADD]

            if (PO_LINE_TP.ASSET.equals(sMsg.A.no(i).poLineTpCd_A1.getValue())) {
                // Error
                sMsg.A.no(i).poLineTpCd_A1.setErrorInfo(1, NPAM1429E);
                result = false;
            }
        }
        return result;
    }

    /**
     * checkRtlWh
     * @param glblCmpyCd String
     * @param sMsg NPAL1500SMsg
     * @return true:OK false:Error
     */
    private static boolean checkRtlWh(String glblCmpyCd, NPAL1500SMsg sMsg, NPAL1500CMsg cMsg) {
        boolean rtrnCd = true;
        String dropShipRtlWhCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_KEY_DROP_SHIP_RTL_WH_CD, glblCmpyCd);

        // QC#27127
        S21LRUMap<Object, S21SsmEZDResult> getInvtyLocCdCache = new S21LRUMap<Object, S21SsmEZDResult>();
        S21LRUMap<Object, NMXC100001EnableWHData> locDestDataCache = new S21LRUMap<Object, NMXC100001EnableWHData>();
        S21LRUMap<Object, RTL_WHTMsg> rtlWhCache = new S21LRUMap<Object, RTL_WHTMsg>();
        S21LRUMap<Object, SWHTMsg> swhCache = new S21LRUMap<Object, SWHTMsg>();
        S21LRUMap<Object, DS_PO_TPTMsg> dsPoToCache = new S21LRUMap<Object, DS_PO_TPTMsg>();

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            // START 02/18/2020 T.Ogura [QC#55916,ADD]
            if (ZYPConstant.FLG_OFF_N.equals(sMsg.A.no(i).openPoFlg_A1.getValue())) {
                continue;
            }
            // END   02/18/2020 T.Ogura [QC#55916,ADD]

            // check enable Warehouse(Destination).
            String invtyLocCd = sMsg.destRtlWhCd_DS.getValue() + sMsg.A.no(i).destRtlSwhCd_A1.getValue();

            // QC#16407 Mod.
            if (!dropShipRtlWhCd.equals(invtyLocCd)) {
                
                //QC#16341 Add. WH Master check.
                String destRtlWhCd = sMsg.destRtlWhCd_DS.getValue();
                RTL_WHTMsg whInMsg = rtlWhCache.get(destRtlWhCd);
                if (whInMsg == null) {
                    whInMsg = new RTL_WHTMsg();
                    ZYPEZDItemValueSetter.setValue(whInMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(whInMsg.rtlWhCd, sMsg.destRtlWhCd_DS.getValue());
                    RTL_WHTMsg whOutMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(whInMsg);
                    if (whOutMsg == null) {
                        cMsg.destRtlWhCd_DS.setErrorInfo(1, NPAM0076E, new String[] {DISPLAY_DEST_RTL_WH_CD });
                        rtrnCd = false;
                    } else {
                        rtlWhCache.put(destRtlWhCd, whOutMsg);
                    }
                }
                    
                // SWH Master check.
                String destRtlSwhCd = sMsg.A.no(i).destRtlSwhCd_A1.getValue();
                SWHTMsg swhInMsg =  swhCache.get(destRtlSwhCd);
                if (swhInMsg == null) {
                    swhInMsg = new SWHTMsg();
                    ZYPEZDItemValueSetter.setValue(swhInMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(swhInMsg.rtlSwhCd, sMsg.A.no(i).destRtlSwhCd_A1.getValue());
                    SWHTMsg swhOutMsg = (SWHTMsg) EZDTBLAccessor.findByKey(swhInMsg);
                    if (swhOutMsg == null) {
                        sMsg.A.no(i).destRtlSwhCd_A1.setErrorInfo(1, NPAM0076E, new String[] {DISPLAY_DEST_SWH_CD });
                        rtrnCd = false;
                    } else {
                        swhCache.put(destRtlSwhCd, swhOutMsg);
                    }
                }
                

                if (ZYPCommonFunc.hasValue(sMsg.dsPoTpCd) && !ZYPCommonFunc.hasValue(sMsg.xxPopPrm)) {
                    String dsPoTpCd = sMsg.dsPoTpCd.getValue();
                    DS_PO_TPTMsg dsPoTpTMsg = dsPoToCache.get(dsPoTpCd);
                    if (dsPoTpTMsg == null) {
                        dsPoTpTMsg = new DS_PO_TPTMsg();
                        ZYPEZDItemValueSetter.setValue(dsPoTpTMsg.glblCmpyCd, sMsg.glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(dsPoTpTMsg.dsPoTpCd, dsPoTpCd);
                        dsPoTpTMsg = (DS_PO_TPTMsg) EZDTBLAccessor.findByKey(dsPoTpTMsg);

                        if (dsPoTpTMsg != null) {
                            ZYPEZDItemValueSetter.setValue(sMsg.fromBizAppLocChkKeyId, dsPoTpTMsg.fromBizAppLocChkKeyId);
                            ZYPEZDItemValueSetter.setValue(sMsg.toBizAppLocChkKeyId, dsPoTpTMsg.toBizAppLocChkKeyId);
                            dsPoToCache.put(dsPoTpCd, dsPoTpTMsg);
                        }
                    }
                }

                if (rtrnCd) {

                    // QC#27127
                    NMXC100001EnableWHData locDestData = locDestDataCache.get(invtyLocCd);
                    if (locDestData == null) {

                        locDestData = NMXC100001EnableWH.checkEnableWH(//
                            glblCmpyCd//
                            , invtyLocCd//
                            , BIZ_ID//
                            , null//
                            , ZYPConstant.FLG_OFF_N//
                            , sMsg.toBizAppLocChkKeyId.getValue());

                        locDestDataCache.put(invtyLocCd, locDestData);
                    }

                    if (ZYPCommonFunc.hasValue(locDestData.getXxMsgId())) {
                        sMsg.A.no(i).destRtlSwhCd_A1.setErrorInfo(1, locDestData.getXxMsgId());
                        rtrnCd = false;
                    }

                    // check enable Warehouse(source)
                    if (DS_PO_TP.BUYBACK_PO.equals(sMsg.dsPoTpCd.getValue())) {
                        String srcInvtyLocCd = sMsg.srcRtlWhCd_SC.getValue() + sMsg.A.no(i).srcRtlSwhCd_A1.getValue();

                        NMXC100001EnableWHData locSrcData = NMXC100001EnableWH.checkEnableWH(//
                                glblCmpyCd//
                                , srcInvtyLocCd//
                                , BIZ_ID//
                                , null//
                                , ZYPConstant.FLG_OFF_N//
                                , sMsg.fromBizAppLocChkKeyId.getValue());

                        if (ZYPCommonFunc.hasValue(locSrcData.getXxMsgId())) {
                            sMsg.A.no(i).srcRtlSwhCd_A1.setErrorInfo(1, locSrcData.getXxMsgId());
                            rtrnCd = false;
                        }
                    }

                    // check reqular Stk Status Mod QC#27127
                    S21SsmEZDResult sqlResult = NPAL1500Query.getInstance().getRegularStkInvtyLocCd(//
                            glblCmpyCd, sMsg.destRtlWhCd_DS.getValue(), sMsg.A.no(i).destRtlSwhCd_A1.getValue(), getInvtyLocCdCache);

                    if (!sqlResult.isCodeNormal()) {
                        sMsg.A.no(i).destRtlSwhCd_A1.setErrorInfo(1, NPAM1431E);
                        rtrnCd = false;
                    }
                }

            }

        }

        return rtrnCd;
    }

    /**
     * checkOrderQty
     * @param sMsg NPAL1500SMsg
     * @return true:OK false:Error
     */
    private static boolean checkOrderQty(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg) {
        boolean rtrnCd = true;

        // check Order Qty <= 0
        String poMdseCmpsnTpCd = "";
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            // START 02/18/2020 T.Ogura [QC#55916,ADD]
            if (ZYPConstant.FLG_OFF_N.equals(sMsg.A.no(i).openPoFlg_A1.getValue())) {
                continue;
            }
            // END   02/18/2020 T.Ogura [QC#55916,ADD]

            poMdseCmpsnTpCd = sMsg.A.no(i).poMdseCmpsnTpCd_HD.getValue();
            if (sMsg.A.no(i).poDispQty_A1.getValueInt() <= 0) {
                sMsg.A.no(i).poDispQty_A1.setErrorInfo(1, NPAM1183E, new String[] {"Order Qty", "zero" });
                rtrnCd = false;
            }
            // QC#15975
            if (PO_MDSE_CMPSN_TP.PARENT.equals(poMdseCmpsnTpCd)) {
                if (!NPAL1500CommonLogic.checkComponentQty(sMsg, cMsg.glblCmpyCd.getValue(), i)) {
                    rtrnCd = false;
                }
            }
        }

        return rtrnCd;
    }

    /**
     * checkUnitPrice
     * @param sMsg NPAL1500SMsg
     * @return true:OK false:Error
     */
    private static boolean checkUnitPrice(NPAL1500SMsg sMsg) {
        boolean rtrnCd = true;

        // check Order Qty <= 0
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            // START 02/18/2020 T.Ogura [QC#55916,ADD]
            if (ZYPConstant.FLG_OFF_N.equals(sMsg.A.no(i).openPoFlg_A1.getValue())) {
                continue;
            }
            // END   02/18/2020 T.Ogura [QC#55916,ADD]

            if (sMsg.A.no(i).entDealNetUnitPrcAmt_A1.getValueInt() < 0) {
                sMsg.A.no(i).entDealNetUnitPrcAmt_A1.setErrorInfo(1, NPAM0046E, new String[] {"Line Price" });
                rtrnCd = false;
            }
        }

        return rtrnCd;
    }

    /**
     * checkExtTotalPrice
     * @param sMsg NPAL1500SMsg
     * @return true:OK false:Error
     */
    private static boolean checkExtTotalPrice(NPAL1500SMsg sMsg) {
        boolean rtrnCd = true;

        // check Ext.Total Amt
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            // START 02/18/2020 T.Ogura [QC#55916,ADD]
            if (ZYPConstant.FLG_OFF_N.equals(sMsg.A.no(i).openPoFlg_A1.getValue())) {
                continue;
            }
            // END   02/18/2020 T.Ogura [QC#55916,ADD]

            BigDecimal totalPrice = BigDecimal.ZERO;

            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).entPoDtlDealNetAmt_A1)) {
                totalPrice = sMsg.A.no(i).entPoDtlDealNetAmt_A1.getValue();
            } else {
                BigDecimal unitPrice = sMsg.A.no(i).entDealNetUnitPrcAmt_A1.getValue();
                BigDecimal qty = sMsg.A.no(i).poDispQty_A1.getValue();
                qty = qty.setScale(0);
                totalPrice = unitPrice.multiply(qty);
            }

            if (MAXIMUM_AMT.compareTo(totalPrice) < 0) {
                sMsg.A.no(i).entDealNetUnitPrcAmt_A1.setErrorInfo(1, NPAM1199E);
                sMsg.A.no(i).poDispQty_A1.setErrorInfo(1, NPAM1199E);
                rtrnCd = false;
            }
        }

        return rtrnCd;
    }

    /**
     * checkShipTo
     * @param glblCmpyCd String
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     * @return true:OK false:Error
     */
    private static boolean checkShipTo(String glblCmpyCd, NPAL1500CMsg cMsg, NPAL1500SMsg sMsg) {
        boolean rtrnCd = true;

        // master check
        if (!isRegisteredShipToCust(sMsg)) {
            cMsg.shipToCustCd.setErrorInfo(1, NPAM1351E);
            rtrnCd = false;
        } else {

            // START 2019/12/02 T.Ogura [QC#54813,ADD]
            if (ZYPCommonFunc.hasValue(sMsg.shipToCustCd) && !ZYPCommonFunc.hasValue(sMsg.shipToLocNm_ST)) {
                Map<String, String> resultMap = null;

                S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getShipToAddressFromShipToCustomer(sMsg.glblCmpyCd.getValue(), sMsg.shipToCustCd.getValue());

                if (ssmResult.isCodeNormal()) {
                    resultMap = (Map<String, String>) ssmResult.getResultObject();
                    if (resultMap != null) {
                        ZYPEZDItemValueSetter.setValue(sMsg.shipToLocNm_ST, (String) resultMap.get("LOC_NM"));
                    }
                }

                if (resultMap == null) {
                    cMsg.shipToCustCd.setErrorInfo(1, NPAM0076E, new String[] {"Ship To Customer Code" });
                    rtrnCd = false;
                }
            }
            // END   2019/12/02 T.Ogura [QC#54813,ADD]

            // START 2017/11/08 [QC#16341, DEL]
            // QC#16341 Add.
            // if (ZYPCommonFunc.hasValue(sMsg.shipToCustCd) && ZYPCommonFunc.hasValue(sMsg.destRtlWhCd_DS) && !sMsg.shipToCustCd.getValue().equals(sMsg.destRtlWhCd_DS.getValue())) {
            //     if (!NPAL1500CommonLogic.getRtlWhInfo(cMsg, sMsg)) {
            //         return false;
            //     }
            // }
            // END   2017/11/08 [QC#16341, DEL]

            String addrChkFlg = ZYPCodeDataUtil.getVarCharConstValue(NPAL1500_ADDR_CHK_FLG, glblCmpyCd);

            if (ZYPCommonFunc.hasValue(addrChkFlg) //
                    && ZYPConstant.FLG_OFF_N.equals(addrChkFlg)) {
                // Unnecessary address check.
                return rtrnCd;
            }

            // Check Address
            if (!callAddrValidApi(cMsg, glblCmpyCd)) {
                rtrnCd = false;
            } else {
                // Set sMsg.
                ZYPEZDItemValueSetter.setValue(sMsg.shipToFirstLineAddr_ST, cMsg.shipToFirstLineAddr_ST);
                ZYPEZDItemValueSetter.setValue(sMsg.shipToScdLineAddr_ST, cMsg.shipToScdLineAddr_ST);
                ZYPEZDItemValueSetter.setValue(sMsg.xxAllLineAddr_ST, cMsg.xxAllLineAddr_ST);
                ZYPEZDItemValueSetter.setValue(sMsg.shipToCtyAddr_ST, cMsg.shipToCtyAddr_ST);
                ZYPEZDItemValueSetter.setValue(sMsg.shipToStCd_ST, cMsg.shipToStCd_ST);
                ZYPEZDItemValueSetter.setValue(sMsg.shipToPostCd_ST, cMsg.shipToPostCd_ST);
                ZYPEZDItemValueSetter.setValue(sMsg.shipToCtryCd_ST, cMsg.shipToCtryCd_ST);
                ZYPEZDItemValueSetter.setValue(sMsg.shipToCntyNm_ST, cMsg.shipToCntyNm_ST);
            }
            // Check consistency between Destination WH Code and
            // Ship-To Code
            // START 2017/11/13 [QC#16341, MOD]
            // if (ZYPCommonFunc.hasValue(sMsg.shipToCustCd)) {
            //     if (!sMsg.destRtlWhCd_DS.getValue().equals(sMsg.shipToCustCd.getValue())) {
            //         cMsg.shipToCustCd.setErrorInfo(1, NPAM1506E);
            //         cMsg.xxDplyTab.setValue(TAB_ADDL_HEADER);
            //         rtrnCd = false;
            //     }
            // }
            if (!NPAL1500CommonLogic.isManualDropShipWHCd(glblCmpyCd, sMsg.destRtlWhCd_DS.getValue())) {
                if (ZYPCommonFunc.hasValue(sMsg.shipToCustCd)) {
                    if (!sMsg.destRtlWhCd_DS.getValue().equals(sMsg.shipToCustCd.getValue())) {
                        cMsg.shipToCustCd.setErrorInfo(1, NPAM1506E);
                        cMsg.xxDplyTab.setValue(TAB_ADDL_HEADER);
                        rtrnCd = false;
                    }
                }
            }
            // END   2017/11/13 [QC#16341, MOD]
        }

        return rtrnCd;
    }

    /**
     * checkCarryAndShpgSrvLvl. Mod QC#29155
     * @param glblCmpyCd String
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     * @param isModifyShpgSvcLvlReln boolean
     * @return true:OK false:Error
     */
    private static boolean checkCarryAndShpgSrvLvl(String glblCmpyCd, NPAL1500CMsg cMsg, NPAL1500SMsg sMsg, boolean isModifyShpgSvcLvlReln) {
        String carrCd = sMsg.carrCd.getValue();

        if (ZYPCommonFunc.hasValue(carrCd)) {
            if (!isExistOtbdCarrCd(sMsg)) {
                cMsg.carrCd.setErrorInfo(1, NPAM0076E, new String[] {"Carrier Code" });
                cMsg.xxDplyTab.setValue(TAB_ADDL_HEADER);
                return false;
            }
        }
        // QC#29155
        if (isModifyShpgSvcLvlReln && ZYPCommonFunc.hasValue(sMsg.shpgSvcLvlCd.getValue()) && ZYPCommonFunc.hasValue(carrCd)) {
            // get Carrier Service Level
            SHPG_SVC_LVL_CARR_RELNTMsg shpgSvcLvlCarrRelnTMsg = new SHPG_SVC_LVL_CARR_RELNTMsg();

            shpgSvcLvlCarrRelnTMsg.setSQLID("001");
            shpgSvcLvlCarrRelnTMsg.setConditionValue("glblCmpyCd01", sMsg.glblCmpyCd.getValue());
            shpgSvcLvlCarrRelnTMsg.setConditionValue("shpgSvcLvlCd01", sMsg.shpgSvcLvlCd.getValue());
            shpgSvcLvlCarrRelnTMsg.setConditionValue("carrCd01", sMsg.carrCd.getValue());

            SHPG_SVC_LVL_CARR_RELNTMsgArray resultTMsgArray = (SHPG_SVC_LVL_CARR_RELNTMsgArray) findByCondition(shpgSvcLvlCarrRelnTMsg);

            // START 2017/10/10 QC#21394 Add.
            if (resultTMsgArray != null && 0 < resultTMsgArray.getValidCount()) {
                if (!checkShpsSvcLvlCarrRelntResult(cMsg, sMsg, resultTMsgArray)) {
                    return false;
                }
            } else {
                // QC#29155
                cMsg.carrCd.setErrorInfo(2, NPAM1636W);
                cMsg.shpgSvcLvlCd.setErrorInfo(2, NPAM1636W);
                cMsg.xxDplyTab.setValue(TAB_ADDL_HEADER);
                return false;
            }
        } 
        // END 2017/10/10 QC#21394 Add.

        // START 2017/10/10 QC#21394 Add.
        // Carrier Account Number
        if (FRT_COND.COLLECT.equals(sMsg.frtCondCd.getValue()) && !ZYPCommonFunc.hasValue(sMsg.carrAcctNum.getValue())) {
            cMsg.carrAcctNum.setErrorInfo(1, NPAM1367E);
            cMsg.xxDplyTab.setValue(TAB_ADDL_HEADER);
            return false;
        }
        // END 2017/10/10 QC#21394 Add.

        // Check OK.
        return true;
    }

    /**
     * checkShpsSvcLvlCarrRelntResult
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     * @param resultTMsgArray SHPG_SVC_LVL_CARR_RELNTMsgArray
     * @return boolean
     */
    private static boolean checkShpsSvcLvlCarrRelntResult(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg, SHPG_SVC_LVL_CARR_RELNTMsgArray resultTMsgArray) {
        for (int i = 0; i < resultTMsgArray.getValidCount(); i++) {

            String carrSvcLvlCd = resultTMsgArray.no(i).carrSvcLvlCd.getValue();
            // QC#29155
            if (!ZYPCommonFunc.hasValue(carrSvcLvlCd)) {
                cMsg.carrCd.setErrorInfo(2, NPAM1636W);
                cMsg.shpgSvcLvlCd.setErrorInfo(2, NPAM1636W);
                cMsg.xxDplyTab.setValue(TAB_ADDL_HEADER);
                return false;
            }
        }
        return true;
    }

    /**
     * checkInventory
     * @param glblCmpyCd String
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     * @return true:OK false:Error
     */
    private static boolean checkInventory(String glblCmpyCd, NPAL1500CMsg cMsg, NPAL1500SMsg sMsg) {
        // Inventory Req Check
        if (EVENT_NM_NPAL1500_CMN_SUBMIT.equals(cMsg.getScreenAplID()) //
                && ZYPCommonFunc.hasValue(sMsg.dsPoTpCd) //
                && PO_TYPE_SUB_CONTRACT.equals(sMsg.dsPoTpCd.getValue())) {
            S21SsmEZDResult ssmRslt = NPAL1500Query.getInstance().getInventoryReq(sMsg);
            if (!ssmRslt.isCodeNormal()) {
                cMsg.dsPoTpCd.setErrorInfo(1, NPAM1369E);
                return false;
            }
        }

        return true;
    }


    /**
     * checkSerialItem
     * @param glblCmpyCd String
     * @param sMsg NPAL1500SMsg
     * @return true:OK false:Error
     */
    private static boolean checkSerialItem(String glblCmpyCd, NPAL1500SMsg sMsg) {

        boolean rtrnCd = true;

        HashMap<String, HashSet<String>> serChkList = new HashMap<String, HashSet<String>>();

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            // START 02/18/2020 T.Ogura [QC#55916,ADD]
            if (ZYPConstant.FLG_OFF_N.equals(sMsg.A.no(i).openPoFlg_A1.getValue())) {
                continue;
            }
            // END   02/18/2020 T.Ogura [QC#55916,ADD]

            // QC#24413
            if (PO_LINE_STS.CANCELLED.equals(sMsg.A.no(i).poLineStsCd_A1.getValue())) {
                continue;
            }
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).rcvSerTakeFlg_IB.getValue())) {
                // Empty check
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).serNumListTxt_A1)) {
                    rtrnCd = checkDsPoTypeIsBuyBack(sMsg, rtrnCd, i);
                    continue;
                // START 2018/07/12 S.Katsuma [QC#26867,MOD]
                } else {
                    if (DS_PO_TP.BUYBACK_PO.equals(sMsg.dsPoTpCd.getValue())) {
                        if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).svcConfigMstrPk_A1)) {
                            if (isExistSvcMachMstr(glblCmpyCd, sMsg.A.no(i).mdseCd_A1.getValue(), sMsg.A.no(i).serNumListTxt_A1.getValue())) {
                                sMsg.A.no(i).serNumListTxt_A1.setErrorInfo(1, NPAM1620E, new String[] {});
                                rtrnCd = false;
                            }
                        }
                    }
                }
                // END 2018/07/12 S.Katsuma [QC#26867,MOD]

                if (!checkSerialList(glblCmpyCd, sMsg,  serChkList, i)) {
                    rtrnCd = false;
                }
            }
        }
        return rtrnCd;
    }

    /**
     * checkSerialList
     * @param glblCmpyCd String
     * @param sMsg NPAL1500SMsg
     * @param serChkList HashMap<String, HashSet<String>>
     * @param i int
     * @return boolean rtrnCd
     */
    private static boolean checkSerialList(String glblCmpyCd, NPAL1500SMsg sMsg, HashMap<String, HashSet<String>> serChkList, int i) {
        // Serial List
        List<String> serNumList = Arrays.asList(sMsg.A.no(i).serNumListTxt_A1.getValue().split(","));

        boolean rtrnCd = true;
        for (String serNum : serNumList) {
            //QC#27024 Add Start
            //except for Received Item
            if(checkReceivedSerNum(glblCmpyCd, sMsg, i, serNum)){
                continue;
            }
            //QC#27024 Mod End
            // Serial Validation Check
            if (!validateSerialNum(glblCmpyCd, sMsg, i, serNum)) {
                rtrnCd = false;
            }
            // Is duplicate?
            if (!checkDuplicateSerialNum(glblCmpyCd, sMsg, serChkList, i, serNum)) {
                rtrnCd = false;
            }
        }
        return rtrnCd;
    }

    /**
     * validateSerialNum
     * @param glblCmpyCd String
     * @param sMsg NPAL1500SMsg
     * @param i int
     * @param serNum String
     * @return boolean
     */
    private static boolean validateSerialNum(String glblCmpyCd, NPAL1500SMsg sMsg, int i, String serNum) {
        boolean rtrnCd = true;
        String mdseCd = sMsg.A.no(i).mdseCd_A1.getValue();
        NLZC403001PMsg result = runSerialValidation(//
                glblCmpyCd, mdseCd, serNum, sMsg);

        if (S21ApiUtil.isXxMsgId(result)) {
            // First Error Message Only.
            sMsg.A.no(i).serNumListTxt_A1.setErrorInfo(1, result.xxMsgIdList.no(0).xxMsgId.getValue());
            rtrnCd = false;
        }
        return rtrnCd;
    }

    /**
     * checkDuplicateSerialNum
     * @param glblCmpyCd String
     * @param sMsg NPAL1500SMsg
     * @param serChkList HashMap<String, HashSet<String>>
     * @param i int
     * @param serNum String
     * @return boolean
     */
    private static boolean checkDuplicateSerialNum(String glblCmpyCd, NPAL1500SMsg sMsg, HashMap<String, HashSet<String>> serChkList, int i,  String serNum) {
        boolean rtrnCd = true;
        String mdseCd = sMsg.A.no(i).mdseCd_A1.getValue();
        String poOrdNum = sMsg.poNum.getValue();
        if (serChkList.containsKey(mdseCd)) {
            // Line duplicate
            HashSet<String> set = serChkList.get(mdseCd);
            if (set.contains(serNum)) {
                // Duplicate
                if (!sMsg.A.no(i).serNumListTxt_A1.isError()) {
                    sMsg.A.no(i).serNumListTxt_A1.setErrorInfo(1, NPAM0039E);
                    rtrnCd = false;
                }
            } else {
                set.add(serNum);

                // check duplicate DB
                if (isDuplicateSerialNum(glblCmpyCd, serNum, sMsg.A.no(i), poOrdNum)) {
                    sMsg.A.no(i).serNumListTxt_A1.setErrorInfo(1, NPAM0039E);
                    rtrnCd = false;
                }
            }
        } else {
            // check duplicate DB
            if (isDuplicateSerialNum(glblCmpyCd, serNum, sMsg.A.no(i), poOrdNum)) {
                sMsg.A.no(i).serNumListTxt_A1.setErrorInfo(1, NPAM0039E);
                rtrnCd = false;
            }

            // add chkList
            HashSet<String> set = new HashSet<String>();
            set.add(serNum);
            serChkList.put(mdseCd, set);
        }
        return rtrnCd;
    }

    /**
     * checkDsPoTypeIsBuyBack
     * @param sMsg NPAL1500SMsg
     * @param rtrnCd boolean
     * @param i int
     * @return boolean rtrnCd
     */
    private static boolean checkDsPoTypeIsBuyBack(NPAL1500SMsg sMsg, boolean rtrnCd, int i) {
        boolean result = rtrnCd;
        if (DS_PO_TP.BUYBACK_PO.equals(sMsg.dsPoTpCd.getValue())) {
            sMsg.A.no(i).serNumListTxt_A1.setErrorInfo(1, ZZM9000E, new String[] {DISPLAY_SER_NUM });
            result = false;
        }
        return result;
    }


    /**
     * isDuplicateSerialNum
     * @param glblCmpyCd String
     * @param serNum String
     * @param asMsg NPAL1500_ASMsg
     * @param poOrdNum String
     * @return boolean
     */
    private static boolean isDuplicateSerialNum(//
            String glblCmpyCd, String serNum, NPAL1500_ASMsg asMsg, String poOrdNum) {

        S21SsmEZDResult result = NPAL1500Query.getInstance().getPoSerNumPk(glblCmpyCd, serNum, asMsg, poOrdNum);

        if (result.getQueryResultCount() > 0) {
            // Duplicate
            return true;
        } else {
            // Not duplicate
            return false;
        }
    }


    /**
     * runSerialValidation
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param serialNum String
     * @param sMsg NPAL1500SMsg
     * @return NLZC403001PMsg
     */
    private static NLZC403001PMsg runSerialValidation(//
            String glblCmpyCd, String mdseCd, String serialNum, NPAL1500SMsg sMsg) {
        NLZC403001PMsg param = new NLZC403001PMsg();
        NLZC403001 api = new NLZC403001();

        String poTypeCd = sMsg.dsPoTpCd.getValue();
        String fromlocCd = sMsg.vndCd.getValue();
        // QC#19811
        if (DS_PO_TP.BUYBACK_PO.equals(poTypeCd) || DS_PO_TP.SUBCONTRACT_PO.equals(poTypeCd)) {
            ZYPEZDItemValueSetter.setValue(param.xxModeCd, SER_VAL_API_MODE_BUYBACK);
        } else {
            ZYPEZDItemValueSetter.setValue(param.xxModeCd, SER_VAL_API_MODE_INBOUND);
        }
        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(param.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(param.serNum, serialNum);
        ZYPEZDItemValueSetter.setValue(param.whCd, fromlocCd);

        // Execute
        api.execute(param, ONBATCH_TYPE.ONLINE);

        return param;
    }

    /**
     * chkRdd
     * @param glblCmpyCd String
     * @param sMsg NPAL1500SMsg
     * @return true:OK false:Error
     */
    private static boolean checkRdd(String glblCmpyCd,NPAL1500CMsg cMsg, NPAL1500SMsg sMsg) {

        boolean rtrnCd = true;

        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        String poMdseCmpsnTpCd = "";
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            // START 02/18/2020 T.Ogura [QC#55916,ADD]
            if (ZYPConstant.FLG_OFF_N.equals(sMsg.A.no(i).openPoFlg_A1.getValue())) {
                continue;
            }
            // END   02/18/2020 T.Ogura [QC#55916,ADD]

            poMdseCmpsnTpCd = sMsg.A.no(i).poMdseCmpsnTpCd_HD.getValue();
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).poSendTs) && (!ZYPCommonFunc.hasValue(sMsg.A.no(i).poStsCd_HD) || PO_STS.SAVED.equals(sMsg.A.no(i).poStsCd_HD.getValue()) && !PO_MDSE_CMPSN_TP.CHILD.equals(poMdseCmpsnTpCd))) {
                // QC#21170 Start
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).rqstRcvDt_A1)) {
                    NPZC113001PMsg npzc113001PMsg = NPAL1500CommonLogic.execGetPrimVndFromAsl(cMsg, sMsg, i);
                    if (ZYPCommonFunc.hasValue(npzc113001PMsg.vndLtDaysNum)) {
                        NPAL1500CommonLogic.setRqstRcvDt(cMsg, sMsg, npzc113001PMsg.vndLtDaysNum.getValueInt(), i);
                    } else {
                        NPAL1500CommonLogic.setRqstRcvDt(cMsg, sMsg, 0, i);
                    }
                }
                // QC#21170 End
                if (ZYPDateUtil.compare(slsDt, sMsg.A.no(i).rqstRcvDt_A1.getValue()) > 0) {
                    sMsg.A.no(i).rqstRcvDt_A1.setErrorInfo(1, NPZM0041E);
                    rtrnCd = false;
                }

                // START 2023/04/03 TZ.Win [QC#60966, ADD]
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).rqstShipDt_A1)) {

                    if (ZYPDateUtil.compare(slsDt, sMsg.A.no(i).rqstShipDt_A1.getValue()) > 0){

                        sMsg.A.no(i).rqstShipDt_A1.setErrorInfo(1, NPAM1657E);
                        rtrnCd = false;
                    }
                    if (ZYPDateUtil.compare(sMsg.A.no(i).rqstShipDt_A1.getValue(), sMsg.A.no(i).rqstRcvDt_A1.getValue()) > 0) {
                    
                        sMsg.A.no(i).rqstShipDt_A1.setErrorInfo(1, NPAM1658E);
                        rtrnCd = false;
                    }
                }
                // END 2023/04/03 TZ.Win [QC#60966, ADD]
            }
        }

        return rtrnCd;
    }

    /**
     * chkSaveData
     * @param glblCmpyCd String
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     * @return true:OK false:Error
     */
    private static boolean checkSaveData(String glblCmpyCd, NPAL1500CMsg cMsg, NPAL1500SMsg sMsg) {
        boolean rtrnCd = true;

        if (EVENT_NM_NPAL1500_CMN_SAVE.equals(cMsg.getScreenAplID())) {
            if (ZYPCommonFunc.hasValue(sMsg.poStsCd_H) && !PO_STS.SAVED.equals(sMsg.poStsCd_H.getValue())) {
                rtrnCd = false;
            }
        }
        return rtrnCd;
    }

    /**
     * chkRWS
     * @param glblCmpyCd String
     * @param sMsg NPAL1500SMsg
     * @return true:OK false:Error
     */
    private static boolean checkRWS(String glblCmpyCd, NPAL1500SMsg sMsg) {
        boolean rtrnCd = true;
        // Compare PO Qty with previous one.
        if (sMsg.A.getValidCount() > 0) {
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {

                // START 02/18/2020 T.Ogura [QC#55916,ADD]
                if (ZYPConstant.FLG_OFF_N.equals(sMsg.A.no(i).openPoFlg_A1.getValue())) {
                    continue;
                }
                // END   02/18/2020 T.Ogura [QC#55916,ADD]

                String mdseCd = sMsg.A.no(i).mdseCd_A1.getValue();
                String poOrdDtlLineNum = sMsg.A.no(i).poOrdDtlLineNum_A1.getValue();

                // check RWS
                NPAL1500Query query = NPAL1500Query.getInstance();
                // QC#25314 Mod Start
                // BigDecimal rwsCnt =
                // query.countValidRwsForSubmmit(glblCmpyCd,
                // sMsg.poNum.getValue());
                BigDecimal rwsCnt = query.countValidRwsForSubmmit(glblCmpyCd, sMsg.poNum.getValue(), poOrdDtlLineNum, mdseCd);

                BigDecimal poDispQty = sMsg.A.no(i).poDispQty_A1.getValue();
                if (rwsCnt != null) {
                    if (poDispQty.compareTo(rwsCnt) < 0) {
                        // START 2019/10/30 M.Naito [QC#53811,ADD]
                        sMsg.A.no(i).poDispQty_A1.setErrorInfo(1, NPAM1645E);
                        // END 2019/10/30 M.Naito [QC#53811,ADD]
                        rtrnCd = false;
                        // Exist RWS
                        // for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                        // rtrnCd = checkRWSDetail(sMsg, rtrnCd, i);
                        // }
                        // QC#25314 Mod End
                    }
                }
            }
        }
        return rtrnCd;
    }

    // QC#25314 Delete Start
//    /**
//     * checkRWSDetail
//     * @param sMsg NPAL1500SMsg
//     * @param rtrnCd boolean
//     * @param i int
//     * @return boolean
//     */
//    private static boolean checkRWSDetail(NPAL1500SMsg sMsg, boolean rtrnCd, int i) {
//        boolean result = rtrnCd;
//        if (PO_STS.VALIDATED.equals(sMsg.A.no(i).poStsCd_HD.getValue()) //
//                || PO_STS.PO_CONFIRMED.equals(sMsg.A.no(i).poStsCd_HD.getValue())//
//                || PO_STS.PO_ERROR.equals(sMsg.A.no(i).poStsCd_HD.getValue())//
//                || PO_STS.RECEIVING.equals(sMsg.A.no(i).poStsCd_HD.getValue())) {
//
//            BigDecimal poDispQtyPrev = sMsg.A.no(i).poDispQty_HD.getValue();
//            BigDecimal poDispQty = sMsg.A.no(i).poDispQty_A1.getValue();
//
//            // When reduced qty.
//            if (poDispQty.compareTo(poDispQtyPrev) < 0) {
//                result = false;
//            }
//        }
//        return result;
//    }
    // QC#25314 Delete End

    /**
     * hasExistMdseCd
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return true: Exist / false: does not exist
     */
    private static boolean hasExistMdseCd(String glblCmpyCd, String mdseCd) {

        ALL_MDSE_VTMsg cond = new ALL_MDSE_VTMsg();
        cond.setSQLID("003");
        cond.setConditionValue("glblCmpyCd01", glblCmpyCd);
        cond.setConditionValue("mdseCd01", mdseCd);

        int count = EZDTBLAccessor.count(cond);

        if (count == 0) {
            return false;
        } else {
            return true;
        }
    }
    /**
     * Check MDSE ASL & Vendor UOM
     * @param bizMsg NPAL1500CMsg
     * @param glblMsg NPAL1500SMsg
     * @param index int
     * @return NPZC129001PMsg
     */
    private static boolean checkMdseAslAndVndUom(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg, int index) {

        // QC#16432 if BuyBack. do not asl check
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

        // QC#16432 END

        // Get Primary Vendor from ASL
        NPZC113001PMsg npzc113001PMsg = NPAL1500CommonLogic.execGetPrimVndFromAsl(bizMsg, glblMsg, index);

        if (npzc113001PMsg == null) {

            return false;
        }

        // QC#53271 Mod Start
        if (ZYPCommonFunc.hasValue(glblMsg.A.no(index).aslMdseCd_A1) &&
    			!PO_LINE_TP.EXPENSE.equals(glblMsg.A.no(index).poLineTpCd_A1.getValue()) &&
    					!PO_LINE_TP.EXPENSE_WITH_RECEIPT.equals(glblMsg.A.no(index).poLineTpCd_A1.getValue())) {

        	if (ZYPCommonFunc.hasValue(npzc113001PMsg.splyItemNum)) {

                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).aslMdseCd_A1, npzc113001PMsg.splyItemNum);

            } else {

                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).aslMdseCd_A1, npzc113001PMsg.mdseCd);
            }
        }
        // QC#53271 Mod End

        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).poDispUomCd_A1, npzc113001PMsg.vndUomCd);
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).aslDtlPk_HD, npzc113001PMsg.aslDtlPk);
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).aslUnitPrcAmt_HD, npzc113001PMsg.unitPrcAmt);

        // Call Change Vendor UOM API
        NPZC129001PMsg npzc129001PMsg = NPAL1500CommonLogic.execChgVndUom(bizMsg, glblMsg, index, glblMsg.A.no(index).poDispQty_A1.getValue());

        if (npzc129001PMsg == null) {

            // QC#19693 Add.
            glblMsg.A.no(index).entDealNetUnitPrcAmt_A1.clear();
            glblMsg.A.no(index).aslMdseCd_A1.clear();
            glblMsg.A.no(index).poDispQty_A1.clear();

            return false;
        }

        BigDecimal qty = glblMsg.A.no(index).poDispQty_A1.getValue().setScale(0);
        BigDecimal extTotal = glblMsg.A.no(index).entDealNetUnitPrcAmt_A1.getValue().multiply(qty);
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).entPoDtlDealNetAmt_A1, extTotal);

        return true;
    }

    /**
     * callAddrValidApi
     * @param cMsg NPAL1500CMsg
     * @param glblCmpyCd String
     */
    private static boolean callAddrValidApi(NPAL1500CMsg cMsg, String glblCmpyCd) {
        boolean rtrnCd = true;

        // Address Line check
        String shipAddressLine = cMsg.shipToFirstLineAddr_ST.getValue() + cMsg.shipToScdLineAddr_ST.getValue();
        String firstAddr = cMsg.shipToFirstLineAddr_ST.getValue();
        String scdAddr = cMsg.shipToScdLineAddr_ST.getValue();
        String xxAddrLine = cMsg.xxAllLineAddr_ST.getValue();
        if (ZYPCommonFunc.hasValue(shipAddressLine) && !xxAddrLine.startsWith(shipAddressLine)) {
            // Edit Address Line
            if (xxAddrLine.length() > 60) {
                firstAddr = xxAddrLine.substring(0, 60);
                scdAddr = xxAddrLine.substring(60);
                if (scdAddr.length() > 60) {
                    scdAddr = scdAddr.substring(0, 60);
                }
            } else {
                firstAddr = xxAddrLine;
                scdAddr = "";
            }
        } else if (!ZYPCommonFunc.hasValue(shipAddressLine)) {
            // Edit Address Line & not derive Address.
            if (xxAddrLine.length() > 60) {
                firstAddr = xxAddrLine.substring(0, 60);
                scdAddr = xxAddrLine.substring(60);
                if (scdAddr.length() > 60) {
                    scdAddr = scdAddr.substring(0, 60);
                }
            } else {
                firstAddr = xxAddrLine;
                scdAddr = "";
            }
        }

        NMZC003001PMsg addrValidApiPMsg = new NMZC003001PMsg();
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.firstLineAddr, firstAddr);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.scdLineAddr, scdAddr);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.ctyAddr, cMsg.shipToCtyAddr_ST);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.stCd, cMsg.shipToStCd_ST);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.postCd, cMsg.shipToPostCd_ST);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.ctryCd, cMsg.shipToCtryCd_ST);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.cntyNm, cMsg.shipToCntyNm_ST);

        NMZC003001 addrValidApi = new NMZC003001();
        addrValidApi.execute(addrValidApiPMsg, ONBATCH_TYPE.ONLINE);

        List<String> msgIdList = S21ApiUtil.getXxMsgIdList(addrValidApiPMsg);

        // check Error
        // Address Line
        if (NMZC0030_ERROR.equals(addrValidApiPMsg.xxVldStsCd_01.getValue()) //
                || NMZC0030_ERROR.equals(addrValidApiPMsg.xxVldStsCd_02.getValue())) {
            cMsg.xxAllLineAddr_ST.setErrorInfo(1, NPAM0076E, new String[] {"Ship to Location Address Line" });
            rtrnCd = false;
        }
        // City
        if (NMZC0030_ERROR.equals(addrValidApiPMsg.xxVldStsCd_03.getValue())) {
            cMsg.shipToCtyAddr_ST.setErrorInfo(1, NPAM0076E, new String[] {"Ship to Location City" });
            rtrnCd = false;
        }
        // State
        if (NMZC0030_ERROR.equals(addrValidApiPMsg.xxVldStsCd_04.getValue())) {
            cMsg.shipToStCd_ST.setErrorInfo(1, NPAM0076E, new String[] {"Ship to Location State" });
            rtrnCd = false;
        }
        // Postal code
        if (NMZC0030_ERROR.equals(addrValidApiPMsg.xxVldStsCd_05.getValue())) {
            cMsg.shipToPostCd_ST.setErrorInfo(1, NPAM0076E, new String[] {"Ship to Location Postal Code" });
            rtrnCd = false;
        }
        // Ctry
        if (NMZC0030_ERROR.equals(addrValidApiPMsg.xxVldStsCd_06.getValue())) {
            cMsg.shipToCtryCd_ST.setErrorInfo(1, NPAM0076E, new String[] {"Ship to Location Country" });
            rtrnCd = false;
        }
        // Ctry
        if (NMZC0030_ERROR.equals(addrValidApiPMsg.xxVldStsCd_07.getValue())) {
            cMsg.shipToCntyNm_ST.setErrorInfo(1, NPAM0076E, new String[] {"Ship to Location County" });
            rtrnCd = false;
        }

        if (msgIdList != null && msgIdList.size() > 0) {
            for (int i = 0; i < msgIdList.size(); i++) {

                setAddrValidResult((String) msgIdList.get(0), cMsg.shipToCtyAddr_ST, addrValidApiPMsg.xxVldStsCd_03);
                setAddrValidResult((String) msgIdList.get(0), cMsg.shipToStCd_ST, addrValidApiPMsg.xxVldStsCd_04);
                setAddrValidResult((String) msgIdList.get(0), cMsg.shipToPostCd_ST, addrValidApiPMsg.xxVldStsCd_05);
                setAddrValidResult((String) msgIdList.get(0), cMsg.shipToCtryCd_ST, addrValidApiPMsg.xxVldStsCd_06);
                setAddrValidResult((String) msgIdList.get(0), cMsg.shipToCntyNm_ST, addrValidApiPMsg.xxVldStsCd_07);

                rtrnCd = false;
            }
        }

        // Not Error => replace address data.
        if (rtrnCd) {
            ZYPEZDItemValueSetter.setValue(cMsg.shipToFirstLineAddr_ST, addrValidApiPMsg.firstLineAddr);
            ZYPEZDItemValueSetter.setValue(cMsg.shipToScdLineAddr_ST, addrValidApiPMsg.scdLineAddr);
            ZYPEZDItemValueSetter.setValue(cMsg.shipToCtyAddr_ST, addrValidApiPMsg.ctyAddr);
            ZYPEZDItemValueSetter.setValue(cMsg.shipToStCd_ST, addrValidApiPMsg.stCd);
            ZYPEZDItemValueSetter.setValue(cMsg.shipToPostCd_ST, addrValidApiPMsg.postCd);
            ZYPEZDItemValueSetter.setValue(cMsg.shipToCtryCd_ST, addrValidApiPMsg.ctryCd);
            ZYPEZDItemValueSetter.setValue(cMsg.shipToCntyNm_ST, addrValidApiPMsg.cntyNm);

            String addrLine = cMsg.shipToFirstLineAddr_ST.getValue() //
                    + cMsg.shipToScdLineAddr_ST.getValue() //
                    + cMsg.shipToThirdLineAddr_ST.getValue() //
                    + cMsg.shipToFrthLineAddr_ST.getValue();

            ZYPEZDItemValueSetter.setValue(cMsg.xxAllLineAddr_ST, addrLine);
        }

        return rtrnCd;
    }

    /**
     * checkApiResult
     * @param rtnPMsg EZDPMsg
     * @param cMsg NPAL1500CMsg
     * @param boolean
     */
    private static boolean checkApiResult(EZDPMsg rtnPMsg, NPAL1500CMsg cMsg) {

        List<String> msgIdList = S21ApiUtil.getXxMsgIdList(rtnPMsg);

        if (msgIdList.size() == 0) {
            // It has no Message.
            return true;
        }

        for (int i = 0; i < msgIdList.size(); i++) {
            String xxMsgId = msgIdList.get(i);
            if (xxMsgId.endsWith("E")) {
                // set first Error Message
                cMsg.setMessageInfo(xxMsgId);
                return false;
            }
        }

        int lastIndex = msgIdList.size() - 1;

        // set latest Warning or Information Message
        cMsg.setMessageInfo(msgIdList.get(lastIndex));

        return true;
    }

    /**
     * checkPrintParam
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     * @param boolean
     */
    private static void checkPrintParam(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.poHdrStsCd)) {
            cMsg.setMessageInfo(NPAM1268E);
        } else if (!ZYPCommonFunc.hasValue(cMsg.poApvlDt)) {
            cMsg.setMessageInfo(NPZM0081E);
        } else if (ZYPCommonFunc.hasValue(cMsg.trsmtMethTpCd)) {
            if (TRSMT_METH_TP.EMAIL_PDF.equals(cMsg.trsmtMethTpCd.getValue())) { // E-mail
                if (!ZYPCommonFunc.hasValue(cMsg.sendPoEmlAddr)) {
                    cMsg.setMessageInfo(NPAM1329E, new String[] {cMsg.sendPoEmlAddr.getValue() });
                }
            } else if (TRSMT_METH_TP.FAX.equals(cMsg.trsmtMethTpCd.getValue())) { // FAX
                if (!ZYPCommonFunc.hasValue(cMsg.sendPoFaxNum)) {
                    cMsg.setMessageInfo(NPAM1329E, new String[] {cMsg.sendPoFaxNum.getValue() });
                }
            } else if (TRSMT_METH_TP.PRINTER.equals(cMsg.trsmtMethTpCd.getValue())) { // Print
                if (!ZYPCommonFunc.hasValue(cMsg.rptDestId)) {
                    cMsg.setMessageInfo(NPAM1329E, new String[] {cMsg.rptDestId.getValue() });
                }
            }
        }
    }

    /**
     * createWrkTableForReport
     * @param cMsg NPAL1500CMsg
     */
    private static void createWrkTableForReport(NPAL1500CMsg cMsg) {
        // execute NPZC0070 Create PO Report API
        NPZC007001 poReportAPI = new NPZC007001();

        NPZC007001PMsg poReportParam = new NPZC007001PMsg();
        ZYPEZDItemValueSetter.setValue(poReportParam.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(poReportParam.whCd, cMsg.shipToCustCd);
        poReportParam.poChrgCd.clear();
        ZYPEZDItemValueSetter.setValue(poReportParam.vndCd, cMsg.vndCd);
        ZYPEZDItemValueSetter.setValue(poReportParam.poOrdNum, cMsg.poNum);
        ZYPEZDItemValueSetter.setValue(poReportParam.usrId, cMsg.poSubmtPsnCd);
        ZYPEZDItemValueSetter.setValue(poReportParam.procDt, ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()));
        //QC#18602(Sol#102) ADD Start
        // Add Print Open Line Flag
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_LO.getValue())) {
            ZYPEZDItemValueSetter.setValue(poReportParam.openFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(poReportParam.openFlg, ZYPConstant.FLG_OFF_N);
        }
        //QC#18602(Sol#102) ADD End

        poReportAPI.execute(poReportParam, ONBATCH_TYPE.ONLINE);
        checkApiResult(poReportParam, cMsg);

        ZYPEZDItemValueSetter.setValue(cMsg.xxTsDsp19Txt_PR, poReportParam.rcvRptTs);
        ZYPEZDItemValueSetter.setValue(cMsg.poRptPrintRqstSq_PR, poReportParam.poRptPrintRqstSq);
    }

    /**
     * execute executePOCreateApi(NPZC104001)
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     * @param pMsg NPZC004001PMsg
     * @return boolean true : normal end, false : abnormal end
     */
    // START 2019/08/02 T.Ogura [QC#51448,MOD]
//    private static boolean executePOCreateApi(NPAL1500CMsg cMsg, NPZC104001PMsg pMsg) {
    private static boolean executePOCreateApi(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg, NPZC104001PMsg pMsg) {
    // END   2019/08/02 T.Ogura [QC#51448,MOD]

        NPZC104001 api = new NPZC104001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(pMsg)) {

            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(pMsg);

            for (String xxMsgId : xxMsgIdList) {

                S21InfoLogOutput.println(xxMsgId);
                cMsg.setMessageInfo(xxMsgId);

                if (xxMsgId.endsWith(MESSAGE_KIND_W)) {

                    ZYPEZDItemValueSetter.setValue(cMsg.poNum, pMsg.poOrdNum);
                }
            }

            int errIndex = -1;    // 2019/08/02 T.Ogura [QC#51448,ADD]
            for (int i = 0; i < pMsg.poLineInfo.getValidCount(); i++) {

                if (ZYPCommonFunc.hasValue(pMsg.poLineInfo.no(i).xxMsgId)) {

                    S21InfoLogOutput.println(pMsg.poLineInfo.no(i).xxMsgId.getValue());
                    // START 2019/08/02 T.Ogura [QC#51448,MOD]
//                    cMsg.A.no(i).poDispQty_A1.setErrorInfo(1, pMsg.poLineInfo.no(i).xxMsgId.getValue());
                    sMsg.A.no(i).poDispQty_A1.setErrorInfo(1, pMsg.poLineInfo.no(i).xxMsgId.getValue());
                    if (errIndex == -1) {
                        errIndex = i;
                    }
                    // END   2019/08/02 T.Ogura [QC#51448,MOD]

                }
            }

            // START 2019/08/02 T.Ogura [QC#51448,ADD]
            if (errIndex != -1) {
                int errScrnInex = (errIndex / cMsg.A.length()) * cMsg.A.length() + 1;
                cMsg.xxPageShowFromNum.setValue(errScrnInex);
                NPAL1500CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
            }
            // END   2019/08/02 T.Ogura [QC#51448,ADD]

            return true;

        } else {

            // START 2019/08/02 T.Ogura [QC#51448,DEL]
//            for (int i = 0; i < pMsg.poLineInfo.getValidCount(); i++) {
//
//                if (ZYPCommonFunc.hasValue(pMsg.poLineInfo.no(i).xxMsgId)) {
//
//                    S21InfoLogOutput.println(pMsg.poLineInfo.no(i).xxMsgId.getValue());
//                    cMsg.setMessageInfo(pMsg.poLineInfo.no(i).xxMsgId.getValue());
//                    return true;
//                }
//            }
            // END   2019/08/02 T.Ogura [QC#51448,DEL]

            ZYPEZDItemValueSetter.setValue(cMsg.poNum, pMsg.poOrdNum);
        }
        return false;
    }

    /**
     * execute Shipping Plan Update API (NWZC003001)
     * @param cMsg NPAL1500CMsg
     * @param glblCmpyCd String
     * @param shpgPlnNumList List<String> update SHPG_PLN_NUM 's list
     */
    private static void executeShippingPlanUpdateApi(NPAL1500CMsg cMsg, String glblCmpyCd, List<String> shpgPlnNumList) {

        List<NWZC003001PMsg> paramList = new ArrayList<NWZC003001PMsg>();

        for (String shpgPlnNum : shpgPlnNumList) {
            NWZC003001PMsg param = new NWZC003001PMsg();
            ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(param.shpgModeCd, "16");
            ZYPEZDItemValueSetter.setValue(param.shpgPlnNum, shpgPlnNum);
            ZYPEZDItemValueSetter.setValue(param.slsDt, ZYPDateUtil.getSalesDate());

            paramList.add(param);
        }

        NWZC003001 apiInstance = new NWZC003001();
        apiInstance.execute(paramList, ONBATCH_TYPE.ONLINE);

        for (NWZC003001PMsg param : paramList) {
            if (S21ApiUtil.isXxMsgId(param)) {
                cMsg.setMessageInfo(param.xxMsgIdList.no(0).xxMsgId.getValue());
                cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                return;
            }
        }
    }

    /**
     * findShipToCust
     * @param glblCmpyCd String
     * @return boolean
     */
    private static boolean isRegisteredShipToCust(NPAL1500SMsg sMsg) {

        if (ZYPCommonFunc.hasValue(sMsg.shipToCustCd.getValue())) {

            SHIP_TO_CUSTTMsg reqShipToCustTMsg = new SHIP_TO_CUSTTMsg();
            reqShipToCustTMsg.setSQLID("024");
            reqShipToCustTMsg.setConditionValue("glblCmpyCd01", sMsg.glblCmpyCd.getValue());
            reqShipToCustTMsg.setConditionValue("shipToCustCd01", sMsg.shipToCustCd.getValue());
            reqShipToCustTMsg.setConditionValue("rgtnStsCd01", "P20");
            reqShipToCustTMsg.setMaxCount(1);

            EZDTMsgArray resTMsgArray = findByCondition(reqShipToCustTMsg);

            if (!NPAL1500CommonLogic.hasValidCount(resTMsgArray)) {
                return false;
            }
        }
        return true;
    }

    /**
     * get Vender Order Qty from ASL
     * @param glblMsg NPAL1500SMsg
     * @param salesDt String
     * @param asMsg NPAL1500_ASMsg
     * @return BigDecimal
     */
    private static BigDecimal getVndOrdQty(NPAL1500SMsg glblMsg , String salesDt, NPAL1500_ASMsg asMsg) {

        BigDecimal retVndOrdQty = new BigDecimal(0);

        List<Map<String, Object>> resultMapList = null;

        S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getVndOrdQty(glblMsg, salesDt, asMsg);

        if (ssmResult.isCodeNormal()) {

            resultMapList = (List<Map<String, Object>>) ssmResult.getResultObject();

            if (resultMapList != null && !resultMapList.isEmpty()) {

                Map<String, Object> resultMap = (Map<String, Object>) resultMapList.get(0);
                String vndUomCd = (String) resultMap.get("VND_UOM_CD");

                if (VND_UOM.EACH.equals(vndUomCd)) { // EACH(PoDispQty)
                    retVndOrdQty = new BigDecimal(asMsg.poDispQty_A1.getValueInt());
                } else { // <> EACH ( PoDispQty * [ASL]vndIncrOrdQty )
                    retVndOrdQty = new BigDecimal(asMsg.poDispQty_A1.getValueInt()).multiply((BigDecimal) resultMap.get("VND_INCR_ORD_QTY"));
                }
            }
        } else {
            retVndOrdQty = new BigDecimal(asMsg.poDispQty_A1.getValueInt());
        }

        return retVndOrdQty;
    }

    /**
     * OutBound Carrier(OTBD_CARR_VTMsg) existing check
     * @param sMsg NPAL1500SMsg
     * @return boolean
     */
    private static boolean isExistOtbdCarrCd(NPAL1500SMsg sMsg) {

        OTBD_CARR_VTMsg otbdCarrVTMsg = new OTBD_CARR_VTMsg();

        otbdCarrVTMsg.setSQLID("002");
        otbdCarrVTMsg.setConditionValue("glblCmpyCd01", sMsg.glblCmpyCd.getValue());
        otbdCarrVTMsg.setConditionValue("carrCd01", sMsg.carrCd.getValue());
        OTBD_CARR_VTMsgArray resultList = (OTBD_CARR_VTMsgArray) EZDTBLAccessor.findByCondition(otbdCarrVTMsg);

        if (NPAL1500CommonLogic.hasValidCount(resultList)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * print
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     */
    private static void print(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg) {

        /** Init **/
        String rcvPrtTs = "";
        BigDecimal eipRptRqstPk = new BigDecimal(ZERO);
        BigDecimal poRptPrintRqstSq = new BigDecimal(ZERO);

        if (TRSMT_METH_TP.PDF_DOWNLOAD.equals(cMsg.trsmtMethTpCd.getValue())) {
            // Direct Print
            createWrkTableForReport(cMsg);
        } else {
            // Email Print or Printer

            createWrkTableForReport(cMsg);
            rcvPrtTs = cMsg.xxTsDsp19Txt_PR.getValue();
            poRptPrintRqstSq = cMsg.poRptPrintRqstSq_PR.getValue();

            // execute NPZC0050 Purchase Order API
            NPZC005001PMsg printApiParam = new NPZC005001PMsg();
            ZYPEZDItemValueSetter.setValue(printApiParam.glblCmpyCd, cMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(printApiParam.userId, cMsg.poSubmtPsnCd);
            ZYPEZDItemValueSetter.setValue(printApiParam.poOrdNum, cMsg.poNum);
            ZYPEZDItemValueSetter.setValue(printApiParam.procStartTs, rcvPrtTs);
            ZYPEZDItemValueSetter.setValue(printApiParam.poRptPrintRqstSq, poRptPrintRqstSq);

            if (ZYPCommonFunc.hasValue(cMsg.trsmtMethTpCd)) {
                ZYPEZDItemValueSetter.setValue(printApiParam.trsmtMethTpCd, cMsg.trsmtMethTpCd);

                if (TRSMT_METH_TP.EMAIL_PDF.equals(cMsg.trsmtMethTpCd.getValue())) {
                    printEmailOrPdf(cMsg, printApiParam);

                } else if (TRSMT_METH_TP.FAX.equals(cMsg.trsmtMethTpCd.getValue())) { // FAX
                    ZYPEZDItemValueSetter.setValue(printApiParam.faxNum, cMsg.sendPoFaxNum);

                } else if (TRSMT_METH_TP.PRINTER.equals(cMsg.trsmtMethTpCd.getValue())) { // PRINTER
                    ZYPEZDItemValueSetter.setValue(printApiParam.rptDestId, cMsg.rptDestId);
                }
            } else {
                ZYPEZDItemValueSetter.setValue(printApiParam.trsmtMethTpCd, TRSMT_METH_TP.PDF_DOWNLOAD);
            }

            NPZC005001 printAPI = new NPZC005001();
            printAPI.execute(printApiParam, ONBATCH_TYPE.ONLINE);
            if (!checkApiResult(printApiParam, cMsg)) {
                return;
            }

            eipRptRqstPk = printApiParam.eipRptRqstPk.getValue();

            // execute NPZC0050 Purchase Order API
            NPZC104001PMsg printParam = setNPZC104001PMsgForPrint(cMsg, sMsg, rcvPrtTs, eipRptRqstPk);

            // START 2019/08/02 T.Ogura [QC#51448,MOD]
//            executePOCreateApi(cMsg, printParam);
            executePOCreateApi(cMsg, sMsg, printParam);
            // END   2019/08/02 T.Ogura [QC#51448,MOD]

            if (!checkApiResult(printParam, cMsg)) {
                return;
            }
            ZYPEZDItemValueSetter.setValue(cMsg.eipRptRqstPk, eipRptRqstPk);
        }
    }

    /**
     * printEmailOrPdf
     * @param cMsg NPAL1500CMsg
     * @param printApiParam NPZC005001PMsg
     */
    private static void printEmailOrPdf(NPAL1500CMsg cMsg, NPZC005001PMsg printApiParam) {
        // E-MAIL
        // PDF
        if (ZYPCommonFunc.hasValue(cMsg.sendPoEmlAddr)) {
            int cnt = 0;
            int idx = 0;
            String emlAddr = cMsg.sendPoEmlAddr.getValue();

            while (emlAddr != null && emlAddr.length() > MAIL_ADDRESS_LENGTH) {
                idx = emlAddr.lastIndexOf(COMMA, MAIL_ADDRESS_LENGTH);
                printApiParam.sendPoEmlAddrList.no(cnt).emlToAddr.setValue(emlAddr.substring(0, idx));
                emlAddr = emlAddr.substring(idx + 1);
                cnt++;
            }

            if (ZYPCommonFunc.hasValue(emlAddr)) {
                printApiParam.sendPoEmlAddrList.no(cnt).emlToAddr.setValue(emlAddr);
                cnt++;
            }
            printApiParam.sendPoEmlAddrList.setValidCount(cnt);
        }
    }

    /**
     * moveErrPage
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     */
    private static void moveErrPage(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg) {

        // Check Addl Header
        // QC#23726 Update.
        if (cMsg.shipToPostCd_ST.isError() //
                || cMsg.shipToCtyAddr_ST.isError()//
                || cMsg.shipToStCd_ST.isError()//
                || cMsg.shipToCtryCd_ST.isError()//
                || cMsg.carrCd.isError() //
                || cMsg.shpgSvcLvlCd.isError() //
                // QC#24918
                || cMsg.xxDsMultMsgDplyTxt_SI.isError()) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_ADDL_HEADER);
            return;
        }

        boolean error = false;
        int index = 0;
        for (; index < sMsg.A.getValidCount(); index++) {
            if (sMsg.A.no(index).poLineTpCd_A1.isError()
                    || sMsg.A.no(index).mdseCd_A1.isError()
                    || sMsg.A.no(index).entDealNetUnitPrcAmt_A1.isError()
                    || sMsg.A.no(index).poDispQty_A1.isError()
                    || sMsg.A.no(index).rqstRcvDt_A1.isError()
                    // START 2023/02/14 S.Dong [QC#60966, ADD]
                    || sMsg.A.no(index).rqstShipDt_A1.isError()
                    // END 2023/02/14 S.Dong [QC#60966, ADD]
                    || sMsg.A.no(index).destRtlSwhCd_A1.isError()
                    || sMsg.A.no(index).srcRtlSwhCd_A1.isError()
                    || sMsg.A.no(index).stkStsCd_A1.isError()
                    || sMsg.A.no(index).serNumListTxt_A1.isError()
                    || sMsg.A.no(index).xxChkBox_A1.isError()
                    || sMsg.A.no(index).xxScrItem130Txt_AC.isError()
                    || sMsg.A.no(index).xxScrItem130Txt_CH.isError()
                    || sMsg.A.no(index).xxScrItem130Txt_VA.isError()) {
                error = true;
                break;
            }
        }

        if (error) {
            int length = cMsg.A.length();
            int start = (index / length) * length + 1;
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum, new BigDecimal(start));

            NPAL1500CommonLogic.pagenation(cMsg, sMsg);

            ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_DETAIL);
        }
    }

    /**
     * setAddrValidResult
     * @param msgId String
     * @param checkItem EZDCStringItem
     * @param rtnStsCd EZDPStringItem
     */
    private static void setAddrValidResult(String msgId, EZDCStringItem checkItem, EZDPStringItem rtnStsCd) {
        if (NMZC0030_ERROR.equals(rtnStsCd.getValue())) {
            // Replace Error Message.
            checkItem.clearErrorInfo();
            checkItem.setErrorInfo(1, msgId);
        }
    }

    /**
     * setNPZC104001PMsgForPrint
     * @param cMsg NPAL1500CMsg
     * @param glblMsg NPAL1500SMsg
     * @param xxRqstTs String
     * @param eipRptRqstPk BigDecimal
     * @return NPZC104001PMsg
     */
    private static NPZC104001PMsg setNPZC104001PMsgForPrint(NPAL1500CMsg cMsg, NPAL1500SMsg glblMsg, String xxRqstTs, BigDecimal eipRptRqstPk) {

        NPZC104001PMsg pMsg = new NPZC104001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC104001Constant.MODE_SEND_PO);
        pMsg.eventId.clear();
        ZYPEZDItemValueSetter.setValue(pMsg.procDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTs, xxRqstTs);
        ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum, cMsg.poNum);
        ZYPEZDItemValueSetter.setValue(pMsg.trsmtMethTpCd, cMsg.trsmtMethTpCd);
        if (ZYPCommonFunc.hasValue(cMsg.trsmtMethTpCd)) {
            if (TRSMT_METH_TP.EMAIL_PDF.equals(cMsg.trsmtMethTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(pMsg.sendPoEmlAddr, cMsg.sendPoEmlAddr);
            } else if (TRSMT_METH_TP.FAX.equals(cMsg.trsmtMethTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(pMsg.sendPoFaxNum, cMsg.trsmtMethTpCd);
            }
        }
        ZYPEZDItemValueSetter.setValue(pMsg.poSendFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(pMsg.poSendTs, xxRqstTs);
        ZYPEZDItemValueSetter.setValue(pMsg.poPrintFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(pMsg.eipRptRqstPk, eipRptRqstPk);
        pMsg.vndIssOrdNum.clear();
        pMsg.poLineInfo.clear();

        return pMsg;
    }

    /**
     * setDeriveItemForSaveAndSubmit
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     * @return boolean
     */
    private static boolean setDeriveItemForSaveAndSubmit(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg) {

        boolean hasErr = false;
        for (int indexS = 0; indexS < sMsg.A.getValidCount(); indexS++) {

            // START 02/18/2020 T.Ogura [QC#55916,ADD]
            if (ZYPConstant.FLG_OFF_N.equals(sMsg.A.no(indexS).openPoFlg_A1.getValue())) {
                continue;
            }
            // END   02/18/2020 T.Ogura [QC#55916,ADD]

            if ("E".equals(cMsg.getMessageKind())) {
                hasErr = true;
            }
            if (ZYPCommonFunc.hasValue(sMsg.A.no(indexS).xxCntDplyFlg_A2) //
                    && (ZYPConstant.FLG_OFF_N.equals(sMsg.A.no(indexS).xxCntDplyFlg_A2.getValue()) //
                    && sMsg.A.no(indexS).mdseCd_A1.isError())) {

                hasErr = true;
                continue;
            }
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(indexS).mdseDescShortTxt_A1)) {
                sMsg.A.no(indexS).poMdseCmpsnTpCd_HD.clear();
                // Set Item Info
                if (DS_PO_TP.BUYBACK_PO.equals(cMsg.dsPoTpCd.getValue())) {

                    if (!NPAL1500CommonLogic.setDeriveItemInfoBB(cMsg, sMsg, indexS)) {

                        hasErr = true;
                    }
                } else {
                    // Set PRNT_CMPY_SET_MDSE_FLG
                    if (NPAL1500CommonLogic.setPrntCmpySetMdseFlg(cMsg, sMsg, indexS)) {
                        hasErr = NPAL1500CommonLogic.setMdseInfo(cMsg, sMsg, indexS, hasErr, true);
                    } else {

                        hasErr = true;

                    }
                }
            }
        }

        if (hasErr) {
            return true;
        } else {
            List<BigDecimal> ordDtlLineList = new ArrayList<BigDecimal>();
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                ordDtlLineList.add(new BigDecimal(sMsg.A.no(i).dispPoDtlLineNum_A1.getValue()));
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
                for (int idxPoDetail = 0; idxPoDetail < sMsg.A.getValidCount(); idxPoDetail++) {
                    if (NPAL1500CommonLogic.sortSetPoOrdDtlLineNum(ordDtlLineList, sMsg, j, idxPoDetail, excludeLineList)) {
                        break;
                    }
                }
            }

            S21SortTarget target = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey key = new S21SortKey();
            key.add("poOrdDtlLineNum_A1", "ASC");
            S21EZDMsgArraySort.sort(target, key, 0, sMsg.A.getValidCount());
        }
        return false;
    }

    /**
     * checkItemMasterForSet
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     * @return boolean
     */
    private static boolean checkItemMasterForSet(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg, S21LRUMap<Object, S21SsmEZDResult> deriveItemInfoCache) {

        boolean isErr = true;
 
        for (int indexS = 0; indexS < sMsg.A.getValidCount(); indexS++) {

            // START 02/18/2020 T.Ogura [QC#55916,ADD]
            if (ZYPConstant.FLG_OFF_N.equals(sMsg.A.no(indexS).openPoFlg_A1.getValue())) {
                continue;
            }
            // END   02/18/2020 T.Ogura [QC#55916,ADD]

            if (ZYPCommonFunc.hasValue(sMsg.A.no(indexS).xxCntDplyFlg_A2) //
                    && (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(indexS).xxCntDplyFlg_A2.getValue()) //
                    || sMsg.A.no(indexS).mdseCd_A1.isError())) {

                continue;
            }

            // START
            if (ZYPCommonFunc.hasValue(sMsg.A.no(indexS).mdseCd_A1)) {
                // END

                String mdseCd = sMsg.A.no(indexS).mdseCd_A1.getValue();
                // QC#27127
                S21SsmEZDResult ssmResult = deriveItemInfoCache.get(mdseCd);
                if (ssmResult == null) {
                    ssmResult = NPAL1500Query.getInstance().getDeriveItemInfo(cMsg, sMsg.A.no(indexS).mdseCd_A1.getValue());
                    deriveItemInfoCache.put(mdseCd, ssmResult);
                }

                if (!ssmResult.isCodeNormal()) {
                    MDSETMsg mdseTMsg = new MDSETMsg();

                    ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, sMsg.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, sMsg.A.no(indexS).mdseCd_A1);

                    mdseTMsg = (MDSETMsg) EZDTBLAccessor.findByKey(mdseTMsg);
                    if (mdseTMsg != null && "16".equals(mdseTMsg.mdseItemTpCd.getValue())) {

                        sMsg.A.no(indexS).xxCntDplyFlg_A2.setValue(ZYPConstant.FLG_ON_Y);
                        sMsg.A.no(indexS).mdseCd_A1.clearErrorInfo();
                        return isErr;
                    }
                    // Error
                    sMsg.A.no(indexS).mdseCd_A1.setErrorInfo(1, NPAM0076E, new String[] {sMsg.A.no(indexS).mdseCd_A1.getValue() });
                    cMsg.setMessageInfo(NPAM0076E, new String[] {"Item#" });
                    isErr = false;
                }
            }
        }
        return isErr;
    }
    
    // START 2017/12/12 [QC#14858, ADD] 
    // if Expense w/Receipt, update Received Qty.
    /**
     * registeDetail
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     * @param pMsg NPZC004001PMsg
     * @param i int
     * @return boolean
     */
    private static boolean registDetailForExpenseWReceipt(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg, NPZC004001PMsg pMsg, int i) {
        if (ZYPCommonFunc.hasValue(sMsg.A.no(i).poRcvQty_A1) //
                && (!PO_STS.CLOSED.equals(sMsg.A.no(i).poStsCd_HD.getValue()) //
                && !PO_STS.CANCELLED.equals(sMsg.A.no(i).poStsCd_HD.getValue()))) {
            // execute PO Status update API (NPZC0040)
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, sMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.poStsCd, PO_STS.RECEIVING);
            ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum, sMsg.poNum.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, sMsg.A.no(i).mdseCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.poOrdDtlLineNum, sMsg.A.no(i).poOrdDtlLineNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.poRcvQty, sMsg.A.no(i).poRcvQty_A1.getValue());

            NPZC004001 api = new NPZC004001();
            api.execute(pMsg, ONBATCH_TYPE.ONLINE);

            if (S21ApiUtil.isXxMsgId(pMsg)) {
                cMsg.setMessageInfo(pMsg.xxMsgIdList.no(0).xxMsgId.getValue());
                cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                return false;
            }
        }

        return true;
    }
    // END 2017/12/12 [QC#14858, ADD] registDetailForExpenseWReceipt

    //QC#18602(Sol#102) ADD Start
    /**
     * checkPoLineForBP
     * @param sMsg NPAL1500SMsg
     * @param rtrnCd boolean
     * @return boolean rtrnCd
     */
    private static boolean checkPoLineForBP(NPAL1500SMsg sMsg, boolean rtrnCd) {
        boolean result = rtrnCd;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            // START 02/18/2020 T.Ogura [QC#55916,ADD]
            if (ZYPConstant.FLG_OFF_N.equals(sMsg.A.no(i).openPoFlg_A1.getValue())) {
                continue;
            }
            // END   02/18/2020 T.Ogura [QC#55916,ADD]

            if (!PO_LINE_TP.EXPENSE.equals(sMsg.A.no(i).poLineTpCd_A1.getValue())) {
                
                sMsg.A.no(i).poLineTpCd_A1.setErrorInfo(1, NPAM1612E);
                result = false;
            }
        }
        return result;
    }
    //QC#18602(Sol#102) ADD End

    //QC#25024 Add Start
    /**
     * updateInbdVis
     * @param bizMsg NPAL1500CMsg
     * @param lineMsg NPAL1500SMsg
     * @return boolean
     */
    private static boolean updateInbdVis(NPAL1500CMsg cMsg, NPAL1500SMsg sMsg, int i) {

        INBD_VISTMsg inbdVisTMsg = new INBD_VISTMsg();

        // Search INBD_VIS
        List<Map<String, Object>> resultMapList = null;
        S21SsmEZDResult ssmResult = NPAL1500Query.getInstance().getInbdVis(cMsg, sMsg, i);

        if (ssmResult.isCodeNormal() && ssmResult != null) {
            resultMapList = (List<Map<String, Object>>) ssmResult.getResultObject();
            if (resultMapList != null && !resultMapList.isEmpty()) {
                for (int j = 0; j < resultMapList.size(); j++) {
                    Map<String, Object> resultMap = (Map<String, Object>) resultMapList.get(j);
                    if (!sMsg.A.no(i).rqstRcvDt_A1.getValue().equals(resultMap.get(DB_ETA_ETD_DT))) {
                        ZYPEZDItemValueSetter.setValue(inbdVisTMsg.glblCmpyCd, sMsg.glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(inbdVisTMsg.inbdVisPk, (BigDecimal) resultMap.get("INBD_VIS_PK"));

                        inbdVisTMsg = (INBD_VISTMsg) EZDTBLAccessor.findByKey(inbdVisTMsg);
                        ZYPEZDItemValueSetter.setValue(inbdVisTMsg.etaEtdDt, sMsg.A.no(i).rqstRcvDt_A1.getValue());

                        EZDTBLAccessor.update(inbdVisTMsg);
                    }
                }
            }
        } else {
            // nothing to do.
            return true;
        }

        return true;
    }
    //QC#25024 Add End

    // START 2018/07/12 S.Katsuma [QC#26867,ADD]
    private static boolean isExistSvcMachMstr(String glblCmpyCd, String mdseCd, String serNum) {
        boolean rtrnCd = false;

        S21SsmEZDResult result = NPAL1500Query.getInstance().checkConfig(glblCmpyCd, mdseCd, serNum);
        BigDecimal count = BigDecimal.ZERO;
        if (result.isCodeNormal()) {
            count = (BigDecimal) result.getResultObject();
        }
        if (BigDecimal.ZERO.compareTo(count) < 0) {
            rtrnCd = true;
        }

        return rtrnCd;
    }
    // END 2018/07/12 S.Katsuma [QC#26867,ADD]
    
    //QC#27024 Add Start
    /**
     * validateSerialNum
     * @param glblCmpyCd String
     * @param sMsg NPAL1500SMsg
     * @param i int
     * @param serNum String
     * @return boolean
     */
    private static boolean checkReceivedSerNum(String glblCmpyCd, NPAL1500SMsg sMsg, int i, String serNum) {
        boolean rtrnCd = false;
        String mdseCd = sMsg.A.no(i).mdseCd_A1.getValue();
        
        //Receive Check
        S21SsmEZDResult result = NPAL1500Query.getInstance().checkReceivedSerNum(glblCmpyCd, mdseCd, serNum);

        BigDecimal count = BigDecimal.ZERO;
        if (result.isCodeNormal()) {
            count = (BigDecimal) result.getResultObject();
        }
        if (BigDecimal.ZERO.compareTo(count) < 0) {
            rtrnCd = true;
        }
        return rtrnCd;
    }
    //QC#27024 Add End
    // QC#28685 Add Start
    private void removeInbdVis(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {

        String mode = NPZC109001Constant.POYO_DELETE_MODE;

        NPZC109001PMsg pMsg = new NPZC109001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, mode);

        int cnt = 0;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).xxChkBox_A1)) {
                ZYPEZDItemValueSetter.setValue(pMsg.detailList.no(cnt).poOrdNum, bizMsg.poNum);
                ZYPEZDItemValueSetter.setValue(pMsg.detailList.no(cnt).poOrdDtlLineNum, bizMsg.A.no(i).poOrdDtlLineNum_A1);
                cnt++;
            }
        }
        pMsg.detailList.setValidCount(cnt);

        // Exceute POYO Update API
        NPZC109001 dPZC109001 = new NPZC109001();
        dPZC109001.execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            for (int idx = 0; idx < pMsg.xxMsgIdList.getValidCount(); idx++) {
                bizMsg.setMessageInfo(pMsg.xxMsgIdList.no(0).xxMsgId.getValue());
                bizMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                return;
            }
        }
    }
    // QC#28685 Add End

    // START 2018/12/13 M.Naito [QC#29027,ADD]
    /**
     * isOnlyCsaSetItem
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return boolean
     */
    private static boolean isOnlyCsaSetItem(String glblCmpyCd, String mdseCd) {
        boolean rtrnCd = false;

        S21SsmEZDResult result = NPAL1500Query.getInstance().checkPrntCmpySetMdse(glblCmpyCd, mdseCd);
        BigDecimal count = BigDecimal.ZERO;
        if (result.isCodeNormal()) {
            count = (BigDecimal) result.getResultObject();
        }
        if (BigDecimal.ZERO.compareTo(count) < 0) {
            rtrnCd = true;
        }

        return rtrnCd;
    }
    // END 2018/12/13 M.Naito [QC#29027,ADD]

    // START 2019/07/17 M.Naito [QC#51695,ADD]
    /**
     * chkInvtyOwnrCd
     * @param glblCmpyCd String
     * @param dsPoTpCd String
     * @param rtlWhCd String
     * @return String
     */
    private static String chkInvtyOwnrCd(String glblCmpyCd, String dsPoTpCd, String rtlWhCd) {

        String poType = ZYPCodeDataUtil.getVarCharConstValue(VAR_CONST_NOT_CHANGE_FOR_ROSS_PO_TP, glblCmpyCd);
        ArrayList<String> poTypeList = new ArrayList<String>();

        if (ZYPCommonFunc.hasValue(poType)) {
            if (poType.indexOf(",") != -1) {
                String[] splitValArray = poType.split(",");
                for (int i = 0; i < splitValArray.length; i++) {
                    if (!splitValArray[i].trim().equals("") && splitValArray[i].length() > 0) {
                        poTypeList.add(splitValArray[i].trim());
                    }
                }
            }
        }
        if (poTypeList != null) {
            if (poTypeList.contains(dsPoTpCd)) {
                return dsPoTpCd;
            }
        }
        RTL_WHTMsg rwTMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(rwTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rwTMsg.rtlWhCd, rtlWhCd);
        rwTMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(rwTMsg);
        if (rwTMsg != null) {
            if (INVTY_OWNR.GMD.equals(rwTMsg.invtyOwnrCd.getValue())) {
                dsPoTpCd = DS_PO_TP.ROSS_PO;
            }
        }
        return dsPoTpCd;
    }
    // END 2019/07/17 M.Naito [QC#51695,ADD]

    // 2019/10/09 QC#53392 Add Start
    /**
     * checkDiscountPrice
     * @param sMsg NPAL1500SMsg
     * @return true:OK false:Error
     */
    private static boolean checkDiscountPrice(NPAL1500SMsg sMsg) {
        boolean rtrnCd = true;

        // check Order Qty <= 0
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
        	if ((PO_LINE_STS.OPEN.equals(sMsg.A.no(i).poLineStsCd_A1.getValue()) || PO_LINE_STS.OPEN_FOR_RECEIPT.equals(sMsg.A.no(i).poLineStsCd_A1.getValue())
                    || PO_LINE_STS.OPEN_FOR_INVOICE.equals(sMsg.A.no(i).poLineStsCd_A1.getValue()))
                    && (!PO_MDSE_CMPSN_TP.CHILD.equals(sMsg.A.no(i).poMdseCmpsnTpCd_HD.getValue()))
                    && (ZYPCommonFunc.hasValue(sMsg.A.no(i).aslUnitPrcAmt_A1.getValue()))) {

        		if (sMsg.A.no(i).poDtlDiscPrcAmt_A1.getValueInt() < 0) {
                    sMsg.A.no(i).poDtlDiscPrcAmt_A1.setErrorInfo(1, NPAM0046E, new String[] {"Disc Price" });
                    rtrnCd = false;
                }
        	}
        }

        return rtrnCd;
    }
    // 2019/10/09 QC#53392 Add End
}
