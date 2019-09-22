/**
 * ************************************************************************
 * * The contents of this file are subject to the MRPL 1.2
 * * (the  "License"),  being   the  Mozilla   Public  License
 * * Version 1.1  with a permitted attribution clause; you may not  use this
 * * file except in compliance with the License. You  may  obtain  a copy of
 * * the License at http://www.floreantpos.org/license.html
 * * Software distributed under the License  is  distributed  on  an "AS IS"
 * * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * * License for the specific  language  governing  rights  and  limitations
 * * under the License.
 * * The Original Code is FLOREANT POS.
 * * The Initial Developer of the Original Code is OROCUBE LLC
 * * All portions are Copyright (C) 2015 OROCUBE LLC
 * * All Rights Reserved.
 * ************************************************************************
 */
/*
 * TicketView.java
 *
 * Created on August 4, 2006, 3:42 PM
 */

package com.floreantpos.ui.views.order;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.floreantpos.IconFactory;
import com.floreantpos.Messages;
import com.floreantpos.main.Application;
import com.floreantpos.model.Ticket;
import com.floreantpos.model.TicketItem;
import com.floreantpos.model.TicketItemDiscount;
import com.floreantpos.model.TicketItemModifier;
import com.floreantpos.util.NumberUtil;

/**
 *
 * @author  MShahriar
 */
public class TicketForSplitView extends com.floreantpos.swing.TransparentPanel implements TableModelListener {
	private Ticket ticket;

	public final static String VIEW_NAME = "TICKET_FOR_SPLIT_VIEW"; //$NON-NLS-1$

	private TicketForSplitView ticketView1;
	private TicketForSplitView ticketView2;
	private TicketForSplitView ticketView3;

	private int viewNumber = 1;

	public TicketForSplitView() {
		initComponents();

		//ticketViewerTable.getColumnExt(1).setVisible(false);
		//ticketViewerTable.getColumnExt(2).setVisible(false);

		ticket = new Ticket();
		ticket.setPriceIncludesTax(Application.getInstance().isPriceIncludesTax());
		ticket.setTerminal(Application.getInstance().getTerminal());
		ticket.setOwner(Application.getCurrentUser());
		ticket.setShift(Application.getInstance().getCurrentShift());

		Calendar currentTime = Calendar.getInstance();
		ticket.setCreateDate(currentTime.getTime());
		ticket.setCreationHour(currentTime.get(Calendar.HOUR_OF_DAY));

		setOpaque(true);
		setTicket(ticket);
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		jPanel1 = new com.floreantpos.swing.TransparentPanel();
		jSeparator1 = new javax.swing.JSeparator();
		jPanel3 = new com.floreantpos.swing.TransparentPanel();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jSeparator2 = new javax.swing.JSeparator();
		jSeparator3 = new javax.swing.JSeparator();
		tfSubtotal = new javax.swing.JTextField();
		tfSubtotal.setMinimumSize(new Dimension(90, 19));
		tfSubtotal.setHorizontalAlignment(SwingConstants.TRAILING);
		tfSubtotal.setColumns(10);
		tfTax = new javax.swing.JTextField();
		tfTax.setMinimumSize(new Dimension(90, 19));
		tfTax.setHorizontalAlignment(SwingConstants.TRAILING);
		tfTax.setColumns(10);
		tfDiscount = new javax.swing.JTextField();
		tfDiscount.setMinimumSize(new Dimension(90, 19));
		tfDiscount.setHorizontalAlignment(SwingConstants.TRAILING);
		tfDiscount.setColumns(10);
		tfTotal = new javax.swing.JTextField();
		tfTotal.setMinimumSize(new Dimension(90, 19));
		tfTotal.setHorizontalAlignment(SwingConstants.TRAILING);
		tfTotal.setColumns(10);
		jPanel5 = new com.floreantpos.swing.TransparentPanel();
		btnScrollUp = new com.floreantpos.swing.PosButton();
		btnScrollDown = new com.floreantpos.swing.PosButton();
		btnTransferToTicket1 = new com.floreantpos.swing.PosButton();
		btnTransferToTicket2 = new com.floreantpos.swing.PosButton();
		btnTransferToTicket3 = new com.floreantpos.swing.PosButton();
		jPanel2 = new com.floreantpos.swing.TransparentPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		ticketViewerTable = new com.floreantpos.ui.ticket.TicketViewerTable();

		setLayout(new java.awt.BorderLayout(5, 5));

		setBorder(javax.swing.BorderFactory.createTitledBorder(null, com.floreantpos.POSConstants.TICKET, javax.swing.border.TitledBorder.CENTER,
				javax.swing.border.TitledBorder.DEFAULT_POSITION));
		setPreferredSize(new java.awt.Dimension(280, 463));
		jPanel1.setLayout(new java.awt.BorderLayout(5, 5));

		jPanel1.add(jSeparator1, java.awt.BorderLayout.CENTER);

		jPanel3.setLayout(new java.awt.GridBagLayout());

		jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); //$NON-NLS-1$
		jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabel5.setText(com.floreantpos.POSConstants.SUBTOTAL + ":"); //$NON-NLS-1$
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
		jPanel3.add(jLabel5, gridBagConstraints);

		jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); //$NON-NLS-1$
		jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabel6.setText(com.floreantpos.POSConstants.TOTAL + ":"); //$NON-NLS-1$
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(0, 5, 3, 0);
		jPanel3.add(jLabel6, gridBagConstraints);

		jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); //$NON-NLS-1$
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabel1.setText(com.floreantpos.POSConstants.DISCOUNT + ":"); //$NON-NLS-1$
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
		jPanel3.add(jLabel1, gridBagConstraints);

		jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); //$NON-NLS-1$
		jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabel2.setText(com.floreantpos.POSConstants.TAX + ":"); //$NON-NLS-1$
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
		jPanel3.add(jLabel2, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		jPanel3.add(jSeparator2, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		jPanel3.add(jSeparator3, gridBagConstraints);

		tfSubtotal.setEditable(false);
		tfSubtotal.setFont(new java.awt.Font("Tahoma", 1, 12)); //$NON-NLS-1$
		gridBagConstraints_1 = new java.awt.GridBagConstraints();
		gridBagConstraints_1.anchor = GridBagConstraints.WEST;
		gridBagConstraints_1.gridwidth = java.awt.GridBagConstraints.REMAINDER;
		gridBagConstraints_1.insets = new java.awt.Insets(3, 5, 0, 5);
		jPanel3.add(tfSubtotal, gridBagConstraints_1);

		tfTax.setEditable(false);
		tfTax.setFont(new java.awt.Font("Tahoma", 1, 12)); //$NON-NLS-1$
		gridBagConstraints_3 = new java.awt.GridBagConstraints();
		gridBagConstraints_3.anchor = GridBagConstraints.WEST;
		gridBagConstraints_3.gridx = 1;
		gridBagConstraints_3.gridy = 3;
		gridBagConstraints_3.insets = new java.awt.Insets(3, 5, 0, 5);
		jPanel3.add(tfTax, gridBagConstraints_3);

		tfDiscount.setEditable(false);
		tfDiscount.setFont(new java.awt.Font("Tahoma", 1, 12)); //$NON-NLS-1$
		gridBagConstraints_2 = new java.awt.GridBagConstraints();
		gridBagConstraints_2.anchor = GridBagConstraints.WEST;
		gridBagConstraints_2.gridx = 1;
		gridBagConstraints_2.gridy = 2;
		gridBagConstraints_2.insets = new java.awt.Insets(3, 5, 0, 5);
		jPanel3.add(tfDiscount, gridBagConstraints_2);

		tfTotal.setEditable(false);
		tfTotal.setFont(new java.awt.Font("Tahoma", 1, 12)); //$NON-NLS-1$
		gridBagConstraints_4 = new java.awt.GridBagConstraints();
		gridBagConstraints_4.anchor = GridBagConstraints.WEST;
		gridBagConstraints_4.gridx = 1;
		gridBagConstraints_4.gridy = 4;
		gridBagConstraints_4.gridwidth = java.awt.GridBagConstraints.REMAINDER;
		gridBagConstraints_4.insets = new java.awt.Insets(3, 5, 3, 5);
		jPanel3.add(tfTotal, gridBagConstraints_4);

		jPanel1.add(jPanel3, java.awt.BorderLayout.NORTH);

		jPanel5.setLayout(new java.awt.GridBagLayout());

		btnScrollUp.setIcon(IconFactory.getIcon("/ui_icons/", "up.png")); //$NON-NLS-1$ //$NON-NLS-2$
		btnScrollUp.setPreferredSize(new java.awt.Dimension(50, 45));
		btnScrollUp.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				doScrollUp(evt);
			}
		});

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
		jPanel5.add(btnScrollUp, gridBagConstraints);

		btnScrollDown.setIcon(IconFactory.getIcon("/ui_icons/", "down.png")); //$NON-NLS-1$ //$NON-NLS-2$
		btnScrollDown.setPreferredSize(new java.awt.Dimension(50, 45));
		btnScrollDown.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				doScrollDown(evt);
			}
		});

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
		jPanel5.add(btnScrollDown, gridBagConstraints);

		btnTransferToTicket1.setText("1"); //$NON-NLS-1$
		btnTransferToTicket1.setPreferredSize(new java.awt.Dimension(60, 45));
		btnTransferToTicket1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnTransferToTicket1ActionPerformed(evt);
			}
		});

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 2);
		jPanel5.add(btnTransferToTicket1, gridBagConstraints);

		btnTransferToTicket2.setText("2"); //$NON-NLS-1$
		btnTransferToTicket2.setPreferredSize(new java.awt.Dimension(60, 45));
		btnTransferToTicket2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnTransferToTicket2ActionPerformed(evt);
			}
		});

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		jPanel5.add(btnTransferToTicket2, gridBagConstraints);

		btnTransferToTicket3.setText("3"); //$NON-NLS-1$
		btnTransferToTicket3.setPreferredSize(new java.awt.Dimension(60, 45));
		btnTransferToTicket3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnTransferToTicket3ActionPerformed(evt);
			}
		});

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
		jPanel5.add(btnTransferToTicket3, gridBagConstraints);

		jPanel1.add(jPanel5, java.awt.BorderLayout.CENTER);

		add(jPanel1, java.awt.BorderLayout.SOUTH);

		jPanel2.setLayout(new java.awt.BorderLayout());

		jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		jScrollPane1.setViewportView(ticketViewerTable);

		jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

		add(jPanel2, java.awt.BorderLayout.CENTER);

	}// </editor-fold>//GEN-END:initComponents

	private void btnTransferToTicket3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferToTicket3ActionPerformed
		if (ticketView3 != null && ticketView3.isVisible()) {
			int selectedRow = ticketViewerTable.getSelectedRow();
			Object object = ticketViewerTable.get(selectedRow);

			if (object instanceof TicketItem) {
				transferTicketItem((TicketItem) object, ticketView3);
			}
		}
	}//GEN-LAST:event_btnTransferToTicket3ActionPerformed

	private void btnTransferToTicket2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferToTicket2ActionPerformed
		if (ticketView2 != null && ticketView2.isVisible()) {
			int selectedRow = ticketViewerTable.getSelectedRow();
			Object object = ticketViewerTable.get(selectedRow);

			if (object instanceof TicketItem) {
				transferTicketItem((TicketItem) object, ticketView2);
			}
		}
	}//GEN-LAST:event_btnTransferToTicket2ActionPerformed

	private void btnTransferToTicket1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferToTicket1ActionPerformed
		if (ticketView1 != null && ticketView1.isVisible()) {
			int selectedRow = ticketViewerTable.getSelectedRow();
			Object object = ticketViewerTable.get(selectedRow);

			if (object instanceof TicketItem) {
				transferTicketItem((TicketItem) object, ticketView1);
			}
		}
	}//GEN-LAST:event_btnTransferToTicket1ActionPerformed

	private void doScrollDown(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doScrollDown
		ticketViewerTable.scrollDown();
	}//GEN-LAST:event_doScrollDown

	private void doScrollUp(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doScrollUp
		ticketViewerTable.scrollUp();
	}//GEN-LAST:event_doScrollUp

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private com.floreantpos.swing.PosButton btnScrollDown;
	private com.floreantpos.swing.PosButton btnScrollUp;
	private com.floreantpos.swing.PosButton btnTransferToTicket1;
	private com.floreantpos.swing.PosButton btnTransferToTicket2;
	private com.floreantpos.swing.PosButton btnTransferToTicket3;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private com.floreantpos.swing.TransparentPanel jPanel1;
	private com.floreantpos.swing.TransparentPanel jPanel2;
	private com.floreantpos.swing.TransparentPanel jPanel3;
	private com.floreantpos.swing.TransparentPanel jPanel5;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JSeparator jSeparator2;
	private javax.swing.JSeparator jSeparator3;
	private javax.swing.JTextField tfDiscount;
	private javax.swing.JTextField tfSubtotal;
	private javax.swing.JTextField tfTax;
	private javax.swing.JTextField tfTotal;
	private com.floreantpos.ui.ticket.TicketViewerTable ticketViewerTable;
	private GridBagConstraints gridBagConstraints_1;
	private GridBagConstraints gridBagConstraints_2;
	private GridBagConstraints gridBagConstraints_3;
	private GridBagConstraints gridBagConstraints_4;

	// End of variables declaration//GEN-END:variables

	public void updateModel() {
		ticket.calculatePrice();

		/*
		 double calculatedSubtotalPrice = ticket.calculateSubtotalAmount();
		 double calculatedTax = ticket.getCalculatedTax();
		 double calculatedTotalPrice = ticket.getCalculatedTotalPrice();

		 ticket.setSubtotalAmount(calculatedSubtotalPrice);
		 ticket.setTaxAmount(calculatedTax);
		 ticket.setTotalAmount(calculatedTotalPrice);
		 */
	}

	public void updateView() {
		if (ticket == null || ticket.getTicketItems() == null || ticket.getTicketItems().size() <= 0) {
			tfSubtotal.setText(""); //$NON-NLS-1$
			tfDiscount.setText(""); //$NON-NLS-1$
			tfTax.setText(""); //$NON-NLS-1$
			tfTotal.setText(""); //$NON-NLS-1$

			return;
		}

		ticket.calculatePrice();

		tfSubtotal.setText(NumberUtil.formatNumber(ticket.getSubtotalAmount()));
		tfDiscount.setText(NumberUtil.formatNumber(ticket.getDiscountAmount()));
		tfTax.setText(NumberUtil.formatNumber(ticket.getTaxAmount()));
		tfTotal.setText(NumberUtil.formatNumber(ticket.getTotalAmount()));
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket _ticket) {
		this.ticket = _ticket;

		ticketViewerTable.setTicket(_ticket);

		updateView();
	}
	public void transferTicketItem(TicketItem ticketItem, TicketForSplitView toTicketView) {
		transferTicketItem(ticketItem, toTicketView, false);
	}
	public void transferTicketItem(TicketItem ticketItem, TicketForSplitView toTicketView,boolean fullTicketItem) {
		TicketItem newTicketItem = new TicketItem();
		
		if (!fullTicketItem)
			newTicketItem.setItemCount(1);
		else
			newTicketItem.setItemCount(ticketItem.getItemCount());

		newTicketItem.setItemId(ticketItem.getItemId());
		newTicketItem.setHasModifiers(ticketItem.isHasModifiers());
		//newTicketItem.setTicketItemModifierGroups(new ArrayList<TicketItemModifierGroup>(ticketItem.getTicketItemModifierGroups()));
		newTicketItem.setName(ticketItem.getName());
		newTicketItem.setGroupName(ticketItem.getGroupName());
		newTicketItem.setCategoryName(ticketItem.getCategoryName());
		newTicketItem.setUnitPrice(ticketItem.getUnitPrice());
		newTicketItem.setTreatAsSeat(ticketItem.isTreatAsSeat());
		newTicketItem.setSeatNumber(ticketItem.getSeatNumber());

		List<TicketItemDiscount> discounts = ticketItem.getDiscounts();
		if (discounts != null) {
			List<TicketItemDiscount> newDiscounts = new ArrayList<TicketItemDiscount>();
			for (TicketItemDiscount ticketItemDiscount : discounts) {
				TicketItemDiscount newDiscount = new TicketItemDiscount(ticketItemDiscount);
				newDiscount.setTicketItem(newTicketItem);
				newDiscounts.add(newDiscount);
			}
			newTicketItem.setDiscounts(newDiscounts);
		}

//		List<TicketItemModifierGroup> ticketItemModifierGroups = ticketItem.getTicketItemModifierGroups();
//		if (ticketItemModifierGroups != null) {
//			for (TicketItemModifierGroup modifierGroup : ticketItemModifierGroups) {
//				TicketItemModifierGroup newGroup = new TicketItemModifierGroup();
//				newGroup.setMaxQuantity(modifierGroup.getMaxQuantity());
//				newGroup.setMenuItemModifierGroup(modifierGroup.getMenuItemModifierGroup());
//				newGroup.setMinQuantity(modifierGroup.getMinQuantity());
//				newGroup.setParent(newTicketItem);
				List<TicketItemModifier> ticketItemModifiers = ticketItem.getTicketItemModifiers();
				if (ticketItemModifiers != null) {
					for (TicketItemModifier ticketItemModifier : ticketItemModifiers) {
						TicketItemModifier newModifier = new TicketItemModifier();
						newModifier.setModifierId(ticketItemModifier.getModifierId());
						newModifier.setMenuItemModifierGroupId(ticketItemModifier.getMenuItemModifierGroupId());
						newModifier.setItemCount(ticketItemModifier.getItemCount());
						newModifier.setName(ticketItemModifier.getName());
						newModifier.setUnitPrice(ticketItemModifier.getUnitPrice());
						newModifier.setTaxRate(ticketItemModifier.getTaxRate());
						newModifier.setModifierType(ticketItemModifier.getModifierType());
						newModifier.setPrintedToKitchen(ticketItemModifier.isPrintedToKitchen());
						newModifier.setShouldPrintToKitchen(ticketItemModifier.isShouldPrintToKitchen());
						newModifier.setTicketItem(newTicketItem);
						//hatran : fix split bill has not modified items
						newTicketItem.addToticketItemModifiers(newModifier);
					}
					
				}
				
//			}
//		}

		List<TicketItemModifier> addOnsList = ticketItem.getAddOns();
		if (addOnsList != null) {
			for (TicketItemModifier addOns : ticketItem.getAddOns()) {
				TicketItemModifier newAddOns = new TicketItemModifier();
				newAddOns.setModifierId(addOns.getModifierId());
				newAddOns.setMenuItemModifierGroupId(addOns.getMenuItemModifierGroupId());
				newAddOns.setItemCount(addOns.getItemCount());
				newAddOns.setName(addOns.getName());
				newAddOns.setUnitPrice(addOns.getUnitPrice());
				newAddOns.setTaxRate(addOns.getTaxRate());
				newAddOns.setModifierType(addOns.getModifierType());
				newAddOns.setPrintedToKitchen(addOns.isPrintedToKitchen()); 
				newAddOns.setShouldPrintToKitchen(addOns.isShouldPrintToKitchen());
				newAddOns.setTicketItem(newTicketItem);
				newTicketItem.addToaddOns(newAddOns);
			}
		}

		newTicketItem.setTaxRate(ticketItem.getTaxRate());
		newTicketItem.setBeverage(ticketItem.isBeverage());
		newTicketItem.setShouldPrintToKitchen(ticketItem.isShouldPrintToKitchen());
		newTicketItem.setPrinterGroup(ticketItem.getPrinterGroup());
		newTicketItem.setPrintedToKitchen(ticketItem.isPrintedToKitchen());

		int itemCount = ticketItem.getItemCount();

		toTicketView.ticketViewerTable.addTicketItem(newTicketItem);
		if (itemCount > 1 && !fullTicketItem && !ticketItem.isHasModifiers()) {
			ticketItem.setItemCount(--itemCount);
		}
		else {
			ticketViewerTable.delete(ticketItem.getTableRowNum());
		}
		repaint();
		toTicketView.repaint();
		updateView();
		toTicketView.updateView();
	}

	public void transferAllTicketItem(TicketItem ticketItem, TicketForSplitView toTicketView) {
		TicketItem newTicketItem = new TicketItem();
		newTicketItem.setItemId(ticketItem.getItemId());
		newTicketItem.setItemCount(ticketItem.getItemCount());
		newTicketItem.setHasModifiers(ticketItem.isHasModifiers());
		newTicketItem.setTicketItemModifiers(new ArrayList<TicketItemModifier>(ticketItem.getTicketItemModifiers()));
		newTicketItem.setName(ticketItem.getName());
		newTicketItem.setGroupName(ticketItem.getGroupName());
		newTicketItem.setCategoryName(ticketItem.getCategoryName());
		newTicketItem.setUnitPrice(ticketItem.getUnitPrice());
		List<TicketItemDiscount> discounts = ticketItem.getDiscounts();
		if (discounts != null) {
			List<TicketItemDiscount> newDiscounts = new ArrayList<TicketItemDiscount>();
			for (TicketItemDiscount ticketItemDiscount : discounts) {
				TicketItemDiscount newDiscount = new TicketItemDiscount(ticketItemDiscount);
				newDiscount.setTicketItem(newTicketItem);
				newDiscounts.add(newDiscount);
			}
			newTicketItem.setDiscounts(newDiscounts);
		}
		newTicketItem.setTaxRate(ticketItem.getTaxRate());
		newTicketItem.setBeverage(ticketItem.isBeverage());
		newTicketItem.setShouldPrintToKitchen(ticketItem.isShouldPrintToKitchen());
		newTicketItem.setPrinterGroup(ticketItem.getPrinterGroup());
		newTicketItem.setPrintedToKitchen(ticketItem.isPrintedToKitchen());

		toTicketView.ticketViewerTable.addAllTicketItem(newTicketItem);
		ticketViewerTable.delete(ticketItem.getTableRowNum());
		repaint();
		toTicketView.repaint();
		updateView();
		toTicketView.updateView();
	}

	public void tableChanged(TableModelEvent e) {
		if (ticket == null || ticket.getTicketItems() == null || ticket.getTicketItems().size() <= 0) {
			tfSubtotal.setText(""); //$NON-NLS-1$
			tfDiscount.setText(""); //$NON-NLS-1$
			tfTax.setText(""); //$NON-NLS-1$
			tfTotal.setText(""); //$NON-NLS-1$

			return;
		}

		ticket.calculatePrice();

		tfSubtotal.setText(NumberUtil.formatNumber(ticket.getSubtotalAmount()));
		tfDiscount.setText(NumberUtil.formatNumber(ticket.getDiscountAmount()));
		tfTax.setText(NumberUtil.formatNumber(ticket.getTaxAmount()));
		tfTotal.setText(NumberUtil.formatNumber(ticket.getTotalAmount()));
	}

	public TicketForSplitView getTicketView1() {
		return ticketView1;
	}

	public void setTicketView1(TicketForSplitView ticketView1) {
		this.ticketView1 = ticketView1;
	}

	public TicketForSplitView getTicketView2() {
		return ticketView2;
	}

	public void setTicketView2(TicketForSplitView ticketView2) {
		this.ticketView2 = ticketView2;
	}

	public TicketForSplitView getTicketView3() {
		return ticketView3;
	}

	public void setTicketView3(TicketForSplitView ticketView3) {
		this.ticketView3 = ticketView3;
	}

	public int getViewNumber() {
		return viewNumber;
	}

	public void setViewNumber(int viewNumber) {
		this.viewNumber = viewNumber;

		TitledBorder titledBorder = new TitledBorder(Messages.getString("TicketForSplitView.1") + viewNumber); //$NON-NLS-1$
		titledBorder.setTitleJustification(TitledBorder.CENTER);

		setBorder(titledBorder);

		switch (viewNumber) {
			case 1:
				btnTransferToTicket1.setIcon(IconFactory.getIcon("next.png")); //$NON-NLS-1$
				btnTransferToTicket1.setText("2"); //$NON-NLS-1$
				btnTransferToTicket2.setIcon(IconFactory.getIcon("next.png")); //$NON-NLS-1$
				btnTransferToTicket2.setText("3"); //$NON-NLS-1$
				btnTransferToTicket3.setIcon(IconFactory.getIcon("next.png")); //$NON-NLS-1$
				btnTransferToTicket3.setText("4"); //$NON-NLS-1$
				break;

			case 2:
				btnTransferToTicket1.setIcon(IconFactory.getIcon("previous.png")); //$NON-NLS-1$
				btnTransferToTicket1.setText("1"); //$NON-NLS-1$
				btnTransferToTicket2.setIcon(IconFactory.getIcon("next.png")); //$NON-NLS-1$
				btnTransferToTicket2.setText("3"); //$NON-NLS-1$
				btnTransferToTicket3.setIcon(IconFactory.getIcon("next.png")); //$NON-NLS-1$
				btnTransferToTicket3.setText("4"); //$NON-NLS-1$
				break;

			case 3:
				btnTransferToTicket1.setIcon(IconFactory.getIcon("previous.png")); //$NON-NLS-1$
				btnTransferToTicket1.setText("1"); //$NON-NLS-1$
				btnTransferToTicket2.setIcon(IconFactory.getIcon("previous.png")); //$NON-NLS-1$
				btnTransferToTicket2.setText("2"); //$NON-NLS-1$
				btnTransferToTicket3.setIcon(IconFactory.getIcon("next.png")); //$NON-NLS-1$
				btnTransferToTicket3.setText("4"); //$NON-NLS-1$

				break;

			case 4:
				btnTransferToTicket1.setIcon(IconFactory.getIcon("previous.png")); //$NON-NLS-1$
				btnTransferToTicket1.setText("1"); //$NON-NLS-1$
				btnTransferToTicket2.setIcon(IconFactory.getIcon("previous.png")); //$NON-NLS-1$
				btnTransferToTicket2.setText("2"); //$NON-NLS-1$
				btnTransferToTicket3.setIcon(IconFactory.getIcon("previous.png")); //$NON-NLS-1$
				btnTransferToTicket3.setText("3"); //$NON-NLS-1$
				break;

			default:
				throw new RuntimeException(Messages.getString("TicketForSplitView.2")); //$NON-NLS-1$
		}
	}
}
