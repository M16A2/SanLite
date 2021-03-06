import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.Iterator;
import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedGetter;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("gx")
@Implements("RouteStrategy")
public abstract class RouteStrategy {
	@ObfuscatedName("jz")
	@ObfuscatedSignature(
		signature = "Lhe;"
	)
	static Widget field2106;
	@ObfuscatedName("x")
	@ObfuscatedGetter(
		intValue = 326663409
	)
	@Export("approxDestinationX")
	public int approxDestinationX;
	@ObfuscatedName("m")
	@ObfuscatedGetter(
		intValue = -1533837037
	)
	@Export("approxDestinationY")
	public int approxDestinationY;
	@ObfuscatedName("k")
	@ObfuscatedGetter(
		intValue = 50701303
	)
	@Export("approxDestinationSizeX")
	public int approxDestinationSizeX;
	@ObfuscatedName("d")
	@ObfuscatedGetter(
		intValue = 695146693
	)
	@Export("approxDestinationSizeY")
	public int approxDestinationSizeY;

	protected RouteStrategy() {
	}

	@ObfuscatedName("x")
	@ObfuscatedSignature(
		signature = "(IIILfm;B)Z",
		garbageValue = "-112"
	)
	@Export("hasArrived")
	public abstract boolean hasArrived(int var1, int var2, int var3, CollisionMap var4);

	@ObfuscatedName("ay")
	@ObfuscatedSignature(
		signature = "(B)I",
		garbageValue = "42"
	)
	@Export("getGcDuration")
	protected static int getGcDuration() {
		int var0 = 0;
		if (GameEngine.garbageCollector == null || !GameEngine.garbageCollector.isValid()) {
			try {
				Iterator var1 = ManagementFactory.getGarbageCollectorMXBeans().iterator();

				while (var1.hasNext()) {
					GarbageCollectorMXBean var2 = (GarbageCollectorMXBean)var1.next();
					if (var2.isValid()) {
						GameEngine.garbageCollector = var2;
						GameEngine.garbageCollectorLastCheckTimeMs = -1L;
						GameEngine.garbageCollectorLastCollectionTime = -1L;
					}
				}
			} catch (Throwable var11) {
			}
		}

		if (GameEngine.garbageCollector != null) {
			long var9 = class217.currentTimeMillis();
			long var3 = GameEngine.garbageCollector.getCollectionTime();
			if (-1L != GameEngine.garbageCollectorLastCollectionTime) {
				long var5 = var3 - GameEngine.garbageCollectorLastCollectionTime;
				long var7 = var9 - GameEngine.garbageCollectorLastCheckTimeMs;
				if (var7 != 0L) {
					var0 = (int)(100L * var5 / var7);
				}
			}

			GameEngine.garbageCollectorLastCollectionTime = var3;
			GameEngine.garbageCollectorLastCheckTimeMs = var9;
		}

		return var0;
	}
}
