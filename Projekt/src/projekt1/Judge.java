package projekt1;

import java.util.ArrayList;
import java.util.List;

public class Judge {
    private String name;
    private List<Judgement> judgements = new ArrayList<>();

    Judge(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    void assignJudgement2Judge(Judgement judgement) {
        judgements.add(judgement);
    }

    int numberOfCases() {
        return judgements.size();
    }

    public String toString() {
        return name;
    }
}
