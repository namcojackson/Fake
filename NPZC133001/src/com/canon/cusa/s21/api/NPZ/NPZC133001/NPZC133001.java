/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */

package com.canon.cusa.s21.api.NPZ.NPZC133001;

import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.BIND_GLBL_CMPY_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.BIND_PO_MSG_SEG_ID;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.BIND_PO_MSG_TP_CD_RN;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.BIND_PO_MSG_TP_CD_SI;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.BIND_PO_MSG_TP_CD_SN;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.BIND_PO_ORD_NUM;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.BIND_PO_STS_CD_VALIDATED;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.COMMA;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.CR;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.DETAIL_REC_NUM_SIZE;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.ERR_GLBL_CMPY_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.ERR_INTERFACE_ID;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.ERR_PO_ORD_NUM;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.ERR_SEGMENT_ID;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.ERR_SEQ_NUMBER;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.ERR_TRANSACTION_ID;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.ERR_UNIT_ID;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.HEADER_REC_NUM_SIZE;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.LF;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.MSG_ID_NDAM0013E;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.MSG_ID_NPAM1236E;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.MSG_ID_NPAM1384E;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.MSG_ID_NPAM1391E;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.MSG_ID_NPAM1392E;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.MSG_ID_NPAM1393E;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.MSG_ID_NPAM1394E;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.MSG_ID_NPAM1395E;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.MSG_ID_NPAM1396E;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.MSG_ID_ZZZM9025E;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.PO_QLFY_CUT_LENGTH;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.EMPTY;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.SPACE;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.TRANSACTION_ID_MAX_LENGTH;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.BIND_RTL_WH_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.DEST_RTL_WH_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.BIND_PRCH_GRP_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.STAR;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.MD_VND_LOC_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.BIND_EFF_FROM_DT;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.BIND_EFF_THRU_DT;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.BIND_SLS_DT;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.BIND_VND_XREF_TP_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.EFF_FROM_DATE;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.EFF_THRU_DATE;
import static com.canon.cusa.s21.api.NPZ.NPZC133001.constant.NPZC133001Constant.VND_XREF_TP_CD_1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.NPAI2090_01TMsg;
import business.parts.NPZC133001PMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_QLFY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * Send PO API for Interface for AZERTY
 * 
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/03/2015   CITS            T.Hakodate      Create          N/A
 * 07/19/2016   CITS            N.Akaishi       Update          N/A
 * 10/18/2017   CITS            T.Kikuhara      Update          QC#20246(Sol#454)
 * 05/28/2018   CITS            K.Kameoka       UPDATE          QC#26074
 *</pre>
 */

public class NPZC133001 extends S21ApiCommonBase {

    /** SSM Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * slsdt.
     */
    private String slsdt = null;

    /**
     * constructor
     */
    public NPZC133001() {

        super();

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param param NPZC132001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NPZC133001PMsg param, final ONBATCH_TYPE onBatchType) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        if (!checkParam(msgMap, onBatchType)) {
            msgMap.flush();
            return;
        }

        // get send po data
        List<Map<String, Object>> sendPoList = sendPoInfo(msgMap, onBatchType);

        if (sendPoList == null || sendPoList.isEmpty()) {

            S21InfoLogOutput.printlnv(MSG_ID_NPAM1236E, param.poOrdNum.getValue());

            msgMap.addXxMsgId(MSG_ID_NPAM1391E);

            msgMap.flush();

            return;
        }

        // Header
        // define Record Number List
        String[] aryHeaderRecNo = new String[] {"0010", "0020", "0030", "0040", "0050", "0060", "0070", "1000", "1010", "1020", "1030", "1040", "1050", "1060", "1070", "1080", "1090", "1100", "1110", "1120", "1130", "1140", "1150", "1160",
                "1170", "1180", "1190", "1200", "1210", "1220", "1230", "1240", "1250", "1800", "1810" };

