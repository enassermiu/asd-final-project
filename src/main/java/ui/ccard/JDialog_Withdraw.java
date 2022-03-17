package ui.ccard;

import framework.service.AccountService;
import framework.service.AccountServiceImpl;
import framework.service.command.Command;


public class JDialog_Withdraw extends javax.swing.JDialog
{
   private AccountService accountService;

    private CardFrm parentframe;
    private String name;
	private Command command;


	public JDialog_Withdraw(Command command, CardFrm parent, String aname )
	{
		super(parent);
		accountService = AccountServiceImpl.getInstance();
		this.command = command;

		parentframe=parent;
		name=aname;
		
		// This code is automatically generated by Visual Cafe when you add
		// components to the visual environment. It instantiates and initializes
		// the components. To modify the code, only use code syntax that matches
		// what Visual Cafe can generate, or Visual Cafe may be unable to back
		// parse your Java file into its visual environment.

		setTitle("Charge Account");
		setModal(true);
		getContentPane().setLayout(null);

		setSize(300,150);
		setLocationRelativeTo(null);
		setVisible(false);

		JLabel1.setText("Name");
		getContentPane().add(JLabel1);
		JLabel1.setForeground(java.awt.Color.black);
		JLabel1.setBounds(12,12,48,24);
		JLabel2.setText("Amount");
		getContentPane().add(JLabel2);
		JLabel2.setForeground(java.awt.Color.black);
		JLabel2.setBounds(12,36,48,24);
		JTextField_NAME.setEditable(false);
		getContentPane().add(JTextField_NAME);
		JTextField_NAME.setBounds(84,12,156,20);
		getContentPane().add(JTextField_AMT);
		JTextField_AMT.setBounds(84,36,156,20);
		JButton_OK.setText("OK");
		JButton_OK.setActionCommand("OK");
		getContentPane().add(JButton_OK);
		JButton_OK.setBounds(48,84,84,24);
		JButton_Calcel.setText("Cancel");
		JButton_Calcel.setActionCommand("Cancel");
		getContentPane().add(JButton_Calcel);
		JButton_Calcel.setBounds(156,84,84,24);

	    JTextField_NAME.setText(name);
	

		SymAction lSymAction = new SymAction();
		JButton_OK.addActionListener(lSymAction);
		JButton_Calcel.addActionListener(lSymAction);

	}




	javax.swing.JLabel JLabel1 = new javax.swing.JLabel();
	javax.swing.JLabel JLabel2 = new javax.swing.JLabel();
	javax.swing.JTextField JTextField_NAME = new javax.swing.JTextField();
	javax.swing.JTextField JTextField_AMT = new javax.swing.JTextField();
	javax.swing.JButton JButton_OK = new javax.swing.JButton();
	javax.swing.JButton JButton_Calcel = new javax.swing.JButton();



	class SymAction implements java.awt.event.ActionListener
	{
		public void actionPerformed(java.awt.event.ActionEvent event)
		{
			Object object = event.getSource();
			if (object == JButton_OK)
				JButtonOK_actionPerformed(event);
			else if (object == JButton_Calcel)
				JButtonCalcel_actionPerformed(event);
		}
	}

	void JButtonOK_actionPerformed(java.awt.event.ActionEvent event)
	{
        parentframe.insertedAmount =JTextField_AMT.getText();
		try {
//			accountService.withdraw(parentframe.ccnumber, Double.parseDouble(parentframe.insertedAmount));
			command.execute(parentframe.ccnumber, Double.parseDouble(parentframe.insertedAmount));
		} catch (Exception e) {
			e.printStackTrace();
		}
		dispose();
	}

	void JButtonCalcel_actionPerformed(java.awt.event.ActionEvent event)
	{
		dispose();
	}
}