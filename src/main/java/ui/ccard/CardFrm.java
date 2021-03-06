package ui.ccard;

import creditcard.commands.*;
import creditcard.model.CreditCardAccount;
import framework.model.Account;
import framework.service.AccountService;
import framework.service.AccountServiceImpl;
import framework.service.command.CommandInvoker;

import java.awt.BorderLayout;
import java.util.Collection;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 * A basic JFC based application.
 */
public class CardFrm extends javax.swing.JFrame {
    private AccountService accountService;
    private CommandInvoker commandInvoker;


    /****
     * init variables in the object
     ****/
    String ccname, ccnumber, insertedAmount;
    boolean newaccount;
    private DefaultTableModel model;
    private JTable JTable1;
    private JScrollPane JScrollPane1;
    CardFrm thisframe;

    public CardFrm() {
        thisframe = this;

        accountService = AccountServiceImpl.getInstance();
        this.commandInvoker = new CommandInvoker();

        accountService.seedCreditAccounts();

        setTitle("Credit-card processing Application.");
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
        model.addColumn("Name");
        model.addColumn("CC number");
        model.addColumn("Exp date");
        model.addColumn("Type");
        model.addColumn("Balance");
        newaccount = false;


        JPanel1.add(JScrollPane1);
        JScrollPane1.setBounds(12, 92, 444, 160);
        JScrollPane1.getViewport().add(JTable1);
        JTable1.setBounds(0, 0, 420, 0);
//        rowdata = new Object[8];

        JButton_NewCCAccount.setText("Add Personal Credit-card account");
        JPanel1.add(JButton_NewCCAccount);
        JButton_NewCCAccount.setBounds(24, 20, 192, 33);

        JButton_NewCompanyCCAccount.setText("Add Company Credit-card account");
        JPanel1.add(JButton_NewCompanyCCAccount);
        JButton_NewCompanyCCAccount.setBounds(24, 50, 192, 33);

        JButton_GenBill.setText("Generate Monthly bills");
        JButton_GenBill.setActionCommand("jbutton");
        JPanel1.add(JButton_GenBill);
        JButton_GenBill.setBounds(240, 20, 192, 33);
        JButton_Deposit.setText("Deposit");
        JPanel1.add(JButton_Deposit);
        JButton_Deposit.setBounds(468, 104, 96, 33);
        JButton_Withdraw.setText("Charge");
        JPanel1.add(JButton_Withdraw);
        JButton_Withdraw.setBounds(468, 164, 96, 33);
        JButton_Exit.setText("Exit");
        JPanel1.add(JButton_Exit);
        JButton_Exit.setBounds(468, 248, 96, 31);


        JButton_GenBill.setActionCommand("jbutton");

        SymWindow aSymWindow = new SymWindow();
        this.addWindowListener(aSymWindow);
        SymAction lSymAction = new SymAction();
        JButton_Exit.addActionListener(lSymAction);
        JButton_NewCCAccount.addActionListener(lSymAction);
        JButton_NewCompanyCCAccount.addActionListener(lSymAction);
        JButton_GenBill.addActionListener(lSymAction);
        JButton_Deposit.addActionListener(lSymAction);
        JButton_Withdraw.addActionListener(lSymAction);

        updateAccountGrid();
        setCommands();
    }

