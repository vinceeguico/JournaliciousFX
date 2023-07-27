package application.controllers;

import java.time.LocalDate;

import application.dal.JournalDAO;
import application.models.JournalModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EditController extends CreateController {
	@FXML private TextField titleField;
	@FXML private DatePicker datePicker;
	@FXML private Spinner<Integer> hourSpinner;
	@FXML private Spinner<Integer> minuteSpinner;
	@FXML private TextArea journalContextArea;
	
	private JournalModel journal;
	

	public void init(JournalModel journal) {
		this.journal = journal;
		
		// add event listener to the time spinners 
		// that updates the field when the user clicks out of the spinner
		super.addFocusLostEventListener(hourSpinner);
		super.addFocusLostEventListener(minuteSpinner);
		
		// set the limits of the hour spinner
		SpinnerValueFactory<Integer> hourValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
		hourValueFactory.setConverter(TIME_FORMAT_CONVERTER);
		
		// set the limits of the minute spinner
		SpinnerValueFactory<Integer> minuteValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
		minuteValueFactory.setConverter(TIME_FORMAT_CONVERTER);
		
		// fill in fields with journal data
		fillOutFields(journal, hourValueFactory, minuteValueFactory);
		
		// initialize time spinners
		hourSpinner.setValueFactory(hourValueFactory);
		minuteSpinner.setValueFactory(minuteValueFactory);
	}
	
	
	private void fillOutFields(JournalModel journal, SpinnerValueFactory<Integer> hourValueFactory, SpinnerValueFactory<Integer> minuteValueFactory) {
		// fill out the title
		titleField.setText(journal.getTitle());
				
		// fill out the date
		LocalDate date = LocalDate.parse(journal.getDate());
		datePicker.setValue(date);
				
		// fill out the time
		hourValueFactory.setValue(journal.getHour());
		minuteValueFactory.setValue(journal.getMinute());
				
		// fill out the context
		journalContextArea.setText(journal.getContext());
	}
	
	@Override
	public void handleSave(ActionEvent e) {
		int id = this.journal.getID();
		
		// get title and context
		String title = titleField.getText();
		String context = journalContextArea.getText();
				
		// get time fields
		int hour = hourSpinner.getValue();
		int minute = minuteSpinner.getValue();
				
		// get date
		LocalDate enteredDate = datePicker.getValue();
		String date = enteredDate.toString();
				
		// update journal in DB
		JournalDAO journalDAO = new JournalDAO();
		journalDAO.updateJournal(id, title, date, hour, minute, context);

		
		// display success message (TODO!) and switch to home page
		super.switchToPrevView(e, View.CREATE);
	}
	
}
