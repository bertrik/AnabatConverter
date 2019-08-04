package nl.sikken.bertrik.anabat;

import java.io.File;
import java.io.FilenameFilter;

import javax.swing.filechooser.FileFilter;

/**
 * @author Bertrik Sikken
 *
 */
public class WavFileFilter extends FileFilter implements FilenameFilter {

    /* (non-Javadoc)
     * @see javax.swing.filechooser.FileFilter#accept(java.io.File)
     */
    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            // show directories
            return true;
        }
        return accept(f.getName());
    }

    /* (non-Javadoc)
     * @see javax.swing.filechooser.FileFilter#getDescription()
     */
    @Override
    public String getDescription() {
        return "WAV audio files";
    }

    /* (non-Javadoc)
     * @see java.io.FilenameFilter#accept(java.io.File, java.lang.String)
     */
    public boolean accept(File dir, String name) {
        return accept(name);
    }
    
    private boolean accept(String name) {
        return name.toLowerCase().endsWith(".wav");
    }

}
