package ru.stga.pft.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {


    @Test
    public void testGroupCreation() {

        gotoGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupData("Test group2", "test1", "test2"));
        submitGroupCreation();
        returnToGroupPage();
    }
}


