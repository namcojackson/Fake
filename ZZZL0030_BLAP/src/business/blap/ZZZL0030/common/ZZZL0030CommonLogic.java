package business.blap.ZZZL0030.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import jofc2.model.Chart;
import jofc2.model.Text;
import jofc2.model.axis.Label;
import jofc2.model.axis.XAxis;
import jofc2.model.axis.XAxisLabels;
import jofc2.model.axis.YAxis;
import jofc2.model.elements.Element;
import jofc2.model.elements.LineChart;
import jofc2.model.elements.Tooltip;
import jofc2.model.elements.LineChart.Dot;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZZZL0030.ZZZL0030CMsg;
import business.blap.ZZZL0030.ZZZL0030Query;
import business.blap.ZZZL0030.ZZZL0030SMsg;
import business.blap.ZZZL0030.constant.ZZZL0030Constant;
import business.db.GLBL_CMPYTMsg;

import com.canon.cusa.s21.framework.ZYP.file.ZYPFileWriter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

public class ZZZL0030CommonLogic implements ZZZL0030Constant {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    private static final SimpleDateFormat dateFormat2 = new SimpleDateFormat("MMM dd HH:mm", Locale.US);

    public static int colorIndex = 0;

    /**
     * Get step (It'll be used for dividing x-Axle.)
     */
    public static synchronized int getStep(String fromDt, String toDt) throws ParseException {

        int delimiter = 0;
        long diffMin = 0L;
        long stTm = 0L;
        long edTm = 0L;

        stTm = dateFormat.parse(fromDt).getTime();
        edTm = dateFormat.parse(toDt).getTime();

        diffMin = edTm - stTm;
        diffMin = diffMin / (1000L * 60L); // Minuate
        if (diffMin <= 60L * 1L) {
            delimiter = 1;
        } else if (diffMin <= 60L * 3L) {
            delimiter = 2;
        } else if (diffMin <= 60L * 5L) {
            delimiter = 4;
        } else if (diffMin <= 60L * 8L) {
            delimiter = 5;
        } else if (diffMin <= 60L * 10L) {
            delimiter = 6;
        } else if (diffMin <= 60L * 15L) {
            delimiter = 10;
        } else if (diffMin <= 60L * 24L) {
            delimiter = 15;
        } else if (diffMin <= 60L * 36L) {
            delimiter = 20;
        } else if (diffMin <= 60L * 24L * 2L) {
            delimiter = 30;
        } else if (diffMin <= 60L * 24L * 4L) {
            delimiter = 60;
        } else if (diffMin <= 60L * 24L * 8L) {
            delimiter = 60 * 2;
        } else if (diffMin <= 60L * 24L * 16L) {
            delimiter = 60 * 6;
        } else if (diffMin <= 60L * 24L * 31L) {
            delimiter = 60 * 12;
        } else {
            // exception
        }
        return delimiter;
    }

