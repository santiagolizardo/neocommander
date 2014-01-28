/**
 * This file is part of MadCommander, a file manager with two panels.
 *
 * MadCommander is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MadCommander is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MadCommander.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.santiagolizardo.madcommander.dialogs.changeattributes;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.text.MaskFormatter;

public class DateTimePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = -2651548649686614311L;

	private JButton now;
	private JLabel dateLabel;
	private JFormattedTextField date;
	private JLabel timeLabel;
	private JFormattedTextField time;
	
	public DateTimePanel() {
		super();
		
		setBorder(BorderFactory.createTitledBorder(" Date time "));
		
		Dimension size = new Dimension(220, 100);
		setMinimumSize(size);
		setPreferredSize(size);
		
		now = new JButton("Now");
		now.addActionListener(this);
		
		dateLabel = new JLabel("Date: ");
		try {
			MaskFormatter formatter = new MaskFormatter("##/##/####");
			date = new JFormattedTextField(formatter);
		} catch (ParseException p) {
			p.printStackTrace();
		}
	
		timeLabel = new JLabel("Time: ");
		try {
			MaskFormatter formatter = new MaskFormatter("##:##");		
			time = new JFormattedTextField(formatter);
		} catch (ParseException p) {
			p.printStackTrace();
		}
		
		updateDateTime();
		
		defineLayout();
	}
	
	public void actionPerformed(ActionEvent event) {
		updateDateTime();
	}
	
	public String getDate() {
		return date.getText();
	}
	
	public String getTime() {
		return time.getText();
	}
	
	private void updateDateTime() {
		Calendar calendar = new GregorianCalendar();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

		date.setText(dateFormat.format(calendar.getTime()));
		time.setText(timeFormat.format(calendar.getTime()));		
	}
	
	private void defineLayout() {
		SpringLayout layout = new SpringLayout();
		layout.putConstraint(SpringLayout.WEST, dateLabel, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, dateLabel, 5, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.WEST, date, 42, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, date, 5, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, now, 5, SpringLayout.EAST, date);
		layout.putConstraint(SpringLayout.NORTH, now, 5, SpringLayout.NORTH, this);
				
		layout.putConstraint(SpringLayout.WEST, timeLabel, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, timeLabel, 5, SpringLayout.SOUTH, dateLabel);

		layout.putConstraint(SpringLayout.WEST, time, 42, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, time, 5, SpringLayout.SOUTH, date);
		
		setLayout(layout);
		
		add(now);
		add(dateLabel);
		add(date);
		add(timeLabel);
		add(time);
	}
}
