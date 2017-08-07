package ru.stga.pft.rest;

/**
 * Created by admin on 07.08.2017.
 */
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.SkipException;

import java.io.IOException;
import java.util.Set;

public class TestBase {


    protected  boolean isIssueOpen( int issueId) throws IOException {
        Issue issue = getIssues().stream().filter((i)->i.getId() == issueId).iterator().next();
        if (issue.getStatus().equals("In Progress"))
        {
            return false;
        }
        else {
            return true;
        }

    }


    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }



    protected Set<Issue> getIssues() throws IOException {
        String json  =  getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json"))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues,new TypeToken<Set<Issue>>(){}.getType());
    }



    protected Executor getExecutor() {
        return Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==", "");
    }

    protected int create(Issue issue) throws IOException {
        String json =  getExecutor().execute(Request.Post("http://demo.bugify.com/api/issues.json").bodyForm(
                new BasicNameValuePair("subject", issue.getSubject()),
                new BasicNameValuePair("description",issue.getDescription())))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

}
