package edu.project3;

import edu.project3.LogExecutors.LocalPathExecutor;
import edu.project3.LogExecutors.LogExecutor;
import edu.project3.PrintStatistic.MarkDownAndAdocOutput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MarkDownAndAdocOutputTest {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    private static final String RESULT_STRING = LINE_SEPARATOR +
            "### General Information" + LINE_SEPARATOR +
            LINE_SEPARATOR +
            "|        Metric         |     Value     |" + LINE_SEPARATOR +
            "|:---------------------:|--------------:|" + LINE_SEPARATOR +
            "|        File(s)        |   test1.txt   |" + LINE_SEPARATOR +
            "|                       |   test2.txt   |" + LINE_SEPARATOR +
            "|                       |   test3.txt   |" + LINE_SEPARATOR +
            "|     Starting date     |       -       |" + LINE_SEPARATOR +
            "|      Final date       |       -       |" + LINE_SEPARATOR +
            "|  Number of requests   |       3       |" + LINE_SEPARATOR +
            "| Average request size  |     208b      |" + LINE_SEPARATOR +
            LINE_SEPARATOR +
            "### Requested resources" + LINE_SEPARATOR +
            LINE_SEPARATOR +
            "|       Resource        |    Number     |" + LINE_SEPARATOR +
            "|:---------------------:|--------------:|" + LINE_SEPARATOR +
            "|     '/product_2'      |       1       |" + LINE_SEPARATOR +
            "|     '/product_1'      |       2       |" + LINE_SEPARATOR +
            LINE_SEPARATOR +
            "### User-Agent" + LINE_SEPARATOR +
            LINE_SEPARATOR +
            "|      Agent name       |    Number     |" + LINE_SEPARATOR +
            "|:---------------------:|--------------:|" + LINE_SEPARATOR +
            "|         Maven         |       1       |" + LINE_SEPARATOR +
            "|        Debian         |       2       |" + LINE_SEPARATOR +
            LINE_SEPARATOR +
            "### Response codes" + LINE_SEPARATOR +
            LINE_SEPARATOR +
            "| Code |         Name          |    Number     |" + LINE_SEPARATOR +
            "|:----:|:---------------------:|--------------:|" + LINE_SEPARATOR +
            "| 304 |     Not Modified      |       1       |" + LINE_SEPARATOR +
            "| 404 |       Not Found       |       1       |" + LINE_SEPARATOR +
            "| 200 |          OK           |       1       |" + LINE_SEPARATOR +
            LINE_SEPARATOR +
            "### IP addresses of requests" + LINE_SEPARATOR +
            LINE_SEPARATOR +
            "|          IP           |    Number     |" + LINE_SEPARATOR +
            "|:---------------------:|--------------:|" + LINE_SEPARATOR +
            "|      93.180.71.3      |       2       |" + LINE_SEPARATOR +
            "|     80.91.33.133      |       1       |" + LINE_SEPARATOR;

    @Test
    @DisplayName("Проверка работы StatisticPrinter")
    void statisticPrinterTest() {
        LogAnalyzer logAnalyzer = new LogAnalyzer(null, null, "md");
        LogExecutor localPathExecutor = new LocalPathExecutor();
        localPathExecutor.executeDataByPath(logAnalyzer, "test*");

        MarkDownAndAdocOutput statisticPrinter = new MarkDownAndAdocOutput(logAnalyzer);
        statisticPrinter.printData();
        File logFile = new File("Log_Statistic.md");
        assertThat(logFile.exists()).isTrue();
        logFile.delete();

        assertThat(statisticPrinter.getResultString()).isEqualTo(RESULT_STRING);
    }
}