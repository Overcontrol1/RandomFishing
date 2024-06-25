package com.overcontrol1.randomfishing.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.overcontrol1.randomfishing.RandomFishing;
import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public final class RandomFishingDataLoader implements IdentifiableResourceReloadListener {
    public static final RandomFishingDataLoader INSTANCE = new RandomFishingDataLoader();

    private static final Identifier ID = new Identifier(RandomFishing.MOD_ID, "data");

    private static final Identifier JSON_PATH = new Identifier(RandomFishing.MOD_ID, "blacklist.json");

    private static final Set<String> MOD_BLACKLIST = new ObjectOpenHashSet<>();

    public static boolean isBlacklisted(String namespace) {
        return MOD_BLACKLIST.contains(namespace);
    }

    @Override
    public Identifier getFabricId() {
        return ID;
    }

    @Override
    public CompletableFuture<Void> reload(Synchronizer synchronizer, ResourceManager manager, Profiler prepareProfiler, Profiler applyProfiler, Executor prepareExecutor, Executor applyExecutor) {
        MOD_BLACKLIST.clear();

        final CompletableFuture<Set<String>> gatherer = CompletableFuture.supplyAsync(() -> load(manager));

        return gatherer.thenCompose(synchronizer::whenPrepared)
                .thenAcceptAsync(MOD_BLACKLIST::addAll);
    }

    private static Set<String> load(ResourceManager manager) {
        final Set<String> result = new ObjectArraySet<>();
        for (Resource resource : manager.getAllResources(JSON_PATH)) {
            try {
                final JsonElement element = JsonParser.parseReader(resource.getReader());
                final JsonArray array = element.getAsJsonArray();
                array.forEach(jsonElement -> result.add(jsonElement.getAsString()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    private RandomFishingDataLoader() {}
}
