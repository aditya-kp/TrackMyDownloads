# TrackMyDownloads

TagComboBox in the searcg GUI now populates tags from the database when the searchGUI is created 
Listeners added to the searchField, runButton, openLocationButton and searchButton

//Note : does the populator add a file that was already added if we select it again ??

To Do 

1. Connect the search button listener to the database and display result when it is pressed 
2. When an item is pressed in the result display its detail 
3. Figure a way out to hide and show the file details depending on the context 
4. Implement Run Button [ link in the master branch read me ] 
5. Implement Open Location Button [ probably the same thing as run button ... just removing the file name from the uri might work]
6. Add a delete file button which will delete entry from file and tagged_to tables 
7. Make the UI unified ... They look like they are whole different programs 
