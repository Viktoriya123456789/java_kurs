package ru.stga.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {


    @Test
    public void testGroupCreation() {

        app.getGroupHelper().gotoGroupPage();
        app.getGroupHelper().createGroup(new GroupData("Test group2", "test1", "test2"));
        //app.getGroupHelper().initGroupCreation();
        //app.getGroupHelper().fillGroupForm(new GroupData("Test group2", "test1", "test2"));
        //app.getGroupHelper().submitGroupCreation();
        //app.getGroupHelper().returnToGroupPage();
    }
}