    public static synchronized ZZZL0030ChartBean getAverageData(ZZZL0030CMsg cMsg, ZZZL0030SMsg data) throws ParseException {

        ZZZL0030ChartBean chartData = new ZZZL0030ChartBean();

        ArrayList<Number> values_BizProcTm = null;
        ArrayList<Number> values_GlblSize = null;
        ArrayList<Number> values_Throughput = null;
        ArrayList<String> xLabels = new ArrayList<String>();
        double sum_procTm = 0.0D;
        double sum_glblSize = 0.0D;
        double sum_throughput = 0.0D;

        int i = 0;
        int sum_cnt_procTm = 0;
        int sum_cnt_glblSize = 0;
        int sum_cnt_throughput = 0;
        boolean firstFlg = true;
        boolean lastFlg = false;

        String fromDate = cMsg.bizStartTs.getValue();
        String toDate = cMsg.bizEndTs.getValue();
        String jvmNm = cMsg.jvmNm_S.getValue();
        String opUsrId = cMsg.opsUsrId.getValue();
        if (cMsg.jvmNm_S.isClear()) {
            jvmNm = "All";
        }
        if (!cMsg.opsUsrId.isClear()) {
            jvmNm = jvmNm + ":" + opUsrId;
        }
        jvmNm = "(" + jvmNm + ")";
        String eventId = "";

        chartData.setFromDate(fromDate);
        chartData.setToDate(toDate);

        int step = getStep(fromDate, toDate); // Get step (It'll
        // be
        // used for divide
        // x-Axle.)
        Date addedDate = addDate(dateFormat.parse(fromDate), 0, 0, 0, 0, step, 0);
        HashMap<String, ArrayList<Number>> chartLineValue_BizProcTm = new HashMap<String, ArrayList<Number>>();
        HashMap<String, ArrayList<Number>> chartLineValue_GlblSize = new HashMap<String, ArrayList<Number>>();
        HashMap<String, ArrayList<Number>> chartLineValue_Throughput = new HashMap<String, ArrayList<Number>>();

        Date startDate = dateFormat.parse(fromDate);
        Date endDate = dateFormat.parse(toDate);
        int totalDataCount = data.A.getValidCount();

        while (!addedDate.after(endDate)) {
            eventId = data.A.no(0).scrAppId_A.getValue();
            for (i = 0; i < totalDataCount; i++) {

                Date opsTm = dateFormat.parse(data.A.no(i).opsTs_A.getValue());
                double processingTm = Double.parseDouble(nullToZero(data.A.no(i).bizElpsTmTxt_A.getValue()));
                double glblAreaSize = Double.parseDouble(data.A.no(i).glblAreaDataCnt_A.getValue().toString());
                double throughput = 0.0D;
                if (processingTm != 0.0D & glblAreaSize != 0.0D) {
                    throughput = glblAreaSize / processingTm;
                }

                if (data.A.no(i).scrAppId_A.getValue().equals(eventId) && (!opsTm.before(startDate) && !opsTm.after(addedDate))) {
                    sum_procTm += processingTm;
                    sum_glblSize += glblAreaSize;
                    sum_throughput += throughput;
                    if (sum_procTm != 0.0D) {
                        sum_cnt_procTm++;
                    }
                    if (sum_glblSize != 0.0D) {
                        sum_cnt_glblSize++;
                    }
                    if (sum_throughput != 0.0D) {
                        sum_cnt_throughput++;
                    }
                }
                if (!data.A.no(i).scrAppId_A.getValue().equals(eventId) || i == totalDataCount - 1) {
                    if (firstFlg) {
                        values_BizProcTm = new ArrayList<Number>();
                        values_GlblSize = new ArrayList<Number>();
                        values_Throughput = new ArrayList<Number>();
                        chartLineValue_BizProcTm.put(eventId + jvmNm, values_BizProcTm);
                        chartLineValue_GlblSize.put(eventId + jvmNm, values_GlblSize);
                        chartLineValue_Throughput.put(eventId + jvmNm, values_Throughput);
                        if (!data.A.no(i).scrAppId_A.getValue().equals(eventId) && i == totalDataCount - 1) {
                            ArrayList<Number> valuesLast_BizProcTm = new ArrayList<Number>();
                            ArrayList<Number> valuesLast_GlblSize = new ArrayList<Number>();
                            ArrayList<Number> valuesLast_Throughput = new ArrayList<Number>();
                            chartLineValue_BizProcTm.put(data.A.no(i).scrAppId_A.getValue() + jvmNm, valuesLast_BizProcTm);
                            chartLineValue_GlblSize.put(data.A.no(i).scrAppId_A.getValue() + jvmNm, valuesLast_GlblSize);
                            chartLineValue_Throughput.put(data.A.no(i).scrAppId_A.getValue() + jvmNm, valuesLast_Throughput);
                        }
                    } else {
                        values_BizProcTm = chartLineValue_BizProcTm.get(eventId + jvmNm);
                        values_GlblSize = chartLineValue_GlblSize.get(eventId + jvmNm);
                        values_Throughput = chartLineValue_Throughput.get(eventId + jvmNm);
                    }
                    if (sum_procTm == 0 || sum_cnt_procTm == 0) {
                        values_BizProcTm.add(0.0D);
                    } else {
                        values_BizProcTm.add((sum_procTm / sum_cnt_procTm) / 1000000D);
                    }
                    if (sum_glblSize == 0 || sum_cnt_glblSize == 0) {
                        values_GlblSize.add(0.0D);
                    } else {
                        values_GlblSize.add(sum_glblSize / sum_cnt_glblSize);
                    }
                    if (sum_throughput == 0 || sum_cnt_throughput == 0) {
                        values_Throughput.add(0.0D);
                    } else {
                        values_Throughput.add((sum_throughput / sum_cnt_throughput) * 1000000D);
                    }
                    sum_procTm = 0.0D;
                    sum_cnt_procTm = 0;
                    sum_glblSize = 0.0D;
                    sum_cnt_glblSize = 0;
                    sum_throughput = 0.0D;
                    sum_cnt_throughput = 0;

                    if ((!opsTm.before(startDate) && !opsTm.after(addedDate)) && i != totalDataCount - 1) {
                        sum_procTm += processingTm;
                        sum_glblSize += glblAreaSize;
                        sum_throughput += throughput;
                        if (sum_procTm != 0.0D) {
                            sum_cnt_procTm++;
                        }
                        if (sum_glblSize != 0.0D) {
                            sum_cnt_glblSize++;
                        }
                        if (sum_throughput != 0.0D) {
                            sum_cnt_throughput++;
                        }
                    }
                }
                if (!data.A.no(i).scrAppId_A.getValue().equals(eventId) && i == totalDataCount - 1) {
                    ArrayList<Number> valueLast_BizProcTm = chartLineValue_BizProcTm.get(data.A.no(i).scrAppId_A.getValue() + jvmNm);
                    ArrayList<Number> valueLast_GlblSize = chartLineValue_GlblSize.get(data.A.no(i).scrAppId_A.getValue() + jvmNm);
                    ArrayList<Number> valueLast_Throughput = chartLineValue_Throughput.get(data.A.no(i).scrAppId_A.getValue() + jvmNm);
                    if (!opsTm.before(startDate) && !opsTm.after(addedDate)) {
                        valueLast_BizProcTm.add(processingTm / 1000000D);
                        valueLast_GlblSize.add(glblAreaSize);
                        valueLast_Throughput.add(throughput * 1000000D);
                    } else {
                        valueLast_BizProcTm.add(0.0D);
                        valueLast_GlblSize.add(0.0D);
                        valueLast_Throughput.add(0.0D);
                    }
                }
                eventId = data.A.no(i).scrAppId_A.getValue();
            }
            Date lastStartDate = addedDate;

            xLabels.add(dateFormat2.format(addedDate).toString());

            if (lastFlg) {
                break;
            }

            addedDate = addDate(addedDate, 0, 0, 0, 0, step, 0);
            if (addedDate.before(endDate)) {
                startDate = addDate(startDate, 0, 0, 0, 0, step, 0);
                firstFlg = false;
            } else {
                startDate = lastStartDate;
                addedDate = endDate;
                lastFlg = true;
            }
        }
        chartData.setLineValues_ProcTm(chartLineValue_BizProcTm);
        chartData.setLineValues_GlblSize(chartLineValue_GlblSize);
        chartData.setLineValues_Throughput(chartLineValue_Throughput);
        chartData.setXAxleLabel(xLabels);
        return chartData;
    }

