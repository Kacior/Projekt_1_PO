package projekt1;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static java.lang.Math.toIntExact;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class JSONReader {


    public void loadData(File folder, Judgements cases, Judges judgesList, Regulations regList) {

        JSONParser parser = new JSONParser();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            for (final File fileEntry : folder.listFiles()) {
                Object obj = parser.parse(new FileReader(fileEntry));

                JSONObject jsonObject = (JSONObject) obj;
                JSONArray items = (JSONArray) jsonObject.get("items");
                for (int i = 0; i < items.size(); i++) {//iteracja po tabeli 'items'
                    JSONObject PartialArray = (JSONObject) items.get(i);

                    JSONArray signatures = (JSONArray) PartialArray.get("courtCases");
                    JSONObject singleSignature = (JSONObject) signatures.get(0);
                    String signature = (String) singleSignature.get("caseNumber");

                    String courtType = (String) PartialArray.get("courtType");//pole 'rodzaj_sadu'
                    Date date = sdf.parse((String) PartialArray.get("judgmentDate"));//pole 'data' orzeczenia
                    String textContent = (String) PartialArray.get("textContent"); // pole 'uzasadnienie' orzeczenia
                    Judgement newcase = new Judgement(date, courtType, textContent, signature);//inicjalizacja nowego orzeczenia
                    JSONArray judges = (JSONArray) PartialArray.get("judges");
                    for (int k = 0; k < judges.size(); k++) {//iteracja po tabeli 'judges'
                        JSONObject PartialJudge = (JSONObject) judges.get(k);
                        String judge = (String) PartialJudge.get("name");//pole 'imie' sedziego
                        String judge_function = (String) PartialJudge.get("function");//pole 'funkcja' sedziego
                        Judge newJudge = new Judge(judge);//inicjalizacja nowego sedziego
                        newcase.assignJudge2Judgement(newJudge, judge_function);//uzupełnienie orzeczenia o skład sedziow
                        newJudge.assignJudgement2Judge(newcase);//przypisanie sedziemu aktualnej sprawy
                        judgesList.addJudge(newJudge, newcase);//dodanie sedziego do listy sedziow
                    }
                    cases.addJudgement(newcase);//dodanie orzeczenia do listy orzeczeń

                    JSONArray regulations = (JSONArray) PartialArray.get("referencedRegulations");
                    for (int a = 0; a < regulations.size(); a++) {
                        JSONObject PartialRegulations = (JSONObject) regulations.get(a);
                        String regTitle = (String) PartialRegulations.get("journalTitle");
                        Regulation newReg = new Regulation(regTitle);
                        regList.addRegulation(newReg, newcase);
                    }
                }
            }
        } catch (IOException|ParseException|java.text.ParseException e) {
            e.printStackTrace();
        }
    }
}
