/*
 * This file is part of USC
 * Copyright (C) 2016 - 2018 USC developer team.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package co.usc.test.dsltest;

import co.usc.test.dsl.DslCommand;
import co.usc.test.dsl.DslParser;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ajlopez on 8/6/2016.
 */
public class DslParserTest {
    @Test
    public void getNoCommandFromEmptyString() {
        DslParser parser = new DslParser("");

        Assert.assertNull(parser.nextCommand());
    }

    @Test
    public void parseSimpleCommand() {
        DslParser parser = new DslParser("do arg1 arg2");

        DslCommand cmd = parser.nextCommand();

        Assert.assertNotNull(cmd);
        Assert.assertTrue(cmd.isCommand("do"));
        Assert.assertEquals(2, cmd.getArity());
        Assert.assertEquals("arg1", cmd.getArgument(0));
        Assert.assertEquals("arg2", cmd.getArgument(1));

        Assert.assertNull(parser.nextCommand());
    }

    @Test
    public void parseSimpleCommandWithAdditionalSpacesAndTabs() {
        DslParser parser = new DslParser("        do\t\t    arg1  \t    arg2    ");

        DslCommand cmd = parser.nextCommand();

        Assert.assertNotNull(cmd);
        Assert.assertTrue(cmd.isCommand("do"));
        Assert.assertEquals(2, cmd.getArity());
        Assert.assertEquals("arg1", cmd.getArgument(0));
        Assert.assertEquals("arg2", cmd.getArgument(1));

        Assert.assertNull(parser.nextCommand());
    }

    @Test
    public void parseSimpleCommandSkippingComment() {
        DslParser parser = new DslParser("do arg1 arg2 # this is a comment");

        DslCommand cmd = parser.nextCommand();

        Assert.assertNotNull(cmd);
        Assert.assertTrue(cmd.isCommand("do"));
        Assert.assertEquals(2, cmd.getArity());
        Assert.assertEquals("arg1", cmd.getArgument(0));
        Assert.assertEquals("arg2", cmd.getArgument(1));

        Assert.assertNull(parser.nextCommand());
    }

    @Test
    public void parseSimpleCommandSkippingEmptyLines() {
        DslParser parser = new DslParser("   \ndo arg1 arg2\n   ");

        DslCommand cmd = parser.nextCommand();

        Assert.assertNotNull(cmd);
        Assert.assertTrue(cmd.isCommand("do"));
        Assert.assertEquals(2, cmd.getArity());
        Assert.assertEquals("arg1", cmd.getArgument(0));
        Assert.assertEquals("arg2", cmd.getArgument(1));

        Assert.assertNull(parser.nextCommand());
    }

    @Test
    public void parseSimpleCommandSkippingCommentLines() {
        DslParser parser = new DslParser("# first comment   \ndo arg1 arg2\n  # second comment   ");

        DslCommand cmd = parser.nextCommand();

        Assert.assertNotNull(cmd);
        Assert.assertTrue(cmd.isCommand("do"));
        Assert.assertEquals(2, cmd.getArity());
        Assert.assertEquals("arg1", cmd.getArgument(0));
        Assert.assertEquals("arg2", cmd.getArgument(1));

        Assert.assertNull(parser.nextCommand());
    }

    @Test
    public void parseSimpleCommandWithNoArguments() {
        DslParser parser = new DslParser("do");

        DslCommand cmd = parser.nextCommand();

        Assert.assertNotNull(cmd);
        Assert.assertTrue(cmd.isCommand("do"));
        Assert.assertEquals(0, cmd.getArity());

        Assert.assertNull(parser.nextCommand());
    }

    @Test
    public void parseTwoSimpleCommands() {
        DslParser parser = new DslParser("do1 arg11 arg12\ndo2 arg21 arg22");

        DslCommand cmd = parser.nextCommand();

        Assert.assertNotNull(cmd);
        Assert.assertTrue(cmd.isCommand("do1"));
        Assert.assertEquals(2, cmd.getArity());
        Assert.assertEquals("arg11", cmd.getArgument(0));
        Assert.assertEquals("arg12", cmd.getArgument(1));

        cmd = parser.nextCommand();

        Assert.assertNotNull(cmd);
        Assert.assertTrue(cmd.isCommand("do2"));
        Assert.assertEquals(2, cmd.getArity());
        Assert.assertEquals("arg21", cmd.getArgument(0));
        Assert.assertEquals("arg22", cmd.getArgument(1));

        Assert.assertNull(parser.nextCommand());
    }
}