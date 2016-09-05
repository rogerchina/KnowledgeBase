/*
 * The contents of this file are copyright (c) 2016 by medavis GmbH, Karlsruhe, Germany
 */
package com.debuglife.codelabs;

import java.text.DateFormat.Field;
import java.util.Calendar;
import java.util.Date;

public class TestMyDate {
    public static void main(String[] args) {
	long time = 1383494400000l;
	Date d = new Date(time);
	System.out.println(d);
	
	Calendar c = Calendar.getInstance();
	c.setTimeInMillis(time);
	
	System.out.println(c.get(Field.MONTH.getCalendarField()));
    }
 }
