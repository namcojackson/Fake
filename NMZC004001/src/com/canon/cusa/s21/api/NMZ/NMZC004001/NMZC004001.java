/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NMZ.NMZC004001;

import static com.canon.cusa.s21.api.NMZ.NMZC004001.constant.NMZC004001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.apache.log4j.Logger;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.AMER_CMPYTMsg;
import business.db.AMER_MSTRTMsgArray;
import business.db.ASL_DTLTMsg;
import business.db.ASL_DTLTMsgArray;
import business.db.ASL_HDRTMsg;
import business.db.ASL_HDRTMsgArray;
import business.db.ASL_QLFY_RELNTMsg;
import business.db.ASL_QLFY_RELNTMsgArray;
import business.db.BACK_ORD_IMPCT_TPTMsg;
import business.db.CMPSNTMsg;
import business.db.COA_ACCTTMsg;
import business.db.COA_PRODTMsg;
import business.db.COA_PROJTMsg;
import business.db.CTRYTMsg;
import business.db.CUST_MDSE_XREFTMsg;
import business.db.CYCLE_CNT_CATGTMsg;
import business.db.DFRD_ACCTG_RULETMsg;
import business.db.DFRD_INV_RULETMsg;
import business.db.DS_INTG_MDSE_TPTMsg;
import business.db.DS_MDL_CONFIGTMsg;
import business.db.EASY_PACK_TPTMsg;
import business.db.FRT_CLSTMsg;
import business.db.HARD_ALLOC_TPTMsg;
import business.db.IMG_SPLY_COLOR_TPTMsg;
import business.db.IMG_SPLY_PVT_LB_TPTMsg;
import business.db.IMG_SPLY_TPTMsg;
import business.db.IMG_SPLY_YIELD_TPTMsg;
import business.db.IMG_SPLY_YIELD_UOMTMsg;
import business.db.INTG_PROD_CATG_CONVTMsg;
import business.db.INTG_PROD_CATG_CONVTMsgArray;
import business.db.IWR_MDLTMsg;
import business.db.LINE_BIZ_TPTMsg;
import business.db.MAINT_ITEM_TPTMsg;
import business.db.MDSETMsg;
import business.db.MDSE_CMPSN_TPTMsg;
import business.db.MDSE_CST_UPD_HIST_DTLTMsg;
import business.db.MDSE_CST_UPD_HIST_HDRTMsg;
import business.db.MDSE_ITEM_BILL_TPTMsg;
import business.db.MDSE_ITEM_CLS_TPTMsg;
import business.db.MDSE_ITEM_FLIP_SETTMsg;
import business.db.MDSE_ITEM_MNFTMsg;
import business.db.MDSE_ITEM_RELN_TPTMsg;
import business.db.MDSE_ITEM_STSTMsg;
import business.db.MDSE_ITEM_TPTMsg;
import business.db.MDSE_PRC_GRPTMsg;
import business.db.MDSE_RGTN_TPTMsg;
import business.db.MDSE_SER_NUM_RNGTMsg;
import business.db.MDSE_SFTY_INFOTMsg;
import business.db.MDSE_STORE_PKGTMsg;
import business.db.MDSE_TERM_COND_OPTTMsg;
import business.db.MKT_MDLTMsg;
import business.db.MKT_MDL_SEGTMsg;
import business.db.MRP_INFOTMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.ORD_TAKE_MDSETMsgArray;
import business.db.PKG_UOM_CLSTMsg;
import business.db.PRCH_GRPTMsg;
import business.db.PRC_CATGTMsg;
import business.db.PRC_LIST_EQUIPTMsg;
import business.db.PRNT_VNDTMsg;
import business.db.PRNT_VNDTMsgArray;
import business.db.PROCR_TPTMsg;
import business.db.PROD_CTRLTMsg;
import business.db.PRT_ITEM_TPTMsg;
import business.db.RMA_REQ_TPTMsg;
import business.db.RTL_SWHTMsg;
import business.db.RTL_WHTMsg;
import business.db.RTRN_CTRL_TPTMsg;
import business.db.RTRN_CTRL_TP_VND_RELNTMsg;
import business.db.RTRN_DSPL_TPTMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.db.SUPDTMsg;
import business.db.SUPD_RELNTMsg;
import business.db.SUPD_RELN_STAGETMsg;
import business.db.SVC_ALLOC_TPTMsg;
import business.db.SVC_CHRG_ITEM_TPTMsg;
import business.db.SVC_COV_BASE_TPTMsg;
import business.db.SVC_COV_TMPL_TPTMsg;
import business.db.SVC_WTY_TPTMsg;
import business.db.SW_CATGTMsg;
import business.db.SW_LIC_CTRL_TPTMsg;
import business.db.SW_MAINT_CTRL_TPTMsg;
import business.db.SW_MAINT_TPTMsg;
import business.db.SW_PROD_CATGTMsg;
import business.db.SW_TPTMsg;
import business.db.TAX_EXEM_TPTMsg;
import business.db.TERM_COND_OPT_TPTMsg;
import business.db.TRFTMsg;
import business.db.VNDTMsg;
import business.db.VND_UOMTMsg;
import business.parts.NMZC004001PMsg;
import business.parts.NMZC004002PMsg;
import business.parts.NMZC004002_xxAslListPMsg;
import business.parts.NMZC004002_xxBomListPMsg;
import business.parts.NMZC004002_xxCustXrefListPMsg;
import business.parts.NMZC004002_xxRelListPMsg;
import business.parts.NMZC004002_xxSerNumListPMsg;
import business.parts.NMZC004002_xxStoreListPMsg;
import business.parts.NMZC004002_xxSupdListPMsg;
import business.parts.NMZC004002_xxTermListPMsg;
import business.parts.NPZC115001PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC004001.common.NMZC004001CommonLogic;
import com.canon.cusa.s21.api.NPZ.NPZC115001.NPZC115001;
import com.canon.cusa.s21.api.NPZ.NPZC115001.constant.NPZC115001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASL_QLFY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CCY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DFRD_ACCTG_RULE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DFRD_INV_RULE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HARD_ALLOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_CST_UPD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_CST_UPD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_RELN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_RGTN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_STRU_ELMNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_QLFY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SW_LIC_CTRL_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.ArrayOfAttribute;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.ArrayOfEvent;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.ArrayOfKeyReference;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.Attribute;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.Event;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.KeyReference;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.OPERATIONTYPES;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.REFERENCETYPES;
import com.canon.usa.s21.integration.service.masterdata.messaging.wrapper.MasterDataMessagingServiceProxy;

/**
 * <pre> 
 * Item Registration API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/14/2016   Fujitsu         C.Tanaka        Create          N/A
 * 02/26/2016   SRAA            K.Aratani       Update          QC3767, QC4507, V1.5, V1.6
 * 03/16/2016   SRAA            K.Aratani       Update          QC#5580
 * 05/16/2016   SRAA            Y.Chen          Update          QC#4342
 * 05/18/2016   SRAA            K.Aratani       Update          QC#4203
 * 05/27/2016   Fujitsu         M.Suzuki        Update          QC#9073
 * 06/13/2016   SRAA            K.Aratani       Update          QC#9916
 * 06/13/2016   SRAA            K.Aratani       Update          QC#9346
 * 06/13/2016   SRAA            K.Aratani       Update          QC#9631
 * 06/16/2016   SRAA            K.Aratani       Update          QC#6748,9891,9916,9970
 * 06/20/2016   SRAA            K.Aratani       Update          QC#10449
 * 06/23/2016   SRAA            K.Aratani       Update          QC#9970-1
 * 06/27/2016   SRAA            K.Aratani       Update          QC#9823, QC#10129
 * 07/05/2016   SRAA            K.Aratani       Update          QC#11172
 * 07/27/2016   SRAA            K.Aratani       Update          QC#12451
 * 08/18/2016   SRAA            K.Aratani       Update          QC#10748
 * 08/26/2016   SRAA            K.Aratani       Update          QC#13868
 * 09/20/2016   SRAA            K.Aratani       Update          QC#14441
 * 09/27/2016   SRAA            K.Aratani       Update          QC#14689
 * 12/14/2016   SRAA            K.Aratani       Update          QC#16628
 * 01/23/2017   Fujitsu         Y.Kanefusa      Update          QC#17260
 * 01/24/2017   Fujitsu         S.Yamamoto      Update          QC#17078
 * 01/27/2017   Fujitsu         M.Ohno          Update          QC#17260-2
 * 07/06/2017   Fujitsu         M.Ohno          Update          QC#19777
 * 09/25/2017   Fujitsu         T.Aoi           Update          QC#18534(L3#445)
 * 04/03/2018   CITS            K.Ogino         Update          Sol#477(QC21587)
 * 06/10/2019   Fujitsu         M.Ohno          Update          QC#50763
 * 08/09/2019   CITS            R.Shimamoto     Update          QC#52531
 * 08/20/2019   CITS            M.Naito         Update          QC#52672
 * 12/13/2019   Fujitsu         A.Kazuki        Update          QC#54218
 * 2019/12/16   Fujitsu         K.Kato          Update          QC#54859
 * 2020/01/06   Fujitsu         A.Kazuki        Update          QC#54218-2
 * 2020/01/17   Fujitsu         K.Kato          Update          QC#55451
 * 2020/01/30   Fujitsu         C.Hara          Update          QC#55620
 * 2020/02/12   Fujitsu         Y.Kanefusa      Update          QC#55844
 * 2020/02/18   Fujitsu         Y.Kanefusa      Update          S21_NA#55754
 * 2020/04/13   CITS            K.Ogino         Update          QC#56494
 * 2022/02/17   Fujitsu         C.Hara          Update          QC#59693
 * 2022/12/20   Hitachi         E.Watabe        Update          QC#60861
 * 2023/01/05   Hitachi         E.Watabe        Update          QC#60892
 * 2023/03/27   Hitachi         E.Watabe        Update          QC#61207
 * 2023/04/06   CITS            F.Komaki        Update          QC#61371
 *</pre>
 */
public class NMZC004001 extends S21ApiCommonBase {

    /** CUSA Global Company Code */
    private String cusaGlblCmpyCd = null;

    /** CUSA Parts Table Name */
    private String cusaPartsTbl = null;

    /** CUSA Merchandise Table Name */
    private String cusaMdseTbl = null;

    /** CUSA Safety Table Name */
    private String cusaSftyTbl = null;

    /** CUSA Composition Table Name */
    private String cusaCmpsnTbl = null;

    /** CUSA Storage Table Name */
    private String cusaStoreTbl = null;

    /** CUSA Serial Number Table Name */
    private String cusaSerNumTbl = null;

    /** Skip Supersede Create Flag */
    private String skipSupdCratFlg = null;

    /** ASL Header List */
    private List<Map<String, Object>> aslHdrList;

    /** Service Model ID */
    private BigDecimal svcMdlId = null;

    /** Logger */
    private static Logger logger = Logger.getLogger(NMZC004001.class);

    /** NMAL004001CommonLogic */
    private NMZC004001CommonLogic commonLogic;

    /** CUSA Set Flag */
    private boolean cusaSetFlg = false;

    
    /**
     * Constructor.
     */
    public NMZC004001() {
        super();
    }

