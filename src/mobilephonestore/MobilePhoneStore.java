/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mobilephonestore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Csernay Attila
 */
public class MobilePhoneStore extends Application {
    
    private String currency = "HUF";
    private File f = new File("mobilsotresave.ini");
    
    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        
        MenuBar menubar = new MenuBar();
        menubar.setUseSystemMenuBar(true);
        
        Menu mm = new Menu("Menu");
        Menu fm = new Menu("File");
        MenuItem mi_load = new MenuItem("Load");
        MenuItem mi_save = new MenuItem("Save");
        fm.getItems().addAll(
                mi_load, mi_save,
                new MenuItem("Exit")
        );
        Menu sm = new Menu("Sell/buy phone");
        sm.getItems().addAll(
                new MenuItem("Sell phone"),
                new MenuItem("Buy phone")
        );
        Menu cm = new Menu("Currency");
        
        Menu eu = new Menu("European Union");
        
        MenuItem p = new MenuItem("HUF");
        p.setOnAction((e) -> {
            currency = "HUF";
        });
        eu.getItems().add(p);
        
        p = new MenuItem("EUR");
        p.setOnAction((e) -> {
            currency = "EUR";
        });
        eu.getItems().add(p);
        
        p = new MenuItem("CZK");
        p.setOnAction((e) -> {
            currency = "CZK";
        });
        eu.getItems().add(p);
        
        Menu o = new Menu("Others");
        
        p = new MenuItem("USD");
        p.setOnAction((e) -> {
            currency = "USD";
        });
        o.getItems().add(p);
        
        p = new MenuItem("JPY");
        p.setOnAction((e) -> {
            currency = "JPY";
        });
        o.getItems().add(p);
        
        p = new MenuItem("CAD");
        p.setOnAction((e) -> {
            currency = "CAD";
        });
        o.getItems().add(p);
        
        cm.getItems().addAll(eu, o);
        
        mm.getItems().addAll(fm, sm, cm);
        
        menubar.getMenus().add(mm);
        
        root.add(menubar, 0, 0, 2, 1);
        
        Label l = new Label("Phone manufacturer");
        root.add(l, 0, 1);
        
        ComboBox cb_gyarto = new ComboBox();
        cb_gyarto.getItems().add("Samsung");
        cb_gyarto.getItems().add("Huawei");
        cb_gyarto.getItems().add("Xaomi");
        cb_gyarto.getItems().add("Apple");
        cb_gyarto.getItems().add("Nokia");
        //cb_gyarto.setValue(cb_gyarto.getItems().get(0));
        cb_gyarto.setId("cb_gyarto");
        root.add(cb_gyarto, 1, 1);
        
        l = new Label("Phone type");
        root.add(l, 0, 2);
        
        SplitMenuButton smb = new SplitMenuButton();
        smb.setId("smb_tipus");
        //for (int i = 0; i < 5; i++) {
        //    smb.getItems().add(new MenuItem("modell " + (i + 1)));
        //}
        root.add(smb, 1, 2);
        
        Label rl = new Label("Color");
        root.add(rl, 0, 3);
        
        ToggleGroup tg = new ToggleGroup();
        
        RadioButton r1 = new RadioButton("Black");
        r1.setOnAction((e) -> {
            rl.setTextFill(Color.BLACK);
        });
        
        RadioButton r2 = new RadioButton("White");
        r2.setOnAction((e) -> {
            rl.setTextFill(Color.WHITE);
        });
        
        RadioButton r3 = new RadioButton("Gray");
        r3.setOnAction((e) -> {
            rl.setTextFill(Color.GRAY);
        });
        
        RadioButton r4 = new RadioButton("Blue");
        r4.setOnAction((e) -> {
            rl.setTextFill(Color.BLUE);
        });
        
        RadioButton r5 = new RadioButton("Green");
        r5.setOnAction((e) -> {
            rl.setTextFill(Color.GREEN);
        });
        
        tg.getToggles().addAll(r1, r2, r3, r4, r5);
        
        root.add(new HBox(r1, r2, r3, r4, r5), 1, 3);
        
        l = new Label("Extras");
        root.add(l, 0, 4);
        
        ChoiceBox chb = new ChoiceBox();
        chb.getItems().addAll("Bloutooth 5.0", "3G", "4G", "5G", "Wifi6");
        chb.setId("chb_extra");
        root.add(chb, 1, 4);
        
        l = new Label("Year of manufacturing");
        root.add(l, 0, 5);
        
        ChoiceBox chb_1 = new ChoiceBox();
        chb_1.getItems().addAll("2016", "2017", "2018", "2019", "2020");
        chb_1.setId("chb_year");
        root.add(chb_1, 1, 5);
        
        l = new Label("Size");
        root.add(l, 0, 6);
        
