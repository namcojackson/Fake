/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0010;

import static business.blap.NSAL0010.constant.NSAL0010Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0010.common.NSAL0010CommonLogic;
import business.db.MDSETMsg;
import business.db.POSTTMsg;
import business.db.SVC_MACH_MSTR_STSTMsg;
import business.file.NSAL0010F00FMsg;
import business.file.NSAL0010F01FMsg;
import business.file.NSAL0010F02FMsg;
import business.file.NSAL0010F03FMsg;
import business.file.NSAL0010F04FMsg;
import business.file.NSAL0010F05FMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.CovTmplInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetCovTmpl;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IWR_COND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CORP_ADVTG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_USG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_NTWK_CONN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/14   Hitachi         K.Kasai         Create          N/A
 * 2015/11/06   Hitachi         T.Tomita        Update          QC#474
 * 2015/11/11   Hitachi         T.Tomita        Update          QC#436, QC#569
 * 2015/11/12   Hitachi         T.Tomita        Update          QC#526, QC#607
 * 2015/11/16   Hitachi         T.Tomita        Update          QC#647
 * 2015/11/20   Hitachi         T.Tomita        Update          QC#959
 * 2015/11/24   Hitachi         T.Tomita        Update          QC#948, QC#1038
 * 2016/01/04   Hitachi         T.Tomita        Update          QC#1029
 * 2016/02/04   Hitachi         T.Tomita        Update          QC#3312
 * 2016/02/22   Hitachi         T.Tomita        Update          QC#969
 * 2016/02/26   Hitachi         T.Tomita        Update          QC#942, 2690
 * 2016/03/24   Hitachi         M.Gotou         Update          QC#4951
 * 2016/03/30   Hitachi         T.Tomita        Update          QC#5398, 6092
 * 2016/04/06   Hitachi         K.Yamada        Update          QC#6482
 * 2016/04/18   Hitachi         T.Tomita        Update          QC#6217
 * 2016/04/19   Hitachi         T.Tomita        Update          QC#6223
 * 2016/04/20   Hitachi         T.Tomita        Update          QC#4892
 * 2016/04/22   Hitachi         T.Tomita        Update          QC#4866
 * 2016/04/25   Hitachi         T.Tomita        Update          QC#5522
 * 2016/04/28   Hitachi         T.Tomita        Update          QC#5398
 * 04/29/2016   Hitachi         S.Nakai         Update          QC#7787
 * 2016/05/09   Hitachi         T.Tomita        Update          QC#7842
 * 2016/05/12   Hitachi         T.Tomita        Update          QC#7832
 * 2016/05/26   Hitachi         T.Tomita        Update          QC#8894
 * 2016/06/10   Hitachi         Y.Tsuchimoto    Update          QC#9591
 * 2016/07/01   Hitachi         T.Tomita        Update          QC#11110
 * 2016/07/04   Hitachi         T.Tomita        Update          QC#11164
 * 2016/09/21   Hitachi         N.Arai          Update          QC#11616
 * 2016/10/13   Hitachi         T.Tomita        Update          CSA QC#14734
 * 2017/01/12   Hitachi         N.Arai          Update          QC#14614
 * 2017/01/17   Hitachi         N.Arai          Update          QC#14614
 * 2017/01/16   Hitachi         K.Ochiai        Update          QC#16331
 * 2017/01/25   Hitachi         N.Arai          Update          QC#14614-2
 * 2017/02/06   Hitachi         N.Arai          Update          QC#17197
 * 2017/03/07   Hitachi         T.Tomita        Update          QC#17990
 * 2017/07/20   Hitachi         U.Kim           Update          QC#19995
 * 2017/08/03   Hitachi         U.Kim           Update          QC#19995
 * 2017/10/16   Hitachi         T.Tomita        Update          QC#21563
 * 2017/10/27   Hitachi         K.Kitachi       Update          QC#21563
 * 2018/07/23   Hitachi         K.Kishimoto     Update          QC#26705
 * 2018/07/31   CITS            T.Wada          Update          QC#26454
 * 2018/08/23   Hitachi         K.Kitachi       Update          QC#27773
 * 2018/10/09   Hitachi         K.Kitachi       Update          QC#28703
 * 2018/11/06   Hitachi         S.Kitamura      Update          QC#28868
 * 2018/12/26   Hitachi         S.Kitamura      Update          QC#28860
 * 2020/03/03   Fujitsu         S.Iidaka        Update          QC#55780
 * 2023/07/10   Hitachi         Y.Nagasawa      Update          QC#61524
 * 2023/10/06   Hitachi         K.Ishizuka      Update          QC#54186
 *</pre>
 */
public class NSAL0010BL02 extends S21BusinessHandler {

    // Add Start 2017/10/16 QC#21563
    /**
     * Function ID List
     */
    private List<String> funcIdList = new ArrayList<String>();
    // Add End 2017/10/16 QC#21563

