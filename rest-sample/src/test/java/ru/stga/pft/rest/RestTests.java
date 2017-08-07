package ru.stga.pft.rest;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase{
    @Test
    public void testCreateIssue() throws IOException {
        skipIfNotFixed(5);
        Set<Issue> oldIssues = getIssues();
        Issue newIssue = new Issue().withSubject("Test issue").withDescription("New test issue");
        int issueId =  create(newIssue);
        Set <Issue> newIssues = getIssues();
        oldIssues.add(newIssue.whitId(issueId));
        assertEquals(newIssues,oldIssues);
    }


//    @Test
//    public void testCreateIssue() throws IOException {
//        Set<Issue> oldIssues = getIssues();
//        Issue newIssue = new Issue().withSubject("Test issue").withDescription("New test issue");
//        int issueId = createIssue(newIssue);
//        Set<Issue> newIssues = getIssues();
//        oldIssues.add(newIssue.withId(issueId));
//        assertEquals(newIssues, oldIssues);
//    }
//
//    private Set<Issue> getIssues() throws IOException {
//        String json = getExecutor().execute(org.apache.http.client.fluent.Request.Get("http://demo.bugify.com/api/issues.json"))
//                .returnContent().asString();
//
//        JsonElement parsed = new JsonParser().parse(json);
//        JsonElement issues = parsed.getAsJsonObject().get("issues");
//        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
//    }
//
//    private Executor getExecutor() {
//        return Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==", "");
//    }
//
//    private int createIssue(Issue newIssue) throws IOException {
//        String json = getExecutor().execute(org.apache.http.client.fluent.Request.Post("http://demo.bugify.com/api/issues.json")
//                .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
//                        new BasicNameValuePair("description", newIssue.getDescription())))
//                .returnContent().asString();
//        JsonElement parsed = new JsonParser().parse(json);
//        return parsed.getAsJsonObject().get("issue_id").getAsInt();
//    }
}