package nl.sikken.bertrik.anabat;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * @author Bertrik Sikken
 *
 */
public class AnabatConverter {

    private final ConverterSetting settings;

    /**
     * @param settings
     */
    public AnabatConverter(ConverterSetting settings) {
        this.settings = settings;
    }
    
    public void convert(File file) throws IOException, ParseException, UnsupportedAudioFileException {
        // create anabat file
        AnabatFile af = new AnabatFile();
        af.setDivRatio(settings.getDivRatio());
        af.setNote(settings.getNote());
        
        int expRatio = settings.getExpRatio();

        // init stream
        IAudioStream stream = new UnifiedAudioStream(file, settings.getChannel());
        
        // heuristic: anything faster than 48 kHz is assumed to be direct-recorded (without expansion factor)
        if (stream.getSampleRate() > 48000) {
            expRatio = 1;
        }
        int fs = stream.getSampleRate() * expRatio;
        
        // try to extract metadata
        D500MetaData d500MetaData = new D500MetaData();
        SonobatMetaData sonobatMetaData = new SonobatMetaData();
        String saveDir;
        String saveName;
        if (d500MetaData.load(file)) {
            Date date = D500MetaData.convertDateCode(d500MetaData.getDateCode());
            Date dateNight = new Date(date.getTime() - 12 * 3600 * 1000);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            saveDir = dateFormat.format(dateNight);
            saveName = AnabatFile.createFileName(date);
            af.setDate(date);
            af.setTape(d500MetaData.getOriginalFilename());
            af.setNote1(d500MetaData.getSettings1() + " / " + d500MetaData.getSettings2());
            // override time expansion setting
            expRatio = 1;
        }
        else if (sonobatMetaData.load(file)) {
            Date date = sonobatMetaData.getDate();
            Date dateNight = new Date(date.getTime() - 12 * 3600 * 1000);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            saveDir = dateFormat.format(dateNight);
            saveName = AnabatFile.createFileName(date);
            af.setDate(date);
            af.setTape(sonobatMetaData.getCode());
            // override time expansion setting
            expRatio = 1;
        }
        else {
            saveDir = "anabat";
            String fileName = file.getName();
            if (fileName.toLowerCase().endsWith(".wav")) {
                fileName = fileName.substring(0, fileName.lastIndexOf('.'));
            }
            saveName = fileName + ".00#";
            af.setDate(new Date());
        }
        
        // initialize filter
        int f0 = settings.getHighPass(); 
        IFilter filter = new BiquadHighpass(fs, f0);
        
        // calc mean
        if (f0 > 0) {
            stream = new FilteredAudioStream(stream, filter);
        }
        SignalStats stats = new SignalStats();
        stats.calculate(stream, stream.getSampleRate() * expRatio / 20);
        stream.close();
        double mean = stats.getMean();
        
        // calculate threshold: 2.5 times the minimum noise level, discarding all noise power below 10 percent
        double threshold = 2.5 * Math.sqrt(stats.getNoisePower(10));
        if (threshold < 1.0) {
            threshold = 1.0;
        }
        
        // find zero crossings
        stream = new UnifiedAudioStream(file, settings.getChannel());
        if (f0 > 0) {
            stream = new FilteredAudioStream(stream, filter);
        }
        ZeroCrossFinder zcf = new ZeroCrossFinder();
        List<Long> list = zcf.processStream(stream, mean, threshold, expRatio, settings.getDivRatio());
        stream.close();
        
        // save anabat file
        File savePath = new File(file.getParent(), saveDir);
        if (!savePath.mkdirs()) {
            throw new IOException("Failed to create directories");
        }
        af.setZeroCrossings(list);
        af.save(new File(savePath, saveName));
    }    
    
}
