/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1190.common;

import static business.blap.NSAL1190.constant.NSAL1190Constant.NSAL1190_MTR_LVL_MAX_NUM;
import static business.blap.NSAL1190.constant.NSAL1190Constant.NZZM0001W;
import static business.blap.NSAL1190.constant.NSAL1190Constant.NZZM0002I;
import static business.blap.NSAL1190.constant.NSAL1190Constant.ZZZM9001E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.apache.log4j.Logger;

import parts.common.EZDMsg;
import business.blap.NSAL1190.NSAL1190CMsg;
import business.blap.NSAL1190.NSAL1190Query;
import business.blap.NSAL1190.NSAL1190SMsg;
import business.blap.NSAL1190.NSAL1190_ASMsg;
import business.blap.NSAL1190.constant.NSAL1190Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_IDX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_LB_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
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
 *<pre>
 * Counters Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/15   Hitachi         O.Okuma         Create          N/A
 * 2016/03/29   Hitachi         O.Okuma         Update          QC5713
 * 2016/06/27   Hitachi         M.Gotou         Update          QC7886
 *</pre>
 */
public class NSAL1190CommonLogic {

    /**
     * Clear Message
     * @param cMsg NSAL1190CMsg
     * @param sMsg NSAL1190SMsg
     */
    public static void clearMsg(NSAL1190CMsg cMsg, NSAL1190SMsg sMsg) {
        sMsg.clear();
        cMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.B);
    }

    /**
     * Create Pull Down
     * @param cMsg NSAL1190CMsg
     */
    public static void createPullDown(NSAL1190CMsg cMsg) {
        ZYPCodeDataUtil.createPulldownList(MTR_IDX.class, cMsg.mtrIdxCd_01, cMsg.mtrIdxDescTxt_01);

        for (int i = 0; i < cMsg.mtrIdxCd_01.length(); i++) {

            setValue(cMsg.mtrIdxDescTxt_01.no(i), cMsg.mtrIdxCd_01.no(i));
        }

        ZYPCodeDataUtil.createPulldownList(MTR_LB_TP.class, cMsg.mtrLbTpCd_01, cMsg.mtrLbTpDescTxt_01);

        for (int i = 0; i < cMsg.mtrLbTpCd_01.length(); i++) {

            setValue(cMsg.mtrLbTpDescTxt_01.no(i), cMsg.mtrLbTpCd_01.no(i));
        }

        int maxCnt = ZYPCodeDataUtil.getNumConstValue(NSAL1190_MTR_LVL_MAX_NUM, cMsg.glblCmpyCd.getValue()).intValue();

        for (int i = 0; i <= maxCnt; i++) {
            String strVal = Integer.valueOf(i).toString();
            setValue(cMsg.xxDplyByCtrlItemCd_01.no(i), strVal);
            setValue(cMsg.xxDplyByCtrlItemCdNm_01.no(i), strVal);
        }
    }

    /**
     * Get Search Data
     * @param cMsg NSAL1190CMsg
     * @param sMsg NSAL1190SMsg
     * @param isSubmit boolean
     */
    public static void getSearchData(NSAL1190CMsg cMsg, NSAL1190SMsg sMsg, boolean isSubmit) {

        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.B);

        S21SsmEZDResult ssmResult = NSAL1190Query.getInstance().getSearchData(cMsg, sMsg, sMsg.A.length() + 1);

        if (!isSubmit) {
            if (ssmResult.isCodeNormal()) {
                // Result > 1000
                int queryResCnt = ssmResult.getQueryResultCount();
                if (queryResCnt > sMsg.A.length()) {
                    cMsg.setMessageInfo(NZZM0001W);
                } else {
                    cMsg.setMessageInfo(NZZM0002I);
                }
                copyToBSMsg(sMsg);
            } else {
                // No result
                cMsg.setMessageInfo(ZZZM9001E);
            }
        }
    }

    /**
     * setPagenation <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NSAL1190CMsg
     * @param sMsg NSAL1190SMsg
     * @param pagenationFrom int
     */
    public static void setPagenation(NSAL1190CMsg cMsg, NSAL1190SMsg sMsg, int pagenationFrom) {

        int cnt = pagenationFrom;
        for (; cnt < pagenationFrom + cMsg.A.length(); cnt++) {
            if (cnt < pagenationFrom + cMsg.A.getValidCount()) {
                EZDMsg.copy(cMsg.A.get(cnt - pagenationFrom), null, sMsg.A.get(cnt), null);
                if (!hasValue(sMsg.A.no(cnt).actvFlg_A)) {
                    setValue(sMsg.A.no(cnt).actvFlg_A, ZYPConstant.FLG_OFF_N);
                }
                if (!hasValue(sMsg.A.no(cnt).bwMtrFlg_A)) {
                    setValue(sMsg.A.no(cnt).bwMtrFlg_A, ZYPConstant.FLG_OFF_N);
                }
                if (!hasValue(sMsg.A.no(cnt).colorMtrFlg_A)) {
                    setValue(sMsg.A.no(cnt).colorMtrFlg_A, ZYPConstant.FLG_OFF_N);
                }
                if (!hasValue(sMsg.A.no(cnt).totMtrFlg_A)) {
                    setValue(sMsg.A.no(cnt).totMtrFlg_A, ZYPConstant.FLG_OFF_N);
                }
                if (!hasValue(sMsg.A.no(cnt).corpAdvtgFlg_A)) {
                    setValue(sMsg.A.no(cnt).corpAdvtgFlg_A, ZYPConstant.FLG_OFF_N);
                }
            } else {
                break;
            }
        }
    }

    /**
     * pagenation <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param itemIndex int
     */
    public static void pagenation(NSAL1190CMsg cMsg, NSAL1190SMsg sMsg, int itemIndex) {

        int startIndex = (itemIndex / cMsg.A.length()) * cMsg.A.length();
        int num = 0;
        ZYPTableUtil.clear(cMsg.A);
        for (int i = startIndex; i < sMsg.A.getValidCount(); i++) {
            if (num >= cMsg.A.length()) {
                break;
            }
            EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(num), null);
            num++;
        }
        cMsg.A.setValidCount(num);
        setValue(cMsg.xxPageShowFromNum, BigDecimal.valueOf(startIndex + 1));
        setValue(cMsg.xxPageShowToNum, BigDecimal.valueOf(startIndex + cMsg.A.getValidCount()));
        setValue(cMsg.xxPageShowOfNum, BigDecimal.valueOf(sMsg.A.getValidCount()));
        setValue(cMsg.xxPageShowCurNum, BigDecimal.valueOf(startIndex + 1).divide(BigDecimal.valueOf(cMsg.A.length()), 0, BigDecimal.ROUND_UP));
        setValue(cMsg.xxPageShowTotNum, BigDecimal.valueOf(sMsg.A.getValidCount()).divide(BigDecimal.valueOf(cMsg.A.length()), 0, BigDecimal.ROUND_UP));
    }

    /**
     * convertPageNumToFirstRowIndex
     * @param cMsg NSAL1190CMsg
     * @return int
     */
    public static int convertPageNumToFirstRowIndex(NSAL1190CMsg cMsg) {
        if (cMsg.xxPageShowCurNum.getValueInt() <= 0) {
            return 0;
        } else if (cMsg.xxPageShowTotNum.getValueInt() < cMsg.xxPageShowCurNum.getValueInt()) {
            setValue(cMsg.xxPageShowCurNum, cMsg.xxPageShowTotNum);
        }
        if (cMsg.A.length() * (cMsg.xxPageShowCurNum.getValueInt() - 1) > (cMsg.xxPageShowOfNum.getValueInt())) {
            return cMsg.xxPageShowCurNum.getValueInt() - 1;
        }
        return cMsg.A.length() * (cMsg.xxPageShowCurNum.getValueInt() - 1);
    }

    private static void copyToBSMsg(NSAL1190SMsg sMsg) {

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            setValue(sMsg.A.no(i).xxRowNum_A, new BigDecimal(i));
            EZDMsg.copy(sMsg.A.no(i), null, sMsg.B.no(i), null);
        }
        sMsg.B.setValidCount(sMsg.A.getValidCount());
    }

    /**
     * getErrPageFromNum
     * @param cMsg NSAL1190CMsg
     * @param sMsg NSAL1190SMsg
     * @return int
     */
    public static int getErrPageFromNum(NSAL1190CMsg cMsg, NSAL1190SMsg sMsg) {

        int errIndex = 0;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            // Error
            if (isError(sMsg.A.no(i))) {
                errIndex = i;
                break;
            }
        }
        return errIndex / cMsg.A.length() * cMsg.A.length();
    }

    private static boolean isError(NSAL1190_ASMsg asMsg) {

        if (asMsg.mtrLbNm_A.isError()) {
            return true;
        }
        if (asMsg.mtrLbTpCd_A.isError()) {
            return true;
        }
        if (asMsg.mtrIdxCd_A.isError()) {
            return true;
        }
        if (asMsg.bllgMtrLvlNum_A.isError()) {
            return true;
        }
        if (asMsg.mtrLbDescTxt_A.isError()) {
            return true;
        }
        if (asMsg.mtrLbNm_AF.isError()) {
            return true;
        }
        if (asMsg.mtrLbNm_AG.isError()) {
            return true;
        }
        if (asMsg.effFromDt_A.isError()) {
            return true;
        }
        if (asMsg.effThruDt_A.isError()) {
            return true;
        }
        if (asMsg.intgMdseCd_A.isError()) {
            return true;
        }
        return false;
    }

    // add start 2016/06/27 CSA Defect#7886
    private static Logger logger = Logger.getLogger(NSAL1190CommonLogic.class);

    /**
    * invokeMasterDataMessaging
    * @param isExistsModel boolean
    * @param ezUpTime String
    * @param mtrLbCd String
    * @param mtrLbNm String
    */
    public static void invokeMasterDataMessaging(boolean isExistsModel, String ezUpTime, String mtrLbCd, String mtrLbNm) {

        try {
            Event event = new Event();
            event.setReferencedEntity(NSAL1190Constant.REF_ENTITY_ITEM);

            if (isExistsModel) {
                event.setOperationType(OPERATIONTYPES.UPDATE);
            } else {
                event.setOperationType(OPERATIONTYPES.CREATE);
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSSS");
            Date formatDate = sdf.parse(ezUpTime);

            GregorianCalendar c = new GregorianCalendar();
            c.setTime(formatDate);
            XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
            event.setCreateStamp(date2);

            event.setId(1);

            ArrayOfKeyReference ak = new ArrayOfKeyReference();

            // Set Key Model ID
            KeyReference kr = new KeyReference();
            kr.setType(REFERENCETYPES.TECHNICAL);

            ArrayOfAttribute aa = new ArrayOfAttribute();
            Attribute a = new Attribute();
            a.setName(NSAL1190Constant.KEY_NAME_MTR_LB_CD);
            a.setValue(mtrLbCd);
            aa.getAttribute().add(a);
            kr.setAttributes(aa);

            ak.getKeyReference().add(kr);

            // Set Key Model Name
            kr = new KeyReference();
            kr.setType(REFERENCETYPES.MNEMONICAL);

            aa = new ArrayOfAttribute();
            a = new Attribute();
            a.setName(NSAL1190Constant.KEY_NAME_MTR_LB_NM);
            a.setValue(mtrLbNm);
            aa.getAttribute().add(a);
            kr.setAttributes(aa);

            ak.getKeyReference().add(kr);

            event.setKeyReferences(ak);

            ArrayOfEvent ae = new ArrayOfEvent();
            ae.getEvent().add(event);

            printRequest(ae);

            logger.debug("Proxy invocation starting");

            // Invoke the proxy
            new MasterDataMessagingServiceProxy().synchronizationMessage(ae);

            logger.debug("Proxy invocation complete");
        } catch (Throwable e) {
            e.printStackTrace();
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
    // add end 2016/06/27 CSA Defect#7886
}
