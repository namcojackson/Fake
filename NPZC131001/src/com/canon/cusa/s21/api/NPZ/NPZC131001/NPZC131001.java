/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC131001;



import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.common.EZDPBigDecimalItem;
import parts.common.EZDPStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.GLBL_CMPYTMsg;
import business.db.MDSETMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.PO_DTLTMsg;
import business.db.PO_SER_NUMTMsg;
import business.db.PRNT_CMPY_VNDTMsg;
import business.db.SHPG_PLNTMsg;
import business.parts.NLZC002001PMsg;
import business.parts.NLZC010001PMsg;
import business.parts.NLZC201001PMsg;
import business.parts.NLZC302001PMsg;
import business.parts.NLZC403001PMsg;
import business.parts.NPZC004001PMsg;
import business.parts.NPZC104001PMsg;
import business.parts.NPZC131001PMsg;
import business.parts.NPZC131001_ordDtlInfoPMsg;
import business.parts.NPZC131001_serNumListPMsg;
import business.parts.NSZC001001PMsg;
import business.parts.NWZC003001PMsg;
import business.parts.NWZC206001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC002001.NLZC002001;
import com.canon.cusa.s21.api.NLZ.NLZC010001.NLZC010001;
import com.canon.cusa.s21.api.NLZ.NLZC201001.NLZC201001;
import com.canon.cusa.s21.api.NLZ.NLZC302001.NLZC302001;
import com.canon.cusa.s21.api.NLZ.NLZC403001.NLZC403001;
import com.canon.cusa.s21.api.NLZ.NLZC403001.constant.NLZC403001Constant;
import com.canon.cusa.s21.api.NPZ.NPZC004001.NPZC004001;
import com.canon.cusa.s21.api.NPZ.NPZC104001.NPZC104001;
import com.canon.cusa.s21.api.NPZ.NPZC104001.constant.NPZC104001Constant;
import com.canon.cusa.s21.api.NPZ.NPZC131001.constant.NPZC131001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.api.NWZ.NWZC003001.NWZC003001;
import com.canon.cusa.s21.api.NWZ.NWZC206001.NWZC206001;
import com.canon.cusa.s21.api.NWZ.NWZC206001.constant.NWZC206001Constant;
import com.canon.cusa.s21.common.NLC.NLCC001001.TRX_RSN;
import com.canon.cusa.s21.common.NLX.NLXC016001.NLXSceConst;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DRCT_SHIP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SER_LOC_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SER_TRX_EVENT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SER_TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_PLN_UPD_MODE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_USG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPExtnNumbering;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * NPZC1310:Drop Ship Create API
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/09   CITS            T.Kikuhara      Create          N/A
 * 2016/03/25   CSAI            K.Lee           Update          QC#6024
 * 2016/03/30   CSAI            K.Lee           Update          QC#6180
 * 2016/03/30   CSAI            K.Lee           Update          QC#6175
 * 2016/06/23   CSAI            K.Lee           Update          QC#10718
 * 2016/07/04   CSAI            T.Wada          Update          QC#8269
 * 2016/07/14   CITS            K.Ogino         Update          QC#11935
 * 2016/07/14   CITS            K.Ogino         Update          QC#11936
 * 2016/07/14   CITS            K.Ogino         Update          QC#11937
 * 2016/07/14   CITS            K.Ogino         Update          QC#11938
 * 2016/07/15   CITS            K.Ogino         Update          QC#11938
 * 2016/08/12   CSAI            T.Wada          Update          QC#13225
 * 2016/09/21   CSAI            T.Hakodate      Update          QC#14457
 * 2017/01/12   CITS            K.Kameoka       Update          QC#17077
 * 2017/08/17   CITS            K.Ogino         Update          QC#20610
 * 2017/09/14   CITS            K.Ogino         Update          QC#21082
 * 2017/10/04   CITS            T.Tokutomi      Update          QC#21191
 * 2018/05/17   CITS            T.Tokutomi      Update          QC#25499
 * 2018/06/05   CITS            K.Ogino         Update          QC#26104
 * 2018/06/15   CITS            K.Ogino         Update          QC#26730
 * 2018/07/30   CITS            K.Ogino         Update          QC#27503
 * 2018/08/13   CITS            T.Hakodate      Update          QC#27719
 * 2019/02/19   Fujitsu         T.Ogura         Update          QC#30312
 * 2019/07/09   CITS            R.Shimamoto     Update          QC#51173
 * 2019/07/24   CITS            R.Shimamoto     Update          QC#51691
 * 2019/07/30   CITS            R.Shimamoto     Update          QC#52080
 * 2019/10/03   Fujitsu         C.Hara          Update          QC#53186
 * 2020/06/04   Fujitsu         T.Ogura         Update          QC#56912
 * 2022/10/21   CITS            R.Azucena       Update          QC#60682
 *</pre>
 */
public class NPZC131001 extends S21ApiCommonBase {

    /** S21ApiMessageMap */
    private S21ApiMessageMap glMsgMap = null;

    /** SQL access parts */
    private S21SsmBatchClient glSsmBatchClient = null;

    /** OnBatchType */
    private ONBATCH_TYPE glOnBatchType = null;

    /** NPZC131001PMsg */
    private NPZC131001PMsg glParam = null;

    /** Sort NPZC131001_ordDtlInfoPMsg List */
    private List<NPZC131001_ordDtlInfoPMsg> glParamPoDtlList = null;

    /** PO Head data */
    private Map<String, Object> glPoHead = null;

    /** PO Detail List */
    private List<Map<String, Object>> glPoDetailList = null;

    /** Shipping Plan List */
    private List<SHPG_PLNTMsg> glShpgPlnList = null;

    /** Tangible data List */
    private List<NPZC131001_ordDtlInfoPMsg> glTtangibleList = null;

    /** Tangible PO Detail List */
    private List<Map<String, Object>> glTtangiblePoDtlList = null;

    /** Tangible Shipping Plan List */
    private List<SHPG_PLNTMsg> glTtangibleShpgPlnList = null;

    /** InTangible data List */
    private List<NPZC131001_ordDtlInfoPMsg> glInTtangibleList = null;

    /** InTangible PO Detail List */
    private List<Map<String, Object>> glInTtangiblePoDtlList = null;

    /** InTangible Shipping Plan List */
    private List<SHPG_PLNTMsg> glInTtangibleShpgPlnList = null;

    /** InTangible MDSE List */
    private List<MDSETMsg> glInTtangibleMdseList = null;

    /**
     * <pre>Constructor</pre>
     */
    public NPZC131001() {
        super();
    }

    /**
     * <pre>
     * Drop Ship Create API
     * call execute(NPZC131001PMsg, ONBATCH_TYPE) method by each PMsg.
     * </pre>
     * @param params NPZC131001PMsg List
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NPZC131001PMsg> params, final ONBATCH_TYPE onBatchType) {

        for (int i = 0; i < params.size(); i++) {
            execute(params.get(i), onBatchType);
        }

    }

    /**
     * <pre>
     * Drop Ship Create API
     * Create Dummy Or Cancel
     * </pre>
     * @param param NPZC131001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NPZC131001PMsg param, final ONBATCH_TYPE onBatchType) {

        glOnBatchType = onBatchType;
        glParam = param;
        glMsgMap = new S21ApiMessageMap(param);
        glSsmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        glPoHead = new HashMap<String, Object>();

        // Check In-parameter
        if (!chkParam()) {
            glMsgMap.flush();
            return;
        }

        // Check PO Header Exist
        if (!chkPoHeaderExist()) {
            glMsgMap.flush();
            return;
        }

        // Exec Main Process
        executeMain();

        glMsgMap.flush();

        return;

    }

    /**
     * <pre>
     * check In-parameter
     * </pre>
     * @return check result(OK:true, NG:false)
     */
    private boolean chkParam() {

        boolean bReturn = true;

        // GLBL_CMPY_CD
        if (!ZYPCommonFunc.hasValue(glParam.glblCmpyCd)) {
            glMsgMap.addXxMsgId(NPZC131001Constant.NPZM0001E);
            return false;
        }

        // MODE
        if (!ZYPCommonFunc.hasValue(glParam.xxModeCd)) {
            glMsgMap.addXxMsgId(NPZC131001Constant.NPZM0093E);
            return false;
        }

        // SLS_DT
        if (!ZYPCommonFunc.hasValue(glParam.slsDt)) {
            glMsgMap.addXxMsgId(NPZC131001Constant.NPZM0180E);
            return false;
        }

        // PO_ORD_NUM
        if (!ZYPCommonFunc.hasValue(glParam.poOrdNum)) {
            glMsgMap.addXxMsgId(NPZC131001Constant.NPZM0018E);
            return false;
        }

        // Detail Zero Count
        if (glParam.ordDtlInfo.getValidCount() == 0) {
            glMsgMap.addXxMsgId(NPZC131001Constant.NPAM1351E);
            return false;
        }

        // Mode Create check
        if (NPZC131001Constant.MODE_CREATE.equals(glParam.xxModeCd.getValue())) {

            // RWS_REF_NUM
            if (!ZYPCommonFunc.hasValue(glParam.rwsRefNum)) {
                glMsgMap.addXxMsgId(NPZC131001Constant.NSZM0463E);
                return false;
            }

            // ETA_DT
            if (!ZYPCommonFunc.hasValue(glParam.etaDt)) {
                glMsgMap.addXxMsgId(NPZC131001Constant.NLZM2314E);
                return false;
            }
        }

        // Check Detail Param
        for (int i = 0; i < glParam.ordDtlInfo.getValidCount(); i++) {

            // PO_ORD_DTL_LINE_NUM
            if (!ZYPCommonFunc.hasValue(glParam.ordDtlInfo.no(i).poOrdDtlLineNum)) {
                glMsgMap.addXxMsgId(NPZC131001Constant.NPAM1250E);
                bReturn = false;
                break;
            }

            // Mode Create
            if (NPZC131001Constant.MODE_CREATE.equals(glParam.xxModeCd.getValue())) {

                // MDSE_CD
                if (!ZYPCommonFunc.hasValue(glParam.ordDtlInfo.no(i).mdseCd)) {
                    glMsgMap.addXxMsgId(NPZC131001Constant.NPZM0020E);
                    bReturn = false;
                    break;
                }

                // STK_STS_CD
                if (!ZYPCommonFunc.hasValue(glParam.ordDtlInfo.no(i).stkStsCd)) {
                    glMsgMap.addXxMsgId(NPZC131001Constant.NWZM0998E);
                    bReturn = false;
                    break;
                }

                // SHIP_QTY
                if (!ZYPCommonFunc.hasValue(glParam.ordDtlInfo.no(i).shipQty)) {
                    glMsgMap.addXxMsgId(NPZC131001Constant.NPZM0195E);
                    bReturn = false;
                    break;
                }
            }
        }

        return bReturn;
    }

    /**
     * <pre>
     * Check PO Header
     * </pre>
     * @return check result(OK:true, NG:false)
     */
    private boolean chkPoHeaderExist() {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put(NPZC131001Constant.GLBL_CMPY_CD, glParam.glblCmpyCd.getValue());
        queryParam.put(NPZC131001Constant.PO_ORD_NUM, glParam.poOrdNum.getValue());
        queryParam.put(NPZC131001Constant.VAR_CHAR_CONST_NM, NPZC131001Constant.CUST_DROP_SHIP_PO_QULF);
        // QC#27503
        glPoHead = (Map<String, Object>) glSsmBatchClient.queryObject("searchPo", queryParam);

        if (glPoHead == null) {
            String msg = S21MessageFunc.clspGetMessage(NPZC131001Constant.NPAM1169E, new String[]{glParam.poOrdNum.getValue()});
            S21InfoLogOutput.println(msg);
            glMsgMap.addXxMsgId(NPZC131001Constant.NPAM1287E);
            return false;
        }

        if (ZYPCommonFunc.hasValue((String) glPoHead.get("OPEN_PO_FLG")) && ZYPConstant.FLG_OFF_N.equals((String) glPoHead.get("OPEN_PO_FLG"))) {
            String msg = S21MessageFunc.clspGetMessage(NPZC131001Constant.NPZM0306E);
            S21InfoLogOutput.println(msg + " PO#:" + glParam.poOrdNum.getValue());
            glMsgMap.addXxMsgId(NPZC131001Constant.NPZM0306E);
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Drop Ship Create API (Main process)
     * </pre>
     * @return result(OK:true, NG:false)
     */
    private boolean executeMain() {

        // Sort Param PO Detail by PO_ORD_DTL_LINE_NUM
        glParamPoDtlList = new ArrayList<NPZC131001_ordDtlInfoPMsg>();

        for (int i = 0; i < glParam.ordDtlInfo.getValidCount(); i++) {
            glParamPoDtlList.add(glParam.ordDtlInfo.no(i));
        }

        // QC:51691 Start
//        Collections.sort(glParamPoDtlList, new Comp(NPZC131001Constant.PO_ORD_DTL_LINE_NUM));
        Collections.sort(glParamPoDtlList, new Comp("poOrdDtlLineNum"));
        // QC:51691 End

        glPoDetailList = new ArrayList<Map<String, Object>>();
        glShpgPlnList = new ArrayList<SHPG_PLNTMsg>();

        for (NPZC131001_ordDtlInfoPMsg paramPoDtl : glParamPoDtlList) {

            // Check PO Detail Exist
            Map<String, Object> poDetailInfo = chkPoDetailExist(paramPoDtl);
            if (poDetailInfo == null) {
                // QC#27503
                glMsgMap.addXxMsgId(NPZC131001Constant.NPZM0156E);
                return false;
            }

            glPoDetailList.add(poDetailInfo);

            // Check Shipping Plan Exist
            if (poDetailInfo.get(NPZC131001Constant.SHPG_PLN_NUM) == null) {
                glMsgMap.addXxMsgId(NPZC131001Constant.NPAM1318E);
                return false;
            }

            SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
            ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.glblCmpyCd, glParam.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.shpgPlnNum, (String) poDetailInfo.get(NPZC131001Constant.SHPG_PLN_NUM));

            shpgPlnTMsg = (SHPG_PLNTMsg) S21ApiTBLAccessor.findByKey(shpgPlnTMsg);

            if (shpgPlnTMsg == null) {
                glMsgMap.addXxMsgId(NPZC131001Constant.NPAM1318E);
                return false;
            }

            glShpgPlnList.add(shpgPlnTMsg);

            // Check MDSE only Create Mode
            if (NPZC131001Constant.MODE_CREATE.equals(glParam.xxModeCd.getValue())) {

                String mdseCd = shpgPlnTMsg.mdseCd.getValue();

                if (!checkMdse(paramPoDtl, mdseCd)) {
                    return false;
                }

            }

            // V1.3
            // Check RWS_REF_NUM Exist only Create Mode
            if (NPZC131001Constant.MODE_CREATE.equals(glParam.xxModeCd.getValue())) {

                String rwfRefNum = null;

                if (!chkRwsRefNum(poDetailInfo)) {

                    rwfRefNum = glParam.rwsRefNum.getValue();

                } else {

                    rwfRefNum = getRwsRefNum(poDetailInfo);

                    if (rwfRefNum == null) {
                        return false;
                    }
                }

                glParam.rwsRefNum.setValue(rwfRefNum);
            }
        }

        // Divide Mode
        if (NPZC131001Constant.MODE_CREATE.equals(glParam.xxModeCd.getValue())) {

            // Create
            if (!executeCreate()) {
                return false;
            }

        } else {

            // Cancel
            if (!executeCancel()) {
                return false;
            }

        }

        return true;
    }

    /**
     * <pre>
     * check PO Detail
     * </pre>
     * @param paramPoDtl NPZC131001_ordDtlInfoPMsg
     * @return poDetailInfo Map<String, Object>
     */
    private Map<String, Object> chkPoDetailExist(NPZC131001_ordDtlInfoPMsg paramPoDtl) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put(NPZC131001Constant.GLBL_CMPY_CD, glParam.glblCmpyCd.getValue());
        queryParam.put(NPZC131001Constant.ORIG_PO_ORD_NUM, glParam.poOrdNum.getValue());
        queryParam.put(NPZC131001Constant.ORIG_PO_ORD_DTL_LINE_NUM, paramPoDtl.poOrdDtlLineNum.getValue());
        queryParam.put(NPZC131001Constant.OPEN_PO_FLG, ZYPConstant.FLG_ON_Y);

        Map<String, Object> poDetailInfo = (Map<String, Object>) glSsmBatchClient.queryObject("searchPoDetail", queryParam);

        return poDetailInfo;

    }

