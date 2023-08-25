package gui;

import java.util.Comparator;
import java.util.LinkedList;
import model.Book;
import java.util.List;
import java.util.PriorityQueue;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ImageGallery extends Application {

    private enum SortDirection {
        ALPHABETICALLY, BY_YEAR
    };

    private ToggleGroup sortingOptions;
    private HBox toolbar;
    private ScrollPane scrollPane;
    private TilePane gallery;
    private final List<Book> allBooks = Book.loadBooks();
    private List<Book> booksDisplayed = null;
    private SortDirection sorting = SortDirection.ALPHABETICALLY;

    @Override
    public void start(Stage primaryStage) throws Exception {

        VBox mainContainer = new VBox(10);
        mainContainer.setAlignment(Pos.CENTER);

        toolbar = createToolBar();
        mainContainer.getChildren().add(toolbar);

        gallery = createTilePane();

        scrollPane = createScrollPane();
        scrollPane.setContent(gallery);
        mainContainer.getChildren().add(scrollPane);

        setActions();

        displayBooks(allBooks);
        //sortBooks();

        primaryStage.setWidth(1050);
        primaryStage.setHeight(600);
        Scene scene = new Scene(mainContainer);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Galería de Libros");
        primaryStage.show();

    }

    private HBox createToolBar() {
        HBox tb = new HBox(20);
        tb.setPadding(new Insets(10, 10, 10, 10));
        tb.setAlignment(Pos.CENTER);
        tb.getChildren().add(createSortingOptions());
        return tb;
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
        gallery.getChildren().clear();
        booksDisplayed = books;
        for (Book book : books) {
            Pane bookView = createBookView(book);
            gallery.getChildren().addAll(bookView);
        }
    }

    private Pane createBookView(Book book) {
        System.out.println("Adding: " + book.getTitle());

        VBox vbox = new VBox();
        String rutaImagen = "file:./covers/" + book.getISBN() + ".jpg";
        System.out.println(rutaImagen);
        final Image image = new Image(rutaImagen, 150, 0, true, false);
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

        return vbox;
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void setActions() {

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

    //////////////////////////////////////
    // MÉTODO QUE USTED DEBE IMPLEMENTAR
    //////////////////////////////////////
    private void sortBooks() {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        Comparator<Book> cmp = null;
        PriorityQueue<Book> sortedCola;
        
        if (sorting == SortDirection.ALPHABETICALLY) {
          
            alert.setContentText("Ordenar los libros mostrados ALFABÉTICAMENTE");
            cmp = new Comparator<Book>(){
                @Override
                public int compare(Book b1, Book b2){
                    int intCmp = b1.getTitle().toLowerCase().compareTo(b2.getTitle().toLowerCase());
                    if(intCmp == 0){
                        //No se puede usar casting
                        Integer año1 = (Integer) b1.getYear();
                        Integer año2 = (Integer) b1.getYear();
                        return año1.compareTo(año2);
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
        
        sortedCola = new PriorityQueue<>(cmp);
        for(Book b: booksDisplayed){
            sortedCola.offer(b);
        }
     
        //pasamos a otra estructura para qye se vea ordenado complegtamente
        List<Book> lista = new LinkedList<>();
        while(!sortedCola.isEmpty()){
            lista.add(sortedCola.poll());
        }
        
        displayBooks(lista);
        
        alert.show();
    }

}
