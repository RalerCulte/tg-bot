package alltheshamefeelsthesame.services;

import org.telegram.telegrambots.meta.api.objects.InputFile;

public class InputFileWithVariants extends InputFile {

    private final InputFile[] variants;

    public InputFileWithVariants(InputFile[] variants) {
        this.variants = variants;
    }

    public InputFile getVariant(int number) {
        return variants[number];
    }
}
