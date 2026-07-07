<?php include __DIR__ . '/layout_top.php'; ?>
<h1 class="text-2xl font-bold mb-6">Marcas</h1>
<div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4">
    <?php foreach ($marcas ?? [] as $m): ?>
        <a href="/marcas/<?= htmlspecialchars($m['slug']) ?>" class="block bg-zinc-900 border border-zinc-800 rounded-lg p-4 hover:border-tobacco-600 transition">
            <h2 class="font-semibold text-lg"><?= htmlspecialchars($m['nome']) ?></h2>
            <?php if (!empty($m['fabricante'])): ?><p class="text-zinc-400 text-sm"><?= htmlspecialchars($m['fabricante']) ?></p><?php endif; ?>
        </a>
    <?php endforeach; ?>
</div>
<?php include __DIR__ . '/layout_bottom.php'; ?>
