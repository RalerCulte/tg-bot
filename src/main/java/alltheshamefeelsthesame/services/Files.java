package alltheshamefeelsthesame.services;

import org.telegram.telegrambots.meta.api.objects.InputFile;

public class Files {

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

    public InputFile getSticker(int number) {
        return stickers[number];
    }

    public InputFile getDocument(int number) {
        return documents[number];
    }

}
