package ru.stga.pft.rest.tests;

/**
 * Created by admin on 07.08.2017.
 */
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import java.io.IOException;

public class TestBase {

    public boolean isIssueOpen(int issueId) throws IOException {
        String json = getExecutor().execute(Request.Get(String.format("http://demo.bugify.com/api/issues/%s.json", issueId)))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues").getAsJsonArray();
        JsonElement testIssue = issues.getAsJsonArray().get(0).getAsJsonObject();
        String issueStatus = testIssue.getAsJsonObject().get("state_name").getAsString();
        if (issueStatus.equals("Resolved") || issueStatus.equals("Closed")) {
            return false;
        } else {
            return true;
        }
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    protected int getIssueStatus() throws IOException {
        String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json")).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues").getAsJsonArray();
        JsonElement testIssue = issues.getAsJsonArray().get(0).getAsJsonObject();
        return Integer.parseInt(testIssue.getAsJsonObject().get("id").getAsString());
    }

    protected Executor getExecutor() {
        return Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==", "");
    }
}