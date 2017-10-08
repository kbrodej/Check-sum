import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.security.MessageDigest;

public class Master extends JDialog {
    private JPanel contentPane;
    private JButton selectFileButton;
    private JTextField pathField;
    private JButton computeButton;
    private JTextField sha1Computed;
    private JTextField sha256Computed;
    private JTextField md5Computed;
    private JTextField md5Pasted;
    private JTextField sha1Pasted;
    private JTextField sha256Pasted;
    private JTextField md5Status;
    private JTextField sha256Status;
    private JTextField sha1Satus;

    public Master() throws Exception{
        contentPane.setPreferredSize(new Dimension(800, 600));
        setContentPane(contentPane);
        setModal(true);

        selectFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                        JFileChooser fc = new JFileChooser();
                        fc.showOpenDialog(getParent());
                        String path = fc.getSelectedFile().getAbsolutePath();
                        pathField.setText(path);
            }
        });
        computeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(md5Pasted.getText().trim().isEmpty()){
                    md5Computed.setText("No md5 to compare");
                    md5Status.setText("MD5 Error");
                }else{
                    try {
                        Compute x = new Compute();
                        x.setAlgorithm("MD5");
                        x.Calculate(pathField.getText());
                        md5Computed.setText(x.getValue());
                        if(md5Pasted.getText().trim().equals(md5Computed.getText().trim())){
                            md5Status.setText(x.getAlgorithm() + " OK");
                        }
                        else{
                            md5Status.setText(x.getAlgorithm() + " Error");
                        }
                    } catch (Exception x){
                        System.out.println(x.getStackTrace());
                    }
                }
                if(sha1Pasted.getText().trim().isEmpty()){
                    sha1Computed.setText("No SHA1 to compare");
                    sha1Satus.setText("SHA1 Error");
                } else {
                    try {
                        Compute y = new Compute();
                        y.setAlgorithm("SHA-1");
                        y.Calculate(pathField.getText());
                        sha1Computed.setText(y.getValue());
                        if(sha1Pasted.getText().trim().equals(sha1Computed.getText().trim())){
                            sha1Satus.setText(y.getAlgorithm() + " OK");
                        }
                        else{
                            sha1Satus.setText(y.getAlgorithm() + " Error");
                        }
                    } catch (Exception y){
                        System.out.println(y.getStackTrace());
                    }
                }
                if(sha256Pasted.getText().trim().isEmpty()){
                    sha256Computed.setText("No SHA256 to compare");
                    sha256Status.setText("SHA256 Error");
                } else {
                    try {
                        Compute z = new Compute();
                        z.setAlgorithm("SHA-256");
                        z.Calculate(pathField.getText());
                        sha256Computed.setText(z.getValue());
                        if(sha256Pasted.getText().trim().equals(sha256Computed.getText().trim())){
                            sha256Status.setText(z.getAlgorithm() + " OK");
                        }
                        else{
                            sha256Status.setText(z.getAlgorithm() + " Error");
                        }
                    } catch (Exception z){
                        System.out.println(z.getStackTrace());
                    }
                }

            }
        });
    }


    /**
     * Created by kleme on 08-Oct-17.
     */
    public static class Compute {
        private String value = "";
        private String algorithm ="";
        private void Calculate(String path) throws Exception{

            MessageDigest md = MessageDigest.getInstance(this.algorithm);

            FileInputStream fis = new FileInputStream(path);

            byte[] pathBytes = new byte[1024];

            int nread = 0;

            while ((nread = fis.read(pathBytes)) != -1) {
                md.update(pathBytes, 0, nread);
            }

            byte[] mdbytes = md.digest();

            StringBuffer sb = new StringBuffer("");
            for (int i = 0; i < mdbytes.length; i++) {
                sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //String pot = "C:/Users/kleme/Downloads/jre-8u144-windows-x64.exe";
            this.value = sb.toString();
        }

        public String getValue() {
            return this.value;
        }
        public void setAlgorithm(String algorithm){
            this.algorithm = algorithm;
        }
        public String getAlgorithm() {
            return this.algorithm;
        }
    }
    public static void main(String[] args) throws Exception{
        Master dialog = new Master();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}

