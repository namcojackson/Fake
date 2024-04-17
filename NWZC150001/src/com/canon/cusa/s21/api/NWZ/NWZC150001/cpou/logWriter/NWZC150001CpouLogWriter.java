package com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.logWriter;

public class NWZC150001CpouLogWriter {
    /** Writing Log Flag */
    private static final boolean WRITE_LOG = false;

    public static void writePerformanceProfilingLog(Class clazz, String str) {
        if (!WRITE_LOG) {
            return;
        }
        writePerformanceProfilingLog(clazz.getSimpleName(), str);
    }

    public static void writePerformanceProfilingLogEnd(Class clazz, String methodNm) {
        if (!WRITE_LOG) {
            return;
        }
        writePerformanceProfilingLogEnd(clazz.getSimpleName(), methodNm);
    }

    public static void writePerformanceProfilingLogStart(Class clazz, String methodNm) {
        if (!WRITE_LOG) {
            return;
        }
        writePerformanceProfilingLogStart(clazz.getSimpleName(), methodNm);
    }

    public static void writePerformanceProfilingLog(String classNm, String str) {
        if (!WRITE_LOG) {
            return;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("#Performance Profiling# [").append(classNm).append("] : ").append(str);
        System.out.println(sb.toString());
    }

    public static void writePerformanceProfilingLogEnd(String classNm, String methodNm) {
        if (!WRITE_LOG) {
            return;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("#Performance Profiling# [").append(classNm).append("] : ").append(methodNm).append(" - [E N D] ").append(System.currentTimeMillis());
        System.out.println(sb.toString());
    }

    public static void writePerformanceProfilingLogStart(String classNm, String methodNm) {
        if (!WRITE_LOG) {
            return;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("#Performance Profiling# [").append(classNm).append("] : ").append(methodNm).append(" - [START] ").append(System.currentTimeMillis());
        System.out.println(sb.toString());
    }
}