        ListView lv = new ListView();
        lv.getItems().addAll("6.1", "5.5", "4.0", "5.8", "4.0");
        lv.setId("lv_size");
        root.add(lv, 1, 6);
        
        l = new Label("Vendor name");
        root.add(l, 0, 7);
        TextField name = new TextField();
        root.add(name, 1, 7);
        
        l = new Label("Vendor user name");
        root.add(l, 0, 8);
        TextField username = new TextField();
        root.add(username, 1, 8);
        
        l = new Label("Vendor password");
        root.add(l, 0, 9);
        PasswordField pw = new PasswordField();
        pw.setId("pf_pw");
        Button pwgen = new Button("Generate password");
        pwgen.setId("b_pw");
        pwgen.setOnAction((e) -> {
            Random seed = new Random();
            String s = (seed.doubles() + "").substring(0, 24);
            pw.setText(Base64.getEncoder().encodeToString(s.getBytes()));
        });
        
        HBox h2 = new HBox(pw, pwgen);
        h2.alignmentProperty().set(Pos.CENTER_LEFT);
        h2.setSpacing(10);
        root.add(h2, 1, 9);
        
        l = new Label("Other data");
        root.add(l, 0, 11);
        TextArea ta = new TextArea();
        ta.maxHeight(50);
        root.add(ta, 1, 11);
        
        Label sl = new Label("Price ");
        root.add(sl, 0, 12);
        Slider s = new Slider(0, 500000, 5);
        s.valueProperty().addListener((e) -> {
            sl.setText("Price " + (int) s.getValue() + " " + this.currency);
        });
        root.add(s, 1, 12);
        
