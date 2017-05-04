package net.offbeatpioneer.demoapp;

import net.offbeatpioneer.demoapp.retrographicsengine.sprites.Mine;
import net.offbeatpioneer.retroengine.auxiliary.struct.quadtree.QuadTree;
import net.offbeatpioneer.retroengine.core.sprites.AbstractSprite;
import net.offbeatpioneer.retroengine.core.sprites.SpriteListGroup;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CoreEngineTest {
    @Test
    public void spriteGroupTest() throws Exception {
        assertEquals(4, 2 + 2);

        SpriteListGroup group = new SpriteListGroup();

        Mine m = new Mine();
        Mine m2 = new Mine();
        group.add(m);
        group.add(m2);

        System.out.println(group.hasChildren());
        System.out.println(group.getChildren().size());
    }

    @Test
    public void quadtree() throws Exception {
        QuadTree qtree = new QuadTree();
        qtree.DYNAMIC_MAX_OBJECTS = true;
        qtree.MAX_OBJ_TARGET_EXPONENT = 0.5;
        for(int i = 0; i < 10000; i++)
        {
            qtree.place(Math.round(Math.random()*1000), Math.round(Math.random()*1000), i);
        }

        List<QuadTree.CoordHolder> list = qtree.findAll(0, 0, 100, 100);
        for(QuadTree.CoordHolder each: list) {
            System.out.println(each.o);
        }

        QuadTree<AbstractSprite> spriteQuadTree = new QuadTree<>();
        qtree.DYNAMIC_MAX_OBJECTS = true;
        qtree.MAX_OBJ_TARGET_EXPONENT = 0.5;
    }
}