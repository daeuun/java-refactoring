package me.whiteship.refactoring._03_long_function._09_preserve_whole_object;

import java.util.HashMap;
import java.util.Map;

public record Participant(String username, Map<Integer, Boolean> homework) {
    public Participant(String username) {
        this(username, new HashMap<>());
    }

    public void setHomeworkDone(int index) {
        this.homework.put(index, true);
    }

    /**
     * 메서드가 어떤 클래스에 의존해야하는지 고려하여 위치 변경
     * @param studyDashboard
     * @return
     */
    double getRate(int studyDashboard) {
        long count = homework().values().stream()
                .filter(v -> v == true)
                .count();
        return (double) (count * 100 / studyDashboard);
    }
}
