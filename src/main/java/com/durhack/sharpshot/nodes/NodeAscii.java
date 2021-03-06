package com.durhack.sharpshot.nodes;

import com.durhack.sharpshot.Bullet;
import com.durhack.sharpshot.Direction;
import com.durhack.sharpshot.INode;
import com.durhack.sharpshot.gui.App;
import com.durhack.sharpshot.util.Ascii;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class NodeAscii implements INode {
    private Direction dir = Direction.UP;

    @Override
    public @NotNull Direction getRotation() {
        return dir;
    }

    @Override
    public void rotateClockwise() {
        dir = Direction.clockwiseOf(dir);
    }

    @Override
    public @NotNull Map<Direction, BigInteger> run(@NotNull Bullet bullet) {
        BigInteger value = bullet.getValue();
        if(value != null) {
            String output  = Ascii.fromBig(value) + "";
            System.out.println(output);
            App.printToOut(output);
        }
        return new HashMap<>();
    }

        @Override
    public @NotNull Node toGraphic() {
        Rectangle rectangle = new Rectangle(32.0, 32.0, Color.FIREBRICK);
        Label label = new Label("CHR");
        return new StackPane(rectangle, label);
    }

    public void reset() {}
    @Override
    public String toString() {
        return "Print Character";
    }
}
