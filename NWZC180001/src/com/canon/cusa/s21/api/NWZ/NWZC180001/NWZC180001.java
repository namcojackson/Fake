/**
 *  <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC180001;

import static com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant.ASTERISK;
import static com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant.DAY_LG;
import static com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant.FIRST_DAY;
import static com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant.MDSE_ITEM_CLS_TP_ALL;
import static com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant.MTH_LG;
import static com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant.NLZM2267E;
import static com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant.NWZC1800_DS_PHYS_WH;
import static com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant.NWZM0473E;
import static com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant.NWZM0482E;
import static com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant.NWZM0492E;
import static com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant.NWZM0514E;
import static com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant.NWZM0580E;
import static com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant.NWZM0977E;
import static com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant.NWZM1241E;
import static com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant.NWZM1244E;
import static com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant.NWZM1494E;
import static com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant.NWZM1496E;
import static com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant.NWZM1923E;
import static com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant.NWZM2276E;
import static com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant.NZZM0010E;
import static com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant.PROC_MODE_INBD;
import static com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant.PROC_MODE_OTBD;
import static com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant.UNDEF_MDL_ID;
import static com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant.VCK_DS_RTL_WH;
import static com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant.YR_LG;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDPMsg;
import parts.dbcommon.EZDDBRecordLockedException;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DEF_SWH_BY_ITEMTMsg;
import business.db.DEF_SWH_BY_ITEMTMsgArray;
import business.db.DS_MDLTMsg;
import business.db.MDSETMsg;
import business.db.RTRN_RSNTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.parts.NLZC215001PMsg;
import business.parts.NPZC113001PMsg;
import business.parts.NWZC180001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC215001.NLZC215001;
import com.canon.cusa.s21.api.NLZ.NLZC215001.Constant.NLZC215001Constant;
import com.canon.cusa.s21.api.NPZ.NPZC113001.NPZC113001;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

/**
 * <pre>
 * NWZC1800 : Default WH API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/15   Fujitsu         S.Yoshida       Create          N/A
 * 2016/01/27   Fujitsu         S.Takami        Update          S21_NA#3780
 * 2016/02/29   SRAA            K.Aratani       Update          QC2159, QC2163, QC4770
 * 2016/04/26   Fujitsu         Y.Kanefusa      Update          QC#7494
 * 2016/05/19   SRAA            K.Aratani       Update          QC#8596
 * 2016/08/24   SRAA            K.Aratani       Update          QC#13810
 * 2016/09/28   Fujitsu         S.Iidaka        Update          S21_NA#14226
 * 2016/09/29   SRAA            K.Aratani       Update          QC#14883
 * 2017/01/31   Fujitsu         R.Nakamura      Update          S21_NA#17186
 * 2017/09/08   CITS            T.Kikuhara      Update          QC#19805(L3)
 * 2017/09/26   CITS            T.Hakodate      Update          QC#19805(L3)
 * 2017/10/19   Fujitsu         H.Sugawara      Update          QC#21751
 * 2018/03/20   Fujitsu         K.Ishizuka      Update          QC#24595
 * 2018/04/18   CITS            K.Masaki        Update          QC#17857
 * 2018/05/11   CITS            K.Ogino         Update          QC#26039
 * 2018/06/11   Fujitsu         K.Ishizuka      Update          QC#24294
 * 2018/08/09   Fujitsu         K.Ishizuka      Update          QC#27677
 * 2018/08/17   Fujitsu         K.Ishizuka      Update          QC#27821
 * 2018/09/20   Fujitsu         S.Takami        Update          QC#28199
 * 2019/01/31   Fujitsu         C.Hara          Update          QC#30068
 * 2022/02/17   Fujitsu         C.Hara          Update          QC#59693
 *</pre>
 */
public class NWZC180001 extends S21ApiCommonBase {

    // -- Internal Variable ---------------
    /** SSM client */
    private S21SsmBatchClient ssmBatchClient;

    /** Drop Ship Warehouse Code */
    private String dropShipWHCd = null;

    //QC2163
    /** Drop Ship Physical Warehouse Code */
    private String dropShipPhysicalWHCd = null;

