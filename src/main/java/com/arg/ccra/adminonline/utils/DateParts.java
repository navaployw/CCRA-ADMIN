/*
 * $Id: DateParts.java,v 2.0 2007/09/12 12:20:29 chalermp Exp $
 *
 * Copyright (C) 2007 Advanced Research Group Co., Ltd. (ARG).
 *
 * All Rights Researved.
 */


package com.arg.ccra.adminonline.utils;

import java.util.Calendar;
import java.util.Locale;

/**
 * @deprecated Use com.arg.util.DateParts instead.
 */
public class DateParts
{
    //~ Static fields/initializers ---------------------------------------------

    /**
     * DOCUMENT ME!
     */
    public static final int JANUARY = 1;

    /**
     * DOCUMENT ME!
     */
    public static final int FEBRUARY = 2;

    /**
     * DOCUMENT ME!
     */
    public static final int MARCH = 3;

    /**
     * DOCUMENT ME!
     */
    public static final int APRIL = 4;

    /**
     * DOCUMENT ME!
     */
    public static final int MAY = 5;

    /**
     * DOCUMENT ME!
     */
    public static final int JUNE = 6;

    /**
     * DOCUMENT ME!
     */
    public static final int JULY = 7;

    /**
     * DOCUMENT ME!
     */
    public static final int AUGUST = 8;

    /**
     * DOCUMENT ME!
     */
    public static final int SEPTEMBER = 9;

    /**
     * DOCUMENT ME!
     */
    public static final int OCTOBER = 10;

    /**
     * DOCUMENT ME!
     */
    public static final int NOVEMBER = 11;

    /**
     * DOCUMENT ME!
     */
    public static final int DECEMBER = 12;

    //~ Instance fields --------------------------------------------------------

    /**
     * DOCUMENT ME!
     */
    public String date = null;

    /**
     * DOCUMENT ME!
     */
    public String hour = null;

    /**
     * DOCUMENT ME!
     */
    public String minute = null;

    /**
     * DOCUMENT ME!
     */
    public String month = null;

    /**
     * DOCUMENT ME!
     */
    public String second = null;

    /**
     * DOCUMENT ME!
     */
    public String year = null;

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Calendar getCalendar()
    {
        return getCalendar(Locale.ENGLISH);
    } // end getCalendar()

