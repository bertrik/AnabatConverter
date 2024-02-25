package nl.sikken.bertrik.anabat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.SwingWorker;

/**
 * SwingWorker to decouple processing from GUI
 */
public class ConverterWorker extends SwingWorker<Boolean, String> {

    private final ConverterSetting setting;
    private final IProgressListener<String> listener;
    private final List<File> files;
    
    /**
     * @param setting
     * @param listener
     * @param files
     */
    public ConverterWorker(ConverterSetting setting, IProgressListener<String> listener, List<File> files) {
        this.setting = setting;
        this.listener = listener;
        this.files = new ArrayList<>(files);
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
