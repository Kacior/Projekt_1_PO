package projekt1;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Regulation {
    private  String title;

    private List<Judgement> judgements = new LinkedList<>();

    public String getTitle() {
        return title;
    }

    public void assignJudgement2Regulation(Judgement judgement) {
        judgements.add(judgement);
    }

    public Regulation(String title) {
        this.title = title;
    }

    public int regulationSize() {
        return judgements.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Regulation that = (Regulation) o;
        return Objects.equals(title, that.title);
    }

}
