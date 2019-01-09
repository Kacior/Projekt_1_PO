package projekt1;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HTMLReader {
    public void loadData(File folder, Judgements judgements, Judges judges, Regulations regList) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                loadData(fileEntry, judgements, judges, regList);
            } else {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Document page = Jsoup.parse(fileEntry, "UTF-8", "");

                    Element title = page.select("title").first();

                    String signatureToExtract = title.text();
                    String signature = signatureToExtract.substring(0, signatureToExtract.indexOf("-") - 1);

                    String textDate = page.getElementsByClass("info-list-value").get(1).text();
                    Date date = new Date();
                    if (textDate.length() > 10) date = sdf.parse("0000-00-00");
                    else date = sdf.parse((String) textDate);

                    String courtType = "ADMINISTRATIVE";

                    String textContent = page.getElementsByClass("info-list-value-uzasadnienie").text();

Judgement newcase = new Judgement(date, courtType, textContent, signature);
judgements.addJudgement(newcase);

                    Element judgeColumn = page.getElementsByClass("info-list-value").get(3);
                    String j = judgeColumn.toString();
                    String judgeName = "";
                    String judgeFunction = "";
                    for (int i = 29; i + 4 < j.length(); i++) {
                        judgeName = "";
                        judgeFunction = "";
                        while (j.charAt(i) != '<') {

                            while (j.charAt(i) != '/') {
                                if (j.charAt(i) == '<') break;
                                judgeName += j.charAt(i++);
                            }
                            if (j.charAt(i) == '<') judgeFunction = "Not Specified";
                            else {
                                i++;
                                while (j.charAt(i) != '/') {
                                    judgeFunction += j.charAt(i++);
                                }
                            }
                            while (j.charAt(i) != '<') {
                                i++;
                            }
                        }
                        i += 4;
                     Judge newJudge = new Judge(judgeName);
                     judges.addJudge(newJudge, newcase);
                     newJudge.assignJudgement2Judge(newcase);
                     newcase.assignJudge2Judgement(newJudge, judgeFunction);
                    }

String regTitle = page.getElementsByClass("nakt").text();
Regulation newReg = new Regulation(regTitle);
                    regList.addRegulation(newReg, newcase);

                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