    public static Date addDate(Date fromDate, int addYear, int addMonth, int addDate, int addHour, int addMinuate, int addSecond) throws ParseException {

        Calendar cal = new GregorianCalendar();

        cal.setTime(fromDate);
        cal.add(Calendar.YEAR, +addYear);
        cal.add(Calendar.MONTH, +addMonth);
        cal.add(Calendar.DATE, +addDate);
        cal.add(Calendar.HOUR, +addHour);
        cal.add(Calendar.MINUTE, +addMinuate);
        cal.add(Calendar.SECOND, +addSecond);

        Date toDate = cal.getTime();
        return toDate;
    }

    public static void setChartDefaultAttr(ZZZL0030CMsg cMsg, Chart chart) {

        Text chartTitle = new Text(TITLE);
        chartTitle = chartTitle.setStyle(chartTitle.createStyle(TITLE_FONT_SIZE, TITLE_FONT_COLOR, TEXT_ALIGN_CENTER));

        // chart.setTitle(chartTitle);
        chart.setBackgroundColour(BACKGROUND_COLOR);

        YAxis ya = new YAxis();
        ya.setGridColour("#DDDEE1");
        ya.setColour("#96A9C5");
        ya.setOffset(false);
        chart.setYAxis(ya);

        XAxis xa = new XAxis();
        xa.setGridColour("#DDDEE1");
        xa.setColour("#96A9C5");
        xa.setOffset(true);
        chart.setXAxis(xa);

        Text yLeg = null;
        if (cMsg.xxYAxle.getValue().equalsIgnoreCase(Y_axle[0])) {
            yLeg = new Text(Y_LEGEND[0]);
        } else if (cMsg.xxYAxle.getValue().equalsIgnoreCase(Y_axle[1])) {
            yLeg = new Text(Y_LEGEND[1]);
        } else if (cMsg.xxYAxle.getValue().equalsIgnoreCase(Y_axle[2])) {
            yLeg = new Text(Y_LEGEND[2]);
        }
        yLeg.setStyle(yLeg.createStyle(10, "#000000", TEXT_ALIGN_CENTER));
        chart.setYLegend(yLeg);

        chart.setFixedNumDecimalsForced(false);
        chart.setDecimalSeparatorIsComma(false);
        chart.setNumDecimals(5);
        chart.setThousandSeparatorDisabled(false);
        // chart.computeYAxisRange(10);

        Tooltip tt = new Tooltip();
        tt.setShadow(true);
        chart.setTooltip(tt);

        return;
    }

