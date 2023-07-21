package application.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

import application.dal.JournalDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

/**
 * Controller for the "Create" page.
 */
public class CreateController extends SceneController implements Initializable {
	@FXML private TextField titleField;
	@FXML private DatePicker datePicker;
	@FXML private Spinner<Integer> hourSpinner;
	@FXML private Spinner<Integer> minuteSpinner;
	@FXML private TextArea journalContextArea;
	
	
	/**
	 * A custom string converter object that formats times
	 */
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
				return -1;
			}
		}
	};
	
	
	// event listener that checks if spinners were clicked in/out of
	private void addFocusLostEventListener(Spinner spinner) {
		// addListener takes ChangeListener Functional Interface implementation as argument
		spinner.getEditor().focusedProperty().addListener((observableValue, previousValue, newValue) -> {
			// If there is not a new value, reset spinner to default
			if (!newValue) {
				spinner.increment(0);
			}
		});
	}
	
	/**
	 * Initializes the page's fields and autofills each field
	 * with the default value
	 * 
	 * @param location the location of a file or directory
	 * @param resources the resources required to locate the root element
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// add event listener to the time spinners 
		// that updates the field when the user clicks out of the spinner
		addFocusLostEventListener(hourSpinner);
		addFocusLostEventListener(minuteSpinner);
		
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
		String title = titleField.getText();
		String context = journalContextArea.getText();
		
		boolean fieldsAreEdited = (!title.equals("") || !context.equals(""));
		if (fieldsAreEdited) {
			this.showAlert(e);
		}
		else {
			super.switchToView(e, View.HOME, View.CREATE);
		}
	}
	
	
	// displays alert message warning user they will lose their progress
	private void showAlert(ActionEvent e) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirm Leaving Page");
		alert.setHeaderText("Warning!");
		alert.setContentText("Are you sure you would like to leave this page? All progress will be lost if you select \"OK\".");
		
		// if user hits OK, redirect to home page
		alert.setOnCloseRequest(event -> {
			if (alert.getResult() == ButtonType.OK) {
				super.switchToView(e, View.HOME, View.CREATE);
			}
		});
		
		// display alert after configuration
		alert.show();
	}
	
	
	/**
	 * Handles logic for clicking the Save button on the Create page
	 * 
	 * @param e an event given by some user action on the application
	 */
	public void handleSave(ActionEvent e) {
		// get title and context
		String title = titleField.getText();
		String context = journalContextArea.getText();
		
		// get time fields
		int hour = hourSpinner.getValue();
		int minute = minuteSpinner.getValue();
		
		// get date
		LocalDate enteredDate = datePicker.getValue();
		String date = enteredDate.toString();
		
		// update DB
		JournalDAO journalDao = new JournalDAO();
		journalDao.createJournal(title, date, hour, minute, context);
		
		// display success message (TODO!) and switch to home page
		super.switchToView(e, View.HOME, View.CREATE);
	}	
}
