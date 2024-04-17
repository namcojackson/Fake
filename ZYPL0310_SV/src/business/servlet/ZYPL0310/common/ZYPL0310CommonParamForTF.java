package business.servlet.ZYPL0310.common;

import java.util.List;

import parts.common.EZDPMsg;

public class ZYPL0310CommonParamForTF {
    private EZDPMsg pMsgTF;

    private List<EZDPMsg> pMsgTFList;

    private Boolean isPMsgList = false;

    public void setPMsgTF(EZDPMsg pMsgTF){
        this.pMsgTF = pMsgTF;
        isPMsgList = false;
    }

    public EZDPMsg getPMsgTF(){
        return this.pMsgTF;
    }

    public void setPMsgTFList(List<EZDPMsg> pMsgTFList){
        this.pMsgTFList = pMsgTFList;
        isPMsgList = true;
    }

    public List<EZDPMsg> getPMsgTFList(){
        return this.pMsgTFList;
    }

    public Boolean isPMsgList(){
        return this.isPMsgList;
    }
}
