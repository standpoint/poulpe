/**
 * Copyright (C) 2012  JTalks.org Team
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.jtalks.poulpe.util.databasebackup.logic.impl;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;

/**
 * Test joining array of Strings into one String functionality.
 * 
 * @author Evgeny Surovtsev
 * 
 */
public class SqlTableDumpUtilTest {
    /**
     * Base testing - join plain array of Strings.
     */
    @Test
    public final void joiningListTest() {
        List<String> list = Arrays.asList("1", "2", "3");
        assertEquals(SqlTableDumpUtil.joinStrings(list, ",").toString(), "1,2,3");
        assertEquals(SqlTableDumpUtil.joinStrings(list, "").toString(), "123");
        assertEquals(SqlTableDumpUtil.joinStrings(list, "<separator>").toString(), "1<separator>2<separator>3");
    }

    /**
     * Joining of empty array must return empty String.
     */
    @Test
    public final void emptyListReturnsEmptyStringTest() {
        List<String> list = Lists.newArrayList();
        assertEquals(SqlTableDumpUtil.joinStrings(list, ",").toString().length(), 0);
    }

    /**
     * Joining of null array or joining with null separator must throw an Exception.
     */
    @Test
    public final void nullListOrSeparatorGenerateExceptionTest() {
        try {
            SqlTableDumpUtil.joinStrings(null, ",");
            fail("Null list must throws NullPointerException.");
        } catch (NullPointerException e) {
            // doing nothing - exception is expected
        }
        try {
            SqlTableDumpUtil.joinStrings(new ArrayList<String>(), null);
            fail("Null separator must throws NullPointerException.");
        } catch (NullPointerException e) {
            // doing nothing - exception is expected
        }
    }
}
