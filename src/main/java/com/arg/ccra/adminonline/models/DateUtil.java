/**
 * Author(s): Thachapon
 * Date:      13 July 2004
 * Copyright Notice:
 *     Copyright 2003 Advanced Research Group Co., Ltd. All rights reserved.
 *     ARG PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/*
 * Created on 21 ??.?. 2547 Window - Preferences - Java - Code Generation - Code
 * and Comments
 */
package com.arg.ccra.adminonline.models;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author Thachapon Window - Preferences - Java - Code Generation - Code and
 * Comments
 */
public class DateUtil
{
    //~ Static fields/initializers ---------------------------------------------

    /**
     * DOCUMENT ME!
     */
    static String LONG_FORMAT_PATTERN = "dd MMMM yyyy";

    /**
     * DOCUMENT ME!
     */
    static String SHORT_FORMAT_PATTERN = "MMMM yyyy";

    /**
     * DOCUMENT ME!
     */
    static String FULL_FORMAT_PATTERN = "dd MMMM yyyy HH:mm";

    /**
     * DOCUMENT ME!
     */
    static String TIME_FORMAT_PATTERN = "HH:mm";

    /**
     * DOCUMENT ME!
     */
    static String SQL_FORMAT_PATTERN = "yyyyMMdd";

    /**
     * DOCUMENT ME!
     */
    public static DateUtil SHORT_FORMAT = new DateUtil(1);

    /**
     * DOCUMENT ME!
     */
    public static DateUtil LONG_FORMAT = new DateUtil(2);

    /**
     * DOCUMENT ME!
     */
    public static DateUtil FULL_FORMAT = new DateUtil(3);

    /**
     * DOCUMENT ME!
     */
    public static DateUtil TIME_FORMAT = new DateUtil(4);

    /**
     * DOCUMENT ME!
     */
    public static DateUtil SQL_FORMAT = new DateUtil(5);

    /**
     * DOCUMENT ME!
     */
    static SimpleDateFormat longFormat = null;

    /**
     * DOCUMENT ME!
     */
    static SimpleDateFormat shortFormat = null;

    //~ Instance fields --------------------------------------------------------