    /**
     * <pre>
     * check MDSE
     * </pre>
     * @param paramPoDtl NPZC131001_ordDtlInfoPMsg
     * @param mdseCd String
     * @return check result(OK:true, NG:false)
     */
    private boolean checkMdse(NPZC131001_ordDtlInfoPMsg paramPoDtl, String shipMdse) {

        if (!ZYPCommonFunc.hasValue(shipMdse)) {
            return true;
        }

        String paramMdse = paramPoDtl.mdseCd.getValue();
        if (shipMdse.equals(paramMdse)) {
            return true;
        }

        // get ORD_TAKE_MDSE_CD
        ORD_TAKE_MDSETMsg ordTakeMdseTMsg = new ORD_TAKE_MDSETMsg();
        ordTakeMdseTMsg.glblCmpyCd.setValue(glParam.glblCmpyCd.getValue());

        // QC#26730
        String tmpShipMdse = "";
        String tmpParamMdse = "";
        // QC#17077 Start
        if (shipMdse.length() > NPZC131001Constant.LENGTH_8) {
            tmpShipMdse = shipMdse.substring(0, NPZC131001Constant.LENGTH_8);
        }
        ordTakeMdseTMsg.ordTakeMdseCd.setValue(tmpShipMdse);
        // ordTakeMdseTMsg.ordTakeMdseCd.setValue(shipMdse.substring(0, NPZC131001Constant.LENGTH_8));
        // QC#17077 End
        ordTakeMdseTMsg = (ORD_TAKE_MDSETMsg) S21ApiTBLAccessor.findByKey(ordTakeMdseTMsg);

        if (ordTakeMdseTMsg != null && EZDTBLAccessor.RTNCD_NORMAL.equals(ordTakeMdseTMsg.getReturnCode())) {

            // Check same MDSE
            // QC#17077 Start
            if (paramMdse.length() > NPZC131001Constant.LENGTH_8) {
                tmpParamMdse = paramMdse.substring(0, NPZC131001Constant.LENGTH_8);
            }
            if (tmpShipMdse.equals(tmpParamMdse)) {
            // if (shipMdse.substring(0, NPZC131001Constant.LENGTH_8).equals(paramMdse.substring(0, NPZC131001Constant.LENGTH_8))) {
            // QC#17077 End
                return true;
            }

        }

        // Check Supersede
        if (chkSupersede(paramMdse, shipMdse)) {
            return true;
        }

        glMsgMap.addXxMsgId(NPZC131001Constant.NPZM0230E);
        return false;
    }