    /**
     * Method name: doProcess <dd>The method explanation: Call each
     * process by screen id. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        NSAL0010CMsg bizMsg = (NSAL0010CMsg) cMsg;
        NSAL0010SMsg sharedMsg = (NSAL0010SMsg) sMsg;
        super.preDoProcess(bizMsg, sharedMsg);

        // Common Column Order Text
        String xxComnColOrdTxt = bizMsg.xxComnColOrdTxt.getValue();
        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = bizMsg.getScreenAplID();
            String tabName = bizMsg.xxDplyTab_01.getValue();

            // Add Start 2017/10/16 QC#21563
            this.funcIdList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_ID);
            // Add End 2017/10/16 QC#21563
            // Tab
            if (TAB_MACH_CONFIG.equals(tabName)) {
                if ("NSAL0010_INIT".equals(screenAplID)) {
                    doProcess_NSAL0010_INIT(bizMsg, sharedMsg);
                    ZYPGUITableColumn.getColData(cMsg, sMsg);
                } else if ("NSAL0010Scrn00_CMN_Submit".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_Search(bizMsg, sharedMsg);
                } else if ("NSAL0010Scrn00_TAB_MachConfig".equals(screenAplID)) {
                 // START 2017/01/17 N.Arai [QC#14614, MOD]
                    // doProcess_NSAL0010Scrn00_TAB_MachConfig(bizMsg, sharedMsg);
                    doProcess_NSAL0010Scrn00_Search(bizMsg, sharedMsg);
                 // END 2017/01/17 N.Arai [QC#14614, MOD]
                } else if ("NSAL0010Scrn00_InsertParentMachineLine".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_InsertParentMachineLine(bizMsg, sharedMsg);
                } else if ("NSAL0010Scrn00_DeleteMachineLine".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_DeleteMachineLine(bizMsg, sharedMsg);
                } else if ("NSAL0010Scrn00_InsertChildMachineLine".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_InsertChildMachineLine(bizMsg, sharedMsg);
                } else if ("NSAL0010Scrn00_Search".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_Search(bizMsg, sharedMsg);
                } else if ("NSAL0010Scrn00_PagePrev".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_PagePrev(bizMsg, sharedMsg);
                } else if ("NSAL0010Scrn00_PageNext".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_PageNext(bizMsg, sharedMsg);
                } else if ("NSAL0010Scrn00_TBLColumnSort".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_TBLColumnSort(bizMsg, sharedMsg);
                } else if ("NSAL0010Scrn00_RefreshMachineInfo".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_RefreshMachineInfo(bizMsg, sharedMsg);
                // START 2016/03/30 T.Tomita [QC#5398, ADD]
                } else if ("NSAL0010Scrn00_OnBlur_MdseCd".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_OnBlur_MdseCd(bizMsg, sharedMsg);
                // END 2016/03/30 T.Tomita [QC#5398, ADD]
                }
            } else if (TAB_SOLUTION.equals(tabName)) {
                if ("NSAL0010Scrn00_TAB_Solution".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_TAB_Solution(bizMsg, sharedMsg);
                    ZYPGUITableColumn.getColData(cMsg, sMsg);
                } else if ("NSAL0010Scrn00_CMN_Submit".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_TAB_Solution(bizMsg, sharedMsg);
                } else if ("NSAL0010Scrn00_OpenWin_LinkNewConfig".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_OpenWin_LinkNewConfig(bizMsg, sharedMsg);
                } else if ("NSAL0010Scrn00_RemoveConfig".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_RemoveConfig(bizMsg, sharedMsg);
                } else if ("NSAL0010Scrn00_OpenWin_MachIdB".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_OpenWin_MachIdB(bizMsg, sharedMsg);
                } else if ("NSAL0010Scrn00_PagePrev".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_PagePrev(bizMsg, sharedMsg);
                } else if ("NSAL0010Scrn00_PageNext".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_PageNext(bizMsg, sharedMsg);
                // START 2015/11/16 T.Tomita [QC#647, MOD]
                } else if ("NSAL0010Scrn00_RefreshMachineInfo".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_RefreshMachineInfo(bizMsg, sharedMsg);
                }
                // END 2015/11/16 T.Tomita [QC#647, MOD]
            } else if (TAB_CONTACTS.equals(tabName)) {
                if ("NSAL0010Scrn00_TAB_Contacts".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_TAB_Contacts(bizMsg, sharedMsg);
                    ZYPGUITableColumn.getColData(cMsg, sMsg);
                } else if ("NSAL0010Scrn00_InactiveContacts".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_InactiveContacts(bizMsg, sharedMsg);
                } else if ("NSAL0010Scrn00_InsertContactLine".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_InsertContactLine(bizMsg, sharedMsg);
                } else if ("NSAL0010Scrn00_DeleteContactLine".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_DeleteContactLine(bizMsg, sharedMsg);
                } else if ("NSAL0010Scrn00_CMN_Submit".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_TAB_Contacts(bizMsg, sharedMsg);
                // mod start 2016/04/19 CSA Defect#6223
                } else if ("NSAL0010Scrn00_OpenWin_Contact".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_OpenWin_Contact(bizMsg, sharedMsg);
                // START 2016/05/09 T.Tomita [QC#7842, ADD]
                } else if ("NSAL0010Scrn00_PagePrev".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_PagePrev(bizMsg, sharedMsg);
                } else if ("NSAL0010Scrn00_PageNext".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_PageNext(bizMsg, sharedMsg);
                // END 2016/05/09 T.Tomita [QC#7842, ADD]
                }
                // mod end 2016/04/19 CSA Defect#6223
            } else if (TAB_ADDL_ATTRB.equals(tabName)) {
                if ("NSAL0010Scrn00_TAB_AddlAttrb".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_TAB_AddlAttrb(bizMsg, sharedMsg);
                    ZYPGUITableColumn.getColData(cMsg, sMsg);
                } else if ("NSAL0010Scrn00_CMN_Submit".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_TAB_AddlAttrb(bizMsg, sharedMsg);
                } else if ("NSAL0010Scrn00_InsertDoNotSendTech".equals(screenAplID)) {
                       doProcess_NSAL0010Scrn00_InsertDoNotSendTech(bizMsg, sharedMsg);
                } else if ("NSAL0010Scrn00_DeleteDoNotSendTech".equals(screenAplID)) {
                       doProcess_NSAL0010Scrn00_DeleteDoNotSendTech(bizMsg, sharedMsg);
                } 
// START 2023/07/10 Y.Nagasawa [QC#61524, DEL]
//                } else if (TAB_CURRENT_LOC.equals(tabName)) {
//                if ("NSAL0010Scrn00_TAB_CurrentLoc".equals(screenAplID)) {
//                    doProcess_NSAL0010Scrn00_TAB_CurrentLoc(bizMsg, sharedMsg);
//                    ZYPGUITableColumn.getColData(cMsg, sMsg);
//                } else if ("NSAL0010Scrn00_CMN_Submit".equals(screenAplID)) {
//                   doProcess_NSAL0010Scrn00_Search(bizMsg, sharedMsg);
//                }
// END 2023/07/10 Y.Nagasawa [QC#61524, DEL]
            } else if (TAB_SLS_ORD_HIST.equals(tabName)) {
                if ("NSAL0010Scrn00_TAB_SlsOrdHist".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_TAB_SLSHistory(bizMsg, sharedMsg);
                    ZYPGUITableColumn.getColData(cMsg, sMsg);
                } else if ("NSAL0010Scrn00_PagePrev".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_PagePrev(bizMsg, sharedMsg);
                } else if ("NSAL0010Scrn00_PageNext".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_PageNext(bizMsg, sharedMsg);
                }
            } else if (TAB_IB_HISTORY.equals(tabName)) {
                if ("NSAL0010Scrn00_TAB_IBHistory".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_TAB_IBHistory(bizMsg, sharedMsg);
                    ZYPGUITableColumn.getColData(cMsg, sMsg);
                } else if ("NSAL0010Scrn00_PagePrev".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_PagePrev(bizMsg, sharedMsg);
                } else if ("NSAL0010Scrn00_PageNext".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_PageNext(bizMsg, sharedMsg);
                }
            } else if (TAB_CONTR_SMRY.equals(tabName)) {
                if ("NSAL0010Scrn00_TAB_ContrSmry".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_TAB_ContrSmry(bizMsg, sharedMsg);
                    ZYPGUITableColumn.getColData(cMsg, sMsg);
                } else if ("NSAL0010Scrn00_PagePrev".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_PagePrev(bizMsg, sharedMsg);
                } else if ("NSAL0010Scrn00_PageNext".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_PageNext(bizMsg, sharedMsg);
                }
            } else if (TAB_SVC_CALL_HIST.equals(tabName)) {
                if ("NSAL0010Scrn00_TAB_SvcCallHist".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_TAB_SvcCallHist(bizMsg, sharedMsg);
                    ZYPGUITableColumn.getColData(cMsg, sMsg);
                } else if ("NSAL0010Scrn00_PagePrev".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_PagePrev(bizMsg, sharedMsg);
                } else if ("NSAL0010Scrn00_PageNext".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_PageNext(bizMsg, sharedMsg);
                }
            } else if (TAB_INVOICE_HIST.equals(tabName)) {
                if ("NSAL0010Scrn00_TAB_InvoiceHist".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_TAB_InvHist(bizMsg, sharedMsg);
                    ZYPGUITableColumn.getColData(cMsg, sMsg);
                } else if ("NSAL0010Scrn00_PagePrev".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_PagePrev(bizMsg, sharedMsg);
                } else if ("NSAL0010Scrn00_PageNext".equals(screenAplID)) {
                    doProcess_NSAL0010Scrn00_PageNext(bizMsg, sharedMsg);
                }
            }

            if ("NSAL0010Scrn00_SearchMdseName".equals(screenAplID)) {
                doProcess_NSAL0010Scrn00_SearchMdseName(bizMsg, sharedMsg);
            // START 2023/07/10 Y.Nagasawa [QC#61524, DEL]
            // } else if ("NSAL0010Scrn00_SelectPostalCode".equals(screenAplID)) {
            //     doProcess_NSAL0010Scrn00_SelectPostalCode(bizMsg, sharedMsg);
            // END 2023/07/10 Y.Nagasawa [QC#61524, DEL]
            } else if ("NSAL0010Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NSAL0010Scrn00_CMN_Download(bizMsg, sharedMsg);
            // START 2016/02/04 T.Tomita [QC#3312, DEL]
//            } else if ("NSAL0010_NWAL1130".equals(screenAplID)) {
//                doProcess_NSAL0010_NWAL1130(bizMsg, sharedMsg);
            // END 2016/02/04 T.Tomita [QC#3312, DEL]
            // START 2016/02/04 T.Tomita [QC#3312, ADD]
            } else if ("NSAL0010_NSAL1240".equals(screenAplID)) {
                doProcess_NSAL0010_NSAL1240(bizMsg, sharedMsg);
            // END 2016/02/04 T.Tomita [QC#3312, ADD]
            // START 2016/01/04 T.Tomita [QC#1029, DEL]
//            // START 2015/11/16 T.Tomita [QC#647, ADD]
//            } else if ("NSAL0010_NMAL6760".equals(screenAplID)) {
//                doProcess_NSAL0010_NMAL6760(bizMsg, sharedMsg);
//            // END 2015/11/16 T.Tomita [QC#647, ADD]
            // END 2016/01/04 T.Tomita [QC#1029, DEL]
            // START 2016/02/25 T.Tomita [QC#2690, MOD]
            } else if ("NSAL0010_NMAL6790".equals(screenAplID)) {
                doProcess_NSAL0010_NMAL6790(bizMsg, sharedMsg);
            // mod start 2016/04/28 CSA Defect#5398
            } else if ("NSAL0010_NMAL6800".equals(screenAplID)) {
                doProcess_NSAL0010_NMAL6800(bizMsg, sharedMsg);
            }
            // mod end 2016/04/28 CSA Defect#5398
            // END 2016/02/25 T.Tomita [QC#2690, MOD]
            if ("NSAL0010Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSAL0010_INIT(bizMsg, sharedMsg);
            // START 2017/01/16 K.Ochiai [QC#16331,ADD]
            } else if ("NSAL0010Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL0010_INIT(bizMsg, sharedMsg);
            }
            // END 2017/01/16 K.Ochiai [QC#16331,ADDD]

            // +++++ [E N D] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            // Set Common Column Order Text of SMsg
            sharedMsg.xxComnColOrdTxt.clear();
            setValue(sharedMsg.xxComnColOrdTxt, xxComnColOrdTxt);
            super.postDoProcess(bizMsg, sharedMsg);
        }
    }

    /**
     * Method name: doProcess_NSAL0010_INIT <dd>The method
     * explanation: Initializing. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NSAL0010_INIT(NSAL0010CMsg bizMsg, NSAL0010SMsg sMsg) {
        BigDecimal machMstrPk = bizMsg.svcMachMstrPk_H2.getValue();
        String dplyTab = bizMsg.xxDplyTab_01.getValue();

        //clear msg
        clearMsg(bizMsg, sMsg);

        ZYPEZDItemValueSetter.setValue(bizMsg.svcMachMstrPk_H1, machMstrPk);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_01, dplyTab);

        bizMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(bizMsg.slsDt, ZYPDateUtil.getSalesDate(bizMsg.glblCmpyCd.getValue()));
        // START 2015/11/16 T.Tomita [QC#647, ADD]
        // Header
        // Mod Start 2017/10/16 QC#21563
        // START 2017/10/27 K.Kitachi [QC#21563, MOD]
        setMachStsPulldown(bizMsg);
        // END 2017/10/27 K.Kitachi [QC#21563, MOD]
        // Mod End 2017/10/16 QC#21563
        ZYPCodeDataUtil.createPulldownList(STK_STS.class, bizMsg.stkStsCd_H1, bizMsg.stkStsDescTxt_H2);
        ZYPCodeDataUtil.createPulldownList(SVC_MACH_TRX_TP.class, bizMsg.svcMachTrxTpCd_H1, bizMsg.svcMachTrxTpDescTxt_H2);
        // START 2016/02/26 T.Tomita [QC#942, ADD]
        ZYPCodeDataUtil.createPulldownList(LOC_STS.class, bizMsg.locStsCd_H1, bizMsg.locStsDescTxt_H2);
        // END 2016/02/26 T.Tomita [QC#942, ADD]
        // Machine Configuration Tab
        // Solution Tab
        // Contact Tab
        ZYPCodeDataUtil.createPulldownList(DS_CTAC_PNT_TP.class, bizMsg.dsCtacPntTpCd_CC, bizMsg.dsCtacPntTpNm_CD);
        ZYPCodeDataUtil.createPulldownList(SVC_CTAC_TP.class, bizMsg.svcCtacTpCd_CC, bizMsg.svcCtacTpDescTxt_CD);
        // Additional Attribute Tab
        ZYPCodeDataUtil.createPulldownList(SVC_CORP_ADVTG_STS.class, bizMsg.corpAdvtgStsCd_DC, bizMsg.svcCorpAdvtgStsDescTxt_DD);
        ZYPCodeDataUtil.createPulldownList(IWR_COND.class, bizMsg.iwrCondCd_DC, bizMsg.iwrCondDescTxt_DD);
        ZYPCodeDataUtil.createPulldownList(SVC_NTWK_CONN_STS.class, bizMsg.svcNtwkConnStsCd_DC, bizMsg.svcNtwkConnStsDescTxt_DD);
        ZYPCodeDataUtil.createPulldownList(LINE_BIZ_TP.class, bizMsg.sldByLineBizTpCd_DC, bizMsg.lineBizTpDescTxt_D1);
        // START 2023/10/06 K.Ishizuka [QC#54186, MOD]
//        // mod start 2016/04/20 CSA Defect#4892
//        setSvcLineBizPulldown(bizMsg);
//        // mod end 2016/04/20 CSA Defect#4892
        setIstlOrSvcPulldown(bizMsg);
        //END 2023/10/06 K.Ishizuka [QC#54186, MOD]
        // START 2023/07/10 Y.Nagasawa [QC#61524, DEL]
        // Current Location Tab
        // ZYPCodeDataUtil.createPulldownList(CTRY.class, bizMsg.ctryCd_FC, bizMsg.ctryDescTxt_FD);
        // END 2023/07/10 Y.Nagasawa [QC#61524, DEL]
        // END 2015/11/16 T.Tomita [QC#647, ADD]


        setHeaderInfo(bizMsg);

        if (ZYPCommonFunc.hasValue(bizMsg.svcMachMstrPk_H1)) {
            // START 2018/11/06 S.Kitamura [QC#28868,ADD]
            outputInitLog(bizMsg);
            // END 2018/11/06 S.Kitamura [QC#28868,ADD]
            findSvcMachMstr(bizMsg, sMsg);
            NSAL0010CommonLogic.preSetToPageOne(bizMsg.xxPageShowFromNum_A);
            NSAL0010CommonLogic.copyGlblMsgToBizMsg(bizMsg, sMsg);
            setValue(bizMsg.xxPageShowOfNum_A, new BigDecimal(sMsg.A.getValidCount()));
            setValue(bizMsg.xxPageShowToNum_A, NSAL0010CommonLogic.calcXxPageShowToNum(bizMsg.xxPageShowFromNum_A.getValueInt(), bizMsg.A.getValidCount()));
        }

    }

    private void clearMsg(NSAL0010CMsg bizMsg, NSAL0010SMsg sMsg) {
        bizMsg.clear();
        bizMsg.A.clear();
        bizMsg.B.clear();
        bizMsg.C.clear();
        bizMsg.E.clear();
        bizMsg.I.clear();
        bizMsg.J.clear();
        bizMsg.K.clear();
        bizMsg.A.setValidCount(0);
        bizMsg.B.setValidCount(0);
        bizMsg.C.setValidCount(0);
        bizMsg.E.setValidCount(0);
        bizMsg.I.setValidCount(0);
        bizMsg.J.setValidCount(0);
        bizMsg.K.setValidCount(0);
        sMsg.clear();
        sMsg.A.clear();
        sMsg.B.clear();
        sMsg.C.clear();
        sMsg.E.clear();
        sMsg.I.clear();
        sMsg.J.clear();
        sMsg.K.clear();
        // START 2020/03/03 S.Iidaka [QC#55780, ADD]
        sMsg.N.clear();
        // END 2020/03/03 S.Iidaka [QC#55780, ADD]
        sMsg.A.setValidCount(0);
        sMsg.B.setValidCount(0);
        sMsg.C.setValidCount(0);
        sMsg.E.setValidCount(0);
        sMsg.I.setValidCount(0);
        sMsg.J.setValidCount(0);
        sMsg.K.setValidCount(0);
        // START 2020/03/03 S.Iidaka [QC#55780, ADD]
        sMsg.N.setValidCount(0);
        // END 2020/03/03 S.Iidaka [QC#55780, ADD]
    }

    /**
     * Method name: doProcess_NSAL0010Scrn00_InsertContractLine <dd>
     * The method explanation: Insert Contract Information Line. <dd>
     * Remarks:
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NSAL0010Scrn00_InsertParentMachineLine(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {

        NSAL0010CommonLogic.copyCMsgToSMsg(cMsg, sMsg);

        if (!ZYPCommonFunc.hasValue(cMsg.mdseCd_H1)) {
            cMsg.mdseCd_H1.setErrorInfo(1, ZZM9000E, new String[]{"Mdse Code"});
            return;
        }

        // START 2016/10/13 T.Tomita [QC#14734, ADD]
        if (SVC_MACH_MSTR_STS.DEALER_SERVICE.equals(cMsg.svcMachMstrStsCd_H3.getValue())) {
            SVC_MACH_MSTR_STSTMsg tMsg = new SVC_MACH_MSTR_STSTMsg();
            setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
            setValue(tMsg.svcMachMstrStsCd, cMsg.svcMachMstrStsCd_H3);
            tMsg = (SVC_MACH_MSTR_STSTMsg) S21CodeTableAccessor.findByKey(tMsg);
            cMsg.setMessageInfo(NSAM0612E, new String[] {tMsg.svcMachMstrStsDescTxt.getValue(), "Insert Parent" });
            return;
        }
        // END 2016/10/13 T.Tomita [QC#14734, ADD]

        if (sMsg.A.getValidCount() == sMsg.A.length()) {
            cMsg.setMessageInfo(NSAM0320E, new String[]{"Config Item", String.valueOf(sMsg.A.length())});
            return;
        }

        // mod start 2016/03/30 CSA Defect#6092
        int machCnt = cMsg.xxTotCnt_A.getValueInt();
        if (machCnt > 0) {
            // exists parent machine

            int parentRow = 0;
            int insertRow = 1;
//            if (SVC_MACH_TP.MACHINE.equals(sMsg.A.no(insertRow).svcMachTpCd_A.getValue())) {
//                cMsg.setMessageInfo(NSAM0321E);
//                return;
//            }

            int cnt = sMsg.A.getValidCount() + 1;
            sMsg.A.setValidCount(cnt);
            for (int i = sMsg.A.getValidCount() - 2; i >= insertRow; i--) {
                EZDMsg.copy(sMsg.A.no(i), null, sMsg.A.no(i + 1), null);
            }
            sMsg.A.no(insertRow).clear();
            // START 2015/11/16 T.Tomita [QC#647, ADD]
            sMsg.A.no(insertRow).svcMachTpCd_A.setValue(SVC_MACH_TP.MACHINE);
            // END 2015/11/16 T.Tomita [QC#647, ADD]
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(parentRow).svcMachRmvDt_A, cMsg.slsDt);
        } else {
            // START 2015/11/16 T.Tomita [QC#647, MOD]
            if (!hasValue(cMsg.svcMachMstrStsCd_H3)) {
                cMsg.svcMachMstrStsCd_H3.setErrorInfo(1, ZZM9000E, new String[]{"IB Status"});
                return;
            }
            int insertRow = 0;
            int cnt = sMsg.A.getValidCount() + 1;
            sMsg.A.setValidCount(cnt);
            for (int i = sMsg.A.getValidCount() - 1; i > insertRow; i--) {
                EZDMsg.copy(sMsg.A.no(i - 1), null, sMsg.A.no(i), null);
            }
            sMsg.A.no(insertRow).clear();
            sMsg.xxRadioBtn_A.setValue(0);
            NSAL0010CommonLogic.setParentMachineLine(cMsg, sMsg, insertRow, getGlobalCompanyCode());
            // END 2015/11/16 T.Tomita [QC#647, MOD]
        }
        NSAL0010CommonLogic.setMachCntForConfig(cMsg, sMsg);
        // mod end 2016/03/30 CSA Defect#6092
        NSAL0010CommonLogic.preSetToPageOne(cMsg.xxPageShowFromNum_A);
        NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
        setValue(cMsg.xxPageShowOfNum_A, new BigDecimal(sMsg.A.getValidCount()));
        setValue(cMsg.xxPageShowToNum_A, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_A.getValueInt(), cMsg.A.getValidCount()));

    }

    /**
     * Method name: doProcess_NSAL0010Scrn00_InsertContractLine <dd>
     * The method explanation: Insert Contract Information Line. <dd>
     * Remarks:
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NSAL0010Scrn00_DeleteMachineLine(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {

        NSAL0010CommonLogic.copyCMsgToSMsg(cMsg, sMsg);

        int deleteRow = cMsg.xxRadioBtn_A.getValueInt();

        if (deleteRow < 0) {
            cMsg.setMessageInfo(NSAM0034E);
            return;
        }

        if (ZYPCommonFunc.hasValue(cMsg.A.no(deleteRow).svcConfigMstrPk_A)) {
            cMsg.setMessageInfo(NSAM0321E);
            return;
        }

        int pageFrom = cMsg.xxPageShowFromNum_A.getValueInt() - 1;
        int deleteIndex = pageFrom + deleteRow;
        ZYPTableUtil.deleteRows(sMsg.A, Arrays.asList(deleteIndex));

        // START 2015/11/11 T.Tomita [QC#436, MOD]
        int newPageFrom = cMsg.xxPageShowFromNum_A.getValueInt();
        if (newPageFrom > sMsg.A.getValidCount() && newPageFrom > cMsg.A.length()) {
            newPageFrom = newPageFrom - cMsg.A.length();
        } else {
// START 2017/01/25 N.Arai [QC#14614-2, MOD]
//          newPageFrom = 1;
            int lastPageFrom = ((sMsg.A.getValidCount() - 1) / cMsg.A.length()) * cMsg.A.length() + 1;
            if (newPageFrom > lastPageFrom) {
                newPageFrom = lastPageFrom;
            }
// END 2017/01/25 N.Arai [QC#14614-2, MOD]
        }
        // END 2015/11/11 T.Tomita [QC#436, MOD]
        // add start 2016/03/30 CSA Defect#6092
        NSAL0010CommonLogic.setMachCntForConfig(cMsg, sMsg);
        // add end 2016/03/30 CSA Defect#6092
        cMsg.xxPageShowFromNum_A.setValue(newPageFrom);
        NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
        setValue(cMsg.xxPageShowOfNum_A, new BigDecimal(sMsg.A.getValidCount()));
        setValue(cMsg.xxPageShowToNum_A, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_A.getValueInt(), cMsg.A.getValidCount()));

//        cMsg.A.no(deleteRow).clear();
//        for (int i = deleteRow + 1; i < cMsg.A.getValidCount(); i++) {
//            EZDMsg.copy(cMsg.A.no(i), null, cMsg.A.no(i - 1), null);
//        }
//        int cnt = cMsg.A.getValidCount() - 1;
//        cMsg.A.setValidCount(cnt);
//        cMsg.xxRadioBtn_A.setValue(0);

    }

    /**
     * Method name: doProcess_NSAL0010Scrn00_InsertContractLine <dd>
     * The method explanation: Insert Contract Information Line. <dd>
     * Remarks:
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NSAL0010Scrn00_InsertChildMachineLine(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {

        NSAL0010CommonLogic.copyCMsgToSMsg(cMsg, sMsg);

        if (sMsg.A.getValidCount() == sMsg.A.length()) {
            cMsg.setMessageInfo(NSAM0320E, new String[]{"Config Item", String.valueOf(sMsg.A.length())});
            return;
        }

        if (sMsg.A.getValidCount() > 0) {
            // exists parent machine
            int cnt = sMsg.A.getValidCount() + 1;
            sMsg.A.setValidCount(cnt);
            sMsg.A.no(cnt - 1).clear();
            // START 2015/11/06 T.Tomita [QC#474, ADD]
            sMsg.A.no(cnt - 1).svcMachTpCd_A.setValue(SVC_MACH_TP.ACCESSORY);
            // END 2015/11/06 T.Tomita [QC#474, ADD]
            sMsg.xxRadioBtn_A.setValue(0);
        }

        int fromNum = (sMsg.A.getValidCount() / cMsg.A.length()) * cMsg.A.length() + 1;
        if (sMsg.A.getValidCount() % cMsg.A.length() == 0) {
            fromNum = (sMsg.A.getValidCount() / cMsg.A.length() - 1) * cMsg.A.length() + 1;
        }
        // add start 2016/03/30 CSA Defect#6092
        NSAL0010CommonLogic.setMachCntForConfig(cMsg, sMsg);
        // add end 2016/03/30 CSA Defect#6092
        cMsg.xxPageShowFromNum_A.setValue(fromNum);
        NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
        setValue(cMsg.xxPageShowOfNum_A, new BigDecimal(sMsg.A.getValidCount()));
        setValue(cMsg.xxPageShowToNum_A, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_A.getValueInt(), cMsg.A.getValidCount()));
    }

    /**
     * Method name: doProcess_NSAL0010Scrn00_DeleteContactLine <dd>
     * The method explanation: Insert Contract Information Line. <dd>
     * Remarks:
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NSAL0010Scrn00_DeleteContactLine(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {

        NSAL0010CommonLogic.copyCMsgToSMsg(cMsg, sMsg);

        int rowNum = cMsg.xxRadioBtn_C.getValueInt();

        int pageFrom = cMsg.xxPageShowFromNum_C.getValueInt() - 1;
        int deleteIndex = pageFrom + rowNum;
        // START 2016/02/22 T.Tomita [QC#969 ADD]
        BigDecimal svcMachCtacPsnPk = sMsg.C.no(deleteIndex).svcMachCtacPsnPk_C.getValue();
        if (hasValue(svcMachCtacPsnPk)) {
            int delCnt = sMsg.N.getValidCount();
            setValue(sMsg.N.no(delCnt).svcMachCtacPsnPk_N, svcMachCtacPsnPk);
            delCnt = delCnt + 1;
            sMsg.N.setValidCount(delCnt);
        }
        // END 2016/02/22 T.Tomita [QC#969 ADD]
        ZYPTableUtil.deleteRows(sMsg.C, Arrays.asList(deleteIndex));

        int fromNum = (sMsg.C.getValidCount() / cMsg.C.length()) * cMsg.C.length() + 1;
        if (sMsg.C.getValidCount() == 0 ) {
            fromNum = 0;
        } else if (sMsg.C.getValidCount() % cMsg.C.length() == 0) {
            fromNum = (sMsg.C.getValidCount() / cMsg.C.length() - 1) * cMsg.C.length() + 1;
        }
        cMsg.xxPageShowFromNum_C.setValue(fromNum);
        NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
        setValue(cMsg.xxPageShowOfNum_C, new BigDecimal(sMsg.C.getValidCount()));
        setValue(cMsg.xxPageShowToNum_C, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_C.getValueInt(), cMsg.C.getValidCount()));
    }

    /**
     * Method name: doProcess_NSAL0010Scrn00_InsertContactLine <dd>
     * The method explanation: Insert Contract Information Line. <dd>
     * Remarks:
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NSAL0010Scrn00_InsertContactLine(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {

        NSAL0010CommonLogic.copyCMsgToSMsg(cMsg, sMsg);

        sMsg.C.setValidCount(sMsg.C.getValidCount() + 1);
        int insRow = sMsg.C.getValidCount() - 1;
        // START 2018/07/23 [QC#26705, ADD]
        if (insRow >= 0) {
            setValue(sMsg.C.no(insRow).effFromDt_C, cMsg.slsDt);
        }
        // END   2018/07/23 [QC#26705, ADD]

        int fromNum = (sMsg.C.getValidCount() / cMsg.C.length()) * cMsg.C.length() + 1;
        if (sMsg.C.getValidCount() % cMsg.C.length() == 0) {
            fromNum = (sMsg.C.getValidCount() / cMsg.C.length() - 1) * cMsg.C.length() + 1;
        }

        cMsg.xxPageShowFromNum_C.setValue(fromNum);
        NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
        setValue(cMsg.xxPageShowOfNum_C, new BigDecimal(sMsg.C.getValidCount()));
        setValue(cMsg.xxPageShowToNum_C, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_C.getValueInt(), cMsg.C.getValidCount()));
    }

     /**
     * Method name: doProcess_NSAL0010Scrn00_Search <dd>The method
     * explanation: Serch Machine Master Information. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NSAL0010Scrn00_Search(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {
        // START 2018/11/06 S.Kitamura [QC#28868,ADD]
        outputSearchLog(cMsg);
        // END 2018/11/06 S.Kitamura [QC#28868,ADD]
        findSvcMachMstr(cMsg, sMsg);
        if (cMsg.xxDplyTab_01.getValue().equals(TAB_MACH_CONFIG)) {
            NSAL0010CommonLogic.preSetToPageOne(cMsg.xxPageShowFromNum_A);
            NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
            setValue(cMsg.xxPageShowOfNum_A, new BigDecimal(sMsg.A.getValidCount()));
            setValue(cMsg.xxPageShowToNum_A, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_A.getValueInt(), cMsg.A.getValidCount()));
        } else if (cMsg.xxDplyTab_01.getValue().equals(TAB_SOLUTION)) {
            NSAL0010CommonLogic.preSetToPageOne(cMsg.xxPageShowFromNum_B);
            NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
            setValue(cMsg.xxPageShowOfNum_B, new BigDecimal(sMsg.B.getValidCount()));
            setValue(cMsg.xxPageShowToNum_B, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_B.getValueInt(), cMsg.B.getValidCount()));
        } else if (cMsg.xxDplyTab_01.getValue().equals(TAB_CONTACTS)) {
            findContacts(cMsg, sMsg);
            NSAL0010CommonLogic.preSetToPageOne(cMsg.xxPageShowFromNum_C);
            NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
            setValue(cMsg.xxPageShowOfNum_C, new BigDecimal(sMsg.C.getValidCount()));
            setValue(cMsg.xxPageShowToNum_C, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_C.getValueInt(), cMsg.C.getValidCount()));
        } else if (cMsg.xxDplyTab_01.getValue().equals(TAB_SLS_ORD_HIST)) {
            NSAL0010CommonLogic.preSetToPageOne(cMsg.xxPageShowFromNum_G);
            NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
            setValue(cMsg.xxPageShowOfNum_G, new BigDecimal(sMsg.G.getValidCount()));
            setValue(cMsg.xxPageShowToNum_G, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_G.getValueInt(), cMsg.G.getValidCount()));
        } else if (cMsg.xxDplyTab_01.getValue().equals(TAB_IB_HISTORY)) {
            NSAL0010CommonLogic.preSetToPageOne(cMsg.xxPageShowFromNum_I);
            NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
            setValue(cMsg.xxPageShowOfNum_I, new BigDecimal(sMsg.I.getValidCount()));
            setValue(cMsg.xxPageShowToNum_I, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_I.getValueInt(), cMsg.I.getValidCount()));
        } else if (cMsg.xxDplyTab_01.getValue().equals(TAB_CONTR_SMRY)) {
            NSAL0010CommonLogic.preSetToPageOne(cMsg.xxPageShowFromNum_J);
            NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
            setValue(cMsg.xxPageShowOfNum_J, new BigDecimal(sMsg.J.getValidCount()));
            setValue(cMsg.xxPageShowToNum_J, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_J.getValueInt(), cMsg.J.getValidCount()));
        } else if (cMsg.xxDplyTab_01.getValue().equals(TAB_SVC_CALL_HIST)) {
            NSAL0010CommonLogic.preSetToPageOne(cMsg.xxPageShowFromNum_K);
            NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
            setValue(cMsg.xxPageShowOfNum_K, new BigDecimal(sMsg.K.getValidCount()));
            setValue(cMsg.xxPageShowToNum_K, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_K.getValueInt(), cMsg.K.getValidCount()));
        } else if (cMsg.xxDplyTab_01.getValue().equals(TAB_INVOICE_HIST)) {
            NSAL0010CommonLogic.preSetToPageOne(cMsg.xxPageShowFromNum_L);
            NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
            setValue(cMsg.xxPageShowOfNum_L, new BigDecimal(sMsg.L.getValidCount()));
            setValue(cMsg.xxPageShowToNum_L, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_L.getValueInt(), cMsg.L.getValidCount()));
        }
    }

 // START 2017/01/17 N.Arai [QC#14614, DEL]
//    private void doProcess_NSAL0010Scrn00_TAB_MachConfig(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {
//        if (cMsg.xxDplyTab_01.getValue().equals(TAB_MACH_CONFIG)) {
//            NSAL0010CommonLogic.preSetToPageOne(cMsg.xxPageShowFromNum_A);
//            NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
//            setValue(cMsg.xxPageShowOfNum_A, new BigDecimal(sMsg.A.getValidCount()));
//            setValue(cMsg.xxPageShowToNum_A, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_A.getValueInt(), cMsg.A.getValidCount()));
//        }
//    }
 // END 2017/01/17 N.Arai [QC#14614, DEL]

    private void doProcess_NSAL0010Scrn00_RefreshMachineInfo(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {

        int rowNum = cMsg.xxRowNum.getValueInt();
        BigDecimal svcMachMstrPk = cMsg.A.no(rowNum).svcMachMstrPk_A1.getValue();
        // START 2015/11/16 T.Tomita [QC#647, MOD]
        if (cMsg.xxDplyTab_01.getValue().equals(TAB_SOLUTION)) {
            svcMachMstrPk = cMsg.B.no(rowNum).svcMachMstrPk_B1.getValue();
        }
        // END 2015/11/16 T.Tomita [QC#647, MOD]
        refleshHeader(cMsg, sMsg, svcMachMstrPk);
        // START 2018/08/23 K.Kitachi [QC#27773, ADD]
        NSAL0010CommonLogic.setStdWtyFlg(cMsg, sMsg);
        // END 2018/08/23 K.Kitachi [QC#27773, ADD]
    }

    // START 2016/03/30 T.Tomita [QC#5398, ADD]
    private void doProcess_NSAL0010Scrn00_OnBlur_MdseCd(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {
        NSAL0010CommonLogic.checkHeaderMaster(cMsg, sMsg);
        // START 2018/08/23 K.Kitachi [QC#27773, ADD]
        NSAL0010CommonLogic.setStdWtyFlg(cMsg, sMsg);
        // END 2018/08/23 K.Kitachi [QC#27773, ADD]
    }
    // END 2016/03/30 T.Tomita [QC#5398, ADD]

    /**
     * Method name: doProcess_NSAL0010_INIT <dd>The method
     * explanation: Initializing. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NSAL0010Scrn00_TAB_Contacts(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.svcMachMstrPk_H1)) {
            // START 2016/04/18 T.Tomita [QC#6217, ADD]
            cMsg.xxChkBox_CH.clear();
            // END 2016/04/18 T.Tomita [QC#6217, ADD]
            findContacts(cMsg, sMsg);
        }

        // START 2020/03/03 S.Iidaka [QC#55780, ADD]
        sMsg.N.clear();
        sMsg.N.setValidCount(0);
        // END 2020/03/03 S.Iidaka [QC#55780, ADD]

        NSAL0010CommonLogic.preSetToPageOne(cMsg.xxPageShowFromNum_C);
        NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
        setValue(cMsg.xxPageShowOfNum_C, new BigDecimal(sMsg.C.getValidCount()));
        setValue(cMsg.xxPageShowToNum_C, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_C.getValueInt(), cMsg.C.getValidCount()));

    }

    private void doProcess_NSAL0010Scrn00_InactiveContacts(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.svcMachMstrPk_H1)) {
            // START 2016/04/18 T.Tomita [QC#6217, MOD]
//            findContactsWithInactive(cMsg, sMsg);
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_CH.getValue())) {
                findContactsWithInactive(cMsg, sMsg);
            } else {
                findContacts(cMsg, sMsg);
            }
            // END 2016/04/18 T.Tomita [QC#6217, MOD]
        }

        NSAL0010CommonLogic.preSetToPageOne(cMsg.xxPageShowFromNum_C);
        NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
        setValue(cMsg.xxPageShowOfNum_C, new BigDecimal(sMsg.C.getValidCount()));
        setValue(cMsg.xxPageShowToNum_C, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_C.getValueInt(), cMsg.C.getValidCount()));

    }

    private void doProcess_NSAL0010Scrn00_TAB_Solution(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.svcMachMstrPk_H1)) {
            findSolutions(cMsg, sMsg);
        }

        NSAL0010CommonLogic.preSetToPageOne(cMsg.xxPageShowFromNum_B);
        NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
        setValue(cMsg.xxPageShowOfNum_B, new BigDecimal(sMsg.B.getValidCount()));
        setValue(cMsg.xxPageShowToNum_B, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_B.getValueInt(), cMsg.B.getValidCount()));

    }

    private void doProcess_NSAL0010Scrn00_OpenWin_LinkNewConfig(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {

        // START 2016/05/12 T.Tomita [QC#7832, MOD]
        for (int idx = 0; idx < cMsg.Q.getValidCount(); idx++) {
            // START 2015/11/11 T.Tomita [QC#569, MOD]
            BigDecimal slctSvcConfigMstrPk = cMsg.Q.no(idx).svcConfigMstrPk_P.getValue();

            // START 2015/11/24 T.Tomita [QC#1038, ADD]
            if (!ZYPCommonFunc.hasValue(slctSvcConfigMstrPk)) {
                return;
            }
            // END 2015/11/24 T.Tomita [QC#1038, ADD]

            for (int chkCnt = 0; chkCnt < sMsg.B.getValidCount(); chkCnt++) {
                if (hasValue(slctSvcConfigMstrPk) && slctSvcConfigMstrPk.compareTo(sMsg.B.no(chkCnt).svcConfigMstrPk_B.getValue()) == 0) {
                    cMsg.setMessageInfo(ZZSM4163E);
                    return;
                }
            }

            List<Map<String, Object>> resList = NSAL0010Query.getInstance().getConfigInfo(getGlobalCompanyCode(), slctSvcConfigMstrPk);
            // END 2015/11/11 T.Tomita [QC#569, MOD]
            int pageSize = sMsg.B.getValidCount() + resList.size();
            if (sMsg.B.length() < pageSize) {
                cMsg.setMessageInfo(NSAM0320E, new String[] {"New Configuration", String.valueOf(sMsg.B.length()) });
                return;
            }

            Map<String, Object> res;
            int sMsgIdx = sMsg.B.getValidCount();
            for (int i = 0; i < resList.size(); i++) {
                res = resList.get(i);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).svcSlnSq_B, (BigDecimal) res.get("SVC_SLN_SQ"));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).svcConfigMstrPk_B, (BigDecimal) res.get("SVC_CONFIG_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).mdseCd_B, (String) res.get("MDSE_CD"));
             // START 2016/09/21 N.Arai [QC#11616, MOD]
             // ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).mdseNm_B, (String) res.get("MDSE_NM"));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).mdseDescShortTxt_B, (String) res.get("MDSE_DESC_SHORT_TXT"));
             // END 2016/09/21 N.Arai [QC#11616, MOD]
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).t_MdlNm_B, (String) res.get("T_MDL_NM"));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).svcMachMstrPk_B1, (BigDecimal) res.get("SVC_MACH_MSTR_PK_1"));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).svcMachMstrPk_B2, (BigDecimal) res.get("SVC_MACH_MSTR_PK_2"));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).istlDt_B, (String) res.get("ISTL_DT"));
                // START 2018/10/09 K.Kitachi [QC#28703, MOD]
//                ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).locNm_B1, (String) res.get("BILL_TO_LOC_NM"));
//                ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).locNm_B2, (String) res.get("DS_ACCT_LOC_NM"));
//                ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).locNm_B3, (String) res.get("SHIP_TO_LOC_NM"));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).dsAcctNm_B1, (String) res.get("OWNR_ACCT_NM"));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).dsAcctNm_B2, (String) res.get("BILL_TO_ACCT_NM"));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).dsAcctNm_B3, (String) res.get("CUR_LOC_ACCT_NM"));
                // END 2018/10/09 K.Kitachi [QC#28703, MOD]
                sMsgIdx++;
            }
            sMsg.B.setValidCount(sMsgIdx);
        }
        // END 2016/05/12 T.Tomita [QC#7832, MOD]

        // START 2015/11/11 T.Tomita [QC#569, MOD]
        int pageFrom = 0;
        if (sMsg.B.getValidCount() % cMsg.B.length() > 0) {
            pageFrom = (sMsg.B.getValidCount() / cMsg.B.length()) * cMsg.B.length() + 1;
        } else {
            pageFrom = (sMsg.B.getValidCount() / cMsg.B.length() - 1) * cMsg.B.length() + 1;
        }
        // END 2015/11/11 T.Tomita [QC#569, MOD]
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_B, new BigDecimal(pageFrom));
        NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
        setValue(cMsg.xxPageShowOfNum_B, new BigDecimal(sMsg.B.getValidCount()));
        setValue(cMsg.xxPageShowToNum_B, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_B.getValueInt(), cMsg.B.getValidCount()));
    }

    private void doProcess_NSAL0010Scrn00_RemoveConfig(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {

        //TODO show warning message

        int rowNum = cMsg.xxRadioBtn_B.getValueInt();
        BigDecimal targetConfigPk = cMsg.B.no(rowNum).svcConfigMstrPk_B.getValue();
        List<Integer> delRows = new ArrayList<Integer>();

        int delListCnt = sMsg.M.getValidCount();
        // START 2015/11/11 T.Tomita [QC#569, MOD]
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            BigDecimal configPk = sMsg.B.no(i).svcConfigMstrPk_B.getValue();
            if (configPk.compareTo(targetConfigPk) == 0 && hasValue(sMsg.B.no(i).svcSlnSq_B)) {
                ZYPEZDItemValueSetter.setValue(sMsg.M.no(delListCnt).svcSlnSq_M, sMsg.B.no(i).svcSlnSq_B);
                ZYPEZDItemValueSetter.setValue(sMsg.M.no(delListCnt).svcConfigMstrPk_M, configPk);
                delListCnt++;
                delRows.add(i);
            }
        }
        sMsg.M.setValidCount(delListCnt);
        // END 2015/11/11 T.Tomita [QC#569, MOD]

        ZYPTableUtil.deleteRows(sMsg.B, delRows);

        // START 2015/11/11 T.Tomita [QC#569, MOD]
        int newPageFrom = cMsg.xxPageShowFromNum_B.getValueInt();
        if (newPageFrom > sMsg.B.getValidCount() && newPageFrom > cMsg.B.length()) {
            newPageFrom = newPageFrom - cMsg.B.length();
        } else {
            newPageFrom = 1;
        }
        cMsg.xxPageShowFromNum_B.setValue(newPageFrom);
        NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
        setValue(cMsg.xxPageShowOfNum_B, new BigDecimal(sMsg.B.getValidCount()));
        setValue(cMsg.xxPageShowToNum_B, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_B.getValueInt(), cMsg.B.getValidCount()));
        // END 2015/11/11 T.Tomita [QC#569, MOD]
    }

    private void doProcess_NSAL0010Scrn00_OpenWin_MachIdB(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {

        int rowNum = cMsg.xxRowNum.getValueInt();
        BigDecimal svcMachMstrPk = cMsg.A.no(rowNum).svcMachMstrPk_A1.getValue();
        refleshHeader(cMsg, sMsg, svcMachMstrPk);
        refleshConfig(cMsg, sMsg, svcMachMstrPk);

    }

    /**
     * Method name: doProcess_NSAL0010_INIT <dd>The method
     * explanation: Initializing. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NSAL0010Scrn00_TAB_AddlAttrb(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {

        NSAL0010CMsg bizMsg = (NSAL0010CMsg) cMsg;

        if (ZYPCommonFunc.hasValue(bizMsg.svcMachMstrPk_H1)) {
            findSvcMachMstr(bizMsg, sMsg);
            findSvcNonPrfTech(bizMsg, sMsg);
        }
    }

    /**
     * Method name: doProcess_NSAL0010_INIT <dd>The method
     * explanation: Initializing. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
// START 2023/07/10 Y.Nagasawa [QC#61524, DEL]
//    private void doProcess_NSAL0010Scrn00_TAB_CurrentLoc(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {
//
//        NSAL0010CMsg bizMsg = (NSAL0010CMsg) cMsg;
//
//        if (ZYPCommonFunc.hasValue(bizMsg.svcMachMstrPk_H1)) {
//            findSvcMachMstr(bizMsg, sMsg);
//        }
//    }
// END 2023/07/10 Y.Nagasawa [QC#61524, DEL]

    private void doProcess_NSAL0010Scrn00_TAB_SLSHistory(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {

        NSAL0010CMsg bizMsg = (NSAL0010CMsg) cMsg;

        if (ZYPCommonFunc.hasValue(bizMsg.svcMachMstrPk_H1)) {
            findSlsHistory(bizMsg, sMsg);
        }

        NSAL0010CommonLogic.preSetToPageOne(cMsg.xxPageShowFromNum_G);
        NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
        setValue(cMsg.xxPageShowOfNum_G, new BigDecimal(sMsg.G.getValidCount()));
        setValue(cMsg.xxPageShowToNum_G, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_G.getValueInt(), cMsg.G.getValidCount()));
    }

    private void doProcess_NSAL0010Scrn00_TAB_IBHistory(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {

        NSAL0010CMsg bizMsg = (NSAL0010CMsg) cMsg;

        if (ZYPCommonFunc.hasValue(bizMsg.svcMachMstrPk_H1)) {
            findIBHistory(bizMsg, sMsg);
        }

        NSAL0010CommonLogic.preSetToPageOne(cMsg.xxPageShowFromNum_I);
        NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
        setValue(cMsg.xxPageShowOfNum_I, new BigDecimal(sMsg.I.getValidCount()));
        setValue(cMsg.xxPageShowToNum_I, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_I.getValueInt(), cMsg.I.getValidCount()));
    }

    private void doProcess_NSAL0010Scrn00_TAB_ContrSmry(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {

        NSAL0010CMsg bizMsg = (NSAL0010CMsg) cMsg;

        if (ZYPCommonFunc.hasValue(bizMsg.svcMachMstrPk_H1)) {
            findContrSmry(bizMsg, sMsg);
        }

        NSXC001001GetCovTmpl covTmpl = new NSXC001001GetCovTmpl();
        for (int i = 0; i < sMsg.J.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(sMsg.J.no(i).xxChkBox_J, ZYPConstant.FLG_OFF_N);
            if (ZYPCommonFunc.hasValue(sMsg.J.no(i).svcPgmMdseCd_J)) {
                CovTmplInfo tmplInfo = new CovTmplInfo();
                tmplInfo.setGlblCmpyCd(cMsg.glblCmpyCd.getValue());
                tmplInfo.setSlsDt(cMsg.slsDt.getValue());
                tmplInfo.setSvcPgmMdseCd(sMsg.J.no(i).svcPgmMdseCd_J.getValue());
                boolean isSuplIncl = covTmpl.isSuplIncl(tmplInfo);

                if (isSuplIncl) {
                    ZYPEZDItemValueSetter.setValue(sMsg.J.no(i).xxChkBox_J, ZYPConstant.FLG_ON_Y);
                    // START 2018/12/26 S.Kitamura [QC#28860,DEL]
                    //break;
                    // END 2018/12/26 S.Kitamura [QC#28860,DEL]
                }
            }
        }

        NSAL0010CommonLogic.preSetToPageOne(cMsg.xxPageShowFromNum_J);
        NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
        setValue(cMsg.xxPageShowOfNum_J, new BigDecimal(sMsg.J.getValidCount()));
        setValue(cMsg.xxPageShowToNum_J, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_J.getValueInt(), cMsg.J.getValidCount()));
    }

    private void doProcess_NSAL0010Scrn00_TAB_SvcCallHist(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {

        NSAL0010CMsg bizMsg = (NSAL0010CMsg) cMsg;

        if (ZYPCommonFunc.hasValue(bizMsg.svcMachMstrPk_H1)) {
            findSvcCallHist(bizMsg, sMsg);
        }

        NSAL0010CommonLogic.preSetToPageOne(cMsg.xxPageShowFromNum_K);
        NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
        setValue(cMsg.xxPageShowOfNum_K, new BigDecimal(sMsg.K.getValidCount()));
        setValue(cMsg.xxPageShowToNum_K, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_K.getValueInt(), cMsg.K.getValidCount()));
    }
    
    private void doProcess_NSAL0010Scrn00_TAB_InvHist(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {

        NSAL0010CMsg bizMsg = (NSAL0010CMsg) cMsg;

        if (ZYPCommonFunc.hasValue(bizMsg.svcMachMstrPk_H1)) {
            findInvHist(bizMsg, sMsg);
        }

        NSAL0010CommonLogic.preSetToPageOne(cMsg.xxPageShowFromNum_L);
        NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
        setValue(cMsg.xxPageShowOfNum_L, new BigDecimal(sMsg.L.getValidCount()));
        setValue(cMsg.xxPageShowToNum_L, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_L.getValueInt(), cMsg.L.getValidCount()));
    }

    /**
     * Method name: doProcess_NSAL0010Scrn00_DeleteDoNotSendTech <dd>
     * The method explanation: Insert Contract Information Line. <dd>
     * Remarks:
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NSAL0010Scrn00_DeleteDoNotSendTech(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {
        // START 2015/11/16 T.Tomita [QC#647, MOD]
        EZDMsg.copy(cMsg.E, null, sMsg.E, null);
        BigDecimal rowNum = cMsg.xxRadioBtn_E.getValue();
        if (!hasValue(rowNum)) {
            return;
        }

        List<Integer> delRows = Arrays.asList(rowNum.intValue());
        // mod start 2016/04/25 CSA Defect#5522
        int delListCnt = sMsg.P.getValidCount();
        ZYPEZDItemValueSetter.setValue(sMsg.P.no(delListCnt).svcNonPrfTechPk_P, cMsg.E.no(rowNum.intValue()).svcNonPrfTechPk_E);
        delListCnt++;
        sMsg.P.setValidCount(delListCnt);
        // mod end 2016/04/25 CSA Defect#5522
        ZYPTableUtil.deleteRows(sMsg.E, delRows);
        EZDMsg.copy(sMsg.E, null, cMsg.E, null);
        // END 2015/11/16 T.Tomita [QC#647, MOD]
    }

    /**
     * Method name: doProcess_NSAL0010Scrn00_InsertDoNotSendTech <dd>
     * The method explanation: Insert Contract Information Line. <dd>
     * Remarks:
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NSAL0010Scrn00_InsertDoNotSendTech(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {
        // START 2015/11/16 T.Tomita [QC#647, ADD]
        EZDMsg.copy(cMsg.E, null, sMsg.E, null);
        // END 2015/11/16 T.Tomita [QC#647, ADD]
        sMsg.E.setValidCount(sMsg.E.getValidCount() + 1);
        EZDMsg.copy(sMsg.E, null, cMsg.E, null);

    }

    private void findSvcMachMstr(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {

        EZDMsg.copy(cMsg, null, sMsg, null);
        // START 2015/11/12 T.Tomita [QC#526, ADD]
        // cMsg.svcMachMstrPk_H1.clear();
        // END 2015/11/12 T.Tomita [QC#526, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.xxSetFlg_MM, ZYPConstant.FLG_OFF_N);
        // START 2016/07/04 T.Tomita [QC#11164, ADD]
        cMsg.xxChkBox_H1.clear();
        cMsg.serNum_H2.clear();
        // END 2016/07/04 T.Tomita [QC#11164, ADD]

        // Header
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", getGlobalCompanyCode());
        // mod start 2017/03/07 CSA Defect#17990
        if (ZYPCommonFunc.hasValue(cMsg.svcMachMstrPk_H1)) {
            queryMap.put("svcMachMstrPk", cMsg.svcMachMstrPk_H1.getValue());
        } else if (ZYPCommonFunc.hasValue(cMsg.serNum_H1)) {
            queryMap.put("serNum", cMsg.serNum_H1.getValue());
        } else {
            return;
        }
        // mod end 2017/03/07 CSA Defect#17990
     // START 2017/01/12 N.Arai [QC#14614, MOD]
        // queryMap.put("rowNum", SEARCH_LIMIT_CNT);
        queryMap.put("rowNum", sMsg.A.length());
     // END 2017/01/12 N.Arai [QC#14614, MOD]

        // START 2016/06/10 [QC#9591, ADD]
        queryMap.put("yes", IWR_ENBL_FLG_YES);
        queryMap.put("no", IWR_ENBL_FLG_NO);
        queryMap.put("yyyymmddStartIndex", YYYYMMDD_START_INDEX);
        queryMap.put("yyyymmddLength", YYYYMMDD_LENGTH);
        // END   2016/06/10 [QC#9591, ADD]

        // START 2016/02/04 T.Tomita [QC#3312, ADD]
        if (!ZYPCommonFunc.hasValue(cMsg.svcMachMstrPk_H1)) {
            BigDecimal rsltCount = NSAL0010Query.getInstance().countSvcMachMstr(queryMap);
            if (rsltCount.compareTo(BigDecimal.ONE) > 0) {
                ZYPEZDItemValueSetter.setValue(cMsg.xxSetFlg_MM, ZYPConstant.FLG_ON_Y);
                return;
            }
        }
        // END 2016/02/04 T.Tomita [QC#3312, ADD]

        S21SsmEZDResult ssmResult = NSAL0010Query.getInstance().getSvcMachMstr(cMsg, queryMap, cMsg);

        if (ssmResult.isCodeNotFound()) {
            cMsg.setMessageInfo(NSAM0013E);
            return;
        }

        // START 2016/02/04 T.Tomita [QC#3312, DEL]
//        if (ssmResult.getQueryResultCount() > 1) {
//            //move to serial inquiry popup
//            ZYPEZDItemValueSetter.setValue(cMsg.xxSetFlg_MM, ZYPConstant.FLG_ON_Y);
//            return;
//        }
        // END 2016/02/04 T.Tomita [QC#3312, DEL]

        //set original machine master status
        ZYPEZDItemValueSetter.setValue(cMsg.svcMachMstrStsCd_H4, cMsg.svcMachMstrStsCd_H3);

        // START 2017/10/27 K.Kitachi [QC#21563, ADD]
        setMachStsPulldown(cMsg);
        // END 2017/10/27 K.Kitachi [QC#21563, ADD]

        //get Svc Config
        Map<String, Object> queryMapConf = new HashMap<String, Object>();
        queryMapConf.put("glblCmpyCd", getGlobalCompanyCode());
        queryMapConf.put("svcMachMstrPk", cMsg.svcMachMstrPk_H1.getValue());
      // START 2017/01/12 N.Arai [QC#14614, MOD]
        // queryMapConf.put("rowNum", SEARCH_LIMIT_CNT + 1);
        queryMapConf.put("rowNum", sMsg.A.length() + 1);

        ssmResult = NSAL0010Query.getInstance().getMachConfig(cMsg, queryMapConf, sMsg.A);
        // if (ssmResult.getQueryResultCount() > SEARCH_LIMIT_CNT) {
        if (ssmResult.getQueryResultCount() > sMsg.A.length()) {
            cMsg.setMessageInfo(NZZM0001W);
        }
        // END 2017/01/12 N.Arai [QC#14614, MOD]
        // add start 2016/03/30 CSA Defect#6092
        NSAL0010CommonLogic.setMachCntForConfig(cMsg, sMsg);
        // add end 2016/03/30 CSA Defect#6092
        cMsg.xxRadioBtn_A.setValue(0);

        // START 2018/08/23 K.Kitachi [QC#27773, ADD]
        NSAL0010CommonLogic.setStdWtyFlg(cMsg, sMsg);
        // END 2018/08/23 K.Kitachi [QC#27773, ADD]

        return;
    }

    private void refleshHeader(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg, BigDecimal svcMachMstrPk) {

        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", getGlobalCompanyCode());
        queryMap.put("svcMachMstrPk", svcMachMstrPk);
        queryMap.put("rowNum", SEARCH_LIMIT_CNT);

        // START 2016/06/10 [QC#9591, ADD]
        queryMap.put("yes", IWR_ENBL_FLG_YES);
        queryMap.put("no", IWR_ENBL_FLG_NO);
        queryMap.put("yyyymmddStartIndex", YYYYMMDD_START_INDEX);
        queryMap.put("yyyymmddLength", YYYYMMDD_LENGTH);
        // END   2016/06/10 [QC#9591, ADD]

        S21SsmEZDResult ssmResult = NSAL0010Query.getInstance().getSvcMachMstr(cMsg, queryMap, cMsg);

        if (ssmResult.isCodeNotFound()) {
            cMsg.setMessageInfo(NSAM0013E);
            return;
        }
    }
    
    private void refleshConfig(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg, BigDecimal svcMachMstrPk) {

        //get Svc Config
        Map<String, Object> queryMapConf = new HashMap<String, Object>();
        queryMapConf.put("glblCmpyCd", getGlobalCompanyCode());
        queryMapConf.put("svcMachMstrPk", svcMachMstrPk);
        queryMapConf.put("rowNum", SEARCH_LIMIT_CNT + 1);
    }

    // START 2016/04/18 T.Tomita [QC#6217, MOD]
    private void findContactsWithInactive(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {

        EZDMsg.copy(cMsg, null, sMsg, null);

        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", getGlobalCompanyCode());
        queryMap.put("svcMachMstrPk", cMsg.svcMachMstrPk_H1.getValue());
        // START 2016/03/24 M.Gotou [QC#4951, DEL]
//        queryMap.put("slsDt", cMsg.slsDt.getValue());
        // END 2016/03/24 M.Gotou [QC#4951, DEL]
        queryMap.put("rowNum", SEARCH_LIMIT_CNT + 1);
        // START 2016/03/24 M.Gotou [QC#4951, DEL]
//        // START 2016/02/22 T.Tomita [QC#969 ADD]
//        queryMap.put("minDt", MIN_DT_VAL);
//        queryMap.put("maxDt", MAX_DT_VAL);
//        // END 2016/02/22 T.Tomita [QC#969 MOD]
        // END 2016/03/24 M.Gotou [QC#4951, DEL]

        S21SsmEZDResult ssmResult = NSAL0010Query.getInstance().getSvcMachCtacPsn(cMsg, queryMap, sMsg.C);

        if (ssmResult.getQueryResultCount() > SEARCH_LIMIT_CNT) {
            cMsg.setMessageInfo(NZZM0001W);
        }

        cMsg.xxRadioBtn_A.setValue(0);
    }
    // END 2016/04/18 T.Tomita [QC#6217, MOD]

    // START 2016/04/18 T.Tomita [QC#6217, MOD]
    private void findContacts(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {

        EZDMsg.copy(cMsg, null, sMsg, null);

        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", getGlobalCompanyCode());
        queryMap.put("svcMachMstrPk", cMsg.svcMachMstrPk_H1.getValue());
        queryMap.put("slsDt", cMsg.slsDt.getValue());
        queryMap.put("rowNum", SEARCH_LIMIT_CNT + 1);
        // START 2016/02/22 T.Tomita [QC#969 ADD]
        queryMap.put("minDt", MIN_DT_VAL);
        // END 2016/02/22 T.Tomita [QC#969 MOD]

        S21SsmEZDResult ssmResult = NSAL0010Query.getInstance().getSvcMachCtacPsnIncTerm(cMsg, queryMap, sMsg.C);

        if (ssmResult.getQueryResultCount() > SEARCH_LIMIT_CNT) {
            cMsg.setMessageInfo(NZZM0001W);
        }

        cMsg.xxRadioBtn_A.setValue(0);
    }
    // END 2016/04/18 T.Tomita [QC#6217, MOD]

    private void findSvcNonPrfTech(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {

        EZDMsg.copy(cMsg, null, sMsg, null);
        // get Svc non prf tech
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", getGlobalCompanyCode());
        queryMap.put("svcMachMstrPk", cMsg.svcMachMstrPk_H1.getValue());
        queryMap.put("rowNum", SEARCH_LIMIT_CNT + 1);

        S21SsmEZDResult ssmResult = NSAL0010Query.getInstance().getSvcNonPrfTech(cMsg, queryMap, sMsg.E);

        if (ssmResult.getQueryResultCount() > SEARCH_LIMIT_CNT) {
            cMsg.setMessageInfo(NZZM0001W);
        }

        // START 2015/11/16 T.Tomita [QC#647, ADD]
        EZDMsg.copy(sMsg.E, null, cMsg.E, null);
        // END 2015/11/16 T.Tomita [QC#647, ADD]
        cMsg.xxRadioBtn_E.setValue(0);
    }

    private void findSlsHistory(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {

        EZDMsg.copy(cMsg, null, sMsg, null);

        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", getGlobalCompanyCode());
        queryMap.put("svcMachMstrPk", cMsg.svcMachMstrPk_H1.getValue());
        queryMap.put("rowNum", SEARCH_LIMIT_CNT + 1);

        S21SsmEZDResult ssmResult = NSAL0010Query.getInstance().getSlsHistory(cMsg, queryMap, sMsg.G);

        if (ssmResult.getQueryResultCount() > SEARCH_LIMIT_CNT) {
            cMsg.setMessageInfo(NZZM0001W);
        }

    }

    private void findIBHistory(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {

        EZDMsg.copy(cMsg, null, sMsg, null);

        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", getGlobalCompanyCode());
        queryMap.put("svcMachMstrPk", cMsg.svcMachMstrPk_H1.getValue());
        queryMap.put("svcMachMstrTrkPk", "SVC_MACH_MSTR_TRK_PK");
        queryMap.put("rowNum", SEARCH_LIMIT_CNT + 1);

        S21SsmEZDResult ssmResult = NSAL0010Query.getInstance().getIBHistory(cMsg, queryMap, sMsg.I);

        if (ssmResult.getQueryResultCount() > SEARCH_LIMIT_CNT) {
            cMsg.setMessageInfo(NZZM0001W);
        }

    }

    private void findContrSmry(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {

        EZDMsg.copy(cMsg, null, sMsg, null);

        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", getGlobalCompanyCode());
        queryMap.put("svcMachMstrPk", cMsg.svcMachMstrPk_H1.getValue());
        queryMap.put("rowNum", SEARCH_LIMIT_CNT + 1);

        S21SsmEZDResult ssmResult = NSAL0010Query.getInstance().getContrSmry(cMsg, queryMap, sMsg.J);

        if (ssmResult.getQueryResultCount() > SEARCH_LIMIT_CNT) {
            cMsg.setMessageInfo(NZZM0001W);
        }

    }

    private void findSvcCallHist(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {

        EZDMsg.copy(cMsg, null, sMsg, null);

        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", getGlobalCompanyCode());
        queryMap.put("svcMachMstrPk", cMsg.svcMachMstrPk_H1.getValue());
        queryMap.put("rowNum", SEARCH_LIMIT_CNT + 1);

        S21SsmEZDResult ssmResult = NSAL0010Query.getInstance().getSvcCallHist(cMsg, queryMap, sMsg.K);

        if (ssmResult.getQueryResultCount() > SEARCH_LIMIT_CNT) {
            cMsg.setMessageInfo(NZZM0001W);
        }

    }

    private void findInvHist(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {

        EZDMsg.copy(cMsg, null, sMsg, null);

        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", getGlobalCompanyCode());
        queryMap.put("svcMachMstrPk", cMsg.svcMachMstrPk_H1.getValue());
        queryMap.put("rowNum", SEARCH_LIMIT_CNT + 1);

        S21SsmEZDResult ssmResult = NSAL0010Query.getInstance().getInvHist(cMsg, queryMap, sMsg.L);

        if (ssmResult.getQueryResultCount() > SEARCH_LIMIT_CNT) {
            cMsg.setMessageInfo(NZZM0001W);
        }

    }

    private void findSolutions(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {

        EZDMsg.copy(cMsg, null, sMsg, null);

        // START 2015/11/11 T.Tomita [QC#607, MOD]
        ZYPTableUtil.clear(sMsg.M);
        // END 2015/11/11 T.Tomita [QC#607, MOD]

        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", getGlobalCompanyCode());
        queryMap.put("svcMachMstrPk", cMsg.svcMachMstrPk_H1.getValue());
        queryMap.put("rowNum", SEARCH_LIMIT_CNT + 1);

        S21SsmEZDResult ssmResult = NSAL0010Query.getInstance().getSolution(cMsg, queryMap, sMsg.B);

        if (ssmResult.getQueryResultCount() > SEARCH_LIMIT_CNT) {
            cMsg.setMessageInfo(NZZM0001W);
        }

     // START 2017/01/17 N.Arai [QC#14614, MOD]
        // cMsg.xxRadioBtn_A.setValue(0);
        cMsg.xxRadioBtn_B.setValue(0);
     // END 2017/01/17 N.Arai [QC#14614, MOD]
    }

    /**
     * set Header Info
     * @param smsg
     * @param map
     */
    private void setHeaderInfo(NSAL0010CMsg cMsg) {
        ZYPEZDItemValueSetter.setValue(cMsg.xxLocRoleTpCdListTxt_M1, DPLY_OWNER);
        ZYPEZDItemValueSetter.setValue(cMsg.xxLocRoleTpCdListTxt_M2, DPLY_BILL_TO);
        ZYPEZDItemValueSetter.setValue(cMsg.xxLocRoleTpCdListTxt_M3, DPLY_CUR_LOC);
    }

