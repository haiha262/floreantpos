package com.floreantpos.bo.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JDialog;

import com.floreantpos.config.TerminalConfig;
import com.floreantpos.main.Application;
import com.floreantpos.swing.PosUIManager;
import com.floreantpos.ui.dialog.NumberSelectionDialog2;
import com.floreantpos.ui.dialog.POSMessageDialog;
import com.floreantpos.util.POSUtil;

public class RateSelectionAction extends AbstractAction {
	public RateSelectionAction() {
		super("Modify Rate");
	}

	public RateSelectionAction(String name) {
		super(name);
	}

	public RateSelectionAction(String name, Icon icon) {
		super(name, icon);
	}

	public void actionPerformed(ActionEvent e) {
		double currentRate = TerminalConfig.getRate();

		
		NumberSelectionDialog2 dialog = new NumberSelectionDialog2();
		dialog.setTitle("Modify Rate");
		dialog.setFloatingPoint(true);
		dialog.setValue(currentRate);
		dialog.pack();
//		dialog.open();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setLocationRelativeTo(POSUtil.getBackOfficeWindow());
		dialog.setVisible(true);
		
		if (dialog.isCanceled()) {
			return;
		}

		currentRate = (double) dialog.getValue();
		if (currentRate < 0 || currentRate > 1) {
			POSMessageDialog.showError(POSUtil.getBackOfficeWindow(), "Rate should be from 0.00 to 1.00");
			return;
		}
		POSMessageDialog.showMessage(POSUtil.getBackOfficeWindow(), "Set Rate : " + currentRate);
		TerminalConfig.setRate(currentRate);
		
	}
}
