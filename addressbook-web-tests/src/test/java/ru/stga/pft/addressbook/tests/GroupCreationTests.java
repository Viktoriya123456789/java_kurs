package ru.stga.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {


    @Test
    public void testGroupCreation() {

        app.gotoGroupPage();
        app.initGroupCreation();
        app.fillGroupForm(new GroupData("Test group2", "test1", "test2"));
        app.submitGroupCreation();
        app.returnToGroupPage();
    }
}