    private void setCommands() {
        this.commandInvoker.addCommand("DEPOSIT", new DepositCommand(accountService));
        this.commandInvoker.addCommand("CHARGE", new ChargeCommand(accountService));
        this.commandInvoker.addCommand("GENERATE_MONTHLY_BILL", new GenerateMonthlyBillCommand(accountService));
        this.commandInvoker.addCommand("ADD_CREDIT_CARD_ACCOUNT", new AddCreditCardAccountCommand(accountService));
        this.commandInvoker.addCommand("ADD_COMPANY_CREDIT_CARD_ACCOUNT", new AddCompanyCreditCardAccountCommand(accountService));
        this.commandInvoker.addCommand("ADD_INTEREST", new AddInterestCommand(accountService));

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
            (new CardFrm()).setVisible(true);
        } catch (Throwable t) {
            t.printStackTrace();
            //Ensure the application exits with an error condition.
            System.exit(1);
        }
    }


    javax.swing.JPanel JPanel1 = new javax.swing.JPanel();
    javax.swing.JButton JButton_NewCCAccount = new javax.swing.JButton();
    javax.swing.JButton JButton_NewCompanyCCAccount = new javax.swing.JButton();
    javax.swing.JButton JButton_GenBill = new javax.swing.JButton();
    javax.swing.JButton JButton_Deposit = new javax.swing.JButton();
    javax.swing.JButton JButton_Withdraw = new javax.swing.JButton();
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
            if (object == CardFrm.this)
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
            else if (object == JButton_NewCCAccount)
                JButtonNewCCAC_actionPerformed(event);
            else if (object == JButton_NewCompanyCCAccount)
                JButtonNewCompanyCCAccount_actionPerformed(event);
            else if (object == JButton_GenBill)
                JButtonGenerateBill_actionPerformed(event);
            else if (object == JButton_Deposit)
                JButtonDeposit_actionPerformed(event);
            else if (object == JButton_Withdraw)
                JButtonWithdraw_actionPerformed(event);
        }
    }

    //When the Exit button is pressed this code gets executed
    //this will exit from the system
    void JButtonExit_actionPerformed(java.awt.event.ActionEvent event) {
        System.exit(0);
    }

    void JButtonNewCCAC_actionPerformed(java.awt.event.ActionEvent event) {
		/*
		 JDialog_AddPAcc type object is for adding personal information
		 construct a JDialog_AddPAcc type object 
		 set the boundaries and show it 
		*/

        JDialog_AddCCAccount ccac = new JDialog_AddCCAccount(commandInvoker.getCommand("ADD_CREDIT_CARD_ACCOUNT"), thisframe);
        ccac.show();

        if (newaccount) {
            // add row to table
            addAccountToGrid((CreditCardAccount)accountService.getAccount(ccnumber));

            JTable1.getSelectionModel().setAnchorSelectionIndex(-1);
            newaccount = false;
        }
    }

    void JButtonNewCompanyCCAccount_actionPerformed(java.awt.event.ActionEvent event) {
		/*
		 JDialog_AddPAcc type object is for adding personal information
		 construct a JDialog_AddPAcc type object
		 set the boundaries and show it
		*/

        JDialog_AddCompAcc ccac = new JDialog_AddCompAcc(commandInvoker.getCommand("ADD_COMPANY_CREDIT_CARD_ACCOUNT"), thisframe);
        ccac.show();

        if (newaccount) {
            // add row to table
            addAccountToGrid((CreditCardAccount)accountService.getAccount(ccnumber));

            JTable1.getSelectionModel().setAnchorSelectionIndex(-1);
            newaccount = false;
        }
    }

    void JButtonGenerateBill_actionPerformed(java.awt.event.ActionEvent event) {
        JDialogGenBill billFrm = new JDialogGenBill(commandInvoker.getCommand("GENERATE_MONTHLY_BILL"));
        billFrm.show();
    }

    void JButtonDeposit_actionPerformed(java.awt.event.ActionEvent event) {
        // get selected name
        int selection = JTable1.getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            ccname = model.getValueAt(selection, 0).toString();
            ccnumber = model.getValueAt(selection, 1).toString();

            //Show the dialog for adding deposit amount for the current mane
            JDialog_Deposit dep = new JDialog_Deposit(commandInvoker.getCommand("DEPOSIT"), thisframe, ccname);
            dep.show();

            updateAccountGrid();
        }
    }

    void JButtonWithdraw_actionPerformed(java.awt.event.ActionEvent event) {
        // get selected name
        int selection = JTable1.getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            ccname = model.getValueAt(selection, 0).toString();
            ccnumber = model.getValueAt(selection, 1).toString();

            //Show the dialog for adding withdraw amount for the current mane
            JDialog_Withdraw wd = new JDialog_Withdraw(commandInvoker.getCommand("CHARGE"), thisframe, ccname);
            wd.show();

            updateAccountGrid();
        }
    }

    private void addAccountToGrid(CreditCardAccount account) {
        Object[] data = new Object[8];

        data[0] = account.getCustomer().getName();
        data[1] = account.getCcNumber();
        data[2] = account.getExpirationDate();
        data[3] = account.getAccountTypeCode();
        //data[4] = account.getCustomer().getCustomerTpeCode();
        data[4] = account.getBalance();

        model.addRow(data);
    }

    private void updateAccountGrid() {
        Collection<Account> accounts = accountService.getAllAccounts();

        model.setRowCount(0);
        accounts.forEach(a -> addAccountToGrid((CreditCardAccount)a));
    }
}