    /**
     * DOCUMENT ME!
     */
    int source = 1;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new DateUtil object.
     *
     * @param i DOCUMENT ME!
     */
    public DateUtil(int i)
    {
        source = i;
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * @param date
     * @param format
     * @return
     */
    public static String convertToString(final Date date, final DateUtil format)
    {
        return convertToString(date, Locale.getDefault(), format);
    }

    /**
     * @param time
     * @param format
     * @return
     */
    public static String convertToString(final long time, final DateUtil format)
    {
        return convertToString(time, Locale.getDefault(), format);
    }

    /**
     * @param date
     * @param docLang
     * @param format
     * @return
     */
    public static String convertToString(final Date date,
        final DocumentLanguage docLang, final DateUtil format)
    {
        return convertToString(date, docLang.getLocale(), format);
    }

    /**
     * @deprecated
     * @param date
     * @param docLang
     * @param format
     * @return
     */
    public static String convertToString(final Date date, final String docLang,
        final DateUtil format)
    {
        return convertToString(date, new DocumentLanguage(docLang), format);
    }

    /**
     * @param date
     * @param locale
     * @param format
     * @return
     */
    public static String convertToString(final Date date, final Locale locale,
        final DateUtil format)
    {
        if (null == date)
        {
            return "";
        }

        return convertToString(date.getTime(), locale, format);
    }

    /**
     * @param time
     * @param docLang
     * @param format
     * @return
     */
    public static String convertToString(final long time,
        final DocumentLanguage docLang, final DateUtil format)
    {
        return convertToString(time, docLang.getLocale(), format);
    }

    /**
     * @deprecated
     * @param time
     * @param docLang
     * @param format
     * @return
     */
    public static String convertToString(final long time, final String docLang,
        final DateUtil format)
    {
        return convertToString(time, new DocumentLanguage(docLang), format);
    }

    /**
     * @param time
     * @param locale
     * @param format
     * @return
     */
    public static String convertToString(final long time, final Locale locale,
        final DateUtil format)
    {
        if (0 == time)
        {
            return "";
        }

        Date date = new Date();

        date.setTime(time);

        if (DateUtil.FULL_FORMAT == format)
        {
            return new SimpleDateFormat(DateUtil.FULL_FORMAT_PATTERN, locale)
                .format(date);
        }

        if (DateUtil.SHORT_FORMAT == format)
        {
            return new SimpleDateFormat(DateUtil.SHORT_FORMAT_PATTERN, locale)
                .format(date);
        }

        if (DateUtil.TIME_FORMAT == format)
        {
            return new SimpleDateFormat(DateUtil.TIME_FORMAT_PATTERN, locale)
                .format(date);
        }

        if (DateUtil.SQL_FORMAT == format)
        {
            return new SimpleDateFormat(DateUtil.SQL_FORMAT_PATTERN, locale)
                .format(date);
        }

        return new SimpleDateFormat(DateUtil.LONG_FORMAT_PATTERN, locale).format(date);
    }

    /**
     * @param time
     * @param docLang
     * @return
     * @throws DateFormatException
     */
    public static String convertToString(final String time,
        final DocumentLanguage docLang)
        throws DateFormatException
    {
        return convertToString(time, docLang.getLocale());
    }

    /**
     * @deprecated
     * @param time
     * @param docLang
     * @return
     * @throws DateFormatException
     */
    public static String convertToString(final String time, final String docLang)
        throws DateFormatException
    {
        return convertToString(time, new DocumentLanguage(docLang));
    }

    /**
     * @param time
     * @param locale
     * @return
     * @throws DateFormatException
     */
    public static String convertToString(final String time, final Locale locale)
        throws DateFormatException
    {
        if (null == time)
        {
            return "";
        }

        String d = time.trim();
        int value = 0;
        Calendar cal = Calendar.getInstance(locale);
        cal.clear();

        if (d.length() == 6)
        {
            value = Integer.parseInt(d.substring(0, 4));
            cal.set(Calendar.YEAR, value);

            value = Integer.parseInt(d.substring(4));
            cal.set(Calendar.MONTH, value - 1);

            return convertToString(cal.getTime(), locale, DateUtil.SHORT_FORMAT);
        }
        else if (d.length() == 8)
        {
            value = Integer.parseInt(d.substring(0, 4));
            cal.set(Calendar.YEAR, value);

            value = Integer.parseInt(d.substring(6));
            cal.set(Calendar.DATE, value);

            value = Integer.parseInt(d.substring(4, 6));
            cal.set(Calendar.MONTH, value - 1);

            return convertToString(cal.getTime(), locale, DateUtil.LONG_FORMAT);
        }
        else
        {
            throw new DateFormatException();
        }
    }

    /**
     * return different of months.
     *
     * @param beginPeriod
     * @param endPeriod
     * @return
     * @throws com.arg.ccra3.online.model.DateFormatException
     */
    public static int dateDiff(final Date beginPeriod, final Date endPeriod)
        throws DateFormatException
    {
        Calendar begin = Calendar.getInstance();
        begin.setTime(beginPeriod);
        begin.clear(Calendar.DATE);

        Calendar end = Calendar.getInstance();
        end.setTime(endPeriod);
        end.clear(Calendar.DATE);

        int value = end.getTime().compareTo(begin.getTime());

        if (value == 0)
        {
            return 0;
        }
        else if (value < 0)
        {
            throw new DateFormatException();
        }
        else
        {
            int beginMonths =
                (begin.get(Calendar.YEAR) * 12) + begin.get(Calendar.MONTH);
            int endMonths =
                (end.get(Calendar.YEAR) * 12) + end.get(Calendar.MONTH);

            return endMonths - beginMonths;
        }
    }

    /**
     * return different of months.
     *
     * @param beginPeriod
     * @param endPeriod
     * @return
     * @throws DateFormatException
     */
    public static int dateDiff(final String beginPeriod, final String endPeriod)
        throws DateFormatException
    {
        if ((null == beginPeriod) || (null == endPeriod))
        {
            throw new DateFormatException();
        }

        String begin = beginPeriod.trim();
        String end = endPeriod.trim();

        if ((begin.length() != 6) || (end.length() != 6))
        {
            throw new DateFormatException();
        }

        int value = 0;
        Date beginDate = null;
        Date endDate = null;

        Calendar cal = Calendar.getInstance();
        cal.clear();

        value = Integer.parseInt(beginPeriod.substring(0, 4));
        cal.set(Calendar.YEAR, value);

        value = Integer.parseInt(beginPeriod.substring(4));
        cal.set(Calendar.MONTH, value - 1);

        beginDate = cal.getTime();

        cal.clear();

        value = Integer.parseInt(endPeriod.substring(0, 4));
        cal.set(Calendar.YEAR, value);

        value = Integer.parseInt(endPeriod.substring(4));
        cal.set(Calendar.MONTH, value - 1);

        endDate = cal.getTime();

        return dateDiff(beginDate, endDate);
    }
}
