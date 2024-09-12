package nl.sikken.bertrik.anabat;

import java.io.File;

/**
 * Runnable wrapper to run one anabat conversion
 */
public class ConverterRunner implements Runnable {

    private final File file;
    private final IProgressListener<String> listener;
    private final AnabatConverter converter;

    /**
     * @param setting conversion setting
     * @param file the file to convert
     * @param listener progress listener, to be called upon completion
     */
    public ConverterRunner(ConverterSetting setting, File file, IProgressListener<String> listener) {
        this.file = file;
        this.listener = listener;
        this.converter = new AnabatConverter(setting);
    }

    @Override
    public void run() {
        try {
            converter.convert(file);
        } catch (Exception e) {
            // TODO log something here
        } finally {
            listener.update(file.getName());
        }
    }

}
