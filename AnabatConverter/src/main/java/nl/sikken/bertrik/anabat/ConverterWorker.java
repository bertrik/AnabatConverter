package nl.sikken.bertrik.anabat;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.SwingWorker;

/**
 * SwingWorker to decouple processing from GUI
 */
public class ConverterWorker extends SwingWorker<Boolean, String> {

    private final List<File> files;
    private final ConverterSetting setting;
    private final IProgressListener<String> listener;
    
    /**
     * @param setting
     * @param listener
     * @param files
     */
    public ConverterWorker(ConverterSetting setting, IProgressListener<String> listener, List<File> files) {
        this.listener = listener;
        this.files = files;
        this.setting = setting;
    }
    
    /* (non-Javadoc)
     * @see javax.swing.SwingWorker#doInBackground()
     */
    @Override
    protected Boolean doInBackground() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (File file : files) {
            executor.execute(new ConverterRunner(setting, file, listener));
        }
        executor.shutdown();
        return true;
    }

}