        Button save = new Button("Save");
        save.setOnAction((e) -> {
            try {
                File f = new File("mobilsotresave.ini");
                if (!f.exists()) {
                    f.createNewFile();
                }
                System.out.println(f.getAbsoluteFile());
                FileWriter fw = new FileWriter(f.getAbsoluteFile());
                fw.append("currency=" + this.currency + "\r\n");
                fw.append("manufacturer=" + cb_gyarto.getSelectionModel().getSelectedIndex() + "\r\n");
                fw.append("type=" + smb.getText() + "\r\n");
                fw.append("Color=" + ((RadioButton) tg.getSelectedToggle()).getText() + "\r\n");
                fw.append("extras=" + chb.getSelectionModel().getSelectedIndex() + "\r\n");
                fw.append("Year=" + chb.getSelectionModel().getSelectedIndex() + "\r\n");
                fw.append("screen=" + lv.getSelectionModel().getSelectedIndex() + "\r\n");
                fw.append("name=" + name.getText() + "\r\n");
                fw.append("username=" + username.getText() + "\r\n");
                fw.append("pw=" + pw.getText() + "\r\n");
                fw.append("other=" + (ta.getText().replaceAll("\r", "&#xD;").replaceAll("\n", "&#xA;").replaceAll("\t", "&#x9;")) + "\r\n");
                fw.append("price=" + s.getValue());
                
                fw.flush();
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(MobilePhoneStore.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Button cancel = new Button("Cancel");
        cancel.setOnAction((e) -> {
            this.currency = "HUF";
            rl.setTextFill(Color.BLACK);
            cb_gyarto.getSelectionModel().select(-1);
            chb.getSelectionModel().select(-1);
            chb_1.getSelectionModel().select(-1);
            lv.getSelectionModel().select(-1);
            name.setText("");
            ta.setText("");
            username.setText("");
            pw.setText("");
            smb.setText("");
            smb.getItems().clear();
            s.setValue(0);
            sl.setText("Price ");
            tg.getSelectedToggle().setSelected(false);
        });
        HBox h = new HBox(cancel, save);
        h.setSpacing(10);
        h.alignmentProperty().set(Pos.CENTER_RIGHT);
        h.setId("hb_gombok");
        root.add(h, 1, 13);
        
        cb_gyarto.setOnAction((e -> {
            System.out.println(cb_gyarto.getValue());
            smb.getItems().clear();
            smb.setText("");
            switch (cb_gyarto.getValue().toString()) {
                case "Samsung":
                    for (int i = 0; i < 10; i++) {
                        String szoveg = "Galaxy s" + (i + 1);
                        MenuItem m = new MenuItem(szoveg);
                        m.setOnAction((e1) -> {
                            smb.setText(szoveg);
                        });
                        smb.getItems().add(m);
                    }
                    break;
                case "Huawei":
                    for (int i = 0; i < 10; i++) {
                        String szoveg = "P" + (i + 1);
                        MenuItem m = new MenuItem(szoveg);
                        m.setOnAction((e1) -> {
                            smb.setText(szoveg);
                        });
                        smb.getItems().add(m);
                    }
                    break;
                case "Xaomi":
                    for (int i = 0; i < 10; i++) {
                        String szoveg = "RedMI " + (i + 1);
                        MenuItem m = new MenuItem(szoveg);
                        m.setOnAction((e1) -> {
                            smb.setText(szoveg);
                        });
                        smb.getItems().add(m);
                    }
                    break;
                case "Apple":
                    for (int i = 0; i < 10; i++) {
                        String szoveg = "Iphone " + (i + 1);
                        MenuItem m = new MenuItem(szoveg);
                        m.setOnAction((e1) -> {
                            smb.setText(szoveg);
                        });
                        smb.getItems().add(m);
                    }
                    break;
                case "Nokia":
                    Random r = new Random();
                    for (int i = 0; i < 10; i++) {
                        String szoveg = Math.abs(r.nextInt(7000) + 1000) + "";
                        MenuItem m = new MenuItem(szoveg);
                        m.setOnAction((e1) -> {
                            smb.setText(szoveg);
                        });
                        smb.getItems().add(m);
                    }
                    break;
            }
        }));
        
        mi_save.setOnAction((e) -> {
            try {
                if (!f.exists()) {
                    f.createNewFile();
                }
                System.out.println(f.getAbsoluteFile());
                FileWriter fw = new FileWriter(f.getAbsoluteFile());
                fw.append("currency=" + this.currency + "\r\n");
                fw.append("manufacturer=" + cb_gyarto.getSelectionModel().getSelectedIndex() + "\r\n");
                fw.append("type=" + smb.getText() + "\r\n");
                fw.append("color=" + ((RadioButton) tg.getSelectedToggle()).getText() + "\r\n");
                fw.append("extras=" + chb.getSelectionModel().getSelectedIndex() + "\r\n");
                fw.append("year=" + chb_1.getSelectionModel().getSelectedIndex() + "\r\n");
                fw.append("screen=" + lv.getSelectionModel().getSelectedIndex() + "\r\n");
                fw.append("name=" + name.getText() + "\r\n");
                fw.append("username=" + username.getText() + "\r\n");
                fw.append("pw=" + pw.getText() + "\r\n");
                fw.append("other=" + (ta.getText().replaceAll("\r", "&#xD;").replaceAll("\n", "&#xA;").replaceAll("\t", "&#x9;")) + "\r\n");
                fw.append("price=" + s.getValue());
                
                fw.flush();
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(MobilePhoneStore.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        mi_load.setOnAction((e) -> {
            if (f.exists()) {
                try {
                    Scanner reader = new Scanner(f);
                    while (reader.hasNextLine()) {
                        String[] data = reader.nextLine().split("=");
                        if (data.length > 1) {
                            switch (data[0]) {
                                case "currency":
                                    this.currency = data[1];
                                    break;
                                case "manufacturer":
                                    cb_gyarto.getSelectionModel().select(Integer.parseInt(data[1]));
                                    break;
                                case "type":
                                    boolean benneVan = false;
                                    
                                    for (MenuItem item : smb.getItems()) {
                                        if (item.getText() == data[1]) {
                                            benneVan = true;
                                            break;
                                        }
                                    }
                                    if (!benneVan) {
                                        String szoveg = data[1];
                                        MenuItem m = new MenuItem(szoveg);
                                        m.setOnAction((e1) -> {
                                            smb.setText(szoveg);
                                        });
                                        smb.getItems().add(m);
                                    }
                                    smb.setText(data[1]);
                                    break;
                                case "color":
                                    for (Toggle toggle : tg.getToggles()) {
                                        if (((RadioButton) toggle).getText().equalsIgnoreCase(data[1])) {
                                            ((RadioButton) toggle).setSelected(true);
                                        }
                                    }
                                    break;
                                case "extras":
                                    chb.getSelectionModel().select(Integer.parseInt(data[1]));
                                    break;
                                case "year":
                                    chb_1.getSelectionModel().select(Integer.parseInt(data[1]));
                                    break;
                                case "screen":
                                    lv.getSelectionModel().select(Integer.parseInt(data[1]));
                                    break;
                                case "name":
                                    name.setText(data[1]);
                                    break;
                                case "username":
                                    username.setText(data[1]);
                                    break;
                                case "pw":
                                    pw.setText(data[1]);
                                    break;
                                case "other":
                                    ta.setText(data[1].replaceAll("&#xD;", "\r").replaceAll("&#xA;", "\n").replaceAll("&#x9;", "\t"));
                                    break;
                                case "price":
                                    s.setValue(Double.parseDouble(data[1]));
                                    break;
                            }
                        }
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MobilePhoneStore.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Hiba a betöltéskor");
                alert.setHeaderText("Hiba a betöltéskor!");
                alert.setContentText("Nem található a \"" + this.f.getAbsoluteFile() + "\" állomány");
                alert.showAndWait();
            }
        });
        
        Scene scene = new Scene(root, 570, 720);
        
        primaryStage.setTitle("Mobile phone store");
        primaryStage.setScene(scene);
        scene.getStylesheets().add("File:Style.css");
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
