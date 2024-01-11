package com.gildedrose;


import org.junit.Test;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;

public class GildedRoseGoldenMasterTest {

    @Test
    public void golden_master_test() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);

        TexttestFixture.runGoldenMaster(new String[]{"100"}, printStream);
        final String actualOutput = byteArrayOutputStream.toString();

        final String expectedOutput = expectedOutput();
        assertThat(actualOutput).isEqualTo(expectedOutput);
    }

    private String expectedOutput() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        File file = new File("src/test/resources/golden-master.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String string;
        while ((string = bufferedReader.readLine()) != null) {
            printStream.println(string);
        }
        return byteArrayOutputStream.toString();
    }

}
