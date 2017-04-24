package org.dolphin.commons;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

public class JuintTest {

	@Test
	public void test() {
		System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm"));
		System.out.println(DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.format(new Date()));
		System.out.println(DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date()));
		System.out.println(DateFormatUtils.format(DateUtils.addYears(new Date(), -1), "yyyy-MM-dd HH:mm"));
	}

}
