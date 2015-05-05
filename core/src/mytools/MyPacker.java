package mytools;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

/**
 * Created by wpower on 4/24/15.
 */
public class MyPacker {

    public static void main (String[] args) throws Exception {

        String inputDir = "android/assets/raw/unpacked/terrain";
        String outputDir = "android/assets/skins";
        String packFileName = "terrainPack";

        TexturePacker.process(inputDir, outputDir, packFileName);
    }
}