    private void doProcess_NSAL0010Scrn00_PagePrev(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {

        if (cMsg.xxDplyTab_01.getValue().equals(TAB_MACH_CONFIG)) {
            if (!hasValue(cMsg.xxPageShowFromNum_A)) {
                return;
            }
            setValue(cMsg.xxPageShowFromNum_A, cMsg.xxPageShowFromNum_A.getValue().subtract(new BigDecimal(cMsg.A.length())));
            NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
            setValue(cMsg.xxPageShowOfNum_A, new BigDecimal(sMsg.A.getValidCount()));
            setValue(cMsg.xxPageShowToNum_A, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_A.getValueInt(), cMsg.A.getValidCount()));
        } else if (cMsg.xxDplyTab_01.getValue().equals(TAB_SOLUTION)) {
            if (!hasValue(cMsg.xxPageShowFromNum_B)) {
                return;
            }
            setValue(cMsg.xxPageShowFromNum_B, cMsg.xxPageShowFromNum_B.getValue().subtract(new BigDecimal(cMsg.B.length())));
            NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
            setValue(cMsg.xxPageShowOfNum_B, new BigDecimal(sMsg.B.getValidCount()));
            setValue(cMsg.xxPageShowToNum_B, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_B.getValueInt(), cMsg.B.getValidCount()));
        } else if (cMsg.xxDplyTab_01.getValue().equals(TAB_CONTACTS)) {
            if (!hasValue(cMsg.xxPageShowFromNum_C)) {
                return;
            }
            setValue(cMsg.xxPageShowFromNum_C, cMsg.xxPageShowFromNum_C.getValue().subtract(new BigDecimal(cMsg.C.length())));
            NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
            setValue(cMsg.xxPageShowOfNum_C, new BigDecimal(sMsg.C.getValidCount()));
            setValue(cMsg.xxPageShowToNum_C, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_C.getValueInt(), cMsg.C.getValidCount()));
        } else if (cMsg.xxDplyTab_01.getValue().equals(TAB_SLS_ORD_HIST)) {
            if (!hasValue(cMsg.xxPageShowFromNum_G)) {
                return;
            }
            setValue(cMsg.xxPageShowFromNum_G, cMsg.xxPageShowFromNum_G.getValue().subtract(new BigDecimal(cMsg.G.length())));
            NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
            setValue(cMsg.xxPageShowOfNum_G, new BigDecimal(sMsg.G.getValidCount()));
            setValue(cMsg.xxPageShowToNum_G, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_G.getValueInt(), cMsg.G.getValidCount()));
        } else if (cMsg.xxDplyTab_01.getValue().equals(TAB_IB_HISTORY)) {
            if (!hasValue(cMsg.xxPageShowFromNum_I)) {
                return;
            }
            setValue(cMsg.xxPageShowFromNum_I, cMsg.xxPageShowFromNum_I.getValue().subtract(new BigDecimal(cMsg.I.length())));
            NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
            setValue(cMsg.xxPageShowOfNum_I, new BigDecimal(sMsg.I.getValidCount()));
            setValue(cMsg.xxPageShowToNum_I, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_I.getValueInt(), cMsg.I.getValidCount()));
        } else if (cMsg.xxDplyTab_01.getValue().equals(TAB_CONTR_SMRY)) {
            if (!hasValue(cMsg.xxPageShowFromNum_J)) {
                return;
            }
            setValue(cMsg.xxPageShowFromNum_J, cMsg.xxPageShowFromNum_J.getValue().subtract(new BigDecimal(cMsg.J.length())));
            NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
            setValue(cMsg.xxPageShowOfNum_J, new BigDecimal(sMsg.J.getValidCount()));
            setValue(cMsg.xxPageShowToNum_J, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_J.getValueInt(), cMsg.J.getValidCount()));
        } else if (cMsg.xxDplyTab_01.getValue().equals(TAB_SVC_CALL_HIST)) {
            if (!hasValue(cMsg.xxPageShowFromNum_K)) {
                return;
            }
            setValue(cMsg.xxPageShowFromNum_K, cMsg.xxPageShowFromNum_K.getValue().subtract(new BigDecimal(cMsg.K.length())));
            NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
            setValue(cMsg.xxPageShowOfNum_K, new BigDecimal(sMsg.K.getValidCount()));
            setValue(cMsg.xxPageShowToNum_K, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_K.getValueInt(), cMsg.K.getValidCount()));
        } else if (cMsg.xxDplyTab_01.getValue().equals(TAB_INVOICE_HIST)) {
            if (!hasValue(cMsg.xxPageShowFromNum_L)) {
                return;
            }
            setValue(cMsg.xxPageShowFromNum_L, cMsg.xxPageShowFromNum_L.getValue().subtract(new BigDecimal(cMsg.L.length())));
            NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
            setValue(cMsg.xxPageShowOfNum_L, new BigDecimal(sMsg.L.getValidCount()));
            setValue(cMsg.xxPageShowToNum_L, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_L.getValueInt(), cMsg.L.getValidCount()));
        }
    }

    private void doProcess_NSAL0010Scrn00_PageNext(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {

        if (cMsg.xxDplyTab_01.getValue().equals(TAB_MACH_CONFIG)) {
            if (!hasValue(cMsg.xxPageShowFromNum_A)) {
                return;
            }
            setValue(cMsg.xxPageShowFromNum_A, cMsg.xxPageShowToNum_A.getValue().add(BigDecimal.ONE));
            NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
            setValue(cMsg.xxPageShowOfNum_A, new BigDecimal(sMsg.A.getValidCount()));
            setValue(cMsg.xxPageShowToNum_A, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_A.getValueInt(), cMsg.A.getValidCount()));
        } else if (cMsg.xxDplyTab_01.getValue().equals(TAB_SOLUTION)) {
            if (!hasValue(cMsg.xxPageShowFromNum_B)) {
                return;
            }
            setValue(cMsg.xxPageShowFromNum_B, cMsg.xxPageShowToNum_B.getValue().add(BigDecimal.ONE));
            NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
            setValue(cMsg.xxPageShowOfNum_B, new BigDecimal(sMsg.B.getValidCount()));
            setValue(cMsg.xxPageShowToNum_B, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_B.getValueInt(), cMsg.B.getValidCount()));
        } else if (cMsg.xxDplyTab_01.getValue().equals(TAB_CONTACTS)) {
            if (!hasValue(cMsg.xxPageShowFromNum_C)) {
                return;
            }
            setValue(cMsg.xxPageShowFromNum_C, cMsg.xxPageShowToNum_C.getValue().add(BigDecimal.ONE));
            NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
            setValue(cMsg.xxPageShowOfNum_C, new BigDecimal(sMsg.C.getValidCount()));
            setValue(cMsg.xxPageShowToNum_C, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_C.getValueInt(), cMsg.C.getValidCount()));
        } else if (cMsg.xxDplyTab_01.getValue().equals(TAB_SLS_ORD_HIST)) {
            if (!hasValue(cMsg.xxPageShowFromNum_G)) {
                return;
            }
            setValue(cMsg.xxPageShowFromNum_G, cMsg.xxPageShowToNum_G.getValue().add(BigDecimal.ONE));
            NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
            setValue(cMsg.xxPageShowOfNum_G, new BigDecimal(sMsg.G.getValidCount()));
            setValue(cMsg.xxPageShowToNum_G, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_G.getValueInt(), cMsg.G.getValidCount()));
        } else if (cMsg.xxDplyTab_01.getValue().equals(TAB_IB_HISTORY)) {
            if (!hasValue(cMsg.xxPageShowFromNum_I)) {
                return;
            }
            setValue(cMsg.xxPageShowFromNum_I, cMsg.xxPageShowToNum_I.getValue().add(BigDecimal.ONE));
            NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
            setValue(cMsg.xxPageShowOfNum_I, new BigDecimal(sMsg.I.getValidCount()));
            setValue(cMsg.xxPageShowToNum_I, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_I.getValueInt(), cMsg.I.getValidCount()));
        } else if (cMsg.xxDplyTab_01.getValue().equals(TAB_CONTR_SMRY)) {
            if (!hasValue(cMsg.xxPageShowFromNum_J)) {
                return;
            }
            setValue(cMsg.xxPageShowFromNum_J, cMsg.xxPageShowToNum_J.getValue().add(BigDecimal.ONE));
            NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
            setValue(cMsg.xxPageShowOfNum_J, new BigDecimal(sMsg.J.getValidCount()));
            setValue(cMsg.xxPageShowToNum_J, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_J.getValueInt(), cMsg.J.getValidCount()));
        } else if (cMsg.xxDplyTab_01.getValue().equals(TAB_SVC_CALL_HIST)) {
            if (!hasValue(cMsg.xxPageShowFromNum_K)) {
                return;
            }
            setValue(cMsg.xxPageShowFromNum_K, cMsg.xxPageShowToNum_K.getValue().add(BigDecimal.ONE));
            NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
            setValue(cMsg.xxPageShowOfNum_K, new BigDecimal(sMsg.K.getValidCount()));
            setValue(cMsg.xxPageShowToNum_K, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_K.getValueInt(), cMsg.K.getValidCount()));
        } else if (cMsg.xxDplyTab_01.getValue().equals(TAB_INVOICE_HIST)) {
            if (!hasValue(cMsg.xxPageShowFromNum_L)) {
                return;
            }
            setValue(cMsg.xxPageShowFromNum_L, cMsg.xxPageShowToNum_L.getValue().add(BigDecimal.ONE));
            NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
            setValue(cMsg.xxPageShowOfNum_L, new BigDecimal(sMsg.L.getValidCount()));
            setValue(cMsg.xxPageShowToNum_L, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_L.getValueInt(), cMsg.L.getValidCount()));
        }
    }

    private void doProcess_NSAL0010Scrn00_TBLColumnSort(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {

        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        if ("L".equals(sortTblNm)) {
            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.L, sMsg.L.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.L.getValidCount());

            // SMsg -> CMsg
            int i = 0;
            for (; i < sMsg.L.getValidCount(); i++) {
                if (i == cMsg.L.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.L.no(i), null, cMsg.L.no(i), null);
            }
            cMsg.L.setValidCount(i);

            // Show 1st page
            setValue(cMsg.xxPageShowFromNum_L, BigDecimal.ONE);
            setValue(cMsg.xxPageShowToNum_L, new BigDecimal(cMsg.L.getValidCount()));
        }
    }

    private void doProcess_NSAL0010Scrn00_SearchMdseName(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {
     // START 2016/09/21 N.Arai [QC#11616, MOD]
        MDSETMsg mdse = NSAL0010CommonLogic.getMdse(getGlobalCompanyCode(), cMsg.mdseCd_H1.getValue());
        if (mdse == null) {
            // cMsg.mdseNm_H1.clear();
            cMsg.mdseDescShortTxt_H1.clear();
            cMsg.mdseCd_H1.setErrorInfo(1, NSAM0063E, new String[] {"Mdse Code", "Mdse Master" });
            return;
        }
        // ZYPEZDItemValueSetter.setValue(cMsg.mdseNm_H1, mdse.mdseNm);
        ZYPEZDItemValueSetter.setValue(cMsg.mdseDescShortTxt_H1, mdse.mdseDescShortTxt);
     // END 2016/09/21 N.Arai [QC#11616, MOD]
    }

// START 2023/07/10 Y.Nagasawa [QC#61524, DEL]
//    private void doProcess_NSAL0010Scrn00_SelectPostalCode(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {
//        POSTTMsg post = NSAL0010CommonLogic.getPost(getGlobalCompanyCode(), cMsg.postCd_F.getValue());
//        if (post == null) {
//            cMsg.ctyAddr_F.clear();
//            cMsg.stCd_F.clear();
//            cMsg.postCd_F.setErrorInfo(1, NSAM0063E, new String[] {"Post Code", "POST Master" });
//            return;
//        }
//        ZYPEZDItemValueSetter.setValue(cMsg.ctyAddr_F, post.ctyAddr);
//        ZYPEZDItemValueSetter.setValue(cMsg.stCd_F, post.stCd);
//    }
// END 2023/07/10 Y.Nagasawa [QC#61524, DEL]

    private void doProcess_NSAL0010Scrn00_CMN_Download(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {

        ResultSet rs = null;
        PreparedStatement stmtSelect = null;

        try {
            NSAL0010Query query = NSAL0010Query.getInstance();
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE_MAX);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(query.getClass());

            //create csv file
            cMsg.xxFileData.setTempFilePath(null,
                    ZYPCSVOutFile.createCSVOutFileNm(BUSINESS_ID + "_" + getUserProfileService().getContextUserInfo().getUserId()),
                    ".csv");

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("glblCmpyCd", getGlobalCompanyCode());
            params.put("svcMachMstrPk", cMsg.svcMachMstrPk_H1.getValue());
            params.put("svcMachMstrTrkPk", "SVC_MACH_MSTR_TRK_PK");
            params.put("rowNum", DOWNLOAD_LIMIT_CNT + 1);

            if (TAB_CONTACTS.equals(cMsg.xxDplyTab_01.getValue())) {
                params.put("slsDt", cMsg.slsDt.getValue());
                // START 2016/02/26 T.Tomita [QC#942, ADD]
                params.put("minDt", MIN_DT_VAL);
                params.put("maxDt", MAX_DT_VAL);
                // END 2016/02/26 T.Tomita [QC#942, ADD]
                stmtSelect = ssmLLClient.createPreparedStatement("getSvcMachCtacPsn", params, execParam);
                rs = stmtSelect.executeQuery();
                NSAL0010F00FMsg fMsg = new NSAL0010F00FMsg();
                ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);
                writeCsvFileForContacts(cMsg, rs, fMsg, csvOutFile);

            } else if (TAB_SLS_ORD_HIST.equals(cMsg.xxDplyTab_01.getValue())) {
                stmtSelect = ssmLLClient.createPreparedStatement("getSlsHistory", params, execParam);
                rs = stmtSelect.executeQuery();
                NSAL0010F01FMsg fMsg = new NSAL0010F01FMsg();
                ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);
                writeCsvFileForSlsHist(cMsg, rs, fMsg, csvOutFile);

            } else if (TAB_IB_HISTORY.equals(cMsg.xxDplyTab_01.getValue())) {
                stmtSelect = ssmLLClient.createPreparedStatement("getIbHist", params, execParam);
                rs = stmtSelect.executeQuery();
                NSAL0010F02FMsg fMsg = new NSAL0010F02FMsg();
                ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);
                writeCsvFileForIBHist(cMsg, rs, fMsg, csvOutFile);

            } else if (TAB_CONTR_SMRY.equals(cMsg.xxDplyTab_01.getValue())) {
                stmtSelect = ssmLLClient.createPreparedStatement("getContrSmry", params, execParam);
                rs = stmtSelect.executeQuery();
                NSAL0010F03FMsg fMsg = new NSAL0010F03FMsg();
                ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);
                writeCsvFileForContrSmry(cMsg, rs, fMsg, csvOutFile);

            } else if (TAB_SVC_CALL_HIST.equals(cMsg.xxDplyTab_01.getValue())) {
                stmtSelect = ssmLLClient.createPreparedStatement("getSvcCallHist", params, execParam);
                rs = stmtSelect.executeQuery();
                NSAL0010F04FMsg fMsg = new NSAL0010F04FMsg();
                ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);
                writeCsvFileForSvcCallHist(cMsg, rs, fMsg, csvOutFile);

            } else if (TAB_INVOICE_HIST.equals(cMsg.xxDplyTab_01.getValue())) {
                stmtSelect = ssmLLClient.createPreparedStatement("getInvHist", params, execParam);
                rs = stmtSelect.executeQuery();
                NSAL0010F05FMsg fMsg = new NSAL0010F05FMsg();
                ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);
                writeCsvFileForInvHist(cMsg, rs, fMsg, csvOutFile);
            }

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }

    }

    private void writeCsvFileForContacts(NSAL0010CMsg cMsg, ResultSet rs,
            NSAL0010F00FMsg fMsg, ZYPCSVOutFile csvOutFile) throws SQLException {
        // mod start 2016/07/01 CSA Defect#11110
        // contacts
        final String[] csvHeader = new String[] {"Last Name", "First Name", "Contact Point", "Contact Value", "Ext", "Contact Type", "Contact ID", "Primary", "Start Date", "End Date" };
        // mod end 2016/07/01 CSA Defect#11110
        csvOutFile.writeHeader(csvHeader);

        //write contents
        while (rs.next()) {
            if (rs.getRow() > DOWNLOAD_LIMIT_CNT) {
                cMsg.setMessageInfo("NZZM0001W", null);
                break;
            }

            //resultset -> fMsg
            setValue(fMsg.ctacPsnLastNm_C, rs.getString("CTAC_PSN_LAST_NM_C"));
            setValue(fMsg.ctacPsnFirstNm_C, rs.getString("CTAC_PSN_FIRST_NM_C"));
            setValue(fMsg.dsCtacPntTpCd_CS, rs.getString("DS_CTAC_PNT_TP_CD_CS"));
            setValue(fMsg.dsCtacPntValTxt_C, rs.getString("DS_CTAC_PNT_VAL_TXT_C"));
            setValue(fMsg.dsCtacPsnExtnNum_C, rs.getString("DS_CTAC_PSN_EXTN_NUM_C"));
            setValue(fMsg.svcCtacTpCd_CS, rs.getString("SVC_CTAC_TP_CD_CS"));
            setValue(fMsg.ctacPsnPk_C, rs.getBigDecimal("CTAC_PSN_PK_C"));
            setValue(fMsg.xxChkBox_C, rs.getString("XX_CHK_BOX_C"));
            setValue(fMsg.xxDtTxt_CF, convertDateFormat(rs.getString("EFF_FROM_DT_C")));
            setValue(fMsg.xxDtTxt_CT, convertDateFormat(rs.getString("EFF_THRU_DT_C")));

            csvOutFile.write();
        }

        csvOutFile.close();
    }

    private void writeCsvFileForSlsHist(NSAL0010CMsg cMsg, ResultSet rs,
            NSAL0010F01FMsg fMsg, ZYPCSVOutFile csvOutFile) throws SQLException {

        // Sls Ord Hist
        final String[] csvHeader = new String[] {"Order Number", "Order Date", "Order Type", "Order Reason", "Line Type", "Account Sold To Name", "Account Bill To Name", "Account Ship To Name", "Ship To Address" };

        csvOutFile.writeHeader(csvHeader);

        //write contents
        while (rs.next()) {
            if (rs.getRow() > DOWNLOAD_LIMIT_CNT) {
                cMsg.setMessageInfo("NZZM0001W", null);
                break;
            }

            //resultset -> fMsg
            setValue(fMsg.cpoOrdNum_G, rs.getString("CPO_ORD_NUM"));
         // START 2017/02/06 N.Arai [QC#17197, MOD]
            // setValue(fMsg.xxDtTxt_G, convertDateFormat(rs.getString("CPO_ORD_TS")));
            setValue(fMsg.xxDtTxt_G, convertDateFormat(rs.getString("CPO_ORD_DT")));
         // END 2017/02/06 N.Arai [QC#17197, MOD]
            setValue(fMsg.dsOrdCatgDescTxt_G, rs.getString("DS_ORD_CATG_DESC_TXT"));
            setValue(fMsg.dsOrdTpDescTxt_G, rs.getString("DS_ORD_TP_DESC_TXT"));
            setValue(fMsg.dsOrdLineCatgDescTxt_G, rs.getString("DS_ORD_LINE_CATG_DESC_TXT"));
            setValue(fMsg.locNm_G1, rs.getString("SELL_TO_LOC_NM"));
            setValue(fMsg.locNm_G2, rs.getString("BILL_TO_LOC_NM"));
            setValue(fMsg.shipToLocNm_G, rs.getString("SHIP_TO_LOC_NM"));
            setValue(fMsg.shipToAddr_G, rs.getString("SHIP_TO_ADDR"));

            csvOutFile.write();
        }

        csvOutFile.close();
    }

    private void writeCsvFileForIBHist(NSAL0010CMsg cMsg, ResultSet rs,
            NSAL0010F02FMsg fMsg, ZYPCSVOutFile csvOutFile) throws SQLException {

        // IB Hist
        final String[] csvHeader = new String[] {"Transaction Date", "Field Update", "Old Value", "New Value", "Updated By", "Note" };

        csvOutFile.writeHeader(csvHeader);

        //write contents
        while (rs.next()) {
            if (rs.getRow() > DOWNLOAD_LIMIT_CNT) {
                cMsg.setMessageInfo("NZZM0001W", null);
                break;
            }

            //resultset -> fMsg
            setValue(fMsg.xxDtTxt_I, convertDateFormat(rs.getString("TRX_RGTN_DT")));
            // QC#26454 Mod Start
//            setValue(fMsg.updFldTxt_I, rs.getString("UPD_USR_ID"));
            setValue(fMsg.updFldTxt_I, rs.getString("MACH_HIST_ATTRB_LONG_NM"));
            // QC#26454 Mod End
            setValue(fMsg.oldValTxt_I, rs.getString("OLD_VAL_TXT"));
            setValue(fMsg.newValTxt_I, rs.getString("NEW_VAL_TXT"));
            setValue(fMsg.updUsrId_I, rs.getString("UPD_USR_ID"));
            setValue(fMsg.xxYesNoCd_I, rs.getString("EXIST_SVC_MEMO"));

            csvOutFile.write();
        }

        csvOutFile.close();
    }

    private void writeCsvFileForContrSmry(NSAL0010CMsg cMsg, ResultSet rs,
            NSAL0010F03FMsg fMsg, ZYPCSVOutFile csvOutFile) throws SQLException {

        final String[] csvHeader = new String[] {"Contract#", "Status", "Start Date", "End Date", "Contract Type", "Supply Inclusive", "Billing Cycle", "Meter Read Method", "Line Description", "Service Branch" };

        csvOutFile.writeHeader(csvHeader);

        NSXC001001GetCovTmpl covTmpl = new NSXC001001GetCovTmpl();

        //write contents
        while (rs.next()) {
            if (rs.getRow() > DOWNLOAD_LIMIT_CNT) {
                cMsg.setMessageInfo("NZZM0001W", null);
                break;
            }

            //resultset -> fMsg
            setValue(fMsg.dsContrNum_J, rs.getString("DS_CONTR_NUM"));
            setValue(fMsg.dsContrCtrlStsNm_J, rs.getString("DS_CONTR_CTRL_STS_NM"));
            setValue(fMsg.xxDtTxt_JF, convertDateFormat(rs.getString("CONTR_EFF_FROM_DT")));
            setValue(fMsg.xxDtTxt_JT, convertDateFormat(rs.getString("CONTR_EFF_THRU_DT")));
            setValue(fMsg.dsContrCatgDescTxt_J, rs.getString("DS_CONTR_CATG_DESC_TXT"));

            setValue(fMsg.xxChkBox_J, ZYPConstant.FLG_OFF_N);
            if (ZYPCommonFunc.hasValue(rs.getString("SVC_PGM_MDSE_CD"))) {
                CovTmplInfo tmplInfo = new CovTmplInfo();
                tmplInfo.setGlblCmpyCd(cMsg.glblCmpyCd.getValue());
                tmplInfo.setSlsDt(cMsg.slsDt.getValue());
                tmplInfo.setSvcPgmMdseCd(rs.getString("SVC_PGM_MDSE_CD"));
                boolean isSuplIncl = covTmpl.isSuplIncl(tmplInfo);

                if (isSuplIncl) {
                    setValue(fMsg.xxChkBox_J, ZYPConstant.FLG_ON_Y);
                }
            }
            setValue(fMsg.bllgCycleDescTxt_J, rs.getString("BLLG_CYCLE_DESC_TXT"));
            setValue(fMsg.mtrReadMethDescTxt_J, rs.getString("MTR_READ_METH_DESC_TXT"));
            setValue(fMsg.svcContrRefCmntTxt_J, rs.getString("SVC_CONTR_REF_CMNT_TXT"));
            setValue(fMsg.svcContrBrDescTxt_J, rs.getString("SVC_CONTR_BR_DESC_TXT"));

            csvOutFile.write();
        }

        csvOutFile.close();
    }

    private void writeCsvFileForSvcCallHist(NSAL0010CMsg cMsg, ResultSet rs,
            NSAL0010F04FMsg fMsg, ZYPCSVOutFile csvOutFile) throws SQLException {
      //START 2017/07/20 U.Kim [QC#19995, MOD]
        //final String[] csvHeader = new String[] {"FSR#", "Customer Name", "Status", "Date Reserved", "Problem Description", "Resolution", "Start Date", "End Date", "Service Manager" };
        final String[] csvHeader = new String[] {"FSR#", "Task#", "Task Type", "Customer Name", "Status", "Date Reserved", "Problem Description", "Resolution", "Start Date", "End Date", "Service Manager" };
      //END 2017/07/20 U.Kim [QC#19995, MOD]
        csvOutFile.writeHeader(csvHeader);

        //write contents
        while (rs.next()) {
            if (rs.getRow() > DOWNLOAD_LIMIT_CNT) {
                cMsg.setMessageInfo("NZZM0001W", null);
                break;
            }

            //resultset -> fMsg
            setValue(fMsg.fsrNum_K, rs.getString("FSR_NUM"));
            //START 2017/07/20 U.Kim [QC#19995, ADD]
            setValue(fMsg.svcTaskNum_K, rs.getString("SVC_TASK_NUM"));
            //START 2017/08/03 U.Kim [QC#19995, MOD]
            //setValue(fMsg.dsSvcCallTpDescTxt_K, rs.getString("DS_SVC_CALL_TP_DESC_TXT"));
            setValue(fMsg.xxScrItem54Txt_K, rs.getString("XX_SCR_ITEM_54_TXT"));
            //START 2017/08/03 U.Kim [QC#19995, MOD]
            //START 2017/07/20 U.Kim [QC#19995, ADD]
            setValue(fMsg.locNm_K, rs.getString("LOC_NM"));
            setValue(fMsg.svcTaskStsNm_K, rs.getString("TASK_STS_NM"));
            setValue(fMsg.xxDtTxt_KR, convertDateFormat(rs.getString("SVC_TASK_RCV_DT")));
            setValue(fMsg.svcPblmTpDescTxt_K, rs.getString("SVC_PBLM_TP_DESC_TXT"));
            setValue(fMsg.svcPblmCrctTpDescTxt_K, rs.getString("SVC_PBLM_CRCT_TP_DESC_TXT"));
            setValue(fMsg.xxDtTxt_KS, convertDateFormat(rs.getString("SVC_TASK_SCHD_DT")));
            setValue(fMsg.xxDtTxt_KC, convertDateFormat(rs.getString("SVC_TASK_CLO_DT")));
            setValue(fMsg.xxFullNm_K, rs.getString("SVC_MNGR"));

            csvOutFile.write();
        }

        csvOutFile.close();
    }

    private void writeCsvFileForInvHist(NSAL0010CMsg cMsg, ResultSet rs,
            NSAL0010F05FMsg fMsg, ZYPCSVOutFile csvOutFile) throws SQLException {

        final String[] csvHeader = new String[] {"Invoice#", "Source Type", "Contract#", "Bill To", "Ship To", "Invoice Type", "Total", "Due", "Applied", "Due Date", "Source#", "Invoice Date", "GL Posted Date" };
        csvOutFile.writeHeader(csvHeader);

        //write contents
        while (rs.next()) {
            if (rs.getRow() > DOWNLOAD_LIMIT_CNT) {
                cMsg.setMessageInfo("NZZM0001W", null);
                break;
            }

            //resultset -> fMsg
            setValue(fMsg.invNum_L, rs.getString("INV_NUM"));
            setValue(fMsg.sysSrcDescTxt_L, rs.getString("SYS_SRC_DESC_TXT"));
            setValue(fMsg.dsContrNum_L, rs.getString("DS_CONTR_NUM"));
            setValue(fMsg.locNm_L1, rs.getString("BILL_TO_LOC_NM"));
            setValue(fMsg.locNm_L2, rs.getString("SHIP_TO_LOC_NM"));
            setValue(fMsg.invTpDescTxt_L, rs.getString("INV_TP_DESC_TXT"));
            setValue(fMsg.invTotDealNetAmt_L, rs.getBigDecimal("INV_TOT_DEAL_NET_AMT"));
            setValue(fMsg.dealRmngBalGrsAmt_L, rs.getBigDecimal("DEAL_RMNG_BAL_GRS_AMT"));
            setValue(fMsg.xxApplyGrsAmt_L, rs.getBigDecimal("APPL_AMT"));
            setValue(fMsg.xxDtTxt_LN, convertDateFormat(rs.getString("NET_DUE_DT")));
            setValue(fMsg.cpoOrdNum_L, rs.getString("CPO_ORD_NUM"));
            setValue(fMsg.xxDtTxt_LI, convertDateFormat(rs.getString("INV_DT")));
            setValue(fMsg.xxDtTxt_LA, convertDateFormat(rs.getString("ACCT_DT")));

            csvOutFile.write();
        }

        csvOutFile.close();
    }

    private String convertDateFormat(String date) {
        if (hasValue(date)) {
            date = ZYPDateUtil.formatEzd8ToDisp(date);
        }
        return date;
    }

    // START 2016/02/04 T.Tomita [QC#3312, DEL]
