/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gymmanagementsystem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class Dashboard extends javax.swing.JFrame {
    private String uname;
    private PreparedStatement ps;
    private ResultSet rs;

    /**
     * Creates new form Dashboard
     */
    //Populate Member and Transaction Table 
    public void populateMemberAndTransactionTable(){
    //Populate Member Table
            DefaultTableModel memTable = (DefaultTableModel) jTable1.getModel();
            memTable.setRowCount(0);
            String getMemQuery ="SELECT * FROM tbl_member";
            try{
                ps = MyConnection.getConnection().prepareStatement(getMemQuery);
                rs = ps.executeQuery();
                while(rs.next()){
                    String mem_id = String.valueOf(rs.getInt("mem_id"));
                    String mem_lname = String.valueOf(rs.getString("mem_lname"));
                    String mem_fname = String.valueOf(rs.getString("mem_fname"));
                    String expiration_date = String.valueOf(rs.getString("expiration_date"));
                    
                    // Determine status based on expiration_date
                     String status = "Inactive";
                     if(expiration_date != null && !expiration_date.isEmpty()) {
                     java.sql.Date expDate = java.sql.Date.valueOf(expiration_date); // assumes "yyyy-MM-dd" format
                     java.sql.Date today = new java.sql.Date(System.currentTimeMillis());
                     if(expDate.compareTo(today) >= 0) {
                     status = "Active";
                     }
                     }
                    
                    String memRecord[] = {mem_id, mem_lname, mem_fname, expiration_date, status};
                    memTable.addRow(memRecord);
                    
                }
            }catch(Exception e){
               e.printStackTrace();
               JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage());
            }
    //Populate Transaction table
            DefaultTableModel transTable = (DefaultTableModel) jTable4.getModel();
            transTable.setRowCount(0);
            String getTransQuery ="SELECT t.transaction_id, m.mem_fname, m.mem_lname, s.sub_name, t.amount, t.Time FROM tbl_transaction t "
                    + "JOIN tbl_member m ON t.mem_id = m.mem_id JOIN tbl_subscription s ON t.sub_id = s.sub_id ORDER BY t.transaction_id";
            try{
                ps = MyConnection.getConnection().prepareStatement(getTransQuery);
                rs = ps.executeQuery();
                while(rs.next()){
                    String transaction_id = String.valueOf(rs.getInt("transaction_id"));
                    String mem_lname = String.valueOf(rs.getString("mem_lname"));
                    String mem_fname = String.valueOf(rs.getString("mem_fname"));
                    String sub_name = String.valueOf(rs.getString("sub_name"));
                    String amount = String.valueOf(rs.getInt("amount"));
                    String dateAndTime = String.valueOf(rs.getString("Time"));
                    
                    String transRecord[] = {transaction_id, mem_lname, mem_fname, sub_name, amount, dateAndTime};
                    transTable.addRow(transRecord);
                    
                }
            }catch(Exception e){
               e.printStackTrace();
               JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage());
            }
}
    //Populate Subcription Table
    public void populateSubscriptionTable() {
            DefaultTableModel subTable = (DefaultTableModel)jTable3.getModel();
            subTable.setRowCount(0);
            String getSubQuery = "Select * FROM tbl_subscription";
            try{
                ps = MyConnection.getConnection().prepareStatement(getSubQuery);
                rs = ps.executeQuery();
                while(rs.next()){
                    String sub_id = String.valueOf(rs.getInt("sub_id"));
                    String sub_name = String.valueOf(rs.getString("sub_name"));
                    String price = String.valueOf(rs.getInt("price"));
                    String duration = String.valueOf(rs.getString("duration"));
                    
                    String subRecord[] = {sub_id, sub_name, price, duration};
                    subTable.addRow(subRecord);
                    
                }
            }catch(Exception e){
               e.printStackTrace();
               JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage());
            }
                }
    //Populate Admin Table
        public void populateAdminTable() {
           DefaultTableModel adminTable = (DefaultTableModel)jTable2.getModel();
           adminTable.setRowCount(0);
           String getAdminQuery = "Select admin_ID, username, role FROM tbl_admin;";
           try{
               
               ps = MyConnection.getConnection().prepareStatement(getAdminQuery);         
               rs = ps.executeQuery();
               while(rs.next()){
                   String admin_id = String.valueOf(rs.getInt("admin_ID"));
                   String username = String.valueOf(rs.getString("username"));
                   String role = String.valueOf(rs.getString("role"));
                   
                   String adminRecord[] = {admin_id,username,role};
                   adminTable.addRow(adminRecord);
                   
               }
           }catch(Exception e){
               e.printStackTrace();
               JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage());
           }
               }
    
    //Populate Admin Logs
        public void populateAdminLogTable() {
            DefaultTableModel adminLogTable = (DefaultTableModel)admin_log.getModel();
            adminLogTable.setRowCount(0);
            String getAdminLogQuery = "Select * FROM tbl_admin_log;";
            try{
                ps = MyConnection.getConnection().prepareStatement(getAdminLogQuery);         
                rs = ps.executeQuery();
                while (rs.next()){
                   String username = String.valueOf(rs.getString("username"));
                   String action = String.valueOf(rs.getString("action"));
                   String dateAndTime = String.valueOf(rs.getString("time"));
                   
                   String adminLogRecord[] = {username,action,dateAndTime};
                   adminLogTable.addRow(adminLogRecord);
                }
            }catch(Exception e){
            }
}
    public Dashboard(String uname) {
        initComponents();
        populateSubscriptionTable();
        populateAdminTable();
        populateAdminLogTable();
        populateMemberAndTransactionTable();
        this.uname = uname;

    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jButton14 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        admin_log = new javax.swing.JTable();
        jButton11 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setBackground(new java.awt.Color(204, 204, 204));
        jTabbedPane1.setForeground(new java.awt.Color(102, 102, 102));
        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Member D", "Last Name", "First Name", "Expiration Date", "Status"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton6.setText("EDIT");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("DELETE");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jButton6)
                .addGap(18, 18, 18)
                .addComponent(jButton7)
                .addContainerGap(576, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("MEMBERS", jPanel1);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Subcription ID", "Name", "Price", "Duration"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        jButton8.setText("EDIT");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("DELETE");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton9, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(24, 24, 24))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 195, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jButton8)
                .addGap(36, 36, 36)
                .addComponent(jButton9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("SUBCRIPTIONS", jPanel2);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Admin ID", "Username", "Role"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jButton12.setText("EDIT");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setText("DELETE");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 7, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 25, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jButton12)
                .addGap(18, 18, 18)
                .addComponent(jButton13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("ADMIN", jPanel3);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Transaction ID", "Member Last Name", "Member First Name", "Subscription Type", "Amount", "Date and Time"
            }
        ));
        jScrollPane5.setViewportView(jTable4);

        jButton14.setText("Delete");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jButton14)
                .addGap(24, 24, 24))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 221, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jButton14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("TRABSACTION HISTORY", jPanel4);

        admin_log.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Username", "Action", "Date and Time"
            }
        ));
        jScrollPane4.setViewportView(admin_log);

        jButton11.setText("DELETE");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 202, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jButton11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("ADMIN LOGS", jPanel5);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 750, 710));

        jPanel7.setBackground(new java.awt.Color(102, 102, 102));
        jPanel7.setForeground(new java.awt.Color(153, 153, 153));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("AKI'S FITNESS");

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("GYM");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(383, 383, 383)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(384, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 70));

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));

        jButton1.setText("ADD/RENEW  MEMBER");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("ADD SUBSCRIPTION");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("ADD ADMIN");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("LOGOUT");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("EXIT");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addContainerGap(404, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 70, 150, 660));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        AddSubscription pi = new AddSubscription(this);
        pi.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        AddMember pi = new AddMember(this);
        pi.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        AddAdmin pi = new AddAdmin(this);
        pi.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        PreparedStatement ps;
        String addLogQuery = "INSERT INTO tbl_admin_log (username, action) VALUES (?, ?)";
        
        try{
            ps = MyConnection.getConnection().prepareStatement(addLogQuery);
                ps.setString(1, uname);
                ps.setString(2, "Logged Out");
                ps.executeUpdate();
             
                Login pi = new Login();
                pi.setVisible(true);
                this.dispose();
                
        }catch(SQLException ex){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        PreparedStatement ps;
        String addLogQuery = "INSERT INTO tbl_admin_log (username, action) VALUES (?, ?)";
        
        try{
            ps = MyConnection.getConnection().prepareStatement(addLogQuery);
                ps.setString(1, uname);
                ps.setString(2, "Logged Out");
                ps.executeUpdate();
             
                Login pi = new Login();
                pi.setVisible(true);
                System.exit(0);
                
        }catch(SQLException ex){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable3.getSelectedRow();
    if(selectedRow == -1) {
        JOptionPane.showMessageDialog(null, "Please select a subscription to delete.");
        return;
    }

    DefaultTableModel memTable = (DefaultTableModel) jTable3.getModel();
    String sub_id = memTable.getValueAt(selectedRow, 0).toString();
    
    int confirm = JOptionPane.showConfirmDialog(
        null,
        "Are you sure you want to delete this subscription?",
        "Confirm Delete",
        JOptionPane.YES_NO_OPTION
);

if (confirm != JOptionPane.YES_OPTION) {
    return;
}

String deleteMemberQuery = "DELETE FROM tbl_subscription WHERE sub_id = ?";

try {
    PreparedStatement ps = MyConnection.getConnection().prepareStatement(deleteMemberQuery);
    ps.setString(1, sub_id);
    ps.executeUpdate();
    ps.close();

    JOptionPane.showMessageDialog(null, "Subscription deleted successfully!");

    
    populateSubscriptionTable();

} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage());
}
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    int selectedRow = jTable1.getSelectedRow();
    if(selectedRow == -1) {
        JOptionPane.showMessageDialog(null, "Please select a member to edit.");
        return;
    }

    DefaultTableModel memTable = (DefaultTableModel) jTable1.getModel();
    String mem_id = memTable.getValueAt(selectedRow, 0).toString();
    String lname = memTable.getValueAt(selectedRow, 1).toString();
    String fname = memTable.getValueAt(selectedRow, 2).toString();
    String expiration_date = memTable.getValueAt(selectedRow, 3).toString();

    // Open EditMember form
    EditMember pi = new EditMember(mem_id, lname, fname, expiration_date, this);
    pi.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
            int selectedRow = jTable2.getSelectedRow();

         if(selectedRow == -1) {
        JOptionPane.showMessageDialog(null, "Please select an admin to edit.");
          return;
        }
        DefaultTableModel memTable = (DefaultTableModel) jTable2.getModel();
        String admin_id = memTable.getValueAt(jTable2.getSelectedRow(), 0).toString();
        String username = memTable.getValueAt(jTable2.getSelectedRow(), 1).toString();
        String role = memTable.getValueAt(jTable2.getSelectedRow(), 2).toString();
        EditAdmin pi = new EditAdmin(admin_id, username, role, this );
        pi.setVisible(true);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
            int selectedRow = jTable3.getSelectedRow();

         if(selectedRow == -1) {
        JOptionPane.showMessageDialog(null, "Please select a subscription to edit.");
          return;
        }
        DefaultTableModel subTable = (DefaultTableModel) jTable3.getModel();
        String sub_id = subTable.getValueAt(jTable3.getSelectedRow(), 0).toString();
        String subname = subTable.getValueAt(jTable3.getSelectedRow(), 1).toString();
        String duration = subTable.getValueAt(jTable3.getSelectedRow(), 2).toString();
        String price = subTable.getValueAt(jTable3.getSelectedRow(), 3).toString();
        EditSubscription pi = new EditSubscription(sub_id, subname, duration, price, this);
        pi.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable1.getSelectedRow();
    if(selectedRow == -1) {
        JOptionPane.showMessageDialog(null, "Please select a member to delete.");
        return;
    }

    DefaultTableModel memTable = (DefaultTableModel) jTable1.getModel();
    String mem_id = memTable.getValueAt(selectedRow, 0).toString();
    
    int confirm = JOptionPane.showConfirmDialog(
        null,
        "Are you sure you want to delete this member?",
        "Confirm Delete",
        JOptionPane.YES_NO_OPTION
);

