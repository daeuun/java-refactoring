package me.whiteship.refactoring._02_duplicated_code._06_pull_up_method;

import java.io.IOException;

public class ReviewerDashboard extends Dashboard {

    public void printReviewers() throws IOException {
        super.printUsernames(30);
    }
    // Pull Members Up 으로 중복되는 method 를 상위 클래스로 옮겼다. 하위 클래스에서는 세부적인 사항을 적용한다.

}