    /**
     * Execute Asset Detail Update
     * @param param NMZC004001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NMZC004001PMsg param, final ONBATCH_TYPE onBatchType) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        doProcess(msgMap);

        msgMap.flush();
    }

    /**
     * Execute Asset Detail Update
     * @param params List
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NMZC004001PMsg> params, final ONBATCH_TYPE onBatchType) {

        for (NMZC004001PMsg msg : params) {
            execute(msg, onBatchType);
        }
    }

    protected void doProcess(S21ApiMessageMap msgMap) {

        commonLogic = new NMZC004001CommonLogic();
        NMZC004001PMsg param = (NMZC004001PMsg) msgMap.getPmsg();

        if (!checkInput(param)) {
            commonLogic.setMsgMap(msgMap);
            return;
        }

        if (!getCusaTblNm(param)) {
            commonLogic.setMsgMap(msgMap);
            return;
        }

        NMZC004002PMsg pMsg;
        for (int i = 0; i < param.NMZC004002PMsg.getValidCount(); i++) {
            aslHdrList = new ArrayList<Map<String, Object>>(LIST_MAX_LG);
            pMsg = param.NMZC004002PMsg.no(i);

            if (!hasValue(pMsg.mdseCd_M)) {
                commonLogic.setErrorMessage(param, i, NMZM0163E, new String[] {MDSE_CD });
                continue;
            }

            if (!setParam(param, i)) {
                continue;
            }

            checkItemInput(param, i);
            if (!commonLogic.isErrMax(i, 1)) {
                continue;
            }

            saveItem(param, i);
        }

        if (param.xxErrItemList.getValidCount() > 0) {
            commonLogic.setMsgMap(msgMap);
        } else {
            sendMsgToDealConfigurator(param);
        }
    }

    private boolean checkInput(NMZC004001PMsg param) {

        boolean err = true;

        if (!hasValue(param.glblCmpyCd)) {
            commonLogic.setErrorMessage(param, 0, NMZM0163E, new String[] {GLBL_CMPY_CD });
            err = false;
        }
        if (!hasValue(param.slsDt)) {
            commonLogic.setErrorMessage(param, 0, NMZM0163E, new String[] {SLS_DT });
            err = false;
        }
        if (param.NMZC004002PMsg.getValidCount() == 0) {
            commonLogic.setErrorMessage(param, 0, NMZM0163E, new String[] {XX_ITEM_LIST });
            err = false;
        }

        return err;
    }

    private boolean getCusaTblNm(NMZC004001PMsg param) {

        String glblCmpyCd = param.glblCmpyCd.getValue();
        boolean err = true;

        cusaGlblCmpyCd = ZYPCodeDataUtil.getVarCharConstValue(CUSA_GLBL_CMPY_CD, glblCmpyCd);
        if (cusaGlblCmpyCd == null) {
            commonLogic.setErrorMessage(param, 0, NMZM0138E, new String[] {MSG_CUSA_GLBL_CMPY_CD });
            err = false;
        }

        cusaPartsTbl = ZYPCodeDataUtil.getVarCharConstValue(CUSA_USMM4100, glblCmpyCd);
        if (cusaPartsTbl == null) {
            commonLogic.setErrorMessage(param, 0, NMZM0138E, new String[] {MSG_CUSA_USMM4100 });
            err = false;
        }

        cusaMdseTbl = ZYPCodeDataUtil.getVarCharConstValue(CUSA_MDSE, glblCmpyCd);
        if (cusaMdseTbl == null) {
            commonLogic.setErrorMessage(param, 0, NMZM0138E, new String[] {MSG_CUSA_MDSE });
            err = false;
        }

        cusaSftyTbl = ZYPCodeDataUtil.getVarCharConstValue(CUSA_MDSE_SFTY_INFO, glblCmpyCd);
        if (cusaSftyTbl == null) {
            commonLogic.setErrorMessage(param, 0, NMZM0138E, new String[] {MSG_CUSA_MDSE_SFTY_INFO });
            err = false;
        }

        cusaCmpsnTbl = ZYPCodeDataUtil.getVarCharConstValue(CUSA_CMPSN, glblCmpyCd);
        if (cusaCmpsnTbl == null) {
            commonLogic.setErrorMessage(param, 0, NMZM0138E, new String[] {MSG_CUSA_CMPSN });
            err = false;
        }

        cusaStoreTbl = ZYPCodeDataUtil.getVarCharConstValue(CUSA_MDSE_STORE_PKG, glblCmpyCd);
        if (cusaStoreTbl == null) {
            commonLogic.setErrorMessage(param, 0, NMZM0138E, new String[] {MSG_CUSA_MDSE_STORE_PKG });
            err = false;
        }

        cusaSerNumTbl = ZYPCodeDataUtil.getVarCharConstValue(CUSA_MDSE_SER_NUM_RNG, glblCmpyCd);
        if (cusaSerNumTbl == null) {
            commonLogic.setErrorMessage(param, 0, NMZM0138E, new String[] {MSG_CUSA_MDSE_SER_NUM_RNG });
            err = false;
        }

        return err;
    }

    private boolean setParam(NMZC004001PMsg param, int idx) {

        if (!setCusaInfo(param, idx)) {
            return false;
        }

        if (!setDefaultValue(param, idx)) {
            return false;
        }

        setFixedValue(param, idx);
        clearParamByItemType(param, idx);

        return true;
    }

    private boolean setCusaInfo(NMZC004001PMsg param, int idx) {

        String glblCmpyCd = param.glblCmpyCd.getValue();
        String slsDt = param.slsDt.getValue();
        NMZC004002PMsg pMsg = param.NMZC004002PMsg.no(idx);
        String mdseCd = pMsg.mdseCd_M.getValue();

        Map<String, Object> cusaPartsMap = null;
        // Set Base field
        if (!hasValue(pMsg.mdseRgtnTpCd_D)) {
            ZYPEZDItemValueSetter.setValue(pMsg.mdseRgtnTpCd_D, MDSE_RGTN_TP.MANUAL);
            AMER_MSTRTMsgArray amerMstrTMsgArray = commonLogic.getAmerMstr(glblCmpyCd, mdseCd);
            if (amerMstrTMsgArray.length() > 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.mdseRgtnTpCd_D, MDSE_RGTN_TP.MERCURY);
            } else {
                cusaPartsMap = NMZC004001Query.getInstance().getCusaParts(cusaPartsTbl, mdseCd);
                if (cusaPartsMap != null) {
                    ZYPEZDItemValueSetter.setValue(pMsg.mdseRgtnTpCd_D, MDSE_RGTN_TP.S21_PARTS);
                }
            }
        }

        //QC3767
        Map<String, Object> defMdseItemStsMap = NMZC004001Query.getInstance().getDefaultItemStatus(glblCmpyCd);
        if (defMdseItemStsMap != null && defMdseItemStsMap.get("MDSE_ITEM_STS_CD") != null) {
            //QC#9823, QC#10129
        	//If Set and Kit, they don't have any component, should be Pre Launch.
        	if ((MDSE_ITEM_TP.SET.equals(pMsg.mdseItemTpCd_D.getValue())
        			|| MDSE_ITEM_TP.KIT.equals(pMsg.mdseItemTpCd_D.getValue()))
                    // 2017/01/27 S21_NA#17342 Mod Start
                    && pMsg.xxBomList.getValidCount() == 0) {
                    // && !NMZC004001CommonLogic.existsCmpsn(glblCmpyCd, mdseCd)) {
                    // 2017/01/27 S21_NA#17342 Mod End
                ZYPEZDItemValueSetter.setValue(pMsg.mdseItemStsCd_D, MDSE_ITEM_STS.PRELAUNCH);
        	} else {
                ZYPEZDItemValueSetter.setValue(pMsg.mdseItemStsCd_D, (String) defMdseItemStsMap.get("MDSE_ITEM_STS_CD"));
        	}
            
        } else {
            commonLogic.setErrorMessage(param, idx, NMZM0181E, null);
            return false;
        }

        // Set CUSA Item
        if (MDSE_RGTN_TP.MERCURY.equals(pMsg.mdseRgtnTpCd_D.getValue())) {

            // Basic Info
            Map<String, Object> cusaMdseMap = NMZC004001Query.getInstance().getCusaMdse(cusaGlblCmpyCd, cusaMdseTbl, mdseCd);
            if (cusaMdseMap != null) {

                if (!hasValue(pMsg.mdseDescShortTxt_D)) {
                //if (!hasValue(pMsg.mdseNm_M)) {
                    ZYPEZDItemValueSetter.setValue(pMsg.mdseNm_M, (String) cusaMdseMap.get("MDSE_NM"));
                    ZYPEZDItemValueSetter.setValue(pMsg.mdseDescShortTxt_D, (String) cusaMdseMap.get("MDSE_NM"));
                }
                if (!hasValue(pMsg.mdseDescLongTxt_D)) {
                //if (!hasValue(pMsg.mdseFmlNm_M)) {
                    ZYPEZDItemValueSetter.setValue(pMsg.mdseFmlNm_M, (String) cusaMdseMap.get("MDSE_FML_NM"));
                    ZYPEZDItemValueSetter.setValue(pMsg.mdseDescLongTxt_D, (String) cusaMdseMap.get("MDSE_FML_NM"));
                }
                if (!hasValue(pMsg.coaProdCd_M)) {
                    ZYPEZDItemValueSetter.setValue(pMsg.coaProdCd_M, (String) cusaMdseMap.get("COA_PROD_CD"));
                }
                if (!hasValue(pMsg.upcCd_M)) {
                    ZYPEZDItemValueSetter.setValue(pMsg.upcCd_M, (String) cusaMdseMap.get("UPC_CD"));
                }
                if (!hasValue(pMsg.prntCmpySetMdseFlg_D) && MDSE_TP.SET.equals((String) cusaMdseMap.get("MDSE_TP_CD"))) {
                    ZYPEZDItemValueSetter.setValue(pMsg.prntCmpySetMdseFlg_D, ZYPConstant.FLG_ON_Y);
                }
                
                //QC#12451
                if (!hasValue(pMsg.frtClsCd_M)) {
                    ZYPEZDItemValueSetter.setValue(pMsg.frtClsCd_M, (String) cusaMdseMap.get("FRT_CLS_CD"));
                }
                
            }

            // Safety Info
            Map<String, Object> cusaSftyMap = NMZC004001Query.getInstance().getCusaSfty(cusaGlblCmpyCd, cusaSftyTbl, mdseCd);
            if (cusaSftyMap != null) {
                if (!hasValue(pMsg.hazMatFlg_S)) {
                    ZYPEZDItemValueSetter.setValue(pMsg.hazMatFlg_S, (String) cusaSftyMap.get("HAZ_MAT_FLG"));
                }
                if (!hasValue(pMsg.madeInCtryCd_S)) {
                    ZYPEZDItemValueSetter.setValue(pMsg.madeInCtryCd_S, (String) cusaSftyMap.get("MADE_IN_CTRY_CD"));
                }
                if (!hasValue(pMsg.asmInCtryCd_S)) {
                    ZYPEZDItemValueSetter.setValue(pMsg.asmInCtryCd_S, (String) cusaSftyMap.get("ASM_IN_CTRY_CD"));
                }
                if (!hasValue(pMsg.hazMatCd_S)) {
                    ZYPEZDItemValueSetter.setValue(pMsg.hazMatCd_S, (String) cusaSftyMap.get("HAZ_MAT_CD"));
                }
            }

            // BOM
            //CusaSetFlag
            if (MDSE_ITEM_TP.SET.equals(pMsg.mdseItemTpCd_D.getValue())) {
                List<Map<String, Object>> cusaSetList = NMZC004001Query.getInstance().getCusaSetList(cusaGlblCmpyCd, cusaMdseTbl, cusaCmpsnTbl, mdseCd);
                if (cusaSetList != null && cusaSetList.size() > 0) {
                    this.cusaSetFlg = true;
                }
            }
            //Cusa Set Default Set
            if (pMsg.xxBomList.getValidCount() == 0 && this.cusaSetFlg) {
                List<Map<String, Object>> cusaCmpsnMap = NMZC004001Query.getInstance().getCusaCmpsnList(cusaGlblCmpyCd, cusaCmpsnTbl, mdseCd, pMsg.xxBomList.length());
                NMZC004002_xxBomListPMsg bomPMsg;
                int i = 0;
                for (i = 0; i < cusaCmpsnMap.size(); i++) {
                    bomPMsg = pMsg.xxBomList.no(i);
                    ZYPEZDItemValueSetter.setValue(bomPMsg.prntMdseCd_C, (String) cusaCmpsnMap.get(i).get("PRNT_MDSE_CD"));
                    ZYPEZDItemValueSetter.setValue(bomPMsg.mdseCmpsnTpCd_C, (String) cusaCmpsnMap.get(i).get("MDSE_CMPSN_TP_CD"));
                    ZYPEZDItemValueSetter.setValue(bomPMsg.childMdseCd_C, (String) cusaCmpsnMap.get(i).get("CHILD_MDSE_CD"));
                    ZYPEZDItemValueSetter.setValue(bomPMsg.childOrdTakeMdseCd_C, (String) cusaCmpsnMap.get(i).get("CHILD_ORD_TAKE_MDSE_CD"));
                    ZYPEZDItemValueSetter.setValue(bomPMsg.childMdseQty_C, (BigDecimal) cusaCmpsnMap.get(i).get("CHILD_MDSE_QTY"));
                    ZYPEZDItemValueSetter.setValue(bomPMsg.mainCmpsnFlg_C, (String) cusaCmpsnMap.get(i).get("MAIN_CMPSN_FLG"));
                    ZYPEZDItemValueSetter.setValue(bomPMsg.effFromDt_C, slsDt);
                    ZYPEZDItemValueSetter.setValue(bomPMsg.pkgUomCd_DC, PKG_UOM.EACH);
                }
                pMsg.xxBomList.setValidCount(i);
            }

            // Storage
            NMZC004002_xxStoreListPMsg storePMsg;
            for (int i = 0; i < pMsg.xxStoreList.getValidCount(); i++) {
                storePMsg = pMsg.xxStoreList.no(i);
                Map<String, Object> cusaStoreMap = NMZC004001Query.getInstance().getCusaStore(cusaGlblCmpyCd, cusaStoreTbl, storePMsg.mdseCd_S.getValue(), storePMsg.pkgUomCd_S.getValue());
                if (cusaStoreMap != null) {
                    if (!hasValue(storePMsg.inEachQty_S)) {
                        ZYPEZDItemValueSetter.setValue(storePMsg.inEachQty_S, (BigDecimal) cusaStoreMap.get("IN_EACH_QTY"));
                    }
                    if (!hasValue(storePMsg.inInchLg_S)) {
                        ZYPEZDItemValueSetter.setValue(storePMsg.inInchLg_S, (BigDecimal) cusaStoreMap.get("IN_INCH_LG"));
                    }
                    if (!hasValue(storePMsg.inInchWdt_S)) {
                        ZYPEZDItemValueSetter.setValue(storePMsg.inInchWdt_S, (BigDecimal) cusaStoreMap.get("IN_INCH_WDT"));
                    }
                    if (!hasValue(storePMsg.inInchHgt_S)) {
                        ZYPEZDItemValueSetter.setValue(storePMsg.inInchHgt_S, (BigDecimal) cusaStoreMap.get("IN_INCH_HGT"));
                    }
                    if (!hasValue(storePMsg.inPoundWt_S)) {
                        ZYPEZDItemValueSetter.setValue(storePMsg.inPoundWt_S, (BigDecimal) cusaStoreMap.get("IN_POUND_WT"));
                    }
                }
            }

            // Serial#
            if (pMsg.xxSerNumList.getValidCount() == 0) {
                List<Map<String, Object>> cusaSerNumMap = NMZC004001Query.getInstance().getCusaSerNumList(cusaGlblCmpyCd, cusaSerNumTbl, mdseCd, pMsg.xxSerNumList.length());
                NMZC004002_xxSerNumListPMsg serPMsg;
                int i = 0;
                for (i = 0; i < cusaSerNumMap.size(); i++) {
                    serPMsg = pMsg.xxSerNumList.no(i);
                    ZYPEZDItemValueSetter.setValue(serPMsg.mdseCd, (String) cusaSerNumMap.get(i).get("MDSE_CD"));
                    ZYPEZDItemValueSetter.setValue(serPMsg.fromSerNum, (String) cusaSerNumMap.get(i).get("FROM_SER_NUM"));
                    ZYPEZDItemValueSetter.setValue(serPMsg.thruSerNum, (String) cusaSerNumMap.get(i).get("THRU_SER_NUM"));
                }
                pMsg.xxSerNumList.setValidCount(i);
            }

            // Set CUSA Parts
        } else if (MDSE_RGTN_TP.S21_PARTS.equals(pMsg.mdseRgtnTpCd_D.getValue())) {

            if (cusaPartsMap == null) {
                cusaPartsMap = NMZC004001Query.getInstance().getCusaParts(cusaPartsTbl, mdseCd);
                if (cusaPartsMap == null) {
                    commonLogic.setErrorMessage(param, idx, NMZM0146E, new String[] {mdseCd, MSG_CUSA_PARTS });
                    return false;
                }
            }

            int cnt = pMsg.xxStoreList.getValidCount();
            if (cnt > 0) {
                BigDecimal poundWt = (BigDecimal) cusaPartsMap.get("P_SPEC_WGHT_PART");
                poundWt = poundWt.multiply(new BigDecimal(LB));
                poundWt = poundWt.setScale(DECL_PLACE_4, BigDecimal.ROUND_HALF_UP);

                NMZC004002_xxStoreListPMsg storePMsg;
                for (int i = 0; i < cnt; i++) {
                    storePMsg = pMsg.xxStoreList.no(i);
                    if (!hasValue(storePMsg.inPoundWt_S) && PKG_UOM.EACH.equals(storePMsg.pkgUomCd_S.getValue())) {
                        ZYPEZDItemValueSetter.setValue(storePMsg.inPoundWt_S, poundWt);
                    }
                }
            }
        }

        return true;
    }

    private boolean setDefaultValue(NMZC004001PMsg param, int idx) {

        String glblCmpyCd = param.glblCmpyCd.getValue();
        NMZC004002PMsg pMsg = param.NMZC004002PMsg.no(idx);
        String mdseItemTpCd = pMsg.mdseItemTpCd_D.getValue();

        // Header
        if (!hasValue(pMsg.thirdPtyVndDropFlg_M)) {
            ZYPEZDItemValueSetter.setValue(pMsg.thirdPtyVndDropFlg_M, ZYPConstant.FLG_OFF_N);
        }
        if (!hasValue(pMsg.invtyValFlg_M)) {
            ZYPEZDItemValueSetter.setValue(pMsg.invtyValFlg_M, pMsg.invtyCtrlFlg_M);
        }
        if (!hasValue(pMsg.sellBySelfFlg_M)) {
            ZYPEZDItemValueSetter.setValue(pMsg.sellBySelfFlg_M, ZYPConstant.FLG_ON_Y);
        }
        if (!hasValue(pMsg.setMdseShipCpltFlg_M)) {
            ZYPEZDItemValueSetter.setValue(pMsg.setMdseShipCpltFlg_M, ZYPConstant.FLG_OFF_N);
            if (MDSE_ITEM_TP.SET.equals(mdseItemTpCd)) {
                ZYPEZDItemValueSetter.setValue(pMsg.setMdseShipCpltFlg_M, ZYPConstant.FLG_ON_Y);
            }
        }
        if (!hasValue(pMsg.daysPriAllocNum_M)) {
            ZYPEZDItemValueSetter.setValue(pMsg.daysPriAllocNum_M, TWO);
            String defDays = ZYPCodeDataUtil.getVarCharConstValue(DEF_DAYS_PRI_ALLOC_NUM, glblCmpyCd);
            if (defDays != null) {
                ZYPEZDItemValueSetter.setValue(pMsg.daysPriAllocNum_M, new BigDecimal(defDays));
            }
        }
        if (!hasValue(pMsg.rgtnStsCd_M)) {
            ZYPEZDItemValueSetter.setValue(pMsg.rgtnStsCd_M, RGTN_STS.READY_FOR_ORDER_TAKING);
        }
        if (!hasValue(pMsg.invPsblFlg_D)) {
            ZYPEZDItemValueSetter.setValue(pMsg.invPsblFlg_D, ZYPConstant.FLG_ON_Y);
        }
        if (!hasValue(pMsg.dfrdAcctgRuleCd_D)) {
            ZYPEZDItemValueSetter.setValue(pMsg.dfrdAcctgRuleCd_D, DFRD_ACCTG_RULE.IMMEDIATE);
        }
        if (!hasValue(pMsg.dfrdInvRuleCd_D)) {
            ZYPEZDItemValueSetter.setValue(pMsg.dfrdInvRuleCd_D, DFRD_INV_RULE.ARREARS);
        }
        if (!hasValue(pMsg.custOrdEnblFlg_D)) {
            ZYPEZDItemValueSetter.setValue(pMsg.custOrdEnblFlg_D, ZYPConstant.FLG_ON_Y);
        }
        if (!hasValue(pMsg.pkgUomClsCd_D)) {
            ZYPEZDItemValueSetter.setValue(pMsg.pkgUomClsCd_D, PKG_UOM_CLS.QUANTITY);
        }
        if (!hasValue(pMsg.assetRecovCostAmt_D)) {
            ZYPEZDItemValueSetter.setValue(pMsg.assetRecovCostAmt_D, BigDecimal.ZERO);
        }
        
        
        //QC4769
        if (!hasValue(pMsg.intntConnSwCtrlFlg_D)) {
            ZYPEZDItemValueSetter.setValue(pMsg.intntConnSwCtrlFlg_D, ZYPConstant.FLG_OFF_N);
        }

        // Selling Hold, Discontinue Flag
        MDSE_ITEM_STSTMsg mdseItemStsTMsg = commonLogic.getMdseItemSts(glblCmpyCd, pMsg.mdseItemStsCd_D.getValue());
        if (mdseItemStsTMsg != null) {
            if (!hasValue(pMsg.dsctnFlg_M)) {
                ZYPEZDItemValueSetter.setValue(pMsg.dsctnFlg_M, mdseItemStsTMsg.dsctnFlg);
            }
            if (!hasValue(pMsg.sellHldFlg_M)) {
                ZYPEZDItemValueSetter.setValue(pMsg.sellHldFlg_M, mdseItemStsTMsg.sellHldFlg);
            }
        }

        // Mdse Type, Mdse Category, Intangible Product Category
        String coaMdseTpCd = pMsg.coaMdseTpCd_D.getValue();
        String swLicCtrlTpCd = pMsg.swLicCtrlTpCd_D.getValue();
        String swCatgCd = pMsg.swCatgCd_D.getValue();

        INTG_PROD_CATG_CONVTMsg intgProdCatgConvTMsg = new INTG_PROD_CATG_CONVTMsg();
        intgProdCatgConvTMsg.setSQLID("001");
        intgProdCatgConvTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        intgProdCatgConvTMsg.setConditionValue("mdseItemTpCd01", mdseItemTpCd);
        intgProdCatgConvTMsg.setConditionValue("coaMdseTpCd01", coaMdseTpCd);
        intgProdCatgConvTMsg.setConditionValue("swLicCtrlTpCd01", swLicCtrlTpCd);
        intgProdCatgConvTMsg.setConditionValue("swCatgCd01", swCatgCd);
        intgProdCatgConvTMsg.setConditionValue("intntConnSwCtrlFlg01", pMsg.intntConnSwCtrlFlg_D.getValue());
        INTG_PROD_CATG_CONVTMsgArray intgProdCatgConvTMsgArray = (INTG_PROD_CATG_CONVTMsgArray) S21ApiTBLAccessor.findByCondition(intgProdCatgConvTMsg);
        if (intgProdCatgConvTMsgArray.length() > 0) {
            if (!hasValue(pMsg.intgProdCatgCd_M)) {
                ZYPEZDItemValueSetter.setValue(pMsg.intgProdCatgCd_M, intgProdCatgConvTMsgArray.no(0).intgProdCatgCd);
            }
            if (!hasValue(pMsg.mdseTpCd_M)) {
                ZYPEZDItemValueSetter.setValue(pMsg.mdseTpCd_M, intgProdCatgConvTMsgArray.no(0).mdseTpCd);
            }
            if (!hasValue(pMsg.mdseCatgCd_D)) {
                ZYPEZDItemValueSetter.setValue(pMsg.mdseCatgCd_D, intgProdCatgConvTMsgArray.no(0).mdseCatgCd);
            }

        } else {
            StringBuilder msgAt3 = new StringBuilder();

            MDSE_ITEM_TPTMsg mdseItemTpTMsg = commonLogic.getMdseItemTp(glblCmpyCd, mdseItemTpCd);
            if (mdseItemTpTMsg != null && hasValue(mdseItemTpTMsg.mdseItemTpDescTxt)) {
                msgAt3.append(mdseItemTpTMsg.mdseItemTpDescTxt.getValue());
            }
            msgAt3.append(",");

            COA_PROJTMsg coaProjTMsg = commonLogic.getCoaProj(glblCmpyCd, coaMdseTpCd);
            if (coaProjTMsg != null && hasValue(coaProjTMsg.coaProjDescTxt)) {
                msgAt3.append(coaProjTMsg.coaProjDescTxt.getValue());
            }
            msgAt3.append(",");

            SW_LIC_CTRL_TPTMsg swLicCtrlTpTMsg = new SW_LIC_CTRL_TPTMsg();
            ZYPEZDItemValueSetter.setValue(swLicCtrlTpTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(swLicCtrlTpTMsg.swLicCtrlTpCd, swLicCtrlTpCd);
            swLicCtrlTpTMsg = (SW_LIC_CTRL_TPTMsg) S21CacheTBLAccessor.findByKey(swLicCtrlTpTMsg);
            if (swLicCtrlTpTMsg != null && hasValue(swLicCtrlTpTMsg.swLicCtrlTpDescTxt)) {
                msgAt3.append(swLicCtrlTpTMsg.swLicCtrlTpDescTxt.getValue());
            }
            msgAt3.append(",");

            SW_CATGTMsg swCatgTMsg = new SW_CATGTMsg();
            ZYPEZDItemValueSetter.setValue(swCatgTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(swCatgTMsg.swCatgCd, swCatgCd);
            swCatgTMsg = (SW_CATGTMsg) S21CacheTBLAccessor.findByKey(swCatgTMsg);
            if (swCatgTMsg != null && hasValue(swCatgTMsg.swCatgDescTxt)) {
                msgAt3.append(swCatgTMsg.swCatgDescTxt.getValue());
            }
            msgAt3.append(",");

            if (hasValue(pMsg.intntConnSwCtrlFlg_D)) {
                msgAt3.append(pMsg.intntConnSwCtrlFlg_D.getValue());
            }

            commonLogic.setErrorMessage(param, idx, NMZM0164E, new String[] {MSG_INTG_PROD_CATG_CONV_01, MSG_INTG_PROD_CATG_CONV_02, msgAt3.toString() });
            return false;
        }

        // Merchandise Class
        if (MDSE_RGTN_TP.MERCURY.equals(pMsg.mdseRgtnTpCd_D.getValue())) {
            AMER_MSTRTMsgArray amerMstrTMsgArray = commonLogic.getAmerMstr(glblCmpyCd, pMsg.mdseCd_M.getValue());
            if (amerMstrTMsgArray.length() > 0 && !hasValue(pMsg.mercClsCd_M)) {
                ZYPEZDItemValueSetter.setValue(pMsg.mercClsCd_M, amerMstrTMsgArray.no(0).amerMdseClsTpCd);
            }
        }

        // Safety
        if (!ZYPConstant.FLG_ON_Y.equals(pMsg.hazMatFlg_S.getValue())) {
            pMsg.hazMatCd_S.clear();
        }

        // 2017/01/23 S21_NA#17260 Add Start
        int cnt = pMsg.xxBomList.getValidCount();
        NMZC004002_xxBomListPMsg bomPMsg;
        for (int i = 0; i < cnt; i++) {
            bomPMsg = pMsg.xxBomList.no(i);
            // Set fixed value
            ZYPEZDItemValueSetter.setValue(bomPMsg.prntMdseCd_DC, bomPMsg.prntMdseCd_C); // 2017/02/01 S21_NA#17260-3 Add Start
            if (MDSE_ITEM_TP.SET.equals(pMsg.mdseItemTpCd_D.getValue())) {
                if (hasValue(bomPMsg.childMdseCd_C)) {
                    if (commonLogic.getOrdTakeMdse(glblCmpyCd, bomPMsg.childMdseCd_C.getValue()) != null) {
                        ZYPEZDItemValueSetter.setValue(bomPMsg.mdseCmpsnTpCd_C, MDSE_CMPSN_TP.SET_ORDERTAKE_MDSE);
                        ZYPEZDItemValueSetter.setValue(bomPMsg.childOrdTakeMdseCd_C, bomPMsg.childMdseCd_C);
                        bomPMsg.childMdseCd_C.clear();
                    } else {
                        ZYPEZDItemValueSetter.setValue(bomPMsg.mdseCmpsnTpCd_C, MDSE_CMPSN_TP.SET_MDSE);
                        bomPMsg.childOrdTakeMdseCd_C.clear();
                    }
                }
            }
        }
        // 2017/01/23 S21_NA#17260 Add End

        NMZC004002_xxSupdListPMsg supdPMsg;
        for (int i = 0; i < pMsg.xxSupdList.getValidCount(); i++) {
            supdPMsg = pMsg.xxSupdList.no(i);
            if (!hasValue(supdPMsg.supdCratDt)) {
                ZYPEZDItemValueSetter.setValue(supdPMsg.supdCratDt, param.slsDt.getValue());
            }
        }

        NMZC004002_xxCustXrefListPMsg custPMsg;
        for (int i = 0; i < pMsg.xxCustXrefList.getValidCount(); i++) {
            custPMsg = pMsg.xxCustXrefList.no(i);
            if (!hasValue(custPMsg.custMdseXrefEnblFlg)) {
                ZYPEZDItemValueSetter.setValue(custPMsg.custMdseXrefEnblFlg, ZYPConstant.FLG_ON_Y);
            }
        }

        NMZC004002_xxAslListPMsg aslPMsg;
        for (int i = 0; i < pMsg.xxAslList.getValidCount(); i++) {
            aslPMsg = pMsg.xxAslList.no(i);
            if (!hasValue(aslPMsg.mdseNm)) {
                //ZYPEZDItemValueSetter.setValue(aslPMsg.mdseNm, pMsg.mdseNm_M);
                ZYPEZDItemValueSetter.setValue(aslPMsg.mdseNm, getMdseNm(pMsg.mdseDescShortTxt_D.getValue()));
            }
            if (!hasValue(aslPMsg.coaMdseTpCd)) {
                ZYPEZDItemValueSetter.setValue(aslPMsg.coaMdseTpCd, pMsg.coaMdseTpCd_D);
            }
            if (!hasValue(aslPMsg.primSplyFlg)) {
                ZYPEZDItemValueSetter.setValue(aslPMsg.primSplyFlg, ZYPConstant.FLG_ON_Y);
            }
            if (!hasValue(aslPMsg.vndMinOrdQty)) {
                ZYPEZDItemValueSetter.setValue(aslPMsg.vndMinOrdQty, BigDecimal.ONE);
            }
            if (!hasValue(aslPMsg.vndIncrOrdQty)) {
                ZYPEZDItemValueSetter.setValue(aslPMsg.vndIncrOrdQty, BigDecimal.ONE);
            }
            if (!hasValue(aslPMsg.effFromDt)) {
                ZYPEZDItemValueSetter.setValue(aslPMsg.effFromDt, ZYPDateUtil.getCurrentSystemTime(DATE_PATTERN));
            }
            ZYPEZDItemValueSetter.setValue(aslPMsg.effThruDt, "");
        }

        return true;
    }

    private void setFixedValue(NMZC004001PMsg param, int idx) {

        String slsDt = param.slsDt.getValue();
        NMZC004002PMsg pMsg = param.NMZC004002PMsg.no(idx);

        ZYPEZDItemValueSetter.setValue(pMsg.invtyDistTpCd_M, INVTY_DIST_TP_CD_3);
        ZYPEZDItemValueSetter.setValue(pMsg.invtySoftAllocTpCd_M, INVTY_SOFT_ALLOC_TP_CD_4);
        ZYPEZDItemValueSetter.setValue(pMsg.thisMthInlndFrtAmt_M, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(pMsg.thisMthIntlFrtAmt_M, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(pMsg.thisMthImptDtyAmt_M, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(pMsg.thisMthInsAmt_M, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(pMsg.nextMthFobAmt_M, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(pMsg.nextMthInlndFrtAmt_M, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(pMsg.nextMthIntlFrtAmt_M, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(pMsg.nextMthImptDtyAmt_M, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(pMsg.nextMthInsAmt_M, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(pMsg.nextMthTotStdCostAmt_M, BigDecimal.ZERO);
        //QC#14689
        //ZYPEZDItemValueSetter.setValue(pMsg.rsdlValAmt_D, BigDecimal.ZERO);
        pMsg.rsdlValAmt_D.clear();
        ZYPEZDItemValueSetter.setValue(pMsg.costCcyCd_M, CCY.US_DOLLAR);
        ZYPEZDItemValueSetter.setValue(pMsg.prcCcyCd_M, CCY.US_DOLLAR);
        pMsg.vndCd_M.clear();

        if (ZYPConstant.FLG_ON_Y.equals(pMsg.custOrdEnblFlg_D.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.mdseItemActvDt_D, slsDt);
        }
        if (hasValue(pMsg.thisMthTotStdCostAmt_M) && pMsg.thisMthTotStdCostAmt_M.getValue().compareTo(BigDecimal.ZERO) >= 0) {
            ZYPEZDItemValueSetter.setValue(pMsg.mdseCstUpdDt_D, slsDt);
        }
        if (hasValue(pMsg.origFobAmt_D) && pMsg.origFobAmt_D.getValue().compareTo(BigDecimal.ZERO) >= 0) {
            ZYPEZDItemValueSetter.setValue(pMsg.origFobAmtUpdDt_D, slsDt);
        }
        if (hasValue(pMsg.assetRecovCostAmt_D) && pMsg.assetRecovCostAmt_D.getValue().compareTo(BigDecimal.ZERO) >= 0) {
            ZYPEZDItemValueSetter.setValue(pMsg.assetRecovCostAmtUpdDt_D, slsDt);
        }
        if (!hasValue(pMsg.prcListEquipPrcAmt_P1)) {
            pMsg.prcCatgCd_P1.clear();
        }
        if (!hasValue(pMsg.prcListEquipPrcAmt_P2)) {
            pMsg.prcCatgCd_P2.clear();
        }
        //QC#5580
        if (!hasValue(pMsg.cpoMinOrdQty_M)) {
            ZYPEZDItemValueSetter.setValue(pMsg.cpoMinOrdQty_M, BigDecimal.ONE);
        }
        if (!hasValue(pMsg.cpoMaxOrdQty_M)) {
            ZYPEZDItemValueSetter.setValue(pMsg.cpoMaxOrdQty_M, new BigDecimal("999999999"));
        }
        if (!hasValue(pMsg.cpoIncrOrdQty_M)) {
            ZYPEZDItemValueSetter.setValue(pMsg.cpoIncrOrdQty_M, BigDecimal.ONE);
        }
        
        
        NMZC004002_xxStoreListPMsg storePMsg;
        for (int i = 0; i < pMsg.xxStoreList.getValidCount(); i++) {
            storePMsg = pMsg.xxStoreList.no(i);
            ZYPEZDItemValueSetter.setValue(storePMsg.inInchVol_S, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(storePMsg.qtyPkgApvlStsCd_S, QTY_PKG_APVL_STS_CD_SU);
            ZYPEZDItemValueSetter.setValue(storePMsg.dmnPkgApvlStsCd_S, QTY_PKG_APVL_STS_CD_SU);
            ZYPEZDItemValueSetter.setValue(storePMsg.adminPkgApvlStsCd_S, QTY_PKG_APVL_STS_CD_SU);
            ZYPEZDItemValueSetter.setValue(storePMsg.pkgUomCd_DS, storePMsg.pkgUomCd_S);
            ZYPEZDItemValueSetter.setValue(storePMsg.mdseCd_DS, storePMsg.mdseCd_S);
        }

        NMZC004002_xxSerNumListPMsg serPMsg;
        for (int i = 0; i < pMsg.xxSerNumList.getValidCount(); i++) {
            serPMsg = pMsg.xxSerNumList.no(i);
            if (i == 0) {
                ZYPEZDItemValueSetter.setValue(serPMsg.serNumDefFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(serPMsg.serNumDefFlg, ZYPConstant.FLG_OFF_N);
            }
        }
    }

    private void clearParamByItemType(NMZC004001PMsg param, int idx) {

        NMZC004002PMsg pMsg = param.NMZC004002PMsg.no(idx);
        String mdseItemTpCd = pMsg.mdseItemTpCd_D.getValue();
        String coaMdseTpCd = pMsg.coaMdseTpCd_D.getValue();

        if (MDSE_ITEM_TP.MACHINE.equals(mdseItemTpCd)) {
            pMsg.rtrnReqPrtFlg_D.clear();
            pMsg.rtrnCtrlTpCd_D.clear();
            pMsg.rtrnDsplTpCd_D.clear();
            pMsg.rtrnToVndCd_D.clear();
            pMsg.rtrnToPrntVndCd_D.clear();
            pMsg.rtrnCtrlTpVndRelnPk_D.clear();
            pMsg.rtrnWhCd_D.clear();
            pMsg.imgSplyOemMnfCd_D.clear();
            pMsg.imgSplyOemCd_D.clear();
            pMsg.imgSplyTpCd_D.clear();
            pMsg.imgSplyColorTpCd_D.clear();
            pMsg.imgSplyYieldCnt_D.clear();
            pMsg.imgSplyYieldUomCd_D.clear();
            pMsg.imgSplyYieldTpCd_D.clear();
            pMsg.svcCovTmplTpCd_D.clear();
            pMsg.svcAllocTpCd_D.clear();
            pMsg.prtDropShipDsblFlg_D.clear();
            pMsg.swCatgCd_D.clear();
            pMsg.swTpCd_D.clear();
            pMsg.swVrsnTxt_D.clear();
            pMsg.swLicCtrlTpCd_D.clear();
            pMsg.swLicSeatFromQty_D.clear();
            pMsg.swLicSeatToQty_D.clear();
            pMsg.swLicSeatFixQty_D.clear();
            pMsg.swMaintCtrlTpCd_D.clear();
            pMsg.asrnPntPerLicQty_D.clear();
            pMsg.swMaintTpCd_D.clear();
            pMsg.swMaintTermYr_D.clear();
            pMsg.asrnPntMinQty_D.clear();
            pMsg.asrnPntMaxQty_D.clear();
            pMsg.asrnPntFixPerOrdQty_D.clear();
            pMsg.swMaintTermYr_D.clear();
            pMsg.intntConnSwCtrlFlg_D.clear();
            pMsg.prtItemTpCd_D.clear();
            pMsg.prtYieldOtptQty_D.clear();
            pMsg.prtYieldDaysAot_D.clear();
            pMsg.swProdCatgCd_D.clear();
            pMsg.bdlMaintMdseCd_D.clear();
            pMsg.maintItemTpCd_D.clear();
            pMsg.maintItemTermMthNum_D.clear();
            pMsg.svcCovBaseTpCd_D.clear();
            pMsg.svcTermCondOptTpCd_D.clear();
            pMsg.svcTermCondOptValTxt_D.clear();
            pMsg.imgSplyPvtLbTpCd_D.clear();
            pMsg.prtSrvyReqFlg_D.clear();
            pMsg.maintPopAvalFlg_D.clear();
            pMsg.extMaintPopAvalFlg_D.clear();
            pMsg.easyPackTpCd_D.clear();
            pMsg.areaOfPaperNum_D.clear();
            pMsg.dsIntgMdseTpCd_D.clear();
            pMsg.xxTermList.clear();
        } else if (MDSE_ITEM_TP.ACCESSORY.equals(mdseItemTpCd)) {
            pMsg.rtrnReqPrtFlg_D.clear();
            pMsg.rtrnCtrlTpCd_D.clear();
            pMsg.rtrnDsplTpCd_D.clear();
            pMsg.mainMachFlg_D.clear();
            pMsg.rtrnToVndCd_D.clear();
            pMsg.rtrnToPrntVndCd_D.clear();
            pMsg.rtrnCtrlTpVndRelnPk_D.clear();
            pMsg.rtrnWhCd_D.clear();
            pMsg.backOrdImpctTpCd_D.clear();
            pMsg.reMnfAvalFlg_D.clear();
            pMsg.imgSplyOemMnfCd_D.clear();
            pMsg.imgSplyOemCd_D.clear();
            pMsg.imgSplyTpCd_D.clear();
            pMsg.imgSplyColorTpCd_D.clear();
            pMsg.imgSplyYieldCnt_D.clear();
            pMsg.imgSplyYieldUomCd_D.clear();
            pMsg.imgSplyYieldTpCd_D.clear();
            pMsg.svcCovTmplTpCd_D.clear();
            pMsg.svcAllocTpCd_D.clear();
            pMsg.prtDropShipDsblFlg_D.clear();
            pMsg.swCatgCd_D.clear();
            pMsg.swTpCd_D.clear();
            pMsg.swVrsnTxt_D.clear();
            pMsg.swLicCtrlTpCd_D.clear();
            pMsg.swLicSeatFromQty_D.clear();
            pMsg.swLicSeatToQty_D.clear();
            pMsg.swLicSeatFixQty_D.clear();
            pMsg.swMaintCtrlTpCd_D.clear();
            pMsg.asrnPntPerLicQty_D.clear();
            pMsg.swMaintTpCd_D.clear();
            pMsg.swMaintTermYr_D.clear();
            pMsg.asrnPntMinQty_D.clear();
            pMsg.asrnPntMaxQty_D.clear();
            pMsg.asrnPntFixPerOrdQty_D.clear();
            pMsg.swMaintTermYr_D.clear();
            pMsg.intntConnSwCtrlFlg_D.clear();
            pMsg.svcChrgItemTpCd_D.clear();
            pMsg.prtItemTpCd_D.clear();
            pMsg.prtYieldOtptQty_D.clear();
            pMsg.prtYieldDaysAot_D.clear();
            pMsg.swProdCatgCd_D.clear();
            pMsg.bdlMaintMdseCd_D.clear();
            pMsg.maintItemTpCd_D.clear();
            pMsg.maintItemTermMthNum_D.clear();
            pMsg.svcCovBaseTpCd_D.clear();
            pMsg.svcTermCondOptTpCd_D.clear();
            pMsg.svcTermCondOptValTxt_D.clear();
            pMsg.imgSplyPvtLbTpCd_D.clear();
            pMsg.prtSrvyReqFlg_D.clear();
            pMsg.maintPopAvalFlg_D.clear();
            pMsg.extMaintPopAvalFlg_D.clear();
            pMsg.easyPackTpCd_D.clear();
            pMsg.areaOfPaperNum_D.clear();
            pMsg.dsIntgMdseTpCd_D.clear();
            pMsg.xxTermList.clear();
        } else if (MDSE_ITEM_TP.SUPPLY.equals(mdseItemTpCd)) {
            pMsg.rtrnReqPrtFlg_D.clear();
            pMsg.rtrnCtrlTpCd_D.clear();
            pMsg.rtrnDsplTpCd_D.clear();
            pMsg.rtrnToVndCd_D.clear();
            pMsg.rtrnToPrntVndCd_D.clear();
            pMsg.rtrnCtrlTpVndRelnPk_D.clear();
            pMsg.rtrnWhCd_D.clear();
            pMsg.mainMachFlg_D.clear();
            pMsg.backOrdImpctTpCd_D.clear();
            pMsg.reMnfAvalFlg_D.clear();
            pMsg.svcCovTmplTpCd_D.clear();
            pMsg.svcAllocTpCd_D.clear();
            pMsg.prtDropShipDsblFlg_D.clear();
            pMsg.swCatgCd_D.clear();
            pMsg.swTpCd_D.clear();
            pMsg.swVrsnTxt_D.clear();
            pMsg.swLicCtrlTpCd_D.clear();
            pMsg.swLicSeatFromQty_D.clear();
            pMsg.swLicSeatToQty_D.clear();
            pMsg.swLicSeatFixQty_D.clear();
            pMsg.swMaintCtrlTpCd_D.clear();
            pMsg.asrnPntPerLicQty_D.clear();
            pMsg.swMaintTpCd_D.clear();
            pMsg.swMaintTermYr_D.clear();
            pMsg.asrnPntMinQty_D.clear();
            pMsg.asrnPntMaxQty_D.clear();
            pMsg.asrnPntFixPerOrdQty_D.clear();
            pMsg.swMaintTermYr_D.clear();
            pMsg.intntConnSwCtrlFlg_D.clear();
            pMsg.sowReqFlg_D.clear();
            pMsg.svcChrgItemTpCd_D.clear();
            pMsg.prtItemTpCd_D.clear();
            pMsg.prtYieldOtptQty_D.clear();
            pMsg.prtYieldDaysAot_D.clear();
            pMsg.swProdCatgCd_D.clear();
            pMsg.bdlMaintMdseCd_D.clear();
            pMsg.maintItemTpCd_D.clear();
            pMsg.maintItemTermMthNum_D.clear();
            pMsg.svcCovBaseTpCd_D.clear();
            pMsg.svcTermCondOptTpCd_D.clear();
            pMsg.svcTermCondOptValTxt_D.clear();
            //pMsg.imgSplyPvtLbTpCd_D.clear();
            pMsg.prtSrvyReqFlg_D.clear();
            pMsg.maintPopAvalFlg_D.clear();
            pMsg.extMaintPopAvalFlg_D.clear();
            pMsg.dsIntgMdseTpCd_D.clear();
        } else if (MDSE_ITEM_TP.PARTS.equals(mdseItemTpCd)) {
            pMsg.mainMachFlg_D.clear();
            pMsg.backOrdImpctTpCd_D.clear();
            pMsg.reMnfAvalFlg_D.clear();
            pMsg.imgSplyOemMnfCd_D.clear();
            pMsg.imgSplyOemCd_D.clear();
            pMsg.imgSplyColorTpCd_D.clear();
            pMsg.imgSplyYieldCnt_D.clear();
            pMsg.imgSplyYieldUomCd_D.clear();
            pMsg.imgSplyYieldTpCd_D.clear();
            pMsg.svcCovTmplTpCd_D.clear();
            pMsg.svcAllocTpCd_D.clear();
            pMsg.swCatgCd_D.clear();
            pMsg.swTpCd_D.clear();
            pMsg.swVrsnTxt_D.clear();
            pMsg.swLicCtrlTpCd_D.clear();
            pMsg.swLicSeatFromQty_D.clear();
            pMsg.swLicSeatToQty_D.clear();
            pMsg.swLicSeatFixQty_D.clear();
            pMsg.swMaintCtrlTpCd_D.clear();
            pMsg.asrnPntPerLicQty_D.clear();
            pMsg.swMaintTpCd_D.clear();
            pMsg.swMaintTermYr_D.clear();
            pMsg.asrnPntMinQty_D.clear();
            pMsg.asrnPntMaxQty_D.clear();
            pMsg.asrnPntFixPerOrdQty_D.clear();
            pMsg.swMaintTermYr_D.clear();
            pMsg.intntConnSwCtrlFlg_D.clear();
            pMsg.sowReqFlg_D.clear();
            pMsg.svcChrgItemTpCd_D.clear();
            pMsg.swProdCatgCd_D.clear();
            pMsg.bdlMaintMdseCd_D.clear();
            pMsg.maintItemTpCd_D.clear();
            pMsg.maintItemTermMthNum_D.clear();
            pMsg.svcCovBaseTpCd_D.clear();
            pMsg.svcTermCondOptTpCd_D.clear();
            pMsg.svcTermCondOptValTxt_D.clear();
            pMsg.maintPopAvalFlg_D.clear();
            pMsg.extMaintPopAvalFlg_D.clear();
            pMsg.easyPackTpCd_D.clear();
            pMsg.areaOfPaperNum_D.clear();
            pMsg.dsIntgMdseTpCd_D.clear();
            pMsg.xxTermList.clear();
        } else if (MDSE_ITEM_TP.MAINTENANCE.equals(mdseItemTpCd)) {
            pMsg.rtrnReqPrtFlg_D.clear();
            pMsg.rtrnCtrlTpCd_D.clear();
            pMsg.rtrnDsplTpCd_D.clear();
            pMsg.rtrnToVndCd_D.clear();
            pMsg.rtrnToPrntVndCd_D.clear();
            pMsg.rtrnCtrlTpVndRelnPk_D.clear();
            pMsg.rtrnWhCd_D.clear();
            pMsg.mainMachFlg_D.clear();
            pMsg.backOrdImpctTpCd_D.clear();
            pMsg.reMnfAvalFlg_D.clear();
            pMsg.imgSplyOemMnfCd_D.clear();
            pMsg.imgSplyOemCd_D.clear();
            pMsg.imgSplyColorTpCd_D.clear();
            pMsg.imgSplyYieldCnt_D.clear();
            pMsg.imgSplyYieldUomCd_D.clear();
            pMsg.imgSplyYieldTpCd_D.clear();
            pMsg.prtDropShipDsblFlg_D.clear();
            pMsg.swCatgCd_D.clear();
            pMsg.swTpCd_D.clear();
            pMsg.swVrsnTxt_D.clear();
            pMsg.swLicCtrlTpCd_D.clear();
            pMsg.swLicSeatFromQty_D.clear();
            pMsg.swLicSeatToQty_D.clear();
            pMsg.swLicSeatFixQty_D.clear();
            pMsg.swMaintCtrlTpCd_D.clear();
            pMsg.asrnPntPerLicQty_D.clear();
            pMsg.swMaintTpCd_D.clear();
            pMsg.swMaintTermYr_D.clear();
            pMsg.asrnPntMinQty_D.clear();
            pMsg.asrnPntMaxQty_D.clear();
            pMsg.asrnPntFixPerOrdQty_D.clear();
            pMsg.swMaintTermYr_D.clear();
            pMsg.intntConnSwCtrlFlg_D.clear();
            pMsg.sowReqFlg_D.clear();
            pMsg.svcChrgItemTpCd_D.clear();
            pMsg.prtItemTpCd_D.clear();
            pMsg.prtYieldOtptQty_D.clear();
            pMsg.prtYieldDaysAot_D.clear();
            pMsg.swProdCatgCd_D.clear();
            pMsg.bdlMaintMdseCd_D.clear();
            pMsg.maintItemTpCd_D.clear();
            pMsg.maintItemTermMthNum_D.clear();
            pMsg.svcTermCondOptTpCd_D.clear();
            pMsg.svcTermCondOptValTxt_D.clear();
            pMsg.imgSplyPvtLbTpCd_D.clear();
            pMsg.prtSrvyReqFlg_D.clear();
            pMsg.maintPopAvalFlg_D.clear();
            pMsg.extMaintPopAvalFlg_D.clear();
            pMsg.easyPackTpCd_D.clear();
            pMsg.areaOfPaperNum_D.clear();
            pMsg.dsIntgMdseTpCd_D.clear();
            pMsg.xxTermList.clear();
        } else if (MDSE_ITEM_TP.MAINTENANCE_OVERAGES.equals(mdseItemTpCd)) {
            pMsg.rtrnReqPrtFlg_D.clear();
            pMsg.rtrnCtrlTpCd_D.clear();
            pMsg.rtrnDsplTpCd_D.clear();
            pMsg.rtrnToVndCd_D.clear();
            pMsg.rtrnToPrntVndCd_D.clear();
            pMsg.rtrnCtrlTpVndRelnPk_D.clear();
            pMsg.rtrnWhCd_D.clear();
            pMsg.mainMachFlg_D.clear();
            pMsg.backOrdImpctTpCd_D.clear();
            pMsg.reMnfAvalFlg_D.clear();
            pMsg.imgSplyOemMnfCd_D.clear();
            pMsg.imgSplyOemCd_D.clear();
            pMsg.imgSplyColorTpCd_D.clear();
            pMsg.imgSplyYieldCnt_D.clear();
            pMsg.imgSplyYieldUomCd_D.clear();
            pMsg.imgSplyYieldTpCd_D.clear();
            pMsg.svcCovTmplTpCd_D.clear();
            pMsg.prtDropShipDsblFlg_D.clear();
            pMsg.swCatgCd_D.clear();
            pMsg.swTpCd_D.clear();
            pMsg.swVrsnTxt_D.clear();
            pMsg.swLicCtrlTpCd_D.clear();
            pMsg.swLicSeatFromQty_D.clear();
            pMsg.swLicSeatToQty_D.clear();
            pMsg.swLicSeatFixQty_D.clear();
            pMsg.swMaintCtrlTpCd_D.clear();
            pMsg.asrnPntPerLicQty_D.clear();
            pMsg.swMaintTpCd_D.clear();
            pMsg.swMaintTermYr_D.clear();
            pMsg.asrnPntMinQty_D.clear();
            pMsg.asrnPntMaxQty_D.clear();
            pMsg.asrnPntFixPerOrdQty_D.clear();
            pMsg.swMaintTermYr_D.clear();
            pMsg.intntConnSwCtrlFlg_D.clear();
            pMsg.sowReqFlg_D.clear();
            pMsg.svcChrgItemTpCd_D.clear();
            pMsg.prtItemTpCd_D.clear();
            pMsg.prtYieldOtptQty_D.clear();
            pMsg.prtYieldDaysAot_D.clear();
            pMsg.swProdCatgCd_D.clear();
            pMsg.bdlMaintMdseCd_D.clear();
            pMsg.maintItemTpCd_D.clear();
            pMsg.maintItemTermMthNum_D.clear();
            pMsg.svcCovBaseTpCd_D.clear();
            pMsg.svcTermCondOptTpCd_D.clear();
            pMsg.svcTermCondOptValTxt_D.clear();
            pMsg.imgSplyPvtLbTpCd_D.clear();
            pMsg.prtSrvyReqFlg_D.clear();
            pMsg.maintPopAvalFlg_D.clear();
            pMsg.extMaintPopAvalFlg_D.clear();
            pMsg.easyPackTpCd_D.clear();
            pMsg.areaOfPaperNum_D.clear();
            pMsg.dsIntgMdseTpCd_D.clear();
            pMsg.xxTermList.clear();
        } else if (MDSE_ITEM_TP.MAINTENANCE_CHARGES.equals(mdseItemTpCd)) {
            pMsg.rtrnReqPrtFlg_D.clear();
            pMsg.rtrnCtrlTpCd_D.clear();
            pMsg.rtrnDsplTpCd_D.clear();
            pMsg.rtrnToVndCd_D.clear();
            pMsg.rtrnToPrntVndCd_D.clear();
            pMsg.rtrnCtrlTpVndRelnPk_D.clear();
            pMsg.rtrnWhCd_D.clear();
            pMsg.mainMachFlg_D.clear();
            pMsg.backOrdImpctTpCd_D.clear();
            pMsg.reMnfAvalFlg_D.clear();
            pMsg.imgSplyOemMnfCd_D.clear();
            pMsg.imgSplyOemCd_D.clear();
            pMsg.imgSplyColorTpCd_D.clear();
            pMsg.imgSplyYieldCnt_D.clear();
            pMsg.imgSplyYieldUomCd_D.clear();
            pMsg.imgSplyYieldTpCd_D.clear();
            pMsg.svcCovTmplTpCd_D.clear();
            pMsg.prtDropShipDsblFlg_D.clear();
            pMsg.swCatgCd_D.clear();
            pMsg.swTpCd_D.clear();
            pMsg.swVrsnTxt_D.clear();
            pMsg.swLicCtrlTpCd_D.clear();
            pMsg.swLicSeatFromQty_D.clear();
            pMsg.swLicSeatToQty_D.clear();
            pMsg.swLicSeatFixQty_D.clear();
            pMsg.swMaintCtrlTpCd_D.clear();
            pMsg.asrnPntPerLicQty_D.clear();
            pMsg.swMaintTpCd_D.clear();
            pMsg.swMaintTermYr_D.clear();
            pMsg.asrnPntMinQty_D.clear();
            pMsg.asrnPntMaxQty_D.clear();
            pMsg.asrnPntFixPerOrdQty_D.clear();
            pMsg.swMaintTermYr_D.clear();
            pMsg.intntConnSwCtrlFlg_D.clear();
            pMsg.sowReqFlg_D.clear();
            pMsg.prtItemTpCd_D.clear();
            pMsg.prtYieldOtptQty_D.clear();
            pMsg.prtYieldDaysAot_D.clear();
            pMsg.swProdCatgCd_D.clear();
            pMsg.bdlMaintMdseCd_D.clear();
            pMsg.maintItemTpCd_D.clear();
            pMsg.maintItemTermMthNum_D.clear();
            pMsg.svcCovBaseTpCd_D.clear();
            pMsg.imgSplyPvtLbTpCd_D.clear();
            pMsg.prtSrvyReqFlg_D.clear();
            pMsg.maintPopAvalFlg_D.clear();
            pMsg.extMaintPopAvalFlg_D.clear();
            pMsg.easyPackTpCd_D.clear();
            pMsg.areaOfPaperNum_D.clear();
            pMsg.dsIntgMdseTpCd_D.clear();
        } else if (MDSE_ITEM_TP.SOFTWARE.equals(mdseItemTpCd) || MDSE_ITEM_TP.SOFTWARE_LICENSE.equals(mdseItemTpCd)) {
            pMsg.rtrnReqPrtFlg_D.clear();
            pMsg.rtrnCtrlTpCd_D.clear();
            pMsg.rtrnDsplTpCd_D.clear();
            pMsg.rtrnToVndCd_D.clear();
            pMsg.rtrnToPrntVndCd_D.clear();
            pMsg.rtrnCtrlTpVndRelnPk_D.clear();
            pMsg.rtrnWhCd_D.clear();
            pMsg.mainMachFlg_D.clear();
            pMsg.backOrdImpctTpCd_D.clear();
            pMsg.reMnfAvalFlg_D.clear();
            pMsg.imgSplyOemMnfCd_D.clear();
            pMsg.imgSplyOemCd_D.clear();
            pMsg.imgSplyColorTpCd_D.clear();
            pMsg.imgSplyYieldCnt_D.clear();
            pMsg.imgSplyYieldUomCd_D.clear();
            pMsg.imgSplyYieldTpCd_D.clear();
            pMsg.svcCovTmplTpCd_D.clear();
            pMsg.svcAllocTpCd_D.clear();
            pMsg.prtDropShipDsblFlg_D.clear();
            pMsg.swMaintTpCd_D.clear();
            pMsg.swMaintTermYr_D.clear();
            pMsg.asrnPntMinQty_D.clear();
            pMsg.asrnPntMaxQty_D.clear();
            pMsg.asrnPntFixPerOrdQty_D.clear();
            pMsg.swMaintTermYr_D.clear();
            pMsg.svcChrgItemTpCd_D.clear();
            pMsg.prtItemTpCd_D.clear();
            pMsg.prtYieldOtptQty_D.clear();
            pMsg.prtYieldDaysAot_D.clear();
            pMsg.maintItemTpCd_D.clear();
            pMsg.maintItemTermMthNum_D.clear();
            pMsg.svcCovBaseTpCd_D.clear();
            pMsg.svcTermCondOptTpCd_D.clear();
            pMsg.svcTermCondOptValTxt_D.clear();
            pMsg.imgSplyPvtLbTpCd_D.clear();
            pMsg.prtSrvyReqFlg_D.clear();
            pMsg.easyPackTpCd_D.clear();
            pMsg.areaOfPaperNum_D.clear();
            pMsg.dsIntgMdseTpCd_D.clear();
            pMsg.xxTermList.clear();
        } else if (MDSE_ITEM_TP.SOFTWARE_MAINTENANCE.equals(mdseItemTpCd)) {
            pMsg.rtrnReqPrtFlg_D.clear();
            pMsg.rtrnCtrlTpCd_D.clear();
            pMsg.rtrnDsplTpCd_D.clear();
            pMsg.rtrnToVndCd_D.clear();
            pMsg.rtrnToPrntVndCd_D.clear();
            pMsg.rtrnCtrlTpVndRelnPk_D.clear();
            pMsg.rtrnWhCd_D.clear();
            pMsg.mainMachFlg_D.clear();
            pMsg.backOrdImpctTpCd_D.clear();
            pMsg.reMnfAvalFlg_D.clear();
            pMsg.imgSplyOemMnfCd_D.clear();
            pMsg.imgSplyOemCd_D.clear();
            pMsg.imgSplyColorTpCd_D.clear();
            pMsg.imgSplyYieldCnt_D.clear();
            pMsg.imgSplyYieldUomCd_D.clear();
            pMsg.imgSplyYieldTpCd_D.clear();
            pMsg.svcCovTmplTpCd_D.clear();
            pMsg.svcAllocTpCd_D.clear();
            pMsg.prtDropShipDsblFlg_D.clear();
            pMsg.swTpCd_D.clear();
            pMsg.swLicCtrlTpCd_D.clear();
            pMsg.swLicSeatFromQty_D.clear();
            pMsg.swLicSeatToQty_D.clear();
            pMsg.swLicSeatFixQty_D.clear();
            pMsg.swMaintCtrlTpCd_D.clear();
            pMsg.asrnPntPerLicQty_D.clear();
            pMsg.swMaintTermYr_D.clear();
            pMsg.intntConnSwCtrlFlg_D.clear();
            pMsg.svcChrgItemTpCd_D.clear();
            pMsg.prtItemTpCd_D.clear();
            pMsg.prtYieldOtptQty_D.clear();
            pMsg.prtYieldDaysAot_D.clear();
            pMsg.bdlMaintMdseCd_D.clear();
            pMsg.svcCovBaseTpCd_D.clear();
            pMsg.svcTermCondOptTpCd_D.clear();
            pMsg.svcTermCondOptValTxt_D.clear();
            pMsg.imgSplyPvtLbTpCd_D.clear();
            pMsg.prtSrvyReqFlg_D.clear();
            pMsg.maintPopAvalFlg_D.clear();
            pMsg.extMaintPopAvalFlg_D.clear();
            pMsg.easyPackTpCd_D.clear();
            pMsg.areaOfPaperNum_D.clear();
            pMsg.dsIntgMdseTpCd_D.clear();
            pMsg.xxTermList.clear();
        } else if (MDSE_ITEM_TP.SET.equals(mdseItemTpCd) || MDSE_ITEM_TP.PROFESSIONAL_SERVICES.equals(mdseItemTpCd)) {
            pMsg.rtrnReqPrtFlg_D.clear();
            pMsg.rtrnCtrlTpCd_D.clear();
            pMsg.rtrnDsplTpCd_D.clear();
            pMsg.rtrnToVndCd_D.clear();
            pMsg.rtrnToPrntVndCd_D.clear();
            pMsg.rtrnCtrlTpVndRelnPk_D.clear();
            pMsg.rtrnWhCd_D.clear();
            pMsg.mainMachFlg_D.clear();
            pMsg.backOrdImpctTpCd_D.clear();
            pMsg.reMnfAvalFlg_D.clear();
            pMsg.imgSplyOemMnfCd_D.clear();
            pMsg.imgSplyOemCd_D.clear();
            pMsg.imgSplyColorTpCd_D.clear();
            pMsg.imgSplyYieldCnt_D.clear();
            pMsg.imgSplyYieldUomCd_D.clear();
            pMsg.imgSplyYieldTpCd_D.clear();
            pMsg.svcCovTmplTpCd_D.clear();
            pMsg.svcAllocTpCd_D.clear();
            pMsg.prtDropShipDsblFlg_D.clear();
            pMsg.swCatgCd_D.clear();
            pMsg.swTpCd_D.clear();
            pMsg.swVrsnTxt_D.clear();
            pMsg.swLicCtrlTpCd_D.clear();
            pMsg.swLicSeatFromQty_D.clear();
            pMsg.swLicSeatToQty_D.clear();
            pMsg.swLicSeatFixQty_D.clear();
            pMsg.swMaintCtrlTpCd_D.clear();
            pMsg.asrnPntPerLicQty_D.clear();
            pMsg.swMaintTpCd_D.clear();
            pMsg.swMaintTermYr_D.clear();
            pMsg.asrnPntMinQty_D.clear();
            pMsg.asrnPntMaxQty_D.clear();
            pMsg.asrnPntFixPerOrdQty_D.clear();
            pMsg.swMaintTermYr_D.clear();
            pMsg.intntConnSwCtrlFlg_D.clear();
            pMsg.svcChrgItemTpCd_D.clear();
            pMsg.prtItemTpCd_D.clear();
            pMsg.prtYieldOtptQty_D.clear();
            pMsg.prtYieldDaysAot_D.clear();
            pMsg.swProdCatgCd_D.clear();
            pMsg.bdlMaintMdseCd_D.clear();
            pMsg.maintItemTermMthNum_D.clear();
            pMsg.svcCovBaseTpCd_D.clear();
            pMsg.svcTermCondOptTpCd_D.clear();
            pMsg.svcTermCondOptValTxt_D.clear();
            pMsg.imgSplyPvtLbTpCd_D.clear();
            pMsg.prtSrvyReqFlg_D.clear();
            pMsg.maintPopAvalFlg_D.clear();
            pMsg.extMaintPopAvalFlg_D.clear();
            pMsg.easyPackTpCd_D.clear();
            pMsg.areaOfPaperNum_D.clear();
            pMsg.dsIntgMdseTpCd_D.clear();
            pMsg.xxTermList.clear();
        } else if (MDSE_ITEM_TP.KIT.equals(mdseItemTpCd)) {
            if (COA_MDSE_TP.MACHINE.equals(coaMdseTpCd)) {
                pMsg.rtrnReqPrtFlg_D.clear();
                pMsg.rtrnCtrlTpCd_D.clear();
                pMsg.rtrnDsplTpCd_D.clear();
                pMsg.rtrnToVndCd_D.clear();
                pMsg.rtrnToPrntVndCd_D.clear();
                pMsg.rtrnCtrlTpVndRelnPk_D.clear();
                pMsg.rtrnWhCd_D.clear();
                pMsg.imgSplyOemMnfCd_D.clear();
                pMsg.imgSplyOemCd_D.clear();
                pMsg.imgSplyTpCd_D.clear();
                pMsg.imgSplyColorTpCd_D.clear();
                pMsg.imgSplyYieldCnt_D.clear();
                pMsg.imgSplyYieldUomCd_D.clear();
                pMsg.imgSplyYieldTpCd_D.clear();
                pMsg.svcCovTmplTpCd_D.clear();
                pMsg.svcAllocTpCd_D.clear();
                pMsg.prtDropShipDsblFlg_D.clear();
                pMsg.swCatgCd_D.clear();
                pMsg.swTpCd_D.clear();
                pMsg.swVrsnTxt_D.clear();
                pMsg.swLicCtrlTpCd_D.clear();
                pMsg.swLicSeatFromQty_D.clear();
                pMsg.swLicSeatToQty_D.clear();
                pMsg.swLicSeatFixQty_D.clear();
                pMsg.swMaintCtrlTpCd_D.clear();
                pMsg.asrnPntPerLicQty_D.clear();
                pMsg.swMaintTpCd_D.clear();
                pMsg.swMaintTermYr_D.clear();
                pMsg.asrnPntMinQty_D.clear();
                pMsg.asrnPntMaxQty_D.clear();
                pMsg.asrnPntFixPerOrdQty_D.clear();
                pMsg.swMaintTermYr_D.clear();
                pMsg.intntConnSwCtrlFlg_D.clear();
                pMsg.prtItemTpCd_D.clear();
                pMsg.prtYieldOtptQty_D.clear();
                pMsg.prtYieldDaysAot_D.clear();
                pMsg.swProdCatgCd_D.clear();
                pMsg.bdlMaintMdseCd_D.clear();
                pMsg.maintItemTpCd_D.clear();
                pMsg.maintItemTermMthNum_D.clear();
                pMsg.svcCovBaseTpCd_D.clear();
                pMsg.svcTermCondOptTpCd_D.clear();
                pMsg.svcTermCondOptValTxt_D.clear();
                pMsg.imgSplyPvtLbTpCd_D.clear();
                pMsg.prtSrvyReqFlg_D.clear();
                pMsg.maintPopAvalFlg_D.clear();
                pMsg.extMaintPopAvalFlg_D.clear();
                pMsg.easyPackTpCd_D.clear();
                pMsg.areaOfPaperNum_D.clear();
                pMsg.dsIntgMdseTpCd_D.clear();
                pMsg.xxTermList.clear();
            } else if (COA_MDSE_TP.ACCESSORY.equals(coaMdseTpCd)) {
                pMsg.rtrnReqPrtFlg_D.clear();
                pMsg.rtrnCtrlTpCd_D.clear();
                pMsg.rtrnDsplTpCd_D.clear();
                pMsg.mainMachFlg_D.clear();
                pMsg.rtrnToVndCd_D.clear();
                pMsg.rtrnToPrntVndCd_D.clear();
                pMsg.rtrnCtrlTpVndRelnPk_D.clear();
                pMsg.rtrnWhCd_D.clear();
                pMsg.backOrdImpctTpCd_D.clear();
                pMsg.reMnfAvalFlg_D.clear();
                pMsg.imgSplyOemMnfCd_D.clear();
                pMsg.imgSplyOemCd_D.clear();
                pMsg.imgSplyTpCd_D.clear();
                pMsg.imgSplyColorTpCd_D.clear();
                pMsg.imgSplyYieldCnt_D.clear();
                pMsg.imgSplyYieldUomCd_D.clear();
                pMsg.imgSplyYieldTpCd_D.clear();
                pMsg.svcCovTmplTpCd_D.clear();
                pMsg.svcAllocTpCd_D.clear();
                pMsg.prtDropShipDsblFlg_D.clear();
                pMsg.swCatgCd_D.clear();
                pMsg.swTpCd_D.clear();
                pMsg.swVrsnTxt_D.clear();
                pMsg.swLicCtrlTpCd_D.clear();
                pMsg.swLicSeatFromQty_D.clear();
                pMsg.swLicSeatToQty_D.clear();
                pMsg.swLicSeatFixQty_D.clear();
                pMsg.swMaintCtrlTpCd_D.clear();
                pMsg.asrnPntPerLicQty_D.clear();
                pMsg.swMaintTpCd_D.clear();
                pMsg.swMaintTermYr_D.clear();
                pMsg.asrnPntMinQty_D.clear();
                pMsg.asrnPntMaxQty_D.clear();
                pMsg.asrnPntFixPerOrdQty_D.clear();
                pMsg.swMaintTermYr_D.clear();
                pMsg.intntConnSwCtrlFlg_D.clear();
                pMsg.svcChrgItemTpCd_D.clear();
                pMsg.prtItemTpCd_D.clear();
                pMsg.prtYieldOtptQty_D.clear();
                pMsg.prtYieldDaysAot_D.clear();
                pMsg.swProdCatgCd_D.clear();
                pMsg.bdlMaintMdseCd_D.clear();
                pMsg.maintItemTpCd_D.clear();
                pMsg.maintItemTermMthNum_D.clear();
                pMsg.svcCovBaseTpCd_D.clear();
                pMsg.svcTermCondOptTpCd_D.clear();
                pMsg.svcTermCondOptValTxt_D.clear();
                pMsg.imgSplyPvtLbTpCd_D.clear();
                pMsg.prtSrvyReqFlg_D.clear();
                pMsg.maintPopAvalFlg_D.clear();
                pMsg.extMaintPopAvalFlg_D.clear();
                pMsg.easyPackTpCd_D.clear();
                pMsg.areaOfPaperNum_D.clear();
                pMsg.dsIntgMdseTpCd_D.clear();
                pMsg.xxTermList.clear();
            } else if (COA_MDSE_TP.SUPPLY.equals(coaMdseTpCd)) {
                pMsg.rtrnReqPrtFlg_D.clear();
                pMsg.rtrnCtrlTpCd_D.clear();
                pMsg.rtrnDsplTpCd_D.clear();
                pMsg.rtrnToVndCd_D.clear();
                pMsg.rtrnToPrntVndCd_D.clear();
                pMsg.rtrnCtrlTpVndRelnPk_D.clear();
                pMsg.rtrnWhCd_D.clear();
                pMsg.mainMachFlg_D.clear();
                pMsg.backOrdImpctTpCd_D.clear();
                pMsg.reMnfAvalFlg_D.clear();
                pMsg.svcCovTmplTpCd_D.clear();
                pMsg.svcAllocTpCd_D.clear();
                pMsg.prtDropShipDsblFlg_D.clear();
                pMsg.swCatgCd_D.clear();
                pMsg.swTpCd_D.clear();
                pMsg.swVrsnTxt_D.clear();
                pMsg.swLicCtrlTpCd_D.clear();
                pMsg.swLicSeatFromQty_D.clear();
                pMsg.swLicSeatToQty_D.clear();
                pMsg.swLicSeatFixQty_D.clear();
                pMsg.swMaintCtrlTpCd_D.clear();
                pMsg.asrnPntPerLicQty_D.clear();
                pMsg.swMaintTpCd_D.clear();
                pMsg.swMaintTermYr_D.clear();
                pMsg.asrnPntMinQty_D.clear();
                pMsg.asrnPntMaxQty_D.clear();
                pMsg.asrnPntFixPerOrdQty_D.clear();
                pMsg.swMaintTermYr_D.clear();
                pMsg.intntConnSwCtrlFlg_D.clear();
                pMsg.sowReqFlg_D.clear();
                pMsg.svcChrgItemTpCd_D.clear();
                pMsg.prtItemTpCd_D.clear();
                pMsg.prtYieldOtptQty_D.clear();
                pMsg.prtYieldDaysAot_D.clear();
                pMsg.swProdCatgCd_D.clear();
                pMsg.bdlMaintMdseCd_D.clear();
                pMsg.maintItemTpCd_D.clear();
                pMsg.maintItemTermMthNum_D.clear();
                pMsg.svcCovBaseTpCd_D.clear();
                pMsg.svcTermCondOptTpCd_D.clear();
                pMsg.svcTermCondOptValTxt_D.clear();
                //pMsg.imgSplyPvtLbTpCd_D.clear();
                pMsg.prtSrvyReqFlg_D.clear();
                pMsg.maintPopAvalFlg_D.clear();
                pMsg.extMaintPopAvalFlg_D.clear();
                pMsg.dsIntgMdseTpCd_D.clear();
            } else if (COA_MDSE_TP.PARTS.equals(coaMdseTpCd)) {
                pMsg.mainMachFlg_D.clear();
                pMsg.backOrdImpctTpCd_D.clear();
                pMsg.reMnfAvalFlg_D.clear();
                pMsg.imgSplyOemMnfCd_D.clear();
                pMsg.imgSplyOemCd_D.clear();
                pMsg.imgSplyColorTpCd_D.clear();
                pMsg.imgSplyYieldCnt_D.clear();
                pMsg.imgSplyYieldUomCd_D.clear();
                pMsg.imgSplyYieldTpCd_D.clear();
                pMsg.svcCovTmplTpCd_D.clear();
                pMsg.svcAllocTpCd_D.clear();
                pMsg.swCatgCd_D.clear();
                pMsg.swTpCd_D.clear();
                pMsg.swVrsnTxt_D.clear();
                pMsg.swLicCtrlTpCd_D.clear();
                pMsg.swLicSeatFromQty_D.clear();
                pMsg.swLicSeatToQty_D.clear();
                pMsg.swLicSeatFixQty_D.clear();
                pMsg.swMaintCtrlTpCd_D.clear();
                pMsg.asrnPntPerLicQty_D.clear();
                pMsg.swMaintTpCd_D.clear();
                pMsg.swMaintTermYr_D.clear();
                pMsg.asrnPntMinQty_D.clear();
                pMsg.asrnPntMaxQty_D.clear();
                pMsg.asrnPntFixPerOrdQty_D.clear();
                pMsg.swMaintTermYr_D.clear();
                pMsg.intntConnSwCtrlFlg_D.clear();
                pMsg.sowReqFlg_D.clear();
                pMsg.svcChrgItemTpCd_D.clear();
                pMsg.swProdCatgCd_D.clear();
                pMsg.bdlMaintMdseCd_D.clear();
                pMsg.maintItemTpCd_D.clear();
                pMsg.maintItemTermMthNum_D.clear();
                pMsg.svcCovBaseTpCd_D.clear();
                pMsg.svcTermCondOptTpCd_D.clear();
                pMsg.svcTermCondOptValTxt_D.clear();
                pMsg.maintPopAvalFlg_D.clear();
                pMsg.extMaintPopAvalFlg_D.clear();
                pMsg.easyPackTpCd_D.clear();
                pMsg.areaOfPaperNum_D.clear();
                pMsg.dsIntgMdseTpCd_D.clear();
                pMsg.xxTermList.clear();
            }
        } else if (MDSE_ITEM_TP.INTANGIBLE.equals(mdseItemTpCd)) {
            pMsg.rtrnReqPrtFlg_D.clear();
            pMsg.rtrnCtrlTpCd_D.clear();
            pMsg.rtrnDsplTpCd_D.clear();
            pMsg.rtrnToVndCd_D.clear();
            pMsg.rtrnToPrntVndCd_D.clear();
            pMsg.rtrnCtrlTpVndRelnPk_D.clear();
            pMsg.rtrnWhCd_D.clear();
            pMsg.mainMachFlg_D.clear();
            pMsg.backOrdImpctTpCd_D.clear();
            pMsg.reMnfAvalFlg_D.clear();
            pMsg.imgSplyOemMnfCd_D.clear();
            pMsg.imgSplyOemCd_D.clear();
            pMsg.imgSplyColorTpCd_D.clear();
            pMsg.imgSplyYieldCnt_D.clear();
            pMsg.imgSplyYieldUomCd_D.clear();
            pMsg.imgSplyYieldTpCd_D.clear();
            pMsg.svcCovTmplTpCd_D.clear();
            pMsg.svcAllocTpCd_D.clear();
            pMsg.prtDropShipDsblFlg_D.clear();
            pMsg.swCatgCd_D.clear();
            pMsg.swTpCd_D.clear();
            pMsg.swVrsnTxt_D.clear();
            pMsg.swLicCtrlTpCd_D.clear();
            pMsg.swLicSeatFromQty_D.clear();
            pMsg.swLicSeatToQty_D.clear();
            pMsg.swLicSeatFixQty_D.clear();
            pMsg.swMaintCtrlTpCd_D.clear();
            pMsg.asrnPntPerLicQty_D.clear();
            pMsg.swMaintTpCd_D.clear();
            pMsg.swMaintTermYr_D.clear();
            pMsg.asrnPntMinQty_D.clear();
            pMsg.asrnPntMaxQty_D.clear();
            pMsg.asrnPntFixPerOrdQty_D.clear();
            pMsg.swMaintTermYr_D.clear();
            pMsg.intntConnSwCtrlFlg_D.clear();
            pMsg.svcChrgItemTpCd_D.clear();
            pMsg.prtItemTpCd_D.clear();
            pMsg.prtYieldOtptQty_D.clear();
            pMsg.prtYieldDaysAot_D.clear();
            pMsg.swProdCatgCd_D.clear();
            pMsg.bdlMaintMdseCd_D.clear();
            pMsg.maintItemTermMthNum_D.clear();
            pMsg.svcCovBaseTpCd_D.clear();
            pMsg.svcTermCondOptTpCd_D.clear();
            pMsg.svcTermCondOptValTxt_D.clear();
            pMsg.imgSplyPvtLbTpCd_D.clear();
            pMsg.prtSrvyReqFlg_D.clear();
            pMsg.maintPopAvalFlg_D.clear();
            pMsg.extMaintPopAvalFlg_D.clear();
            pMsg.easyPackTpCd_D.clear();
            pMsg.areaOfPaperNum_D.clear();
            pMsg.sowReqFlg_D.clear();
            pMsg.xxTermList.clear();
        } else if (MDSE_ITEM_TP.PART_CONSUMABLES.equals(mdseItemTpCd)) {
            pMsg.mainMachFlg_D.clear();
            pMsg.backOrdImpctTpCd_D.clear();
            pMsg.reMnfAvalFlg_D.clear();
            pMsg.imgSplyOemMnfCd_D.clear();
            pMsg.imgSplyOemCd_D.clear();
            pMsg.imgSplyColorTpCd_D.clear();
            pMsg.imgSplyYieldCnt_D.clear();
            pMsg.imgSplyYieldUomCd_D.clear();
            pMsg.imgSplyYieldTpCd_D.clear();
            pMsg.svcCovTmplTpCd_D.clear();
            pMsg.svcAllocTpCd_D.clear();
            pMsg.swCatgCd_D.clear();
            pMsg.swTpCd_D.clear();
            pMsg.swVrsnTxt_D.clear();
            pMsg.swLicCtrlTpCd_D.clear();
            pMsg.swLicSeatFromQty_D.clear();
            pMsg.swLicSeatToQty_D.clear();
            pMsg.swLicSeatFixQty_D.clear();
            pMsg.swMaintCtrlTpCd_D.clear();
            pMsg.asrnPntPerLicQty_D.clear();
            pMsg.swMaintTpCd_D.clear();
            pMsg.swMaintTermYr_D.clear();
            pMsg.asrnPntMinQty_D.clear();
            pMsg.asrnPntMaxQty_D.clear();
            pMsg.asrnPntFixPerOrdQty_D.clear();
            pMsg.swMaintTermYr_D.clear();
            pMsg.intntConnSwCtrlFlg_D.clear();
            pMsg.sowReqFlg_D.clear();
            pMsg.svcChrgItemTpCd_D.clear();
            pMsg.swProdCatgCd_D.clear();
            pMsg.bdlMaintMdseCd_D.clear();
            pMsg.maintItemTpCd_D.clear();
            pMsg.maintItemTermMthNum_D.clear();
            pMsg.svcCovBaseTpCd_D.clear();
            pMsg.svcTermCondOptTpCd_D.clear();
            pMsg.svcTermCondOptValTxt_D.clear();
            pMsg.maintPopAvalFlg_D.clear();
            pMsg.extMaintPopAvalFlg_D.clear();
            pMsg.easyPackTpCd_D.clear();
            pMsg.areaOfPaperNum_D.clear();
            pMsg.dsIntgMdseTpCd_D.clear();
            pMsg.xxTermList.clear();
        }
    }

    private void checkItemInput(NMZC004001PMsg param, int idx) {

        checkFlag(param, idx);
        if (!commonLogic.isErrMax(idx, ERR_MAX_CNT)) {
            return;
        }

        checkMandatory(param, idx);
        if (!commonLogic.isErrMax(idx, 1)) {
            return;
        }

        checkCodeTable(param, idx);
        if (!commonLogic.isErrMax(idx, ERR_MAX_CNT)) {
            return;
        }

        checkMaster(param, idx);
        if (!commonLogic.isErrMax(idx, ERR_MAX_CNT)) {
            return;
        }

        checkIntegrity(param, idx);
        if (!commonLogic.isErrMax(idx, ERR_MAX_CNT)) {
            return;
        }

        checkBomList(param, idx);
        if (!commonLogic.isErrMax(idx, ERR_MAX_CNT)) {
            return;
        }

        checkStoreList(param, idx);
        if (!commonLogic.isErrMax(idx, ERR_MAX_CNT)) {
            return;
        }

        checkSerNumList(param, idx);
        if (!commonLogic.isErrMax(idx, ERR_MAX_CNT)) {
            return;
        }

        checkSupdList(param, idx);
        if (!commonLogic.isErrMax(idx, ERR_MAX_CNT)) {
            return;
        }

        checkRelList(param, idx);
        if (!commonLogic.isErrMax(idx, ERR_MAX_CNT)) {
            return;
        }

        checkCustXrefList(param, idx);
        if (!commonLogic.isErrMax(idx, ERR_MAX_CNT)) {
            return;
        }

        checkAslList(param, idx);
        // QC#4342
        if (!commonLogic.isErrMax(idx, ERR_MAX_CNT)) {
            return;
        }

        checkTermCondOptList(param, idx);
        if (!commonLogic.isErrMax(idx, ERR_MAX_CNT)) {
            return;
        }
    }

    private void checkMandatory(NMZC004001PMsg param, int idx) {

        NMZC004002PMsg pMsg = param.NMZC004002PMsg.no(idx);

        //commonLogic.hasMandatory(pMsg.mdseNm_M, param, idx, new String[] {MDSE_NM });
        //commonLogic.hasMandatory(pMsg.mdseFmlNm_M, param, idx, new String[] {MDSE_FML_NM });
        //QC#5580
        commonLogic.hasMandatory(pMsg.mdseDescShortTxt_D, param, idx, new String[] {MDSE_DESC_SHORT_TXT });
        //commonLogic.hasMandatory(pMsg.mdseDescLongTxt_D, param, idx, new String[] {MDSE_DESC_LONG_TXT });
        commonLogic.hasMandatory(pMsg.mnfItemCd_D, param, idx, new String[] {MNF_ITEM_CD });
        commonLogic.hasMandatory(pMsg.coaProdCd_M, param, idx, new String[] {COA_PROD_CD });
        commonLogic.hasMandatory(pMsg.prchGrpCd_D, param, idx, new String[] {PRCH_GRP_CD }); //QC#13868
        commonLogic.hasMandatory(pMsg.invtyCtrlFlg_M, param, idx, new String[] {INVTY_CTRL_FLG });
        commonLogic.hasMandatory(pMsg.thisMthTotStdCostAmt_M, param, idx, new String[] {THIS_MTH_TOT_STD_COST_AMT });
        commonLogic.hasMandatory(pMsg.zerothProdCtrlCd_M, param, idx, new String[] {PROD_CTRL_CD });
        commonLogic.hasMandatory(pMsg.zerothProdCtrlNm_M, param, idx, new String[] {PROD_CTRL_NM });
        commonLogic.hasMandatory(pMsg.firstProdCtrlCd_M, param, idx, new String[] {PROD_CTRL_CD });
        commonLogic.hasMandatory(pMsg.firstProdCtrlNm_M, param, idx, new String[] {PROD_CTRL_NM });
        commonLogic.hasMandatory(pMsg.scdProdCtrlCd_M, param, idx, new String[] {PROD_CTRL_CD });
        commonLogic.hasMandatory(pMsg.scdProdCtrlNm_M, param, idx, new String[] {PROD_CTRL_NM });
        commonLogic.hasMandatory(pMsg.thirdProdCtrlCd_M, param, idx, new String[] {PROD_CTRL_CD });
        commonLogic.hasMandatory(pMsg.thirdProdCtrlNm_M, param, idx, new String[] {PROD_CTRL_NM });
        commonLogic.hasMandatory(pMsg.frthProdCtrlCd_M, param, idx, new String[] {PROD_CTRL_CD });
        commonLogic.hasMandatory(pMsg.frthProdCtrlNm_M, param, idx, new String[] {PROD_CTRL_NM });
        commonLogic.hasMandatory(pMsg.rcvSerTakeFlg_M, param, idx, new String[] {RCV_SER_TAKE_FLG });
        commonLogic.hasMandatory(pMsg.shpgSerTakeFlg_M, param, idx, new String[] {SHPG_SER_TAKE_FLG });
        commonLogic.hasMandatory(pMsg.mdseItemTpCd_D, param, idx, new String[] {MDSE_ITEM_TP_CD });
        //QC#5580
        commonLogic.hasMandatory(pMsg.cpoMinOrdQty_M, param, idx, new String[] {CPO_MIN_ORD_QTY });
        commonLogic.hasMandatory(pMsg.cpoMaxOrdQty_M, param, idx, new String[] {CPO_MAX_ORD_QTY });
        commonLogic.hasMandatory(pMsg.cpoIncrOrdQty_M, param, idx, new String[] {CPO_INCR_ORD_QTY });

        if (!commonLogic.hasMandatory(pMsg.revCoaAcctCd_D, param, idx, new String[] {REV_COA_ACCT_CD })) {
            return;
        }
        if (!commonLogic.hasMandatory(pMsg.cogsCoaAcctCd_D, param, idx, new String[] {COGS_COA_ACCT_CD })) {
            return;
        }
        if (!commonLogic.hasMandatory(pMsg.expCoaAcctCd_D, param, idx, new String[] {EXP_COA_ACCT_CD })) {
            return;
        }
        //V1.6
        //if (!commonLogic.hasMandatory(pMsg.acrlCoaAcctCd_D, param, idx, new String[] {ACRL_COA_ACCT_CD })) {
        //    return;
        //}
        //if (!commonLogic.hasMandatory(pMsg.wtyDaysAot_D, param, idx, new String[] {WTY_DAYS_AOT })) {
        //    return;
        //}
        if (!commonLogic.hasMandatory(pMsg.mtrMachFlg_D, param, idx, new String[] {MTR_MACH_FLG })) {
            return;
        }
        if (!commonLogic.hasMandatory(pMsg.instlBaseCtrlFlg_D, param, idx, new String[] {INSTL_BASE_CTRL_FLG })) {
            return;
        }
        if (!commonLogic.hasMandatory(pMsg.lineBizTpCd_D, param, idx, new String[] {LINE_BIZ_TP_CD })) {
            return;
        }
        if (!commonLogic.hasMandatory(pMsg.svcCallEnblFlg_D, param, idx, new String[] {SVC_CALL_ENBL_FLG })) {
            return;
        }
        if (!commonLogic.hasMandatory(pMsg.coaMdseTpCd_D, param, idx, new String[] {COA_MDSE_TP_CD })) {
            return;
        }

        if (pMsg.xxStoreList.getValidCount() == 0) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0163E, new String[] {XX_STORE_LIST })) {
                return;
            }
        }

        if (hasValue(pMsg.mdseItemTpCd_D)) {
            if (MDSE_ITEM_TP.PARTS.equals(pMsg.mdseItemTpCd_D.getValue())
            		|| MDSE_ITEM_TP.PART_CONSUMABLES.equals(pMsg.mdseItemTpCd_D.getValue())
            		|| (MDSE_ITEM_TP.KIT.equals(pMsg.mdseItemTpCd_D.getValue())
            				&& COA_MDSE_TP.PARTS.equals(pMsg.coaMdseTpCd_D.getValue()))) {
                if (!commonLogic.hasMandatory(pMsg.prtSrvyReqFlg_D, param, idx, new String[] {PRT_SRVY_REQ_FLG })) {
                    return;
                }
            } else if (MDSE_ITEM_TP.SOFTWARE.equals(pMsg.mdseItemTpCd_D.getValue()) || MDSE_ITEM_TP.SOFTWARE_LICENSE.equals(pMsg.mdseItemTpCd_D.getValue())) {
                if (!commonLogic.hasMandatory(pMsg.maintPopAvalFlg_D, param, idx, new String[] {MAINT_POP_AVAL_FLG })) {
                    return;
                }
                if (!commonLogic.hasMandatory(pMsg.extMaintPopAvalFlg_D, param, idx, new String[] {EXT_MAINT_POP_AVAL_FLG })) {
                    return;
                }
            }
        }

        //QC#14441
        if (MDSE_ITEM_TP.SUPPLY.equals(pMsg.mdseItemTpCd_D.getValue()) && hasValue(pMsg.easyPackTpCd_D)) {
        	if (!hasValue(pMsg.areaOfPaperNum_D) || BigDecimal.ZERO.compareTo(pMsg.areaOfPaperNum_D.getValue()) >= 0) {
        		if (!commonLogic.setErrorMessage(param, idx, "NMZM0334E", null)) {
        			return;
        		}
        	}
        }
        if (hasValue(pMsg.svcWtyTpCd_D)) {
            if (!commonLogic.hasMandatory(pMsg.wtyDaysAot_D, param, idx, new String[] {WTY_DAYS_AOT })) {
                return;
            }
        }

        NMZC004002_xxBomListPMsg bomPMsg;
        for (int i = 0; i < pMsg.xxBomList.getValidCount(); i++) {
            bomPMsg = pMsg.xxBomList.no(i);
            if (!commonLogic.hasMandatory(bomPMsg.prntMdseCd_C, param, idx, new String[] {PRNT_MDSE_CD_C })) {
                return;
            }
            if (!commonLogic.hasMandatory(bomPMsg.mdseCmpsnTpCd_C, param, idx, new String[] {MDSE_CMPSN_TP_CD_C })) {
                return;
            }
            if (!commonLogic.hasMandatory(bomPMsg.childMdseQty_C, param, idx, new String[] {CHILD_MDSE_QTY_C })) {
                return;
            }
            if (!commonLogic.hasMandatory(bomPMsg.effFromDt_C, param, idx, new String[] {EFF_FROM_DT_C })) {
                return;
            }
            if (!commonLogic.hasMandatory(bomPMsg.pkgUomCd_DC, param, idx, new String[] {PKG_UOM_CD_DC })) {
                return;
            }
        }

        NMZC004002_xxStoreListPMsg storePMsg;
        for (int i = 0; i < pMsg.xxStoreList.getValidCount(); i++) {
            storePMsg = pMsg.xxStoreList.no(i);
            if (!commonLogic.hasMandatory(storePMsg.pkgUomCd_S, param, idx, new String[] {PKG_UOM_CD_S })) {
                return;
            }
            if (!commonLogic.hasMandatory(storePMsg.mdseCd_S, param, idx, new String[] {MDSE_CD_S })) {
                return;
            }
            if (!commonLogic.hasMandatory(storePMsg.inEachQty_S, param, idx, new String[] {IN_EACH_QTY_S })) {
                return;
            }
            //QC#16628
            //Should set this by API
            //if (!commonLogic.hasMandatory(storePMsg.primPkgUomFlg_DS, param, idx, new String[] {PRIM_PKG_UOM_FLG_DS })) {
            //    return;
            //}
        }

        NMZC004002_xxSerNumListPMsg serPMsg;
        for (int i = 0; i < pMsg.xxSerNumList.getValidCount(); i++) {
            serPMsg = pMsg.xxSerNumList.no(i);
            if (!commonLogic.hasMandatory(serPMsg.mdseCd, param, idx, new String[] {MDSE_CD_SN })) {
                return;
            }
            if (!commonLogic.hasMandatory(serPMsg.fromSerNum, param, idx, new String[] {FROM_SER_NUM_SN })) {
                return;
            }
            if (!commonLogic.hasMandatory(serPMsg.thruSerNum, param, idx, new String[] {THRU_SER_NUM_SN })) {
                return;
            }
        }

        NMZC004002_xxSupdListPMsg supdPMsg;
        for (int i = 0; i < pMsg.xxSupdList.getValidCount(); i++) {
            supdPMsg = pMsg.xxSupdList.no(i);
            if (!commonLogic.hasMandatory(supdPMsg.mdseCd, param, idx, new String[] {MDSE_CD_SD })) {
                return;
            }
            if (!commonLogic.hasMandatory(supdPMsg.supdToMdseCd, param, idx, new String[] {SUPD_TO_MDSE_CD_SR })) {
                return;
            }
        }

        NMZC004002_xxRelListPMsg relPMsg;
        for (int i = 0; i < pMsg.xxRelList.getValidCount(); i++) {
            relPMsg = pMsg.xxRelList.no(i);
            if (!commonLogic.hasMandatory(relPMsg.mdseCd, param, idx, new String[] {MDSE_CD_F })) {
                return;
            }
            if (!commonLogic.hasMandatory(relPMsg.relnMdseCd, param, idx, new String[] {RELN_MDSE_CD_F })) {
                return;
            }
            if (!commonLogic.hasMandatory(relPMsg.mdseItemRelnTpCd, param, idx, new String[] {MDSE_ITEM_RELN_TP_CD_F })) {
                return;
            }
        }

        NMZC004002_xxCustXrefListPMsg custPMsg;
        for (int i = 0; i < pMsg.xxCustXrefList.getValidCount(); i++) {
            custPMsg = pMsg.xxCustXrefList.no(i);
            if (!commonLogic.hasMandatory(custPMsg.mdseCd, param, idx, new String[] {MDSE_CD_X })) {
                return;
            }
            if (!commonLogic.hasMandatory(custPMsg.custMdseCd, param, idx, new String[] {CUST_MDSE_CD_X })) {
                return;
            }
            if (!commonLogic.hasMandatory(custPMsg.dsAcctNum, param, idx, new String[] {DS_ACCT_NUM_X })) {
                return;
            }
            if (!commonLogic.hasMandatory(custPMsg.effFromDt, param, idx, new String[] {EFF_FROM_DT_X })) {
                return;
            }
        }

        NMZC004002_xxAslListPMsg aslPMsg;
        for (int i = 0; i < pMsg.xxAslList.getValidCount(); i++) {
            aslPMsg = pMsg.xxAslList.no(i);
            if (!commonLogic.hasMandatory(aslPMsg.prntVndCd, param, idx, new String[] {PRNT_VND_CD_A })) {
                return;
            }
            if (!commonLogic.hasMandatory(aslPMsg.mdseCd, param, idx, new String[] {MDSE_CD_A })) {
                return;
            }
            if (!commonLogic.hasMandatory(aslPMsg.splyItemNum, param, idx, new String[] {SPLY_ITEM_NUM_A })) {
                return;
            }
            if (!commonLogic.hasMandatory(aslPMsg.vndCd, param, idx, new String[] {VND_CD_A })) {
                return;
            }
            if (!commonLogic.hasMandatory(aslPMsg.unitPrcAmt, param, idx, new String[] {UNIT_PRC_AMT_A })) {
                return;
            }
        }
        
        // QC#4342
        NMZC004002_xxTermListPMsg termPMsg;
        for (int i = 0; i < pMsg.xxTermList.getValidCount(); i++) {
            termPMsg = pMsg.xxTermList.no(i);
            if (!commonLogic.hasMandatory(termPMsg.termCondOptTpCd, param, idx, new String[] {MDSE_TERM_COND_OPT_TP_CD_T })) {
                return;
            }
        }
    }

    private void checkFlag(NMZC004001PMsg param, int idx) {

        NMZC004002PMsg pMsg = param.NMZC004002PMsg.no(idx);

        if (!commonLogic.verifyFlag(pMsg.invtyCtrlFlg_M)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {INVTY_CTRL_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.invtyValFlg_M)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {INVTY_VAL_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.rcvSerTakeFlg_M)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {RCV_SER_TAKE_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.shpgSerTakeFlg_M)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {SHPG_SER_TAKE_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.configFlg_D)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {CONFIG_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.rtrnReqPrtFlg_D)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {RTRN_REQ_PRT_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.rmaInspReqFlg_D)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {RMA_INSP_REQ_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.invPsblFlg_D)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {INV_PSBL_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.mtrMachFlg_D)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {MTR_MACH_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.instlBaseCtrlFlg_D)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {INSTL_BASE_CTRL_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.svcCallEnblFlg_D)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {SVC_CALL_ENBL_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.iwrEnblFlg_D)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {IWR_ENBL_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.custOrdEnblFlg_D)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {CUST_ORD_ENBL_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.reMnfItemExstFlg_D)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {RE_MNF_ITEM_EXST_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.mainMachFlg_D)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {MAIN_MACH_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.reMnfAvalFlg_D)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {RE_MNF_AVAL_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.prtDropShipDsblFlg_D)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {PRT_DROP_SHIP_DSBL_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.intntConnSwCtrlFlg_D)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {INTNT_CONN_SW_CTRL_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.sowReqFlg_D)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {SOW_REQ_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.prntCmpySetMdseFlg_D)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {PRNT_CMPY_SET_MDSE_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.thirdPtyItemFlg_D)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {THIRD_PTY_ITEM_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.prtSrvyReqFlg_D)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {PRT_SRVY_REQ_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.maintPopAvalFlg_D)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {MAINT_POP_AVAL_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.extMaintPopAvalFlg_D)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {EXT_MAINT_POP_AVAL_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.fccReqFlg_S)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {FCC_REQ_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.fccPrt15over18thReqFlg_S)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {FCC_PRT_15OVER18_REQ_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.fccVerFlg_S)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {FCC_VER_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.fccCrtifFlg_S)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {FCC_CRTIF_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.fccDocFlg_S)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {FCC_DOC_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.fccPrt68ReqFlg_S)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {FCC_PRT68_REQ_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.fdaReqFlg_S)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {FDA_REQ_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.fdaRdlgProdFlg_S)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {FDA_RDLG_PROD_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.fdaMedcDvcFlg_S)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {FDA_MEDC_DVC_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.tscaReqFlg_S)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {TSCA_REQ_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.ulReqFlg_S)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {UL_REQ_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.cfcReqFlg_S)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {CFC_REQ_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.msdsReqFlg_S)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {MSDS_REQ_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.hazMatFlg_S)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {HAZ_MAT_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.mercCntnFlg_S)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {MERC_CNTN_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.odcTaxFlg_S)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {ODC_TAX_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.cstmReqFlg_M)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {CUSTOM_REQ_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.showEtaFlg_M)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {SHOW_ETA_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.prodAuthReqFlg_M)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {PROD_AUTH_REQ_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.wrtDownFlg_M)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {WRT_DOWN_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.fobReCalcExclFlg_M)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {FOB_RE_CALC_EXEC_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.carryChrgCalcManFlg_M)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {CARRY_CHRG_CALC_MAN_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.shpgOrdHldFlg_M)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {SHPG_ORD_HLD_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.soAuthReqFlg_M)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {SO_AUTH_REQ_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.expItemFlg_M)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {EXP_ITEM_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.distByWhFlg_M)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {DIST_BY_WH_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.manPrcAllwOnOrdFlg_M)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {MAN_PRC_ALLW_ON_ORD_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.autoLicAllocFlg_M)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {AUTO_LIC_ALLOC_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.custIstlFlg_D)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {CUST_ISTL_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.siteSrvyReqFlg_D)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {SITE_SRVY_REQ_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.vndRtrnFlg_D)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {VND_RTRN_FLG })) {
                return;
            }
        }
        if (!commonLogic.verifyFlag(pMsg.assetMgtFlg_D)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {ASSET_MGT_FLG })) {
                return;
            }
        }
        
    }

    private void checkCodeTable(NMZC004001PMsg param, int idx) {

        String glblCmpyCd = param.glblCmpyCd.getValue();
        NMZC004002PMsg pMsg = param.NMZC004002PMsg.no(idx);

        if (hasValue(pMsg.coaProdCd_M)) {
            COA_PRODTMsg coaProdTMsg = new COA_PRODTMsg();
            ZYPEZDItemValueSetter.setValue(coaProdTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(coaProdTMsg.coaProdCd, pMsg.coaProdCd_M.getValue());
            coaProdTMsg = (COA_PRODTMsg) S21CacheTBLAccessor.findByKey(coaProdTMsg);
            if (coaProdTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {COA_PROD_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.invtyHardAllocTpCd_M)) {
            HARD_ALLOC_TPTMsg hardAllocTpTMsg = new HARD_ALLOC_TPTMsg();
            ZYPEZDItemValueSetter.setValue(hardAllocTpTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(hardAllocTpTMsg.hardAllocTpCd, pMsg.invtyHardAllocTpCd_M.getValue());
            hardAllocTpTMsg = (HARD_ALLOC_TPTMsg) S21CacheTBLAccessor.findByKey(hardAllocTpTMsg);
            if (hardAllocTpTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {INVTY_HARD_ALLOC_TP_CD })) {
                    return;
                }
            }
        //Mandatory
        } else {
        	ZYPEZDItemValueSetter.setValue(pMsg.invtyHardAllocTpCd_M, HARD_ALLOC_TP.AUTO);
        }

        if (hasValue(pMsg.rmaReqTpCd_M)) {
            RMA_REQ_TPTMsg rmaReqTpTMsg = new RMA_REQ_TPTMsg();
            ZYPEZDItemValueSetter.setValue(rmaReqTpTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(rmaReqTpTMsg.rmaReqTpCd, pMsg.rmaReqTpCd_M.getValue());
            rmaReqTpTMsg = (RMA_REQ_TPTMsg) S21CacheTBLAccessor.findByKey(rmaReqTpTMsg);
            if (rmaReqTpTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {RMA_REQ_TP_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.easyPackTpCd_D)) {
            EASY_PACK_TPTMsg easyPackTpTMsg = new EASY_PACK_TPTMsg();
            ZYPEZDItemValueSetter.setValue(easyPackTpTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(easyPackTpTMsg.easyPackTpCd, pMsg.easyPackTpCd_D.getValue());
            easyPackTpTMsg = (EASY_PACK_TPTMsg) S21CacheTBLAccessor.findByKey(easyPackTpTMsg);
            if (easyPackTpTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {EASY_PACK_TP_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.mdseRgtnTpCd_D)) {
            MDSE_RGTN_TPTMsg mdseRgtnTpTMsg = new MDSE_RGTN_TPTMsg();
            ZYPEZDItemValueSetter.setValue(mdseRgtnTpTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(mdseRgtnTpTMsg.mdseRgtnTpCd, pMsg.mdseRgtnTpCd_D.getValue());
            mdseRgtnTpTMsg = (MDSE_RGTN_TPTMsg) S21CacheTBLAccessor.findByKey(mdseRgtnTpTMsg);
            if (mdseRgtnTpTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {MDSE_RGTN_TP_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.mdsePrcGrpCd_D)) {
            MDSE_PRC_GRPTMsg mdsePrcGrpTMsg = new MDSE_PRC_GRPTMsg();
            ZYPEZDItemValueSetter.setValue(mdsePrcGrpTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(mdsePrcGrpTMsg.mdsePrcGrpCd, pMsg.mdsePrcGrpCd_D.getValue());
            mdsePrcGrpTMsg = (MDSE_PRC_GRPTMsg) S21CacheTBLAccessor.findByKey(mdsePrcGrpTMsg);
            if (mdsePrcGrpTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {MDSE_PRC_GRP_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.cycleCntCatgCd_D)) {
            CYCLE_CNT_CATGTMsg cycleCntCatgTMsg = new CYCLE_CNT_CATGTMsg();
            ZYPEZDItemValueSetter.setValue(cycleCntCatgTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cycleCntCatgTMsg.cycleCntCatgCd, pMsg.cycleCntCatgCd_D.getValue());
            cycleCntCatgTMsg = (CYCLE_CNT_CATGTMsg) S21CacheTBLAccessor.findByKey(cycleCntCatgTMsg);
            if (cycleCntCatgTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {CYCLE_CNT_CATG_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.mdseItemTpCd_D)) {
            MDSE_ITEM_TPTMsg mdseItemTpTMsg = commonLogic.getMdseItemTp(glblCmpyCd, pMsg.mdseItemTpCd_D.getValue());
            if (mdseItemTpTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {MDSE_ITEM_TP_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.mdseItemClsTpCd_D)) {
            MDSE_ITEM_CLS_TPTMsg mdseItemClsTpTMsg = new MDSE_ITEM_CLS_TPTMsg();
            ZYPEZDItemValueSetter.setValue(mdseItemClsTpTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(mdseItemClsTpTMsg.mdseItemClsTpCd, pMsg.mdseItemClsTpCd_D.getValue());
            mdseItemClsTpTMsg = (MDSE_ITEM_CLS_TPTMsg) S21CacheTBLAccessor.findByKey(mdseItemClsTpTMsg);
            if (mdseItemClsTpTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {MDSE_ITEM_CLS_TP_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.mktMdlSegCd_D)) {
            MKT_MDL_SEGTMsg mktMdlSegTMsg = new MKT_MDL_SEGTMsg();
            ZYPEZDItemValueSetter.setValue(mktMdlSegTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(mktMdlSegTMsg.mktMdlSegCd, pMsg.mktMdlSegCd_D.getValue());
            mktMdlSegTMsg = (MKT_MDL_SEGTMsg) S21CacheTBLAccessor.findByKey(mktMdlSegTMsg);
            if (mktMdlSegTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {MKT_MDL_SEG_CD })) {
                    return;
                }
            }
        }

        MDSE_ITEM_STSTMsg mdseItemTpStsTMsg = commonLogic.getMdseItemSts(glblCmpyCd, pMsg.mdseItemStsCd_D.getValue());
        if (mdseItemTpStsTMsg == null) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {MDSE_ITEM_STS_CD })) {
                return;
            }
        }

        if (hasValue(pMsg.rtrnCtrlTpCd_D)) {
            RTRN_CTRL_TPTMsg rtrnCtrlTpTMsg = new RTRN_CTRL_TPTMsg();
            ZYPEZDItemValueSetter.setValue(rtrnCtrlTpTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(rtrnCtrlTpTMsg.rtrnCtrlTpCd, pMsg.rtrnCtrlTpCd_D.getValue());
            rtrnCtrlTpTMsg = (RTRN_CTRL_TPTMsg) S21CacheTBLAccessor.findByKey(rtrnCtrlTpTMsg);
            if (rtrnCtrlTpTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {RTRN_CTRL_TP_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.rtrnDsplTpCd_D)) {
            RTRN_DSPL_TPTMsg rtrnDsplTpTMsg = new RTRN_DSPL_TPTMsg();
            ZYPEZDItemValueSetter.setValue(rtrnDsplTpTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(rtrnDsplTpTMsg.rtrnDsplTpCd, pMsg.rtrnDsplTpCd_D.getValue());
            rtrnDsplTpTMsg = (RTRN_DSPL_TPTMsg) S21CacheTBLAccessor.findByKey(rtrnDsplTpTMsg);
            if (rtrnDsplTpTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {RTRN_DSPL_TP_CD })) {
                    return;
                }
            }
        }
        
        if (hasValue(pMsg.dfrdAcctgRuleCd_D)) {
            DFRD_ACCTG_RULETMsg dftdAcctgRuleTMsg = new DFRD_ACCTG_RULETMsg();
            ZYPEZDItemValueSetter.setValue(dftdAcctgRuleTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dftdAcctgRuleTMsg.dfrdAcctgRuleCd, pMsg.dfrdAcctgRuleCd_D.getValue());
            dftdAcctgRuleTMsg = (DFRD_ACCTG_RULETMsg) S21CacheTBLAccessor.findByKey(dftdAcctgRuleTMsg);
            if (dftdAcctgRuleTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {DFRD_ACCTG_RULE_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.dfrdInvRuleCd_D)) {
            DFRD_INV_RULETMsg dfrdInvRuleTMsg = new DFRD_INV_RULETMsg();
            ZYPEZDItemValueSetter.setValue(dfrdInvRuleTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dfrdInvRuleTMsg.dfrdInvRuleCd, pMsg.dfrdInvRuleCd_D.getValue());
            dfrdInvRuleTMsg = (DFRD_INV_RULETMsg) S21CacheTBLAccessor.findByKey(dfrdInvRuleTMsg);
            if (dfrdInvRuleTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {DFRD_INV_RULE_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.taxExemTpCd_D)) {
            TAX_EXEM_TPTMsg taxExemTpTMsg = new TAX_EXEM_TPTMsg();
            ZYPEZDItemValueSetter.setValue(taxExemTpTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(taxExemTpTMsg.taxExemTpCd, pMsg.taxExemTpCd_D.getValue());
            taxExemTpTMsg = (TAX_EXEM_TPTMsg) S21CacheTBLAccessor.findByKey(taxExemTpTMsg);
            if (taxExemTpTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {TAX_EXEM_TP_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.svcWtyTpCd_D)) {
            SVC_WTY_TPTMsg svcWtyTpTMsg = new SVC_WTY_TPTMsg();
            ZYPEZDItemValueSetter.setValue(svcWtyTpTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(svcWtyTpTMsg.svcWtyTpCd, pMsg.svcWtyTpCd_D.getValue());
            svcWtyTpTMsg = (SVC_WTY_TPTMsg) S21CacheTBLAccessor.findByKey(svcWtyTpTMsg);
            if (svcWtyTpTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {SVC_WTY_TP_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.iwrMdlCd_D)) {
            IWR_MDLTMsg iwrMdlTMsg = new IWR_MDLTMsg();
            ZYPEZDItemValueSetter.setValue(iwrMdlTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(iwrMdlTMsg.iwrMdlCd, pMsg.iwrMdlCd_D.getValue());
            iwrMdlTMsg = (IWR_MDLTMsg) S21CacheTBLAccessor.findByKey(iwrMdlTMsg);
            if (iwrMdlTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {IWR_MDL_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.mdseItemMnfCd_D)) {
            MDSE_ITEM_MNFTMsg mdseItemMnfTMsg = new MDSE_ITEM_MNFTMsg();
            ZYPEZDItemValueSetter.setValue(mdseItemMnfTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(mdseItemMnfTMsg.mdseItemMnfCd, pMsg.mdseItemMnfCd_D.getValue());
            mdseItemMnfTMsg = (MDSE_ITEM_MNFTMsg) S21CacheTBLAccessor.findByKey(mdseItemMnfTMsg);
            if (mdseItemMnfTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {MDSE_ITEM_MNF_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.backOrdImpctTpCd_D)) {
            BACK_ORD_IMPCT_TPTMsg backOrdImpctTpTMsg = new BACK_ORD_IMPCT_TPTMsg();
            ZYPEZDItemValueSetter.setValue(backOrdImpctTpTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(backOrdImpctTpTMsg.backOrdImpctTpCd, pMsg.backOrdImpctTpCd_D.getValue());
            backOrdImpctTpTMsg = (BACK_ORD_IMPCT_TPTMsg) S21CacheTBLAccessor.findByKey(backOrdImpctTpTMsg);
            if (backOrdImpctTpTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {BACK_ORD_IMPCT_TP_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.imgSplyTpCd_D)) {
            IMG_SPLY_TPTMsg imgSplyTpTMsg = new IMG_SPLY_TPTMsg();
            ZYPEZDItemValueSetter.setValue(imgSplyTpTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(imgSplyTpTMsg.imgSplyTpCd, pMsg.imgSplyTpCd_D.getValue());
            imgSplyTpTMsg = (IMG_SPLY_TPTMsg) S21CacheTBLAccessor.findByKey(imgSplyTpTMsg);
            if (imgSplyTpTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {IMG_SPLY_TP_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.imgSplyColorTpCd_D)) {
            IMG_SPLY_COLOR_TPTMsg imgSplyColorTpTMsg = new IMG_SPLY_COLOR_TPTMsg();
            ZYPEZDItemValueSetter.setValue(imgSplyColorTpTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(imgSplyColorTpTMsg.imgSplyColorTpCd, pMsg.imgSplyColorTpCd_D.getValue());
            imgSplyColorTpTMsg = (IMG_SPLY_COLOR_TPTMsg) S21CacheTBLAccessor.findByKey(imgSplyColorTpTMsg);
            if (imgSplyColorTpTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {IMG_SPLY_COLOR_TP_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.imgSplyYieldUomCd_D)) {
            IMG_SPLY_YIELD_UOMTMsg imgSplyYieldUomTMsg = new IMG_SPLY_YIELD_UOMTMsg();
            ZYPEZDItemValueSetter.setValue(imgSplyYieldUomTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(imgSplyYieldUomTMsg.imgSplyYieldUomCd, pMsg.imgSplyYieldUomCd_D.getValue());
            imgSplyYieldUomTMsg = (IMG_SPLY_YIELD_UOMTMsg) S21CacheTBLAccessor.findByKey(imgSplyYieldUomTMsg);
            if (imgSplyYieldUomTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {IMG_SPLY_YIELD_UOM_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.imgSplyYieldTpCd_D)) {
            IMG_SPLY_YIELD_TPTMsg imgSplyYieldTpTMsg = new IMG_SPLY_YIELD_TPTMsg();
            ZYPEZDItemValueSetter.setValue(imgSplyYieldTpTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(imgSplyYieldTpTMsg.imgSplyYieldTpCd, pMsg.imgSplyYieldTpCd_D.getValue());
            imgSplyYieldTpTMsg = (IMG_SPLY_YIELD_TPTMsg) S21CacheTBLAccessor.findByKey(imgSplyYieldTpTMsg);
            if (imgSplyYieldTpTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {IMG_SPLY_YIELD_TP_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.svcCovTmplTpCd_D)) {
            SVC_COV_TMPL_TPTMsg svcCovTmplTpTMsg = new SVC_COV_TMPL_TPTMsg();
            ZYPEZDItemValueSetter.setValue(svcCovTmplTpTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(svcCovTmplTpTMsg.svcCovTmplTpCd, pMsg.svcCovTmplTpCd_D.getValue());
            svcCovTmplTpTMsg = (SVC_COV_TMPL_TPTMsg) S21CacheTBLAccessor.findByKey(svcCovTmplTpTMsg);
            if (svcCovTmplTpTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {SVC_COV_TMPL_TP_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.svcAllocTpCd_D)) {
            SVC_ALLOC_TPTMsg svcAllocTpTMsg = new SVC_ALLOC_TPTMsg();
            ZYPEZDItemValueSetter.setValue(svcAllocTpTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(svcAllocTpTMsg.svcAllocTpCd, pMsg.svcAllocTpCd_D.getValue());
            svcAllocTpTMsg = (SVC_ALLOC_TPTMsg) S21CacheTBLAccessor.findByKey(svcAllocTpTMsg);
            if (svcAllocTpTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {SVC_ALLOC_TP_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.swCatgCd_D)) {
            SW_CATGTMsg swCatgTMsg = new SW_CATGTMsg();
            ZYPEZDItemValueSetter.setValue(swCatgTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(swCatgTMsg.swCatgCd, pMsg.swCatgCd_D.getValue());
            swCatgTMsg = (SW_CATGTMsg) S21CacheTBLAccessor.findByKey(swCatgTMsg);
            if (swCatgTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {SW_CATG_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.swTpCd_D)) {
            SW_TPTMsg swTpTMsg = new SW_TPTMsg();
            ZYPEZDItemValueSetter.setValue(swTpTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(swTpTMsg.swTpCd, pMsg.swTpCd_D.getValue());
            swTpTMsg = (SW_TPTMsg) S21CacheTBLAccessor.findByKey(swTpTMsg);
            if (swTpTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {SW_TP_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.swLicCtrlTpCd_D)) {
            SW_LIC_CTRL_TPTMsg swLicCtrlTpTMsg = new SW_LIC_CTRL_TPTMsg();
            ZYPEZDItemValueSetter.setValue(swLicCtrlTpTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(swLicCtrlTpTMsg.swLicCtrlTpCd, pMsg.swLicCtrlTpCd_D.getValue());
            swLicCtrlTpTMsg = (SW_LIC_CTRL_TPTMsg) S21CacheTBLAccessor.findByKey(swLicCtrlTpTMsg);
            if (swLicCtrlTpTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {SW_LIC_CTRL_TP_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.swMaintCtrlTpCd_D)) {
            SW_MAINT_CTRL_TPTMsg swMaintCtrlTpTMsg = new SW_MAINT_CTRL_TPTMsg();
            ZYPEZDItemValueSetter.setValue(swMaintCtrlTpTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(swMaintCtrlTpTMsg.swMaintCtrlTpCd, pMsg.swMaintCtrlTpCd_D.getValue());
            swMaintCtrlTpTMsg = (SW_MAINT_CTRL_TPTMsg) S21CacheTBLAccessor.findByKey(swMaintCtrlTpTMsg);
            if (swMaintCtrlTpTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {SW_MAINT_CTRL_TP_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.swMaintTpCd_D)) {
            SW_MAINT_TPTMsg swMaintTpTMsg = new SW_MAINT_TPTMsg();
            ZYPEZDItemValueSetter.setValue(swMaintTpTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(swMaintTpTMsg.swMaintTpCd, pMsg.swMaintTpCd_D.getValue());
            swMaintTpTMsg = (SW_MAINT_TPTMsg) S21CacheTBLAccessor.findByKey(swMaintTpTMsg);
            if (swMaintTpTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {SW_MAINT_TP_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.svcChrgItemTpCd_D)) {
            SVC_CHRG_ITEM_TPTMsg svcChrgItemTpTMsg = new SVC_CHRG_ITEM_TPTMsg();
            ZYPEZDItemValueSetter.setValue(svcChrgItemTpTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(svcChrgItemTpTMsg.svcChrgItemTpCd, pMsg.svcChrgItemTpCd_D.getValue());
            svcChrgItemTpTMsg = (SVC_CHRG_ITEM_TPTMsg) S21CacheTBLAccessor.findByKey(svcChrgItemTpTMsg);
            if (svcChrgItemTpTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {SVC_CHRG_ITEM_TP_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.mdseItemBillTpCd_D)) {
            MDSE_ITEM_BILL_TPTMsg mdseItemBillTpTMsg = new MDSE_ITEM_BILL_TPTMsg();
            ZYPEZDItemValueSetter.setValue(mdseItemBillTpTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(mdseItemBillTpTMsg.mdseItemBillTpCd, pMsg.mdseItemBillTpCd_D.getValue());
            mdseItemBillTpTMsg = (MDSE_ITEM_BILL_TPTMsg) S21CacheTBLAccessor.findByKey(mdseItemBillTpTMsg);
            if (mdseItemBillTpTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {MDSE_ITEM_BILL_TP_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.defSrcProcrTpCd_D)) {
            PROCR_TPTMsg procrTpTMsg = new PROCR_TPTMsg();
            ZYPEZDItemValueSetter.setValue(procrTpTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(procrTpTMsg.procrTpCd, pMsg.defSrcProcrTpCd_D.getValue());
            procrTpTMsg = (PROCR_TPTMsg) S21CacheTBLAccessor.findByKey(procrTpTMsg);
            if (procrTpTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {DEF_SRC_PROCR_TP_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.pkgUomClsCd_D)) {
            PKG_UOM_CLSTMsg pkgUomClsTMsg = commonLogic.getPkgUomClsTMsg(glblCmpyCd, pMsg.pkgUomClsCd_D.getValue());
            if (pkgUomClsTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {PKG_UOM_CLS_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.prtItemTpCd_D)) {
            PRT_ITEM_TPTMsg prtItemTpTMsg = new PRT_ITEM_TPTMsg();
            ZYPEZDItemValueSetter.setValue(prtItemTpTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(prtItemTpTMsg.prtItemTpCd, pMsg.prtItemTpCd_D.getValue());
            prtItemTpTMsg = (PRT_ITEM_TPTMsg) S21CacheTBLAccessor.findByKey(prtItemTpTMsg);
            if (prtItemTpTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {PRT_ITEM_TP_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.swProdCatgCd_D)) {
            SW_PROD_CATGTMsg swProdCatgTMsg = new SW_PROD_CATGTMsg();
            ZYPEZDItemValueSetter.setValue(swProdCatgTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(swProdCatgTMsg.swProdCatgCd, pMsg.swProdCatgCd_D.getValue());
            swProdCatgTMsg = (SW_PROD_CATGTMsg) S21CacheTBLAccessor.findByKey(swProdCatgTMsg);
            if (swProdCatgTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {SW_PROD_CATG_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.maintItemTpCd_D)) {
            MAINT_ITEM_TPTMsg maintItemTpTMsg = new MAINT_ITEM_TPTMsg();
            ZYPEZDItemValueSetter.setValue(maintItemTpTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(maintItemTpTMsg.maintItemTpCd, pMsg.maintItemTpCd_D.getValue());
            maintItemTpTMsg = (MAINT_ITEM_TPTMsg) S21CacheTBLAccessor.findByKey(maintItemTpTMsg);
            if (maintItemTpTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {MAINT_ITEM_TP_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.svcCovBaseTpCd_D)) {
            SVC_COV_BASE_TPTMsg svcCovBaseTpTMsg = new SVC_COV_BASE_TPTMsg();
            ZYPEZDItemValueSetter.setValue(svcCovBaseTpTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(svcCovBaseTpTMsg.svcCovBaseTpCd, pMsg.svcCovBaseTpCd_D.getValue());
            svcCovBaseTpTMsg = (SVC_COV_BASE_TPTMsg) S21CacheTBLAccessor.findByKey(svcCovBaseTpTMsg);
            if (svcCovBaseTpTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {SVC_COV_BASE_TP_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.lineBizTpCd_D)) {
            LINE_BIZ_TPTMsg lineBizTpTMsg = new LINE_BIZ_TPTMsg();
            ZYPEZDItemValueSetter.setValue(lineBizTpTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(lineBizTpTMsg.lineBizTpCd, pMsg.lineBizTpCd_D.getValue());
            lineBizTpTMsg = (LINE_BIZ_TPTMsg) S21CacheTBLAccessor.findByKey(lineBizTpTMsg);
            if (lineBizTpTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {LINE_BIZ_TP_CD })) {
                    return;
                }
            }
        }

//        if (hasValue(pMsg.nmfcClsTpCd_D)) {
//            NMFC_CLS_TPTMsg nmFcClsTpTMsg = new NMFC_CLS_TPTMsg();
//            ZYPEZDItemValueSetter.setValue(nmFcClsTpTMsg.glblCmpyCd, glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(nmFcClsTpTMsg.nmfcClsTpCd, pMsg.nmfcClsTpCd_D.getValue());
//            nmFcClsTpTMsg = (NMFC_CLS_TPTMsg) S21CacheTBLAccessor.findByKey(nmFcClsTpTMsg);
//            if (nmFcClsTpTMsg == null) {
//                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {NMFC_CLS_TP_CD })) {
//                    return;
//                }
//            }
//        }
        if (hasValue(pMsg.frtClsCd_M)) {
        	FRT_CLSTMsg frtClsTMsg = new FRT_CLSTMsg();
            ZYPEZDItemValueSetter.setValue(frtClsTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(frtClsTMsg.frtClsCd, pMsg.frtClsCd_M.getValue());
            frtClsTMsg = (FRT_CLSTMsg) S21CacheTBLAccessor.findByKey(frtClsTMsg);
        	if (frtClsTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {FRT_CLS_CD })) {
                	return;
                }
        	}
        }
        if (hasValue(pMsg.imgSplyPvtLbTpCd_D)) {
            IMG_SPLY_PVT_LB_TPTMsg imgSplyPvtLbTpTMsg = new IMG_SPLY_PVT_LB_TPTMsg();
            ZYPEZDItemValueSetter.setValue(imgSplyPvtLbTpTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(imgSplyPvtLbTpTMsg.imgSplyPvtLbTpCd, pMsg.imgSplyPvtLbTpCd_D.getValue());
            imgSplyPvtLbTpTMsg = (IMG_SPLY_PVT_LB_TPTMsg) S21CacheTBLAccessor.findByKey(imgSplyPvtLbTpTMsg);
            if (imgSplyPvtLbTpTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {IMG_SPLY_PVT_LB_TP_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.madeInCtryCd_S)) {
            CTRYTMsg madeInCtryTMsg = commonLogic.getCtry(glblCmpyCd, pMsg.madeInCtryCd_S.getValue());
            if (madeInCtryTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {MADE_IN_CTRY_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.asmInCtryCd_S)) {
            CTRYTMsg asmInCtryTMsg = commonLogic.getCtry(glblCmpyCd, pMsg.asmInCtryCd_S.getValue());
            if (asmInCtryTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {ASM_IN_CTRY_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.prcCatgCd_P1)) {
            PRC_CATGTMsg msrpPrcCatgTMsg = commonLogic.getPrcCatg(glblCmpyCd, pMsg.prcCatgCd_P1.getValue());
            if (msrpPrcCatgTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {MSRP_PRC_CATG_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.prcCatgCd_P2)) {
            PRC_CATGTMsg flPrcCatgTMsg = commonLogic.getPrcCatg(glblCmpyCd, pMsg.prcCatgCd_P2.getValue());
            if (flPrcCatgTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {FL_PRC_CATG_CD })) {
                    return;
                }
            }
        }
    }

    private void checkMaster(NMZC004001PMsg param, int idx) {

        String glblCmpyCd = param.glblCmpyCd.getValue();
        NMZC004002PMsg pMsg = param.NMZC004002PMsg.no(idx);

        //V1.5
        if (hasValue(pMsg.zerothProdCtrlCd_M)) {
            PROD_CTRLTMsg tMsg = commonLogic.getProdCtrl(glblCmpyCd, pMsg.zerothProdCtrlCd_M.getValue());
            if (tMsg == null || !MDSE_STRU_ELMNT_TP.PRODUCT_LINE_GROUP.equals(tMsg.mdseStruElmntTpCd.getValue())) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {PROD_CTRL_CD })) {
                    return;
                }
            }
        }

        //V1.5
        if (hasValue(pMsg.firstProdCtrlCd_M)) {
            PROD_CTRLTMsg tMsg = commonLogic.getProdCtrl(glblCmpyCd, pMsg.firstProdCtrlCd_M.getValue());
            if (tMsg == null || !MDSE_STRU_ELMNT_TP.PRODUCT_LINE.equals(tMsg.mdseStruElmntTpCd.getValue())) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {PROD_CTRL_CD })) {
                    return;
                }
            }
        }

        //V1.5
        if (hasValue(pMsg.scdProdCtrlCd_M)) {
            PROD_CTRLTMsg tMsg = commonLogic.getProdCtrl(glblCmpyCd, pMsg.scdProdCtrlCd_M.getValue());
            if (tMsg == null || !MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2.equals(tMsg.mdseStruElmntTpCd.getValue())) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {PROD_CTRL_CD })) {
                    return;
                }
            }
        }

        //V1.5
        if (hasValue(pMsg.thirdProdCtrlCd_M)) {
            PROD_CTRLTMsg tMsg = commonLogic.getProdCtrl(glblCmpyCd, pMsg.thirdProdCtrlCd_M.getValue());
            if (tMsg == null || !MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3.equals(tMsg.mdseStruElmntTpCd.getValue()) || !S21StringUtil.isEquals(pMsg.scdProdCtrlCd_M.getValue(), tMsg.scdProdHrchCd.getValue())) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {PROD_CTRL_CD })) {
                    return;
                }
            }
        }

        //V1.5
        if (hasValue(pMsg.frthProdCtrlCd_M)) {
            PROD_CTRLTMsg tMsg = commonLogic.getProdCtrl(glblCmpyCd, pMsg.frthProdCtrlCd_M.getValue());
            if (tMsg == null || !MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_4.equals(tMsg.mdseStruElmntTpCd.getValue())) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {PROD_CTRL_CD })) {
                    return;
                }
            }
        }

        //V1.5
        if (hasValue(pMsg.slsMatGrpCd_D1)) {
            Map<String, Object> map = NMZC004001Query.getInstance().getSlsMatGrp(glblCmpyCd, pMsg.slsMatGrpCd_D1.getValue(), "1");
            if (map == null || map.get("SLS_MAT_GRP_CD") == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {SLS_MAT_GRP })) {
                    return;
                }
            }
        }
        
        //V1.5
        if (hasValue(pMsg.slsMatGrpCd_D2)) {
            Map<String, Object> map = NMZC004001Query.getInstance().getSlsMatGrp(glblCmpyCd, pMsg.slsMatGrpCd_D2.getValue(), "2");
            if (map == null || map.get("SLS_MAT_GRP_CD") == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {SLS_MAT_GRP })) {
                    return;
                }
            }
        }
        
        //V1.5
        if (hasValue(pMsg.slsMatGrpCd_D3)) {
            Map<String, Object> map = NMZC004001Query.getInstance().getSlsMatGrp(glblCmpyCd, pMsg.slsMatGrpCd_D3.getValue(), "3");
            if (map == null || map.get("SLS_MAT_GRP_CD") == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {SLS_MAT_GRP })) {
                    return;
                }
            }
        }
        
        //V1.5
        if (hasValue(pMsg.dsCmsnGrpCd_D)) {
            Map<String, Object> map = NMZC004001Query.getInstance().getDsCmsnGrp(glblCmpyCd, pMsg.dsCmsnGrpCd_D.getValue());
            if (map == null || map.get("DS_CMSN_GRP_CD") == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {DS_CMSN_GRP })) {
                    return;
                }
            }
        }
        
        if (hasValue(pMsg.prchGrpCd_D)) {
            PRCH_GRPTMsg tMsg = new PRCH_GRPTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.prchGrpCd, pMsg.prchGrpCd_D.getValue());
            tMsg = (PRCH_GRPTMsg) S21CacheTBLAccessor.findByKey(tMsg);
            if (tMsg == null) {
                if (commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {PRCH_GRP_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.mktMdlCd_D)) {
            MKT_MDLTMsg tMsg = new MKT_MDLTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.mktMdlCd, pMsg.mktMdlCd_D.getValue());
            tMsg = (MKT_MDLTMsg) S21CacheTBLAccessor.findByKey(tMsg);
            if (tMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {MKT_MDL_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.defSrcWhCd_D)) {
            RTL_WHTMsg tMsg = new RTL_WHTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, pMsg.defSrcWhCd_D.getValue());
            tMsg = (RTL_WHTMsg) S21CacheTBLAccessor.findByKey(tMsg);
            if (tMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {DEF_SRC_WH_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.defSrcSubWhCd_D)) {
            //QC#10449
        	if (hasValue(pMsg.defSrcWhCd_D)) {
                RTL_SWHTMsg tMsg = new RTL_SWHTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, pMsg.defSrcWhCd_D.getValue());
                ZYPEZDItemValueSetter.setValue(tMsg.rtlSwhCd, pMsg.defSrcSubWhCd_D.getValue());
                tMsg = (RTL_SWHTMsg) S21CacheTBLAccessor.findByKey(tMsg);
                if (tMsg == null) {
                    if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {DEF_SRC_SUB_WH_CD })) {
                        return;
                    }
                }
        	} else {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {DEF_SRC_SUB_WH_CD })) {
                    return;
                }
        	}
        }

        if (hasValue(pMsg.revCoaAcctCd_D)) {
            COA_ACCTTMsg tMsg = commonLogic.getCoaAcct(glblCmpyCd, pMsg.revCoaAcctCd_D.getValue());
            if (tMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {REV_COA_ACCT_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.cogsCoaAcctCd_D)) {
            COA_ACCTTMsg tMsg = commonLogic.getCoaAcct(glblCmpyCd, pMsg.cogsCoaAcctCd_D.getValue());
            if (tMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {COGS_COA_ACCT_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.expCoaAcctCd_D)) {
            COA_ACCTTMsg tMsg = commonLogic.getCoaAcct(glblCmpyCd, pMsg.expCoaAcctCd_D.getValue());
            if (tMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {EXP_COA_ACCT_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.acrlCoaAcctCd_D)) {
            COA_ACCTTMsg tMsg = commonLogic.getCoaAcct(glblCmpyCd, pMsg.acrlCoaAcctCd_D.getValue());
            if (tMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {ACRL_COA_ACCT_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.iwrMdseCd_D)) {
            MDSETMsg tMsg = commonLogic.getMdse(glblCmpyCd, pMsg.iwrMdseCd_D.getValue());
            if (tMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {IWR_MDSE_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.coaMdseTpCd_D)) {
            COA_PROJTMsg tMsg = commonLogic.getCoaProj(glblCmpyCd, pMsg.coaMdseTpCd_D.getValue());
            if (tMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {COA_MDSE_TP_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.bdlMaintMdseCd_D)) {
            MDSETMsg tMsg = commonLogic.getMdse(glblCmpyCd, pMsg.bdlMaintMdseCd_D.getValue());
            if (tMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {BDL_MAINT_MDSE_CD })) {
                    return;
                }
            }
        }

        //QC#4203
        //if (hasValue(pMsg.rtrnToPrntVndCd_D)) {
        //    PRNT_VNDTMsg tMsg = new PRNT_VNDTMsg();
        //    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        //    ZYPEZDItemValueSetter.setValue(tMsg.prntVndCd, pMsg.rtrnToPrntVndCd_D.getValue());
        //    tMsg = (PRNT_VNDTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        //    if (tMsg == null) {
        //        if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {RTRN_TO_PRNT_VND_CD })) {
        //            return;
        //        }
        //    }
        //}

        //if (hasValue(pMsg.rtrnToVndCd_D)) {
        //    VNDTMsg tMsg = new VNDTMsg();
        //    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        //    ZYPEZDItemValueSetter.setValue(tMsg.vndCd, pMsg.rtrnToVndCd_D.getValue());
        //    tMsg = (VNDTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        //    if (tMsg == null) {
        //        if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {RTRN_TO_VND_CD })) {
        //            return;
        //        }
        //    }
        //}
        
        //Check RTRN_CTRL_TP_VND_RELN for rtrnCtrlTpCd_D, rtrnToVndCd_D, rtrnWhCd_D
        if (hasValue(pMsg.rtrnCtrlTpVndRelnPk_D)) {
            RTRN_CTRL_TP_VND_RELNTMsg rtrnCtrlTpVndRelnTMsg = new RTRN_CTRL_TP_VND_RELNTMsg();
            ZYPEZDItemValueSetter.setValue(rtrnCtrlTpVndRelnTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(rtrnCtrlTpVndRelnTMsg.rtrnCtrlTpVndRelnPk, pMsg.rtrnCtrlTpVndRelnPk_D.getValue());
            rtrnCtrlTpVndRelnTMsg = (RTRN_CTRL_TP_VND_RELNTMsg) S21CacheTBLAccessor.findByKey(rtrnCtrlTpVndRelnTMsg);
            if (rtrnCtrlTpVndRelnTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {RTRN_CTRL_TP_VND_RELN_PK })) {
                    return;
                }
            }
        } else if (hasValue(pMsg.rtrnCtrlTpCd_D) || hasValue(pMsg.rtrnToVndCd_D) || hasValue(pMsg.rtrnWhCd_D)) {
            //QC#10449
        	Map<String, Object> map = commonLogic.getRtrnCtrlTpVndReln(glblCmpyCd, pMsg.rtrnCtrlTpCd_D.getValue(), 
        			pMsg.rtrnToVndCd_D.getValue(), pMsg.rtrnWhCd_D.getValue(), ZYPDateUtil.getSalesDate());
            if (map == null || map.get("RTRN_CTRL_TP_VND_RELN_PK") == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {RTRN_CTRL_TP_CD + ", " + RTRN_TO_VND_CD + ", " + RTRN_WH_CD })) {
                    return;
                }
            } else {
            	ZYPEZDItemValueSetter.setValue(pMsg.rtrnCtrlTpVndRelnPk_D, (BigDecimal) map.get("RTRN_CTRL_TP_VND_RELN_PK"));
            }
        	
        }
        //Check PO_VND_V for rtrnToVndCd_D, rtrnToPrntVndCd_D
        if (hasValue(pMsg.rtrnToVndCd_D) || hasValue(pMsg.rtrnToPrntVndCd_D)) {
        	Map<String, Object> map = commonLogic.getPoVndV(glblCmpyCd, pMsg.rtrnToVndCd_D.getValue(), pMsg.rtrnToPrntVndCd_D.getValue());
            if (map == null || map.get("VND_CD") == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {RTRN_TO_VND_CD + ", " + RTRN_TO_PRNT_VND_CD })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.dsIntgMdseTpCd_D)) {
        	DS_INTG_MDSE_TPTMsg dsIntgMdseTpTMsg = new DS_INTG_MDSE_TPTMsg();
            ZYPEZDItemValueSetter.setValue(dsIntgMdseTpTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsIntgMdseTpTMsg.dsIntgMdseTpCd, pMsg.dsIntgMdseTpCd_D.getValue());
            dsIntgMdseTpTMsg = (DS_INTG_MDSE_TPTMsg) S21CacheTBLAccessor.findByKey(dsIntgMdseTpTMsg);
            if (dsIntgMdseTpTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {DS_INTG_MDSE_TP_CD })) {
                    return;
                }
            }
        }
        
        //QC#9970-1
        if (hasValue(pMsg.trfCd_M)) {
        	TRFTMsg trfTMsg = new TRFTMsg();
            ZYPEZDItemValueSetter.setValue(trfTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(trfTMsg.trfCd, pMsg.trfCd_M.getValue());
            trfTMsg = (TRFTMsg) S21CacheTBLAccessor.findByKey(trfTMsg);
            if (trfTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {TRF_CD })) {
                    return;
                }
            }
        }
    }

    private void checkIntegrity(NMZC004001PMsg param, int idx) {

        String glblCmpyCd = param.glblCmpyCd.getValue();
        String slsDt = param.slsDt.getValue();
        NMZC004002PMsg pMsg = param.NMZC004002PMsg.no(idx);
        String mdseCd = pMsg.mdseCd_M.getValue();

        // Header related
        if (MDSE_ITEM_TP.MACHINE.equals(pMsg.mdseItemTpCd_D.getValue())
        		|| (MDSE_ITEM_TP.KIT.equals(pMsg.mdseItemTpCd_D.getValue())
        				&& COA_MDSE_TP.MACHINE.equals(pMsg.coaMdseTpCd_D.getValue()))) {
            if (!hasValue(pMsg.mktMdlCd_D)) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0163E, new String[] {MKT_MDL_CD })) {
                    return;
                }
            }
            if (!hasValue(pMsg.mktMdlSegCd_D)) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0163E, new String[] {MKT_MDL_SEG_CD })) {
                    return;
                }
            }
        }

        MDSETMsg mdseTMsg = commonLogic.getMdse(glblCmpyCd, mdseCd);
        if (mdseTMsg != null) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0165E, null)) {
                return;
            }
        }

        String mdseRgtnTpCd = pMsg.mdseRgtnTpCd_D.getValue();
        AMER_MSTRTMsgArray amerMstrTMsgArray;
        if (MDSE_RGTN_TP.MERCURY.equals(mdseRgtnTpCd)) {
            amerMstrTMsgArray = commonLogic.getAmerMstr(glblCmpyCd, mdseCd);
            if (amerMstrTMsgArray.length() == 0) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0147E, new String[] {MSG_ITEM_NUM })) {
                    return;
                }
            }

            AMER_CMPYTMsg amerCmpyTMsg = new AMER_CMPYTMsg();
            ZYPEZDItemValueSetter.setValue(amerCmpyTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(amerCmpyTMsg.amerCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(amerCmpyTMsg.amerMdseCd, mdseCd);
            amerCmpyTMsg = (AMER_CMPYTMsg) S21CacheTBLAccessor.findByKey(amerCmpyTMsg);
            if (amerCmpyTMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0148E, new String[] {MSG_ITEM_NUM, glblCmpyCd })) {
                    return;
                }
            } else {
                if (!AMER_XPND_TP_CD_1.equals(amerCmpyTMsg.amerXpndTpCd.getValue())) {
                    if (!commonLogic.setErrorMessage(param, idx, NMZM0149E, new String[] {MSG_ITEM_NUM, glblCmpyCd })) {
                        return;
                    }
                }
            }
        } else if (!MDSE_RGTN_TP.S21_PARTS.equals(mdseRgtnTpCd)) {
            amerMstrTMsgArray = commonLogic.getAmerMstr(glblCmpyCd, mdseCd);
            if (amerMstrTMsgArray.length() > 0) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0151E, new String[] {mdseCd, MSG_AMER_MSTR })) {
                    return;
                }
            }

            Map<String, Object> cusaPartsMap = NMZC004001Query.getInstance().getCusaParts(cusaPartsTbl, mdseCd);
            if (cusaPartsMap != null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0151E, new String[] {mdseCd, MSG_CUSA_PARTS })) {
                    return;
                }
            }

            cusaPartsMap = NMZC004001Query.getInstance().getCusaParts(cusaPartsTbl, mdseCd.trim());
            if (cusaPartsMap != null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0144E, new String[] {MDSE_CD })) {
                    return;
                }
            }

            String mdseCdTrim = mdseCd.trim();
            if (mdseCdTrim.length() == LG_8) {
                ORD_TAKE_MDSETMsg ordTakeMdseTMsg = commonLogic.getOrdTakeMdse(glblCmpyCd, mdseCdTrim);
                if (ordTakeMdseTMsg != null) {
                    if (!commonLogic.setErrorMessage(param, idx, NMZM0144E, new String[] {MDSE_CD })) {
                        return;
                    }
                }
            }

            Map<String, Object> amerMstrMap = NMZC004001Query.getInstance().getAmerMstrCmpy(glblCmpyCd, mdseCdTrim);
            if (amerMstrMap != null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0144E, new String[] {MDSE_CD })) {
                    return;
                }
            }
        }

        String mdseItemTpCd = pMsg.mdseItemTpCd_D.getValue();
        String coaMdseTpCd = pMsg.coaMdseTpCd_D.getValue();

        String[] msgAt = new String[2];
        INTG_PROD_CATG_CONVTMsg intgProdCatgConvTMsg = new INTG_PROD_CATG_CONVTMsg();
        intgProdCatgConvTMsg.setSQLID("002");
        intgProdCatgConvTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        intgProdCatgConvTMsg.setConditionValue("mdseItemTpCd01", mdseItemTpCd);
        intgProdCatgConvTMsg.setConditionValue("coaMdseTpCd01", coaMdseTpCd);
        INTG_PROD_CATG_CONVTMsgArray intgProdCatgConvTMsgArray = (INTG_PROD_CATG_CONVTMsgArray) S21ApiTBLAccessor.findByCondition(intgProdCatgConvTMsg);
        if (intgProdCatgConvTMsgArray.length() == 0) {
            MDSE_ITEM_TPTMsg mdseItemTpTMsg = commonLogic.getMdseItemTp(glblCmpyCd, mdseItemTpCd);
            if (mdseItemTpTMsg != null && hasValue(mdseItemTpTMsg.mdseItemTpDescTxt)) {
                msgAt[0] = mdseItemTpTMsg.mdseItemTpDescTxt.getValue();
            }

            COA_PROJTMsg coaProjTMsg = commonLogic.getCoaProj(glblCmpyCd, coaMdseTpCd);
            if (coaProjTMsg != null && hasValue(coaProjTMsg.coaProjDescTxt)) {
                msgAt[1] = coaProjTMsg.coaProjDescTxt.getValue();
            }

            if (!commonLogic.setErrorMessage(param, idx, NMZM0156E, msgAt)) {
                return;
            }
        }

        //V1.5
        //boolean checkStru = false;
        //String[] prodCtrlCd = {null, null, null, null, null };
        //if (hasValue(pMsg.zerothProdCtrlCd_M)) {
        //    prodCtrlCd[0] = pMsg.zerothProdCtrlCd_M.getValue();
        //    checkStru = true;
        //}
        //if (hasValue(pMsg.firstProdCtrlCd_M)) {
        //    prodCtrlCd[1] = pMsg.firstProdCtrlCd_M.getValue();
        //    checkStru = true;
        //}
        //if (hasValue(pMsg.scdProdCtrlCd_M)) {
        //    prodCtrlCd[2] = pMsg.scdProdCtrlCd_M.getValue();
        //    checkStru = true;
        //}
        //if (hasValue(pMsg.thirdProdCtrlCd_M)) {
        //    prodCtrlCd[3] = pMsg.thirdProdCtrlCd_M.getValue();
        //    checkStru = true;
        //}
        //if (hasValue(pMsg.frthProdCtrlCd_M)) {
        //    prodCtrlCd[4] = pMsg.frthProdCtrlCd_M.getValue();
        //    checkStru = true;
        //}
        //if (checkStru) {
        //    Map<String, Object> mdseStruMap = NMZC004001Query.getInstance().getMdseStru(glblCmpyCd, prodCtrlCd);
        //    if (mdseStruMap == null) {
        //        if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {MSG_MDSE_STRU })) {
        //            return;
        //        }
        //    }
        //}

        //QC#9346
        //if (MDSE_ITEM_TP.MAINTENANCE.equals(mdseItemTpCd) || MDSE_ITEM_TP.MAINTENANCE_CHARGES.equals(mdseItemTpCd)) {
        //    MDSE_TP_VAL_SETTMsgArray tMsgArray = commonLogic.getMdseTpValSet(glblCmpyCd, MDSE_TP_CTX_TP.SERVICE_ALLOCATION_TYPE_REQUIRED_ITEM, coaMdseTpCd);
        //
        //    if (tMsgArray.length() > 0 && !hasValue(pMsg.svcAllocTpCd_D)) {
        //        if (!commonLogic.setErrorMessage(param, idx, NMZM0163E, new String[] {SVC_ALLOC_TP_CD })) {
        //            return;
        //        }
        //    }
        //}
        Map<String, Object> mdseTpValSetMap = commonLogic.getMdseTpValSet(glblCmpyCd, MDSE_TP_CTX_TP.SERVICE_ALLOCATION_TYPE_REQUIRED_ITEM, mdseItemTpCd, coaMdseTpCd);
        if (mdseTpValSetMap != null && mdseTpValSetMap.get("MDSE_TP_VAL_SET_PK") != null && !hasValue(pMsg.svcAllocTpCd_D)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0163E, new String[] {SVC_ALLOC_TP_CD })) {
                return;
            }
        }

        if (!mdseCd.equals(pMsg.mdseCd_D.getValue()) || !mdseCd.equals(pMsg.mdseCd_S.getValue())) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0158E, new String[] {MDSE_CD, MSG_MDSE_CD })) {
                return;
            }
        }

        // General - Safety related
        if (ZYPConstant.FLG_ON_Y.equals(pMsg.hazMatFlg_S.getValue())) {
            if (!hasValue(pMsg.hazMatCd_S)) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0163E, new String[] {MSG_HAZ_MAT_CD })) {
                    return;
                }
            }
            if (!hasValue(pMsg.madeInCtryCd_S)) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0163E, new String[] {MSG_MADE_IN_CTRY_CD })) {
                    return;
                }
            }
        }

        // Inventory related
        String invtyCtrlFlg = pMsg.invtyCtrlFlg_M.getValue();

        if (MDSE_ITEM_TP.SOFTWARE.equals(mdseItemTpCd) && !SW_LIC_CTRL_TP.NO.equals(pMsg.swLicCtrlTpCd_D.getValue()) && !ZYPConstant.FLG_ON_Y.equals(invtyCtrlFlg)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0134E, null)) {
                return;
            }
        }

        //QC#9631
        if (MDSE_ITEM_TP.KIT.equals(mdseItemTpCd) && !ZYPConstant.FLG_ON_Y.equals(invtyCtrlFlg)) {
            if (!commonLogic.setErrorMessage(param, idx, NMAM8590E, null)) {
                return;
            }
        }

//        if (ZYPConstant.FLG_ON_Y.equals(invtyCtrlFlg) && !hasValue(pMsg.nmfcClsTpCd_D)) {
//            if (!commonLogic.setErrorMessage(param, idx, NMZM0163E, new String[] {MSG_NMFC_CLS_TP_CD })) {
//                return;
//            }
//        }

        if (MDSE_ITEM_TP.SET.equals(mdseItemTpCd) && ZYPConstant.FLG_ON_Y.equals(invtyCtrlFlg)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0133E, new String[] {MDSE_ITEM_TP_CD, MDSE_ITEM_TP_CD_SET, MSG_INVTY_CTRL_FLG, ZYPConstant.FLG_OFF_N })) {
                return;
            }
        }

        
        //QC#10449
        if (hasValue(pMsg.defSrcWhCd_D)) {
            Map<String, Object> rtlWhMap = NMZC004001Query.getInstance().getRtlWh(glblCmpyCd, pMsg.defSrcWhCd_D.getValue(), ZYPDateUtil.getSalesDate());
            if (rtlWhMap == null || rtlWhMap.get("RTL_WH_CD") == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {DEF_SRC_WH_CD })) {
                    return;
                }
            }
        }
        
        if (hasValue(pMsg.defSrcSubWhCd_D)) {
            if (!hasValue(pMsg.defSrcWhCd_D)) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0163E, new String[] {DEF_SRC_WH_CD })) {
                    return;
                }
            } else {
                //QC#10449
                //RTL_SWHTMsg tMsg = new RTL_SWHTMsg();
                //tMsg.setSQLID("004");
                //tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                //tMsg.setConditionValue("rtlWhCd01", pMsg.defSrcWhCd_D.getValue());
                //tMsg.setConditionValue("rtlSwhCd01", pMsg.defSrcSubWhCd_D.getValue());
                //RTL_SWHTMsgArray tMsgArray = (RTL_SWHTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
                //if (tMsgArray.length() == 0) {
                //    if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {MDS_RTL_SWH })) {
                //        return;
                //    }
                //}
                Map<String, Object> rtlWhSubWhMap = NMZC004001Query.getInstance().getRtlWhSubWh(glblCmpyCd, pMsg.defSrcWhCd_D.getValue(), pMsg.defSrcSubWhCd_D.getValue(), ZYPDateUtil.getSalesDate());
                if (rtlWhSubWhMap == null || rtlWhSubWhMap.get("RTL_SWH_CD") == null) {
                    if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {MDS_RTL_SWH })) {
                        return;
                    }
                }
            }
        }

        if (ZYPConstant.FLG_ON_Y.equals(pMsg.rtrnReqPrtFlg_D.getValue())) {
            if (!hasValue(pMsg.rtrnCtrlTpCd_D)) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0163E, new String[] {RTRN_CTRL_TP_CD })) {
                    return;
                }
            }
            if (!hasValue(pMsg.rtrnDsplTpCd_D)) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0163E, new String[] {RTRN_DSPL_TP_CD })) {
                    return;
                }
            }
        }

        //QC4507
        //if (ZYPConstant.FLG_ON_Y.equals(pMsg.instlBaseCtrlFlg_D.getValue())) {
        //    if (ZYPConstant.FLG_ON_Y.equals(pMsg.rcvSerTakeFlg_M.getValue()) && ZYPConstant.FLG_ON_Y.equals(pMsg.shpgSerTakeFlg_M.getValue())) {
        //        if (!commonLogic.setErrorMessage(param, idx, NMZM0157E, null)) {
        //            return;
        //        }
        //    }
        //}
        if (ZYPConstant.FLG_ON_Y.equals(pMsg.rcvSerTakeFlg_M.getValue()) || ZYPConstant.FLG_ON_Y.equals(pMsg.shpgSerTakeFlg_M.getValue())) {
        	if (!ZYPConstant.FLG_ON_Y.equals(pMsg.instlBaseCtrlFlg_D.getValue())) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0180E, null)) {
                    return;
                }
            }
        }
        
        //QC#9678
        if (hasValue(pMsg.mdseItemTpCd_D) 
        		&& (MDSE_ITEM_TP.SOFTWARE.equals(pMsg.mdseItemTpCd_D.getValue()) 
        				|| MDSE_ITEM_TP.SOFTWARE_LICENSE.equals(pMsg.mdseItemTpCd_D.getValue()))) {
        	if (ZYPCommonFunc.hasValue(pMsg.swLicCtrlTpCd_D)) {
        		SW_LIC_CTRL_TPTMsg swLicCtrlTpTMsg = new SW_LIC_CTRL_TPTMsg();
    	        ZYPEZDItemValueSetter.setValue(swLicCtrlTpTMsg.glblCmpyCd, glblCmpyCd);
    	        ZYPEZDItemValueSetter.setValue(swLicCtrlTpTMsg.swLicCtrlTpCd, pMsg.swLicCtrlTpCd_D);
    	        swLicCtrlTpTMsg = (SW_LIC_CTRL_TPTMsg) EZDTBLAccessor.findByKey(swLicCtrlTpTMsg);
    	        String swLicReqFlg = ZYPConstant.FLG_OFF_N;
    	        if (swLicCtrlTpTMsg != null) {
    	        	swLicReqFlg = swLicCtrlTpTMsg.swLicReqFlg.getValue();
    	        }
    	        if (ZYPConstant.FLG_ON_Y.equals(swLicReqFlg)) {
    	        	//Not Controlled
    	        	if (ZYPConstant.FLG_OFF_N.equals(pMsg.shpgSerTakeFlg_M.getValue()) && ZYPConstant.FLG_OFF_N.equals(pMsg.rcvSerTakeFlg_M.getValue())) {
            			//NMAM8591E=0,On Receipt&Shipment or On Shipment should be On when License Control is Yes.
    	                if (!commonLogic.setErrorMessage(param, idx, NMAM8591E, null)) {
    	                    return;
    	                }
    	        	}
    	        }
        	}
        }

        // Accounting related
        DFRD_ACCTG_RULETMsg dfrdAcctgRuleTMsg = new DFRD_ACCTG_RULETMsg();
        ZYPEZDItemValueSetter.setValue(dfrdAcctgRuleTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dfrdAcctgRuleTMsg.dfrdAcctgRuleCd, pMsg.dfrdAcctgRuleCd_D.getValue());
        dfrdAcctgRuleTMsg = (DFRD_ACCTG_RULETMsg) S21CacheTBLAccessor.findByKey(dfrdAcctgRuleTMsg);
        if (dfrdAcctgRuleTMsg != null) {
            boolean crctCmbn = false;
            if (ZYPConstant.FLG_ON_Y.equals(dfrdAcctgRuleTMsg.dfrdRevFlg.getValue()) && DFRD_INV_RULE.ADVANCED.equals(pMsg.dfrdInvRuleCd_D.getValue())) {
                crctCmbn = true;
            }
            if (ZYPConstant.FLG_OFF_N.equals(dfrdAcctgRuleTMsg.dfrdRevFlg.getValue()) && DFRD_INV_RULE.ARREARS.equals(pMsg.dfrdInvRuleCd_D.getValue())) {
                crctCmbn = true;
            }
            if (!crctCmbn) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0158E, new String[] {MSG_ACCT_INV_RULE, MSG_DFRD_AD })) {
                    return;
                }
            }
        }

        if (BigDecimal.ZERO.compareTo(pMsg.thisMthTotStdCostAmt_M.getValue()) == 1) {
            if (!commonLogic.setErrorMessage(param, idx, ZZZM9026E, new String[] {MSG_THIS_MTH_TOT_STD_COST_AMT })) {
                return;
            }
        //QC#10748
        //} else if (BigDecimal.ZERO.compareTo(pMsg.thisMthTotStdCostAmt_M.getValue()) == 0 && ZYPConstant.FLG_ON_Y.equals(invtyCtrlFlg)) {
        //    if (!commonLogic.setErrorMessage(param, idx, NMZM0162E, new String[] {MSG_INVTY_CTRL_FLG, MSG_ON, MSG_THIS_MTH_TOT_STD_COST_AMT })) {
        //        return;
        //    }
        }

        if (hasValue(pMsg.assetRecovCostAmt_D) && BigDecimal.ZERO.compareTo(pMsg.assetRecovCostAmt_D.getValue()) == 1) {
            if (!commonLogic.setErrorMessage(param, idx, ZZZM9026E, new String[] {MSG_ASSET_RECOV_COST_AMT })) {
                return;
            }
        }

        // Service related
        //if (!hasValue(pMsg.svcWtyTpCd_D)) {
        //    if (MDSE_ITEM_TP.ACCESSORY.equals(mdseItemTpCd) || MDSE_ITEM_TP.MACHINE.equals(mdseItemTpCd)) {
        //        if (!commonLogic.setErrorMessage(param, idx, NMZM0163E, new String[] {MSG_SVC_WTY_TP_CD })) {
        //            return;
        //        }
        //    }
        //}
        //Install Base Trackable
        if (hasValue(pMsg.svcWtyTpCd_D)) {
            if (!ZYPConstant.FLG_ON_Y.equals(pMsg.instlBaseCtrlFlg_D.getValue())) {
	            if (!commonLogic.setErrorMessage(param, idx, NMZM0158E, new String[] {INSTL_BASE_CTRL_FLG, ZYPConstant.FLG_ON_Y })) {
	            	return;
	            }
            }
        }

        if (ZYPConstant.FLG_ON_Y.equals(pMsg.iwrEnblFlg_D.getValue())) {
        	if (!ZYPConstant.FLG_ON_Y.equals(pMsg.instlBaseCtrlFlg_D.getValue())) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0133E, new String[] {MSG_IWR_ENB_FLG, ZYPConstant.FLG_ON_Y, MSG_INSTL_BASE_CTRL_FLG, ZYPConstant.FLG_ON_Y })) {
                    return;
                }
        	}
        }
        if (ZYPConstant.FLG_ON_Y.equals(pMsg.svcCallEnblFlg_D.getValue())) {
        	if (!ZYPConstant.FLG_ON_Y.equals(pMsg.instlBaseCtrlFlg_D.getValue())) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0133E, new String[] {MSG_SVC_CALL_ENB_FLG, ZYPConstant.FLG_ON_Y, MSG_INSTL_BASE_CTRL_FLG, ZYPConstant.FLG_ON_Y })) {
                    return;
                }
        	}
        }

        //if (!ZYPConstant.FLG_ON_Y.equals(pMsg.instlBaseCtrlFlg_D.getValue())) {
        //    if (ZYPConstant.FLG_ON_Y.equals(pMsg.svcCallEnblFlg_D.getValue())) {
        //        if (!commonLogic.setErrorMessage(param, idx, NMZM0133E, new String[] {MSG_SVC_CALL_ENB_FLG, ZYPConstant.FLG_ON_Y, MSG_INSTL_BASE_CTRL_FLG, ZYPConstant.FLG_ON_Y })) {
        //            return;
        //        }
        //    }
        //    if (ZYPConstant.FLG_ON_Y.equals(pMsg.iwrEnblFlg_D.getValue())) {
        //        if (!commonLogic.setErrorMessage(param, idx, NMZM0133E, new String[] {MSG_IWR_ENB_FLG, ZYPConstant.FLG_ON_Y, MSG_INSTL_BASE_CTRL_FLG, ZYPConstant.FLG_ON_Y })) {
        //            return;
        //        }
        //    }
        //}

        if (ZYPConstant.FLG_ON_Y.equals(pMsg.iwrEnblFlg_D.getValue()) && !hasValue(pMsg.iwrMdlCd_D.getValue())) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0163E, new String[] {IWR_MDL_CD })) {
                return;
            }
        }

        // Order related
        if (hasValue(pMsg.cpoMinOrdQty_M) && BigDecimal.ZERO.compareTo(pMsg.cpoMinOrdQty_M.getValue()) == 1) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0152E, new String[] {CPO_MIN_ORD_QTY })) {
                return;
            }
        }
        if (hasValue(pMsg.cpoMaxOrdQty_M) && BigDecimal.ZERO.compareTo(pMsg.cpoMaxOrdQty_M.getValue()) == 1) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0152E, new String[] {CPO_MAX_ORD_QTY })) {
                return;
            }
        }
        if (hasValue(pMsg.cpoIncrOrdQty_M) && BigDecimal.ZERO.compareTo(pMsg.cpoIncrOrdQty_M.getValue()) == 1) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0152E, new String[] {CPO_INCR_ORD_QTY })) {
                return;
            }
        }

        if (hasValue(pMsg.cpoMinOrdQty_M) && hasValue(pMsg.cpoMaxOrdQty_M) && pMsg.cpoMinOrdQty_M.getValue().compareTo(pMsg.cpoMaxOrdQty_M.getValue()) == 1) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0139E, new String[] {MSG_CPO_MAX_ORD_QTY, MSG_CPO_MIN_ORD_QTY })) {
                return;
            }
        }

        if (hasValue(pMsg.cpoIncrOrdQty_M) && hasValue(pMsg.cpoMinOrdQty_M) && pMsg.cpoIncrOrdQty_M.getValue().compareTo(pMsg.cpoMaxOrdQty_M.getValue().subtract(pMsg.cpoMinOrdQty_M.getValue())) == 1) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0141E, new String[] {MSG_CPO_INCR_ORD_QTY, MSG_INCR_QTY })) {
                return;
            }
        }

        if (hasValue(pMsg.cpoIncrOrdQty_M) && hasValue(pMsg.cpoMaxOrdQty_M) && pMsg.cpoIncrOrdQty_M.getValue().compareTo(pMsg.cpoMaxOrdQty_M.getValue()) == 1) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0141E, new String[] {MSG_CPO_INCR_ORD_QTY, MSG_CPO_MAX_ORD_QTY })) {
                return;
            }
        }

        // BOM related
        if (pMsg.xxBomList.getValidCount() > 0 && !MDSE_ITEM_TP.SET.equals(mdseItemTpCd) && !MDSE_ITEM_TP.KIT.equals(mdseItemTpCd)) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0155E, null)) {
                return;
            }
        }

        // Price related
        if (hasValue(pMsg.prcListEquipPrcAmt_P1) && BigDecimal.ZERO.compareTo(pMsg.prcListEquipPrcAmt_P1.getValue()) == 1) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0152E, new String[] {MSG_MSRP_PRC })) {
                return;
            }
        }

        if (hasValue(pMsg.prcListEquipPrcAmt_P2) && BigDecimal.ZERO.compareTo(pMsg.prcListEquipPrcAmt_P2.getValue()) == 1) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0152E, new String[] {MSG_FL_PRC })) {
                return;
            }
        }

        if (hasValue(pMsg.prcCatgCd_P1)) {
            if (!NMZC004001Query.getInstance().existPrcCatg(glblCmpyCd, slsDt, pMsg.prcCatgCd_P1.getValue(), PRC_LIST_TP.MSRP)) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {MSG_MSRP_PRC_CATG })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.prcCatgCd_P2)) {
            Map<String, Object> map = NMZC004001Query.getInstance().getPrcListStruTpCd(glblCmpyCd, slsDt, pMsg.prcCatgCd_P2.getValue());
            if (map == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {MSG_FL_PRC_CATG })) {
                    return;
                }
            }
        }

        if (hasValue(pMsg.svcMdlNm)) {
            Map<String, Object> map = NMZC004001Query.getInstance().getMdlId(glblCmpyCd, pMsg.svcMdlNm.getValue());
            if (map == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {MSG_SVC_MDL })) {
                    return;
                }
            } else {
                svcMdlId = (BigDecimal) map.get("MDL_ID");
            }
        }

        // 2019/06/10 QC#50763 Add Start
        // Product Code - Revenue Account Check
        // ZZ:ADMINISTRATION - 4%:Sales
        if (hasValue(pMsg.coaProdCd_M) && hasValue(pMsg.revCoaAcctCd_D)) {
            String revCoaAcctCd = pMsg.revCoaAcctCd_D.getValue();
            if (COA_PROD.ADMINISTRATION.equals(pMsg.coaProdCd_M.getValue()) && revCoaAcctCd.startsWith(REV_ACCT_START_WITH_CD)) {
                if (!commonLogic.setErrorMessage(param, idx, NMAM0086E, new String[] {MSG_PROD_CD_ZZ_01, MSG_PROD_CD_ZZ_02 })) {
                    return;
                }
            }
        }
        // 2019/06/10 QC#50763 Add End

        // 2022/02/17 QC#59693 Add Start
        // Default Source Procure Type force set
        if (ZYPCommonFunc.hasValue(pMsg.defSrcWhCd_D)) {
            String supplierWh = ZYPCodeDataUtil.getVarCharConstValue("NMAL0110_SUP_WH", glblCmpyCd);
            if (ZYPCommonFunc.hasValue(supplierWh)) {
                String[] supplierWhList = supplierWh.split(",");
                if (Arrays.asList(supplierWhList).contains(pMsg.defSrcWhCd_D.getValue())) {
                    ZYPEZDItemValueSetter.setValue(pMsg.defSrcProcrTpCd_D, PROCR_TP.SUPPLIER);
                } else {
                    ZYPEZDItemValueSetter.setValue(pMsg.defSrcProcrTpCd_D, PROCR_TP.WAREHOUSE);
                }
            }
        } else {
            pMsg.defSrcProcrTpCd_D.clear();
        }
        // 2022/02/17 QC#59693 Add End
    }

    private void checkBomList(NMZC004001PMsg param, int idx) {

        String glblCmpyCd = param.glblCmpyCd.getValue();
        String slsDt = param.slsDt.getValue();
        NMZC004002PMsg pMsg = param.NMZC004002PMsg.no(idx);
        String mdseCd = pMsg.mdseCd_M.getValue();

        int cnt = pMsg.xxBomList.getValidCount();
        boolean dup = false;

        String mdseItemTpCd = pMsg.mdseItemTpCd_D.getValue();
        if (MDSE_ITEM_TP.SET.equals(mdseItemTpCd) && this.cusaSetFlg) {
            List<Map<String, Object>> cusaCmpsnMap = NMZC004001Query.getInstance().getCusaCmpsnList(cusaGlblCmpyCd, cusaCmpsnTbl, mdseCd, pMsg.xxBomList.length());
            if (cnt != cusaCmpsnMap.size()) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0135E, null)) {
                    return;
                }
            }
        }

        NMZC004002_xxBomListPMsg bomPMsg;
        NMZC004002_xxBomListPMsg bomPMsg2;
        boolean dupOrdTake = false;
        boolean comb = false;
        String prntMdseCd = null;
        String childMdseCd = null;
        String childOrdTakeMdseCd = null;
        String mdseCmpsnTpCd = null;
        for (int i = 0; i < cnt; i++) {
            // 2017/01/27 S21_NA#17260-2 Add Start
            prntMdseCd = null;
            childMdseCd = null;
            childOrdTakeMdseCd = null;
            mdseCmpsnTpCd = null;
            // 2017/01/27 S21_NA#17260-2 Add End
            bomPMsg = pMsg.xxBomList.no(i);
            // Flag Check
            if (!commonLogic.verifyFlag(bomPMsg.baseCmptFlg_DC)) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {BASE_CMPT_FLG })) {
                    return;
                }
            }
            if (!commonLogic.verifyFlag(bomPMsg.mndCmptFlg_DC)) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {MND_CMPT_FLG })) {
                    return;
                }
            }
            if (!commonLogic.verifyFlag(bomPMsg.mainCmpsnFlg_C)) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {MND_CMPT_FLG })) {
                    return;
                }
            }

            // Code Table Check
            if (hasValue(bomPMsg.mdseCmpsnTpCd_C)) {
                mdseCmpsnTpCd = bomPMsg.mdseCmpsnTpCd_C.getValue();
                MDSE_CMPSN_TPTMsg mdseCmpsnTpTMsg = new MDSE_CMPSN_TPTMsg();
                ZYPEZDItemValueSetter.setValue(mdseCmpsnTpTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(mdseCmpsnTpTMsg.mdseCmpsnTpCd, mdseCmpsnTpCd);
                mdseCmpsnTpTMsg = (MDSE_CMPSN_TPTMsg) S21CacheTBLAccessor.findByKey(mdseCmpsnTpTMsg);
                if (mdseCmpsnTpTMsg == null) {
                    if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {MDSE_CMPSN_TP_CD })) {
                        return;
                    }
                }
            }

            // Master Check
            if (hasValue(bomPMsg.childMdseCd_C)) {
                MDSETMsg tMsg = commonLogic.getMdse(glblCmpyCd, bomPMsg.childMdseCd_C.getValue());
                if (tMsg == null) {
                    if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {CHILD_MDSE_CD })) {
                        return;
                    }
                }
            }

            if (hasValue(bomPMsg.childOrdTakeMdseCd_C)) {
                ORD_TAKE_MDSETMsg tMsg = commonLogic.getOrdTakeMdse(glblCmpyCd, bomPMsg.childOrdTakeMdseCd_C.getValue());
                if (tMsg == null) {
                    if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {CHILD_ORD_TAKE_MDSE_CD })) {
                        return;
                    }
                }
            }

            // Integrity Check
            prntMdseCd = bomPMsg.prntMdseCd_C.getValue();
            if (!mdseCd.equals(prntMdseCd)) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0158E, new String[] {MSE_PRNT_MDSE_CD_C, MSG_MDSE_CD })) {
                    return;
                }
            }

            if (BigDecimal.ONE.compareTo(bomPMsg.childMdseQty_C.getValue()) == 1) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0140E, new String[] {MSG_CHILD_MDSE_QTY_C, BigDecimal.ZERO.toString() })) {
                    return;
                }
            }

            if (slsDt.compareTo(bomPMsg.effFromDt_C.getValue()) == -1) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0153E, new String[] {MSG_EFF_FROM_DT_C, SLS_DT })) {
                    return;
                }
            }

            if (hasValue(bomPMsg.childMdseCd_C)) {
                childMdseCd = bomPMsg.childMdseCd_C.getValue();
                if (hasValue(bomPMsg.childOrdTakeMdseCd_C)) {
                    if (!commonLogic.setErrorMessage(param, idx, NMZM0159E, new String[] {CHILD_MDSE_CD_C, CHILD_ORD_TAKE_MDSE_CD })) {
                        return;
                    }
                }
            }

            if (hasValue(bomPMsg.childOrdTakeMdseCd_C)) {
                childOrdTakeMdseCd = bomPMsg.childOrdTakeMdseCd_C.getValue();
            }

            for (int j = 0; j < cnt; j++) {
                bomPMsg2 = pMsg.xxBomList.no(j);
                if (i != j && hasValue(bomPMsg.childMdseCd_C) && hasValue(bomPMsg2.childMdseCd_C)) {
                    if (childMdseCd.equals(bomPMsg2.childMdseCd_C.getValue())) {
                        dup = true;
                        break;
                    }
                }
                if (i != j && hasValue(bomPMsg.childOrdTakeMdseCd_C) && hasValue(bomPMsg2.childOrdTakeMdseCd_C)) {
                    if (childOrdTakeMdseCd.equals(bomPMsg2.childOrdTakeMdseCd_C.getValue())) {
                        dupOrdTake = true;
                        break;
                    }
                }
            }

            if (prntMdseCd.equals(childMdseCd) || prntMdseCd.equals(childOrdTakeMdseCd)) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0136E, null)) {
                    return;
                }
            }

            if (MDSE_ITEM_TP.KIT.equals(mdseItemTpCd) && hasValue(bomPMsg.childOrdTakeMdseCd_C)) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0160E, null)) {
                    return;
                }
            }

            if (hasValue(bomPMsg.childKitMatCd_C)) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {MSG_CHILD_KIT_MAT_CD_C })) {
                    return;
                }
            }

            if (slsDt.compareTo(bomPMsg.effFromDt_C.getValue()) == 1) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0166E, new String[] {EFF_FROM_DT_C })) {
                    return;
                }
            }

            if (hasValue(bomPMsg.mdseCmpsnTpCd_C)) {
                comb = false;
                if (MDSE_ITEM_TP.SET.equals(mdseItemTpCd) && MDSE_CMPSN_TP.SET_MDSE.equals(mdseCmpsnTpCd) && hasValue(childMdseCd)) {
                    comb = true;
                } else if (MDSE_ITEM_TP.SET.equals(mdseItemTpCd) && MDSE_CMPSN_TP.SET_ORDERTAKE_MDSE.equals(mdseCmpsnTpCd) && hasValue(childOrdTakeMdseCd)) {
                    comb = true;
                } else if (MDSE_ITEM_TP.KIT.equals(mdseItemTpCd) && MDSE_CMPSN_TP.KIT_MDSE.equals(mdseCmpsnTpCd) && hasValue(childMdseCd)) {
                    comb = true;
                }
                if (!comb) {
                    if (!commonLogic.setErrorMessage(param, idx, NMZM0161E, new String[] {MSG_MDSE_CMPSN_TP_CD_C, MSG_CHILD_ITEM_CD })) {
                        return;
                    }
                }

                if (comb && hasValue(bomPMsg.childMdseCd_C)) {
                    if (NMZC004001Query.getInstance().existCmpsnHierarchy10(glblCmpyCd, childMdseCd)) {
                        if (!commonLogic.setErrorMessage(param, idx, NMZM0145E, null)) {
                            return;
                        }
                    }
                    if (NMZC004001Query.getInstance().existCmpsnHierarchy10to8(glblCmpyCd, childMdseCd)) {
                        if (!commonLogic.setErrorMessage(param, idx, NMZM0145E, null)) {
                            return;
                        }
                    }
                } else if (comb && hasValue(bomPMsg.childOrdTakeMdseCd_C)) {
                    if (NMZC004001Query.getInstance().existCmpsnHierarchy8to10(glblCmpyCd, childOrdTakeMdseCd)) {
                        if (!commonLogic.setErrorMessage(param, idx, NMZM0145E, null)) {
                            return;
                        }
                    }
                    if (NMZC004001Query.getInstance().existCmpsnHierarchy8(glblCmpyCd, childOrdTakeMdseCd)) {
                        if (!commonLogic.setErrorMessage(param, idx, NMZM0145E, null)) {
                            return;
                        }
                    }
                }
            }

            if (MDSE_ITEM_TP.SET.equals(mdseItemTpCd) && this.cusaSetFlg) {
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("cusaCmpsnTbl", cusaCmpsnTbl);
                params.put("cusaGlblCmpyCd", cusaGlblCmpyCd);
                params.put("mdseCd", mdseCd);
                params.put("mdseCmpsnTpCd", mdseCmpsnTpCd);
                params.put("childMdseCd", childMdseCd);
                params.put("childOrdTakeMdseCd", childOrdTakeMdseCd);
                params.put("childMdseQty", bomPMsg.childMdseQty_C.getValue());
                // params.put("mainCmpsnFlg", bomPMsg.mainCmpsnFlg_C.getValue()); // 2017/01/27 S21_NA#17260-2 Del
                if (!NMZC004001Query.getInstance().existCusaCmpsn(params)) {
                    if (!commonLogic.setErrorMessage(param, idx, NMZM0135E, null)) {
                        return;
                    }
                }
            }
        }
        if (dup || dupOrdTake) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0144E, new String[] {MSG_CHILD_MDSE_CD_C })) {
                return;
            }
        }

    }

    private void checkStoreList(NMZC004001PMsg param, int idx) {

        NMZC004002PMsg pMsg = param.NMZC004002PMsg.no(idx);

        //QC#16628
        //If IW does not send base package UOM as EA, S21 exchange it to EA.
        NMZC004002_xxStoreListPMsg firstStorePMsg = new NMZC004002_xxStoreListPMsg();
        boolean basePkgUomFlg = false;
        for (int i = 0; i < pMsg.xxStoreList.getValidCount(); i++) {
            String basePkgUomCd = "";
            PKG_UOM_CLSTMsg pkgUomClsTMsg = commonLogic.getPkgUomClsTMsg(param.glblCmpyCd.getValue(), pMsg.pkgUomClsCd_D.getValue());
            if (pkgUomClsTMsg != null) {
                basePkgUomCd = pkgUomClsTMsg.basePkgUomCd.getValue();
            }
            if (basePkgUomCd.equals(pMsg.xxStoreList.no(i).pkgUomCd_S.getValue())) {
                ZYPEZDItemValueSetter.setValue(pMsg.xxStoreList.no(i).pkgUomCd_S, PKG_UOM.EACH);
                ZYPEZDItemValueSetter.setValue(pMsg.xxStoreList.no(i).pkgUomCd_DS, PKG_UOM.EACH);
                ZYPEZDItemValueSetter.setValue(pMsg.xxStoreList.no(i).primPkgUomFlg_DS, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(pMsg.xxStoreList.no(i).inEachQty_S, BigDecimal.ONE);
                basePkgUomFlg = true;
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.xxStoreList.no(i).primPkgUomFlg_DS, ZYPConstant.FLG_OFF_N);
            }
            // 2020/01/17 QC#55451 Mod Start
            //if (i == 0) {
            //    //copy first line
            //    EZDMsg.copy(pMsg.xxStoreList.no(i), null, firstStorePMsg, null);
            //}
            if (!ZYPCommonFunc.hasValue(firstStorePMsg.pkgUomCd_S) || PKG_UOM.EACH_UNBOX.equals(firstStorePMsg.pkgUomCd_S.getValue())) {
                //copy first line
                EZDMsg.copy(pMsg.xxStoreList.no(i), null, firstStorePMsg, null);
            }
            // 2020/01/17 QC#55451 Mod End
        }
        //If no base package UOM, S21 add it to xxStoreList and clear dimensions on no base package UOM.
        if (!basePkgUomFlg) {

            for (int i = 0; i < pMsg.xxStoreList.getValidCount(); i++) {
                // 2020/01/17 QC#55451 Mod Start
                if (!PKG_UOM.EACH_UNBOX.equals(pMsg.xxStoreList.no(i).pkgUomCd_S.getValue())) {
                    ZYPEZDItemValueSetter.setValue(pMsg.xxStoreList.no(i).inInchLg_S, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(pMsg.xxStoreList.no(i).inInchWdt_S, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(pMsg.xxStoreList.no(i).inInchHgt_S, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(pMsg.xxStoreList.no(i).inPoundWt_S, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(pMsg.xxStoreList.no(i).inInchVol_S, BigDecimal.ZERO);
                }
                // 2020/01/17 QC#55451 Mod End
            }

            int cnt = pMsg.xxStoreList.getValidCount() + 1;
            pMsg.xxStoreList.setValidCount(cnt);
            EZDMsg.copy(firstStorePMsg, null, pMsg.xxStoreList.no(cnt-1), null);
            ZYPEZDItemValueSetter.setValue(pMsg.xxStoreList.no(cnt-1).pkgUomCd_S, PKG_UOM.EACH);
            ZYPEZDItemValueSetter.setValue(pMsg.xxStoreList.no(cnt-1).pkgUomCd_DS, PKG_UOM.EACH);
            ZYPEZDItemValueSetter.setValue(pMsg.xxStoreList.no(cnt-1).primPkgUomFlg_DS, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(pMsg.xxStoreList.no(cnt-1).inEachQty_S, BigDecimal.ONE);
            // 2020/01/17 QC#55451 Add Start
            pMsg.xxStoreList.no(cnt-1).inInchLgUomCd_S.clear();
            pMsg.xxStoreList.no(cnt-1).inInchWdtUomCd_S.clear();
            pMsg.xxStoreList.no(cnt-1).inInchHgtUomCd_S.clear();
            pMsg.xxStoreList.no(cnt-1).inPoundWtUomCd_S.clear();
            if (PKG_UOM.EACH_UNBOX.equals(firstStorePMsg.pkgUomCd_S.getValue())) {
                ZYPEZDItemValueSetter.setValue(pMsg.xxStoreList.no(cnt-1).inInchLg_S, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(pMsg.xxStoreList.no(cnt-1).inInchWdt_S, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(pMsg.xxStoreList.no(cnt-1).inInchHgt_S, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(pMsg.xxStoreList.no(cnt-1).inPoundWt_S, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(pMsg.xxStoreList.no(cnt-1).inInchVol_S, BigDecimal.ZERO);
            }
            // 2020/01/17 QC#55451 Add End
        }
        
        NMZC004002_xxStoreListPMsg storePMsg;
        NMZC004002_xxStoreListPMsg storePmsg2;
        boolean existEA = false;
        boolean existPrim = false;
        boolean dupPrim = false;
        boolean dup = false;
        int cnt = pMsg.xxStoreList.getValidCount();
        for (int i = 0; i < cnt; i++) {
            storePMsg = pMsg.xxStoreList.no(i);

            // Flag Check
            //QC#16628
            //if (!commonLogic.verifyFlag(storePMsg.primPkgUomFlg_DS)) {
            //    if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {PRIM_PKGUOM_FLG })) {
            //        return;
            //    }
            //}
            if (!commonLogic.verifyFlag(storePMsg.upcOrScc14LbFlg_S)) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {UPC_OR_SCC14_LB_FLG })) {
                    return;
                }
            }
            
            // Integrity Check
            // General - UOM related
            if (!pMsg.mdseCd_M.getValue().equals(storePMsg.mdseCd_S.getValue())) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0158E, new String[] {MSG_MDSE_CD_S, MSG_MDSE_CD })) {
                    return;
                }
            }

            String msg = null;
            String pkgUomCd = storePMsg.pkgUomCd_S.getValue();

            if (PKG_UOM.EACH.equals(pkgUomCd)) {
                existEA = true;
                msg = MSG_PKG_UOM_CD_EA;
            //QC#16628
            //} else if (PKG_UOM.EACH.equals(pkgUomCd)) {
            } else if (PKG_UOM.EACH_UNBOX.equals(pkgUomCd)) {
                msg = MSG_PKG_UOM_CD_EU;
            }

            if (msg != null) {
                if (!hasValue(storePMsg.inInchLg_S)) {
                    if (!commonLogic.setErrorMessage(param, idx, NMZM0162E, new String[] {MSG_UOM, msg, MSG_IN_INCH_LG })) {
                        return;
                    }
                }
                if (!hasValue(storePMsg.inInchWdt_S)) {
                    if (!commonLogic.setErrorMessage(param, idx, NMZM0162E, new String[] {MSG_UOM, msg, MSG_IN_INCH_WDT })) {
                        return;
                    }
                }
                if (!hasValue(storePMsg.inInchHgt_S)) {
                    if (!commonLogic.setErrorMessage(param, idx, NMZM0162E, new String[] {MSG_UOM, msg, MSG_IN_INCH_HGT })) {
                        return;
                    }
                }
                if (!hasValue(storePMsg.inPoundWt_S)) {
                    if (!commonLogic.setErrorMessage(param, idx, NMZM0162E, new String[] {MSG_UOM, msg, MSG_IN_POUND_WT })) {
                        return;
                    }
                }
                // 2019/12/16 QC#54859 Add Start
                if (PKG_UOM.EACH_UNBOX.equals(pkgUomCd)) {
                    if (hasValue(storePMsg.inInchLg_S) && !hasValue(storePMsg.inInchLgUomCd_S)) {
                        if (!commonLogic.setErrorMessage(param, idx, NMZM0162E, new String[] {MSG_UOM, msg,  MSG_IN_INCH_LG_UOM_CD})) {
                            return;
                        }
                    }
                    if (hasValue(storePMsg.inInchWdt_S) && !hasValue(storePMsg.inInchWdtUomCd_S)) {
                        if (!commonLogic.setErrorMessage(param, idx, NMZM0162E, new String[] {MSG_UOM, msg,  MSG_IN_INCH_WDT_UOM_CD})) {
                            return;
                        }
                    }
                    if (hasValue(storePMsg.inInchHgt_S) && !hasValue(storePMsg.inInchHgtUomCd_S)) {
                        if (!commonLogic.setErrorMessage(param, idx, NMZM0162E, new String[] {MSG_UOM, msg,  MSG_IN_INCH_HGT_UOM_CD})) {
                            return;
                        }
                    }
                    if (hasValue(storePMsg.inPoundWt_S) && !hasValue(storePMsg.inPoundWtUomCd_S)) {
                        if (!commonLogic.setErrorMessage(param, idx, NMZM0162E, new String[] {MSG_UOM, msg,  MSG_IN_POUND_WT_UOM_CD})) {
                            return;
                        }
                    }
                }
                // 2019/12/16 QC#54859 Add End
            }

            for (int j = 0; j < cnt; j++) {
                storePmsg2 = pMsg.xxStoreList.no(j);
                if (i != j && hasValue(storePmsg2.pkgUomCd_S) && pkgUomCd.equals(storePmsg2.pkgUomCd_S.getValue())) {
                    dup = true;
                    break;
                }
            }

            if (ZYPConstant.FLG_ON_Y.equals(storePMsg.primPkgUomFlg_DS.getValue())) {
                if (existPrim) {
                    dupPrim = true;
                }
                existPrim = true;
            }

            //QC#16628
            //PKG_UOMTMsg tMsg = new PKG_UOMTMsg();
            //tMsg.setSQLID("006");
            //tMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
            //tMsg.setConditionValue("pkgUomCd01", storePMsg.pkgUomCd_S.getValue());
            //tMsg.setConditionValue("pkgUomClsCd01", pMsg.pkgUomClsCd_D.getValue());
            //PKG_UOMTMsgArray tMsgArray = (PKG_UOMTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
            //if (tMsgArray.length() == 0) {
            if (!PKG_UOM.EACH_UNBOX.equals(storePMsg.pkgUomCd_S.getValue())) { // 2020/02/20 QC#55844 Add
                if (!commonLogic.existsPkgUomAndPkgUomClsReln(param.glblCmpyCd.getValue(), pMsg.pkgUomClsCd_D.getValue(), storePMsg.pkgUomCd_S.getValue())) {
                    if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {MSG_PKG_UOM_CLS_CD })) {
                        return;
                    }
                }
            }
        }
        if (!existEA) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0163E, new String[] {MSG_UOM_EA })) {
                return;
            }
        }
        if (dup) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0144E, new String[] {MSG_PKG_UOM_CD })) {
                return;
            }
        }
        if (!existPrim) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0163E, new String[] {MSG_PRIM_PKG_UOM_FLG })) {
                return;
            }
        }
        if (dupPrim) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0144E, new String[] {MSG_PRIM_PKG_UOM_FLG })) {
                return;
            }
        }
    }

    private void checkSerNumList(NMZC004001PMsg param, int idx) {

        NMZC004002PMsg pMsg = param.NMZC004002PMsg.no(idx);

        NMZC004002_xxSerNumListPMsg serPMsg;
        for (int i = 0; i < pMsg.xxSerNumList.getValidCount(); i++) {
            serPMsg = pMsg.xxSerNumList.no(i);
            
            // Integrity Check
            if (!pMsg.mdseCd_M.getValue().equals(serPMsg.mdseCd.getValue())) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0158E, new String[] {MSG_MDSE_CD_SN, MSG_MDSE_CD })) {
                    return;
                }
            }

            if (serPMsg.fromSerNum.getValue().length() != serPMsg.thruSerNum.getValue().length()) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0142E, new String[] {MSG_FROM_LEN, MSG_TO_LEN })) {
                    return;
                }
            }
        }
    }

    private void checkSupdList(NMZC004001PMsg param, int idx) {

        String glblCmpyCd = param.glblCmpyCd.getValue();
        NMZC004002PMsg pMsg = param.NMZC004002PMsg.no(idx);
        String mdseCd = pMsg.mdseCd_M.getValue();

        NMZC004002_xxSupdListPMsg supdPMsg;
        NMZC004002_xxSupdListPMsg supdPMsg2;
        int cnt = pMsg.xxSupdList.getValidCount();
        boolean dup = false;
        for (int i = 0; i < cnt; i++) {
            supdPMsg = pMsg.xxSupdList.no(i);

            // Flag Check
            if (!commonLogic.verifyFlag(supdPMsg.incgFlg)) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {INCG_FLG })) {
                    return;
                }
            }

            // Master Check
            if (hasValue(supdPMsg.supdToMdseCd)) {
                MDSETMsg tMsg = commonLogic.getMdse(glblCmpyCd, supdPMsg.supdToMdseCd.getValue());
                if (tMsg == null) {
                    if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {SUPD_TO_MDSE_CD_SR })) {
                        return;
                    }
                }
            }

            // Integration Check
            String supdMdseCd = null;
            supdMdseCd = supdPMsg.mdseCd.getValue();
            if (!mdseCd.equals(supdMdseCd)) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0158E, new String[] {MSG_MDSE_CD_SD, MSG_MDSE_CD })) {
                    return;
                }
            }
            if (hasValue(supdPMsg.supdFromMdseCd) && !mdseCd.equals(supdPMsg.supdFromMdseCd.getValue())) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0158E, new String[] {MSG_MDSE_CD_SD, mdseCd })) {
                    return;
                }
            }

            for (int j = 0; j < cnt; j++) {
                supdPMsg2 = pMsg.xxSupdList.no(j);
                if (i != j && supdPMsg.supdToMdseCd.getValue().equals(supdPMsg2.supdToMdseCd.getValue())) {
                    dup = true;
                    break;
                }
            }

            if (commonLogic.usableMdseCd8(glblCmpyCd, supdMdseCd, AMER_MDSE_CLS_TP_CD_A)) {
                // 01/27/2017 QC#17078 Mod Start
//                if (mdseCd.substring(0, LG_8).equals(supdPMsg.supdToMdseCd.getValue().substring(0, LG_8))) {
//                    skipSupdCratFlg = ZYPConstant.FLG_ON_Y;
//                }
                String rgstMdseCd = mdseCd;
                if (rgstMdseCd.length() > 8) {
                    rgstMdseCd = rgstMdseCd.substring(0, LG_8);
                }
                String supdToMdseCd = supdPMsg.supdToMdseCd.getValue();
                if (supdToMdseCd.length() > 8) {
                    supdToMdseCd = supdToMdseCd.substring(0, LG_8);
                }

                if (rgstMdseCd.equals(supdToMdseCd)) {
                    skipSupdCratFlg = ZYPConstant.FLG_ON_Y;
                }
                // 01/27/2017 QC#17078 Mod End
            }
        }
        if (dup) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0144E, new String[] {SUPD_TO_MDSE_CD })) {
                return;
            }
        }
    }

    private void checkRelList(NMZC004001PMsg param, int idx) {

        String glblCmpyCd = param.glblCmpyCd.getValue();
        NMZC004002PMsg pMsg = param.NMZC004002PMsg.no(idx);

        NMZC004002_xxRelListPMsg relPMsg;
        int cnt = pMsg.xxRelList.getValidCount();
        for (int i = 0; i < cnt; i++) {
            relPMsg = pMsg.xxRelList.no(i);

            // Code Table Check
            if (hasValue(relPMsg.mdseItemRelnTpCd)) {
                MDSE_ITEM_RELN_TPTMsg mdseItemRelnTpTMsg = new MDSE_ITEM_RELN_TPTMsg();
                ZYPEZDItemValueSetter.setValue(mdseItemRelnTpTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(mdseItemRelnTpTMsg.mdseItemRelnTpCd, relPMsg.mdseItemRelnTpCd.getValue());
                mdseItemRelnTpTMsg = (MDSE_ITEM_RELN_TPTMsg) S21CacheTBLAccessor.findByKey(mdseItemRelnTpTMsg);
                if (mdseItemRelnTpTMsg == null) {
                    if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {MDSE_CMPSN_TP_CD })) {
                        return;
                    }
                }
            }

            // Master Check
            String relnMdseCd = null;
            relnMdseCd = relPMsg.relnMdseCd.getValue();
            MDSETMsg tMsg = commonLogic.getMdse(glblCmpyCd, relnMdseCd);
            if (tMsg == null) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {RELN_MDSE_CD_F })) {
                    return;
                }
            }

            // Integrity Check
            if (!pMsg.mdseCd_M.getValue().equals(relPMsg.mdseCd.getValue())) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0158E, new String[] {MSG_MDSE_CD_F, MSG_MDSE_CD })) {
                    return;
                }
            }

            for (int j = 0; j < cnt; j++) {
                if (i != j && relnMdseCd.equals(pMsg.xxRelList.no(j).relnMdseCd.getValue())) {
                    if (!commonLogic.setErrorMessage(param, idx, NMZM0144E, new String[] {MSG_MDSE_CD_F })) {
                        return;
                    }
                }
            }
        }
    }

    private void checkCustXrefList(NMZC004001PMsg param, int idx) {

        NMZC004002PMsg pMsg = param.NMZC004002PMsg.no(idx);

        NMZC004002_xxCustXrefListPMsg custPMsg;
        NMZC004002_xxCustXrefListPMsg custPMsg2;
        int cnt = pMsg.xxCustXrefList.getValidCount();
        for (int i = 0; i < cnt; i++) {
            custPMsg = pMsg.xxCustXrefList.no(i);

            // Flag Check
            if (!hasValue(custPMsg.custMdseXrefEnblFlg)) {
                ZYPEZDItemValueSetter.setValue(custPMsg.custMdseXrefEnblFlg, ZYPConstant.FLG_OFF_N);
            } else {
                if (!commonLogic.verifyFlag(custPMsg.custMdseXrefEnblFlg)) {
                    if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {CUST_MDSE_XREF_ENBL_FLG })) {
                        return;
                    }
                }
            }

            // Master Check
            String dsAcctNum = null;
            if (hasValue(custPMsg.dsAcctNum)) {
                dsAcctNum = custPMsg.dsAcctNum.getValue();
                SELL_TO_CUSTTMsg tMsg = new SELL_TO_CUSTTMsg();
                tMsg.setSQLID("001");
                tMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
                tMsg.setConditionValue("sellToCustCd01", dsAcctNum);
                SELL_TO_CUSTTMsgArray tMsgArray = (SELL_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
                if (tMsgArray.length() == 0) {
                    if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {DS_ACCT_NUM_X })) {
                        return;
                    }
                }
            }

            // Integrity Check
            if (!pMsg.mdseCd_M.getValue().equals(custPMsg.mdseCd.getValue())) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0158E, new String[] {MSG_MDSE_CD_X, MSG_MDSE_CD })) {
                    return;
                }
            }

            for (int j = 0; j < cnt; j++) {
                custPMsg2 = pMsg.xxCustXrefList.no(j);
                if (i != j && dsAcctNum.equals(custPMsg2.dsAcctNum.getValue()) && custPMsg.custMdseCd.getValue().equals(custPMsg2.custMdseCd.getValue())) {
                    if (!commonLogic.setErrorMessage(param, idx, NMZM0144E, new String[] {MSG_DS_ACCT_NUM_CUST_MDSE_CD })) {
                        return;
                    }
                }
            }
        }
    }

    private void checkAslList(NMZC004001PMsg param, int idx) {

        String glblCmpyCd = param.glblCmpyCd.getValue();
        String slsDt = param.slsDt.getValue();
        NMZC004002PMsg pMsg = param.NMZC004002PMsg.no(idx);

        NMZC004002_xxAslListPMsg aslPMsg;
        NMZC004002_xxAslListPMsg aslPMsg2;
        int cnt = pMsg.xxAslList.getValidCount();
        for (int i = 0; i < cnt; i++) {
            aslPMsg = pMsg.xxAslList.no(i);

            // 2023/04/07 QC#61371 START
            // Check Supplier Item Code
            String splyItemNumMaxLen = NMZC004001CommonLogic.getSupplierItemCodeMaxLength(glblCmpyCd, aslPMsg.vndCd.getValue());
            if (ZYPCommonFunc.hasValue(splyItemNumMaxLen)) {
                if (aslPMsg.splyItemNum.getValue().length() > Integer.parseInt(splyItemNumMaxLen)) {
                    if (!commonLogic.setErrorMessage(param, idx, NPAM1652E, new String[] {SPLY_ITEM_CD, splyItemNumMaxLen })) {
                        return;
                    }
                }
            }
            // 2023/04/07 QC#61371 END

            // Flag Check
            if (!hasValue(aslPMsg.primSplyFlg)) {
                ZYPEZDItemValueSetter.setValue(aslPMsg.primSplyFlg, ZYPConstant.FLG_OFF_N);
            } else {
                if (!commonLogic.verifyFlag(aslPMsg.primSplyFlg)) {
                    if (!commonLogic.setErrorMessage(param, idx, NMZM0143E, new String[] {PRIM_SPLY_FLG })) {
                        return;
                    }
                }
            }

            // Code Table Check
            if (hasValue(aslPMsg.vndUomCd)) {
                VND_UOMTMsg vndUomTMsg = new VND_UOMTMsg();
                ZYPEZDItemValueSetter.setValue(vndUomTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(vndUomTMsg.vndUomCd, aslPMsg.vndUomCd.getValue());
                vndUomTMsg = (VND_UOMTMsg) S21CacheTBLAccessor.findByKey(vndUomTMsg);
                if (vndUomTMsg == null) {
                    if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {VND_UOM_CD })) {
                        return;
                    }
                }
            }

            // Master Check
            if (hasValue(aslPMsg.prntVndCd)) {
                PRNT_VNDTMsg tMsg = new PRNT_VNDTMsg();
                tMsg.setSQLID("001");
                tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                tMsg.setConditionValue("prntVndCd01", aslPMsg.prntVndCd.getValue());
                tMsg.setConditionValue("inacDt01", slsDt);
                PRNT_VNDTMsgArray tMsgArray = (PRNT_VNDTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
                if (tMsgArray.length() == 0) {
                    if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {PRNT_VND_CD_A })) {
                        return;
                    }
                }
            }

            if (hasValue(aslPMsg.coaMdseTpCd)) {
                COA_PROJTMsg tMsg = commonLogic.getCoaProj(glblCmpyCd, aslPMsg.coaMdseTpCd.getValue());
                if (tMsg == null) {
                    if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {COA_MDSE_TP_CD })) {
                        return;
                    }
                }
            }

            // Integrity Check
            Map<String, Object> map = new HashMap<String, Object>();
            if (hasValue(aslPMsg.aslNm)) {
                ASL_HDRTMsg aslHdrTMsg = new ASL_HDRTMsg();
                aslHdrTMsg.setSQLID("001");
                aslHdrTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                aslHdrTMsg.setConditionValue("prntVndCd01", aslPMsg.prntVndCd.getValue());
                aslHdrTMsg.setConditionValue("aslNm01", aslPMsg.aslNm.getValue());
                ASL_HDRTMsgArray aslHdrTMsgArray = (ASL_HDRTMsgArray) S21ApiTBLAccessor.findByCondition(aslHdrTMsg);
                if (aslHdrTMsgArray.length() == 0) {
                	 // QC#52531 Mod Start
//                    if (!commonLogic.setErrorMessage(param, idx, NMZM0138E, new String[] {MSG_ASL_HDR })) {
//                        return;
//                    }
                	// set Supplier Name
                	String supplierName = commonLogic.getSupplierName(param.glblCmpyCd.getValue(), aslPMsg.prntVndCd.getValue());
                    map.put("ASL_NM", supplierName);
                	// QC#52531 Mod End
                    aslHdrList.add(map);
                } else {
                    map.put("ASL_HDR_PK", aslHdrTMsgArray.no(0).aslHdrPk.getValue());
                    map.put("ASL_NM", aslHdrTMsgArray.no(0).aslNm.getValue());
                    map.put("ASL_DESC_TXT", aslHdrTMsgArray.no(0).aslDescTxt.getValue());
                    map.put("ASL_START_DT", aslHdrTMsgArray.no(0).aslStartDt.getValue());
                    map.put("ASL_END_DT", aslHdrTMsgArray.no(0).aslEndDt.getValue());
                    aslHdrList.add(map);
                }
            } else {
                map.put("ASL_HDR_PK", BigDecimal.ZERO);
                // QC#52531 Mod Start
                // set Supplier Name
            	String supplierName = commonLogic.getSupplierName(param.glblCmpyCd.getValue(), aslPMsg.prntVndCd.getValue());
                map.put("ASL_NM", supplierName);
                // QC#52531 Mod End
                aslHdrList.add(map);
            }

            if (!pMsg.mdseCd_M.getValue().equals(aslPMsg.mdseCd.getValue())) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0158E, new String[] {MSG_MDSE_CD_A, MSG_MDSE_CD })) {
                    return;
                }
            }

            if (hasValue(aslPMsg.effThruDt) && ZYPDateUtil.compare(aslPMsg.effFromDt.getValue(), aslPMsg.effThruDt.getValue()) == 1) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0153E, new String[] {MSG_EFF_THRU_DT_A, MSG_EFF_FROM_DT_A })) {
                    return;
                }
            }

            if (ZYPDateUtil.compare(slsDt, aslPMsg.effFromDt.getValue()) == 1) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0153E, new String[] {MSG_EFF_FROM_DT_A, SLS_DT })) {
                    return;
                }
            }

            for (int j = 0; j < cnt; j++) {
                aslPMsg2 = pMsg.xxAslList.no(j);
                if (i != j && aslPMsg.splyItemNum.getValue().equals(aslPMsg2.splyItemNum.getValue()) && aslPMsg.vndCd.getValue().equals(aslPMsg2.vndCd.getValue())) {
                    if (!commonLogic.setErrorMessage(param, idx, NMZM0144E, new String[] {MSG_SPLY_ITEM_NUM_VND_CD })) {
                        return;
                    }
                }
            }

            String prntVndCd = aslPMsg.prntVndCd.getValue();
            String vndCd = aslPMsg.vndCd.getValue();

            BigDecimal aslHdrPk = (BigDecimal) aslHdrList.get(i).get("ASL_HDR_PK");
            if (aslHdrPk != null && !BigDecimal.ZERO.equals(aslHdrPk)) {
                aslHdrPk = (BigDecimal) aslHdrList.get(i).get("ASL_HDR_PK");

                ASL_QLFY_RELNTMsg aslQlfyRelnTMsg = new ASL_QLFY_RELNTMsg();
                aslQlfyRelnTMsg.setSQLID("001");
                aslQlfyRelnTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                aslQlfyRelnTMsg.setConditionValue("aslHdrPk01", aslHdrPk);
                aslQlfyRelnTMsg.setConditionValue("aslQlfyTpCd01", ASL_QLFY_TP.BIG_DEAL_SPECIFIC);
                ASL_QLFY_RELNTMsgArray aslQlfyRelnTMsgArray = (ASL_QLFY_RELNTMsgArray) S21ApiTBLAccessor.findByCondition(aslQlfyRelnTMsg);

                String bigDeal = null;
                if (aslQlfyRelnTMsgArray.length() > 0) {
                    bigDeal = ZYPConstant.FLG_ON_Y;
                }

                Map<String, Object> params = new HashMap<String, Object>();
                params.put("glblCmpyCd", glblCmpyCd);
                params.put("bigDeal", bigDeal);
                params.put("prntVndCd", prntVndCd);
                params.put("aslHdrPk", aslHdrPk);
                params.put("vndCd", vndCd);
                params.put("mdseCd", aslPMsg.mdseCd.getValue());
                params.put("effFromDt", aslPMsg.effFromDt.getValue());
                params.put("effThruDt", aslPMsg.effFromDt.getValue());
                params.put("aslQlfyTpCd", ASL_QLFY_TP.BIG_DEAL_SPECIFIC);
                // Add Start QC#9073 2016/05/27
                params.put("maxDate", MAX_DATE_VALUE);
                // Add Start QC#9073 2016/05/27
                if (NMZC004001Query.getInstance().isDupAsl(params)) {
                    if (!commonLogic.setErrorMessage(param, idx, NMZM0144E, new String[] {MSG_ASL_LINE })) {
                        return;
                    }
                }
            }

            if (!NMZC004001Query.getInstance().existPrimVnd(glblCmpyCd, prntVndCd, vndCd)) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {MSG_PRIM_VND })) {
                    return;
                }
            }
        }
    }
    
    // QC#4342
    private void checkTermCondOptList(NMZC004001PMsg param, int idx) {

        NMZC004002PMsg pMsg = param.NMZC004002PMsg.no(idx);

        NMZC004002_xxTermListPMsg termPMsg;
        NMZC004002_xxTermListPMsg termPMsg2;
        int cnt = pMsg.xxTermList.getValidCount();
        int cntRspTm = 0;
        for (int i = 0; i < cnt; i++) {
            termPMsg = pMsg.xxTermList.no(i);

            // Master Check
            String termCondOptTpCd = null;
            if (hasValue(termPMsg.termCondOptTpCd)) {
                termCondOptTpCd = termPMsg.termCondOptTpCd.getValue();
                TERM_COND_OPT_TPTMsg tMsg = new TERM_COND_OPT_TPTMsg();
                tMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
                tMsg.termCondOptTpCd.setValue(termPMsg.termCondOptTpCd.getValue());
                tMsg = (TERM_COND_OPT_TPTMsg) S21ApiTBLAccessor.findByKey(tMsg);
                if (tMsg == null) {
                    if (!commonLogic.setErrorMessage(param, idx, NMZM0154E, new String[] {MDSE_TERM_COND_OPT_TP_CD_T })) {
                        return;
                    }
                } else {
                    if (ZYPConstant.FLG_ON_Y.equals(tMsg.rspTmTpFlg.getValue())) {
                        cntRspTm++;
                    }
                }
            }

            // Integrity Check
            if (!pMsg.mdseCd_M.getValue().equals(termPMsg.mdseCd.getValue())) {
                if (!commonLogic.setErrorMessage(param, idx, NMZM0158E, new String[] {MSG_MDSE_CD_X, MSG_MDSE_CD })) {
                    return;
                }
            }
            for (int j = 0; j < cnt; j++) {
                termPMsg2 = pMsg.xxTermList.no(j);
                if (i != j && termCondOptTpCd.equals(termPMsg2.termCondOptTpCd.getValue())) {
                    if (!commonLogic.setErrorMessage(param, idx, NMZM0144E, new String[] {MDSE_TERM_COND_OPT_TP_CD_T })) {
                        return;
                    }
                }
            }
        }
        if (cntRspTm > 1) {
            if (!commonLogic.setErrorMessage(param, idx, NMAM8325E, null)) {
                return;
            }
        }
        if (cnt > 0) {
            if (!MDSE_ITEM_TP.MAINTENANCE_CHARGES.equals(pMsg.mdseItemTpCd_D.getValue())) {
                String mdseItemTpNm = ZYPCodeDataUtil.getName(MDSE_ITEM_TP.class, param.glblCmpyCd.getValue(), pMsg.mdseItemTpCd_D.getValue());
                if (!commonLogic.setErrorMessage(param, idx, NMAM0076E, new String[] {"Item Type is " + mdseItemTpNm, MDSE_TERM_COND_OPT_TP_CD_T })) {
                    return;
                }
            }
        }
    }

    private void saveItem(NMZC004001PMsg param, int idx) {

        NMZC004002PMsg pMsg = param.NMZC004002PMsg.no(idx);

        if (!insertMdse(param, idx)) {
            return;
        }

        if (!insertMdseSftyInfo(param, idx)) {
            return;
        }

        //QC#14689
        //if (hasValue(pMsg.ordTakeMdseCd)) {
            if (!saveOrdTakeMdse(param, idx)) {
                return;
            }
        //}

        BigDecimal zero = new BigDecimal(0);
        if (zero.compareTo(pMsg.thisMthTotStdCostAmt_M.getValue()) <= 0) {
            if (!insertMdseCstUpdHist(param, idx, ZYPConstant.FLG_ON_Y)) {
                return;
            }
        }

        if (zero.compareTo(pMsg.assetRecovCostAmt_D.getValue()) <= 0) {
            if (!insertMdseCstUpdHist(param, idx, ZYPConstant.FLG_OFF_N)) {
                return;
            }
        }

        if (pMsg.xxBomList.getValidCount() > 0) {
            if (!insertBom(param, idx)) {
                return;
            }
        }
        if (pMsg.xxStoreList.getValidCount() > 0) {
            if (!insertStore(param, idx)) {
                return;
            }
        }
        if (pMsg.xxSerNumList.getValidCount() > 0) {
            if (!insertSerNum(param, idx)) {
                return;
            }
        }
        if (pMsg.xxSupdList.getValidCount() > 0 && !ZYPConstant.FLG_ON_Y.equals(skipSupdCratFlg)) {
            if (!insertSupd(param, idx)) {
                return;
            }
        }
        if (pMsg.xxRelList.getValidCount() > 0) {
            if (!insertRel(param, idx)) {
                return;
            }
        }
        if (pMsg.xxCustXrefList.getValidCount() > 0) {
            if (!insertCustXref(param, idx)) {
                return;
            }
        }
        if (pMsg.xxAslList.getValidCount() > 0) {
            if (!saveAsl(param, idx)) {
                return;
            }
        }
        // QC#4342
        if (pMsg.xxTermList.getValidCount() > 0) {
            if (!insertTermCondOpt(param, idx)) {
                return;
            }
        }
        // Del Start 2020/01/06 QC#54218-2
//        if (hasValue(pMsg.prcListEquipPrcAmt_P1)) {
        // Del End   2020/01/06 QC#54218-2
        // Add Start 2020/01/06 QC#54218-2
        if (hasValue(pMsg.prcCatgCd_P1) && hasValue(pMsg.prcListEquipPrcAmt_P1)) {
        // Add End   2020/01/06 QC#54218-2
            if (!savePrcListEquip(param, idx, true)) {
                return;
            }
            // Add Start 2023/01/05 QC#60892
            if(MDSE_ITEM_TP.PARTS.equals(pMsg.mdseItemTpCd_D.getValue())){
                String evacPrcCatgCd = param.NMZC004002PMsg.no(idx).prcCatgCd_P1.getValue();
                param.NMZC004002PMsg.no(idx).prcCatgCd_P1.setValue(PARTS_PRC_CATG_CD);
                if (!savePrcListEquip(param, idx, true)) {
                    param.NMZC004002PMsg.no(idx).prcCatgCd_P1.setValue(evacPrcCatgCd);
                    return;
                }
                param.NMZC004002PMsg.no(idx).prcCatgCd_P1.setValue(evacPrcCatgCd);
            }
            // Add end 2023/01/05 QC#60892
        }
        // Del Start 2020/01/06 QC#54218-2
//      if (hasValue(pMsg.prcListEquipPrcAmt_P2)) {
        // Del End   2020/01/06 QC#54218-2
        // Add Start 2020/01/06 QC#54218-2
        if (hasValue(pMsg.prcCatgCd_P2) && hasValue(pMsg.prcListEquipPrcAmt_P2)) {
        // Add End   2020/01/06 QC#54218-2
            if (!savePrcListEquip(param, idx, false)) {
                return;
            }
        }

        if (hasValue(pMsg.svcMdlNm)) {
            if (!insertSvcMdl(param, idx)) {
                return;
            }
        }

        // Sol#477(QC21587)
        if (!insertMrpInfo(param, idx)) {
            return;
        }
        
    }

    private boolean insertMdse(NMZC004001PMsg param, int idx) {

        NMZC004002PMsg pMsg = param.NMZC004002PMsg.no(idx);

        MDSETMsg tMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, pMsg.mdseCd_M.getValue());
        //ZYPEZDItemValueSetter.setValue(tMsg.mdseNm, pMsg.mdseNm_M);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseNm, getMdseNm(pMsg.mdseDescShortTxt_D.getValue()));
        //ZYPEZDItemValueSetter.setValue(tMsg.mdseFmlNm, pMsg.mdseFmlNm_M);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseFmlNm, getMdseFmlNm(pMsg.mdseDescLongTxt_D.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.coaProdCd, pMsg.coaProdCd_M);
        ZYPEZDItemValueSetter.setValue(tMsg.upcCd, pMsg.upcCd_M);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseTpCd, pMsg.mdseTpCd_M);
        ZYPEZDItemValueSetter.setValue(tMsg.mercClsCd, pMsg.mercClsCd_M);
        ZYPEZDItemValueSetter.setValue(tMsg.thirdPtyVndDropFlg, pMsg.thirdPtyVndDropFlg_M);
        ZYPEZDItemValueSetter.setValue(tMsg.etaDaysAot, pMsg.etaDaysAot_M);
        ZYPEZDItemValueSetter.setValue(tMsg.invtyDistTpCd, pMsg.invtyDistTpCd_M);
        ZYPEZDItemValueSetter.setValue(tMsg.invtySoftAllocTpCd, pMsg.invtySoftAllocTpCd_M);
        ZYPEZDItemValueSetter.setValue(tMsg.invtyHardAllocTpCd, pMsg.invtyHardAllocTpCd_M);
        ZYPEZDItemValueSetter.setValue(tMsg.showEtaFlg, pMsg.showEtaFlg_M);
        ZYPEZDItemValueSetter.setValue(tMsg.prodAuthReqFlg, pMsg.prodAuthReqFlg_M);
        ZYPEZDItemValueSetter.setValue(tMsg.invtyCtrlFlg, pMsg.invtyCtrlFlg_M);
        // 2020/04/13 QC#56494 Add Start
        /** internal Item Flag */
        if (ZYPCommonFunc.hasValue(pMsg.itrlItemFlg)) {
            ZYPEZDItemValueSetter.setValue(tMsg.itrlItemFlg, pMsg.itrlItemFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.itrlItemFlg, ZYPConstant.FLG_OFF_N);
        }
        // 2020/04/13 QC#56494 Add End
        ZYPEZDItemValueSetter.setValue(tMsg.invtyValFlg, pMsg.invtyValFlg_M);
        //QC#14689
        //ZYPEZDItemValueSetter.setValue(tMsg.thisMthFobAmt, pMsg.thisMthFobAmt_M);
        //ZYPEZDItemValueSetter.setValue(tMsg.thisMthInlndFrtAmt, pMsg.thisMthInlndFrtAmt_M);
        //ZYPEZDItemValueSetter.setValue(tMsg.thisMthIntlFrtAmt, pMsg.thisMthIntlFrtAmt_M);
        //ZYPEZDItemValueSetter.setValue(tMsg.thisMthImptDtyAmt, pMsg.thisMthImptDtyAmt_M);
        //ZYPEZDItemValueSetter.setValue(tMsg.thisMthInsAmt, pMsg.thisMthInsAmt_M);
        ZYPEZDItemValueSetter.setValue(tMsg.thisMthFobAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.thisMthInlndFrtAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.thisMthIntlFrtAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.thisMthImptDtyAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.thisMthInsAmt, BigDecimal.ZERO);