    public static synchronized Chart createNewChart(ZZZL0030CMsg cMsg, ZZZL0030ChartBean dataForChart) {

        Chart chart = new Chart();
        colorIndex = 0;
        // Default setting for chart.
        ZZZL0030CommonLogic.setChartDefaultAttr(cMsg, chart);

        // Set Line-charts.
        HashMap<String, ArrayList<Number>> chartLineValue = null;
        if (cMsg.xxYAxle.getValue().equalsIgnoreCase(Y_axle[0])) {
            chartLineValue = dataForChart.getLineValues_ProcTm();
        } else if (cMsg.xxYAxle.getValue().equalsIgnoreCase(Y_axle[1])) {
            chartLineValue = dataForChart.getLineValues_GlblSize();
        } else if (cMsg.xxYAxle.getValue().equalsIgnoreCase(Y_axle[2])) {
            chartLineValue = dataForChart.getLineValues_Throughput();
        }
        Iterator iterator = chartLineValue.entrySet().iterator();

        ArrayList<Number> maxValues = new ArrayList<Number>();

        while (iterator.hasNext()) {
            Entry lineChartList = (Entry) iterator.next();
            String lineName = lineChartList.getKey().toString();
            ArrayList<Number> lineValues = (ArrayList<Number>) lineChartList.getValue();
            LineChart linChart = new LineChart();
            ArrayList<Dot> dotValues = new ArrayList<Dot>();
            linChart.setText(lineName);
            for (Number val : lineValues) {
                dotValues.add(new Dot(val, COLOR[colorIndex], 4, 2));
            }
            linChart.addDots(dotValues);
            linChart.setColour(COLOR[colorIndex]);
            Element e = linChart;
            e.setToggleVisibility();
            e.setGradientFill(true);
            chart.addElements(e);
            colorIndex++;
            if (colorIndex >= COLOR.length) {
                colorIndex = 0;
            }
            // gathering max values of each line chart.
            maxValues.add(linChart.getMaxValue());
        }
        // Set X-Axle's labels.
        XAxisLabels xLabels = new XAxisLabels(dataForChart.getXAxleLabel());
        xLabels.setRotation(Label.Rotation.VERTICAL);
        chart.setXAxis(chart.getXAxis().setXAxisLabels(xLabels));
        // Set Y-Axle's Max, Min value.
        double max = ZZZL0030CommonLogic.maxVal(maxValues);
        if (max != 0.0D) {
            max += 10D-(max%10D);
            double step = max / 10.0D;
            chart.getYAxis().setMax(max);
            chart.getYAxis().setMin(0.0D);
            chart.getYAxis().setSteps(step);
        }
        return chart;
    }

