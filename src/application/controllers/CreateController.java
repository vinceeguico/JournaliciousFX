package application.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

import application.dal.JournalDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

/**
 * Controller for the "Create" page.
 * 
 * @author Vince Eguico
 */

public class CreateController extends SceneController implements Initializable {
	@FXML private TextField titleField;
	@FXML private DatePicker datePicker;
	@FXML private Spinner<Integer> hourSpinner;
	@FXML private Spinner<Integer> minuteSpinner;
	@FXML private TextArea journalContextArea;
	
	// formats the hour and minute spinners to have leading 0s
	private static final StringConverter<Integer> TIME_FORMAT_CONVERTER = new StringConverter<Integer>() {
		@Override
		public String toString(Integer val) {
			// pad the time element's value with 0s if it is a single digit
			return String.format("%02d", val);
		}

		@Override
		public Integer fromString(String str) {
			try {
				
				return Integer.parseInt(str);
				
			} catch (NumberFormatException ex) {
				return 0;
			}
		}
	};
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// set the limits of the hour spinner
		SpinnerValueFactory<Integer> hourValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
		hourValueFactory.setConverter(TIME_FORMAT_CONVERTER);
		
		// set the limits of the minute spinner
		SpinnerValueFactory<Integer> minuteValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
		minuteValueFactory.setConverter(TIME_FORMAT_CONVERTER);
		
		
		// autofill the date
		datePicker.setValue(LocalDate.now());
		
		// autofill the time
		LocalTime currTime = LocalTime.now();
		hourValueFactory.setValue(currTime.getHour());
		minuteValueFactory.setValue(currTime.getMinute());
		
		// initialize time spinners
		hourSpinner.setValueFactory(hourValueFactory);
		minuteSpinner.setValueFactory(minuteValueFactory);
	}
	
	
	/**
	 * Handles clicks on "Home" button.
	 * 
	 * @param e An event given by some user action on the application
	 */
	public void handleBackHomeClick(ActionEvent e) {
		super.switchToView(e, View.HOME, View.CREATE);
	}
	
	public void handleSave(ActionEvent e) {
		// get title and context
		String title = titleField.getText();
		String context = journalContextArea.getText();
		
		// get date
		LocalDate enteredDate = datePicker.getValue();
		String date = enteredDate.toString();
		
		// check for valid time
		int hour = hourSpinner.getValue();
		int minute = minuteSpinner.getValue();
		
		// update DB
		JournalDAO journalDao = new JournalDAO();
		journalDao.createJournal(title, date, hour, minute, context);
		
		// display success message (TODO!) and switch to home page
		super.switchToView(e, View.HOME, View.CREATE);
	}
	
	public void handleCancel(ActionEvent e) {
		// check for unfinished work and display warning msg
		
		// switch to home page
	}
	
}
