package ui.bank;

import banking.commands.*;
import framework.model.Account;
import framework.service.AccountService;
import framework.service.AccountServiceImpl;
import framework.service.command.CommandInvoker;
import ui.ccard.JDialogGenBill;

import java.awt.*;
import java.util.Collection;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;

/**
 * A basic JFC based application.
 */
public class BankFrm extends javax.swing.JFrame {

    private AccountService accountService;
    private CommandInvoker commandInvoker;

    /****
     * init variables in the object
     ****/
    String accountnr, insertedAmount;
    boolean newaccount;
    private DefaultTableModel model;
    private JTable JTable1;
    private JScrollPane JScrollPane1;
    BankFrm myframe;

    public BankFrm() {
        myframe = this;

        accountService = AccountServiceImpl.getInstance();
        accountService.seedBankAccounts();
        this.commandInvoker = new CommandInvoker();

        setTitle("Bank Application.");
        setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(0, 0));

        setSize(600, 350);
        setLocationRelativeTo(null);
        setVisible(false);

        JPanel1.setLayout(null);
        getContentPane().add(BorderLayout.CENTER, JPanel1);
        JPanel1.setBounds(0, 0, 575, 310);
		/*
		/Add five buttons on the pane 
		/for Adding personal account, Adding company account
		/Deposit, Withdraw and Exit from the system
		*/
        JScrollPane1 = new JScrollPane();
        model = new DefaultTableModel();
        JTable1 = new JTable(model);
        model.addColumn("AccountNr");
        model.addColumn("Name");
        model.addColumn("City");
        model.addColumn("P/C");
        model.addColumn("Ch/S");
        model.addColumn("Amount");

        newaccount = false;

        JPanel1.add(JScrollPane1);
        JScrollPane1.setBounds(12, 92, 444, 160);
        JScrollPane1.getViewport().add(JTable1);
        JTable1.setBounds(0, 0, 420, 0);
//        rowdata = new Object[8];

        JButton_PerAC.setText("Add personal account");
        JPanel1.add(JButton_PerAC);
        JButton_PerAC.setBounds(24, 20, 192, 33);
        JButton_CompAC.setText("Add company account");
        JButton_CompAC.setActionCommand("jbutton");
        JPanel1.add(JButton_CompAC);
        JButton_CompAC.setBounds(240, 20, 192, 33);
        JButton_Deposit.setText("Deposit");
        JPanel1.add(JButton_Deposit);
        JButton_Deposit.setBounds(468, 104, 96, 33);
        JButton_Withdraw.setText("Withdraw");
        JPanel1.add(JButton_Withdraw);
        JButton_Addinterest.setBounds(448, 20, 106, 33);
        JButton_Addinterest.setText("Add interest");
        JPanel1.add(JButton_Addinterest);
        JButton_Report.setBounds(455, 60, 106, 33);
        JButton_Report.setText("Report");
        JPanel1.add(JButton_Report);
        JButton_Withdraw.setBounds(468, 164, 96, 33);
        JButton_Exit.setText("Exit");
        JPanel1.add(JButton_Exit);
        JButton_Exit.setBounds(468, 248, 96, 31);
        // lineBorder1.setRoundedCorners(true);
        // lineBorder1.setLineColor(java.awt.Color.green);
        //$$ lineBorder1.move(24,312);

        JButton_PerAC.setActionCommand("jbutton");

        SymWindow aSymWindow = new SymWindow();
        this.addWindowListener(aSymWindow);
        SymAction lSymAction = new SymAction();
        JButton_Exit.addActionListener(lSymAction);
        JButton_PerAC.addActionListener(lSymAction);
        JButton_CompAC.addActionListener(lSymAction);
        JButton_Deposit.addActionListener(lSymAction);
        JButton_Withdraw.addActionListener(lSymAction);
        JButton_Addinterest.addActionListener(lSymAction);
        JButton_Report.addActionListener(lSymAction);
        updateAccountGrid();

