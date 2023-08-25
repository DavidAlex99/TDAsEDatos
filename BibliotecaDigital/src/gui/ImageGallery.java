package gui;

import model.Book;
import java.util.*;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ImageGallery extends Application {

    private enum SortDirection {
        ALPHABETICALLY, BY_YEAR
    };
    private Button allButton;
    private Button uniqueButton;
    private ToggleGroup sortingOptions;
    private Label totalLabel;
    private HBox toolbar;
    private ScrollPane scrollPane;
    private TilePane gallery;
    private final List<Book> allBooks = Book.loadBooks();
    private boolean filterRepeated = false;
    private List<Book> booksDisplayed = null;
    private SortDirection sorting = SortDirection.ALPHABETICALLY;
    private String currentYear = null;

    @Override
    public void start(Stage primaryStage) throws Exception {

        VBox mainContainer = new VBox(10);
        mainContainer.setAlignment(Pos.CENTER);

        toolbar = createToolBar();
        mainContainer.getChildren().add(toolbar);

        totalLabel = createTotalLabel();
        mainContainer.getChildren().add(totalLabel);

        gallery = createTilePane();

        scrollPane = createScrollPane();
        scrollPane.setContent(gallery);
        mainContainer.getChildren().add(scrollPane);

        setActions();
        
        displayBooks(allBooks);
        sortBooks();
        //booksDisplayed = allBooks;
        //sortBooks();

        primaryStage.setWidth(1050);
        primaryStage.setHeight(600);
        Scene scene = new Scene(mainContainer);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Galería de Libros");
        primaryStage.show();

        for (Book book : allBooks) {
            book.downloadCover();
        }

    }

    private HBox createToolBar() {

        HBox tb = new HBox(20);
        tb.setPadding(new Insets(10, 10, 10, 10));
        tb.setAlignment(Pos.CENTER);
        allButton = createAllButton();
        uniqueButton = createUniqueButton();

        tb.getChildren().add(allButton);
        tb.getChildren().add(uniqueButton);
        tb.getChildren().add(createSortingOptions());

        return tb;
    }

    private Button createAllButton() {
        Button button = new Button("Mostrar todos");
        return button;
    }

    private Button createUniqueButton() {
        Button button = new Button("Remover repetidos");
        return button;
    }

    private HBox createSortingOptions() {
        sortingOptions = new ToggleGroup();
        RadioButton rb1 = new RadioButton("Alfabéticamente");
        rb1.setToggleGroup(sortingOptions);
        rb1.setSelected(true);
        RadioButton rb2 = new RadioButton("Año");
        rb2.setToggleGroup(sortingOptions);
        HBox hbox = new HBox(20);
        hbox.getChildren().add(new Label("Ordenar por:"));
        hbox.getChildren().add(rb1);
        hbox.getChildren().add(rb2);
        return hbox;
    }

    private void updateBookCont() {
        totalLabel.setText("Mostrando " + booksDisplayed.size() + " libros");
    }

    private Label createTotalLabel() {
        Label label = new Label();
        label.setTextFill(Color.web("#872323"));
        label.setFont(Font.font("Cambria", 20));
        label.setStyle("-fx-font-weight: bold");
        return label;
    }

    private ScrollPane createScrollPane() {
        ScrollPane sp = new ScrollPane();
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Horizontal
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Vertical scroll bar
        sp.setFitToWidth(true);
        return sp;
    }

    private TilePane createTilePane() {
        TilePane tilePane = new TilePane();
        tilePane.setAlignment(Pos.CENTER);
        tilePane.setPadding(new Insets(15, 15, 15, 15));
        tilePane.setVgap(30);
        tilePane.setHgap(20);
        return tilePane;
    }

    private void displayBooks(List<Book> books) {
        //hay que limpiar los children
        gallery.getChildren().clear();
        booksDisplayed = books;
        updateBookCont();
        for (Book book : books) {
            Pane bookView = createBookView(book);
            gallery.getChildren().addAll(bookView);
        }
    }

    private Pane createBookView(Book book) {
        System.out.println("Adding: " + book.getTitle());

        VBox vbox = new VBox();
        final Image image = new Image("file:./covers/" + book.getISBN() + ".jpg", 150, 0, true, false);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(150);

        vbox.getChildren().add(imageView);

        Label titleLabel = new Label(book.getTitle());
        titleLabel.setMaxWidth(150);
        vbox.getChildren().add(titleLabel);

        Label yearLabel = new Label("" + book.getYear());
        yearLabel.setTextFill(Color.web("#0000FF"));
        yearLabel.setStyle("-fx-font-weight: bold");
        vbox.getChildren().add(yearLabel);

        yearLabel.setOnMouseClicked(event -> {
            currentYear = yearLabel.getText();
            //showBooksOfYear(yearLabel.getText());
        });

        return vbox;
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void setActions() {

        allButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                filterRepeated = false;
                showAllBooks();
            }
        });

        uniqueButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                filterRepeated = true;
                removeRepeatedBooks();
            }
        });

        sortingOptions.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) -> {
            RadioButton selection = (RadioButton) sortingOptions.getSelectedToggle();
            if (selection != null) {
                if (selection.getText().equalsIgnoreCase("Alfabéticamente")) {
                    sorting = SortDirection.ALPHABETICALLY;
                } else {
                    sorting = SortDirection.BY_YEAR;
                }
                sortBooks();
            }
        });

    }

    //********************************************************
    //METODOS QUE USTED DEBE IMPLEMENTAR EN LA PRÁCTICA
    //********************************************************
    //Solved by @ispovala
    /*private void showAllBooks() {

        booksDisplayed = allBooks;
        sortBooks();

    }

    private void removeRepeatedBooks() {

        Set<Book> noRepeatedSet = new HashSet<>();
        for (Book book : booksDisplayed) {
            noRepeatedSet.add(book);
        }

        List<Book> noRepeatedList = new LinkedList<>(noRepeatedSet);
        displayBooks(noRepeatedList);
        sortBooks();
    }

    private void sortBooks() {

        PriorityQueue<Book> booksQueue;
        Comparator<Book> cmp = null;

        if (sorting == SortDirection.ALPHABETICALLY) {
            cmp = (Book b1, Book b2) -> {
                int result = b1.getTitle().toLowerCase().compareTo(b2.getTitle().toLowerCase());
                if (result == 0) {
                    return b1.getYear() - b2.getYear();
                }
                return result;
            };
        } else if (sorting == SortDirection.BY_YEAR) {
            cmp = (Book b1, Book b2) -> {
                int result = b1.getYear() - b2.getYear();
                if (result == 0) {
                    return b1.getTitle().toLowerCase().compareTo(b2.getTitle().toLowerCase());
                }
                return result;
            };
        }

        booksQueue = new PriorityQueue<>(cmp);

        for (Book book : this.booksDisplayed) {
            booksQueue.offer(book);
        }

        List<Book> orderedBooks = new LinkedList<>();
        for (Book book : this.booksDisplayed) {
            orderedBooks.add(booksQueue.poll());
        }

        displayBooks(orderedBooks);

    }

    private void showBooksOfYear(String year) {

        Map<Integer, ArrayList<Book>> booksPerYear = new TreeMap<>();

        for (Book book : booksDisplayed) {
            if (booksPerYear.containsKey(book.getYear())) {
                booksPerYear.get(book.getYear()).add(book);
            } else {
                ArrayList<Book> books = new ArrayList<>();
                books.add(book);
                booksPerYear.put(book.getYear(), books);
            }
        }

        displayBooks(booksPerYear.get(Integer.parseInt(year)));
        sortBooks();
    }*/
    
    private void showAllBooks() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Mostrar TODOS los libros"); 
        alert.show();
        
        displayBooks(allBooks);
    }

    private void removeRepeatedBooks() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Solo mostrar libros ÚNICOS (sin repeticiones)");
        
        
        Set<Book> noRepeated = new HashSet<>();
        for(Book book: booksDisplayed){
            noRepeated.add(book);
        }
        //Pasamos la lista por el metodoDisplayBks
        displayBooks(new LinkedList<>(noRepeated));
        
        //Segun el API displayBooks(new LinkedList<>(noRepeated));de linked, hay un constructor
        /*
        public LinkedList(Collection<? extends E> c) {
            this();
            addAll(c);
        }
        */
        
        sortBooks();
        alert.show();
    }

    private void sortBooks() {
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        PriorityQueue<Book> cola;
        Comparator<Book> cmp = null;
        if (sorting == SortDirection.ALPHABETICALLY) {
            alert.setContentText("Ordenar los libros mostrados ALFABÉTICAMENTE");
            cmp = new Comparator<Book>(){
                @Override
                public int compare(Book b1, Book b2){
                    //int intCmp = b1.getTitle().compareTo(b2.getTitle());
                    int intCmp = b1.getTitle().toLowerCase().compareTo(b2.getTitle().toLowerCase());
                        if(intCmp == 0){
                            Integer year1 = (Integer) b1.getYear();
                            Integer year2 = (Integer) b2.getYear();
                            return year1.compareTo(year2);
                            
                        }
                    return intCmp;
                }
            };
        } else if (sorting == SortDirection.BY_YEAR) {
            alert.setContentText("Ordenar los libros mostrados por AÑO");
            cmp = new Comparator<Book>(){
                @Override
                public int compare(Book b1, Book b2){
                    //RECORDAR QUE NO HAY QIE USAR CASI CASTING
                    Integer year1 = (Integer) b1.getYear();
                    Integer year2 = (Integer) b2.getYear();
                    int result = year1.compareTo(year2);
                    if(result == 0){
                        return b1.getTitle().compareTo(b2.getTitle());
                    }
                    return result;
                }
            };
        }
        
        cola = new PriorityQueue<>(cmp);
        for(Book b: booksDisplayed){
            cola.offer(b);
        }
        
        //pasamos a otra estructura para qye se vea ordenado complegtamente
        List<Book> lista = new LinkedList<>();
        while(!cola.isEmpty()){
            lista.add(cola.poll());
        }
        
        displayBooks(lista);
        alert.show();
        
        
    }

    //Se pasa como argumento la fecha
    private void showBooksOfYear(String year) {
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Mostrar únicamente los libros del año " + year);
        alert.show();
        
        Map<Integer, ArrayList<Book>> booksPerYear = new TreeMap<>();
        for(Book b: booksDisplayed){
            if(booksPerYear.containsKey(b.getYear())){
                ArrayList<Book> mapYear = booksPerYear.get(b.getYear());
                mapYear.add(b);
            }
        }
    }
    

}