if (confirm != JOptionPane.YES_OPTION) {
    return;
}

String deleteMemberQuery = "DELETE FROM tbl_member WHERE mem_id = ?";

try {
    PreparedStatement ps = MyConnection.getConnection().prepareStatement(deleteMemberQuery);
    ps.setString(1, mem_id);
    ps.executeUpdate();
    ps.close();

    JOptionPane.showMessageDialog(null, "Member deleted successfully!");

    
    populateMemberAndTransactionTable();

} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage());
}
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable2.getSelectedRow();
    if(selectedRow == -1) {
        JOptionPane.showMessageDialog(null, "Please select an admin to delete.");
        return;
    }

    DefaultTableModel memTable = (DefaultTableModel) jTable2.getModel();
    String admin_id = memTable.getValueAt(selectedRow, 0).toString();
    
    int confirm = JOptionPane.showConfirmDialog(
        null,
        "Are you sure you want to delete this admin?",
        "Confirm Delete",
        JOptionPane.YES_NO_OPTION
);

if (confirm != JOptionPane.YES_OPTION) {
    return;
}

String deleteMemberQuery = "DELETE FROM tbl_admin WHERE admin_id = ?";

try {
    PreparedStatement ps = MyConnection.getConnection().prepareStatement(deleteMemberQuery);
    ps.setString(1, admin_id);
    ps.executeUpdate();
    ps.close();

    JOptionPane.showMessageDialog(null, "Admin deleted successfully!");

    
    populateAdminTable();

} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage());
}
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
       int selectedRow = jTable4.getSelectedRow();
    if(selectedRow == -1) {
        JOptionPane.showMessageDialog(null, "Please select a transaction to delete.");
        return;
    }

    DefaultTableModel memTable = (DefaultTableModel) jTable4.getModel();
    String trans_id = memTable.getValueAt(selectedRow, 0).toString();
    
    int confirm = JOptionPane.showConfirmDialog(
        null,
        "Are you sure you want to delete this Transaction?",
        "Confirm Delete",
        JOptionPane.YES_NO_OPTION
);

