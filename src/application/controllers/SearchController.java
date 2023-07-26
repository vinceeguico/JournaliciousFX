package application.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.dal.JournalDAO;
import application.models.JournalModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * Controller for the "Search" screen.
 */
public class SearchController extends SceneController implements Initializable {
	@FXML TextField searchTextField;
	@FXML ListView<JournalModel> journalListView;
	
	private ObservableList<JournalModel> journalsObsList;
	private ToggleGroup radioBtnToggleGroup;
	
	
	public SearchController() {
		this.journalsObsList = FXCollections.observableArrayList();
		this.radioBtnToggleGroup = new ToggleGroup();
	}
	
	
	/**
	 * Handles mouse clicks on the "Search" screen.
	 * 
	 * @param e An event given by some user action on the application.
	 */
	public void handleBackHomeClick(ActionEvent e) {
		super.switchToView(e, View.HOME, View.SEARCH);
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {		
		// add all journals to journalListView
		this.populateJournals();
		
		// provide journalListView with an overridden cell factory
		// that defines content and styling for each cell of the list
		journalListView.setCellFactory(param -> new JournalCell());
	}
	
	
	private void populateJournals() {
		// add all journals to the observation list
		resetJournalsObsList();
		
		// populate the listView with observable list
		journalListView.setItems(this.journalsObsList);
	}
	
	
	private JournalModel getToggledJournal() {
		RadioButton selectedRadioBtn = (RadioButton) this.radioBtnToggleGroup.getSelectedToggle();
		
		JournalModel journal = null;
		if (selectedRadioBtn != null) {
			journal = (JournalModel) selectedRadioBtn.getUserData();
		}
		
		return journal;
	}
	
	
	public void handleDelete(ActionEvent e) {
		JournalModel journal = getToggledJournal();
		
		if (journal != null) {
			// delete journal from db
			JournalDAO journalDAO = new JournalDAO();
			journalDAO.deleteJournal(journal);
			
			// update observable list to reflect deletion
			this.journalsObsList.remove(journal);
		}
	}
	
	
	/**
	 * Event listener on "Enter Password" field for when key is pressed,
	 * searches keyword if the ENTER key is pressed
	 * 
	 * @param e an event given by some user action on the application
	 */
	public void handleKeyPress(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			ActionEvent actionEvent = new ActionEvent(e.getSource(), e.getTarget());
			handleSearch(actionEvent);
		}
	}
	
	
	public void handleSearch(ActionEvent e) {
		// get search keyword
		String keyword = searchTextField.getText();
		boolean keywordIsEmpty = keyword.equals("");
		
		if (!keywordIsEmpty) {
			updateJournalsObsListByKeyword(keyword);
		}
		else {
			resetJournalsObsList();
		}
		
	}
	
	private void updateJournalsObsListByKeyword(String keyword) {
		JournalDAO journalDAO = new JournalDAO();
		ArrayList<JournalModel> userJournals = journalDAO.getJournals(keyword);
		
		this.journalsObsList.clear();
		this.journalsObsList.addAll(userJournals);
	}
	
	private void resetJournalsObsList() {
		JournalDAO journalDAO = new JournalDAO();
		ArrayList<JournalModel> userJournals = journalDAO.getJournals();
		
		this.journalsObsList.clear();
		this.journalsObsList.addAll(userJournals);
	}
	
	
	/**
	 * Inner class that defines the content and styles of a journal entry cell.
	 */
	private class JournalCell extends ListCell<JournalModel> {
		private final HBox container;
		private final RadioButton radioBtn;
		private final Text title;
		private final Text date;
		private final Text context;
		
		private JournalCell() {
			radioBtn = new RadioButton();
			title = new Text();
			date = new Text();
			context = new Text();
			
			container = new HBox(radioBtn, title, date, context);
			container.setSpacing(15);
		}
		
		
		@Override
		protected void updateItem(JournalModel journal, boolean empty) {
			super.updateItem(journal, empty);
			
			if (empty || journal == null) {
				setGraphic(null);
			}
			else {
				radioBtn.setToggleGroup(radioBtnToggleGroup);
				// associate the journal with the radio button
				// so it can be retrieved from its toggle group
				radioBtn.setUserData(journal);
				
				// update rest of fields to match journal model
				title.setText(journal.getTitle());
				date.setText(journal.getDate());
				context.setText(journal.getContext());
				
				// make the cell visible
				setGraphic(container);
			}
		}
	}

}
