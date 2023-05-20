package me.whiteship.refactoring._02_duplicated_code._04_extract_function;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class StudyDashboard {
    // 의도와 구현 분리하기
    // 책을 볼 때처럼 코드를 봐도 이해되지 않는다면 "구현"을 들여다 보는 것. 어떤 일을 하는지 잘 보인다면 "의도"를 파악할 수 있도록 잘 작성한 코드이다.
    // extract function 으로 메소드를 추출해서 이름을 지정하면 "의도"를 잘 드러낼 수 있다.

    private void printParticipants(int eventId) throws IOException {
        GHIssue issue = getGhIssue(eventId);
        Set<String> usernames = getUsernames(issue);
        print(usernames);
    }

    private void printReviewers() throws IOException {
        GHIssue issue = getGhIssue(30);
        Set<String> reviewers = getUsernames(issue);
        print(reviewers);
    }

    private static void print(Set<String> usernames) {
        usernames.forEach(System.out::println);
    }

    private static Set<String> getUsernames(GHIssue issue) throws IOException {
        Set<String> participants = new HashSet<>();
        issue.getComments().forEach(c -> participants.add(c.getUserName()));
        return participants;
    }

    private static GHIssue getGhIssue(int eventId) throws IOException {
        GitHub gitHub = GitHub.connect();
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        GHIssue issue = repository.getIssue(eventId);
        return issue;
    }

    public static void main(String[] args) throws IOException {
        StudyDashboard studyDashboard = new StudyDashboard();
        studyDashboard.printReviewers();
        studyDashboard.printParticipants(15);
    }

}