    public static Chart addElement(ZZZL0030ChartBean currentData, ZZZL0030ChartBean newData, ZZZL0030CMsg cMsg) {

        Iterator iterator_BizProcTm = newData.getLineValues_ProcTm().entrySet().iterator();
        Iterator iterator_GlblAreaSize = newData.getLineValues_GlblSize().entrySet().iterator();
        Iterator iterator_Throughput = newData.getLineValues_Throughput().entrySet().iterator();

        while (iterator_BizProcTm.hasNext()) {
            Entry lineChartList = (Entry) iterator_BizProcTm.next();
            String lineName = lineChartList.getKey().toString();
            if (currentData.getLineValues_ProcTm().get(lineName) == null) {
                ArrayList<Number> lineValues = (ArrayList<Number>) lineChartList.getValue();
                currentData.getLineValues_ProcTm().put(lineName, lineValues);
            }
        }
        while (iterator_GlblAreaSize.hasNext()) {
            Entry lineChartList = (Entry) iterator_GlblAreaSize.next();
            String lineName = lineChartList.getKey().toString();
            if (currentData.getLineValues_GlblSize().get(lineName) == null) {
                ArrayList<Number> lineValues = (ArrayList<Number>) lineChartList.getValue();
                currentData.getLineValues_GlblSize().put(lineName, lineValues);
            }
        }
        while (iterator_Throughput.hasNext()) {
            Entry lineChartList = (Entry) iterator_Throughput.next();
            String lineName = lineChartList.getKey().toString();
            if (currentData.getLineValues_Throughput().get(lineName) == null) {
                ArrayList<Number> lineValues = (ArrayList<Number>) lineChartList.getValue();
                currentData.getLineValues_Throughput().put(lineName, lineValues);
            }
        }
        return createNewChart(cMsg, currentData);
    }

//    public static void outputChartDataFile(ZZZL0030CMsg cMsg, Chart chart) {
//        // Create a data file(JSON file) for Chart.
//        byte[] buff = null;
//        buff = chart.toString().getBytes();
//        buff = chart.toDebugString().getBytes();
//        StringBuilder fileName = new StringBuilder();
//        fileName.append("Chart_Data_");
//        fileName.append(ZZZL0030CommonLogic.dateFormat.format(new Date()));
//        cMsg.xxFileData.setTempFilePath(null, fileName.toString(), ".json");
//        ZYPFileWriter.writeFile(cMsg.xxFileData.getTempFilePath(), buff);
//    }

    public static double maxVal(ArrayList<Number> n) {
        double max = n.get(0).doubleValue();

        for (int i = 1; i < n.size(); i++)
            if (n.get(i).doubleValue() > max) {
                max = n.get(i).doubleValue();
            }
        return Math.round(max);
    }

    public static double minVal(ArrayList<Number> n) {
        double min = n.get(0).doubleValue();

        for (int i = 1; i < n.size(); i++)
            if (n.get(i).doubleValue() < min) {
                min = n.get(i).doubleValue();
            }
        return Math.round(min);
    }