        ZYPEZDItemValueSetter.setValue(tMsg.thisMthTotStdCostAmt, pMsg.thisMthTotStdCostAmt_M);
        //QC#14689
        //ZYPEZDItemValueSetter.setValue(tMsg.nextMthFobAmt, pMsg.nextMthFobAmt_M);
        //ZYPEZDItemValueSetter.setValue(tMsg.nextMthInlndFrtAmt, pMsg.nextMthInlndFrtAmt_M);
        //ZYPEZDItemValueSetter.setValue(tMsg.nextMthIntlFrtAmt, pMsg.nextMthIntlFrtAmt_M);
        //ZYPEZDItemValueSetter.setValue(tMsg.nextMthImptDtyAmt, pMsg.nextMthImptDtyAmt_M);
        //ZYPEZDItemValueSetter.setValue(tMsg.nextMthInsAmt, pMsg.nextMthInsAmt_M);
        ZYPEZDItemValueSetter.setValue(tMsg.nextMthFobAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.nextMthInlndFrtAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.nextMthIntlFrtAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.nextMthImptDtyAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.nextMthInsAmt, BigDecimal.ZERO);

        ZYPEZDItemValueSetter.setValue(tMsg.nextMthTotStdCostAmt, pMsg.nextMthTotStdCostAmt_M);
        ZYPEZDItemValueSetter.setValue(tMsg.lastMthTotStdCostAmt, pMsg.lastMthTotStdCostAmt_M);
        ZYPEZDItemValueSetter.setValue(tMsg.costCcyCd, pMsg.costCcyCd_M);
        ZYPEZDItemValueSetter.setValue(tMsg.wrtDownFlg, pMsg.wrtDownFlg_M);
        ZYPEZDItemValueSetter.setValue(tMsg.fobReCalcExclFlg, pMsg.fobReCalcExclFlg_M);
        ZYPEZDItemValueSetter.setValue(tMsg.carryChrgCalcManFlg, pMsg.carryChrgCalcManFlg_M);
        ZYPEZDItemValueSetter.setValue(tMsg.dsctnFlg, pMsg.dsctnFlg_M);
        ZYPEZDItemValueSetter.setValue(tMsg.sellHldFlg, pMsg.sellHldFlg_M);
        //QC#14689
        //ZYPEZDItemValueSetter.setValue(tMsg.basePrcAmt, pMsg.basePrcAmt_M);
        ZYPEZDItemValueSetter.setValue(tMsg.basePrcAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.cpoMinOrdQty, pMsg.cpoMinOrdQty_M);
        ZYPEZDItemValueSetter.setValue(tMsg.cpoMaxOrdQty, pMsg.cpoMaxOrdQty_M);
        ZYPEZDItemValueSetter.setValue(tMsg.cpoIncrOrdQty, pMsg.cpoIncrOrdQty_M);
        ZYPEZDItemValueSetter.setValue(tMsg.sellBySelfFlg, pMsg.sellBySelfFlg_M);
        ZYPEZDItemValueSetter.setValue(tMsg.setMdseShipCpltFlg, pMsg.setMdseShipCpltFlg_M);
        ZYPEZDItemValueSetter.setValue(tMsg.daysPriAllocNum, pMsg.daysPriAllocNum_M);
        ZYPEZDItemValueSetter.setValue(tMsg.shpgOrdHldFlg, pMsg.shpgOrdHldFlg_M);
        ZYPEZDItemValueSetter.setValue(tMsg.prcCcyCd, pMsg.prcCcyCd_M);
        ZYPEZDItemValueSetter.setValue(tMsg.soAuthReqFlg, pMsg.soAuthReqFlg_M);
        ZYPEZDItemValueSetter.setValue(tMsg.expItemFlg, pMsg.expItemFlg_M);
        ZYPEZDItemValueSetter.setValue(tMsg.distByWhFlg, pMsg.distByWhFlg_M);
        ZYPEZDItemValueSetter.setValue(tMsg.trfCd, pMsg.trfCd_M);
        ZYPEZDItemValueSetter.setValue(tMsg.frtClsCd, pMsg.frtClsCd_M);
        ZYPEZDItemValueSetter.setValue(tMsg.intgProdCatgCd, pMsg.intgProdCatgCd_M);
        ZYPEZDItemValueSetter.setValue(tMsg.zerothProdCtrlCd, pMsg.zerothProdCtrlCd_M);
        ZYPEZDItemValueSetter.setValue(tMsg.zerothProdCtrlNm, pMsg.zerothProdCtrlNm_M);
        ZYPEZDItemValueSetter.setValue(tMsg.firstProdCtrlCd, pMsg.firstProdCtrlCd_M);
        ZYPEZDItemValueSetter.setValue(tMsg.firstProdCtrlNm, pMsg.firstProdCtrlNm_M);
        ZYPEZDItemValueSetter.setValue(tMsg.scdProdCtrlCd, pMsg.scdProdCtrlCd_M);
        ZYPEZDItemValueSetter.setValue(tMsg.scdProdCtrlNm, pMsg.scdProdCtrlNm_M);
        ZYPEZDItemValueSetter.setValue(tMsg.thirdProdCtrlCd, pMsg.thirdProdCtrlCd_M);
        ZYPEZDItemValueSetter.setValue(tMsg.thirdProdCtrlNm, pMsg.thirdProdCtrlNm_M);
        ZYPEZDItemValueSetter.setValue(tMsg.frthProdCtrlCd, pMsg.frthProdCtrlCd_M);
        ZYPEZDItemValueSetter.setValue(tMsg.frthProdCtrlNm, pMsg.frthProdCtrlNm_M);
        ZYPEZDItemValueSetter.setValue(tMsg.fifthProdCtrlCd, pMsg.fifthProdCtrlCd_M);
        ZYPEZDItemValueSetter.setValue(tMsg.fifthProdCtrlNm, pMsg.fifthProdCtrlNm_M);
        ZYPEZDItemValueSetter.setValue(tMsg.sixthProdCtrlCd, pMsg.sixthProdCtrlCd_M);
        ZYPEZDItemValueSetter.setValue(tMsg.sixthProdCtrlNm, pMsg.sixthProdCtrlNm_M);
        ZYPEZDItemValueSetter.setValue(tMsg.svnthProdCtrlCd, pMsg.svnthProdCtrlCd_M);
        ZYPEZDItemValueSetter.setValue(tMsg.svnthProdCtrlNm, pMsg.svnthProdCtrlNm_M);
        ZYPEZDItemValueSetter.setValue(tMsg.rgtnStsCd, pMsg.rgtnStsCd_M);
        ZYPEZDItemValueSetter.setValue(tMsg.rcvSerTakeFlg, pMsg.rcvSerTakeFlg_M);
        ZYPEZDItemValueSetter.setValue(tMsg.shpgSerTakeFlg, pMsg.shpgSerTakeFlg_M);
        ZYPEZDItemValueSetter.setValue(tMsg.manPrcAllwOnOrdFlg, pMsg.manPrcAllwOnOrdFlg_M);
        ZYPEZDItemValueSetter.setValue(tMsg.rmaReqTpCd, pMsg.rmaReqTpCd_M);
        ZYPEZDItemValueSetter.setValue(tMsg.trfApvlStsCd, pMsg.trfApvlStsCd_M);
        ZYPEZDItemValueSetter.setValue(tMsg.autoLicAllocFlg, pMsg.autoLicAllocFlg_M);

