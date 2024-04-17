/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLAL1100;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDFastTBLAccessor;
import business.blap.NLAL1100.common.NLAL1100CommonLogic;
import business.blap.NLAL1100.constant.NLAL1100Constant;
import business.db.RTRN_TRK_NTFY_GRPTMsg;
import business.file.NLAL1100F00FMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CARR_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_TRK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_LVL;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NLAL1100 Manage RMA Orders
 * Function Name : search business process
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/21/2015   CITS            M.Ito           Create          N/A
 * 05/16/2016   CSAI            Y.Imazu         Update          QC#7972
 * 11/28/2016   CITS            Y.Fujii         Update          R362
 * 03/02/2017   CITS            K.Kameoka       Update          QC#17824
 * 09/28/2017   CITS            R.Shimamoto     Update          QC#18669
 * 01/24/2018   CITS            K.Ogino         Update          QC#23044
 * 02/07/2018   CITS            K.Ogino         Update          QC#24010
 * 05/09/2019   Fujitsu         T.Ogura         Update          QC#50027
 *</pre>
 */
public class NLAL1100BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NLAL1100_INIT".equals(screenAplID)) {
                doProcess_NLAL1100_INIT((NLAL1100CMsg) cMsg, (NLAL1100SMsg) sMsg);
                getColData((NLAL1100CMsg) cMsg, (NLAL1100SMsg) sMsg);

            } else if ("NLAL1100Scrn00_Search".equals(screenAplID)) {
                doProcess_NLAL1100Scrn00_Search((NLAL1100CMsg) cMsg, (NLAL1100SMsg) sMsg);

            } else if ("NLAL1100Scrn00_Search_RtlWHInfo".equals(screenAplID)) {
                doProcess_NLAL1100Scrn00_Search_RtlWhInfo((NLAL1100CMsg) cMsg);

            } else if ("NLAL1100Scrn00_Search_RtlSWHInfo".equals(screenAplID)) {
                doProcess_NLAL1100Scrn00_Search_RtlSWHInfo((NLAL1100CMsg) cMsg);

            } else if ("NLAL1100Scrn00_Search_ShipToCustInfo".equals(screenAplID)) {
                doProcess_NLAL1100Scrn00_Search_ShipToCustInfo((NLAL1100CMsg) cMsg);

            } else if ("NLAL1100Scrn00_Search_CarrInfo".equals(screenAplID)) {
                doProcess_NLAL1100Scrn00_Search_CarrInfo((NLAL1100CMsg) cMsg);

            } else if ("NLAL1100Scrn00_Search_SlsRepInfo".equals(screenAplID)) {
                doProcess_NLAL1100Scrn00_Search_SlsRepInfo((NLAL1100CMsg) cMsg);

            } else if ("NLAL1100Scrn00_Search_CarrLineInfo".equals(screenAplID)) {
                doProcess_NLAL1100Scrn00_Search_CarrLineInfo((NLAL1100CMsg) cMsg);

            } else if ("NLAL1100Scrn00_Search_NtfyGrp".equals(screenAplID)) {
                doProcess_NLAL1100Scrn00_Search_NtfyGrp((NLAL1100CMsg) cMsg);

            } else if ("NLAL1100Scrn00_Select_Search".equals(screenAplID)) {
                doProcess_NLAL1100Scrn00_Select_Search((NLAL1100CMsg) cMsg, (NLAL1100SMsg) sMsg);

            } else if ("NLAL1100Scrn00_ExpandHolds".equals(screenAplID)) {
                doProcess_NLAL1100Scrn00_ExpandHolds((NLAL1100CMsg) cMsg, (NLAL1100SMsg) sMsg);

            } else if ("NLAL1100Scrn00_ContractHolds".equals(screenAplID)) {
                doProcess_NLAL1100Scrn00_ContractHolds((NLAL1100CMsg) cMsg, (NLAL1100SMsg) sMsg);

            } else if ("NLAL1100Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NLAL1100Scrn00_PageNext((NLAL1100CMsg) cMsg, (NLAL1100SMsg) sMsg);

            } else if ("NLAL1100Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NLAL1100Scrn00_PagePrev((NLAL1100CMsg) cMsg, (NLAL1100SMsg) sMsg);

            } else if ("NLAL1100Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NLAL1100Scrn00_PageJump((NLAL1100CMsg) cMsg, (NLAL1100SMsg) sMsg);

            } else if ("NLAL1100Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NLAL1100Scrn00_CMN_Download((NLAL1100CMsg) cMsg, (NLAL1100SMsg) sMsg);

            } else if ("NLAL1100Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NLAL1100Scrn00_CMN_Submit((NLAL1100CMsg) cMsg, (NLAL1100SMsg) sMsg);

            } else if ("NLAL1100Scrn00_Send_Rqst".equals(screenAplID)) {
                doProcess_NLAL1100Scrn00_Send_Rqst((NLAL1100CMsg) cMsg, (NLAL1100SMsg) sMsg);

            } else if ("NLAL1100Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NLAL1100_INIT((NLAL1100CMsg) cMsg, (NLAL1100SMsg) sMsg);

            } else if ("NLAL1100Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NLAL1100Scrn00_CMN_Clear((NLAL1100CMsg) cMsg, (NLAL1100SMsg) sMsg);

            } else if ("NLAL1100Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NLAL1100Scrn00_TBLColumnSort((NLAL1100CMsg) cMsg, (NLAL1100SMsg) sMsg);

            } else if ("NLAL1100Scrn00_Save_Comment".equals(screenAplID)) {
                doProcess_NLAL1100Scrn00_Save_Comment((NLAL1100CMsg) cMsg);

            } else if ("NLAL1100Scrn00_Save_Search".equals(screenAplID)) {
                // no process.

            } else if ("NLAL1100Scrn00_Delete_Search".equals(screenAplID)) {
                // no process.

            } else if ("NLAL1100Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData((NLAL1100CMsg) cMsg, (NLAL1100SMsg) sMsg);
            } else if ("NLAL1100Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData((NLAL1100CMsg) cMsg, (NLAL1100SMsg) sMsg);
            } else if ("NLAL1100Scrn00_Search_CoordInfo".equals(screenAplID)) {
                doProcess_NLAL1100Scrn00_Search_CoordInfo((NLAL1100CMsg) cMsg);

            } else if (NLAL1100Constant.EVENT_NM_NLAL1100_PRINT.equals(screenAplID)) {
                // no process.

            // START QC#18669 Add.
            } else if ("NLAL1100Scrn00_Apply".equals(screenAplID)) {
            	doProcess_NLAL1100Scrn00_Apply((NLAL1100CMsg) cMsg, (NLAL1100SMsg) sMsg);
			
			// END QC#18669 Add.
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * The method explanation: The method explanation: It is a method
     * of the execution when the event[INIT] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLAL1100_INIT(NLAL1100CMsg cMsg, NLAL1100SMsg sMsg) {

        NLAL1100CommonLogic.clearMsg(cMsg, sMsg);

        // Set Common Value
        setCommonVal(cMsg, sMsg);

        // create Pull-Down
        createPulldownList(cMsg, sMsg);

        if (ZYPCommonFunc.hasValue(cMsg.rmaNum_IP) || ZYPCommonFunc.hasValue(cMsg.soNum_IP) || ZYPCommonFunc.hasValue(cMsg.rwsNum_IP) || ZYPCommonFunc.hasValue(cMsg.rtlWhCd_IP) || ZYPCommonFunc.hasValue(cMsg.shipToCustCd_IP)
                || ZYPCommonFunc.hasValue(cMsg.serNum_IP) || ZYPCommonFunc.hasValue(cMsg.carrCd_IP) || ZYPCommonFunc.hasValue(cMsg.svcConfigMstrPk_IP)) {

            ZYPEZDItemValueSetter.setValue(cMsg.rmaNum_H1, cMsg.rmaNum_IP.getValue());
            ZYPEZDItemValueSetter.setValue(cMsg.soNum_H1, cMsg.soNum_IP.getValue());
            ZYPEZDItemValueSetter.setValue(cMsg.rwsNum_H1, cMsg.rwsNum_IP.getValue());
            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCd_H1, cMsg.rtlWhCd_IP.getValue());
            ZYPEZDItemValueSetter.setValue(cMsg.shipToCustCd_H1, cMsg.shipToCustCd_IP.getValue());
            ZYPEZDItemValueSetter.setValue(cMsg.serNum_H1, cMsg.serNum_IP.getValue());
            ZYPEZDItemValueSetter.setValue(cMsg.carrCd_H1, cMsg.carrCd_IP.getValue());
            ZYPEZDItemValueSetter.setValue(cMsg.svcConfigMstrPk_H1, cMsg.svcConfigMstrPk_IP.getValue());

            if (ZYPCommonFunc.hasValue(cMsg.rmaNum_H1) //
                    || ZYPCommonFunc.hasValue(cMsg.soNum_H1) //
                    || ZYPCommonFunc.hasValue(cMsg.rwsNum_H1)) {
                ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_CL, ZYPConstant.FLG_ON_Y);
            }
            doProcess_NLAL1100Scrn00_Search(cMsg, sMsg);
        }
    }

    /**
     * doProcess_NLAL1100Scrn00_CMN_Clear
     * @param cMsg NLAL1100CMsg
     * @param sMsg NLAL1100SMsg
     */
    private void doProcess_NLAL1100Scrn00_CMN_Clear(NLAL1100CMsg cMsg, NLAL1100SMsg sMsg) {

        String col = cMsg.xxComnColOrdTxt.getValue();
        NLAL1100CommonLogic.clearMsg(cMsg, sMsg);

        // Set Common Value
        setCommonVal(cMsg, sMsg);

        // create Pull-Down
        createPulldownList(cMsg, sMsg);

        ZYPEZDItemValueSetter.setValue(cMsg.xxComnColOrdTxt, col);
    }

    /**
     * setCommonVal
     * @param cMsg NLAL1100CMsg
     * @param sMsg NLAL1100SMsg
     */
    private void setCommonVal(NLAL1100CMsg cMsg, NLAL1100SMsg sMsg) {

        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd_G1, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(cMsg.srchOptUsrId_U1, getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(cMsg.slsDt_G1, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem130Txt_U1, getContextUserInfo().getUserId() + "(" + getContextUserInfo().getFirstName() + " " + getContextUserInfo().getLastName() + ")");
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[Search] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLAL1100Scrn00_Search(NLAL1100CMsg cMsg, NLAL1100SMsg sMsg) {

//        QC:10504
        if (!checkInputForSearch(cMsg)) {

            NLAL1100CommonLogic.search(cMsg, sMsg);
        }

        NLAL1100CommonLogic.search(cMsg, sMsg);

        cMsg.xxWrnSkipFlg_G1.clear();
    }

    /**
     * checkInputForSearch
     * @param cMsg NLAL1100CMsg
     */
    private boolean checkInputForSearch(NLAL1100CMsg cMsg) {

        boolean hasErr = false;

        cMsg.rtlWhNm_H1.clear();
        cMsg.rtlSwhNm_H1.clear();
        cMsg.dsAcctNm_H1.clear();
        cMsg.carrNm_H1.clear();
        cMsg.tocNm_H1.clear();

        // Retail Warehouse Code
        if (ZYPCommonFunc.hasValue(cMsg.rtlWhCd_H1)) {

            doProcess_NLAL1100Scrn00_Search_RtlWhInfo(cMsg);

            if (cMsg.rtlWhCd_H1.isError()) {

                hasErr = true;
            }
        }

        // Sub Warehouse Code
        if (ZYPCommonFunc.hasValue(cMsg.rtlSwhCd_H1)) {

            doProcess_NLAL1100Scrn00_Search_RtlSWHInfo(cMsg);

            if (cMsg.rtlSwhCd_H1.isError()) {

                hasErr = true;
            }
        }

        // Ship To Customer Code
        if (ZYPCommonFunc.hasValue(cMsg.shipToCustCd_H1)) {

            doProcess_NLAL1100Scrn00_Search_ShipToCustInfo(cMsg);

            if (cMsg.shipToCustCd_H1.isError()) {

                hasErr = true;
            }
        }

        // Carrier Code
        if (ZYPCommonFunc.hasValue(cMsg.carrCd_H1)) {

            doProcess_NLAL1100Scrn00_Search_CarrInfo(cMsg);

            if (cMsg.carrCd_H1.isError()) {

                hasErr = true;
            }
        }

        // Sales Rep
        if (ZYPCommonFunc.hasValue(cMsg.slsRepBrCd_H1)) {

            doProcess_NLAL1100Scrn00_Search_SlsRepInfo(cMsg);

            if (cMsg.slsRepBrCd_H1.isError()) {

                hasErr = true;
            }
        }

        return hasErr;
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[Search_RtlWhInfo] is generated.
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NLAL1100Scrn00_Search_RtlWhInfo(NLAL1100CMsg cMsg) {

        cMsg.rtlWhNm_H1.clear();

        if (ZYPCommonFunc.hasValue(cMsg.rtlWhCd_H1)) {

            S21SsmEZDResult result = NLAL1100Query.getInstance().getRtlWhNm(cMsg);

            if (result.isCodeNormal()) {

                List rtlWhNmList = (List) result.getResultObject();

                if (rtlWhNmList.size() > 1) {
                    ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_H1, NLAL1100Constant.MULTIPLE);
                } else {
                    ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_H1, (String) rtlWhNmList.get(0));
                }

            } else {
                cMsg.rtlWhCd_H1.setErrorInfo(1, NLAL1100Constant.NLZM2278E, new String[] {"Warehouse" });
                return;
            }

        } else {

            cMsg.rtlWhCd_H1.setErrorInfo(1, NLAL1100Constant.ZZM9000E, new String[] {"Warehouse" });
        }
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[Search_RtlSWHInfo] is generated.
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NLAL1100Scrn00_Search_RtlSWHInfo(NLAL1100CMsg cMsg) {

        cMsg.rtlSwhNm_H1.clear();
        // QC:10497

        if (ZYPCommonFunc.hasValue(cMsg.rtlSwhCd_H1)) {

            S21SsmEZDResult result = NLAL1100Query.getInstance().getRtlSwhNm(cMsg);

            if (result.isCodeNormal()) {

                List rtlSwhNmList = (List) result.getResultObject();

                if (rtlSwhNmList.size() > 1) {
                    ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm_H1, NLAL1100Constant.MULTIPLE);
                } else {
                    ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm_H1, (String) rtlSwhNmList.get(0));
                }

                if (ZYPCommonFunc.hasValue(cMsg.rtlWhCd_H1)) {
                    doProcess_NLAL1100Scrn00_Search_RtlWhInfo(cMsg);
                }
            } else {
                if (ZYPCommonFunc.hasValue(cMsg.rtlWhCd_H1)) {
                    cMsg.rtlSwhCd_H1.setErrorInfo(1, NLAL1100Constant.NLZM2278E, new String[] {"Warehouse/Sub Warehouse" });
                } else {
                    cMsg.rtlSwhCd_H1.setErrorInfo(1, NLAL1100Constant.NLZM2278E, new String[] {"Sub Warehouse" });
                }
            }
        } else {
            cMsg.rtlSwhCd_H1.setErrorInfo(1, NLAL1100Constant.ZZM9000E, new String[] {"Sub Warehouse" });
        }

    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[Search_ShipToCustInfo] is generated.
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NLAL1100Scrn00_Search_ShipToCustInfo(NLAL1100CMsg cMsg) {

        cMsg.dsAcctNm_H1.clear();

        if (ZYPCommonFunc.hasValue(cMsg.shipToCustCd_H1)) {

            S21SsmEZDResult result = NLAL1100Query.getInstance().getAcctNm(cMsg);

            if (result.isCodeNormal()) {

                List dsAcctNmList = (List) result.getResultObject();

                if (dsAcctNmList.size() > 1) {
                    ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_H1, NLAL1100Constant.MULTIPLE);
                } else {
                    ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_H1, (String) dsAcctNmList.get(0));
                }

            } else {

                cMsg.shipToCustCd_H1.setErrorInfo(1, NLAL1100Constant.NLZM2278E, new String[] {"Customer Pickup" });
            }

        } else {

            cMsg.shipToCustCd_H1.setErrorInfo(1, NLAL1100Constant.ZZM9000E, new String[] {"Customer Pickup" });
        }
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[Search_CarrInfo] is generated.
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NLAL1100Scrn00_Search_CarrInfo(NLAL1100CMsg cMsg) {

        cMsg.carrNm_H1.clear();

        if (ZYPCommonFunc.hasValue(cMsg.carrCd_H1)) {

            S21SsmEZDResult result = NLAL1100Query.getInstance().getCarrNm(cMsg.glblCmpyCd_G1.getValue(), cMsg.carrCd_H1.getValue());

            if (result.isCodeNormal()) {

                List carrNmList = (List) result.getResultObject();

                if (carrNmList.size() > 1) {
                    ZYPEZDItemValueSetter.setValue(cMsg.carrNm_H1, NLAL1100Constant.MULTIPLE);
                } else {
                    ZYPEZDItemValueSetter.setValue(cMsg.carrNm_H1, (String) carrNmList.get(0));
                }

            } else {

                cMsg.carrCd_H1.setErrorInfo(1, NLAL1100Constant.NLZM2278E, new String[] {"Pickup Carrier" });
            }

        } else {

            cMsg.carrCd_H1.setErrorInfo(1, NLAL1100Constant.ZZM9000E, new String[] {"Pickup Carrier" });
        }
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[Search_SlsRepInfo] is generated.
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NLAL1100Scrn00_Search_SlsRepInfo(NLAL1100CMsg cMsg) {

        cMsg.tocNm_H1.clear();

        if (ZYPCommonFunc.hasValue(cMsg.slsRepBrCd_H1)) {

            S21SsmEZDResult result = NLAL1100Query.getInstance().getSlsRepName(cMsg);

            if (result.isCodeNormal()) {

                List tocNmList = (List) result.getResultObject();

                if (tocNmList.size() > 1) {
                    ZYPEZDItemValueSetter.setValue(cMsg.tocNm_H1, NLAL1100Constant.MULTIPLE);
                } else {
                    ZYPEZDItemValueSetter.setValue(cMsg.tocNm_H1, (String) tocNmList.get(0));
                }

            } else {
                cMsg.slsRepBrCd_H1.setErrorInfo(1, NLAL1100Constant.NLZM2278E, new String[] {"Sales Rep" });
            }

        } else {

            cMsg.slsRepBrCd_H1.setErrorInfo(1, NLAL1100Constant.ZZM9000E, new String[] {"Sales Rep" });
        }
    }

    /**
     * doProcess_NLAL1100Scrn00_Search_CarrLineInfo
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NLAL1100Scrn00_Search_CarrLineInfo(NLAL1100CMsg cMsg) {

        int index = cMsg.xxNum_EV.getValueInt();

        cMsg.A.no(index).locNm_A1.clear();

        if (ZYPCommonFunc.hasValue(cMsg.A.no(index).carrCd_A1)) {

            // Master Check
            S21SsmEZDResult result = NLAL1100Query.getInstance().getCarrNm(cMsg.glblCmpyCd_G1.getValue(), cMsg.A.no(index).carrCd_A1.getValue());

            if (result.isCodeNormal()) {

                List carrNmList = (List)result.getResultObject();
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(index).locNm_A1, (String) carrNmList.get(0));

            } else {

                cMsg.A.no(index).carrCd_A1.setErrorInfo(1, NLAL1100Constant.NLZM2278E, new String[] {"Carrier"});
                return;
            }

            // Carrier Service Level Check
            if (ZYPCommonFunc.hasValue(cMsg.A.no(index).shpgSvcLvlCd_A1)) {

                result = NLAL1100Query.getInstance().getCarrSvcLvlCnt(cMsg.glblCmpyCd_G1.getValue(), cMsg.A.no(index).carrCd_A1.getValue(), cMsg.A.no(index).shpgSvcLvlCd_A1.getValue());

                if (result.isCodeNormal()) {

                    BigDecimal carrSvcLvlCnt = (BigDecimal) result.getResultObject();

                    if (carrSvcLvlCnt == null || carrSvcLvlCnt.intValue() == 0) {

                        cMsg.A.no(index).carrCd_A1.setErrorInfo(1, NLAL1100Constant.NLBM1308E, new String[] {"Pickup Service Level", "Carrier"});
                        cMsg.A.no(index).shpgSvcLvlCd_A1.setErrorInfo(1, NLAL1100Constant.NLBM1308E, new String[] {"Pickup Service Level", "Carrier"});
                        return;
                    }

                } else {

                    cMsg.A.no(index).carrCd_A1.setErrorInfo(1, NLAL1100Constant.NLBM1308E, new String[] {"Pickup Service Level", "Carrier"});
                    cMsg.A.no(index).shpgSvcLvlCd_A1.setErrorInfo(1, NLAL1100Constant.NLBM1308E, new String[] {"Pickup Service Level", "Carrier"});
                    return;
                }
            }

        } else {

            cMsg.A.no(index).carrCd_A1.setErrorInfo(1, NLAL1100Constant.ZZM9000E, new String[]{"Carrier Code"});
            return;
        }
    }

    /**
     * doProcess_NLAL1100Scrn00_Search_NtfyGrp
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NLAL1100Scrn00_Search_NtfyGrp(NLAL1100CMsg cMsg) {

        int index = cMsg.xxNum_EV.getValueInt();

        cMsg.A.no(index).rtrnTrkNtfyGrpDescTxt_A1.clear();

        if (ZYPCommonFunc.hasValue(cMsg.A.no(index).rtrnTrkNtfyGrpCd_A1)) {

            RTRN_TRK_NTFY_GRPTMsg rtrnTrkNtfyGrpTMsg = new RTRN_TRK_NTFY_GRPTMsg();
            ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyGrpTMsg.glblCmpyCd, cMsg.glblCmpyCd_G1.getValue());
            ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyGrpTMsg.rtrnTrkNtfyGrpCd, cMsg.A.no(index).rtrnTrkNtfyGrpCd_A1.getValue());
            rtrnTrkNtfyGrpTMsg = (RTRN_TRK_NTFY_GRPTMsg) EZDFastTBLAccessor.findByKey(rtrnTrkNtfyGrpTMsg);

            if (rtrnTrkNtfyGrpTMsg == null) {

                cMsg.A.no(index).rtrnTrkNtfyGrpCd_A1.setErrorInfo(1, NLAL1100Constant.NLZM2278E, new String[]{"Assigned to Group"});
                return;
            }

            ZYPEZDItemValueSetter.setValue(cMsg.A.no(index).rtrnTrkNtfyGrpDescTxt_A1, rtrnTrkNtfyGrpTMsg.rtrnTrkNtfyGrpDescTxt.getValue());

        } else {

            cMsg.A.no(index).rtrnTrkNtfyGrpCd_A1.setErrorInfo(1, NLAL1100Constant.ZZM9000E, new String[]{"Assigned to Group Code"});
        }
    }

    /**
     * PullDown onChange Event for Select Search
     * @param cMsg NLAL1100CMsg
     * @param sMsg NLAL1100SMsg
     */
    private void doProcess_NLAL1100Scrn00_Select_Search(NLAL1100CMsg cMsg, NLAL1100SMsg sMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_S1)) {

            NLAL1100CommonLogic.callNszc0330forSearchSearchOption(cMsg);
        }
    }

    /**
     * doProcess_NLAL1100Scrn00_ContractHolds : [-] --> [+]
     * @param cMsg NLAL1100CMsg
     * @param sMsg NLAL1100SMsg
     */
    private void doProcess_NLAL1100Scrn00_ContractHolds(NLAL1100CMsg cMsg, NLAL1100SMsg sMsg) {

        NLAL1100CommonLogic.saveCurrentPageToSMsg(cMsg, sMsg);

        int eventLine = cMsg.xxNum_EV.getValueInt();
        int sMsgIdxA = cMsg.A.no(eventLine).xxNum_A1.getValueInt();
        String preRwsNum = cMsg.A.no(eventLine).rwsNum_A1.getValue();

        for (int i = sMsgIdxA; i < sMsg.A.getValidCount(); i++) {

            if (sMsgIdxA == i) {

                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxSmryLineFlg_A1, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxDplyCtrlFlg_A1, ZYPConstant.FLG_ON_Y);

            } else if (preRwsNum.equals(sMsg.A.no(i).rwsNum_A1.getValue())) {

                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxSmryLineFlg_A1, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxDplyCtrlFlg_A1, ZYPConstant.FLG_OFF_N);

            } else {

                break;
            }
        }

        ZYPTableUtil.clear(cMsg.A);
        NLAL1100CommonLogic.pagenation(cMsg, sMsg, cMsg.A.length() * (cMsg.xxPageShowCurNum.getValueInt() - 1));
    }

    /**
     * doProcess_NLAL1100Scrn00_ExpandHolds : [+] --> [-]
     * @param cMsg NLAL1100CMsg
     * @param sMsg NLAL1100SMsg
     */
    private void doProcess_NLAL1100Scrn00_ExpandHolds(NLAL1100CMsg cMsg, NLAL1100SMsg sMsg) {

        NLAL1100CommonLogic.saveCurrentPageToSMsg(cMsg, sMsg);

        int eventLine = cMsg.xxNum_EV.getValueInt();
        int sMsgIdxA = cMsg.A.no(eventLine).xxNum_A1.getValueInt();
        String preRwsNum = cMsg.A.no(eventLine).rwsNum_A1.getValue();

        for (int i = sMsgIdxA; i < sMsg.A.getValidCount(); i++) {

            if (sMsgIdxA == i) {

                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxSmryLineFlg_A1, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxDplyCtrlFlg_A1, ZYPConstant.FLG_ON_Y);

            } else if (preRwsNum.equals(sMsg.A.no(i).rwsNum_A1.getValue())) {

                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxSmryLineFlg_A1, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxDplyCtrlFlg_A1, ZYPConstant.FLG_ON_Y);

            } else {

                break;
            }
        }

        ZYPTableUtil.clear(cMsg.A);
        NLAL1100CommonLogic.pagenation(cMsg, sMsg, cMsg.A.length() * (cMsg.xxPageShowCurNum.getValueInt() - 1));
    }

    /**
     * doProcess_NLAL1100Scrn00_Save_Comment
     * @param cMsg NLAL1100CMsg
     */
    private void doProcess_NLAL1100Scrn00_Save_Comment(NLAL1100CMsg cMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.cpoOrdNum_C1)) {

            NLAL1100CommonLogic.searchComment(cMsg);
            cMsg.xxWrnSkipFlg_G1.clear();
        }
    }

    /**
     * doProcess_NLAL1100Scrn00_PageNext
     * @param cMsg NLAL1100CMsg
     * @param sMsg NLAL1100SMsg
     */
    private void doProcess_NLAL1100Scrn00_PageNext(NLAL1100CMsg cMsg, NLAL1100SMsg sMsg) {

        NLAL1100CommonLogic.saveCurrentPageToSMsg(cMsg, sMsg);
        ZYPTableUtil.clear(cMsg.A);
        NLAL1100CommonLogic.pagenation(cMsg, sMsg, cMsg.xxPageShowToNum.getValueInt());
    }

    /**
     * doProcess_NLAL1100Scrn00_PagePrev
     * @param cMsg NLAL1100CMsg
     * @param sMsg NLAL1100SMsg
     */
    private void doProcess_NLAL1100Scrn00_PagePrev(NLAL1100CMsg cMsg, NLAL1100SMsg sMsg) {

        NLAL1100CommonLogic.saveCurrentPageToSMsg(cMsg, sMsg);
        ZYPTableUtil.clear(cMsg.A);
        NLAL1100CommonLogic.pagenation(cMsg, sMsg, cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1);
    }

    /**
     * doProcess_NLAL1100Scrn00_PageJump
     * @param cMsg NLAL1100CMsg
     * @param sMsg NLAL1100SMsg
     */
    private void doProcess_NLAL1100Scrn00_PageJump(NLAL1100CMsg cMsg, NLAL1100SMsg sMsg) {

        NLAL1100CommonLogic.saveCurrentPageToSMsg(cMsg, sMsg);
        ZYPTableUtil.clear(cMsg.A);
        NLAL1100CommonLogic.pagenation(cMsg, sMsg, cMsg.A.length() * (cMsg.xxPageShowCurNum.getValueInt() - 1));
    }

    /**
     * doProcess_NLAL1100Scrn00_CMN_Submit
     * @param cMsg NLAL1100CMsg
     * @param sMsg NLAL1100SMsg
     */
    private void doProcess_NLAL1100Scrn00_CMN_Submit(NLAL1100CMsg cMsg, NLAL1100SMsg sMsg) {

        if (!"E".equals(cMsg.getMessageKind()) && !"W".equals(cMsg.getMessageKind()) && !ZYPConstant.FLG_ON_Y.equals(cMsg.xxWrnSkipFlg_G1.getValue())) {

            EZDMessageInfo msgInfo =  cMsg.getMessageInfo();
            doProcess_NLAL1100Scrn00_Search(cMsg, sMsg);

            if (msgInfo != null) {

                cMsg.setMessageInfo(msgInfo.getCode(), msgInfo.getParameter());
            }
        }
    }

    /**
     * doProcess_NLAL1100Scrn00_Send_Rqst
     * @param cMsg NLAL1100CMsg
     * @param sMsg NLAL1100SMsg
     */
    private void doProcess_NLAL1100Scrn00_Send_Rqst(NLAL1100CMsg cMsg, NLAL1100SMsg sMsg) {

        if (!"E".equals(cMsg.getMessageKind()) && !"W".equals(cMsg.getMessageKind()) && !ZYPConstant.FLG_ON_Y.equals(cMsg.xxWrnSkipFlg_G1.getValue())) {

            EZDMessageInfo msgInfo =  cMsg.getMessageInfo();
            doProcess_NLAL1100Scrn00_Search(cMsg, sMsg);

            if (msgInfo != null) {

                cMsg.setMessageInfo(msgInfo.getCode(), msgInfo.getParameter());
            }
        }
    }

    /**
     * doProcess_NLAL1100Scrn00_TBLColumnSort
     * @param cMsg NLAL1100CMsg
     * @param sMsg NLAL1100SMsg
     */
    private void doProcess_NLAL1100Scrn00_TBLColumnSort(NLAL1100CMsg cMsg, NLAL1100SMsg sMsg) {

        NLAL1100CommonLogic.saveCurrentPageToSMsg(cMsg, sMsg);

        String sortTblNm = cMsg.xxSortTblNm_A.getValue();
        String sortItemNm = cMsg.xxSortItemNm_A.getValue();
        String sortOrdBy  = cMsg.xxSortOrdByTxt_A.getValue();

        if ("A".equals(sortTblNm)) {

            // execute column sort function
            NLAL1100CommonLogic.sort(sMsg.A, sortItemNm, sortOrdBy, sMsg.A.no(0).getBaseContents(), false);

            for (int i = 0; i < sMsg.A.getValidCount(); i++) {

                NLAL1100_ASMsg sMsgALine = sMsg.A.no(i);
                ZYPEZDItemValueSetter.setValue(sMsgALine.xxNum_A1, new BigDecimal(i));
            }

            NLAL1100CommonLogic.pagenation(cMsg, sMsg, 0);
        }
    }

    /**
     * doProcess_NLAL1100Scrn00_CMN_Download
     * @param cMsg NLAL1100CMsg
     * @param sMsg NLAL1100SMsg
     */
    private void doProcess_NLAL1100Scrn00_CMN_Download(NLAL1100CMsg cMsg, NLAL1100SMsg sMsg) {

        ResultSet rs = null;
        PreparedStatement ps = null;

        try {

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(NLAL1100Constant.MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NLAL1100Query.getInstance().getClass());

            //create csv file, parameters
            cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NLAL1100Constant.CSV_FILE_NAME), ".csv");
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("cMsg", cMsg);
            ssmParam.put("slsDt", cMsg.slsDt_G1.getValue());
            ssmParam.put("flgOn", ZYPConstant.FLG_ON_Y);
            ssmParam.put("flgOff", ZYPConstant.FLG_OFF_N);
            ssmParam.put("subSysId", "NWZ");
            ssmParam.put("procId", "OM");
            ssmParam.put("docTpCc", "RT");
            ssmParam.put("eventId", "RMA Closed");
            ssmParam.put("rownum", sMsg.A.length());

            ps = ssmLLClient.createPreparedStatement("search", ssmParam, execParam);
            rs = ps.executeQuery();
            writeCsvFile(cMsg, rs);

        } catch (SQLException e) {

            throw new S21AbendException(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    /**
     * writeCsvFile
     * @param cMsg NLAL1100CMsg
     * @param rs ResultSet
     */
    private void writeCsvFile(NLAL1100CMsg cMsg, ResultSet rs) throws SQLException {

        NLAL1100F00FMsg fMsg = new NLAL1100F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        fMsg.addExclusionItem("xxSmryLineFlg");
        fMsg.addExclusionItem("xxChkBox");
        // QC#17824 Start
        fMsg.addExclusionItem("carrAcctNum");

        // Preferred View
        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(cMsg));
        // QC#17824 End

        // write header
        csvOutFile.writeHeader(NLAL1100Constant.CSV_HDR, fMsg, ZYPGUITableColumn.getColOrder(cMsg));

        if (!rs.next()) {

            cMsg.setMessageInfo(NLAL1100Constant.NZZM0000E, null);
            csvOutFile.close();
            return;
        }

        //write contents
        do {

            if (rs.getRow() >= NLAL1100Constant.LIMIT_DL_ROWNUM) {

                cMsg.setMessageInfo(NLAL1100Constant.NZZM0001W, null);
                break;
            }

            Map<String, String> outOfWhChkMap = new HashMap<String, String>();
            outOfWhChkMap.put("DS_ORD_CATG_CD", rs.getString("DS_ORD_CATG_CD"));
            outOfWhChkMap.put("DS_ORD_TP_CD", rs.getString("DS_ORD_TP_CD"));
            outOfWhChkMap.put("RTRN_RSN_CD", rs.getString("RTRN_RSN_CD"));
            outOfWhChkMap.put("MDSE_CD", rs.getString("MDSE_CD"));
            outOfWhChkMap.put("SHIP_TO_POST_CD", rs.getString("SHIP_TO_POST_CD"));
            outOfWhChkMap.put("RCV_RTL_WH_CD", rs.getString("RTL_WH_CD"));

            //resultSet -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.svcConfigMstrPk, rs.getBigDecimal("SVC_CONFIG_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(fMsg.cpoOrdNum, rs.getString("CPO_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.dplyLineNum, rs.getString("DPLY_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.rwsNum, rs.getString("RWS_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.rwsDtlLineNum, rs.getString("RWS_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhNm, rs.getString("RTL_WH_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlSwhCd, rs.getString("RTL_SWH_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm, rs.getString("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.fromLocStCd, rs.getString("FROM_LOC_ST_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.fromLocPsnNm, rs.getString("FROM_LOC_PSN_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.mdseCd, rs.getString("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt, rs.getString("MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.rwsQty, rs.getBigDecimal("RWS_QTY"));
            ZYPEZDItemValueSetter.setValue(fMsg.serNum, rs.getString("SER_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.ordBookTsDplyTxt, rs.getString("ORD_BOOK_TS_DPLY_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.ordAgingBcktDescTxt, rs.getString("ORD_AGING_BCKT_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtrnTrkNtfyGrpDescTxt, rs.getString("RTRN_TRK_NTFY_GRP_DESC_TXT"));
            // START 2019/05/09 T.Ogura [QC#50027,MOD]
//            ZYPEZDItemValueSetter.setValue(fMsg.schdPickUpDt, rs.getString("SCHD_PICK_UP_DT"));
            String schdPickUpTsDplyTxt = convSchdDelyTs(rs.getString("SCHD_PICK_UP_DT"),
                    rs.getString("SCHD_PICK_UP_TM"),
                    rs.getString("SHIP_TO_CTRY_CD"),
                    rs.getString("SHIP_TO_POST_CD"));
            if (ZYPCommonFunc.hasValue(schdPickUpTsDplyTxt)) {
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTm_SP, schdPickUpTsDplyTxt);
            } else {
                fMsg.xxDtTm_SP.clear();
            }
            ZYPEZDItemValueSetter.setValue(fMsg.techMeetTruckFlg, rs.getString("TECH_MEET_TRUCK_FLG"));
            // END   2019/05/09 T.Ogura [QC#50027,MOD]
            ZYPEZDItemValueSetter.setValue(fMsg.carrRsnDescTxt, rs.getString("CARR_RSN_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.actlPickUpDt, rs.getString("ACTL_PICK_UP_DT"));
            ZYPEZDItemValueSetter.setValue(fMsg.inspCpltDt, rs.getString("INSP_CPLT_DT"));
            ZYPEZDItemValueSetter.setValue(fMsg.outOfWhInvtyProcFlg, NLAL1100CommonLogic.getOutOfWhInvtyProc(cMsg, outOfWhChkMap));
            ZYPEZDItemValueSetter.setValue(fMsg.xxTsDsp19Txt_OC, rs.getString("ORD_CLO_DT_TM_TS_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.carrNm, rs.getString("CARR_NM"));
            // QC#23044
            ZYPEZDItemValueSetter.setValue(fMsg.sendRqstFlg, rs.getString("SEND_REQ_FLG"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsOrdCatgDescTxt, rs.getString("DS_ORD_CATG_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsOrdTpDescTxt, rs.getString("DS_ORD_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsOrdLineCatgDescTxt, rs.getString("DS_ORD_LINE_CATG_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtrnLineStsDescTxt, rs.getString("RTRN_LINE_DPLY_STS_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtrnTrkStsDescTxt, rs.getString("RTRN_TRK_STS_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.fsrNum, rs.getString("FSR_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.svcMachRmvDt, rs.getString("PROD_RMV_DT"));
            ZYPEZDItemValueSetter.setValue(fMsg.relMemoTxt, rs.getString("REL_MEMO_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.rwsStsDescTxt, rs.getString("RWS_DTL_STS_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxTsDsp19Txt_RC, rs.getString("RWS_CLO_DT_TM_TS_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.rqstPickUpDt, rs.getString("RQST_PICK_UP_DT"));
            ZYPEZDItemValueSetter.setValue(fMsg.shpgSvcLvlDescTxt, rs.getString("SHPG_SVC_LVL_DESC_TXT"));
            //QC#17824 start
            //ZYPEZDItemValueSetter.setValue(fMsg.carrAcctNum, rs.getString("CARR_ACCT_NUM"));
            //QC#17824 end
            ZYPEZDItemValueSetter.setValue(fMsg.schdCoordPsnNm, rs.getString("SCHD_COORD_PSN_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.schdCoordStsDescTxt, rs.getString("SCHD_COORD_STS_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.t_MdlNm, rs.getString("T_MDL_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.shipLocCd, rs.getString("FROM_LOC_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.fromLocCtyAddr, rs.getString("FROM_LOC_CTY_ADDR"));

            csvOutFile.write();

        } while (rs.next());

        csvOutFile.close();
    }

    // START 2019/05/09 T.Ogura [QC#50027,ADD]
    /**
     * convSchdDelyTs
     * @param schdDelyDt
     * @param schdDelyTm
     * @param ctryCd
     * @param postCd
     * @return String
     */
    private String convSchdDelyTs(String schdDelyDt, String schdDelyTm, String ctryCd, String postCd) {
        String schdDelyTsDplyTxt = null;

        if (ZYPCommonFunc.hasValue(schdDelyDt)) {
            if (ZYPCommonFunc.hasValue(schdDelyTm)) {
                String systemTimeZoneTs = schdDelyDt + schdDelyTm;

                if (ZYPCommonFunc.hasValue(ctryCd) && ZYPCommonFunc.hasValue(postCd)) {
                    schdDelyTsDplyTxt = convertTime(systemTimeZoneTs, ctryCd, postCd);
                } else {
                    schdDelyTsDplyTxt = convFormatTimeInclTime(systemTimeZoneTs);
                }
            } else {
                schdDelyTsDplyTxt = convFormatTime(schdDelyDt);
            }
        }

        return schdDelyTsDplyTxt;
    }
    // END   2019/05/09 T.Ogura [QC#50027,ADD]

    // START 2019/05/09 T.Ogura [QC#50027,ADD]
    /**
     * convertTime
     * @param ts
     * @param ctryCd
     * @param postCd
     * @return String
     */
    private String convertTime(String ts, String ctryCd, String postCd) {
        SvcTimeZoneInfo timeInfo = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, ts, ctryCd, postCd);
        return convFormatTimeInclTime(timeInfo.getDateTime().substring(0, 12));
    }
    // END   2019/05/09 T.Ogura [QC#50027,ADD]

    // START 2019/05/09 T.Ogura [QC#50027,ADD]
    /**
     * convFormatTimeInclTime
     * @param ts
     * @return String
     */
    private String convFormatTimeInclTime(String ts) {
        SimpleDateFormat sfdDate = new SimpleDateFormat("yyyyMMddHHmm");
        SimpleDateFormat sfdString = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        Date formatDate = null;

        try {
            formatDate = sfdDate.parse(ts);
            return sfdString.format(formatDate);
        } catch (ParseException e) {
            return null;
        }
    }
    // END   2019/05/09 T.Ogura [QC#50027,ADD]

    // START 2019/05/09 T.Ogura [QC#50027,ADD]
    /**
     * convFormatTimeInclTime
     * @param ts
     * @return String
     */
    private String convFormatTime(String ts) {
        SimpleDateFormat sfdDate = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sfdString = new SimpleDateFormat("MM/dd/yyyy");
        Date formatDate = null;

        try {
            formatDate = sfdDate.parse(ts);
            return sfdString.format(formatDate);
        } catch (ParseException e) {
            return null;
        }
    }
    // END   2019/05/09 T.Ogura [QC#50027,ADD]

    /**
     * The method explanation: create Pulldown List.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void createPulldownList(NLAL1100CMsg cMsg, NLAL1100SMsg sMsg) {

        // Create Pulldown List
        ZYPCodeDataUtil.createPulldownList(CARR_RSN.class, cMsg.carrRsnCd_L1, cMsg.carrRsnDescTxt_L1);
        ZYPCodeDataUtil.createPulldownList(RTRN_TRK_STS.class, cMsg.rtrnTrkStsCd_L1, cMsg.rtrnTrkStsDescTxt_L1);
        ZYPCodeDataUtil.createPulldownList(SHPG_SVC_LVL.class, cMsg.shpgSvcLvlCd_L1, cMsg.shpgSvcLvlDescTxt_L1);

        // Create Save Search Option List
        NLAL1100CommonLogic.createPullDownSearchOption(cMsg);

        // Create AmPm List
        NLAL1100CommonLogic.setPulldownTimeAmPm(cMsg);    // 2019/05/09 T.Ogura [QC#50027,ADD]

        // Aging Bucket List
        S21SsmEZDResult ordAgingBcktPulldownList = NLAL1100Query.getInstance().getOrdAgingBcktPulldownList(cMsg);

        if (ordAgingBcktPulldownList.isCodeNormal()) {

            List<Map<String, Object>> ordAgingBcktList = (List<Map<String, Object>>) ordAgingBcktPulldownList.getResultObject();

            for (int i = 0; i < ordAgingBcktList.size(); i++) {

                Map<String, Object> pullDownData = ordAgingBcktList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.ordAgingBcktCd_L1.no(i), (String) pullDownData.get("ORD_AGING_BCKT_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.ordAgingBcktDescTxt_L1.no(i), (String) pullDownData.get("ORD_AGING_BCKT_DESC_TXT"));

                if (i >= cMsg.ordAgingBcktCd_L1.length()) {

                    break;
                }
            }
        }

        // Return Line Display Status  List
        S21SsmEZDResult rtrnLineDplyStsPulldownList = NLAL1100Query.getInstance().getRtrnLineDplyStsPulldownList(cMsg);

        if (rtrnLineDplyStsPulldownList.isCodeNormal()) {

            List<Map<String, Object>> rtrnLineDplyStsList = (List<Map<String, Object>>) rtrnLineDplyStsPulldownList.getResultObject();

            for (int i = 0; i < rtrnLineDplyStsList.size(); i++) {

                Map<String, Object> pullDownData = rtrnLineDplyStsList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.rtrnLineDplyStsCd_L1.no(i), (String) pullDownData.get("RTRN_LINE_DPLY_STS_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.rtrnLineDplyStsDescTxt_L1.no(i), (String) pullDownData.get("RTRN_LINE_DPLY_STS_DESC_TXT"));

                if (i >= cMsg.rtrnLineDplyStsCd_L1.length()) {

                    break;
                }
            }
        }
    }

    /**
     * getColData
     * @param cMsg NLAL1100CMsg
     * @param sMsg NLAL1100SMsg
     */
    private static void getColData(NLAL1100CMsg cMsg, NLAL1100SMsg sMsg) {

        ZYPGUITableColumn.getColData((NLAL1100CMsg) cMsg, (NLAL1100SMsg) sMsg, "AHEAD");
    }

    /**
     * doProcess_NLAL1100Scrn00_Search_CoordInfo
     * @param msg
     */
    private void doProcess_NLAL1100Scrn00_Search_CoordInfo(NLAL1100CMsg cMsg) {
        S21SsmEZDResult result = NLAL1100Query.getInstance().getPsnNm(cMsg);

        if (result.isCodeNormal()) {
            String psnNm = (String) result.getResultObject();
            ZYPEZDItemValueSetter.setValue(cMsg.xxPsnFirstMidLastNm_H1, psnNm);
        } else {
            cMsg.psnCd_H1.setErrorInfo(1, NLAL1100Constant.NLZM2278E, new String[] {cMsg.psnCd_H1.getValue() });
        }
    }
    
    /**
     * doProcess_NLAL1100Scrn00_Apply
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NLAL1100Scrn00_Apply(NLAL1100CMsg cMsg, NLAL1100SMsg sMsg) {

    	cMsg.clearErrorInfo();
        sMsg.clearErrorInfo();
        cMsg.A.clearErrorInfo();
        sMsg.A.clearErrorInfo();

        int fromIdx = cMsg.xxPageShowFromNum.getValueInt() - 1;

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {

            EZDMsg.copy(cMsg.A.no(i), null, sMsg.A.no(fromIdx + i), null);
        }

        // Input check in Detail
        boolean chkBoxOn = false;
        boolean hasErr = false;
        int firstErrIdx = -1;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A1.getValue())) {

                chkBoxOn = true;

                if (!ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).openStsFlg_A1.getValue())) {

                    // Not Open Status
                    sMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NLAL1100Constant.NWAM0146E);
                    hasErr = true;

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }
                }
            }
        }

        if (!chkBoxOn) {

            for (int i = 0; i < cMsg.A.getValidCount(); i++) {

                NLAL1100_ACMsg cMsgALine = cMsg.A.no(i);
                cMsgALine.xxChkBox_A1.setErrorInfo(1, NLAL1100Constant.NLBM0002E);
            }

            hasErr = true;
        }

        // Get RTRN_TRK_NTFY_GRP_DESC_TXT
        if (ZYPCommonFunc.hasValue(cMsg.rtrnTrkNtfyGrpCd_G)) {

        	cMsg.rtrnTrkNtfyGrpDescTxt_G.clear();
			RTRN_TRK_NTFY_GRPTMsg rtrnTrkNtfyGrpTMsg = new RTRN_TRK_NTFY_GRPTMsg();
		    ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyGrpTMsg.glblCmpyCd, cMsg.glblCmpyCd_G1.getValue());
		    ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyGrpTMsg.rtrnTrkNtfyGrpCd, cMsg.rtrnTrkNtfyGrpCd_G.getValue());
		    rtrnTrkNtfyGrpTMsg = (RTRN_TRK_NTFY_GRPTMsg) EZDFastTBLAccessor.findByKey(rtrnTrkNtfyGrpTMsg);

		    if (rtrnTrkNtfyGrpTMsg == null) {

		        cMsg.rtrnTrkNtfyGrpCd_G.setErrorInfo(1, NLAL1100Constant.NLZM2278E, new String[]{"Assigned to Group"});
		        return;
		    }
		    ZYPEZDItemValueSetter.setValue(cMsg.rtrnTrkNtfyGrpDescTxt_G, rtrnTrkNtfyGrpTMsg.rtrnTrkNtfyGrpDescTxt.getValue());

        }

        // Get LOC_NM
        if (ZYPCommonFunc.hasValue(cMsg.locNm_G)) {
        	
        	// Master Check
            S21SsmEZDResult result = NLAL1100Query.getInstance().getCarrNm(cMsg.glblCmpyCd_G1.getValue(), cMsg.carrCd_G.getValue());
        	
            if (result.isCodeNormal()) {

                List carrNmList = (List)result.getResultObject();
                ZYPEZDItemValueSetter.setValue(cMsg.locNm_G, (String) carrNmList.get(0));

            } else {

                cMsg.carrCd_G.setErrorInfo(1, NLAL1100Constant.NLZM2278E, new String[] {"Carrier"});
                return;
            }

            // Carrier Service Level Check
            if (ZYPCommonFunc.hasValue(cMsg.shpgSvcLvlCd_G)) {

                result = NLAL1100Query.getInstance().getCarrSvcLvlCnt(cMsg.glblCmpyCd_G1.getValue(), cMsg.carrCd_G.getValue(), cMsg.shpgSvcLvlCd_G.getValue());

                if (result.isCodeNormal()) {

                    BigDecimal carrSvcLvlCnt = (BigDecimal) result.getResultObject();

                    if (carrSvcLvlCnt == null || carrSvcLvlCnt.intValue() == 0) {

                        cMsg.carrCd_G.setErrorInfo(1, NLAL1100Constant.NLBM1308E, new String[] {"Pickup Service Level", "Carrier"});
                        cMsg.shpgSvcLvlCd_G.setErrorInfo(1, NLAL1100Constant.NLBM1308E, new String[] {"Pickup Service Level", "Carrier"});
                        return;
                    }

                } else {

                    cMsg.carrCd_G.setErrorInfo(1, NLAL1100Constant.NLBM1308E, new String[] {"Pickup Service Level", "Carrier"});
                    cMsg.shpgSvcLvlCd_G.setErrorInfo(1, NLAL1100Constant.NLBM1308E, new String[] {"Pickup Service Level", "Carrier"});
                    return;
                }
            }

        }
 
        // Copy to selected lines
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

        	if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A1.getValue())) {

        		if (ZYPCommonFunc.hasValue(cMsg.rtrnTrkNtfyGrpCd_G)) {

        			ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtrnTrkNtfyGrpCd_A1, cMsg.rtrnTrkNtfyGrpCd_G);
        		}
        		if (ZYPCommonFunc.hasValue(cMsg.carrCd_G)) {

        			ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).carrCd_A1, cMsg.carrCd_G);
        		}
        		if (ZYPCommonFunc.hasValue(cMsg.schdPickUpDt_G)) {

        			ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).schdPickUpDt_A1, cMsg.schdPickUpDt_G);
        		}
                // START 2019/05/09 T.Ogura [QC#50027,ADD]
                if (ZYPCommonFunc.hasValue(cMsg.rcvTsDplyTxt_G)) {

                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rcvTsDplyTxt_A1, cMsg.rcvTsDplyTxt_G);
                }
                if (ZYPCommonFunc.hasValue(cMsg.rqstRcvDtTxt_G)) {

                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rqstRcvDtTxt_A1, cMsg.rqstRcvDtTxt_G);
                }
                // END   2019/05/09 T.Ogura [QC#50027,ADD]
        		if (ZYPCommonFunc.hasValue(cMsg.inspCpltDt_G)) {

        			ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).inspCpltDt_A1, cMsg.inspCpltDt_G);
        		}
        		if (ZYPCommonFunc.hasValue(cMsg.shpgSvcLvlCd_G)) {

        			ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).shpgSvcLvlCd_A1, cMsg.shpgSvcLvlCd_G);
        		}
        		if (ZYPCommonFunc.hasValue(cMsg.actlPickUpDt_G)) {

        			ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).actlPickUpDt_A1, cMsg.actlPickUpDt_G);
        		}
        		if (ZYPCommonFunc.hasValue(cMsg.carrRsnCd_G)) {

        			ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).carrRsnCd_A1, cMsg.carrRsnCd_G);
        		}
        		if (ZYPCommonFunc.hasValue(cMsg.rtrnTrkStsCd_G)) {

        			ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtrnTrkStsCd_A1, cMsg.rtrnTrkStsCd_G);
        		}
        		if (ZYPCommonFunc.hasValue(cMsg.rtrnTrkNtfyGrpDescTxt_G)) {

        			ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtrnTrkNtfyGrpDescTxt_A1, cMsg.rtrnTrkNtfyGrpDescTxt_G);
        		}
        		if (ZYPCommonFunc.hasValue(cMsg.locNm_G)) {

        			ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).locNm_A1, cMsg.locNm_G);
        		}

        	}

        }

        if (hasErr) {
        	NLAL1100CommonLogic.pagenation(cMsg, sMsg, NLAL1100CommonLogic.getPageStartRowIndex(firstErrIdx, cMsg, sMsg));
        	return;
        }

        NLAL1100CommonLogic.pagenation(cMsg, sMsg, fromIdx);

    }
}