        // define Record Length List
        Map<String, String[]> poHeaderFormatLengthMap = new HashMap<String, String[]>(aryHeaderRecNo.length);
        poHeaderFormatLengthMap.put("0010", new String[] {"25", "22", "22", "22", "4", "2", "3", "2", "1", "6", "5", "2", "35", "30", "35", "74", "80", "80", "15", "15", "10" });
        poHeaderFormatLengthMap.put("0020", new String[] {"25", "22", "22", "22", "4", "2", "3", "30", "80", "80", "80", "80" });
        poHeaderFormatLengthMap.put("0030", new String[] {"25", "22", "22", "22", "4", "2", "3", "80", "80", "80", "80", "80" });
        poHeaderFormatLengthMap.put("0040", new String[] {"25", "22", "22", "22", "4", "2", "3", "80", "80", "80", "80", "80" });
        poHeaderFormatLengthMap.put("0050", new String[] {"25", "22", "22", "22", "4", "2", "3", "80" });
        poHeaderFormatLengthMap.put("0060", new String[] {"25", "22", "22", "22", "4", "2", "3", "80", "80", "80", "80", "80" });
        poHeaderFormatLengthMap.put("0070", new String[] {"25", "22", "22", "22", "4", "2", "3", "80" });
        poHeaderFormatLengthMap.put("1000", new String[] {"25", "22", "22", "22", "4", "2", "3", "40", "22", "15", "15", "22", "15", "80", "10" });
        poHeaderFormatLengthMap.put("1010", new String[] {"25", "22", "22", "22", "4", "2", "3", "50", "22", "1", "3", "3", "3", "15", "3", "22", "25", "20", "20", "80", "25", "10", "25", "10", "1", "1", "15", "1" });
        poHeaderFormatLengthMap.put("1020", new String[] {"25", "22", "22", "22", "4", "2", "3", "15", "15", "22", "30", "80" });
        poHeaderFormatLengthMap.put("1030", new String[] {"25", "22", "22", "22", "4", "2", "3", "80" });
        poHeaderFormatLengthMap.put("1040", new String[] {"25", "22", "22", "22", "4", "2", "3", "30", "80", "80", "80", "80" });
        poHeaderFormatLengthMap.put("1050", new String[] {"25", "22", "22", "22", "4", "2", "3", "80", "80", "80", "80", "80" });
        poHeaderFormatLengthMap.put("1060", new String[] {"25", "22", "22", "22", "4", "2", "3", "80", "80", "80", "80", "80" });
        poHeaderFormatLengthMap.put("1070", new String[] {"25", "22", "22", "22", "4", "2", "3", "80" });
        poHeaderFormatLengthMap.put("1080", new String[] {"25", "22", "22", "22", "4", "2", "3", "30", "80", "80", "80", "80" });
        poHeaderFormatLengthMap.put("1090", new String[] {"25", "22", "22", "22", "4", "2", "3", "80", "80", "80", "80", "80" });
        poHeaderFormatLengthMap.put("1100", new String[] {"25", "22", "22", "22", "4", "2", "3", "80", "80", "80", "80", "80" });
        poHeaderFormatLengthMap.put("1110", new String[] {"25", "22", "22", "22", "4", "2", "3", "80" });
        poHeaderFormatLengthMap.put("1120", new String[] {"25", "22", "22", "22", "4", "2", "3", "30", "80", "80", "80", "80" });
        poHeaderFormatLengthMap.put("1130", new String[] {"25", "22", "22", "22", "4", "2", "3", "80", "80", "80", "80", "80" });
        poHeaderFormatLengthMap.put("1140", new String[] {"25", "22", "22", "22", "4", "2", "3", "80", "80", "80", "80", "80" });
        poHeaderFormatLengthMap.put("1150", new String[] {"25", "22", "22", "22", "4", "2", "3", "80" });
        poHeaderFormatLengthMap.put("1160", new String[] {"25", "22", "22", "22", "4", "2", "3", "20", "22", "20", "60", "35", "35", "35", "35", "30", "15", "20", "3", "20", "10", "20", "10", "15" });
        poHeaderFormatLengthMap.put("1170", new String[] {"25", "22", "22", "22", "4", "2", "3", "50", "50", "10", "60", "10", "60", "10", "60" });
        poHeaderFormatLengthMap.put("1180", new String[] {"25", "22", "22", "22", "4", "2", "3", "50", "50", "10", "60" });
        poHeaderFormatLengthMap.put("1190", new String[] {"25", "22", "22", "22", "4", "2", "3", "20", "20", "60", "35", "35", "35", "35", "30", "15", "20", "3", "25", "10", "25", "10", "25", "10" });
        poHeaderFormatLengthMap.put("1200", new String[] {"25", "22", "22", "22", "4", "2", "3", "50", "50", "10", "60", "10", "60", "10", "60" });
        poHeaderFormatLengthMap.put("1210", new String[] {"25", "22", "22", "22", "4", "2", "3", "20", "20", "60", "35", "35", "35", "35", "30", "15", "3", "3", "30", "10", "30", "10", "30", "10" });
        poHeaderFormatLengthMap.put("1220", new String[] {"25", "22", "22", "22", "4", "2", "3", "50", "50", "10", "60", "10", "60", "10", "60" });
        poHeaderFormatLengthMap.put("1230", new String[] {"25", "22", "22", "22", "4", "2", "3", "50", "50", "240", "60" });
        poHeaderFormatLengthMap.put("1240", new String[] {"25", "22", "22", "22", "4", "2", "3", "60", "60", "60" });
        poHeaderFormatLengthMap.put("1250", new String[] {"25", "22", "22", "22", "4", "2", "3", "80", "80", "15", "80", "15", "15", "15", "15", "15" });
        poHeaderFormatLengthMap.put("1800", new String[] {"25", "22", "22", "22", "4", "2", "3", "3", "1", "255", "1" });
        poHeaderFormatLengthMap.put("1810", new String[] {"25", "22", "22", "22", "4", "2", "3", "7", "1", "400" });

        // Detail
        // define Record Number List
        String[] aryDetailRecNo = new String[] {"2000", "2010", "2020", "2030", "2040", "2050", "2060", "2070", "2080", "2090", "2100", "2110", "2120", "2130", "2800", "2810", "2820", "2830", "2840", "2850", "3000", "3010", "3020", "3030",
                "3040", "3050", "3060", "3800", "3810", "4000", "4010", "4020", "4030", "4040", "4050" };

        // define Record Length List
        Map<String, String[]> poDetailFormatLengthMap = new HashMap<String, String[]>(aryHeaderRecNo.length);
        poDetailFormatLengthMap.put("2000", new String[] {"25", "22", "22", "22", "4", "2", "3", "22", "22", "25", "2", "22", "3", "25", "22", "80", "20", "22", "25" });
        poDetailFormatLengthMap.put("2010", new String[] {"25", "22", "22", "22", "4", "2", "3", "1", "15", "22", "22", "22", "22", "22", "1", "1", "1", "25", "10", "25" });
        poDetailFormatLengthMap.put("2020", new String[] {"25", "22", "22", "22", "4", "2", "3", "40", "25", "80" });
        poDetailFormatLengthMap.put("2030", new String[] {"25", "22", "22", "22", "4", "2", "3", "80" });
        poDetailFormatLengthMap.put("2040", new String[] {"25", "22", "22", "22", "4", "2", "3", "30", "80", "80", "80", "80" });
        poDetailFormatLengthMap.put("2050", new String[] {"25", "22", "22", "22", "4", "2", "3", "80", "80", "80", "80", "80" });
        poDetailFormatLengthMap.put("2060", new String[] {"25", "22", "22", "22", "4", "2", "3", "80", "80", "80", "80", "80" });
        poDetailFormatLengthMap.put("2070", new String[] {"25", "22", "22", "22", "4", "2", "3", "80" });
        poDetailFormatLengthMap.put("2080", new String[] {"25", "22", "22", "22", "4", "2", "3", "80", "80", "80", "80", "80" });
        poDetailFormatLengthMap.put("2090", new String[] {"25", "22", "22", "22", "4", "2", "3", "80", "80", "80", "80", "80" });
        poDetailFormatLengthMap.put("2100", new String[] {"25", "22", "22", "22", "4", "2", "3", "80", "80", "80", "80", "80" });
        poDetailFormatLengthMap.put("2110", new String[] {"25", "22", "22", "22", "4", "2", "3", "80" });
        poDetailFormatLengthMap.put("2120", new String[] {"25", "22", "22", "22", "4", "2", "3", "40", "40", "40", "40", "40", "40", "40", "40", "40", "40" });
        poDetailFormatLengthMap.put("2130", new String[] {"25", "22", "22", "22", "4", "2", "3", "40", "40", "40", "40", "40", "40", "40", "40", "40", "40" });
        poDetailFormatLengthMap.put("2800", new String[] {"25", "22", "22", "22", "4", "2", "3", "3", "1", "255", "1" });
        poDetailFormatLengthMap.put("2810", new String[] {"25", "22", "22", "22", "4", "2", "3", "7", "1", "400" });
        poDetailFormatLengthMap.put("2820", new String[] {"25", "22", "22", "22", "4", "2", "3", "3", "1", "255", "1" });
        poDetailFormatLengthMap.put("2830", new String[] {"25", "22", "22", "22", "4", "2", "3", "7", "1", "400" });
        poDetailFormatLengthMap.put("2840", new String[] {"25", "22", "22", "22", "4", "2", "3", "3", "1", "255", "1" });
        poDetailFormatLengthMap.put("2850", new String[] {"25", "22", "22", "22", "4", "2", "3", "7", "1", "400" });
        poDetailFormatLengthMap.put("3000", new String[] {"25", "22", "22", "22", "4", "2", "3", "22", "22", "25", "2", "15", "15", "15", "22", "22", "22", "1", "15", "25", "20", "20", "80", "25", "2", "25", "3", "1" });
        poDetailFormatLengthMap.put("3010", new String[] {"25", "22", "22", "22", "4", "2", "3", "30", "80", "80", "80", "80" });
        poDetailFormatLengthMap.put("3020", new String[] {"25", "22", "22", "22", "4", "2", "3", "80", "80", "80", "80", "80" });
        poDetailFormatLengthMap.put("3030", new String[] {"25", "22", "22", "22", "4", "2", "3", "80", "80", "80", "80", "80" });
        poDetailFormatLengthMap.put("3040", new String[] {"25", "22", "22", "22", "4", "2", "3", "80" });
        poDetailFormatLengthMap.put("3050", new String[] {"25", "22", "22", "22", "4", "2", "3", "20", "20", "60", "35", "35", "35", "35", "30", "15", "3", "3", "30", "10", "30", "10", "30", "10" });
        poDetailFormatLengthMap.put("3060", new String[] {"25", "22", "22", "22", "4", "2", "3", "50", "50", "40", "10", "60", "10", "60", "10", "60" });
        poDetailFormatLengthMap.put("3800", new String[] {"25", "22", "22", "22", "4", "2", "3", "3", "1", "255", "1" });
        poDetailFormatLengthMap.put("3810", new String[] {"25", "22", "22", "22", "4", "2", "3", "7", "1", "400" });
        poDetailFormatLengthMap.put("4000", new String[] {"25", "22", "22", "22", "4", "2", "3", "4", "25", "20", "25", "22", "22", "22", "22", "22", "22", "15", "25" });
        poDetailFormatLengthMap.put("4010", new String[] {"25", "22", "22", "22", "4", "2", "3", "30", "150", "150", "150" });
        poDetailFormatLengthMap.put("4020", new String[] {"25", "22", "22", "22", "4", "2", "3", "150", "150", "150" });
        poDetailFormatLengthMap.put("4030", new String[] {"25", "22", "22", "22", "4", "2", "3", "150", "150", "150" });
        poDetailFormatLengthMap.put("4040", new String[] {"25", "22", "22", "22", "4", "2", "3", "150", "150", "150" });
        poDetailFormatLengthMap.put("4050", new String[] {"25", "22", "22", "22", "4", "2", "3", "150", "150", "150" });