        ZYPEZDItemValueSetter.setValue(tMsg.custIstlFlg, pMsg.custIstlFlg_D);
        ZYPEZDItemValueSetter.setValue(tMsg.siteSrvyReqFlg, pMsg.siteSrvyReqFlg_D);
        ZYPEZDItemValueSetter.setValue(tMsg.dsCmsnGrpCd, pMsg.dsCmsnGrpCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.easyPackTpCd, pMsg.easyPackTpCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.configFlg, pMsg.configFlg_D);
        ZYPEZDItemValueSetter.setValue(tMsg.vndRtrnFlg, pMsg.vndRtrnFlg_D);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseRgtnTpCd, pMsg.mdseRgtnTpCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.prchUseLifeMthNum, pMsg.prchUseLifeMthNum_D);
        ZYPEZDItemValueSetter.setValue(tMsg.rntlUseLifeMthNum, pMsg.rntlUseLifeMthNum_D);
        ZYPEZDItemValueSetter.setValue(tMsg.prchGrpCd, pMsg.prchGrpCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.configLtDayNum, pMsg.configLtDayNum_D);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCatgCd, pMsg.mdseCatgCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.assetMgtFlg, pMsg.assetMgtFlg_D);
        ZYPEZDItemValueSetter.setValue(tMsg.rsdlValAmt, pMsg.rsdlValAmt_D);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseFrtClsCd, pMsg.mdseFrtClsCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.daysPriPoCratNum, pMsg.daysPriPoCratNum_D);
        ZYPEZDItemValueSetter.setValue(tMsg.rtrnReqPrtFlg, pMsg.rtrnReqPrtFlg_D);
        ZYPEZDItemValueSetter.setValue(tMsg.origFobAmt, pMsg.origFobAmt_D);
        //QC#14689
        //ZYPEZDItemValueSetter.setValue(tMsg.actlExchRate, pMsg.actlExchRate_D);
        tMsg.actlExchRate.clear();
        ZYPEZDItemValueSetter.setValue(tMsg.origFobAmtUpdDt, pMsg.origFobAmtUpdDt_D);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseItemTpCd, pMsg.mdseItemTpCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseItemClsTpCd, pMsg.mdseItemClsTpCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.mktMdlCd, pMsg.mktMdlCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.mktMdlSegCd, pMsg.mktMdlSegCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseItemStsCd, pMsg.mdseItemStsCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.rtrnCtrlTpCd, pMsg.rtrnCtrlTpCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.rtrnDsplTpCd, pMsg.rtrnDsplTpCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.assetRecovCostAmt, pMsg.assetRecovCostAmt_D);
        ZYPEZDItemValueSetter.setValue(tMsg.rmaInspReqFlg, pMsg.rmaInspReqFlg_D);
        ZYPEZDItemValueSetter.setValue(tMsg.defSrcWhCd, pMsg.defSrcWhCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.defSrcSubWhCd, pMsg.defSrcSubWhCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.revCoaAcctCd, pMsg.revCoaAcctCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.cogsCoaAcctCd, pMsg.cogsCoaAcctCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.expCoaAcctCd, pMsg.expCoaAcctCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.invPsblFlg, pMsg.invPsblFlg_D);
        ZYPEZDItemValueSetter.setValue(tMsg.dfrdAcctgRuleCd, pMsg.dfrdAcctgRuleCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.dfrdInvRuleCd, pMsg.dfrdInvRuleCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.taxExemTpCd, pMsg.taxExemTpCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.svcWtyTpCd, pMsg.svcWtyTpCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.wtyDaysAot, pMsg.wtyDaysAot_D);
        ZYPEZDItemValueSetter.setValue(tMsg.mtrMachFlg, pMsg.mtrMachFlg_D);
        ZYPEZDItemValueSetter.setValue(tMsg.instlBaseCtrlFlg, pMsg.instlBaseCtrlFlg_D);
        ZYPEZDItemValueSetter.setValue(tMsg.svcCallEnblFlg, pMsg.svcCallEnblFlg_D);
        ZYPEZDItemValueSetter.setValue(tMsg.iwrEnblFlg, pMsg.iwrEnblFlg_D);
        ZYPEZDItemValueSetter.setValue(tMsg.iwrMdlCd, pMsg.iwrMdlCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.iwrMdseCd, pMsg.iwrMdseCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.custOrdEnblFlg, pMsg.custOrdEnblFlg_D);
        ZYPEZDItemValueSetter.setValue(tMsg.reMnfItemExstFlg, pMsg.reMnfItemExstFlg_D);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseItemMnfCd, pMsg.mdseItemMnfCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.mnfItemCd, pMsg.mnfItemCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseItemActvDt, pMsg.mdseItemActvDt_D);
        ZYPEZDItemValueSetter.setValue(tMsg.coaMdseTpCd, pMsg.coaMdseTpCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.mainMachFlg, pMsg.mainMachFlg_D);
        ZYPEZDItemValueSetter.setValue(tMsg.backOrdImpctTpCd, pMsg.backOrdImpctTpCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.reMnfAvalFlg, pMsg.reMnfAvalFlg_D);
        ZYPEZDItemValueSetter.setValue(tMsg.imgSplyOemMnfCd, pMsg.imgSplyOemMnfCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.imgSplyOemCd, pMsg.imgSplyOemCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.imgSplyTpCd, pMsg.imgSplyTpCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.imgSplyColorTpCd, pMsg.imgSplyColorTpCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.imgSplyYieldCnt, pMsg.imgSplyYieldCnt_D);
        ZYPEZDItemValueSetter.setValue(tMsg.imgSplyYieldUomCd, pMsg.imgSplyYieldUomCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.imgSplyYieldTpCd, pMsg.imgSplyYieldTpCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.svcCovTmplTpCd, pMsg.svcCovTmplTpCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.svcAllocTpCd, pMsg.svcAllocTpCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.prtDropShipDsblFlg, pMsg.prtDropShipDsblFlg_D);
        ZYPEZDItemValueSetter.setValue(tMsg.swCatgCd, pMsg.swCatgCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.swTpCd, pMsg.swTpCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.swVrsnTxt, pMsg.swVrsnTxt_D);
        ZYPEZDItemValueSetter.setValue(tMsg.swLicCtrlTpCd, pMsg.swLicCtrlTpCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.swLicSeatFromQty, pMsg.swLicSeatFromQty_D);
        ZYPEZDItemValueSetter.setValue(tMsg.swLicSeatToQty, pMsg.swLicSeatToQty_D);
        ZYPEZDItemValueSetter.setValue(tMsg.swLicSeatFixQty, pMsg.swLicSeatFixQty_D);
        ZYPEZDItemValueSetter.setValue(tMsg.swMaintCtrlTpCd, pMsg.swMaintCtrlTpCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.asrnPntPerLicQty, pMsg.asrnPntPerLicQty_D);
        ZYPEZDItemValueSetter.setValue(tMsg.swMaintTpCd, pMsg.swMaintTpCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.swMaintTermYr, pMsg.swMaintTermYr_D);
        ZYPEZDItemValueSetter.setValue(tMsg.asrnPntMinQty, pMsg.asrnPntMinQty_D);
        ZYPEZDItemValueSetter.setValue(tMsg.asrnPntMaxQty, pMsg.asrnPntMaxQty_D);
        ZYPEZDItemValueSetter.setValue(tMsg.asrnPntFixPerOrdQty, pMsg.asrnPntFixPerOrdQty_D);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCstUpdEffFromDt, pMsg.mdseCstUpdEffFromDt_D);
        ZYPEZDItemValueSetter.setValue(tMsg.intntConnSwCtrlFlg, pMsg.intntConnSwCtrlFlg_D);
        ZYPEZDItemValueSetter.setValue(tMsg.assetRecovCostAmtUpdDt, pMsg.assetRecovCostAmtUpdDt_D);
        ZYPEZDItemValueSetter.setValue(tMsg.prevAssetRecovCostAmt, pMsg.prevAssetRecovCostAmt_D);
        ZYPEZDItemValueSetter.setValue(tMsg.nextAssetRecovCostAmt, pMsg.nextAssetRecovCostAmt_D);
        ZYPEZDItemValueSetter.setValue(tMsg.assetRecovCstEffFromDt, pMsg.assetRecovCstEffFromDt_D);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCstUpdDt, pMsg.mdseCstUpdDt_D);
        ZYPEZDItemValueSetter.setValue(tMsg.sowReqFlg, pMsg.sowReqFlg_D);
        ZYPEZDItemValueSetter.setValue(tMsg.prntCmpySetMdseFlg, pMsg.prntCmpySetMdseFlg_D);
        ZYPEZDItemValueSetter.setValue(tMsg.thirdPtyItemFlg, pMsg.thirdPtyItemFlg_D);
        //QC#14689
        //ZYPEZDItemValueSetter.setValue(tMsg.coaCcCd, pMsg.coaCcCd_D);
        tMsg.coaCcCd.clear();
        ZYPEZDItemValueSetter.setValue(tMsg.mdseItemBillTpCd, pMsg.mdseItemBillTpCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.defSrcProcrTpCd, pMsg.defSrcProcrTpCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.areaOfPaperNum, pMsg.areaOfPaperNum_D);
        ZYPEZDItemValueSetter.setValue(tMsg.pkgUomClsCd, pMsg.pkgUomClsCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseDescShortTxt, pMsg.mdseDescShortTxt_D);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseDescLongTxt, pMsg.mdseDescLongTxt_D);
        ZYPEZDItemValueSetter.setValue(tMsg.prtItemTpCd, pMsg.prtItemTpCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.prtYieldOtptQty, pMsg.prtYieldOtptQty_D);
        ZYPEZDItemValueSetter.setValue(tMsg.prtYieldDaysAot, pMsg.prtYieldDaysAot_D);
        ZYPEZDItemValueSetter.setValue(tMsg.swProdCatgCd, pMsg.swProdCatgCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.bdlMaintMdseCd, pMsg.bdlMaintMdseCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.maintItemTpCd, pMsg.maintItemTpCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.maintItemTermMthNum, pMsg.maintItemTermMthNum_D);
        ZYPEZDItemValueSetter.setValue(tMsg.svcCovBaseTpCd, pMsg.svcCovBaseTpCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.slsMatGrpCd_01, pMsg.slsMatGrpCd_D1);
        ZYPEZDItemValueSetter.setValue(tMsg.slsMatGrpCd_02, pMsg.slsMatGrpCd_D2);
        ZYPEZDItemValueSetter.setValue(tMsg.slsMatGrpCd_03, pMsg.slsMatGrpCd_D3);
        ZYPEZDItemValueSetter.setValue(tMsg.lineBizTpCd, pMsg.lineBizTpCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.nmfcClsTpCd, pMsg.nmfcClsTpCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.imgSplyPvtLbTpCd, pMsg.imgSplyPvtLbTpCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.rtrnToPrntVndCd, pMsg.rtrnToPrntVndCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.rtrnToVndCd, pMsg.rtrnToVndCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.prtSrvyReqFlg, pMsg.prtSrvyReqFlg_D);
        ZYPEZDItemValueSetter.setValue(tMsg.maintPopAvalFlg, pMsg.maintPopAvalFlg_D);
        ZYPEZDItemValueSetter.setValue(tMsg.extMaintPopAvalFlg, pMsg.extMaintPopAvalFlg_D);
        //QC#4203
        ZYPEZDItemValueSetter.setValue(tMsg.rtrnCtrlTpVndRelnPk, pMsg.rtrnCtrlTpVndRelnPk_D);
        ZYPEZDItemValueSetter.setValue(tMsg.rtrnWhCd, pMsg.rtrnWhCd_D);
        ZYPEZDItemValueSetter.setValue(tMsg.dsIntgMdseTpCd, pMsg.dsIntgMdseTpCd_D);
        // 2017/07/06 S21_NA#19777 Add Start
        ZYPEZDItemValueSetter.setValue(tMsg.cstmReqFlg, pMsg.cstmReqFlg_M);
        // 2017/07/06 S21_NA#19777 Add End
        //2017/09/25 QC#18534(L3#445) Add Start
        ZYPEZDItemValueSetter.setValue(tMsg.ovrdMnfWtyFlg, ZYPConstant.FLG_OFF_N);
        //2017/09/25 QC#18534(L3#445) Add Start

        S21FastTBLAccessor.insert(tMsg);
        if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            commonLogic.setErrorMessage(param, idx, NMZM0150E, new String[] {MSG_MDSE });
            return false;
        }

        return true;
    }

    private boolean insertMdseSftyInfo(NMZC004001PMsg param, int idx) {

        NMZC004002PMsg pMsg = param.NMZC004002PMsg.no(idx);

        MDSE_SFTY_INFOTMsg tMsg = new MDSE_SFTY_INFOTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, pMsg.mdseCd_S);
        ZYPEZDItemValueSetter.setValue(tMsg.hazMatFlg, pMsg.hazMatFlg_S);
        ZYPEZDItemValueSetter.setValue(tMsg.dsplTpCd, pMsg.dsplTpCd_S);
        ZYPEZDItemValueSetter.setValue(tMsg.mercCntnFlg, pMsg.mercCntnFlg_S);
        ZYPEZDItemValueSetter.setValue(tMsg.odcTaxFlg, pMsg.odcTaxFlg_S);
        ZYPEZDItemValueSetter.setValue(tMsg.madeInCtryCd, pMsg.madeInCtryCd_S);
        ZYPEZDItemValueSetter.setValue(tMsg.asmInCtryCd, pMsg.asmInCtryCd_S);
        ZYPEZDItemValueSetter.setValue(tMsg.hazMatCd, pMsg.hazMatCd_S);
        ZYPEZDItemValueSetter.setValue(tMsg.cstmSftyApvlStsCd, pMsg.cstmSftyApvlStsCd_S);
        ZYPEZDItemValueSetter.setValue(tMsg.ordTakeSftyApvlStsCd, pMsg.ordTakeSftyApvlStsCd_S);
        ZYPEZDItemValueSetter.setValue(tMsg.mnfStCd, pMsg.mnfStCd_S);
        // 2017/07/06 S21_NA#19777 Add Start
        ZYPEZDItemValueSetter.setValue(tMsg.fccReqFlg, pMsg.fccReqFlg_S);
        ZYPEZDItemValueSetter.setValue(tMsg.fccPrt15over18thReqFlg, pMsg.fccPrt15over18thReqFlg_S);
        ZYPEZDItemValueSetter.setValue(tMsg.fccPrt68ReqFlg, pMsg.fccPrt68ReqFlg_S);
        ZYPEZDItemValueSetter.setValue(tMsg.fccVerFlg, pMsg.fccVerFlg_S);
        ZYPEZDItemValueSetter.setValue(tMsg.fccCrtifFlg, pMsg.fccCrtifFlg_S);
        ZYPEZDItemValueSetter.setValue(tMsg.fccDocFlg, pMsg.fccDocFlg_S);
        ZYPEZDItemValueSetter.setValue(tMsg.fdaReqFlg, pMsg.fdaReqFlg_S);
        ZYPEZDItemValueSetter.setValue(tMsg.fdaRdlgProdFlg, pMsg.fdaRdlgProdFlg_S);
        ZYPEZDItemValueSetter.setValue(tMsg.fdaMedcDvcFlg, pMsg.fdaMedcDvcFlg_S);
        ZYPEZDItemValueSetter.setValue(tMsg.tscaReqFlg, pMsg.tscaReqFlg_S);
        ZYPEZDItemValueSetter.setValue(tMsg.ulReqFlg, pMsg.ulReqFlg_S);
        ZYPEZDItemValueSetter.setValue(tMsg.cfcReqFlg, pMsg.cfcReqFlg_S);
        ZYPEZDItemValueSetter.setValue(tMsg.msdsReqFlg, pMsg.msdsReqFlg_S);
        // 2017/07/06 S21_NA#19777 Add End

        S21FastTBLAccessor.insert(tMsg);
        if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            commonLogic.setErrorMessage(param, idx, NMZM0150E, new String[] {MSG_MDSE_SFTY_INFO });
            return false;
        }

        return true;
    }

    private boolean saveOrdTakeMdse(NMZC004001PMsg param, int idx) {

        String glblCmpyCd = param.glblCmpyCd.getValue();
        NMZC004002PMsg pMsg = param.NMZC004002PMsg.no(idx);
        String mdseCd = pMsg.mdseCd_M.getValue();

        if (commonLogic.usableMdseCd8(glblCmpyCd, mdseCd, AMER_MDSE_CLS_TP_CD_A) 
                && mdseCd != null && mdseCd.length() >= 8) {  //QC#11172
            //QC#14689
            //String ordTakeMdseCd = pMsg.ordTakeMdseCd.getValue();
            String ordTakeMdseCd = mdseCd.substring(0, 8);

            ORD_TAKE_MDSETMsg tMsg = new ORD_TAKE_MDSETMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.ordTakeMdseCd, ordTakeMdseCd);
            tMsg = (ORD_TAKE_MDSETMsg) S21ApiTBLAccessor.findByKeyForUpdate(tMsg);
            if (tMsg == null) {
                tMsg = new ORD_TAKE_MDSETMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.ordTakeMdseCd, ordTakeMdseCd);
                ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, mdseCd);
                S21FastTBLAccessor.insert(tMsg);
            } else {
                if (!mdseCd.equals(tMsg.mdseCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, mdseCd);
                    S21FastTBLAccessor.update(tMsg);
                }
            }
            if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                commonLogic.setErrorMessage(param, idx, NMZM0150E, new String[] {MSG_ORD_TAKE_MDSE });
                return false;
            }
        }

        return true;
    }

    private boolean insertMdseCstUpdHist(NMZC004001PMsg param, int idx, String stdFlg) {

        String glblCmpyCd = param.glblCmpyCd.getValue();
        String slsDt = param.slsDt.getValue();
        NMZC004002PMsg pMsg = param.NMZC004002PMsg.no(idx);

        BigDecimal mdseCstUpdHistHdrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.MDSE_CST_UPD_HIST_HDR_SQ);
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String txt = ITEM_IMPORT + timeStamp;
        String mdseCstUpdTpCd;
        BigDecimal rqstTotStdCostAmt;
        if (ZYPConstant.FLG_ON_Y.equals(stdFlg)) {
            mdseCstUpdTpCd = MDSE_CST_UPD_TP.STANDARD_COST;
            rqstTotStdCostAmt = pMsg.thisMthTotStdCostAmt_M.getValue();
        } else {
            mdseCstUpdTpCd = MDSE_CST_UPD_TP.ARV_COST;
            rqstTotStdCostAmt = pMsg.assetRecovCostAmt_D.getValue();
        }

        MDSE_CST_UPD_HIST_HDRTMsg mdseCstUpdHistHdrTMsg = new MDSE_CST_UPD_HIST_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(mdseCstUpdHistHdrTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseCstUpdHistHdrTMsg.mdseCstUpdHistHdrPk, mdseCstUpdHistHdrPk);
        ZYPEZDItemValueSetter.setValue(mdseCstUpdHistHdrTMsg.mdseCstUpdGrpTxt, txt);
        ZYPEZDItemValueSetter.setValue(mdseCstUpdHistHdrTMsg.mdseCstUpdTpCd, mdseCstUpdTpCd);
        ZYPEZDItemValueSetter.setValue(mdseCstUpdHistHdrTMsg.mdseCstUpdDescTxt, txt);
        ZYPEZDItemValueSetter.setValue(mdseCstUpdHistHdrTMsg.mdseCstUpdCratPsnCd, pMsg.apvlByPsnCd.getValue());
        ZYPEZDItemValueSetter.setValue(mdseCstUpdHistHdrTMsg.mdseCstUpdCratDt, slsDt);

        S21FastTBLAccessor.insert(mdseCstUpdHistHdrTMsg);
        if (!RTNCD_NORMAL.equals(mdseCstUpdHistHdrTMsg.getReturnCode())) {
            commonLogic.setErrorMessage(param, idx, NMZM0150E, new String[] {MSG_MDSE_CST_UPD_HIST_HDR });
            return false;
        }

        MDSE_CST_UPD_HIST_DTLTMsg mdseCstUpdHistDtlTMsg = new MDSE_CST_UPD_HIST_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(mdseCstUpdHistDtlTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseCstUpdHistDtlTMsg.mdseCstUpdHistHdrPk, mdseCstUpdHistHdrPk);
        ZYPEZDItemValueSetter.setValue(mdseCstUpdHistDtlTMsg.mdseCd, pMsg.mdseCd_M.getValue());
        ZYPEZDItemValueSetter.setValue(mdseCstUpdHistDtlTMsg.rqstTotStdCostAmt, rqstTotStdCostAmt);
        ZYPEZDItemValueSetter.setValue(mdseCstUpdHistDtlTMsg.pkgUomCd, PKG_UOM.EACH);
        ZYPEZDItemValueSetter.setValue(mdseCstUpdHistDtlTMsg.mdseCstUpdEffFromDt, slsDt);
        ZYPEZDItemValueSetter.setValue(mdseCstUpdHistDtlTMsg.mdseCstUpdStsCd, MDSE_CST_UPD_STS.ACTIVE);

        S21FastTBLAccessor.insert(mdseCstUpdHistDtlTMsg);
        if (!RTNCD_NORMAL.equals(mdseCstUpdHistDtlTMsg.getReturnCode())) {
            commonLogic.setErrorMessage(param, idx, NMZM0150E, new String[] {MSG_MDSE_CST_UPD_HIST_DTL });
            return false;
        }

        return true;
    }

    private boolean insertBom(NMZC004001PMsg param, int idx) {

        String glblCmpyCd = param.glblCmpyCd.getValue();
        NMZC004002PMsg pMsg = param.NMZC004002PMsg.no(idx);

        BigDecimal cmpsnPk;
        NMZC004002_xxBomListPMsg bomPMsg;
        CMPSNTMsg cmpsnTMsg = new CMPSNTMsg();
        ZYPEZDItemValueSetter.setValue(cmpsnTMsg.glblCmpyCd, glblCmpyCd);
        for (int i = 0; i < pMsg.xxBomList.getValidCount(); i++) {
            bomPMsg = pMsg.xxBomList.no(i);
            cmpsnPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CMPSN_SQ);

            ZYPEZDItemValueSetter.setValue(cmpsnTMsg.prntMdseCd, bomPMsg.prntMdseCd_C);
            ZYPEZDItemValueSetter.setValue(cmpsnTMsg.cmpsnPk, cmpsnPk);
            ZYPEZDItemValueSetter.setValue(cmpsnTMsg.mdseCmpsnTpCd, bomPMsg.mdseCmpsnTpCd_C);
            ZYPEZDItemValueSetter.setValue(cmpsnTMsg.childMdseCd, bomPMsg.childMdseCd_C);
            ZYPEZDItemValueSetter.setValue(cmpsnTMsg.childOrdTakeMdseCd, bomPMsg.childOrdTakeMdseCd_C);
            ZYPEZDItemValueSetter.setValue(cmpsnTMsg.childKitMatCd, bomPMsg.childKitMatCd_C);
            ZYPEZDItemValueSetter.setValue(cmpsnTMsg.childMdseQty, bomPMsg.childMdseQty_C);
            ZYPEZDItemValueSetter.setValue(cmpsnTMsg.effFromDt, bomPMsg.effFromDt_C);
            ZYPEZDItemValueSetter.setValue(cmpsnTMsg.effThruDt, bomPMsg.effThruDt_C);
            ZYPEZDItemValueSetter.setValue(cmpsnTMsg.allocRatio, bomPMsg.allocRatio_C);
            ZYPEZDItemValueSetter.setValue(cmpsnTMsg.childBomCmptCd, bomPMsg.childBomCmptCd_C);
            ZYPEZDItemValueSetter.setValue(cmpsnTMsg.childBomCmptNm, bomPMsg.childBomCmptNm_C);
            ZYPEZDItemValueSetter.setValue(cmpsnTMsg.childBomPrcAmt, bomPMsg.childBomPrcAmt_C);
            ZYPEZDItemValueSetter.setValue(cmpsnTMsg.mainCmpsnFlg, bomPMsg.mainCmpsnFlg_C);
            ZYPEZDItemValueSetter.setValue(cmpsnTMsg.cusaMdseCd, bomPMsg.cusaMdseCd_C);
            ZYPEZDItemValueSetter.setValue(cmpsnTMsg.baseCmptFlg, bomPMsg.baseCmptFlg_DC);
            ZYPEZDItemValueSetter.setValue(cmpsnTMsg.mndCmptFlg, bomPMsg.mndCmptFlg_DC);
            ZYPEZDItemValueSetter.setValue(cmpsnTMsg.pkgUomCd, bomPMsg.pkgUomCd_DC);
            ZYPEZDItemValueSetter.setValue(cmpsnTMsg.cmpsnRevnNum, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(cmpsnTMsg.actvFlg, ZYPConstant.FLG_ON_Y);

            S21FastTBLAccessor.insert(cmpsnTMsg);
            if (!RTNCD_NORMAL.equals(cmpsnTMsg.getReturnCode())) {
                commonLogic.setErrorMessage(param, idx, NMZM0150E, new String[] {MSG_CMPSN });
                return false;
            }
        }

        return true;
    }

    private boolean insertStore(NMZC004001PMsg param, int idx) {

        String glblCmpyCd = param.glblCmpyCd.getValue();
        NMZC004002PMsg pMsg = param.NMZC004002PMsg.no(idx);

        String basePkgUomCd = null;
        PKG_UOM_CLSTMsg pkgUomClsTMsg = commonLogic.getPkgUomClsTMsg(glblCmpyCd, pMsg.pkgUomClsCd_D.getValue());
        if (pkgUomClsTMsg != null) {
            basePkgUomCd = pkgUomClsTMsg.basePkgUomCd.getValue();
        }

        NMZC004002_xxStoreListPMsg storePMsg;
        // 2020/01/17 QC#55451 Del Start
        //MDSE_STORE_PKGTMsg mdseStorePkgTMsg = new MDSE_STORE_PKGTMsg();
        //ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.glblCmpyCd, glblCmpyCd);
        // 2020/01/17 QC#55451 Del End
        for (int i = 0; i < pMsg.xxStoreList.getValidCount(); i++) {
            storePMsg = pMsg.xxStoreList.no(i);

            // 2020/01/17 QC#55451 Add Start
            MDSE_STORE_PKGTMsg mdseStorePkgTMsg = new MDSE_STORE_PKGTMsg();
            ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.glblCmpyCd, glblCmpyCd);
            // 2020/01/17 QC#55451 Add End
            ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.pkgUomCd, storePMsg.pkgUomCd_S);
            ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.mdseCd, storePMsg.mdseCd_S);
            ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.scc14Num, storePMsg.scc14Num_S);
            ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.gtinCd, storePMsg.gtinCd_S);
            ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.inEachQty, storePMsg.inEachQty_S);
            ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.inInchLg, storePMsg.inInchLg_S);
            ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.inInchWdt, storePMsg.inInchWdt_S);
            ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.inInchHgt, storePMsg.inInchHgt_S);
            ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.inPoundWt, storePMsg.inPoundWt_S);
            ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.inInchVol, storePMsg.inInchVol_S);
            ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.ovrdUpcOrScc14Num, storePMsg.ovrdUpcOrScc14Num_S);
            ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.upcOrScc14LbFlg, storePMsg.upcOrScc14LbFlg_S);
            ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.qtyPkgApvlUserId, storePMsg.qtyPkgApvlUserId_S);
            ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.qtyPkgApvlUpdDt, storePMsg.qtyPkgApvlUpdDt_S);
            ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.qtyPkgApvlStsCd, storePMsg.qtyPkgApvlStsCd_S);
            ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.dmnPkgApvlUserId, storePMsg.dmnPkgApvlUserId_S);
            ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.dmnPkgApvlUpdDt, storePMsg.dmnPkgApvlUpdDt_S);
            ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.dmnPkgApvlStsCd, storePMsg.dmnPkgApvlStsCd_S);
            ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.adminPkgApvlUserId, storePMsg.adminPkgApvlUserId_S);
            ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.adminPkgApvlUpdDt, storePMsg.adminPkgApvlUpdDt_S);
            ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.adminPkgApvlStsCd, storePMsg.adminPkgApvlStsCd_S);
            ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.qtyPkgApvlUpdDt, storePMsg.qtyPkgApvlUpdDt_S);
            ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.primPkgUomFlg, storePMsg.primPkgUomFlg_DS);
            // 2020/01/17 QC#55451 Mod Start
            // ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.basePkgUomCd, basePkgUomCd);
            // 2020/02/20 QC#55844 Mod Start
            //if (!PKG_UOM.EACH.equals(storePMsg.pkgUomCd_S.getValue())) {
            if (PKG_UOM.EACH.equals(storePMsg.pkgUomCd_S.getValue())) {
            // 2020/02/20 QC#55844 Mod End
                ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.basePkgUomCd, basePkgUomCd);
            }
            // 2020/01/17 QC#55451 Mod End
            // 2019/12/16 QC#54859 Add Start
            if (PKG_UOM.EACH_UNBOX.equals(storePMsg.pkgUomCd_S.getValue())) {
                if (ZYPCommonFunc.hasValue(storePMsg.inInchLg_S)) {
                    ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.inInchLgUomCd, storePMsg.inInchLgUomCd_S);
                }
                if (ZYPCommonFunc.hasValue(storePMsg.inInchWdt_S)) {
                    ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.inInchWdtUomCd, storePMsg.inInchWdtUomCd_S);
                }
                if (ZYPCommonFunc.hasValue(storePMsg.inInchHgt_S)) {
                    ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.inInchHgtUomCd, storePMsg.inInchHgtUomCd_S);
                }
                if (ZYPCommonFunc.hasValue(storePMsg.inPoundWt_S)) {
                    ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.inPoundWtUomCd, storePMsg.inPoundWtUomCd_S);
                }
            }
            // 2019/12/16 QC#54859 Add End

            S21FastTBLAccessor.insert(mdseStorePkgTMsg);
            if (!RTNCD_NORMAL.equals(mdseStorePkgTMsg.getReturnCode())) {
                commonLogic.setErrorMessage(param, idx, NMZM0150E, new String[] {MSG_MDSE_STORE_PKG });
                return false;
            }

            // 2020/01/17 QC#55451 Del Start
            //if (!PKG_UOM.EACH.equals(storePMsg.pkgUomCd_S.getValue())) {
            //    basePkgUomCd = null;
            //}
            // 2020/01/17 QC#55451 Del End
        }

        return true;
    }

    private boolean insertSerNum(NMZC004001PMsg param, int idx) {

        String glblCmpyCd = param.glblCmpyCd.getValue();
        NMZC004002PMsg pMsg = param.NMZC004002PMsg.no(idx);

        NMZC004002_xxSerNumListPMsg serNumPMsg;
        MDSE_SER_NUM_RNGTMsg mdseSerNumRngTMsg = new MDSE_SER_NUM_RNGTMsg();
        ZYPEZDItemValueSetter.setValue(mdseSerNumRngTMsg.glblCmpyCd, glblCmpyCd);
        for (int i = 0; i < pMsg.xxSerNumList.getValidCount(); i++) {
            serNumPMsg = pMsg.xxSerNumList.no(i);

            ZYPEZDItemValueSetter.setValue(mdseSerNumRngTMsg.mdseSerNumRngPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.MDSE_SER_NUM_RNG_SQ));
            ZYPEZDItemValueSetter.setValue(mdseSerNumRngTMsg.mdseCd, serNumPMsg.mdseCd.getValue());
            ZYPEZDItemValueSetter.setValue(mdseSerNumRngTMsg.fromSerNum, serNumPMsg.fromSerNum.getValue());
            ZYPEZDItemValueSetter.setValue(mdseSerNumRngTMsg.thruSerNum, serNumPMsg.thruSerNum.getValue());
            ZYPEZDItemValueSetter.setValue(mdseSerNumRngTMsg.lgSerNum, serNumPMsg.lgSerNum.getValue());
            // START 2019/08/20 M.Naito [QC#52672,MOD]
            if (!ZYPCommonFunc.hasValue(serNumPMsg.lgSerNum) && ZYPCommonFunc.hasValue(serNumPMsg.fromSerNum)) {
                ZYPEZDItemValueSetter.setValue(mdseSerNumRngTMsg.lgSerNum, BigDecimal.valueOf(serNumPMsg.fromSerNum.getValue().length()));
            }
            // END 2019/08/20 M.Naito [QC#52672,MOD]
            ZYPEZDItemValueSetter.setValue(mdseSerNumRngTMsg.serNumDefFlg, serNumPMsg.serNumDefFlg.getValue());

            S21FastTBLAccessor.insert(mdseSerNumRngTMsg);
            if (!RTNCD_NORMAL.equals(mdseSerNumRngTMsg.getReturnCode())) {
                commonLogic.setErrorMessage(param, idx, NMZM0150E, new String[] {MSG_MDSE_SER_NUM_RNG });
                return false;
            }
        }

        return true;
    }

    private boolean insertSupd(NMZC004001PMsg param, int idx) {

        String glblCmpyCd = param.glblCmpyCd.getValue();
        NMZC004002PMsg pMsg = param.NMZC004002PMsg.no(idx);

        NMZC004002_xxSupdListPMsg supdPMsg;
        // START 2023/03/27 E.Watabe [QC#61207,MOD]
        //SUPD_RELN_STAGETMsg supdRelnStageTMsg = new SUPD_RELN_STAGETMsg();
        //ZYPEZDItemValueSetter.setValue(supdRelnStageTMsg.glblCmpyCd, glblCmpyCd);
        //for (int i = 0; i < pMsg.xxSupdList.getValidCount(); i++) {
        //    supdPMsg = pMsg.xxSupdList.no(i);
        //
        //    ZYPEZDItemValueSetter.setValue(supdRelnStageTMsg.supdRelnStagePk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SUPD_RELN_STAGE_SQ));
        //    ZYPEZDItemValueSetter.setValue(supdRelnStageTMsg.supdFromMdseCd, supdPMsg.supdFromMdseCd.getValue());
        //    ZYPEZDItemValueSetter.setValue(supdRelnStageTMsg.supdToMdseCd, supdPMsg.supdToMdseCd.getValue());
        //    ZYPEZDItemValueSetter.setValue(supdRelnStageTMsg.supdRelnStageDt, param.slsDt.getValue());
        //    ZYPEZDItemValueSetter.setValue(supdRelnStageTMsg.submtFlg, ZYPConstant.FLG_OFF_N);
        //
        //    S21FastTBLAccessor.insert(supdRelnStageTMsg);
        //    if (!RTNCD_NORMAL.equals(supdRelnStageTMsg.getReturnCode())) {
        //        commonLogic.setErrorMessage(param, idx, NMZM0150E, new String[] {MSG_SUPD_RELN_STAGE });
        //        return false;
        //    }
        //}
        for (int i = 0; i < pMsg.xxSupdList.getValidCount(); i++) {
            supdPMsg = pMsg.xxSupdList.no(i);

            String supdFromMdseCd = supdPMsg.supdToMdseCd.getValue();
            String supdToMdseCd = supdPMsg.supdFromMdseCd.getValue();

            if (existSupd(supdFromMdseCd, glblCmpyCd) == null) {
                if (!insertSupd(supdFromMdseCd, ZYPConstant.FLG_ON_Y, glblCmpyCd)) {
                    commonLogic.setErrorMessage(param, idx, ZZMM0001E, new String[] {MSG_PRM_TBL_SUPD, MSG_PRM_MDSE_CD, supdFromMdseCd });
                    return false;
                }
            }
            if (existSupd(supdToMdseCd, glblCmpyCd) != null) {
                if (!updateSupd(supdToMdseCd, glblCmpyCd)) {
                    commonLogic.setErrorMessage(param, idx, ZZMM0015E, new String[] {MSG_PRM_TBL_SUPD, MSG_PRM_MDSE_CD, supdToMdseCd });
                    return false;
                }
            } else {
                if (!insertSupd(supdToMdseCd, ZYPConstant.FLG_OFF_N, glblCmpyCd)) {
                    commonLogic.setErrorMessage(param, idx, ZZMM0001E, new String[] {MSG_PRM_TBL_SUPD, MSG_PRM_MDSE_CD, supdToMdseCd });
                    return false;
                }
            }
            if (!insertSupdReln(supdFromMdseCd, supdToMdseCd, param.slsDt.getValue(), glblCmpyCd)) {
                commonLogic.setErrorMessage(param, idx, ZZMM0001E, new String[] {MSG_PRM_TBL_SUPD_RELN, MSG_PRM_MDSE_CD, supdToMdseCd });
                return false;
            }
            if (!insertRelByMdseCd(supdFromMdseCd, supdToMdseCd, glblCmpyCd)) {
                commonLogic.setErrorMessage(param, idx, ZZMM0001E, new String[] {MSG_MDSE_ITEM_FLIP_SET, MSG_PRM_MDSE_CD, supdToMdseCd });
                return false;
            }

        }

        // END 2023/03/27 E.Watabe [QC#61207,MOD]

        return true;
    }

    // START 2023/03/27 E.Watabe [QC#61207,ADD]
    private SUPDTMsg existSupd(String mdseCd, String glblCmpyCd) {

        if (!ZYPCommonFunc.hasValue(mdseCd)) {
            return null;
        }

        SUPDTMsg supdTMsg = new SUPDTMsg();
        ZYPEZDItemValueSetter.setValue(supdTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(supdTMsg.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(supdTMsg.ezCancelFlag, "0");
        SUPDTMsg retVal = (SUPDTMsg) S21CacheTBLAccessor.findByKey(supdTMsg);
        return retVal;
    }

    private boolean insertSupd(String mdseCd, String incgFlg, String glblCmpyCd) {

        SUPDTMsg insertMsg = new SUPDTMsg();
        ZYPEZDItemValueSetter.setValue(insertMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insertMsg.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(insertMsg.incgFlg, incgFlg);
        S21FastTBLAccessor.insert(insertMsg);

        if (!RTNCD_NORMAL.equals(insertMsg.getReturnCode())) {
            return false;
        }
        return true;
    }

    private boolean updateSupd(String mdseCd, String glblCmpyCd) {

        SUPDTMsg updateMsg = new SUPDTMsg();
        ZYPEZDItemValueSetter.setValue(updateMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(updateMsg.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(updateMsg.ezCancelFlag, "0");
        updateMsg = (SUPDTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(updateMsg);
        if (updateMsg != null) {
            return false;
        }
        ZYPEZDItemValueSetter.setValue(updateMsg.incgFlg, ZYPConstant.FLG_OFF_N);
        S21FastTBLAccessor.update(updateMsg);

        if (!RTNCD_NORMAL.equals(updateMsg.getReturnCode())) {
            return false;
        }
        return true;
    }

    private boolean insertSupdReln(String supdFromMdseCd, String supdToMdseCd, String slsDt, String glblCmpyCd) {

        SUPD_RELNTMsg insertMsg = new SUPD_RELNTMsg();
        ZYPEZDItemValueSetter.setValue(insertMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insertMsg.supdRelnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SUPD_RELN_SQ));
        ZYPEZDItemValueSetter.setValue(insertMsg.supdFromMdseCd, supdFromMdseCd);
        ZYPEZDItemValueSetter.setValue(insertMsg.supdToMdseCd, supdToMdseCd);
        ZYPEZDItemValueSetter.setValue(insertMsg.supdCratDt, slsDt);
        S21FastTBLAccessor.insert(insertMsg);

        if (!RTNCD_NORMAL.equals(insertMsg.getReturnCode())) {
            return false;
        }
        return true;
    }
    
    private boolean insertRelByMdseCd(String supdFromMdseCd, String supdToMdseCd, String glblCmpyCd) {

        MDSE_ITEM_FLIP_SETTMsg insertMsg = new MDSE_ITEM_FLIP_SETTMsg();
        ZYPEZDItemValueSetter.setValue(insertMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insertMsg.mdseCd, supdToMdseCd);
        ZYPEZDItemValueSetter.setValue(insertMsg.relnMdseCd, supdFromMdseCd);
        ZYPEZDItemValueSetter.setValue(insertMsg.mdseItemRelnTpCd, MDSE_ITEM_RELN_TP.COMPATIBLE);
        S21FastTBLAccessor.insert(insertMsg);

        if (!RTNCD_NORMAL.equals(insertMsg.getReturnCode())) {
            return false;
        }
        return true;
    }

    // END 2023/03/27 E.Watabe [QC#61207,ADD]

    private boolean insertRel(NMZC004001PMsg param, int idx) {

        String glblCmpyCd = param.glblCmpyCd.getValue();
        NMZC004002PMsg pMsg = param.NMZC004002PMsg.no(idx);

        NMZC004002_xxRelListPMsg relPMsg;
        MDSE_ITEM_FLIP_SETTMsg mdseItemFlipSetTMsg = new MDSE_ITEM_FLIP_SETTMsg();
        ZYPEZDItemValueSetter.setValue(mdseItemFlipSetTMsg.glblCmpyCd, glblCmpyCd);
        for (int i = 0; i < pMsg.xxRelList.getValidCount(); i++) {
            relPMsg = pMsg.xxRelList.no(i);

            ZYPEZDItemValueSetter.setValue(mdseItemFlipSetTMsg.mdseCd, relPMsg.mdseCd.getValue());
            ZYPEZDItemValueSetter.setValue(mdseItemFlipSetTMsg.relnMdseCd, relPMsg.relnMdseCd.getValue());
            ZYPEZDItemValueSetter.setValue(mdseItemFlipSetTMsg.mdseItemRelnTpCd, relPMsg.mdseItemRelnTpCd.getValue());

            S21FastTBLAccessor.insert(mdseItemFlipSetTMsg);
            if (!RTNCD_NORMAL.equals(mdseItemFlipSetTMsg.getReturnCode())) {
                commonLogic.setErrorMessage(param, idx, NMZM0150E, new String[] {MSG_MDSE_ITEM_FLIP_SET });
                return false;
            }
        }

        return true;
    }

    private boolean insertCustXref(NMZC004001PMsg param, int idx) {

        String glblCmpyCd = param.glblCmpyCd.getValue();
        NMZC004002PMsg pMsg = param.NMZC004002PMsg.no(idx);

        NMZC004002_xxCustXrefListPMsg custPMsg;
        CUST_MDSE_XREFTMsg custMdseXrefTMsg = new CUST_MDSE_XREFTMsg();
        ZYPEZDItemValueSetter.setValue(custMdseXrefTMsg.glblCmpyCd, glblCmpyCd);
        for (int i = 0; i < pMsg.xxCustXrefList.getValidCount(); i++) {
            custPMsg = pMsg.xxCustXrefList.no(i);

            ZYPEZDItemValueSetter.setValue(custMdseXrefTMsg.mdseCd, custPMsg.mdseCd.getValue());
            ZYPEZDItemValueSetter.setValue(custMdseXrefTMsg.custMdseCd, custPMsg.custMdseCd.getValue());
            ZYPEZDItemValueSetter.setValue(custMdseXrefTMsg.dsAcctNum, custPMsg.dsAcctNum.getValue());
            ZYPEZDItemValueSetter.setValue(custMdseXrefTMsg.custMdseNm, custPMsg.custMdseNm.getValue());
            ZYPEZDItemValueSetter.setValue(custMdseXrefTMsg.effFromDt, custPMsg.effFromDt.getValue());
            ZYPEZDItemValueSetter.setValue(custMdseXrefTMsg.custMdseXrefEnblFlg, custPMsg.custMdseXrefEnblFlg.getValue());

            S21FastTBLAccessor.insert(custMdseXrefTMsg);
            if (!RTNCD_NORMAL.equals(custMdseXrefTMsg.getReturnCode())) {
                commonLogic.setErrorMessage(param, idx, NMZM0150E, new String[] {MST_CUST_MDSE_XREF });
                return false;
            }
        }

        return true;
    }

    private boolean saveAsl(NMZC004001PMsg param, int idx) {

        String glblCmpyCd = param.glblCmpyCd.getValue();
        NMZC004002PMsg pMsg = param.NMZC004002PMsg.no(idx);

        ASL_DTLTMsg aslDtlTMsg = new ASL_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(aslDtlTMsg.glblCmpyCd, glblCmpyCd);
        for (int i = 0; i < pMsg.xxAslList.getValidCount(); i++) {
            // QC#4342
            // Call ASL API
            NMZC004002_xxAslListPMsg aslPMsg = pMsg.xxAslList.no(i);
            Map<String, Object> aslHdrMap = aslHdrList.get(i);
            if(!callAslUpdateApi(param, idx, aslPMsg, aslHdrMap)){
                return false;
            }

            // Update Primary Supply Flag
            BigDecimal aslHdrPk = (BigDecimal) aslHdrList.get(i).get("ASL_HDR_PK");
            if (BigDecimal.ZERO.equals(aslHdrPk)) {
                aslDtlTMsg.setSQLID("001");
                aslDtlTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                aslDtlTMsg.setConditionValue("mdseCd01", pMsg.mdseCd_M.getValue());
                aslDtlTMsg.setConditionValue("primSplyFlg01", ZYPConstant.FLG_ON_Y);
                aslDtlTMsg.setConditionValue("aslHdrPk01", aslHdrPk);
                ASL_DTLTMsgArray aslDtlTMsgArray = (ASL_DTLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(aslDtlTMsg);
                for (int j = 0; j < aslDtlTMsgArray.length(); i++) {
                    aslDtlTMsg = (ASL_DTLTMsg) aslDtlTMsgArray.get(j);
                    ZYPEZDItemValueSetter.setValue(aslDtlTMsg.primSplyFlg, ZYPConstant.FLG_OFF_N);

                    S21FastTBLAccessor.update(aslDtlTMsg);
                    if (!RTNCD_NORMAL.equals(aslDtlTMsg.getReturnCode())) {
                        commonLogic.setErrorMessage(param, idx, NMZM0150E, new String[] {MST_ASL_DTL });
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    // QC#4342
    private boolean callAslUpdateApi(NMZC004001PMsg param, int idx, NMZC004002_xxAslListPMsg aslPMsg, Map<String, Object> aslHdrMap) {
        NPZC115001PMsg pMsg = new NPZC115001PMsg();
        pMsg.xxAslDtlList.setValidCount(1);

        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC115001Constant.ENTRY_MODE);
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.procDt, param.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.aslHdrPk, (BigDecimal) aslHdrMap.get("ASL_HDR_PK"));
        ZYPEZDItemValueSetter.setValue(pMsg.prntVndCd, aslPMsg.prntVndCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.aslNm, (String) aslHdrMap.get("ASL_NM"));
        ZYPEZDItemValueSetter.setValue(pMsg.aslDescTxt, (String) aslHdrMap.get("ASL_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(pMsg.aslStartDt, (String) aslHdrMap.get("ASL_START_DT"));
        ZYPEZDItemValueSetter.setValue(pMsg.aslEndDt, (String) aslHdrMap.get("ASL_END_DT"));

        ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(0).mdseCd, aslPMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(0).vndUomCd, aslPMsg.vndUomCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(0).splyItemNum, aslPMsg.splyItemNum);
        ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(0).vndCd, aslPMsg.vndCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(0).primSplyFlg, aslPMsg.primSplyFlg);
        ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(0).unitPrcAmt, aslPMsg.unitPrcAmt);
        ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(0).effFromDt, aslPMsg.effFromDt.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(0).effThruDt, aslPMsg.effThruDt);
        ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(0).aslItemCmntTxt, aslPMsg.aslItemCmntTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(0).vndLtDaysNum, aslPMsg.vndLtDaysNum); // 2020/01/30 QC#55620 Add

        new NPZC115001().execute(pMsg, ONBATCH_TYPE.BATCH);

        boolean isError = false;
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(pMsg);
            for (String xxMsgId : xxMsgIdList) {
                S21InfoLogOutput.println(xxMsgId);
                if (xxMsgId.endsWith("E")) {
                    isError = true;
                    commonLogic.setErrorMessage(param, idx, xxMsgId, null);
                    return false;
                }
            }
        }
        return !isError;
    }
    
    // QC#4342
    private boolean insertTermCondOpt(NMZC004001PMsg param, int idx) {

        String glblCmpyCd = param.glblCmpyCd.getValue();
        NMZC004002PMsg pMsg = param.NMZC004002PMsg.no(idx);

        NMZC004002_xxTermListPMsg termPMsg;
        MDSE_TERM_COND_OPTTMsg custMdseXrefTMsg = new MDSE_TERM_COND_OPTTMsg();
        ZYPEZDItemValueSetter.setValue(custMdseXrefTMsg.glblCmpyCd, glblCmpyCd);
        for (int i = 0; i < pMsg.xxTermList.getValidCount(); i++) {
            termPMsg = pMsg.xxTermList.no(i);

            ZYPEZDItemValueSetter.setValue(custMdseXrefTMsg.mdseCd, termPMsg.mdseCd.getValue());
            ZYPEZDItemValueSetter.setValue(custMdseXrefTMsg.mdseTermCondOptPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.MDSE_TERM_COND_OPT_SQ));
            ZYPEZDItemValueSetter.setValue(custMdseXrefTMsg.termCondOptTpCd, termPMsg.termCondOptTpCd.getValue());
            ZYPEZDItemValueSetter.setValue(custMdseXrefTMsg.termCondOptValTxt, termPMsg.termCondOptValTxt.getValue());

            S21FastTBLAccessor.insert(custMdseXrefTMsg);
            if (!RTNCD_NORMAL.equals(custMdseXrefTMsg.getReturnCode())) {
                commonLogic.setErrorMessage(param, idx, NMZM0150E, new String[] {MST_MDSE_TERM_COND_OPT });
                return false;
            }
        }

        return true;
    }

    private boolean savePrcListEquip(NMZC004001PMsg param, int idx, boolean msrcFlg) {

        String glblCmpyCd = param.glblCmpyCd.getValue();
        String slsDt = param.slsDt.getValue();
        NMZC004002PMsg pMsg = param.NMZC004002PMsg.no(idx);
        String mdseCd = pMsg.mdseCd_M.getValue();

        String prcItemCd = mdseCd;
        String newPrcThruDt = null;
        String oldPrcThruDt = null;
        BigDecimal oldPrcListEquipPk = null;
        String msrcPrcCatgCd = null;
        BigDecimal prcListEquipPrcAmt = null;
        BigDecimal oldPrcListEquipPrcAmt = null;
        if (msrcFlg) {
            msrcPrcCatgCd = pMsg.prcCatgCd_P1.getValue();
            prcListEquipPrcAmt = pMsg.prcListEquipPrcAmt_P1.getValue();
        } else {
            msrcPrcCatgCd = pMsg.prcCatgCd_P2.getValue();
            prcListEquipPrcAmt = pMsg.prcListEquipPrcAmt_P2.getValue();
        }

        if (commonLogic.usableMdseCd8(glblCmpyCd, mdseCd, AMER_MDSE_CLS_TP_CD_A)) {
            
            // 01/27/2017 QC#17078 Mod Start
//            prcItemCd = mdseCd.substring(0, LG_8);
            prcItemCd = mdseCd;
            if (prcItemCd.length() >= 8) {
                prcItemCd = prcItemCd.substring(0, LG_8);
            }
            // 01/27/2017 QC#17078 Mod End

            Map<String, Object> map = NMZC004001Query.getInstance().getPrcListEquip(glblCmpyCd, slsDt, msrcPrcCatgCd, prcItemCd, ZYPConstant.FLG_ON_Y);
            if (map != null) {
                // QC#55754 2020/02/18 Mod Start
                //if (prcListEquipPrcAmt.compareTo((BigDecimal) map.get("PRC_LIST_EQUIP_PRC_AMT")) == 0) {
                //    oldPrcThruDt = ZYPDateUtil.addDays(slsDt, -1);
                //    oldPrcListEquipPk = (BigDecimal) map.get("PRC_LIST_EQUIP_PK");
                //} else {
                //    newPrcThruDt = (String) map.get("EFF_THRU_DT");
                //}
                oldPrcThruDt = ZYPDateUtil.addDays(slsDt, -1);
                oldPrcListEquipPk = (BigDecimal) map.get("PRC_LIST_EQUIP_PK");
                oldPrcListEquipPrcAmt = (BigDecimal) map.get("PRC_LIST_EQUIP_PRC_AMT");
                newPrcThruDt = (String) map.get("EFF_THRU_DT");
                // QC#55754 2020/02/18 Mod End
            } else {
                map = NMZC004001Query.getInstance().getPrcListEquip(glblCmpyCd, slsDt, msrcPrcCatgCd, prcItemCd, null);
                if (map != null) {
                    newPrcThruDt = (String) map.get("EFF_FROM_DT");
                }
            }
        }

        PRC_LIST_EQUIPTMsg prcListEquipTMsg = new PRC_LIST_EQUIPTMsg();
        ZYPEZDItemValueSetter.setValue(prcListEquipTMsg.glblCmpyCd, glblCmpyCd);
        if (oldPrcThruDt == null) {
            ZYPEZDItemValueSetter.setValue(prcListEquipTMsg.prcListEquipPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_LIST_EQUIP_SQ));
            ZYPEZDItemValueSetter.setValue(prcListEquipTMsg.prcCatgCd, msrcPrcCatgCd);
            ZYPEZDItemValueSetter.setValue(prcListEquipTMsg.prcQlfyTpCd, PRC_QLFY_TP.ITEM_CODE);
            ZYPEZDItemValueSetter.setValue(prcListEquipTMsg.prcQlfyValTxt, prcItemCd);
            ZYPEZDItemValueSetter.setValue(prcListEquipTMsg.pkgUomCd, PKG_UOM.EACH);
            ZYPEZDItemValueSetter.setValue(prcListEquipTMsg.prcListEquipPrcAmt, prcListEquipPrcAmt);
            ZYPEZDItemValueSetter.setValue(prcListEquipTMsg.openMktFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(prcListEquipTMsg.effFromDt, slsDt);
            ZYPEZDItemValueSetter.setValue(prcListEquipTMsg.effThruDt, newPrcThruDt);
            ZYPEZDItemValueSetter.setValue(prcListEquipTMsg.delFlg, ZYPConstant.FLG_OFF_N);

            S21FastTBLAccessor.insert(prcListEquipTMsg);
            if (!RTNCD_NORMAL.equals(prcListEquipTMsg.getReturnCode())) {
                commonLogic.setErrorMessage(param, idx, NMZM0150E, new String[] {MST_PRC_LIST_EQUIP });
                return false;
            }
        } else {
            // QC#55754 2020/02/18 Mod Start
            //ZYPEZDItemValueSetter.setValue(prcListEquipTMsg.prcListEquipPk, oldPrcListEquipPk);
            //prcListEquipTMsg = (PRC_LIST_EQUIPTMsg) S21ApiTBLAccessor.findByKeyForUpdate(prcListEquipTMsg);
            //ZYPEZDItemValueSetter.setValue(prcListEquipTMsg.effThruDt, oldPrcThruDt);
            //S21FastTBLAccessor.update(prcListEquipTMsg);
            //if (!RTNCD_NORMAL.equals(prcListEquipTMsg.getReturnCode())) {
            //    commonLogic.setErrorMessage(param, idx, NMZM0150E, new String[] {MST_PRC_LIST_EQUIP });
            //    return false;
            //}
            if (prcListEquipPrcAmt.compareTo(oldPrcListEquipPrcAmt) != 0) {
                ZYPEZDItemValueSetter.setValue(prcListEquipTMsg.prcListEquipPk, oldPrcListEquipPk);
                prcListEquipTMsg = (PRC_LIST_EQUIPTMsg) S21ApiTBLAccessor.findByKeyForUpdate(prcListEquipTMsg);
                ZYPEZDItemValueSetter.setValue(prcListEquipTMsg.effThruDt, oldPrcThruDt);

                S21FastTBLAccessor.update(prcListEquipTMsg);
                if (!RTNCD_NORMAL.equals(prcListEquipTMsg.getReturnCode())) {
                    commonLogic.setErrorMessage(param, idx, NMZM0150E, new String[] {MST_PRC_LIST_EQUIP });
                    return false;
                }

                PRC_LIST_EQUIPTMsg newPrcListEquipTMsg = new PRC_LIST_EQUIPTMsg();
                ZYPEZDItemValueSetter.setValue(newPrcListEquipTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(newPrcListEquipTMsg.prcListEquipPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_LIST_EQUIP_SQ));
                ZYPEZDItemValueSetter.setValue(newPrcListEquipTMsg.prcCatgCd, msrcPrcCatgCd);
                ZYPEZDItemValueSetter.setValue(newPrcListEquipTMsg.prcQlfyTpCd, PRC_QLFY_TP.ITEM_CODE);
                ZYPEZDItemValueSetter.setValue(newPrcListEquipTMsg.prcQlfyValTxt, prcItemCd);
                ZYPEZDItemValueSetter.setValue(newPrcListEquipTMsg.pkgUomCd, PKG_UOM.EACH);
                ZYPEZDItemValueSetter.setValue(newPrcListEquipTMsg.prcListEquipPrcAmt, prcListEquipPrcAmt);
                ZYPEZDItemValueSetter.setValue(newPrcListEquipTMsg.openMktFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(newPrcListEquipTMsg.effFromDt, slsDt);
                ZYPEZDItemValueSetter.setValue(newPrcListEquipTMsg.effThruDt, newPrcThruDt);
                ZYPEZDItemValueSetter.setValue(newPrcListEquipTMsg.delFlg, ZYPConstant.FLG_OFF_N);

                S21FastTBLAccessor.insert(newPrcListEquipTMsg);
                if (!RTNCD_NORMAL.equals(newPrcListEquipTMsg.getReturnCode())) {
                    commonLogic.setErrorMessage(param, idx, NMZM0150E, new String[] {MST_PRC_LIST_EQUIP });
                    return false;
                }
                // QC#55754 2020/02/18 Mod End
            }
        }

        return true;
    }

    private boolean insertSvcMdl(NMZC004001PMsg param, int idx) {

        DS_MDL_CONFIGTMsg dsMdlConfigTMsg = new DS_MDL_CONFIGTMsg();
        ZYPEZDItemValueSetter.setValue(dsMdlConfigTMsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(dsMdlConfigTMsg.mdlId, svcMdlId);
        // START 2022/12/20 E.Watabe [QC#60861, MOD]
        // ZYPEZDItemValueSetter.setValue(dsMdlConfigTMsg.prntMdseCd, param.NMZC004002PMsg.no(idx).mdseCd_M.getValue());
        ORD_TAKE_MDSETMsg ordTakeMdseTMsg = new ORD_TAKE_MDSETMsg();
        ordTakeMdseTMsg.setSQLID("001");
        ordTakeMdseTMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        ordTakeMdseTMsg.setConditionValue("mdseCd01", param.NMZC004002PMsg.no(idx).mdseCd_M.getValue());
        ORD_TAKE_MDSETMsgArray ordTakeMdseTMsgArray = (ORD_TAKE_MDSETMsgArray) S21ApiTBLAccessor.findByCondition(ordTakeMdseTMsg);
        if (ordTakeMdseTMsgArray.length() > 0) {
            ZYPEZDItemValueSetter.setValue(dsMdlConfigTMsg.prntMdseCd, ordTakeMdseTMsgArray.no(0).ordTakeMdseCd.getValue());
        }else{
            ZYPEZDItemValueSetter.setValue(dsMdlConfigTMsg.prntMdseCd, param.NMZC004002PMsg.no(idx).mdseCd_M.getValue());
        }
        // END   2022/12/20 E.Watabe [QC#60861, MOD]
        ZYPEZDItemValueSetter.setValue(dsMdlConfigTMsg.dsMdlConfigPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_MDL_CONFIG_SQ));
        ZYPEZDItemValueSetter.setValue(dsMdlConfigTMsg.prntConfigPk, dsMdlConfigTMsg.dsMdlConfigPk);
        ZYPEZDItemValueSetter.setValue(dsMdlConfigTMsg.effFromDt, param.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(dsMdlConfigTMsg.mdlConfigFlg, ZYPConstant.FLG_OFF_N);

        S21FastTBLAccessor.insert(dsMdlConfigTMsg);
        if (!RTNCD_NORMAL.equals(dsMdlConfigTMsg.getReturnCode())) {
            commonLogic.setErrorMessage(param, idx, NMZM0150E, new String[] {MSG_DS_MDL_CONFIG });
            return false;
        }

        return true;
    }

    private static void sendMsgToDealConfigurator(NMZC004001PMsg param) {

        try {

            Event event = new Event();
            event.setReferencedEntity(REF_ENTRY_ITEM);

            event.setOperationType(OPERATIONTYPES.CREATE);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSSS");
            Date formatDate = sdf.parse(sdf.format(new Date()));

            GregorianCalendar c = new GregorianCalendar();
            c.setTime(formatDate);
            XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
            event.setCreateStamp(date2);

            event.setId(1);

            ArrayOfKeyReference ak = new ArrayOfKeyReference();

            KeyReference kr = new KeyReference();
            kr.setType(REFERENCETYPES.TECHNICAL_MNEMONICAL);

            ArrayOfAttribute aa = new ArrayOfAttribute();
            Attribute a = new Attribute();
            a.setName(ATTR_NM_MDSE_CD);

            // Set MDSE Code
            for (int i = 0; i < param.NMZC004002PMsg.getValidCount(); i++) {

                a.setValue(param.NMZC004002PMsg.no(i).mdseCd_M.getValue());
                aa.getAttribute().add(a);

                kr.setAttributes(aa);

                ak.getKeyReference().add(kr);
            }

            event.setKeyReferences(ak);

            ArrayOfEvent ae = new ArrayOfEvent();
            ae.getEvent().add(event);

            printRequest(ae);

            logger.debug("Proxy invocation starting");

            // Invoke the proxy
            new MasterDataMessagingServiceProxy().synchronizationMessage(ae);

            logger.debug("Proxy invocation complete");

        } catch (Throwable e) {
            logger.error(NMZM0137W);
        }
    }

    private static void printRequest(ArrayOfEvent ae) throws JAXBException {

        StringWriter stringWriter = new StringWriter();

        JAXBContext jaxbContext = JAXBContext.newInstance(ArrayOfEvent.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // format the XML output
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        QName qName = new QName("com.canon.usa.s21.integration.service.masterdata.messaging.type.ArrayOfEvent", "ArrayOfEvent");
        JAXBElement<ArrayOfEvent> root = new JAXBElement<ArrayOfEvent>(qName, ArrayOfEvent.class, ae);

        jaxbMarshaller.marshal(root, stringWriter);

        String result = stringWriter.toString();

        logger.debug(result);
    }

	private static String getMdseNm(String mdseDescShortTxt) {
        if (ZYPCommonFunc.hasValue(mdseDescShortTxt) && mdseDescShortTxt.length() > 30) {
            return mdseDescShortTxt.substring(0, 30);
        } else {
            return mdseDescShortTxt;
        }
    }

    private static String getMdseFmlNm(String mdseDescShortTxt) {
        if (ZYPCommonFunc.hasValue(mdseDescShortTxt) && mdseDescShortTxt.length() > 90) {
            return mdseDescShortTxt.substring(0, 90);
        } else {
            return mdseDescShortTxt;
        }
    }

    // Sol#477(QC21587)
    private boolean insertMrpInfo(NMZC004001PMsg param, int idx) {

        NMZC004002PMsg pMsg = param.NMZC004002PMsg.no(idx);
        commonLogic = new NMZC004001CommonLogic();
        String glblCmpyCd = param.glblCmpyCd.getValue();

        MDSETMsg mdseTMsg = commonLogic.getMdse(glblCmpyCd, pMsg.mdseCd_M.getValue());
        if (mdseTMsg == null) {
            if (!commonLogic.setErrorMessage(param, idx, NMZM0165E, null)) {
                return false;
            }
        }

        if (ZYPConstant.FLG_OFF_N.equals(mdseTMsg.invtyCtrlFlg.getValue())) {
            return true;
        }

        if (!RGTN_STS.READY_FOR_ORDER_TAKING.equals(mdseTMsg.rgtnStsCd.getValue())) {
            return true;
        }

        // get MRP Default Item Plan info
        List<Map<String, String>> mrpDefaultPlnList = commonLogic.getMrpDefItemPln(glblCmpyCd, param.slsDt.getValue(), mdseTMsg);

        if (mrpDefaultPlnList == null || mrpDefaultPlnList.isEmpty()) {
            return true;
        }

        for (Map<String, String> mrpDefaultPlnMap : mrpDefaultPlnList) {

            BigDecimal mrpInfoPk = commonLogic.getMrpInfoPk(glblCmpyCd, mrpDefaultPlnMap.get("MRP_PLN_NM"));
            MRP_INFOTMsg mrpInfoTMsg = commonLogic.getMrpInfo(glblCmpyCd, mrpInfoPk);

            MRP_INFOTMsg tMsg = new MRP_INFOTMsg();
            tMsg.mrpInfoPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.MRP_INFO_SQ));
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            String mdseCdTrim = pMsg.mdseCd_M.getValue().trim();
            if (mdseCdTrim.length() >= LG_8) {
                mdseCdTrim = mdseCdTrim.substring(0, LG_8);
                ORD_TAKE_MDSETMsg ordTakeMdseTMsg = commonLogic.getOrdTakeMdse(glblCmpyCd, mdseCdTrim);
                if (ordTakeMdseTMsg != null) {
                    ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, ordTakeMdseTMsg.ordTakeMdseCd.getValue());
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, pMsg.mdseCd_M);
                }
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, pMsg.mdseCd_M);
            }
            ZYPEZDItemValueSetter.setValue(tMsg.mrpPlnNm, mrpDefaultPlnMap.get("MRP_PLN_NM"));
            ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCatgCd, mrpDefaultPlnMap.get("RTL_WH_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, mrpDefaultPlnMap.get("RTL_WH_CD"));
            if (RTL_WH_CATG.TECH_CAR_STOCK.equals(tMsg.rtlWhCatgCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(tMsg.rtlSwhCd, "G");
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.rtlSwhCd, "NEW");
            }
            ZYPEZDItemValueSetter.setValue(tMsg.invtyLocCd, tMsg.rtlWhCd.getValue() + tMsg.rtlSwhCd.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.mrpInfoRgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
            ZYPEZDItemValueSetter.setValue(tMsg.minOrdQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tMsg.incrOrdQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tMsg.ovrdMaxInvtyQty, BigDecimal.ZERO);
            if (mrpInfoTMsg != null) {
                ZYPEZDItemValueSetter.setValue(tMsg.procrTpCd, PROCR_TP.WAREHOUSE);
                ZYPEZDItemValueSetter.setValue(tMsg.rplshDlyFlg, mrpInfoTMsg.rplshDlyFlg);
                ZYPEZDItemValueSetter.setValue(tMsg.rplshMonFlg, mrpInfoTMsg.rplshMonFlg);
                ZYPEZDItemValueSetter.setValue(tMsg.rplshTueFlg, mrpInfoTMsg.rplshTueFlg);
                ZYPEZDItemValueSetter.setValue(tMsg.rplshWedFlg, mrpInfoTMsg.rplshWedFlg);
                ZYPEZDItemValueSetter.setValue(tMsg.rplshThuFlg, mrpInfoTMsg.rplshThuFlg);
                ZYPEZDItemValueSetter.setValue(tMsg.rplshFriFlg, mrpInfoTMsg.rplshFriFlg);
                ZYPEZDItemValueSetter.setValue(tMsg.calcOrdProcCd, mrpInfoTMsg.calcOrdProcCd);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.procrTpCd, PROCR_TP.SUPPLIER);
                ZYPEZDItemValueSetter.setValue(tMsg.rplshDlyFlg, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(tMsg.rplshMonFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(tMsg.rplshTueFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(tMsg.rplshWedFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(tMsg.rplshThuFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(tMsg.rplshFriFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(tMsg.calcOrdProcCd, ZYPConstant.FLG_ON_1);
            }
            if (pMsg.xxSupdList.getValidCount() > 0 && !ZYPConstant.FLG_ON_Y.equals(skipSupdCratFlg)) {
                ZYPEZDItemValueSetter.setValue(tMsg.supdFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.supdFlg, ZYPConstant.FLG_OFF_N);
            }
            ZYPEZDItemValueSetter.setValue(tMsg.mrpEnblFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(tMsg.ropQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tMsg.maxInvtyQty, BigDecimal.ZERO);
            tMsg.locRoleTpCd.clear();
            tMsg.srcLocCd.clear();
            tMsg.srcRtlWhCd.clear();
            tMsg.srcRtlSwhCd.clear();
            tMsg.origMrpInfoPk.clear();
            tMsg.mrpCtrlCd.clear();
            tMsg.plnDelyDaysAot.clear();
            tMsg.mrpPlnCd.clear();
            tMsg.mrpRunPrmPk.clear();
            tMsg.mrpInfoCmntTxt.clear();

            S21FastTBLAccessor.insert(tMsg);
            if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                commonLogic.setErrorMessage(param, idx, NMZM0150E, new String[] {"MRP_INFO" });
                return false;
            }
        }

        return true;
    }
}
