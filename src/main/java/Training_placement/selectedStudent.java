/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Training_placement;



import javax.mail.PasswordAuthentication;
import javax.mail.Authenticator;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class selectedStudent extends javax.swing.JFrame {

    /**
     * Creates new form selectedStudent
     */
    static Connection conn;
    static PreparedStatement stmt;
    ResultSet rs;
    int flag;
//    public selectedStudent() {
//        initComponents();
//        //connect();
//    }
    public static void connect() 
    {
             String  dburl="jdbc:oracle:thin:@//localhost:1521/xe";
             String user="system";
             String pass="system";
                 try {
                     //Class.forName("oracle.jdbc.OracleDiver");
                     conn=DriverManager.getConnection(dburl,user,pass);
                 } catch (SQLException ex) {
                     Logger.getLogger(StudentAddPage.class.getName()).log(Level.SEVERE, null, ex);
                 }
        
        
    }
   public selectedStudent(String name) {
    initComponents();
    connect();
    int flag = 0;
    boolean found = false;
    String query = "select s.prn_no,s.stu_name,s.email_id,s.contact,s.gender,s.grade,s.branch,c.cmp_id,c.cmp_name from tandpstudentdata s inner join companydetails c on s.cmp_id=c.cmp_id where s.grade>=c.cutoff";
    try {
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        ResultSetMetaData rsd = rs.getMetaData();
        int c = rsd.getColumnCount();
        DefaultTableModel d = (DefaultTableModel) selected.getModel();
        d.setRowCount(0);

        while (rs.next()) {
            Vector v = new Vector(); // Moved inside the loop
            String prn = rs.getString("prn_no");
            String sname = rs.getString("stu_name");
            String email = rs.getString("email_id");
            String cont = rs.getString("contact");
            String gen = rs.getString("gender");
            float grd = rs.getFloat("grade");
            String brnch = rs.getString("branch");
            String cmp = rs.getString("cmp_id");
            String cname = rs.getString("cmp_name");

            if(name.equalsIgnoreCase(prn) || name.equalsIgnoreCase(cmp)) {
                flag = 1;

                // Add values to the Vector
                v.add(prn);
                v.add(sname);
                v.add(email);
                v.add(cont);
                v.add(gen);
                v.add(grd);
                v.add(brnch);
                v.add(cmp);
                v.add(cname);

                d.addRow(v);
                
                
            }
            found = true;
        }

        if (flag == 0) {
            JOptionPane.showMessageDialog(this, "Not Selected for any company.....");
        }
        if (!found) {
            JOptionPane.showMessageDialog(this, "Exception...");
        }
    } catch (SQLException ex) {
        Logger.getLogger(selectedStudent.class.getName()).log(Level.SEVERE, null, ex);
    }
}

     private void sendemail(String msg, String sub, String to[], String from) {
         String host="smtp.gmail.com";
         
         Properties prop=System.getProperties();
         prop.put("mail.smtp.host", host);
         prop.put("mail.smtp.port", "465");
         prop.put("mail.smtp.ssl.enable", true);
         prop.put("mail.smtp.auth", true);
         
         Session ss=Session.getInstance(prop, new Authenticator(){
             @Override protected PasswordAuthentication getPasswordAuthentication(){
                 return new PasswordAuthentication("trainingandplacementofficertpo@gmail.com","dnktrptuzzjnepgr");
             }
         
         } );
         
         ss.setDebug(true);
         MimeMessage mmsg=new MimeMessage(ss);
        try {
            mmsg.setFrom(from);
            for(String recep:to){
            mmsg.addRecipients(Message.RecipientType.TO, recep);
        }
            
            mmsg.setSubject(sub);
            mmsg.setText(msg);
            
            Transport.send(mmsg);
            JOptionPane.showMessageDialog(this, "Email send");
        } catch (MessagingException ex) {
            Logger.getLogger(selectedStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
            

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        selected = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(102, 255, 255));

        jLabel1.setFont(new java.awt.Font("Sitka Text", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("Student Selected List");

        selected.setBackground(new java.awt.Color(0, 204, 204));
        selected.setFont(new java.awt.Font("Sylfaen", 1, 14)); // NOI18N
        selected.setForeground(new java.awt.Color(255, 255, 255));
        selected.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student Prn", " Name", " Email", "Contact", "Gender", "Grade", "Branch", "Company id", "Company name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        selected.setGridColor(new java.awt.Color(0, 204, 204));
        selected.setRowHeight(40);
        jScrollPane2.setViewportView(selected);

        jButton1.setBackground(new java.awt.Color(0, 153, 153));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Send Mail");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 754, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(180, 180, 180))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(327, 327, 327)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String msg="You are selected for placment drive...thank you";
        //String to="khadeyuvraj8@gmail.com";
        String from="trainingandplacementofficertpo@gmail.com";
        String sub="Selecting for drive";
        
        List<String> emails=new ArrayList<>();
        /*String Query="select s.email_id from tandpstudentdata s inner join companydetails c on s.cmp_id=c.cmp_id where s.grade > c.cutoff";
        try {
            stmt=conn.prepareStatement(Query);
            rs=stmt.executeQuery();
            //boolean found=false;
            while(rs.next())
            {
                String email=rs.getString("email_id");
                emails.add(email);
                //found=true;

            }
        } catch (SQLException ex) {
            Logger.getLogger(selectedStudent.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        DefaultTableModel d = (DefaultTableModel) selected.getModel();
        int row=d.getRowCount();
        for(int i=0;i<row;i++)
        {
            String value=selected.getValueAt(i, 2).toString();
            emails.add(value);
        }
        
        String[] to=emails.toArray(new String[0]);
        sendemail(msg,sub,to,from);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(selectedStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(selectedStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(selectedStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(selectedStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable selected;
    // End of variables declaration//GEN-END:variables

   
}