    /**
     * DOCUMENT ME!
     *
     * @param locale DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Calendar getCalendar(final Locale locale)
    {
        int year = Integer.parseInt(this.year = this.year.trim());
        int month =
            mapCalendarMONTH(Integer.parseInt(this.month = this.month.trim()));
        int date = Integer.parseInt(this.date = this.date.trim());
        int hour =
            Integer.parseInt(((null == this.hour)
                    || ((this.hour = this.hour.trim()).length() == 0)) ? "0"
                                                                       : this.hour);
        int minute =
            Integer.parseInt(((null == this.minute)
                    || ((this.minute = this.minute.trim()).length() == 0))
                ? "0" : this.minute);
        int second =
            Integer.parseInt(((null == this.second)
                    || ((this.second = this.second.trim()).length() == 0))
                ? "0" : this.second);
        Calendar calendar = Calendar.getInstance(locale);
        calendar.set(year, month, date, hour, minute, second);
        calendar.clear(Calendar.MILLISECOND);

        return calendar;
    } // end getCalendar()

    /**
     * DOCUMENT ME!
     *
     * @param givenMonth DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static int mapCalendarMONTH(final int givenMonth)
    {
        switch (givenMonth)
        {
        case JANUARY:
            return Calendar.JANUARY;

        case FEBRUARY:
            return Calendar.FEBRUARY;

        case MARCH:
            return Calendar.MARCH;

        case APRIL:
            return Calendar.APRIL;

        case MAY:
            return Calendar.MAY;

        case JUNE:
            return Calendar.JUNE;

        case JULY:
            return Calendar.JULY;

        case AUGUST:
            return Calendar.AUGUST;

        case SEPTEMBER:
            return Calendar.SEPTEMBER;

        case OCTOBER:
            return Calendar.OCTOBER;

        case NOVEMBER:
            return Calendar.NOVEMBER;

        case DECEMBER:
            return Calendar.DECEMBER;

        default:

            String err_msg = "'" + givenMonth + "' is not a valid month index!";
            throw new RuntimeException(err_msg);
        } // end switch
    } // end mapCalendarMONTH()

    /**
     * DOCUMENT ME!
     *
     * @param month DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static int mapRealWorldMonth(final int month)
    {
        switch (month)
        {
        case Calendar.JANUARY:
            return JANUARY;

        case Calendar.FEBRUARY:
            return FEBRUARY;

        case Calendar.MARCH:
            return MARCH;

        case Calendar.APRIL:
            return APRIL;

        case Calendar.MAY:
            return MAY;

        case Calendar.JUNE:
            return JUNE;

        case Calendar.JULY:
            return JULY;

        case Calendar.AUGUST:
            return AUGUST;

        case Calendar.SEPTEMBER:
            return SEPTEMBER;

        case Calendar.OCTOBER:
            return OCTOBER;

        case Calendar.NOVEMBER:
            return NOVEMBER;

        case Calendar.DECEMBER:
            return DECEMBER;

        default:

            String err_msg = "'" + month + "' is not a valid month digit!";
            throw new RuntimeException(err_msg);
        } // end switch
    } // end mapRealWorldMonth()

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String toString()
    {
        return "year: " + year + ", month: " + month + ", date: " + date
        + ", hour: " + hour + ", minute: " + minute + ", second: " + second;
    } // end toString()

    /**
     * DOCUMENT ME!
     *
     * @param calendar DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static DateParts getInstance(final Calendar calendar)
    {
        DateParts result = new DateParts();
        result.year = String.valueOf(calendar.get(Calendar.YEAR));
        result.month = String.valueOf(mapRealWorldMonth(calendar.get(
                        Calendar.MONTH)));
        result.date = String.valueOf(calendar.get(Calendar.DATE));
        result.hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        result.minute = String.valueOf(calendar.get(Calendar.MINUTE));
        result.second = String.valueOf(calendar.get(Calendar.SECOND));

        return result;
    } // end getInstance()

    /**
     * DOCUMENT ME!
     */
    public void padLeft()
    {
        if ((null == this.year) || (this.year.length() == 0))
        {
            this.year = "0000";
        } // end if
        else
        {
            switch (this.year.length())
            {
            case 3:
                this.year = "0" + this.year;

                break;

            case 2:
                this.year = "00" + this.year;

                break;

            case 1:
                this.year = "000" + this.year;

                break;
            } // end switch
        } // end else

        if ((null == this.month) || (this.month.length() == 0))
        {
            this.month = "00";
        } // end if
        else if (this.month.length() == 1)
        {
            this.month = "0" + this.month;
        } // end else if

        if ((null == this.date) || (this.date.length() == 0))
        {
            this.date = "00";
        } // end if
        else if (this.date.length() == 1)
        {
            this.date = "0" + this.date;
        } // end else if

        if ((null == this.hour) || (this.hour.length() == 0))
        {
            this.hour = "00";
        } // end if
        else if (this.hour.length() == 1)
        {
            this.hour = "0" + this.hour;
        } // end else if

        if ((null == this.minute) || (this.minute.length() == 0))
        {
            this.minute = "00";
        } // end if
        else if (this.minute.length() == 1)
        {
            this.minute = "0" + this.minute;
        } // end else if

        if ((null == this.second) || (this.second.length() == 0))
        {
            this.second = "00";
        } // end if
        else if (this.second.length() == 1)
        {
            this.second = "0" + this.second;
        } // end else if
    } // end padLeft()
} // end DateParts
