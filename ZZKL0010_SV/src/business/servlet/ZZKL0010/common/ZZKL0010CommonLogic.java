package business.servlet.ZZKL0010.common;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileWriter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.mobility.mapping.S21MobileAttrStructure;
import com.canon.cusa.s21.framework.mobility.mapping.S21MobileMsgStructure;
import com.canon.cusa.s21.framework.mobility.mapping.ServiceDescription;
import com.canon.cusa.s21.framework.mobility.service.S21MobileServiceMap;
import com.canon.cusa.s21.framework.mobility.util.MappingFileUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

import parts.common.EZDItemAttribute;
import parts.common.EZDMsg;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.ZZKL0010.ZZKL0010BMsg;
import business.servlet.ZZKL0010.constant.ZZKL0010Constant;

public class ZZKL0010CommonLogic {

    public static final String PMSG_PACKAGE = "business.parts";

    private static String getPMsgClsPath(String msgName) {
        String clsPath = PMSG_PACKAGE + "." + msgName;
        return clsPath;
    }

    private String[] getArgumentArray(String arguments) {
        if (arguments == null || arguments.equals("")) {
            return null;
        }
        return arguments.split(",");
    }

    public static void getApiStructure(ZZKL0010BMsg scrnMsg) {
        String eventName = nvlStr(scrnMsg.xxScrItem20Txt.getValue());
        MappingFileUtil mfUtil = new MappingFileUtil();
        ServiceDescription desc = mfUtil.getServiceDescObjFromEvent(eventName);
        if (desc == null) {
            String[] msg = {eventName + " event File is not found" };
            scrnMsg.setMessageInfo("ZZXM0001E", msg); // TODO
            return;
        }

        String apiName = nvlStr(desc.getApiName());
        String methodName = nvlStr(desc.getMethodName());
        String arguments = nvlStr(desc.getArguments());
        String resrcObjName = nvlStr(desc.getResrcObjNm());
        String description = nvlStr(desc.getDescription());
        //
        scrnMsg.xxScrItem20Txt_A.setValue(apiName);
        scrnMsg.xxScrItem30Txt.setValue(methodName);
        scrnMsg.xxScrItem130Txt.setValue(arguments);
        scrnMsg.resrcObjNm.setValue(resrcObjName);
        scrnMsg.xxScrItem130Txt_A.setValue(description);

        try {
            setListBoxFromObj(scrnMsg, desc);
        } catch (InstantiationException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
    }

    private static void setListBoxFromObj(ZZKL0010BMsg scrnMsg, ServiceDescription desc) throws InstantiationException, IllegalAccessException {
        String[] arguments = desc.getArguments();
        List<S21MobileMsgStructure> msglist = desc.getResponseMessages();

        String subDesc = "List";
        if (arguments == null){
            return;
        }
        int cntAll = 0;
        for (int i = 0; i < arguments.length; i++) {
            S21MobileMsgStructure msg = msglist.get(i);
            HashMap<String, S21MobileAttrStructure> attrMap = msg.getMap();

            String msgClassPath = getPMsgClsPath(arguments[i].replace(subDesc, ""));
            Class msgCls;
            try {
                msgCls = Class.forName(msgClassPath);
            } catch (ClassNotFoundException e) {
                // TODO 自動生成された catch ブロック
                e.printStackTrace();
                return;
            }
            EZDMsg ezMsg = (EZDMsg) msgCls.newInstance();
            Iterator ite = ezMsg.keysIterator();
            // scrnMsg.B.no(cntAll).xxScrItem30Txt_B1.setValue(msgClass[i]);
            String msgName = Integer.toString(i) + " : " + arguments[i];
            while (ite.hasNext()) {
                String key = (String) ite.next();
                EZDItemAttribute atr = ezMsg.getAttr(key);
                S21MobileAttrStructure mattr = attrMap.get(key);
                scrnMsg.A.no(cntAll).xxScrItem30Txt_A1.setValue(msgName);
                scrnMsg.A.no(cntAll).xxScrItem54Txt_A1.setValue(key);
                scrnMsg.A.no(cntAll).xxScrItem54Txt_A2.clear();

                if (mattr != null && mattr.isIgnore()) {
                    scrnMsg.A.no(cntAll).xxIntfcChkFlg_A1.setValue(ZYPConstant.CHKBOX_ON_Y);
                } else {
                    scrnMsg.A.no(cntAll).xxIntfcChkFlg_A1.clear();
                }
                if (mattr != null && mattr.isExchangeTime()) {
                    scrnMsg.A.no(cntAll).xxIntfcChkFlg_A2.setValue(ZYPConstant.CHKBOX_ON_Y);
                } else {
                    scrnMsg.A.no(cntAll).xxIntfcChkFlg_A2.clear();
                }

                if (atr.getType() == 0) {
                    scrnMsg.A.no(cntAll).xxScrItem10Txt_A3.setValue(String.valueOf(atr.getArrayLength()));
                    cntAll++;
                    cntAll = setListBoxFromObj(scrnMsg, atr, cntAll, msgName, mattr.getMsgList().getMap());
                    continue;
                }
                scrnMsg.A.no(cntAll).xxScrItem10Txt_A3.setValue(String.valueOf(atr.getDigit()));
                scrnMsg.A.no(cntAll).xxScrItem10Txt_A4.setValue(String.valueOf(atr.getType()));
                cntAll++;
            }
        }
        scrnMsg.A.setValidCount(cntAll);
    }

    private static int setListBoxFromObj(ZZKL0010BMsg scrnMsg, EZDItemAttribute atr, int cntAll, String msgName, HashMap<String, S21MobileAttrStructure> attrMap) throws InstantiationException, IllegalAccessException {
        String subDesc = "Array";
        if (atr.getDetailMsgName() == null) {
            return cntAll;
        }
        String msgClassPath = atr.getDetailMsgName().replace(subDesc, "");
        // String msgClassPath = getPMsgClsPath(msgClass);
        Class msgCls;
        try {
            msgCls = Class.forName(msgClassPath);
        } catch (ClassNotFoundException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
            return cntAll;
        }

        EZDMsg ezMsg = (EZDMsg) msgCls.newInstance();
        Iterator ite = ezMsg.keysIterator();

        String name = atr.getDisplayName();

        while (ite.hasNext()) {

            String key = (String) ite.next();
            EZDItemAttribute attr = ezMsg.getAttr(key);
            S21MobileAttrStructure mattr = attrMap.get(key);

            scrnMsg.A.no(cntAll).xxScrItem30Txt_A1.setValue(msgName);
            scrnMsg.A.no(cntAll).xxScrItem54Txt_A1.setValue(key);
            scrnMsg.A.no(cntAll).xxScrItem54Txt_A2.setValue(name);

            if (mattr != null && mattr.isIgnore()) {
                scrnMsg.A.no(cntAll).xxIntfcChkFlg_A1.setValue(ZYPConstant.CHKBOX_ON_Y);
            } else {
                scrnMsg.A.no(cntAll).xxIntfcChkFlg_A1.clear();
            }
            if (mattr != null && mattr.isExchangeTime()) {
                scrnMsg.A.no(cntAll).xxIntfcChkFlg_A2.setValue(ZYPConstant.CHKBOX_ON_Y);
            } else {
                scrnMsg.A.no(cntAll).xxIntfcChkFlg_A2.clear();
            }

            if (attr.getType() == 0) {
                scrnMsg.A.no(cntAll).xxScrItem10Txt_A3.setValue(String.valueOf(attr.getArrayLength()));
                cntAll++;
                cntAll = setListBoxFromObj(scrnMsg, attr, cntAll, msgName, mattr.getMsgList().getMap());
                continue;
            }
            scrnMsg.A.no(cntAll).xxScrItem10Txt_A3.setValue(String.valueOf(attr.getDigit()));
            scrnMsg.A.no(cntAll).xxScrItem10Txt_A4.setValue(String.valueOf(attr.getType()));
            cntAll++;
        }
        return cntAll;
    }

    public static void setListBox(ZZKL0010BMsg scrnMsg) {
        String msgClass[] = scrnMsg.xxScrItem130Txt.getValue().split(",");
        String subDesc = "List";

        int cntAll = 0;
        for (int i = 0; i < msgClass.length; i++) {
            String msgClassPath = getPMsgClsPath(msgClass[i].replace(subDesc, ""));
            EZDMsg ezMsg;
            try {
                Class msgCls = Class.forName(msgClassPath);
                ezMsg = (EZDMsg) msgCls.newInstance();
            } catch (ClassNotFoundException e) {
                scrnMsg.xxScrItem130Txt.setErrorInfo(1, "ZZM9032E", new String[] {"ClassName" });
                return;
            } catch (NoClassDefFoundError ex) {
                scrnMsg.xxScrItem130Txt.setErrorInfo(1, "ZZM9032E", new String[] {"Class Name" });
                return;
            } catch (InstantiationException ex) {
                scrnMsg.xxScrItem130Txt.setErrorInfo(1, "ZZM9032E", new String[] {"Class Name ex" });
                return;
            } catch (IllegalAccessException e) {
                scrnMsg.xxScrItem130Txt.setErrorInfo(1, "ZZM9032E", new String[] {"Class Name access" });
                return;
            }
            Iterator ite = ezMsg.keysIterator();
            // scrnMsg.B.no(cntAll).xxScrItem30Txt_B1.setValue(msgClass[i]);
            String msgName = Integer.toString(i) + " : " + msgClass[i];
            while (ite.hasNext()) {
                String key = (String) ite.next();
                EZDItemAttribute atr = ezMsg.getAttr(key);
                scrnMsg.A.no(cntAll).xxScrItem30Txt_A1.setValue(msgName);
                scrnMsg.A.no(cntAll).xxScrItem54Txt_A1.setValue(key);
                scrnMsg.A.no(cntAll).xxScrItem54Txt_A2.clear();
                scrnMsg.A.no(cntAll).xxIntfcChkFlg_A1.clear();
                scrnMsg.A.no(cntAll).xxIntfcChkFlg_A2.clear();
                System.out.println(cntAll + " : " + key);

                if (atr.getType() == 0) {
                    scrnMsg.A.no(cntAll).xxScrItem10Txt_A3.setValue(String.valueOf(atr.getArrayLength()));
                    cntAll++;
                    cntAll = setListBox(scrnMsg, atr, cntAll, msgName);
                    continue;
                }
                scrnMsg.A.no(cntAll).xxScrItem10Txt_A3.setValue(String.valueOf(atr.getDigit()));
                scrnMsg.A.no(cntAll).xxScrItem10Txt_A4.setValue(String.valueOf(atr.getType()));
                cntAll++;
            }
        }
        scrnMsg.A.setValidCount(cntAll);
        
        
        S21TableColorController tblColor = new S21TableColorController( ZZKL0010Constant.SCREEN_ID0, scrnMsg );
        tblColor.setAlternateRowsBG( "A", scrnMsg.A );
    }

    private static int setListBox(ZZKL0010BMsg scrnMsg, EZDItemAttribute atr, int cntAll, String msgName) {
        String subDesc = "Array";
        if (atr.getDetailMsgName() == null) {
            return cntAll;
        }
        String msgClassPath = atr.getDetailMsgName().replace(subDesc, "");
        // String msgClassPath = getPMsgClsPath(msgClass);
        EZDMsg ezMsg;
        try {
            Class msgCls = Class.forName(msgClassPath);
            ezMsg = (EZDMsg) msgCls.newInstance();
        } catch (ClassNotFoundException e) {
            scrnMsg.xxScrItem130Txt.setErrorInfo(1, "ZZM9032E", new String[] {"ClassName" });
            return cntAll;
        } catch (NoClassDefFoundError ex) {
            scrnMsg.xxScrItem130Txt.setErrorInfo(1, "ZZM9032E", new String[] {"Class Name" });
            return cntAll;
        } catch (InstantiationException ex) {
            scrnMsg.xxScrItem130Txt.setErrorInfo(1, "ZZM9032E", new String[] {"Class Name ex" });
            return cntAll;
        } catch (IllegalAccessException e) {
            scrnMsg.xxScrItem130Txt.setErrorInfo(1, "ZZM9032E", new String[] {"Class Name access" });
            return cntAll;
        }

        Iterator<String> ite = ezMsg.keysIterator();

        String name = atr.getDisplayName();

        while (ite.hasNext()) {

            String key = ite.next();
            EZDItemAttribute attr = ezMsg.getAttr(key);
            scrnMsg.A.no(cntAll).xxScrItem30Txt_A1.setValue(msgName);
            scrnMsg.A.no(cntAll).xxScrItem54Txt_A1.setValue(key);
            scrnMsg.A.no(cntAll).xxScrItem54Txt_A2.setValue(name);
            scrnMsg.A.no(cntAll).xxIntfcChkFlg_A1.clear();
            scrnMsg.A.no(cntAll).xxIntfcChkFlg_A2.clear();

            if (attr.getType() == 0) {
                scrnMsg.A.no(cntAll).xxScrItem10Txt_A3.setValue(String.valueOf(attr.getArrayLength()));
                cntAll++;
                cntAll = setListBox(scrnMsg, attr, cntAll, msgName);
                continue;
            }
            scrnMsg.A.no(cntAll).xxScrItem10Txt_A3.setValue(String.valueOf(attr.getDigit()));
            scrnMsg.A.no(cntAll).xxScrItem10Txt_A4.setValue(String.valueOf(attr.getType()));
            cntAll++;
        }
        return cntAll;
    }

    public static void doDownLoad(ZZKL0010BMsg scrnMsg) {
        MappingFileUtil mfUtil = new MappingFileUtil();
        String eventName = scrnMsg.xxScrItem20Txt.getValue();
        String apiName = scrnMsg.xxScrItem20Txt_A.getValue();
        String methodName = scrnMsg.xxScrItem30Txt.getValue();
        String argument = scrnMsg.xxScrItem130Txt.getValue();
        String description = scrnMsg.xxScrItem130Txt_A.getValue();
        String resrcObjName = scrnMsg.resrcObjNm.getValue();

        String fileName = eventName + ".xml";

        List<Integer> ignoreRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxIntfcChkFlg_A1", ZYPConstant.CHKBOX_ON_Y);
        List<Integer> chTmRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxIntfcChkFlg_A2", ZYPConstant.CHKBOX_ON_Y);

        List<String> ignore = getCheckedList(ignoreRows, scrnMsg);
        List<String> chTmZn = getCheckedList(chTmRows, scrnMsg);
        
        System.out.println(chTmZn);

        try {
            ServiceDescription desc = mfUtil.getApiStructure(eventName, apiName, methodName, resrcObjName, description, argument.split(","), ignore, chTmZn);
            // String json = JsonUtil.encode(desc, true);
//            String str = toXml(desc);
            String str = mfUtil.getXmlString(desc);
            execDownLoad(scrnMsg, fileName, str);
            // System.out.println(JsonUtil.encode(desc, true));
        } catch (ClassNotFoundException e) {
            String[] msg = {e.getMessage() };
            scrnMsg.setMessageInfo("ZZXM0001E", msg); // TODO
            e.printStackTrace();
        }
    }

    public static void execDownLoad(ZZKL0010BMsg scrnMsg, String fileName, String str) {
        scrnMsg.xxFileData.setTempFilePath(null, fileName, "");
        ZYPFileWriter.writeFile(scrnMsg.xxFileData.getTempFilePath(), str.getBytes());
    }

    private static List<String> getCheckedList(List<Integer> selectedList, ZZKL0010BMsg scrnMsg) {
        List<String> checkedList = new ArrayList<String>();
        Iterator<Integer> ite = selectedList.iterator();
        String msgName = scrnMsg.A.no(0).xxScrItem30Txt_A1.getValue();
        String value = "";
        while (ite.hasNext()) {
            int num = ite.next();
            if (!msgName.equals(scrnMsg.A.no(num).xxScrItem30Txt_A1.getValue())) {
                msgName = scrnMsg.A.no(num).xxScrItem30Txt_A1.getValue();
                if (value.equals("")) {
                    checkedList.add(null);
                } else {
                    value = value.substring(0, value.length() - 1);
                    System.out.println("value : " + value);
                    checkedList.add(value);
                    value = "";
                }
            }
            String parent = scrnMsg.A.no(num).xxScrItem54Txt_A2.getValue();
            if (parent != null && !parent.equals("")) {
                value = value + parent + ".";
            }
            value = value + scrnMsg.A.no(num).xxScrItem54Txt_A1.getValue() + ",";
        }
        checkedList.add(value);
        return checkedList;
    }

    /**
     * initialize button
     * @param handler
     */
    public static void enableDownloadButton(EZDCommonHandler handler) {
        setCommonButton(handler);
        handler.setButtonProperties(ZZKL0010Constant.CMN_BTN6[0], ZZKL0010Constant.CMN_BTN6[1], ZZKL0010Constant.CMN_BTN6[2], 1, null);
        handler.setButtonProperties(ZZKL0010Constant.CMN_BTN9[0], ZZKL0010Constant.CMN_BTN9[1], ZZKL0010Constant.CMN_BTN9[2], 1, null);
    }

    /**
     * set common button attribute
     * @param handler
     */
    public static void setCommonButton(EZDCommonHandler handler) {
        handler.setButtonProperties(ZZKL0010Constant.CMN_BTN1[0], ZZKL0010Constant.CMN_BTN1[1], ZZKL0010Constant.CMN_BTN1[2], 0, null);
        handler.setButtonProperties(ZZKL0010Constant.CMN_BTN2[0], ZZKL0010Constant.CMN_BTN2[1], ZZKL0010Constant.CMN_BTN2[2], 0, null);
        handler.setButtonProperties(ZZKL0010Constant.CMN_BTN3[0], ZZKL0010Constant.CMN_BTN3[1], ZZKL0010Constant.CMN_BTN3[2], 0, null);
        handler.setButtonProperties(ZZKL0010Constant.CMN_BTN4[0], ZZKL0010Constant.CMN_BTN4[1], ZZKL0010Constant.CMN_BTN4[2], 0, null);
        handler.setButtonProperties(ZZKL0010Constant.CMN_BTN5[0], ZZKL0010Constant.CMN_BTN5[1], ZZKL0010Constant.CMN_BTN5[2], 0, null);
        handler.setButtonProperties(ZZKL0010Constant.CMN_BTN6[0], ZZKL0010Constant.CMN_BTN6[1], ZZKL0010Constant.CMN_BTN6[2], 0, null);
        handler.setButtonProperties(ZZKL0010Constant.CMN_BTN7[0], ZZKL0010Constant.CMN_BTN7[1], ZZKL0010Constant.CMN_BTN7[2], 0, null);
        handler.setButtonProperties(ZZKL0010Constant.CMN_BTN8[0], ZZKL0010Constant.CMN_BTN8[1], ZZKL0010Constant.CMN_BTN8[2], 0, null);
        handler.setButtonProperties(ZZKL0010Constant.CMN_BTN9[0], ZZKL0010Constant.CMN_BTN9[1], ZZKL0010Constant.CMN_BTN9[2], 0, null);
        handler.setButtonProperties(ZZKL0010Constant.CMN_BTN10[0], ZZKL0010Constant.CMN_BTN10[1], ZZKL0010Constant.CMN_BTN10[2], 1, null);
    }

    private static String nvlStr(String input) {
        if (input == null) {
            input = "";
        }
        return input;
    }

    private static String nvlStr(String[] input) {
        StringBuilder argStr = new StringBuilder();
        if (input == null) {
            return "";
        }
        for (int i = 0; i < input.length; i++) {
            argStr.append(input[i]).append(",");
        }
        argStr.delete(argStr.length() - 1, argStr.length());
        return argStr.toString();
    }
    
    
    // TODO scrn01
    public static void setSelectedValue(ZZKL0010BMsg scrnMsg, int index){
        String eventName = scrnMsg.B.no(index).xxScrItem30Txt_B1.getValue();
        scrnMsg.xxScrItem20Txt.setValue(eventName);
    }
    
    public static void initApiList(ZZKL0010BMsg scrnMsg){
        int cnt = 0;
        Set<String> keyList = S21MobileServiceMap.getInstance().getServiceNames();
        Iterator<String> ite = keyList.iterator();
        while(ite.hasNext()){
            String key = ite.next();
            scrnMsg.B.no(cnt).xxScrItem30Txt_B1.setValue(key);
//            scrnMsg.B.no(cnt).xxScrItem20Txt_B2.setValue(key);
//            scrnMsg.B.no(cnt).xxScrItem30Txt_B3.setValue(key);
//            scrnMsg.B.no(cnt).xxScrItem130Txt_B4.setValue(key);
            cnt++;
        }
        scrnMsg.B.setValidCount(cnt);
        S21TableColorController tblColor = new S21TableColorController( ZZKL0010Constant.SCREEN_ID1, scrnMsg );
        tblColor.setAlternateRowsBG( "B", scrnMsg.B );
    }
}
