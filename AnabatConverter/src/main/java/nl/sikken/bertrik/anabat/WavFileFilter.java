package nl.sikken.bertrik.anabat;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Locale;

import javax.swing.filechooser.FileFilter;

public class WavFileFilter extends FileFilter implements FilenameFilter {

    /**
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

    /**
     * @see javax.swing.filechooser.FileFilter#getDescription()
     */
    @Override
    public String getDescription() {
        return "WAV audio files";
    }

    /**
     * @see java.io.FilenameFilter#accept(java.io.File, java.lang.String)
     */
    @Override
    public boolean accept(File dir, String name) {
        return accept(name);
    }

    private boolean accept(String name) {
        return name.toLowerCase(Locale.ROOT).endsWith(".wav");
    }

}
