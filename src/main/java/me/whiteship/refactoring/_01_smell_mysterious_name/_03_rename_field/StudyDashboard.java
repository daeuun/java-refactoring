package me.whiteship.refactoring._01_smell_mysterious_name._03_rename_field;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueComment;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StudyDashboard {

    private Set<StudyReview> studyReviews = new HashSet<>();

    /**
     * 스터디 리뷰 이슈에 작성되어 있는 리뷰어 목록과 리뷰를 읽어옵니다.
     * @throws IOException
     */
    private void loadReviews() throws IOException {
        GitHub gitHub = GitHub.connect();
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        GHIssue issue = repository.getIssue(30);

        List<GHIssueComment> reviews = issue.getComments();
        for (GHIssueComment review : reviews) {
//            review.getUser().getLogin(); // github api를 한번 더 로드해야함
            StudyReview studyReview = new StudyReview(review.getUserName(), review.getBody());
//            System.out.println(studyReview.review()); // getter 와 역할이 비슷한 field 를 참조하는 메서드도 만들어준다.
//            System.out.println(studyReview.reviewer());
            studyReviews.add(studyReview);
        }
    }

    public Set<StudyReview> getStudyReviews() {
        return studyReviews;
    }

    public static void main(String[] args) throws IOException {
        StudyDashboard studyDashboard = new StudyDashboard();
        studyDashboard.loadReviews();
        studyDashboard.getStudyReviews().forEach(System.out::println);
        // record 에서 생성한 toString() 에 의해 StudyReview[reviewer=xxx, review=xxx] 형태로 출력
    }
}