    // 2018/09/20 QC#28199 Add Start
    /** Overwrite With Base Component rtlWhCd flag */
    private boolean isOverWriteWithBase = false;
    // 2018/09/20 QC#28199 Add End
    /**

     */
    // 2022/02/17 QC#59693 Add Start
    /** Having Merchandise Default Source flag */
    private boolean haveMdseDefSrc = false;
    // 2022/02/17 QC#59693 Add End
    /**
     * <pre>
     * Constructor
     * </pre>
     */
    public NWZC180001() {
        super();
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * <pre>
     * Default WH API (List)
     * </pre>
     * @param inPrmMsgAry Input parameter list.
     * @param onBatchType Kind of Online or Batch.
     */
    public void execute(final List<NWZC180001PMsg> inPrmMsgAry, final ONBATCH_TYPE onBatchType) {
        // 2018/09/20 QC#28199 Add Start
        int baseCmptIdx = -1;
        for (int i = 0; i < inPrmMsgAry.size(); i++) {
            NWZC180001PMsg inPrmPMsg = inPrmMsgAry.get(i);
            if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, inPrmPMsg.baseCmptFlg.getValue())) {
                baseCmptIdx = i;
                inPrmPMsg.rtlWhCd_BC.clear();
                execute(inPrmPMsg, onBatchType);
                break;
            }
        }
        if (baseCmptIdx >= 0 //
                && inPrmMsgAry.get(baseCmptIdx).xxMsgIdList.getValidCount() == 0 //
                && ZYPCommonFunc.hasValue(inPrmMsgAry.get(baseCmptIdx).rtlWhCd)) {
            NWZC180001PMsg baseInMsg = inPrmMsgAry.get(baseCmptIdx);
            for (int i = 0; i < inPrmMsgAry.size(); i++) {
                if (i == baseCmptIdx) {
                    continue;
                }
                ZYPEZDItemValueSetter.setValue(inPrmMsgAry.get(i).rtlWhCd_BC, baseInMsg.rtlWhCd);
                ZYPEZDItemValueSetter.setValue(inPrmMsgAry.get(i).rtlSwhCd_BC, baseInMsg.rtlSwhCd);
                ZYPEZDItemValueSetter.setValue(inPrmMsgAry.get(i).ordLineSrcCd_BC, baseInMsg.ordLineSrcCd);
                ZYPEZDItemValueSetter.setValue(inPrmMsgAry.get(i).vndCd_BC, baseInMsg.vndCd);
                ZYPEZDItemValueSetter.setValue(inPrmMsgAry.get(i).thirdPtyDspTpCd_BC, baseInMsg.thirdPtyDspTpCd);
            }
        }
        // 2018/09/20 QC#28199 Add End
        for (int i = 0; i < inPrmMsgAry.size(); i++) {
            // 2018/09/20 QC#28199 Add Start
            if (i == baseCmptIdx) {
                continue;
            }
            // 2018/09/20 QC#28199 Add End
            execute(inPrmMsgAry.get(i), onBatchType);
        }
    }

    /**
     * <pre>
     * Default WH API
     * </pre>
     * @param inPrmPMsg Input parameter
     * @param onBatchType Kind of Online or Batch
     */
    public void execute(final NWZC180001PMsg inPrmPMsg, final ONBATCH_TYPE onBatchType) {

        // Create Message Map
        final S21ApiMessageMap msgMap = new S21ApiMessageMap(inPrmPMsg);

        try {

            // Parameter Check
            checkInPrm(msgMap);
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }

            String glblCmpyCd = inPrmPMsg.glblCmpyCd.getValue();
            dropShipWHCd = ZYPCodeDataUtil.getVarCharConstValue(VCK_DS_RTL_WH, glblCmpyCd);

            //QC2163 DROPSHIP
            dropShipPhysicalWHCd = ZYPCodeDataUtil.getVarCharConstValue(NWZC1800_DS_PHYS_WH, glblCmpyCd);
            if (dropShipPhysicalWHCd == null) dropShipPhysicalWHCd = "";

            // 2018/09/20 QC#28199 Add Start
            if (ZYPCommonFunc.hasValue(inPrmPMsg.rtlWhCd_BC) //
                    && NWXC150001DsCheck.isAvalableOverWriteBaseRtlWhCd(inPrmPMsg.glblCmpyCd.getValue(), inPrmPMsg.rtlWhCd_BC.getValue())) {
                this.isOverWriteWithBase = true;
            } else {
                this.isOverWriteWithBase = false;
            }
            // 2018/09/20 QC#28199 Add End
            String mode = inPrmPMsg.xxModeCd.getValue();
            if (PROC_MODE_OTBD.equals(mode)) {
                executeOtBdProcess(msgMap, onBatchType);
                // 2018/09/20 QC#28199 Add Start
                setBaseComponetRtlWhCd(inPrmPMsg);
                // 2018/09/20 QC#28199 Add End

            } else if (PROC_MODE_INBD.equals(mode)) {
                executeInBdProcess(msgMap, onBatchType);

            }

            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }


        // locked by another user
        } catch (EZDDBRecordLockedException e) {
            throw e;

        } finally {

            // Flush Message Map.
            msgMap.flush();
        }
    }

    /**
     * <pre>
     * Check Input Parameters
     * </pre>
     * @param msgMap S21ApiMessageMap
     */
    private void checkInPrm(S21ApiMessageMap msgMap) {

        NWZC180001PMsg inPrmPMsg = (NWZC180001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(inPrmPMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NWZM0473E);
        }

        if (!ZYPCommonFunc.hasValue(inPrmPMsg.xxModeCd)) {
            msgMap.addXxMsgId(NWZM0580E);
        }

        if (!ZYPCommonFunc.hasValue(inPrmPMsg.dsOrdCatgCd)) {
            msgMap.addXxMsgId(NWZM1494E);
        }

        if (!ZYPCommonFunc.hasValue(inPrmPMsg.dsOrdTpCd)) {
            msgMap.addXxMsgId(NWZM1496E);
        }

        if (!ZYPCommonFunc.hasValue(inPrmPMsg.postCd)) {
            msgMap.addXxMsgId(NWZM0514E);
        }

        String mode = inPrmPMsg.xxModeCd.getValue();
        if (PROC_MODE_OTBD.equals(mode)) {

            if (!ZYPCommonFunc.hasValue(inPrmPMsg.mdseCd)) {
                msgMap.addXxMsgId(NWZM0492E);
            }

        } else if (PROC_MODE_INBD.equals(mode)) {

            if (!ZYPCommonFunc.hasValue(inPrmPMsg.slsDt)) {
                msgMap.addXxMsgId(NWZM0482E);
            }

            if (!ZYPCommonFunc.hasValue(inPrmPMsg.dsRtrnRsnCd)) {
                msgMap.addXxMsgId(NWZM1244E);
            }

        } else {
            msgMap.addXxMsgId(NWZM0977E);
        }
    }

    /**
     * <pre>
     * Execute Out-Bound Process
     * </pre>
     * @param msgMap S21ApiMessageMap
     * @param onBatchType ONBATCH_TYPE
     */
    private void executeOtBdProcess(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
        NWZC180001PMsg inPrmPMsg = (NWZC180001PMsg) msgMap.getPmsg();

        // Get Item
        // 2018/06/11 S21_NA#24294 Mod Start
        // Map<String, String> itemMap = getItem(inPrmPMsg);
        MDSETMsg mdseTmsg = getItem(inPrmPMsg);
        // if (itemMap == null
        //         || itemMap.isEmpty()) {
        if( mdseTmsg == null){
            msgMap.addXxMsgIdWithPrm(NZZM0010E, new String[]{"MDSE"});
            return;
        }

        haveMdseDefSrc = false; // 2022/02/17 QC#59693 Add

        // if (ZYPCommonFunc.hasValue(itemMap.get("DEF_SRC_WH_CD"))) {
        if (ZYPCommonFunc.hasValue(mdseTmsg.defSrcWhCd)) {
            // ZYPEZDItemValueSetter.setValue(inPrmPMsg.rtlWhCd, itemMap.get("DEF_SRC_WH_CD"));
            // ZYPEZDItemValueSetter.setValue(inPrmPMsg.rtlSwhCd, itemMap.get("DEF_SRC_SUB_WH_CD"));
            ZYPEZDItemValueSetter.setValue(inPrmPMsg.rtlWhCd, mdseTmsg.defSrcWhCd);
            ZYPEZDItemValueSetter.setValue(inPrmPMsg.rtlSwhCd, mdseTmsg.defSrcSubWhCd);
            //QC#13810
            // String defSrcWhCd = (String) itemMap.get("DEF_SRC_WH_CD");
            String defSrcWhCd = mdseTmsg.defSrcWhCd.getValue();
            // 2018/06/11 S21_NA#24294 Mod End
            if (defSrcWhCd.equals(dropShipWHCd)) {
                // Get Vender
                NPZC113001PMsg getPrimVndPMsg = getPrimVnd(inPrmPMsg, onBatchType);
                if (getPrimVndPMsg.xxMsgIdList.getValidCount() > 0) {
                    addToMyMsgMap(msgMap, getPrimVndPMsg);
                    return;
                }
                String vndCd = getPrimVndPMsg.vndCd.getValue();
                // 2022/02/17 QC#59693 Del Start
                // inPrmPMsg.rtlSwhCd.clear();
                // ZYPEZDItemValueSetter.setValue(inPrmPMsg.ordLineSrcCd, getOrdLineSrc(inPrmPMsg, vndCd));
                // 2022/02/17 QC#59693 Del End
                inPrmPMsg.vndCd.setValue(vndCd);
            } else {
                // 2022/02/17 QC#59693 Del Start
                // inPrmPMsg.ordLineSrcCd.setValue(ORD_LINE_SRC.INTERNAL);
                // 2022/02/17 QC#59693 Del End
                inPrmPMsg.vndCd.clear();
            }
            // 2022/02/17 QC#59693 Add Start
            ZYPEZDItemValueSetter.setValue(inPrmPMsg.ordLineSrcCd, getOrdLineSrcFromProcrTpCd(inPrmPMsg, mdseTmsg.defSrcProcrTpCd.getValue()));
            haveMdseDefSrc = true;
            // 2022/02/17 QC#59693 Add End
            inPrmPMsg.thirdPtyDspTpCd.clear();
            return;
        }

        // Get Vender
        NPZC113001PMsg getPrimVndPMsg = getPrimVnd(inPrmPMsg, onBatchType);
        if (getPrimVndPMsg.xxMsgIdList.getValidCount() > 0) {
            addToMyMsgMap(msgMap, getPrimVndPMsg);
            return;
        }
        String vndCd = getPrimVndPMsg.vndCd.getValue();

        // 2018/06/11 S21_NA#24294 Mod Start
        // if (ZYPConstant.FLG_ON_Y.equals(itemMap.get("THIRD_PTY_VND_DROP_FLG"))) {
        if (ZYPConstant.FLG_ON_Y.equals(mdseTmsg.thirdPtyVndDropFlg.getValue())) {
        // 2018/06/11 S21_NA#24294 Mod End
            inPrmPMsg.rtlWhCd.setValue(dropShipWHCd);
            inPrmPMsg.rtlSwhCd.clear();
            ZYPEZDItemValueSetter.setValue(inPrmPMsg.ordLineSrcCd, getOrdLineSrc(inPrmPMsg, vndCd));
            inPrmPMsg.vndCd.setValue(vndCd);
            inPrmPMsg.thirdPtyDspTpCd.clear();
            return;
        }

        // Get Mapping Template
        Map<String, String> mpngTmplMap = getMpngTmpl(inPrmPMsg);
        if (mpngTmplMap == null
                || mpngTmplMap.isEmpty()) {
            inPrmPMsg.rtlWhCd.clear();
            inPrmPMsg.rtlSwhCd.clear();
            inPrmPMsg.ordLineSrcCd.clear();
            inPrmPMsg.vndCd.clear();
            inPrmPMsg.thirdPtyDspTpCd.clear();
            return;
        }

        // Default WH Setup
        String defWhMpngTmlCd = mpngTmplMap.get("FIRST_BIZ_CTX_ATTRB_TXT");
        //QC2159
        //Map<String, String> defWhMap = getDefWh(inPrmPMsg, defWhMpngTmlCd);
        // 2018/06/11 S21_NA#24294 Mod Start
        // Map<String, String> defWhMap = getDefWh(inPrmPMsg, defWhMpngTmlCd, (String) itemMap.get("THIRD_PTY_ITEM_FLG"));
        // Map<String, String> defWhMap = getDefWh(inPrmPMsg, defWhMpngTmlCd, mdseTmsg.thirdPtyVndDropFlg.getValue());
        Map<String, String> defWhMap = getDefWh(inPrmPMsg, defWhMpngTmlCd, mdseTmsg.thirdPtyItemFlg.getValue()); // 2018/08/17 S21_NA#27821 Mod
        // 2018/06/11 S21_NA#24294 Mod End
        if (defWhMap == null) {
            inPrmPMsg.rtlWhCd.clear();
            inPrmPMsg.rtlSwhCd.clear();
            inPrmPMsg.ordLineSrcCd.clear();
            inPrmPMsg.vndCd.clear();
            inPrmPMsg.thirdPtyDspTpCd.clear();

            if (isEquipment(inPrmPMsg)) {
                inPrmPMsg.rtlWhCd.setValue(dropShipWHCd);
                inPrmPMsg.ordLineSrcCd.setValue(ORD_LINE_SRC.ITT_DROP_SHIP);
                inPrmPMsg.vndCd.setValue(vndCd);
            }
            return;
        }

        String invtyOwnrCd = mpngTmplMap.get("SCD_BIZ_CTX_ATTRB_TXT");
        // Del Start 2017/10/19 QC#21751
        //String defWhMapTmplCd = mpngTmplMap.get("FIRST_BIZ_CTX_ATTRB_TXT");
        // Del End 2017/10/19 QC#21751

        if (ZYPConstant.FLG_OFF_N.equals(defWhMap.get("ON_HND_CHK_FLG"))) {

            // Convert WMS Code
            String physWhCd = defWhMap.get("OTBD_DEF_WH_CD");
            //QC4770
            if (physWhCd == null) {
            	//NWZM1923E=0,Outbound Default WH does not set up.
                msgMap.addXxMsgId(NWZM1923E);
                return;
            }
            //QC2163
            if (dropShipPhysicalWHCd.equals(physWhCd)) {
                inPrmPMsg.rtlWhCd.setValue(dropShipWHCd);
                inPrmPMsg.rtlSwhCd.clear();
                ZYPEZDItemValueSetter.setValue(inPrmPMsg.ordLineSrcCd, defWhMap.get("OTBD_DEF_LINE_SRC_CD"));
                // inPrmPMsg.vndCd.clear(); //QC#7494 2016/04/26 Mod
                inPrmPMsg.vndCd.setValue(vndCd);
                inPrmPMsg.thirdPtyDspTpCd.clear();
                return;
            }
            // Mod Start 2017/10/19 QC#21751
            //String rtlWhCd = getRtlWh(inPrmPMsg, physWhCd, invtyOwnrCd, defWhMapTmplCd);
            String rtlWhCd = getRtlWh(inPrmPMsg, physWhCd, invtyOwnrCd);
            // Mod End 2017/10/19 QC#21751
            if (rtlWhCd == null) {
                // 2018/08/09 S21_NA#27677 Mod Start
                // msgMap.addXxMsgIdWithPrm(NZZM0010E, new String[]{"RTL_WH"});
                msgMap.addXxMsgIdWithPrm(NWZM2276E, new String[]{physWhCd});
                // 2018/08/09 S21_NA#27677 Mod End
                return;
            }
            ZYPEZDItemValueSetter.setValue(inPrmPMsg.rtlWhCd, rtlWhCd);
            ZYPEZDItemValueSetter.setValue(inPrmPMsg.rtlSwhCd, defWhMap.get("OTBD_DEF_SWH_CD"));
            ZYPEZDItemValueSetter.setValue(inPrmPMsg.ordLineSrcCd, defWhMap.get("OTBD_DEF_LINE_SRC_CD"));
            inPrmPMsg.vndCd.clear();
            inPrmPMsg.thirdPtyDspTpCd.clear();
            return;
        }

        String[] physWhKeys = {"OTBD_PRIM_ON_HND_CHK_WH_CD","OTBD_SCD_ON_HND_CHK_WH_CD"};
        String[] rtlSubWhKeys = {"OTBD_PRIM_ON_HND_CHK_SWH_CD", "OTBD_SCD_ON_HND_CHK_SWH_CD"};
        String[] ordLineSrcKeys = {"OTBD_PRIM_ON_HND_LINE_SRC_CD","OTBD_SCD_ON_HND_LINE_SRC_CD"};

        // Primary and Secondary On Hand Check
        for (int i = 0; i < physWhKeys.length; i++) {

            // Convert WMS Code
            String physWhCd = defWhMap.get(physWhKeys[i]);
            //QC4770
            if (physWhCd == null) continue;
            //QC2163
            if (dropShipPhysicalWHCd.equals(physWhCd)) {
                inPrmPMsg.rtlWhCd.setValue(dropShipWHCd);
                inPrmPMsg.rtlSwhCd.clear();
                ZYPEZDItemValueSetter.setValue(inPrmPMsg.ordLineSrcCd, defWhMap.get(ordLineSrcKeys[i]));
                //inPrmPMsg.vndCd.clear();
                inPrmPMsg.vndCd.setValue(vndCd);
                inPrmPMsg.thirdPtyDspTpCd.clear();
                return;
            }
            // Mod Start 2017/10/19 QC#21751
            //String rtlWhCd = getRtlWh(inPrmPMsg, physWhCd, invtyOwnrCd, defWhMapTmplCd);
            String rtlWhCd = getRtlWh(inPrmPMsg, physWhCd, invtyOwnrCd);
            // Mod End 2017/10/19 QC#21751
            if (rtlWhCd == null) {
                msgMap.addXxMsgIdWithPrm(NZZM0010E, new String[]{"RTL_WH"});
                return;
            }

            // Get Sub Warehouse
            String rtlSubWhCd = defWhMap.get(rtlSubWhKeys[i]);
            if (!ZYPCommonFunc.hasValue(rtlSubWhCd)) {
                rtlSubWhCd = getRtlSubWh(inPrmPMsg, rtlWhCd);
            }

            // Check Inventory
            if (isAvalDcStk(inPrmPMsg, rtlWhCd, rtlSubWhCd)) {
                ZYPEZDItemValueSetter.setValue(inPrmPMsg.rtlWhCd, rtlWhCd);
                ZYPEZDItemValueSetter.setValue(inPrmPMsg.rtlSwhCd, defWhMap.get(rtlSubWhKeys[i]));
                ZYPEZDItemValueSetter.setValue(inPrmPMsg.ordLineSrcCd, defWhMap.get(ordLineSrcKeys[i]));
                inPrmPMsg.vndCd.clear();
                inPrmPMsg.thirdPtyDspTpCd.clear();
                return;
            }
        }

        // Default Warehouse
        // Convert WMS Code
        String physWhCd = defWhMap.get("OTBD_DEF_WH_CD");
        //QC4770
        if (physWhCd == null) {
        	//NWZM1923E=0,Outbound Default WH does not set up.
            msgMap.addXxMsgId(NWZM1923E);
            return;
        }
        //QC2163
        if (dropShipPhysicalWHCd.equals(physWhCd)) {
            inPrmPMsg.rtlWhCd.setValue(dropShipWHCd);
            inPrmPMsg.rtlSwhCd.clear();
            ZYPEZDItemValueSetter.setValue(inPrmPMsg.ordLineSrcCd, defWhMap.get("OTBD_DEF_LINE_SRC_CD"));
            // inPrmPMsg.vndCd.clear(); //QC#7494 2016/04/26 Mod
            inPrmPMsg.vndCd.setValue(vndCd);
            inPrmPMsg.thirdPtyDspTpCd.clear();
            return;
        }
        // Mod Start 2017/10/19 QC#21751
        //String rtlWhCd = getRtlWh(inPrmPMsg, physWhCd, invtyOwnrCd, defWhMapTmplCd);
        String rtlWhCd = getRtlWh(inPrmPMsg, physWhCd, invtyOwnrCd);
        // Mod End 2017/10/19 QC#21751
        if (rtlWhCd == null) {
            msgMap.addXxMsgIdWithPrm(NZZM0010E, new String[]{"RTL_WH"});
            return;
        }
        ZYPEZDItemValueSetter.setValue(inPrmPMsg.rtlWhCd, rtlWhCd);
        ZYPEZDItemValueSetter.setValue(inPrmPMsg.rtlSwhCd, defWhMap.get("OTBD_DEF_SWH_CD"));
        ZYPEZDItemValueSetter.setValue(inPrmPMsg.ordLineSrcCd, defWhMap.get("OTBD_DEF_LINE_SRC_CD"));
        inPrmPMsg.vndCd.clear();
        inPrmPMsg.thirdPtyDspTpCd.clear();

    }

    /**
     * <pre>
     * Execute In-Bound Process
     * </pre>
     * @param msgMap      S21ApiMessageMap
     * @param onBatchType ONBATCH_TYPE
     */
    private void executeInBdProcess(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
        NWZC180001PMsg inPrmPMsg = (NWZC180001PMsg) msgMap.getPmsg();

        //QC#19805(L3) UPD START

        // Get Mapping Template
        Map<String, String> mpngTmplMap = getMpngTmpl(inPrmPMsg);
        if (mpngTmplMap == null
                || mpngTmplMap.isEmpty()) {
            inPrmPMsg.rtlWhCd.clear();
            inPrmPMsg.rtlSwhCd.clear();
            inPrmPMsg.ordLineSrcCd.clear();
            inPrmPMsg.vndCd.clear();
            inPrmPMsg.thirdPtyDspTpCd.clear();
            return;
        }
        String defWhMapTmplCd = mpngTmplMap.get("FIRST_BIZ_CTX_ATTRB_TXT");
        if (!ZYPCommonFunc.hasValue(defWhMapTmplCd)) {
            msgMap.addXxMsgIdWithPrm(NZZM0010E, new String[]{"FIRST_BIZ_CTX_ATTRB_TXT"});
            return;
        }

        // Get Item
        // 2018/06/11 S21_NA#24294 Mod Start
        // Map<String, String> itemMap = getItem(inPrmPMsg);
        MDSETMsg mdseTmsg = getItem(inPrmPMsg);
        String thirdPtyItemFlg = null;
        String dropRtrnVndCd = null;
        String mdseCatgCd = null;
        String mdseItemActvDt = null;
        // if (itemMap != null && !itemMap.isEmpty()) {
        //     thirdPtyItemFlg = (String) itemMap.get("THIRD_PTY_ITEM_FLG");
        //     dropRtrnVndCd = (String) itemMap.get("DROP_RTRN_VND_CD");
        //     mdseCatgCd = (String) itemMap.get("MDSE_CATG_CD");
        //     mdseItemActvDt = (String) itemMap.get("MDSE_ITEM_ACTV_DT");
        // }
        if (mdseTmsg != null) {
            thirdPtyItemFlg = ZYPCommonFunc.hasValue(mdseTmsg.thirdPtyItemFlg) ? mdseTmsg.thirdPtyItemFlg.getValue() : "";
            dropRtrnVndCd = ZYPCommonFunc.hasValue(mdseTmsg.dropRtrnVndCd) ? mdseTmsg.dropRtrnVndCd.getValue() : "";
            mdseCatgCd = ZYPCommonFunc.hasValue(mdseTmsg.mdseCatgCd) ? mdseTmsg.mdseCatgCd.getValue() : "";
            mdseItemActvDt = ZYPCommonFunc.hasValue(mdseTmsg.mdseItemActvDt) ? mdseTmsg.mdseItemActvDt.getValue() : "";
        }
        // 2018/06/11 S21_NA#24294 Mod End

        // Get Inventory Owner from RTRN_RSN
        RTRN_RSNTMsg rtrnRsnTMsg = new RTRN_RSNTMsg();
        rtrnRsnTMsg.glblCmpyCd.setValue(inPrmPMsg.glblCmpyCd.getValue());
        rtrnRsnTMsg.rtrnRsnCd.setValue(inPrmPMsg.dsRtrnRsnCd.getValue());
        rtrnRsnTMsg = (RTRN_RSNTMsg) S21CodeTableAccessor.findByKey(rtrnRsnTMsg);
        if (rtrnRsnTMsg == null) {
            msgMap.addXxMsgId(NWZM1241E);
            return;
        }
        String invtyOwnrCd = rtrnRsnTMsg.invtyOwnrCd.getValue();
        if (!ZYPCommonFunc.hasValue(invtyOwnrCd)) {
            msgMap.addXxMsgIdWithPrm(NZZM0010E, new String[]{"INVTY_OWNR_CD (RTRN_RSN Table)"});
            return;
        }

        Map<String, String> defWhMap = null;
        if (ZYPCommonFunc.hasValue(dropRtrnVndCd)) {
            // get RMA_WH_CD by drop return vendor code.
            defWhMap = getRmaDefWh(inPrmPMsg, defWhMapTmplCd, thirdPtyItemFlg, dropRtrnVndCd, null);
        }

        //QC17857
        String thirdPtyDspTpCd = "";

        if (defWhMap == null) {
            // get RMA_WH_CD by third party disposition code.
            //QC17857
            thirdPtyDspTpCd = getThirdPtyDspTpCd(inPrmPMsg, mdseCatgCd, mdseItemActvDt, invtyOwnrCd);
            //String thirdPtyDspTpCd = getThirdPtyDspTpCd(inPrmPMsg, mdseCatgCd, mdseItemActvDt, invtyOwnrCd);
            if (thirdPtyDspTpCd == null) {
                msgMap.addXxMsgId(NLZM2267E);
                return;
            }
            defWhMap = getRmaDefWh(inPrmPMsg, defWhMapTmplCd, thirdPtyItemFlg, null, thirdPtyDspTpCd);
        }

        if (defWhMap == null) {
            inPrmPMsg.rtlWhCd.clear();
            inPrmPMsg.rtlSwhCd.clear();
            inPrmPMsg.ordLineSrcCd.clear();
            inPrmPMsg.vndCd.clear();
            inPrmPMsg.thirdPtyDspTpCd.clear();
            return;
        }

        // Convert WMS Code
        String physWhCd = defWhMap.get("RMA_DEF_WH_CD");
        if (!ZYPCommonFunc.hasValue(physWhCd)) {
            msgMap.addXxMsgIdWithPrm(NZZM0010E, new String[]{"RMA_DEF_WH_CD (ORD_DEF_WH Table)"});
            return;
        }
        // Mod Start 2017/10/19 QC#21751
        //String rtlWhCd = getRtlWh(inPrmPMsg, physWhCd, invtyOwnrCd, defWhMapTmplCd);
        String rtlWhCd = getRtlWh(inPrmPMsg, physWhCd, invtyOwnrCd);
        // Mod End 2017/10/19 QC#21751
        if (rtlWhCd == null) {
            // 2019/01/31 QC#30068 Mod Start
            // msgMap.addXxMsgIdWithPrm(NZZM0010E, new String[]{"RTL_WH"});
            msgMap.addXxMsgIdWithPrm(NWZM2276E, new String[]{physWhCd});
            // 2019/01/31 QC#30068 Mod End
            return;
        }

        // Get Sub WH
        NLZC215001PMsg defSubWhPMsg = getSubWh(inPrmPMsg, onBatchType, rtlWhCd);
        if (defSubWhPMsg.xxMsgIdList.getValidCount() > 0) {
            addToMyMsgMap(msgMap, defSubWhPMsg);
            return;
        }

        ZYPEZDItemValueSetter.setValue(inPrmPMsg.rtlWhCd, defSubWhPMsg.destRtlWhCd);
        ZYPEZDItemValueSetter.setValue(inPrmPMsg.rtlSwhCd, defSubWhPMsg.destRtlSwhCd);
        inPrmPMsg.vndCd.clear();
        //QC17857 Start
        if (ZYPCommonFunc.hasValue(defSubWhPMsg.thirdPtyDspTpCd)) {
            ZYPEZDItemValueSetter.setValue(inPrmPMsg.thirdPtyDspTpCd, defSubWhPMsg.thirdPtyDspTpCd);
        } else {
            ZYPEZDItemValueSetter.setValue(inPrmPMsg.thirdPtyDspTpCd, thirdPtyDspTpCd);
        }
        //QC17857 End

        //QC#19805(L3) UPD END
    }

    /**
     * <pre>
     * Get Item
     * </pre>
     * @param inPrmPMsg NWZC180001PMsg
     * @return Item Information
     */
    @SuppressWarnings("unchecked")
    // private Map<String, String> getItem(NWZC180001PMsg inPrmPMsg) {
    private MDSETMsg getItem(NWZC180001PMsg inPrmPMsg) {
        return NWXMdseTMsgThreadLocalCache.getInstance().get(inPrmPMsg.glblCmpyCd.getValue(), inPrmPMsg.mdseCd.getValue());
        // Map<String, String> param = new HashMap<String, String>();
        // param.put("glblCmpyCd", inPrmPMsg.glblCmpyCd.getValue());
        // param.put("mdseCd", inPrmPMsg.mdseCd.getValue());
        // return (Map<String, String>) ssmBatchClient.queryObject("getItem", param);
    }

    /**
     * <pre>
     * Is Equipment
     * </pre>
     * @param inPrmPMsg NWZC180001PMsg
     * @return Mapping Template
     */
    @SuppressWarnings("unchecked")
    private boolean isEquipment(NWZC180001PMsg inPrmPMsg) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", inPrmPMsg.glblCmpyCd.getValue());
        param.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP.RETAIL_EQUIPMENT_ORDERS);
        param.put("dsOrdCatgCd", inPrmPMsg.dsOrdCatgCd.getValue());
        param.put("dsOrdTpCd", inPrmPMsg.dsOrdTpCd.getValue());
        param.put("equipmentFlg", ZYPConstant.CHKBOX_ON_Y);
        BigDecimal cnt = ((Map<String, BigDecimal>) ssmBatchClient.queryObject("getMpngTmpl", param)).get("CNT");