    /**
     * <pre>
     * check Supersede
     * </pre>
     * @param mdseCd String
     * @return result(OK:true, NG:false)
     */
    private boolean chkSupersede(String paramMdse, String shipMdse) {

        NWZC206001PMsg pMsg = new NWZC206001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glParam.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, glParam.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, paramMdse);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC206001Constant.SUPD_LIST_MODE);
        ZYPEZDItemValueSetter.setValue(pMsg.xxAvalOrdFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pMsg.xxAvalPrchFlg, ZYPConstant.FLG_OFF_N);

        NWZC206001 dWZC206001 = new NWZC206001();
        dWZC206001.execute(pMsg, glOnBatchType);

        if (pMsg.xxMsgIdList.getValidCount() > 0) {

            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {

                glMsgMap.addXxMsgId(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());

            }

            return false;
        }

        if (pMsg.A.getValidCount() > 0) {

            boolean isMdseMach = false;
            for (int i = 0; i < pMsg.A.getValidCount(); i++) {

                if (ZYPConstant.FLG_ON_Y.equals(pMsg.A.no(i).incgFlg.getValue())) {
                    if (paramMdse.equals(pMsg.A.no(i).mdseCd.getValue())) {

                        isMdseMach = true;
                        break;
                    }
                } else {
                    if (shipMdse.equals(pMsg.A.no(i).mdseCd.getValue())) {
                        isMdseMach = false;
                    } else {
                        isMdseMach = true;
                    }
                }
            }

            if (!isMdseMach) {
                glMsgMap.addXxMsgId(NPZC131001Constant.NPZM0230E);
            }

            return isMdseMach;

        } else {

            glMsgMap.addXxMsgId(NPZC131001Constant.NPZM0230E);

            return false;

        }

    }

    /**
     * <pre>
     * Create process
     * </pre>
     * @return result(OK:true, NG:false)
     */
    private boolean executeCreate() {

        // Divide PO Detail
        if (!dividePoDetail()) {
            return false;
        }

        // Divide Tangible or Intangible
        if (!divideTangibleOrIntangible()) {
            return false;
        }

        // Exec Tangible Process
        if (glTtangibleList.size() > 0) {
            if (!executeTangible()) {
                return false;
            }
        }

        // Exec Inangible Process
        if (glInTtangibleList.size() > 0) {
            if (!executeIntangible()) {
                return false;
            }
        }

        return true;
    }

    /**
     * <pre>
     * Divide PO Detail
     * </pre>
     * @return result(OK:true, NG:false)
     */
    private boolean dividePoDetail() {

        Map<String, Object> poDetailInfo = null;
        String oldPoOrdDtlLineNum = "";
        String oldSetPoOrdDtlLineNum = "";
        String addSetPoOrdDtlLineNum = "";
        BigDecimal totalShipQty = new BigDecimal("0");
        BigDecimal poBalQty = new BigDecimal("0");
        BigDecimal poQty = new BigDecimal("0");
        BigDecimal poCancQty = new BigDecimal("0");

        boolean bReturn = true;

        // glParamPoDtlList is already sort
        for (int i = 0; i < glParamPoDtlList.size(); i++) {

            NPZC131001_ordDtlInfoPMsg paramPoDtl = glParamPoDtlList.get(i);
            String poOrdDtlLineNum = paramPoDtl.poOrdDtlLineNum.getValue();
            BigDecimal shipQty = paramPoDtl.shipQty.getValue();

            boolean setCompUpdFlg = false;

            // QC:51691 Start
            // Get PO Detail
            poDetailInfo = getPoDetail(paramPoDtl);
            poBalQty = (BigDecimal) poDetailInfo.get(NPZC131001Constant.PO_BAL_QTY);

            if (poDetailInfo == null) {
                // QC#27503
                glMsgMap.addXxMsgId(NPZC131001Constant.NPZM0156E);
                bReturn = false;
                break;
            }

            String setPoOrdDtlLineNum = (String) poDetailInfo.get(NPZC131001Constant.SET_PO_ORD_DTL_LINE_NUM);

            if (ZYPCommonFunc.hasValue(glParam.asnSoNum) && ZYPCommonFunc.hasValue(setPoOrdDtlLineNum)) {
                setCompUpdFlg = true;
            }

            String prmMdseCd = paramPoDtl.mdseCd.getValue();
            String mdseCd = (String) poDetailInfo.get(NPZC131001Constant.MDSE_CD);

            // Total Ship Quantity (No Item Flip or Set Component)
            if (i == 0 || oldPoOrdDtlLineNum.equals(poOrdDtlLineNum)) {

            	totalShipQty = totalShipQty.add(shipQty);

            }

            if (i != 0 && !oldPoOrdDtlLineNum.equals(poOrdDtlLineNum)) {

            	totalShipQty = BigDecimal.ZERO;
            	totalShipQty = totalShipQty.add(shipQty);
            }

            // Ship Quantity Check
            if (!mdseCd.equals(prmMdseCd) || setCompUpdFlg) {

            	totalShipQty = totalShipQty.subtract(shipQty);

            	if (shipQty.compareTo(poBalQty) > 0) {

                	// START 2019/02/19 T.Ogura [QC#30312,MOD]
                	//                    glMsgMap.addXxMsgId(NPZC131001Constant.NPAM1178E);
                	glMsgMap.addXxMsgId(NPZC131001Constant.NPZM0310E);
                	// END   2019/02/19 T.Ogura [QC#30312,MOD]
                	bReturn = false;
                	break;

                }

            } else {

            	if (totalShipQty.compareTo(poBalQty) > 0) {

                	// START 2019/02/19 T.Ogura [QC#30312,MOD]
                	//                    glMsgMap.addXxMsgId(NPZC131001Constant.NPAM1178E);
                	glMsgMap.addXxMsgId(NPZC131001Constant.NPZM0310E);
                	// END   2019/02/19 T.Ogura [QC#30312,MOD]
                	bReturn = false;
                	break;

                }

            }

            /*
            if (i == 0 || oldPoOrdDtlLineNum.equals(poOrdDtlLineNum)) {

                totalShipQty = totalShipQty.add(shipQty);

                // Get PO Detail
                poDetailInfo = getPoDetail(paramPoDtl);

                if (poDetailInfo == null) {
                    // QC#27503
                    glMsgMap.addXxMsgId(NPZC131001Constant.NPZM0156E);
                    bReturn = false;
                    break;
                }

                poBalQty = (BigDecimal) poDetailInfo.get(NPZC131001Constant.PO_BAL_QTY);

            }

            if (i == glParamPoDtlList.size() - 1 || !oldPoOrdDtlLineNum.equals(poOrdDtlLineNum)) {

                if (totalShipQty.compareTo(poBalQty) > 0) {

                    // START 2019/02/19 T.Ogura [QC#30312,MOD]
//                    glMsgMap.addXxMsgId(NPZC131001Constant.NPAM1178E);
                    glMsgMap.addXxMsgId(NPZC131001Constant.NPZM0310E);
                    // END   2019/02/19 T.Ogura [QC#30312,MOD]
                    bReturn = false;
                    break;

                }

                totalShipQty = BigDecimal.ZERO;
            }

            // Get PO Detail
            poDetailInfo = getPoDetail(paramPoDtl);

            if (poDetailInfo == null) {
                // QC#27503
                glMsgMap.addXxMsgId(NPZC131001Constant.NPZM0156E);
                bReturn = false;
                break;

            }
            */
            // QC:51691 End

            // set poOrdDtlLineNum for Inner Param
            ZYPEZDItemValueSetter.setValue(paramPoDtl.poOrdDtlLineNum, (String) poDetailInfo.get(NPZC131001Constant.PO_ORD_DTL_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(glParamPoDtlList.get(i).poOrdDtlLineNum, (String) poDetailInfo.get(NPZC131001Constant.PO_ORD_DTL_LINE_NUM));
            poBalQty = (BigDecimal) poDetailInfo.get(NPZC131001Constant.PO_BAL_QTY);
            poQty = (BigDecimal) poDetailInfo.get(NPZC131001Constant.PO_QTY);
            poCancQty = (BigDecimal) poDetailInfo.get(NPZC131001Constant.PO_CANC_QTY);

            // QC:51691 Start
            //String setPoOrdDtlLineNum = (String) poDetailInfo.get(NPZC131001Constant.SET_PO_ORD_DTL_LINE_NUM);

            //if (ZYPCommonFunc.hasValue(glParam.asnSoNum) && ZYPCommonFunc.hasValue(setPoOrdDtlLineNum)) {
            //    setCompUpdFlg = true;
            //}
            // QC:51691 End

            // Get Carrier Name
            // ********************************************************************************
            String carrNm = getCarrNm(paramPoDtl);

            // Call Shipping Plan Update API
            // ********************************************************************************
            String shpgPlnNum = (String) poDetailInfo.get(NPZC131001Constant.SHPG_PLN_NUM);

            if (!callShpgPlnUpd(paramPoDtl, shpgPlnNum, carrNm)) {
                bReturn = false;
                break;
            }

            // Update Shipping Plan Mdse
            // ********************************************************************************
            // QC:51691 Start
            //String prmMdseCd = paramPoDtl.mdseCd.getValue();
            //String mdseCd = (String) poDetailInfo.get(NPZC131001Constant.MDSE_CD);
            // QC:51691 End

            // QC#21191 Modify
            String prmCarrCd = paramPoDtl.carrCd.getValue();
            String carrCd = (String) poDetailInfo.get(NPZC131001Constant.CARR_CD);

            // QC#26730 Call PO Create API. Item Change only
            // ********************************************************************************
            if (!mdseCd.equals(prmMdseCd) || setCompUpdFlg) {

                int lineCnt = 0;
                // Receive ASN Process only.
                // QC#26104
                NPZC104001PMsg poCreateApiParam = setPoCreateApiHead(poDetailInfo, carrCd);
                Map<String, Object> poLineInfo = new HashMap<String, Object>();

                // Split PO_DTL Process.
                if (!mdseCd.equals(prmMdseCd) || poQty.subtract(poCancQty).compareTo(shipQty) > 0) {
                    // update original po_dtl. set shipped shpg_pln_num.
                    poLineInfo.put(NPZC131001Constant.PO_ORD_DTL_LINE_NUM, paramPoDtl.poOrdDtlLineNum.getValue());
                    poLineInfo.put(NPZC131001Constant.DISP_PO_DTL_LINE_NUM, poDetailInfo.get(NPZC131001Constant.DISP_PO_DTL_LINE_NUM));

                    if (poQty.compareTo(shipQty) == 0) {

                        poLineInfo.put(NPZC131001Constant.PO_QTY, poQty);
                        poLineInfo.put(NPZC131001Constant.MDSE_CD, prmMdseCd);
                        updShpgPlnMdse(shpgPlnNum, prmMdseCd);

                    } else {

                        poLineInfo.put(NPZC131001Constant.PO_QTY, poQty.subtract(shipQty));
                        poLineInfo.put(NPZC131001Constant.MDSE_CD, mdseCd);
                    }

                    poLineInfo.put(NPZC131001Constant.SHPG_PLN_NUM, shpgPlnNum);
                    poLineInfo.put(NPZC131001Constant.ORIG_PO_ORD_NUM, glParam.poOrdNum.getValue());
                    poLineInfo.put(NPZC131001Constant.ORIG_PO_ORD_DTL_LINE_NUM, paramPoDtl.poOrdDtlLineNum.getValue());
                    setPoCreateApiSetItemDtl(poDetailInfo, poLineInfo, poCreateApiParam, lineCnt);
                    ZYPEZDItemValueSetter.setValue(poCreateApiParam.poLineInfo.no(lineCnt).setPoOrdDtlLineNum, (String) poDetailInfo.get(NPZC131001Constant.SET_PO_ORD_DTL_LINE_NUM));
                    ZYPEZDItemValueSetter.setValue(poCreateApiParam.poLineInfo.no(lineCnt).dispPoDtlLineNum, (String) poDetailInfo.get(NPZC131001Constant.DISP_PO_DTL_LINE_NUM));
                    lineCnt++;
                }

                // create new po_dtl. set original shpg_pln_num
                if (poQty.subtract(poCancQty).compareTo(shipQty) > 0) {

                    if (ZYPCommonFunc.hasValue((String) poDetailInfo.get(NPZC131001Constant.SET_PO_ORD_DTL_LINE_NUM))) {
                        String divideShpgPlnNum = "";
                        String newPoOrdDtlLineNum = "";

                        // Set item process.
                        if (!oldSetPoOrdDtlLineNum.equals(setPoOrdDtlLineNum)) {
                            // Get Parent set Item
                            PO_DTLTMsg poDtlTMsg = new PO_DTLTMsg();
                            ZYPEZDItemValueSetter.setValue(poDtlTMsg.glblCmpyCd, glParam.glblCmpyCd.getValue());
                            ZYPEZDItemValueSetter.setValue(poDtlTMsg.poOrdNum, (String) poDetailInfo.get(NPZC131001Constant.PO_ORD_NUM));
                            ZYPEZDItemValueSetter.setValue(poDtlTMsg.poOrdDtlLineNum, (String) poDetailInfo.get(NPZC131001Constant.SET_PO_ORD_DTL_LINE_NUM));
                            poDtlTMsg = (PO_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateWait((poDtlTMsg));

                            // Parent Set Item Quantity Adjustment
                            Map<String, Object> params = new HashMap<String, Object>();
                            params.put("glblCmpyCd", glParam.glblCmpyCd.getValue());
                            params.put("prntMdseCd", poDtlTMsg.mdseCd.getValue());
                            params.put("childMdseCd", mdseCd);

                            BigDecimal childQty = (BigDecimal) glSsmBatchClient.queryObject("getCmpsnChildQty", params);
                            if (childQty == null) {
                                childQty = paramPoDtl.shipQty.getValue().divide(poDtlTMsg.poQty.getValue());
                            }
                            BigDecimal setQty = poDtlTMsg.poQty.getValue().subtract(paramPoDtl.shipQty.getValue().divide(childQty));

                            // update original parent set item
                            Map<String, Object> parentPoDetailInfo = getSetParentPoDetail(poDtlTMsg.poOrdDtlLineNum.getValue());
                            poLineInfo = new HashMap<String, Object>();
                            poLineInfo.put(NPZC131001Constant.PO_ORD_DTL_LINE_NUM, poDtlTMsg.poOrdDtlLineNum.getValue());
                            poLineInfo.put(NPZC131001Constant.MDSE_CD, poDtlTMsg.mdseCd.getValue());
                            poLineInfo.put(NPZC131001Constant.PO_QTY, setQty);
                            poLineInfo.put(NPZC131001Constant.SHPG_PLN_NUM, poDtlTMsg.shpgPlnNum.getValue());
                            poLineInfo.put(NPZC131001Constant.ORIG_PO_ORD_NUM, glParam.poOrdNum.getValue());
                            poLineInfo.put(NPZC131001Constant.ORIG_PO_ORD_DTL_LINE_NUM, poDtlTMsg.poOrdDtlLineNum.getValue());
                            setPoCreateApiSetItemDtl(parentPoDetailInfo, poLineInfo, poCreateApiParam, lineCnt);
                            ZYPEZDItemValueSetter.setValue(poCreateApiParam.poLineInfo.no(lineCnt).dispPoDtlLineNum, poDtlTMsg.dispPoDtlLineNum);
                            poCreateApiParam.poLineInfo.no(lineCnt).setPoOrdDtlLineNum.clear();
                            lineCnt++;

                            // Add Parent Item PO_DTL
                            newPoOrdDtlLineNum = getMaxNextPoOrdDtlLineNum();
                            addSetPoOrdDtlLineNum = newPoOrdDtlLineNum;

                            poLineInfo = new HashMap<String, Object>();
                            poLineInfo.put(NPZC131001Constant.PO_ORD_DTL_LINE_NUM, newPoOrdDtlLineNum);
                            poLineInfo.put(NPZC131001Constant.MDSE_CD, poDtlTMsg.mdseCd.getValue());
                            poLineInfo.put(NPZC131001Constant.PO_QTY, poDtlTMsg.poQty.getValue().subtract(setQty));
                            poLineInfo.put(NPZC131001Constant.SHPG_PLN_NUM, poDtlTMsg.shpgPlnNum.getValue());
                            poLineInfo.put(NPZC131001Constant.ORIG_PO_ORD_NUM, glParam.poOrdNum.getValue());
                            poLineInfo.put(NPZC131001Constant.ORIG_PO_ORD_DTL_LINE_NUM, poDtlTMsg.poOrdDtlLineNum.getValue());
                            setPoCreateApiSetItemDtl(parentPoDetailInfo, poLineInfo, poCreateApiParam, lineCnt);
                            ZYPEZDItemValueSetter.setValue(poCreateApiParam.poLineInfo.no(lineCnt).dispPoDtlLineNum, poDtlTMsg.dispPoDtlLineNum);
                            poCreateApiParam.poLineInfo.no(lineCnt).setPoOrdDtlLineNum.clear();
                            lineCnt++;

                            // set child item line number
                            int lineNum = Integer.parseInt(newPoOrdDtlLineNum);
                            lineNum++;
                            newPoOrdDtlLineNum = ZYPCommonFunc.leftPad(Integer.valueOf(lineNum).toString(), 3, "0");

                        } else {
                            // set Child Item line number
                            newPoOrdDtlLineNum = getMaxNextPoOrdDtlLineNum();   
                        }

                        divideShpgPlnNum = getDivideShpgPlnNum(poOrdDtlLineNum);

                        // update divide shipping plan mdse code
                        updShpgPlnMdse(divideShpgPlnNum, prmMdseCd);

                        // add Child item po detail line
                        poLineInfo = new HashMap<String, Object>();
                        poLineInfo.put(NPZC131001Constant.PO_ORD_DTL_LINE_NUM, newPoOrdDtlLineNum);
                        poLineInfo.put(NPZC131001Constant.MDSE_CD, prmMdseCd);
                        poLineInfo.put(NPZC131001Constant.PO_QTY, shipQty);
                        poLineInfo.put(NPZC131001Constant.SHPG_PLN_NUM, divideShpgPlnNum);
                        poLineInfo.put(NPZC131001Constant.ORIG_PO_ORD_NUM, glParam.poOrdNum.getValue());
                        poLineInfo.put(NPZC131001Constant.ORIG_PO_ORD_DTL_LINE_NUM, paramPoDtl.poOrdDtlLineNum.getValue());
                        setPoCreateApiSetItemDtl(poDetailInfo, poLineInfo, poCreateApiParam, lineCnt);
                        ZYPEZDItemValueSetter.setValue(poCreateApiParam.poLineInfo.no(lineCnt).dispPoDtlLineNum, (String) poDetailInfo.get(NPZC131001Constant.DISP_PO_DTL_LINE_NUM));
                        ZYPEZDItemValueSetter.setValue(paramPoDtl.poOrdDtlLineNum, newPoOrdDtlLineNum);
                        ZYPEZDItemValueSetter.setValue(poCreateApiParam.poLineInfo.no(lineCnt).setPoOrdDtlLineNum, addSetPoOrdDtlLineNum);
                        lineCnt++;

                        NPZC104001 dPZC104001 = new NPZC104001();
                        dPZC104001.execute(poCreateApiParam, glOnBatchType);

                        if (poCreateApiParam.xxMsgIdList.getValidCount() > 0) {

                            for (int j = 0; j < poCreateApiParam.xxMsgIdList.getValidCount(); j++) {

                                glMsgMap.addXxMsgId(poCreateApiParam.xxMsgIdList.no(j).xxMsgId.getValue());

                            }

                            return false;
                        }
                    } else {
                        // Regular item Process.
                        String divideShpgPlnNum = getDivideShpgPlnNum(poOrdDtlLineNum);
                        String newPoOrdDtlLineNum = getMaxNextPoOrdDtlLineNum();

                        updShpgPlnMdse(divideShpgPlnNum, prmMdseCd);

                        poLineInfo = new HashMap<String, Object>();
                        poLineInfo.put(NPZC131001Constant.PO_ORD_DTL_LINE_NUM, newPoOrdDtlLineNum);
                        poLineInfo.put(NPZC131001Constant.MDSE_CD, prmMdseCd);
                        poLineInfo.put(NPZC131001Constant.PO_QTY, shipQty);
                        poLineInfo.put(NPZC131001Constant.SHPG_PLN_NUM, divideShpgPlnNum);
                        poLineInfo.put(NPZC131001Constant.ORIG_PO_ORD_NUM, glParam.poOrdNum.getValue());
                        poLineInfo.put(NPZC131001Constant.ORIG_PO_ORD_DTL_LINE_NUM, paramPoDtl.poOrdDtlLineNum.getValue());
                        setPoCreateApiSetItemDtl(poDetailInfo, poLineInfo, poCreateApiParam, lineCnt);
                        ZYPEZDItemValueSetter.setValue(paramPoDtl.poOrdDtlLineNum, newPoOrdDtlLineNum);
                        ZYPEZDItemValueSetter.setValue(poCreateApiParam.poLineInfo.no(lineCnt).dispPoDtlLineNum, (String) poDetailInfo.get(NPZC131001Constant.DISP_PO_DTL_LINE_NUM));
                        ZYPEZDItemValueSetter.setValue(poCreateApiParam.poLineInfo.no(lineCnt).poInvQty, BigDecimal.ZERO);    // 06/04/2020 T.Ogura [QC#56912,ADD]
                        NPZC104001 dPZC104001 = new NPZC104001();
                        dPZC104001.execute(poCreateApiParam, glOnBatchType);

                        if (poCreateApiParam.xxMsgIdList.getValidCount() > 0) {

                            for (int j = 0; j < poCreateApiParam.xxMsgIdList.getValidCount(); j++) {

                                glMsgMap.addXxMsgId(poCreateApiParam.xxMsgIdList.no(j).xxMsgId.getValue());

                            }

                            return false;
                        }
                    }
                } else {
                    if (lineCnt > 0) {
                        NPZC104001 dPZC104001 = new NPZC104001();
                        dPZC104001.execute(poCreateApiParam, glOnBatchType);

                        if (poCreateApiParam.xxMsgIdList.getValidCount() > 0) {

                            for (int j = 0; j < poCreateApiParam.xxMsgIdList.getValidCount(); j++) {

                                glMsgMap.addXxMsgId(poCreateApiParam.xxMsgIdList.no(j).xxMsgId.getValue());

                            }

                            return false;

                        }
                    }
                }
            }

            oldPoOrdDtlLineNum = poOrdDtlLineNum;
            if (ZYPCommonFunc.hasValue(setPoOrdDtlLineNum)) {
                oldSetPoOrdDtlLineNum = setPoOrdDtlLineNum;
            }
        }

        return bReturn;

    }

    /**
     * <pre>
     * get PO Detail
     * </pre>
     * @param paramPoDtl NPZC131001_ordDtlInfoPMsg
     * @return poDetailInfo Map<String, Object>
     */
    private Map<String, Object> getPoDetail(NPZC131001_ordDtlInfoPMsg paramPoDtl) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put(NPZC131001Constant.GLBL_CMPY_CD, glParam.glblCmpyCd.getValue());
        queryParam.put(NPZC131001Constant.ORIG_PO_ORD_NUM, glParam.poOrdNum.getValue());
        queryParam.put(NPZC131001Constant.ORIG_PO_ORD_DTL_LINE_NUM, paramPoDtl.poOrdDtlLineNum.getValue());
        queryParam.put(NPZC131001Constant.OPEN_PO_FLG, ZYPConstant.FLG_ON_Y);

        Map<String, Object> poDetailInfo = (Map<String, Object>) glSsmBatchClient.queryObject("getPoDetail", queryParam);

        return poDetailInfo;
    }

    /**
     * <pre>
     * get PO Detail
     * </pre>
     * @param poOrdDtlLineNum String
     * @return poDetailInfo Map<String, Object>
     */
    private Map<String, Object> getSetParentPoDetail(String poOrdDtlLineNum) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put(NPZC131001Constant.GLBL_CMPY_CD, glParam.glblCmpyCd.getValue());
        queryParam.put(NPZC131001Constant.ORIG_PO_ORD_NUM, glParam.poOrdNum.getValue());
        queryParam.put(NPZC131001Constant.ORIG_PO_ORD_DTL_LINE_NUM, poOrdDtlLineNum);
        queryParam.put(NPZC131001Constant.OPEN_PO_FLG, ZYPConstant.FLG_ON_Y);

        Map<String, Object> poDetailInfo = (Map<String, Object>) glSsmBatchClient.queryObject("getPoDetail", queryParam);

        return poDetailInfo;
    }

    /**
     * <pre>
     * get CARR_NM
     * </pre>
     * @param paramPoDtl NPZC131001_ordDtlInfoPMsg
     * @return CARR_NM String
     */
    private String getCarrNm(NPZC131001_ordDtlInfoPMsg paramPoDtl) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put(NPZC131001Constant.GLBL_CMPY_CD, glParam.glblCmpyCd.getValue());
        queryParam.put(NPZC131001Constant.CARR_CD, paramPoDtl.carrCd.getValue());

        return (String) glSsmBatchClient.queryObject("getCarrNm", queryParam);

    }

    /**
     * <pre>
     * set PO Create API Param Head
     * </pre>
     * @param poDetailInfo Map<String, Object>
     * @param poCreateApiParam NPZC104001PMsg (OUTPUT)
     */
    private NPZC104001PMsg setPoCreateApiHead(Map<String, Object> poDetailInfo, String prmCarrCd) {

        NPZC104001PMsg pMsg = new NPZC104001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glParam.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC104001Constant.MODE_UPDATE);
        ZYPEZDItemValueSetter.setValue(pMsg.procDt, glParam.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
        ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum, glParam.poOrdNum);
        ZYPEZDItemValueSetter.setValue(pMsg.dsPoTpCd, (String) glPoHead.get(NPZC131001Constant.DS_PO_TP_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.dsPoTpNm, (String) glPoHead.get(NPZC131001Constant.DS_PO_TP_NM));
        ZYPEZDItemValueSetter.setValue(pMsg.poQlfyCd, (String) glPoHead.get(NPZC131001Constant.PO_QLFY_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poSubmtPsnCd, (String) glPoHead.get(NPZC131001Constant.PO_SUBMT_PSN_CD));
        // QC#20610 Mod
        ZYPEZDItemValueSetter.setValue(pMsg.poSubmtTs, ZYPDateUtil.getSalesDate(glParam.glblCmpyCd.getValue()));
//        ZYPEZDItemValueSetter.setValue(pMsg.poSubmtTs, (String) glPoHead.get(NPZC131001Constant.PO_SUBMT_TS));
        ZYPEZDItemValueSetter.setValue(pMsg.poApvlStsCd, (String) glPoHead.get(NPZC131001Constant.PO_APVL_STS_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poApvlPsnCd, (String) glPoHead.get(NPZC131001Constant.PO_APVL_PSN_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poApvlDt, (String) glPoHead.get(NPZC131001Constant.PO_APVL_DT));
        ZYPEZDItemValueSetter.setValue(pMsg.poApvlTs, (String) glPoHead.get(NPZC131001Constant.PO_APVL_TS));
        ZYPEZDItemValueSetter.setValue(pMsg.destRtlWhCd, (String) glPoHead.get(NPZC131001Constant.DEST_RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.srcRtlWhCd, (String) glPoHead.get(NPZC131001Constant.SRC_RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poOrdSrcCd, (String) glPoHead.get(NPZC131001Constant.PO_ORD_SRC_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.prntVndCd, (String) glPoHead.get(NPZC131001Constant.PRNT_VND_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.prntVndNm, (String) glPoHead.get(NPZC131001Constant.PRNT_VND_NM));
        ZYPEZDItemValueSetter.setValue(pMsg.dealCcyCd, (String) poDetailInfo.get(NPZC131001Constant.CCY_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.vndCd, (String) glPoHead.get(NPZC131001Constant.VND_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.vndNm, (String) glPoHead.get(NPZC131001Constant.VND_NM));
        ZYPEZDItemValueSetter.setValue(pMsg.vndDropShipFlg, (String) glPoHead.get(NPZC131001Constant.VND_DROP_SHIP_FLG));
        ZYPEZDItemValueSetter.setValue(pMsg.prchGrpCd, (String) glPoHead.get(NPZC131001Constant.PRCH_GRP_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.vndPmtTermCd, (String) glPoHead.get(NPZC131001Constant.VND_PMT_TERM_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.vndPmtTermDescTxt, (String) glPoHead.get(NPZC131001Constant.VND_PMT_TERM_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(pMsg.rqstTechTocCd, (String) glPoHead.get(NPZC131001Constant.RQST_TECH_TOC_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.rqstRcvDt, (String) glPoHead.get(NPZC131001Constant.RQST_RCV_DT));
        ZYPEZDItemValueSetter.setValue(pMsg.rqstRcvTm, (String) glPoHead.get(NPZC131001Constant.RQST_RCV_TM));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, (String) poDetailInfo.get(NPZC131001Constant.SHIP_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToLocNm, (String) poDetailInfo.get(NPZC131001Constant.SHIP_TO_LOC_NM));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToAcctNm, (String) poDetailInfo.get(NPZC131001Constant.SHIP_TO_ACCT_NM));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToAddlLocNm, (String) poDetailInfo.get(NPZC131001Constant.SHIP_TO_ADDL_LOC_NM));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstLineAddr, (String) poDetailInfo.get(NPZC131001Constant.SHIP_TO_FIRST_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToScdLineAddr, (String) poDetailInfo.get(NPZC131001Constant.SHIP_TO_SCD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToThirdLineAddr, (String) poDetailInfo.get(NPZC131001Constant.SHIP_TO_THIRD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToFrthLineAddr, (String) poDetailInfo.get(NPZC131001Constant.SHIP_TO_FRTH_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstRefCmntTxt, (String) poDetailInfo.get(NPZC131001Constant.SHIP_TO_FIRST_REF_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToScdRefCmntTxt, (String) poDetailInfo.get(NPZC131001Constant.SHIP_TO_SCD_REF_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCtyAddr, (String) poDetailInfo.get(NPZC131001Constant.SHIP_TO_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToStCd, (String) poDetailInfo.get(NPZC131001Constant.SHIP_TO_ST_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToProvNm, (String) poDetailInfo.get(NPZC131001Constant.SHIP_TO_PROV_NM));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCntyNm, (String) poDetailInfo.get(NPZC131001Constant.SHIP_TO_CNTY_NM));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToPostCd, (String) poDetailInfo.get(NPZC131001Constant.SHIP_TO_POST_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCtryCd, (String) poDetailInfo.get(NPZC131001Constant.SHIP_TO_CTRY_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.ctacPsnNm, (String) poDetailInfo.get(NPZC131001Constant.CTAC_PSN_NM));
        ZYPEZDItemValueSetter.setValue(pMsg.rtrnShipToRtlWhCd, (String) glPoHead.get(NPZC131001Constant.RTRN_SHIP_TO_RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.shipFromSoNumListTxt, (String) glPoHead.get(NPZC131001Constant.SHIP_FROM_SO_NUM_LIST_TXT));
        ZYPEZDItemValueSetter.setValue(pMsg.carrCd, prmCarrCd);
        ZYPEZDItemValueSetter.setValue(pMsg.carrAcctNum, (String) glPoHead.get(NPZC131001Constant.CARR_ACCT_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.shpgSvcLvlCd, (String) poDetailInfo.get(NPZC131001Constant.SHPG_SVC_LVL_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.frtChrgToCd, (String) poDetailInfo.get(NPZC131001Constant.FRT_CHRG_TO_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.frtChrgMethCd, (String) poDetailInfo.get(NPZC131001Constant.FRT_CHRG_METH_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.lineBizTpCd, (String) glPoHead.get(NPZC131001Constant.LINE_BIZ_TP_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poOrdCmntTxt, (String) glPoHead.get(NPZC131001Constant.PO_ORD_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(pMsg.trsmtMethTpCd, (String) glPoHead.get(NPZC131001Constant.TRSMT_METH_TP_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.sendPoFaxNum, (String) glPoHead.get(NPZC131001Constant.SEND_PO_FAX_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.sendPoEmlAddr, (String) glPoHead.get(NPZC131001Constant.SEND_PO_EML_ADDR));
        ZYPEZDItemValueSetter.setValue(pMsg.poSendFlg, (String) glPoHead.get(NPZC131001Constant.PO_SEND_FLG));
        ZYPEZDItemValueSetter.setValue(pMsg.poSendTs, (String) glPoHead.get(NPZC131001Constant.PO_SEND_TS));
        ZYPEZDItemValueSetter.setValue(pMsg.poPrintFlg, (String) glPoHead.get(NPZC131001Constant.PO_PRINT_FLG));
        ZYPEZDItemValueSetter.setValue(pMsg.dsctnInd, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pMsg.vndIssOrdNum, (String) glPoHead.get(NPZC131001Constant.VND_ISS_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.wfFlg, ZYPConstant.FLG_OFF_N);

        return pMsg;
    }

    /**
     * <pre>
     * set PO Create API Param Detail
     * </pre>
     * @param recCnt int
     * @param poDetailInfo Map<String, Object>
     * @param poLineInfo Map<String, Object>
     * @param pMsg NPZC104001PMsg (OUTPUT)
     */
    private void setPoCreateApiDtl(Map<String, Object> poDetailInfo, Map<String, Object> poLineInfo, NPZC104001PMsg pMsg) {

        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).poOrdDtlLineNum, (String) poLineInfo.get(NPZC131001Constant.PO_ORD_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).poLineTpCd, (String) poDetailInfo.get(NPZC131001Constant.PO_LINE_TP_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).poMdseCmpsnTpCd, (String) poDetailInfo.get(NPZC131001Constant.PO_MDSE_CMPSN_TP_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).setPoOrdDtlLineNum, (String) poDetailInfo.get(NPZC131001Constant.SET_PO_ORD_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).mdseCd, (String) poLineInfo.get(NPZC131001Constant.MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).mdseDescShortTxt, (String) poDetailInfo.get(NPZC131001Constant.MDSE_DESC_SHORT_TXT));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).poQty, (BigDecimal) poLineInfo.get(NPZC131001Constant.PO_QTY));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).poInvQty, (BigDecimal) poDetailInfo.get(NPZC131001Constant.PO_INV_QTY));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).poDispUomCd, (String) poDetailInfo.get(NPZC131001Constant.PO_DISP_UOM_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).thisMthFobCostAmt, (BigDecimal) poDetailInfo.get(NPZC131001Constant.THIS_MTH_FOB_COST_AMT));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).entDealNetUnitPrcAmt, (BigDecimal) poDetailInfo.get(NPZC131001Constant.ENT_DEAL_NET_UNIT_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).entPoDtlDealNetAmt, (BigDecimal) poDetailInfo.get(NPZC131001Constant.ENT_PO_DTL_DEAL_NET_AMT));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).entFuncNetUnitPrcAmt, (BigDecimal) poDetailInfo.get(NPZC131001Constant.ENT_FUNC_NET_UNIT_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).entPoDtlFuncNetAmt, (BigDecimal) poDetailInfo.get(NPZC131001Constant.ENT_PO_DTL_FUNC_NET_AMT));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).exchRate, (BigDecimal) poDetailInfo.get(NPZC131001Constant.EXCH_RATE));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).custUomCd, (String) poDetailInfo.get(NPZC131001Constant.CUST_UOM_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).destRtlSwhCd, (String) poDetailInfo.get(NPZC131001Constant.DEST_RTL_SWH_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).srcRtlSwhCd, (String) poDetailInfo.get(NPZC131001Constant.SRC_RTL_SWH_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).invtyLocCd, (String) poDetailInfo.get(NPZC131001Constant.INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).rqstRcvDt, (String) poDetailInfo.get(NPZC131001Constant.RQST_RCV_DT));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).rqstRcvTm, (String) poDetailInfo.get(NPZC131001Constant.RQST_RCV_TM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).frtCondCd, (String) poDetailInfo.get(NPZC131001Constant.FRT_COND_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).origMdseCd, (String) poDetailInfo.get(NPZC131001Constant.ORIG_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).fromStkStsCd, (String) poDetailInfo.get(NPZC131001Constant.FROM_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).toStkStsCd, (String) poDetailInfo.get(NPZC131001Constant.TO_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).adminPsnCd, (String) poDetailInfo.get(NPZC131001Constant.ADMIN_PSN_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).poMatchTpCd, (String) poDetailInfo.get(NPZC131001Constant.PO_MATCH_TP_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).ordQty, (BigDecimal) poDetailInfo.get(NPZC131001Constant.ORD_QTY));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).cpoOrdNum, (String) poDetailInfo.get(NPZC131001Constant.CPO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).cpoDtlLineNum, (String) poDetailInfo.get(NPZC131001Constant.CPO_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).cpoDtlLineSubNum, (String) poDetailInfo.get(NPZC131001Constant.CPO_DTL_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).custIssPoNum, (String) poDetailInfo.get(NPZC131001Constant.CUST_ISS_PO_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).custIssPoDt, (String) poDetailInfo.get(NPZC131001Constant.CUST_ISS_PO_DT));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).cpoOrdTpCd, (String) poDetailInfo.get(NPZC131001Constant.CPO_ORD_TP_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).billToCustCd, (String) poDetailInfo.get(NPZC131001Constant.BILL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).sellToCustCd, (String) poDetailInfo.get(NPZC131001Constant.SELL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).shpgPlnNum, (String) poLineInfo.get(NPZC131001Constant.SHPG_PLN_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).prchReqNum, (String) poDetailInfo.get(NPZC131001Constant.PRCH_REQ_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).prchReqLineNum, (String) poDetailInfo.get(NPZC131001Constant.PRCH_REQ_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).prchReqLineSubNum, (BigDecimal) poDetailInfo.get(NPZC131001Constant.PRCH_REQ_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).trxRefNum, (String) poDetailInfo.get(NPZC131001Constant.TRX_REF_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).trxRefLineNum, (String) poDetailInfo.get(NPZC131001Constant.TRX_REF_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).trxRefLineSubNum, (String) poDetailInfo.get(NPZC131001Constant.TRX_REF_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).aslDtlPk, (BigDecimal) poDetailInfo.get(NPZC131001Constant.ASL_DTL_PK));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).aslMdseCd, (String) poDetailInfo.get(NPZC131001Constant.ASL_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).aslUnitPrcAmt, (BigDecimal) poDetailInfo.get(NPZC131001Constant.ASL_UNIT_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).shipFromSoNumListTxt, (String) poDetailInfo.get(NPZC131001Constant.SHIP_FROM_SO_NUM_LIST_TXT));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).vndInvtyLocCd, (String) poDetailInfo.get(NPZC131001Constant.VND_INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).vndIssPoOrdNum, (String) poDetailInfo.get(NPZC131001Constant.VND_ISS_PO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).proNum, (String) poDetailInfo.get(NPZC131001Constant.PRO_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).vndPoAckStsCd, (String) poDetailInfo.get(NPZC131001Constant.VND_PO_ACK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).origPoOrdNum, (String) poLineInfo.get(NPZC131001Constant.ORIG_PO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).origPoOrdDtlLineNum, (String) poLineInfo.get(NPZC131001Constant.ORIG_PO_ORD_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).origDispPoDtlLineNum, (String) poDetailInfo.get(NPZC131001Constant.ORIG_DISP_PO_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).svcConfigMstrPk, (BigDecimal) poDetailInfo.get(NPZC131001Constant.SVC_CONFIG_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).poSendTs, (String) poDetailInfo.get(NPZC131001Constant.PO_SEND_TS));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).poOrdDtlCmntTxt, (String) poDetailInfo.get(NPZC131001Constant.PO_ORD_DTL_CMNT_TXT));
        // QC#20610 Add Start
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).poDispQty, pMsg.poLineInfo.no(0).poQty);
        // QC#20610 Add End
        pMsg.poLineInfo.setValidCount(1);
    }

    /**
     * <pre>
     * set PO Create API Param Set Item Detail
     * </pre>
     * @param recCnt int
     * @param poDetailInfo Map<String, Object>
     * @param poLineInfo Map<String, Object>
     * @param pMsg NPZC104001PMsg (OUTPUT)
     */
    private void setPoCreateApiSetItemDtl(Map<String, Object> poDetailInfo, Map<String, Object> poLineInfo, NPZC104001PMsg pMsg, int i) {

        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).poOrdDtlLineNum, (String) poLineInfo.get(NPZC131001Constant.PO_ORD_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).poLineTpCd, (String) poDetailInfo.get(NPZC131001Constant.PO_LINE_TP_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).poMdseCmpsnTpCd, (String) poDetailInfo.get(NPZC131001Constant.PO_MDSE_CMPSN_TP_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).mdseCd, (String) poLineInfo.get(NPZC131001Constant.MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).mdseDescShortTxt, (String) poDetailInfo.get(NPZC131001Constant.MDSE_DESC_SHORT_TXT));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).poQty, (BigDecimal) poLineInfo.get(NPZC131001Constant.PO_QTY));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).poInvQty, (BigDecimal) poDetailInfo.get(NPZC131001Constant.PO_INV_QTY));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).poDispUomCd, (String) poDetailInfo.get(NPZC131001Constant.PO_DISP_UOM_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).thisMthFobCostAmt, (BigDecimal) poDetailInfo.get(NPZC131001Constant.THIS_MTH_FOB_COST_AMT));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).entDealNetUnitPrcAmt, (BigDecimal) poDetailInfo.get(NPZC131001Constant.ENT_DEAL_NET_UNIT_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).entPoDtlDealNetAmt, pMsg.poLineInfo.no(i).poQty.getValue().multiply(pMsg.poLineInfo.no(i).entDealNetUnitPrcAmt.getValue()));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).entFuncNetUnitPrcAmt, (BigDecimal) poDetailInfo.get(NPZC131001Constant.ENT_FUNC_NET_UNIT_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).entPoDtlFuncNetAmt, pMsg.poLineInfo.no(i).poQty.getValue().multiply(pMsg.poLineInfo.no(i).entFuncNetUnitPrcAmt.getValue()));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).exchRate, (BigDecimal) poDetailInfo.get(NPZC131001Constant.EXCH_RATE));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).custUomCd, (String) poDetailInfo.get(NPZC131001Constant.CUST_UOM_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).destRtlSwhCd, (String) poDetailInfo.get(NPZC131001Constant.DEST_RTL_SWH_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).srcRtlSwhCd, (String) poDetailInfo.get(NPZC131001Constant.SRC_RTL_SWH_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).invtyLocCd, (String) poDetailInfo.get(NPZC131001Constant.INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).rqstRcvDt, (String) poDetailInfo.get(NPZC131001Constant.RQST_RCV_DT));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).rqstRcvTm, (String) poDetailInfo.get(NPZC131001Constant.RQST_RCV_TM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).frtCondCd, (String) poDetailInfo.get(NPZC131001Constant.FRT_COND_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).origMdseCd, (String) poDetailInfo.get(NPZC131001Constant.ORIG_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).fromStkStsCd, (String) poDetailInfo.get(NPZC131001Constant.FROM_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).toStkStsCd, (String) poDetailInfo.get(NPZC131001Constant.TO_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).adminPsnCd, (String) poDetailInfo.get(NPZC131001Constant.ADMIN_PSN_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).poMatchTpCd, (String) poDetailInfo.get(NPZC131001Constant.PO_MATCH_TP_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).ordQty, (BigDecimal) poDetailInfo.get(NPZC131001Constant.ORD_QTY));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).cpoOrdNum, (String) poDetailInfo.get(NPZC131001Constant.CPO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).cpoDtlLineNum, (String) poDetailInfo.get(NPZC131001Constant.CPO_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).cpoDtlLineSubNum, (String) poDetailInfo.get(NPZC131001Constant.CPO_DTL_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).custIssPoNum, (String) poDetailInfo.get(NPZC131001Constant.CUST_ISS_PO_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).custIssPoDt, (String) poDetailInfo.get(NPZC131001Constant.CUST_ISS_PO_DT));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).cpoOrdTpCd, (String) poDetailInfo.get(NPZC131001Constant.CPO_ORD_TP_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).billToCustCd, (String) poDetailInfo.get(NPZC131001Constant.BILL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).sellToCustCd, (String) poDetailInfo.get(NPZC131001Constant.SELL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).shpgPlnNum, (String) poLineInfo.get(NPZC131001Constant.SHPG_PLN_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).prchReqNum, (String) poDetailInfo.get(NPZC131001Constant.PRCH_REQ_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).prchReqLineNum, (String) poDetailInfo.get(NPZC131001Constant.PRCH_REQ_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).prchReqLineSubNum, (BigDecimal) poDetailInfo.get(NPZC131001Constant.PRCH_REQ_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).trxRefNum, (String) poDetailInfo.get(NPZC131001Constant.TRX_REF_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).trxRefLineNum, (String) poDetailInfo.get(NPZC131001Constant.TRX_REF_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).trxRefLineSubNum, (String) poDetailInfo.get(NPZC131001Constant.TRX_REF_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).aslDtlPk, (BigDecimal) poDetailInfo.get(NPZC131001Constant.ASL_DTL_PK));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).aslMdseCd, (String) poDetailInfo.get(NPZC131001Constant.ASL_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).aslUnitPrcAmt, (BigDecimal) poDetailInfo.get(NPZC131001Constant.ASL_UNIT_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).shipFromSoNumListTxt, (String) poDetailInfo.get(NPZC131001Constant.SHIP_FROM_SO_NUM_LIST_TXT));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).vndInvtyLocCd, (String) poDetailInfo.get(NPZC131001Constant.VND_INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).vndIssPoOrdNum, (String) poDetailInfo.get(NPZC131001Constant.VND_ISS_PO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).proNum, (String) poDetailInfo.get(NPZC131001Constant.PRO_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).vndPoAckStsCd, (String) poDetailInfo.get(NPZC131001Constant.VND_PO_ACK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).origPoOrdNum, (String) poLineInfo.get(NPZC131001Constant.ORIG_PO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).origPoOrdDtlLineNum, (String) poLineInfo.get(NPZC131001Constant.ORIG_PO_ORD_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).origDispPoDtlLineNum, (String) poDetailInfo.get(NPZC131001Constant.ORIG_DISP_PO_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).svcConfigMstrPk, (BigDecimal) poDetailInfo.get(NPZC131001Constant.SVC_CONFIG_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).poSendTs, (String) poDetailInfo.get(NPZC131001Constant.PO_SEND_TS));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).poOrdDtlCmntTxt, (String) poDetailInfo.get(NPZC131001Constant.PO_ORD_DTL_CMNT_TXT));
        // QC#20610 Add Start
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).poDispQty, pMsg.poLineInfo.no(i).poQty);
        // QC#20610 Add End

        pMsg.poLineInfo.setValidCount(++i);
    }
    /**
     * <pre>
     * get SHPG_PLN_NUM
     * </pre>
     * @param paramPoDtl NPZC131001_ordDtlInfoPMsg
     * @return SHPG_PLN_NUM String
     */
    private String getShpgPlnNum(Map<String, Object> poDetailInfo) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put(NPZC131001Constant.GLBL_CMPY_CD, glParam.glblCmpyCd.getValue());
        queryParam.put(NPZC131001Constant.PO_ORD_NUM, glParam.poOrdNum.getValue());
        queryParam.put(NPZC131001Constant.PO_ORD_DTL_LINE_NUM, (String) poDetailInfo.get(NPZC131001Constant.PO_ORD_DTL_LINE_NUM));
        queryParam.put(NPZC131001Constant.SHPG_STS_CD, SHPG_STS.P_OR_O_PRINTED);
        queryParam.put(NPZC131001Constant.RTE_STS_CD, RTE_STS.UN_ROUTED);
        queryParam.put(NPZC131001Constant.SO_CLOSE_FLG, ZYPConstant.FLG_OFF_N);

        return (String) glSsmBatchClient.queryObject("getShpgPlnNum", queryParam);
    }

    /**
     * <pre>
     * get MAX(PO_ORD_DTL_LINE_NUM) + 1
     * </pre>
     * @return MAX(PO_ORD_DTL_LINE_NUM) + 1 String
     */
    private String getMaxNextPoOrdDtlLineNum() {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put(NPZC131001Constant.GLBL_CMPY_CD, glParam.glblCmpyCd.getValue());
        queryParam.put(NPZC131001Constant.PO_ORD_NUM, glParam.poOrdNum.getValue());

        return (String) glSsmBatchClient.queryObject("getMaxNextPoOrdDtlLineNum", queryParam);
    }

    /**
     * <pre>
     * divide tangible or intangible
     * </pre>
     * @return result(OK:true, NG:false)
     */
    private boolean divideTangibleOrIntangible() {

        glTtangibleList = new ArrayList<NPZC131001_ordDtlInfoPMsg>();
        glTtangiblePoDtlList = new ArrayList<Map<String, Object>>();
        glTtangibleShpgPlnList = new ArrayList<SHPG_PLNTMsg>();
        glInTtangibleList = new ArrayList<NPZC131001_ordDtlInfoPMsg>();
        glInTtangiblePoDtlList = new ArrayList<Map<String, Object>>();
        glInTtangibleShpgPlnList = new ArrayList<SHPG_PLNTMsg>();
        glInTtangibleMdseList = new ArrayList<MDSETMsg>();

        for (int i = 0; i < glParamPoDtlList.size(); i++) {

            NPZC131001_ordDtlInfoPMsg paramPoDtl = glParamPoDtlList.get(i);
            MDSETMsg mdseTMsg = new MDSETMsg();
            mdseTMsg.glblCmpyCd.setValue(glParam.glblCmpyCd.getValue());
            mdseTMsg.mdseCd.setValue(paramPoDtl.mdseCd.getValue());

            mdseTMsg = (MDSETMsg) S21ApiTBLAccessor.findByKey(mdseTMsg);

            if (mdseTMsg == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(mdseTMsg.getReturnCode())) {

                continue;

            } else {

                // divide tangible or intangible
                if (ZYPConstant.FLG_ON_Y.equals(mdseTMsg.invtyCtrlFlg.getValue())) {

                    glTtangibleList.add(paramPoDtl);
                    glTtangiblePoDtlList.add(glPoDetailList.get(i));
                    glTtangibleShpgPlnList.add(glShpgPlnList.get(i));

                } else if (ZYPConstant.FLG_OFF_N.equals(mdseTMsg.invtyCtrlFlg.getValue())) {

                    glInTtangibleList.add(paramPoDtl);
                    glInTtangiblePoDtlList.add(glPoDetailList.get(i));
                    glInTtangibleShpgPlnList.add(glShpgPlnList.get(i));
                    glInTtangibleMdseList.add(mdseTMsg);
                }
            }
        }

        return true;
    }

    /**
     * <pre>
     * Exec Tangible Process
     * </pre>
     * @return result(OK:true, NG:false)
     */
    private boolean executeTangible() {

        String rwfRefNum = "";
        boolean bReturn = true;
        NLZC201001PMsg poRecPmsg = new NLZC201001PMsg();

        for (int i = 0; i < glTtangibleList.size(); i++) {

            NPZC131001_ordDtlInfoPMsg paramPoDtl = glTtangibleList.get(i);
            Map<String, Object> poDtl = glTtangiblePoDtlList.get(i);
            SHPG_PLNTMsg shpgPln = glTtangibleShpgPlnList.get(i);

            if (i == 0) {

                // set NLZC2010 PO Receiving API head param
                ZYPEZDItemValueSetter.setValue(poRecPmsg.glblCmpyCd, glParam.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(poRecPmsg.sysSrcCd, SYS_SRC.S21_PROCUREMENT);

                String sceOrdTpCd = "";

                if (ZYPCommonFunc.hasValue(glParam.sceOrdTpCd)) {

                    sceOrdTpCd = glParam.sceOrdTpCd.getValue();

                } else {

                    if (chkPrntCmpyVnd(glParam.glblCmpyCd.getValue(), glPoHead.get(NPZC131001Constant.VND_CD).toString())) {
                        sceOrdTpCd = NLXSceConst.SCE_ORD_TP_CD_DG;
                    } else {
                        sceOrdTpCd = NLXSceConst.SCE_ORD_TP_CD_DP;
                    }
                }

                ZYPEZDItemValueSetter.setValue(poRecPmsg.sceOrdTpCd, sceOrdTpCd);

                // V1.3
                // ZYPEZDItemValueSetter.setValue(poRecPmsg.rwsRefNum,
                // rwfRefNum);
                ZYPEZDItemValueSetter.setValue(poRecPmsg.rwsRefNum, glParam.rwsRefNum);
                ZYPEZDItemValueSetter.setValue(poRecPmsg.poRcvFromLocTpCd, LOC_TP.VENDOR);
                ZYPEZDItemValueSetter.setValue(poRecPmsg.poRcvFromLocCd, (String) glPoHead.get(NPZC131001Constant.VND_CD));
                ZYPEZDItemValueSetter.setValue(poRecPmsg.whCd, (String) poDtl.get(NPZC131001Constant.RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(poRecPmsg.poRcvDrctShipTpCd, DRCT_SHIP_TP.SELL_THEN_BUY);
                ZYPEZDItemValueSetter.setValue(poRecPmsg.inlndCarrCd, paramPoDtl.carrCd);
                ZYPEZDItemValueSetter.setValue(poRecPmsg.poRcvEtaDt, glParam.etaDt);
                ZYPEZDItemValueSetter.setValue(poRecPmsg.poRcvTrxHdrNum, glParam.poOrdNum);

                // QC13225
                if (ZYPCommonFunc.hasValue(glParam.vndInvNum)) {
                    ZYPEZDItemValueSetter.setValue(poRecPmsg.domInvNum, glParam.vndInvNum);
                } else {
                    ZYPEZDItemValueSetter.setValue(poRecPmsg.domInvNum, glParam.shipFromSoNum);
                }

                // QC#21082
                String custIssPoNum = (String) poDtl.get(NPZC131001Constant.CUST_ISS_PO_NUM);
                if (ZYPCommonFunc.hasValue(custIssPoNum)) {
                    int digit = poRecPmsg.getAttr("custPoNum").getDigit();
                    if (custIssPoNum.length() > digit) {
                        ZYPEZDItemValueSetter.setValue(poRecPmsg.custPoNum, custIssPoNum.substring(0, digit));
                    } else {
                        ZYPEZDItemValueSetter.setValue(poRecPmsg.custPoNum, custIssPoNum);
                    }
                }
                ZYPEZDItemValueSetter.setValue(poRecPmsg.shipFromSoNum, glParam.shipFromSoNum);
            }

            // NLZC2010 PO Receiving API detail glParam set
            ZYPEZDItemValueSetter.setValue(poRecPmsg.OrdInfoLIst.no(i).mdseCd, paramPoDtl.mdseCd);
            ZYPEZDItemValueSetter.setValue(poRecPmsg.OrdInfoLIst.no(i).stkStsCd, paramPoDtl.stkStsCd);
            ZYPEZDItemValueSetter.setValue(poRecPmsg.OrdInfoLIst.no(i).poRcvQty, paramPoDtl.shipQty);
            ZYPEZDItemValueSetter.setValue(poRecPmsg.OrdInfoLIst.no(i).poRcvTrxLineNum, paramPoDtl.poOrdDtlLineNum);
            ZYPEZDItemValueSetter.setValue(poRecPmsg.OrdInfoLIst.no(i).asnSoNum, glParam.asnSoNum);
            ZYPEZDItemValueSetter.setValue(poRecPmsg.OrdInfoLIst.no(i).asnLineNum, paramPoDtl.asnLineNum);
            ZYPEZDItemValueSetter.setValue(poRecPmsg.OrdInfoLIst.no(i).carrCd, paramPoDtl.carrCd);
            ZYPEZDItemValueSetter.setValue(poRecPmsg.OrdInfoLIst.no(i).bolNum, paramPoDtl.bolNum);
            ZYPEZDItemValueSetter.setValue(poRecPmsg.OrdInfoLIst.no(i).proNum, paramPoDtl.proNum);
            ZYPEZDItemValueSetter.setValue(poRecPmsg.OrdInfoLIst.no(i).invtyLocCd, (String) poDtl.get(NPZC131001Constant.INVTY_LOC_CD));
            ZYPEZDItemValueSetter.setValue(poRecPmsg.OrdInfoLIst.no(i).shipFromInvtyLocCd, (String) glPoHead.get(NPZC131001Constant.VND_CD));
            ZYPEZDItemValueSetter.setValue(poRecPmsg.OrdInfoLIst.no(i).prchReqNum, (String) poDtl.get(NPZC131001Constant.PRCH_REQ_NUM));
            ZYPEZDItemValueSetter.setValue(poRecPmsg.OrdInfoLIst.no(i).prchReqLineNum, (String) poDtl.get(NPZC131001Constant.PRCH_REQ_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(poRecPmsg.OrdInfoLIst.no(i).prchReqLineSubNum, (BigDecimal) poDtl.get(NPZC131001Constant.PRCH_REQ_LINE_SUB_NUM));
            poRecPmsg.OrdInfoLIst.setValidCount(i + 1);
        }

        for (int i = 0; i < glParam.msgInfo.getValidCount() && i < poRecPmsg.MsgInfoLIst.length(); i++) {
            ZYPEZDItemValueSetter.setValue(poRecPmsg.MsgInfoLIst.no(i).poRcvMsgTxt, glParam.msgInfo.no(i).msgTxtInfoTxt);
            poRecPmsg.MsgInfoLIst.setValidCount(i + 1);
        }

        if (!bReturn) {
            return false;
        }

        // call NLZC2010 PO Receiving API
        NLZC201001 dLZC201001 = new NLZC201001();
        dLZC201001.execute(poRecPmsg, glOnBatchType);

        if (poRecPmsg.xxMsgIdList.getValidCount() > 0) {

            for (int j = 0; j < poRecPmsg.xxMsgIdList.getValidCount(); j++) {
                glMsgMap.addXxMsgId(poRecPmsg.xxMsgIdList.no(j).xxMsgId.getValue());
            }

            return false;
        }

        String poRcvNum = poRecPmsg.poRcvNum.getValue();

        // call Sell Then Buy Direct Shipment API
        if (!callSellThenBuyDirectShipmentApi(poRcvNum)) {
            return false;
        }

        // get PO_RCV info
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put(NPZC131001Constant.GLBL_CMPY_CD, glParam.glblCmpyCd.getValue());
        queryParam.put(NPZC131001Constant.PO_RCV_NUM, poRcvNum);
        List<Map<String, Object>> poRcvInfoList = (List<Map<String, Object>>) glSsmBatchClient.queryObjectList("getPoRcvInfo", queryParam);

        if (poRcvInfoList == null || poRcvInfoList.size() == 0) {
            return false;
        }

        // PO Rcv Dtl Loop Start
        List<String> origPoDtlLineList = new ArrayList<String>();
        List<String> mdseCdList = new ArrayList<String>();
        List<String> serNumList = new ArrayList<String>();
        for (int k = 0; k < poRcvInfoList.size(); k++) {
            Map<String, Object> poRcvInfoMap = poRcvInfoList.get(k);

            // call PO Status Update API
            if (!callPoUpdateApi(poRcvInfoMap, PO_STS.RECEIVING)) {
                bReturn = false;
                break;
            }
            if (!callPoUpdateApi(poRcvInfoMap, PO_STS.RECEIVING_COMPLETION)) {
                bReturn = false;
                break;
            }

            // QC#11937
            List<NPZC131001_serNumListPMsg> serList = new ArrayList<NPZC131001_serNumListPMsg>();

            // get Ser Num List
            for (int i = 0; i < glParam.serNumList.getValidCount(); i++) {

                String poOrdDtlLineNum = (String) poRcvInfoMap.get(NPZC131001Constant.ORIG_PO_ORD_DTL_LINE_NUM);
                String mdseCd = (String) poRcvInfoMap.get(NPZC131001Constant.MDSE_CD);
                String paramPoOrdDtlLineNum = glParam.serNumList.no(i).poOrdDtlLineNum.getValue();
                String paramMdseCd = glParam.serNumList.no(i).mdseCd.getValue();
                BigDecimal poRcvQty = (BigDecimal) poRcvInfoMap.get(NPZC131001Constant.PO_RCV_QTY);
                String serNum = glParam.serNumList.no(i).serNum.getValue();

                // QC#25499
                MDSETMsg mdseTMsg = new MDSETMsg();
                ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, glParam.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, paramMdseCd);
                mdseTMsg = (MDSETMsg) S21ApiTBLAccessor.findByKey(mdseTMsg);

                if (mdseTMsg == null || ZYPConstant.FLG_OFF_N.equals(mdseTMsg.rcvSerTakeFlg.getValue())) {
                    continue;
                }
                
                if (paramPoOrdDtlLineNum.equals(poOrdDtlLineNum) && paramMdseCd.equals(mdseCd)) {
                    if (poRcvQty.compareTo(BigDecimal.valueOf(serList.size())) == 0) {
                        break;
                    }
                    if (origPoDtlLineList.contains(poOrdDtlLineNum) && mdseCdList.contains(mdseCd) && serNumList.contains(serNum)) {
                        continue;
                    }
                    serList.add(glParam.serNumList.no(i));
                    origPoDtlLineList.add(poOrdDtlLineNum);
                    serNumList.add(serNum);
                    mdseCdList.add(mdseCd);
                }
            }

            // Ser Num List Loop Start
            for (int serNumListCnt = 0; serNumListCnt < serList.size(); serNumListCnt++) {

                NPZC131001_serNumListPMsg pSerList = serList.get(serNumListCnt);

                // call Serial Validation API
                NLZC403001PMsg serValPmsg = new NLZC403001PMsg();
                ZYPEZDItemValueSetter.setValue(serValPmsg.glblCmpyCd, glParam.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(serValPmsg.xxModeCd, NLZC403001Constant.MODE_INBOUND);
                ZYPEZDItemValueSetter.setValue(serValPmsg.whCd, (String) poRcvInfoMap.get(NPZC131001Constant.INVTY_LOC_CD));
                ZYPEZDItemValueSetter.setValue(serValPmsg.mdseCd, pSerList.mdseCd);
                ZYPEZDItemValueSetter.setValue(serValPmsg.serNum, pSerList.serNum);

                NLZC403001 dLZC403001 = new NLZC403001();
                dLZC403001.execute(serValPmsg, glOnBatchType);

                if (NLZC403001Constant.RETURN_CODE_ERROR.equals(serValPmsg.xxRsltStsCd.getValue()) && serValPmsg.xxMsgIdList.getValidCount() > 0) {

                    for (int i = 0; i < serValPmsg.xxMsgIdList.getValidCount(); i++) {
                        glMsgMap.addXxMsgId(serValPmsg.xxMsgIdList.no(i).xxMsgId.getValue());
                    }

                    bReturn = false;
                    break;
                }

                String rsltStsCd = serValPmsg.xxRsltStsCd.getValue();

                if (!NLZC403001Constant.RETURN_CODE_NORMAL.equals(rsltStsCd)) {
                    callMachineMasterAPI(poRcvInfoMap, null);
                    continue;
                }

                // create PO Serial
                if (!insertPoSerNum(pSerList, poRcvInfoMap)) {
                    bReturn = false;
                    break;
                }

                // set NLZC3020 Serial Update API
                NLZC302001PMsg serPmsg = new NLZC302001PMsg();
                ZYPEZDItemValueSetter.setValue(serPmsg.glblCmpyCd, glParam.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(serPmsg.UpdateDetailList.no(0).serNum, pSerList.serNum);
                ZYPEZDItemValueSetter.setValue(serPmsg.UpdateDetailList.no(0).mdseCd, pSerList.mdseCd);
                ZYPEZDItemValueSetter.setValue(serPmsg.UpdateDetailList.no(0).serTrxTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
                ZYPEZDItemValueSetter.setValue(serPmsg.UpdateDetailList.no(0).serTrxEventCd, SER_TRX_EVENT.DROP_SHIPMENT);
                ZYPEZDItemValueSetter.setValue(serPmsg.UpdateDetailList.no(0).serTrxSrcTpCd, SER_TRX_SRC_TP.SO);
                ZYPEZDItemValueSetter.setValue(serPmsg.UpdateDetailList.no(0).serTrxSrcHdrNum, "");
                ZYPEZDItemValueSetter.setValue(serPmsg.UpdateDetailList.no(0).serTrxSrcLineNum, (String) poRcvInfoMap.get(NPZC131001Constant.PO_RCV_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(serPmsg.UpdateDetailList.no(0).serTrxRefNum, (String) poRcvInfoMap.get(NPZC131001Constant.CPO_ORD_NUM));
                ZYPEZDItemValueSetter.setValue(serPmsg.UpdateDetailList.no(0).fromLocCd, (String) poRcvInfoMap.get(NPZC131001Constant.VND_CD));
                ZYPEZDItemValueSetter.setValue(serPmsg.UpdateDetailList.no(0).toLocCd, (String) poRcvInfoMap.get(NPZC131001Constant.SHIP_TO_CUST_CD));
                ZYPEZDItemValueSetter.setValue(serPmsg.UpdateDetailList.no(0).manCratFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(serPmsg.UpdateDetailList.no(0).toStkStsCd, (String) poRcvInfoMap.get(NPZC131001Constant.STK_STS_CD));
                ZYPEZDItemValueSetter.setValue(serPmsg.UpdateDetailList.no(0).locStsCd, LOC_STS.DC_STOCK);
                serPmsg.UpdateDetailList.setValidCount(1);

                // call NLZC3020 Serial Update API
                NLZC302001 dLZC302001 = new NLZC302001();
                dLZC302001.execute(serPmsg, glOnBatchType);

                if (serPmsg.xxMsgIdList.getValidCount() > 0) {
                    for (int l = 0; l < serPmsg.xxMsgIdList.getValidCount(); l++) {
                        glMsgMap.addXxMsgId(serPmsg.xxMsgIdList.no(l).xxMsgId.getValue());
                    }
                    bReturn = false;
                    break;
                }

                // Ser Num List Loop End
            }
            callMachineMasterAPI(poRcvInfoMap, serList);

            // PO Rcv Dtl Loop End
        }

        return bReturn;
    }

    /**
     * call Machine Master Update API
     * @param poRcvInfoMap (Map<String, Object>
     * @param serList List<NPZC131001_serNumListPMsg>
     * @return boolean
     */
    private void callMachineMasterAPI(Map<String, Object> poRcvInfoMap, List<NPZC131001_serNumListPMsg> serList) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put(NPZC131001Constant.GLBL_CMPY_CD, glParam.glblCmpyCd.getValue());
        queryParam.put(NPZC131001Constant.MDSE_CD, (String) poRcvInfoMap.get(NPZC131001Constant.MDSE_CD));

        String iBMdseCd = (String) glSsmBatchClient.queryObject("findIBTrac", queryParam);

        if (iBMdseCd == null || iBMdseCd.isEmpty()) {
            return;
        }

        if (glParam.serNumList.getValidCount() > 0) {

            if (serList == null) {
             // call Machine Master Update API
                NSZC001001PMsg mmPmsg = new NSZC001001PMsg();
                mmPmsg.serNum.clear();
                ZYPEZDItemValueSetter.setValue(mmPmsg.glblCmpyCd, glParam.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(mmPmsg.slsDt, glParam.slsDt);
                ZYPEZDItemValueSetter.setValue(mmPmsg.xxModeCd, ProcessMode.INSERT_WAREHOUSE.code);
                ZYPEZDItemValueSetter.setValue(mmPmsg.mdseCd, (String) poRcvInfoMap.get(NPZC131001Constant.MDSE_CD));
                ZYPEZDItemValueSetter.setValue(mmPmsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.CREATED);
                ZYPEZDItemValueSetter.setValue(mmPmsg.locNm, (String) poRcvInfoMap.get(NPZC131001Constant.INVTY_LOC_NM));
                ZYPEZDItemValueSetter.setValue(mmPmsg.stkStsCd, (String) poRcvInfoMap.get(NPZC131001Constant.STK_STS_CD));
                ZYPEZDItemValueSetter.setValue(mmPmsg.autoCratFlg, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(mmPmsg.effFromDt, glParam.shipDt);
                ZYPEZDItemValueSetter.setValue(mmPmsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_INVENTORY);
                ZYPEZDItemValueSetter.setValue(mmPmsg.svcMachQty, BigDecimal.ONE);
                ZYPEZDItemValueSetter.setValue(mmPmsg.svcMachMstrLocStsCd, LOC_STS.DC_STOCK);
                ZYPEZDItemValueSetter.setValue(mmPmsg.curLocNum, (String) poRcvInfoMap.get(NPZC131001Constant.INVTY_LOC_CD));

                NSZC001001 dSZC001001 = new NSZC001001();
                dSZC001001.execute(mmPmsg, glOnBatchType);

                if (mmPmsg.xxMsgIdList.getValidCount() > 0) {
                    for (int c = 0; c < mmPmsg.xxMsgIdList.getValidCount(); c++) {
                        glMsgMap.addXxMsgId(mmPmsg.xxMsgIdList.no(c).xxMsgId.getValue());
                    }
                    return;
                }

                // call Allocation mode
                if (!callAllocationModeApi(poRcvInfoMap, mmPmsg.svcMachMstrPk)) {
                    return;
                }

                return;
            }
            for (int i = 0; i <serList.size(); i++) {

                NPZC131001_serNumListPMsg serNumPMsg = serList.get(i);

                // parameter SerNumList
                String poOrdDtlLineNum = (String) poRcvInfoMap.get(NPZC131001Constant.ORIG_PO_ORD_DTL_LINE_NUM);
                String mdseCd = (String) poRcvInfoMap.get(NPZC131001Constant.MDSE_CD);
                String paramPoOrdDtlLineNum = serNumPMsg.poOrdDtlLineNum.getValue();
                String paramMdseCd = serNumPMsg.mdseCd.getValue();
                String serNum = serNumPMsg.serNum.getValue();

                // call Machine Master Update API
                NSZC001001PMsg mmPmsg = new NSZC001001PMsg();

                if (paramPoOrdDtlLineNum.equals(poOrdDtlLineNum) && paramMdseCd.equals(mdseCd)) {
                    ZYPEZDItemValueSetter.setValue(mmPmsg.serNum, serNum);
                } else {
                    mmPmsg.serNum.clear();
                }

                ZYPEZDItemValueSetter.setValue(mmPmsg.glblCmpyCd, glParam.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(mmPmsg.slsDt, glParam.slsDt);
                ZYPEZDItemValueSetter.setValue(mmPmsg.xxModeCd, ProcessMode.INSERT_WAREHOUSE.code);
                ZYPEZDItemValueSetter.setValue(mmPmsg.mdseCd, mdseCd);
                ZYPEZDItemValueSetter.setValue(mmPmsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.CREATED);
                ZYPEZDItemValueSetter.setValue(mmPmsg.locNm, (String) poRcvInfoMap.get(NPZC131001Constant.INVTY_LOC_NM));
                ZYPEZDItemValueSetter.setValue(mmPmsg.stkStsCd, (String) poRcvInfoMap.get(NPZC131001Constant.STK_STS_CD));
                ZYPEZDItemValueSetter.setValue(mmPmsg.autoCratFlg, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(mmPmsg.effFromDt, glParam.shipDt);
                ZYPEZDItemValueSetter.setValue(mmPmsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_INVENTORY);
                ZYPEZDItemValueSetter.setValue(mmPmsg.svcMachQty, BigDecimal.ONE);
                ZYPEZDItemValueSetter.setValue(mmPmsg.svcMachMstrLocStsCd, LOC_STS.DC_STOCK);
                ZYPEZDItemValueSetter.setValue(mmPmsg.curLocNum, (String) poRcvInfoMap.get(NPZC131001Constant.INVTY_LOC_CD));

                NSZC001001 dSZC001001 = new NSZC001001();
                dSZC001001.execute(mmPmsg, glOnBatchType);

                if (mmPmsg.xxMsgIdList.getValidCount() > 0) {

                    for (int c = 0; c < mmPmsg.xxMsgIdList.getValidCount(); c++) {
                        glMsgMap.addXxMsgId(mmPmsg.xxMsgIdList.no(c).xxMsgId.getValue());
                    }

                    return;
                }

                // call Allocation mode
                if (!callAllocationModeApi(poRcvInfoMap, mmPmsg.svcMachMstrPk)) {
                    return;
                }
            }

        } else {

            // QC#27719 MOD START
            BigDecimal poRcvQty = (BigDecimal) poRcvInfoMap.get("PO_RCV_QTY");
            for (int i = 0; i < poRcvQty.intValue(); i++) {
                // call Machine Master Update API
                NSZC001001PMsg mmPmsg = new NSZC001001PMsg();
                mmPmsg.serNum.clear();
                ZYPEZDItemValueSetter.setValue(mmPmsg.glblCmpyCd, glParam.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(mmPmsg.slsDt, glParam.slsDt);
                ZYPEZDItemValueSetter.setValue(mmPmsg.xxModeCd, ProcessMode.INSERT_WAREHOUSE.code);
                ZYPEZDItemValueSetter.setValue(mmPmsg.mdseCd, (String) poRcvInfoMap.get(NPZC131001Constant.MDSE_CD));
                ZYPEZDItemValueSetter.setValue(mmPmsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.CREATED);
                ZYPEZDItemValueSetter.setValue(mmPmsg.locNm, (String) poRcvInfoMap.get(NPZC131001Constant.INVTY_LOC_NM));
                ZYPEZDItemValueSetter.setValue(mmPmsg.stkStsCd, (String) poRcvInfoMap.get(NPZC131001Constant.STK_STS_CD));
                ZYPEZDItemValueSetter.setValue(mmPmsg.autoCratFlg, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(mmPmsg.effFromDt, glParam.shipDt);
                ZYPEZDItemValueSetter.setValue(mmPmsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_INVENTORY);
                ZYPEZDItemValueSetter.setValue(mmPmsg.svcMachQty, BigDecimal.ONE);
                ZYPEZDItemValueSetter.setValue(mmPmsg.svcMachMstrLocStsCd, LOC_STS.DC_STOCK);
                ZYPEZDItemValueSetter.setValue(mmPmsg.curLocNum, (String) poRcvInfoMap.get(NPZC131001Constant.INVTY_LOC_CD));

                NSZC001001 dSZC001001 = new NSZC001001();
                dSZC001001.execute(mmPmsg, glOnBatchType);

                if (mmPmsg.xxMsgIdList.getValidCount() > 0) {
                    for (int c = 0; c < mmPmsg.xxMsgIdList.getValidCount(); c++) {
                        glMsgMap.addXxMsgId(mmPmsg.xxMsgIdList.no(c).xxMsgId.getValue());
                    }
                    return;
                }

                // call Allocation mode
                // if (!callAllocationModeApi(poRcvInfoMap,
                // mmPmsg.svcMachMstrPk)) {
                // return;
                // }
            }
            // QC#27719 MOD END
        }
    }

    /**
     * <pre>
     * check RWS_REF_NUM exist
     * </pre>
     * @param poDtl Map<String, Object>
     * @return result(OK:true, NG:false)
     */
    private boolean chkRwsRefNum(Map<String, Object> poDtl) {

    	// QC:51173 Start
//        RWS_HDRTMsg rwsHdrTMsg = new RWS_HDRTMsg();
//        rwsHdrTMsg.glblCmpyCd.setValue(glParam.glblCmpyCd.getValue());
//        rwsHdrTMsg.whCd.setValue((String) poDtl.get(NPZC131001Constant.RTL_WH_CD));
//        rwsHdrTMsg.rwsRefNum.setValue(glParam.rwsRefNum.getValue());
//        rwsHdrTMsg.setSQLID("001");
//        RWS_HDRTMsgArray rwsHdrTMsgArray = (RWS_HDRTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(rwsHdrTMsg);
//
//        if (rwsHdrTMsgArray.length() > 0) {
//            return true;
//        }

    	HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("glblCmpyCd", glParam.glblCmpyCd.getValue());
        paramMap.put("whCd", (String) poDtl.get(NPZC131001Constant.RTL_WH_CD));
        paramMap.put("rwsRefNum", glParam.rwsRefNum.getValue());

        List<String> rwsRefNumList = (List<String>) glSsmBatchClient.queryObjectList("getRwsRefNum", paramMap);

        if (!rwsRefNumList.isEmpty()) {
            return true;
        }
        // QC:51173 End

        return false;
    }

    /**
     * <pre>
     * get RwsRefNum
     * </pre>
     * @param poDtl Map<String, Object>
     * @return rwfRefNum String
     */
    private String getRwsRefNum(Map<String, Object> poDtl) {

    	// QC:51173 Start
        /*
        String paramRwsRefNum = glParam.rwsRefNum.getValue();
        String wkRwsRefNum = "";
        long lRwsRefNum = 0;

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put(NPZC131001Constant.GLBL_CMPY_CD, glParam.glblCmpyCd.getValue());
        queryParam.put(NPZC131001Constant.WH_CD, (String) poDtl.get(NPZC131001Constant.RTL_WH_CD));
        queryParam.put(NPZC131001Constant.RWS_REF_NUM, glParam.rwsRefNum.getValue());

        Map<String, Object> resultMap = (Map<String, Object>) glSsmBatchClient.queryObject("getRwsRefNum", queryParam);

        if (resultMap == null) {
            return null;
        } else {
            wkRwsRefNum = (String) resultMap.get(NPZC131001Constant.RWS_REF_NUM);
        }

        lRwsRefNum = Long.parseLong(wkRwsRefNum.substring(wkRwsRefNum.indexOf("-") + 1)) + 1;
        wkRwsRefNum = paramRwsRefNum.substring(0, wkRwsRefNum.indexOf("-") + 1);

        if (paramRwsRefNum.length() == NPZC131001Constant.LENGTH_13) {
            wkRwsRefNum = wkRwsRefNum + lRwsRefNum;
        } else if (paramRwsRefNum.length() < NPZC131001Constant.LENGTH_13) {
            wkRwsRefNum = wkRwsRefNum + String.format("%03d", lRwsRefNum);
        }

        if (wkRwsRefNum.length() > NPZC131001Constant.LENGTH_15) {
            glMsgMap.addXxMsgId(NPZC131001Constant.NPAM1320E);
            return null;
        }

        return wkRwsRefNum;
        */

    	String rwsRefNum = null;
    	String shipFromSoNum = glParam.rwsRefNum.getValue();

        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("glblCmpyCd", glParam.glblCmpyCd.getValue());
        paramMap.put("whCd", (String) poDtl.get(NPZC131001Constant.RTL_WH_CD));
        paramMap.put("rwsRefNum", glParam.rwsRefNum.getValue());

        List<String> rwsRefNumList = (List<String>) glSsmBatchClient.queryObjectList("getRwsRefNum", paramMap);

        if (rwsRefNumList.isEmpty()) {

            rwsRefNum = shipFromSoNum;

        } else {
            // START 2022/10/21 R.Azucena [QC#60682, MOD]
            // paramMap.put("rwsRefNum", "^" + shipFromSoNum + "-" + "[0-9]{1,2}" + "$");
            if (shipFromSoNum.matches("^.*-[0-9]{1,2}$")) {
                int index = shipFromSoNum.lastIndexOf("-");
                shipFromSoNum = shipFromSoNum.substring(0, index);
            } else if (shipFromSoNum.length() > NPZC131001Constant.BASE_NUM_MAX_LIMIT) {
                shipFromSoNum = shipFromSoNum.substring(0, NPZC131001Constant.BASE_NUM_MAX_LIMIT);
            }

            if (shipFromSoNum.length() > NPZC131001Constant.BASE_NUM_MAX_LIMIT) {
                paramMap.put("rwsRefNum", "^" + shipFromSoNum + "-" + "[0-9]{1}" + "$");
            } else {
                paramMap.put("rwsRefNum", "^" + shipFromSoNum + "-" + "[0-9]{2}" + "$");
            }
            // END 2022/10/21 R.Azucena [QC#60682, MOD]

            List<String> maxRwsRefNumList = (List<String>) glSsmBatchClient.queryObjectList("getMaxRwsRefNum", paramMap);

            if (maxRwsRefNumList == null || maxRwsRefNumList.isEmpty()) {
                // START 2022/10/21 R.Azucena [QC#60682, MOD]
                // if (shipFromSoNum.length() < 15 - 2) {
                if (shipFromSoNum.length() <= NPZC131001Constant.BASE_NUM_MAX_LIMIT) {
                // END 2022/10/21 R.Azucena [QC#60682, MOD]

                    rwsRefNum = shipFromSoNum + "-" + "01";

                } else {

                    rwsRefNum = shipFromSoNum + "-" + "1";

                }

            } else {

                // if (!maxRwsRefNumList.isEmpty()) {

                String maxRwsRefNum = maxRwsRefNumList.get(0);

                if (maxRwsRefNum == null) {
                    // START 2022/10/21 R.Azucena [QC#60682, MOD]
                    // if (shipFromSoNum.length() < 15 - 2) {
                    if (shipFromSoNum.length() <= NPZC131001Constant.BASE_NUM_MAX_LIMIT) {
                    // END 2022/10/21 R.Azucena [QC#60682, MOD]

                        rwsRefNum = shipFromSoNum + "-" + "01";

                    } else {

                        rwsRefNum = shipFromSoNum + "-" + "1";

                    }

                } else {

                    int index = maxRwsRefNum.lastIndexOf("-");

                    String strRevision = String.valueOf(Integer.parseInt(maxRwsRefNum.substring(index + 1)) + 1);

                    // START 2022/10/21 R.Azucena [QC#60682, MOD]
                    // if (shipFromSoNum.length() < 15 - 2) {
                    if (shipFromSoNum.length() <= NPZC131001Constant.BASE_NUM_MAX_LIMIT) {
                    // END 2022/10/21 R.Azucena [QC#60682, MOD]
                        rwsRefNum = shipFromSoNum + "-" + ZYPCommonFunc.leftPad(strRevision, 2, "0");

                    } else {

                        rwsRefNum = shipFromSoNum + "-" + strRevision;
                    }
                }
            }
        }

        return rwsRefNum;
        // QC:51173 End
    }

    /**
     * <pre>
     * check PRNT_CMPY_VND
     * </pre>
     * @param vndCd String
     * @return result(OK:true, NG:false)
     */
    private boolean chkPrntCmpyVnd(String glblCmpyCd, String vndCd) {

        PRNT_CMPY_VNDTMsg prntCmpyVndTMsg = new PRNT_CMPY_VNDTMsg();
        prntCmpyVndTMsg.glblCmpyCd.setValue(glblCmpyCd);
        prntCmpyVndTMsg.prntCmpyVndCd.setValue(vndCd);
        prntCmpyVndTMsg = (PRNT_CMPY_VNDTMsg) S21ApiTBLAccessor.findByKey(prntCmpyVndTMsg);

        if (prntCmpyVndTMsg == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(prntCmpyVndTMsg.getReturnCode())) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * call Sell Then Buy Direct Shipment API
     * </pre>
     * @param poRcvNum String
     * @return result(OK:true, NG:false)
     */
    private boolean callSellThenBuyDirectShipmentApi(String poRcvNum) {

        NLZC010001PMsg pMsg = new NLZC010001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glParam.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.sysSrcCd, SYS_SRC.S21_PROCUREMENT);
        ZYPEZDItemValueSetter.setValue(pMsg.poRcvNum, poRcvNum);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, glParam.slsDt);
        NLZC010001 dLZC010001 = new NLZC010001();

        dLZC010001.execute(pMsg, glOnBatchType);

        List<String> msgIds = S21ApiUtil.getXxMsgIdList(pMsg);

        for (String msgId : msgIds) {
            S21InfoLogOutput.println("Sell Then Buy API Error:" + S21MessageFunc.clspGetMessage(msgId));
            glMsgMap.addXxMsgId(msgId);
            if (msgId.endsWith("E")) {
                return false;
            }
        }
        return true;
    }

    /**
     * <pre>
     * call PO Update API
     * </pre>
     * @param poRcvInfoMap Map<String, Object>
     * @return result(OK:true, NG:false)
     */
    private boolean callPoUpdateApi(Map<String, Object> poRcvInfoMap, String poStsCd) {

        String poOrdNum = (String) poRcvInfoMap.get(NPZC131001Constant.PO_ORD_NUM);
        String mdseCd = (String) poRcvInfoMap.get(NPZC131001Constant.MDSE_CD);
        BigDecimal poRcvQty = (BigDecimal) poRcvInfoMap.get(NPZC131001Constant.PO_RCV_QTY);
        String poOrdDtlLineNum = (String) poRcvInfoMap.get(NPZC131001Constant.PO_ORD_DTL_LINE_NUM);

        NPZC004001PMsg pMsg = new NPZC004001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glParam.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum, poOrdNum);
        ZYPEZDItemValueSetter.setValue(pMsg.poStsCd, poStsCd);
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(pMsg.poRcvQty, poRcvQty);
        ZYPEZDItemValueSetter.setValue(pMsg.poOrdDtlLineNum, poOrdDtlLineNum);

        NPZC004001 dPZC004001 = new NPZC004001();
        dPZC004001.execute(pMsg, glOnBatchType);

        if (pMsg.xxMsgIdList.getValidCount() > 0) {

            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {

                glMsgMap.addXxMsgId(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());

            }

            return false;
        }

        return true;
    }

    /**
     * <pre>
     * call Shipping Plan Update API For Divide PO
     * </pre>
     * @param paramPoDtl NPZC131001_ordDtlInfoPMsg
     * @param shpgPlnNum String
     * @param carrNum String
     * @return result(OK:true, NG:false)
     */
    private boolean callShpgPlnUpd(NPZC131001_ordDtlInfoPMsg paramPoDtl, String shpgPlnNum, String carrNm) {

        List<NWZC003001PMsg> pMsgList = new ArrayList<NWZC003001PMsg>();
        NWZC003001PMsg pMsg = new NWZC003001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glParam.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.shpgModeCd, SHPG_PLN_UPD_MODE.VENDOR_INVOICE_RECEIVED);
        ZYPEZDItemValueSetter.setValue(pMsg.shpgPlnNum, shpgPlnNum);
        ZYPEZDItemValueSetter.setValue(pMsg.ordQty, paramPoDtl.shipQty);
        ZYPEZDItemValueSetter.setValue(pMsg.spTotFuncFrtAmt, glParam.frtAmt);
        ZYPEZDItemValueSetter.setValue(pMsg.actlShipDt, glParam.shipDt);

        // T.Wada QC13225
        // ZYPEZDItemValueSetter.setValue(pMsg.invNum,
        // glParam.vndInvNum);
        ZYPEZDItemValueSetter.setValue(pMsg.invNum, glParam.rwsRefNum);

        ZYPEZDItemValueSetter.setValue(pMsg.carrCd, paramPoDtl.carrCd); // 2019/10/03 QC#53186 Add
        ZYPEZDItemValueSetter.setValue(pMsg.carrNm, carrNm);
        ZYPEZDItemValueSetter.setValue(pMsg.bolNum, paramPoDtl.bolNum);
        ZYPEZDItemValueSetter.setValue(pMsg.proNum, paramPoDtl.proNum);
        pMsgList.add(pMsg);

        NWZC003001 dWZC003001 = new NWZC003001();
        dWZC003001.execute(pMsgList, glOnBatchType);

        if (pMsg.xxMsgIdList.getValidCount() > 0) {

            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                glMsgMap.addXxMsgId(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(pMsg.xxMsgIdList.no(0).xxMsgId.getValue()));
            }

            return false;
        }

        return true;
    }

    /**
     * <pre>
     * call Shipping Plan Update API For Divide PO Set Item
     * 
     * </pre>
     * @param shipQty BigDecimal
     * @param shpgPlnNum String
     * @param carrNum String
     * @return result(OK:true, NG:false)
     */
    private boolean callSetShpgPlnUpd(BigDecimal shipQty, String bolNum, String proNum, String shpgPlnNum, String carrNm) {

        List<NWZC003001PMsg> pMsgList = new ArrayList<NWZC003001PMsg>();
        NWZC003001PMsg pMsg = new NWZC003001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glParam.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.shpgModeCd, SHPG_PLN_UPD_MODE.VENDOR_INVOICE_RECEIVED);
        ZYPEZDItemValueSetter.setValue(pMsg.shpgPlnNum, shpgPlnNum);
        ZYPEZDItemValueSetter.setValue(pMsg.ordQty, shipQty);
        ZYPEZDItemValueSetter.setValue(pMsg.spTotFuncFrtAmt, glParam.frtAmt);
        ZYPEZDItemValueSetter.setValue(pMsg.actlShipDt, glParam.shipDt);

        // T.Wada QC13225
        // ZYPEZDItemValueSetter.setValue(pMsg.invNum,
        // glParam.vndInvNum);
        ZYPEZDItemValueSetter.setValue(pMsg.invNum, glParam.rwsRefNum);

        ZYPEZDItemValueSetter.setValue(pMsg.carrNm, carrNm);
        ZYPEZDItemValueSetter.setValue(pMsg.bolNum, bolNum);
        ZYPEZDItemValueSetter.setValue(pMsg.proNum, proNum);
        pMsgList.add(pMsg);

        NWZC003001 dWZC003001 = new NWZC003001();
        dWZC003001.execute(pMsgList, glOnBatchType);

        if (pMsg.xxMsgIdList.getValidCount() > 0) {

            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                glMsgMap.addXxMsgId(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(pMsg.xxMsgIdList.no(0).xxMsgId.getValue()));
            }

            return false;
        }

        return true;
    }

    // QC#14457 START
    /**
     * getDivideShpgPlnNum
     * @param poOrdDtlLineNum
     * @return String ShpgPlnNum
     */
    private String getDivideShpgPlnNum(String poOrdDtlLineNum) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", glParam.glblCmpyCd.getValue());
        queryParam.put("poOrdNum", glParam.poOrdNum.getValue());
        queryParam.put("poOrdDtlLineNum", poOrdDtlLineNum);
        queryParam.put("shpgStsCd", SHPG_STS.SHIPPED);

        return (String) glSsmBatchClient.queryObject("getDivideShpgPlnNum", queryParam);

    }

    // QC#14457 END

    /**
     * <pre>
     * call Shipping Plan Update API For InTangible
     * </pre>
     * @return result(OK:true, NG:false)
     */
    private boolean callShpgPlnUpdForInTang(String shpgPlnNum) {

        List<NWZC003001PMsg> pMsgList = new ArrayList<NWZC003001PMsg>();
        NWZC003001PMsg pMsg = new NWZC003001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glParam.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.shpgModeCd, SHPG_PLN_UPD_MODE.PO_CANCEL);
        ZYPEZDItemValueSetter.setValue(pMsg.shpgPlnNum, shpgPlnNum);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, glParam.slsDt);
        pMsgList.add(pMsg);

        NWZC003001 dWZC003001 = new NWZC003001();
        dWZC003001.execute(pMsgList, glOnBatchType);

        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                glMsgMap.addXxMsgId(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
            }
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Update Shipping Plan Mdse
     * </pre>
     * @param shpgPlnNum String
     * @param carrNum String
     */
    private void updShpgPlnMdse(String shpgPlnNum, String mdseCd) {

        SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
        shpgPlnTMsg.glblCmpyCd.setValue(glParam.glblCmpyCd.getValue());
        shpgPlnTMsg.shpgPlnNum.setValue(shpgPlnNum);

        shpgPlnTMsg = (SHPG_PLNTMsg) S21ApiTBLAccessor.findByKeyForUpdate(shpgPlnTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgPlnTMsg.getReturnCode())) {
            return;
        }

        if (!mdseCd.equals(shpgPlnTMsg.mdseCd.getValue())) {
            shpgPlnTMsg.mdseCd.setValue(mdseCd);
            S21ApiTBLAccessor.update(shpgPlnTMsg);
        }
    }

    /**
     * <pre>
     * create PO Serial
     * </pre>
     * @param serNumListPMsg NPZC131001_serNumListPMsg
     * @param poRcvInfoMap Map<String, Object>
     * @return result(OK:true, NG:false)
     */
    private boolean insertPoSerNum(NPZC131001_serNumListPMsg serNumListPMsg, Map<String, Object> poRcvInfoMap) {

        PO_SER_NUMTMsg poSelNumTMsg = new PO_SER_NUMTMsg();

        // PO_SER_NUM insert
        // get SQ
        BigDecimal poSelNum = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PO_SER_NUM_SQ);

        ZYPEZDItemValueSetter.setValue(poSelNumTMsg.glblCmpyCd, glParam.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(poSelNumTMsg.poSerNumPk, poSelNum);
        ZYPEZDItemValueSetter.setValue(poSelNumTMsg.serOwnrId, NLXSceConst.OWNER_PROCUREMENT);
        ZYPEZDItemValueSetter.setValue(poSelNumTMsg.mdseCd, serNumListPMsg.mdseCd.getValue());
        ZYPEZDItemValueSetter.setValue(poSelNumTMsg.serNum, serNumListPMsg.serNum.getValue());
        ZYPEZDItemValueSetter.setValue(poSelNumTMsg.serEventTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
        ZYPEZDItemValueSetter.setValue(poSelNumTMsg.serLocGrpCd, SER_LOC_GRP.DEALER_OR_RETAILER_OR_DISTRIBUTOR);
        ZYPEZDItemValueSetter.setValue(poSelNumTMsg.serEventCd, NLXSceConst.SER_EVENT_CD_THIRD_PARTY_ORDER_RELEASED);
        ZYPEZDItemValueSetter.setValue(poSelNumTMsg.fromLocCd, (String) poRcvInfoMap.get(NPZC131001Constant.VND_CD));
        ZYPEZDItemValueSetter.setValue(poSelNumTMsg.fromLocNm, (String) poRcvInfoMap.get(NPZC131001Constant.VND_NM));
        ZYPEZDItemValueSetter.setValue(poSelNumTMsg.toLocCd, (String) poRcvInfoMap.get(NPZC131001Constant.SHIP_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(poSelNumTMsg.toLocNm, (String) poRcvInfoMap.get(NPZC131001Constant.SHIP_TO_LOC_NM));
        ZYPEZDItemValueSetter.setValue(poSelNumTMsg.keyInfoCd_01, (String) poRcvInfoMap.get(NPZC131001Constant.CPO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(poSelNumTMsg.keyInfoCd_02, (String) poRcvInfoMap.get(NPZC131001Constant.CPO_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(poSelNumTMsg.keyInfoCd_03, (String) poRcvInfoMap.get(NPZC131001Constant.CPO_DTL_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(poSelNumTMsg.keyInfoCd_04, (String) poRcvInfoMap.get(NPZC131001Constant.PO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(poSelNumTMsg.keyInfoCd_05, (String) poRcvInfoMap.get(NPZC131001Constant.PO_ORD_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(poSelNumTMsg.keyInfoCd_06, (String) poRcvInfoMap.get(NPZC131001Constant.PO_RCV_NUM));
        ZYPEZDItemValueSetter.setValue(poSelNumTMsg.keyInfoCd_07, (String) poRcvInfoMap.get(NPZC131001Constant.PO_RCV_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(poSelNumTMsg.keyInfoCd_08, serNumListPMsg.soSerId.getValue());
        ZYPEZDItemValueSetter.setValue(poSelNumTMsg.keyInfoCd_09, (String) poRcvInfoMap.get(NPZC131001Constant.SET_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(poSelNumTMsg.serNumSendFlg, ZYPConstant.FLG_OFF_N);

        S21ApiTBLAccessor.insert(poSelNumTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(poSelNumTMsg.getReturnCode())) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * call Allocation Mode API
     * </pre>
     * @param poRcvInfoMap Map<String, Object>
     * @param svcMachMstrPk EZDPBigDecimalItem
     * @return result(OK:true, NG:false)
     */
    private boolean callAllocationModeApi(Map<String, Object> poRcvInfoMap, EZDPBigDecimalItem svcMachMstrPk) {

        NSZC001001PMsg pMsg = new NSZC001001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glParam.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, glParam.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ProcessMode.ALLOCATION_ON.code);
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, (String) poRcvInfoMap.get(NPZC131001Constant.CPO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.cpoDtlLineNum, (String) poRcvInfoMap.get(NPZC131001Constant.CPO_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.cpoDtlLineSubNum, (String) poRcvInfoMap.get(NPZC131001Constant.CPO_DTL_LINE_SUB_NUM));

        NSZC001001 dSZC001001 = new NSZC001001();
        dSZC001001.execute(pMsg, glOnBatchType);

        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                glMsgMap.addXxMsgId(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
            }
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Exec Intangible Process
     * </pre>
     * @return result(OK:true, NG:false)
     */
    private boolean executeIntangible() {

        boolean bReturn = true;

        for (int i = 0; i < glInTtangibleList.size(); i++) {
            NPZC131001_ordDtlInfoPMsg paramPoDtl = glInTtangibleList.get(i);

            // call Inventory Transaction Update API
            MDSETMsg mdseTMsg = glInTtangibleMdseList.get(i);
            if (ZYPConstant.FLG_ON_Y.equals(mdseTMsg.invtyValFlg.getValue())) {

                SHPG_PLNTMsg shpgPln = glInTtangibleShpgPlnList.get(i);

                if (!callInvTrnUpd(paramPoDtl, shpgPln)) {
                    bReturn = false;
                    break;
                }

            }

            Map<String, Object> poRcvInfoMap = new HashMap<String, Object>();
            poRcvInfoMap.put(NPZC131001Constant.PO_ORD_NUM, glParam.poOrdNum.getValue());
            poRcvInfoMap.put(NPZC131001Constant.MDSE_CD, paramPoDtl.mdseCd.getValue());
            poRcvInfoMap.put(NPZC131001Constant.PO_RCV_QTY, paramPoDtl.shipQty.getValue());
            poRcvInfoMap.put(NPZC131001Constant.PO_ORD_DTL_LINE_NUM, paramPoDtl.poOrdDtlLineNum.getValue());

            // call PO Status Update API
            if (!callPoUpdateApi(poRcvInfoMap, PO_STS.RECEIVING)) {
                bReturn = false;
                break;
            }

            if (!callPoUpdateApi(poRcvInfoMap, PO_STS.RECEIVING_COMPLETION)) {
                bReturn = false;
                break;
            }

        }

        return bReturn;
    }

    /**
     * <pre>
     * call Inventory Transaction Update API
     * </pre>
     * @return result(OK:true, NG:false)
     */
    private boolean callInvTrnUpd(NPZC131001_ordDtlInfoPMsg paramPoDtl, SHPG_PLNTMsg shpgPln) {

        List<NLZC002001PMsg> invtyUpdateApiParamList = new ArrayList<NLZC002001PMsg>();

        // get stdCcyCd by GLBL_CMPY_CD
        String stdCcyCd;
        GLBL_CMPYTMsg glblTMsg = new GLBL_CMPYTMsg();
        glblTMsg.glblCmpyCd.setValue(glParam.glblCmpyCd.getValue());
        glblTMsg = (GLBL_CMPYTMsg) S21ApiTBLAccessor.findByKey(glblTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(glblTMsg.getReturnCode())) {

            return false;

        } else {

            stdCcyCd = glblTMsg.stdCcyCd.getValue();
        }

        // Domestic Purchase Stock-In
        NLZC002001PMsg pmsg = new NLZC002001PMsg();

        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstTpCd, NLZC002001.RQST_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, glParam.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pmsg.trxCd, TRX.PURCHASE_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(pmsg.trxRsnCd, TRX_RSN.PURCHASE_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(pmsg.xxTrxDtlCd, NLZC002001.TRX_DTL_INTG_WITH_COST);
        ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, shpgPln.mdseCd);
        ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd, (String) glPoHead.get(NPZC131001Constant.INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(pmsg.stkStsCd, shpgPln.stkStsCd);
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstQty, paramPoDtl.shipQty);
        ZYPEZDItemValueSetter.setValue(pmsg.invtyTrxDt, ZYPDateUtil.getSalesDate(glParam.glblCmpyCd.getValue()));
        ZYPEZDItemValueSetter.setValue(pmsg.xxSysTp, NLZC002001.SYS_TP_OM);
        ZYPEZDItemValueSetter.setValue(pmsg.sysSrcCd, SYS_SRC.S21_ORDER);
        String soNum = ZYPExtnNumbering.getUniqueID(glParam.glblCmpyCd.getValue(), NPZC131001Constant.DUMMY_SO_NUM_FOR_INTG);
        ZYPEZDItemValueSetter.setValue(pmsg.soNum, soNum);
        ZYPEZDItemValueSetter.setValue(pmsg.soSlpNum, NPZC131001Constant.MIN_SO_SLP_NUM);
        ZYPEZDItemValueSetter.setValue(pmsg.bolNum, shpgPln.bolNum);
        ZYPEZDItemValueSetter.setValue(pmsg.carrCd, shpgPln.carrCd);
        ZYPEZDItemValueSetter.setValue(pmsg.proNum, shpgPln.proNum);
        ZYPEZDItemValueSetter.setValue(pmsg.cpoOrdNum, shpgPln.trxHdrNum);
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineNum, shpgPln.trxLineNum);
        ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineSubNum, shpgPln.trxLineSubNum);
        ZYPEZDItemValueSetter.setValue(pmsg.sellToCustCd, shpgPln.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(pmsg.billToCustCd, shpgPln.billToCustCd);
        ZYPEZDItemValueSetter.setValue(pmsg.shipToCustCd, shpgPln.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(pmsg.shipToCustNm, shpgPln.shipToLocNm);
        ZYPEZDItemValueSetter.setValue(pmsg.vndCd, (String) glPoHead.get(NPZC131001Constant.VND_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.tocCd, shpgPln.slsRepTocCd);
        ZYPEZDItemValueSetter.setValue(pmsg.ccyCd, stdCcyCd);
        ZYPEZDItemValueSetter.setValue(pmsg.uomCd, PKG_UOM.EACH);
        invtyUpdateApiParamList.add(pmsg);

        // Direct Sales Stock Out
        NLZC002001PMsg pmsgStkOut = new NLZC002001PMsg();
        EZDMsg.copy(pmsg, null, pmsgStkOut, null);
        ZYPEZDItemValueSetter.setValue(pmsgStkOut.xxRqstTpCd, NLZC002001.RQST_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(pmsgStkOut.trxCd, TRX.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(pmsgStkOut.trxRsnCd, TRX_RSN.DIRECT_SALE_STOCK_OUT_INTANGIBLE_WITH_COST);
        pmsgStkOut.xxTrxDtlCd.clear();
        ZYPEZDItemValueSetter.setValue(pmsgStkOut.shipFromLocCustCd, (String) glPoHead.get(NPZC131001Constant.INVTY_LOC_CD));
        pmsgStkOut.vndCd.clear();
        invtyUpdateApiParamList.add(pmsgStkOut);

        // Direct Sales Stock In
        NLZC002001PMsg pmsgStkIn = new NLZC002001PMsg();
        EZDMsg.copy(pmsgStkOut, null, pmsgStkIn, null);
        ZYPEZDItemValueSetter.setValue(pmsgStkIn.xxRqstTpCd, NLZC002001.RQST_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(pmsgStkIn.trxCd, TRX.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(pmsgStkIn.trxRsnCd, TRX_RSN.DIRECT_SALE_STOCK_IN_INTANGIBLE_WITH_COST);
        pmsgStkOut.xxTrxDtlCd.clear();
        ZYPEZDItemValueSetter.setValue(pmsgStkIn.locStsCd, LOC_STS.WAITING_FOR_INSTALLATION);
        ZYPEZDItemValueSetter.setValue(pmsgStkIn.shipFromLocCustCd, (String) glPoHead.get(NPZC131001Constant.INVTY_LOC_CD));
        invtyUpdateApiParamList.add(pmsgStkIn);

        NLZC002001 dLZC002001 = new NLZC002001();
        dLZC002001.execute(invtyUpdateApiParamList, glOnBatchType);

        if (invtyUpdateApiParamList.get(0).xxMsgIdList.getValidCount() > 0) {

            for (int i = 0; i < invtyUpdateApiParamList.get(0).xxMsgIdList.getValidCount(); i++) {
                glMsgMap.addXxMsgId(invtyUpdateApiParamList.get(0).xxMsgIdList.no(i).xxMsgId.getValue());

            }

            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Cancel process
     * </pre>
     * @return result(OK:true, NG:false)
     */
    private boolean executeCancel() {

        int cnt = 0;
        for (NPZC131001_ordDtlInfoPMsg paramPoDtl : glParamPoDtlList) {

            // call Cancel PO Update API
            if (!callCancelPoUpdateApi(paramPoDtl)) {
                return false;
            }

            // call Cancel Shipping Plan Update API
            Map<String, Object> poDetailInfo = glPoDetailList.get(cnt);
            if (!callCancelShippingPlanUpdateApi(poDetailInfo)) {
                return false;
            }

            cnt++;

        }

        return true;
    }

    /**
     * <pre>
     * call Cancel PO Update API
     * </pre>
     * @param paramPoDtl NPZC131001_ordDtlInfoPMsg
     * @return result(OK:true, NG:false)
     */
    private boolean callCancelPoUpdateApi(NPZC131001_ordDtlInfoPMsg paramPoDtl) {

        NPZC004001PMsg pMsg = new NPZC004001PMsg();

        // QC:52080 Start
        // Get PO Detail
        Map<String, Object> poDetailInfo = null;
        BigDecimal poRcvQty = null;

        poDetailInfo = getPoDetail(paramPoDtl);

        if (poDetailInfo == null) {
            glMsgMap.addXxMsgId(NPZC131001Constant.NPZM0156E);
            return false;
        }

        poRcvQty = (BigDecimal) poDetailInfo.get("PO_RCV_QTY");

        if (BigDecimal.ZERO.compareTo(poRcvQty) != 0) {

        	// Closed(Open For Invoice)
        	ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glParam.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum, glParam.poOrdNum);
            ZYPEZDItemValueSetter.setValue(pMsg.poStsCd, PO_STS.CLOSED);
            ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, paramPoDtl.mdseCd);
            ZYPEZDItemValueSetter.setValue(pMsg.poRcvQty, paramPoDtl.shipQty);
            ZYPEZDItemValueSetter.setValue(pMsg.poOrdDtlLineNum, paramPoDtl.poOrdDtlLineNum);

        } else {

        	// PO Line Cancel
        	ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glParam.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum, glParam.poOrdNum);
            ZYPEZDItemValueSetter.setValue(pMsg.poStsCd, PO_STS.CANCELLED);
            ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, paramPoDtl.mdseCd);
            ZYPEZDItemValueSetter.setValue(pMsg.poRcvQty, paramPoDtl.shipQty);
            ZYPEZDItemValueSetter.setValue(pMsg.poOrdDtlLineNum, paramPoDtl.poOrdDtlLineNum);
        }
        // QC:52080 End

        NPZC004001 dPZC004001 = new NPZC004001();
        dPZC004001.execute(pMsg, glOnBatchType);

        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                glMsgMap.addXxMsgId(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
            }
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * call Cancel Shipping Plan Update API
     * </pre>
     * @param poDetailInfo Map<String, Object>
     * @return result(OK:true, NG:false)
     */
    private boolean callCancelShippingPlanUpdateApi(Map<String, Object> poDetailInfo) {

        List<NWZC003001PMsg> pMsgList = new ArrayList<NWZC003001PMsg>();
        NWZC003001PMsg pMsg = new NWZC003001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glParam.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.shpgModeCd, SHPG_PLN_UPD_MODE.PO_CANCEL);
        ZYPEZDItemValueSetter.setValue(pMsg.shpgPlnNum, (String) poDetailInfo.get(NPZC131001Constant.SHPG_PLN_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, glParam.slsDt);
        pMsgList.add(pMsg);

        NWZC003001 dWZC003001 = new NWZC003001();
        dWZC003001.execute(pMsgList, glOnBatchType);

        if (pMsg.xxMsgIdList.getValidCount() > 0) {

            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                glMsgMap.addXxMsgId(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
            }

            return false;
        }

        return true;
    }

    /**
     * Override Comparator Class
     */
    public static class Comp implements Comparator<Object> {

        /** sortKey */
        private String sortKey;

        /**
         * Constructor
         * @param sortKey String
         */
        public Comp(String sortKey) {
            this.sortKey = sortKey;
        }

        /**
         * comparator
         * @param oPMsg1 Object
         * @param oPMsg2 Object
         * @return result int
         */
        @Override
        public int compare(Object oPMsg1, Object oPMsg2) {

            String ret1 = getValue(sortKey, oPMsg1);
            String ret2 = getValue(sortKey, oPMsg2);
            return ret1.compareTo(ret2);

        }

        private String getValue(String key, Object oPMsg) {

            try {

                Field field = oPMsg.getClass().getField(key);
                EZDPStringItem item = (EZDPStringItem) field.get(oPMsg);
                return item.getValue();

            } catch (Exception e) {

                return "";
            }
        }
    }
}