//    private void doProcess_NSAL0010_NWAL1130(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {
//        // START 2015/11/11 T.Tomita [QC#569, MOD]
//        if (cMsg.xxScrEventNm.getValue().equals(SEARCH)){
//            doProcess_NSAL0010Scrn00_Search(cMsg, sMsg);
//        } else if (cMsg.xxScrEventNm.getValue().equals(OPENWIN_MACHIDA)) {
//            setMachInfo(cMsg, sMsg);
//        } else if (OPENWIN_LINKNEWCONFIG.equals(cMsg.xxScrEventNm.getValue())) {
//            doProcess_NSAL0010Scrn00_OpenWin_LinkNewConfig(cMsg, sMsg);
//        }
//        // END 2015/11/11 T.Tomita [QC#569, MOD]
//    }
    // END 2016/02/04 T.Tomita [QC#3312, DEL]
    // START 2016/02/04 T.Tomita [QC#3312, ADD]
    private void doProcess_NSAL0010_NSAL1240(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {
        if (cMsg.xxScrEventNm.getValue().equals(SEARCH)){
            doProcess_NSAL0010Scrn00_Search(cMsg, sMsg);
        } else if (cMsg.xxScrEventNm.getValue().equals(OPENWIN_SERIALNUM)) {
            // START 2016/05/26 T.Tomita [QC#8894, MOD]
            if (!hasValue(cMsg.svcMachMstrPk_H1) && !hasValue(cMsg.serNum_H1)) {
                return;
            }
            doProcess_NSAL0010Scrn00_Search(cMsg, sMsg);
            // END 2016/05/26 T.Tomita [QC#8894, MOD]
        } else if (cMsg.xxScrEventNm.getValue().equals(OPENWIN_MACHIDA)) {
            setMachInfo(cMsg, sMsg);
        } else if (OPENWIN_LINKNEWCONFIG.equals(cMsg.xxScrEventNm.getValue())) {
            doProcess_NSAL0010Scrn00_OpenWin_LinkNewConfig(cMsg, sMsg);
        }
    }
    // END 2016/02/04 T.Tomita [QC#3312, ADD]

    // mod start 2016/04/25 CSA Defect#5522
    private void setMachInfo(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {
        if (!hasValue(cMsg.xxRowNum)) {
            return;
        }

     // START 2017/01/12 N.Arai [QC#14614, MOD]
        // int addCount = sMsg.A.getValidCount() + cMsg.O.getValidCount();
        int addCount = sMsg.A.getValidCount() + (cMsg.O.getValidCount() - 1);
     // END 2017/01/12 N.Arai [QC#14614, MOD]
        if (addCount > sMsg.A.length()) {
            cMsg.setMessageInfo(NSAM0320E, new String[] {"Config Item", String.valueOf(sMsg.A.length()) });
            return;
        }

        int selectIndex = cMsg.xxPageShowFromNum_A.getValueInt() - 1 + cMsg.xxRowNum.getValueInt();
        int sMsgIndex = 0;
        NSAL0010_ASMsg asMsg;
        for (int i = 0; i < cMsg.O.getValidCount(); i++) {
            sMsgIndex = sMsg.A.getValidCount();
         // START 2017/01/12 N.Arai [QC#14614, MOD]
            // asMsg = sMsg.A.no(sMsgIndex);
            if (i == 0) {
                asMsg = sMsg.A.no(selectIndex);
            } else {
                asMsg = sMsg.A.no(sMsgIndex);
            }
         // END 2017/01/12 N.Arai [QC#14614, MOD]

            // set Value
            setValue(asMsg.serNum_A, cMsg.O.no(i).serNum_O);
            setValue(asMsg.mdseCd_A, cMsg.O.no(i).mdseCd_O);
         // START 2016/09/21 N.Arai [QC#11616, MOD]
         // setValue(asMsg.mdseNm_A, getMdseNm(getGlobalCompanyCode(), cMsg.O.no(i).mdseCd_O.getValue()));
            setValue(asMsg.mdseDescShortTxt_A, getMdseDescShortTxt(getGlobalCompanyCode(), cMsg.O.no(i).mdseCd_O.getValue()));
         // END 2016/09/21 N.Arai [QC#11616, MOD]
            setValue(asMsg.svcMachMstrStsCd_A, cMsg.O.no(i).svcMachMstrStsCd_O);
            setValue(asMsg.svcMachMstrStsDescTxt_A, getSvcMachMstrStsDescTxt(getGlobalCompanyCode(), cMsg.O.no(i).svcMachMstrStsCd_O.getValue()));
            setValue(asMsg.svcMachMstrPk_A1, cMsg.O.no(i).svcMachMstrPk_O);
            if (!hasValue(asMsg.svcMachTpCd_A)) {
                setValue(asMsg.svcMachTpCd_A, SVC_MACH_TP.ACCESSORY);
            }

            if (i == 0) {
                continue;
            }
            sMsg.A.setValidCount(++sMsgIndex);
        }

        // delete empty line
// START 2017/01/25 N.Arai [QC#14614-2, MOD]
//        ZYPTableUtil.deleteRows(sMsg.A, getEmptyConfigLine(sMsg));
        List<Integer> delList = getEmptyConfigLine(sMsg);
        ZYPTableUtil.deleteRows(sMsg.A, delList);

        // set Page
//        int pageFromNum = cMsg.xxPageShowFromNum_A.getValueInt();
        int pageFromNum = ((cMsg.xxPageShowFromNum_A.getValueInt() - delList.size() - 1) / cMsg.A.length()) * cMsg.A.length() + 1;
        if (cMsg.O.getValidCount() > 1) {
//            pageFromNum = sMsg.A.getValidCount();
              pageFromNum = ((sMsg.A.getValidCount() - 1) / cMsg.A.length()) * cMsg.A.length() + 1;
        }
// END 2017/01/25 N.Arai [QC#14614-2, MOD]
        setValue(cMsg.xxPageShowFromNum_A, NSAL0010CommonLogic.getPageShowFromNum(pageFromNum, cMsg.A.length()));
        NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
        setValue(cMsg.xxPageShowOfNum_A, new BigDecimal(sMsg.A.getValidCount()));
        setValue(cMsg.xxPageShowToNum_A, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_A.getValueInt(), cMsg.A.getValidCount()));
    }

    private String getSvcMachMstrStsDescTxt(String glblCmpyCd, String svcMachMstrStsCd) {
        if (!hasValue(svcMachMstrStsCd)) {
            return null;
        }

        SVC_MACH_MSTR_STSTMsg machSts = (SVC_MACH_MSTR_STSTMsg) ZYPCodeDataUtil.findByCode(SVC_MACH_MSTR_STS.class, glblCmpyCd, svcMachMstrStsCd);

        if (machSts == null) {
            return null;
        }
        return machSts.svcMachMstrStsDescTxt.getValue();
    }

 // START 2016/09/21 N.Arai [QC#11616, MOD]
//    private String getMdseNm(String glblCmpyCd, String mdseCd) {
//        if (!hasValue(mdseCd)) {
//            return null;
//        }
//
//        MDSETMsg tmsg = NSAL0010CommonLogic.getMdse(glblCmpyCd, mdseCd);
//
//        if (tmsg == null) {
//            return null;
//        }
//        return tmsg.mdseNm.getValue();
//    }

    /**
     * getMdseDescShortTxt
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return String
     */
    private String getMdseDescShortTxt(String glblCmpyCd, String mdseCd) {
        if (!hasValue(mdseCd)) {
            return null;
        }

        MDSETMsg tmsg = NSAL0010CommonLogic.getMdse(glblCmpyCd, mdseCd);

        if (tmsg == null) {
            return null;
        }
        return tmsg.mdseDescShortTxt.getValue();
    }
 // END 2016/09/21 N.Arai [QC#11616, MOD]

    private List<Integer> getEmptyConfigLine(NSAL0010SMsg sMsg) {
        List<Integer> rtnList = new ArrayList<Integer>();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (isEmptyConfigLine(sMsg.A.no(i))) {
                rtnList.add(i);
            }
        }
        return rtnList;
    }

    private boolean isEmptyConfigLine(NSAL0010_ASMsg asMsg) {
        if (hasValue(asMsg.serNum_A)) {
            return false;
        } else if (hasValue(asMsg.mdseCd_A)) {
            return false;
        } else if (hasValue(asMsg.svcMachMstrStsCd_A)) {
            return false;
        } else if (hasValue(asMsg.svcMachMstrPk_A1)) {
            return false;
        }
        return true;
    }
    // mod end 2016/04/25 CSA Defect#5522

    // START 2016/02/25 T.Tomita [QC#2690, ADD]
    // START 2016/04/19 T.Tomita [QC#6223, MOD]
    private void doProcess_NSAL0010_NMAL6790(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {
        if (!hasValue(cMsg.xxRowNum)) {
            return;
        }

        // add start 2016/04/25 CSA Defect#5522
        int addCount = sMsg.C.getValidCount() + cMsg.Y.getValidCount();
        if (addCount > sMsg.C.length()) {
            cMsg.setMessageInfo(NSAM0320E, new String[]{"Contacts", String.valueOf(sMsg.C.length())});
            return;
        }
        // add end 2016/04/25 CSA Defect#5522

        int selectIndex = cMsg.xxPageShowFromNum_C.getValueInt() - 1 + cMsg.xxRowNum.getValueInt();
        int sMsgIndex = 0;
        NSAL0010_CSMsg csMsg = null;
        for (int i = 0; i < cMsg.Y.getValidCount(); i++) {
            sMsgIndex = sMsg.C.getValidCount();
            csMsg = sMsg.C.no(sMsgIndex);
            if (i == 0) {
                csMsg = sMsg.C.no(selectIndex);
            }

            // set Value
            setValue(csMsg.ctacPsnLastNm_C, cMsg.Y.no(i).ctacPsnLastNm_P2);
            setValue(csMsg.ctacPsnFirstNm_C, cMsg.Y.no(i).ctacPsnFirstNm_P2);
            setValue(csMsg.dsCtacPntPk_C, cMsg.Y.no(i).dsCtacPntPk_P2);
            setValue(csMsg.dsCtacPntTpCd_CS, cMsg.Y.no(i).dsCtacPntTpCd_P2);
            setValue(csMsg.dsCtacPntValTxt_C, cMsg.Y.no(i).dsCtacPntValTxt_P3);
            setValue(csMsg.dsCtacPsnExtnNum_C, cMsg.Y.no(i).dsCtacPsnExtnNum_P2);
            setValue(csMsg.ctacPsnNum_C, cMsg.Y.no(i).ctacPsnNum_P2);
            // START 2016/04/22 T.Tomita [QC#4866, ADD]
            setValue(csMsg.svcCtacTpCd_CS, cMsg.Y.no(i).svcCtacTpCd_P2);
            // END 2016/04/22 T.Tomita [QC#4866, ADD]
            csMsg.ctacPsnPk_C.clear();
            setValue(csMsg.xxChkBox_C, ZYPConstant.FLG_OFF_N);
            setValue(csMsg.effFromDt_C, cMsg.slsDt);
            csMsg.effThruDt_C.clear();

            // get CTAC_PSN_PK
            if (hasValue(csMsg.ctacPsnNum_C)) {
                Map<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
                ssmParam.put("ctacPsnNum", csMsg.ctacPsnNum_C.getValue());

                BigDecimal ctacPsnPk = NSAL0010Query.getInstance().getCtacPsnPk(ssmParam);
                setValue(csMsg.ctacPsnPk_C, ctacPsnPk);
            }

            if (i == 0) {
                continue;
            }
            sMsg.C.setValidCount(++sMsgIndex);
        }

        // delete empty line
        ZYPTableUtil.deleteRows(sMsg.C, getEmptyCtacLine(sMsg));

        // set Page
        int pageFromNum = cMsg.xxPageShowFromNum_C.getValueInt();
        if (cMsg.Y.getValidCount() > 1) {
            pageFromNum = sMsg.C.getValidCount();
        }
        setValue(cMsg.xxPageShowFromNum_C, NSAL0010CommonLogic.getPageShowFromNum(pageFromNum, cMsg.C.length()));
        NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
        setValue(cMsg.xxPageShowOfNum_C, new BigDecimal(sMsg.C.getValidCount()));
        setValue(cMsg.xxPageShowToNum_C, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_C.getValueInt(), cMsg.C.getValidCount()));
    }

    private List<Integer> getEmptyCtacLine(NSAL0010SMsg sMsg) {
        List<Integer> rtnList = new ArrayList<Integer>();
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            if (isEmptyCtacLine(sMsg.C.no(i))) {
                rtnList.add(i);
            }
        }
        return rtnList;
    }

    private boolean isEmptyCtacLine(NSAL0010_CSMsg csMsg) {
        if (hasValue(csMsg.svcMachCtacPsnPk_C)) {
            return false;
        } else if (hasValue(csMsg.dsCtacPntPk_C)) {
            return false;
        } else if (hasValue(csMsg.ctacPsnLastNm_C)) {
            return false;
        } else if (hasValue(csMsg.ctacPsnFirstNm_C)) {
            return false;
        } else if (hasValue(csMsg.dsCtacPntTpCd_CS)) {
            return false;
        } else if (hasValue(csMsg.dsCtacPntValTxt_C)) {
            return false;
        } else if (hasValue(csMsg.dsCtacPsnExtnNum_C)) {
            return false;
        } else if (hasValue(csMsg.svcCtacTpCd_CS)) {
            return false;
        } else if (hasValue(csMsg.ctacPsnPk_C)) {
            return false;
        } else if (hasValue(csMsg.ctacPsnPk_CA)) {
            return false;
        } else if (hasValue(csMsg.ctacPsnNum_C)) {
            return false;
        } else if (hasValue(csMsg.xxChkBox_C)) {
            return false;
        } else if (hasValue(csMsg.effFromDt_C)) {
            return false;
        } else if (hasValue(csMsg.effThruDt_C)) {
            return false;
        }
        return true;
    }

    private void doProcess_NSAL0010Scrn00_OpenWin_Contact(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {
        NSAL0010CommonLogic.copyCMsgToSMsg(cMsg, sMsg);
    }
    // END 2016/04/19 T.Tomita [QC#6223, MOD]
    // END 2016/02/25 T.Tomita [QC#2690, ADD]

    // START 2023/10/06 K.Ishizuka [QC#54186, DEL]
//  // add start 2016/04/20 CSA Defect#4892
//    private void setSvcLineBizPulldown(NSAL0010CMsg cMsg) {
//        cMsg.svcByLineBizTpCd_DC.clear();
//        cMsg.lineBizTpDescTxt_D2.clear();
//
//        List<Map<String, Object>> dataList = NSAL0010Query.getInstance().getSvcLineBiz(cMsg.glblCmpyCd.getValue());
//        int i = 0;
//        for (Map<String, Object> data : dataList) {
//            setValue(cMsg.svcByLineBizTpCd_DC.no(i), (String) data.get("LINE_BIZ_TP_CD"));
//            setValue(cMsg.lineBizTpDescTxt_D2.no(i), (String) data.get("LINE_BIZ_TP_DESC_TXT"));
//            i++;
//        }
//    }
//  // add end 2016/04/20 CSA Defect#4892
    //END 2023/10/06 K.Ishizuka [QC#54186, DEL]
    
    // START 2023/10/06 K.Ishizuka [QC#54186, ADD]
    private void setIstlOrSvcPulldown(NSAL0010CMsg cMsg) {
        cMsg.istlBySvcPrvdPtyCd_DS.clear();
        cMsg.svcPrvdPtyDescTxt_D1.clear();
        cMsg.istlBySvcPrvdPtyCd_DC.clear();
        NSAL0010CommonLogic.setSvcPrvdPtyPulldown(cMsg, true, this.getGlobalCompanyCode());
        cMsg.svcBySvcPrvdPtyCd_DS.clear();
        cMsg.svcPrvdPtyDescTxt_D2.clear();
        cMsg.svcBySvcPrvdPtyCd_DC.clear();
        NSAL0010CommonLogic.setSvcPrvdPtyPulldown(cMsg, false, this.getGlobalCompanyCode());
    }
    //END 2023/10/06 K.Ishizuka [QC#54186, ADD]

    // add start 2016/04/28 CSA Defect#5398
    private void doProcess_NSAL0010_NMAL6800(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {
        NSAL0010CommonLogic.checkHeaderMaster(cMsg, sMsg);
        // START 2018/08/23 K.Kitachi [QC#27773, ADD]
        NSAL0010CommonLogic.setStdWtyFlg(cMsg, sMsg);
        // END 2018/08/23 K.Kitachi [QC#27773, ADD]
    }
    // add end 2016/04/28 CSA Defect#5398
    // Add Start 2017/10/16 QC#21563
    // START 2017/10/27 K.Kitachi [QC#21563, MOD]
    private void setMachStsPulldown(NSAL0010CMsg bizMsg) {
        bizMsg.svcMachMstrStsDescTxt_H2.clear();
        bizMsg.svcMachUsgStsDescTxt_H2.clear();

        if (this.funcIdList.contains(FUNC_ID_CUST_MSTR)) {
            setMachStsPulldownForCustMstr(bizMsg);
            setMachUsgStsPulldownForCustMstr(bizMsg);
        } else {
            ZYPCodeDataUtil.createPulldownList(SVC_MACH_MSTR_STS.class, bizMsg.svcMachMstrStsCd_H1, bizMsg.svcMachMstrStsDescTxt_H2);
            ZYPCodeDataUtil.createPulldownList(SVC_MACH_USG_STS.class, bizMsg.svcMachUsgStsCd_H1, bizMsg.svcMachUsgStsDescTxt_H2);
        }
    }

    private void setMachStsPulldownForCustMstr(NSAL0010CMsg bizMsg) {
        String constVal = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_MACH_STS_CUST_MSTR, bizMsg.glblCmpyCd.getValue());
        if (hasValue(bizMsg.svcMachMstrStsCd_H3)) {
            constVal = constVal + DELIMITER + bizMsg.svcMachMstrStsCd_H3.getValue();
        }
        List<Map<String, Object>> svcMachMstrStsList = NSAL0010Query.getInstance().getMachSts(bizMsg.glblCmpyCd.getValue(), constVal);
        int i = 0;
        for (Map<String, Object> svcMachMstrSts : svcMachMstrStsList) {
            ZYPEZDItemValueSetter.setValue(bizMsg.svcMachMstrStsCd_H1.no(i), (String) svcMachMstrSts.get("SVC_MACH_MSTR_STS_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.svcMachMstrStsDescTxt_H2.no(i), (String) svcMachMstrSts.get("SVC_MACH_MSTR_STS_DESC_TXT"));
            i++;
        }
    }

    private void setMachUsgStsPulldownForCustMstr(NSAL0010CMsg bizMsg) {
        String constVal = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_USG_STS_CUST_MSTR, bizMsg.glblCmpyCd.getValue());
        if (hasValue(bizMsg.svcMachUsgStsCd_H3)) {
            constVal = constVal + DELIMITER + bizMsg.svcMachUsgStsCd_H3.getValue();
        }
        List<Map<String, Object>> svcMachMstrUsgStsList = NSAL0010Query.getInstance().getMachUsgSts(bizMsg.glblCmpyCd.getValue(), constVal);
        int i = 0;
        for (Map<String, Object> svcMachMstrUsgSts : svcMachMstrUsgStsList) {
            ZYPEZDItemValueSetter.setValue(bizMsg.svcMachUsgStsCd_H1.no(i), (String) svcMachMstrUsgSts.get("SVC_MACH_USG_STS_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.svcMachUsgStsDescTxt_H2.no(i), (String) svcMachMstrUsgSts.get("SVC_MACH_USG_STS_DESC_TXT"));
            i++;
        }
    }
    // END 2017/10/27 K.Kitachi [QC#21563, MOD]
    // Add End 2017/10/16 QC#21563

    // START 2018/11/06 S.Kitamura [QC#28868,ADD]
    private void outputInitLog(NSAL0010CMsg cMsg) {
        StringBuffer sb = new StringBuffer();
        sb.append("NSAL0010 Init Condition : ");
        sb.append(" Install Base#[").append(cMsg.svcMachMstrPk_H1.getValue()).append("]");
        S21InfoLogOutput.println(sb.toString());
    }
    private void outputSearchLog(NSAL0010CMsg cMsg) {
        StringBuffer sb = new StringBuffer();
        sb.append("NSAL0010 Search Condition :");
        if (hasValue(cMsg.serNum_H1)) {
            sb.append(" Serial#[").append(cMsg.serNum_H1.getValue()).append("]");
        }
        if (hasValue(cMsg.svcMachMstrPk_H1)) {
            sb.append(" Install Base#[").append(cMsg.svcMachMstrPk_H1.getValue()).append("]");
        }
        S21InfoLogOutput.println(sb.toString());
    }
    // END 2018/11/06 S.Kitamura [QC#28868,ADD]

}