//        return BigDecimal.ZERO.compareTo(cnt) > 0;
        return cnt.compareTo(BigDecimal.ZERO) > 0;
    }

    /**
     * <pre>
     * Get Mapping Template
     * </pre>
     * @param inPrmPMsg NWZC180001PMsg
     * @return Mapping Template
     */
    private Map<String, String> getMpngTmpl(NWZC180001PMsg inPrmPMsg) {
        // 2018/06/14 S21_NA#24294 Mod Start
        // Map<String, String> param = new HashMap<String, String>();
        // param.put("glblCmpyCd", inPrmPMsg.glblCmpyCd.getValue());
        // param.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP.DEFAULT_WAREHOUSE_TEMPLATE);
        // param.put("dsOrdCatgCd", inPrmPMsg.dsOrdCatgCd.getValue());
        // param.put("dsOrdTpCd", inPrmPMsg.dsOrdTpCd.getValue());
        // return (Map<String, String>) ssmBatchClient.queryObject("getMpngTmpl", param);
        return NWXC150001DsCheck.getWhMpngTmpl(inPrmPMsg.glblCmpyCd.getValue(), inPrmPMsg.dsOrdCatgCd.getValue(), inPrmPMsg.dsOrdTpCd.getValue());
        // 2018/06/14 S21_NA#24294 Mod End
    }

    /**
     * <pre>
     * Get Default Warehouse
     * </pre>
     * @param inPrmPMsg      NWZC180001PMsg
     * @param defWhMpngTmlCd Default Warehouse Mapping Template
     * @return Default Warehouse
     */
    @SuppressWarnings("unchecked")
    //QC2159
    //private Map<String, String> getDefWh(NWZC180001PMsg inPrmPMsg, String defWhMpngTmlCd) {
    private Map<String, String> getDefWh(NWZC180001PMsg inPrmPMsg, String defWhMpngTmlCd, String thirdPtyItemFlg) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", inPrmPMsg.glblCmpyCd.getValue());
        param.put("mdseCd", inPrmPMsg.mdseCd.getValue());
        //QC#8596
        //param.put("postCd", inPrmPMsg.postCd.getValue());
        param.put("postCd", cutOffString(inPrmPMsg.postCd.getValue(), 5));
        param.put("defWhMappingTmlCd", defWhMpngTmlCd);
        param.put("mdseItemClsTpCd_All", MDSE_ITEM_CLS_TP_ALL);
        //QC2159
        if (ZYPCommonFunc.hasValue(thirdPtyItemFlg)) {
	        param.put("thirdPtyItemFlg", thirdPtyItemFlg);
        }

        List<Map<String, String>> defWhList = (List<Map<String, String>>) ssmBatchClient.queryObjectList("getDefWh", param);
        if (defWhList == null
                || defWhList.isEmpty()) {
            return null;
        }

        if (defWhList.size() == 1) {
            return defWhList.get(0);
        }

        for (Map<String, String> defWhMap : defWhList) {
            if (MDSE_ITEM_CLS_TP_ALL.equals(defWhMap.get("MDSE_ITEM_CLS_TP_CD"))) {
                continue;
            }
            return defWhMap;
        }

        return null;
    }

    // Mod Start 2017/10/19 QC#21751
    ///**
    // * <pre>
    // * Get Retail Warehouse
    // * </pre>
    // * @param inPrmPMsg      NWZC180001PMsg
    // * @param physWhCd       Physical Warehouse Code
    // * @param invtyOwnrCd    Inventory Ownership Code
    // * @param defWhMapTmplCd Default Warehouse Mapping Template Code
    // * @return Retail Warehouse Code
    // */
    //@SuppressWarnings("unchecked")
    //private String getRtlWh(NWZC180001PMsg inPrmPMsg, String physWhCd, String invtyOwnrCd, String defWhMapTmplCd) {
    /**
     * <pre>
     * Get Retail Warehouse
     * </pre>
     * @param inPrmPMsg      NWZC180001PMsg
     * @param physWhCd       Physical Warehouse Code
     * @param invtyOwnrCd    Inventory Ownership Code
     * @return Retail Warehouse Code
     */
    @SuppressWarnings("unchecked")
    private String getRtlWh(NWZC180001PMsg inPrmPMsg, String physWhCd, String invtyOwnrCd) {
        // Mod End 2017/10/19 QC#21751
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", inPrmPMsg.glblCmpyCd.getValue());
        param.put("physWhCd", physWhCd);
        param.put("invtyOwnrCd", invtyOwnrCd);
        // Del Start 2017/10/19 QC#21751
        //param.put("defWhMapTmplCd", defWhMapTmplCd);
        // Del End 2017/10/19 QC#21751

        List<Map<String, String>> rtlWhList = (List<Map<String, String>>) ssmBatchClient.queryObjectList("getRtlWh", param);

        if (rtlWhList == null || rtlWhList.isEmpty()) {

            // QC#19805(L3) UPD T.Hakodate START
            // check Drop RMA RTL_WH_CD
            param.put("rtlWhCatgCd", RTL_WH_CATG.DROP_RMA);
            
            rtlWhList = (List<Map<String, String>>) ssmBatchClient.queryObjectList("rtlDropWhList", param);
            
            if (rtlWhList == null || rtlWhList.isEmpty()) {
                return null;
            }
            // QC#19805(L3) UPD T.Hakodate END
        }

        if (rtlWhList.size() == 1) {
            // 2019/01/31 QC#30068 Add Start
            if (ZYPDateUtil.compare(inPrmPMsg.slsDt.getValue(), rtlWhList.get(0).get("EFF_FROM_DT")) < 0) {
                return null;
            } else if (ZYPDateUtil.compare(inPrmPMsg.slsDt.getValue(), rtlWhList.get(0).get("EFF_THRU_DT")) > 0) {
                return null;
            }
            // 2019/01/31 QC#30068 Add End
            return rtlWhList.get(0).get("RTL_WH_CD");
        }

        if (rtlWhList.size() > 2) {
            return "";
        }

        boolean existsAllFlg = false;
        String rtlWhCd = "";
        for (Map<String, String> rtlWhMap : rtlWhList) {
            if (MDSE_ITEM_CLS_TP_ALL.equals(rtlWhMap.get("RTL_WH_CATG_CD"))) {
                existsAllFlg = true;
                continue;
            }
            rtlWhCd = rtlWhMap.get("RTL_WH_CD");
        }

        if (!existsAllFlg) {
            return "";
        }

        return rtlWhCd;
    }

    /**
     * <pre>
     * Get Retail Sub Warehouse
     * </pre>
     * @param inPrmPMsg NWZC180001PMsg
     * @param rtlWhCd   Retail Warehouse Code
     * @return Retail Sub Warehouse Code
     */
    private String getRtlSubWh(NWZC180001PMsg inPrmPMsg, String rtlWhCd) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", inPrmPMsg.glblCmpyCd.getValue());
        param.put("rtlWhCd", rtlWhCd);
        param.put("slsDt", inPrmPMsg.slsDt.getValue());
        return (String) ssmBatchClient.queryObject("getRtlSubWh", param);
    }

    /**
     * <pre>
     * Is Available DC Stock 
     * </pre>
     * @param inPrmPMsg  NWZC180001PMsg
     * @param rtlWhCd    Retail Warehouse Code
     * @param rtlSubWhCd Retail Sub Warehouse Code
     * @return result
     */
    private boolean isAvalDcStk(NWZC180001PMsg inPrmPMsg, String rtlWhCd, String rtlSubWhCd) {
        StringBuilder invtyLocCd = new StringBuilder(rtlWhCd);
        if (ZYPCommonFunc.hasValue(rtlSubWhCd)) {
            invtyLocCd = invtyLocCd.append(rtlSubWhCd);
        }

        //2016/09/28 S21_NA#14226 START
        String mdseCd = inPrmPMsg.mdseCd.getValue();
        if (ZYPCommonFunc.hasValue(mdseCd) && mdseCd.length() > 8) {
            mdseCd = mdseCd.substring(0, 8);
        }
        //2016/09/28 S21_NA#14226 END

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", inPrmPMsg.glblCmpyCd.getValue());
        param.put("mdseCd", mdseCd + "%");//2016/09/28 S21_NA#14226
        param.put("locStsCd", LOC_STS.DC_STOCK);
        param.put("stkStsCd", STK_STS.GOOD);
        param.put("invtyLocCd", invtyLocCd.toString());
        param.put("ordQty", inPrmPMsg.ordQty.getValue());

        return (Integer) ssmBatchClient.queryObject("isAvalDcStk", param) > 0;
    }

    /**
     * <pre>
     * Get Order Line Status
     * </pre>
     * @param inPrmPMsg NWZC180001PMsg
     * @param vndCd     Vendor Code
     * @return Order Line Status
     */
    private String getOrdLineSrc(NWZC180001PMsg inPrmPMsg, String vndCd) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", inPrmPMsg.glblCmpyCd.getValue());
        param.put("vndCd", vndCd);
        return (String) ssmBatchClient.queryObject("getOrdLineSrc", param);
    }

    /**
     * <pre>
     * Get Sub Warehouse
     * </pre>
     * @param inPrmPMsg   NWZC180001PMsg
     * @param onBatchType ONBATCH_TYPE
     * @param destRtlWhCd Destination Retail Warehouse Code
     * @return Sub Warehouse Information
     */
    private NLZC215001PMsg getSubWh(NWZC180001PMsg inPrmPMsg, ONBATCH_TYPE onBatchType, String destRtlWhCd) {
        NLZC215001PMsg defSubWhPMsg = new NLZC215001PMsg();
        defSubWhPMsg.glblCmpyCd.setValue(inPrmPMsg.glblCmpyCd.getValue());
        defSubWhPMsg.slsDt.setValue(inPrmPMsg.slsDt.getValue());
        defSubWhPMsg.xxModeCd.setValue(NLZC215001Constant.MODE_RMA);
        defSubWhPMsg.mdseCd.setValue(inPrmPMsg.mdseCd.getValue());
        defSubWhPMsg.dsOrdCatgCd.setValue(inPrmPMsg.dsOrdCatgCd.getValue());
        defSubWhPMsg.destRtlWhCd.setValue(destRtlWhCd);
        // Add Start 2017/01/31 QC#17186
        defSubWhPMsg.svcMachMstrPk.setValue(inPrmPMsg.svcMachMstrPk.getValue());
        // Add End 2017/01/31 QC#17186
        new NLZC215001().execute(defSubWhPMsg, onBatchType);
        return defSubWhPMsg;
    }

    /**
     * <pre>
     * Get Primary Vendor
     * </pre>
     * @param inPrmPMsg   NWZC180001PMsg
     * @param onBatchType ONBATCH_TYPE
     * @return Primary Vendor Information
     */
    private NPZC113001PMsg getPrimVnd(NWZC180001PMsg inPrmPMsg, ONBATCH_TYPE onBatchType) {
        NPZC113001PMsg getPrimVndPMsg = new NPZC113001PMsg();
        getPrimVndPMsg.glblCmpyCd.setValue(inPrmPMsg.glblCmpyCd.getValue());
        getPrimVndPMsg.slsDt.setValue(inPrmPMsg.slsDt.getValue());
        getPrimVndPMsg.mdseCd.setValue(inPrmPMsg.mdseCd.getValue());
        new NPZC113001().execute(getPrimVndPMsg, onBatchType);
        return getPrimVndPMsg;
    }

    /**
     * <pre>
     * Add to my message map
     * </pre>
     * @param msgMap S21ApiMessageMap
     * @param pMsg   EZDPMsg
     */
    private void addToMyMsgMap(S21ApiMessageMap msgMap, EZDPMsg pMsg) {
        for (S21ApiMessage msg : S21ApiUtil.getXxMsgList(pMsg)) {
            msgMap.addXxMsgIdWithPrm(msg.getXxMsgid(), msg.getXxMsgPrmArray());
        }
    }
    //QC#8596
    private String cutOffString(String val, int len) {
		if (ZYPCommonFunc.hasValue(val) && val.length() > len) {
			return val.substring(0, len);
		} else {
			return val;
		}
    }

    //QC#19805(L3) ADD START
    @SuppressWarnings("unchecked")
    private Map<String, String> getRmaDefWh(NWZC180001PMsg inPrmPMsg, String defWhMapTmplCd, String thirdPtyItemFlg, String dropRtrnVndCd, String thirdPtyDspTpCd) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", inPrmPMsg.glblCmpyCd.getValue());
        param.put("mdseCd", inPrmPMsg.mdseCd.getValue());
        param.put("postCd", cutOffString(inPrmPMsg.postCd.getValue(), 5));
        param.put("defWhMapTmplCd", defWhMapTmplCd);
        param.put("mdseItemClsTpCd_All", MDSE_ITEM_CLS_TP_ALL);
        if (ZYPCommonFunc.hasValue(thirdPtyItemFlg)) {
            param.put("thirdPtyItemFlg", thirdPtyItemFlg);
        }
        if (ZYPCommonFunc.hasValue(dropRtrnVndCd)) {
            param.put("dropRtrnVndCd", dropRtrnVndCd);
        }
        if (ZYPCommonFunc.hasValue(thirdPtyDspTpCd)) {
            param.put("thirdPtyDspTpCd", thirdPtyDspTpCd);
        }

        List<Map<String, String>> defWhList = (List<Map<String, String>>) ssmBatchClient.queryObjectList("getRmaDefWh", param);
        if (defWhList == null || defWhList.isEmpty()) {
            return null;
        }

        if (defWhList.size() == 1) {
            return defWhList.get(0);
        }

        for (Map<String, String> defWhMap : defWhList) {
            if (MDSE_ITEM_CLS_TP_ALL.equals(defWhMap.get("MDSE_ITEM_CLS_TP_CD"))) {
                continue;
            }
            return defWhMap;
        }

        return null;
    }

    private String getThirdPtyDspTpCd(NWZC180001PMsg pMsg, String mdseCatgCd, String mdseItemActvDt, String invtyOwnrCd) {

        // get third party disposition code by Item
        String thirdPtyDspTpCd = getThirdPtyDspTpCdByItem(pMsg, mdseCatgCd);

        if (!ZYPCommonFunc.hasValue(thirdPtyDspTpCd)) {
            // get third party disposition code by Model
            thirdPtyDspTpCd = getThirdPtyDspTpCdByMdl(pMsg, mdseItemActvDt, invtyOwnrCd);
            if (!ZYPCommonFunc.hasValue(thirdPtyDspTpCd)) {
                return null;
            }
        }

        return thirdPtyDspTpCd;
    }

    private String getThirdPtyDspTpCdByItem(NWZC180001PMsg pMsg, String mdseCatgCd) {
        String ThirdPtyDspTpCd = null;
        // 2018/03/20 S21_NA#24595 Mod Start
        // ThirdPtyDspTpCd = getThirdPtyDspTpCdByMdseAndOrdCatg(pMsg, ASTERISK);
        ThirdPtyDspTpCd = getThirdPtyDspTpCdByMdseAndOrdCatg(pMsg, mdseCatgCd, ASTERISK);
        // 2018/03/20 S21_NA#24595 Mod End
        if (!ZYPCommonFunc.hasValue(ThirdPtyDspTpCd)) {
            // 2018/03/20 S21_NA#24595 Mod Start
            // ThirdPtyDspTpCd = getThirdPtyDspTpCdByMdseAndOrdCatg(pMsg, mdseCatgCd);
            ThirdPtyDspTpCd = getThirdPtyDspTpCdByMdseAndOrdCatg(pMsg, mdseCatgCd, null);
            // 2018/03/20 S21_NA#24595 Mod End
        }
        return ThirdPtyDspTpCd;
    }

    // 2018/03/20 S21_NA#24595 Mod Start
    // private String getThirdPtyDspTpCdByMdseAndOrdCatg(NWZC180001PMsg pMsg, String mdseCatgCd) {
    private String getThirdPtyDspTpCdByMdseAndOrdCatg(NWZC180001PMsg pMsg, String mdseCatgCd, String dsOrdCatgCd) {
    // 2018/03/20 S21_NA#24595 Mod End
        final DEF_SWH_BY_ITEMTMsg condition = new DEF_SWH_BY_ITEMTMsg();
        condition.setSQLID("001");
        condition.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        condition.setConditionValue("mdseCatgCd01", mdseCatgCd);
        // 2018/03/20 S21_NA#24595 Mod Start
        // condition.setConditionValue("dsOrdCatgCd01", pMsg.dsOrdCatgCd.getValue());
        if (ZYPCommonFunc.hasValue(dsOrdCatgCd)) {
            condition.setConditionValue("dsOrdCatgCd01", dsOrdCatgCd);
        } else {
            condition.setConditionValue("dsOrdCatgCd01", pMsg.dsOrdCatgCd.getValue());
        }
        // 2018/03/20 S21_NA#24595 Mod End
        condition.setConditionValue("effFromDt01", pMsg.slsDt.getValue());
        condition.setConditionValue("effThruDt01", pMsg.slsDt.getValue());
        DEF_SWH_BY_ITEMTMsgArray tmsgArray = (DEF_SWH_BY_ITEMTMsgArray) EZDTBLAccessor.findByCondition(condition);
        if (tmsgArray.getValidCount() > 0) {
            return (String) tmsgArray.no(0).thirdPtyDspTpCd.getValue();
        }
        return null;
    }

    private String getThirdPtyDspTpCdByMdl(NWZC180001PMsg pMsg, String mdseItemActvDt, String invtyOwnrCd) {

        if (!ZYPCommonFunc.hasValue(pMsg.svcMachMstrPk)) {
            return getThirdPtyDspTpCdByMdlAge(pMsg, UNDEF_MDL_ID, mdseItemActvDt, invtyOwnrCd);
        }

        SVC_MACH_MSTRTMsg smmTMsg = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(smmTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(smmTMsg.svcMachMstrPk, pMsg.svcMachMstrPk);
        smmTMsg = (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(smmTMsg);

        if (smmTMsg != null) {
            BigDecimal mdlId = getMdlId(pMsg, smmTMsg.svcConfigMstrPk.getValue());
            if (!ZYPCommonFunc.hasValue(mdlId)) {
                // QC17857
                return getThirdPtyDspTpCdByMdlAge(pMsg, UNDEF_MDL_ID, smmTMsg.istlDt.getValue(), invtyOwnrCd);
                //return getThirdPtyDspTpCdByMdlAge(pMsg, UNDEF_MDL_ID, mdseItemActvDt, invtyOwnrCd);
            }

            if (ZYPConstant.FLG_OFF_N.equals(getMtrReqMdlFlg(pMsg, mdlId))) {
                // QC17857
                return getThirdPtyDspTpCdByMdlAge(pMsg, mdlId, smmTMsg.istlDt.getValue(), invtyOwnrCd);
                //return getThirdPtyDspTpCdByMdlAge(pMsg, mdlId, mdseItemActvDt, invtyOwnrCd);
            }

            return getThirdPtyDspTpCdByMtrCnt(pMsg, mdlId, invtyOwnrCd);
        } else {
            return getThirdPtyDspTpCdByMdlAge(pMsg, UNDEF_MDL_ID, mdseItemActvDt, invtyOwnrCd);
        }
    }

    private String getThirdPtyDspTpCdByMdlAge(NWZC180001PMsg pMsg, BigDecimal mdlId, String mdseItemActvDt, String invtyOwnrCd) {
        BigDecimal diffMonth = getDiffMonth(pMsg.slsDt.getValue(), mdseItemActvDt);
        String thirdPtyDspTpCd = getThirdPtyDspTpCdByElpsMthAotOfMdl(pMsg, mdlId, diffMonth, invtyOwnrCd);
        if (!ZYPCommonFunc.hasValue(thirdPtyDspTpCd)) {
            thirdPtyDspTpCd = getThirdPtyDspTpCdByElpsMthAotOfUnidMdl(pMsg, diffMonth, invtyOwnrCd);
        }
        return thirdPtyDspTpCd;
    }

    private String getThirdPtyDspTpCdByMtrCnt(NWZC180001PMsg pMsg, BigDecimal mdlId, String invtyOwnrCd) {
        BigDecimal mtrCnt = getMtrCnt(pMsg);
        String thirdPtyDspTpCd = getThirdPtyDspTpCdByMtrCntOfMdl(pMsg, mdlId, mtrCnt, invtyOwnrCd);
        if (!ZYPCommonFunc.hasValue(thirdPtyDspTpCd)) {
            thirdPtyDspTpCd = getThirdPtyDspTpCdByMtrCntOfUnidMdl(pMsg, mtrCnt, invtyOwnrCd);
        }
        return thirdPtyDspTpCd;
    }

    private BigDecimal getMtrCnt(NWZC180001PMsg pMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("svcMachMstrPk", pMsg.svcMachMstrPk.getValue());
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("flgN", ZYPConstant.FLG_OFF_N);
        BigDecimal mtrCnt = (BigDecimal) ssmBatchClient.queryObject("getMtrCntInfoList", ssmParam);
        if (mtrCnt == null) {
            return BigDecimal.ZERO;
        }
        return mtrCnt;
    }

    @SuppressWarnings("unchecked")
    private String getThirdPtyDspTpCdByElpsMthAotOfMdl(NWZC180001PMsg pMsg, BigDecimal mdlId, BigDecimal diffMonth, String invtyOwnrCd) {
        if (ZYPCommonFunc.hasValue(mdlId)) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
            ssmParam.put("mdlId", mdlId);
            ssmParam.put("fromElpsMthAot", diffMonth);
            ssmParam.put("toElpsMthAot", diffMonth);
            ssmParam.put("effFromDt", pMsg.slsDt.getValue());
            ssmParam.put("effThruDt", pMsg.slsDt.getValue());
            ssmParam.put("invtyOwnrCd", invtyOwnrCd);
            Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getThirdPtyDspTpCdByElpsMthAotOfMdl", ssmParam);
            if (result != null) {
                return (String) result.get("THIRD_PTY_DSP_TP_CD");
            } else {
                ssmParam.put("invtyOwnrCd", null);
                Map<String, Object> resultSec = (Map<String, Object>) ssmBatchClient.queryObject("getThirdPtyDspTpCdByElpsMthAotOfMdl", ssmParam);
                if (resultSec != null) {
                    return (String) resultSec.get("THIRD_PTY_DSP_TP_CD");
                }
            }
        }
        return null;
    }

    private String getThirdPtyDspTpCdByElpsMthAotOfUnidMdl(NWZC180001PMsg pMsg, BigDecimal diffMonth, String invtyOwnrCd) {
        // QC#26039
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("mdlId", UNDEF_MDL_ID);
        ssmParam.put("fromElpsMthAot", diffMonth);
        ssmParam.put("toElpsMthAot", diffMonth);
        ssmParam.put("effFromDt", pMsg.slsDt.getValue());
        ssmParam.put("effThruDt", pMsg.slsDt.getValue());
        ssmParam.put("invtyOwnrCd", invtyOwnrCd);
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getThirdPtyDspTpCdByElpsMthAotOfMdl", ssmParam);
        if (result != null) {
            return (String) result.get("THIRD_PTY_DSP_TP_CD");
        } else {
            ssmParam.put("invtyOwnrCd", null);
            Map<String, Object> resultSec = (Map<String, Object>) ssmBatchClient.queryObject("getThirdPtyDspTpCdByElpsMthAotOfMdl", ssmParam);
            if (resultSec != null) {
                return (String) resultSec.get("THIRD_PTY_DSP_TP_CD");
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private String getThirdPtyDspTpCdByMtrCntOfMdl(NWZC180001PMsg pMsg, BigDecimal mdlId, BigDecimal mtrCnt, String invtyOwnrCd) {
        if (ZYPCommonFunc.hasValue(mdlId)) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
            ssmParam.put("mdlId", mdlId);
            ssmParam.put("fromMtrCnt01", mtrCnt);
            ssmParam.put("toMtrCnt01", mtrCnt);
            ssmParam.put("effFromDt", pMsg.slsDt.getValue());
            ssmParam.put("effThruDt", pMsg.slsDt.getValue());
            ssmParam.put("invtyOwnrCd", invtyOwnrCd);
            Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getThirdPtyDspTpCdByMtrCntOfMdl", ssmParam);
            if (result != null) {
                return (String) result.get("THIRD_PTY_DSP_TP_CD");
            } else {
                ssmParam.put("invtyOwnrCd", null);
                Map<String, Object> resultSec = (Map<String, Object>) ssmBatchClient.queryObject("getThirdPtyDspTpCdByMtrCntOfMdl", ssmParam);
                if (resultSec != null) {
                    return (String) resultSec.get("THIRD_PTY_DSP_TP_CD");
                }
            }
        }
        return null;
    }

    private String getThirdPtyDspTpCdByMtrCntOfUnidMdl(NWZC180001PMsg pMsg, BigDecimal mtrCnt, String invtyOwnrCd) {
        // QC#26039
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("mdlId", UNDEF_MDL_ID);
        ssmParam.put("fromMtrCnt01", mtrCnt);
        ssmParam.put("toMtrCnt01", mtrCnt);
        ssmParam.put("effFromDt", pMsg.slsDt.getValue());
        ssmParam.put("effThruDt", pMsg.slsDt.getValue());
        ssmParam.put("invtyOwnrCd", invtyOwnrCd);
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getThirdPtyDspTpCdByMtrCntOfMdl", ssmParam);
        if (result != null) {
            return (String) result.get("THIRD_PTY_DSP_TP_CD");
        } else {
            ssmParam.put("invtyOwnrCd", null);
            Map<String, Object> resultSec = (Map<String, Object>) ssmBatchClient.queryObject("getThirdPtyDspTpCdByMtrCntOfMdl", ssmParam);
            if (resultSec != null) {
                return (String) resultSec.get("THIRD_PTY_DSP_TP_CD");
            }
        }
        return null;
    }

    private BigDecimal getDiffMonth(String slsDt, String actvDt) {
        int diffCount = 0;
        if (ZYPCommonFunc.hasValue(actvDt)) {
            Calendar slsDtCal = getCalInstance(slsDt);
            Calendar mdseEzIntimeCal = getCalInstance(actvDt);

            if (slsDtCal.before(mdseEzIntimeCal)) {
                while (slsDtCal.before(mdseEzIntimeCal)) {
                    slsDtCal.add(Calendar.MONTH, 1);
                    diffCount--;
                }
            } else {
                diffCount--;
                while (!slsDtCal.before(mdseEzIntimeCal)) {
                    slsDtCal.add(Calendar.MONTH, -1);
                    diffCount++;
                }
            }

            if (diffCount != 0) {
                int slsDay = Integer.parseInt(slsDt.substring(MTH_LG, DAY_LG));
                int actvDay = Integer.parseInt(actvDt.substring(MTH_LG, DAY_LG));
                if (slsDay < actvDay) {
                    return new BigDecimal(diffCount - 1);
                }
            }
        }

        return new BigDecimal(diffCount);
    }

    private Calendar getCalInstance(String trgtDt) {
        Calendar cal = Calendar.getInstance();
        cal.set(Integer.parseInt(trgtDt.substring(0, YR_LG)), Integer.parseInt(trgtDt.substring(YR_LG, MTH_LG)), FIRST_DAY, 0, 0, 0);
        cal.set(Calendar.MILLISECOND,0);
        return cal;
    }

    private BigDecimal getMdlId(NWZC180001PMsg pMsg, BigDecimal svcConfigMstrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        if (ZYPCommonFunc.hasValue(svcConfigMstrPk)) {
            param.put("svcConfigMstrPk", svcConfigMstrPk);
            param.put("svcMachTpCd", SVC_MACH_TP.MACHINE);
        } else {
            param.put("svcMachMstrPk", pMsg.svcMachMstrPk.getValue());
        }
        return (BigDecimal) ssmBatchClient.queryObject("getMdlId", param);
    }

    private String getMtrReqMdlFlg(NWZC180001PMsg pMsg, BigDecimal mdlId) {
        DS_MDLTMsg dsMdlTMsg = new DS_MDLTMsg();
        ZYPEZDItemValueSetter.setValue(dsMdlTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(dsMdlTMsg.mdlId, mdlId);
        dsMdlTMsg = (DS_MDLTMsg) S21ApiTBLAccessor.findByKey(dsMdlTMsg);
        if (dsMdlTMsg != null) {
            return dsMdlTMsg.mtrReqMdlFlg.getValue();
        }
        return ZYPConstant.FLG_OFF_N;
    }
    //QC#19805(L3) ADD END

    // 2018/09/20 QC#28199 Add Start
    private void setBaseComponetRtlWhCd(NWZC180001PMsg inPMsg) {

        if (!this.isOverWriteWithBase) {
            return;
        }

        // 2022/02/17 QC#59693 Add Start
        if (this.haveMdseDefSrc) {
            return;
        }
        // 2022/02/17 QC#59693 Add End

        if (inPMsg.xxMsgIdList.getValidCount() == 0 //
                && ZYPCommonFunc.hasValue(inPMsg.rtlWhCd_BC) //
                && NWXC150001DsCheck.isAvalableOverWriteRtlWhCd(inPMsg.glblCmpyCd.getValue(), inPMsg.rtlWhCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(inPMsg.rtlWhCd, inPMsg.rtlWhCd_BC);
//            ZYPEZDItemValueSetter.setValue(inPMsg.rtlSwhCd, inPMsg.rtlSwhCd_BC); 2018/09/20 QC#28199 (2)
            ZYPEZDItemValueSetter.setValue(inPMsg.ordLineSrcCd, inPMsg.ordLineSrcCd_BC);
            ZYPEZDItemValueSetter.setValue(inPMsg.vndCd, inPMsg.vndCd_BC);
            ZYPEZDItemValueSetter.setValue(inPMsg.thirdPtyDspTpCd, inPMsg.thirdPtyDspTpCd_BC);
        }
        return;
    }
    // 2018/09/20 QC#28199 Add End

    // 2022/02/17 QC#59693 Add Start
    /**
     * <pre>
     * Get Order Line Status From Procure Type Code
     * </pre>
     * @param inPrmPMsg NWZC180001PMsg
     * @param vndCd     defSrcProcrTpCd
     * @return Order Line Status
     */
    private String getOrdLineSrcFromProcrTpCd(NWZC180001PMsg inPrmPMsg, String procrTpCd) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", inPrmPMsg.glblCmpyCd.getValue());
        param.put("procrTpCd", procrTpCd);
        return (String) ssmBatchClient.queryObject("getOrdLineSrcFromProcrTpCd", param);
    }
    // 2022/02/17 QC#59693 Add End
}
