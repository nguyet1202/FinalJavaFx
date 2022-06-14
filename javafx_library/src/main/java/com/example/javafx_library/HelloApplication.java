package com.example.javafx_library;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.List;
public class HelloApplication extends Application {
    private Scene scene;
    private static final String EMPTY = "";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {

        DBConnection connection = new DBConnection();
        VBox root = new VBox();
        VBox bookRoot = new VBox();

        HBox hInsertBook = new HBox();
        TextField tfName = new TextField();
        TextField tfImage = new TextField();
        TextField tfAuthor = new TextField();
        TextField tfCategory = new TextField();
        TextField tfPrice = new TextField();
        TextField tfQuantity = new TextField();
        Button btnAdd = new Button("Add");

        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                connection.insertBook(new Book(tfName.getText(),tfImage.getText(),tfAuthor.getText(),tfCategory.getText(), Float.parseFloat(tfPrice.getText()),Integer.parseInt(tfQuantity.getText())));
                getThenDisplayBooks(bookRoot, connection);
            }
        });
        hInsertBook.getChildren().addAll(tfName, tfImage,tfAuthor,tfCategory,tfPrice,tfQuantity,btnAdd);


        root.getChildren().addAll(hInsertBook, bookRoot);

        getThenDisplayBooks(bookRoot, connection);

        Scene scene = new Scene(root, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    void displayBook(DBConnection connection, VBox root, List<Book> books) {
        root.getChildren().clear();
        for (int i = 0; i < books.size(); i++) {
            final int finialI = i;
            HBox booktBox = new HBox();
            Label lbId = new Label("" + books.get(i).id);
            Label lbName = new Label(books.get(i).name);
            Image image = new Image(books.get(i).getImage());
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitWidth(110);
            imageView.setFitHeight(110);
            Label lbAuthor = new Label(books.get(i).author);
            Label lbCategory = new Label(books.get(i).Category);
            Label lbPrice = new Label(""+books.get(i).price);
            Label lbQuantity = new Label(""+books.get(i).quantityNow);
            Button btnEdit = new Button("Edit");
            Button btnDelete = new Button("Delete");


            btnDelete.setOnAction(actionEvent -> {
                System.out.println("Click delete " + books.get(finialI).id);

                connection.deleteBook(books.get(finialI).id);
                getThenDisplayBooks(root, connection);
            });
//            btnEdit.setOnAction(actionEvent -> {
//
//            };

            booktBox.setSpacing(20);
            booktBox.getChildren().addAll(lbId, lbName, imageView,lbAuthor,lbCategory,lbPrice,lbQuantity, btnDelete);
            root.getChildren().add(booktBox);
        }
    }

    private void getThenDisplayBooks(VBox root, DBConnection connection) {
        List<Book> books = connection.getBook();
        displayBook(connection, root, books);
    }
}