        List<NPAI2090_01TMsg> insertHeaderTMsgList = new ArrayList<NPAI2090_01TMsg>(HEADER_REC_NUM_SIZE);
        List<NPAI2090_01TMsg> insertDetailTMsgList = new ArrayList<NPAI2090_01TMsg>(DETAIL_REC_NUM_SIZE);

        BigDecimal seqNumber = param.seqNumber.getValue();
        if (!sendPoList.isEmpty()) {

            Map<String, Object> headerPo = (Map<String, Object>) sendPoList.get(0);
            Map<String, Object[]> poHeaderMapValue = getPoHeaderIfValueMap(param, headerPo);

            // insert I/F data(Header)
            insertHeaderTMsgList.clear();
            for (String headerRecNo : aryHeaderRecNo) {

                // IF Header (1 : 0010 - 35 : 1810)
                String[] targetFormatLength = poHeaderFormatLengthMap.get(headerRecNo);
                Object[] targetValue = poHeaderMapValue.get(headerRecNo);

                NPAI2090_01TMsg headerTMsg = convNPAI2090(param, seqNumber, targetFormatLength, targetValue);
                insertHeaderTMsgList.add(headerTMsg);

                seqNumber = seqNumber.add(BigDecimal.ONE);
            }

            createIfNPAI2090(insertHeaderTMsgList);

            // insert I/F data(Detail)
            for (Map<String, Object> obj : sendPoList) {
                insertDetailTMsgList.clear();

                Map<String, Object> poDetail = (Map<String, Object>) obj;

                Map<String, Object[]> poDetailMapValue = getPoDetailIfValueMap(param, poDetail);

                // IF Detail (36 : 2000 - 70 4050)
                for (String detailRecNo : aryDetailRecNo) {

                    String[] targetFormatLength = poDetailFormatLengthMap.get(detailRecNo);
                    Object[] targetValue = poDetailMapValue.get(detailRecNo);

                    NPAI2090_01TMsg detailTMsg = convNPAI2090(param, seqNumber, targetFormatLength, targetValue);
                    insertDetailTMsgList.add(detailTMsg);

                    seqNumber = seqNumber.add(BigDecimal.ONE);
                }

                createIfNPAI2090(insertDetailTMsgList);
            }

        }

