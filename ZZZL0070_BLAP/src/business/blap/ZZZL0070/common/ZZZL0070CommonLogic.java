package business.blap.ZZZL0070.common;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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
import business.blap.ZZZL0070.ZZZL0070CMsg;
import business.blap.ZZZL0070.constant.ZZZL0070Constant;
import business.db.GLBL_CMPYTMsg;

public class ZZZL0070CommonLogic implements ZZZL0070Constant {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    private static final SimpleDateFormat dateFormat2 = new SimpleDateFormat("MMM dd", Locale.US);

    public static int colorIndex = 0;

    /**
     * Get step (It'll be used for dividing x-Axle.)
     */
    public static synchronized List<String> getXaxleStep(String fromDt, String toDt) {

        List<String> step = new ArrayList<String>();
        
        try {
            Date startDate = dateFormat.parse(fromDt);
            Date endDate = dateFormat.parse(toDt);
            Date nextDay = startDate;
            
            step.add(dateFormat.format(startDate));
            
            do {
                nextDay = addDate(nextDay, 0, 0, 1, 0, 0, 0);
                step.add(dateFormat.format(nextDay));
            } while (nextDay.before(endDate));
        } catch (ParseException e) {
        }
        return step;
    }
    public static synchronized List<String> getXaxleStep2(String fromDt, String toDt) {

        List<String> step = new ArrayList<String>();
        
        try {
            Date startDate = dateFormat.parse(fromDt);
            Date endDate = dateFormat.parse(toDt);
            Date nextDay = startDate;
            
            step.add(dateFormat2.format(startDate));
            
            do {
                nextDay = addDate(nextDay, 0, 0, 1, 0, 0, 0);
                step.add(dateFormat2.format(nextDay));
            } while (nextDay.before(endDate));
        } catch (ParseException e) {
        }
        return step;
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

    private static void setChartDefaultAttr(ZZZL0070CMsg cMsg, Chart chart, int chartIndex) {        
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
        xa.setStroke(3);
        chart.setXAxis(xa);


        Text yLeg = new Text(Y_LEGEND[chartIndex]);
        yLeg.setStyle(yLeg.createStyle(11, "#000000", TEXT_ALIGN_CENTER));
        chart.setYLegend(yLeg);

        chart.setFixedNumDecimalsForced(true);
        chart.setDecimalSeparatorIsComma(false);
        chart.setThousandSeparatorDisabled(false);
        
        if (chartIndex == 1) {
            chart.setNumDecimals(1);
        } else {
            chart.setNumDecimals(0);
        }

        Tooltip tt = new Tooltip();
        tt.setShadow(true);
        chart.setTooltip(tt);

        return;
    }

    public static synchronized Chart createNewChart(ZZZL0070CMsg cMsg, HashMap<String, ArrayList<Number>> chartLineValue, List<String> xLabel, int chartIndex) {

        Chart chart = new Chart();
        colorIndex = 0;
        // Default setting for chart.
        ZZZL0070CommonLogic.setChartDefaultAttr(cMsg, chart, chartIndex);

        // Set Line-charts.
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
        XAxisLabels xLabels = new XAxisLabels(xLabel);
        xLabels.setRotation(Label.Rotation.VERTICAL);
        chart.setXAxis(chart.getXAxis().setXAxisLabels(xLabels));
        // Set Y-Axle's Max, Min value.
        double max = ZZZL0070CommonLogic.maxVal(maxValues);
        if (max != 0D) {
            max += 10D-(max%10D);
            double step = max / 10D;
            chart.getYAxis().setMax(max);
            chart.getYAxis().setSteps(step);
        }
        return chart;
    }

    public static double maxVal(ArrayList<Number> n) {
        double max = n.get(0).doubleValue();

        for (int i = 1; i < n.size(); i++)
            if (n.get(i).doubleValue() > max) {
                max = n.get(i).doubleValue();
            }
        return Math.round(max);
    }
    
    public static double maxIntVal(ArrayList<Number> n) {
        int max = n.get(0).intValue();

        for (int i = 1; i < n.size(); i++)
            if (n.get(i).intValue() > max) {
                max = n.get(i).intValue();
            }
        return max;
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
    
    public static BigDecimal round(double val, int digit) {
        String pattern = "";
        if (digit == 0) {
            pattern = "0";
        } else if (digit == 1) {
            pattern = "0.#";
        } else if (digit == 2) {
            pattern = "0.##";
        } else if (digit == 3) {
            pattern = "0.###";
        }
        DecimalFormat df =  new DecimalFormat(pattern);
        return new BigDecimal(df.format(val));
    }

    /**
     * Method name: chkGlbCmpCd
     * <dd>The method explanation: Search GLBL_CMPY table by Primary
     * Key
     * @param cMsg ZZPL0090CMsg
     * @return boolean true or false
     */
    public static boolean chkGlbCmpCd(ZZZL0070CMsg cMsg) {

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
    
    public static synchronized boolean isBeforeToday(String date) {
        
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        String sToday = dateFormat.format(today);
        
        Date targetDay;
        
        try {
            targetDay = dateFormat.parse(date);
            if (targetDay.before(dateFormat.parse(sToday))) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
        
    }

    // public static ZZZL0070ChartBean getDummyData() {
    // ZZZL0070ChartBean chartData = new ZZZL0070ChartBean();
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
    // chartLineValue.put("ZZZL0070Scrn00_INIT",a1);
    // chartLineValue.put("ZZZL0070Scrn00_Search", a2);
    // chartLineValue.put("ZZZL0070Scrn00_Submit", a3);
    // chartLineValue.put("ZZZL0070Scrn00_Delete", a4);
    //  
    // chartData.setLineValues(chartLineValue);
    // chartData.setXAxleLabel(xLabels);
    //  
    // return chartData;
    // }
    //
    // public static ZZZL0070ChartBean getDummyData2() {
    // ZZZL0070ChartBean chartData = new ZZZL0070ChartBean();
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
    // chartLineValue.put("ABCL1234Scrn00_Return", a1);
    //  
    // chartData.setLineValues(chartLineValue);
    // return chartData;
    // }
}