    /**
     * Method name: nvl
     * <dd>The method explanation:
     * <dd>Remarks:
     * @param str String
     * @return String
     */
    public static String nvl(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    public static String nullToZero(String val) {

        if (val == null || val.equals("")) {
            return "0.0D";
        }
        return val;
    }

    /**
     * Method name: chkGlbCmpCd
     * <dd>The method explanation: Search GLBL_CMPY table by Primary
     * Key
     * @param cMsg ZZPL0090CMsg
     * @return boolean true or false
     */
    public static boolean chkGlbCmpCd(ZZZL0030CMsg cMsg) {

        String glblCmpyCd = cMsg.glblCmpyCd.getValue();

        // Search GLBL_CMPY table by Primary Key
        GLBL_CMPYTMsg tMsg = new GLBL_CMPYTMsg();

        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (tMsg == null) {
            cMsg.glblCmpyCd.setErrorInfo(1, "ZZZM9006E", new String[] {"Global Company Code" });
            return false;
        }
        return true;
    }

    public static void getJvmNm(ZZZL0030CMsg cMsg) {

        S21SsmEZDResult ssmResult = ZZZL0030Query.getInstance().getJvmNm(cMsg);

        // Set default value.
        cMsg.jvmNm_C.no(0).setValue("All");
        cMsg.jvmNm_D.no(0).setValue("All");

        int i = 0;
        List resultList = (List) ssmResult.getResultObject();

        // Set jvm name to cMsg
        for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
            i++;
            Map resultMap = (Map) iterator.next();
            String jvm = ZZZL0030CommonLogic.nvl((String) resultMap.get("JVM_NM"));
            cMsg.jvmNm_C.no(i).setValue(jvm);
            cMsg.jvmNm_D.no(i).setValue(jvm);
        }
        return;
    }

    // public static ZZZL0030ChartBean getDummyData() {
    // ZZZL0030ChartBean chartData = new ZZZL0030ChartBean();
    //  
    // HashMap<String, ArrayList<Number>> chartLineValue = new
    // HashMap<String, ArrayList<Number>>();
    // ArrayList<String> xLabels = new ArrayList<String>();
    //  
    // ArrayList<Number> a1 = new ArrayList<Number>();
    // ArrayList<Number> a2 = new ArrayList<Number>();
    // ArrayList<Number> a3 = new ArrayList<Number>();
    // ArrayList<Number> a4 = new ArrayList<Number>();
    //  
    // int i;
    // int j;
    //  
    // for (i=0;i<31;i++) {
    // a1.add(Math.random());
    // a2.add(Math.random());
    // a3.add(Math.random());
    // a4.add(Math.random());
    // }
    // for (j=1;j<31;j++) {
    // xLabels.add(String.valueOf(j)+"-Day");
    // }
    // chartLineValue.put("ZZZL0030Scrn00_INIT",a1);
    // chartLineValue.put("ZZZL0030Scrn00_Search", a2);
    // chartLineValue.put("ZZZL0030Scrn00_Submit", a3);
    // chartLineValue.put("ZZZL0030Scrn00_Delete", a4);
    //  
    // chartData.setLineValues(chartLineValue);
    // chartData.setXAxleLabel(xLabels);
    //  
    // return chartData;
    // }
    //
    // public static ZZZL0030ChartBean getDummyData2() {
    // ZZZL0030ChartBean chartData = new ZZZL0030ChartBean();
    //  
    // HashMap<String, ArrayList<Number>> chartLineValue = new
    // HashMap<String, ArrayList<Number>>();
    //  
    // ArrayList<Number> a1 = new ArrayList<Number>();
    //  
    // int i;
    //  
    // for (i=0;i<31;i++) {
    // a1.add(Math.random());
    // }
    // chartLineValue.put("DBCL1234Scrn00_Return", a1);
    //  
    // chartData.setLineValues(chartLineValue);
    // return chartData;
    // }
}
