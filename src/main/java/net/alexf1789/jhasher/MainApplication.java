package net.alexf1789.jhasher;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class MainApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("JHasher");

        // base idea -> grid with:
        // file selector (all line)
        // text field (all line)
        // gap
        // text field (sha1)
        // text field(sha256)
        // text field(sha512)
        // gap
        // button 1 - button2
        GridPane root = new GridPane();

        FileChooser inputFile = new FileChooser();
        inputFile.setTitle("Choose a file...");
        inputFile.setInitialDirectory(new File(System.getProperty("user.home")));

        TextField inputText = new TextField();
        TextField inputFileName = new TextField();
        TextField verify = new TextField();

        inputText.setPromptText("Text to hash...");

        inputFileName.setPromptText("File to hash...");

        verify.setPromptText("Verify hash");

        inputFileName.setOnMouseClicked((_) -> {
            File file = inputFile.showOpenDialog(stage);

            if(file != null && file.isFile())
                inputFileName.setText(file.getAbsolutePath().toString());
        });

        TextField[] hash = {
                new TextField(),
                new TextField(),
                new TextField()
        };

        Button calculateText = new Button("Text");
        Button calculateFile = new Button("File");
        Button reset = new Button("Reset");

        calculateText.setOnMouseClicked((_) -> {
            if(inputText.getText().isBlank() || inputText.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Insert a text to calculate its hash!").show();
                return;
            }

            String[] computedHash = new TextHasher(inputText.getText()).getHash();
            for(int i=0; i<computedHash.length; i++) {
                hash[i].setText(computedHash[i]);
            }

            if(!(verify.getText().isBlank() && verify.getText().isEmpty())) {
                for(int i=0; i<computedHash.length; i++) {
                    if(verify.getText().equals(computedHash[i])) {
                        verify.setBackground(Background.fill(Color.LIGHTGREEN));
                        return;
                    }
                }

                verify.setBackground(Background.fill(Color.LIGHTCORAL));
            }
        });

        calculateFile.setOnMouseClicked((_) -> {
            if(inputFileName.getText().isBlank() || inputFileName.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Select a file to calculate its hash!").show();
                return;
            }

            String[] computedHash = new FileHasher(new File(inputFileName.getText())).getHash();
            for(int i=0; i<computedHash.length; i++) {
                hash[i].setText(computedHash[i]);
            }

            if(!(verify.getText().isBlank() && verify.getText().isEmpty())) {
                for(int i=0; i<computedHash.length; i++) {
                    if(verify.getText().equals(computedHash[i])) {
                        verify.setBackground(Background.fill(Color.LIGHTGREEN));
                        return;
                    }
                }

                verify.setBackground(Background.fill(Color.LIGHTCORAL));
            }
        });

        reset.setOnAction((_) -> {
            inputText.setText("");
            inputFileName.setText("");

            for(TextField hashField : hash) {
                hashField.setText("");
            }

            verify.setText("");
            verify.setBackground(Background.fill(Color.WHITE));
        });

        ButtonBar buttonBar = new ButtonBar();
        buttonBar.getButtons().addAll(calculateText, calculateFile, reset);
        buttonBar.setButtonOrder("U+");

        String[] hashDescriptions = {
                "SHA1",
                "SHA256",
                "SHA512"
        };

        root.setPadding(new Insets(15));
        root.setHgap(5);
        root.setVgap(5);

        int currentTableRow = 0;

        root.add(inputFileName, 0, currentTableRow++);

        root.add(inputText, 0, currentTableRow++);

        Separator sep1 = new Separator(Orientation.HORIZONTAL);
        sep1.setPadding(new Insets(30, 10, 30, 10));
        root.add(sep1, 0, currentTableRow++);

        for(int i=0; i<hash.length; i++) {
            hash[i].setPromptText(hashDescriptions[i]);

            root.add(hash[i], 0, currentTableRow++);
        }

        Separator sep2 = new Separator(Orientation.HORIZONTAL);
        sep2.setPadding(new Insets(30, 10, 30, 10));
        root.add(sep2, 0, currentTableRow++);

        root.add(verify, 0, currentTableRow++);

        Separator sep3 = new Separator(Orientation.HORIZONTAL);
        sep3.setPadding(new Insets(30, 10, 30, 10));
        root.add(sep3, 0, currentTableRow++);

        root.add(buttonBar, 0, currentTableRow);

        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

}