        setCommands();
    }

    private void setCommands() {
        this.commandInvoker.addCommand("DEPOSIT", new DepositCommand(accountService));
        this.commandInvoker.addCommand("WITHDRAW", new WithdrawCommand(accountService));
        this.commandInvoker.addCommand("ADD_INTEREST", new AddInterestCommand(accountService));
        this.commandInvoker.addCommand("ADD_PERSONAL_ACCOUNT", new AddPersonalAccountCommand(accountService));
        this.commandInvoker.addCommand("ADD_COMPANY_ACCOUNT", new AddCompanyAccountCommand(accountService));
    }


    /*****************************************************
     * The entry point for this application.
     * Sets the Look and Feel to the System Look and Feel.
     * Creates a new JFrame1 and makes it visible.
     *****************************************************/
    static public void main(String args[]) {
        try {
            // Add the following code if you want the Look and Feel
            // to be set to the Look and Feel of the native system.

            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
            }

            //Create a new instance of our application's frame, and make it visible.
            (new BankFrm()).setVisible(true);
        } catch (Throwable t) {
            t.printStackTrace();
            //Ensure the application exits with an error condition.
            System.exit(1);
        }
    }


    javax.swing.JPanel JPanel1 = new javax.swing.JPanel();
    javax.swing.JButton JButton_PerAC = new javax.swing.JButton();
    javax.swing.JButton JButton_CompAC = new javax.swing.JButton();
    javax.swing.JButton JButton_Deposit = new javax.swing.JButton();
    javax.swing.JButton JButton_Withdraw = new javax.swing.JButton();
    javax.swing.JButton JButton_Addinterest = new javax.swing.JButton();
    javax.swing.JButton JButton_Report = new javax.swing.JButton();
    javax.swing.JButton JButton_Exit = new javax.swing.JButton();

    void exitApplication() {
        try {
            this.setVisible(false);    // hide the Frame
            this.dispose();            // free the system resources
            System.exit(0);            // close the application
        } catch (Exception e) {
        }
    }

    class SymWindow extends java.awt.event.WindowAdapter {
        public void windowClosing(java.awt.event.WindowEvent event) {
            Object object = event.getSource();
            if (object == BankFrm.this)
                BankFrm_windowClosing(event);
        }
    }

    void BankFrm_windowClosing(java.awt.event.WindowEvent event) {
        // to do: code goes here.

        BankFrm_windowClosing_Interaction1(event);
    }

    void BankFrm_windowClosing_Interaction1(java.awt.event.WindowEvent event) {
        try {
            this.exitApplication();
        } catch (Exception e) {
        }
    }

    class SymAction implements java.awt.event.ActionListener {
        public void actionPerformed(java.awt.event.ActionEvent event) {
            Object object = event.getSource();
            if (object == JButton_Exit)
                JButtonExit_actionPerformed(event);
            else if (object == JButton_PerAC)
                JButtonPerAC_actionPerformed(event);
            else if (object == JButton_CompAC)
                JButtonCompAC_actionPerformed(event);
            else if (object == JButton_Deposit)
                JButtonDeposit_actionPerformed(event);
            else if (object == JButton_Withdraw)
                JButtonWithdraw_actionPerformed(event);
            else if (object == JButton_Report) {
                try {
                    JButtonGenerateReport_actionPerformed(event);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if (object == JButton_Addinterest) {
                try {
                    JButtonAddinterest_actionPerformed(event);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    //When the Exit button is pressed this code gets executed
    //this will exit from the system
    void JButtonExit_actionPerformed(java.awt.event.ActionEvent event) {
        System.exit(0);
    }

    void JButtonPerAC_actionPerformed(java.awt.event.ActionEvent event) {
		/*
		 JDialog_AddPAcc type object is for adding personal information
		 construct a JDialog_AddPAcc type object 
		 set the boundaries and show it 
		*/

        JDialog_AddPAcc pac = new JDialog_AddPAcc(commandInvoker.getCommand("ADD_PERSONAL_ACCOUNT"), myframe);
        pac.show();

        if (newaccount) {
            // add row to table
            addAccountToGrid(accountService.getAccount(accountnr));

            JTable1.getSelectionModel().setAnchorSelectionIndex(-1);
            newaccount = false;
        }
    }

    void JButtonCompAC_actionPerformed(java.awt.event.ActionEvent event) {
		/*
		 construct a JDialog_AddCompAcc type object 
		 set the boundaries and 
		 show it 
		*/

        JDialog_AddCompAcc pac = new JDialog_AddCompAcc(commandInvoker.getCommand("ADD_COMPANY_ACCOUNT"), myframe);
        pac.show();

        if (newaccount) {
            // add row to table
            addAccountToGrid(accountService.getAccount(accountnr));
            JTable1.getSelectionModel().setAnchorSelectionIndex(-1);
            newaccount = false;
        }
    }

    void JButtonDeposit_actionPerformed(java.awt.event.ActionEvent event) {
        // get selected name
        int selection = JTable1.getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            accountnr = model.getValueAt(selection, 0).toString();

            //Show the dialog for adding deposit amount for the current mane
            JDialog_Deposit dep = new JDialog_Deposit(commandInvoker.getCommand("DEPOSIT"), myframe, accountnr);
            dep.show();

            updateAccountGrid();
        }
    }

    void JButtonWithdraw_actionPerformed(java.awt.event.ActionEvent event) {
        // get selected name
        int selection = JTable1.getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            accountnr = model.getValueAt(selection, 0).toString();

            //Show the dialog for adding withdraw amount for the current mane
            JDialog_Withdraw wd = new JDialog_Withdraw(commandInvoker.getCommand("WITHDRAW"), myframe, accountnr);
            wd.show();

            updateAccountGrid();
        }
    }

    void JButtonAddinterest_actionPerformed(java.awt.event.ActionEvent event) throws Exception {
        commandInvoker.getCommand("ADD_INTEREST").execute();
        updateAccountGrid();

        JOptionPane.showMessageDialog(JButton_Addinterest, "Add interest to all accounts",
                "Add interest to all accounts", JOptionPane.WARNING_MESSAGE);
    }

    void JButtonGenerateReport_actionPerformed(java.awt.event.ActionEvent event) throws Exception {
        JDialogGenRepo reportFrm = new JDialogGenRepo(myframe);
        reportFrm.show();
    }

    private void addAccountToGrid(Account account) {
        Object[] data = new Object[8];

        data[0] = account.getAccountNumber();
        data[1] = account.getCustomer().getName();
        data[2] = account.getCustomer().getAddress().getCity();
        data[3] = account.getCustomer().getCustomerTpeCode();
        data[4] = account.getAccountTypeCode();
        data[5] = account.getBalance();

        model.addRow(data);
    }

    private void updateAccountGrid() {
        Collection<Account> accounts = accountService.getAllAccounts();

        model.setRowCount(0);
        accounts.forEach(a -> addAccountToGrid(a));
    }
}
