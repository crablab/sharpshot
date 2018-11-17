package com.durhack.sharpshot.gui;

import com.durhack.sharpshot.Bullet;
import com.durhack.sharpshot.Coordinate;
import com.durhack.sharpshot.Direction;
import com.durhack.sharpshot.INode;
import com.durhack.sharpshot.nodes.Container;
import com.durhack.sharpshot.nodes.NodeAdd;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.Map;

public class Grid extends Application {
    private GridPane pane = new GridPane();
    private Container container;

    public Grid() {
        container = new Container(10, 5);
        container.getBullets().put(new Coordinate(1, 2), new Bullet(Direction.DOWN, BigInteger.ONE));
        container.getNodes().put(new Coordinate(1, 2), new NodeAdd());
    }

    private void render() {
        pane.getChildren().clear();

        for (int x = 0; x < container.getWidth(); x++) {
            for (int y = 0; y < container.getHeight(); y++) {
                pane.add(emptyGraphic(), x, y);
            }
        }

        for (Map.Entry<Coordinate, INode> nodeLocation : container.getNodes().entrySet()) {
            Coordinate coordinate = nodeLocation.getKey();
            INode node = nodeLocation.getValue();
            pane.add(toGraphic(node), coordinate.getX(), coordinate.getY());
        }

        for (Map.Entry<Coordinate, Bullet> bulletLocations : container.getBullets().entrySet()) {
            Coordinate coordinate = bulletLocations.getKey();
            Bullet bullet = bulletLocations.getValue();
            pane.add(toGraphic(bullet), coordinate.getX(), coordinate.getY());
        }
    }

    private void tick() {
        container.tick();
        render();
    }

    private Node emptyGraphic(){
        return new Rectangle(32.0, 32.0, Color.WHITE);
    }

    @NotNull
    private Node toGraphic(@NotNull INode node) {
        return new Rectangle(32.0, 32.0, Color.RED);
    }

    @NotNull
    private Node toGraphic(@NotNull Bullet bullet) {
        StackPane stackPane = new StackPane();

        Rectangle background = new Rectangle(16.0, 16.0, Color.WHEAT);
        stackPane.getChildren().add(background);

        BigInteger value = bullet.getValue();
        if (value != null) {
            Label label = new Label(value.toString());
            stackPane.getChildren().add(label);
        }

        return stackPane;
    }

    public static void main(String[] args) {
        launch(Grid.class, args);
    }

    @Override
    public void start(Stage primaryStage) {
        render();
        Scene rootScene = new Scene(pane);
        primaryStage.setScene(rootScene);
        primaryStage.show();
    }
}
