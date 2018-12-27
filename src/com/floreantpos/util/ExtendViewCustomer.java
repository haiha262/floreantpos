package com.floreantpos.util;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import com.floreantpos.bo.ui.BackOfficeWindow;
import com.floreantpos.config.TerminalConfig;
import com.floreantpos.demo.KitchenDisplayView;
import com.floreantpos.extension.ExtensionManager;
import com.floreantpos.extension.OrderServiceExtension;
import com.floreantpos.extension.OrderServiceFactory;
import com.floreantpos.model.OrderType;
import com.floreantpos.model.dao.OrderTypeDAO;
import com.floreantpos.ui.HeaderPanel;
import com.floreantpos.ui.views.CustomerView;
import com.floreantpos.ui.views.IView;
import com.floreantpos.ui.views.LoginView;
import com.floreantpos.ui.views.SwitchboardOtherFunctionsView;
import com.floreantpos.ui.views.SwitchboardView;
import com.floreantpos.ui.views.TableMapView;
import com.floreantpos.ui.views.order.RootView;
import com.floreantpos.ui.views.payment.SettleTicketDialog;
import com.floreantpos.util.TicketAlreadyExistsException;

public class ExtendViewCustomer  extends com.floreantpos.swing.TransparentPanel {
	private HeaderPanel headerPanel = new HeaderPanel();
	private static ExtendViewCustomer instance;

	private ExtendViewCustomer() {
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(3, 3, 3, 3));

		initView();

	}
	private void initView() {
		headerPanel.setVisible(false);
		add(headerPanel, BorderLayout.NORTH);//hatran head menu 

		//add(contentPanel); // hatran content main menu
	}
	public synchronized static ExtendViewCustomer getInstance() {
		if (instance == null) {
			instance = new ExtendViewCustomer();
		}
		return instance;
	}
	public void showText(String text)
	{
		removeAll();
		JLabel lblDeliveryCharge = new javax.swing.JLabel();
		lblDeliveryCharge.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblDeliveryCharge.setText(text); //$NON-NLS-1$ //$NON-NLS-2$
		add(lblDeliveryCharge,BorderLayout.CENTER);//hatran head menu 
	}
}
