package nl.sikken.bertrik.anabat;

import org.apache.log4j.PropertyConfigurator;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public final class AnabatConverterGui extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private static final String APP_VERSION = "v0.8";
    
    private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="50,14"
    private JPanel jContentPane = null;
    private JTextField jTextFieldFileName = null;
    private JLabel jLabelHighPass = null;
    private JTextField jTextFieldHighPass = null;
    private JLabel jLabelExpRatio = null;
    private JLabel jLabelDivRatio = null;
    private JTextField jTextFieldExpRatio = null;
    private JTextField jTextFieldDivRatio = null;
    private JButton jButtonConvert = null;
    private JButton jButtonSelect = null;
    private JTextArea jTextAreaNotes = null;
    private JLabel jLabelStatusBar = null;
    private JPanel jPanelStatusBar = null;
    private JLabel jLabelNotes = null;
    private JButton jButtonConvertMultiple = null;
    private JProgressBar jProgressBar = null;
    
    private JLabel jLabelChannel = null;
    private JComboBox<EChannel> jComboChannel = null;
    
    /**
     * This method initializes 
     * 
     */
    public AnabatConverterGui() {
    	super();
    }

    /**
     * This method initializes jFrame	
     * 	
     * @return javax.swing.JFrame	
     */
    private JFrame getJFrame() {
        if (jFrame == null) {
            jFrame = new JFrame();
            jFrame.setSize(new Dimension(599, 286));
            jFrame.setTitle("AnabatConverter " + APP_VERSION);
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.setMinimumSize(new Dimension(300, 200));
            jFrame.setLocation(new Point(500, 400));
            jFrame.setVisible(true);
            jFrame.setContentPane(getJContentPane());
        }
        return jFrame;
    }

    /**
     * This method initializes jContentPane	
     * 	
     * @return javax.swing.JPanel	
     */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            int y = 1;
            
            // Select file ...
            GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
            gridBagConstraints7.gridx = 1;
            gridBagConstraints7.gridy = y;
            gridBagConstraints7.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints7.insets = new Insets(2, 2, 2, 2);
            
            GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
            gridBagConstraints1.gridx = 2;
            gridBagConstraints1.gridy = y;
            gridBagConstraints1.gridwidth = 4;
            gridBagConstraints1.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints1.insets = new Insets(2, 2, 2, 2);
            
            y++;
            
            // High pass
            GridBagConstraints gridBagConstraintsHighPassLabel = new GridBagConstraints();
            gridBagConstraintsHighPassLabel.gridx = 1;
            gridBagConstraintsHighPassLabel.gridy = y;
            gridBagConstraintsHighPassLabel.anchor = GridBagConstraints.EAST;
            
            GridBagConstraints gridBagConstraintsHighPassField = new GridBagConstraints();
            gridBagConstraintsHighPassField.gridx = 2;
            gridBagConstraintsHighPassField.gridy = y;
            gridBagConstraintsHighPassField.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraintsHighPassField.insets = new Insets(2, 2, 2, 2);

            y++;
            
            // Channel
            GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
            gridBagConstraints18.gridx = 1;
            gridBagConstraints18.gridy = y;
            gridBagConstraints18.anchor = GridBagConstraints.EAST;
           
            GridBagConstraints gridBagConstraints28 = new GridBagConstraints();
            gridBagConstraints28.gridx = 2;
            gridBagConstraints28.gridy = y;
            gridBagConstraints28.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints28.insets = new Insets(2, 2, 2, 2);
            
            y++;
            
            // Time expansion ratio
            GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
            gridBagConstraints3.gridx = 1;
            gridBagConstraints3.gridy = y;
            gridBagConstraints3.anchor = GridBagConstraints.EAST;
            
            GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
            gridBagConstraints5.gridx = 2;
            gridBagConstraints5.gridy = y;
            gridBagConstraints5.anchor = GridBagConstraints.WEST;
            gridBagConstraints5.insets = new Insets(2, 2, 2, 2);
            gridBagConstraints5.weightx = 1.0D;
            
            y++;
            
            // Freq div ratio
            GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
            gridBagConstraints4.gridx = 1;
            gridBagConstraints4.gridy = y;
            gridBagConstraints4.anchor = GridBagConstraints.EAST;
            
            GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
            gridBagConstraints6.gridx = 2;
            gridBagConstraints6.gridy = y;
            gridBagConstraints6.anchor = GridBagConstraints.WEST;
            gridBagConstraints6.insets = new Insets(2, 2, 2, 2);
            gridBagConstraints6.weightx = 1.0D;
            
            y++;
            
            // notes
            GridBagConstraints gridBagConstraints32 = new GridBagConstraints();
            gridBagConstraints32.gridx = 1;
            gridBagConstraints32.gridy = y;
            gridBagConstraints32.anchor = GridBagConstraints.NORTHEAST;
            
            GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
            gridBagConstraints31.anchor = GridBagConstraints.CENTER;
            gridBagConstraints31.gridx = 2;
            gridBagConstraints31.gridy = y;
            gridBagConstraints31.gridwidth = 3;
            gridBagConstraints31.insets = new Insets(2, 2, 2, 2);
            gridBagConstraints31.weighty = 1.0D;
            gridBagConstraints31.fill = GridBagConstraints.BOTH;
            
            y++;
            
            // convert single file
            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = y;
            gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints.insets = new Insets(2, 2, 2, 2);
            gridBagConstraints.gridwidth = 2;
            gridBagConstraints.anchor = GridBagConstraints.SOUTH;
            gridBagConstraints.weightx = 1.0D;
            
            // convert all files
            GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
            gridBagConstraints12.gridx = 3;
            gridBagConstraints12.gridy = y;
            gridBagConstraints12.gridwidth = 2;
            gridBagConstraints12.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints12.insets = new Insets(2, 2, 2, 2);
            gridBagConstraints12.weightx = 1.0D;
            
            y++;
            
            // progress bar
            GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
            gridBagConstraints21.gridx = 1;
            gridBagConstraints21.gridy = y;
            gridBagConstraints21.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints21.gridwidth = 4;
            gridBagConstraints21.insets = new Insets(2, 2, 2, 2);
            gridBagConstraints21.weightx = 1.0D;
            
            y++;
           
            // status bar
            GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
            gridBagConstraints2.gridx = 1;
            gridBagConstraints2.gridy = y;
            gridBagConstraints2.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints2.gridwidth = 4;
            gridBagConstraints2.insets = new Insets(2, 2, 2, 2);
            gridBagConstraints2.weightx = 1.0D;
            
            y++;
            
            jLabelNotes = new JLabel("Notes");
            jLabelStatusBar = new JLabel("Status");
            jLabelDivRatio = new JLabel("Frequency division ratio");
            jLabelExpRatio = new JLabel("Time expansion ratio");
            jLabelChannel = new JLabel("Channel");
            jLabelHighPass = new JLabel("HighPass (Hz)");
            
            jContentPane = new JPanel();
            jContentPane.setLayout(new GridBagLayout());
            jContentPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            
            jContentPane.add(getJButtonSelect(), gridBagConstraints7);
            jContentPane.add(getJTextFieldFileName(), gridBagConstraints1);
            jContentPane.add(jLabelHighPass, gridBagConstraintsHighPassLabel);
            jContentPane.add(getJTextFieldHighPass(), gridBagConstraintsHighPassField);
            jContentPane.add(jLabelExpRatio, gridBagConstraints3);
            jContentPane.add(getJTextFieldExpRatio(), gridBagConstraints5);
            jContentPane.add(jLabelDivRatio, gridBagConstraints4);
            jContentPane.add(getJTextFieldDivRatio(), gridBagConstraints6);
            jContentPane.add(jLabelNotes, gridBagConstraints32);
            jContentPane.add(getJTextAreaNotes(), gridBagConstraints31);
            jContentPane.add(getJButtonConvert(), gridBagConstraints);
            jContentPane.add(getJPanelStatusBar(), gridBagConstraints2);
            jContentPane.add(getJButtonConvertMultiple(), gridBagConstraints12);
            jContentPane.add(getJProgressBar(), gridBagConstraints21);
            jContentPane.add(jLabelChannel, gridBagConstraints18);
            jContentPane.add(getJComboChannel(), gridBagConstraints28);
        }
        return jContentPane;
    }

    /**
     * This method initializes jTextFieldFileName	
     * 	
     * @return javax.swing.JTextField	
     */
    private JTextField getJTextFieldFileName() {
        if (jTextFieldFileName == null) {
            jTextFieldFileName = new JTextField();
        }
        return jTextFieldFileName;
    }

    private JTextField getJTextFieldHighPass() {
        if (jTextFieldHighPass == null) {
            jTextFieldHighPass = new JTextField();
            jTextFieldHighPass.setText("15000");
            jTextFieldHighPass.setHorizontalAlignment(JTextField.CENTER);
        }
        return jTextFieldHighPass;
    }
    
    
    /**
     * This method initializes jTextFieldExpRatio	
     * 	
     * @return javax.swing.JTextField	
     */
    private JTextField getJTextFieldExpRatio() {
        if (jTextFieldExpRatio == null) {
            jTextFieldExpRatio = new JTextField();
            jTextFieldExpRatio.setText("10");
            jTextFieldExpRatio.setColumns(3);
            jTextFieldExpRatio.setHorizontalAlignment(JTextField.CENTER);
        }
        return jTextFieldExpRatio;
    }

    /**
     * This method initializes jTextFieldDivRatio	
     * 	
     * @return javax.swing.JTextField	
     */
    private JTextField getJTextFieldDivRatio() {
        if (jTextFieldDivRatio == null) {
            jTextFieldDivRatio = new JTextField();
            jTextFieldDivRatio.setText("8");
            jTextFieldDivRatio.setColumns(3);
            jTextFieldDivRatio.setHorizontalAlignment(JTextField.CENTER);
        }
        return jTextFieldDivRatio;
    }
    
    /**
     * This method initializes jButtonConvert	
     * 	
     * @return javax.swing.JButton	
     */
    private JButton getJButtonConvert() {
        if (jButtonConvert == null) {
            jButtonConvert = new JButton();
            jButtonConvert.setText("Convert single file");
            jButtonConvert.addActionListener(this);
        }
        return jButtonConvert;
    }
    
    private void setStatus(String status) {
        jLabelStatusBar.setText(status);
    }
    
    @Override
    public void actionPerformed(java.awt.event.ActionEvent event) {
        
        setStatus("Starting single conversion ...");
        
        EChannel channel = (EChannel)jComboChannel.getSelectedItem();
        
        ConverterSetting setting = new ConverterSetting(
                channel.getIndex(), 
                jTextAreaNotes.getText(), 
                Integer.parseInt(jTextFieldDivRatio.getText()), 
                Integer.parseInt(jTextFieldExpRatio.getText()),
                Integer.parseInt(jTextFieldHighPass.getText()));
        AnabatConverter converter = new AnabatConverter(setting);
        
        String loadName = jTextFieldFileName.getText();
        try {
            converter.convert(new File(loadName));
            setStatus("Successfully converted " + loadName);
        } catch (Exception e) {
            setStatus(e.toString());
        }
    }
        
    private void createAndShowGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        //Display the window.
        getJFrame().setVisible(true);
    }

    private JButton getJButtonSelect() {
        if (jButtonSelect == null) {
            jButtonSelect = new JButton();
            jButtonSelect.setText("Select file...");
            jButtonSelect.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    String initialDir = new File(jTextFieldFileName.getText()).getPath();
                    JFileChooser fc = new JFileChooser(new File(initialDir));
                    fc.setFileFilter(new WavFileFilter());
                    fc.setMultiSelectionEnabled(true);
                    if (fc.showOpenDialog(getParent()) == JFileChooser.APPROVE_OPTION) {
                        jTextFieldFileName.setText(fc.getSelectedFile().getAbsoluteFile().toString());
                    }
                }
            });
        }
        return jButtonSelect;
    }

    /**
     * This method initializes jTextAreaNotes	
     * 	
     * @return javax.swing.JTextArea	
     */
    private JTextArea getJTextAreaNotes() {
        if (jTextAreaNotes == null) {
            jTextAreaNotes = new JTextArea();
        }
        return jTextAreaNotes;
    }

    /**
     * This method initializes jPanelStatusBar	
     * 	
     * @return javax.swing.JPanel	
     */
    private JPanel getJPanelStatusBar() {
        if (jPanelStatusBar == null) {
            BorderLayout borderLayout = new BorderLayout();
            jPanelStatusBar = new JPanel();
            jPanelStatusBar.setLayout(borderLayout);
            jPanelStatusBar.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            jPanelStatusBar.add(jLabelStatusBar, BorderLayout.EAST);
        }
        return jPanelStatusBar;
    }

    /**
     * This method initializes jButtonConvertMultiple	
     * 	
     * @return javax.swing.JButton	
     */
    private JButton getJButtonConvertMultiple() {
        if (jButtonConvertMultiple == null) {
            jButtonConvertMultiple = new JButton();
            jButtonConvertMultiple.setText("Convert all files in directory");
            jButtonConvertMultiple.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent event) {
                    
                    setStatus("Starting batch conversion ...");
                    
                    EChannel channel = (EChannel)jComboChannel.getSelectedItem();
                    
                    ConverterSetting setting = new ConverterSetting(
                            channel.getIndex(), 
                            jTextAreaNotes.getText(), 
                            Integer.parseInt(jTextFieldDivRatio.getText()),
                            Integer.parseInt(jTextFieldExpRatio.getText()),
                            Integer.parseInt(jTextFieldHighPass.getText()));
                    
                    try {
                        File dir = new File(jTextFieldFileName.getText()).getParentFile();
                        List<File> files = Arrays.asList(Optional.ofNullable(dir.listFiles(new WavFileFilter()))
                                .orElse(new File[0]));
                        
                        // set up progress bar
                        jProgressBar.setMinimum(0);
                        jProgressBar.setMaximum(files.size());
                        jProgressBar.setStringPainted(true);
                        
                        IProgressListener<String> listener = new IProgressListener<>() {
                            int fileCount;

                            @Override
                            public void update(String string) {
                                jProgressBar.setValue(++fileCount);
                                jProgressBar.setString(string);
                            }
                        };
                        ConverterWorker worker = new ConverterWorker(setting, listener, files);
                        worker.execute();
                    } catch (Exception e) {
                        setStatus(e.toString());
                    }
                }
            });
        }
        return jButtonConvertMultiple;
    }
    
    private JProgressBar getJProgressBar() {
        if (jProgressBar == null) {
            jProgressBar = new JProgressBar();
        }
        return jProgressBar;
    }
    
    private JComboBox<EChannel> getJComboChannel() {
        if (jComboChannel == null) {
            jComboChannel = new JComboBox<>(EChannel.values());
        }
        return jComboChannel;
    }
    
    public static void main(String[] args) {
        PropertyConfigurator.configure("log4j.properties");
     
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AnabatConverterGui().createAndShowGUI();
            }
        });
    }

}
