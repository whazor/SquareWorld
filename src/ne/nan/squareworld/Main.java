package ne.nan.squareworld;

import ne.nan.squareworld.generators.Finder;
import ne.nan.squareworld.generators.levels.Region;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by nanne on 19/02/16.
 */
public class Main extends JavaPlugin {
    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id)
    {
        return new YourGenerator();
    }

    private class YourGenerator extends ChunkGenerator {
        private Boolean generateBedrockBlock;
        private Region region = new Region(1337);
        private Finder finder = new Finder(region);

        public YourGenerator() {
            this.generateBedrockBlock = false;
        }

        @Override
        public ChunkData generateChunkData(World world, Random random, int x, int z, BiomeGrid biome) {
            ChunkData data = createChunkData(world);


            for (int k = 0; k < 100; k++) {
                for (int i = 0; i < 16; i++) {
                    for (int j = 0; j < 16; j++) {

                        data.setBlock(i, -1-k, j, Material.BEDROCK);
                    }
                }
            }

            Material[][] chunk = finder.getChunk(x, z);
            for (int i = 0; i < chunk.length; i++) {
                for (int j = 0; j < chunk[i].length; j++) {
                    data.setBlock(i, 0, j, chunk[i][j]);
                }
            }


            return data;
        }

        @Override
        public List<BlockPopulator> getDefaultPopulators(World world) {
            return new ArrayList<>(0);
        }

        @Override
        public Location getFixedSpawnLocation(World world, Random random) {
            return new Location(world, 10, 10, 10);
        }
    }
}