        msgMap.flush();
        return;

    }

    /**
     * Mandatory CHECK
     */
    private boolean checkParam(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        NPZC133001PMsg param = (NPZC133001PMsg) msgMap.getPmsg();

        boolean returnValue = true;

        // Global Company Code
        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            S21InfoLogOutput.println(MSG_ID_ZZZM9025E, new String[] {ERR_GLBL_CMPY_CD });
            msgMap.addXxMsgId(MSG_ID_NDAM0013E);
            returnValue = false;
        }

        // Internal Interface ID
        if (!ZYPCommonFunc.hasValue(param.interfaceId)) {
            S21InfoLogOutput.println(MSG_ID_ZZZM9025E, new String[] {ERR_INTERFACE_ID });
            msgMap.addXxMsgId(MSG_ID_NPAM1392E);
            returnValue = false;
        }

        // Internal Transaction ID
        if (!ZYPCommonFunc.hasValue(param.transactionId)) {
            S21InfoLogOutput.println(MSG_ID_ZZZM9025E, new String[] {ERR_TRANSACTION_ID });
            msgMap.addXxMsgId(MSG_ID_NPAM1393E);
            returnValue = false;
        }

        // Segment ID
        if (!ZYPCommonFunc.hasValue(param.segmentId)) {
            S21InfoLogOutput.println(MSG_ID_ZZZM9025E, new String[] {ERR_SEGMENT_ID });
            msgMap.addXxMsgId(MSG_ID_NPAM1394E);
            returnValue = false;
        }

        // Unit ID
        if (!ZYPCommonFunc.hasValue(param.unitId)) {
            S21InfoLogOutput.println(MSG_ID_ZZZM9025E, new String[] {ERR_UNIT_ID });
            msgMap.addXxMsgId(MSG_ID_NPAM1395E);
            returnValue = false;
        }

        // Sequence Number
        if (!ZYPCommonFunc.hasValue(param.seqNumber)) {
            S21InfoLogOutput.println(MSG_ID_ZZZM9025E, new String[] {ERR_SEQ_NUMBER });
            msgMap.addXxMsgId(MSG_ID_NPAM1396E);
            returnValue = false;
        }

        // PO Order Number
        if (!ZYPCommonFunc.hasValue(param.poOrdNum)) {
            S21InfoLogOutput.println(MSG_ID_ZZZM9025E, new String[] {ERR_PO_ORD_NUM });
            msgMap.addXxMsgId(MSG_ID_NPAM1384E);
            returnValue = false;
        }

        return returnValue;
    }

    /**
     * Convert I/F Value to TMsg
     */
    private NPAI2090_01TMsg convNPAI2090(NPZC133001PMsg param, BigDecimal seqNumber, String[] formatLength, Object[] valueList) {

        NPAI2090_01TMsg outTMsg = new NPAI2090_01TMsg();

        ZYPEZDItemValueSetter.setValue(outTMsg.interfaceId, param.interfaceId);
        ZYPEZDItemValueSetter.setValue(outTMsg.transactionId, param.transactionId);
        ZYPEZDItemValueSetter.setValue(outTMsg.segmentId, param.segmentId);
        ZYPEZDItemValueSetter.setValue(outTMsg.unitId, param.unitId);
        ZYPEZDItemValueSetter.setValue(outTMsg.seqNumber, seqNumber);

        StringBuilder format = new StringBuilder();

        for (int i = 0; i < valueList.length; i++) {

            if (valueList[i] == null) {
                valueList[i] = "";
            } else {
                int len = Integer.parseInt(formatLength[i]);
                if (valueList[i] instanceof String) {
                    valueList[i] = ZYPCommonFunc.toFixedLengthString((String) valueList[i], len);
                }
            }
        }

        for (Object length : formatLength) {
            format.append("%-").append(length).append("s");
        }

        ZYPEZDItemValueSetter.setValue(outTMsg.poOtbdIfDataTxt, String.format(format.toString(), valueList));

        return outTMsg;
    }

    /**
     * Set I/F Header Value
     */
    private Map<String, Object[]> getPoHeaderIfValueMap(NPZC133001PMsg param, Map<String, Object> headerPo) {

        Map<String, Object[]> poHeaderValueMap = new HashMap<String, Object[]>(HEADER_REC_NUM_SIZE);

        // RUN_ID(Record 0010)
        String strTransactionId = param.transactionId.getValue().toPlainString();
        String runId = new String(EMPTY);
        if (strTransactionId.length() > TRANSACTION_ID_MAX_LENGTH) {
            runId = strTransactionId.substring(strTransactionId.length() - TRANSACTION_ID_MAX_LENGTH);
        } else {
            runId = strTransactionId;
        }

        // PO
        String poOrdNum = (String) headerPo.get("PO_ORD_NUM");
        String poSubmitTs = (String) headerPo.get("PO_SUBMT_TS");
        String poOrdCmntTxt = (String) headerPo.get("PO_ORD_CMNT_TXT");
        if (ZYPCommonFunc.hasValue(poOrdCmntTxt)) {
            poOrdCmntTxt = ZYPCommonFunc.replaceAll(poOrdCmntTxt, CR, SPACE);
            poOrdCmntTxt = ZYPCommonFunc.replaceAll(poOrdCmntTxt, LF, SPACE);
        }

        // DS_PO_TP
        String poIntfcPoTpNm = (String) headerPo.get("PO_INTFC_PO_TP_NM");

        // DS_PO
        String vndPmtTermDescTxt = (String) headerPo.get("VND_PMT_TERM_DESC_TXT");
        String prntVndCd = (String) headerPo.get("PRNT_VND_CD");
        String prntVndNm = (String) headerPo.get("PRNT_VND_NM");
        String poQlfyCd = (String) headerPo.get("PO_QLFY_CD");
        String destRtlWhCd = (String) headerPo.get("DEST_RTL_WH_CD");
        String destRtlSwhCd = (String) headerPo.get("DEST_RTL_SWH_CD");
        String carrAcctNum = (String) headerPo.get("CARR_ACCT_NUM");

        // PO_DTL
        String ccyCd = (String) headerPo.get("CCY_CD");
        String carrCd = (String) headerPo.get("CARR_CD");
        String custIssPoNum = (String) headerPo.get("CUST_ISS_PO_NUM");

        String shipToLocNm = (String) headerPo.get("SHIP_TO_LOC_NM");
        String shipToFirstLineAddr = (String) headerPo.get("SHIP_TO_FIRST_LINE_ADDR");
        String shipToScdLineAddr = (String) headerPo.get("SHIP_TO_SCD_LINE_ADDR");
        String shipToThirdLineAddr = (String) headerPo.get("SHIP_TO_THIRD_LINE_ADDR");
        String shipToCityAddr = (String) headerPo.get("SHIP_TO_CTY_ADDR");
        String shipToPostCd = (String) headerPo.get("SHIP_TO_POST_CD");
        String shipToCtryCd = (String) headerPo.get("SHIP_TO_CTRY_CD");
        String shipToStCd = (String) headerPo.get("SHIP_TO_ST_CD");

        //QC#20246 ADD START
        String ctacPsnNm = (String) headerPo.get("CTAC_PSN_NM");
        String shipToFrthLineAddr = (String) headerPo.get("SHIP_TO_FRTH_LINE_ADDR");
        //QC#20246 ADD END

        // RTL_WH
        String rtlWhCd = (String) headerPo.get("RTL_WH_CD");
        String rtlWhNm = (String) headerPo.get("RTL_WH_NM");
        String rtlWhFirstLineAddr = (String) headerPo.get("RTL_WH_FIRST_LINE_ADDR");
        String rtlWhScdLineAddr = (String) headerPo.get("RTL_WH_SCD_LINE_ADDR");
        String rtlWhThirdLineAddr = (String) headerPo.get("RTL_WH_THIRD_LINE_ADDR");
        String rtlWhFrthLineAddr = (String) headerPo.get("RTL_WH_FRTH_LINE_ADDR");
        String rtlWhCtyAddr = (String) headerPo.get("RTL_WH_CTY_ADDR");
        String rtlWhStCd = (String) headerPo.get("RTL_WH_ST_CD");
        String rtlWhPostCd = (String) headerPo.get("RTL_WH_POST_CD");
        String rtlWhCtryCd = (String) headerPo.get("RTL_WH_CTRY_CD");

        // FRT_COND
        String frtChrgToCd = (String) headerPo.get("FRT_CHRG_TO_CD");

        // DS_SHIP_TO_CUST
        String bigDealNum = (String) headerPo.get("BIG_DEAL_NUM");

        // DS_PO_DTL
        String shipToAcctNm = (String) headerPo.get("SHIP_TO_ACCT_NM");

        // OTBD_CARR_V
        String carrNm = (String) headerPo.get("CARR_NM");

        // AUTH_PSN
        String lastNm = (String) headerPo.get("LAST_NM");
        String firstNm = (String) headerPo.get("FIRST_NM");

        // PO_MSG
        String poMsgSn = (String) headerPo.get("PO_MSG_TXT_SN");
        String poMsgSi = (String) headerPo.get("PO_MSG_TXT_SI");

        // PO_FREIGHT_TERMS (Record 1010)
        String frtChrgToNm = new String();
        if (ZYPCommonFunc.hasValue(frtChrgToCd)) {
            if ("1".equals(frtChrgToCd)) {
                frtChrgToNm = "CBS_STANDARD";
            } else if ("2".equals(frtChrgToCd)) {
                frtChrgToNm = "NC";
            }
        }

        // PO_ATTRIBUTE10 (Record 1060)
        String frtChrgToFullNm = new String();
        if (ZYPCommonFunc.hasValue(frtChrgToCd)) {
            if ("1".equals(frtChrgToCd)) {
                frtChrgToFullNm = "CBS Standard";
            } else if ("2".equals(frtChrgToCd)) {
                frtChrgToFullNm = "No Charge";
            }
        }

        if (ZYPCommonFunc.hasValue(vndPmtTermDescTxt)) {
            vndPmtTermDescTxt = vndPmtTermDescTxt.toUpperCase();
        }

        // set rtl wh address info
        StringBuilder rtlWhAddress = new StringBuilder();
        if (ZYPCommonFunc.hasValue(rtlWhCd)) {

            rtlWhAddress.append(rtlWhCd)
            .append(SPACE).append(rtlWhNm)
            .append(SPACE).append(rtlWhFirstLineAddr);

            if (ZYPCommonFunc.hasValue(rtlWhScdLineAddr)) {
                rtlWhAddress.append(SPACE).append(rtlWhScdLineAddr);
            }
            if (ZYPCommonFunc.hasValue(rtlWhThirdLineAddr)) {
                rtlWhAddress.append(SPACE).append(rtlWhThirdLineAddr);
            }
            if (ZYPCommonFunc.hasValue(rtlWhFrthLineAddr)) {
                rtlWhAddress.append(SPACE).append(rtlWhFrthLineAddr);
            }
            if (ZYPCommonFunc.hasValue(rtlWhCtyAddr)) {
                rtlWhAddress.append(SPACE).append(rtlWhCtyAddr);
            }
            rtlWhAddress.append(COMMA).append(SPACE).append(rtlWhStCd).append(SPACE).append(rtlWhPostCd).append(SPACE).append(rtlWhCtryCd);
        }

        // System TimeStamp
        String sysTimeStampStr = ZYPDateUtil.getCurrentSystemTime("yyyyMMdd HHmmss"); // TODO

        // System Date (time "000000")
        String sysDateStr = ZYPDateUtil.getCurrentSystemTime("yyyyMMdd 000000"); // TODO

        // PO_ATTRIBUTE14 (Record 1060)
        String poQlfy = new String("");

        //QC#26074 Add Start
        slsdt = ZYPDateUtil.getSalesDate(param.glblCmpyCd.getValue());
        
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
        ssmParam.put(BIND_VND_XREF_TP_CD, VND_XREF_TP_CD_1);
        ssmParam.put(BIND_EFF_FROM_DT, EFF_FROM_DATE);
        ssmParam.put(BIND_EFF_THRU_DT, EFF_THRU_DATE);
        ssmParam.put(BIND_SLS_DT, slsdt);
        
        ssmParam.put(BIND_RTL_WH_CD, headerPo.get(DEST_RTL_WH_CD));
        ssmParam.put(BIND_PRCH_GRP_CD, STAR);

        String vndLocCd = (String) ssmBatchClient.queryObject("searchVndShipToXrefRtl", ssmParam);
        //QC#26074 Add End
        
        if (ZYPCommonFunc.hasValue(poQlfyCd)) {
            if (PO_QLFY.DROPSHIP.equals(poQlfyCd)) {
                poQlfy = "DROPSHIP";
            } else {
                poQlfy = destRtlSwhCd;
            }
            poQlfy = ZYPCommonFunc.toFixedLengthString(poQlfy, PO_QLFY_CUT_LENGTH);
        }
        //QC#26074 Add Start
        //Set "DROPSHIP" for Manual Drop Ship
        else if(ZYPCommonFunc.hasValue(vndLocCd)){
            if(MD_VND_LOC_CD.equals(vndLocCd)){
                poQlfy = "DROPSHIP";
                poQlfy = ZYPCommonFunc.toFixedLengthString(poQlfy, PO_QLFY_CUT_LENGTH);
            }
        }
        //QC#26074 Add End
        else {
            poQlfy = ZYPCommonFunc.paddingSpace(poQlfy, false, PO_QLFY_CUT_LENGTH);
        }
        if (!ZYPCommonFunc.hasValue(carrAcctNum)) {
            carrAcctNum = "";
        }
        poQlfy = poQlfy + carrAcctNum;

        // VND
        String vndFirstLineAddr = (String) headerPo.get("VND_FIRST_LINE_ADDR");
        String vndCtyAddr = (String) headerPo.get("VND_CTY_ADDR");
        String vndPostCd = (String) headerPo.get("VND_POST_CD");
        String vndStCd = (String) headerPo.get("VND_ST_CD");

        // VAR_CHAR_CONST
        String npal0110BillToCd = ZYPCodeDataUtil.getVarCharConstValue("NPAL0110_BILL_TO_CD", param.glblCmpyCd.getValue());
        String npal0110BillToNm = ZYPCodeDataUtil.getVarCharConstValue("NPAL0110_BILL_TO_NM", param.glblCmpyCd.getValue());
        String npal0110BillToAddr1 = ZYPCodeDataUtil.getVarCharConstValue("NPAL0110_BILL_TO_ADDR1", param.glblCmpyCd.getValue());
        String npal0110BillToAddr2 = ZYPCodeDataUtil.getVarCharConstValue("NPAL0110_BILL_TO_ADDR2", param.glblCmpyCd.getValue());
        String npal0110BillToPostCd = ZYPCodeDataUtil.getVarCharConstValue("NPAL0110_BILL_TO_POST_CD", param.glblCmpyCd.getValue());
        String npal0110BillToCtryCd = ZYPCodeDataUtil.getVarCharConstValue("NPAL0110_BILL_TO_CTRY_CD", param.glblCmpyCd.getValue());
        String npal0110BillToCtyAddr = ZYPCodeDataUtil.getVarCharConstValue("NPAL0110_BILL_TO_CTY_ADDR", param.glblCmpyCd.getValue());
        String npal0110BillToStCd = ZYPCodeDataUtil.getVarCharConstValue("NPAL0110_BILL_ST_CD", param.glblCmpyCd.getValue());
        String npal0110BillToTelNum = ZYPCodeDataUtil.getVarCharConstValue("NPAL0110_BILL_TO_TEL_NUM", param.glblCmpyCd.getValue());

        // Set Header Record Data
        poHeaderValueMap.put("0010", new Object[] {"850CSAAZERTY", poOrdNum, "", "", "0010", "CT", "CTL", "ED", "P", "POO", "STAND", "", poOrdNum, "850CSAAZERTY", "AZERTYMERCH", "CBS AZERTY TRADING PARTNER PROFILE", "", "",
                sysTimeStampStr, runId, "ASC X12" });
        poHeaderValueMap.put("0020", new Object[] {"850CSAAZERTY", poOrdNum, "", "", "0020", "A1", "TH1", "", "", "", "", "" });
        poHeaderValueMap.put("0030", new Object[] {"850CSAAZERTY", poOrdNum, "", "", "0030", "A2", "TH2", "", "", "", "", "" });
        poHeaderValueMap.put("0040", new Object[] {"850CSAAZERTY", poOrdNum, "", "", "0040", "A2", "TH3", "", "", "", "", "" });
        poHeaderValueMap.put("0050", new Object[] {"850CSAAZERTY", poOrdNum, "", "", "0050", "A2", "TH4", "" });
        poHeaderValueMap.put("0060", new Object[] {"850CSAAZERTY", poOrdNum, "", "", "0060", "A1", "TD1", "", "", "", "", "" });
        poHeaderValueMap.put("0070", new Object[] {"850CSAAZERTY", poOrdNum, "", "", "0070", "A2", "TD2", "" });
        poHeaderValueMap.put("1000", new Object[] {"850CSAAZERTY", poOrdNum, "", "", "1000", "PO", "PO1", poOrdNum, "0", "", poSubmitTs, "0", "", poOrdCmntTxt, poIntfcPoTpNm });
        poHeaderValueMap.put("1010", new Object[] {"850CSAAZERTY", poOrdNum, "", "", "1010", "PO", "PO2", vndPmtTermDescTxt, vndPmtTermDescTxt, "", "", "", "", ccyCd, ccyCd, "", carrNm, carrCd, "", "", "", "", frtChrgToNm, frtChrgToNm,
                "N", "N", "", "N" });
        poHeaderValueMap.put("1020", new Object[] {"850CSAAZERTY", poOrdNum, "", "", "1020", "PO", "PO3", sysDateStr, "", "", prntVndCd, prntVndNm });
        poHeaderValueMap.put("1030", new Object[] {"850CSAAZERTY", poOrdNum, "", "", "1030", "PO", "PO3", poMsgSn });
        poHeaderValueMap.put("1040", new Object[] {"850CSAAZERTY", poOrdNum, "", "", "1040", "A1", "PO1", "THIRDPARTY", "", "", "", "" });
        poHeaderValueMap.put("1050", new Object[] {"850CSAAZERTY", poOrdNum, "", "", "1050", "A2", "PO2", "", "", "", bigDealNum, custIssPoNum });
        
        // QC#16826 
        if (!ZYPCommonFunc.hasValue(carrNm)) {
            carrNm = "";
        }
        if (!ZYPCommonFunc.hasValue(frtChrgToFullNm)) {
            frtChrgToFullNm = "";
        }
        poHeaderValueMap.put("1060", new Object[] {"850CSAAZERTY", poOrdNum, "", "", "1060", "A2", "PO3", carrNm + " " + frtChrgToFullNm, poMsgSi, rtlWhAddress.toString(), "", poQlfy });

        poHeaderValueMap.put("1070", new Object[] {"850CSAAZERTY", poOrdNum, "", "", "1070", "A2", "PO4", shipToAcctNm });
        poHeaderValueMap.put("1080", new Object[] {"850CSAAZERTY", poOrdNum, "", "", "1080", "A1", "SU1", "", "", "", "", "" });
        poHeaderValueMap.put("1090", new Object[] {"850CSAAZERTY", poOrdNum, "", "", "1090", "A2", "SU2", "", "", "", "", "" });
        poHeaderValueMap.put("1100", new Object[] {"850CSAAZERTY", poOrdNum, "", "", "1100", "A2", "SU3", "", "", "", "", "" });
        poHeaderValueMap.put("1110", new Object[] {"850CSAAZERTY", poOrdNum, "", "", "1110", "A2", "SU4", "" });
        poHeaderValueMap.put("1120", new Object[] {"850CSAAZERTY", poOrdNum, "", "", "1120", "A1", "SS1", "", "952418", "000", "", "" });
        poHeaderValueMap.put("1130", new Object[] {"850CSAAZERTY", poOrdNum, "", "", "1130", "A2", "SS2", "", "", "", "", "" });
        poHeaderValueMap.put("1140", new Object[] {"850CSAAZERTY", poOrdNum, "", "", "1140", "A2", "SS3", "", "", "", "", "" });
        poHeaderValueMap.put("1150", new Object[] {"850CSAAZERTY", poOrdNum, "", "", "1150", "A2", "SS4", "" });
        poHeaderValueMap.put("1160",
                new Object[] {"850CSAAZERTY", poOrdNum, "", "", "1160", "AD", "SU1", "", "AZERTYMERCH", "952418", "", vndFirstLineAddr, "", "", "", vndCtyAddr, vndPostCd, "", "", vndStCd, vndStCd, "", "", "AZERTYMERCH" });
        poHeaderValueMap.put("1170", new Object[] {"850CSAAZERTY", poOrdNum, "", "", "1170", "CN", "SS1", "", "", "", "", "", "", "", "" });
        poHeaderValueMap.put("1180", new Object[] {"850CSAAZERTY", poOrdNum, "", "", "1180", "CN", "SS2", "", "", "", "" });
        //QC#20246 UPD START
        poHeaderValueMap.put("1190", new Object[] {"850CSAAZERTY", poOrdNum, "", "", "1190", "AX", "ST1", destRtlWhCd, "", shipToLocNm, shipToFirstLineAddr, shipToScdLineAddr, shipToThirdLineAddr, shipToFrthLineAddr, shipToCityAddr, shipToPostCd,
                shipToCtryCd, shipToCtryCd, "", "", shipToStCd, shipToStCd, "", "" });
        poHeaderValueMap.put("1200", new Object[] {"850CSAAZERTY", poOrdNum, "", "", "1200", "CN", "ST1", ctacPsnNm, "", "", "", "", "", "", "" });
        //QC#20246 UPD END
        poHeaderValueMap.put("1210", new Object[] {"850CSAAZERTY", poOrdNum, "", "", "1210", "AX", "BT1", npal0110BillToCd, "", npal0110BillToNm, npal0110BillToAddr1, npal0110BillToAddr2, "", "", npal0110BillToCtyAddr,
                npal0110BillToPostCd, npal0110BillToCtryCd, npal0110BillToCtryCd, "", "", npal0110BillToStCd, npal0110BillToStCd, "", "" });
        poHeaderValueMap.put("1220", new Object[] {"850CSAAZERTY", poOrdNum, "", "", "1220", "CN", "BT1", "", "", "", npal0110BillToTelNum, "", "", "", "" });
        poHeaderValueMap.put("1230", new Object[] {"850CSAAZERTY", poOrdNum, "", "", "1230", "PO", "PO4", lastNm, firstNm, "", npal0110BillToTelNum });
        poHeaderValueMap.put("1240", new Object[] {"850CSAAZERTY", poOrdNum, "", "", "1240", "PO", "PO5", "", "", "" });
        poHeaderValueMap.put("1250", new Object[] {"850CSAAZERTY", poOrdNum, "", "", "1250", "PR", "CRD", "", "", "", "", "", "", "", "", "" });
        poHeaderValueMap.put("1800", new Object[] {"850CSAAZERTY", poOrdNum, "", "", "1800", "AT", "HAH", "", "", "", "" });
        poHeaderValueMap.put("1810", new Object[] {"850CSAAZERTY", poOrdNum, "", "", "1810", "AT", "HAD", "", "", "" });

        return poHeaderValueMap;
    }

    /**
     * Set I/F Detail Value
     */
    private Map<String, Object[]> getPoDetailIfValueMap(NPZC133001PMsg param, Map<String, Object> detailPo) {

        Map<String, Object[]> poDetailValueMap = new HashMap<String, Object[]>(DETAIL_REC_NUM_SIZE);

        // PO
        String poOrdNum = (String) detailPo.get("PO_ORD_NUM");

        // DS_PO
        String rqstRcvDt = (String) detailPo.get("RQST_RCV_DT");
        String rqstRcvTm = (String) detailPo.get("RQST_RCV_TM");
        if (!ZYPCommonFunc.hasValue(rqstRcvTm)) {
            rqstRcvTm = "000000";
        }

        // PO_DTL
        int poOrdDtlLineNum = Integer.parseInt((String) detailPo.get("PO_ORD_DTL_LINE_NUM"));
        String mdseCd = (String) detailPo.get("MDSE_CD");
        String shipToCustCd = (String) detailPo.get("SHIP_TO_CUST_CD");

        String shipToFirstLineAddr = (String) detailPo.get("SHIP_TO_FIRST_LINE_ADDR");
        String shipToScdLineAddr = (String) detailPo.get("SHIP_TO_SCD_LINE_ADDR");
        String shipToCityAddr = (String) detailPo.get("SHIP_TO_CTY_ADDR");
        String shipToPostCd = (String) detailPo.get("SHIP_TO_POST_CD");
        String shipToCtryCd = (String) detailPo.get("SHIP_TO_CTRY_CD");
        String shipToStCd = (String) detailPo.get("SHIP_TO_ST_CD");

        //QC#20246 ADD START
        String ctacPsnNm = (String) detailPo.get("CTAC_PSN_NM");
        String shipToThirdLineAddr = (String) detailPo.get("SHIP_TO_THIRD_LINE_ADDR");
        String shipToFrthLineAddr = (String) detailPo.get("SHIP_TO_FRTH_LINE_ADDR");
        //QC#20246 ADD END

        // DS_PO_DTL
        BigDecimal poDispQty = (BigDecimal) detailPo.get("PO_DISP_QTY");
        String poDispQtyStr = poDispQty.toPlainString();
        String poDispUomCd = (String) detailPo.get("PO_DISP_UOM_CD");
        String aslMdseCd = (String) detailPo.get("ASL_MDSE_CD");
        BigDecimal entDealNetUnitPrcAmt = (BigDecimal) detailPo.get("ENT_DEAL_NET_UNIT_PRC_AMT");
        String mdseDescShortText = (String) detailPo.get("MDSE_DESC_SHORT_TXT");
        String shipToAcctNm = (String) detailPo.get("SHIP_TO_ACCT_NM");

        // PO_MSG
        String poMsgRn = (String) detailPo.get("PO_MSG_TXT_RN");

        // VND_UOM
        String vndUomNm = (String) detailPo.get("VND_UOM_NM");
        if (ZYPCommonFunc.hasValue(vndUomNm)) {
            vndUomNm = vndUomNm.toUpperCase();
        }

        // Set Detail Record Data
        poDetailValueMap.put("2000", new Object[] {"850CSAAZERTY", poOrdNum, poOrdDtlLineNum, "", "2000", "IT", "IT1", poOrdDtlLineNum, poDispQtyStr, vndUomNm, poDispUomCd, mdseCd, "", aslMdseCd, entDealNetUnitPrcAmt, mdseDescShortText, "",
                "", "" });
        poDetailValueMap.put("2010", new Object[] {"850CSAAZERTY", poOrdNum, poOrdDtlLineNum, "", "2010", "IT", "IT2", "N", "", poDispQtyStr, "", entDealNetUnitPrcAmt, "", "", "N", "N", "N", "", "", "QUANTITY" });
        poDetailValueMap.put("2020", new Object[] {"850CSAAZERTY", poOrdNum, poOrdDtlLineNum, "", "2020", "IT", "IT3", "", "", "" });
        poDetailValueMap.put("2030", new Object[] {"850CSAAZERTY", poOrdNum, poOrdDtlLineNum, "", "2030", "IT", "IT4", poMsgRn });
        poDetailValueMap.put("2040", new Object[] {"850CSAAZERTY", poOrdNum, poOrdDtlLineNum, "", "2040", "A1", "LN1", "", "", "", "", "" });
        poDetailValueMap.put("2050", new Object[] {"850CSAAZERTY", poOrdNum, poOrdDtlLineNum, "", "2050", "A2", "LN2", "", "", "", "", "" });
        poDetailValueMap.put("2060", new Object[] {"850CSAAZERTY", poOrdNum, poOrdDtlLineNum, "", "2060", "A2", "LN3", "", "", "", "", "" });
        poDetailValueMap.put("2070", new Object[] {"850CSAAZERTY", poOrdNum, poOrdDtlLineNum, "", "2070", "A2", "LN4", "" });
        poDetailValueMap.put("2080", new Object[] {"850CSAAZERTY", poOrdNum, poOrdDtlLineNum, "", "2080", "A1", "LP1", "", "", "", "", "" });
        poDetailValueMap.put("2090", new Object[] {"850CSAAZERTY", poOrdNum, poOrdDtlLineNum, "", "2090", "A2", "LP2", "", "", "", "", "" });
        poDetailValueMap.put("2100", new Object[] {"850CSAAZERTY", poOrdNum, poOrdDtlLineNum, "", "2100", "A2", "LN3", "", "", "", "", "" });
        poDetailValueMap.put("2110", new Object[] {"850CSAAZERTY", poOrdNum, poOrdDtlLineNum, "", "2110", "A2", "LN4", ""});
        poDetailValueMap.put("2120", new Object[] {"850CSAAZERTY", poOrdNum, poOrdDtlLineNum, "", "2120", "PS", "PS1", mdseCd, "", "", "", "", "", "", "", "", "" });
        poDetailValueMap.put("2130", new Object[] {"850CSAAZERTY", poOrdNum, poOrdDtlLineNum, "", "2130", "PS", "PS2", "", "", "", "", "", "", "", "", "", "" });
        poDetailValueMap.put("2800", new Object[] {"850CSAAZERTY", poOrdNum, poOrdDtlLineNum, "", "2800", "AT", "LAH", "", "", "", "" });
        poDetailValueMap.put("2810", new Object[] {"850CSAAZERTY", poOrdNum, poOrdDtlLineNum, "", "2810", "AT", "LAD", "", "", "" });
        poDetailValueMap.put("2820", new Object[] {"850CSAAZERTY", poOrdNum, poOrdDtlLineNum, "", "2820", "AT", "MAH", "", "", "", "" });
        poDetailValueMap.put("2830", new Object[] {"850CSAAZERTY", poOrdNum, poOrdDtlLineNum, "", "2830", "AT", "MAD", "", "", "" });
        poDetailValueMap.put("2840", new Object[] {"850CSAAZERTY", poOrdNum, poOrdDtlLineNum, "", "2840", "AT", "IAH", "", "", "", "" });
        poDetailValueMap.put("2850", new Object[] {"850CSAAZERTY", poOrdNum, poOrdDtlLineNum, "", "2850", "AT", "IAD", "", "", "" });
        if (!ZYPCommonFunc.hasValue(rqstRcvDt)) {
            rqstRcvDt = "";
        }
        if (!ZYPCommonFunc.hasValue(rqstRcvTm)) {
            rqstRcvTm = "";
        }
        poDetailValueMap.put("3000", new Object[] {"850CSAAZERTY", poOrdNum, poOrdDtlLineNum, "1", "3000", "SH", "SH1", "1", poDispQtyStr, vndUomNm, poDispUomCd, rqstRcvDt + rqstRcvTm, "", "", "0", "0", entDealNetUnitPrcAmt, "", "", "", "",
                "", "", "", "", "", "", "N" });
        poDetailValueMap.put("3010", new Object[] {"850CSAAZERTY", poOrdNum, poOrdDtlLineNum, "1", "3010", "A1", "SH1", "", "", "", "", "" });
        poDetailValueMap.put("3020", new Object[] {"850CSAAZERTY", poOrdNum, poOrdDtlLineNum, "1", "3020", "A2", "SH2", "", "", "", "", "" });
        poDetailValueMap.put("3030", new Object[] {"850CSAAZERTY", poOrdNum, poOrdDtlLineNum, "1", "3030", "A2", "SH3", "", "", "", "", "" });
        poDetailValueMap.put("3040", new Object[] {"850CSAAZERTY", poOrdNum, poOrdDtlLineNum, "1", "3040", "A2", "SH4", "" });
        //QC#20246 UPD START
        poDetailValueMap.put("3050", new Object[] {"850CSAAZERTY", poOrdNum, poOrdDtlLineNum, "1", "3050", "AX", "SL1", shipToCustCd, shipToAcctNm, "", shipToFirstLineAddr, shipToScdLineAddr, shipToThirdLineAddr, shipToFrthLineAddr, shipToCityAddr, shipToPostCd,
                shipToCtryCd, shipToCtryCd, "", "", shipToStCd, shipToStCd, "", "" });
        poDetailValueMap.put("3060", new Object[] {"850CSAAZERTY", poOrdNum, poOrdDtlLineNum, "1", "3060", "CN", "SL1", ctacPsnNm, "", "", "", "", "", "", "", "" });
        //QC#20246 UPD END
        poDetailValueMap.put("3800", new Object[] {"850CSAAZERTY", poOrdNum, poOrdDtlLineNum, "", "3800", "AT", "SAH", "", "", "", "" });
        poDetailValueMap.put("3810", new Object[] {"850CSAAZERTY", poOrdNum, poOrdDtlLineNum, "", "3810", "AT", "SAD", "", "", "" });
        poDetailValueMap.put("4000", new Object[] {"850CSAAZERTY", poOrdNum, poOrdDtlLineNum, "1", "4000", "PR", "PR1", "1", "", "INVENTORY", "", "1", "0", "0", "0", "", "1", "", "" });
        poDetailValueMap.put("4010", new Object[] {"850CSAAZERTY", poOrdNum, poOrdDtlLineNum, "1", "4010", "PR", "PR2", "", "", "", "" });
        poDetailValueMap.put("4020", new Object[] {"850CSAAZERTY", poOrdNum, poOrdDtlLineNum, "1", "4020", "PR", "PR3", "", "", "" });
        poDetailValueMap.put("4030", new Object[] {"850CSAAZERTY", poOrdNum, poOrdDtlLineNum, "1", "4030", "PR", "PR4", "", "", "" });
        poDetailValueMap.put("4040", new Object[] {"850CSAAZERTY", poOrdNum, poOrdDtlLineNum, "1", "4040", "PR", "PR5", "", "", "" });
        poDetailValueMap.put("4050", new Object[] {"850CSAAZERTY", poOrdNum, poOrdDtlLineNum, "1", "4050", "PR", "PR4", "", "", "" });

        return poDetailValueMap;
    }

    private boolean createIfNPAI2090(List<NPAI2090_01TMsg> npai209001TMsgList) {

        boolean returnValue = true;

        NPAI2090_01TMsg[] insertTMsg = new NPAI2090_01TMsg[npai209001TMsgList.size()];
        for (int i = 0; i < npai209001TMsgList.size(); i++) {
            insertTMsg[i] = npai209001TMsgList.get(i);
        }

        S21FastTBLAccessor.insert(insertTMsg);

        return returnValue;
    }

    private List<Map<String, Object>> sendPoInfo(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        NPZC133001PMsg param = (NPZC133001PMsg) msgMap.getPmsg();

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
        ssmParam.put(BIND_PO_ORD_NUM, param.poOrdNum.getValue());
        ssmParam.put(BIND_PO_STS_CD_VALIDATED, PO_STS.VALIDATED);
        ssmParam.put(BIND_PO_MSG_TP_CD_SI, PO_MSG_TP.SPECIAL_INSTRUCTIONS);
        ssmParam.put(BIND_PO_MSG_TP_CD_SN, PO_MSG_TP.SHIPPER_NOTE);
        ssmParam.put(BIND_PO_MSG_TP_CD_RN, PO_MSG_TP.RECEIVER_NOTE);
        ssmParam.put(BIND_PO_MSG_SEG_ID, BigDecimal.ZERO);

        return ssmBatchClient.queryObjectList("sendPoInfo", ssmParam);
    }
}
