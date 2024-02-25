package nl.sikken.bertrik.anabat;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class UnifiedAudioStream implements IAudioStream {
    
    private final AudioFormat audioFormat;
    private final InputStream inputStream;
    private final int channel;
    private final long numFrames;
    // pre-allocated buffers
    private final ByteBuffer buffer;
    private final int[] samples;

    
    /**
     * Constructor 
     */
    public UnifiedAudioStream(File file, int channel) throws UnsupportedAudioFileException, IOException {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
        audioFormat = audioInputStream.getFormat();
        
        // buffer the input stream
        inputStream = new BufferedInputStream(audioInputStream, 1024 * audioFormat.getFrameSize());
        
        // process channel
        if (audioFormat.getChannels() == 1) {
            channel = 0;
        }
        if (channel >= audioFormat.getChannels()) {
            throw new IllegalArgumentException(String.format("No such channel %d in %s", channel, file.getName()));
        }
        this.channel = channel;
        
        // number of frames
        this.numFrames = audioInputStream.getFrameLength();
        
        // pre-allocated buffers
        buffer = ByteBuffer.allocate(audioFormat.getFrameSize());
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        samples = new int[audioFormat.getChannels()];
    }
    
    /**
     * @return a 
     * @throws IOException 
     */
    public double getSample() throws IOException {
        
        // read one frame
        buffer.rewind();
        int number = inputStream.read(buffer.array());
        if (number == -1) {
            return number;
        }
        
        // extract all channels
        for (int i = 0; i < audioFormat.getChannels(); i++) {
            switch (audioFormat.getSampleSizeInBits()) {
            case 24:
                // skip lowest 8 bits of 24 bit sample
                buffer.get();
                samples[i] = buffer.getShort();
                break;
            case 16:
                samples[i] = buffer.getShort();
                break;
            case 8:
                samples[i] = (buffer.get() & 0xFF) << 8;
                break;
            default:
                throw new IllegalStateException("Unhandled sample size " + audioFormat.getSampleSizeInBits());
            }
        }
        
        // return the sample from the selected channel
        return samples[channel];
    }

    public int getSampleRate() {
        return (int) audioFormat.getSampleRate();
    }
    
    public long getNumberOfFrames() {
        return numFrames;
    }
    
    public void close() {
        try {
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException e) {
            // do not care anymore
        }
    }
}
