import javafx.application.Application;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.CheckBox;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.application.Platform;

import java.time.LocalDate;


public class GUI extends Application {
    private Hotel hotel = new Hotel();
    public static class RoomInfo {
        String name;
        String imagePath;
        double price;
        Room.RoomType type;

        public RoomInfo(String name, String imagePath, double price, Room.RoomType type) {
            this.name = name;
            this.imagePath = imagePath;
            this.price = price;
            this.type = type;
        }
    }
    private List<RoomInfo> roomsData = new ArrayList<>();
    private void initRooms(){
        roomsData.add(new RoomInfo("Single Room", "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%2Fid%2FOIP.G37UxDfCrbDvwNakQxbhrgHaE8%3Fcb%3Ducfimg2%26pid%3DApi%26ucfimg%3D1&f=1&ipt=d2d867bb6ddc85788dbb914050ebf350a7672ca02350856be497ffd49df6176c&ipo=images", 100, Room.RoomType.SINGLE));
        roomsData.add(new RoomInfo("Double Room", "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse3.mm.bing.net%2Fth%2Fid%2FOIP.x_pdWXwyGyz9zZQGUxEeRQHaE7%3Fpid%3DApi&f=1&ipt=00db6854772080053c9da762ce9a03fa90964f82d5f95a7cc2ea89f1f8e518bc", 160, Room.RoomType.DOUBLE));
        roomsData.add(new RoomInfo("Triple Room", "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.arkadi-hotel.gr%2Fwp-content%2Fuploads%2F2021%2F02%2FArkadi-Hotel-Chania-triple-Room-8-1024x683.jpg&f=1&nofb=1&ipt=2a6de4090abb0ab8eec23c22ded514cd853d3c56cbfcae1d1b08da53060d48c5", 210, Room.RoomType.TRIPLE));
        roomsData.add(new RoomInfo("Suite", "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fcache.marriott.com%2Fmarriottassets%2Fmarriott%2FLASJW%2Flasjw-suite-0084-hor-clsc.jpg%3Finterpolation%3Dprogressive-bilinear%26&f=1&nofb=1&ipt=b0b989bf02c870229b7a9b71065fb2d3989dc7eea724c79be41a3eb6fd3f293b", 250, Room.RoomType.SUITE));
    }
    @Override
    public void start(Stage primaryStage) {
        initRooms();
        VBox root = new VBox(10);
        root.setStyle("-fx-background-color: #f0f0f5; -fx-padding: 15;");

        HBox header = new HBox(10);
        Button exitBtn = new Button("Exit");
        exitBtn.setStyle("-fx-background-color: #2E8B57; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5;");
        exitBtn.setOnAction(e -> System.exit(0));
        header.getChildren().add(exitBtn);

        FlowPane roomsPane = new FlowPane(10, 10);
        roomsPane.setPrefWrapLength(800);

        for (RoomInfo info : roomsData) {
            VBox card = new VBox(5);
            card.setStyle("-fx-background-color: #ffffff; -fx-padding: 10; -fx-border-radius: 10; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5,0,0,2);");

            // Customize image size if needed
            ImageView img = new ImageView(new Image(info.imagePath));
            img.setFitWidth(200);
            img.setFitHeight(150);
            img.setPreserveRatio(true);

            Button bookBtn = new Button("Book Now");
            bookBtn.setStyle("-fx-background-color: #2E8B57; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5;");
            bookBtn.setOnAction(e -> openRoomBooking(info.type));

            Label nameLabel = new Label(info.name);
            nameLabel.setFont(new Font("Arial", 16));
            nameLabel.setTextFill(Color.BLACK);

            Label priceLabel = new Label("$" + info.price + "/night");
            priceLabel.setTextFill(Color.web("#2E8B57"));
            priceLabel.setFont(new Font("Arial", 14));

            card.getChildren().addAll(img, nameLabel, priceLabel, bookBtn);
            roomsPane.getChildren().add(card);

            hotel.addRoom(info.type);
        }

        ScrollPane scrollPane = new ScrollPane(roomsPane);
        scrollPane.setFitToWidth(true);

        root.getChildren().addAll(header, scrollPane);

        Scene scene = new Scene(root, 900, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hotel Booking");
        primaryStage.show();
    }

    private void openRoomBooking(Room.RoomType type) {
        Stage stage = new Stage();
        stage.setTitle("Book a Room");

        DatePicker startDatePicker = new DatePicker();
        DatePicker endDatePicker = new DatePicker();
        Spinner<Integer> peopleSpinner = new Spinner<>(1, 6, 1);
        CheckBox massageCheckBox = new CheckBox("Massage (+10% for Suite)");
        Button suggestBtn = new Button("Suggest Rooms");
        Button bookBtn = new Button("Confirm Booking");
        Label messageLabel = new Label();

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("Check-in Date:"), 0, 0);
        grid.add(startDatePicker, 1, 0);
        grid.add(new Label("Check-out Date:"), 0, 1);
        grid.add(endDatePicker, 1, 1);
        grid.add(new Label("Number of People:"), 0, 2);
        grid.add(peopleSpinner, 1, 2);
        grid.add(new Label("Room Type:"), 0, 3);
        grid.add(new Label(type.toString()), 1, 3);
        grid.add(massageCheckBox, 1, 4);
        grid.add(suggestBtn, 0, 5);
        grid.add(bookBtn, 1, 5);
        grid.add(messageLabel, 0, 6, 2, 1);

        suggestBtn.setOnAction(e -> messageLabel.setText("Booking options ready"));

        bookBtn.setOnAction(e -> {
            LocalDate start = startDatePicker.getValue();
            LocalDate end = endDatePicker.getValue();
            if (start == null || end == null || end.isBefore(start)) {
                messageLabel.setText("Invalid dates selected.");
                return;
            }
            boolean booked = false;
            for (Room room : hotel.getRooms()) {
                if (room.getType() == type && hotel.isRoomAvailable(room, start, end)) {
                    hotel.createBooking(room.getId(), "Guest", start, end, massageCheckBox.isSelected());
                    messageLabel.setText("Booking successful!");
                    booked = true;
                    break;
                }
            }
            if (!booked) messageLabel.setText("No available rooms for selected criteria.");
        });

        stage.setScene(new Scene(grid, 400, 350));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}