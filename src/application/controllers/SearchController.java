package application.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import application.dal.JournalDAO;
import application.models.JournalModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {		
		// add all journals to journalListView
		this.populateJournals();
		
		// provide journalListView with an overridden cell factory
		// that defines content and styling for each cell of the list
		journalListView.setCellFactory(param -> new JournalCell());
		
		// disable the default focusing behavior when clicking a cell
		journalListView.setFocusTraversable(false);
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
			// refresh list view to reflect deletion
			this.journalListView.refresh();
			
			// uncheck the radio button
			this.radioBtnToggleGroup.selectToggle(null);
		}
	}
	
	
	public void handleEdit(ActionEvent e) {
		Toggle selectedRadioBtn = this.radioBtnToggleGroup.getSelectedToggle();
		
		if (selectedRadioBtn != null) {
			JournalModel journal = (JournalModel) selectedRadioBtn.getUserData();
			super.switchToEditView(e, journal);	
		}
	}
	
	
	/**
	 * Handles mouse clicks on the "Search" screen.
	 * 
	 * @param e An event given by some user action on the application.
	 */
	public void handleBackClick(ActionEvent e) {
		super.switchToPrevView(e, View.SEARCH);
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
		// layout panes
		private final HBox container;
		private final VBox journalInfoContainer;
		private final VBox journalContentContainer;
		
		// visual elements
		private final RadioButton radioBtn;
		private final Text title;
		private final Text date;
		private final Text time;
		private final Text context;
		
		// styling constants
		private static final int TITLE_FONT_SIZE = 15;
		private static final int DEFAULT_FONT_SIZE = 12;
		private static final int PREVIEW_MAX_NUM_LINES = 4;
		private static final int PREVIEW_MAX_LINE_LENGTH = 55;
		private static final String ELIPSES = "...";
		
		private JournalCell() {
			radioBtn = new RadioButton();
			title = new Text();
			date = new Text();
			time = new Text();
			context = new Text();
			
			// configure radio buttons to select list view's cell when clicked
			radioBtn.setOnAction(event -> updateListViewSelection());
			radioBtn.setOnMouseClicked(event -> updateListViewSelection());
			
			title.setStyle("-fx-font-weight: bold;");
			title.setFont(new Font(TITLE_FONT_SIZE));
			
			Font defaultFont = new Font(DEFAULT_FONT_SIZE);
			date.setFont(defaultFont);
			time.setFont(defaultFont);
			
			
			journalInfoContainer = new VBox(title, date, time);
			journalInfoContainer.setMinWidth(150);
			journalInfoContainer.setMaxWidth(150);
			journalInfoContainer.setSpacing(10);
			
			journalContentContainer = new VBox(context);
			
			container = new HBox(radioBtn, journalInfoContainer, journalContentContainer);
			container.setPadding(new Insets(5, 0, 5, 0));
			container.setSpacing(15);
		}
		
		
		/**
		 * When radio button is clicked, update the list view selection as well
		 */
		private void updateListViewSelection() {
			ListView<JournalModel> listView = this.getListView();
			
			if (radioBtn.isSelected()) {
				listView.getSelectionModel().select(this.getIndex());
			}
			else {
				listView.getSelectionModel().clearSelection(this.getIndex());
			}
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
				
				radioBtn.setSelected(getListView().getSelectionModel().isSelected(this.getIndex()));
				
				// update rest of fields to match journal model
				title.setText(journal.getTitle());
				date.setText(journal.getDate());
				time.setText(journal.getTime());
				
				String previewText = constructPreviewText(journal.getContext());
				context.setText(previewText);
				
				// make the cell visible
				setGraphic(container);
			}
		}
		
		
		@Override
		public void updateSelected(boolean selected) {
			// toggle the radio button of the cell
			radioBtnToggleGroup.selectToggle(this.radioBtn);
		}
		
		
		private String constructPreviewText(String text) {
			String previewText = "";
			
			Scanner in = new Scanner(text);
			int lineCount = 0;
			while (in.hasNext() && lineCount < PREVIEW_MAX_NUM_LINES) {
				String line = in.nextLine();
				if (line.length() > PREVIEW_MAX_LINE_LENGTH) {
					line = line.substring(0, PREVIEW_MAX_LINE_LENGTH - ELIPSES.length());
					line += ELIPSES;
				}
				previewText += line;
				previewText += "\n";
				
				lineCount++;
			}
			
			return previewText;
		}
	}

}
