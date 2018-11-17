package com.durhack.sharpshot.nodes;

import com.durhack.sharpshot.Bullet;
import com.durhack.sharpshot.Direction;
import com.durhack.sharpshot.INode;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.*;

public abstract class NodeArithmetic implements INode {
    private Direction dir = Direction.UP;

    private Bullet mostRecentBullet = null;

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
        // Ignore null bullets
        if(bullet.getValue() == null)
            return new HashMap<>();

        if(mostRecentBullet == null) {
            // Nothing to add
            mostRecentBullet = bullet;
            return new HashMap<>();
        } else {
            HashMap<Direction, BigInteger> map = new HashMap<>();
            assert mostRecentBullet.getValue() != null;
            //map.put(Direction.UP, mostRecentBullet.getValue().add(bullet.getValue()));
            map.put(Direction.UP, operation(mostRecentBullet.getValue(),bullet.getValue()));
            mostRecentBullet = null;
            return map;
        }
    }

    public abstract BigInteger operation(BigInteger val1, BigInteger val2);
}
