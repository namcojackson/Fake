package business.blap.ZZZL0030.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


public class ZZZL0030ChartBean implements Serializable {
        
    private String bizId;
    
    private String eventId;
    
    private String jvmName;
    
    private String userId;
    
    private String fromDate;
    
    private String toDate;
    
    private String yLegend;
    
    private String xLegend;
    
    private String yAxleName;
    
    private String xAxleName;
    
    private double yAxle_MaxValue_ProcTm;
    
    private double yAxle_MaxValue_GlblSize;
    
    private double yAxle_MaxValue_Throughput;
    
    private double yAxle_MinValue;
    
    private int yAxle_Step;
    
    private double xAxle_MaxValue;
    
    private double xAxle_MinValue;
    
    private int xAxle_Step;
    
    private ArrayList<String> xAxleLabel;
    
    private HashMap<String, ArrayList<Number>> lineValues_ProcTm;
    private HashMap<String, ArrayList<Number>> lineValues_GlblSize;
    private HashMap<String, ArrayList<Number>> lineValues_Throughput;

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public HashMap<String, ArrayList<Number>> getLineValues_ProcTm() {
        if(this.lineValues_ProcTm == null){
            this.lineValues_ProcTm = new HashMap<String, ArrayList<Number>>();
        }
        return this.lineValues_ProcTm;
    }

    public void setLineValues_ProcTm(HashMap<String, ArrayList<Number>> lineValues_ProcTm) {
        this.lineValues_ProcTm = lineValues_ProcTm;
    }
    
    public HashMap<String, ArrayList<Number>> getLineValues_GlblSize() {
        if(this.lineValues_GlblSize == null){
            this.lineValues_GlblSize = new HashMap<String, ArrayList<Number>>();
        }
        return this.lineValues_GlblSize;
    }

    public void setLineValues_GlblSize(HashMap<String, ArrayList<Number>> lineValues_GlblSize) {
        this.lineValues_GlblSize = lineValues_GlblSize;
    }
    
    public HashMap<String, ArrayList<Number>> getLineValues_Throughput() {
        if(this.lineValues_Throughput == null){
            this.lineValues_Throughput = new HashMap<String, ArrayList<Number>>();
        }
        return this.lineValues_Throughput;
    }

    public void setLineValues_Throughput(HashMap<String, ArrayList<Number>> lineValues_Throughput) {
        this.lineValues_Throughput = lineValues_Throughput;
    }

    public double getXAxle_MaxValue() {
        return xAxle_MaxValue;
    }

    public void setXAxle_MaxValue(double axle_MaxValue) {
        xAxle_MaxValue = axle_MaxValue;
    }

    public double getXAxle_MinValue() {
        return xAxle_MinValue;
    }

    public void setXAxle_MinValue(double axle_MinValue) {
        xAxle_MinValue = axle_MinValue;
    }

    public int getXAxle_Step() {
        return xAxle_Step;
    }

    public void setXAxle_Step(int axle_Step) {
        xAxle_Step = axle_Step;
    }

    public String getXAxleName() {
        return xAxleName;
    }

    public void setXAxleName(String axleName) {
        xAxleName = axleName;
    }

    public double getYAxle_MaxValue() {
        return yAxle_MaxValue_ProcTm;
    }

    public void setYAxle_MaxValue(double axle_MaxValue) {
        yAxle_MaxValue_ProcTm = axle_MaxValue;
    }
    
    public double getYAxle_MaxValue_GlblSize() {
        return yAxle_MaxValue_GlblSize;
    }

    public void setYAxle_MaxValue_GlblSize(double axle_MaxValue) {
        yAxle_MaxValue_GlblSize = axle_MaxValue;
    }
    
    public double getYAxle_MaxValue_Throughput() {
        return yAxle_MaxValue_Throughput;
    }

    public void setYAxle_MaxValue_Throughput(double axle_MaxValue) {
        yAxle_MaxValue_Throughput = axle_MaxValue;
    }

    public double getYAxle_MinValue() {
        return yAxle_MinValue;
    }

    public void setYAxle_MinValue(double axle_MinValue) {
        yAxle_MinValue = axle_MinValue;
    }

    public int getYAxle_Step() {
        return yAxle_Step;
    }

    public void setYAxle_Step(int axle_Step) {
        yAxle_Step = axle_Step;
    }

    public String getYAxleName() {
        return yAxleName;
    }

    public void setYAxleName(String axleName) {
        yAxleName = axleName;
    }

    public String getXLegend() {
        return xLegend;
    }

    public void setXLegend(String legend) {
        xLegend = legend;
    }

    public String getYLegend() {
        return yLegend;
    }

    public void setYLegend(String legend) {
        yLegend = legend;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
    public String getEventId() {
        return eventId;
    }
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
    public String getJvmName() {
        return jvmName;
    }
    public void setJvmName(String jvmName) {
        this.jvmName = jvmName;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public ArrayList<String> getXAxleLabel() {
        return xAxleLabel;
    }
    public void setXAxleLabel(ArrayList<String> axleLabel) {
        xAxleLabel = axleLabel;
    }
}