if (confirm != JOptionPane.YES_OPTION) {
    return;
}

String deleteMemberQuery = "DELETE FROM tbl_transaction WHERE transaction_id = ?";

try {
    PreparedStatement ps = MyConnection.getConnection().prepareStatement(deleteMemberQuery);
    ps.setString(1, trans_id);
    ps.executeUpdate();
    ps.close();

    JOptionPane.showMessageDialog(null, "Transaction deleted successfully!");

    
    populateMemberAndTransactionTable();

} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage());
} 
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        int selectedRow = admin_log.getSelectedRow();
    if(selectedRow == -1) {
        JOptionPane.showMessageDialog(null, "Please select an admin log to delete.");
        return;
    }

    DefaultTableModel memTable = (DefaultTableModel) admin_log.getModel();
    String dateAndTime = memTable.getValueAt(selectedRow, 2).toString();
    
    int confirm = JOptionPane.showConfirmDialog(
        null,
        "Are you sure you want to delete this admin og?",
        "Confirm Delete",
        JOptionPane.YES_NO_OPTION
);

if (confirm != JOptionPane.YES_OPTION) {
    return;
}

String deleteMemberQuery = "DELETE FROM tbl_admin_log WHERE Time = ?";

try {
    PreparedStatement ps = MyConnection.getConnection().prepareStatement(deleteMemberQuery);
    ps.setString(1, dateAndTime);
    ps.executeUpdate();
    ps.close();

    JOptionPane.showMessageDialog(null, "Admin Log deleted successfully!");

    
    populateAdminLogTable();

} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage());
}
    }//GEN-LAST:event_jButton11ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable admin_log;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    // End of variables declaration//GEN-END:variables
}
