package alltheshamefeelsthesame.services;

import org.telegram.telegrambots.meta.api.objects.InputFile;

public class Files {

    private final InputFile[] secondVariants = {
            new InputFile("var1"),
            new InputFile("var2"),
            new InputFile("var3"),
            new InputFile("var4"),
            new InputFile("var5"),
            new InputFile("var6"),
            new InputFile("var7"),
    };

    private final InputFile[] sixthVariants = {
            new InputFile("var1"),
            new InputFile("var2"),
            new InputFile("var3"),
            new InputFile("var4"),
    };

    private final InputFile[] seventhVariants = {
            new InputFile("var1"),
            new InputFile("var2"),
            new InputFile("var3"),
            new InputFile("var4"),
            new InputFile("var5"),
            new InputFile("var6"),
            new InputFile("var7"),
    };

    private final InputFile[] ninthVariants = {
            new InputFile("var1"),
            new InputFile("var2"),
            new InputFile("var3"),
            new InputFile("var4"),
            new InputFile("var5"),
            new InputFile("var6"),
            new InputFile("var7"),
    };


    private final InputFile[] stickers = {
            new InputFile("CAACAgIAAxkBAAECtGBhDvJPajzHQxWmAAG1r1YVK7idBuAAAgYAA0FBEieWjrXCU-2x6SAE"),
            new InputFile("CAACAgIAAxkBAAECtt9hEc4sG5EWT-DmyfV0efRNcDWwrwACDQ4AAm0xeUkEtmmVpSCCLSAE"),
            new InputFile("CAACAgIAAxkBAAECttZhEbrGqpgUOy_tiLYIo_xr2RxFNAAC-hAAAqHHKEg5ZXbrk1gHoyAE"),
            new InputFile("CAACAgIAAxkBAAECtyZhEk7y7pPxAXCBPEPJdPddUpRP3QACrQ0AAqyZIEjdinfy_Yf5cCAE"),
            new InputFile("CAACAgIAAxkBAAECt7NhEnp7PiiEMt_U7NshvPBaaJx-DAAC-BAAAuO_SEpmmeh30LPWwSAE")
    };

    private final InputFile[] documents = {
            new InputFile("BQACAgIAAxkBAAIDNWEQE8_yyx1SFkxV6FseGOe-PHRrAALmEQACzbiJSFulm2tyWvTuIAQ"),
            new InputFile("BQACAgIAAxkBAAIHBmERp4DVIobpUaulBfF4YBrgv0TsAALTEQACzbiRSDm3iZTugUhtIAQ"),
            new InputFile("BQACAgIAAxkBAAIKg2ETcWkiEBBxeUGCdPCZKYzOTxcIAAKWDwACFwqZSMIq4QahgW3zIAQ")
    };

    private final InputFile[] oibLabs = {
            new InputFile("lab1"),
            new InputFileWithVariants(secondVariants),
            new InputFile("lab3"),
            new InputFile("lab4"),
            new InputFile("lab5"),
            new InputFileWithVariants(sixthVariants),
            new InputFileWithVariants(seventhVariants),
            new InputFile("lab8"),
            new InputFileWithVariants(ninthVariants),
            new InputFile("lab10"),
            new InputFile("lab11"),
            new InputFile("lab12"),
            new InputFile("lab13"),
            new InputFile("lab14"),
    };

    private final InputFile[] dataStructuresLabs = {
            new InputFile("lab1"),
            new InputFile("lab2"),
            new InputFile("lab3"),
            new InputFile("lab4")
    };

    private final InputFile[] infLabs = {
            new InputFile("lab1"),
            new InputFile("lab2"),
            new InputFile("lab3"),
            new InputFile("lab4"),
            new InputFile("lab5")
    };

    public InputFile getSticker(int number) {
        return stickers[number];
    }

    public InputFile getDocument(int number) {
        return documents[number];
    }

    public InputFile getOibLab(int number) {
        return oibLabs[number];
    }

    public InputFile getDataStructuresLab(int number) {
        return dataStructuresLabs[number];
    }

    public InputFile getInfLab(int number) {
        return infLabs[number];
    }

}
